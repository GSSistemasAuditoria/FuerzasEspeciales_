package com.auditorias.fuerzasespeciales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuardaCatalogoCasoModel implements Parcelable {

    public static final Creator<GuardaCatalogoCasoModel> CREATOR = new Creator<GuardaCatalogoCasoModel>() {
        @Override
        public GuardaCatalogoCasoModel createFromParcel(Parcel in) {
            return new GuardaCatalogoCasoModel(in);
        }

        @Override
        public GuardaCatalogoCasoModel[] newArray(int size) {
            return new GuardaCatalogoCasoModel[size];
        }
    };

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
    private Integer idSubFase;
    @SerializedName("PorcentajeAvanceGeneral")
    @Expose
    private Integer porcentajeAvanceGeneral;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("CasoDescripcion")
    @Expose
    private String casoDescripcion;
    @SerializedName("CasoNombre")
    @Expose
    private String casoNombre;
    @SerializedName("ColorFase")
    @Expose
    private String colorFase;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("SubFase")
    @Expose
    private Object subFase;
    @SerializedName("TotalResponsables")
    @Expose
    private Integer totalResponsables;

    public GuardaCatalogoCasoModel() {
    }

    protected GuardaCatalogoCasoModel(Parcel in) {
        error = in.readString();
        exito = in.readString();
        byte tmpActivaoUltima = in.readByte();
        activaoUltima = tmpActivaoUltima == 0 ? null : tmpActivaoUltima == 1;
        fechaCierre = in.readString();
        fechaCompromiso = in.readString();
        fechaInicio = in.readString();
        fechaMod = in.readString();
        fechaRegistro = in.readString();
        if (in.readByte() == 0) {
            idCaso = null;
        } else {
            idCaso = in.readInt();
        }
        if (in.readByte() == 0) {
            idCasoFase = null;
        } else {
            idCasoFase = in.readInt();
        }
        if (in.readByte() == 0) {
            idEtapaFase = null;
        } else {
            idEtapaFase = in.readInt();
        }
        if (in.readByte() == 0) {
            idFase = null;
        } else {
            idFase = in.readInt();
        }
        if (in.readByte() == 0) {
            idStatus = null;
        } else {
            idStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            idStatusAutorizacion = null;
        } else {
            idStatusAutorizacion = in.readInt();
        }
        if (in.readByte() == 0) {
            idSubFase = null;
        } else {
            idSubFase = in.readInt();
        }
        if (in.readByte() == 0) {
            porcentajeAvanceGeneral = null;
        } else {
            porcentajeAvanceGeneral = in.readInt();
        }
        abogado = in.readString();
        casoDescripcion = in.readString();
        casoNombre = in.readString();
        colorFase = in.readString();
        fase = in.readString();
        folio = in.readString();
        if (in.readByte() == 0) {
            totalResponsables = null;
        } else {
            totalResponsables = in.readInt();
        }
    }

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

    public String getColorFase() {
        return colorFase;
    }

    public void setColorFase(String colorFase) {
        this.colorFase = colorFase;
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

    public Object getSubFase() {
        return subFase;
    }

    public void setSubFase(Object subFase) {
        this.subFase = subFase;
    }

    public Integer getTotalResponsables() {
        return totalResponsables;
    }

    public void setTotalResponsables(Integer totalResponsables) {
        this.totalResponsables = totalResponsables;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(exito);
        dest.writeByte((byte) (activaoUltima == null ? 0 : activaoUltima ? 1 : 2));
        dest.writeString(fechaCierre);
        dest.writeString(fechaCompromiso);
        dest.writeString(fechaInicio);
        dest.writeString(fechaMod);
        dest.writeString(fechaRegistro);
        if (idCaso == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCaso);
        }
        if (idCasoFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCasoFase);
        }
        if (idEtapaFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idEtapaFase);
        }
        if (idFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idFase);
        }
        if (idStatus == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idStatus);
        }
        if (idStatusAutorizacion == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idStatusAutorizacion);
        }
        if (idSubFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idSubFase);
        }
        if (porcentajeAvanceGeneral == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(porcentajeAvanceGeneral);
        }
        dest.writeString(abogado);
        dest.writeString(casoDescripcion);
        dest.writeString(casoNombre);
        dest.writeString(colorFase);
        dest.writeString(fase);
        dest.writeString(folio);
        if (totalResponsables == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalResponsables);
        }
    }
}
