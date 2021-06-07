package com.auditorias.fuerzasespeciales.models.notificaciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataNotificacion {

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
    @SerializedName("IdAlarma")
    @Expose
    private Object idAlarma;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdTipoNotificacion")
    @Expose
    private Integer idTipoNotificacion;
    @SerializedName("IdUsuario")
    @Expose
    private String idUsuario;
    @SerializedName("Leida")
    @Expose
    private Boolean leida;
    @SerializedName("Icono")
    @Expose
    private String icono;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("Sonido")
    @Expose
    private String sonido;
    @SerializedName("TipoNotificacion")
    @Expose
    private String tipoNotificacion;

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

    public Object getIdAlarma() {
        return idAlarma;
    }

    public void setIdAlarma(Object idAlarma) {
        this.idAlarma = idAlarma;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(Integer idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

}
