package com.auditorias.fuerzasespeciales.request.cierreFase;

import com.auditorias.fuerzasespeciales.request.inicioFase.Denuncia;
import com.auditorias.fuerzasespeciales.request.inicioFase.Fase;
import com.auditorias.fuerzasespeciales.request.inicioFase.ResponsablesFase;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CerrarFase {

    @SerializedName("caso")
    @Expose
    private Denuncia denuncia;
    @SerializedName("fase")
    @Expose
    private Fase fase;
    @SerializedName("responsables")
    @Expose
    private List<ResponsablesFase> responsables = null;
    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;
    @SerializedName("documentos")
    @Expose
    private List<Documentos> documentos = null;

    public CerrarFase(Denuncia denuncia, Fase fase, Integer idTipoApp, List<ResponsablesFase> responsables, List<Documentos> documentos) {
        this.denuncia = denuncia;
        this.documentos = documentos;
        this.fase = fase;
        this.responsables = responsables;
        this.idTipoApp = idTipoApp;
    }


    public CerrarFase(Integer idTipoApp,Fase fase, List<ResponsablesFase> responsables,  List<Documentos> documentos) {
        this.fase = fase;
        this.responsables = responsables;
        this.idTipoApp = idTipoApp;
        this.documentos = documentos;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public List<ResponsablesFase> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsablesFase> responsables) {
        this.responsables = responsables;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }
}
