package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.UnidaDeNegocioModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TableUnidadNegocio {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tableUnidadNegocio).concat(Constantes.parectesis_inicio)
            .concat(Constantes.error).concat(Constantes.string_text_coma) //error
            .concat(Constantes.exito).concat(Constantes.string_text_coma) //exito
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // Descripcion
            .concat(Constantes.fechaMod).concat(Constantes.string_text_coma) //fecha modificacion
            .concat(Constantes.id).concat(Constantes.string_text_coma) //id
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)// id de estatus
            .concat(Constantes.ceco).concat(Constantes.string_text_coma) //ceco
            .concat(Constantes.nombreCorto).concat(Constantes.string_text_coma)//nombre corto
            .concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String error, String exito, String descripcion, String fechaMod, String id, String idStatus, String ceco, String nombreCorto) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.error, error);
        contentValues.put(Constantes.exito, exito);
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.fechaMod, fechaMod);
        contentValues.put(Constantes.id, id);
        contentValues.put(Constantes.idStatus, idStatus);
        contentValues.put(Constantes.ceco, ceco);
        contentValues.put(Constantes.nombreCorto, nombreCorto);

        db.insert(Constantes.tableUnidadNegocio, null, contentValues);

    }

    public static void setAgregarTipoFraude (Activity activity, UnidaDeNegocioModel unidaDeNegocioModel){
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTableUnidadNegocio().AddInformations(db, unidaDeNegocioModel.getError(), unidaDeNegocioModel.getExito(), unidaDeNegocioModel.getDescripcion(), unidaDeNegocioModel.getFechaMod(),
                String.valueOf(unidaDeNegocioModel.getId()), String.valueOf(unidaDeNegocioModel.getIdStatus()), unidaDeNegocioModel.getCeco(), unidaDeNegocioModel.getNombreCorto());
        db.close();
    }

    public static String getIdUnidadNegocio(Activity activity) {
        String idUnidadNegocio = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableUnidadNegocio);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idUnidadNegocio = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idUnidadNegocio;
    }

}
