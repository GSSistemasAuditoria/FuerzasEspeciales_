package com.auditorias.fuerzasespeciales.request;

import android.content.Intent;

import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class envioRequest {

    public envioRequest(FaseRequest fase, List<ResponsablesPresentacionRequest> responsables, List<Documentos> documentos) {
        this.fase = fase;
        this.responsables = responsables;
        this.documentos = documentos;
    }

    public envioRequest(IniciaCasoRequest caso, Integer idTipoApp, FaseRequest fase) {
        this.caso = caso;
        this.idTipoApp = idTipoApp;
        this.fase = fase;
    }

    public envioRequest(Integer idTipoApp, FaseRequest fase, List<ResponsablesPresentacionRequest> responsables, List<Documentos> documentos) {
        this.idTipoApp = idTipoApp;
        this.fase = fase;
        this.responsables = responsables;
        this.documentos = documentos;
    }

    public envioRequest(IniciaCasoRequest caso,Integer idTipoApp, FaseRequest fase, List<ResponsablesPresentacionRequest> responsables, List<Documentos> documentos) {
        this.documentos = documentos;
        this.fase = fase;
        this.caso = caso;
        this.idTipoApp = idTipoApp;
        this.responsables = responsables;
    }

    public envioRequest(IniciaCasoRequest caso, FaseRequest fase, List<ResponsablesPresentacionRequest> responsables) {
        this.caso = caso;
        this.fase = fase;
        this.responsables = responsables;
    }

    public envioRequest(Integer idTipoApp, SolicitudRequest solicitud, List<Documentos> documentos) {
        this.documentos = documentos;
        this.idTipoApp = idTipoApp;
        this.solicitud = solicitud;
    }

    public envioRequest(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public envioRequest(DatosFecha datosFecha) {
        this.datosFecha = datosFecha;
    }

    @SerializedName("caso")
    @Expose
    private IniciaCasoRequest caso;
    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;
    @SerializedName("fase")
    @Expose
    private FaseRequest fase;
    @SerializedName("responsables")
    @Expose
    private List<ResponsablesPresentacionRequest> responsables = null;
    @SerializedName("documentos")
    @Expose
    private List<Documentos> documentos = null;
    @SerializedName("solicitud")
    @Expose
    private SolicitudRequest solicitud;
    @SerializedName("datosFecha")
    @Expose
    private DatosFecha datosFecha;
    @SerializedName("idUsuario")
    @Expose
    int idUsuario;

    public DatosFecha getDatosFecha() {
        return datosFecha;
    }

    public void setDatosFecha(DatosFecha datosFecha) {
        this.datosFecha = datosFecha;
    }

    public SolicitudRequest getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRequest solicitud) {
        this.solicitud = solicitud;
    }

    public IniciaCasoRequest getCaso() {
        return caso;
    }

    public void setCaso(IniciaCasoRequest caso) {
        this.caso = caso;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }

    public FaseRequest getFase() {
        return fase;
    }

    public void setFase(FaseRequest fase) {
        this.fase = fase;
    }

    public List<ResponsablesPresentacionRequest> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsablesPresentacionRequest> responsables) {
        this.responsables = responsables;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
