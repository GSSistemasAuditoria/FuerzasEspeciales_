package com.auditorias.fuerzasespeciales.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.auditorias.fuerzasespeciales.SQLite.SQLHelper;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;

public class Functions {

    private static final String TAG = Functions.class.getName();

    private Context mContext;

    public Functions() {

    }

    public Functions(Context mContext) {
        this.mContext = mContext;
    }

    public static void hideTheKeyboard(Context context, TextInputEditText textInputEditText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textInputEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i(TAG, "NetworkCapabilities.TRANSPORT_CELLULAR");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i(TAG, "NetworkCapabilities.TRANSPORT_WIFI");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i(TAG, "NetworkCapabilities.TRANSPORT_ETHERNET");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static String getIdUser(Activity activity) {
        String IdUsuario = "";
        SQLHelper dataSession = new SQLHelper(activity);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = Constantes.select.concat(Constantes.idUsuario).concat(Constantes.from).concat(Constantes.tableDataUser);//"SELECT AppFuerzasEspeciales.IdUsuario FROM tableDataUser";
        //Log.d(TAG, "getIdUser: consulta de a la base de datos buscando el idusuario " + mQueryCount);
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    IdUsuario = mCur.getString(mCur.getColumnIndex(Constantes.idUsuario));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return IdUsuario;
    }

    public static void signOff(Activity activity) {
        String table_user = "tableDataUser";
        String sql = "DROP TABLE IF EXISTS " + table_user;
        SQLHelper dataLogin = new SQLHelper(activity);
        SQLiteDatabase db = dataLogin.getWritableDatabase();
        db.execSQL(sql);
        activity.deleteDatabase("sifra_fs.db");
        db.close();
    }

    public String getNombreAbodago() {
        String nombre = "";
        SQLHelper dataSession = new SQLHelper(mContext);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = "SELECT Nombre FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    nombre = mCur.getString(mCur.getColumnIndex("Nombre"));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return nombre;
    }

    public String getCecoAbodago() {
        String ceco = "";
        SQLHelper dataSession = new SQLHelper(mContext);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = "SELECT Ceco FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    ceco = mCur.getString(mCur.getColumnIndex("Ceco"));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return ceco;
    }

    public String getZonaAbodago() {
        String zona = "";
        SQLHelper dataSession = new SQLHelper(mContext);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = "SELECT Zona FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    zona = mCur.getString(mCur.getColumnIndex("Zona"));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return zona;
    }

    public String getRegionAbodago() {
        String zona = "";
        SQLHelper dataSession = new SQLHelper(mContext);
        SQLiteDatabase db = dataSession.getWritableDatabase();
        String mQueryCount = "SELECT Region FROM tableDataUser";
        try {
            try (Cursor mCur = db.rawQuery(mQueryCount, new String[]{})) {
                mCur.moveToFirst();
                if (!mCur.isAfterLast()) {
                    zona = mCur.getString(mCur.getColumnIndex("Region"));
                    db.close();
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            //System.out.println("not selected");
        }
        return zona;
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


}

