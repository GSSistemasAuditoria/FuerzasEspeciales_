package com.auditorias.fuerzasespeciales.request.documentos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObtenerDocumentos {

    @SerializedName("idDocumento")
    @Expose
    private Integer idDocumento;
    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;

    public ObtenerDocumentos(Integer idDocumento, Integer idTipoApp) {
        this.idDocumento = idDocumento;
        this.idTipoApp = idTipoApp;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }
}

