package com.auditorias.fuerzasespeciales.models.configuraciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfiguracionesData {
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Clave")
    @Expose
    private String clave;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("FechaMod")
    @Expose
    private Object fechaMod;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("Valor")
    @Expose
    private String valor;

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Object fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
