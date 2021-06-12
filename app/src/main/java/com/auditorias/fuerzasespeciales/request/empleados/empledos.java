package com.auditorias.fuerzasespeciales.request.empleados;

import com.auditorias.fuerzasespeciales.request.CasoRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class empledos {

    @SerializedName("empleado")
    @Expose
    private String empleado;

    public empledos(String empleado) {
        this.empleado = empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
