package com.auditorias.fuerzasespeciales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CasosModel implements Parcelable{

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Exito")
    @Expose
    private String exito;
    @SerializedName("Descripcion")
    @Expose
    private Object descripcion;
    @SerializedName("FechaMod")
    @Expose
    private Object fechaMod;
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
    private Object datosAgencia;
    @SerializedName("DatosDemanda")
    @Expose
    private Object datosDemanda;
    @SerializedName("FechaCompromiso")
    @Expose
    private String fechaCompromiso;
    @SerializedName("FechaRegistro")
    @Expose
    private Object fechaRegistro;
    @SerializedName("FechaReporte")
    @Expose
    private String fechaReporte;
    @SerializedName("Folio")
    @Expose
    private String folio;
    @SerializedName("IdAbogado")
    @Expose
    private Integer idAbogado;
    @SerializedName("IdEtapaCaso")
    @Expose
    private Integer idEtapaCaso;
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
    private Integer importe;
    @SerializedName("MontoRecuperado")
    @Expose
    private Integer montoRecuperado;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("TotalResponsables")
    @Expose
    private Integer totalResponsables;
    @SerializedName("Abogado")
    @Expose
    private String abogado;
    @SerializedName("AvanceFase")
    @Expose
    private Integer avanceFase;
    @SerializedName("AvanceSubFase")
    @Expose
    private Integer avanceSubFase;
    @SerializedName("ColorEtapaCaso")
    @Expose
    private String colorEtapaCaso;
    @SerializedName("EtapaCaso")
    @Expose
    private String etapaCaso;
    @SerializedName("Fase")
    @Expose
    private String fase;
    @SerializedName("IdCasoFase")
    @Expose
    private Integer idCasoFase;
    @SerializedName("IdFase")
    @Expose
    private Integer idFase;
    @SerializedName("IdSubFase")
    @Expose
    private Object idSubFase;
    @SerializedName("SubFase")
    @Expose
    private Object subFase;
    @SerializedName("UdN")
    @Expose
    private String udN;

    public CasosModel() {
    }

    protected CasosModel(Parcel in) {
        error = in.readString();
        exito = in.readString();
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
            avanceCaso = null;
        } else {
            avanceCaso = in.readInt();
        }
        fechaCompromiso = in.readString();
        fechaReporte = in.readString();
        folio = in.readString();
        if (in.readByte() == 0) {
            idAbogado = null;
        } else {
            idAbogado = in.readInt();
        }
        if (in.readByte() == 0) {
            idEtapaCaso = null;
        } else {
            idEtapaCaso = in.readInt();
        }
        if (in.readByte() == 0) {
            idStatusSentencia = null;
        } else {
            idStatusSentencia = in.readInt();
        }
        if (in.readByte() == 0) {
            idTipoFraude = null;
        } else {
            idTipoFraude = in.readInt();
        }
        if (in.readByte() == 0) {
            idUdN = null;
        } else {
            idUdN = in.readInt();
        }
        if (in.readByte() == 0) {
            importe = null;
        } else {
            importe = in.readInt();
        }
        if (in.readByte() == 0) {
            montoRecuperado = null;
        } else {
            montoRecuperado = in.readInt();
        }
        nombre = in.readString();
        if (in.readByte() == 0) {
            totalResponsables = null;
        } else {
            totalResponsables = in.readInt();
        }
        abogado = in.readString();
        if (in.readByte() == 0) {
            avanceFase = null;
        } else {
            avanceFase = in.readInt();
        }
        if (in.readByte() == 0) {
            avanceSubFase = null;
        } else {
            avanceSubFase = in.readInt();
        }
        colorEtapaCaso = in.readString();
        etapaCaso = in.readString();
        fase = in.readString();
        if (in.readByte() == 0) {
            idCasoFase = null;
        } else {
            idCasoFase = in.readInt();
        }
        if (in.readByte() == 0) {
            idFase = null;
        } else {
            idFase = in.readInt();
        }
        udN = in.readString();
    }

    public static final Creator<CasosModel> CREATOR = new Creator<CasosModel>() {
        @Override
        public CasosModel createFromParcel(Parcel in) {
            return new CasosModel(in);
        }

        @Override
        public CasosModel[] newArray(int size) {
            return new CasosModel[size];
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

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Object fechaMod) {
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

    public Object getDatosAgencia() {
        return datosAgencia;
    }

    public void setDatosAgencia(Object datosAgencia) {
        this.datosAgencia = datosAgencia;
    }

    public Object getDatosDemanda() {
        return datosDemanda;
    }

    public void setDatosDemanda(Object datosDemanda) {
        this.datosDemanda = datosDemanda;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public Object getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Object fechaRegistro) {
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

    public Integer getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(Integer idAbogado) {
        this.idAbogado = idAbogado;
    }

    public Integer getIdEtapaCaso() {
        return idEtapaCaso;
    }

    public void setIdEtapaCaso(Integer idEtapaCaso) {
        this.idEtapaCaso = idEtapaCaso;
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

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

    public Integer getMontoRecuperado() {
        return montoRecuperado;
    }

    public void setMontoRecuperado(Integer montoRecuperado) {
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

    public Integer getAvanceFase() {
        return avanceFase;
    }

    public void setAvanceFase(Integer avanceFase) {
        this.avanceFase = avanceFase;
    }

    public Integer getAvanceSubFase() {
        return avanceSubFase;
    }

    public void setAvanceSubFase(Integer avanceSubFase) {
        this.avanceSubFase = avanceSubFase;
    }

    public String getColorEtapaCaso() {
        return colorEtapaCaso;
    }

    public void setColorEtapaCaso(String colorEtapaCaso) {
        this.colorEtapaCaso = colorEtapaCaso;
    }

    public String getEtapaCaso() {
        return etapaCaso;
    }

    public void setEtapaCaso(String etapaCaso) {
        this.etapaCaso = etapaCaso;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Integer getIdCasoFase() {
        return idCasoFase;
    }

    public void setIdCasoFase(Integer idCasoFase) {
        this.idCasoFase = idCasoFase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Object getIdSubFase() {
        return idSubFase;
    }

    public void setIdSubFase(Object idSubFase) {
        this.idSubFase = idSubFase;
    }

    public Object getSubFase() {
        return subFase;
    }

    public void setSubFase(Object subFase) {
        this.subFase = subFase;
    }

    public String getUdN() {
        return udN;
    }

    public void setUdN(String udN) {
        this.udN = udN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(exito);
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
        if (avanceCaso == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avanceCaso);
        }
        dest.writeString(fechaCompromiso);
        dest.writeString(fechaReporte);
        dest.writeString(folio);
        if (idAbogado == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idAbogado);
        }
        if (idEtapaCaso == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idEtapaCaso);
        }
        if (idStatusSentencia == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idStatusSentencia);
        }
        if (idTipoFraude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idTipoFraude);
        }
        if (idUdN == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idUdN);
        }
        if (importe == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(importe);
        }
        if (montoRecuperado == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(montoRecuperado);
        }
        dest.writeString(nombre);
        if (totalResponsables == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalResponsables);
        }
        dest.writeString(abogado);
        if (avanceFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avanceFase);
        }
        if (avanceSubFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avanceSubFase);
        }
        dest.writeString(colorEtapaCaso);
        dest.writeString(etapaCaso);
        dest.writeString(fase);
        if (idCasoFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCasoFase);
        }
        if (idFase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idFase);
        }
        dest.writeString(udN);
    }
}
