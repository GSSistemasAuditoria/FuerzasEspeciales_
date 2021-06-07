package com.auditorias.fuerzasespeciales.models.detalleDenuncia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleDenunciaFaseReprogramaciones {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Autorizada")
    @Expose
    private Boolean autorizada;
    @SerializedName("FechaCompromisoAnterior")
    @Expose
    private String fechaCompromisoAnterior;
    @SerializedName("FechaCompromisoNueva")
    @Expose
    private String fechaCompromisoNueva;
    @SerializedName("FechaRespuesta")
    @Expose
    private String fechaRespuesta;
    @SerializedName("FechaSolicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("IdAutorizador")
    @Expose
    private Object idAutorizador;
    @SerializedName("IdCambioFecha")
    @Expose
    private Integer idCambioFecha;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaSolicitud")
    @Expose
    private Integer idEtapaSolicitud;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("MotivoRechazo")
    @Expose
    private String motivoRechazo;
    @SerializedName("MotivoSolicitud")
    @Expose
    private String motivoSolicitud;
    @SerializedName("Reenviada")
    @Expose
    private Boolean reenviada;
    @SerializedName("StatusAutorizacion")
    @Expose
    private String statusAutorizacion;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("EtapaSolicitud")
    @Expose
    private String etapaSolicitud;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("FechaDocumento")
    @Expose
    private String fechaDocumento;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("IdAbogado")
    @Expose
    private Object idAbogado;
    @SerializedName("IdCaso")
    @Expose
    private Integer idCaso;
    @SerializedName("IdDocAdjunto")
    @Expose
    private Integer idDocAdjunto;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("NombreCaso")
    @Expose
    private String nombreCaso;
    @SerializedName("NombreDocumento")
    @Expose
    private String nombreDocumento;
    @SerializedName("SubFase")
    @Expose
    private Object subFase;
    @SerializedName("TamArchivo")
    @Expose
    private Integer tamArchivo;
    @SerializedName("TipoArchivo")
    @Expose
    private String tipoArchivo;
    @SerializedName("Udn")
    @Expose
    private Object udn;

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

    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public String getFechaCompromisoAnterior() {
        return fechaCompromisoAnterior;
    }

    public void setFechaCompromisoAnterior(String fechaCompromisoAnterior) {
        this.fechaCompromisoAnterior = fechaCompromisoAnterior;
    }

    public String getFechaCompromisoNueva() {
        return fechaCompromisoNueva;
    }

    public void setFechaCompromisoNueva(String fechaCompromisoNueva) {
        this.fechaCompromisoNueva = fechaCompromisoNueva;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Object getIdAutorizador() {
        return idAutorizador;
    }

    public void setIdAutorizador(Object idAutorizador) {
        this.idAutorizador = idAutorizador;
    }

    public Integer getIdCambioFecha() {
        return idCambioFecha;
    }

    public void setIdCambioFecha(Integer idCambioFecha) {
        this.idCambioFecha = idCambioFecha;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaSolicitud() {
        return idEtapaSolicitud;
    }

    public void setIdEtapaSolicitud(Integer idEtapaSolicitud) {
        this.idEtapaSolicitud = idEtapaSolicitud;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public Boolean getReenviada() {
        return reenviada;
    }

    public void setReenviada(Boolean reenviada) {
        this.reenviada = reenviada;
    }

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    public String getEtapaSolicitud() {
        return etapaSolicitud;
    }

    public void setEtapaSolicitud(String etapaSolicitud) {
        this.etapaSolicitud = etapaSolicitud;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Object getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(Object idAbogado) {
        this.idAbogado = idAbogado;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdDocAdjunto() {
        return idDocAdjunto;
    }

    public void setIdDocAdjunto(Integer idDocAdjunto) {
        this.idDocAdjunto = idDocAdjunto;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Object getSubFase() {
        return subFase;
    }

    public void setSubFase(Object subFase) {
        this.subFase = subFase;
    }

    public Integer getTamArchivo() {
        return tamArchivo;
    }

    public void setTamArchivo(Integer tamArchivo) {
        this.tamArchivo = tamArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Object getUdn() {
        return udn;
    }

    public void setUdn(Object udn) {
        this.udn = udn;
    }

    /*@SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Autorizada")
    @Expose
    private Boolean autorizada;
    @SerializedName("FechaCompromisoAnterior")
    @Expose
    private String fechaCompromisoAnterior;
    @SerializedName("FechaCompromisoNueva")
    @Expose
    private String fechaCompromisoNueva;
    @SerializedName("FechaRespuesta")
    @Expose
    private String fechaRespuesta;
    @SerializedName("FechaSolicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("IdAutorizador")
    @Expose
    private Object idAutorizador;
    @SerializedName("IdCambioFecha")
    @Expose
    private Integer idCambioFecha;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaSolicitud")
    @Expose
    private Integer idEtapaSolicitud;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("MotivoRechazo")
    @Expose
    private String motivoRechazo;
    @SerializedName("MotivoSolicitud")
    @Expose
    private String motivoSolicitud;
    @SerializedName("StatusAutorizacion")
    @Expose
    private String statusAutorizacion;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("FechaDocumento")
    @Expose
    private String fechaDocumento;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("IdAbogado")
    @Expose
    private Object idAbogado;
    @SerializedName("IdDocAdjunto")
    @Expose
    private Integer idDocAdjunto;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("NombreCaso")
    @Expose
    private String nombreCaso;
    @SerializedName("NombreDocumento")
    @Expose
    private String nombreDocumento;
    @SerializedName("SubFase")
    @Expose
    private Object subFase;
    @SerializedName("TamArchivo")
    @Expose
    private Integer tamArchivo;
    @SerializedName("TipoArchivo")
    @Expose
    private Object tipoArchivo;

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

    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public String getFechaCompromisoAnterior() {
        return fechaCompromisoAnterior;
    }

    public void setFechaCompromisoAnterior(String fechaCompromisoAnterior) {
        this.fechaCompromisoAnterior = fechaCompromisoAnterior;
    }

    public String getFechaCompromisoNueva() {
        return fechaCompromisoNueva;
    }

    public void setFechaCompromisoNueva(String fechaCompromisoNueva) {
        this.fechaCompromisoNueva = fechaCompromisoNueva;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Object getIdAutorizador() {
        return idAutorizador;
    }

    public void setIdAutorizador(Object idAutorizador) {
        this.idAutorizador = idAutorizador;
    }

    public Integer getIdCambioFecha() {
        return idCambioFecha;
    }

    public void setIdCambioFecha(Integer idCambioFecha) {
        this.idCambioFecha = idCambioFecha;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdEtapaSolicitud() {
        return idEtapaSolicitud;
    }

    public void setIdEtapaSolicitud(Integer idEtapaSolicitud) {
        this.idEtapaSolicitud = idEtapaSolicitud;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Object getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(Object idAbogado) {
        this.idAbogado = idAbogado;
    }

    public Integer getIdDocAdjunto() {
        return idDocAdjunto;
    }

    public void setIdDocAdjunto(Integer idDocAdjunto) {
        this.idDocAdjunto = idDocAdjunto;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Object getSubFase() {
        return subFase;
    }

    public void setSubFase(Object subFase) {
        this.subFase = subFase;
    }

    public Integer getTamArchivo() {
        return tamArchivo;
    }

    public void setTamArchivo(Integer tamArchivo) {
        this.tamArchivo = tamArchivo;
    }

    public Object getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(Object tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }*/
}
