package com.xiaomi.youpin.login.ui.web;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alipay.sdk.util.i;
import java.util.HashMap;
import java.util.Map;

public class LoginNewPwdChangeActivity extends LoginAutoLoginBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23626a = 100;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler b = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                LoginNewPwdChangeActivity.this.b.sendEmptyMessageDelayed(100, 1000);
                Map access$100 = LoginNewPwdChangeActivity.a("https://account.xiaomi.com");
                if (access$100 != null && !access$100.isEmpty()) {
                    String str = (String) access$100.get("passInfo");
                    if (!TextUtils.isEmpty(str) && str.contains("need-relogin")) {
                        LoginNewPwdChangeActivity.this.setResult(-1);
                        LoginNewPwdChangeActivity.this.finish();
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

    /* access modifiers changed from: protected */
    public void onWebViewFinish() {
        super.onWebViewFinish();
        setResult(0);
    }
}
