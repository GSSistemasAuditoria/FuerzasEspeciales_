package com.auditorias.fuerzasespeciales.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.auditorias.fuerzasespeciales.webServicies.Constantes;

public class SQLHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;

    private final TableDataUser tableSession = new TableDataUser();
    private final TablePerfilUsuarios tablePerfilUsuarios = new TablePerfilUsuarios();
    private final TableEtapasCaso tableEtapasCaso = new TableEtapasCaso();
    private final TablaTipoEmpleado tablaTipoEmpleado = new TablaTipoEmpleado();
    private final TableStatusResponsableFase tableStatusResponsableFase = new TableStatusResponsableFase();
    private final TableTipoFraude tableTipoFraude = new TableTipoFraude();
    private final TableUnidadNegocio tableUnidadNegocio = new TableUnidadNegocio();
    private final TableFaseCaso tableFaseCaso = new TableFaseCaso();
    private final TablaConfiguraciones tablaConfiguraciones = new TablaConfiguraciones();
    private final TablaDocumentoPDF tablaDocumentoPDF = new TablaDocumentoPDF();

    public TableDataUser getTableSession() {
        return tableSession;
    }

    public TablePerfilUsuarios getTablePerfilUsuarios() {
        return tablePerfilUsuarios;
    }

    public TableEtapasCaso getTableEtapasCaso() {
        return tableEtapasCaso;
    }

    public TablaTipoEmpleado getTablaTipoEmpleado() {
        return tablaTipoEmpleado;
    }

    public TableTipoFraude getTableTipoFraude() {
        return tableTipoFraude;
    }

    public TableStatusResponsableFase getTableStatusResponsableFase() {
        return tableStatusResponsableFase;
    }

    public TableUnidadNegocio getTableUnidadNegocio() {
        return tableUnidadNegocio;
    }

    public TableFaseCaso getTableFaseCaso() {
        return tableFaseCaso;
    }

    public TablaConfiguraciones getTablaConfiguraciones() {
        return tablaConfiguraciones;
    }

    public TablaDocumentoPDF getTablaDocumentoPDF() {
        return tablaDocumentoPDF;
    }

    public SQLHelper(Context context) {
        super(context, Constantes.DB_name, null, Constantes.version);

        db = getWritableDatabase();
        db.execSQL(tableSession.sql);
        db.execSQL(tablePerfilUsuarios.sql);
        db.execSQL(tableEtapasCaso.sql);
        db.execSQL(tablaTipoEmpleado.sql);
        db.execSQL(tableStatusResponsableFase.sql);
        db.execSQL(tableTipoFraude.sql);
        db.execSQL(tableUnidadNegocio.sql);
        db.execSQL(tableFaseCaso.sql);
        db.execSQL(tablaConfiguraciones.sql);
        db.execSQL(tablaDocumentoPDF.sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tableSession.sql);
        db.execSQL(tablePerfilUsuarios.sql);
        db.execSQL(tableEtapasCaso.sql);
        db.execSQL(tablaTipoEmpleado.sql);
        db.execSQL(tableStatusResponsableFase.sql);
        db.execSQL(tableTipoFraude.sql);
        db.execSQL(tableUnidadNegocio.sql);
        db.execSQL(tableFaseCaso.sql);
        db.execSQL(tablaConfiguraciones.sql);
        db.execSQL(tablaDocumentoPDF.sql);
        //Log.d("base de datos operando", "tabla tableSession creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableDataUser);
        db.execSQL(tableSession.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tablePerfilUsuario);
        db.execSQL(tablePerfilUsuarios.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableEtapasCaso);
        db.execSQL(tableEtapasCaso.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableTipoEmpleado);
        db.execSQL(tablaTipoEmpleado.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableStatusResponsableFase);
        db.execSQL(tableStatusResponsableFase.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableTipoFraude);
        db.execSQL(tableTipoFraude.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableUnidadNegocio);
        db.execSQL(tableUnidadNegocio.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tableFaseCaso);
        db.execSQL(tableFaseCaso.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tablaConfiguraciones);
        db.execSQL(tablaConfiguraciones.getSql());

        db.execSQL("DROP TABLE IF EXISTS " + Constantes.tablaDocumentoPDF);
        db.execSQL(tablaDocumentoPDF.getSql());

    }
}

