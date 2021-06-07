package com.auditorias.fuerzasespeciales.models.catalogos.faseActiva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaseActiva {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<FaseActivaDatos> listData = null;

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

    public List<FaseActivaDatos> getListData() {
        return listData;
    }

    public void setListData(List<FaseActivaDatos> listData) {
        this.listData = listData;
    }
}
