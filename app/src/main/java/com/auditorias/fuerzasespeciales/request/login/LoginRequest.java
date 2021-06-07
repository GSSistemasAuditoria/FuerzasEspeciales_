package com.auditorias.fuerzasespeciales.request.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    public LoginRequest(String idEmpleado, Integer tipoApp, String oAuthToken, String browserInfo) {
        this.idEmpleado = idEmpleado;
        this.tipoApp = tipoApp;
        this.oAuthToken = oAuthToken;
        this.browserInfo = browserInfo;
    }

    public LoginRequest(Integer tipoApp, String oAuthToken) {
        this.tipoApp = tipoApp;
        this.oAuthToken = oAuthToken;
        this.browserInfo = browserInfo;
    }

    public LoginRequest() {

    }

    @SerializedName("idEmpleado")
    @Expose
    private String idEmpleado;
    @SerializedName("tipoApp")
    @Expose
    private Integer tipoApp;
    @SerializedName("oAuthToken")
    @Expose
    private String oAuthToken;
    @SerializedName("browserInfo")
    @Expose
    private String browserInfo;

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getTipoApp() {
        return tipoApp;
    }

    public void setTipoApp(Integer tipoApp) {
        this.tipoApp = tipoApp;
    }

    public String getOAuthToken() {
        return oAuthToken;
    }

    public void setOAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
