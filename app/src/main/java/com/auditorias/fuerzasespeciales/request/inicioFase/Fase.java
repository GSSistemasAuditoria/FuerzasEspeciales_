package com.auditorias.fuerzasespeciales.request.inicioFase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fase {

    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;

    public Fase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Fase(Integer idCasoFase, String fechaCompromiso) {
        this.idCasoFase = idCasoFase;
        this.fechaCompromiso = fechaCompromiso;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }
}
