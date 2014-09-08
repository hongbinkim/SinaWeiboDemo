package com.hbk.sinaweibodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/19.
 */
public class DiscoverAdapter extends BaseAdapter
{
    private Context context;
    private List<Integer> imageList = new ArrayList<Integer>();
    private List<Integer> titleList = new ArrayList<Integer>();

    public DiscoverAdapter(Context context)
    {
        this.context = context;
        imageList.add(R.drawable.discover_hot_weibo);
        imageList.add(R.drawable.discover_new_friend);
        imageList.add(R.drawable.discover_game);
        imageList.add(R.drawable.discover_app);
        imageList.add(R.drawable.discover_nearby);
        imageList.add(R.drawable.discover_movie);
        imageList.add(R.drawable.discover_music);
        imageList.add(R.drawable.discover_travel);
        imageList.add(R.drawable.discover_more);

        titleList.add(R.string.hot_weibo);
        titleList.add(R.string.new_friend);
        titleList.add(R.string.game);
        titleList.add(R.string.apps);
        titleList.add(R.string.nearby);
        titleList.add(R.string.movie);
        titleList.add(R.string.music);
        titleList.add(R.string.travel);
        titleList.add(R.string.more);
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
        return 0;
    }

    static class ViewHolder{
        ImageView lineImageView;
        ImageView lineImageView2;
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_discover_cell, null);
            holder = new ViewHolder();
            holder.lineImageView = ViewUtility.findViewById(convertView, R.id.line_image);
            holder.lineImageView2 = ViewUtility.findViewById(convertView, R.id.line_image2);
            holder.imageView = ViewUtility.findViewById(convertView, R.id.imageView);
            holder.textView = ViewUtility.findViewById(convertView, R.id.textView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        if (position == 0 || position == 2 || position == 5){
            holder.lineImageView.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.lineImageView.setVisibility(View.GONE);
        }

        if (position == (imageList.size() - 1)){
            holder.lineImageView2.setVisibility(View.VISIBLE);
        }
        else {
            holder.lineImageView2.setVisibility(View.GONE);
        }


        holder.imageView.setImageResource(imageList.get(position));
        holder.textView.setText(titleList.get(position));

        return convertView;
    }
}
