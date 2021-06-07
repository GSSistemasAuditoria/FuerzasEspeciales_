package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EtapaCasoModel {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("CodigoColor")
    @Expose
    private String codigoColor;
    @SerializedName("CodigoColorSecundario")
    @Expose
    private String codigoColorSecundario;
    @SerializedName("IDColorSecundario")
    @Expose
    private Integer iDColorSecundario;
    @SerializedName("Icono")
    @Expose
    private String icono;
    @SerializedName("IdColor")
    @Expose
    private Integer idColor;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(String codigoColor) {
        this.codigoColor = codigoColor;
    }

    public String getCodigoColorSecundario() {
        return codigoColorSecundario;
    }

    public void setCodigoColorSecundario(String codigoColorSecundario) {
        this.codigoColorSecundario = codigoColorSecundario;
    }

    public Integer getIDColorSecundario() {
        return iDColorSecundario;
    }

    public void setIDColorSecundario(Integer iDColorSecundario) {
        this.iDColorSecundario = iDColorSecundario;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }
}
