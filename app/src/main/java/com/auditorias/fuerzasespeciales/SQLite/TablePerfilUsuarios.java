package com.auditorias.fuerzasespeciales.SQLite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.catalogos.usuario.PerfilUsuarioModel;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

import java.util.List;

public class TablePerfilUsuarios {

    String sql = Constantes.create_table_if_no_exists.concat(Constantes.tablePerfilUsuario).concat(Constantes.parectesis_inicio)
            .concat(Constantes.descripcion).concat(Constantes.string_text_coma) // Descripcion
            .concat(Constantes.id).concat(Constantes.string_text_coma) //id
            .concat(Constantes.idStatus).concat(Constantes.text_parectesis_final);// id de estatus


    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db, String descripcion,  String Id, String IdStatus) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.descripcion, descripcion);
        contentValues.put(Constantes.id, Id);
        contentValues.put(Constantes.idStatus, IdStatus);

        db.insert(Constantes.tablePerfilUsuario, null, contentValues);

    }

    public static void setAgregarNuevoPerfil(Activity activity, PerfilUsuarioModel perfilUsuarioModel) {
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTablePerfilUsuarios().AddInformations(db, perfilUsuarioModel.getDescripcion(), String.valueOf(perfilUsuarioModel.getId()), String.valueOf(perfilUsuarioModel.getIdStatus()));
        db.close();
    }

    public static List<PerfilUsuarioModel> getListPerfilUsuariosOfflineSQL(Activity activity, List<PerfilUsuarioModel> listPerdilUsuarios) {
        listPerdilUsuarios.clear();
        SQLHelper dataProducts = new SQLHelper(activity);
        SQLiteDatabase db = dataProducts.getWritableDatabase();
        String mQueryCount = Constantes.select.concat("*").concat(Constantes.from).concat(Constantes.tablePerfilUsuario);
        Cursor mCur = db.rawQuery(mQueryCount, new String[]{});
        PerfilUsuarioModel perfilUsuario;
        while (mCur.moveToNext()) {
            perfilUsuario = new PerfilUsuarioModel();
            perfilUsuario.setDescripcion(mCur.getString(0));
            perfilUsuario.setId(Integer.valueOf(mCur.getString(1)));
            perfilUsuario.setIdStatus(Integer.valueOf(mCur.getString(2)));
            listPerdilUsuarios.add(perfilUsuario);
        }
        mCur.close();
        db.close();
        return listPerdilUsuarios;
    }

    public static String getIdPerfil(Activity activity) {
        String id = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tablePerfilUsuario);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    id = mCur.getString(mCur.getColumnIndex(Constantes.id));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return id;
    }

    public static String getseleccionarIdPerfil(Activity activity, String idPerfil ) {
        String idPerfilSelecto = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        @SuppressLint("Recycle") Cursor mCur = db.rawQuery(Constantes.select.concat(Constantes.id).concat(Constantes.from).concat(Constantes.tablePerfilUsuario), null);
        try {
            mCur.moveToFirst();
            do {
                if (mCur.getString(0).equals(idPerfil)) {
                    idPerfilSelecto = mCur.getString(0);
                    //Log.d("id", String.valueOf(idPerfil));
                }
            } while (mCur.moveToNext());
        } catch (SQLiteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("not selected");
        }
        return idPerfilSelecto;
    }
}
