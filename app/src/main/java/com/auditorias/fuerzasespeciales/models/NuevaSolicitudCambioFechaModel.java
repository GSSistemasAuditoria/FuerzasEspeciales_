package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NuevaSolicitudCambioFechaModel {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Autorizada")
    @Expose
    private Boolean autorizada;
    @SerializedName("FechaCompromisoAnterior")
    @Expose
    private String fechaCompromisoAnterior;
    @SerializedName("FechaCompromisoNueva")
    @Expose
    private String fechaCompromisoNueva;
    @SerializedName("FechaRespuesta")
    @Expose
    private Object fechaRespuesta;
    @SerializedName("FechaSolicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("IdCambioFecha")
    @Expose
    private Integer idCambioFecha;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaSolicitud")
    @Expose
    private Integer idEtapaSolicitud;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("MotivoRechazo")
    @Expose
    private Object motivoRechazo;
    @SerializedName("MotivoSolicitud")
    @Expose
    private String motivoSolicitud;
    @SerializedName("StatusAutorizacion")
    @Expose
    private String statusAutorizacion;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExito() {
        return exito;
    }

    public void setExito(String exito) {
        this.exito = exito;
    }

    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public String getFechaCompromisoAnterior() {
        return fechaCompromisoAnterior;
    }

    public void setFechaCompromisoAnterior(String fechaCompromisoAnterior) {
        this.fechaCompromisoAnterior = fechaCompromisoAnterior;
    }

    public String getFechaCompromisoNueva() {
        return fechaCompromisoNueva;
    }

    public void setFechaCompromisoNueva(String fechaCompromisoNueva) {
        this.fechaCompromisoNueva = fechaCompromisoNueva;
    }

    public Object getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Object fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Integer getIdCambioFecha() {
        return idCambioFecha;
    }

    public void setIdCambioFecha(Integer idCambioFecha) {
        this.idCambioFecha = idCambioFecha;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaSolicitud() {
        return idEtapaSolicitud;
    }

    public void setIdEtapaSolicitud(Integer idEtapaSolicitud) {
        this.idEtapaSolicitud = idEtapaSolicitud;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Object getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(Object motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }
}
