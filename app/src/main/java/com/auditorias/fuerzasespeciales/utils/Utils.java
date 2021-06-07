package com.auditorias.fuerzasespeciales.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.GradientDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.StatusResponsableFaseModel;
import com.auditorias.fuerzasespeciales.models.catalogos.etapa.EtapaCaso;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters.CatalogoArrayAdapter;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Utils {

    //TODO: lo que eh creado por mi parte objetos, funciones, calculos, etc Att: Gustavo F. Palma Pinzón
    public static String SetCambioFormatoFechaMesDia(String dateString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("es", "MX"));
        symbols.setShortMonths(meses);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd", symbols);
        assert date != null;
        return fmtOut.format(date);
    }

    public static String SetCambioFormatoFechaDiaMesAnio(String dateString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("es", "MX"));
        symbols.setShortMonths(meses);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmtOut = new SimpleDateFormat("dd MMM yyyy ", symbols);
        assert date != null;
        return fmtOut.format(date);
    }

    public static void textInputEditTextDisabled(TextInputEditText textInputEditText) {
        textInputEditText.setKeyListener(null);
        textInputEditText.setEnabled(false);
        textInputEditText.setInputType(InputType.TYPE_NULL);
        textInputEditText.setTextIsSelectable(false);
        textInputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;  // Blocks input from hardware keyboards.
            }
        });
    }

    public static void editTextDisabled(EditText editText) {
        editText.setKeyListener(null);
        editText.setEnabled(false);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setTextIsSelectable(false);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;  // Blocks input from hardware keyboards.
            }
        });
    }

    public static List<EtapaCaso> llenadoDeEtapa(LinearLayout linearLayout, List<EtapaCaso> list, Activity activity) {
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).getExito().equals(Constantes.exitoTrue)) {
                TextView text = new TextView(activity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                params.setMargins(10, 0, 10, 0);
                text.setPadding(10, 5, 10, 5);
                text.setGravity(Gravity.CENTER);
                text.setLayoutParams(params);
                text.setTextColor(activity.getColor(R.color.gray2));
                //poner este cambio de fondo en un objeto
                GradientDrawable shape = new GradientDrawable();
                shape.setCornerRadius(8);
                shape.setColor(Color.parseColor(list.get(x).getCodigoColor()));
                text.setBackground(shape);
                //text.setBackgroundResource(R.drawable.rounded_corner);
                text.setText(list.get(x).getDescripcion());
                linearLayout.addView(text);
            } else {
                Utils.message(activity, list.get(x).getError());
            }
        }
        return list;
    }

    public static void setTextViewLetraYFondoBlue(Context activity, TextView textView) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(10);
        shape.setAlpha(50);
        shape.setColor(activity.getColor(R.color.blue200));
        textView.setBackground(shape);
    }

    @SuppressLint("ResourceType")
    public static void setTextViewLetraYFondo(Context activity, TextView textView, String colorAutorizacion) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(10);
        shape.setAlpha(80);
        shape.setColor(Color.parseColor(colorAutorizacion));
        textView.setBackground(shape);
    }

    public static String insertarCaracter(String textoReal, String textoInsert, int pos) {
        StringBuilder stringBuilder = new StringBuilder(textoReal);
        stringBuilder.insert(pos, textoInsert);
        return stringBuilder.toString();
    }

    public static String getNombreUriDocumentos(Context context, Uri uri) {
        String datos = null;
        Cursor cursor = null;
        cursor = context.getContentResolver().query(uri, null, null, null, null, null);
        try {
            // moveToFirst() returns false if the cursor has 0 rows.
            if (cursor != null && cursor.moveToFirst()) {
                datos = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return datos;
    }

    public static String getTamanioUriDocumentos(Context context, Uri uri) {
        String tamanio = null;
        Cursor cursor = null;
        cursor = context.getContentResolver().query(uri, null, null, null, null, null);
        try {
            // moveToFirst() returns false if the cursor has 0 rows.
            if (cursor != null && cursor.moveToFirst()) {
                //datos = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                tamanio = cursor.getString(cursor.getColumnIndex(OpenableColumns.SIZE));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return tamanio;
    }

    public static boolean isValidDate(String pDateString) throws ParseException {

        Date fechaactual = new Date(System.currentTimeMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaInicioDate = date.parse(pDateString);  //String a date

        Log.i("isValidDate", "isValidDate: fechaactual" + fechaactual);
        Log.i("isValidDate", "isValidDate: fechaCompromisopasada" + pDateString);
        //comprueba si es que inicio esta después que fecha actual
        //if(fechaInicioDate.after(fechaactual)){
        //    System.out.println("Fecha inicio mayor");
        //}else{
        //    System.out.println("Fecha actual mayor");
        //}

        //@SuppressLint("SimpleDateFormat") Date date = new SimpleDateFormat("dd/MM/yyyy").parse(pDateString);
        //Log.i("isValidDate", "isValidDate: fecha compromiso anterior " + date);
        //Log.i("isValidDate", "isValidDate: fecha compromiso nueva " + new Date().before(date));
        return fechaInicioDate.before(fechaactual);
    }

    /*public Date devolverFecha(Date fechaEntrada) {

        SimpleDateFormat formato = SimpleDateFormat("dd/M/yyyy");
        String fechaString = fechaEntrada.toString(); // Convierte Date a String
        Date miFecha = formato.parse(fechaString); // convierte String a Date
        return miFecha;

    }*/


    public static String cambiarFechayyyyMMdd(String date) throws ParseException {
        Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String parsedDate = formatter.format(initDate);
        return parsedDate;
    }

    public static String cambiarFechaMMddyyyyToyyyyMMdd(String date) throws ParseException {
        Date initDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String parsedDate = formatter.format(initDate);
        return parsedDate;
    }

    public static GradientDrawable cambiarColorTextView(String color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(8);
        shape.setColor(Color.parseColor(color));
        //textView.setBackground(shape);
        return shape;
    }

    public static String getNombreDocumentos(String nombreArchivo) {
        int iend = nombreArchivo.indexOf(".");
        String nombreImagen = "";
        if (iend != -1) {
            nombreImagen = nombreArchivo.substring(0, iend);
        }
        return nombreImagen;
    }

    public static String getExtensionArchivos(String nombre) {
        //extension sin el punto
        String extencionArchivo = nombre.substring(nombre.lastIndexOf(".") + 1);
        //extension con punto
        //String extencionArchivo = nombre.substring(nombre.lastIndexOf("."));
        return extencionArchivo;
    }

    public static String setBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bao);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        byte[] ba = bao.toByteArray();
        return Base64.encodeToString(ba, Base64.NO_WRAP);
    }

    public static Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeigth / height;
        Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap cambiarPosicionImage(Bitmap mBitmap/*, float newWidth*//*, float newHeigth*/) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap getUriToBitmapImagen(Activity activity, Uri uri) {
        //Bitmap selectedImage;
        InputStream inputStream = null;
        try {
            //assert mPath != null;
            assert uri != null;
            inputStream = activity.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    public static byte[] compress(String string) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(string.getBytes());
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        return compressed;
    }

    public static String decompress(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }

    public static String compressBase64(String imgOrigen) throws IOException {
        byte[] compressed = compress(imgOrigen); //se comprime la imagen
        return Base64.encodeToString(compressed, Base64.NO_WRAP);
    }

    public static String decompressBase64(String imgCompress) throws IOException {
        byte[] BytesCompress = Base64.decode(imgCompress, Base64.DEFAULT);
        return decompress(BytesCompress);
    }

    public static byte[] decompressBase64ToBytesArray(String imgCompress) throws IOException {
        byte[] BytesCompress = Base64.decode(imgCompress, Base64.DEFAULT);
        return BytesCompress;
    }

    /*public byte[] base64ToByteArray (String base64) throws UnsupportedEncodingException {
        //byte[] name = Base64.getEncoder().encode("hello World".getBytes());
        //byte[] decodedString = Base64.getDecoder().decode(base64.getBytes("UTF-8"));
        //System.out.println(new String(decodedString));
        byte[] decodedString = Base64.decode(base64.getBytes("UTF-8"));
        return decodedString;
    }*/

    /*public static byte[] FromBase64Bytes(String base64) throws IOException {
        byte[] name = Base64.getEncoder().encode(base64.getBytes());
        byte[] decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
        System.out.println(new String(decodedString));
        return decodedString;
    }*/

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    //Log.i(TAG, "NetworkCapabilities.TRANSPORT_CELLULAR");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    //Log.i(TAG, "NetworkCapabilities.TRANSPORT_WIFI");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    //Log.i(TAG, "NetworkCapabilities.TRANSPORT_ETHERNET");
                    return true;
                }
            }
        }
        return false;
    }

    public static String formatoAnioMesDia(String fechaACambiar) {
        String nuevoFormato;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date d = null;
        try {
            d = formato.parse(fechaACambiar);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        formato.applyPattern("yyyy-MM-dd");
        nuevoFormato = formato.format(d);
        return nuevoFormato;
    }

    public static long getByteToMegas(long size) {
        long fileSizeInKB = size * 1024;
        long fileSizeInMB = fileSizeInKB * 1024;
        return fileSizeInMB;
    }

    public static String setFormatoNumeroDecimalDinero(Double numero) {
        return new DecimalFormat("#,##0.#").format(numero);
    }

    public static String setFormatoNumeroEnteroPorcentaje(int numero) {
        return new DecimalFormat("##0").format(numero).concat(Constantes.signoPorcentaje);
    }

    public static String encodeFileToBase64Binary(File yourFile) {
        int size = (int) yourFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(yourFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String encoded = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return encoded;
    }

    public static String encodeImage(String path) {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;

    }

    public static File base64ToFile(String filename, String data) throws IOException {
        File file = null;
        byte[] fileAsBytes = Base64.decode(data, 0);
        file = new File(filename);
        FileUtils.writeByteArrayToFile(file, fileAsBytes);
        return file;
    }

    //TODO: valida si esixte un empledo dentro de una lista
    public static boolean validaSiExisteResponsableInternoOExterno(List<ResponsablesResquest> listResponsables, String nombre) {
        for (ResponsablesResquest responsable : listResponsables) {
            if (String.valueOf(responsable.getNombre()).equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static TextWatcher amount(Activity activity, final EditText editText) {
        return new TextWatcher() {
            DecimalFormat dec = new DecimalFormat("0.00");
            private String current = "";

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current) && s.toString().compareTo("") != 0) {
                    editText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[" + activity.getResources().getString(R.string.text_lable_signo_pesos) + ",.]", "").replace(" ", "");
                    double parsed = Double.parseDouble(cleanString.replaceAll("\\s", "").trim());
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
                    DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
                    symbols.setCurrencySymbol(activity.getResources().getString(R.string.text_lable_signo_pesos) + " ");
                    decimalFormat.setDecimalFormatSymbols(symbols);
                    String formatted = decimalFormat.format((parsed / 100));
                    current = formatted;
                    editText.setText(formatted);
                    editText.setSelection(formatted.length());
                    editText.addTextChangedListener(this);
                }
            }
        };
    }

    public static void setCaracteresEntreDiezAndVeinte(Activity activity, TextInputEditText textInputEditText) {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 10) {
                    textInputEditText.setTextColor(activity.getColor(R.color.Red600));
                    //textInputEditText.setError("Debe contener entre 10 y 20 caracteres!");
                    //textInputEditText.requestFocus();
                } else {
                    textInputEditText.setTextColor(activity.getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void setCaracteresEntreDiezAndCincuenta(Activity activity, TextInputEditText textInputEditText) {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 10) {
                    textInputEditText.setTextColor(activity.getColor(R.color.black));
                    //textInputEditText.setError("Debe contener entre 10 y 50 caracteres!");
                    //textInputEditText.requestFocus();
                } else {
                    textInputEditText.setTextColor(activity.getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void errorEditText(EditText editText, String error) {
        editText.setError(error);
        editText.requestFocus();
    }

    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            //Log.i("onActivityResult", "onActivityResult: uri" + uri.toString());
            //Log.i("onActivityResult", "onActivityResult: authority" + uri.getAuthority());
            //Log.i("onActivityResult", "onActivityResult: path" + uri.getPath());

            // ExternalStorageProvider
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Log.i("onActivityResult", "onActivityResult: path" + "getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);
                //System.out.println("getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);

                // This is for checking Main Memory
                if ("primary".equalsIgnoreCase(type)) {
                    if (split.length > 1) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1] + "/";
                    } else {
                        return Environment.getExternalStorageDirectory() + "/";
                    }
                    // This is for checking SD Card
                } else if ("home".equalsIgnoreCase(type)) {
                    return /*"storage"*/ Environment.getExternalStorageDirectory() + "/" + "document/" + docId.replace(":", "/");
                }

            }
        }
        return null;
    }

    //TODO utils de sistemas auditorias
    public static Uri getUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "IMG_" + Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }

    public static Bitmap getBitmap(Context context, Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bitmap;
    }

    public static Bitmap getBitmap2(Context context, Uri selectedimg) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        AssetFileDescriptor fileDescriptor = null;
        fileDescriptor =
                context.getContentResolver().openAssetFileDescriptor(selectedimg, "r");
        Bitmap original
                = BitmapFactory.decodeFileDescriptor(
                fileDescriptor.getFileDescriptor(), null, options);
        return original;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "IMG_" + Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }

    public static Uri getTempImage(Context context, Uri uri) throws FileNotFoundException {
        InputStream imageStream = context.getContentResolver().openInputStream(uri);
        Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
        Uri tempImageUri = resizeImage(context, imageBitmap);
        return tempImageUri;
    }

    public static Uri getImageUriFromFile(Context context, File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return getImageUri(context.getApplicationContext(), bitmap);
    }

    public static Uri resizeImage(Context context, Bitmap imageBitmap) {
        int nh = (int) (imageBitmap.getHeight() * (1024.0 / imageBitmap.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(imageBitmap, 1024, nh, true);
        return getImageUri(context.getApplicationContext(), scaled);
    }

    public static boolean checkPermission(Activity activity, String manifestPerm, int permission) {
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            int storagePermission = activity.checkSelfPermission(manifestPerm);
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{manifestPerm}, permission);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void message(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void messageShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /*public static Animation bubbleAnimation(Context context, int animation) {
        final Animation myAnim = AnimationUtils.loadAnimation(context, animation);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        myAnim.setRepeatCount(Animation.INFINITE);
        return myAnim;
    }*/

    public static void showFile(Context context, Uri uri) {
        Intent intentUrlView = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooserIntent = Intent.createChooser(intentUrlView, "Seleccione una aplicación");
        //chooserIntent = null;
        if (intentUrlView.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(chooserIntent);
        }
    }

    public static void showImageFile(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/*");
        context.startActivity(intent);
    }

    public static void showDocumentFile(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/*");
        context.startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    public static void showImageFile(Context context, String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            File file = new File(path);
            Uri tempFileUri = FileProvider.getUriForFile(context,
                    "com.scanlibrary.provider", // As defined in Manifest
                    file);
            //intent.setDataAndType(Uri.parse("content://" + path), "image/*");
            intent.setDataAndType(tempFileUri, "image/*");
            intent.addFlags(0x00000001);
        } else {
            intent.setDataAndType(Uri.parse("file://" + path), "image/*");
        }
        context.startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    public static void showDocumentFile(Context context, String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            File file = new File(path);
            Uri tempFileUri = FileProvider.getUriForFile(context,
                    "com.scanlibrary.provider", // As defined in Manifest
                    file);
            //intent.setDataAndType(Uri.parse("content://" + path), "application/*");
            intent.setDataAndType(tempFileUri, "application/*");
            intent.addFlags(0x00000001);
        } else {
            intent.setDataAndType(Uri.parse("file://" + path), "application/*");
        }
        context.startActivity(intent);
    }

    public static String getContentName(ContentResolver resolver, Uri uri) {
        Cursor cursor = resolver.query(uri, null, null, null, null);
        cursor.moveToFirst();
        int nameIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);
        if (nameIndex >= 0) {
            return cursor.getString(nameIndex);
        } else {
            return null;
        }
    }

    public static String getImageUriPath(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    /*public static List<ScanItem> getScanDirectoryFiles(Context context, String path) {
        List<ScanItem> listNames = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        AuditoriaDataBase adb = new AuditoriaDataBase(context);
        File f = null;
        try {
            f = new File(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path) + "/");

            if (!f.exists()) {
                createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path));
                createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_FILE.replace("<directorio>", path));
                f = new File(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path) + "/");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        //File f = new File(ScanConstants.PDF_PATH2.replace("<directorio>", path) + "/");
        File[] files = f.listFiles();
        for (File file : files) {
            //if (file.getName().contains(".pdf")) {
            if (!file.getName().contains("IMG") && !file.getName().contains(".pdf")) {
                temp.add(file.getName());
            }
        }
        if (temp.size() != 0) {
            Collections.sort(temp);
            for (String name : temp) {
                for (File file : files) {
                    //if (file.getName().contains(".pdf")) {
                    if (!file.getName().contains("IMG") && !file.getName().contains(".pdf")) {
                        if (file.getName().equals(name)) {
                            //String query = "SELECT nombre,path,estatus FROM documentos WHERE nombre = '" + name.replace(".jpg",".pdf") + "'";
                            //String query = "SELECT nombre,path,estatus FROM documentos WHERE path = '" +
                            //TODO: modificacion
                            //ScanConstants.PDF_PATH2.replace("<directorio>", path) + "/" + name.replace(".jpg",".pdf") + "'";
                            String dir = context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path) + "/" + name + "";
                            Documento documento = null;
                            try {
                                documento = adb.readDocumento(dir);
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                            }
                            if (documento != null) {
                                boolean flag = false;
                                if (Normalizer.normalize(documento.getEstatus(), Normalizer.Form.NFD).equals("pendiente")) {
                                    flag = false;
                                } else {
                                    flag = true;
                                }
                                ScanItem scanItem = new ScanItem();
                                scanItem.setImagePath(file.getAbsolutePath());
                                scanItem.setEraseFlag(false);
                                scanItem.setUpdateFlag(flag);
                                listNames.add(scanItem);
                            }
                        }
                        //System.out.println("Archivo escaneado: " + file.getName());
                    }
                }
            }

            //TODO: modificacion
            //File pdfDirectory = new File(ScanConstants.PDF_PATH2.replace("<directorio>", path) + "/");
            File pdfDirectory = new File(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path) + "/");
            File[] pdfFiles = pdfDirectory.listFiles();
            String[] buffer = null;
            for (int i = 0; i < listNames.size(); i++) {
                for (File pathPdf : pdfFiles) {
                    buffer = listNames.get(i).getImagePath().split("/");
                    if (pathPdf.getName().contains(buffer[buffer.length - 1].replace(".jpg", ""))) {
                        listNames.get(i).setPdfPath(pathPdf.getAbsolutePath());
                    }
                }
            }
        }
        return listNames;
    }*/

    /*public static boolean pdfFileExist(String filename, String directory) {
        File f = new File(ScanConstants.PDF_PATH2.replace("<directorio>", directory) + "/");
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.getName().contains(filename)) {
                return true;
            }
        }
        return false;
    }*/

    public static File moveFile(Context context, File file, File dir, String newName) throws IOException {
        File newFile = null;
        if (Normalizer.normalize(newName, Normalizer.Form.NFD).equals("")) {
            newFile = new File(dir, file.getName()).getCanonicalFile();
        } else {
            newFile = new File(dir, newName).getCanonicalFile();
        }
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            outputChannel = new FileOutputStream(newFile).getChannel();
            inputChannel = new FileInputStream(file).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
        } catch (IOException e) {
            return null;
        } catch (RuntimeException e) {
            return null;
        } catch (Error e) {
            return null;
        } finally {
            if (inputChannel != null) {
                inputChannel.close();
            }
            if (outputChannel != null) {
                outputChannel.close();
            }
        }
        return newFile;
    }

    public static void createScanFolder(String directory) {
        //File folder = new File(Environment.getExternalStorageDirectory() + "/" + directory);
        File folder = new File(directory);
        if (folder.exists()) {

        } else {
            //File nuevaCarpeta = new File(Environment.getExternalStorageDirectory(), directory);
            //nuevaCarpeta.mkdirs();
            folder.mkdirs();
            folder.setExecutable(true);
            folder.setReadable(true);
            folder.setWritable(true);
        }
    }

    /*public static void deleteScanFolder(Context context, String directory) {
        //File folder = new File(Environment.getExternalStorageDirectory() + "/" + directory);
        File folder = new File(directory);
        if (folder.exists()) {
            //folder.delete();
            //context.deleteFile(folder.getName());
            try {
                FileUtils.deleteDirectory(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //File nuevaCarpeta = new File(Environment.getExternalStorageDirectory(), directory);
            //nuevaCarpeta.mkdirs();
        }
    }*/

    public static void shareFile(Context context, Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("*/*");
        context.startActivity(Intent.createChooser(shareIntent, "Compartir archivo"));
    }

    public static void removeIMGscanFiles(Context context, String directory) {
        File f = new File(context.getExternalFilesDir(null) + ScanConstants.AUDITORIA_GS + ScanConstants.TEMP_PHOTOS + "/");
        if (f.exists()) {
            File[] files = f.listFiles();
            for (File file : files) {
                if (file.getName().contains("IMG")) {
                    file.delete();
                }
            }
        }
    }

    public static boolean deleteFile(Context context, String filename) {
        File fileDelete = new File(filename);
        if (fileDelete.exists()) {
            try {
                fileDelete.delete();
                return true;
            } catch (RuntimeException e) {
                e.printStackTrace();
                return false;
            } catch (Error e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        //message(getApplicationContext(), "Documento borrado");
    }

    public static boolean removeItem(Context context, String imgFile, String pdfFile) {
        int count = 0;
        if (deleteFile(context, imgFile)) {
            count++;
        }
        if (deleteFile(context, pdfFile)) {
            count++;
        }
        return count == 2;
    }

    public static Bitmap rotateBitmap(float angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static String getDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat(ScanConstants.DATE_FORMAT);
        return simpleDate.format(date);
    }

    public static int getDateNumber(String day, String option) {
        Date d = null;
        try {
            d = new SimpleDateFormat(ScanConstants.DATE_FORMAT).parse(day);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            //if (option.equals("day")) {
            if (option.equals(ScanConstants.DAY_TAG)) {
                return c.getTime().getDay();
                //} else if (option.equals("month")) {
            } else if (option.equals(ScanConstants.MONTH_TAG)) {
                return c.getTime().getMonth();
                //} else if (option.equals("year")) {
            } else if (option.equals(ScanConstants.YEAR_TAG)) {
                return c.get(Calendar.YEAR);
                //} else if (option.equals("real_day")) {
            } else if (option.equals(ScanConstants.REAL_DAY_TAG)) {
                return c.get(Calendar.DAY_OF_MONTH);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String getDayName(int day) {
        switch (day) {
            case 0:
                return "Dom.";
            case 1:
                return "Lun.";
            case 2:
                return "Mar.";
            case 3:
                return "Mie.";
            case 4:
                return "Jue.";
            case 5:
                return "Vie.";
            case 6:
                return "Sab.";
            default:
                return null;
        }
    }

    public static String getMonthName(int month) {
        switch (month) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return null;
        }
    }

    public static String getMonthNameAb(int month) {
        switch (month) {
            case 0:
                return "Ene";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Abr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Ago";
            case 8:
                return "Sept";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dic";
            default:
                return null;
        }
    }

    public static List<String> getAudCalendar() {
        Map<String, String> dates = new HashMap<>();
        List<String> lista = new ArrayList<>();
        String fechaHoy = getDate(ScanConstants.DATE_FORMAT);
        dates.put("fechaActual", fechaHoy);
        //String fechaHoy = "31-07-2017";
        int numeroDeDia = getDateNumber(fechaHoy, "day");
        String nombreDia = getDayName(numeroDeDia);
        int dia = getDateNumber(fechaHoy, "real_day");
        int rangoInicial = dia - (numeroDeDia - 1);
        String fechaInicio = null;
        String fechaFin = null;

        if (numeroDeDia == 0) {
            numeroDeDia = 7;
        }
        if (nombreDia.equals("Lun.") || nombreDia.equals("Mar.") || nombreDia.equals("Mie.")) {
            fechaInicio = getDate(fechaHoy, (numeroDeDia - 1) * -1);
            fechaFin = getDate(fechaHoy, (5 - numeroDeDia));
        } else {
            fechaInicio = getDate(fechaHoy, (numeroDeDia - 1) * -1);
            fechaFin = getDate(fechaHoy, (12 - numeroDeDia));
        }
        String temp = fechaInicio;
        boolean flag = true;
        do {
            if (temp.equals(fechaFin)) {
                flag = false;
            }
            if (!getDayName(getDateNumber(temp, "day")).equals("Sab.") &&
                    !getDayName(getDateNumber(temp, "day")).equals("Dom.")) {
                lista.add(temp);
            }
            temp = getDate(temp, 1);
        } while (flag);
        return lista;
    }

    public static Map<String, String> getAudRange() {
        Map<String, String> dates = new HashMap<>();
        List<String> lista = new ArrayList<>();
        String fechaHoy = getDate(ScanConstants.DATE_FORMAT);
        dates.put("fechaActual", fechaHoy);
        //String fechaHoy = "31-07-2017";
        int numeroDeDia = getDateNumber(fechaHoy, "day");
        String nombreDia = getDayName(numeroDeDia);
        int dia = getDateNumber(fechaHoy, "real_day");
        int rangoInicial = dia - (numeroDeDia - 1);
        String fechaInicio = null;
        String fechaFin = null;

        if (numeroDeDia == 0) {
            numeroDeDia = 7;
        }
        if (nombreDia.equals("Lun.") || nombreDia.equals("Mar.") || nombreDia.equals("Mie.")) {
            fechaInicio = getDate(fechaHoy, (numeroDeDia - 1) * -1);
            fechaFin = getDate(fechaHoy, (5 - numeroDeDia));
            dates.put("fechaInicio", fechaInicio);
            dates.put("fechaFin", fechaFin);
            dates.put("noSemanas", "1");
        } else {
            fechaInicio = getDate(fechaHoy, (numeroDeDia - 1) * -1);
            fechaFin = getDate(fechaHoy, (12 - numeroDeDia));
            dates.put("fechaInicio", fechaInicio);
            dates.put("fechaFin", fechaFin);
            dates.put("noSemanas", "2");
        }
        String temp = fechaInicio;
        boolean flag = true;
        do {
            if (temp.equals(fechaFin)) {
                flag = false;
            }
            if (!getDayName(getDateNumber(temp, "day")).equals("Sab.") &&
                    !getDayName(getDateNumber(temp, "day")).equals("Dom.")) {
                lista.add(temp);
            }
            temp = getDate(temp, 1);
        } while (flag);
        return dates;
    }

    public static String getDate(String date, int dias) {
        Calendar calendar = stringToCalendar(date);
        calendar.add(Calendar.DATE, dias);
        SimpleDateFormat format1 = new SimpleDateFormat(ScanConstants.DATE_FORMAT);
        String formatted = format1.format(calendar.getTime());
        //System.out.println(formatted);
        return formatted;
    }

    private static Calendar stringToCalendar(String date) {
        Date d = null;
        try {
            d = new SimpleDateFormat(ScanConstants.DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    /*public static void createDirectory(FolioItem folioItem) {
        String fecha = folioItem.getFecha().replace("-", "");
        String folio = folioItem.getNumeroFolio();
        String path = fecha + "/" + folio;
        createScanFolder(ScanConstants.IMAGE_PATH2.replace("<directorio>", path));
        createScanFolder(ScanConstants.PDF_PATH2.replace("<directorio>", path));
        if (folioItem.getHallazgos() != null) {
            for (String hallazgo : folioItem.getHallazgos()) {
                path = fecha + "/" + folio + "/" + hallazgo;
                createScanFolder(ScanConstants.IMAGE_PATH2.replace("<directorio>", path));
                createScanFolder(ScanConstants.PDF_PATH2.replace("<directorio>", path));
            }
        }
    }*/

    /*public static void createDirectory(Context context, Auditoria auditoria) {
        String fecha = auditoria.getFechaInicio().replace("-", "");
        String folio = auditoria.getFolio();
        String path = fecha + "/" + folio;
        createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path));
        createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_FILE.replace("<directorio>", path));
        //createScanFolder(ScanConstants.GZIP_PATH2);
        if (auditoria.getHallazgos() != null) {
            for (Hallazgo hallazgo : auditoria.getHallazgos()) {
                path = fecha + "/" + folio + "/" + hallazgo.getFolio();
                createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_PICTURE.replace("<directorio>", path));
                createScanFolder(context.getExternalFilesDir(null) + ScanConstants.SCAN_FILE.replace("<directorio>", path));
                //createScanFolder(ScanConstants.GZIP_PATH2.replace("<directorio>", path));
            }
        }
    }*/

    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static int compareDates(String date, String compare) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(date);
            Date date2 = sdf.parse(compare);
            return date1.compareTo(date2);
        } catch (ParseException e) {
            //e.printStackTrace();
            return 2;
        }
    }


    public static String getSharedPreferencesTagValue(Context context, String tag) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("AuditoriaGS", Context.MODE_PRIVATE);
        if (sharedPrefs.contains(tag)) {
            return sharedPrefs.getString(tag, "");
        } else {
            return "";
        }
    }

    public static String fileToBase64(Context context, Uri uri) throws IOException {
        InputStream iStream = context.getContentResolver().openInputStream(uri);
        byte[] inputData = IOUtils.toByteArray(iStream);
        //String encodedImage = Base64.encodeToString(inputData, Base64.DEFAULT);
        String encodedImage = Base64.encodeToString(inputData, Base64.NO_WRAP);
        return encodedImage;
    }

    /*public static File base64ToFile(String filename, String data) throws IOException {
        File file = null;
        byte[] fileAsBytes = Base64.decode(data, 0);
        file = new File(filename);
        FileUtils.writeByteArrayToFile(file, fileAsBytes);
        return file;
    }*/


    public static AlertDialog.Builder createDialogMessage(Activity activity, String title, String message) {

        return new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message);
    }

    /*public static AlertDialog createDialogMessageGS(Activity mActivity, String title,
                                                    String message,
                                                    String btnAceptarString,
                                                    DialogInterface.OnClickListener mListenerAceptar,
                                                    DialogInterface.OnClickListener mListenerCancelar,
                                                    View layout) {
        AlertDialog mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setPositiveButton((btnAceptarString != null) ? btnAceptarString : mActivity.getString(R.string.aceptar), mListenerAceptar)
                .setNegativeButton(mActivity.getString(R.string.cancelar), mListenerCancelar)
                .setCancelable(false)
                .create();
        if (message != null) {
            mAlertDialog.setMessage(message);
        }
        if (layout != null) {
            if (layout.getParent() != null) {
                ((ViewGroup) layout.getParent()).removeView(layout);
            }
            mAlertDialog.setView(layout);
        }
        mAlertDialog.show();
        return mAlertDialog;
    }*/

    public static AlertDialog createDialogMessageGS(Activity mActivity, String title,
                                                    String message,
                                                    View layout) {
        AlertDialog mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .create();
        if (message != null) {
            mAlertDialog.setMessage(message);
        }
        if (layout != null) {
            mAlertDialog.setView(layout, 0, 0, 0, 0);
        }
        mAlertDialog.show();
        return mAlertDialog;
    }

    /*public static AlertDialog.Builder createDialogMessageGS(Context activity, String title, String message) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogLayout = inflater.inflate(R.layout.dialog_gs, null);
        //TextView textViewTitle = (TextView) dialogLayout.findViewById(R.id.textViewDialogTitle);
        TextView textViewMessage = (TextView) dialogLayout.findViewById(R.id.textViewDialogMessage);
        //textViewTitle.setText(title);
        textViewMessage.setText(message);
        return new AlertDialog.Builder(activity).setView(dialogLayout).setTitle(title);
    }*/

    /*public static AlertDialog.Builder createDialogMessageGSFragment(Context activity, String title, String message) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogLayout = inflater.inflate(R.layout.dialog_gs_fragment, null);
        //TextView textViewTitle = (TextView) dialogLayout.findViewById(R.id.textViewDialogTitle);
        TextView textViewMessage = (TextView) dialogLayout.findViewById(R.id.textViewDialogMessage);
        //textViewTitle.setText(title);
        textViewMessage.setText(message);
        return new AlertDialog.Builder(activity).setView(dialogLayout).setTitle(title);
    }*/

    /*public static List<CatalogoItem> addTitleToCatalog(String title, List<CatalogObject> listOrigen) {
        List<CatalogoItem> listDestino = new ArrayList<>();
        CatalogoItem ci = new CatalogoItem("- " + title + " -", "");
        listDestino.add(ci);
        for (CatalogObject co : listOrigen) {
            if (title.equals("Categoria")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdHallazgoTipo()));
            } else if (title.equals("Moneda")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdMoneda()));
            } else if (title.equals("Negocio")) {
                //}else if(title.equals("Afectacion")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdAfectacion()));
                //}else if(title.equals("Rubro")) {
            } else if (title.equals("Origen")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdSubtipo()));
            } else if (title.equals("Tipo de Daño")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdHallazgoSubtipoClasificacion()));
                //}else if(title.equals("Producto")) {
            } else if (title.equals("Modo de operar")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdProducto()));
            } else if (title.equals("Riesgo")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdRiesgo()));
            } else if (title.equals("Tipo de Documento")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdTipodocumento()));
            } else if (title.equals("Motivo")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), "" + co.getId()));
            } else if (title.equals("Caja")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), "" + co.getId()));
            } else if (title.equals("Empleado")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), "" + co.getId()));
            } else if (title.equals("Condición del Producto")) {
                listDestino.add(new CatalogoItem(co.getClasificacion(), "" + co.getId()));
            } else if (title.equals("Ubicación")) {
                listDestino.add(new CatalogoItem(co.getUbicacion(), "" + co.getId()));
            } else if (title.equals("Escolaridad")) {
                listDestino.add(new CatalogoItem(co.getEscolaridad(), ""));
            } else if (title.equals("Cajero")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), String.valueOf(co.getId())));
            } else if (title.equals("Seleccione")) {
                listDestino.add(new CatalogoItem(co.getDescripcion(), String.valueOf(co.getId())));
            } else {
                listDestino.add(new CatalogoItem(co.getDescripcion(), ""));
            }
        }
        return listDestino;
    }*/

    /*public static List<CatalogoItem> addTitleToTempCatalog(String title, List<TempCatalogo> listOrigen) {
        List<CatalogoItem> listDestino = new ArrayList<>();
        if (title != null) {
            CatalogoItem ci = new CatalogoItem("- " + title + " -", "");
            listDestino.add(ci);
        }
        for (TempCatalogo co : listOrigen) {
            listDestino.add(new CatalogoItem(co.getDescripcion(), co.getIdCatalogo()));
        }
        return listDestino;
    }*/

    /*public static List<CatalogoItem> addTitleToTempCatalog(String title, List<CatalogoItem> listOrigen, boolean otro) {
        List<CatalogoItem> listDestino = new ArrayList<>();
        if (title != null) {
            listDestino.add(new CatalogoItem("- " + title + " -", "0"));
        }
        listDestino.addAll(listOrigen);
        if (otro) {
            listDestino.add(new CatalogoItem("OTRO", ""));
        }
        return listDestino;
    }*/

    /*public static void deleteHallazgoAcciones(AuditoriaDataBase adb, CreateHallazgo hallazgo) {
        List<Acciones> listAcciones = null;
        List<Acciones> listResponsables = null;
        List<Acciones> listResponsablesEjecutores = null;
        try {
            listAcciones = adb.readAcciones(new String[]{hallazgo.getTag(), hallazgo.getIdAuditoria()}, 3);
            listResponsables = adb.readResponsables(4, new String[]{hallazgo.getIdAuditoria(), hallazgo.getTag()});
            listResponsablesEjecutores = adb.readResponsablesEjecutores(4, new String[]{hallazgo.getIdAuditoria(), hallazgo.getTag()});
            for (Acciones accion : listAcciones) {
                adb.deleteAccionesResponsables(accion, "acciones");
            }
            for (Acciones responsable : listResponsables) {
                adb.deleteAccionesResponsables(responsable, "responsables");
            }
            for (Acciones responsable : listResponsablesEjecutores) {
                adb.deleteAccionesResponsables(responsable, "responsablesEjecutores");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }*/

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 10.63.50.108");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*public static List<Auditoria> ordenaLista(List<Auditoria> list) {
        List<Auditoria> temp = new ArrayList<>();
        int numero = 0;
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre"
                , "octubre", "noviembre", "diciembre"};
        for (String mes : meses) {
            for (int i = 1; i < 32; i++) {
                for (Auditoria auditoria : list) {
                    numero = getDateNumber(auditoria.getFechaInicio(), ScanConstants.REAL_DAY_TAG);
                    if (auditoria.getMes().equalsIgnoreCase(mes) && numero == i) {
                        temp.add(auditoria);
                        //break;
                    }
                }
            }
        }
        return temp;
    }*/


    public static File[] getDirectoryFiles(String path, String extension) {
        File f = new File(path);
        if (!f.exists()) {
            f = new File(path);
        }
        //File f = new File(ScanConstants.PDF_PATH2.replace("<directorio>", path) + "/");
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
            }
        }
        return files;
    }

    public static File saveImageToExternalStorage(Bitmap image, Context context, String extension) {
        String fullPath = context.getExternalFilesDir(null) + ScanConstants.AUDITORIA_GS + "tempFirmas/";
        OutputStream fOut = null;
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(fullPath, "firma." + extension);
            file.createNewFile();
            fOut = new FileOutputStream(file);
            if (extension.equals("png")) {
                image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            } else if (extension.equals("jpg")) {
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            }
            fOut.flush();
            fOut.close();
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            return file;
        } catch (FileNotFoundException e) {
            //Log.e("saveToExternalStorage()", e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            //Log.e("saveToExternalStorage()", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean validaCorreo(String correo) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public static String changeDateFormat(String fecha) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000").format(date);
        return formattedDate;
    }

    public static String changeDateFormat(String fecha, String recentFormat, String newFormat) {
        Date date = null;
        try {
            date = new SimpleDateFormat(recentFormat).parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = new SimpleDateFormat(newFormat).format(date);
        return formattedDate;
    }

    public static String getTituloCedula(int caja, int cedula) {
        String tipoCaja = "";
        if (caja == 1) {
            tipoCaja = "Principal";
        } else if (caja == 2) {
            tipoCaja = "Anclada";
        }

        switch (cedula) {
            case 1:
                return "Caja " + tipoCaja + " Moneda Nacional";
            case 2:
                return "Caja " + tipoCaja + " Documentos";
            case 3:
                return "Caja " + tipoCaja + " Dólares";
            case 4:
                return "Caja " + tipoCaja + " Euros";
            case 5:
                return "Caja " + tipoCaja + " Dólar Canadiense";
            case 6:
                return "Caja " + tipoCaja + " Monedas Plata";
            case 7:
                return "Caja " + tipoCaja + " Aceptadores Pared";
            case 8:
                return "Caja " + tipoCaja + " Dispensadores Pared";
            default:
                return "";
        }
    }

    /*public static String getTituloCedula(int caja, String cedula, List<CatalogObject> listMonedas) {
        String tipoCaja = "", encabezado = "";
        if (caja == 1) {
            tipoCaja = "Principal";
        } else if (caja == 2) {
            tipoCaja = "Anclada";
        } else if (caja == 3) {
            tipoCaja = "Documento";
        } else if (caja == 4) {
            tipoCaja = "Aceptador 80";
        } else if (caja == 5) {
            tipoCaja = "Dispensador 80";
        } else if (caja == 7) {
            tipoCaja = "Aceptador 81";
        } else if (caja == 8) {
            tipoCaja = "Dispensador 81";
        }

        if (listMonedas.size() > 0) {
            for (CatalogObject moneda : listMonedas) {
                if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals(moneda.getIdMoneda())) {
                    encabezado = "Caja " + tipoCaja + " " + tipoOracion(moneda.getDescripcion());
                    break;
                } else {
                    encabezado = "Caja " + tipoCaja;
                }
            }
        } else {
            encabezado = "Caja " + tipoCaja;
        }


//        if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals("MXN")) {
//            return "Caja " + tipoCaja + " Moneda Nacional";
//        } else if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals("USD")) {
//            return "Caja " + tipoCaja + " Dólares";
//        } else if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals("EUR")) {
//            return "Caja " + tipoCaja + " Euros";
//        } else if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals("CAD")) {
//            return "Caja " + tipoCaja + " Dólar Canadiense";
//        } else if (Normalizer.normalize(cedula, Normalizer.Form.NFD).equals("ONZ")) {
//            return "Caja " + tipoCaja + " Monedas Plata";
//        } else {
//            return "Caja " + tipoCaja;
//        }
        return encabezado;
    }*/

    public static boolean connection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static void addCustomItemsOnSpinner(Context context, Spinner spinner, List<StatusResponsableFaseModel> list) {
        CatalogoArrayAdapter catalogoArrayAdapter = new CatalogoArrayAdapter(context, R.layout.simple_item_layout, list);
        spinner.setAdapter(catalogoArrayAdapter);
    }

    public static void initializeSpinner(String label, Spinner spinner) {
        CatalogoArrayAdapter catalogoArrayAdapter = (CatalogoArrayAdapter) spinner.getAdapter();
        for (int i = 0; i < catalogoArrayAdapter.getListItems().size(); i++) {
            StatusResponsableFaseModel ci = catalogoArrayAdapter.getListItems().get(i);
            //if(ci.getItemName().equals(label)){
            if (ci.getId().equals(label)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    public static String getCurrencyFormat(double number) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String currency = format.format(number);
        return currency;
    }

    public static double removeCurrencyFormat(String currency) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        Number number = null;
        double valor = 0.0;
        try {
            number = format.parse(currency);
            valor = number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public static String calculadiferencia(double totalArqueado, double lecturaCaja) {
        String texto = "";
        double diferencia = totalArqueado - lecturaCaja;
        if (diferencia == 0) {
            texto = "Sin diferencias";
        } else if (diferencia > 0) {
            texto = "Sobrante de $" + new DecimalFormat("###,###.##").format(diferencia);
        } else if (diferencia < 0) {
            texto = "Faltante de $" + new DecimalFormat("###,###.##").format(diferencia);
        }
        return texto;
    }

    public static String calculaClasificacion(double totalArqueado, double lecturaCaja) {
        String texto = "";
        double diferencia = totalArqueado - lecturaCaja;
        if (diferencia == 0) {
            texto = "SIN DIFERENCIA";
        } else if (diferencia > 0) {
            texto = "SOBRANTE";
        } else if (diferencia < 0) {
            texto = "FALTANTE";
        }
        return texto;
    }

    public static double calculadiferenciaCantidad(double totalArqueado, double lecturaCaja) {
        double diferencia = totalArqueado - lecturaCaja;
        return diferencia;
    }

    /*public static double getTipoCambio(Context context, int idAuditoria, String idMoneda) {
        ArqueoDBMethods adbm = new ArqueoDBMethods(new AuditoriaDataBase(context), context);
        double tipoCambio = 1.0;
        List<ValoresLecturaCaja> list = adbm.readArqueoLectura(idAuditoria);
        ValoresLecturaCaja datosCaja = null;
        for (ValoresLecturaCaja vlc : list) {
            if (vlc.getIdMoneda().equals(idMoneda)) {
                if (vlc.getTipoCambio() != 0.0 && vlc.getTipoCambio() != 1.0) {
                    tipoCambio = vlc.getTipoCambio();
                }
            }
        }
        return tipoCambio;
    }*/

    public static void closeInputStream(InputStreamReader inputStreamReader) {
        try {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat formatoFecha = new SimpleDateFormat(ScanConstants.DATE_FORMAT);
        calendar.setTime(new Date());
        String fechaActual = formatoFecha.format(calendar.getTime());
        return fechaActual;
    }

    public static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23) {
            ei = new ExifInterface(input);
        } else {
            ei = new ExifInterface(selectedImage.getPath());
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static void hideKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void escribirEnLog(Context context, String textoMensaje) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        createScanFolder(context.getExternalFilesDir(null) + "/Log");
        File file = new File(context.getExternalFilesDir(null) + "/Log/Log.txt");
        String sCadena;
        ArrayList<String> list = new ArrayList<>();
        try {
            file.createNewFile();
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((sCadena = bf.readLine()) != null) {
                list.add(sCadena);
            }
            if (list.size() < 10) {
                list.add(textoMensaje);
                System.out.println("Escrito con éxito");
            } else {
                list.remove(0);
                list.add(textoMensaje);
            }
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("NO escrito.");
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String obtenerFechaHora() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        calendar.setTime(new Date());
        String fechaActual = formatoFecha.format(calendar.getTime());
        return fechaActual;
    }

    //obtener la ruta real de un archivo a partir de una tipo Uri
    public static String getRealPath(final Context context, final Uri uri) {

        if (uri.getScheme().equals("file")) {
            return uri.toString();

        } else if (uri.getScheme().equals("content")) {
            // DocumentProvider
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (DocumentsContract.isDocumentUri(context, uri)) {

                    // ExternalStorageProvider
                    if (isExternalStorageDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];

                        if ("primary".equalsIgnoreCase(type)) {
                            return Environment.getExternalStorageDirectory() + "/" + split[1];
                        }
                    }
                    // DownloadsProvider
                    else if (isDownloadsDocument(uri)) {

                        final String id = DocumentsContract.getDocumentId(uri);
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                        return getDataColumn(context, contentUri, null, null);
                    }
                    // MediaProvider
                    else if (isMediaDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];

                        Uri contentUri = null;
                        if ("image".equals(type)) {
                            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals(type)) {
                            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if ("audio".equals(type)) {
                            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }

                        final String selection = "_id=?";
                        final String[] selectionArgs = new String[]{
                                split[1]
                        };

                        return getDataColumn(context, contentUri, selection, selectionArgs);
                    }
                }
            }
        }

        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        try {
            //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static String getDecimalFormattedString(String value) {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }

        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt(-1 + str1.length()) == '.') {
            j--;
            str3 = ".";
        }

        for (int k = j; ; k--) {
            if (k < 0) {
                if (str2.length() > 0) {
                    str3 = str3 + "." + str2;
                }
                return str3;
            }
            if (i == 3) {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }
    }

    public static String trimCommaOfString(String string) { // String returnString;
        if (string.contains(",")) {
            return string.replace(",", "");
        } else {
            return string;
        }
    }

    public static void editTextFormatoDinero(EditText editText, TextWatcher textWatcher, boolean activaCero) {
        int start = editText.getSelectionStart();
        editText.removeTextChangedListener(textWatcher);
        String value = Normalizer.normalize(editText.getText().toString(), Normalizer.Form.NFD);
        if (value != null && !value.equals("")) {
            if (value.startsWith(".")) {
                editText.setText("0.");
            }
            if (value.startsWith("0") && !value.startsWith("0.")) {
                if (!activaCero) {
                    editText.setText("");
                } else {
                    if (value.equals("0")) {
                        editText.setText("0");
                    } else if (value.startsWith("0")) {
                        if (value.replace("0", "").equals("")) {
                            editText.setText("0");
                            value = "0";
                        } else {
                            editText.setText(value.replace("0", ""));
                        }
                    }
                }
            }
            String str = editText.getText().toString().replaceAll(",", "").replace("$", "");
            if (!value.equals("")) {
                if (!value.equals("0")) {
                    editText.setText(Utils.getDecimalFormattedString(str));
                }
            }
            if (start > 0) {
                if (start < str.length() && !str.equals("0.")) {
                    editText.setSelection(start);
                } else {
                    editText.setSelection(editText.getText().toString().length());
                }
            } else {
                editText.setSelection(editText.getText().toString().length());
            }
        }
        editText.addTextChangedListener(textWatcher);
    }

    /*public static double decimalFormat(String valor,int decimales) {
        double valorDouble = Double.parseDouble(valor);
        double cantidad = 0.0;
        try {
            BigDecimal formatNumber = new BigDecimal(valorDouble);
            formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
            cantidad = Double.parseDouble(formatNumber.toString());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }

    public static double decimalFormat(double valor,int decimales) {
        double cantidad = 0.0;
        try {
            BigDecimal formatNumber = new BigDecimal(valor);
            formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
            cantidad = Double.parseDouble(formatNumber.toString());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }//*/

    public static double decimalFormat(String valor, int decimales) {
        double valorDouble = Double.parseDouble(valor);
        double cantidad = 0.0;

        String valorString = "" + valor;
        if (valorString.contains(".")) {
            String[] temp = valorString.split("\\.");
            if (temp[1].length() == 2 || temp[1].length() == 1) {
                cantidad = valorDouble;
            } else {
                try {
                    BigDecimal formatNumber = new BigDecimal(valorDouble);
                    formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
                    cantidad = Double.parseDouble(formatNumber.toString());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                BigDecimal formatNumber = new BigDecimal(valorDouble);
                formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
                cantidad = Double.parseDouble(formatNumber.toString());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return cantidad;
    }

    public static double decimalFormat(double valor, int decimales, boolean flag) {
        double valorDouble = valor;
        double cantidad = 0.0;

        String valorString = "" + valor;
        if (valorString.contains(".")) {
            String[] temp = valorString.split("\\.");
            if (temp[1].length() == 2 || temp[1].length() == 1) {
                cantidad = valorDouble;
            } else {
                try {
                    BigDecimal formatNumber = new BigDecimal(valorDouble);
                    if (!flag) {
                        formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
                    } else {
                        formatNumber = formatNumber.setScale(decimales, RoundingMode.HALF_UP);
                    }
                    cantidad = Double.parseDouble(formatNumber.toString());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                BigDecimal formatNumber = new BigDecimal(valorDouble);
                if (!flag) {
                    formatNumber = formatNumber.setScale(decimales, RoundingMode.DOWN);
                } else {
                    formatNumber = formatNumber.setScale(decimales, RoundingMode.HALF_UP);
                }
                cantidad = Double.parseDouble(formatNumber.toString());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return cantidad;
    }

    public static boolean validaExpresion(String cadena, String regex) {
        Pattern patron = Pattern.compile(regex);
        return patron.matcher(cadena).matches();
    }

    public static boolean buscarCadena(String cadena, String regex) {
        Pattern patron = Pattern.compile(regex);
        Matcher matcher = patron.matcher(cadena);
        return matcher.find();
    }

    public static String revisaConexionServer(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        HttpResponse response = null;

        try {
            String url = new Encryption().decryptAES(ScanConstants.ADA_PUBLIC) + "ProbarConexion";
            HttpGet get = new HttpGet(url);
            //get.setHeader("Authorization",sharedPrefs.getString("jwt",""));
            response = client.execute(get);

            if (response != null) {
                InputStreamReader inputStreamReader = null;
                try {
                    inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                    BufferedReader rd = new BufferedReader(inputStreamReader);
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        str.append(line);
                    }
                } catch (IOException e) {
                    Log.e("AGU", "", e);
                    //Utils.escribirEnLog(Log.getStackTraceString(e));
                    return "Error: " + e.getMessage();
                } catch (RuntimeException e) {
                    Log.e("AGU", "", e);
                    //Utils.escribirEnLog(Log.getStackTraceString(e));
                    return "Error: " + e.getMessage();
                } catch (Error e) {
                    Log.e("AGU", "", e);
                    //Utils.escribirEnLog(Log.getStackTraceString(e));
                    return "Error: " + e.getMessage();
                } finally {
                    Utils.closeInputStream(inputStreamReader);
                }

                return str.toString();
            } else {
                return "Error: no se pudo revisar la conexión";
            }
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("AGU", "", e);
            Utils.escribirEnLog(context, Log.getStackTraceString(e));
            return "Error: " + e.getMessage();
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Log.e("AGU", "", e);
            Utils.escribirEnLog(context, Log.getStackTraceString(e));
            return "Error: " + e.getMessage();
        } catch (Error e) {
            //e.printStackTrace();
            Log.e("AGU", "", e);
            Utils.escribirEnLog(context, Log.getStackTraceString(e));
            return "Error: " + e.getMessage();
        }
    }

    public static String getIdAuditor(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String idAuditor = sharedPrefs.getString(ScanConstants.SHARED_PREFERENCES_ID_EMPLEADO, "");
        return new Encryption().decryptAES(idAuditor);
    }

    public static void reloadActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(0, 0);
        activity.startActivity(activity.getIntent());
        activity.overridePendingTransition(0, 0);
    }

    public static String escribirLogServicio(Context context, String clase, String trace) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            HttpPost post = null;
            String url = new Encryption().decryptAES(ScanConstants.ADA_PUBLIC) + "SetLogMovil";
            post = new HttpPost(url);
            JSONObject jsonObjectPost = new JSONObject();
            jsonObjectPost.put("method", clase);
            jsonObjectPost.put("tType", 2);
            jsonObjectPost.put("trace", "Auditor: " + new Encryption().decryptAES(sharedPrefs.getString("idEmpleado", "")) + " Trace: " + trace);
            StringEntity se = new StringEntity(jsonObjectPost.toString(), HTTP.UTF_8);
            post.setHeader("Authorization", new Encryption().decryptAES(sharedPrefs.getString("jwt", "")));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setEntity(se);
            response = client.execute(post);

            if (response != null) {
                InputStreamReader inputStreamReader = null;
                inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader rd = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = rd.readLine()) != null) {
                    str.append(line);
                }
                return str.toString();
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (RuntimeException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (Error e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (JSONException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        }
    }

    public static String escribirLogServicio(Context context, String clase, String trace, int tType, int requestSize) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            HttpPost post = null;
            String url = new Encryption().decryptAES(ScanConstants.ADA_PUBLIC) + "SetLogMovil";
            post = new HttpPost(url);
            JSONObject jsonObjectPost = new JSONObject();
            jsonObjectPost.put("method", clase);
            jsonObjectPost.put("tType", tType);
            jsonObjectPost.put("trace", trace);
            jsonObjectPost.put("requestSize", requestSize);
            StringEntity se = new StringEntity(jsonObjectPost.toString(), HTTP.UTF_8);
            post.setHeader("Authorization", new Encryption().decryptAES(sharedPrefs.getString("jwt", "")));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setEntity(se);
            response = client.execute(post);

            if (response != null) {
                InputStreamReader inputStreamReader = null;
                inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader rd = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = rd.readLine()) != null) {
                    str.append(line);
                }
                return str.toString();
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (RuntimeException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (Error e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        } catch (JSONException e) {
            Log.e("AGU", "", e);
            return "Error al enviar Log: " + e.getMessage();
        }
    }

    public static String[] getDisplayNameFromUri(Context context, Uri uri) {
        String[] datos = new String[2];
        Cursor cursor = null;
        cursor = context.getContentResolver().query(uri, null, null, null, null, null);
        try {
            // moveToFirst() returns false if the cursor has 0 rows.
            if (cursor != null && cursor.moveToFirst()) {
                datos[0] = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                datos[1] = cursor.getString(cursor.getColumnIndex(OpenableColumns.SIZE));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return datos;
    }

    public static boolean verificarInstalador(Context context) {
        String nameIntaller = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        if (nameIntaller != null) {
            return nameIntaller.startsWith("com.android.vending");
        }
        return false;
    }

    @SuppressLint("NewApi")
    public static void verificarLock(Context activity) {
        KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager.isDeviceSecure()) { // API level 23 and Up
            // Device can be locked, using either a PIN, a password or a pattern
        } else {
            // Device locking disabled
        }
    }

    public static boolean isDeviceRoot() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return in.readLine() != null;
        } catch (RuntimeException t) {
            return false;
        } catch (IOException t) {
            return false;
        } catch (Error t) {
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /*public static void deleteHallazgosCedula(AuditoriaDataBase adb, CreateHallazgo hallazgo, Context context) {
        adb.deleteHallazgo(hallazgo.getIdHallazgo(), hallazgo.getIdAuditoria());
        adb.deleteTempHallazgo(hallazgo.getIdHallazgo(), hallazgo.getIdAuditoria());
        List<Acciones> listAcciones = null;
        List<Acciones> listResponsables = null;
        List<Acciones> listResponsablesEjecutores = null;
        try {
            listAcciones = adb.readAcciones(new String[]{hallazgo.getIdAuditoria(), hallazgo.getIdHallazgo()}, 1);
            listResponsables = adb.readResponsables(7, new String[]{hallazgo.getIdAuditoria(), hallazgo.getIdHallazgo()});
            listResponsablesEjecutores = adb.readResponsablesEjecutores(7, new String[]{hallazgo.getIdAuditoria(), hallazgo.getIdHallazgo()});
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
        for (Acciones acciones : listAcciones) {
            adb.deleteAccionesResponsables(acciones.getIdHallazgo(), acciones.getIdAuditoria(), "acciones");
        }
        for (Acciones acciones : listResponsables) {
            adb.deleteAccionesResponsables(acciones.getIdHallazgo(), acciones.getIdAuditoria(), "responsables");
        }

        for (Acciones acciones : listResponsablesEjecutores) {
            adb.deleteAccionesResponsables(acciones.getIdHallazgo(), acciones.getIdAuditoria(), "responsablesEjecutores");
        }
        Utils.deleteScanFolder(context, context.getExternalFilesDir(null) + ScanConstants.AUDITORIA_GS + hallazgo.getFechaDirectorio() +
                "/" + hallazgo.getIdAuditoria() + "/" + hallazgo.getIdHallazgo());
    }*/

    /*public static boolean buscarElementoLista(List<Documento> list, String cadena) {
        for (Documento documento : list) {
            if (documento.getNombre().split("\\.")[0].equals(cadena)) {
                return true;
            }
        }
        return false;
    }*/

    /*public static void reloadFragment(FragmentActivity activity) {
        Fragment frg = null;
        frg = activity.getSupportFragmentManager().findFragmentById(R.id.fragment);
        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }*/

    /*public static boolean existeEmpleadoSap(String idAuditoria, int idEmpleado, ArqueoDBMethods adbm) {
        List<PuestosEstructura> listEmpleados = adbm.readPuestosEstructura(Integer.parseInt(idAuditoria), 1);
        for (PuestosEstructura empleado : listEmpleados) {
            if (empleado.getIdEmpleado() == idEmpleado) {
                return true;
            }
        }
        return false;
    }*/

    public static String tipoOracion(String cadena) {
        String result = "";
        if (cadena != null) {
            String[] nombre = cadena.trim().split(" ");
            for (String palabra : nombre) {
                if (palabra.trim().length() > 0) {
                    result += palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase() + " ";
                }
            }
        }
        return result;
    }

    /*public static String getTipoMoneda(int tipoMoneda, List<CatalogObject> tiposMoneda) {
        for (CatalogObject co : tiposMoneda) {
            if (co.getId() == tipoMoneda) {
                return co.getDescripcion();
            }
        }
        return "";
    }*/

    /*public static JSONObject evidenciaCaja(ValoresLecturaCaja valoresLecturaCaja) throws JSONException {
        JSONObject adaFile = new JSONObject();
        if (valoresLecturaCaja.getFotoEvidencia() == null) {
            adaFile.put("Name", JSONObject.NULL);
            adaFile.put("Extension", JSONObject.NULL);
            adaFile.put("Content", JSONObject.NULL);
        } else {
            adaFile.put("Name", valoresLecturaCaja.getDescripcion());
            adaFile.put("Extension", ".jpg");
            adaFile.put("Content", valoresLecturaCaja.getFotoEvidencia());
        }
        return adaFile;
    }*/

    /*public static String getTipoMonedaTexto(int tipoMoneda, ArqueoDBMethods adbm) {
        List<CatalogObject> tiposMoneda = adbm.readCatalogosArqueo("moneda");
        for (CatalogObject co : tiposMoneda) {
            if (co.getId() == tipoMoneda) {
                return co.getDescripcion();
            }
        }
        return "";
    }*/

    /*public static String getMonedaNacional(List<CatalogObject> list) {
        String monedaNacional = "";
        for (CatalogObject moneda : list) {
            if (moneda.getTipoMoneda().equals("MN")) {
                monedaNacional = moneda.getIdMoneda();
            }
        }
        return monedaNacional;
    }*/

    /*public static void showAlertDialog(Context mContext, String title, String message,
                                       DialogInterface.OnClickListener mOnClickListenerAceptar) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.aceptar, mOnClickListenerAceptar)
                .setNegativeButton(R.string.cancelar, null)
                .setCancelable(false)
                .show();
    }*/

    public static Fragment createFragment(Fragment mFragment, String... data) {
        Bundle mBundle = new Bundle();
        for (int i = 0; i < data.length; i++)
            mBundle.putString(data[i], data[++i]);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    public static void setFadeAnimation(View view) { //if only need to appear when down use lastPosition = -1 and add argument position
        view.setVisibility(View.VISIBLE);
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    public static void setFadeAnimationOut(final Activity mActivity, final View view) { //if only need to appear when down use lastPosition = -1 and add argument position
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public static void setFadeAnimationOutIn(final Activity mActivity, final View viewOut, final View viewIn) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(500);
        viewOut.startAnimation(anim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewOut.setVisibility(View.GONE);
                        viewIn.setVisibility(View.VISIBLE);
                    }
                });
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(500);
                        viewIn.startAnimation(anim);
                    }
                });
            }
        }).start();
    }

    public static void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String escaparCaracteres(String original) {
        String caracteres = "<>&'\"%@+";
        if (original != null) {
            for (int i = 0; i < caracteres.length(); i++) {
                original = original.replace("" + caracteres.charAt(i), "");
            }
        }
        return original;
    }

    public static void openGalery(Activity activity) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        activity.startActivityForResult(gallery, 100);
    }

    public static Bitmap base64ToBitmap(String base64) {
        byte[] decodedBytes = Base64.decode(base64, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    /*public static void addOtherEmploye(final Activity mActivity, final String idAuditoria, final Spinner spinner){
        final ArqueoDBMethods adbm = new ArqueoDBMethods(new AuditoriaDataBase(mActivity), mActivity);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        final AlertDialog mDialog = new AlertDialog.Builder(mActivity)
                .setView(inflater.inflate(R.layout.dialog_register_new_employe, null))
                .setTitle(mActivity.getString(R.string.register_employe))
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(R.string.cancelar, null)
                .show();

        final TextInputEditText etNumberEmployee = mDialog.findViewById(R.id.etNumberEmploye);
        final TextInputEditText etNameEmployee = mDialog.findViewById(R.id.etNameEmploye);
        etNameEmployee.setEnabled(false);

        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNumberEmployee.getText().toString().trim().equals("")) {
                    Utils.message(mActivity, "El número de empleado no puede ser vacío.");
                    return;
                }
                if (etNameEmployee.getText().toString().trim().equals("")) {
                    Utils.message(mActivity, "El nombre no puede ser vacío.");
                    return;
                }

                if (!Utils.existeEmpleadoSap(idAuditoria, Integer.parseInt(etNumberEmployee.getText().toString()), adbm)) {
                    PuestosEstructura empleado = new PuestosEstructura();
                    empleado.setIdAuditoria(Integer.parseInt(idAuditoria));
                    empleado.setIdEmpleado(Integer.parseInt(etNumberEmployee.getText().toString().trim()));
                    empleado.setNombre(etNameEmployee.getText().toString().toUpperCase().trim());
                    empleado.setExisteSAP(1);
                    adbm.createPuestosEstructura(empleado);
                    Utils.message(mActivity, "Empleado registrado exitosamente.");

                    List<PuestosEstructura> lstEmpleado = getLstEmployee();

                    spinner.setAdapter(new ArrayAdapter<PuestosEstructura>(lstEmpleado) {
                        @Override
                        public View getView(int i, View view, ViewGroup viewGroup) {
                            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_item_layout, viewGroup, false);
                            //view.findViewById(R.id.textViewItem).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            if (getItem(i).getNombre() == null) {
                                if(i == 0)
                                    ((TextView) view.findViewById(R.id.textViewItem)).setText(mActivity.getString(R.string.employe));
                                else
                                    ((TextView) view.findViewById(R.id.textViewItem)).setText(mActivity.getString(R.string.other));
                            }else
                                ((TextView) view.findViewById(R.id.textViewItem)).setText(getItem(i).getNombre() +
                                        " | " + ((getItem(i).getPuesto() == null) ? "ND" : getItem(i).getPuesto()));
                            view.setTag(getItem(i));
                            return view;
                        }
                    });

                    spinner.setSelection(lstEmpleado.indexOf(empleado));
                    hideSoftKeyboard(mActivity);
                    //Utils.reloadActivity(activity);
                    mDialog.dismiss();
                } else {
                    Utils.message(mActivity, "El empleado ya existe.");
                    hideSoftKeyboard(mActivity);
                    PuestosEstructura mPuesto = new PuestosEstructura();
                    mPuesto.setIdEmpleado(Integer.parseInt(etNumberEmployee.getText().toString()));
                    spinner.setSelection(getLstEmployee().indexOf(mPuesto));
                    mDialog.dismiss();
                }
            }

            private List<PuestosEstructura> getLstEmployee(){
                List<PuestosEstructura> lstEmpleado = new ArrayList<>();
                lstEmpleado.add(new PuestosEstructura());
                lstEmpleado.addAll(adbm.readPuestosEstructura(Integer.parseInt(idAuditoria), 1));
                lstEmpleado.add(new PuestosEstructura());
                return lstEmpleado;
            }
        });
        //online
        if(Utils.connection(mActivity)){
            mDialog.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String texto = etNumberEmployee.getText().toString();
                    if(!texto.equals("") && !texto.contains("+") && !texto.contains(",")) {
                        texto = texto.replace(" ","%20");
                        new RequestAutocomplete(mActivity, texto, etNameEmployee, mActivity, idAuditoria).execute();
                    }
                }
            });
        }else{
            mDialog.dismiss();
            Utils.message(mActivity,mActivity.getString(R.string.error_internet));
            spinner.setSelection(0);
        }
    }*/


    /*public static void registrarOtroEmpleado(final Activity activity, final String idAuditoria, final Spinner spinner) {
        AuditoriaDataBase adb = new AuditoriaDataBase(activity);
        final ArqueoDBMethods adbm = new ArqueoDBMethods(adb, activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_arqueo_cajeros_agregar, null);
        final TextInputEditText editTextIdEmpleado = layout.findViewById(R.id.editTextIdEmpleado);
        final TextInputEditText editTextNombreEmpleado = layout.findViewById(R.id.editTextNombreEmpleado);
        ImageView imageViewBusqueda = (ImageView) layout.findViewById(R.id.imageViewBusqueda);
        editTextNombreEmpleado.setEnabled(false);

        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(activity);
        alertDialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hideKeyboard(activity, editTextIdEmpleado);
            }
        }).setPositiveButton(R.string.save, null)
                .setCancelable(false)
                .setView(layout)
                .setTitle(R.string.register_employe);

        final android.app.AlertDialog dialog = alertDialog.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogo) {
                Button guardar = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editTextIdEmpleado.getText().toString().trim().equals("")) {
                            if (!editTextNombreEmpleado.getText().toString().trim().equals("")) {
                                if (!Utils.existeEmpleadoSap(idAuditoria, Integer.parseInt(editTextIdEmpleado.getText().toString()), adbm)) {
                                    PuestosEstructura empleado = new PuestosEstructura();
                                    empleado.setIdAuditoria(Integer.parseInt(idAuditoria));
                                    empleado.setIdEmpleado(Integer.parseInt(editTextIdEmpleado.getText().toString().trim()));
                                    empleado.setNombre(editTextNombreEmpleado.getText().toString().toUpperCase().trim());
                                    empleado.setExisteSAP(1);
                                    adbm.createPuestosEstructura(empleado);
                                    Utils.message(activity, "Empleado registrado exitosamente.");
                                    List<CatalogObject> listEmpleados = obtenerPlantillaTienda(idAuditoria, activity);
                                    addCustomItemsOnSpinner(activity, spinner, Utils.addTitleToCatalog("Empleado", listEmpleados));
                                    Utils.initializeSpinner("" + empleado.getIdEmpleado(), spinner);
                                    hideKeyboard(activity, editTextIdEmpleado);
                                    dialog.dismiss();
                                    //Utils.reloadActivity(activity);
                                } else {
                                    Utils.message(activity, "El empleado ya existe.");
                                    List<CatalogObject> listEmpleados = obtenerPlantillaTienda(idAuditoria, activity);
                                    addCustomItemsOnSpinner(activity, spinner, Utils.addTitleToCatalog("Empleado", listEmpleados));
                                    Utils.initializeSpinner(editTextIdEmpleado.getText().toString().trim(), spinner);
                                    editTextIdEmpleado.setText("");
                                    editTextNombreEmpleado.setText("");
                                    hideKeyboard(activity, editTextIdEmpleado);
                                    dialog.dismiss();
                                }
                            } else {
                                Utils.message(activity, "El nombre no puede ser vacío.");
                            }
                        } else {
                            Utils.message(activity, "El número de empleado no puede ser vacío.");
                        }
                    }
                });
            }
        });
        dialog.show();

        //online
        if (Utils.connection(activity)) {
            imageViewBusqueda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String texto = editTextIdEmpleado.getText().toString();
                    if (!texto.equals("") && !texto.contains("+") && !texto.contains(",")) {
                        texto = texto.replace(" ", "%20");
                        new RequestAutocomplete(activity, texto, editTextNombreEmpleado, activity, idAuditoria).execute();
                    }
                }
            });
        } else {
            dialog.dismiss();
            Utils.message(activity, "Se requiere conexión a la red.");
            spinner.setSelection(0);
        }
    }*/

    /*public static List<CatalogObject> obtenerPlantillaTienda(String idAuditoria, Context context) {
        List<CatalogObject> listEmpleados = new ArrayList<>();
        AuditoriaDataBase adb = new AuditoriaDataBase(context);
        List<PuestosEstructura> puestos = new ArqueoDBMethods(adb, context).readPuestosEstructura(Integer.parseInt(idAuditoria), 1);
        for (PuestosEstructura puestosEstructura : puestos) {
            CatalogObject co = new CatalogObject();
            if (puestosEstructura.getPuesto() != null) {
                co.setDescripcion(puestosEstructura.getNombre() + " | " + puestosEstructura.getPuesto());
            } else {
                co.setDescripcion(puestosEstructura.getNombre() + " | ND");
            }
            co.setId(puestosEstructura.getIdEmpleado());
            listEmpleados.add(co);
        }

        CatalogObject cOtro = new CatalogObject();
        cOtro.setDescripcion("OTRO");
        cOtro.setId(0);
        listEmpleados.add(cOtro);

        return listEmpleados;
    }*/

    /*public static boolean validaNombre(String nombre, List<Firma> list) {
        for (Firma temp : list) {
            if (temp.getNombre().contains(nombre)) {
                return true;
            }
        }
        return false;
    }*/

    /*public static int getPositionSpinner(String label, Spinner spinner) {
        CatalogoAdapter catalogoAdapter = (CatalogoAdapter) spinner.getAdapter();
        for (int i = 0; i < catalogoAdapter.getListItems().size(); i++) {
            CatalogoItem ci = catalogoAdapter.getListItems().get(i);
            if (Normalizer.normalize(ci.getItemName(), Normalizer.Form.NFD).contains(Normalizer.normalize(label, Normalizer.Form.NFD))) {
                return i;
            }
        }
        return 0;
    }*/

    /*public static AlertDialog createDialogMessageGS(Context mActivity, String title,
                                                    String message,
                                                    String btnAceptarString,
                                                    DialogInterface.OnClickListener mListenerAceptar,
                                                    DialogInterface.OnClickListener mListenerCancelar,
                                                    View layout) {
        AlertDialog mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setPositiveButton((btnAceptarString != null) ? btnAceptarString:mActivity.getString(R.string.aceptar), mListenerAceptar)
                .setNegativeButton(mActivity.getString(R.string.cancelar), mListenerCancelar)
                .setCancelable(false)
                .create();
        if(message != null)
            mAlertDialog.setMessage(message);
        if(layout != null)
            mAlertDialog.setView(layout);
        mAlertDialog.show();
        try {
            mAlertDialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return mAlertDialog;
    }*/

    /*public static ResponseGralVO validaResponse(Context mContext, String result, String nameResultService){
        ResponseGralVO mResponseGralVO = null;
        try {
            if (result.split(" ")[0].equals("AGU:")) {
                Toast.makeText(mContext, mContext.getString(R.string.error_internet), Toast.LENGTH_LONG).show();
                return null;
            }
            mResponseGralVO = new Gson().fromJson(result.replace(nameResultService, "result"), ResponseGralVO.class);
            if (!mResponseGralVO.getResult().isExito()) {
                Toast.makeText(mContext, mContext.getString(R.string.error_get_data), Toast.LENGTH_LONG).show();
                return null;
            }

        }catch (JsonSyntaxException e){
            Toast.makeText(mContext, mContext.getString(R.string.error_server), Toast.LENGTH_LONG).show();
            Utils.escribirEnLog(mContext,result);
            e.printStackTrace();
        }
        return mResponseGralVO;
    }*/


    /*public static PopupWindow createViewDialogFilter(final Activity activity, ImageView imageViewFilter) {
        final PopupWindow popup = new PopupWindow(activity);
        View layout = activity.getLayoutInflater().inflate(R.layout.notas_menu_filter_layout, null);
        popup.setContentView(layout);

        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        //no muestra el borde del popup
        popup.setBackgroundDrawable(new BitmapDrawable());

        popup.showAsDropDown(imageViewFilter);

        return popup;
    }*/

    public static double truncateNumber(double value) {
        /*String valor = String.valueOf(value);
        if (valor.contains(".")) {
            String[] temp = valor.split("\\.");
            if (temp[1].length() > 2) {
                temp[1] = temp[1].substring(0, places);
                valor = temp[0] + "." + temp[1];
            }
        }
        return Double.parseDouble(valor);*/
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(value));
    }

    /*public static AlertDialog getHallazgos(final String hallazgoTipo, final int idAuditoria, final double faltante, final double sobrante, final Context context, final Activity activity) {
        AlertDialog dialogHallazgos = null;
        final AuditoriaDataBase adb = new AuditoriaDataBase(context);
        final List<HallazgoCedula> difHallazgos = new ArrayList<>();
        String fecha = adb.readFechaInicioAuditoria(String.valueOf(idAuditoria));
        final int tipoHallazgo = adb.readTipoHallazgo(hallazgoTipo);
        boolean existeF = false;
        boolean existeS = false;
        List<CreateHallazgo> hallazgosTemp = adb.readTempHallazgos(new String[]{String.valueOf(idAuditoria), String.valueOf(tipoHallazgo)});
        final List<Hallazgo> hallazgos = adb.readHallazgosTipo(String.valueOf(idAuditoria), tipoHallazgo);
        if (sobrante == 0 && faltante == 0) {
            for (CreateHallazgo tempHallazgo : hallazgosTemp) {
                Utils.deleteHallazgosCedula(adb, tempHallazgo, context);
            }
            for (Hallazgo hallazgo : hallazgos) {
                adb.updateEstatusHallazgo(new String[]{hallazgo.getFolio(), hallazgo.getIdAuditoria()});
            }
        } else {
            for (CreateHallazgo tempHallazgo : hallazgosTemp) {
                if (tempHallazgo.getCategoria().equals("1")) {
                    if (faltante != Double.parseDouble(tempHallazgo.getMontoTotal())) {
                        Utils.deleteHallazgosCedula(adb, tempHallazgo, context);
                    } else {
                        existeF = true;
                    }
                } else if (tempHallazgo.getCategoria().equals("4")) {
                    if (sobrante != Double.parseDouble(tempHallazgo.getMonto())) {
                        Utils.deleteHallazgosCedula(adb, tempHallazgo, context);
                    } else {
                        existeS = true;
                    }
                }
            }
            for (Hallazgo hallazgo : hallazgos) {
                if (hallazgo.getIdtipoHallazgo() == 1) {
                    if (faltante != hallazgo.getMonto()) {
                        adb.updateEstatusHallazgo(new String[]{hallazgo.getFolio(), hallazgo.getIdAuditoria()});
                    } else {
                        existeF = true;
                    }
                } else if (hallazgo.getIdtipoHallazgo() == 4) {
                    if (sobrante != hallazgo.getMonto()) {
                        adb.updateEstatusHallazgo(new String[]{hallazgo.getFolio(), hallazgo.getIdAuditoria()});
                    } else {
                        existeS = true;
                    }
                }
            }

            if (!existeF) {
                if (faltante != 0) {
                    HallazgoCedula hallazgoCedula = new HallazgoCedula(idAuditoria, faltante, "faltante", fecha);
                    difHallazgos.add(hallazgoCedula);
                }
            }

            if (!existeS) {
                if (sobrante != 0) {
                    HallazgoCedula hallazgoCedula = new HallazgoCedula(idAuditoria, sobrante, "sobrante", fecha);
                    difHallazgos.add(hallazgoCedula);
                }
            }

            if (difHallazgos.size() > 0) {
                final LayoutInflater inflater = LayoutInflater.from(context);
                View dialogLayout = null;

                dialogLayout = inflater.inflate(R.layout.dialog_arqueo_diferencias_layout, null);

                final ListView listViewDiferencias = (ListView) dialogLayout.findViewById(R.id.listViewDiferencias);
                ArqueoDiferenciasAdapter arqueoDiferenciasAdapter = new ArqueoDiferenciasAdapter(activity, difHallazgos);
                listViewDiferencias.setAdapter(arqueoDiferenciasAdapter);

                final int finalTipoHallazgo = tipoHallazgo;
                listViewDiferencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        HallazgoCedula hallazgoCedula = difHallazgos.get(position);
                        List<CreateHallazgo> hallazgosTemp = adb.readTempHallazgos(new String[]{String.valueOf(idAuditoria), String.valueOf(finalTipoHallazgo)});
                        String tipo = "";
                        if (hallazgoCedula.getTipo().equals("sobrante")) {
                            tipo = "4";
                        } else if (hallazgoCedula.getTipo().equals("faltante")) {
                            tipo = "1";
                        }

                        CreateHallazgo hallazgo1 = null;

                        for (CreateHallazgo createHallazgo : hallazgosTemp) {
                            if (createHallazgo.getCategoria().equals(tipo)) {
                                hallazgo1 = createHallazgo;
                                break;
                            }
                        }
                        if (hallazgo1 != null) {
                            Utils.message(context, "Ya se generó el hallazgo.");
                        } else {
                            SharedPreferences sharedPreferences = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
                            Encryption encryption = new Encryption();
                            String monto = String.valueOf(Utils.decimalFormat(hallazgoCedula.getMonto(), 2, false)).replace("-", "");
                            Intent intent = new Intent(context, HallazgoCategoriaActivity.class);
                            intent.putExtra("idAuditoria", encryption.encryptAES(String.valueOf(idAuditoria)));
                            intent.putExtra("idAuditor", encryption.decryptAES(sharedPreferences.getString(ScanConstants.SHARED_PREFERENCES_NOMBRE_EMPLEADO, "")));
                            CreateHallazgo hallazgo = new CreateHallazgo();
                            hallazgo.setFechaDirectorio(hallazgoCedula.getFecha());
                            hallazgo.setFechaHallazgo(Utils.getDate("yyyy-MM-dd"));
                            hallazgo.setFechaRegistro(Utils.getDate("yyyy-MM-dd"));
                            hallazgo.setIdAuditoria("" + idAuditoria);
                            hallazgo.setIdAuditor(encryption.decryptAES(sharedPreferences.getString(ScanConstants.SHARED_PREFERENCES_ID_EMPLEADO, "")));
                            hallazgo.setClasificacion("1");
                            if (hallazgoCedula.getTipo().equals("sobrante")) {
                                hallazgo.setCategoria("4");
                                hallazgo.setMonto(monto);
                                hallazgo.setTituloHallazgo("Sobrante por $" + Utils.decimalFormat(hallazgoCedula.getMonto() / 1000, 3, true));
                            } else if (hallazgoCedula.getTipo().equals("faltante")) {
                                hallazgo.setCategoria("1");
                                hallazgo.setMontoTotal(monto);
                                hallazgo.setTituloHallazgo("Faltante por $" + Utils.decimalFormat(hallazgoCedula.getMonto() / 1000, 3, true));
                            }
                            hallazgo.setTag(java.util.UUID.randomUUID().toString());
                            intent.putExtra("hallazgo", encryption.encryptAES(new Gson().toJson(hallazgo)));
                            intent.putExtra(hallazgoTipo, true);
                            context.startActivity(intent);
                        }
                    }

                });

                dialogHallazgos = Utils.createDialogMessageGS(activity, activity.getString(R.string.hallazgos), null, activity.getString(R.string.aceptar), null, null, dialogLayout);
            }
        }
        return dialogHallazgos;
    }*/

    public static void setCameraImage(Activity activity) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(activity.getExternalFilesDir(null) + ScanConstants.AUDITORIA_GS + ScanConstants.TEMP_PHOTOS + "temp.jpg");
        boolean isDirectoryCreated = file.getParentFile().mkdirs();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Uri tempFileUri = FileProvider.getUriForFile(activity, "com.scanlibrary.provider", file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        } else {
            Uri tempFileUri = Uri.fromFile(file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        }
        activity.startActivityForResult(cameraIntent, 1003);
    }

    public static void openCamera(Activity activity, int requestCode, String pathFoto) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //File file = createImageFile(pathFoto);
        File file = createImageFile(activity.getExternalFilesDir(null) + pathFoto);
        boolean isDirectoryCreated = file.getParentFile().mkdirs();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Uri tempFileUri = FileProvider.getUriForFile(activity,
                    "com.scanlibrary.provider",
                    file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        } else {
            Uri tempFileUri = Uri.fromFile(file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        }
        activity.startActivityForResult(cameraIntent, requestCode);
    }

    public static void openCamera(Fragment activity, int requestCode, String pathFoto) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File file = createImageFile(activity.getActivity().getExternalFilesDir(null) + pathFoto);
        //File file = createImageFile(pathFoto);
        boolean isDirectoryCreated = file.getParentFile().mkdirs();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Uri tempFileUri = FileProvider.getUriForFile(activity.getActivity(),
                    "com.scanlibrary.provider",
                    file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        } else {
            Uri tempFileUri = Uri.fromFile(file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        }
        activity.startActivityForResult(cameraIntent, requestCode);
    }

    private static File createImageFile(String pathFoto) {
        File file = new File(pathFoto);
        //capturedImageUri = Uri.fromFile(file);
        return file;
    }

    /*public static String executeGet(Context context,String metodo,String mensajeError){
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        HttpResponse response = null;
        try{
            StringBuilder url = new StringBuilder();
            url.append(new Encryption().decryptAES(ScanConstants.ADA_PUBLIC));
            url.append(metodo);

            HttpGet get = new HttpGet(url.toString());
            get.setHeader("Authorization", new Encryption().decryptAES(sharedPrefs.getString("jwt","")));
            response = client.execute(get);

            if(response!=null){
                InputStreamReader inputStreamReader = null;
                inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader rd = new BufferedReader(inputStreamReader);
                String str = IOUtils.toString(rd);
                Utils.closeInputStream(inputStreamReader);
                return str;
            }else{
                return mensajeError;
            }
        } catch(IOException e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + ": " + e.getMessage();
        }catch(RuntimeException e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + ": " + e.getMessage();
        }catch(Error e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + ": " + e.getMessage();
        }
    }*/

    /*public static String executePost(Context context,String metodo,JSONObject json,String mensajeError,String seccion,String idAuditoria){
        SharedPreferences sharedPrefs = context.getSharedPreferences(ScanConstants.SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try{
            Utils.escribirLogServicio(context,metodo,context.getString(R.string.traceRequest,seccion,idAuditoria),0,json.toString().length());
            StringBuilder url = new StringBuilder();
            url.append(new Encryption().decryptAES(ScanConstants.ADA_PUBLIC));
            url.append(metodo);

            HttpPost post = new HttpPost(url.toString());
            StringEntity se = new StringEntity( json.toString(), HTTP.UTF_8);
            post.setHeader("Authorization", new Encryption().decryptAES(sharedPrefs.getString("jwt","")));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setEntity(se);

            response = client.execute(post);

            if(response!=null){
                InputStreamReader inputStreamReader = null;
                inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader rd = new BufferedReader(inputStreamReader);
                String str = IOUtils.toString(rd);
                Utils.closeInputStream(inputStreamReader);
                return str;
            }else{
                return mensajeError;
            }
        } catch(IOException e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + e.getMessage();
        }catch(RuntimeException e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + e.getMessage();
        }catch(Error e) {
            Log.e(PrestaPrendaConstants.PP_LOG_ERROR_TAG,"",e);
            return mensajeError + e.getMessage();
        }
    }*/

    /*public static void contestaPreguntaChecklistPP(Context context,String idAuditoria,String idPregunta,String idRubro,int respuesta){
        AuditoriaDataBase adb = new AuditoriaDataBase(context);
        ChecklistDBMethods checklistDBMethods = new ChecklistDBMethods(adb,context);
        List<Checklist> listChecklist = checklistDBMethods.readChecklist(new String[]{idAuditoria},1);
        PreguntaRubro preguntaRubro = null;
        RespuestaRubro respuestaRubro = null;
        if(listChecklist.size() != 0) {
            for (Checklist checklist : listChecklist) {

                List<Encuestados> encuestados = checklistDBMethods.readEncuestadosChecklist(new String[]{String.valueOf(checklist.getId()),idAuditoria});
                for(Encuestados encuestado:encuestados) {
                    List<PreguntaRubro> listPreguntas = checklistDBMethods.readRubroPreguntasChecklist(2,
                            new String[]{idPregunta, idRubro, String.valueOf(checklist.getId()), idAuditoria});
                    List<RespuestaRubro> listRespuestas = checklistDBMethods.readRubroRespuestasChecklist(1, new String[]{idPregunta, idRubro,
                            String.valueOf(checklist.getId()), idAuditoria});
                    if (listPreguntas.size() != 0) {
                        preguntaRubro = listPreguntas.get(0);
                    }
                    if (listRespuestas.size() != 0) {
                        respuestaRubro = listRespuestas.get(0);

                    }
                    if (preguntaRubro != null) {
                        List<TipoRespuesta> listTipoRespuesta = checklistDBMethods.readTipoRespuestas(new String[]{String.valueOf(checklist.getId()),
                                String.valueOf(preguntaRubro.getIdPregunta()),
                                String.valueOf(preguntaRubro.getIdRubro()),
                                idAuditoria});
                        if (respuestaRubro != null) {
                            //update respuesta
                            Pregunta pregunta = new Pregunta();
                            pregunta.setIdAuditoria(idAuditoria);
                            pregunta.setIdRubro(Integer.parseInt(idRubro));
                            pregunta.setIdPregunta(Integer.parseInt(idPregunta));
                            pregunta.setIdEncuestado(respuestaRubro.getIdEncuestado());
                            pregunta.setIdChecklist(respuestaRubro.getIdChecklist());
                            pregunta.setRespuesta(respuesta);
                            checklistDBMethods.updateRespuestaChecklist(pregunta);

                            calculaCalificacionChecklist(context, preguntaRubro.getIdChecklist(), preguntaRubro.getIdAuditoria());
                        } else {
                            //create respuesta
                            if (listTipoRespuesta.size() != 0) {
                                TipoRespuesta tipoRespuesta = listTipoRespuesta.get(0);
                                RespuestaRubro respuestaRubroTemp = new RespuestaRubro(encuestado.getIdEncuestado(), preguntaRubro.getIdPregunta(), respuesta, "", tipoRespuesta.getTipoRespuesta(),
                                        preguntaRubro.getIdRubro(), idAuditoria, preguntaRubro.getIdChecklist());
                                checklistDBMethods.createRubroRespuestaChecklist(respuestaRubroTemp);

                                calculaCalificacionChecklist(context, preguntaRubro.getIdChecklist(), idAuditoria);
                            } else {

                            }
                        }
                    } else {
                        //no se pudo calificar
                        message(context, PrestaPrendaConstants.PP_MENSAJE_ERROR_CALIFICA_CHECKLIST);
                    }
                }
            }
        }else{
            message(context,PrestaPrendaConstants.PP_MENSAJE_ERROR_SIN_CHECKLIST);
        }
    }*/

    /*private static void calculaCalificacionChecklist(Context context,String idChecklist,String idAuditoria){
        float sumaPonderaciones = 0.0f;

        AuditoriaDataBase adb = new AuditoriaDataBase(context);
        ChecklistDBMethods cdb = new ChecklistDBMethods(adb,context);

        List<Encuestados> encuestados = cdb.readEncuestadosChecklist(new String[]{idChecklist,idAuditoria});

        for(Encuestados encuestado:encuestados) {
            sumaPonderaciones = 0.0f;
            List<RubroChecklist> listSecciones = cdb.readRubrosChecklist(new String[]{encuestado.getIdChecklist(),encuestado.getIdAuditoria()});
            for(RubroChecklist rubroChecklist:listSecciones) {
                List<RespuestaRubro> listRespuestas = cdb.readRubroRespuestasChecklist(3,
                        new String[]{String.valueOf(encuestado.getIdEncuestado()),String.valueOf(rubroChecklist.getIdRubro()),encuestado.getIdChecklist(),encuestado.getIdAuditoria()});
                for (RespuestaRubro respuestaRubro : listRespuestas) {
                    List<PreguntaRubro> tempPreguntas = cdb.readRubroPreguntasChecklist(2,
                            new String[]{String.valueOf(respuestaRubro.getIdPregunta()),
                                    String.valueOf(respuestaRubro.getIdRubro()),
                                    respuestaRubro.getIdChecklist(),
                                    respuestaRubro.getIdAuditoria()});
                    int flagActiva = 1;
                    if(tempPreguntas.size() != 0){
                        flagActiva = tempPreguntas.get(0).getActiva();
                    }
                    if(flagActiva != 0) {
                        if (respuestaRubro.getTipoRespuesta() == 1 || respuestaRubro.getTipoRespuesta() == 3) {
                            List<Ponderacion> listPonderacion = cdb.readPonderacionRespuestasChecklist(new String[]{String.valueOf(encuestado.getIdEncuestado()),
                                    String.valueOf(respuestaRubro.getIdPregunta()),
                                    String.valueOf(respuestaRubro.getRespuesta()),
                                    String.valueOf(respuestaRubro.getIdRubro()),
                                    respuestaRubro.getIdChecklist(),
                                    respuestaRubro.getIdAuditoria()});
                            for (Ponderacion ponderacion : listPonderacion) {
                                sumaPonderaciones += ponderacion.getPonderacion();
                            }
                        }
                    }else{
                        Pregunta preguntaTemp = new Pregunta();
                        preguntaTemp.setIdChecklist(respuestaRubro.getIdChecklist());
                        preguntaTemp.setIdAuditoria(respuestaRubro.getIdAuditoria());
                        preguntaTemp.setIdEncuestado(respuestaRubro.getIdEncuestado());
                        preguntaTemp.setIdPregunta(respuestaRubro.getIdPregunta());
                        preguntaTemp.setIdRubro(respuestaRubro.getIdRubro());
                        preguntaTemp.setRespuesta(0);
                        preguntaTemp.setTexto("");
                        cdb.updateRespuestaChecklist(preguntaTemp);
                    }
                }
                cdb.updateCalificacionSeccionChecklist(rubroChecklist.getIdRubro(),
                        encuestado.getIdEncuestado(),Integer.parseInt(rubroChecklist.getIdChecklist()),
                        Math.round(sumaPonderaciones),rubroChecklist.getIdAuditoria());
                sumaPonderaciones = 0.0f;
            }
        }

        //calcular calificacion checklist
        List<RubroChecklist> seccionesChecklist = cdb.readRubrosChecklist(new String[]{idChecklist,idAuditoria});
        float calificacionChecklist = 0.0f;
        for(RubroChecklist seccion:seccionesChecklist){
            int suma = 0;
            List<Integer> listCalificacionesSeccion = cdb.readCalificacionSeccionChecklist(1,new String[]{idChecklist,idAuditoria,String.valueOf(seccion.getIdRubro())});
            for(Integer calificacion:listCalificacionesSeccion){
                suma += calificacion;
            }
            float respuesta01 = seccion.getPonderacion() / 100.0f;
            float respuesta02 = respuesta01 * suma;
            float respuesta03 = respuesta02 / encuestados.size();
            calificacionChecklist += respuesta03;
        }

        cdb.updateCalificacionChecklist(idChecklist,idAuditoria,Math.round(calificacionChecklist));
        //cdb.updateEncuestadoContestoChecklist(idAuditoria,idChecklist,"" + encuestados.get(viewPagerEncuestados.getCurrentItem()).getIdEncuestado(),1);
    }*/

    public static void hideKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static Bitmap resizeImageBitmap(Bitmap imageBitmap, int ancho, int alto) {
        //Bitmap scaled = Bitmap.createScaledBitmap(imageBitmap, ancho, alto, true);
        //return scaled;
        return Bitmap.createScaledBitmap(imageBitmap, ancho, alto, true);
    }

    public static String bitmapToBase64(Bitmap bitmap, String extension) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (extension.contains("png")) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        byteArrayOutputStream.close();
        return encoded;
    }

    /*public static Bitmap base64ToBitmap(String base64) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        //decode base64 string to image
        imageBytes = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        baos.close();
        return decodedImage;
    }//*/
    

    /*public static MenuBuilder showMenu(Context context, View view, int menuRes){
        MenuBuilder menuBuilder = new MenuBuilder(context);
        MenuInflater inflater = new MenuInflater(context);
        inflater.inflate(menuRes, menuBuilder);
        Context wrapper = new ContextThemeWrapper(context, R.style.PopupTheme);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(wrapper, menuBuilder, view);
        optionsMenu.setForceShowIcon(true);
        optionsMenu.show();
        return menuBuilder;
    }*/

    /*public static PopupWindow mostrarMenuFiltro(Activity activity,View anchorView) {
        final PopupWindow popup = new PopupWindow(activity);
        View layout = activity.getLayoutInflater().inflate(R.layout.prestaprenda_menu_filtro_layout, null);
        popup.setContentView(layout);
        // Set content width and height

        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        //popup.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.drawable_editext));
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        //popup.showAsDropDown(anchorView,0,Math.round(anchorView.getY())-dpToPx(100));
        popup.showAsDropDown(anchorView);
        return popup;
    }*/

    /*public static void contestarPregunta(Context context,String idAuditoria,String idAccion,int respuesta){
        CatalogosPPDBMethods catalogosPPDBMethods = new CatalogosPPDBMethods(context);
        AuditoriaDataBase auditoriaDataBase = new AuditoriaDataBase(context);
        ChecklistDBMethods checklistDBMethods = new ChecklistDBMethods(auditoriaDataBase,context);
        //List<CatalogoItem> listAcciones = catalogosPPDBMethods.readCatalogoPrestaPrenda(PrestaPrendaConstants.PP_CATALOGO_OPCION_ACCION_CALIFICA);
        //CatalogoItem catalogoItem = null;
        //for(CatalogoItem ci:listAcciones){
        //    if(ci.getItemId().equals(idAccion)){
        //        catalogoItem = ci;
        //    }
        //}

        //if(catalogoItem != null){
        //califica
        List<Checklist> listChecklist = checklistDBMethods.readChecklist(new String[]{idAuditoria},1);
        if(listChecklist.size() != 0){
            //List<PreguntaRubro> listPregunta = checklistDBMethods.readRubroPreguntasChecklist(4,new String[]{String.valueOf(listChecklist.get(0).getId()),idAuditoria,catalogoItem.getItemId()});
            List<PreguntaRubro> listPregunta = checklistDBMethods.readRubroPreguntasChecklist(4,new String[]{String.valueOf(listChecklist.get(0).getId()),idAuditoria,idAccion});
            if(listPregunta.size() != 0){
                PreguntaRubro preguntaRubro = listPregunta.get(0);
                Utils.contestaPreguntaChecklistPP(context,idAuditoria,String.valueOf(preguntaRubro.getIdPregunta()),String.valueOf(preguntaRubro.getIdRubro()),respuesta);
            }else{
                //no puede calificar
            }
        }else{
            //no puede calificar
        }
        ///}else{
        //no puede calificar
        //}
    }*/

    /*public static LinearLayout setTab(Context context,String texto){
        LinearLayout tab = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.prestaprenda_tab_layout, null);
        TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
        tab_label.setText(texto);
        return tab;
    }*/

    public static String generateBase64Image(Context context, String filePath) {
        String base64 = null;
        File file = new File(context.getExternalFilesDir(null) + filePath);
        if (file != null) {
            try {
                Uri capturedImageUri = Uri.fromFile(file);
                Bitmap bitmap = Utils.getBitmap(context, capturedImageUri);
                bitmap = Utils.rotateImageIfRequired(context, bitmap, capturedImageUri);
                bitmap = Utils.resizeImageBitmap(bitmap, 768, 1024);
                base64 = Utils.bitmapToBase64(bitmap, "jpg");
                bitmap.recycle();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
                Utils.message(context, "No se pudo guardar la evidencia");
            }
        } else {
            Utils.message(context, "No se pudo guardar la evidencia");
        }
        return base64;
    }

    /*public static int initializeSpinnerPP(String label,Spinner spinner){
        CatalogoAdapter catalogoAdapter = (CatalogoAdapter) spinner.getAdapter();
        for(int i=0;i<catalogoAdapter.getListItems().size();i++){
            CatalogoItem ci = catalogoAdapter.getListItems().get(i);
            if(ci.getItemName().contains(label)){
                return i;
            }
        }
        return 0;
    }*/

    public static void startVoiceInput(Fragment activity, int idIntent, String texto) {
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_PROMPT, texto);
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);

        try {
            activity.startActivityForResult(intentActionRecognizeSpeech,
                    idIntent);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(activity.getContext(),
                    "Tu dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }
    }


    /*public static int seccionFirmadaPP(Context context,String idAuditoria,String seccion){
        EstatusFirmaPP estatusFirmaPP = new PrestaPrendaDBMethods(context).readEstatusFirmaSeccionesPP("WHERE idAuditoria = ?",new String[]{idAuditoria});
        int firmado = 0;
        if(estatusFirmaPP != null){
            if(seccion.equals(PrestaPrendaConstants.PP_SECCION_TOMA_FISICA)){
                firmado = estatusFirmaPP.getFirmadoTomaFisica();
            }else if(seccion.equals(PrestaPrendaConstants.PP_SECCION_PESADO)){
                firmado = estatusFirmaPP.getFirmadoPesado();
            }else if(seccion.equals(PrestaPrendaConstants.PP_SECCION_VALUACION)){
                firmado = estatusFirmaPP.getFirmadoValuacion();
            }else if(seccion.equals(PrestaPrendaConstants.PP_SECCION_REGIONAL)){
                firmado = estatusFirmaPP.getFirmadoRegional();
            }
        }
        return firmado;
    }*/
}