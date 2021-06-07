package com.auditorias.fuerzasespeciales.SQLite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.auditorias.fuerzasespeciales.models.EtapaCasoModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

import java.util.List;

public class TableEtapasCaso {
    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tableEtapasCaso).concat(Constantes.parectesis_inicio)
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma)
            .concat(Constantes.id).concat(Constantes.string_text_coma)
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)
            .concat(Constantes.codigoColor).concat(Constantes.string_text_coma)
            .concat(Constantes.codigoColorSecundario).concat(Constantes.string_text_coma)
            //.concat(AppFuerzasEspeciales.idColorSecundario).concat(AppFuerzasEspeciales.string_text_coma)
            .concat(Constantes.icono).concat(Constantes.text_parectesis_final);
            //.concat(AppFuerzasEspeciales.idColor).concat(AppFuerzasEspeciales.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String Descripcion, String Id, String IdStatus, String CodigoColor, String CodigoColorSecundario/*, String IDColorSecundario*/, String Icono/*, String IdColor*/) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, Descripcion);
        contentValues.put(Constantes.id, Id);
        contentValues.put(Constantes.idStatus, IdStatus);
        contentValues.put(Constantes.codigoColor, CodigoColor);
        contentValues.put(Constantes.codigoColorSecundario, CodigoColorSecundario);
        //contentValues.put(AppFuerzasEspeciales.idColorSecundario, IDColorSecundario);
        contentValues.put(Constantes.icono, Icono);
        //contentValues.put(AppFuerzasEspeciales.idColor, IdColor);
        db.insert(Constantes.tableEtapasCaso, null, contentValues);

    }

    public static void setAgregarNuevaEtapaSQLite(Activity activity, EtapaCasoModel etapaCasoModel) {
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        String icono;
        String codigoColorSecundario;
        if (etapaCasoModel.getIDColorSecundario() == null) {
            icono = "0";
        } else {
            icono = String.valueOf(etapaCasoModel.getIcono());
        }

        if (etapaCasoModel.getCodigoColorSecundario()== null){
            codigoColorSecundario = "0";
        }else {
            codigoColorSecundario=etapaCasoModel.getCodigoColorSecundario();
        }

        date.getTableEtapasCaso().AddInformations(db,
                etapaCasoModel.getDescripcion(), String.valueOf(etapaCasoModel.getId()), String.valueOf(etapaCasoModel.getIdStatus()), etapaCasoModel.getCodigoColor(),
                codigoColorSecundario/*, String.valueOf(etapaCasoModel.getIDColorSecundario())*/, icono/*, String.valueOf(etapaCasoModel.getIdColor())*/);
        db.close();
    }

    public static List<EtapaCasoModel> getSeleccionarTodasEtapasSQLite(Activity activity, List<EtapaCasoModel> listEtapaCaso) {
        //listEtapaCaso.clear();
        SQLHelper dataProducts = new SQLHelper(activity);
        SQLiteDatabase db = dataProducts.getWritableDatabase();
        String mQueryCount = Constantes.select.concat("*").concat(Constantes.from).concat(Constantes.tableEtapasCaso);
        Cursor mCur = db.rawQuery(mQueryCount, new String[]{});
        EtapaCasoModel etapaCasoModel;
        while (mCur.moveToNext()) {
            etapaCasoModel = new EtapaCasoModel();
            etapaCasoModel.setDescripcion(mCur.getString(0));
            etapaCasoModel.setId(Integer.parseInt(mCur.getString(1)));
            etapaCasoModel.setIdStatus(Integer.parseInt(mCur.getString(2)));
            etapaCasoModel.setCodigoColor(mCur.getString(3));
            etapaCasoModel.setCodigoColorSecundario(mCur.getString(4));
            //etapaCasoModel.setIDColorSecundario(Integer.valueOf(mCur.getString(5)));
            etapaCasoModel.setIcono(mCur.getString(5));
            //etapaCasoModel.setIdColor(Integer.valueOf(mCur.getString(7)));
            listEtapaCaso.add(etapaCasoModel);
        }
        mCur.close();
        db.close();
        return listEtapaCaso;
    }

    public static Integer getBuscarIdEtapaDeTablaEtapasCaso(Activity activity, Integer idEtapaCaso) {
        //String idEtapaCaso = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        //Cursor c = db.rawQuery("SELECT _id, user, comment FROM comments", null);
        Cursor c = db.rawQuery(Constantes.select.concat(String.valueOf(idEtapaCaso)).concat(Constantes.from).concat(Constantes.tableEtapasCaso),
                null);
        Log.d("estaEstaEsUnaPrueba", "getBuscarIdEtapaDeTablaEtapasCaso: " + Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableEtapasCaso));
        if (c != null) {
            c.moveToFirst();
            do {
                idEtapaCaso = Integer.parseInt(c.getString(c.getColumnIndex(Constantes.id)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return idEtapaCaso;
    }

    public static String getId(Activity activity, String posicion) {
        String idEtapaCaso = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        @SuppressLint("Recycle") Cursor mCur = db.rawQuery(Constantes.select.concat(String.valueOf(Constantes.id)).concat(Constantes.from).concat(Constantes.tableEtapasCaso), null);
        try {
            mCur.moveToFirst();
            do {
                if (mCur.getString(0).equals(posicion)) {
                    idEtapaCaso = mCur.getString(0);
                    Log.d("id", String.valueOf(idEtapaCaso));
                }
            } while (mCur.moveToNext());
        } catch (SQLiteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("not selected");
        }
        return idEtapaCaso;
    }

    public static String getIdEtapaCaso(Activity activity) {
        String idEtapaCaso = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableEtapasCaso);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idEtapaCaso = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idEtapaCaso;
    }

    public static String getCodigoColorEtapaCaso(Activity activity) {
        String codigoColoeEtapaCaso = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.codigoColor).concat(Constantes.from).concat(Constantes.tableEtapasCaso);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    codigoColoeEtapaCaso = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return codigoColoeEtapaCaso;
    }

    public static String getDescripcionEtapaCaso(Activity activity) {
        String descripcionEtapaCaso = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.descripcion).concat(Constantes.from).concat(Constantes.tableEtapasCaso);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    descripcionEtapaCaso = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return descripcionEtapaCaso;
    }


}
