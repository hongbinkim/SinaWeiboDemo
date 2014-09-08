package com.hbk.sinaweibodemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.ViewUtility;

/**
 * Created by HongBinKim on 14/8/20.
 */
public class MoreItem extends LinearLayout
{
    private LinearLayout itemButton;
    private ImageView imageView;
    private TextView textView;

    public MoreItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.setOrientation(VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_more_item, this, true);
        imageView = ViewUtility.findViewById(this, R.id.image_view);
        textView = ViewUtility.findViewById(this, R.id.text_view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MoreItem);
        Drawable drawable = array.getDrawable(R.styleable.MoreItem_imageSrc);
        String text = array.getString(R.styleable.MoreItem_titleText);
        imageView.setImageDrawable(drawable);
        textView.setText(text);

        array.recycle();
    }


}
