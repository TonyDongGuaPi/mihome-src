package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.logic.LoginHelper;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import java.util.Map;

public class LoginMiuiActivity extends LoginBaseActivity {
    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.login_mi_by_system_account_activity;
    }

    /* access modifiers changed from: protected */
    public boolean isCheckAuthLoginTime() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void init() {
        Intent intent = getIntent();
        String c = LoginIntentUtil.c(intent);
        if (TextUtils.isEmpty(c)) {
            c = AccountManagerUtil.b(this.mContext);
        }
        ((TextView) findViewById(R.id.account_name)).setText(c);
        if (LoginUtil.a((Context) this, intent)) {
            finish();
            return;
        }
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginMiuiActivity.this.a();
            }
        });
        findViewById(R.id.change_account).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginMiuiActivity.this.b();
            }
        });
        findViewById(R.id.head_line).setVisibility(DarkModeCompat.a((Context) this) ? 8 : 0);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
        this.vLoadingDialog.show();
        this.mLoginManager.a((Activity) this, (MiuiSystemLoginCallback) new MiuiSystemLoginCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                if (LoginMiuiActivity.this.isValid()) {
                    LoginMiuiActivity.this.onLoginFinish();
                    LoginMiuiActivity.this.processLoginSuccess(4);
                }
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                String str2;
                if (LoginMiuiActivity.this.isValid()) {
                    LoginMiuiActivity.this.onLoginFinish();
                    if (!(i == -2010 || i == -2001)) {
                        switch (i) {
                            case LoginErrorCode.x /*-2013*/:
                            case LoginErrorCode.w /*-2012*/:
                                break;
                            default:
                                switch (i) {
                                    case LoginErrorCode.s /*-2008*/:
                                    case LoginErrorCode.r /*-2007*/:
                                    case LoginErrorCode.q /*-2006*/:
                                        break;
                                    default:
                                        str2 = LoginHelper.a(LoginMiuiActivity.this.getString(R.string.login_passport_login_fail), i, str);
                                        break;
                                }
                        }
                    }
                    str2 = LoginHelper.a(LoginMiuiActivity.this.getString(R.string.login_systemaccount_login_fail), i, str);
                    ToastManager.a().a(str2);
                    LoginMiuiActivity.this.processLoginFail();
                    LoginBaseActivity.logLoginFailEvent("MIUI", i, str, map);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        MjLoginRouter.a(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        LoginEventUtil.a(this.mContext, false);
    }
}
