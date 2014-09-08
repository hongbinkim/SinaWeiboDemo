package com.hbk.sinaweibodemo.discover;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbk.sinaweibodemo.Adapter.DiscoverAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseFragment;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by HongBinKim on 14/8/19.
 */
public class DiscoverFragment extends Fragment
{
    private ListView listView;
    private LayoutInflater inflater;
    private LinearLayout headerView;
    private DiscoverAdapter adapter;
    private List<String> topics = new ArrayList<String>();
    private TextView topic1, topic2, topic3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_discover, container, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        listView = ViewUtility.findViewById(getView(), R.id.list_view);

        headerView = (LinearLayout)inflater.inflate(R.layout.view_discover_header, null);
        listView.addHeaderView(headerView);

        topic1 = ViewUtility.findViewById(headerView, R.id.topic1);
        topic2 = ViewUtility.findViewById(headerView, R.id.topic2);
        topic3 = ViewUtility.findViewById(headerView, R.id.topic3);

        adapter = new DiscoverAdapter(getActivity().getApplicationContext());
        listView.setAdapter(adapter);


    }

    @Override
    public void onStart()
    {
        super.onStart();
        if ( AccessTokenKeeper.isLogin(getActivity().getApplicationContext()))
        {
            new TopicAsyncTask().execute();
        }

    }


    private class TopicAsyncTask extends AsyncTask<Void, Integer, List<String>>{

        @Override
        protected List<String> doInBackground(Void... params)
        {
            RestTemplate restTemplate = HttpUtility.getStringHttpMessageTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(getActivity().getApplicationContext());
            String url = URLHelper.TOPIC_DAILY_LIST + "?" + Utility.encodeUrl(param);

            String result = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node;
            try
            {
                node = mapper.readTree(result);

                node = node.get("trends");

                Iterator<JsonNode> iterator = node.getElements();

                if (iterator.hasNext())
                {
                    node = iterator.next();
                    iterator = node.getElements();
                    while (iterator.hasNext())
                    {
                        node = iterator.next();
                        String topic = node.get("name").getTextValue();
                        topics.add(topic);
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }


            return topics;
        }

        @Override
        protected void onPostExecute(List<String> list)
        {
            StringBuffer buffer = new StringBuffer();
            try
            {
                buffer.append("#").append(list.get(0)).append("#");
                topic1.setText(buffer.toString());

                buffer = new StringBuffer();
                buffer.append("#").append(list.get(1)).append("#");
                topic2.setText(buffer.toString());

                buffer = new StringBuffer();
                buffer.append("#").append(list.get(2)).append("#");
                topic3.setText(buffer.toString());
            }
            catch (IndexOutOfBoundsException e)
            {

            }

        }
    }
}
