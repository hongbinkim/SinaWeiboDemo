package com.hbk.sinaweibodemo.message;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hbk.sinaweibodemo.Adapter.WeiboAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseActivity;
import com.hbk.sinaweibodemo.bean.WeiboList;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class MentionActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2
{
    private PullToRefreshListView listView;
    private WeiboAdapter adapter;
    private WeiboList weiboList = new WeiboList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mention);
        initNavigationBarSubViews();
        setNavigationTitle("All Weibo");
        setNaviLeftImgButtonImage(R.drawable.navigation_back_selector);

        listView = ViewUtility.findViewById(this, R.id.table_view);


        listView.setOnRefreshListener(this);

        adapter = new WeiboAdapter(MentionActivity.this, weiboList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        new MentionAsyncTask().execute();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView)
    {
        new MentionAsyncTask().execute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView)
    {
         new MentionLoadMoreAsyncTask().execute();
    }


    //    MentionAsyncTask
    private class MentionAsyncTask extends AsyncTask<Void, Integer, WeiboList>
    {
        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(MentionActivity.this);
            param.put("count", "10");
            String url = URLHelper.STATUSES_MENTIONS_TIMELINE + "?" + Utility.encodeUrl(param);
            WeiboList list = restTemplate.getForObject(url, WeiboList.class);
            return list;
        }

        @Override
        protected void onPostExecute(WeiboList list)
        {
            weiboList = list;
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();

            listView.setMode(PullToRefreshBase.Mode.BOTH);

        }
    }

    //    MentionLoadMoreAsyncTask
    private class MentionLoadMoreAsyncTask extends AsyncTask<Void, Integer, WeiboList>
    {

        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(MentionActivity.this);
            param.put("count", "10");
            param.put("max_id", String.valueOf(weiboList.getMaxId()));
            String url = URLHelper.STATUSES_MENTIONS_TIMELINE + "?" + Utility.encodeUrl(param);
            WeiboList list = restTemplate.getForObject(url, WeiboList.class);
            return list;

        }

        @Override
        protected void onPostExecute(WeiboList list)
        {
            adapter.addLoadMoreList(list);
            weiboList = adapter.getList();
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
            if(list.getWeiboList().size() < 1)
            {
                listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
        }
    }
}
