package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosCasoFaseModel {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("ActivaoUltima")
    @Expose
    private boolean activaoUltima;
    @SerializedName("FechaCierre")
    @Expose
    private Object fechaCierre;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaInicio")
    @Expose
    private Object fechaInicio;
    @SerializedName("FechaMod")
    @Expose
    private Object fechaMod;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaFase")
    @Expose
    private Integer idEtapaFase;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Object idStatusAutorizacion;
    @SerializedName("IdSubFase")
    @Expose
    private Integer idSubFase;
    @SerializedName("PorcentajeAvanceGeneral")
    @Expose
    private Integer porcentajeAvanceGeneral;
    @SerializedName("ColorEtapaFase")
    @Expose
    private String colorEtapaFase;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("NombreFase")
    @Expose
    private String nombreFase;
    @SerializedName("NombreSubfase")
    @Expose
    private Object nombreSubfase;

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

    public boolean getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(boolean activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public Object getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Object fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public Object getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Object fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Object getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Object fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaFase() {
        return idEtapaFase;
    }

    public void setIdEtapaFase(Integer idEtapaFase) {
        this.idEtapaFase = idEtapaFase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Object getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Object idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Integer getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Integer idSubFase) {
        this.idSubFase = idSubFase;
    }

    public Integer getPorcentajeAvanceGeneral() {
        return porcentajeAvanceGeneral;
    }

    public void setPorcentajeAvanceGeneral(Integer porcentajeAvanceGeneral) {
        this.porcentajeAvanceGeneral = porcentajeAvanceGeneral;
    }

    public String getColorEtapaFase() {
        return colorEtapaFase;
    }

    public void setColorEtapaFase(String colorEtapaFase) {
        this.colorEtapaFase = colorEtapaFase;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public Object getNombreSubfase() {
        return nombreSubfase;
    }

    public void setNombreSubfase(Object nombreSubfase) {
        this.nombreSubfase = nombreSubfase;
    }

}
