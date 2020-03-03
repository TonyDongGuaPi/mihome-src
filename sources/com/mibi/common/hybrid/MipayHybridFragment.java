package com.mibi.common.hybrid;

import android.app.Fragment;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.mibi.common.R;
import com.mibi.common.component.CommonErrorView;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.utils.UrlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import miuipub.app.ActionBar;
import miuipub.hybrid.GeolocationPermissions;
import miuipub.hybrid.HybridChromeClient;
import miuipub.hybrid.HybridFragment;
import miuipub.hybrid.HybridSettings;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.JsResult;
import miuipub.hybrid.SslErrorHandler;

public class MipayHybridFragment extends HybridFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7571a = "MibiHybrid";
    private static final int b = 5000;
    private HybridView c;
    private CommonErrorView d;
    private String e;
    private String f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public JsResultWrapper h = new JsResultWrapper();
    /* access modifiers changed from: private */
    public List<Interceptor> i;
    private List<ResultHandler> j;

    /* access modifiers changed from: protected */
    public List<Interceptor> a() {
        return null;
    }

    /* access modifiers changed from: protected */
    public List<ResultHandler> b() {
        return null;
    }

    public MipayHybridFragment() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a());
        this.i = Collections.unmodifiableList(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(b());
        this.j = Collections.unmodifiableList(arrayList2);
    }

    @RequiresApi(api = 11)
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        e();
        d();
        this.c = (HybridView) view.findViewById(R.id.hybrid_view);
        this.d = (CommonErrorView) view.findViewById(R.id.error_view);
        k();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        Log.d(f7571a, "web view init");
        a(this.c, g(), this.e);
        j();
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = 11)
    public void d() {
        if (Utils.b()) {
            h();
        } else {
            i();
        }
    }

    @RequiresApi(api = 11)
    private void h() {
        ActionBar T = T();
        if (T != null) {
            T.setCustomView(R.layout.mibi_custom_action_bar);
            T.setDisplayOptions(16);
            Button button = (Button) T.getCustomView().findViewById(R.id.buttonLeft);
            button.setText(R.string.mibi_cancel);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MipayHybridFragment.this.getActivity().onBackPressed();
                }
            });
            ((Button) T.getCustomView().findViewById(R.id.buttonRight)).setVisibility(8);
            T.show();
        }
    }

    @RequiresApi(api = 11)
    private void i() {
        ActionBar T = T();
        if (T != null) {
            T.setHomeButtonEnabled(true);
            T.setDisplayHomeAsUpEnabled(true);
            T.setDisplayOptions(4, 7);
            T.setDisplayShowCustomEnabled(false);
            T.show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (m()) {
            return true;
        }
        getActivity().finish();
        return true;
    }

    private void j() {
        this.c.setHybridChromeClient(new HybridChromeClient() {
            public void a(String str, GeolocationPermissions.Callback callback) {
            }

            @RequiresApi(api = 11)
            public void a(HybridView hybridView, String str) {
                super.a(hybridView, str);
                ActionBar T = MipayHybridFragment.this.T();
                if (T != null) {
                    if (Utils.b()) {
                        ((TextView) T.getCustomView().findViewById(R.id.title)).setText(str);
                    } else {
                        T.setTitle(str);
                    }
                }
            }

            public boolean a(HybridView hybridView, String str, String str2, JsResult jsResult) {
                MipayHybridFragment.this.h.a(jsResult);
                return super.a(hybridView, str, str2, MipayHybridFragment.this.h);
            }

            public boolean b(HybridView hybridView, String str, String str2, JsResult jsResult) {
                MipayHybridFragment.this.h.a(jsResult);
                return super.b(hybridView, str, str2, MipayHybridFragment.this.h);
            }
        });
        this.c.setHybridViewClient(new MipayHybridViewClient(this.e, this.f) {
            public boolean a(HybridView hybridView, String str) {
                for (Interceptor a2 : MipayHybridFragment.this.i) {
                    if (a2.a(MipayHybridFragment.this.getActivity(), str)) {
                        return true;
                    }
                }
                return super.a(hybridView, str);
            }

            public void a(HybridView hybridView, String str, String str2, String str3) {
                if (!MipayHybridFragment.this.g) {
                    MipayHybridFragment.this.a(false);
                    hybridView.postDelayed(new Runnable() {
                        public void run() {
                            if (!MipayHybridFragment.this.l() && MipayHybridFragment.this.isAdded()) {
                                MipayHybridFragment.this.a(true);
                            }
                        }
                    }, 5000);
                    super.a(hybridView, str, str2, str3);
                } else if (hybridView.canGoBack()) {
                    hybridView.goBack();
                } else {
                    MipayHybridFragment.this.getActivity().finish();
                }
            }

            public void b(HybridView hybridView, String str) {
                super.b(hybridView, str);
                boolean unused = MipayHybridFragment.this.g = false;
                if (!MipayHybridFragment.this.l() && !UrlUtils.b(str)) {
                    MipayHybridFragment.this.a(true);
                }
            }

            public void a(HybridView hybridView, SslErrorHandler sslErrorHandler, SslError sslError) {
                Log.d(MipayHybridFragment.f7571a, "ssl error: " + sslError);
            }

            public void a(HybridView hybridView, int i, String str, String str2) {
                Log.d(MipayHybridFragment.f7571a, "error: " + i + " " + str);
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(this.f)) {
            MistatisticUtils.a((Fragment) this, "MipayHybrid_" + this.f);
        }
    }

    public void onPause() {
        super.onPause();
        if (!TextUtils.isEmpty(this.f)) {
            MistatisticUtils.b((Fragment) this, "MipayHybrid_" + this.f);
        }
    }

    public void e() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            this.e = UrlUtils.e(intent.getStringExtra("com.miui.sdk.hybrid.extra.URL"));
            this.f = intent.getStringExtra("id");
        }
    }

    private void k() {
        HybridSettings settings = this.c.getSettings();
        settings.b(true);
        settings.e(true);
        settings.j(true);
        settings.b(100);
        if (CommonConstants.b) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public View f() {
        return getActivity().getLayoutInflater().inflate(R.layout.mibi_hybrid, (ViewGroup) null);
    }

    public void onDestroy() {
        super.onDestroy();
        this.h.b();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        for (ResultHandler a2 : this.j) {
            a2.a(this.c, i2, i3, intent);
        }
    }

    private static class JsResultWrapper extends JsResult {

        /* renamed from: a  reason: collision with root package name */
        private JsResult f7576a;

        private JsResultWrapper() {
        }

        /* access modifiers changed from: package-private */
        public void a(JsResult jsResult) {
            this.f7576a = jsResult;
        }

        public void a() {
            if (this.f7576a != null) {
                this.f7576a.a();
                this.f7576a = null;
            }
        }

        public void b() {
            if (this.f7576a != null) {
                this.f7576a.b();
                this.f7576a = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean l() {
        return this.c.getVisibility() == 0;
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.c.setVisibility(0);
            this.d.setVisibility(8);
            return;
        }
        this.c.setVisibility(4);
        this.d.setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            return m();
        }
        return false;
    }

    private boolean m() {
        if (!this.c.canGoBack() || !isAdded()) {
            return false;
        }
        this.g = true;
        this.c.goBack();
        return true;
    }
}
