package com.auditorias.fuerzasespeciales.utils;

import android.os.Environment;


public class ScanConstants {

    public static final String DATABASE_NAME = "AuditoriasDB";
    public static final int STORAGE_PERMISSION = 201;
    public static final int STORAGE_PERMISSION2 = 203;
    public static final int CAMERA_PERMISSION = 202;
    private static final int REQUEST_CODE = 99;
    public final static int PICKFILE_REQUEST_CODE = 1;
    public final static int START_CAMERA_REQUEST_CODE = 2;
    public final static String OPEN_INTENT_PREFERENCE = "selectContent";
    public final static String IMAGE_BASE_PATH_EXTRA = "ImageBasePath";
    public final static int OPEN_CAMERA = 4;
    public final static int OPEN_MEDIA = 5;
    public final static String SCANNED_RESULT = "scannedResult";
    public final static String REGEX_NOMBRE = "^[a-zA-Z0-9\\s]+$";

    public final static int TIMEOUT_NORMAL = 30000;
    public final static int TIMEOUT_LONG = 30000;
    public final static int TIMEOUT_SHORT = 30000;

    public final static String PHOTO_PATH = "/Auditoria GS/user/userPhoto.jpg";
    public final static String PATH = Environment.getExternalStorageDirectory().getPath() + "/Auditoria GS/";
    public final static String AUDITORIA_GS = "/Auditoria GS/";
    public final static String TEMP_PHOTOS = "tempPhotos/";
    public final static String SCAN_PICTURE = "/Auditoria GS/<directorio>/ImagenesEscaneadas";
    public final static String SCAN_FILE = "/Auditoria GS/<directorio>/DocumentosPDF";

    public final static String IMAGE_INCIDENCIA_PATH =  "/Auditoria GS/TempImgPP/incidencia.jpg";
    public final static String IMAGE_FUERA_POLITICA_PATH = "/Auditoria GS/TempImgPP/fueraPolitica.jpg";
    public final static String IMAGE_EVIDENCIA_PP_PATH = "/Auditoria GS/TempImgPP/evidenciaPP.jpg";
    public final static String IMAGE_EVIDENCIA_TRANSITO_PP_PATH = "/Auditoria GS/TempImgPP/evidenciaTransitoPP.jpg";//*/

    public final static String SELECTED_BITMAP = "selectedBitmap";

    //produccion llave maestra
    public final static String URL_LOGOUT = "https://auth.socio.gs/nidp/jsp/logoutSuccess_latest.jsp";
    public static final String URL_USER_INFO = "https://auth.socio.gs/nidp/oauth/nam/userinfo";//*/

    //http://www.auditoriags.com/ServicioAda_Pruebas/AdaDataService.svc/
    //public final static String ADA_PUBLIC = "4KyixEVLzWvCxpff3GzyEof74G/zc++ZAVOLkXqCMbQ3JyJI1bvLRu587d9BbK+H63I9HThaE9EniNwaI7Z4HErJBAmUMqhM1H1lVBNQ+HM=";
    //http://10.89.149.85/ServicioADA/AdaDataService.svc/;
    //public final static String ADA_PUBLIC = "ofjcUcZ+Cnrw2m6EQ/yxhj8aMkAkybq+XES3tvJiWejMtPtt42UKdTXn/KHQcew+JCjwsBkeLvx75cKtan7GhQ==";
    //https://www.auditoriags.com/ServicioADA/AdaDataService.svc/
    // public final static String ADA_PUBLIC = "fcqVM4Fhe5obVMeXHmZlJwCCAHM9zmKsdlxNctt/RcQ5GO+ZgIzAvoEriu/dgztoOEtWBGgIUbS3B0q+19fDOg==";
    //https://www.auditoriags.com/ServicioADA_BETA/AdaDataService.svc/
    public final static String ADA_PUBLIC = "fcqVM4Fhe5obVMeXHmZlJwCCAHM9zmKsdlxNctt/RcQTkoO/Gp9qOm8yqXjBsIjucqBbdMw6XZQMmz3b07+jhQ==";

    //"https://www.auditoriags.com/ServicioADA_D/AdaDataService.svc/"
    //public final static String ADA_PUBLIC = "fcqVM4Fhe5obVMeXHmZlJwCCAHM9zmKsdlxNctt/RcSplZfdTo+1Z206UV3ntcNfqaTxb6nd/ym3+Cjq67bn3Q==";
    //"https://www.auditoriags.com/ServicioADA_Pruebas/AdaDataService.svc/";
    //public final static String ADA_PUBLIC =  "fcqVM4Fhe5obVMeXHmZlJwCCAHM9zmKsdlxNctt/RcTkHWTGIgGN8d6TBKQK0MNnr203ltLe+kb8GpNgKpJh2vI+Zlzr89TL3n5z3jtde6g=";

    public final static String ADA_TEST = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878a9c01308a20044af";
    public final static String ADA_PRODUCTION = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf636b70b55bc61bfc1229810aa31356b70506ddf2f27174fa7fbff5bb776d2a54a9c01308a20044af";

    public final static String URL_LOGiN = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c33287894ac865bcaa87ce6a9c01308a20044af";
    public final static String URL_AUDITORIAS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878f3ebaab7fe24689aacb2da300111325e6fa9bedfb025b7c5fd1c859ef9e013ffa9c01308a20044af";
    public final static String URL_HALLAZGOS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c33287844e0f53ba882a61c8ac0bacda38260c743abc43fda2a404f655705b01be29719a9c01308a20044af";
    public final static String URL_DOCUMENTOS_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328785fbe5c4c162dc674afab753093dfa830e22067d6bec19150a9c01308a20044af";
    public final static String URL_DOCUMENTOS_AUDITORIA = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328785fbe5c4c162dc67476bd82d19fdaad2b6544cf27cd2ebe81a9c01308a20044af";

    public final static String URL_CATALOGO_RUBRO_CHECKLIST_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3937288d912a921b09e91704a7400158129e208899319d8885c889bc2687edd8f10c0b149af8884d7b20404525450f641a9c01308a20044af";
    public final static String URL_CATALOGO_PUESTO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3937288d912a921b09e91704a740015818c084c4db7750b948570105255c924372daf2691afd8e688b20404525450f641a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_TIPO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e30093bc746112fcad42b23741ff3baf0ddd08b52ee737d437a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_AFECTACION_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3d611a819358a89e0a336ac90195ec3470da3ba98397c476bbcc0f50d35bbf4ada9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_RUBRO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3d611a819358a89e0dbf865c8d80e0919773a74e8fbaf48f3a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_DANO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3d611a819358a89e04eff10f26e7360d5c0eba2240fa208f0a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_PRODUCTO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3d611a819358a89e06c564af2a87ceb5fccec3f0df6d7f24fa9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_EMPLEADOS_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3868115f633d69759520e84ad7381516dd3527b076a5828fba9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_MONEDA_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3545da3beabf74a65171794da8e220c04a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_RIESGO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3937288d912a921b09e91704a74001581c762e7ceb8770d46b52a34506a343798a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_TIPO_DOCUMENTO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3d611a819358a89e04eff10f26e7360d57290449c46588430311fc53148923a63a9c01308a20044af";
    public final static String URL_CREATE_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c33287880b7b6ade6a7849bbf917e74ed97c84709e6700c28a0c0133a045130dc520227a9c01308a20044af";
    public final static String URL_OBTENER_DOCUMENTO_HALLAZGO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656e239593551084a30cd354462bfb4d463c96655440d696d718a9c01308a20044af";
    public final static String URL_DESCARGAR_CATALOGO_TIPO_DOCUMENTO_AUDITORIA = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e30093bc746112fcad7290449c46588430311fc53148923a63a9c01308a20044af";

    public final static String URL_CHECKLIST_DATOS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656eea972e21a5d19bd8e2be1b8a534f3a3b96655440d696d718a9c01308a20044af";
    public final static String URL_CHECKLIST_HALLAZGOS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c33287826cb202495525cf81775ae223ad857c53ceb774b26f7c09657821dc35d99c68ca9c01308a20044af";
    public final static String URL_CHECKLIST_ENCABEZADO = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e33b83874ce90aeb558c9795be9bdf01a3e2be1b8a534f3a3b96655440d696d718a9c01308a20044af";
    public final static String URL_CHECKLIST_FOOTER = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878478a4c932767f6e3946c4accdeec4e73813ab2a34ad9657cdf3d3ab5770cb8b1a9c01308a20044af";
    public final static String URL_CHECKLIST_ENCUESTADOS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656e18019f64c76a5259059d462ea2c4f3b9e2be1b8a534f3a3b96655440d696d718a9c01308a20044af";
    public final static String URL_CHECKLIST_SECCIONES = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656e473144a25dff284e5c889bc2687edd8f0a6ce8ddba495e3da9c01308a20044af";
    public final static String URL_CHECKLIST_PREGUNTAS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656ebf11c12a00beed7a65bead80195dd5f506ad923e727262c81ae132a3295090f8a9c01308a20044af";
    public final static String URL_CHECKLIST_RESPUESTAS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656ebf11c12a00beed7ae7d722b16b19757d4626942994b7b63cee684657fe1c74152b41639770fa4c3aa9c01308a20044af";
    public final static String URL_CHECKLIST_RESPUESTAS_PONDERACIONES = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878bc442dbd85a500647c3a0ca28d84a1a6e7c6d68d54c16f92655705b01be29719a9c01308a20044af";
    public final static String URL_CHECKLIST_SECCIONES_PONDERACIONES = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c332878bc442dbd85a50064e3c33fb190ea9d2e2eed9f6ebb3a02735d5bf6ea0e6857fca9c01308a20044af";
    public final static String URL_CHECKLIST = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c33287826cb202495525cf8b14bf4b37943c9fafbef775ef3d902b51cdfb6eea78697a443abc43fda2a404f38db61b4d925d9c7a9c01308a20044af";
    public final static String URL_CHECKLIST_REGLAS = "3ec40a36abc4cba911b8e8d47685d3ac5516b5d9461dc5cf6358fe0750ae41f6aef6ce1dd6b72679c072147397c3ee05fba70e9b1c3328787d050f17186b656ede69d87f98209168d8f614a4f57dd5005c889bc2687edd8f541af61540ceb3b9aa337f1e96f2cae5a9c01308a20044af";

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String HOUR_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public final static String DIA_TAG = "dia";
    public final static String FOLIO_TAG = "folio";
    public final static String HALLAZGOS_TAG = "hallazgos";
    public final static String HALLAZGOS_NOMBRE_TAG = "hallazgosNombre";
    public final static String HALLAZGOS_CUANTIFICABLE_TAG = "hallazgosCuantificable";
    public final static String REAL_DAY_TAG = "real_day";
    public final static String DAY_TAG = "day";
    public final static String MONTH_TAG = "month";
    public final static String YEAR_TAG = "year";
    public final static String DESCARGAR_TAG = "descargar";
    public final static String NO_DESCARGAR_TAG = "no descargar";
    public final static String FECHA_INICIO_TAG = "fechaInicio";
    public final static String FECHA_FIN_TAG = "fechaFin";
    public final static String FECHA_TAG = "fecha";
    public final static String SEMANAS_TAG = "noSemanas";
    public final static String PATH_TAG = "path";
    public final static String PATH_DOCUMENTS_TAG = "pathDocuments";
    public final static String ID_AUDITOR_TAG = "idAuditor";

    public final static String OSWALD_REGULAR_FONT = "fonts/Oswald-Regular.ttf";
    public final static String OSWALD_BOLD_FONT = "fonts/Oswald-Bold.ttf";

    public final static String NO_HALLAZGOS_TEXT = "No hay hallazgos para esta auditoría";

    /*Etapas de la auditoría*/
    public final static String POR_INICIAR_STATUS = "Por iniciar";
    public final static String TERMINADA_STATUS = "Terminada";
    public final static String EN_PROCESO_STATUS = "En proceso";
    public final static String CUANTIFICABLE_STATUS = "Cuantificable";
    public final static String POR_CREAR_STATUS = "Por Crear";
    public final static String CANCELADA_STATUS = "Cancelada";
    public final static String PENDIENTE_STATUS = "pendiente";
    public final static String AGREGADO_STATUS = "agregado";

    public final static String SHARED_PREFERENCES_NAME = "AuditoriaGS";
    public final static String SHARED_PREFERENCES_USER_PHOTO_PATH = "userPhotoPath";
    public final static String SHARED_PREFERENCES_ID_EMPLEADO = "idEmpleado";
    public final static String SHARED_PREFERENCES_NOMBRE_EMPLEADO = "nombreEmpleado";
    public final static String SHARED_PREFERENCES_DIA_ACTUAL = "diaActual";
    public final static String SHARED_PREFERENCES_LOGIN_TAG = "login";

    public final static String OPTION_MENU_DESCARGAR_AUDITORIAS = "Actualizar auditorías";
    public final static String OPTION_MENU_SUBIR_DOCUMENTOS = "Sincronizar documentos";
    public final static String OPTION_MENU_ACTUALIZAR_CATALOGOS = "Actualizar catálogos";
    public final static String OPTION_MENU_CERRAR_SESION = "Cerrar sesión";
    public final static String OPTION_MENU_NUEVA_INSTALACION = "Nueva instalación";

    public final static String DIALOG_TITLE_EXIT = "Cerrar sesión";
    public final static String DIALOG_MESSAGE_EXIT = "Estás seguro de querer salir de la aplicación?";
    public final static String DIALOG_TITLE_BORRAR = "Borrar documento";
    public final static String DIALOG_MESSAGE_BORRAR = "Estás seguro de querer borrar este documento?";
    public final static String DIALOG_TITLE_CREAR = "Crear hallazgo";
    public final static String DIALOG_MESSAGE_CREAR = "Desea crear un hallazgo?";

    public final static String QUERY_TAG_AUDITORIA_OPTION = "auditoria";

    public final static String JSON_RESPONSE_EXITO = "Auditorias cargadas exitosamente";

    public final static String REQUEST_METHOD_HALLAZGOS = "ObtenerHallazgosAuditoriaResult";
    public final static String REQUEST_METHOD_AUDITORIAS = "ObtenerMisAuditoriasPorFechaResult";

    public static final String ID_PAIS_MEX = "MEX";

    //Constantes inventario
    public final static String ID_ADUITORIA = "idAuditoria";
    public final static String ID_GRUPO = "idGrupo";
    public final static String RESULT = "result";
    public final static String TIPO_HALLAZGO_INVENTARIO = "INVENTARIO";
    public final static String ESTADO_CALCULAR_DIFERENCIAS = "EstadoDiferencias";
    public final static String INSERTAR_RELACION_FAMILIAS_INVENTARIO = "insertarRelacionFamiliaAuditoria";
    public final static String INSERTAR_RELACION_FAMILIAS_INVENTARIO_RESULT = "insertarRelacionFamiliaAuditoriaResult";
    public final static String OBTENER_INVENTARIO_TIENDA = "obtenerInventarioTiendaPorLinea";
    public final static String INVENTARIO_LINEA = "inventarioLinea";
    public final static String INSERTAR_INVENTARIO_LINEA = "insertarInventarioPorLinea";
    public final static String INSERTAR_INVENTARIO_LINEA_RESULT = "insertarInventarioPorLineaResult";
    public final static int EVIDENCIA_PAGO_EFECTIVO = 2;
    public final static int EVIDENCIA_DIFERENCIA = 1;
    public final static int LINEA_PROCESO = 1;
    public final static int LINEA_CERRADA = 2;
    public final static String EVIDENCIA_DIFERENCIAS = "evidenciaDiferencias";
    public final static String EVIDENCIA_PAGARE = "evidenciaPagare";
    public final static String EVIDENCIA_EFECTIVO = "evidenciaEfectivo";
    public final static String EXTENSION_EVIDENCIA = ".jpg";
    public final static String CERRAR_INVENTARIO = "CerrarInventario";
    public final static String CERRAR_INVENTARIO_RESULT = "CerrarInventarioResult";
    public final static String OBTENER_CEDULAS_INVENTARIO_LINEA = "ObtenerCedulasInventarioPorLinea";
    public final static String OBTENER_CEDULAS_INVENTARIO_LINEA_RESULT = "ObtenerCedulasInventarioPorLineaResult";
    public final static String IMPACTAR_CEDULAS_INVENTARIO = "ImpactarCedulaInventario";
    public final static String NUMERO_EMPLEADO = "numeroEmpleado";
    public final static String OBTENER_INVENTARIO_TIENDA_POR_LINEA = "obtenerInventarioTiendaPorLinea";
    public final static String OBTENER_INVENTARIO_TIENDA_POR_LINEA_RESULT = "obtenerInventarioTiendaPorLineaResult";
    public final static String MONTO_PAGARE = "_MONTOPAGARE";

    //Constantes papeles de trabajo configurados
    public final static String FOLIO_DOCU_ARQUEO_CAJA = "AGU_A     ";
    public final static String FOLIO_DOCU_ARQUEO_CAJEROS = "AGU-173   ";
    //public final static String FOLIO_DOCU_ARQUEO_CAJEROS = "AGU-155   ";
    public final static String FOLIO_DOCU_ARQUEO_TARJETAS = "AGU-R     ";
    public final static String FOLIO_DOCU_INVENTARIO = "AGU-154   ";
    public final static String FOLIO_DOCU_INFORME = "H-1       ";
}
