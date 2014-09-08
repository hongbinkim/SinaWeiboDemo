package com.hbk.sinaweibodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/20.
 */
public class MeAdapter extends BaseAdapter
{
    private Context context;
    private List<Integer> imageList = new ArrayList<Integer>();
    private List<Integer> titleList = new ArrayList<Integer>();

    public MeAdapter(Context context)
    {
        this.context = context;
        imageList.add(R.drawable.me_new_friend);
        imageList.add(R.drawable.me_album);
        imageList.add(R.drawable.me_favorite);
        imageList.add(R.drawable.me_like);
        imageList.add(R.drawable.me_weibo_pay);
        imageList.add(R.drawable.me_service);
        imageList.add(R.drawable.me_card);
        imageList.add(R.drawable.me_box);

        titleList.add(R.string.new_friends);
        titleList.add(R.string.album);
        titleList.add(R.string.favorite);
        titleList.add(R.string.like);
        titleList.add(R.string.weibo_pay);
        titleList.add(R.string.personalized_service);
        titleList.add(R.string.my_card);
        titleList.add(R.string.my_draft_box);
    }

    @Override
    public int getCount()
    {
        return imageList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    static class ViewHolder{
        ImageView lineImageView;
        ImageView lineImageView2;
        ImageView imageView;
        TextView textView;
        TextView countTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_me_cell, null);
            holder = new ViewHolder();
            holder.lineImageView = ViewUtility.findViewById(convertView, R.id.line_image);
            holder.lineImageView2 = ViewUtility.findViewById(convertView, R.id.line_image2);
            holder.imageView = ViewUtility.findViewById(convertView, R.id.image_view);
            holder.textView = ViewUtility.findViewById(convertView, R.id.text_view);
            holder.countTextView = ViewUtility.findViewById(convertView, R.id.count_text);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.imageView.setImageResource(imageList.get(position));
        holder.textView.setText(titleList.get(position));
        holder.countTextView.setVisibility(View.GONE);

        if (position == 0 || position == 1 || position == 4 || position == 6)
        {
            holder.lineImageView.setVisibility(View.VISIBLE);
        }
        else {
            holder.lineImageView.setVisibility(View.GONE);
        }

        if (position == 7)
        {
            holder.lineImageView2.setVisibility(View.VISIBLE);
        }
        else {
            holder.lineImageView2.setVisibility(View.GONE);
        }

        return convertView;
    }
}
