package com.auditorias.fuerzasespeciales.models.catalogos.faseActiva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaseActivaDatos {

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
    private String colorFase;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
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
    private String statusAutorizacion;
    @SerializedName("SubfasesActivas")
    @Expose
    private List<SubfaseActiva> listSubfasesActivas = null;

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

    public String getColorFase() {
        return colorFase;
    }

    public void setColorFase(String colorFase) {
        this.colorFase = colorFase;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
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

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public List<SubfaseActiva> getListSubfasesActivas() {
        return listSubfasesActivas;
    }

    public void setListSubfasesActivas(List<SubfaseActiva> listSubfasesActivas) {
        this.listSubfasesActivas = listSubfasesActivas;
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
    private List<SubfaseActivaModel> subfasesActivas = null;

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

    public List<SubfaseActivaModel> getSubfasesActivas() {
        return subfasesActivas;
    }

    public void setSubfasesActivas(List<SubfaseActivaModel> subfasesActivas) {
        this.subfasesActivas = subfasesActivas;
    }*/
}
