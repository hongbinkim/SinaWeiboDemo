package com.hbk.sinaweibodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PinnedSectionListView;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.bean.WeiboBean;
import com.hbk.sinaweibodemo.bean.WeiboList;
import com.hbk.sinaweibodemo.common.profile.ProfileSectionView;
import com.hbk.sinaweibodemo.util.Constants;
import com.hbk.sinaweibodemo.util.TimeUtility;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by HongBinKim on 14-8-27.
 */
public class ProfileWeiboAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter
{
    private WeiboList list;
    private Context context;
    private String repostCount;
    private String commentCount;
    private String likeCount;
    private DisplayImageOptions avatarOption, contentOtpion;

    private ProfileSectionView firstSectionView;
    private ProfileSectionView secondSectionView;

    public ProfileWeiboAdapter(Context context)
    {
        this.context = context;
        this.repostCount = context.getResources().getString(R.string.repost_count);
        this.commentCount = context.getResources().getString(R.string.comment_count);
        this.likeCount = context.getResources().getString(R.string.like_count);

        this.avatarOption = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar_default)
                .showImageForEmptyUri(R.drawable.avatar_default)
                .showImageOnFail(R.drawable.avatar_default)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .build();
        this.contentOtpion = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.empty_picture)
                .showImageForEmptyUri(R.drawable.empty_picture)
                .showImageOnFail(R.drawable.empty_picture)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .build();
    }

    @Override
    public boolean isItemViewTypePinned(int viewType)
    {
        return viewType == Constants.SECTION;
    }

    @Override
    public int getCount()
    {
        return list == null ? 1 : list.getWeiboList().size() + 1;
    }

    @Override
    public Object getItem(int position)
    {
        if (getItemViewType(position) == Constants.SECTION)
        {
            return null;
        }
        return list == null ? null : list.getWeiboList().get(position - 1);
    }

    @Override
    public long getItemId(int position)
    {
        if (getItemViewType(position) == Constants.SECTION)
        {
            return 0;
        }
        return list == null ? 0 : list.getWeiboList().get(position - 1).getId();
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position == 0 ? Constants.SECTION : Constants.NOT_SECTION;
    }

    static class ViewHolder
    {
        ImageView avatarImageView;
        ImageView verifiedImageView;
        TextView nickTextView;
        TextView dateTextView;
        TextView sourceTextView;
        TextView contentTextView;
        ImageView contentImageView;
        RelativeLayout retweetStatus;
        TextView retweetContentTextView;
        ImageView retweetImageView;
        TextView repostCountTextView;
        TextView commentCountTextView;
        TextView likeCountTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (getItemViewType(position) == Constants.SECTION)
        {
            if (convertView == null)
            {
                ProfileSectionView sectionView = new ProfileSectionView(context);

                if (firstSectionView == null)
                {
                    firstSectionView = sectionView;
                }
                else
                {
                    secondSectionView = sectionView;
                    secondSectionView.setSectionView(firstSectionView);

                }


                convertView = sectionView;

            }

            return convertView;

        }

        ViewHolder holder;
        if (convertView == null)
        {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_weibo, null);
            holder = new ViewHolder();
            holder.avatarImageView = ViewUtility.findViewById(convertView, R.id.avatar_imageView);
            holder.verifiedImageView = ViewUtility.findViewById(convertView, R.id.verified_imageView);
            holder.nickTextView = ViewUtility.findViewById(convertView, R.id.nick_text);
            holder.dateTextView = ViewUtility.findViewById(convertView, R.id.date_text);
            holder.sourceTextView = ViewUtility.findViewById(convertView, R.id.source_text);
            holder.contentTextView = ViewUtility.findViewById(convertView, R.id.content_text);
            holder.contentImageView = ViewUtility.findViewById(convertView, R.id.content_imageView);
            holder.retweetStatus = ViewUtility.findViewById(convertView, R.id.retweeted_status);
            holder.retweetContentTextView = ViewUtility.findViewById(convertView, R.id.retweeted_content_text);
            holder.retweetImageView = ViewUtility.findViewById(convertView, R.id.retweeted_content_imageView);
            holder.repostCountTextView = ViewUtility.findViewById(convertView, R.id.repost_count_text);
            holder.commentCountTextView = ViewUtility.findViewById(convertView, R.id.comment_count_text);
            holder.likeCountTextView = ViewUtility.findViewById(convertView, R.id.like_count_text);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        WeiboBean bean = list.getWeiboList().get(position - 1);

        setAvatarImage(bean.getUser().getAvatarHd(), holder.avatarImageView);

        if (bean.getUser().isVerified())
        {
            holder.verifiedImageView.setVisibility(View.VISIBLE);
        } else
        {
            holder.verifiedImageView.setVisibility(View.GONE);
        }
        holder.nickTextView.setText(bean.getUser().getScreenName());

        String dateStr = TimeUtility.getListTime(bean.getCreatedAt());
        holder.dateTextView.setText(dateStr);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getString(R.string.from))
                .append(" ").append(Utility.htmlRemoveTag(bean.getSource()));
        holder.sourceTextView.setText(stringBuilder.toString());

        holder.contentTextView.setText(bean.getText());

        boolean picEmpty = StringUtils.isEmpty(bean.getThumbnailPic());
        if (picEmpty)
        {
            holder.contentImageView.setVisibility(View.GONE);
        } else
        {
            holder.contentImageView.setVisibility(View.VISIBLE);
            setContentImage(bean.getBmiddlePic(), holder.contentImageView);
        }

        if (bean.getRetweetedStatus() == null)
        {
            holder.retweetStatus.setVisibility(View.GONE);
        } else
        {
            holder.retweetStatus.setVisibility(View.VISIBLE);
            holder.retweetContentTextView.setText(bean.getRetweetedStatus().getText());
            boolean isEmtpy = StringUtils.isEmpty(bean.getRetweetedStatus().getThumbnailPic());
            if (isEmtpy)
            {
                holder.retweetImageView.setVisibility(View.GONE);
            } else
            {
                holder.retweetImageView.setVisibility(View.VISIBLE);
                setContentImage(bean.getRetweetedStatus().getBmiddlePic(), holder.retweetImageView);
            }
        }

        repostCount = bean.getRepostsCount() <= 0 ? repostCount : String.valueOf(bean.getRepostsCount());
        holder.repostCountTextView.setText(repostCount);
        commentCount = bean.getCommentsCount() <= 0 ? commentCount : String.valueOf(bean.getCommentsCount());
        holder.commentCountTextView.setText(commentCount);
        likeCount = bean.getAttitudesCount() <= 0 ? likeCount : String.valueOf(bean.getAttitudesCount());
        holder.likeCountTextView.setText(likeCount);



        return convertView;
    }

    public void addRefreshList(WeiboList list)
    {
        this.list.getWeiboList().addAll(0, list.getWeiboList());
        this.list.setMaxId(list.getMaxId());
        this.list.setSinceId(list.getSinceId());
        this.list.setTotalNumber(list.getTotalNumber());
    }

    public void addLoadMoreList(WeiboList list)
    {
        if (list.getWeiboList().size() > 0)
        {
            list.getWeiboList().remove(0);
            this.list.getWeiboList().addAll(list.getWeiboList());
            this.list.setMaxId(list.getMaxId());
            this.list.setSinceId(list.getSinceId());
            this.list.setTotalNumber(list.getTotalNumber());
        }

    }

    private void setAvatarImage(String url, ImageView view)
    {
        ImageLoader.getInstance().displayImage(url, view, avatarOption);
    }

    private void setContentImage(String url, ImageView view)
    {
        ImageLoader.getInstance().displayImage(url, view, contentOtpion);
    }

    public WeiboList getList()
    {
        return list;
    }

    public void setList(WeiboList list)
    {

        this.list = list;
    }
}
