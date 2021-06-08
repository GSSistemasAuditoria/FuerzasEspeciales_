package com.auditorias.fuerzasespeciales.request.reprogramacion;

import com.auditorias.fuerzasespeciales.request.DatosFecha;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalculoFechaCompromiso {
    @SerializedName("datosFecha")
    @Expose
    private DatosFecha datosFecha;

    public CalculoFechaCompromiso(DatosFecha datosFecha) {
        this.datosFecha = datosFecha;
    }

    public DatosFecha getDatosFecha() {
        return datosFecha;
    }

    public void setDatosFecha(DatosFecha datosFecha) {
        this.datosFecha = datosFecha;
    }
}
