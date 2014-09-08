package com.hbk.sinaweibodemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.bean.WeiboBean;
import com.hbk.sinaweibodemo.util.TimeUtility;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by HongBinKim on 14/8/21.
 */
public class WeiboView extends RelativeLayout
{

    private ImageView avatarImageView;
    private ImageView vipImageView;
    private TextView nickTextView;
    private TextView dateTextView;
    private TextView sourceTextView;
    private ImageButton optionButton;
    private TextView contentTextView;
    private ImageView contentImageView;

    private RelativeLayout retweedtedView;
    private TextView retweetedContentTextView;
    private ImageView retweetedContentImageView;

    private LinearLayout bottomBar;
    private TextView repostCountTextView;
    private TextView commentCountTextView;
    private TextView likeCountTextView;

    private Context context;

    private String repostCount;
    private String commentCount;
    private String likeCount;

    private DisplayImageOptions avatarOption, contentOtpion;



    public WeiboView(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_weibo, this, true);
        this.context = context;
        initSubViews();
    }

    public WeiboView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_weibo, this, true);
        this.context = context;
        initSubViews();
    }

    private void initSubViews()
    {
        this.repostCount = context.getResources().getString(R.string.repost_count);
        this.commentCount = context.getResources().getString(R.string.comment_count);
        this.likeCount = context.getResources().getString(R.string.like_count);

        avatarImageView = ViewUtility.findViewById(this, R.id.avatar_imageView);
        vipImageView = ViewUtility.findViewById(this, R.id.verified_imageView);
        nickTextView = ViewUtility.findViewById(this, R.id.nick_text);
        dateTextView = ViewUtility.findViewById(this, R.id.date_text);
        sourceTextView = ViewUtility.findViewById(this, R.id.source_text);
        optionButton = ViewUtility.findViewById(this, R.id.option_button);
        contentTextView = ViewUtility.findViewById(this, R.id.content_text);
        contentImageView = ViewUtility.findViewById(this, R.id.content_imageView);

        retweedtedView = ViewUtility.findViewById(this, R.id.retweeted_status);
        retweetedContentTextView = ViewUtility.findViewById(this, R.id.retweeted_content_text);
        retweetedContentImageView = ViewUtility.findViewById(this, R.id.retweeted_content_imageView);

        bottomBar = ViewUtility.findViewById(this, R.id.status_bottom);
        repostCountTextView = ViewUtility.findViewById(this, R.id.repost_count_text);
        commentCountTextView = ViewUtility.findViewById(this, R.id.comment_count_text);
        likeCountTextView = ViewUtility.findViewById(this, R.id.like_count_text);

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

    public void layoutSubViews(WeiboBean bean)
    {
        ImageLoader.getInstance().displayImage(bean.getUser().getAvatarHd(), avatarImageView, avatarOption);

        if (bean.getUser().isVerified())
        {
            vipImageView.setVisibility(View.VISIBLE);
        } else
        {
            vipImageView.setVisibility(View.GONE);
        }
        
        nickTextView.setText(bean.getUser().getScreenName());

        String dateStr = TimeUtility.getListTime(bean.getCreatedAt());
        dateTextView.setText(dateStr);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getString(R.string.from))
                .append(" ").append(Utility.htmlRemoveTag(bean.getSource()));
        sourceTextView.setText(stringBuilder.toString());

        contentTextView.setText(bean.getText());

        boolean picEmpty = StringUtils.isEmpty(bean.getThumbnailPic());
        if (picEmpty)
        {
            contentImageView.setVisibility(View.GONE);
        } else
        {
            contentImageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(bean.getBmiddlePic(), contentImageView);
        }

        if (bean.getRetweetedStatus() == null)
        {
            retweedtedView.setVisibility(View.GONE);
        } else
        {
            retweedtedView.setVisibility(View.VISIBLE);
            retweetedContentTextView.setText(bean.getRetweetedStatus().getText());
            boolean isEmtpy = StringUtils.isEmpty(bean.getRetweetedStatus().getThumbnailPic());
            if (isEmtpy)
            {
                retweetedContentImageView.setVisibility(View.GONE);
            } else
            {
                retweetedContentImageView.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(bean.getRetweetedStatus().getBmiddlePic(), retweetedContentImageView);
            }
        }



        repostCount = bean.getRepostsCount() <= 0 ? repostCount : String.valueOf(bean.getRepostsCount());
        repostCountTextView.setText(repostCount);
        commentCount = bean.getCommentsCount() <= 0 ? commentCount : String.valueOf(bean.getCommentsCount());
        commentCountTextView.setText(commentCount);
        likeCount = bean.getAttitudesCount() <= 0 ? likeCount : String.valueOf(bean.getAttitudesCount());
        likeCountTextView.setText(likeCount);
        
    }



    public ImageView getAvatarImageView()
    {
        return avatarImageView;
    }

    public ImageView getVipImageView()
    {
        return vipImageView;
    }

    public TextView getNickTextView()
    {
        return nickTextView;
    }

    public TextView getDateTextView()
    {
        return dateTextView;
    }

    public TextView getSourceTextView()
    {
        return sourceTextView;
    }

    public ImageButton getOptionButton()
    {
        return optionButton;
    }

    public TextView getContentTextView()
    {
        return contentTextView;
    }

    public ImageView getContentImageView()
    {
        return contentImageView;
    }

    public RelativeLayout getRetweedtedView()
    {
        return retweedtedView;
    }

    public TextView getRetweetedContentTextView()
    {
        return retweetedContentTextView;
    }

    public ImageView getRetweetedContentImageView()
    {
        return retweetedContentImageView;
    }

    public LinearLayout getBottomBar()
    {
        return bottomBar;
    }

    public TextView getRepostCountTextView()
    {
        return repostCountTextView;
    }

    public TextView getCommentCountTextView()
    {
        return commentCountTextView;
    }

    public TextView getLikeCountTextView()
    {
        return likeCountTextView;
    }
}
