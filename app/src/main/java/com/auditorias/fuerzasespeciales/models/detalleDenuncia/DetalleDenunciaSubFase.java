package com.auditorias.fuerzasespeciales.models.detalleDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleDenunciaSubFase {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("ActivaoUltima")
    @Expose
    private Boolean activaoUltima;
    @SerializedName("FechaCierre")
    @Expose
    private String fechaCierre;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaInicio")
    @Expose
    private String fechaInicio;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaFase")
    @Expose
    private Integer idEtapaFase;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("IdSubFase")
    @Expose
    private Object idSubFase;
    @SerializedName("PorcentajeAvanceGeneral")
    @Expose
    private Integer porcentajeAvanceGeneral;
    @SerializedName("ColorEtapaFase")
    @Expose
    private String colorEtapaFase;
    @SerializedName("Documentos")
    @Expose
    private List<DetalleDocumento> listDocumentos = null;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("ImagenSrc")
    @Expose
    private String imagenSrc;
    @SerializedName("ImagenUrl")
    @Expose
    private String imagenUrl;
    @SerializedName("NombreFase")
    @Expose
    private String nombreFase;
    @SerializedName("NombreSubfase")
    @Expose
    private String nombreSubfase;
    @SerializedName("Reprogramaciones")
    @Expose
    private List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones = null;
    @SerializedName("StatusAutorizacion")
    @Expose
    private String statusAutorizacion;
    @SerializedName("Subfases")
    @Expose
    private String subfases;

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

    public Boolean getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(Boolean activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaFase() {
        return idEtapaFase;
    }

    public void setIdEtapaFase(Integer idEtapaFase) {
        this.idEtapaFase = idEtapaFase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Object getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Object idSubFase) {
        this.idSubFase = idSubFase;
    }

    public Integer getPorcentajeAvanceGeneral() {
        return porcentajeAvanceGeneral;
    }

    public void setPorcentajeAvanceGeneral(Integer porcentajeAvanceGeneral) {
        this.porcentajeAvanceGeneral = porcentajeAvanceGeneral;
    }

    public String getColorEtapaFase() {
        return colorEtapaFase;
    }

    public void setColorEtapaFase(String colorEtapaFase) {
        this.colorEtapaFase = colorEtapaFase;
    }

    public List<DetalleDocumento> getListDocumentos() {
        return listDocumentos;
    }

    public void setListDocumentos(List<DetalleDocumento> listDocumentos) {
        this.listDocumentos = listDocumentos;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public String getImagenSrc() {
        return imagenSrc;
    }

    public void setImagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getNombreSubfase() {
        return nombreSubfase;
    }

    public void setNombreSubfase(String nombreSubfase) {
        this.nombreSubfase = nombreSubfase;
    }

    public List<DetalleDenunciaFaseReprogramaciones> getListReprogramaciones() {
        return listReprogramaciones;
    }

    public void setListReprogramaciones(List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones) {
        this.listReprogramaciones = listReprogramaciones;
    }

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public String getSubfases() {
        return subfases;
    }

    public void setSubfases(String subfases) {
        this.subfases = subfases;
    }

    /*@SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("ActivaoUltima")
    @Expose
    private Boolean activaoUltima;
    @SerializedName("FechaCierre")
    @Expose
    private String fechaCierre;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaInicio")
    @Expose
    private String fechaInicio;
    @SerializedName("FechaMod")
    @Expose
    private String fechaMod;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaFase")
    @Expose
    private Integer idEtapaFase;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("IdStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Object idStatusAutorizacion;
    @SerializedName("IdSubFase")
    @Expose
    private Object idSubFase;
    @SerializedName("PorcentajeAvanceGeneral")
    @Expose
    private Integer porcentajeAvanceGeneral;
    @SerializedName("ColorEtapaFase")
    @Expose
    private String colorEtapaFase;
    @SerializedName("Documentos")
    @Expose
    private List<DetalleDocumento> listDocumentos = null;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("ImagenSrc")
    @Expose
    private String imagenSrc;
    @SerializedName("ImagenUrl")
    @Expose
    private String imagenUrl;
    @SerializedName("NombreFase")
    @Expose
    private String nombreFase;
    @SerializedName("NombreSubfase")
    @Expose
    private String nombreSubfase;
    @SerializedName("Reprogramaciones")
    @Expose
    private List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones = null;
    @SerializedName("StatusAutorizacion")
    @Expose
    private Object statusAutorizacion;
    @SerializedName("Subfases")
    @Expose
    private Object subfases;

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

    public Boolean getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(Boolean activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaFase() {
        return idEtapaFase;
    }

    public void setIdEtapaFase(Integer idEtapaFase) {
        this.idEtapaFase = idEtapaFase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Object getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Object idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Object getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Object idSubFase) {
        this.idSubFase = idSubFase;
    }

    public Integer getPorcentajeAvanceGeneral() {
        return porcentajeAvanceGeneral;
    }

    public void setPorcentajeAvanceGeneral(Integer porcentajeAvanceGeneral) {
        this.porcentajeAvanceGeneral = porcentajeAvanceGeneral;
    }

    public String getColorEtapaFase() {
        return colorEtapaFase;
    }

    public void setColorEtapaFase(String colorEtapaFase) {
        this.colorEtapaFase = colorEtapaFase;
    }

    public List<DetalleDocumento> getListDocumentos() {
        return listDocumentos;
    }

    public void setListDocumentos(List<DetalleDocumento> listDocumentos) {
        this.listDocumentos = listDocumentos;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public String getImagenSrc() {
        return imagenSrc;
    }

    public void setImagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getNombreSubfase() {
        return nombreSubfase;
    }

    public void setNombreSubfase(String nombreSubfase) {
        this.nombreSubfase = nombreSubfase;
    }

    public List<DetalleDenunciaFaseReprogramaciones> getListReprogramaciones() {
        return listReprogramaciones;
    }

    public void setListReprogramaciones(List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones) {
        this.listReprogramaciones = listReprogramaciones;
    }

    public Object getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(Object statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public Object getSubfases() {
        return subfases;
    }

    public void setSubfases(Object subfases) {
        this.subfases = subfases;
    }*/
}
