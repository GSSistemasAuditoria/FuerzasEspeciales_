package com.auditorias.fuerzasespeciales.models.denunciaTerminada;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CasosTerminados {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<DataCasosTerminados> dataCasosTerminados = null;

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

    public List<DataCasosTerminados> getDataCasosTerminados() {
        return dataCasosTerminados;
    }

    public void setDataCasosTerminados(List<DataCasosTerminados> dataCasosTerminados) {
        this.dataCasosTerminados = dataCasosTerminados;
    }
}
