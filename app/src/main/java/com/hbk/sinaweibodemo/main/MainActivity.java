package com.hbk.sinaweibodemo.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import com.hbk.sinaweibodemo.ui.MoreView;
import com.hbk.sinaweibodemo.ui.TabButton;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Constants;
import com.hbk.sinaweibodemo.util.ViewUtility;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TabHost tabHost;
    private TabButton homeButton, messageButton, moreButton, discoverButton, meButton;
    private MoreView moreView;

    private boolean isLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLogin = false;


        moreView = ViewUtility.findViewById(this, R.id.more_view);


//        tabHost
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

//        Not Login State
        tabHost.addTab(tabHost.newTabSpec(Constants.VISITOR_HOME_TAB).setIndicator("Visitor Home").setContent(R.id.visitor_home_tab));
        tabHost.addTab(tabHost.newTabSpec(Constants.VISITOR_MESSAGE_TAB).setIndicator("Visitor Message").setContent(R.id.visitor_message_tab));
        tabHost.addTab(tabHost.newTabSpec(Constants.VISITOR_ME_TAB).setIndicator("Visitor Me").setContent(R.id.visitor_me_tab));

//        Login State
        tabHost.addTab(tabHost.newTabSpec(Constants.HOME_TAB).setIndicator("home").setContent(R.id.home_tab));
        tabHost.addTab(tabHost.newTabSpec(Constants.MESSAGE_TAB).setIndicator("message").setContent(R.id.message_tab));
        tabHost.addTab(tabHost.newTabSpec(Constants.DISCOVER_TAB).setIndicator("discover").setContent(R.id.discover_tab));
        tabHost.addTab(tabHost.newTabSpec(Constants.ME_TAB).setIndicator("me").setContent(R.id.me_tab));



//        tabButtons
        homeButton = (TabButton)findViewById(R.id.tab_home);
        messageButton = (TabButton)findViewById(R.id.tab_message);
        moreButton= (TabButton)findViewById(R.id.tab_more);
        discoverButton = (TabButton)findViewById(R.id.tab_discover);
        meButton = (TabButton)findViewById(R.id.tab_me);

        homeButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);
        moreButton.setOnClickListener(this);
        discoverButton.setOnClickListener(this);
        meButton.setOnClickListener(this);


        homeButton.setChecked(true);


        validIsLogin();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

//    Read AccessToken

    public void validIsLogin()
    {



        isLogin = AccessTokenKeeper.isLogin(MainActivity.this);
        if (isLogin)
        {

        }
        String tag = isLogin ? Constants.HOME_TAB : Constants.VISITOR_HOME_TAB;
        tabHost.setCurrentTabByTag(tag);


    }

//    OnClickListener
    @Override
    public void onClick(View v) {
        TabButton button = (TabButton)v;

        String tag;

        switch (button.getId()){
            case R.id.tab_home:
                tag = isLogin ? Constants.HOME_TAB : Constants.VISITOR_HOME_TAB;
                tabHost.setCurrentTabByTag(tag);
                break;
            case R.id.tab_message:
                tag = isLogin ? Constants.MESSAGE_TAB : Constants.VISITOR_MESSAGE_TAB;
                tabHost.setCurrentTabByTag(tag);
                break;
            case R.id.tab_more:
                tabMoreClick();
                break;
            case R.id.tab_discover:
                if (isLogin){
                    tabHost.setCurrentTabByTag(Constants.DISCOVER_TAB);
                }
                break;
            case R.id.tab_me:
                tag = isLogin ? Constants.ME_TAB : Constants.VISITOR_ME_TAB;
                tabHost.setCurrentTabByTag(tag);
                break;
        }
    }

    private void tabMoreClick()
    {
        moreView.show();
    }
}
