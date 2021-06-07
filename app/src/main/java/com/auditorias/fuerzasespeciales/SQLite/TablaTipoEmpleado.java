package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.TipoDeEmpleadoModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TablaTipoEmpleado {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tableTipoEmpleado).concat(Constantes.parectesis_inicio)
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // Descripcion
            .concat(Constantes.id).concat(Constantes.string_text_coma) //id
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)// id de estatus
            .concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String descripcion, String id, String idStatus) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.id, id);
        contentValues.put(Constantes.idStatus, idStatus);

        db.insert(Constantes.tableEtapasCaso, null, contentValues);

    }

    public static void setAgregarTipoEmpleado(Activity activity, TipoDeEmpleadoModel tipoDeEmpleadoModel){
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTablaTipoEmpleado().AddInformations(db, tipoDeEmpleadoModel.getDescripcion(), String.valueOf(tipoDeEmpleadoModel.getId()), String.valueOf(tipoDeEmpleadoModel.getIdStatus()));
        db.close();
    }

    public static String getIdTipoEmpleado(Activity activity) {
        String idTipoEmpleado = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableTipoEmpleado);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idTipoEmpleado = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idTipoEmpleado;
    }
}
