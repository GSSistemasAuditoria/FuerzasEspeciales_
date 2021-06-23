package com.auditorias.fuerzasespeciales.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class CasoRequest {

    public CasoRequest(Integer idTipoDenuncia, Integer idUdN, Integer idTipoFraude, Integer idAbogado, Integer idEtapaCaso, String nombre, String descripcion, double importe, double montoRecuperado, String fechaReporte, Integer idRegion) {
        this.idTipoDenuncia = idTipoDenuncia;
        this.idUdN = idUdN;
        this.idTipoFraude = idTipoFraude;
        this.idAbogado = idAbogado;
        this.idEtapaCaso = idEtapaCaso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.importe = importe;
        this.montoRecuperado = montoRecuperado;
        this.fechaReporte = fechaReporte;
        this.idRegion = idRegion;
    }

    public CasoRequest() {
    }

    @SerializedName("IdTipoDenuncia")
    @Expose
    private Integer idTipoDenuncia;
    @SerializedName("IdUdN")
    @Expose
    private Integer idUdN;
    @SerializedName("IdTipoFraude")
    @Expose
    private Integer idTipoFraude;
    @SerializedName("IdAbogado")
    @Expose
    private Integer idAbogado;
    @SerializedName("IdEtapaCaso")
    @Expose
    private Integer idEtapaCaso;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Importe")
    @Expose
    private double importe;
    @SerializedName("MontoRecuperado")
    @Expose
    private double montoRecuperado;
    @SerializedName("FechaReporte")
    @Expose
    private String fechaReporte;
    @SerializedName("IdRegion")
    @Expose
    private Integer idRegion;

    public Integer getIdTipoDenuncia() {
        return idTipoDenuncia;
    }

    public void setIdTipoDenuncia(Integer idTipoDenuncia) {
        idTipoDenuncia = idTipoDenuncia;
    }

    public Integer getIdUdN() {
        return idUdN;
    }

    public void setIdUdN(Integer idUdN) {
        this.idUdN = idUdN;
    }

    public Integer getIdTipoFraude() {
        return idTipoFraude;
    }

    public void setIdTipoFraude(Integer idTipoFraude) {
        this.idTipoFraude = idTipoFraude;
    }

    public Integer getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(Integer idAbogado) {
        this.idAbogado = idAbogado;
    }

    public Integer getIdEtapaCaso() {
        return idEtapaCaso;
    }

    public void setIdEtapaCaso(Integer idEtapaCaso) {
        this.idEtapaCaso = idEtapaCaso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getMontoRecuperado() {
        return montoRecuperado;
    }

    public void setMontoRecuperado(double montoRecuperado) {
        this.montoRecuperado = montoRecuperado;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

}
