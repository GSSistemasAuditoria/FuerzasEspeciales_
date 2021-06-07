package com.auditorias.fuerzasespeciales.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudRequest {

    public SolicitudRequest(Integer idCasoFase, String motivoSolicitud, String fechaCompromisoNueva) {
        this.idCasoFase = idCasoFase;
        this.motivoSolicitud = motivoSolicitud;
        this.fechaCompromisoNueva = fechaCompromisoNueva;
    }

    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("MotivoSolicitud")
    @Expose
    private String motivoSolicitud;
    @SerializedName("FechaCompromisoNueva")
    @Expose
    private String fechaCompromisoNueva;

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getFechaCompromisoNueva() {
        return fechaCompromisoNueva;
    }

    public void setFechaCompromisoNueva(String fechaCompromisoNueva) {
        this.fechaCompromisoNueva = fechaCompromisoNueva;
    }
}
