package com.xiaomi.youpin.login.risk_control;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xiaomi.youpin.login.LoginEventUtil;
import com.xiaomi.youpin.login.other.common.DisplayUtils;

public class LoginSystemRiskControlActivity extends Activity {
    public static final String INTENT = "intent";

    /* renamed from: a  reason: collision with root package name */
    private static final int f23594a = 100;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.b(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.a(this)) {
            setRequestedOrientation(1);
        }
        startActivityForResult((Intent) getIntent().getParcelableExtra("intent"), 100);
    }

    public void onBackPressed() {
        LoginEventUtil.d(this);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (i2 == -1) {
                LoginEventUtil.c(this);
            } else {
                LoginEventUtil.d(this);
            }
            finish();
        }
    }
}
