package miuipub.hybrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.miuipub.internal.hybrid.HybridManager;
import com.miuipub.internal.hybrid.HybridProgressView;
import com.miuipub.internal.hybrid.WebContainerView;
import com.miuipub.internal.hybrid.provider.AbsWebView;
import com.miuipub.internal.hybrid.provider.WebViewFactory;
import com.miuipub.internal.hybrid.provider.WebViewFactoryProvider;

public class HybridView extends FrameLayout {
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;

    /* renamed from: a  reason: collision with root package name */
    private AbsWebView f2945a;
    private ProgressBar b;
    private HybridProgressView c;
    private ViewGroup d;
    private Button e;
    private TextView f;
    private WebContainerView g = ((WebContainerView) findViewById(R.id.webContainer));
    private WebViewFactoryProvider h;
    private HybridSettings i;
    private HybridManager j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;

    public HybridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HybridViewStyle, 0, 0);
        this.k = obtainStyledAttributes.getInt(R.styleable.HybridViewStyle_hybridProgressBar, 0);
        this.l = obtainStyledAttributes.getBoolean(R.styleable.HybridViewStyle_hybridErrorPage, true);
        this.m = obtainStyledAttributes.getBoolean(R.styleable.HybridViewStyle_hybridPullable, true);
        obtainStyledAttributes.recycle();
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.hybrid_view_layout, this, true);
        this.h = WebViewFactory.a(context);
        this.f2945a = this.h.a(context, this);
        this.g.setWebView(this.f2945a.a());
        if (this.k == 1) {
            this.b = (ProgressBar) findViewById(R.id.progress_circular);
        } else if (this.k == 2) {
            this.c = (HybridProgressView) findViewById(R.id.progress_horizontal);
        }
        this.f = (TextView) findViewById(R.id.hybrid_provider);
        if (this.m) {
            this.f.setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    public HybridManager getHybridManager() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public void setHybridManager(HybridManager hybridManager) {
        this.j = hybridManager;
    }

    /* access modifiers changed from: package-private */
    public AbsWebView getWebView() {
        return this.f2945a;
    }

    public void setHybridViewClient(HybridViewClient hybridViewClient) {
        hybridViewClient.a(this.j);
        this.f2945a.a(this.h.a(hybridViewClient, this));
    }

    public void setHybridChromeClient(HybridChromeClient hybridChromeClient) {
        hybridChromeClient.a(this.j);
        this.f2945a.a(this.h.a(hybridChromeClient, this));
    }

    public void loadUrl(String str) {
        this.f2945a.a(str);
    }

    public void addJavascriptInterface(Object obj, String str) {
        this.f2945a.a(obj, str);
    }

    public HybridSettings getSettings() {
        if (this.i == null) {
            this.i = this.f2945a.b();
        }
        return this.i;
    }

    public void destroy() {
        this.f2945a.c();
    }

    public void reload() {
        this.f2945a.d();
    }

    public void clearCache(boolean z) {
        this.f2945a.a(z);
    }

    public boolean canGoBack() {
        return this.f2945a.e();
    }

    public void goBack() {
        this.f2945a.g();
    }

    public String getUrl() {
        return this.f2945a.h();
    }

    public String getTitle() {
        return this.f2945a.i();
    }

    /* access modifiers changed from: package-private */
    public void setProgress(int i2) {
        if (i2 > 80 && !this.n) {
            hideReloadView();
        }
        if (i2 == 100) {
            this.g.setBackground((Drawable) null);
        }
        if (this.b != null || this.c != null) {
            if (this.k == 1) {
                if (this.b != null) {
                    if (this.b.getVisibility() == 8) {
                        this.b.setVisibility(0);
                    }
                    this.b.setProgress(i2);
                    if (i2 == this.b.getMax()) {
                        this.b.setVisibility(8);
                    }
                }
            } else if (this.k == 2 && this.c != null) {
                if (this.c.getVisibility() == 8) {
                    this.c.setVisibility(0);
                }
                this.c.setProgress(i2);
                if (i2 == this.c.getMax()) {
                    this.c.setVisibility(8);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setLoadingError(boolean z) {
        this.n = z;
    }

    /* access modifiers changed from: package-private */
    public void showReloadView() {
        if (this.l) {
            if (this.d == null) {
                this.d = (ViewGroup) ((ViewStub) findViewById(R.id.webview_reload_stub)).inflate();
                this.e = (Button) this.d.findViewById(R.id.reload);
                this.e.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HybridView.this.reload();
                        HybridView.this.setReloadContentVisibility(8);
                    }
                });
            }
            this.d.setVisibility(0);
            setReloadContentVisibility(0);
            this.f2945a.a(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void hideReloadView() {
        if (this.l) {
            if (this.d != null) {
                this.d.setVisibility(8);
            }
            this.f2945a.a(0);
        }
    }

    /* access modifiers changed from: private */
    public void setReloadContentVisibility(int i2) {
        int childCount = this.d.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            this.d.getChildAt(i3).setVisibility(i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void setWebProvider(String str) {
        String host = Uri.parse(str).getHost();
        if (TextUtils.isEmpty(host)) {
            this.f.setText("");
            return;
        }
        this.f.setText(String.format(getContext().getString(R.string.hybrid_provider), new Object[]{host}));
    }

    public int getContentHeight() {
        return this.f2945a.j();
    }

    public float getScale() {
        return this.f2945a.k();
    }

    public HybridBackForwardList copyBackForwardList() {
        return this.f2945a.n();
    }
}
