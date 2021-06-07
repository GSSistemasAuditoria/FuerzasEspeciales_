package com.auditorias.fuerzasespeciales.request;

import com.auditorias.fuerzasespeciales.models.login.LoginAuthModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendRequest {

    @SerializedName("LoginoAuthResult")
    @Expose
    private LoginAuthModel loginoAuthResult;

    @SerializedName("udn")
    @Expose
    private NuevoCecoRequest udn;

    @SerializedName("caso")
    @Expose
    private CasoRequest caso;

    @SerializedName("responsables")
    @Expose
    private List<ResponsablesResquest> responsables = null;


    public SendRequest(LoginAuthModel loginoAuthResult) {
        this.loginoAuthResult = loginoAuthResult;
    }

    public SendRequest(CasoRequest caso, List<ResponsablesResquest> responsables){
        this.caso = caso;
        this.responsables = responsables;
    }


    public SendRequest(NuevoCecoRequest udn) {
        this.udn = udn;
    }

    public NuevoCecoRequest getUdn() {
        return udn;
    }

    public void setUdn(NuevoCecoRequest udn) {
        this.udn = udn;
    }

    public LoginAuthModel getLoginoAuthResult() {
        return loginoAuthResult;
    }

    public void setLoginoAuthResult(LoginAuthModel loginoAuthResult) {
        this.loginoAuthResult = loginoAuthResult;
    }

    public CasoRequest getCasoRequest() {
        return caso;
    }

    public void setCasoRequest(CasoRequest caso) {
        this.caso = caso;
    }

    public List<ResponsablesResquest> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsablesResquest> responsables) {
        this.responsables = responsables;
    }

    public CasoRequest getCaso() {
        return caso;
    }

    public void setCaso(CasoRequest caso) {
        this.caso = caso;
    }


}

