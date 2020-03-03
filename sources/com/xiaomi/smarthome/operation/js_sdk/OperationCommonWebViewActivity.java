package com.xiaomi.smarthome.operation.js_sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.base.BaseFragmentWebViewActivity;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import javax.annotation.Nullable;

public class OperationCommonWebViewActivity extends BaseFragmentWebViewActivity {
    public static final String ARGS_FROM_DEEP_LINK = "args_from_deep_link";
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_URL = "url";
    static final String TAG = "OperationCommonWebView";

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return R.id.container;
    }

    public static void start(Context context, String str, @Nullable String str2) {
        Intent intent = new Intent(context, OperationCommonWebViewActivity.class);
        intent.putExtra("url", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("title", str2);
        }
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_operation_common_web_view);
        String stringExtra = getIntent().getStringExtra("url");
        String stringExtra2 = getIntent().getStringExtra("title");
        if (!getIntent().getBooleanExtra(ARGS_FROM_DEEP_LINK, false) || (URLUtil.isHttpsUrl(stringExtra) && JsSdkUtils.c(stringExtra))) {
            openNewWindow((CommonWebViewFragment) null, CommonWebViewFragment.newInstance(stringExtra, stringExtra2, true));
            return;
        }
        LogUtil.b(TAG, "not valid url: " + stringExtra);
        finish();
    }
}
