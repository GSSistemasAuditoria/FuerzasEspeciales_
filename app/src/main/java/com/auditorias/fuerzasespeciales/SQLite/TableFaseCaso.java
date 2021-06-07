package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.CatalogoFaseModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TableFaseCaso {


    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tableFaseCaso).concat(Constantes.parectesis_inicio)
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // Descripcion
            .concat(Constantes.id).concat(Constantes.string_text_coma) //id
            .concat(Constantes.idStatus).concat(Constantes.string_text_coma)// id de estatus
            .concat(Constantes.idFaseAnterior).concat(Constantes.string_text_coma)//IdFaseAnterior
            .concat(Constantes.idFaseSiguiente).concat(Constantes.string_text_coma)//IdFaseSiguiente
            .concat(Constantes.imagenUrl).concat(Constantes.string_text_coma)//ImagenUrl
            .concat(Constantes.limiteMesesCompromiso).concat(Constantes.string_text_coma)//LimiteMesesCompromiso
            .concat(Constantes.text_parectesis_final);

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String descripcion, String id, String idStatus, String idFaseAnterior, String idFaseSiguiente, String imagenUrl, String limiteMesesCompromiso) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.id, id);
        contentValues.put(Constantes.idStatus, idStatus);
        contentValues.put(Constantes.idFaseAnterior, idFaseAnterior);
        contentValues.put(Constantes.idFaseSiguiente, idFaseSiguiente);
        contentValues.put(Constantes.imagenUrl, imagenUrl);
        contentValues.put(Constantes.limiteMesesCompromiso, limiteMesesCompromiso);

        db.insert(Constantes.tableFaseCaso, null, contentValues);

    }

    public static void setAgregarFaceCaso (Activity activity, CatalogoFaseModel catalogoFaseModel){
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTableFaseCaso().AddInformations(db, catalogoFaseModel.getDescripcion(), String.valueOf(catalogoFaseModel.getId()), String.valueOf(catalogoFaseModel.getIdStatus()),
                String.valueOf(catalogoFaseModel.getIdFaseAnterior()), String.valueOf(catalogoFaseModel.getIdFaseSiguiente()), catalogoFaseModel.getImagenUrl(), String.valueOf(catalogoFaseModel.getLimiteMesesCompromiso()));
        db.close();
    }

    public static String getIdFaceCaso(Activity activity) {
        String idFaceCaso = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tableFaseCaso);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idFaceCaso = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idFaceCaso;
    }


}
