package com.hbk.sinaweibodemo.common.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14-8-27.
 */
public class ProfileSectionView extends RelativeLayout implements View.OnClickListener
{
    private Context context;
    private RadioGroup group;
    private RadioButton profileRadio, weiboRadio, albumRadio;
    private ImageView barImageView;
    private int gap = 0;
    private ProfileSectionView sectionView;

    private List<RadioButton> list = new ArrayList<RadioButton>();

    public ProfileSectionView(Context context)
    {
        super(context);



        this.context = context;
        this.setBackgroundResource(R.drawable.market_cursor_background);
        this.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utility.dip2px(context, 52)));

        LayoutInflater.from(context).inflate(R.layout.view_profile_section, this, true);

        this.group = ViewUtility.findViewById(this, R.id.section_group);
        group.setWillNotDraw(false);


        this.profileRadio = ViewUtility.findViewById(this, R.id.profile_radio);
        this.weiboRadio = ViewUtility.findViewById(this, R.id.weibo_radio);
        this.albumRadio = ViewUtility.findViewById(this, R.id.album_radio);

        this.barImageView = ViewUtility.findViewById(this, R.id.bar_image);


        this.profileRadio.setOnClickListener(this);
        this.weiboRadio.setOnClickListener(this);
        this.albumRadio.setOnClickListener(this);

        list.add(this.profileRadio);
        list.add(this.weiboRadio);
        list.add(this.albumRadio);


    }


    public void setArrowImageX(int viewId)
    {
        for (RadioButton button : list)
        {
            if (button.getId() == viewId)
            {
                barImageView.setX(button.getX());
            }
        }

    }



    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        AppLogger.v("onDraw -- >" + this.barImageView);
    }

    @Override
    public void onClick(final View buttonView)
    {
        float first = buttonView.getX();

        final float toX = buttonView.getX() - barImageView.getX() + gap;

        barImageView.setTranslationX(buttonView.getX());


        AppLogger.v("tox -- >" + this.barImageView);


       




        TranslateAnimation animation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0,
                TranslateAnimation.RELATIVE_TO_SELF, toX,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0);
        animation.setDuration(context.getResources().getInteger(android.R.integer.config_longAnimTime));
        animation.setFillAfter(true);



        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {



                barImageView.clearAnimation();

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }

    //is git git test

    public void setSectionView(ProfileSectionView sectionView)
    {
        this.sectionView = sectionView;
    }
}
