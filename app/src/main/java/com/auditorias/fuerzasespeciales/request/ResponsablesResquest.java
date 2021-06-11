package com.auditorias.fuerzasespeciales.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsablesResquest {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("IdTipoEmpleado")
    @Expose
    private Integer idTipoEmpleado;
    @SerializedName("IdStatusResponsable")
    @Expose
    private Integer idStatusResponsable;
    @SerializedName("IdEmpleado")
    @Expose
    private Integer idEmpleado;

    public ResponsablesResquest(String nombre, Integer idTipoEmpleado, Integer idStatusResponsable, Integer idEmpleado) {
        this.nombre = nombre;
        this.idTipoEmpleado = idTipoEmpleado;
        this.idStatusResponsable = idStatusResponsable;
        this.idEmpleado = idEmpleado;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public Integer getIdStatusResponsable() {
        return idStatusResponsable;
    }

    public void setIdStatusResponsable(Integer idStatusResponsable) {
        this.idStatusResponsable = idStatusResponsable;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
