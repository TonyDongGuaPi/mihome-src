package cn.fraudmetrix.octopus.aspirit.activity.mvp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.fraudmetrix.octopus.aspirit.R;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.a;
import cn.fraudmetrix.octopus.aspirit.base.ui.mvp.BaseMvpActivity;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.l;
import cn.fraudmetrix.octopus.aspirit.view.CircleProgerssView;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;

public abstract class OctopusMainView extends BaseMvpActivity<d> implements a.b {

    /* renamed from: a  reason: collision with root package name */
    protected TextView f593a;
    protected LinearLayout b;
    protected LinearLayout c;
    protected TextView d;
    protected WebView e;
    protected String f;
    protected LinearLayout g;
    protected ScrollView h;
    protected CircleProgerssView i;
    protected cn.fraudmetrix.octopus.aspirit.f.a j;
    protected LayoutInflater k;
    protected ArrayList<View> l;

    private Bitmap a(View view, Bitmap.Config config) {
        if (view == null) {
            return null;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), config);
            view.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private WebSettings m() {
        return this.e.getSettings();
    }

    public void a(int i2) {
        ((d) this.m).a(i2);
    }

    public void a(final int i2, final boolean z) {
        if (this.h.getVisibility() == 8) {
            i();
        }
        if (i2 > this.i.getProgress()) {
            if (!z || i2 - this.i.getProgress() < 2) {
                this.i.setProgress(i2);
                return;
            }
            this.i.setProgress(this.i.getProgress() + 2);
            if (this.i.getProgress() < i2) {
                this.i.postDelayed(new Runnable() {
                    public void run() {
                        if (OctopusMainView.this.i.getProgress() < i2) {
                            OctopusMainView.this.a(i2, z);
                        }
                    }
                }, 250);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, int i2) {
        WebSettings settings = webView.getSettings();
        if (Build.VERSION.SDK_INT > 18) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setCacheMode(i2);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.e, true);
        }
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (this.f == null) {
            this.f = settings.getUserAgentString();
        }
    }

    public void a(String str) {
        this.f593a.setText(str);
    }

    public void a(String str, String str2) {
        if (this.e != null && !TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                if (!(str2.toLowerCase().indexOf("iphone") == -1 && str2.toLowerCase().indexOf("android") == -1)) {
                    str2 = this.f;
                }
                m().setUserAgentString(str2);
            }
            this.e.loadUrl(str);
        }
    }

    public void a(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
        this.b.setVisibility(0);
        this.d.setText(R.string.octopus_loading_data);
    }

    public void a(boolean z, int i2) {
        a(z);
        if (i2 > 0) {
            this.b.postDelayed(new Runnable() {
                public void run() {
                    OctopusMainView.this.k();
                }
            }, (long) i2);
        }
    }

    public void b(final int i2) {
        this.i.post(new Runnable() {
            public void run() {
                for (int i = 0; i < OctopusMainView.this.l.size(); i++) {
                    TextView textView = (TextView) OctopusMainView.this.l.get(i);
                    textView.setTextColor(OctopusMainView.this.getResources().getColor(R.color.octopus_font_gray));
                    Drawable drawable = OctopusMainView.this.getResources().getDrawable(R.mipmap.img_completed_grey);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
                }
                TextView textView2 = (TextView) OctopusMainView.this.k.inflate(R.layout.item_txt, (ViewGroup) null);
                textView2.setText(i2);
                OctopusMainView.this.l.add(textView2);
                OctopusMainView.this.g.addView(textView2);
                if (OctopusMainView.this.g.getVisibility() == 8) {
                    OctopusMainView.this.g.setVisibility(0);
                }
            }
        });
    }

    public void b(String str) {
        ((d) this.m).a(str);
    }

    public void b(String str, String str2) {
        ((d) this.m).b(str, str2);
    }

    public void c(String str) {
        ((d) this.m).b(str);
    }

    public void c(String str, String str2) {
        ((d) this.m).a(str, str2);
    }

    public void d(String str) {
        ((d) this.m).c(str);
    }

    public void d(String str, String str2) {
        if (str.startsWith("taobaoauth:")) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str.replace("taobaoauth:", "https:")));
                intent.setClassName(a.f599a.get("005003"), "com.taobao.browser.BrowserActivity");
                startActivity(intent);
                return;
            } catch (Exception unused) {
                str = str.replace("taobaoauth:", "taobao:");
            }
        }
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception unused2) {
            a((CharSequence) str2, 0);
        }
    }

    public void e() {
        this.e.stopLoading();
    }

    public String f() {
        Bitmap a2 = a((View) this.e, Bitmap.Config.RGB_565);
        return a2 != null ? l.a(a2) : "";
    }

    public cn.fraudmetrix.octopus.aspirit.f.a g() {
        return this.j;
    }

    public void h() {
        ((d) this.m).c();
    }

    public void i() {
        this.i.post(new Runnable() {
            public void run() {
                OctopusMainView.this.a(OctopusMainView.this.e, -1);
                OctopusMainView.this.e.setVisibility(8);
                OctopusMainView.this.h.setVisibility(0);
            }
        });
    }

    public void j() {
        a(OctopusManager.b().q());
    }

    public void k() {
        this.b.postDelayed(new Runnable() {
            public void run() {
                OctopusMainView.this.c.setVisibility(8);
                OctopusMainView.this.b.setVisibility(8);
            }
        }, 1000);
    }
}
