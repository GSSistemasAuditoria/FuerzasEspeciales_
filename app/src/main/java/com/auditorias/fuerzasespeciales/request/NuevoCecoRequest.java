package com.auditorias.fuerzasespeciales.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NuevoCecoRequest {

    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("NombreCorto")
    @Expose
    private String nombreCorto;
    @SerializedName("Ceco")
    @Expose
    private String ceco;

    public NuevoCecoRequest(String descripcion, String nombreCorto, String ceco) {
        this.descripcion = descripcion;
        this.nombreCorto = nombreCorto;
        this.ceco = ceco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getCeco() {
        return ceco;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

}
