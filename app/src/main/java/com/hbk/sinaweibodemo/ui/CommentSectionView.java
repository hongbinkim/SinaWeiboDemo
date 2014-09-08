package com.hbk.sinaweibodemo.ui;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14-8-25.
 */
public class CommentSectionView extends RelativeLayout implements View.OnClickListener
{
    private Context context;

    private Button repostButton;
    private Button commentButton;
    private Button likeButton;
    private ImageView arrowImageView;

    private int normalColor;
    private int hilightedColor;

    private List<Button> list = new ArrayList<Button>();

    private Button selectedButton;




    public CommentSectionView(Context context)
    {
        super(context);

        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.view_comment_section, this, true);

        this.repostButton = ViewUtility.findViewById(this, R.id.repost_button);
        this.commentButton = ViewUtility.findViewById(this, R.id.comment_button);
        this.likeButton = ViewUtility.findViewById(this, R.id.like_button);
        this.arrowImageView = ViewUtility.findViewById(this, R.id.arrow_image);

        normalColor = context.getResources().getColor(R.color.font_intro);
        hilightedColor = context.getResources().getColor(R.color.font_discover);

        repostButton.setTextColor(normalColor);
        commentButton.setTextColor(hilightedColor);
        likeButton.setTextColor(normalColor);



        repostButton.setOnClickListener(this);
        commentButton.setOnClickListener(this);
        likeButton.setOnClickListener(this);

        list.add(repostButton);
        list.add(commentButton);
        list.add(likeButton);
    }



    @Override
    public void onClick(View v)
    {
        setSelecteButtonTextColor((Button)v);



        translateArrowImage(v);
    }

    private void translateArrowImage(View view)
    {

        //AppLogger.v("left ------ >" + view.getLeft());
        TranslateAnimation animation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, view.getLeft(), TranslateAnimation.RELATIVE_TO_SELF, 0);
        animation.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        arrowImageView.startAnimation(animation);
    }

    public Button getSelectedButton()
    {
        return selectedButton;
    }

    public void setSelectedButton(Button selectedButton)
    {
        this.selectedButton = selectedButton;
        setSelecteButtonTextColor(selectedButton);
    }

    private void setSelecteButtonTextColor(Button button)
    {
        for (Button btn : list)
        {
            if (btn.getId() == button.getId())
            {
                btn.setTextColor(hilightedColor);
            }
            else
            {
                btn.setTextColor(normalColor);
            }
        }
    }
}
