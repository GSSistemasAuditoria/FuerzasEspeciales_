package com.auditorias.fuerzasespeciales.models;

import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.catalogos.estatusSentencia.EstatusSentencia;
import com.auditorias.fuerzasespeciales.models.catalogos.integracion.IntegracionDoc;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.models.catalogos.etapa.EtapaCaso;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.FaseActiva;
import com.auditorias.fuerzasespeciales.models.catalogos.statusAutorizacion.StatusAutorizacion;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoDenuncia.TipoDenuncia;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoEmpleado.TipoEmpleado;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoFraude.TipoFraude;
import com.auditorias.fuerzasespeciales.models.catalogos.unidadDeNegocio.UnidadDeNegocio;
import com.auditorias.fuerzasespeciales.models.configuraciones.Configuraciones;
import com.auditorias.fuerzasespeciales.models.configuraciones.ConfiguracionesData;
import com.auditorias.fuerzasespeciales.models.datosUsuario.DetalleUsuario;
import com.auditorias.fuerzasespeciales.models.datosUsuario.Empleado;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.models.denucia.GuardarDenuncia;
import com.auditorias.fuerzasespeciales.models.denucia.cerrar.CerrarFase;
import com.auditorias.fuerzasespeciales.models.denucia.inicio.InicioFase;
import com.auditorias.fuerzasespeciales.models.denunciaTerminada.CasosTerminados;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenuncia;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDocumento;
import com.auditorias.fuerzasespeciales.models.login.LoginAuthModel;
import com.auditorias.fuerzasespeciales.models.notificaciones.NotificacionesUsuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestaGeneral {

    /****  servicio de login  *******************************************************/
    @SerializedName("LoginoAuthResult")
    @Expose
    private LoginAuthModel loginoAuthResult;

    public LoginAuthModel getLoginoAuthResult() {
        return loginoAuthResult;
    }

    public void setLoginoAuthResult(LoginAuthModel loginoAuthResult) {
        this.loginoAuthResult = loginoAuthResult;
    }

    /****  servicio de Obtener Catalogo Etapa Caso  *******************************************************/
    @SerializedName("ObtenerCatalogoEtapaCasoResult")
    @Expose
    private List<EtapaCaso> listEtapaCaso = null;

    public List<EtapaCaso> getListEtapaCaso() {
        return listEtapaCaso;
    }

    public void setListEtapaCaso(List<EtapaCaso> listEtapaCaso) {
        this.listEtapaCaso = listEtapaCaso;
    }

    /****  servicio de Obtener Casos Abogado  *******************************************************/
    @SerializedName("ObtenerCasosAbogadoResult")
    @Expose
    private List<CasosAbogado> listCasosAbogado = null;

    public List<CasosAbogado> getListCasosAbogado() {
        return listCasosAbogado;
    }

    public void setListCasosAbogado (List<CasosAbogado> listCasosAbogado) {
        this.listCasosAbogado = listCasosAbogado;
    }

    /****  servicio de Obtener Configuraciones  *******************************************************/
    @SerializedName("ObtenerConfiguracionesResult")
    @Expose
    private Configuraciones configuraciones;

    public Configuraciones getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(Configuraciones configuraciones) {
        this.configuraciones = configuraciones;
    }

    /****  servicio de Obtener Configuracion *******************************************************/
    @SerializedName("ObtenerConfiguracionResult")
    @Expose
    private ConfiguracionesData configuracionData;

    public ConfiguracionesData getConfiguracionData() {
        return configuracionData;
    }

    public void setConfiguracionData(ConfiguracionesData configuracionData) {
        this.configuracionData = configuracionData;
    }

    /****  servicio de Obtener Detalle Caso *******************************************************/
    @SerializedName("ObtenerDetalleCasoResult")
    @Expose
    private DetalleDenuncia detalleDenuncia;

    public DetalleDenuncia getDetalleDenuncia() {
        return detalleDenuncia;
    }

    public void setDetalleDenuncia(DetalleDenuncia detalleDenuncia) {
        this.detalleDenuncia = detalleDenuncia;
    }


    /****  servicio de Obtener Documento  *******************************************************/
    @SerializedName("ObtenerDocumentoResult")
    @Expose
    private DetalleDocumento detalleDocumento;

    public DetalleDocumento getDetalleDocumento() {
        return detalleDocumento;
    }

    public void setDetalleDocumento(DetalleDocumento detalleDocumento) {
        this.detalleDocumento = detalleDocumento;
    }

    /****  servicio de Datos Caso  *******************************************************/
    @SerializedName("ObtenerDatosCasoResult")
    @Expose
    private DatosDenuncia datosDenuncia;

    public DatosDenuncia getDatosDenuncia() {
        return datosDenuncia;
    }

    public void setDatosDenuncia(DatosDenuncia datosDenuncia) {
        this.datosDenuncia = datosDenuncia;
    }

    /****  servicio de Catalogo Fase Activa  *******************************************************/
    @SerializedName("ObtenerCatalogoFaseActivaResult")
    @Expose
    private FaseActiva faseActiva;

    public FaseActiva getFaseActiva() {
        return faseActiva;
    }

    public void setFaseActiva(FaseActiva faseActiva) {
        this.faseActiva = faseActiva;
    }

    /****  servicio de Catalogo Fase Activa  *******************************************************/
    @SerializedName("CerrarFaseResult")
    @Expose
    private CerrarFase cerrarFase;

    public CerrarFase getCerrarFase() {
        return cerrarFase;
    }

    public void setCerrarFase(CerrarFase cerrarFase) {
        this.cerrarFase = cerrarFase;
    }


    /****  servicio de Catalogo Catalogo Status Autorizacion *******************************************************/
    @SerializedName("ObtenerCatalogoStatusAutorizacionResult")
    @Expose
    private StatusAutorizacion StatusAutorizacion;

    public StatusAutorizacion getStatusAutorizacion() {
        return StatusAutorizacion;
    }

    public void setStatusAutorizacion(StatusAutorizacion statusAutorizacion) {
        this.StatusAutorizacion = statusAutorizacion;
    }

    /****  servicio de casos terminados   *******************************************************/
    @SerializedName("ObtenerCasosTerminadosAbogadoResult")
    @Expose
    private CasosTerminados casosTerminados;

    public CasosTerminados getCasosTerminados() {
        return casosTerminados;
    }

    public void setCasosTerminados(CasosTerminados casosTerminados) {
        this.casosTerminados = casosTerminados;
    }


    /****  servicio de detalle de usuario  *******************************************************/
    @SerializedName("ObtenerDetalleUsuarioResult")
    @Expose
    private List<DetalleUsuario> detalleUsuario = null;

    public List<DetalleUsuario> getDetalleUsuario() {
        return detalleUsuario;
    }

    public void setDetalleUsuario(List<DetalleUsuario> detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

    /****  servicio de detalle de Integracion Doc  *******************************************************/
    @SerializedName("ObtenerCatalogoIntegracionDocResult")
    @Expose
    private IntegracionDoc integracionDoc;

    public IntegracionDoc getIntegracionDoc() {
        return integracionDoc;
    }

    public void setIntegracionDoc(IntegracionDoc integracionDoc) {
        this.integracionDoc = integracionDoc;
    }

    /****  servicio de Catalogo Status Responsable Fase *******************************************************/
    @SerializedName("ObtenerCatalogoStatusResponsableFaseResult")
    @Expose
    private List<EstatusResponsableFase> lisEstatusResponsableFase = null;

    public List<EstatusResponsableFase> getLisEstatusResponsableFase() {
        return lisEstatusResponsableFase;
    }

    public void setLisEstatusResponsableFase(List<EstatusResponsableFase> lisEstatusResponsableFase) {
        this.lisEstatusResponsableFase = lisEstatusResponsableFase;
    }

    /****  servicio de obtener notificaciones usuario *******************************************************/
    @SerializedName("ObtenerNotificacionesUsuarioResult")
    @Expose
    private NotificacionesUsuario notificacionesUsuario;

    public NotificacionesUsuario getNotificacionesUsuario() {
        return notificacionesUsuario;
    }

    public void setNotificacionesUsuario(NotificacionesUsuario notificacionesUsuario) {
        this.notificacionesUsuario = notificacionesUsuario;
    }

    /****  servicio de Obtener Tipo Denuncia *******************************************************/
    @SerializedName("ObtenerTipoDenunciaResult")
    @Expose
    private TipoDenuncia tipoDenuncia;

    public TipoDenuncia getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(TipoDenuncia tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    /****  servicio de Guardar Denuncia *******************************************************/
    @SerializedName("GuardaCatalogoCasoResult")
    @Expose
    private GuardarDenuncia guardarDenuncia;

    public GuardarDenuncia getGuardarDenuncia() {
        return guardarDenuncia;
    }

    public void setGuardarDenuncia(GuardarDenuncia guardarDenuncia) {
        this.guardarDenuncia = guardarDenuncia;
    }

    /****  servicio de Obtener Tipo Empleado *******************************************************/
    @SerializedName("ObtenerCatalogoTipoEmpleadoResult")
    @Expose
    private List<TipoEmpleado> listTipoEmpleado = null;

    public List<TipoEmpleado> getListTipoEmpleado() {
        return listTipoEmpleado;
    }

    public void setListTipoEmpleado(List<TipoEmpleado> listTipoEmpleado) {
        this.listTipoEmpleado = listTipoEmpleado;
    }

    /****  servicio de Obtener Unidid de Negocio *******************************************************/
    @SerializedName("ObtenerCatalogoUdNResult")
    @Expose
    private List<UnidadDeNegocio> listUnidadDeNegocio = null;

    public List<UnidadDeNegocio> getListUnidadDeNegocio() {
        return listUnidadDeNegocio;
    }

    public void setListUnidadDeNegocio(List<UnidadDeNegocio> listUnidadDeNegocio) {
        this.listUnidadDeNegocio = listUnidadDeNegocio;
    }

    /****  servicio de Obtener Tipo Fraude *******************************************************/
    @SerializedName("ObtenerCatalogoTipoFraudeResult")
    @Expose
    private List<TipoFraude> listTipoFraude = null;

    public List<TipoFraude> getListTipoFraude() {
        return listTipoFraude;
    }

    public void setListTipoFraude(List<TipoFraude> listTipoFraude) {
        this.listTipoFraude = listTipoFraude;
    }

    /****  servicio de Obtener Datos Empleado Datos Auxiliares *******************************************************/
    @SerializedName("ObtenerDatosEmpleadoDatosAuxiliaresResult")
    @Expose
    private List<Empleado> listEmpleado = null;

    public List<Empleado> getListEmpleado() {
        return listEmpleado;
    }

    public void setListEmpleado(List<Empleado> listEmpleado) {
        this.listEmpleado = listEmpleado;
    }

    /****  servicio de Iniciar Fase *******************************************************/
    @SerializedName("IniciarFaseResult")
    @Expose
    private InicioFase iniciarFase;

    public InicioFase getIniciarFase() {
        return iniciarFase;
    }

    public void setIniciarFase(InicioFase iniciarFase) {
        this.iniciarFase = iniciarFase;
    }

    /****  servicio de Obtener Tipo Denuncia *******************************************************/
    @SerializedName("ObtenerCatalogoStatusSentenciaResult")
    @Expose
    private EstatusSentencia estatusSentencia;

    public EstatusSentencia getEstatusSentencia() {
        return estatusSentencia;
    }

    public void setEstatusSentencia(EstatusSentencia estatusSentencia) {
        this.estatusSentencia = estatusSentencia;
    }
}
