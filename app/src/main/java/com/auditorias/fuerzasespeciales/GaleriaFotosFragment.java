package com.auditorias.fuerzasespeciales;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters.GaleriaFotosAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class GaleriaFotosFragment extends Fragment implements View.OnClickListener {

    private final List<String> listFotos = new ArrayList<>();
    private GaleriaFotosAdapter galeriaFotosAdapter;
    private Uri uri;
    private String imagen;
    private String extenciion;
    private String tamanio;
    private String uriStringPDF;
    private String navigationFragment;
    //TODO: son todos los view del fragment
    private View view;
    //TODO: es el activity del fragment
    private Activity activity;
    private ImageView imagenfeo;
    private LinearLayout folderEmpty;
    private String nombreFoto;
    private String currentPhotoPath;
    private String nombreDeArchivoFoto;
    private Double valorDeConfiguracionFileMaxSize;
    private String descripcionConfiguracionFileMaxSize;
    private Integer valorDeConfiguracionPhotoNumber;
    private String descripcionConfiguracionPhotoNumber;
    private String idCaso;
    private String idCasoFase;
    private String justificacion;
    private String tipoAppMovil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_galeria_tomar_fotos, container, false);
        activity = getActivity();
        //TODO: es el context del fragment
        //Context context = getContext();
        //TODO: es el fragmentmanager del fragment
        //FragmentManager fragmentManager = getFragmentManager();

        setEnlaces(view);

        getObtenerConfiguracionFileMaxSize(activity);
        getObtenerConfiguracionPhotoNumber(activity);

        //TODO: todo referente a los dtaos de tranferencia de fragments
        Bundle args = getArguments();
        if (args != null) {
            idCaso = args.getString("idCaso");
            idCasoFase = args.getString("idCasoFase");
            navigationFragment = args.getString("navigationFragment");
            justificacion = args.getString("justificacion");
            tipoAppMovil = args.getString("tipoAppMovil");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //para versiones con android 10.0 o superior.
            if ((activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constantes.RESPUESTA_DE_CAMARA);
            }
        } else {
            //para versiones inferiores a android 10.0.
            myRequestStoragePermission(activity);
        }
        return view;
    }

    public void setEnlaces(View view) {

        folderEmpty = view.findViewById(R.id.linearLayoutAdvertenciaFotosGTFF);
        imagenfeo = view.findViewById(R.id.imagenViewAdvertenciaFotosGTFF);

        //TODO: son todos los botones del fragment
        ImageButton buttonAgregarFotoGTFF = view.findViewById(R.id.buttonAgregarFotoGTFF);
        buttonAgregarFotoGTFF.setOnClickListener(this);

        ImageButton buttonSavePDFGTFF = view.findViewById(R.id.buttonGuardarPDFGTFF);
        buttonSavePDFGTFF.setOnClickListener(this);

        RecyclerView recyclerViewGaleria = view.findViewById(R.id.recyclerViewGaleria);
        galeriaFotosAdapter = new GaleriaFotosAdapter(activity, listFotos, new GaleriaFotosAdapter.OnListener() {
            @Override
            public void onItemSelectedListener(String fotoString , int posicion) {
                showAlertDialogEliminarEnvidencia(activity, getString(R.string.text_label_liminar_evidencia), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), posicion);
            }
        });
        recyclerViewGaleria.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new GridLayoutManager(activity, 3);
        recyclerViewGaleria.setLayoutManager(layoutManagerCategory);
        recyclerViewGaleria.setNestedScrollingEnabled(false);
        recyclerViewGaleria.setAdapter(galeriaFotosAdapter);
    }

    private void getObtenerConfiguracionPhotoNumber(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionPhotoNumber = Integer.parseInt(respuestaGeneral.getConfiguracionData().getValor());
                            descripcionConfiguracionPhotoNumber = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.photoNumber));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getObtenerConfiguracionFileMaxSize(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionFileMaxSize = (double) Utils.getByteToMegas(Long.parseLong(respuestaGeneral.getConfiguracionData().getValor()));
                            descripcionConfiguracionFileMaxSize = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.fileMaxSize));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlertDialogEliminarEnvidencia(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //clearFocusEditText(activity);
                if (!listFotos.isEmpty()) {
                    listFotos.remove(position);
                    galeriaFotosAdapter.notifyDataSetChanged();
                }
            }
        });
        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAgregarFotoGTFF:
                if (listFotos.size() < valorDeConfiguracionPhotoNumber){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        dispatchTakePictureIntent();
                    } else {
                        //para versiones inferiores a android 10.0.
                        openCamera(activity);
                    }

                } else {
                    Utils.messageShort(activity, descripcionConfiguracionPhotoNumber);
                }

                break;

            case R.id.buttonGuardarPDFGTFF:
                if (listFotos.isEmpty() || listFotos == null) {
                    Utils.messageShort(activity, getString(R.string.text_label_falta_tomar_una_foto));
                } else {
                    GeneratePDF(view, navigationFragment);
                }

                break;

            default:
                break;
        }
    }

    public void GeneratePDF(View view, String navigationFragment) {
        if (write()) {
            Toast.makeText(activity, "Archivo cargado correctamente", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putString("nombrePDFFotos", nombreFoto);
            bundle.putString("tamanioPDFFotos", tamanio);
            bundle.putString("extenciionPDFFotos", extenciion);
            bundle.putString("justificacion", justificacion);
            bundle.putString("uriStringPDFPDFFotos", uriStringPDF);
            bundle.putString("idCaso", idCaso);
            bundle.putString("idCasoFase", idCasoFase);
            bundle.putString("justificacion", justificacion);
            bundle.putString("tipoAppMovil", tipoAppMovil);
            if (navigationFragment.equals("reprogramadas")){
                Navigation.findNavController(view).navigate(R.id.action_navigation_geleria_tomar_fotos_fragment_to_navigation_reprogramadas_prentacion_de_demanda_fragment, bundle);
            }else if (navigationFragment.equals("terminar")){
                Navigation.findNavController(view).navigate(R.id.action_navigation_geleria_tomar_fotos_fragment_to_navigation_terminar_presentacion_denuncia_fragment, bundle);
            }

        } else {
            Toast.makeText(activity, getString(R.string.text_label_no_se_creo_el_archivo_pdf), Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean write() {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
            Date date = new Date();
            nombreFoto = dateFormat.format(date);

            //String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/sifra/";
            String path = Environment.getExternalStorageDirectory() + "/sifra/";

            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }

            File newFile = new File(dir, nombreFoto + ".pdf");
            Document document = new Document();

            if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(String.valueOf(newFile.length()))) {

                if (listFotos != null || listFotos.size() != 0) {

                    PdfWriter.getInstance(document, new FileOutputStream(newFile.getAbsoluteFile()));
                    document.open();

                    for (int i = 0; i < listFotos.size(); i++) {
                        Image foto = Image.getInstance(Base64.decode(listFotos.get(i)/*.getDocumento()*/, Base64.NO_WRAP));
                        foto.scaleAbsolute(500, 350);

                        document.add(foto);
                        document.add(new Phrase(Chunk.NEWLINE));
                    }
                    document.close();
                    nombreDeArchivoFoto = Utils.getNombreDocumentos(String.valueOf(newFile));
                    //pdf = Utils.fileToBase64(activity, Uri.fromFile(newFile));
                    extenciion = "pdf";
                    tamanio = String.valueOf(newFile.length());
                    uriStringPDF = newFile.getPath();


                } else {
                    document.close();
                    Utils.messageShort(activity, "No hay fotos");
                }

            } else {
                document.close();
                Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == Constantes.RESPUESTA_DE_CAMARA) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    galleryAddPic();
                    setPic();
                } else {
                    MediaScannerConnection.scanFile(activity,
                            new String[]{currentPhotoPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });

                    Bitmap bitmapImageFoto = BitmapFactory.decodeFile(currentPhotoPath);

                    folderEmpty.setVisibility(View.GONE);

                    try {
                        uri = Utils.resizeImage(activity, bitmapImageFoto);
                        imagen = Utils.fileToBase64(activity, uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    galeriaFotosAdapter.getList().add(imagen);
                    galeriaFotosAdapter.notifyDataSetChanged();
                }
            }

        }
    }

    //TODO: funcion para abrir la camara con un intent en la version de android x o superior
    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri imagenUri = FileProvider.getUriForFile(activity, "com.auditorias.fuerzasespeciales.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
                startActivityForResult(takePictureIntent, Constantes.RESPUESTA_DE_CAMARA);
            }
        }
    }

    //TODO: funcion para poder crear el archivo de la imagen que se tomo con la camara en la version de android x o superior
    private File createImageFile() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
        Date date = new Date();
        nombreFoto = dateFormat.format(date);
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreFoto, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }

    //TODO: funcion para poder agregar la foto tomada con la camara a la galeria en la version de android x o superior
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        uri = Uri.fromFile(f);
        mediaScanIntent.setData(uri);
        activity.sendBroadcast(mediaScanIntent);
    }

    //TODO: funcion para enviar la foto tomanda con la camara a un imageview oara poder mostra la foto que se tomo en la version de android x o superior
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setPic() {
        float targetW = imagenfeo.getWidth();
        float targetH = imagenfeo.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        float scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = (int) scaleFactor;
        bmOptions.inPurgeable = true;

        //File file = new File(currentPhotoPath);
        //bitmapImageFoto = BitmapFactory.decodeFile(currentPhotoPath);
        //Log.i("setPic", "setPic: " + currentPhotoPath);

        folderEmpty.setVisibility(View.GONE);
        Bitmap bitmap = null;
        try {
            bitmap = Utils.getBitmap(activity, uri);
            uri = Utils.resizeImage(activity, Utils.cambiarPosicionImage(bitmap));
            imagen = Utils.fileToBase64(activity, uri);
            //bitmapImageFoto = Utils.redimensionarImagenMaximo(bitmap, 100, 100);
            //imagen = convertBitmapToBase64(bitmapImageFoto);//Utils.fileToBase64(activity, uri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        galeriaFotosAdapter.getList().add(imagen);
        galeriaFotosAdapter.notifyDataSetChanged();

    }

    /*public static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);
    }*/

    //TODO:funcionalidad para android inferiores a android 10
    /***************************************** Funcionalida de android para api bajos de android 10 *********************************************************************************/
    /***************************************** funcion para permisos  *********************************************************************************/
    @SuppressLint("ObsoleteSdkInt")
    private boolean myRequestStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if ((activity.checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (activity.checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }

        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))) {
            cargarDialogoRecomendacion(activity);
        } else {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, Constantes.MIS_PERMISOS);
        }
        return false;
    }

    private void cargarDialogoRecomendacion(Activity activity) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la Ap");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, Constantes.MIS_PERMISOS);
                }
            }
        });
        dialogo.show();
    }

    /***************************************** Funcion paa abrir camara de un dispositivo con android menor a android 10 *********************************************************************************/
    private void openCamera(Activity activity) {
        File file = new File(Environment.getExternalStorageDirectory(), Constantes.DIRECCION_FOTO);
        boolean isDirectoryCreated = file.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = file.mkdirs();
        }
        if (isDirectoryCreated) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.getDefault());
            Date date = new Date();
            nombreDeArchivoFoto = dateFormat.format(date);

            //Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = nombreDeArchivoFoto + ".jpg";
            currentPhotoPath = Environment.getExternalStorageDirectory() + File.separator + Constantes.DIRECCION_FOTO + File.separator + imageName;
            File newFile = new File(currentPhotoPath);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String authorities = activity.getApplicationContext().getPackageName() + ".fileprovider";
                Uri imageUri = FileProvider.getUriForFile(activity, authorities, newFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            }
            startActivityForResult(intent, Constantes.RESPUESTA_DE_CAMARA);
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", currentPhotoPath);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constantes.MIS_PERMISOS) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(activity, "Permisos aceptados y botones activados", Toast.LENGTH_SHORT).show();
                //imgBtnCameraNewVisit.setEnabled(true);
            }
        } else {
            showExplanation(activity);
        }
    }

    private void showExplanation(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Permisos aceptados y botones activados");
        builder.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //activityTPDF.finish();
            }
        });
        builder.show();
    }
}