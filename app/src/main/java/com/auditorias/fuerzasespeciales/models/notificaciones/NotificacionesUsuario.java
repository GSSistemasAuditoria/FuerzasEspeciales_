package com.auditorias.fuerzasespeciales.models.notificaciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificacionesUsuario {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Data")
    @Expose
    private List<DataNotificacion> dataNotificacions = null;

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

    public List<DataNotificacion> getDataNotificacions() {
        return dataNotificacions;
    }

    public void setDataNotificacions(List<DataNotificacion> dataNotificacions) {
        this.dataNotificacions = dataNotificacions;
    }


}
