package com.xiaomi.smarthome.operation.js_sdk.titlebar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebTitleBarView extends FrameLayout implements ITitleBar {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21125a = "WebViewTitleBar";
    private static final int j = 1;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public ProgressBar c;
    private ProgressBar d;
    private TextView e;
    private TitleBarMenuAdapter f;
    private View g;
    private String h;
    /* access modifiers changed from: private */
    public int i;
    private ImageView k;
    /* access modifiers changed from: private */
    public TitleBarActionReceiver l;

    public interface TitleBarActionReceiver {
        void a();

        void a(View view, TitleBarMenu.Menu menu, String str);

        void a(boolean z);

        void b();

        void c();
    }

    public WebTitleBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebTitleBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint({"HandlerLeak"})
    public WebTitleBarView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        inflate(context, R.layout.webview_title_bar_layout, this);
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WebTitleBarView.this.c(view);
            }
        });
        this.k = (ImageView) findViewById(R.id.bg_img);
        this.e = (TextView) findViewById(R.id.title);
        this.c = (ProgressBar) findViewById(R.id.progress_bar);
        this.c.setIndeterminateDrawable((Drawable) null);
        this.g = findViewById(R.id.bootom_border);
        this.d = (ProgressBar) findViewById(R.id.loading_bar);
        this.b = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1 && WebTitleBarView.this.c.getProgress() < WebTitleBarView.this.i) {
                    WebTitleBarView.this.c.setProgress(WebTitleBarView.this.c.getProgress() + 1);
                    WebTitleBarView.this.c.postInvalidate();
                    WebTitleBarView.this.b.sendEmptyMessageDelayed(1, (long) ((((int) (Math.random() * 5.0d)) + 2) * 50));
                }
            }
        };
        this.f = new TitleBarMenuAdapter(getContext(), (TitleBarMenu) findViewById(R.id.option_menu));
        this.f.a((TitleBarMenuAdapter.OnMenuClickListener) new TitleBarMenuAdapter.OnMenuClickListener() {
            public void a(View view, TitleBarMenu.Menu menu, String str) {
                if (WebTitleBarView.this.l != null) {
                    WebTitleBarView.this.l.a(view, menu, str);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 19) {
            this.f.a();
            this.f.a((View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    WebTitleBarView.this.b(view);
                }
            });
        }
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WebTitleBarView.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.l != null) {
            this.l.a();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.l != null) {
            this.l.b();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.l != null) {
            this.l.c();
        }
    }

    public static class TitleBarClientAdapter implements TitleBarActionReceiver {

        /* renamed from: a  reason: collision with root package name */
        private TitleBarActionReceiver f21129a;

        public TitleBarClientAdapter() {
        }

        public TitleBarClientAdapter(TitleBarActionReceiver titleBarActionReceiver) {
            this.f21129a = titleBarActionReceiver;
        }

        public void a(View view, TitleBarMenu.Menu menu, String str) {
            if (this.f21129a != null) {
                this.f21129a.a(view, menu, str);
            }
        }

        public void b() {
            if (this.f21129a != null) {
                this.f21129a.b();
            }
        }

        public void a() {
            if (this.f21129a != null) {
                this.f21129a.a();
            }
        }

        public void a(boolean z) {
            if (this.f21129a != null) {
                this.f21129a.a(z);
            }
        }

        public void c() {
            if (this.f21129a != null) {
                this.f21129a.c();
            }
        }
    }

    public TitleBarActionReceiver getDefaultTitleBarActionReceiver(final CommonWebView commonWebView, final Activity activity) {
        if (commonWebView == null || activity == null) {
            return new TitleBarClientAdapter();
        }
        return new TitleBarActionReceiver() {
            public void a(View view, TitleBarMenu.Menu menu, String str) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("index", menu.a());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsSdkUtils.b(commonWebView, "smartHome.dispatchEvent", str, jSONObject.toString());
            }

            public void b() {
                commonWebView.doShare();
            }

            public void a() {
                a(false);
            }

            public void a(boolean z) {
                if (z) {
                    activity.finish();
                } else {
                    activity.onBackPressed();
                }
            }

            @SuppressLint({"CheckResult"})
            public void c() {
                JsSdkUtils.b(commonWebView, "smartHome.dispatchEvent", "titleClick");
            }
        };
    }

    public void setTitleBarActionReceiver(TitleBarActionReceiver titleBarActionReceiver) {
        this.l = titleBarActionReceiver;
    }

    public void setDefaultTitle(String str) {
        if (a(str)) {
            this.e.setText(str);
            this.h = str;
        }
    }

    private static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && !str.trim().startsWith("https://") && !str.trim().startsWith(ConnectionHelper.HTTP_PREFIX)) {
            return true;
        }
        return false;
    }

    public void setNavigationBar(String str) {
        try {
            this.k.setImageDrawable(new ColorDrawable(-1));
            setBackgroundColor(-1);
            this.g.setBackgroundColor(-1);
            if (!TextUtils.isEmpty(this.h)) {
                this.e.setText(this.h);
            }
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString(Constants.Name.BACKGROUND_IMAGE);
            String optString3 = jSONObject.optString("backgroundColor", "");
            String optString4 = jSONObject.optString("borderBottomColor", "");
            boolean optBoolean = jSONObject.optBoolean("reset", false);
            Bitmap a2 = JsSdkUtils.a(optString2);
            if (a2 != null) {
                this.k.setImageBitmap(a2);
                this.g.setBackgroundColor(0);
                return;
            }
            if (!TextUtils.isEmpty(optString)) {
                this.e.setText(optString);
            }
            if (optBoolean) {
                this.k.setImageDrawable(new ColorDrawable(-1));
                setBackgroundColor(-1);
                this.g.setBackgroundColor(-1);
            } else if (!TextUtils.isEmpty(optString3)) {
                int parseColor = Color.parseColor(optString3);
                this.k.setImageDrawable(new ColorDrawable(parseColor));
                setBackgroundColor(parseColor);
                this.g.setBackgroundColor(parseColor);
            }
            if (!optBoolean && TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4)) {
                this.g.setBackgroundColor(Color.parseColor(optString4));
            }
        } catch (Exception e2) {
            Log.e(f21125a, "setNavigationBar: " + Log.getStackTraceString(e2));
        }
    }

    public void setNavigationBarLoading(String str) {
        boolean z;
        int i2 = 0;
        try {
            z = new JSONObject(str).optBoolean("enabled", false);
        } catch (JSONException e2) {
            e2.printStackTrace();
            z = false;
        }
        ProgressBar progressBar = this.d;
        if (!z) {
            i2 = 8;
        }
        progressBar.setVisibility(i2);
    }

    public void onReceivedTitle(String str) {
        if (a(str)) {
            if (!TextUtils.isEmpty(str)) {
                this.e.setText(str);
            } else {
                this.e.setText(this.h);
            }
        }
    }

    public void onProgressChanged(int i2) {
        if (i2 >= this.i) {
            this.c.setVisibility(0);
            if (this.b != null) {
                this.b.removeMessages(1);
                if (i2 >= this.c.getProgress()) {
                    double d2 = (double) i2;
                    Double.isNaN(d2);
                    int i3 = (int) (d2 * 1.1d);
                    if (i3 <= 99) {
                        this.c.setProgress(i3);
                        this.c.postInvalidate();
                    }
                }
            }
        }
        if (i2 >= 90 && this.b != null) {
            this.b.removeMessages(1);
            this.c.setVisibility(8);
        }
    }

    public void setShareButtonEnable(boolean z) {
        LogUtil.a(f21125a, "setShareButtonEnable: enable: " + z);
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (z) {
            this.f.a();
        } else {
            this.f.b();
        }
    }

    public void onBackPressed(boolean z) {
        if (this.l != null) {
            this.l.a(z);
        }
    }

    public void setOptionButton(String str) {
        this.f.a(str);
    }

    public void setPopMenu(String str) {
        this.f.b(str);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.removeCallbacksAndMessages((Object) null);
    }
}
