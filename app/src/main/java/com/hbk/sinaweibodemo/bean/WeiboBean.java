package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created_at	string	微博创建时间
 id	int64	微博ID
 mid	int64	微博MID
 idstr	string	字符串型的微博ID
 text	string	微博信息内容
 source	string	微博来源
 favorited	boolean	是否已收藏，true：是，false：否
 truncated	boolean	是否被截断，true：是，false：否
 in_reply_to_status_id	string	（暂未支持）回复ID
 in_reply_to_user_id	string	（暂未支持）回复人UID
 in_reply_to_screen_name	string	（暂未支持）回复人昵称
 thumbnail_pic	string	缩略图片地址，没有时不返回此字段
 bmiddle_pic	string	中等尺寸图片地址，没有时不返回此字段
 original_pic	string	原始图片地址，没有时不返回此字段
 geo	object	地理信息字段 详细
 user	object	微博作者的用户信息字段 详细
 retweeted_status	object	被转发的原微博信息字段，当该微博为转发微博时返回 详细
 reposts_count	int	转发数
 comments_count	int	评论数
 attitudes_count	int	表态数
 mlevel	int	暂未支持
 visible	object	微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
 pic_urls	object	微博配图地址。多图时返回多图链接。无配图返回“[]”
 ad	object array	微博流内的推广微博ID
 */
public class WeiboBean implements Parcelable{

    @JsonProperty("created_at")
    private String createdAt;
    private long id;
    private String idstr;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    @JsonProperty("thumbnail_pic")
    private String thumbnailPic;
    @JsonProperty("bmiddle_pic")
    private String bmiddlePic;
    @JsonProperty("original_pic")
    private String originalPic;
    @JsonProperty("retweeted_status")
    private WeiboBean retweetedStatus;
    @JsonProperty("reposts_count")
    private int repostsCount;
    @JsonProperty("comments_count")
    private int commentsCount;
    @JsonProperty("attitudes_count")
    private int attitudesCount;
    @JsonProperty("pic_urls")
    private List<PicUrls> picUrls = new ArrayList<PicUrls>();
    private UserBean user;
    private GeoBean geo;


    public static class PicUrls implements Parcelable {

        public String thumbnail_pic;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(thumbnail_pic);
        }

        public static final Creator<PicUrls> CREATOR =
                new Creator<PicUrls>() {
                    public PicUrls createFromParcel(Parcel in) {
                        PicUrls picUrls = new PicUrls();
                        picUrls.thumbnail_pic = in.readString();
                        return picUrls;
                    }

                    public PicUrls[] newArray(int size) {
                        return new PicUrls[size];
                    }
                };

    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public String getBmiddlePic() {
        return bmiddlePic;
    }

    public void setBmiddlePic(String bmiddlePic) {
        this.bmiddlePic = bmiddlePic;
    }

    public String getOriginalPic() {
        return originalPic;
    }

    public void setOriginalPic(String originalPic) {
        this.originalPic = originalPic;
    }

    public WeiboBean getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(WeiboBean retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public List<PicUrls> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<PicUrls> picUrls) {
        this.picUrls = picUrls;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public GeoBean getGeo() {
        return geo;
    }

    public void setGeo(GeoBean geo) {
        this.geo = geo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeLong(id);
        dest.writeString(idstr);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeString(thumbnailPic);
        dest.writeString(bmiddlePic);
        dest.writeString(originalPic);
        dest.writeInt(repostsCount);
        dest.writeInt(commentsCount);
        dest.writeInt(attitudesCount);
        dest.writeByte(favorited ? (byte)1 : (byte)0);
        dest.writeByte(truncated ? (byte)1 : (byte)0);
        dest.writeParcelable(retweetedStatus, flags);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(geo, flags);
        dest.writeTypedList(picUrls);

    }

    public static final Creator<WeiboBean> CREATOR = new Creator<WeiboBean>() {
        @Override
        public WeiboBean createFromParcel(Parcel source) {
            WeiboBean bean = new WeiboBean();
            bean.createdAt = source.readString();
            bean.id = source.readLong();
            bean.idstr = source.readString();
            bean.text = source.readString();
            bean.source =source.readString();
            bean.thumbnailPic = source.readString();
            bean.bmiddlePic = source.readString();
            bean.originalPic = source.readString();
            bean.repostsCount = source.readInt();
            bean.attitudesCount = source.readInt();
            bean.commentsCount = source.readInt();
            bean.favorited = (source.readByte() == 1 ? true : false);
            bean.truncated = (source.readByte() == 1 ? true : false);
            bean.retweetedStatus = source.readParcelable(WeiboBean.class.getClassLoader());
            bean.user = source.readParcelable(UserBean.class.getClassLoader());
            bean.geo = source.readParcelable(GeoBean.class.getClassLoader());
            bean.picUrls = new ArrayList<PicUrls>();
            source.readTypedList(bean.picUrls, PicUrls.CREATOR);
            return bean;
        }

        @Override
        public WeiboBean[] newArray(int size) {
            return new WeiboBean[size];
        }
    };
}
