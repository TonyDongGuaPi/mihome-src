package com.xiaomi.smarthome.operation.js_sdk.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.library.common.util.ImageSaveHelper;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonJavaScriptInterface;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.IWebPageAction;
import com.xiaomi.smarthome.operation.js_sdk.location.LocationHelper;
import com.xiaomi.smarthome.operation.js_sdk.network.NetState;
import com.xiaomi.smarthome.operation.js_sdk.promise.JsPromiseAsync;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.ITitleBar;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import com.xiaomi.smarthome.screenshot.Screenshot;
import com.xiaomi.smarthome.screenshot.callback.ScreenshotListener;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class CommonJavaScriptInterface extends BaseJSInterface {
    private static final String c = "CommonJavaScriptInterfa";
    private String d = "{}";
    private long e = -1;

    public CommonJavaScriptInterface(Activity activity, CommonWebView commonWebView) {
        super(activity, commonWebView);
        SystemApi a2 = SystemApi.a();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("os_name", "android");
            jSONObject.put("app_version", a2.e(SHApplication.getAppContext()));
            jSONObject.put("os_version", a2.f());
            jSONObject.put(DTransferConstants.l, a2.e());
            this.d = jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void onShareOptionsReady(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            LogUtil.a(c, "onShareOptionsReady: ");
            JsSdkUtils.a((Runnable) new Runnable(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CommonWebView.this.onShareOptionsReady(this.f$1);
                }
            });
        }
    }

    @JavascriptInterface
    public void setShareButtonEnable(boolean z) {
        ITitleBar titleBarImpl;
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed() && Build.VERSION.SDK_INT >= 19 && (titleBarImpl = commonWebView.getTitleBarImpl()) != null) {
            JsSdkUtils.a((Runnable) new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ITitleBar.this.setShareButtonEnable(this.f$1);
                }
            });
        }
    }

    @JavascriptInterface
    public boolean isLogin() {
        return SHApplication.getStateNotifier().a() == 4;
    }

    @JavascriptInterface
    public String getSettings() {
        return this.d;
    }

    @JavascriptInterface
    public void startLogin() {
        if (SHApplication.getStateNotifier().a() == 4) {
            JsSdkUtils.a((Runnable) new Runnable() {
                public final void run() {
                    CommonJavaScriptInterface.this.b();
                }
            }, 200);
            return;
        }
        final Activity activity = (Activity) this.f21058a.get();
        final CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            LoginApi.a().a((Context) activity, 1, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
                public void a() {
                    JsSdkUtils.a((Runnable) new Runnable(activity, commonWebView) {
                        private final /* synthetic */ Activity f$0;
                        private final /* synthetic */ CommonWebView f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CommonJavaScriptInterface.AnonymousClass1.a(this.f$0, this.f$1);
                        }
                    }, 200);
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ void a(Activity activity, CommonWebView commonWebView) {
                    if (!activity.isDestroyed()) {
                        commonWebView.initCookie();
                        commonWebView.reload();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            commonWebView.initCookie();
            commonWebView.reload();
        }
    }

    @JavascriptInterface
    public boolean openYoupinPage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JsSdkUtils.a((Runnable) new Runnable(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                CommonJavaScriptInterface.a(this.f$0);
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(String str) {
        try {
            UrlDispatchManger.a().c(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void onBackPressed(boolean z) {
        ITitleBar titleBarImpl;
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed() && (titleBarImpl = commonWebView.getTitleBarImpl()) != null) {
            JsSdkUtils.a((Runnable) new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ITitleBar.this.onBackPressed(this.f$1);
                }
            });
        }
    }

    @JavascriptInterface
    public void gotoFeedback() {
        Activity activity = (Activity) this.f21058a.get();
        if (activity != null && !activity.isDestroyed()) {
            if (SHApplication.getStateNotifier().a() != 4) {
                LoginApi.a().a((Context) activity, 1, (LoginApi.LoginCallback) null);
                return;
            }
            Intent intent = new Intent(activity, FeedbackActivity.class);
            intent.putExtra("extra_device_model", FeedbackApi.CHOICENESS);
            activity.startActivity(intent);
        }
    }

    @JavascriptInterface
    public int getTitleBarPadding() {
        return TitleBarUtil.a();
    }

    @JavascriptInterface
    public String getLocale() {
        Locale g = GlobalDynamicSettingManager.a().g();
        if (g == null) {
            g = Locale.getDefault();
        }
        return g.toString();
    }

    @JavascriptInterface
    public void openNewWebView(String str) {
        Activity activity = (Activity) this.f21058a.get();
        if (activity != null && !activity.isDestroyed()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("url");
                String optString2 = jSONObject.optString("title");
                if (!TextUtils.isEmpty(optString)) {
                    Intent intent = new Intent(activity, OperationCommonWebViewActivity.class);
                    intent.putExtra("url", optString);
                    intent.putExtra("title", optString2);
                    activity.startActivity(intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void setOptionButton(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            ITitleBar titleBarImpl = commonWebView.getTitleBarImpl();
            if (titleBarImpl != null) {
                JsSdkUtils.a((Runnable) new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.setOptionButton(this.f$1);
                    }
                });
            }
            Log.d(c, "setOptionButton: " + str);
        }
    }

    @JavascriptInterface
    public void setPopMenu(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            ITitleBar titleBarImpl = commonWebView.getTitleBarImpl();
            if (titleBarImpl != null) {
                JsSdkUtils.a((Runnable) new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.setPopMenu(this.f$1);
                    }
                });
            }
            Log.d(c, "setPopMenu: " + str);
        }
    }

    @JavascriptInterface
    public void setNavigationBar(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            ITitleBar titleBarImpl = commonWebView.getTitleBarImpl();
            if (titleBarImpl != null) {
                JsSdkUtils.a((Runnable) new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.setNavigationBar(this.f$1);
                    }
                });
            }
            Log.d(c, "setNavigationBar: " + str);
        }
    }

    @JavascriptInterface
    public void setNavigationBarLoading(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            ITitleBar titleBarImpl = commonWebView.getTitleBarImpl();
            if (titleBarImpl != null) {
                JsSdkUtils.a((Runnable) new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.setNavigationBarLoading(this.f$1);
                    }
                });
            }
            Log.d(c, "showNavigationBarLoading: ");
        }
    }

    @JavascriptInterface
    public void toNative(String str) {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
        }
    }

    @JavascriptInterface
    public void popWindow(String str) {
        IWebPageAction webPageActionImpl;
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed() && (webPageActionImpl = commonWebView.getWebPageActionImpl()) != null) {
            JsSdkUtils.a((Runnable) new Runnable(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IWebPageAction.this.a(this.f$1);
                }
            });
        }
    }

    @JavascriptInterface
    public void pushWindow(String str) {
        IWebPageAction webPageActionImpl;
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed() && (webPageActionImpl = commonWebView.getWebPageActionImpl()) != null) {
            JsSdkUtils.a((Runnable) new Runnable(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IWebPageAction.this.b(this.f$1);
                }
            });
        }
    }

    @JavascriptInterface
    public String getNetworkType() {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity == null || commonWebView == null || activity.isDestroyed()) {
            return NetState.f21095a.c().toString();
        }
        return commonWebView.getNetworkHelper().b().c().toString();
    }

    @JavascriptInterface
    public boolean onShare(String str, String str2, String str3, String str4) {
        Activity activity = (Activity) this.f21058a.get();
        if (activity == null || activity.isDestroyed() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        CoreApi.a().s();
        Intent intent = new Intent(activity, CommonShareActivity.class);
        intent.putExtra("ShareTitle", str);
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra("ShareContent", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            if (str4.endsWith(".zip")) {
                intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, str4);
            } else {
                intent.putExtra(CommonShareActivity.SHARE_IMAGE_URL_NO_ZIP, str4);
            }
        }
        intent.putExtra(CommonShareActivity.SHARE_URL, str2);
        activity.startActivity(intent);
        return true;
    }

    @JavascriptInterface
    public void screenshot() {
        Activity activity = (Activity) this.f21058a.get();
        if (activity != null && !activity.isDestroyed() && System.currentTimeMillis() - this.e > 500) {
            this.e = System.currentTimeMillis();
            JsSdkUtils.a((Runnable) new Runnable() {
                public final void run() {
                    CommonJavaScriptInterface.this.a();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        final Activity activity = (Activity) this.f21058a.get();
        final CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null && !activity.isDestroyed()) {
            final File file = new File(ImageSaveHelper.b, "screenshot.jpg");
            new Screenshot.Builder(activity).a((View) commonWebView).a(true).a(file.getAbsolutePath()).a((ScreenshotListener) new ScreenshotListener() {
                public void a(Bitmap bitmap, boolean z) {
                    LogUtil.b("CommonWebViewActivity", "onSuccess");
                    ToastUtil.a((CharSequence) activity.getString(R.string.image_saved) + ImageSaveHelper.f18682a + activity.getString(R.string.file_directory), 1);
                    try {
                        ImageSaveHelper.a(file);
                        commonWebView.loadUrl("javascript:_hideImgDownloader()");
                        if (bitmap != null && !bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void a(int i, String str) {
                    LogUtil.b("CommonWebViewActivity", "onFail = " + str);
                    ToastUtil.a((int) R.string.save_fail);
                }

                public void a() {
                    LogUtil.b("CommonWebViewActivity", "onPreStart");
                }
            }).a().a();
        }
    }

    @JavascriptInterface
    public void getLocation(final String str) {
        JsSdkUtils.c((WebView) this.b.get(), "getLocationStart: " + new Date().toLocaleString());
        new JsPromiseAsync(this.b, this.f21058a) {
            /* access modifiers changed from: protected */
            @SuppressLint({"CheckResult"})
            public void a(final JsPromiseAsync.PromiseCallback promiseCallback) {
                LocationHelper.a().b(str).subscribe(new Consumer<LocationHelper.WebLocation>() {
                    /* renamed from: a */
                    public void accept(LocationHelper.WebLocation webLocation) throws Exception {
                        JsSdkUtils.c((WebView) AnonymousClass3.this.c.get(), "getLocationSuccess: " + new Date().toLocaleString());
                        promiseCallback.a(webLocation.toString());
                    }
                }, new Consumer<Throwable>() {
                    /* renamed from: a */
                    public void accept(Throwable th) throws Exception {
                        JsSdkUtils.c((WebView) AnonymousClass3.this.c.get(), "getLocationFail: " + new Date().toLocaleString());
                        promiseCallback.b(th.getLocalizedMessage());
                    }
                });
            }

            public List<String> a() {
                return Collections.singletonList("getLocation");
            }
        }.then("smartHome.dispatchEvent", "smartHome.dispatchEvent");
    }
}
