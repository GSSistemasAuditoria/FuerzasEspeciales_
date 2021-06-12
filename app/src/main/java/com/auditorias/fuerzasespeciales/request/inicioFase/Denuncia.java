package com.auditorias.fuerzasespeciales.request.inicioFase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Denuncia {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("DatosDemanda")
    @Expose
    private String datosDemanda;

    @SerializedName("DatosAgencia")
    @Expose
    private String datosAgencia;

    @SerializedName("IdStatusSentencia")
    @Expose
    private Integer IdStatusSentencia;

    public Denuncia(Integer id, String datosDemanda, String datosAgencia) {
        this.id = id;
        this.datosDemanda = datosDemanda;
        this.datosAgencia = datosAgencia;
    }

    public Denuncia(String datosDemanda, String datosAgencia) {
        this.datosDemanda = datosDemanda;
        this.datosAgencia = datosAgencia;
    }

    public Denuncia(Integer idStatusSentencia) {
        IdStatusSentencia = idStatusSentencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatosDemanda() {
        return datosDemanda;
    }

    public void setDatosDemanda(String datosDemanda) {
        this.datosDemanda = datosDemanda;
    }

    public String getDatosAgencia() {
        return datosAgencia;
    }

    public void setDatosAgencia(String datosAgencia) {
        this.datosAgencia = datosAgencia;
    }

    public Integer getIdStatusSentencia() {
        return IdStatusSentencia;
    }

    public void setIdStatusSentencia(Integer idStatusSentencia) {
        IdStatusSentencia = idStatusSentencia;
    }
}
