package com.xiaomi.youpin.login.ui.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import com.alipay.sdk.util.i;
import java.util.HashMap;
import java.util.Map;

public class LoginH5HomeAcvtivity extends LoginAutoLoginBaseActivity {
    public static final String NEED_RELOGIN_EVENT = "login_need_relogin";

    /* renamed from: a  reason: collision with root package name */
    private static final int f23623a = 100;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler b = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                LoginH5HomeAcvtivity.this.b.sendEmptyMessageDelayed(100, 1000);
                Map access$100 = LoginH5HomeAcvtivity.a("https://account.xiaomi.com");
                if (access$100 != null && !access$100.isEmpty()) {
                    String str = (String) access$100.get("passInfo");
                    if (!TextUtils.isEmpty(str) && str.contains("need-relogin")) {
                        LocalBroadcastManager.getInstance(LoginH5HomeAcvtivity.this.mContext.getApplicationContext()).sendBroadcast(new Intent(LoginH5HomeAcvtivity.NEED_RELOGIN_EVENT));
                        LoginH5HomeAcvtivity.this.finish();
                    }
                }
            }
        }
    };

    /* access modifiers changed from: private */
    @Nullable
    public static Map<String, String> a(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        if (TextUtils.isEmpty(cookie)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String split : cookie.split(i.b)) {
            String[] split2 = split.split("=");
            if (split2.length > 1) {
                hashMap.put(split2[0].trim(), split2[1].trim());
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.vTitleBar.setOnCloseClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginH5HomeAcvtivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.b.sendEmptyMessageDelayed(100, 1000);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.b.removeMessages(100);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.b.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }
}
