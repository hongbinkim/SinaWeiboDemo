package com.hbk.sinaweibodemo.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hbk.sinaweibodemo.Adapter.WeiboAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseFragment;
import com.hbk.sinaweibodemo.bean.WeiboBean;
import com.hbk.sinaweibodemo.bean.WeiboList;
import com.hbk.sinaweibodemo.common.WeiboContentActivity;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by HongBinKim on 14/8/11.
 */
public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener
{

    private PullToRefreshListView listView;

    private WeiboList weiboList = new WeiboList();

    private WeiboAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home, container, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setNavigationTitle("Home");
        setNaviImageButtonImage(R.drawable.navigation_friend_selector, R.drawable.navigation_home_more_selector);

        listView = ViewUtility.findViewById(getView(), R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);



        adapter = new WeiboAdapter(getContext(), weiboList);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(this);



    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (AccessTokenKeeper.isLogin(getContext()))
        {
            new HomeLineAsycTask().execute();
        }

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView)
    {
       new HomeLineRefreshAsycTask().execute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView)
    {
        new HomeLineLoadMoreAsycTask().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        WeiboBean bean =  (WeiboBean)adapter.getItem(position);

        Intent intent = new Intent(getContext(), WeiboContentActivity.class);

        intent.putExtra("bean", bean);
        startActivity(intent);
    }


    //    HomeLineAsycTask
    private class HomeLineAsycTask extends AsyncTask<Void, Void, WeiboList>
    {

        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> pram = HttpUtility.getBaseHttpParmas(getContext());
            pram.put("count", "20");
            String url = URLHelper.HOME_TIMELINE + "?" + Utility.encodeUrl(pram);
            WeiboList response = restTemplate.getForObject(url, WeiboList.class);
            return response;
        }

        @Override
        protected void onPostExecute(WeiboList list)
        {
            weiboList = list;
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    //    HomeLineLoadMoreAsycTask
    private class HomeLineLoadMoreAsycTask extends AsyncTask<Void, Integer, WeiboList>
    {

        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(getContext());
            param.put("count", "20");
            param.put("max_id", String.valueOf(weiboList.getMaxId()));
            String url = URLHelper.HOME_TIMELINE + "?" + Utility.encodeUrl(param);
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

        }
    }

    //    HomeLineRefreshAsycTask
    private class HomeLineRefreshAsycTask extends AsyncTask<Void, Integer, WeiboList>
    {

        @Override
        protected WeiboList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(getContext());
            param.put("since_id", String.valueOf(weiboList.getSinceId()));
            String url = URLHelper.HOME_TIMELINE + "?" + Utility.encodeUrl(param);
            WeiboList list = restTemplate.getForObject(url, WeiboList.class);
            return list;
        }

        @Override
        protected void onPostExecute(WeiboList list)
        {
            adapter.addRefreshList(list);
            weiboList = adapter.getList();
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
        }
    }
}
