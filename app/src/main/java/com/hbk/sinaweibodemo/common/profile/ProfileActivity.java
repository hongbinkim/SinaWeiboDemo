package com.hbk.sinaweibodemo.common.profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PinnedSectionListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshPinnedListView;
import com.hbk.sinaweibodemo.Adapter.ProfileWeiboAdapter;
import com.hbk.sinaweibodemo.Adapter.WeiboAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.bean.WeiboList;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Map;

public class ProfileActivity extends Activity {

    private PullToRefreshPinnedListView listView;
    private ProfileWeiboAdapter weiboAdapter;
    private WeiboList weiboList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = ViewUtility.findViewById(this, R.id.list_view);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        ProfileHeaderView headerView = new ProfileHeaderView(ProfileActivity.this);
        listView.getRefreshableView().addHeaderView(headerView);


        weiboAdapter = new ProfileWeiboAdapter(ProfileActivity.this);
        listView.setAdapter(weiboAdapter);


        new WeiboAsyncTask().execute();
    }


    private class WeiboAsyncTask extends AsyncTask<Void, Integer, WeiboList>
    {

        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate template = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(ProfileActivity.this);
            param.put("uid", AccessTokenKeeper.getKeyUid(ProfileActivity.this));

            String url = URLHelper.STATUSES_TIMELINE_BY_ID + "?" + Utility.encodeUrl(param);

            WeiboList list = template.getForObject(url, WeiboList.class);

            return list;
        }

        @Override
        protected void onPostExecute(WeiboList list)
        {
            weiboList = list;
            weiboAdapter.setList(list);
            weiboAdapter.notifyDataSetChanged();
        }
    }




}
