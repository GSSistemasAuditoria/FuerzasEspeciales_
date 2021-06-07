package com.auditorias.fuerzasespeciales.models.configuraciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Configuraciones {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<ConfiguracionesData> data = null;

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

    public List<ConfiguracionesData> getData() {
        return data;
    }

    public void setData(List<ConfiguracionesData> data) {
        this.data = data;
    }
}
