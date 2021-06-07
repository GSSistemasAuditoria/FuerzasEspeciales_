package com.auditorias.fuerzasespeciales.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginAuthModel {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("JWT")
    @Expose
    private String jWT;
    @SerializedName("Usuario")
    @Expose
    private Usuario usuario;

    public LoginAuthModel() {
    }

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

    public String getJWT() {
        return jWT;
    }

    public void setJWT(String jWT) {
        this.jWT = jWT;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
