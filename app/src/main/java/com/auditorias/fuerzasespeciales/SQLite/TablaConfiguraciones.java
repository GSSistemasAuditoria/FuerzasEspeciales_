package com.auditorias.fuerzasespeciales.SQLite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.configuraciones.ConfiguracionesData;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TablaConfiguraciones {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tablaConfiguraciones).concat(Constantes.parectesis_inicio)
            .concat(Constantes.clave).concat(Constantes.string_text_coma) // clave
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // descripcion
            .concat(Constantes.fechaMod).concat(Constantes.string_text_coma)// fecha modificacion
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)//id status
            .concat(Constantes.valor).concat(Constantes.string_text_coma)//valor
            .concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String clave, String descripcion, String fechaMod, String idStatus, String valor) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.clave, clave);
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.fechaMod, fechaMod);
        contentValues.put(Constantes.idStatus, idStatus);
        contentValues.put(Constantes.valor, valor);

        db.insert(Constantes.tableFaseCaso, null, contentValues);

    }

    public static void upDateInformation(SQLiteDatabase db, String clave, String descripcion, String fechaMod, String idStatus, String valor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.clave, clave);
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.fechaMod, fechaMod);
        contentValues.put(Constantes.idStatus, idStatus);
        contentValues.put(Constantes.valor, valor);

        String mQueryCount =
                Constantes.clave.concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(" ,").
                        concat(Constantes.descripcion).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(" , ").
                        concat(Constantes.fechaMod).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(" , ").
                        concat(Constantes.idStatus).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(" , ").
                        concat(Constantes.valor).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion);

        db.update(Constantes.tablaConfiguraciones, contentValues, mQueryCount, null);
        // Update con ContentValues

        //cv.put("comment", "Esto es un comentario actualizado por el método update()");
        //String[] args = new String []{ "Academia Android"};
        //db.update("comments", cv, "user=?", args);

    }


    public static void setAgregarConfiguracion (Activity activity, ConfiguracionesData configuracionesData){
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTablaConfiguraciones().AddInformations(db, String.valueOf(configuracionesData.getClave()), String.valueOf(configuracionesData.getDescripcion()),
                String.valueOf(configuracionesData.getFechaMod()), String.valueOf(configuracionesData.getIdStatus()), String.valueOf(configuracionesData.getValor()));
        db.close();
    }


    public String getBuscarClaveConfiguraciones(Activity activity, String claveABuscar) {
        String claveObtenida ="";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.clave).concat(Constantes.from).concat(Constantes.tablaConfiguraciones);
        @SuppressLint("Recycle") Cursor mCur = db.rawQuery(mQueryCount, null);
        try {
            mCur.moveToFirst();
            do {
                if (mCur.getString(0).equals(claveABuscar)) {
                    claveObtenida = mCur.getString(0);
                    //Log.d("id", String.valueOf(id));
                }
            } while (mCur.moveToNext());
        } catch (SQLiteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("not selected");
        }
        return claveObtenida;
    }

    public static String updateTable(Activity activity) {
        String clave = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.clave).concat(Constantes.from).concat(Constantes.tablaConfiguraciones);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    clave = mCur.getString(mCur.getColumnIndex(Constantes.clave));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return clave;
    }

    public static String getClaveConfiguraciones(Activity activity) {
        String clave = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.clave).concat(Constantes.from).concat(Constantes.tablaConfiguraciones);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    clave = mCur.getString(mCur.getColumnIndex(Constantes.clave));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return clave;
    }


}
