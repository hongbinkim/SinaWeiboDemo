package com.hbk.sinaweibodemo.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PinnedSectionListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshPinnedListView;
import com.hbk.sinaweibodemo.Adapter.WeiboContentAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseActivity;
import com.hbk.sinaweibodemo.bean.CommentBean;
import com.hbk.sinaweibodemo.bean.CommentList;
import com.hbk.sinaweibodemo.bean.WeiboBean;
import com.hbk.sinaweibodemo.ui.WeiboView;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Constants;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.sina.weibo.sdk.api.share.Base;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeiboContentActivity extends BaseActivity
{

    private PullToRefreshPinnedListView listView;
    private WeiboView weiboView;
    private WeiboBean bean;

    private WeiboContentAdapter adapter;
    private CommentList commentList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_content);
        initNavigationBarSubViews();
        setNavigationTitle("Weibo Content");
        setNaviImageButtonImage(R.drawable.navigation_back_selector, R.drawable.navigation_more_selector);

        listView = ViewUtility.findViewById(this, R.id.list_view);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        weiboView = new WeiboView(WeiboContentActivity.this);
        weiboView.getBottomBar().setVisibility(View.GONE);
        listView.getRefreshableView().addHeaderView(weiboView);

        Intent intent = getIntent();
        bean = intent.getParcelableExtra("bean");

        weiboView.layoutSubViews(bean);

        adapter = new WeiboContentAdapter(WeiboContentActivity.this);
        listView.setAdapter(adapter);

//        listView.setAdapter(new SimpleAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1));

       new CommentsAsyncTask().execute();
    }



    static class SimpleAdapter extends ArrayAdapter<Item> implements PinnedSectionListView.PinnedSectionListAdapter
    {

        private static final int[] COLORS = new int[] {
                R.color.green_light, R.color.orange_light,
                R.color.blue_light, R.color.red_light };

        public SimpleAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);

            final int sectionsNumber = 'Z' - 'A' + 1;
            prepareSections(sectionsNumber);

            int sectionPosition = 0, listPosition = 0;
            for (char i=0; i<sectionsNumber; i++) {
                Item section = new Item(Item.SECTION, String.valueOf((char)('A' + i)));
                section.sectionPosition = sectionPosition;
                section.listPosition = listPosition++;
                onSectionAdded(section, sectionPosition);
                add(section);

                final int itemsNumber = (int) Math.abs((Math.cos(2f*Math.PI/3f * sectionsNumber / (i+1f)) * 25f));
                for (int j=0;j<itemsNumber;j++) {
                    Item item = new Item(Item.ITEM, section.text.toUpperCase(Locale.ENGLISH) + " - " + j);
                    item.sectionPosition = sectionPosition;
                    item.listPosition = listPosition++;
                    add(item);
                }

                sectionPosition++;
            }
        }

        protected void prepareSections(int sectionsNumber) { }
        protected void onSectionAdded(Item section, int sectionPosition) { }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTextColor(Color.DKGRAY);
            view.setTag("" + position);
            Item item = getItem(position);
            if (item.type == Item.SECTION) {
                //view.setOnClickListener(PinnedSectionListActivity.this);
                view.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
            }
            return view;
        }

        @Override public int getViewTypeCount() {
            return 2;
        }

        @Override public int getItemViewType(int position) {
            return getItem(position).type;
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == Item.SECTION;
        }

    }

    static class Item {

        public static final int ITEM = 0;
        public static final int SECTION = 1;

        public final int type;
        public final String text;

        public int sectionPosition;
        public int listPosition;

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }

        @Override public String toString() {
            return text;
        }

    }


    private class CommentsAsyncTask extends AsyncTask<Void, Integer, CommentList>
    {

        @Override
        protected CommentList doInBackground(Void... params)
        {
            RestTemplate template = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(WeiboContentActivity.this);
            param.put("id", bean.getIdstr());

            String url = URLHelper.COMMENTS_TIMELINE_BY_MSGID + "?" + Utility.encodeUrl(param);

            CommentList coms = template.getForObject(url, CommentList.class);

            return coms;
        }

        @Override
        protected void onPostExecute(CommentList coms)
        {
            CommentBean com = new CommentBean();
            com.setIsSection(Constants.SECTION);
            coms.getCommentList().add(0, com);
            commentList = coms;
            adapter.setComs(coms);
            adapter.notifyDataSetChanged();
        }
    }



}
