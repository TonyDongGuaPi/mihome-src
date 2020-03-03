package cn.fraudmetrix.octopus.aspirit.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.fraudmetrix.octopus.aspirit.R;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.OctopusMainView;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.d;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.h;
import cn.fraudmetrix.octopus.aspirit.view.CircleProgerssView;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.mishopsdk.util.Constants;
import java.util.ArrayList;

public class OctopusMainActivity extends OctopusMainView {
    private Toolbar n;
    /* access modifiers changed from: private */
    public ProgressBar o;
    private cn.fraudmetrix.octopus.aspirit.activity.mvp.b p;
    private AlertDialog q = null;

    private class a extends WebChromeClient {
        private a() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (!((d) OctopusMainActivity.this.m).b()) {
                ((d) OctopusMainActivity.this.m).a(webView, i);
                super.onProgressChanged(webView, i);
                if (i == 100) {
                    OctopusMainActivity.this.o.setVisibility(8);
                    return;
                }
                if (OctopusMainActivity.this.o.getVisibility() == 8) {
                    OctopusMainActivity.this.o.setVisibility(0);
                }
                OctopusMainActivity.this.o.setProgress(i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
        }

        public void onPageFinished(WebView webView, String str) {
            OctopusMainActivity.this.k();
            if (OctopusMainActivity.this.m() && !webView.getSettings().getLoadsImagesAutomatically()) {
                webView.getSettings().setLoadsImagesAutomatically(true);
            }
            ((d) OctopusMainActivity.this.m).a(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!((d) OctopusMainActivity.this.m).b()) {
                super.onPageStarted(webView, str, bitmap);
                ((d) OctopusMainActivity.this.m).b(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            ((d) OctopusMainActivity.this.m).a(webView, i, str, str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
            h.c("onReceivedSslError:" + webView.getUrl());
            ((d) OctopusMainActivity.this.m).a(webView, 10003, "", webView.getUrl());
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return super.shouldInterceptRequest(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return ((d) OctopusMainActivity.this.m).c(webView, str);
        }
    }

    /* access modifiers changed from: private */
    public boolean m() {
        return this.h.getVisibility() == 8;
    }

    private void n() {
        if (OctopusManager.b().o()) {
            if (this.q == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.octopus_task_canclemsg);
                builder.setTitle(R.string.octopus_task_dialogtitle);
                builder.setPositiveButton(getResources().getString(R.string.octopus_task_dialogconfirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (!((d) OctopusMainActivity.this.m).b()) {
                            cn.fraudmetrix.octopus.aspirit.main.a.a().b();
                            OctopusMainActivity.this.a(50);
                        }
                    }
                });
                builder.setNegativeButton(R.string.octopus_task_dialogcancle, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                this.q = builder.create();
            }
            this.q.show();
            this.q.getButton(-1).setTextColor(getResources().getColor(R.color.color_black));
            this.q.getButton(-2).setTextColor(getResources().getColor(R.color.color_black));
        }
    }

    private void o() {
        this.n = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.n);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
        }
        if (Build.VERSION.SDK_INT != 27) {
            getWindow().setFormat(-3);
        }
        getWindow().setSoftInputMode(18);
        this.f593a = (TextView) findViewById(R.id.webview_title_tv);
        this.n.setTitle((CharSequence) "");
        int j = OctopusManager.b().j();
        if (j > 0) {
            this.n.setNavigationIcon(j);
        }
        int i = OctopusManager.b().i();
        if (i > 0) {
            this.n.setBackgroundResource(i);
        }
        int n2 = OctopusManager.b().n();
        if (n2 > 0) {
            findViewById(R.id.webview_bg).setBackgroundResource(n2);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(n2));
            }
        }
        int k = OctopusManager.b().k();
        if (k > 0) {
            this.f593a.setTextColor(getResources().getColor(k));
        }
        int l = OctopusManager.b().l();
        if (l > 0) {
            this.f593a.setTextSize(2, (float) l);
        }
        int m = OctopusManager.b().m();
        if (m > 0) {
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -2);
            layoutParams.gravity = m;
            this.f593a.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        n();
    }

    private void q() {
        this.o = (ProgressBar) findViewById(R.id.web_pro);
        this.e = new WebView(this);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ((LinearLayout) findViewById(R.id.webview_layout)).addView(this.e);
        this.e.setWebChromeClient(new a());
        this.e.setWebViewClient(new b());
        a(this.e, -1);
        this.p = new cn.fraudmetrix.octopus.aspirit.activity.mvp.b();
        this.p.a(this);
        this.e.addJavascriptInterface(this.p, "bridge");
    }

    public int a() {
        return R.layout.octopus_main_activity;
    }

    public void b() {
        o();
        this.c = (LinearLayout) findViewById(R.id.load_mask);
        this.b = (LinearLayout) findViewById(R.id.load_progress);
        this.d = (TextView) findViewById(R.id.load_progress_msg);
        this.h = (ScrollView) findViewById(R.id.progress_layout);
        this.i = (CircleProgerssView) findViewById(R.id.progress_view);
        this.i.setProgress(0);
        this.g = (LinearLayout) findViewById(R.id.progress_step_layout);
        this.g.setVisibility(8);
        this.k = LayoutInflater.from(this);
        cn.fraudmetrix.octopus.aspirit.utils.a.a().c();
        q();
    }

    public void c() {
        super.c();
        this.n.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OctopusMainActivity.this.p();
            }
        });
        this.n.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                menuItem.getItemId();
                int i = R.id.action_refresh;
                return false;
            }
        });
    }

    public void d() {
        super.d();
        this.l = new ArrayList();
        this.m = new d();
        ((d) this.m).a(this);
        this.j = new cn.fraudmetrix.octopus.aspirit.f.b(this);
        if (!((d) this.m).a(getIntent())) {
            c(R.string.octopus_data_error);
            finish();
        }
        ((d) this.m).a();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            this.p.d();
            this.e.stopLoading();
            this.e.removeAllViewsInLayout();
            this.e.removeAllViews();
            this.e.setWebViewClient((WebViewClient) null);
            this.e.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        h.a(ActivityInfo.TYPE_STR_ONDESTROY);
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        p();
        return true;
    }
}
