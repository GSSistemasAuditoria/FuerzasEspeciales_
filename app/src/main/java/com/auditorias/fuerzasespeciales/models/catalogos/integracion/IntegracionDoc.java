package com.auditorias.fuerzasespeciales.models.catalogos.integracion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IntegracionDoc {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<IntegracionDocData> dataList = null;

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

    public List<IntegracionDocData> getDataList() {
        return dataList;
    }

    public void setData(List<IntegracionDocData> dataList) {
        this.dataList = dataList;
    }

}
