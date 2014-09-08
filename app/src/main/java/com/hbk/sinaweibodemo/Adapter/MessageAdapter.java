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
 * Created by HongBinKim on 14/8/18.
 */
public class MessageAdapter extends BaseAdapter
{
    private Context context;
    private List<Integer> imageList = new ArrayList<Integer>();
    private List<Integer> titleList = new ArrayList<Integer>();

    public MessageAdapter(Context context)
    {
        this.context = context;
        imageList.add(R.drawable.messagescenter_at);
        imageList.add(R.drawable.messagescenter_comments);
        imageList.add(R.drawable.messagescenter_good);
        imageList.add(R.drawable.messagescenter_messagebox);

        titleList.add(R.string.message_mention);
        titleList.add(R.string.message_comment);
        titleList.add(R.string.message_like);
        titleList.add(R.string.message_box);

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

    static class ViewHoler
    {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHoler holer;
        if (convertView == null){
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_message_table_cell, null);
            holer = new ViewHoler();
            holer.imageView = ViewUtility.findViewById(convertView, R.id.imageView);
            holer.textView = ViewUtility.findViewById(convertView, R.id.textView);
            convertView.setTag(holer);
        }
        else {
            holer = (ViewHoler)convertView.getTag();
        }

        holer.imageView.setImageResource(imageList.get(position));
        holer.textView.setText(titleList.get(position));

        return convertView;
    }
}
