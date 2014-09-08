package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/11.
 */
public class WeiboList implements Parcelable{
    @JsonProperty("statuses")
    private List<WeiboBean> weiboList = new ArrayList<WeiboBean>();
    @JsonProperty("max_id")
    private long maxId;
    @JsonProperty("since_id")
    private long sinceId;
    @JsonProperty("total_number")
    private int totalNumber;

    public List<WeiboBean> getWeiboList() {
        return weiboList;
    }

    public void setWeiboList(List<WeiboBean> weiboList) {
        if (weiboList.size() > 0){
            this.maxId = weiboList.get(weiboList.size() - 1).getId();
            this.sinceId = weiboList.get(0).getId();
        }

        this.weiboList = weiboList;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(weiboList);
        dest.writeLong(maxId);
        dest.writeLong(sinceId);
        dest.writeInt(totalNumber);
    }

    public static final Creator<WeiboList> CREATOR = new Creator<WeiboList>() {
        @Override
        public WeiboList createFromParcel(Parcel source) {
            WeiboList list = new WeiboList();
            list.weiboList = new ArrayList<WeiboBean>();
            source.readTypedList(list.weiboList, WeiboBean.CREATOR);
            list.maxId = source.readLong();
            list.sinceId = source.readLong();
            list.totalNumber = source.readInt();
            return list;
        }

        @Override
        public WeiboList[] newArray(int size) {
            return new WeiboList[0];
        }
    };
}
