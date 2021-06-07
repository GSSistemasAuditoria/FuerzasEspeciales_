package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleCasoDocumentoModel {

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
    @SerializedName("Contenido")
    @Expose
    private Object contenido;
    @SerializedName("IdCambioFecha")
    @Expose
    private Object idCambioFecha;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapa")
    @Expose
    private Integer idEtapa;
    @SerializedName("IdIntegracion")
    @Expose
    private Object idIntegracion;
    @SerializedName("ImagenChica")
    @Expose
    private Object imagenChica;
    @SerializedName("StringArchivo")
    @Expose
    private String stringArchivo;
    @SerializedName("TamArchivo")
    @Expose
    private Integer tamArchivo;
    @SerializedName("TipoArchivo")
    @Expose
    private String tipoArchivo;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("Fase")
    @Expose
    private Object fase;
    @SerializedName("SubFase")
    @Expose
    private Object subFase;
    @SerializedName("TipoIntegracion")
    @Expose
    private Object tipoIntegracion;

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

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    public Object getIdCambioFecha() {
        return idCambioFecha;
    }

    public void setIdCambioFecha(Object idCambioFecha) {
        this.idCambioFecha = idCambioFecha;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Object getIdIntegracion() {
        return idIntegracion;
    }

    public void setIdIntegracion(Object idIntegracion) {
        this.idIntegracion = idIntegracion;
    }

    public Object getImagenChica() {
        return imagenChica;
    }

    public void setImagenChica(Object imagenChica) {
        this.imagenChica = imagenChica;
    }

    public String getStringArchivo() {
        return stringArchivo;
    }

    public void setStringArchivo(String stringArchivo) {
        this.stringArchivo = stringArchivo;
    }

    public Integer getTamArchivo() {
        return tamArchivo;
    }

    public void setTamArchivo(Integer tamArchivo) {
        this.tamArchivo = tamArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public Object getFase() {
        return fase;
    }

    public void setFase(Object fase) {
        this.fase = fase;
    }

    public Object getSubFase() {
        return subFase;
    }

    public void setSubFase(Object subFase) {
        this.subFase = subFase;
    }

    public Object getTipoIntegracion() {
        return tipoIntegracion;
    }

    public void setTipoIntegracion(Object tipoIntegracion) {
        this.tipoIntegracion = tipoIntegracion;
    }
}
