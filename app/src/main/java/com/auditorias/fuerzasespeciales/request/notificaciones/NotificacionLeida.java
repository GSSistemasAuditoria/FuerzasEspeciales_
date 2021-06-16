package com.auditorias.fuerzasespeciales.request.notificaciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificacionLeida {

    @SerializedName("notificacion")
    @Expose
    private ActualizarNotificacionRequest actualizarNotificacionRequest;

    public NotificacionLeida(ActualizarNotificacionRequest actualizarNotificacionRequest) {
        this.actualizarNotificacionRequest = actualizarNotificacionRequest;
    }

    public ActualizarNotificacionRequest getActualizarNotificacion() {
        return actualizarNotificacionRequest;
    }

    public void setActualizarNotificacion(ActualizarNotificacionRequest actualizarNotificacionRequest) {
        this.actualizarNotificacionRequest = actualizarNotificacionRequest;
    }
}
