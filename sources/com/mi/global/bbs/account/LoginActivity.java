package com.mi.global.bbs.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.xiaomi.infrared.InifraredContants;

public class LoginActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public Button mLoginButton;

    public void onInvalidAuthonToken() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.bbs_activity_login);
        Log.d("mi-community", "onCreate");
        LoginManager.getInstance().addLoginListener(this);
        this.mLoginButton = (Button) findViewById(R.id.btn_login);
        if (LoginManager.getInstance().hasLogin()) {
            this.mLoginButton.setText(InifraredContants.B);
        } else {
            this.mLoginButton.setText("登录");
        }
        this.mLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    LoginManager.getInstance().logout();
                } else {
                    LoginActivity.this.gotoAccount();
                }
            }
        });
    }

    public void onLogin(String str, String str2, String str3) {
        Log.d("mi-community", "onLogin");
        runOnUiThread(new Runnable() {
            public void run() {
                LoginActivity.this.mLoginButton.setText(InifraredContants.B);
            }
        });
    }

    public void onLogout() {
        Log.d("mi-community", "onLogout");
        this.mLoginButton.setText("登录");
    }
}
