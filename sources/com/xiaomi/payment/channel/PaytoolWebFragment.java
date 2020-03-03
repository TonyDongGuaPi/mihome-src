package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.MiuiUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import miuipub.app.Activity;

public class PaytoolWebFragment extends BaseProcessFragment {
    private static final String v = "PaytoolWebFragment";
    /* access modifiers changed from: private */
    public boolean A;
    /* access modifiers changed from: private */
    public String B;
    private WebView w;
    private String x;
    /* access modifiers changed from: private */
    public String y;
    /* access modifiers changed from: private */
    public long z;

    public void a(Bundle bundle) {
        super.a(bundle);
        f(5);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_paytool_web, (ViewGroup) null);
        this.w = (WebView) inflate.findViewById(R.id.webview);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.x = bundle.getString("url");
        this.y = bundle.getString(MibiConstants.dA);
        this.z = bundle.getLong(MibiConstants.dd, 0);
        this.B = bundle.getString("title");
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        this.A = memoryStorage.d(this.t, MibiConstants.cZ);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.B);
        b(R.string.mibi_btn_prev);
        if (Utils.b()) {
            getActivity().getWindow().getAttributes().width = (int) getResources().getDimension(R.dimen.mibi_pad_window_width);
        }
        MiuiUtils.a((Activity) getActivity());
        this.w.getSettings().setJavaScriptEnabled(true);
        this.w.setLayerType(1, (Paint) null);
        this.w.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                PaytoolWebFragment.this.T().e(true);
                Uri parse = Uri.parse(str);
                if (parse != null && TextUtils.equals(parse.getPath(), PaytoolWebFragment.this.y)) {
                    webView.stopLoading();
                    PaytoolWebFragment.this.p().m().a(PaytoolWebFragment.this.t, MibiConstants.hg, (Object) Long.valueOf(PaytoolWebFragment.this.z));
                    Bundle bundle = new Bundle();
                    bundle.putString("title", PaytoolWebFragment.this.B);
                    bundle.putBoolean(MibiConstants.hi, true);
                    ChannelUtils.a((StepFragment) PaytoolWebFragment.this, bundle, PaytoolWebFragment.this.A);
                }
            }

            public void onPageFinished(WebView webView, String str) {
                PaytoolWebFragment.this.T().e(false);
            }
        });
        if (!TextUtils.isEmpty(this.x)) {
            this.w.loadUrl(this.x);
        }
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, this.A ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.A ? "Pay:" : "Recharge:");
    }

    public void o() {
        T().e(false);
        CookieSyncManager.createInstance(getActivity());
        CookieManager.getInstance().removeAllCookie();
        super.o();
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }
}
