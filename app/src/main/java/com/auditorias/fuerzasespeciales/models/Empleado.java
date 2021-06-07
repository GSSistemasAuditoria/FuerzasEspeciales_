package com.auditorias.fuerzasespeciales.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empleado {

    @SerializedName("Ceco")
    @Expose
    private String ceco;
    @SerializedName("Correo")
    @Expose
    private String correo;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("NumEmpleado")
    @Expose
    private String numEmpleado;
    @SerializedName("Puesto")
    @Expose
    private String puesto;
    @SerializedName("Vista")
    @Expose
    private String vista;

    public Empleado (){

    }

    public Empleado(String ceco, String correo, String nombre, String numEmpleado, String puesto, String vista) {
        this.ceco = ceco;
        this.correo = correo;
        this.nombre = nombre;
        this.numEmpleado = numEmpleado;
        this.puesto = puesto;
        this.vista = vista;
    }

    public Empleado(String ceco, String nombre, String numEmpleado) {
        this.ceco = ceco;
        this.nombre = nombre;
        this.numEmpleado = numEmpleado;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

}
