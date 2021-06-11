package com.auditorias.fuerzasespeciales.models.catalogos.tipoDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTipoDenuncia {

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
    @SerializedName("EtiquetaResponsables")
    @Expose
    private String etiquetaResponsables;
    @SerializedName("IdTipoResponsable")
    @Expose
    private Integer idTipoResponsable;
    @SerializedName("TipoResponsable")
    @Expose
    private String tipoResponsable;

    public DataTipoDenuncia(String descripcion, String fechaMod, Integer id, Integer idStatus, String etiquetaResponsables, Integer idTipoResponsable, String tipoResponsable) {
        this.descripcion = descripcion;
        this.fechaMod = fechaMod;
        this.id = id;
        this.idStatus = idStatus;
        this.etiquetaResponsables = etiquetaResponsables;
        this.idTipoResponsable = idTipoResponsable;
        this.tipoResponsable = tipoResponsable;
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

    public String getEtiquetaResponsables() {
        return etiquetaResponsables;
    }

    public void setEtiquetaResponsables(String etiquetaResponsables) {
        this.etiquetaResponsables = etiquetaResponsables;
    }

    public Integer getIdTipoResponsable() {
        return idTipoResponsable;
    }

    public void setIdTipoResponsable(Integer idTipoResponsable) {
        this.idTipoResponsable = idTipoResponsable;
    }

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

}

