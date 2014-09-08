package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/12.
 */
public class GeoBean implements Parcelable{
    private List<String> coordinates = new ArrayList<String>();
    private String type;

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(coordinates);
        dest.writeString(type);
    }

    public static final Creator<GeoBean> CREATOR = new Creator<GeoBean>() {
        @Override
        public GeoBean createFromParcel(Parcel source) {
            GeoBean bean = new GeoBean();
            bean.coordinates = new ArrayList<String>();

            source.readStringList(bean.coordinates);

            bean.type = source.readString();
            return bean;
        }

        @Override
        public GeoBean[] newArray(int size) {
            return new GeoBean[size];
        }
    };
}
