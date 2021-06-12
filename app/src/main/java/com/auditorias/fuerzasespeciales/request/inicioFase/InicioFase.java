package com.auditorias.fuerzasespeciales.request.inicioFase;

import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InicioFase {

    @SerializedName("idTipoApp")
    @Expose
    private Integer idTipoApp;
    @SerializedName("caso")
    @Expose
    private Denuncia denuncia;
    @SerializedName("fase")
    @Expose
    private Fase fase;
    @SerializedName("responsables")
    @Expose
    private List<ResponsablesFase> responsables = null;
    @SerializedName("documentos")
    @Expose
    private List<Documentos> documentos = null;

    public InicioFase(Denuncia denuncia, Fase fase, List<ResponsablesFase> responsables) {
        this.denuncia = denuncia;
        this.fase = fase;
        this.responsables = responsables;
    }

    public InicioFase(Integer idTipoApp, Denuncia denuncia, Fase fase, List<ResponsablesFase> responsables, List<Documentos> documentos) {
        this.idTipoApp = idTipoApp;
        this.denuncia = denuncia;
        this.fase = fase;
        this.responsables = responsables;
        this.documentos = documentos;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
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

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    public Integer getIdTipoApp() {
        return idTipoApp;
    }

    public void setIdTipoApp(Integer idTipoApp) {
        this.idTipoApp = idTipoApp;
    }
}
