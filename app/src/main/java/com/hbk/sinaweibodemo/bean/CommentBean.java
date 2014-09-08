package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hbk.sinaweibodemo.util.Constants;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by HongBinKim on 14/8/18.
 * created_at	string	评论创建时间
 id	int64	评论的ID
 text	string	评论的内容
 source	string	评论的来源
 user	object	评论作者的用户信息字段 详细
 mid	string	评论的MID
 idstr	string	字符串型的评论ID
 status	object	评论的微博信息字段 详细
 reply_comment	object	评论来源评论，当本评论属于对另一评论的回复时返回此字段
 */
public class CommentBean implements Parcelable
{
    private long id;
    @JsonProperty("created_at")
    private String createdAt;
    private String text;
    private String source;
    private UserBean user;
    private String mid;
    private String idstr;
    private WeiboBean status;
    private int isSection = Constants.NOT_SECTION;


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public UserBean getUser()
    {
        return user;
    }

    public void setUser(UserBean user)
    {
        this.user = user;
    }

    public String getMid()
    {
        return mid;
    }

    public void setMid(String mid)
    {
        this.mid = mid;
    }

    public String getIdstr()
    {
        return idstr;
    }

    public void setIdstr(String idstr)
    {
        this.idstr = idstr;
    }

    public WeiboBean getStatus()
    {
        return status;
    }

    public void setStatus(WeiboBean status)
    {
        this.status = status;
    }

    public int getIsSection()
    {
        return isSection;
    }

    public void setIsSection(int isSection)
    {
        this.isSection = isSection;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(id);
        dest.writeString(createdAt);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeString(mid);
        dest.writeString(idstr);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(status, flags);
    }

    public static final Creator<CommentBean> CREATOR = new Creator<CommentBean>()
    {
        @Override
        public CommentBean createFromParcel(Parcel source)
        {
            CommentBean bean = new CommentBean();
            bean.id = source.readLong();
            bean.createdAt = source.readString();
            bean.text = source.readString();
            bean.source = source.readString();
            bean.mid = source.readString();
            bean.idstr = source.readString();
            bean.user = source.readParcelable(UserBean.class.getClassLoader());
            bean.status = source.readParcelable(WeiboBean.class.getClassLoader());
            return bean;
        }

        @Override
        public CommentBean[] newArray(int size)
        {
            return new CommentBean[size];
        }
    };


}
