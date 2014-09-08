package com.hbk.sinaweibodemo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hbk.sinaweibodemo.R;

/**
 * Created by HongBinKim on 14/8/11.
 */
public class BaseActivity extends Activity {
    protected ImageButton naviLeftImgButton, naviRightImgButton;
    protected TextView naviTitleTextView;




    protected void initNavigationBarSubViews() {

        naviLeftImgButton = (ImageButton) findViewById(R.id.navigation_left_image_button);
        naviLeftImgButton.setVisibility(View.INVISIBLE);

        naviRightImgButton = (ImageButton) findViewById(R.id.navigation_right_image_button);
        naviRightImgButton.setVisibility(View.INVISIBLE);

        naviTitleTextView = (TextView) findViewById(R.id.navigation_title);

        naviLeftImgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    protected void setNaviImageButtonImage(int leftId, int rightId) {
        setNaviLeftImgButtonImage(leftId);
        setNaviRightImgButtonImage(rightId);
    }

    protected void setNaviLeftImgButtonImage(int id) {
        Drawable drawable = getResources().getDrawable(id);
        naviLeftImgButton.setImageDrawable(drawable);
        naviLeftImgButton.setVisibility(View.VISIBLE);
    }

    protected void setNaviRightImgButtonImage(int id) {
        Drawable drawable = getResources().getDrawable(id);
        naviRightImgButton.setImageDrawable(drawable);
        naviRightImgButton.setVisibility(View.VISIBLE);
    }


    protected void setNavigationTitle(CharSequence txt) {
        naviTitleTextView.setText(txt);
    }

}
