package com.hbk.sinaweibodemo.message;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hbk.sinaweibodemo.Adapter.MessageComAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseActivity;
import com.hbk.sinaweibodemo.bean.CommentList;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CommentActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2
{
    private PullToRefreshListView listView;
    private MessageComAdapter adapter;
    private CommentList coms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initNavigationBarSubViews();
        setNavigationTitle("All Comments");
        setNaviLeftImgButtonImage(R.drawable.navigation_back_selector);

        listView = ViewUtility.findViewById(this, R.id.table_view);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);




        adapter = new MessageComAdapter(CommentActivity.this);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        new CommentsTomeAsyncTask().execute();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView)
    {
        new CommentsTomeAsyncTask().execute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView)
    {
        new CommentsTomeLoadMoreAsyncTask().execute();
    }

    //    CommentsTomeAsyncTask
    private class CommentsTomeAsyncTask extends AsyncTask<Void, Integer, CommentList>
    {

        @Override
        protected CommentList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(CommentActivity.this);
            param.put("count", "10");
            String url = URLHelper.COMMENTS_TO_ME_TIMELINE + "?" + Utility.encodeUrl(param);
            CommentList list = restTemplate.getForObject(url, CommentList.class);
            return list;
        }

        @Override
        protected void onPostExecute(CommentList commentList)
        {
            coms = commentList;
            adapter.setComs(commentList);
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
        }
    }

    //    CommentsTomeLoadMoreAsyncTask
    private class CommentsTomeLoadMoreAsyncTask extends AsyncTask<Void, Integer, CommentList>
    {

        @Override
        protected CommentList doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(CommentActivity.this);
            param.put("count", "10");
            param.put("max_id", String.valueOf(coms.getMaxId()));
            String url = URLHelper.COMMENTS_TO_ME_TIMELINE + "?" + Utility.encodeUrl(param);
            CommentList list = restTemplate.getForObject(url, CommentList.class);
            return list;
        }

        @Override
        protected void onPostExecute(CommentList commentList)
        {
            adapter.addLoadMoreList(commentList);
            coms = adapter.getComs();
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
        }
    }

}
