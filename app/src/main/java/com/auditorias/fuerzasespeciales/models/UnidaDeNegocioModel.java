package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnidaDeNegocioModel {

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
    @SerializedName("Ceco")
    @Expose
    private String ceco;
    @SerializedName("NombreCorto")
    @Expose
    private String nombreCorto;

    public UnidaDeNegocioModel(String descripcion, String fechaMod, Integer id, Integer idStatus, String ceco, String nombreCorto) {
        this.descripcion = descripcion;
        this.fechaMod = fechaMod;
        this.id = id;
        this.idStatus = idStatus;
        this.ceco = ceco;
        this.nombreCorto = nombreCorto;
    }

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

    public String getCeco() {
        return ceco;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }


}
