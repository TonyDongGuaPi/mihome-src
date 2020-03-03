package com.xiaomi.smarthome.newui.onekey_delete;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.base.BaseFragmentWebViewActivity;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.smarthome.shop.utils.ToastUtil;

public class AuthTokenActivity extends BaseFragmentWebViewActivity implements AuthActivityBridge {
    public static final String ARGS_KEY_URL = "url";

    /* renamed from: a  reason: collision with root package name */
    private static final String f20695a = "AuthTokenActivity";

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return R.id.container;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_operation_common_web_view);
        String stringExtra = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(stringExtra)) {
            openNewWindow((CommonWebViewFragment) null, AuthFragment.a(stringExtra, ""));
        } else {
            finish();
        }
    }

    public void onAuthSuccess(String str) {
        LogUtil.a(f20695a, "onAuthSuccess: ");
        Intent intent = new Intent();
        intent.putExtra(RevokeAuthActivity.ARG_AUTH_TOKEN, str);
        setResult(-1, intent);
        finish();
    }

    public void onAuthFailed(String str) {
        ToastUtil.a((Context) this, (CharSequence) "auth failed: " + str);
    }
}
