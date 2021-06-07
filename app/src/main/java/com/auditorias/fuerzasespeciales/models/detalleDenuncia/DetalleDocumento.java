package com.auditorias.fuerzasespeciales.models.detalleDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetalleDocumento {

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
    private String contenido;
    @SerializedName("IdCambioFecha")
    @Expose
    private Integer idCambioFecha;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapa")
    @Expose
    private Integer idEtapa;
    @SerializedName("IdIntegracion")
    @Expose
    private Integer idIntegracion;
    @SerializedName("ImagenChica")
    @Expose
    private String imagenChica;
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
    private String fase;
    @SerializedName("SubFase")
    @Expose
    private String subFase;
    @SerializedName("TipoIntegracion")
    @Expose
    private String tipoIntegracion;

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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getIdCambioFecha() {
        return idCambioFecha;
    }

    public void setIdCambioFecha(Integer idCambioFecha) {
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

    public Integer getIdIntegracion() {
        return idIntegracion;
    }

    public void setIdIntegracion(Integer idIntegracion) {
        this.idIntegracion = idIntegracion;
    }

    public String getImagenChica() {
        return imagenChica;
    }

    public void setImagenChica(String imagenChica) {
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

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getSubFase() {
        return subFase;
    }

    public void setSubFase(String subFase) {
        this.subFase = subFase;
    }

    public String getTipoIntegracion() {
        return tipoIntegracion;
    }

    public void setTipoIntegracion(String tipoIntegracion) {
        this.tipoIntegracion = tipoIntegracion;
    }

}
