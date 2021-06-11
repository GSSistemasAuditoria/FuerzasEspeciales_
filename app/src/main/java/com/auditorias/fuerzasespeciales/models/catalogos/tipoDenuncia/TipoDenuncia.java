package com.auditorias.fuerzasespeciales.models.catalogos.tipoDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TipoDenuncia {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<DataTipoDenuncia> lisTipoDenuncia = null;

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

    public List<DataTipoDenuncia> getLisTipoDenuncia() {
        return lisTipoDenuncia;
    }

    public void setLisTipoDenuncia(List<DataTipoDenuncia> lisTipoDenuncia) {
        this.lisTipoDenuncia = lisTipoDenuncia;
    }


}
