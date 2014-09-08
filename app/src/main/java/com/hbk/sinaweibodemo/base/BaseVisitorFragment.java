package com.hbk.sinaweibodemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.login.LoginActivity;
import com.hbk.sinaweibodemo.util.AppLogger;

/**
 * Created by HongBinKim on 14/8/11.
 */
public class BaseVisitorFragment extends BaseFragment implements View.OnClickListener {
    protected Button signUpButton, loginButton;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpButton = (Button)findViewById(R.id.sing_up_button);
        loginButton = (Button)findViewById(R.id.login_button);

        signUpButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sing_up_button:
                break;
            case R.id.login_button:
                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivityForResult(intent, 0);


                break;
        }
    }
}
