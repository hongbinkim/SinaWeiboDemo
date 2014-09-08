package com.hbk.sinaweibodemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.hbk.sinaweibodemo.R;

/**
 * Created by HongBinKim on 14/8/10.
 */
public class BaseFragment extends Fragment {
    protected ImageButton naviLeftImgButton, naviRightImgButton;
    protected TextView naviTitleTextView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initNavigationBarSubViews();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    protected void initNavigationBarSubViews() {

        naviLeftImgButton = (ImageButton) getView().findViewById(R.id.navigation_left_image_button);

        naviRightImgButton = (ImageButton) getView().findViewById(R.id.navigation_right_image_button);

        naviTitleTextView = (TextView) getView().findViewById(R.id.navigation_title);
    }

    protected void setNaviImageButtonImage(int leftId, int rightId) {
        setNaviLeftImgButtonImage(leftId);
        setNaviRightImgButtonImage(rightId);
    }

    protected void setNaviLeftImgButtonImage(int id) {
        Drawable drawable = getResources().getDrawable(id);
        naviLeftImgButton.setImageDrawable(drawable);
    }

    protected void setNaviRightImgButtonImage(int id) {
        Drawable drawable = getResources().getDrawable(id);
        naviRightImgButton.setImageDrawable(drawable);
    }

    protected Context getContext(){
        return getActivity().getApplicationContext();
    }

    protected void setNavigationTitle(CharSequence txt) {
        naviTitleTextView.setText(txt);
    }

    protected View findViewById(int id) {
        return getView().findViewById(id);
    }
}
