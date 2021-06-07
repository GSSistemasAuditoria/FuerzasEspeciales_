package com.auditorias.fuerzasespeciales.models.denucia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosDenuncia {

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
    @SerializedName("AvanceCaso")
    @Expose
    private Integer avanceCaso;
    @SerializedName("DatosAgencia")
    @Expose
    private String datosAgencia;
    @SerializedName("DatosDemanda")
    @Expose
    private String datosDemanda;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("FechaReporte")
    @Expose
    private String fechaReporte;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("IdAbogado")
    @Expose
    private String idAbogado;
    @SerializedName("IdEtapaCaso")
    @Expose
    private Integer idEtapaCaso;
    @SerializedName("IdRegion")
    @Expose
    private Integer idRegion;
    @SerializedName("IdStatusSentencia")
    @Expose
    private Integer idStatusSentencia;
    @SerializedName("IdTipoFraude")
    @Expose
    private Integer idTipoFraude;
    @SerializedName("IdUdN")
    @Expose
    private Integer idUdN;
    @SerializedName("Importe")
    @Expose
    private Double importe;
    @SerializedName("MontoRecuperado")
    @Expose
    private Double montoRecuperado;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("TotalResponsables")
    @Expose
    private Integer totalResponsables;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("ColorAutorizacion1")
    @Expose
    private String colorAutorizacion1;
    @SerializedName("ColorAutorizacion2")
    @Expose
    private String colorAutorizacion2;
    @SerializedName("ColorEtapaCaso")
    @Expose
    private String colorEtapaCaso;
    @SerializedName("ColorFase")
    @Expose
    private String colorFase;
    @SerializedName("ColorSubFase")
    @Expose
    private String colorSubFase;
    @SerializedName("Documentos")
    @Expose
    private List<Object> documentos = null;
    @SerializedName("EtapaCaso")
    @Expose
    private String etapaCaso;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("EtapaSubFase")
    @Expose
    private String etapaSubFase;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("Fases")
    @Expose
    private String fases;
    @SerializedName("FechaCierreFaseAnterior")
    @Expose
    private String fechaCierreFaseAnterior;
    @SerializedName("FechaCompromisoAnterior")
    @Expose
    private String fechaCompromisoAnterior;
    @SerializedName("FechaSolicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdEtapaFase")
    @Expose
    private Integer idEtapaFase;
    @SerializedName("IdEtapaSubFase")
    @Expose
    private Object idEtapaSubFase;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("IdStatusAutorizacion")
    @Expose
    private Integer idStatusAutorizacion;
    @SerializedName("IdSubFase")
    @Expose
    private Integer idSubFase;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Responsables")
    @Expose
    private List<DatosDenunciaResponsable> listResponsables = null;
    @SerializedName("StatusAutorizacion")
    @Expose
    private String statusAutorizacion;
    @SerializedName("SubFase")
    @Expose
    private String subFase;
    @SerializedName("TipoFraude")
    @Expose
    private String tipoFraude;
    @SerializedName("UdN")
    @Expose
    private String udN;
    @SerializedName("UdNCeco")
    @Expose
    private String udNCeco;
    @SerializedName("Zona")
    @Expose
    private String zona;

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

    public Integer getAvanceCaso() {
        return avanceCaso;
    }

    public void setAvanceCaso(Integer avanceCaso) {
        this.avanceCaso = avanceCaso;
    }

    public String getDatosAgencia() {
        return datosAgencia;
    }

    public void setDatosAgencia(String datosAgencia) {
        this.datosAgencia = datosAgencia;
    }

    public String getDatosDemanda() {
        return datosDemanda;
    }

    public void setDatosDemanda(String datosDemanda) {
        this.datosDemanda = datosDemanda;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(String idAbogado) {
        this.idAbogado = idAbogado;
    }

    public Integer getIdEtapaCaso() {
        return idEtapaCaso;
    }

    public void setIdEtapaCaso(Integer idEtapaCaso) {
        this.idEtapaCaso = idEtapaCaso;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdStatusSentencia() {
        return idStatusSentencia;
    }

    public void setIdStatusSentencia(Integer idStatusSentencia) {
        this.idStatusSentencia = idStatusSentencia;
    }

    public Integer getIdTipoFraude() {
        return idTipoFraude;
    }

    public void setIdTipoFraude(Integer idTipoFraude) {
        this.idTipoFraude = idTipoFraude;
    }

    public Integer getIdUdN() {
        return idUdN;
    }

    public void setIdUdN(Integer idUdN) {
        this.idUdN = idUdN;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getMontoRecuperado() {
        return montoRecuperado;
    }

    public void setMontoRecuperado(Double montoRecuperado) {
        this.montoRecuperado = montoRecuperado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalResponsables() {
        return totalResponsables;
    }

    public void setTotalResponsables(Integer totalResponsables) {
        this.totalResponsables = totalResponsables;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    public String getColorAutorizacion1() {
        return colorAutorizacion1;
    }

    public void setColorAutorizacion1(String colorAutorizacion1) {
        this.colorAutorizacion1 = colorAutorizacion1;
    }

    public String getColorAutorizacion2() {
        return colorAutorizacion2;
    }

    public void setColorAutorizacion2(String colorAutorizacion2) {
        this.colorAutorizacion2 = colorAutorizacion2;
    }

    public String getColorEtapaCaso() {
        return colorEtapaCaso;
    }

    public void setColorEtapaCaso(String colorEtapaCaso) {
        this.colorEtapaCaso = colorEtapaCaso;
    }

    public String getColorFase() {
        return colorFase;
    }

    public void setColorFase(String colorFase) {
        this.colorFase = colorFase;
    }

    public String getColorSubFase() {
        return colorSubFase;
    }

    public void setColorSubFase(String colorSubFase) {
        this.colorSubFase = colorSubFase;
    }

    public List<Object> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Object> documentos) {
        this.documentos = documentos;
    }

    public String getEtapaCaso() {
        return etapaCaso;
    }

    public void setEtapaCaso(String etapaCaso) {
        this.etapaCaso = etapaCaso;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public String getEtapaSubFase() {
        return etapaSubFase;
    }

    public void setEtapaSubFase(String etapaSubFase) {
        this.etapaSubFase = etapaSubFase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    public String getFechaCierreFaseAnterior() {
        return fechaCierreFaseAnterior;
    }

    public void setFechaCierreFaseAnterior(String fechaCierreFaseAnterior) {
        this.fechaCierreFaseAnterior = fechaCierreFaseAnterior;
    }

    public String getFechaCompromisoAnterior() {
        return fechaCompromisoAnterior;
    }

    public void setFechaCompromisoAnterior(String fechaCompromisoAnterior) {
        this.fechaCompromisoAnterior = fechaCompromisoAnterior;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Object getIdEtapaSubFase() {
        return idEtapaSubFase;
    }

    public void setIdEtapaSubFase(Object idEtapaSubFase) {
        this.idEtapaSubFase = idEtapaSubFase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Integer getIdStatusAutorizacion() {
        return idStatusAutorizacion;
    }

    public void setIdStatusAutorizacion(Integer idStatusAutorizacion) {
        this.idStatusAutorizacion = idStatusAutorizacion;
    }

    public Integer getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Integer idSubFase) {
        this.idSubFase = idSubFase;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<DatosDenunciaResponsable> getListResponsables() {
        return listResponsables;
    }

    public void setListResponsables(List<DatosDenunciaResponsable> listResponsables) {
        this.listResponsables = listResponsables;
    }

    public String getStatusAutorizacion() {
        return statusAutorizacion;
    }

    public void setStatusAutorizacion(String statusAutorizacion) {
        this.statusAutorizacion = statusAutorizacion;
    }

    public String getSubFase() {
        return subFase;
    }

    public void setSubFase(String subFase) {
        this.subFase = subFase;
    }

    public String getTipoFraude() {
        return tipoFraude;
    }

    public void setTipoFraude(String tipoFraude) {
        this.tipoFraude = tipoFraude;
    }

    public String getUdN() {
        return udN;
    }

    public void setUdN(String udN) {
        this.udN = udN;
    }

    public String getUdNCeco() {
        return udNCeco;
    }

    public void setUdNCeco(String udNCeco) {
        this.udNCeco = udNCeco;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
