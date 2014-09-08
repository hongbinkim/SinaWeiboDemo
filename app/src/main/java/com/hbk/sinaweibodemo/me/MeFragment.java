package com.hbk.sinaweibodemo.me;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hbk.sinaweibodemo.Adapter.MeAdapter;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.base.BaseFragment;
import com.hbk.sinaweibodemo.bean.UserBean;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.HttpUtility;
import com.hbk.sinaweibodemo.util.URLHelper;
import com.hbk.sinaweibodemo.util.Utility;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by HongBinKim on 14/8/20.
 */
public class MeFragment extends BaseFragment
{

    private LayoutInflater inflater;
    private ListView listView;
    private LinearLayout headerView;
    private MeAdapter adapter;

    private ImageView avatarImageView;
    private TextView nickTextView;
    private TextView introTextView;
    private TextView weiboCountTextView;
    private TextView followCountTextView;
    private TextView followerCountTextView;

    private DisplayImageOptions avatarOption;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.inflater = inflater;

        return inflater.inflate(R.layout.fragment_me, container, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setNavigationTitle("Me");
        listView = ViewUtility.findViewById(getView(), R.id.list_view);

        headerView = (LinearLayout) inflater.inflate(R.layout.view_me_header, null);
        listView.addHeaderView(headerView);

        avatarImageView = ViewUtility.findViewById(headerView, R.id.avatar_image);
        nickTextView = ViewUtility.findViewById(headerView, R.id.nick_text);
        introTextView = ViewUtility.findViewById(headerView, R.id.intro_text);
        weiboCountTextView = ViewUtility.findViewById(headerView, R.id.weibo_count_text);
        followCountTextView = ViewUtility.findViewById(headerView, R.id.follow_count_text);
        followerCountTextView = ViewUtility.findViewById(headerView, R.id.follower_count_text);

        this.avatarOption = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar_default)
                .showImageForEmptyUri(R.drawable.avatar_default)
                .showImageOnFail(R.drawable.avatar_default)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .build();

        adapter = new MeAdapter(getContext());

        listView.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (AccessTokenKeeper.isLogin(getContext()))
        {
            new UserInfoAsyncTask().execute();
        }


    }

    //    UserInfoAsyncTask
    private class UserInfoAsyncTask extends AsyncTask<Void, Integer, UserBean>
    {

        @Override
        protected UserBean doInBackground(Void... params)
        {
            RestTemplate template = HttpUtility.getMappingJacksonTemplate();
            Map<String, String> param = HttpUtility.getBaseHttpParmas(getContext());
            param.put("uid", AccessTokenKeeper.getKeyUid(getContext()));

            String url = URLHelper.USER_SHOW + "?" + Utility.encodeUrl(param);

            UserBean bean = template.getForObject(url, UserBean.class);

            return bean;
        }

        @Override
        protected void onPostExecute(UserBean userBean)
        {
            ImageLoader.getInstance().displayImage(userBean.getAvatarHd(), avatarImageView, avatarOption);
            nickTextView.setText(userBean.getScreenName());
            introTextView.setText(userBean.getDescription());
            weiboCountTextView.setText(String.valueOf(userBean.getStatusesCount()));
            followCountTextView.setText(String.valueOf(userBean.getFriendsCount()));
            followerCountTextView.setText(String.valueOf(userBean.getFollowersCount()));
        }
    }
}
