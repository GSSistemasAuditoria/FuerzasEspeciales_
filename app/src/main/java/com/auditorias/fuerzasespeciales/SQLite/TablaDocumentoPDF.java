package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TablaDocumentoPDF {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tablaDocumentoPDF).concat(Constantes.parectesis_inicio)
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // descripcion
            .concat(Constantes.idIntegracionDoc).concat(Constantes.string_text_coma) // idIntegracionDoc
            .concat(Constantes.tipoArchivo).concat(Constantes.string_text_coma) //tipoArchivo
            .concat(Constantes.tamanioArhivo).concat(Constantes.string_text_coma)// tamanioArhivo
            .concat(Constantes.stringArchivo).concat(Constantes.string_text_coma)//stringArchivo
            .concat(Constantes.path).concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String descripcion,String idIntegracionDoc, String tipoArchivo, String tamanioArhivo, String stringArchivo, String path) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.idIntegracionDoc, idIntegracionDoc);
        contentValues.put(Constantes.tipoArchivo, tipoArchivo);
        contentValues.put(Constantes.tamanioArhivo, tamanioArhivo);
        contentValues.put(Constantes.stringArchivo, stringArchivo);
        contentValues.put(Constantes.path, path);

        db.insert(Constantes.tablaDocumentoPDF, null, contentValues);
        db.close();
    }

    public static void setAgregarDocumentoPDF(Activity activity, String descripcion,String idIntegracionDoc, String tipoArchivo, String tamanioArhivo, String stringArchivo, String path) {
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTablaDocumentoPDF().AddInformations(db, descripcion, idIntegracionDoc, tipoArchivo, tamanioArhivo, stringArchivo, path);
        db.close();
    }

    public static String getDescripcionPDF(Activity activity) {
        String descripcion = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.descripcion).concat(Constantes.from).concat(Constantes.tablaDocumentoPDF);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    descripcion = mCur.getString(mCur.getColumnIndex(Constantes.descripcion));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return descripcion;
    }

    public static String getTipoArchivo(Activity activity) {
        String tipoArchivo = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.tipoArchivo).concat(Constantes.from).concat(Constantes.tablaDocumentoPDF);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    tipoArchivo = mCur.getString(mCur.getColumnIndex(Constantes.tipoArchivo));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return tipoArchivo;
    }

    public static String getTamanioArhivo(Activity activity) {
        String tamanioArhivo = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.tamanioArhivo).concat(Constantes.from).concat(Constantes.tablaDocumentoPDF);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    tamanioArhivo = mCur.getString(mCur.getColumnIndex(Constantes.tamanioArhivo));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return tamanioArhivo;
    }

    public static String getPath(Activity activity) {
        String path = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.path).concat(Constantes.from).concat(Constantes.tablaDocumentoPDF);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    path = mCur.getString(mCur.getColumnIndex(Constantes.path));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return path;
    }

    public static String getStringArchivo(Activity activity) {
        String stringArchivo = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.stringArchivo).concat(Constantes.from).concat(Constantes.tablaDocumentoPDF);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    stringArchivo = mCur.getString(mCur.getColumnIndex(Constantes.stringArchivo));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return stringArchivo;
    }
    public void updateTablaDocumentoPDF(SQLiteDatabase db, String descripcion, String tipoArchivo, String tamanioArhivo, String stringArchivo) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.tipoArchivo, tipoArchivo);
        contentValues.put(Constantes.tamanioArhivo, tamanioArhivo);
        contentValues.put(Constantes.stringArchivo, stringArchivo);

        db.update(
                Constantes.tablaDocumentoPDF, //Tsbla
                contentValues, //Datos
                Constantes.descripcion.concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(Constantes.and)
                .concat(Constantes.tipoArchivo).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(Constantes.and)
                .concat(Constantes.tamanioArhivo).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(Constantes.and)
                .concat(Constantes.stringArchivo).concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion).concat(Constantes.and),
                new String[]{descripcion}
                );

        db.close();
    }

    public static void setActualizaTablaDocumentoPDF(Activity activity, String descripcion, String tipoArchivo, String tamanioArhivo, String stringArchivo) {
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTablaDocumentoPDF().updateTablaDocumentoPDF(db, descripcion, tipoArchivo, tamanioArhivo, stringArchivo);
        db.close();
    }


}
