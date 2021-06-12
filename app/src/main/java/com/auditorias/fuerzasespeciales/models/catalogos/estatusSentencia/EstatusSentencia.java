package com.auditorias.fuerzasespeciales.models.catalogos.estatusSentencia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstatusSentencia {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<DataEstatusSentencia> listDataEstatusSentencia = null;

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

    public List<DataEstatusSentencia> getListDataEstatusSentencia() {
        return listDataEstatusSentencia;
    }

    public void setListDataEstatusSentencia(List<DataEstatusSentencia> listDataEstatusSentencia) {
        this.listDataEstatusSentencia = listDataEstatusSentencia;
    }


}
