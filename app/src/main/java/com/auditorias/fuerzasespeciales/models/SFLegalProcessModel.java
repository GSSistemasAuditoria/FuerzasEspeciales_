package com.auditorias.fuerzasespeciales.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SFLegalProcessModel implements Parcelable {

    private int id;
    private String title;
    private String status;
    private String colorStatus;

    public SFLegalProcessModel() {

    }

    public SFLegalProcessModel(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }


    protected SFLegalProcessModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        status = in.readString();
        colorStatus = in.readString();
    }

    public static final Creator<SFLegalProcessModel> CREATOR = new Creator<SFLegalProcessModel>() {
        @Override
        public SFLegalProcessModel createFromParcel(Parcel in) {
            return new SFLegalProcessModel(in);
        }

        @Override
        public SFLegalProcessModel[] newArray(int size) {
            return new SFLegalProcessModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(String colorStatus) {
        this.colorStatus = colorStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(colorStatus);
    }
}
