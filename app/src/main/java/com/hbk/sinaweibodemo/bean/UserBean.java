package com.hbk.sinaweibodemo.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by HongBinKim on 14/8/12.
 * id	int64	用户UID
 idstr	string	字符串型的用户UID
 screen_name	string	用户昵称
 name	string	友好显示名称
 province	int	用户所在省级ID
 city	int	用户所在城市ID
 location	string	用户所在地
 description	string	用户个人描述
 url	string	用户博客地址
 profile_image_url	string	用户头像地址（中图），50×50像素
 profile_url	string	用户的微博统一URL地址
 domain	string	用户的个性化域名
 weihao	string	用户的微号
 gender	string	性别，m：男、f：女、n：未知
 followers_count	int	粉丝数
 friends_count	int	关注数
 statuses_count	int	微博数
 favourites_count	int	收藏数
 created_at	string	用户创建（注册）时间
 following	boolean	暂未支持
 allow_all_act_msg	boolean	是否允许所有人给我发私信，true：是，false：否
 geo_enabled	boolean	是否允许标识用户的地理位置，true：是，false：否
 verified	boolean	是否是微博认证用户，即加V用户，true：是，false：否
 verified_type	int	暂未支持
 remark	string	用户备注信息，只有在查询用户关系时才返回此字段
 status	object	用户的最近一条微博信息字段 详细
 allow_all_comment	boolean	是否允许所有人对我的微博进行评论，true：是，false：否
 avatar_large	string	用户头像地址（大图），180×180像素
 avatar_hd	string	用户头像地址（高清），高清头像原图
 verified_reason	string	认证原因
 follow_me	boolean	该用户是否关注当前登录用户，true：是，false：否
 online_status	int	用户的在线状态，0：不在线、1：在线
 bi_followers_count	int	用户的互粉数
 lang	string	用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
 */
public class UserBean implements Parcelable{
    private long id;
    private String idstr;
    @JsonProperty("screen_name")
    private String screenName;
    private String name;
    private int province;
    private int city;
    private String location;
    private String description;
    private String url;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    @JsonProperty("profile_url")
    private String profileUrl;
    private String domain;
    private String weihao;
    private String gender;
    @JsonProperty("followers_count")
    private int followersCount;
    @JsonProperty("friends_count")
    private int friendsCount;
    @JsonProperty("statuses_count")
    private int statusesCount;
    @JsonProperty("favourites_count")
    private int favouritesCount;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("allow_all_act_msg")
    private boolean allowAllActMsg;
    @JsonProperty("geo_enabled")
    private boolean geoEnabled;
    private boolean verified;
    private String remark;
    private WeiboBean status;
    @JsonProperty("allow_all_comment")
    private boolean allowAllComment;
    @JsonProperty("avatar_large")
    private String avatarLarge;
    @JsonProperty("avatar_hd")
    private String avatarHd;
    @JsonProperty("verified_reason")
    private String verifiedReason;
    @JsonProperty("follow_me")
    private boolean followMe;
    @JsonProperty("online_status")
    private int onlineStatus;
    @JsonProperty("biFollowersCount")
    private int bi_followers_count;

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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAllowAllActMsg() {
        return allowAllActMsg;
    }

    public void setAllowAllActMsg(boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    public boolean isGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public WeiboBean getStatus() {
        return status;
    }

    public void setStatus(WeiboBean status) {
        this.status = status;
    }

    public boolean isAllowAllComment() {
        return allowAllComment;
    }

    public void setAllowAllComment(boolean allowAllComment) {
        this.allowAllComment = allowAllComment;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public String getVerifiedReason() {
        return verifiedReason;
    }

    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    public boolean isFollowMe() {
        return followMe;
    }

    public void setFollowMe(boolean followMe) {
        this.followMe = followMe;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(idstr);
        dest.writeString(screenName);
        dest.writeString(name);
        dest.writeInt(province);
        dest.writeInt(city);
        dest.writeString(location);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(profileImageUrl);
        dest.writeString(profileUrl);
        dest.writeString(domain);
        dest.writeString(weihao);
        dest.writeString(gender);
        dest.writeInt(followersCount);
        dest.writeInt(friendsCount);
        dest.writeInt(statusesCount);
        dest.writeInt(favouritesCount);
        dest.writeString(createdAt);
        dest.writeString(remark);
        dest.writeParcelable(status, flags);
        dest.writeString(avatarLarge);
        dest.writeString(avatarHd);
        dest.writeString(verifiedReason);
        dest.writeInt(onlineStatus);
        dest.writeInt(bi_followers_count);
        dest.writeBooleanArray(new boolean[]{allowAllActMsg, geoEnabled, verified, allowAllComment, followMe});
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            UserBean bean = new UserBean();
            bean.id = source.readLong();
            bean.idstr = source.readString();
            bean.screenName = source.readString();
            bean.name = source.readString();
            bean.province = source.readInt();
            bean.city = source.readInt();
            bean.location = source.readString();
            bean.description = source.readString();
            bean.url = source.readString();
            bean.profileImageUrl = source.readString();
            bean.profileUrl = source.readString();
            bean.domain = source.readString();
            bean.weihao = source.readString();
            bean.gender = source.readString();
            bean.followersCount = source.readInt();
            bean.friendsCount = source.readInt();
            bean.statusesCount = source.readInt();
            bean.favouritesCount = source.readInt();
            bean.createdAt = source.readString();
            bean.remark = source.readString();
            bean.status = source.readParcelable(WeiboBean.class.getClassLoader());
            bean.avatarLarge = source.readString();
            bean.avatarHd = source.readString();
            bean.verifiedReason = source.readString();
            bean.onlineStatus = source.readInt();
            bean.bi_followers_count = source.readInt();

            boolean[] booleans = new  boolean[5];
            source.readBooleanArray(booleans);
            bean.allowAllActMsg = booleans[0];
            bean.geoEnabled = booleans[1];
            bean.verified = booleans[2];
            bean.allowAllComment = booleans[3];
            bean.followMe = booleans[4];

            return bean;
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
