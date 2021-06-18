package com.auditorias.fuerzasespeciales.request.notificaciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificaciones {


    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;
    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;
    @SerializedName("token")
    @Expose
    private String token;

    public Notificaciones(int idUsuario, String token) {
        this.idUsuario = idUsuario;
        this.token = token;
    }

    public Notificaciones(Integer idTipoApp, int idUsuario) {
        this.idTipoApp = idTipoApp;
        this.idUsuario = idUsuario;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
