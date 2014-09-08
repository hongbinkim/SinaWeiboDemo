package com.hbk.sinaweibodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PinnedSectionListView;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.bean.CommentBean;
import com.hbk.sinaweibodemo.bean.CommentList;
import com.hbk.sinaweibodemo.ui.CommentSectionView;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Constants;
import com.hbk.sinaweibodemo.util.TimeUtility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14-8-25.
 */
public class WeiboContentAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter, View.OnClickListener
{
    private Context context;
    private CommentList coms;
    private DisplayImageOptions avatarOption;

    private int normalColor;
    private int hilightedColor;

    private int selectedId = 0;

    private boolean firstSection = false;

    private View sectionView;

    private List<Button> firstList = new ArrayList<Button>();

    private List<Button> secondList = new ArrayList<Button>();

    public WeiboContentAdapter(Context context)
    {
        this.context = context;
        this.avatarOption = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar_default)
                .showImageForEmptyUri(R.drawable.avatar_default)
                .showImageOnFail(R.drawable.avatar_default)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .build();

        normalColor = context.getResources().getColor(R.color.font_intro);
        hilightedColor = context.getResources().getColor(R.color.font_discover);
    }

    @Override
    public boolean isItemViewTypePinned(int viewType)
    {
        return viewType == Constants.SECTION;
    }

    @Override
    public int getCount()
    {
        return coms == null ? 0 : coms.getCommentList().size() + 0;
    }

    @Override
    public Object getItem(int position)
    {
//        if (position == 0)
//        {
//            return null;
//        }
        return coms == null ? null : coms.getCommentList().get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        CommentBean bean = (CommentBean)getItem(position);

        return bean.getIsSection();
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }



    static class ViewHolder
    {
        RelativeLayout commentView;
        ImageView avatarImageView;
        ImageView vipImageView;
        TextView nickTextView;
        TextView dateTextView;
        TextView contentTextView;

        RelativeLayout sectionView;
        Button repostButton;
        Button commentButton;
        Button likeButton;
        ImageView arrowImageView;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {



        ViewHolder holder;
        if (convertView == null)
        {


            convertView = LayoutInflater.from(context).inflate(R.layout.view_comment_cell, null);
            holder = new ViewHolder();

            holder.commentView = ViewUtility.findViewById(convertView, R.id.comment_content);
            holder.avatarImageView = ViewUtility.findViewById(convertView, R.id.avatar_image);
            holder.vipImageView = ViewUtility.findViewById(convertView, R.id.vip_image);
            holder.nickTextView = ViewUtility.findViewById(convertView, R.id.nick_text);
            holder.dateTextView = ViewUtility.findViewById(convertView, R.id.date_text);
            holder.contentTextView = ViewUtility.findViewById(convertView, R.id.content_text);

            holder.sectionView = ViewUtility.findViewById(convertView, R.id.comment_section);


            if (getItemViewType(position) == Constants.SECTION)
            {
                holder.repostButton = ViewUtility.findViewById(convertView, R.id.repost_button);
                holder.commentButton = ViewUtility.findViewById(convertView, R.id.comment_button);
                holder.likeButton = ViewUtility.findViewById(convertView, R.id.section_like_button);
                holder.arrowImageView = ViewUtility.findViewById(convertView, R.id.arrow_image);

                holder.repostButton.setTextColor(normalColor);
                holder.commentButton.setTextColor(hilightedColor);
                holder.likeButton.setTextColor(normalColor);


                firstList.add(holder.repostButton);
                firstList.add(holder.commentButton);
                firstList.add(holder.likeButton);

                holder.repostButton.setOnClickListener(this);
                holder.commentButton.setOnClickListener(this);
                holder.likeButton.setOnClickListener(this);

                if (sectionView == null && firstSection)
                {






                    sectionView = convertView;



                }



                firstSection = true;
            }

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        if (getItemViewType(position) == Constants.SECTION)
        {

            holder.commentView.setVisibility(View.GONE);
            holder.sectionView.setVisibility(View.VISIBLE);



            setSelecteButtonTextColor(selectedId);

        }
        else
        {
            holder.commentView.setVisibility(View.VISIBLE);
            holder.sectionView.setVisibility(View.GONE);

            CommentBean bean = coms.getCommentList().get(position);

            if (bean.getUser().isVerified())
            {
                holder.vipImageView.setVisibility(View.VISIBLE);
            } else
            {
                holder.vipImageView.setVisibility(View.GONE);
            }

            ImageLoader.getInstance().displayImage(bean.getUser().getAvatarHd(), holder.avatarImageView, avatarOption);

            holder.nickTextView.setText(bean.getUser().getScreenName());

            String dateStr = TimeUtility.getListTime(bean.getCreatedAt());
            holder.dateTextView.setText(dateStr);

            holder.contentTextView.setText(bean.getText());

        }


        return convertView;
    }

    @Override
    public void onClick(View v)
    {

        AppLogger.v("fist click -----");

        selectedId  = v.getId();

        setSelecteButtonTextColor(selectedId);

    }

    public CommentList getComs()
    {
        return coms;
    }

    public void setComs(CommentList coms)
    {
        this.coms = coms;
    }


    private void setSelecteButtonTextColor(int viewId)
    {





        for (Button btn : firstList)
        {
            if (btn.getId() == viewId)
            {
                btn.setTextColor(hilightedColor);

            }
            else
            {
                btn.setTextColor(normalColor);
            }
        }

        if (sectionView != null)
        {
            Button button= ViewUtility.findViewById(sectionView, viewId);
            button.setTextColor(hilightedColor);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AppLogger.v("second click -----");

            for (Button btn : secondList)
            {
                if (btn.getId() == v.getId())
                {
                    btn.setTextColor(hilightedColor);

                }
                else
                {
                    btn.setTextColor(normalColor);
                }
            }

            setSelecteButtonTextColor(v.getId());
        }
    };
}
