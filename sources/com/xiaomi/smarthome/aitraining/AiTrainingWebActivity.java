package com.xiaomi.smarthome.aitraining;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AiTrainingWebActivity extends BaseActivity implements View.OnClickListener {
    public static final String AICLIENTID = "aiclientid";
    public static final String AIMIOTCLIENTID = "aimiotclientid";
    public static final String AIVERSION = "aiversion";
    public static final String CLIENTID = "clientid";
    public static final String DID = "did";
    public static final String MIOT_SID = "i.ai.mi.com";
    public static final String SHOW_CLOSE_BTN = "showCloseBtn";
    public static final String TITLE = "title";
    public static final String URL = "url";

    /* renamed from: a  reason: collision with root package name */
    private static final String f13663a = "WebActivity";
    private static final String b = "recoder_clockmyk1.mp3";
    private static final int d = 99;
    private static final int e = 100;
    private static final int f = 101;
    private static final int g = 102;
    private static final int h = 103;
    private static final long i = 3599000;
    /* access modifiers changed from: private */
    public ProgressBar A;
    private final String c = "mico://audiorecord?id=''&callback=saveAudio";
    /* access modifiers changed from: private */
    public AudioManager j;
    /* access modifiers changed from: private */
    public WebView k;
    private LinearLayout l;
    private PopupWindow m;
    /* access modifiers changed from: private */
    public int n = 99;
    /* access modifiers changed from: private */
    public ImageView o;
    /* access modifiers changed from: private */
    public View p;
    /* access modifiers changed from: private */
    public View q;
    private Chronometer r;
    private LinearLayout s;
    private LinearLayout t;
    private AnimatorSet u = new AnimatorSet();
    /* access modifiers changed from: private */
    public String v;
    /* access modifiers changed from: private */
    public String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public String y;
    /* access modifiers changed from: private */
    public MediaManager z;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.n = message.what;
        switch (message.what) {
            case 99:
                if (this.o != null) {
                    this.o.setImageDrawable(getResources().getDrawable(R.drawable.training_skill_start));
                }
                if (this.p != null && this.q != null) {
                    this.p.setVisibility(8);
                    this.q.setVisibility(8);
                    return;
                }
                return;
            case 100:
                e();
                return;
            case 101:
                f();
                return;
            case 102:
                g();
                return;
            case 103:
                h();
                return;
            default:
                return;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.comm_web_activity);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.title_bar);
        String stringExtra = getIntent().getStringExtra("title");
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        textView.setText(stringExtra);
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_return_btn);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        this.A = (ProgressBar) findViewById(R.id.loading_progress);
        if (getIntent().getBooleanExtra(SHOW_CLOSE_BTN, true)) {
            ImageView imageView2 = new ImageView(this);
            imageView2.setId(R.id.module_a_3_close_btn_title);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageView.getLayoutParams());
            layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.std_titlebar_height) + getResources().getDimensionPixelOffset(R.dimen.std_titlebar_margin_left_right);
            layoutParams.gravity = 16;
            frameLayout.addView(imageView2, layoutParams);
            imageView.setImageResource(R.drawable.std_tittlebar_main_device_back);
            imageView2.setImageResource(R.drawable.std_tittlebar_main_device_back2);
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(this);
        }
        this.k = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        if (!intent.hasExtra("url")) {
            finish();
            return;
        }
        String stringExtra2 = intent.getStringExtra("url");
        if (Build.VERSION.SDK_INT >= 19) {
            WebView webView = this.k;
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = this.k.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        this.k.addJavascriptInterface(new JavaScripdtObject(textView), "android");
        this.k.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                AiTrainingWebActivity.this.A.setProgress(i);
                if (i >= 80) {
                    webView.loadUrl("javascript:window.android.setTitle(document.title)");
                }
            }
        });
        this.k.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null || !"mico://audiorecord?id=''&callback=saveAudio".equalsIgnoreCase(str)) {
                    super.shouldOverrideUrlLoading(webView, str);
                    return false;
                }
                if (XmPluginHostApi.instance().getApiLevel() < 75) {
                    AiTrainingWebActivity.this.a();
                } else {
                    XmPluginHostApi.instance().checkAndRequestPermisson(AiTrainingWebActivity.this, true, new Callback<List<String>>() {
                        /* renamed from: a */
                        public void onSuccess(List<String> list) {
                            LogUtil.c("WebActivity", "onSuccess checkAndRequestPermisson");
                            AiTrainingWebActivity.this.k.post(new Runnable() {
                                public void run() {
                                    AiTrainingWebActivity.this.a();
                                }
                            });
                        }

                        public void onFailure(int i, String str) {
                            LogUtil.c("WebActivity", "onFailure checkAndRequestPermisson");
                        }
                    }, "android.permission.RECORD_AUDIO");
                }
                return true;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                AiTrainingWebActivity.this.A.setVisibility(8);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                AiTrainingWebActivity.this.A.setVisibility(0);
            }

            public void onPageFinished(WebView webView, String str) {
                AiTrainingWebActivity.this.A.setVisibility(8);
                LogUtil.c("WebActivity", "WEB_URL  loading URL finish cookie= " + CookieManager.getInstance().getCookie(str));
                super.onPageFinished(webView, str);
                webView.loadUrl("javascript:" + (("var newscript = document.createElement(\"script\");" + "newscript.text = function openPag(option){newPage.openPage(JSON.stringify(option));};var thirdPartConfig = { headerBgColor: '#F44D55', thirdPart: 'yeelight'};window.thirdPartConfig = thirdPartConfig;") + "document.body.appendChild(newscript);"));
            }

            @SuppressLint({"NewApi"})
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                if (webResourceRequest == null || webResourceResponse == null) {
                    LogUtil.c("WebActivity", "WEB_URL  loading URL httpError=  request" + webResourceRequest + " errorResponse " + webResourceResponse);
                    return;
                }
                LogUtil.c("WebActivity", "WEB_URL  loading URL httpError= " + webResourceRequest.getUrl() + " " + webResourceRequest.getRequestHeaders() + " \n " + webResourceResponse.getStatusCode() + "  " + webResourceResponse.getResponseHeaders());
            }
        });
        removeCookie(this);
        synCookies(this, stringExtra2, "ai_miot_did=" + intent.getStringExtra("did"));
        synCookies(this, stringExtra2, "ai_client_id=" + intent.getStringExtra(AICLIENTID));
        synCookies(this, stringExtra2, "ai_miot_client_id=" + intent.getStringExtra(AIMIOTCLIENTID));
        synCookies(this, stringExtra2, "clientID=" + intent.getStringExtra(CLIENTID));
        try {
            synCookies(this, stringExtra2, "ai_version=" + intent.getStringExtra(AIVERSION));
            a(stringExtra2);
        } catch (NoSuchMethodError e2) {
            LogUtil.b("WebActivity", Log.getStackTraceString(e2));
            synCookies(this, stringExtra2, "ai_version=audio");
            this.k.loadUrl(stringExtra2);
        }
    }

    private void a(final String str) {
        XmPluginHostApi.instance().getServiceToken("i.ai.mi.com", new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("WebActivity", "jsonToken: " + jSONObject.toString());
                String unused = AiTrainingWebActivity.this.v = jSONObject.optString("serviceToken");
                String unused2 = AiTrainingWebActivity.this.w = jSONObject.optString("cUserId");
                String unused3 = AiTrainingWebActivity.this.x = jSONObject.optString("ph");
                String unused4 = AiTrainingWebActivity.this.y = jSONObject.optString(IAccountProvider.d);
                try {
                    if (AiTrainingWebActivity.this.x.contains(",")) {
                        String unused5 = AiTrainingWebActivity.this.x = AiTrainingWebActivity.this.x.split(",")[1];
                    }
                    if (AiTrainingWebActivity.this.y.contains(",")) {
                        String unused6 = AiTrainingWebActivity.this.y = AiTrainingWebActivity.this.y.split(",")[1];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AiTrainingWebActivity aiTrainingWebActivity = AiTrainingWebActivity.this;
                Context context = AiTrainingWebActivity.this.getContext();
                String str = str;
                aiTrainingWebActivity.synCookies(context, str, "serviceToken=" + AiTrainingWebActivity.this.v);
                AiTrainingWebActivity aiTrainingWebActivity2 = AiTrainingWebActivity.this;
                Context context2 = AiTrainingWebActivity.this.getContext();
                String str2 = str;
                aiTrainingWebActivity2.synCookies(context2, str2, "cUserId=" + AiTrainingWebActivity.this.w);
                AiTrainingWebActivity aiTrainingWebActivity3 = AiTrainingWebActivity.this;
                Context context3 = AiTrainingWebActivity.this.getContext();
                String str3 = str;
                aiTrainingWebActivity3.synCookies(context3, str3, "i.ai.mi.com_ph=" + AiTrainingWebActivity.this.x);
                AiTrainingWebActivity aiTrainingWebActivity4 = AiTrainingWebActivity.this;
                Context context4 = AiTrainingWebActivity.this.getContext();
                String str4 = str;
                aiTrainingWebActivity4.synCookies(context4, str4, "i.ai.mi.com_slh=" + AiTrainingWebActivity.this.y);
                AiTrainingWebActivity.this.k.loadUrl(str);
            }

            public void onFailure(int i, String str) {
                LogUtil.c("WebActivity", "jsonToken: onFailure " + str);
            }
        });
    }

    public void synCookies(Context context, String str, String str2) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }

    public static void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public void onResume() {
        this.k.onResume();
        super.onResume();
    }

    public void onPause() {
        this.k.onPause();
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.k, (Object[]) null);
            this.k.loadUrl("");
        } catch (Exception unused) {
        }
        this.u.cancel();
        ViewParent parent = this.k.getParent();
        this.k.removeAllViews();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.k);
        }
        this.k.destroy();
        this.k = null;
    }

    public void onBackPressed() {
        b();
        if (this.k == null || !this.k.canGoBack()) {
            super.onBackPressed();
        } else {
            this.k.goBack();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.m == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_training_audio_record, (ViewGroup) null, false);
            inflate.findViewById(R.id.ll_btn_record_bg).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            this.p = inflate.findViewById(R.id.img_dismiss_pop);
            this.s = (LinearLayout) inflate.findViewById(R.id.tr_left_anim);
            this.t = (LinearLayout) inflate.findViewById(R.id.tr_right_anim);
            int i2 = (int) (getResources().getDisplayMetrics().density * 2.0f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, (int) (getResources().getDisplayMetrics().density * 13.0f));
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            while (i3 < 9) {
                final TextView textView = new TextView(getContext());
                final TextView textView2 = new TextView(getContext());
                float random = (float) (Math.random() + 0.3d);
                if (random >= 1.0f) {
                    double d2 = (double) random;
                    Double.isNaN(d2);
                    random = (float) (d2 * 0.3d);
                }
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, random});
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        textView.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{1.0f, random});
                ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        textView2.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                i3++;
                long j2 = (long) (i3 * 150);
                ofFloat.setDuration(j2);
                ofFloat.setRepeatMode(1);
                ofFloat.setRepeatCount(-1);
                ofFloat2.setDuration(j2);
                ofFloat2.setRepeatMode(1);
                ofFloat2.setRepeatCount(-1);
                layoutParams.rightMargin = i2;
                textView.setLayoutParams(layoutParams);
                textView2.setLayoutParams(layoutParams);
                textView.setBackgroundColor(getResources().getColor(R.color.black_50_transparent));
                textView2.setBackgroundColor(getResources().getColor(R.color.black_50_transparent));
                arrayList.add(ofFloat);
                arrayList.add(ofFloat2);
                this.s.addView(textView);
                this.t.addView(textView2);
            }
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            this.u.playTogether(arrayList);
            this.p.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AiTrainingWebActivity.this.b();
                }
            });
            this.q = inflate.findViewById(R.id.img_upload_audio);
            this.q.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    try {
                        new UploadFileHelper().a(AiTrainingWebActivity.this.k, AiTrainingWebActivity.this.getContext(), new File(AiTrainingWebActivity.this.j.f()), AiTrainingWebActivity.this.j == null ? 0 : AiTrainingWebActivity.this.j.c(), new String[]{"serviceToken", "cUserId", "i.ai.mi.com_ph", "i.ai.mi.com_slh", "userId"}, new String[]{AiTrainingWebActivity.this.v, AiTrainingWebActivity.this.w, AiTrainingWebActivity.this.x, AiTrainingWebActivity.this.y, XmPluginHostApi.instance().getAccountId()});
                    } catch (Exception e) {
                        LogUtil.b("WebActivity", Log.getStackTraceString(e));
                    } catch (Throwable th) {
                        AiTrainingWebActivity.this.b();
                        throw th;
                    }
                    AiTrainingWebActivity.this.b();
                }
            });
            this.o = (ImageView) inflate.findViewById(R.id.img_audio_state);
            this.r = (Chronometer) inflate.findViewById(R.id.chronometer_record);
            this.r.setFormat("00:00");
            this.r.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                public void onChronometerTick(Chronometer chronometer) {
                    long d = (AiTrainingWebActivity.this.j == null || !AiTrainingWebActivity.this.j.d()) ? AiTrainingWebActivity.this.z == null ? 0 : AiTrainingWebActivity.this.z.d() : AiTrainingWebActivity.this.j.c();
                    if (d > AiTrainingWebActivity.i) {
                        AiTrainingWebActivity.this.d();
                        AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_skill_finish));
                        Message obtain = Message.obtain();
                        obtain.what = 101;
                        AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                        d = 3599000;
                    }
                    AiTrainingWebActivity.this.a(chronometer, d);
                }
            });
            this.o.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Message obtain = Message.obtain();
                    switch (AiTrainingWebActivity.this.n) {
                        case 99:
                            AiTrainingWebActivity.this.c();
                            AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_skill_finish));
                            obtain.what = 100;
                            AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                            return;
                        case 100:
                            AiTrainingWebActivity.this.d();
                            AiTrainingWebActivity.this.p.setVisibility(0);
                            AiTrainingWebActivity.this.q.setVisibility(0);
                            AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_select_playtran));
                            obtain.what = 101;
                            AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                            return;
                        case 101:
                            AiTrainingWebActivity.this.c();
                            AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_select_pausetran));
                            obtain.what = 102;
                            AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                            return;
                        case 102:
                            AiTrainingWebActivity.this.d();
                            AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_select_playtran));
                            obtain.what = 103;
                            AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                            return;
                        case 103:
                            AiTrainingWebActivity.this.c();
                            AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_select_pausetran));
                            obtain.what = 102;
                            AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                            return;
                        default:
                            return;
                    }
                }
            });
            inflate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AiTrainingWebActivity.this.b();
                }
            });
            this.m = new PopupWindow(getContext());
            this.m.setWidth(-1);
            this.m.setHeight(-1);
            this.m.setContentView(inflate);
            this.m.setBackgroundDrawable(new ColorDrawable(0));
            this.m.setOutsideTouchable(true);
            this.m.setTouchable(true);
            this.m.setAnimationStyle(16973910);
        }
        this.m.showAtLocation(getWindow().getDecorView(), 80, 0, 0);
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.m != null && this.m.isShowing()) {
            this.m.dismiss();
        }
        Message obtain = Message.obtain();
        obtain.what = 99;
        this.mHandler.sendMessage(obtain);
        f();
        h();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.u != null) {
            this.u.start();
        }
        if (this.r != null) {
            this.r.setBase(SystemClock.elapsedRealtime());
            this.r.start();
        }
        if (this.s != null && this.t != null) {
            this.s.setVisibility(0);
            this.t.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.u != null) {
            this.u.cancel();
        }
        if (this.r != null) {
            a(this.r, this.j == null ? 0 : this.j.c());
            this.r.stop();
        }
    }

    private void e() {
        this.j = new AudioManager(getFileStreamPath(b).getAbsolutePath());
        this.j.a();
    }

    private void f() {
        if (this.j != null) {
            getFileStreamPath(b).getAbsolutePath();
            this.j.b();
        }
    }

    private void g() {
        if (this.j != null) {
            if (this.z != null) {
                this.z.c();
            }
            this.z = new MediaManager();
            this.z.a(this.j.f(), (MediaPlayer.OnCompletionListener) new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    AiTrainingWebActivity.this.d();
                    AiTrainingWebActivity.this.o.setImageDrawable(AiTrainingWebActivity.this.getResources().getDrawable(R.drawable.training_select_playtran));
                    Message obtain = Message.obtain();
                    obtain.what = 103;
                    AiTrainingWebActivity.this.mHandler.sendMessage(obtain);
                }
            });
        }
    }

    private void h() {
        if (this.z != null) {
            this.z.c();
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"DefaultLocale"})
    public void a(Chronometer chronometer, long j2) {
        int round = Math.round(((float) j2) / 1000.0f);
        chronometer.setText(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(round / 60), Integer.valueOf(round % 60)}));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.module_a_3_close_btn_title) {
            finish();
        } else if (id == R.id.module_a_3_return_btn) {
            onBackPressed();
        } else if (id == R.id.title_bar_more) {
            super.onBackPressed();
        }
    }

    public class JavaScripdtObject {
        /* access modifiers changed from: private */
        public final TextView b;

        public JavaScripdtObject(TextView textView) {
            this.b = textView;
        }

        @JavascriptInterface
        public void setTitle(final String str) {
            this.b.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(str)) {
                        JavaScripdtObject.this.b.setText(URLDecoder.decode(str));
                    }
                }
            });
        }
    }
}
