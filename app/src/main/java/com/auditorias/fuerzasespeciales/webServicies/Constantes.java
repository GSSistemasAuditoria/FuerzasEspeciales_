package com.auditorias.fuerzasespeciales.webServicies;

import android.os.Environment;

import java.io.File;

public class Constantes {

    //TODO: banderas de los metodos que se necesitan en el consumo de servicios
    public static final String METHOD_GET = "0";
    public static final String METHOD_POST = "1";

    //TODO: valores para permisos de camara, galería
    public static final int MIS_PERMISOS = 100;
    public static final int RESPUESTA_DE_CAMARA = 200;
    public static final int RESPUESTA_DE_GALERIA = 300;
    public static final int RESPUESTA_DE_ALMACENAMIENTO = 400;
    public static final String DIRECCION_DE_APP = "auditorias/";
    public static final String DIRECCION_FOTO = DIRECCION_DE_APP + "sifra";

    //TODO: posibles urls de los servicios
    //+´¿|tring BASE_URL_GETS="http://10.112.57.102/FuerzasEspecialesServicio.svc/";
    //public static String BASE_URL_GETS = "http://10.112.57.102/SifraServicio.svc/";
    //public static String BASE_URL_GETS = "http://10.112.57.102/SifraServicio/SifraServicio.svc/";
    //public static String BASE_URL_GETS = "http://10.112.56.102/ServicioSIFRA/SifraServicio.svc/";

    public static String BASE_URL_GETS = "http://10.112.57.102/ServicioSIFRA/SifraServicio.svc/";
    //public static String BASE_URL_GETS = "http://10.63.50.108/ServicioSIFRA/SifraServicio.svc/";
    //public static String BASE_URL_GETS = "https://www.auditoriags.com/SifraServicio/SifraServicio.svc/";

    //public static String BASE_URL_IMAGE = "http://10.112.56.102/ServicioSIFRA";
    public static String BASE_URL_IMAGE = "http://10.112.57.102/ServicioSIFRA";
    //public static String BASE_URL_IMAGE = "http://10.63.50.108/ServicioSIFRA";
    //public static String BASE_URL_IMAGE = "https://www.auditoriags.com/SifraServicio";

    public static String LOGIN_URL = "https://authns.desadsi.gs/nidp/oauth/nam/authz";

    //TODO: son todos los endpoins de los sevicios
    //TODO: Gets
    public static String obtenerCatalogoEtapaCaso = "ObtenerCatalogoEtapaCaso";
    public static String obtenerCatalogoUsuario = "ObtenerCatalogoUsuario";
    public static String obtenerCatalogoPerfil = "ObtenerCatalogoPerfil";
    public static String ObtenerCasosAbogado = "ObtenerCasosAbogado";
    public static String obtenerCatalogoUdN = "ObtenerCatalogoUdN";
    public static String obtenerCatalogoTipoFraude = "ObtenerCatalogoTipoFraude";
    public static String obtenerDatosEmpleadoDatosAuxiliares = "ObtenerDatosEmpleadoDatosAuxiliares";
    public static String obtenerCatalogoTipoEmpleado = "ObtenerCatalogoTipoEmpleado";
    public static String obtenerCatalogoFase = "ObtenerCatalogoFase";
    public static String obtenerDetalleCaso = "ObtenerDetalleCaso";
    public static String ObtenerDatosCaso = "ObtenerDatosCaso";
    public static String obtenerCatalogoStatusResponsableFase = "ObtenerCatalogoStatusResponsableFase";
    public static String obtenerCatalogoFaseActiva = "ObtenerCatalogoFaseActiva";
    public static String obtenerCasosTerminadosAbogado = "ObtenerCasosTerminadosAbogado";
    public static String obtenerConfiguraciones = "ObtenerConfiguraciones";
    public static String obtenerConfiguracion = "ObtenerConfiguracion";
    public static String obtenerDocumento = "ObtenerDocumento";
    public static String obtenerCatalogoStatusAutorizacion = "ObtenerCatalogoStatusAutorizacion";
    public static String obtenerDetalleUsuario = "ObtenerDetalleUsuario";
    public static String obtenerCatalogoIntegracionDoc = "ObtenerCatalogoIntegracionDoc";
    public static String obtenerTipoDenuncia = "ObtenerTipoDenuncia";
    public static String obtenerCatalogoStatusSentencia = "ObtenerCatalogoStatusSentencia";
    //TODO: Posts
    public static String loginoAuth = "LoginoAuth";
    //public static String guardaCatalogoUdN = "GuardaCatalogoUdN";
    public static String guardaCatalogoCaso = "GuardaCatalogoCaso";
    public static String iniciarFase = "IniciarFase";
    public static String cerrarFase = "CerrarFase";
    public static String calculaFechaCompromiso = "CalculaFechaCompromiso";
    public static String nuevaSolicitudCambioFecha = "NuevaSolicitudCambioFecha";
    public static String obtenerNotificacionesUsuario = "ObtenerNotificacionesUsuario";
    public static String exitoTrue = "true";
    public static String selecionar = "Selecionar";
    public static String signoIgual = "=";
    public static String signoInterrogacion = "?";
    public static String signoAnd = "&";
    public static String idTipoApp = "idTipoApp";
    public static String bearer = "Bearer ";
    public static String and = " and ";
    public static String filtro = "filtro";
    //TODO: Variables que contienen caracteres dentro de ellas
    public static String idAbogado = "idAbogado";
    public static String empleado = "empleado";
    public static String folio = "folio";
    public static String tipoApp = "tipoApp";
    public static String oAuthToken = "oAuthToken";
    public static String browserInfo = "browserInfo";
    public static String idCaso = "idCaso";
    public static String signoPesos = "$ ";
    public static String signoPorcentaje = " %";

    //TODO: variables de la base de datos
    public static int version = 1;
    public static String DB_name = "sifra_fs.db";
    public static String select = "SELECT ";
    public static String from = " FROM ";
    public static String parectesis_inicio = " (";
    public static String text_parectesis_final = " TEXT)";
    public static String string_text_coma = " TEXT,";
    public static String create_table_if_no_exists = " CREATE TABLE IF NOT EXISTS ";

    //TODO: nombre de las tablas que se contienen en la base de datos
    public static String tableDataUser = "tableDataUser";
    public static String tablePerfilUsuario = "tablePerfilUsuario";
    public static String tableEtapasCaso = "TableEtapasCaso";
    public static String tableTipoEmpleado = "TableTipoEmpleado";
    public static String tableStatusResponsableFase = "TableStatusResponsableFase";
    public static String tableTipoFraude = "TableTipoFraude";
    public static String tableUnidadNegocio = "TableUnidadNegocio";
    public static String tableFaseCaso = "TableFaseCaso";
    public static String tablaConfiguraciones = "TablaConfiguraciones";
    public static String tablaDocumentoPDF = "TablaDocumentoPDF";

    //TODO: variables de la tabla tableDataUser que se encuentra dentro de la base de datos
    public static String error = "Error";
    public static String exito = "Exito";
    public static String jwt = "JWT";
    public static String ceco = "Ceco";
    public static String contrasena = "Contrasena";
    public static String correo = "Correo";
    public static String fechaAlta = "FechaAlta";
    public static String fechaMod = "FechaMod";
    public static String idEmpleado = "idEmpleado";
    public static String idPerfil = "idPerfil";
    public static String idStatus = "idStatus";
    public static String idTipoEmpleado = "idTipoEmpleado";
    public static String idUsuario = "idUsuario";
    public static String nombre = "Nombre";
    public static String telefono = "Telefono";
    public static String estatus = "Estatus";
    public static String idRegion = "idRegion";
    public static String perfil = "Perfil";
    public static String region = "Region";
    public static String tipoEmpleado = "TipoEmpleado";
    public static String zona = "Zona";
    public static String descripcion = "Descripcion";
    public static String id = "id";
    public static String codigoColor = "CodigoColor";
    public static String codigoColorSecundario = "CodigoColorSecundario";
    public static String idColorSecundario = "IDColorSecundario";
    public static String icono = "Icono";
    public static String idColor = "idColor";
    public static String nombreCorto = "NombreCorto";
    public static String idFaseAnterior = "idFaseAnterior";
    public static String idFaseSiguiente = "idFaseSiguiente";
    public static String imagenUrl = "imagenUrl";
    public static String limiteMesesCompromiso = "LimiteMesesCompromiso";
    public static String clave = "Clave";
    public static String valor = "Valor";
    public static String idDocumento = "idDocumento";
    public static String tipoArchivo = "TipoArchivo";
    public static String tamanioArhivo = "TamanioArhivo";
    public static String stringArchivo = "StringArchivo";
    public static String path = "path";
    public static String fileMaxSize = "FileMaxSize";
    public static String formatDocuments = "FormatDocuments";
    public static String tipoAppMovil = "TipoAppMovil";
    public static String faseInicialCaso = "FaseInicialCaso";
    public static String formatImages = "FormatImages";
    public static String photoNumber = "PhotoNumber";
    public static String idIntegracionDoc = "IdIntegracionDoc";

    public File getStoragePath() {
        String removableStoragePath;
        File fileList[] = new File("/storage/").listFiles();
        for (File file : fileList) {
            if (!file.getAbsolutePath().equalsIgnoreCase(Environment.getExternalStorageDirectory().getAbsolutePath()) && file.isDirectory() && file.canRead()) {
                return file;
            }
        }
        return Environment.getExternalStorageDirectory();
    }

}


