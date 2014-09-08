package com.hbk.sinaweibodemo.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hbk.sinaweibodemo.R;

import com.hbk.sinaweibodemo.base.BaseVisitorFragment;

/**
 * Created by HongBinKim on 14/8/10.
 */
public class VisitorHomeFragment extends BaseVisitorFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_visitor_home, container, true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setNavigationTitle("Home");
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
