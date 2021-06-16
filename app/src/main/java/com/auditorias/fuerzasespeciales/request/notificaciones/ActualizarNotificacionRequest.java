package com.auditorias.fuerzasespeciales.request.notificaciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualizarNotificacionRequest {

    @SerializedName("Id")
    @Expose
    private int id;

    public ActualizarNotificacionRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
