package com.auditorias.fuerzasespeciales.request.inicioFase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsablesFase {

    @SerializedName("IdCasoFase")
    @Expose
    private int IdCasoFase;
    @SerializedName("IdCasoResponsable")
    @Expose
    private int IdCasoResponsable;
    @SerializedName("IdStatusResponsable")
    @Expose
    private int IdStatusResponsable;

    public ResponsablesFase(int idCasoFase, int idCasoResponsable, int idStatusResponsable) {
        IdCasoFase = idCasoFase;
        IdCasoResponsable = idCasoResponsable;
        IdStatusResponsable = idStatusResponsable;
    }

    public int getIdCasoResponsable() {
        return IdCasoResponsable;
    }

    public void setIdCasoResponsable(int idCasoResponsable) {
        IdCasoResponsable = idCasoResponsable;
    }

    public int getIdStatusResponsable() {
        return IdStatusResponsable;
    }

    public void setIdStatusResponsable(int idStatusResponsable) {
        IdStatusResponsable = idStatusResponsable;
    }

    public int getIdCasoFase() {
        return IdCasoFase;
    }

    public void setIdCasoFase(int idCasoFase) {
        IdCasoFase = idCasoFase;
    }
}
