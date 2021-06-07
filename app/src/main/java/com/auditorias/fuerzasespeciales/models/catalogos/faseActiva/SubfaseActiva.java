package com.auditorias.fuerzasespeciales.models.catalogos.faseActiva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubfaseActiva {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("DiasCalculoAutomatico")
    @Expose
    private Integer diasCalculoAutomatico;
    @SerializedName("DiasProrroga")
    @Expose
    private Integer diasProrroga;
    @SerializedName("DocCambioFecha")
    @Expose
    private Boolean docCambioFecha;
    @SerializedName("DocCierre")
    @Expose
    private Boolean docCierre;
    @SerializedName("DocInicio")
    @Expose
    private Boolean docInicio;
    @SerializedName("IdFaseAnterior")
    @Expose
    private Object idFaseAnterior;
    @SerializedName("IdFaseSiguiente")
    @Expose
    private Object idFaseSiguiente;
    @SerializedName("ImagenUrl")
    @Expose
    private String imagenUrl;
    @SerializedName("LimiteMesesCompromiso")
    @Expose
    private Integer limiteMesesCompromiso;
    @SerializedName("Orden")
    @Expose
    private Integer orden;
    @SerializedName("PorcentajeFin")
    @Expose
    private Integer porcentajeFin;
    @SerializedName("PorcentajeInicio")
    @Expose
    private Integer porcentajeInicio;
    @SerializedName("Subfases")
    @Expose
    private Object subfases;
    @SerializedName("ActivaoUltima")
    @Expose
    private Object activaoUltima;
    @SerializedName("ColorFase")
    @Expose
    private Object colorFase;
    @SerializedName("EtapaFase")
    @Expose
    private Object etapaFase;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapa")
    @Expose
    private Integer idEtapa;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("StatusAutorizacion")
    @Expose
    private Object statusAutorizacion;
    @SerializedName("SubfasesActivas")
    @Expose
    private Object subfasesActivas;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExito() {
        return exito;
    }

    public void setExito(String exito) {
        this.exito = exito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getDiasCalculoAutomatico() {
        return diasCalculoAutomatico;
    }

    public void setDiasCalculoAutomatico(Integer diasCalculoAutomatico) {
        this.diasCalculoAutomatico = diasCalculoAutomatico;
    }

    public Integer getDiasProrroga() {
        return diasProrroga;
    }

    public void setDiasProrroga(Integer diasProrroga) {
        this.diasProrroga = diasProrroga;
    }

    public Boolean getDocCambioFecha() {
        return docCambioFecha;
    }

    public void setDocCambioFecha(Boolean docCambioFecha) {
        this.docCambioFecha = docCambioFecha;
    }

    public Boolean getDocCierre() {
        return docCierre;
    }

    public void setDocCierre(Boolean docCierre) {
        this.docCierre = docCierre;
    }

    public Boolean getDocInicio() {
        return docInicio;
    }

    public void setDocInicio(Boolean docInicio) {
        this.docInicio = docInicio;
    }

    public Object getIdFaseAnterior() {
        return idFaseAnterior;
    }

    public void setIdFaseAnterior(Object idFaseAnterior) {
        this.idFaseAnterior = idFaseAnterior;
    }

    public Object getIdFaseSiguiente() {
        return idFaseSiguiente;
    }

    public void setIdFaseSiguiente(Object idFaseSiguiente) {
        this.idFaseSiguiente = idFaseSiguiente;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getLimiteMesesCompromiso() {
        return limiteMesesCompromiso;
    }

    public void setLimiteMesesCompromiso(Integer limiteMesesCompromiso) {
        this.limiteMesesCompromiso = limiteMesesCompromiso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getPorcentajeFin() {
        return porcentajeFin;
    }

    public void setPorcentajeFin(Integer porcentajeFin) {
        this.porcentajeFin = porcentajeFin;
    }

    public Integer getPorcentajeInicio() {
        return porcentajeInicio;
    }

    public void setPorcentajeInicio(Integer porcentajeInicio) {
        this.porcentajeInicio = porcentajeInicio;
    }

    public Object getSubfases() {
        return subfases;
    }

    public void setSubfases(Object subfases) {
        this.subfases = subfases;
    }

    public Object getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(Object activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public Object getColorFase() {
        return colorFase;
    }

    public void setColorFase(Object colorFase) {
        this.colorFase = colorFase;
    }

    public Object getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(Object etapaFase) {
        this.etapaFase = etapaFase;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Object getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(Object statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public Object getSubfasesActivas() {
        return subfasesActivas;
    }

    public void setSubfasesActivas(Object subfasesActivas) {
        this.subfasesActivas = subfasesActivas;
    }

    /*@SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("DiasCalculoAutomatico")
    @Expose
    private Integer diasCalculoAutomatico;
    @SerializedName("DiasProrroga")
    @Expose
    private Integer diasProrroga;
    @SerializedName("DocCambioFecha")
    @Expose
    private Boolean docCambioFecha;
    @SerializedName("DocCierre")
    @Expose
    private Boolean docCierre;
    @SerializedName("DocInicio")
    @Expose
    private Boolean docInicio;
    @SerializedName("IdFaseAnterior")
    @Expose
    private Object idFaseAnterior;
    @SerializedName("IdFaseSiguiente")
    @Expose
    private Object idFaseSiguiente;
    @SerializedName("ImagenUrl")
    @Expose
    private Object imagenUrl;
    @SerializedName("LimiteMesesCompromiso")
    @Expose
    private Integer limiteMesesCompromiso;
    @SerializedName("Orden")
    @Expose
    private Integer orden;
    @SerializedName("PorcentajeFin")
    @Expose
    private Integer porcentajeFin;
    @SerializedName("PorcentajeInicio")
    @Expose
    private Integer porcentajeInicio;
    @SerializedName("Subfases")
    @Expose
    private Object subfases;
    @SerializedName("ActivaoUltima")
    @Expose
    private Object activaoUltima;
    @SerializedName("ColorFase")
    @Expose
    private Object colorFase;
    @SerializedName("EtapaFase")
    @Expose
    private Object etapaFase;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapa")
    @Expose
    private Integer idEtapa;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("StatusAutorizacion")
    @Expose
    private Object statusAutorizacion;
    @SerializedName("SubfasesActivas")
    @Expose
    private Object subfasesActivas;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExito() {
        return exito;
    }

    public void setExito(String exito) {
        this.exito = exito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getDiasCalculoAutomatico() {
        return diasCalculoAutomatico;
    }

    public void setDiasCalculoAutomatico(Integer diasCalculoAutomatico) {
        this.diasCalculoAutomatico = diasCalculoAutomatico;
    }

    public Integer getDiasProrroga() {
        return diasProrroga;
    }

    public void setDiasProrroga(Integer diasProrroga) {
        this.diasProrroga = diasProrroga;
    }

    public Boolean getDocCambioFecha() {
        return docCambioFecha;
    }

    public void setDocCambioFecha(Boolean docCambioFecha) {
        this.docCambioFecha = docCambioFecha;
    }

    public Boolean getDocCierre() {
        return docCierre;
    }

    public void setDocCierre(Boolean docCierre) {
        this.docCierre = docCierre;
    }

    public Boolean getDocInicio() {
        return docInicio;
    }

    public void setDocInicio(Boolean docInicio) {
        this.docInicio = docInicio;
    }

    public Object getIdFaseAnterior() {
        return idFaseAnterior;
    }

    public void setIdFaseAnterior(Object idFaseAnterior) {
        this.idFaseAnterior = idFaseAnterior;
    }

    public Object getIdFaseSiguiente() {
        return idFaseSiguiente;
    }

    public void setIdFaseSiguiente(Object idFaseSiguiente) {
        this.idFaseSiguiente = idFaseSiguiente;
    }

    public Object getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(Object imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getLimiteMesesCompromiso() {
        return limiteMesesCompromiso;
    }

    public void setLimiteMesesCompromiso(Integer limiteMesesCompromiso) {
        this.limiteMesesCompromiso = limiteMesesCompromiso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getPorcentajeFin() {
        return porcentajeFin;
    }

    public void setPorcentajeFin(Integer porcentajeFin) {
        this.porcentajeFin = porcentajeFin;
    }

    public Integer getPorcentajeInicio() {
        return porcentajeInicio;
    }

    public void setPorcentajeInicio(Integer porcentajeInicio) {
        this.porcentajeInicio = porcentajeInicio;
    }

    public Object getSubfases() {
        return subfases;
    }

    public void setSubfases(Object subfases) {
        this.subfases = subfases;
    }

    public Object getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(Object activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public Object getColorFase() {
        return colorFase;
    }

    public void setColorFase(Object colorFase) {
        this.colorFase = colorFase;
    }

    public Object getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(Object etapaFase) {
        this.etapaFase = etapaFase;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Object getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(Object statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public Object getSubfasesActivas() {
        return subfasesActivas;
    }

    public void setSubfasesActivas(Object subfasesActivas) {
        this.subfasesActivas = subfasesActivas;
    }*/
}