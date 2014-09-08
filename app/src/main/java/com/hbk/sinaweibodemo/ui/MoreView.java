package com.hbk.sinaweibodemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongBinKim on 14/8/20.
 */
public class MoreView extends RelativeLayout implements View.OnClickListener
{


    private MoreItem textItem, albumsItem, cameraItem;
    private MoreItem checkInItem, reviewItem, moreItem;
    private MoreItem friendCircleItem, audioPictureItem, miaopaiItem;
    private MoreItem flashDeleteItem, blogItem, collectionItem;
    private ImageButton backButton, cancelButton;
    private ImageView lineView;

    private int marginTop;
    private int marginLeft;

    private int deltaY;

    private int width;
    private int height;

    private List<MoreItem> line1 = new ArrayList<MoreItem>();
    private List<MoreItem> line2 = new ArrayList<MoreItem>();
    private List<MoreItem> line3 = new ArrayList<MoreItem>();
    private List<MoreItem> line4 = new ArrayList<MoreItem>();
    private List<MoreItem> lineSum = new ArrayList<MoreItem>();

    public MoreView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.setBackgroundResource(R.drawable.tabbar_compose_blur_background);

        LayoutInflater.from(context).inflate(R.layout.view_more, this, true);


        textItem = ViewUtility.findViewById(this, R.id.text_item);
        albumsItem = ViewUtility.findViewById(this, R.id.albums_item);
        cameraItem = ViewUtility.findViewById(this, R.id.camera_item);

        textItem.setOnClickListener(this);
        albumsItem.setOnClickListener(this);
        cameraItem.setOnClickListener(this);

        line1.add(textItem);
        line1.add(albumsItem);
        line1.add(cameraItem);


        checkInItem = ViewUtility.findViewById(this, R.id.checkin_item);
        reviewItem = ViewUtility.findViewById(this, R.id.review_item);
        moreItem = ViewUtility.findViewById(this, R.id.more_item);

        checkInItem.setOnClickListener(this);
        reviewItem.setOnClickListener(this);
        moreItem.setOnClickListener(this);

        line2.add(checkInItem);
        line2.add(reviewItem);
        line2.add(moreItem);

        friendCircleItem = ViewUtility.findViewById(this, R.id.friend_circle_item);
        audioPictureItem = ViewUtility.findViewById(this, R.id.audio_picture_item);
        miaopaiItem = ViewUtility.findViewById(this, R.id.miaopai_item);

        friendCircleItem.setOnClickListener(this);
        audioPictureItem.setOnClickListener(this);
        miaopaiItem.setOnClickListener(this);

        line3.add(friendCircleItem);
        line3.add(audioPictureItem);
        line3.add(miaopaiItem);

        flashDeleteItem = ViewUtility.findViewById(this, R.id.flash_delete_item);
        blogItem = ViewUtility.findViewById(this, R.id.blog_item);
        collectionItem = ViewUtility.findViewById(this, R.id.collection_item);

        flashDeleteItem.setOnClickListener(this);
        blogItem.setOnClickListener(this);
        collectionItem.setOnClickListener(this);

        line4.add(flashDeleteItem);
        line4.add(blogItem);
        line4.add(collectionItem);

        lineSum.addAll(line1);
        lineSum.addAll(line2);
        lineSum.addAll(line3);
        lineSum.addAll(line4);

        backButton = ViewUtility.findViewById(this, R.id.back_button);
        backButton.setOnClickListener(this);
        cancelButton = ViewUtility.findViewById(this, R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        lineView = ViewUtility.findViewById(this, R.id.line_image);

        setFirstTab();

        int height = Utility.getWindowHeight(getContext());

        marginTop = (int) (height / 2.7);

        deltaY = Utility.getWindowHeight(getContext()) - marginTop;


        LayoutParams layoutParams = (LayoutParams) textItem.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, marginTop, layoutParams.rightMargin, layoutParams.bottomMargin);


        marginLeft = Utility.dip2px(getContext(), 43);

        setItemsTranslateX(line3, Utility.getWindowWidth(getContext()));
        setItemsTranslateX(line4, Utility.getWindowWidth(getContext()));


        setItemsMarginLeft(marginLeft);

        width = Utility.getWindowWidth(getContext());
        height = Utility.getWindowHeight(getContext());
    }

    private void setFirstTab()
    {
        backButton.setVisibility(GONE);
        lineView.setVisibility(GONE);

    }

    private void setSecondTab()
    {
        backButton.setVisibility(VISIBLE);
        lineView.setVisibility(VISIBLE);
    }

    private void setItemsMarginLeft(int margin)
    {
        for (MoreItem item : line1)
        {
            LayoutParams layoutParams = (LayoutParams) item.getLayoutParams();
            layoutParams.setMargins(margin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        }
    }

    private void setItemsTranslateX(List<MoreItem> list, int translateX)
    {
        for (MoreItem item : list)
        {
            item.setTranslationX(translateX);
        }
    }

    private void setItemsX(List<MoreItem> list, int X)
    {
        for (MoreItem item : list)
        {
            item.setX(X);
        }
    }

    private void setItemsAnimation(List<MoreItem> list, Animation animation)
    {
        for (MoreItem item : list)
        {
            animation.setFillAfter(true);
            animation.setFillEnabled(true);
            int duration = getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime);
            animation.setDuration(duration);

            item.startAnimation(animation);
        }
    }

    private void setItemsAnimation2(List<MoreItem> list, Animation animation)
    {
        for (MoreItem item : list)
        {
            animation.setFillAfter(true);
            animation.setFillEnabled(true);
            int duration = getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
            animation.setDuration(duration);

            item.startAnimation(animation);
        }
    }


    @Override
    public void onClick(View v)
    {
        final int id = v.getId();
        if (id == R.id.more_item)
        {
            outAnimation();
        } else if (id == R.id.back_button)
        {
            inAnimation();
        } else if (id == R.id.cancel_button)
        {
            hide();
        } else
        {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.more_item_image);

            v.startAnimation(animation);


            animation.setAnimationListener(new Animation.AnimationListener()
            {
                @Override
                public void onAnimationStart(Animation animation)
                {

                }

                @Override
                public void onAnimationEnd(Animation animation)
                {
                    switch (id)
                    {
                        case R.id.text_item:
                            break;
                        case R.id.albums_item:
                            break;
                        case R.id.camera_item:
                            break;
                        case R.id.checkin_item:
                            break;
                        case R.id.review_item:
                            break;
                        case R.id.friend_circle_item:
                            break;
                        case R.id.audio_picture_item:
                            break;
                        case R.id.miaopai_item:
                            break;
                        case R.id.flash_delete_item:
                            break;
                        case R.id.blog_item:
                            break;
                        case R.id.collection_item:
                            break;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation)
                {

                }
            });
        }


    }

    public void hide()
    {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.more_view_hide);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        this.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

                hideItems();
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                MoreView.this.setY(Utility.getWindowHeight(getContext()));
                MoreView.this.setVisibility(INVISIBLE);
                MoreView.this.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }

    public void show()
    {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.more_view_show);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        this.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                MoreView.this.setY(0);
                MoreView.this.setVisibility(VISIBLE);
                showItems();
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }

    private void showItems()
    {
        TranslateAnimation animation = new TranslateAnimation(0, 0, deltaY, 0);
        setItemsAnimation2(lineSum, animation);

    }

    private void hideItems()
    {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, deltaY);
        setItemsAnimation2(lineSum, animation);

    }

    private void inAnimation()
    {
        TranslateAnimation animationIn = new TranslateAnimation(-width, 0, 0, 0);
        TranslateAnimation animationOut = new TranslateAnimation(0, width, 0, 0);


        setItemsAnimation(line1, animationIn);
        setItemsAnimation(line2, animationIn);
        setItemsAnimation(line3, animationOut);
        setItemsAnimation(line4, animationOut);


        animationIn.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

                setItemsTranslateX(line1, 0);
                setItemsTranslateX(line2, 0);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        animationOut.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                setItemsTranslateX(line3, Utility.getWindowWidth(getContext()));
                setItemsTranslateX(line4, Utility.getWindowWidth(getContext()));
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        setFirstTab();
    }

    private void outAnimation()
    {
        TranslateAnimation animationIn = new TranslateAnimation(width, 0, 0, 0);
        TranslateAnimation animationOut = new TranslateAnimation(0, -width, 0, 0);


        setItemsAnimation(line1, animationOut);
        setItemsAnimation(line2, animationOut);
        setItemsAnimation(line3, animationIn);
        setItemsAnimation(line4, animationIn);


        animationOut.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                setItemsTranslateX(line1, -Utility.getWindowWidth(getContext()));
                setItemsTranslateX(line2, -Utility.getWindowWidth(getContext()));
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        animationIn.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                setItemsTranslateX(line3, 0);
                setItemsTranslateX(line4, 0);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {


            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        setSecondTab();
    }
}
