package com.auditorias.fuerzasespeciales.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosFecha {

    public DatosFecha(Integer idFase, String fechaCompromiso) {
        this.idFase = idFase;
        this.fechaCompromiso = fechaCompromiso;
    }

    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }
}
