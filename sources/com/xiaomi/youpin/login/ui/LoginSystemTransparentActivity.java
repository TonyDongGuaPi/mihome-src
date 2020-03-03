package com.xiaomi.youpin.login.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import com.xiaomi.youpin.login.LoginEventUtil;
import com.xiaomi.youpin.login.other.common.DisplayUtils;

public class LoginSystemTransparentActivity extends Activity {
    public static final String PARAM_XMACCOUNTVISIBILITY = "XmAccountVisibility";

    /* renamed from: a  reason: collision with root package name */
    private static final int f23605a = 100;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.b(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.a(this)) {
            setRequestedOrientation(1);
        }
        Intent intent = ((XmAccountVisibility) getIntent().getParcelableExtra(PARAM_XMACCOUNTVISIBILITY)).newChooseAccountIntent;
        intent.putExtra("descriptionTextOverride", "是否允许小米有品访问您的系统小米帐号？");
        startActivityForResult(intent, 100);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (i2 == -1) {
                LoginEventUtil.b((Context) this, intent.getStringExtra("authAccount"), intent.getStringExtra("accountType"));
            } else {
                LoginEventUtil.a(this);
            }
            finish();
        }
    }
}
