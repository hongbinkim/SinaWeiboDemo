package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/18.
 */
public class CommentList implements Parcelable
{
    @JsonProperty("comments")
    private List<CommentBean> commentList = new ArrayList<CommentBean>();
    @JsonProperty("max_id")
    private long maxId;
    @JsonProperty("since_id")
    private long sinceId;
    @JsonProperty("total_number")
    private int totalNumber;

    public List<CommentBean> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<CommentBean> commentList)
    {
        if (commentList.size() > 0)
        {
            this.maxId = commentList.get(commentList.size() - 1).getId();
            this.sinceId = commentList.get(0).getId();
        }
        this.commentList = commentList;
    }

    public long getMaxId()
    {
        return maxId;
    }

    public void setMaxId(long maxId)
    {
        this.maxId = maxId;
    }

    public long getSinceId()
    {
        return sinceId;
    }

    public void setSinceId(long sinceId)
    {
        this.sinceId = sinceId;
    }

    public int getTotalNumber()
    {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber)
    {
        this.totalNumber = totalNumber;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedList(commentList);
        dest.writeLong(maxId);
        dest.writeLong(sinceId);
        dest.writeInt(totalNumber);
    }

    public static final Parcelable.Creator<CommentList> CREATOR = new Parcelable.Creator<CommentList>()
    {
        @Override
        public CommentList createFromParcel(Parcel source)
        {
            CommentList list = new CommentList();
            list.commentList = new ArrayList<CommentBean>();
            source.readTypedList(list.commentList, CommentBean.CREATOR);
            list.maxId = source.readLong();
            list.sinceId = source.readLong();
            list.totalNumber = source.readInt();
            return list;
        }

        @Override
        public CommentList[] newArray(int size)
        {
            return new CommentList[size];
        }
    };
}
