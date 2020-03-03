package com.xiaomi.smarthome.operation.js_sdk.share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

public class ShareHelper extends IUrlInterceptorAdapter implements IShareAction {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21107a = "ShareHelper";
    private final CommonWebView b;
    private LoadingDialogHelper c;
    private final Context d;

    public void a() {
    }

    public ShareHelper(CommonWebView commonWebView, Context context) {
        this.b = commonWebView;
        this.c = new LoadingDialogHelper(context);
        this.d = context;
    }

    @SuppressLint({"CheckResult"})
    public void c() {
        if (Build.VERSION.SDK_INT >= 19) {
            JsSdkUtils.a((WebView) this.b, "window.hasOwnProperty('smartHome')").subscribe(new Consumer() {
                public final void accept(Object obj) {
                    ShareHelper.this.d((String) obj);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(String str) throws Exception {
        if (Boolean.parseBoolean(str)) {
            JsSdkUtils.a((WebView) this.b, "smartHome.share.getOption()").subscribe(new Consumer() {
                public final void accept(Object obj) {
                    ShareHelper.this.f((String) obj);
                }
            });
        } else {
            Observable.zip(JsSdkUtils.a((WebView) this.b, "location.href"), JsSdkUtils.a((WebView) this.b, "document.title"), new BiFunction() {
                public final Object apply(Object obj, Object obj2) {
                    return ShareHelper.this.a((String) obj, (String) obj2);
                }
            }).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    ShareHelper.this.e((String) obj);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(String str) throws Exception {
        LogUtil.a(f21107a, "doShare: smartHome.share.getOption()： " + String.valueOf(str));
        if (b(str)) {
            c(str);
        } else {
            this.c.a();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String a(String str, String str2) throws Exception {
        JSONObject jSONObject = new JSONObject();
        String a2 = JsSdkUtils.a(this.d, (int) R.drawable.ic_launcher);
        if (!TextUtils.isEmpty(a2)) {
            jSONObject.put("url", str);
            jSONObject.put("title", str2);
            jSONObject.put("imageUrl", a2);
            jSONObject.put("description", str2);
        }
        Log.d(f21107a, "doShare: third web : " + jSONObject.toString());
        return jSONObject.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(String str) throws Exception {
        if (b(str)) {
            c(str);
        } else {
            ToastUtil.a((int) R.string.share_failed);
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str) || Constants.Name.UNDEFINED.equalsIgnoreCase(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("url");
            String string2 = jSONObject.getString("description");
            String string3 = jSONObject.getString("title");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("url");
            String optString = jSONObject.optString("imageUrl");
            String string2 = jSONObject.getString("description");
            String string3 = jSONObject.getString("title");
            Intent intent = new Intent(this.d, CommonShareActivity.class);
            intent.putExtra("ShareTitle", string3);
            if (!TextUtils.isEmpty(string2)) {
                intent.putExtra("ShareContent", string2);
            }
            if (!TextUtils.isEmpty(optString)) {
                if (optString.endsWith(".zip")) {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, optString);
                } else {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_URL_NO_ZIP, optString);
                }
            }
            intent.putExtra(CommonShareActivity.SHARE_URL, string);
            this.d.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (this.c.d()) {
            Log.d(f21107a, "onShareOptionsReady: 手动取消loading，不在分享");
            return;
        }
        this.c.c();
        c(str);
    }

    public void b(WebView webView, String str) {
        this.c.c();
    }

    public void b() {
        this.c.c();
    }
}
