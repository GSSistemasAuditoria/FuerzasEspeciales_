package com.auditorias.fuerzasespeciales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubFaseDelCasoModel implements Parcelable {

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

    protected SubFaseDelCasoModel(Parcel in) {
        error = in.readString();
        exito = in.readString();
        descripcion = in.readString();
        fechaMod = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            idStatus = null;
        } else {
            idStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            diasCalculoAutomatico = null;
        } else {
            diasCalculoAutomatico = in.readInt();
        }
        if (in.readByte() == 0) {
            diasProrroga = null;
        } else {
            diasProrroga = in.readInt();
        }
        byte tmpDocCambioFecha = in.readByte();
        docCambioFecha = tmpDocCambioFecha == 0 ? null : tmpDocCambioFecha == 1;
        byte tmpDocCierre = in.readByte();
        docCierre = tmpDocCierre == 0 ? null : tmpDocCierre == 1;
        byte tmpDocInicio = in.readByte();
        docInicio = tmpDocInicio == 0 ? null : tmpDocInicio == 1;
        if (in.readByte() == 0) {
            limiteMesesCompromiso = null;
        } else {
            limiteMesesCompromiso = in.readInt();
        }
        if (in.readByte() == 0) {
            orden = null;
        } else {
            orden = in.readInt();
        }
        if (in.readByte() == 0) {
            porcentajeFin = null;
        } else {
            porcentajeFin = in.readInt();
        }
        if (in.readByte() == 0) {
            porcentajeInicio = null;
        } else {
            porcentajeInicio = in.readInt();
        }
    }

    public static final Creator<SubFaseDelCasoModel> CREATOR = new Creator<SubFaseDelCasoModel>() {
        @Override
        public SubFaseDelCasoModel createFromParcel(Parcel in) {
            return new SubFaseDelCasoModel(in);
        }

        @Override
        public SubFaseDelCasoModel[] newArray(int size) {
            return new SubFaseDelCasoModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(exito);
        dest.writeString(descripcion);
        dest.writeString(fechaMod);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (idStatus == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idStatus);
        }
        if (diasCalculoAutomatico == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(diasCalculoAutomatico);
        }
        if (diasProrroga == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(diasProrroga);
        }
        dest.writeByte((byte) (docCambioFecha == null ? 0 : docCambioFecha ? 1 : 2));
        dest.writeByte((byte) (docCierre == null ? 0 : docCierre ? 1 : 2));
        dest.writeByte((byte) (docInicio == null ? 0 : docInicio ? 1 : 2));
        if (limiteMesesCompromiso == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(limiteMesesCompromiso);
        }
        if (orden == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orden);
        }
        if (porcentajeFin == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(porcentajeFin);
        }
        if (porcentajeInicio == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(porcentajeInicio);
        }
    }
}
