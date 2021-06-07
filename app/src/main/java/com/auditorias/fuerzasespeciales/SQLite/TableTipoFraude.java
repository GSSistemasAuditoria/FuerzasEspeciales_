package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.TipoDeEmpleadoModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TableTipoFraude {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tableTipoFraude).concat(Constantes.parectesis_inicio)
            .concat(Constantes.error).concat(Constantes.string_text_coma) //error
            .concat(Constantes.exito).concat(Constantes.string_text_coma) //exito
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // Descripcion
            .concat(Constantes.fechaMod).concat(Constantes.string_text_coma) //fecha modificacion
            .concat(Constantes.id).concat(Constantes.string_text_coma) //id
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)// id de estatus
            .concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String error, String exito, String descripcion, String fechaMod, String id, String idStatus) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.error, error);
        contentValues.put(Constantes.exito, exito);
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.fechaMod, fechaMod);
        contentValues.put(Constantes.id, id);
        contentValues.put(Constantes.idStatus, idStatus);

        db.insert(Constantes.tableTipoFraude, null, contentValues);

    }

    public static void setAgregarTipoFraude (Activity activity, TipoDeEmpleadoModel tipoDeEmpleadoModel){
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTableTipoFraude().AddInformations(db, tipoDeEmpleadoModel.getError(), tipoDeEmpleadoModel.getExito(), tipoDeEmpleadoModel.getDescripcion(), tipoDeEmpleadoModel.getFechaMod(),
                String.valueOf(tipoDeEmpleadoModel.getId()), String.valueOf(tipoDeEmpleadoModel.getIdStatus()));
        db.close();
    }

    public static String getIdTipoFruade(Activity activity) {
        String idTipoFraude = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableTipoFraude);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idTipoFraude = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idTipoFraude;
    }
}
