package com.auditorias.fuerzasespeciales.request.inicioSubFase;

import com.auditorias.fuerzasespeciales.request.DocumentoRequest;
import com.auditorias.fuerzasespeciales.request.inicioFase.ResponsablesFase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InicioSubFase {

    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;
    @SerializedName("fase")
    @Expose
    private SubFase SubFase;
    @SerializedName("responsables")
    @Expose
    private List<ResponsablesFase> responsables = null;
    @SerializedName("documentos")
    @Expose
    private List<Documentos> documentos = null;

    public InicioSubFase(Integer idTipoApp, com.auditorias.fuerzasespeciales.request.inicioSubFase.SubFase subFase, List<ResponsablesFase> responsables, List<Documentos> documentos) {
        this.idTipoApp = idTipoApp;
        SubFase = subFase;
        this.responsables = responsables;
        this.documentos = documentos;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }

    public com.auditorias.fuerzasespeciales.request.inicioSubFase.SubFase getSubFase() {
        return SubFase;
    }

    public void setSubFase(com.auditorias.fuerzasespeciales.request.inicioSubFase.SubFase subFase) {
        SubFase = subFase;
    }

    public List<ResponsablesFase> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsablesFase> responsables) {
        this.responsables = responsables;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }
}
