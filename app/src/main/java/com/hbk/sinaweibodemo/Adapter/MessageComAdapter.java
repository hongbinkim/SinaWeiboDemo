package com.hbk.sinaweibodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.bean.CommentBean;
import com.hbk.sinaweibodemo.bean.CommentList;
import com.hbk.sinaweibodemo.util.TimeUtility;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by HongBinKim on 14/8/18.
 */
public class MessageComAdapter extends BaseAdapter
{
    private Context context;
    private CommentList coms;
    private DisplayImageOptions options;

    public MessageComAdapter(Context context)
    {
        this.context = context;
        this.options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar_default)
                .showImageForEmptyUri(R.drawable.avatar_default)
                .showImageOnFail(R.drawable.avatar_default)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .build();
    }

    @Override
    public int getCount()
    {

        return coms == null ? 0 : coms.getCommentList().size();
    }

    @Override
    public Object getItem(int position)
    {
        return coms == null ? null : coms.getCommentList().get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return coms == null ? 0 : coms.getCommentList().get(position).getId();
    }

    static class ViewHoler{
        ImageView avatarImageView;
        ImageView vipImageView;
        TextView nickTextView;
        Button replyButton;
        TextView dateTextView;
        TextView sourceTextView;
        TextView contentTextView;
        ImageView statusAvatarImageView;
        TextView statusNickTextView;
        TextView statusContentTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHoler holer;
        if (convertView == null){
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_message_comment, null);
            holer = new ViewHoler();
            holer.avatarImageView = ViewUtility.findViewById(convertView, R.id.avatar_imageView);
            holer.vipImageView = ViewUtility.findViewById(convertView, R.id.verified_imageView);
            holer.nickTextView = ViewUtility.findViewById(convertView, R.id.nick_text);
            holer.replyButton = ViewUtility.findViewById(convertView, R.id.reply_button);
            holer.dateTextView = ViewUtility.findViewById(convertView, R.id.date_text);
            holer.sourceTextView = ViewUtility.findViewById(convertView, R.id.source_text);
            holer.contentTextView = ViewUtility.findViewById(convertView, R.id.content_text);
            holer.statusAvatarImageView = ViewUtility.findViewById(convertView, R.id.status_avatar_image);
            holer.statusNickTextView = ViewUtility.findViewById(convertView, R.id.status_nick_text);
            holer.statusContentTextView = ViewUtility.findViewById(convertView, R.id.status_content_text);
            convertView.setTag(holer);
        }
        else
        {
            holer = (ViewHoler)convertView.getTag();
        }

        CommentBean bean = coms.getCommentList().get(position);

        setAvatarImage(bean.getUser().getAvatarHd(), holer.avatarImageView);

        int visibility = bean.getUser().isVerified() ? View.VISIBLE : View.GONE;
        holer.vipImageView.setVisibility(visibility);

        String dateStr = TimeUtility.getListTime(bean.getCreatedAt());
        holer.dateTextView.setText(dateStr);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getString(R.string.from))
                .append(" ").append(Utility.htmlRemoveTag(bean.getSource()));
        holer.sourceTextView.setText(stringBuilder.toString());

        holer.contentTextView.setText(bean.getText());

        holer.nickTextView.setText(bean.getUser().getScreenName());

        setAvatarImage(bean.getStatus().getUser().getAvatarHd(), holer.statusAvatarImageView);

        holer.statusNickTextView.setText("@" + bean.getStatus().getUser().getScreenName());

        holer.statusContentTextView.setText(bean.getStatus().getText());


        return convertView;
    }

    private void setAvatarImage(String url, ImageView view)
    {
        ImageLoader.getInstance().displayImage(url, view, options);
    }

    public CommentList getComs()
    {
        return coms;
    }

    public void setComs(CommentList coms)
    {

        this.coms = coms;
    }

    public void addLoadMoreList(CommentList list)
    {
        if (list.getCommentList().size() > 0)
        {
            list.getCommentList().remove(0);
            this.coms.getCommentList().addAll(list.getCommentList());
            this.coms.setMaxId(list.getMaxId());
            this.coms.setSinceId(list.getSinceId());
            this.coms.setTotalNumber(list.getTotalNumber());
        }

    }
}
