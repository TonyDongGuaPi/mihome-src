package com.youpin.weex.app.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.alipay.android.phone.a.a.a;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.IWXDebugProxy;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.ui.component.WXWeb;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.youpin.weex.app.R;
import com.youpin.weex.app.WXAnalyzerDelegate;
import com.youpin.weex.app.common.BugReportUtil;
import com.youpin.weex.app.common.TimeManager;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.common.WeexCacheManager;
import com.youpin.weex.app.common.WeexConstant;
import com.youpin.weex.app.util.CommonUtils;
import com.youpin.weex.app.util.OpenUtils;
import com.youpin.weex.app.widget.CommonErrorView;
import com.youpin.weex.app.widget.CommonLoadingView;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import wendu.webviewjavascriptbridge.WVJBWebView;

public abstract class AbsWeexFragment extends Fragment implements Handler.Callback, View.OnClickListener, IWXRenderListener, WXSDKInstance.NestedInstanceInterceptor {
    private static final String D = "tab_selected_index_changed";

    /* renamed from: a  reason: collision with root package name */
    static final String f2526a = "WeexFragment";
    public static final String b = "com.xiaomi.youpin.action.on_logout";
    public static final String c = "com.xiaomi.youpin.action.on_login";
    public static final String d = "action_on_login_success";
    public static final String e = "action_on_logout";
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int j = 4;
    /* access modifiers changed from: private */
    public boolean A = false;
    private String B;
    private WXAnalyzerDelegate C;
    private boolean E = false;
    Bundle f;
    private ViewGroup k;
    /* access modifiers changed from: private */
    public WXSDKInstance l;
    /* access modifiers changed from: private */
    public Handler m = new Handler(Looper.getMainLooper(), this);
    private BroadcastReceiver n;
    /* access modifiers changed from: private */
    public WxReloadListener o;
    /* access modifiers changed from: private */
    public WxRefreshListener p;
    /* access modifiers changed from: private */
    public String q;
    private View r;
    /* access modifiers changed from: private */
    public WVJBWebView s;
    private String t;
    private CommonLoadingView u;
    private CommonErrorView v;
    /* access modifiers changed from: private */
    public String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public String z;

    public interface WxRefreshListener {
        void a();
    }

    public interface WxReloadListener {
        void a();
    }

    /* access modifiers changed from: private */
    public String d(String str) {
        return str == null ? "" : str;
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void f() {
        g();
        this.l = new WXSDKInstance(getContext());
        this.l.registerRenderListener(this);
    }

    /* access modifiers changed from: protected */
    public void g() {
        if (this.l != null) {
            this.l.registerRenderListener((IWXRenderListener) null);
            this.l.destroy();
            this.l = null;
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a();
        setHasOptionsMenu(false);
        this.C = new WXAnalyzerDelegate(getContext());
        this.C.a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtils.d(f2526a, "onCreateView");
        c();
        if (this.r != null) {
            return this.r;
        }
        this.r = layoutInflater.inflate(R.layout.fragment_wxpage, viewGroup, false);
        this.k = (FrameLayout) this.r.findViewById(R.id.container);
        this.u = (CommonLoadingView) this.r.findViewById(R.id.loading);
        String str = null;
        this.u.setBackground((Drawable) null);
        this.s = (WVJBWebView) this.r.findViewById(R.id.webview);
        this.v = (CommonErrorView) this.r.findViewById(R.id.common_error_view);
        TitleBarUtil.a((View) this.v);
        this.v.setOnRetryClickListener(this);
        o();
        this.f = getArguments();
        if (this.f != null) {
            str = this.f.getString("bundleUrl");
        }
        this.q = str;
        return this.r;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        LogUtils.d(f2526a, "onViewCreated" + this);
        this.m.post(new Runnable() {
            public void run() {
                WeexConstant.a(AbsWeexFragment.this.getActivity() == null ? null : AbsWeexFragment.this.getActivity().getWindow());
            }
        });
        if (e()) {
            LogUtils.d(f2526a, "延时调用");
            this.m.sendEmptyMessageDelayed(1, 500);
            return;
        }
        this.m.sendEmptyMessage(1);
    }

    public void setUserVisibleHint(boolean z2) {
        super.setUserVisibleHint(z2);
        j();
        if (z2 && e() && this.m.hasMessages(1)) {
            LogUtils.d(f2526a, "存在延时任务，立即调用！");
            this.m.removeMessages(1);
            b(this.q);
        }
    }

    private void j() {
        if (getUserVisibleHint() && !TextUtils.isEmpty(this.q) && this.A && this.q.contains("&maintab=true")) {
            HashMap hashMap = new HashMap();
            hashMap.put("curPage", this.q);
            if (this.l != null) {
                this.l.fireGlobalEventCallback(D, hashMap);
                this.E = true;
                LogUtils.d("CurrentPage", " 发送成功: ***   " + this.q);
            }
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.l != null) {
            this.l.onActivityCreate();
        }
        a(this.n);
    }

    public void onStart() {
        super.onStart();
        LogUtils.d(f2526a, "onStart");
        if (this.l != null) {
            this.l.onActivityStart();
        }
        if (this.C != null) {
            this.C.b();
        }
        TitleBarUtil.a(getActivity().getWindow());
    }

    public void onResume() {
        super.onResume();
        LogUtils.d(f2526a, "onResume");
        if (this.l != null && this.A) {
            this.l.onActivityResume();
        }
        if (this.C != null) {
            this.C.c();
        }
        WXAppStoreApiManager.b().a((Activity) getActivity());
    }

    public void onPause() {
        super.onPause();
        LogUtils.d(f2526a, "onPause");
        if (this.l != null) {
            this.l.onActivityPause();
        }
        if (this.C != null) {
            this.C.d();
        }
        WXAppStoreApiManager.b().a((Activity) null);
    }

    public void onStop() {
        super.onStop();
        if (this.l != null) {
            this.l.onActivityStop();
        }
        if (this.C != null) {
            this.C.e();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(f2526a, "onDestroyView");
        this.m.removeMessages(1);
        d();
        if (this.u.isAnimationLoading()) {
            q();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(f2526a, ActivityInfo.TYPE_STR_ONDESTROY);
        b();
        if (this.l != null) {
            this.l.registerRenderListener((IWXRenderListener) null);
            this.l.onActivityDestroy();
        }
        h();
        if (this.C != null) {
            this.C.f();
        }
        WeexCacheManager.a().c();
        TimeManager.a().c();
    }

    public void a(MotionEvent motionEvent) {
        if (this.C != null) {
            this.C.a(motionEvent);
        }
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        return this.C != null && this.C.a(i2, keyEvent);
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.l != null) {
            this.l.onRequestPermissionsResult(i2, strArr, iArr);
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    public boolean handleMessage(Message message) {
        String str;
        switch (message.what) {
            case 1:
                b(this.q);
                return true;
            case 2:
                if (this.A) {
                    str = "YPWeexSDK JSRuntimeException";
                } else {
                    str = this.y ? "YPWeexSDK BundleDownloadFailed" : "YPWeexSDK BundleRenderError";
                }
                BugReportUtil.a(this.z, this.w, this.x, this.q, str, (String) message.obj);
                return true;
            case 3:
                BugReportUtil.a(this.z, this.w, this.x, this.q, "YPWeexSDK JSDownGradeException", (String) message.obj);
                return true;
            case 4:
                BugReportUtil.a(this.z, this.w, this.x, this.q, "YPWeexSDK WebViewLoadFailed", (String) message.obj);
                return true;
            default:
                return true;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.common_error_retry && !TextUtils.isEmpty(this.q)) {
            o();
            b(this.q);
        }
    }

    public void onViewCreated(WXSDKInstance wXSDKInstance, View view) {
        this.E = false;
        View a2 = this.C != null ? this.C.a(wXSDKInstance, view) : null;
        if (a2 == null) {
            a2 = view;
        }
        if (this.k != null) {
            this.k.removeAllViews();
            this.k.addView(a2);
        }
    }

    public void onRenderSuccess(WXSDKInstance wXSDKInstance, int i2, int i3) {
        LogUtils.d(f2526a, "onRenderSuccess");
        if (this.l != null && !this.A) {
            this.l.onActivityResume();
        }
        if (this.C != null) {
            this.C.a(wXSDKInstance);
        }
        TimeManager.a().c("weex_render");
        TimeManager.a().a("render_type", "weex");
        TimeManager.a().b();
        q();
        this.A = true;
        if (!this.E) {
            j();
        }
    }

    public void onRefreshSuccess(WXSDKInstance wXSDKInstance, int i2, int i3) {
        LogUtils.d(f2526a, "onRefreshSuccess");
    }

    public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
        int i2;
        LogUtils.d(f2526a, "onException");
        if (this.C != null) {
            this.C.a(wXSDKInstance, str, str2);
        }
        TimeManager.a().c("weex_render");
        q();
        LogUtils.e(f2526a, "onException", wXSDKInstance, this.l, str, str2);
        if (!TextUtils.isEmpty(this.z)) {
            WeexCacheManager.a().a(this.z);
        }
        if (str != null && str.contains("|")) {
            String[] split = str.split(PaymentOptionsDecoder.pipeSeparator);
            if (split.length == 2) {
                try {
                    i2 = Integer.parseInt(split[1]);
                } catch (NumberFormatException unused) {
                    i2 = 0;
                }
                if (wXSDKInstance == this.l && "1".equals(split[0]) && i2 > 1000 && i2 < 1005) {
                    l();
                    Message obtainMessage = this.m.obtainMessage(3);
                    obtainMessage.obj = str + str2;
                    obtainMessage.sendToTarget();
                    return;
                }
            }
        }
        Message obtainMessage2 = this.m.obtainMessage(2);
        obtainMessage2.obj = str + str2;
        obtainMessage2.sendToTarget();
        if (!this.A) {
            if (TextUtils.isEmpty(this.x)) {
                this.x = this.q;
                l();
                return;
            }
            l();
        }
    }

    public void onCreateNestInstance(WXSDKInstance wXSDKInstance, NestedContainer nestedContainer) {
        Log.d(f2526a, "Nested Instance created.");
    }

    public void h() {
        if (this.n != null) {
            LocalBroadcastManager.getInstance(getContext().getApplicationContext()).unregisterReceiver(this.n);
            this.n = null;
        }
        a((WxReloadListener) null);
        a((WxRefreshListener) null);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            broadcastReceiver = new DefaultBroadcastReceiver();
        }
        this.n = broadcastReceiver;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH);
        intentFilter.addAction(WXSDKEngine.JS_FRAMEWORK_RELOAD);
        intentFilter.addAction("com.xiaomi.youpin.action.on_login");
        intentFilter.addAction("com.xiaomi.youpin.action.on_logout");
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(getContext().getApplicationContext()).registerReceiver(this.n, intentFilter);
        if (this.o == null) {
            a((WxReloadListener) new WxReloadListener() {
                public void a() {
                    AbsWeexFragment.this.f();
                    AbsWeexFragment.this.k();
                }
            });
        }
        if (this.p == null) {
            a((WxRefreshListener) new WxRefreshListener() {
                public void a() {
                    AbsWeexFragment.this.f();
                    AbsWeexFragment.this.k();
                }
            });
        }
    }

    public void a(WxReloadListener wxReloadListener) {
        this.o = wxReloadListener;
    }

    public void a(WxRefreshListener wxRefreshListener) {
        this.p = wxRefreshListener;
    }

    /* access modifiers changed from: private */
    public void k() {
        i();
        c(this.w);
    }

    /* access modifiers changed from: private */
    public void l() {
        this.k.setVisibility(8);
        this.s.getSettings().setUserAgentString(this.B);
        this.s.setVisibility(0);
        this.s.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                a(i + str);
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }

            private void a(String str) {
                AbsWeexFragment.this.n();
                Message obtainMessage = AbsWeexFragment.this.m.obtainMessage(4);
                obtainMessage.obj = str;
                obtainMessage.sendToTarget();
            }
        });
        this.s.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (i == 100) {
                    TimeManager.a().c("webview_load");
                    TimeManager.a().a("render_type", "html");
                    TimeManager.a().b();
                }
            }
        });
        this.s.registerHandler("openUrl", new WVJBWebView.WVJBHandler() {
            public void a(Object obj, WVJBWebView.WVJBResponseCallback wVJBResponseCallback) {
                LogUtils.d(AbsWeexFragment.f2526a, obj.toString());
                try {
                    String optString = ((JSONObject) obj).optString("url", (String) null);
                    JSONObject jSONObject = new JSONObject();
                    if (TextUtils.isEmpty(optString)) {
                        jSONObject.put("result", "failed");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("msg", "url is null");
                        jSONObject.put("data", jSONObject2);
                        wVJBResponseCallback.a(jSONObject);
                        return;
                    }
                    WXAppStoreApiManager.b().c().a(optString);
                    jSONObject.put("result", "success");
                    wVJBResponseCallback.a(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        this.s.registerHandler(WXWeb.GO_BACK, new WVJBWebView.WVJBHandler() {
            public void a(Object obj, WVJBWebView.WVJBResponseCallback wVJBResponseCallback) {
                JSONObject jSONObject = new JSONObject();
                if (AbsWeexFragment.this.getActivity() != null) {
                    try {
                        jSONObject.put("result", "success");
                        if (wVJBResponseCallback != null) {
                            wVJBResponseCallback.a(jSONObject);
                        }
                        AbsWeexFragment.this.getActivity().finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        jSONObject.put("result", "failed");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("msg", "getActivity() null");
                        jSONObject.put("data", jSONObject2);
                        if (wVJBResponseCallback != null) {
                            wVJBResponseCallback.a(jSONObject);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.s.registerHandler("hybridWebReport", new WVJBWebView.WVJBHandler() {
            public void a(Object obj, final WVJBWebView.WVJBResponseCallback wVJBResponseCallback) {
                LogUtils.d(AbsWeexFragment.f2526a, obj.toString());
                JSONObject jSONObject = (JSONObject) obj;
                try {
                    jSONObject.put("app_key", StoreApiManager.a().b().d());
                    jSONObject.put("app_version", AppInfo.f());
                    jSONObject.put("weex_version", OpenUtils.e);
                    jSONObject.put("platform", a.f813a);
                    jSONObject.put("device_id", AppIdManager.a().c());
                    jSONObject.put("channel", StoreApiManager.a().b().c());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TimeManager.a().a(jSONObject, (ICallback) new ICallback() {
                    public void callback(Map map) {
                        int intValue = ((Integer) map.get("result")).intValue();
                        JSONObject jSONObject = new JSONObject();
                        if (intValue == 0) {
                            try {
                                jSONObject.put("result", "success");
                                wVJBResponseCallback.a(jSONObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                jSONObject.put("result", "failed");
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("msg", map.get("msg"));
                                jSONObject.put("data", jSONObject2);
                                wVJBResponseCallback.a(jSONObject);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
        TimeManager.a().b("webview_load");
        this.s.loadUrl(this.x);
    }

    private void m() {
        if (this.t == null) {
            try {
                InputStream open = getContext().getAssets().open("WebViewJavascriptBridge.js");
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                open.close();
                this.t = new String(bArr);
                this.s.evaluateJavascript(this.t);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            this.s.evaluateJavascript(this.t);
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        if (this.u.isAnimationLoading()) {
            q();
        }
        this.v.setVisibility(0);
    }

    private void o() {
        this.v.setVisibility(8);
    }

    private void p() {
        this.u.setVisibility(0);
        this.u.startAnimation();
    }

    private void q() {
        this.u.stopAnimation();
        this.u.setVisibility(8);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new String(Base64.decode(str, 0));
    }

    /* access modifiers changed from: protected */
    public void i() {
        if (!this.u.isAnimationLoading()) {
            p();
        }
    }

    private void b(String str) {
        String string;
        this.B = UserAgent.d();
        if (TextUtils.isEmpty(str)) {
            n();
            return;
        }
        Uri parse = Uri.parse(str);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared_pref", 0);
        String str2 = "";
        if (!(sharedPreferences == null || (string = sharedPreferences.getString("Dev_WeexPort", "")) == null || !(string instanceof String))) {
            str2 = string;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.w = str2;
            LogUtils.d(f2526a, "bundleUrl *****   " + this.w);
            k();
            return;
        }
        int a2 = OpenUtils.a(parse);
        if (a2 == 0) {
            String queryParameter = parse.getQueryParameter("_wx_tpl");
            String queryParameter2 = parse.getQueryParameter(OpenUtils.d);
            this.w = a(queryParameter);
            this.x = a(queryParameter2);
            if (TextUtils.isEmpty(this.w)) {
                this.w = "";
                BugReportUtil.a(this.z, this.w, this.x, this.q, "YPWeexSDK decodeError:", queryParameter);
            }
            k();
        } else if (a2 == 1) {
            TimeManager.a().a("weex_open_time");
            TimeManager.a().a("miotweex", this.B);
            TimeManager.a().a("page_url", str);
            String queryParameter3 = parse.getQueryParameter("pageid");
            if (!TextUtils.isEmpty(queryParameter3)) {
                TimeManager.a().a("page_id", queryParameter3);
            }
            if (StoreApiManager.a().b() != null) {
                TimeManager.a().a("app_key", StoreApiManager.a().b().d());
                TimeManager.a().a("channel", StoreApiManager.a().b().c());
            }
            TimeManager.a().a("app_version", AppInfo.f());
            TimeManager.a().a("weex_version", OpenUtils.e);
            TimeManager.a().a("platform", a.f813a);
            TimeManager.a().a("device_id", AppIdManager.a().c());
            WeexCacheManager.a().a(parse, new WeexCacheManager.LoadCallBackListener() {
                public void a(String str, String str2, final String str3, final String str4, String str5, String str6, boolean z) {
                    LogUtils.d(AbsWeexFragment.f2526a, "onSuccessLoad");
                    String unused = AbsWeexFragment.this.z = str;
                    String unused2 = AbsWeexFragment.this.w = str4;
                    String unused3 = AbsWeexFragment.this.x = str5;
                    boolean unused4 = AbsWeexFragment.this.y = z;
                    TimeManager.a().a("page_name", str);
                    TimeManager.a().a("weex_url", str4);
                    TimeManager.a().a("html_url", str5);
                    AbsWeexFragment.this.m.post(new Runnable() {
                        public void run() {
                            FragmentActivity activity = AbsWeexFragment.this.getActivity();
                            if (activity != null && !activity.isFinishing() && !AbsWeexFragment.this.isDetached()) {
                                AbsWeexFragment.this.a(str3, str4);
                            }
                        }
                    });
                }

                public void a(final String str) {
                    AbsWeexFragment.this.m.post(new Runnable() {
                        public void run() {
                            LogUtils.e(AbsWeexFragment.f2526a, "load error:", str);
                            BugReportUtil.a(AbsWeexFragment.this.z, AbsWeexFragment.this.w, AbsWeexFragment.this.x, AbsWeexFragment.this.q, "YPWeexSDK onFailLoad", str);
                            String unused = AbsWeexFragment.this.x = AbsWeexFragment.this.q;
                            FragmentActivity activity = AbsWeexFragment.this.getActivity();
                            if (activity != null && !activity.isFinishing() && !AbsWeexFragment.this.isDetached()) {
                                AbsWeexFragment.this.l();
                            }
                        }
                    });
                }

                public void a() {
                    AbsWeexFragment.this.i();
                }
            });
        }
    }

    private void c(String str) {
        LogUtils.d(f2526a, "renderPageByURL, " + str);
        q();
        if (this.l != null && !TextUtils.isEmpty(str)) {
            if (AppInfo.n()) {
                CommonUtils.a(this.k, new RuntimeException("Can't render page, container is null"));
            }
            if (this.k != null) {
                HashMap hashMap = new HashMap(WeexConstant.f2515a);
                hashMap.put("bundleUrl", str);
                hashMap.put("originUrl", this.q);
                this.l.renderByUrl("FRAGMENT", str, hashMap, (String) null, WXRenderStrategy.APPEND_ASYNC);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        LogUtils.d(f2526a, "renderPageByJSContent," + this.l);
        q();
        if (this.l != null) {
            CommonUtils.a(this.k, new RuntimeException("Can't render page, container is null"));
            HashMap hashMap = new HashMap(WeexConstant.f2515a);
            hashMap.put("bundleUrl", str2);
            hashMap.put("originUrl", this.q);
            boolean z2 = false;
            if (!(StoreApiManager.a() == null || StoreApiManager.a().b() == null)) {
                z2 = StoreApiManager.a().b().f();
            }
            hashMap.put("Staging", Boolean.valueOf(z2));
            TimeManager.a().b("weex_render");
            this.l.render("FRAGMENT", str, (Map<String, Object>) hashMap, (String) null, WXRenderStrategy.APPEND_ASYNC);
        }
    }

    public class DefaultBroadcastReceiver extends BroadcastReceiver {
        public DefaultBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH.equals(action)) {
                if (AbsWeexFragment.this.p != null) {
                    AbsWeexFragment.this.p.a();
                }
            } else if (WXSDKEngine.JS_FRAMEWORK_RELOAD.equals(action)) {
                if (AbsWeexFragment.this.o != null) {
                    AbsWeexFragment.this.o.a();
                }
            } else if (TextUtils.equals("com.xiaomi.youpin.action.on_login", action) || TextUtils.equals("action_on_login_success", action)) {
                if (AbsWeexFragment.this.A && AbsWeexFragment.this.l != null) {
                    WXAppStoreApiManager.b().c().c(new ICallback() {
                        public void callback(Map map) {
                            HashMap hashMap = new HashMap();
                            if (map != null && map.size() > 0) {
                                hashMap.put("mode", AbsWeexFragment.this.d((String) map.get("mode")));
                                hashMap.put("uid", AbsWeexFragment.this.d((String) map.get("uid")));
                                hashMap.put("isLogin", true);
                                hashMap.put("icon", AbsWeexFragment.this.d((String) map.get("icon")));
                                hashMap.put(FamilyMemberData.d, AbsWeexFragment.this.d((String) map.get(FamilyMemberData.d)));
                            }
                            AbsWeexFragment.this.l.fireGlobalEventCallback("user_login_status_change", hashMap);
                        }
                    });
                } else if (AbsWeexFragment.this.s.getVisibility() == 0) {
                    AbsWeexFragment.this.s.reload();
                }
            } else if (!TextUtils.equals("com.xiaomi.youpin.action.on_logout", action) && !TextUtils.equals("action_on_logout", action)) {
            } else {
                if (AbsWeexFragment.this.A && AbsWeexFragment.this.l != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("isLogin", false);
                    AbsWeexFragment.this.l.fireGlobalEventCallback("user_login_status_change", hashMap);
                } else if (AbsWeexFragment.this.s.getVisibility() == 0) {
                    AbsWeexFragment.this.s.reload();
                }
            }
        }
    }
}
