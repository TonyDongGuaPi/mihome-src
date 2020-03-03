package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookSdk;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.frame.login.MijiaLoginManager;
import com.xiaomi.smarthome.frame.login.ui.view.CaptchaDialog;
import com.xiaomi.smarthome.frame.login.ui.view.LoginBaseTitleBar;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.frame.login.util.LoginHistoryUtil;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.MiscStat;
import com.xiaomi.youpin.login.api.phone.PhoneQueryManager;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.Map;
import java.util.Random;

public abstract class LoginBaseActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f16197a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
                String action = intent.getAction();
                char c = 65535;
                if (action.hashCode() == 735880982 && action.equals(LoginEventUtil.f16346a)) {
                    c = 0;
                }
                if (c == 0 && intent.getBooleanExtra("login_success", false)) {
                    LoginBaseActivity.this.finish();
                }
            }
        }
    };
    protected Context mContext;
    protected MijiaLoginManager mLoginManager;
    protected PhoneQueryManager mPhoneQueryManager;
    protected long mRandomValue;
    protected CaptchaDialog vCaptchaDialog;
    protected XQProgressDialog vLoadingDialog;
    protected LoginBaseTitleBar vTitleBar;

    /* access modifiers changed from: protected */
    @LayoutRes
    public abstract int getLayoutResId();

    /* access modifiers changed from: protected */
    public abstract void init();

    /* access modifiers changed from: protected */
    public boolean isCheckAuthLoginTime() {
        return false;
    }

    protected static void logLoginFailEvent(String str, String str2, String str3, Map<String, String> map) {
        LoginHostApi g = FrameManager.b().g();
        g.a("loginType: " + str + " errCode " + str2 + " errMsg " + str3);
    }

    protected static void logLoginFailEvent(String str, int i, String str2, Map<String, String> map) {
        MiscStat.a(str, i, str2);
        logLoginFailEvent(str, String.valueOf(i), str2, map);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            FacebookSdk.sdkInitialize(getApplicationContext());
            SHApplication.initAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(getLayoutResId());
        if (isCheckAuthLoginTime()) {
            int intExtra = getIntent().getIntExtra("login_type", 1);
            Log.d("LoginMi", "type   :" + intExtra);
            if (intExtra == 3) {
                long b = LoginIntentUtil.b(getIntent());
                Log.d("LoginMi", "timeStamp   :" + b + "System.currentTimeMillis() - timeStamp" + (System.currentTimeMillis() - b));
                if (System.currentTimeMillis() - b > 10000) {
                    FrameManager.b().g().a(this.mContext);
                    finish();
                }
            }
        }
        this.mContext = this;
        this.mRandomValue = new Random().nextLong();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoginEventUtil.f16346a);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f16197a, intentFilter);
        this.vLoadingDialog = new XQProgressDialog(this.mContext);
        this.vLoadingDialog.setCancelable(true);
        this.vCaptchaDialog = new CaptchaDialog(this);
        TitleBarUtil.a((Activity) this);
        this.mPhoneQueryManager = new PhoneQueryManager(this);
        this.mLoginManager = new MijiaLoginManager(this);
        this.vTitleBar = (LoginBaseTitleBar) findViewById(R.id.title_bar);
        if (this.vTitleBar != null) {
            TitleBarUtil.a((View) this.vTitleBar);
            this.vTitleBar.setOnBackClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LoginBaseActivity.this.onBackPressed();
                }
            });
        }
        init();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LoginUtil.a((Activity) this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f16197a);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onLoginFinish() {
        dismissLoginDialog();
    }

    /* access modifiers changed from: protected */
    public void dismissLoginDialog() {
        if (this.vLoadingDialog != null && this.vLoadingDialog.isShowing()) {
            this.vLoadingDialog.dismiss();
        }
        if (this.vCaptchaDialog != null && this.vCaptchaDialog.a()) {
            this.vCaptchaDialog.b();
        }
    }

    /* access modifiers changed from: protected */
    public void processLoginSuccess(int i) {
        FrameManager.b().g().a(i);
        LoginHistoryUtil.a(this.mContext);
        setResult(-1);
        LoginEventUtil.a(this.mContext, true);
        finish();
    }

    /* access modifiers changed from: protected */
    public void processLoginSuccess(int i, LoginMiAccount loginMiAccount) {
        FrameManager.b().g().a(i);
        LoginHistoryUtil.a(this.mContext);
        setResult(-1);
        LoginEventUtil.a(this.mContext, true);
        finish();
    }

    /* access modifiers changed from: protected */
    public void processLoginFail() {
        FrameManager.b().g().b();
    }

    /* access modifiers changed from: protected */
    public void processTokenExpired() {
        LoginEventUtil.a(this.mContext);
    }
}
