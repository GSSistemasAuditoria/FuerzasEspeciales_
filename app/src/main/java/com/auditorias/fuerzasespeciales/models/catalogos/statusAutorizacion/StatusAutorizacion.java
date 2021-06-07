package com.auditorias.fuerzasespeciales.models.catalogos.statusAutorizacion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusAutorizacion {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<EstatusAutorizacionData> data = null;

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

    public List<EstatusAutorizacionData> getData() {
        return data;
    }

    public void setData(List<EstatusAutorizacionData> data) {
        this.data = data;
    }
}
