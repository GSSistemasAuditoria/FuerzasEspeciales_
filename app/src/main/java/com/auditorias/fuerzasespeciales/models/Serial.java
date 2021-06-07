package com.auditorias.fuerzasespeciales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.auditorias.fuerzasespeciales.models.catalogos.usuario.PerfilUsuarioModel;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.FaseActiva;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenuncia;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Serial implements Parcelable {

    public static final Creator<Serial> CREATOR = new Creator<Serial>() {
        @Override
        public Serial createFromParcel(Parcel in) {
            return new Serial(in);
        }

        @Override
        public Serial[] newArray(int size) {
            return new Serial[size];
        }
    };

    @SerializedName("ObtenerCasosAbogadoResult")
    @Expose
    private List<CasosModel> casosAbogadoList = null;

    @SerializedName("ObtenerCatalogoUdNResult")
    @Expose
    private List<UnidaDeNegocioModel> unidadDeNegocioList = null;

    @SerializedName("ObtenerCatalogoTipoFraudeResult")
    @Expose
    private List<TipoFraudeModel> tipoFraudeList = null;

    @SerializedName("ObtenerDatosEmpleadoDatosAuxiliaresResult")
    @Expose
    private List<Empleado> obtenerDatosEmpleadoDatosAuxiliaresResult = null;

    @SerializedName("ObtenerCatalogoTipoEmpleadoResult")
    @Expose
    private List<TipoDeEmpleadoModel> tipoDeEmpleadoList = null;

    @SerializedName("GuardaCatalogoUdNResult")
    @Expose
    private UnidaDeNegocioModel unidaDeNegocioModel;

    @SerializedName("GuardaCatalogoCasoResult")
    @Expose
    private GuardaCatalogoCasoModel guardaCatalogoCasoResult;

    @SerializedName("ObtenerCatalogoFaseResult")
    @Expose
    private List<CatalogoFaseModel> catalogoFaseList = null;

    @SerializedName("ObtenerDetalleCasoResult")
    @Expose
    private DetalleDenuncia obtenerDetalleCaso;

    @SerializedName("ObtenerDatosCasoResult")
    @Expose
    private DatosDenuncia obtenerDatosCasoResult;

    @SerializedName("ObtenerCatalogoStatusResponsableFaseResult")
    @Expose
    private List<StatusResponsableFaseModel> statusResponsableFaseList = null;

    @SerializedName("IniciarFaseResult")
    @Expose
    private IniciarFaseModel iniciarFaseResult;

    @SerializedName("ObtenerCatalogoFaseActivaResult")
    @Expose
    private FaseActiva faseActiva;

    //TODO: servicio de login de la app


    //TODO: servicio para obtener el catalogo de la etapa del caso
    @SerializedName("ObtenerCatalogoEtapaCasoResult")
    @Expose
    private List<EtapaCasoModel> etapaCasoList = null;

    @SerializedName("CerrarFaseResult")
    @Expose
    private CerrarFaseModel cerrarFaseResult;




    @SerializedName("ObtenerCatalogoPerfilResult")
    @Expose
    private List<PerfilUsuarioModel> listPerfilUsuarios = null;

    @SerializedName("CalculaFechaCompromisoResult")
    @Expose
    private CalculaFechaCompromisoModel calculaFechaCompromisoModel;


    @SerializedName("NuevaSolicitudCambioFechaResult")
    @Expose
    private NuevaSolicitudCambioFechaModel nuevaSolicitudCambioFechaModel;

    public NuevaSolicitudCambioFechaModel getNuevaSolicitudCambioFechaModel() {
        return nuevaSolicitudCambioFechaModel;
    }

    public void setNuevaSolicitudCambioFechaModel(NuevaSolicitudCambioFechaModel nuevaSolicitudCambioFechaModel) {
        this.nuevaSolicitudCambioFechaModel = nuevaSolicitudCambioFechaModel;
    }


    public CalculaFechaCompromisoModel getCalculaFechaCompromisoModel() {
        return calculaFechaCompromisoModel;
    }

    public void setCalculaCalculaFechaCompromisoModel(CalculaFechaCompromisoModel calculaFechaCompromisoModel) {
        this.calculaFechaCompromisoModel = calculaFechaCompromisoModel;
    }

    public List<PerfilUsuarioModel> getListPerfilUsuarios() {
        return listPerfilUsuarios;
    }

    public void setListPerfilUsuarios(List<PerfilUsuarioModel> listPerfilUsuarios) {
        this.listPerfilUsuarios = listPerfilUsuarios;
    }


    public Serial() {
    }

    protected Serial(Parcel in) {
        casosAbogadoList = in.createTypedArrayList(CasosModel.CREATOR);
        guardaCatalogoCasoResult = in.readParcelable(GuardaCatalogoCasoModel.class.getClassLoader());
        catalogoFaseList = in.createTypedArrayList(CatalogoFaseModel.CREATOR);
        obtenerDatosCasoResult = in.readParcelable(DatosDenuncia.class.getClassLoader());
    }


    public List<EtapaCasoModel> getEtapaCasoList() {
        return etapaCasoList;
    }

    public void setEtapaCasoList(List<EtapaCasoModel> etapaCasoList) {
        this.etapaCasoList = etapaCasoList;
    }

    //TODO:
    public GuardaCatalogoCasoModel getGuardaCatalogoCasoResult() {
        return guardaCatalogoCasoResult;
    }

    public void setGuardaCatalogoCasoResult(GuardaCatalogoCasoModel guardaCatalogoCasoResult) {
        this.guardaCatalogoCasoResult = guardaCatalogoCasoResult;
    }

    public List<CasosModel> getCasosAbogadoList() {
        return casosAbogadoList;
    }

    public void setCasosAbogadoList(List<CasosModel> casosAbogadoList) {
        this.casosAbogadoList = casosAbogadoList;
    }

    public List<UnidaDeNegocioModel> getUnidadDeNegocioList() {
        return unidadDeNegocioList;
    }

    public void setUnidadDeNegocioList(List<UnidaDeNegocioModel> unidadDeNegocioList) {
        this.unidadDeNegocioList = unidadDeNegocioList;
    }

    public List<TipoFraudeModel> getTipoFraudeList() {
        return tipoFraudeList;
    }

    public void setTipoFraudeList(List<TipoFraudeModel> tipoFraudeList) {
        this.tipoFraudeList = tipoFraudeList;
    }

    public UnidaDeNegocioModel getUnidaDeNegocioModel() {
        return unidaDeNegocioModel;
    }

    public void setUnidaDeNegocioModel(UnidaDeNegocioModel unidaDeNegocioModel) {
        unidaDeNegocioModel = unidaDeNegocioModel;
    }

    public List<Empleado> getObtenerDatosEmpleadoDatosAuxiliaresResult() {
        return obtenerDatosEmpleadoDatosAuxiliaresResult;
    }

    public void setObtenerDatosEmpleadoDatosAuxiliaresResult(List<Empleado> obtenerDatosEmpleadoDatosAuxiliaresResult) {
        this.obtenerDatosEmpleadoDatosAuxiliaresResult = obtenerDatosEmpleadoDatosAuxiliaresResult;
    }

    public List<TipoDeEmpleadoModel> gettipoDeEmpleadoList() {
        return tipoDeEmpleadoList;
    }

    public void settipoDeEmpleadoList(List<TipoDeEmpleadoModel> tipoDeEmpleadoList) {
        this.tipoDeEmpleadoList = tipoDeEmpleadoList;
    }

    public List<CatalogoFaseModel> getCatalogoFaseList() {
        return catalogoFaseList;
    }

    public void setFaseDelCasoResultList(List<CatalogoFaseModel> catalogoFaseList) {
        this.catalogoFaseList = catalogoFaseList;
    }

    public DetalleDenuncia getDetalleCasoModel() {
        return obtenerDetalleCaso;
    }

    public void setDetalleCasoModelResult(DetalleDenuncia obtenerDetalleCaso) {
        this.obtenerDetalleCaso = obtenerDetalleCaso;
    }

    public DatosDenuncia getDatosCasoModel() {
        return obtenerDatosCasoResult;
    }

    public void setDatosCasoModel(DatosDenuncia obtenerDatosCasoResult) {
        this.obtenerDatosCasoResult = obtenerDatosCasoResult;
    }

    public List<StatusResponsableFaseModel> getStatusResponsableFaseList() {
        return statusResponsableFaseList;
    }

    public void setStatusResponsableFaseList(List<StatusResponsableFaseModel> statusResponsableFaseList) {
        this.statusResponsableFaseList = statusResponsableFaseList;
    }

    public IniciarFaseModel getIniciarFaseResult() {
        return iniciarFaseResult;
    }

    public void setIniciarFaseResult(IniciarFaseModel iniciarFaseResult) {
        this.iniciarFaseResult = iniciarFaseResult;
    }

    public FaseActiva getFaseActiva() {
        return faseActiva;
    }

    public void setFaseActiva(FaseActiva obtenerCatalogoFaseActivaResult) {
        this.faseActiva = obtenerCatalogoFaseActivaResult;
    }

    public CerrarFaseModel getCerrarFaseResult() {
        return cerrarFaseResult;
    }

    public void setCerrarFaseResult(CerrarFaseModel cerrarFaseResult) {
        this.cerrarFaseResult = cerrarFaseResult;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(casosAbogadoList);
        dest.writeParcelable(guardaCatalogoCasoResult, flags);
        dest.writeTypedList(catalogoFaseList);
        //dest.writeParcelable(obtenerDatosCasoResult, flags);
    }

}
