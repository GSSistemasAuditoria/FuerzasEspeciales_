package com.auditorias.fuerzasespeciales.models.datosUsuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleUsuario {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Ceco")
    @Expose
    private String ceco;
    @SerializedName("Correo")
    @Expose
    private String correo;
    @SerializedName("FechaAlta")
    @Expose
    private Object fechaAlta;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("IdEmpleado")
    @Expose
    private Object idEmpleado;
    @SerializedName("IdPerfil")
    @Expose
    private Integer idPerfil;
    @SerializedName("IdRegion")
    @Expose
    private Integer idRegion;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("IdTipoEmpleado")
    @Expose
    private Integer idTipoEmpleado;
    @SerializedName("IdUsuario")
    @Expose
    private String idUsuario;
    @SerializedName("IdZona")
    @Expose
    private Integer idZona;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("UltimoAcceso")
    @Expose
    private Object ultimoAcceso;
    @SerializedName("Estatus")
    @Expose
    private Object estatus;
    @SerializedName("Perfil")
    @Expose
    private String perfil;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("TipoEmpleado")
    @Expose
    private String tipoEmpleado;
    @SerializedName("Zona")
    @Expose
    private String zona;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Object getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Object fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Object getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Object idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Object ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Object getEstatus() {
        return estatus;
    }

    public void setEstatus(Object estatus) {
        this.estatus = estatus;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

}
