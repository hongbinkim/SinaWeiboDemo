package com.hbk.sinaweibodemo.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hbk.sinaweibodemo.Adapter.MessageAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseFragment;
import com.hbk.sinaweibodemo.util.ViewUtility;

/**
 * Created by HongBinKim on 14/8/18.
 */
public class MessageFragment extends BaseFragment implements AdapterView.OnItemClickListener
{
    private PullToRefreshListView listView;
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_message, container, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setNavigationTitle("Message");

        listView = ViewUtility.findViewById(getView(), R.id.table_view);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        adapter = new MessageAdapter(getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onStart()
    {
        super.onStart();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent;
        switch (position)
        {
            case 1:
                intent = new Intent(getContext(), MentionActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getContext(), CommentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
