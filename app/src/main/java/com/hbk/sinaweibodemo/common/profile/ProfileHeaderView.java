package com.hbk.sinaweibodemo.common.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hbk.sinaweibodemo.R;

/**
 * Created by HongBinKim on 14-8-27.
 */
public class ProfileHeaderView extends RelativeLayout
{
    private Context context;

    public ProfileHeaderView(Context context)
    {
        super(context);
        this.context = context;


        LayoutInflater.from(context).inflate(R.layout.view_profile_header, this, true);
    }
}
