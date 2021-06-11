package com.auditorias.fuerzasespeciales.models.denucia.inicio;

import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InicioFase {

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
    private Object fechaCierre;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaInicio")
    @Expose
    private String fechaInicio;
    @SerializedName("FechaMod")
    @Expose
    private Object fechaMod;
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
    private Integer idSubFase;
    @SerializedName("PorcentajeAvanceGeneral")
    @Expose
    private Integer porcentajeAvanceGeneral;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("AvanceCaso")
    @Expose
    private Integer avanceCaso;
    @SerializedName("CasoDescripcion")
    @Expose
    private String casoDescripcion;
    @SerializedName("CasoNombre")
    @Expose
    private String casoNombre;
    @SerializedName("ColorEtapaCaso")
    @Expose
    private Object colorEtapaCaso;
    @SerializedName("ColorFase")
    @Expose
    private String colorFase;
    @SerializedName("DatosAgencia")
    @Expose
    private String datosAgencia;
    @SerializedName("DatosDemanda")
    @Expose
    private String datosDemanda;
    @SerializedName("EtapaCaso")
    @Expose
    private Object etapaCaso;
    @SerializedName("EtapaFase")
    @Expose
    private String etapaFase;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("IdEmpleado")
    @Expose
    private Integer idEmpleado;
    @SerializedName("IdTipoDenuncia")
    @Expose
    private Integer idTipoDenuncia;
    @SerializedName("Importe")
    @Expose
    private Double importe;
    @SerializedName("MontoRecuperado")
    @Expose
    private Double montoRecuperado;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Responsables")
    @Expose
    private List<DatosDenunciaResponsable> listResponsables = null;
    @SerializedName("SubFase")
    @Expose
    private String subFase;
    @SerializedName("TipoDenuncia")
    @Expose
    private String tipoDenuncia;
    @SerializedName("TipoFraude")
    @Expose
    private String tipoFraude;
    @SerializedName("TotalResponsables")
    @Expose
    private Integer totalResponsables;
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

    public Boolean getActivaoUltima() {
        return activaoUltima;
    }

    public void setActivaoUltima(Boolean activaoUltima) {
        this.activaoUltima = activaoUltima;
    }

    public Object getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Object fechaCierre) {
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

    public Object getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Object fechaMod) {
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

    public Integer getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Integer idSubFase) {
        this.idSubFase = idSubFase;
    }

    public Integer getPorcentajeAvanceGeneral() {
        return porcentajeAvanceGeneral;
    }

    public void setPorcentajeAvanceGeneral(Integer porcentajeAvanceGeneral) {
        this.porcentajeAvanceGeneral = porcentajeAvanceGeneral;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    public Integer getAvanceCaso() {
        return avanceCaso;
    }

    public void setAvanceCaso(Integer avanceCaso) {
        this.avanceCaso = avanceCaso;
    }

    public String getCasoDescripcion() {
        return casoDescripcion;
    }

    public void setCasoDescripcion(String casoDescripcion) {
        this.casoDescripcion = casoDescripcion;
    }

    public String getCasoNombre() {
        return casoNombre;
    }

    public void setCasoNombre(String casoNombre) {
        this.casoNombre = casoNombre;
    }

    public Object getColorEtapaCaso() {
        return colorEtapaCaso;
    }

    public void setColorEtapaCaso(Object colorEtapaCaso) {
        this.colorEtapaCaso = colorEtapaCaso;
    }

    public String getColorFase() {
        return colorFase;
    }

    public void setColorFase(String colorFase) {
        this.colorFase = colorFase;
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

    public Object getEtapaCaso() {
        return etapaCaso;
    }

    public void setEtapaCaso(Object etapaCaso) {
        this.etapaCaso = etapaCaso;
    }

    public String getEtapaFase() {
        return etapaFase;
    }

    public void setEtapaFase(String etapaFase) {
        this.etapaFase = etapaFase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdTipoDenuncia() {
        return idTipoDenuncia;
    }

    public void setIdTipoDenuncia(Integer idTipoDenuncia) {
        this.idTipoDenuncia = idTipoDenuncia;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<DatosDenunciaResponsable> getResponsables() {
        return listResponsables;
    }

    public void setResponsables(List<DatosDenunciaResponsable> listResponsables) {
        this.listResponsables = listResponsables;
    }

    public String getSubFase() {
        return subFase;
    }

    public void setSubFase(String subFase) {
        this.subFase = subFase;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getTipoFraude() {
        return tipoFraude;
    }

    public void setTipoFraude(String tipoFraude) {
        this.tipoFraude = tipoFraude;
    }

    public Integer getTotalResponsables() {
        return totalResponsables;
    }

    public void setTotalResponsables(Integer totalResponsables) {
        this.totalResponsables = totalResponsables;
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
