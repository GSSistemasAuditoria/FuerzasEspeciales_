package com.auditorias.fuerzasespeciales.request.denuncia;

import com.auditorias.fuerzasespeciales.request.CasoRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosDenunciaRequest {

    @SerializedName("idCaso")
    @Expose
    private Integer idCaso;

    public DatosDenunciaRequest(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }
}
