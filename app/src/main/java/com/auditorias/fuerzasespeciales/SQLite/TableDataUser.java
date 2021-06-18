package com.auditorias.fuerzasespeciales.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.auditorias.fuerzasespeciales.models.login.LoginAuthModel;
import com.auditorias.fuerzasespeciales.models.login.Usuario;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class TableDataUser {

    String sql = Constantes.create_table_if_no_exists.concat( Constantes.tableDataUser).concat(Constantes.parectesis_inicio).concat(Constantes.jwt).concat(Constantes.string_text_coma)
            .concat(Constantes.ceco).concat(Constantes.string_text_coma).concat(Constantes.contrasena).concat(Constantes.string_text_coma).concat(Constantes.correo).concat(Constantes.string_text_coma)
            .concat(Constantes.fechaAlta).concat(Constantes.string_text_coma).concat(Constantes.fechaMod).concat(Constantes.string_text_coma).concat(Constantes.idEmpleado).concat(Constantes.string_text_coma)
            .concat(Constantes.idPerfil).concat(Constantes.string_text_coma).concat(Constantes.idStatus).concat(Constantes.string_text_coma).concat(Constantes.idTipoEmpleado).concat(Constantes.string_text_coma)
            .concat(Constantes.idUsuario).concat(Constantes.string_text_coma).concat(Constantes.nombre).concat(Constantes.string_text_coma).concat(Constantes.telefono).concat(Constantes.string_text_coma)
            .concat(Constantes.estatus).concat(Constantes.string_text_coma).concat(Constantes.idRegion).concat(Constantes.string_text_coma).concat(Constantes.perfil).concat(Constantes.string_text_coma)
            .concat(Constantes.region).concat(Constantes.string_text_coma).concat(Constantes.tipoEmpleado).concat(Constantes.string_text_coma).concat(Constantes.tipoEmpleado).concat(Constantes.text_parectesis_final);

    public static void setAgregarDatosDeAbogado(Activity activity, LoginAuthModel loginAuthModel, Usuario usuario) {
        SQLHelper date = new SQLHelper(activity);
        SQLiteDatabase db = date.getWritableDatabase();
        date.getTableSession().AddInformations(db, loginAuthModel.getJWT(), usuario.getCeco(), usuario.getContrasena(), usuario.getCorreo(), usuario.getFechaAlta(),
                usuario.getFechaMod(), usuario.getIdEmpleado(), usuario.getIdPerfil(), usuario.getIdStatus(), usuario.getIdTipoEmpleado(),
                usuario.getIdUsuario(), usuario.getNombre(), usuario.getTelefono(), usuario.getEstatus(), usuario.getIdRegion(),
                usuario.getPerfil(), usuario.getRegion(), usuario.getTipoEmpleado(), usuario.getZona());
        db.close();
    }

    public static String getPerfil(Activity activity) {
        String perfil = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.perfil).concat(Constantes.from).concat(Constantes.tableDataUser);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    perfil = mCur.getString(mCur.getColumnIndex(Constantes.perfil));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return perfil;
    }

    public static String getIdEmpleado(Activity activity) {
        String idEmpleado = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.idUsuario).concat(Constantes.from).concat(Constantes.tableDataUser);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idEmpleado = mCur.getString(mCur.getColumnIndex(Constantes.idUsuario));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idEmpleado;
    }

    public static String getNombreAbodago(Activity activity) {
        String nombre = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.nombre).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT Nombre FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    nombre = mCur.getString(mCur.getColumnIndex(Constantes.nombre));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return nombre;
    }

    public static String getCecoAbodago(Activity activity) {
        String ceco = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.ceco).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT Ceco FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    ceco = mCur.getString(mCur.getColumnIndex(Constantes.ceco));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return ceco;
    }

    public static String getZonaAbodago(Activity activity) {
        String zona = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.zona).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT Zona FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    zona = mCur.getString(mCur.getColumnIndex(Constantes.zona));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return zona;
    }

    public static String getRegionAbodago(Activity activity) {
        String region = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.region).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT Region FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    region = mCur.getString(mCur.getColumnIndex(Constantes.region));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return region;
    }

    public static String getIdRegion(Activity activity) {
        String idRegion = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.idRegion).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT AppFuerzasEspeciales.IdUsuario FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    idRegion = mCur.getString(mCur.getColumnIndex(Constantes.idRegion));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return idRegion;
    }

    public static String getJWT(Context activity) {
        String jwt = "";
        SQLHelper sqlHelper = new SQLHelper(activity);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.jwt).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT AppFuerzasEspeciales.IdUsuario FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    jwt = mCur.getString(mCur.getColumnIndex(Constantes.jwt));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return jwt;
    }

    public static void updateJWT(Activity activity, String JWT) {
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.jwt, JWT);
        db.update(Constantes.tableDataUser, contentValues, Constantes.jwt.concat(Constantes.signoIgual).concat(Constantes.signoInterrogacion), new String[]{JWT});

        db.close();
    }

    String getSql() {
        return sql;
    }

    public void AddInformations(SQLiteDatabase db,
                                String JWT, String Ceco, String Contrasena, String Correo, String FechaAlta, String FechaMod, String IdEmpleado, String IdPerfil, String IdStatus, String IdTipoEmpleado,
                                String IdUsuario, String Nombre, String Telefono, String Estatus, String IdRegion, String Perfil, String Region, String TipoEmpleado, String Zona) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.jwt, JWT);
        contentValues.put(Constantes.ceco, Ceco);
        contentValues.put(Constantes.contrasena, Contrasena);
        contentValues.put(Constantes.correo, Correo);
        contentValues.put(Constantes.fechaAlta, FechaAlta);
        contentValues.put(Constantes.fechaMod, FechaMod);
        contentValues.put(Constantes.idEmpleado, IdEmpleado);
        contentValues.put(Constantes.idPerfil, IdPerfil);
        contentValues.put(Constantes.idStatus, IdStatus);
        contentValues.put(Constantes.idTipoEmpleado, IdTipoEmpleado);
        contentValues.put(Constantes.idUsuario, IdUsuario);
        contentValues.put(Constantes.nombre, Nombre);
        contentValues.put(Constantes.telefono, Telefono);
        contentValues.put(Constantes.estatus, Estatus);
        contentValues.put(Constantes.idRegion, IdRegion);
        contentValues.put(Constantes.perfil, Perfil);
        contentValues.put(Constantes.region, Region);
        contentValues.put(Constantes.tipoEmpleado, TipoEmpleado);
        contentValues.put(Constantes.zona, Zona);

        db.insert(Constantes.tableDataUser, null, contentValues);

    }
}
