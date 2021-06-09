package com.auditorias.fuerzasespeciales.request.inicioFase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InicioFase {

    @SerializedName("caso")
    @Expose
    private Denuncia denuncia;
    @SerializedName("fase")
    @Expose
    private Fase fase;
    @SerializedName("responsables")
    @Expose
    private List<ResponsablesFase> responsables = null;

    public InicioFase(Denuncia denuncia, Fase fase, List<ResponsablesFase> responsables) {
        this.denuncia = denuncia;
        this.fase = fase;
        this.responsables = responsables;
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
}
