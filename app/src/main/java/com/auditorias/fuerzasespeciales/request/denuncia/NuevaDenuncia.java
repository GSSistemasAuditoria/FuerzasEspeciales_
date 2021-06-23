package com.auditorias.fuerzasespeciales.request.denuncia;

import com.auditorias.fuerzasespeciales.request.CasoRequest;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NuevaDenuncia {

    @SerializedName("caso")
    @Expose
    private CasoRequest caso;

    @SerializedName("responsables")
    @Expose
    private List<ResponsablesResquest> responsables = null;

    public NuevaDenuncia() {
    }

    public NuevaDenuncia(CasoRequest caso, List<ResponsablesResquest> responsables) {
        this.caso = caso;
        this.responsables = responsables;
    }

    public NuevaDenuncia(CasoRequest caso) {
        this.caso = caso;
    }

    public CasoRequest getCaso() {
        return caso;
    }

    public void setCaso(CasoRequest caso) {
        this.caso = caso;
    }

    public List<ResponsablesResquest> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsablesResquest> responsables) {
        this.responsables = responsables;
    }
}
