package com.auditorias.fuerzasespeciales.request.inicioSubFase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubFase {

    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;

    public SubFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }
}
