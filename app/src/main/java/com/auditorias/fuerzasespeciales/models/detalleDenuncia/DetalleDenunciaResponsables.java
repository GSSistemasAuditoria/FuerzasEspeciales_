package com.auditorias.fuerzasespeciales.models.detalleDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleDenunciaResponsables {


    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Ceco")
    @Expose
    private String ceco;
    @SerializedName("FechaMod")
    @Expose
    private Object fechaMod;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdCasoResponsable")
    @Expose
    private Integer idCasoResponsable;
    @SerializedName("IdEmpleado")
    @Expose
    private String idEmpleado;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("IdStatusResponsable")
    @Expose
    private Integer idStatusResponsable;
    @SerializedName("IdTipoEmpleado")
    @Expose
    private Integer idTipoEmpleado;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("FaseAgregado")
    @Expose
    private String faseAgregado;
    @SerializedName("StatusResponsable")
    @Expose
    private String statusResponsable;
    @SerializedName("TipoEmpleado")
    @Expose
    private String tipoEmpleado;
    @SerializedName("TipoResponsable")
    @Expose
    private String tipoResponsable;

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

    public String getCeco() {
        return ceco;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    public Object getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Object fechaMod) {
        this.fechaMod = fechaMod;
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

    public Integer getIdCasoResponsable() {
        return idCasoResponsable;
    }

    public void setIdCasoResponsable(Integer idCasoResponsable) {
        this.idCasoResponsable = idCasoResponsable;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdStatusResponsable() {
        return idStatusResponsable;
    }

    public void setIdStatusResponsable(Integer idStatusResponsable) {
        this.idStatusResponsable = idStatusResponsable;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFaseAgregado() {
        return faseAgregado;
    }

    public void setFaseAgregado(String faseAgregado) {
        this.faseAgregado = faseAgregado;
    }

    public String getStatusResponsable() {
        return statusResponsable;
    }

    public void setStatusResponsable(String statusResponsable) {
        this.statusResponsable = statusResponsable;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }
}
