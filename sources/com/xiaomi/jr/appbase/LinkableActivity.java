package com.xiaomi.jr.appbase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import com.miui.supportlite.app.ActionBar;
import com.xiaomi.jr.appbase.utils.WebUtils;
import com.xiaomi.jr.base.BaseFragment;
import com.xiaomi.jr.common.utils.FragmentHelper;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.ui.ActionBarCustomizer;
import com.xiaomi.jr.web.WebFragment;
import com.xiaomi.jr.web.utils.WebConstants;

public class LinkableActivity extends BaseActivity {
    public static final String KEY_PAGE_TYPE = "page_type";
    public static final int PAGE_TYPE_FLOW = 2;
    public static final int PAGE_TYPE_WEB = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f10309a = "flow_fragment";
    private static final String b = "web_fragment";
    protected BaseFragment mFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        setContentView(R.layout.linkable_activity);
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            a(extras.getString("url"));
            boolean z = extras.getInt("page_type") == 2;
            if (bundle == null) {
                String string = extras.getString("title");
                if (TextUtils.isEmpty(string)) {
                    string = " ";
                }
                setTitle(string);
                if (z) {
                    a(extras);
                    return;
                }
                final View findViewById = findViewById(R.id.container);
                findViewById.findViewById(R.id.container).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        LinkableActivity.this.a(extras);
                        findViewById.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                return;
            }
            this.mFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(z ? f10309a : b);
        }
    }

    private void b() {
        boolean a2 = UrlUtils.a(getIntent().getStringExtra("url"), WebConstants.g, false);
        getIntent().putExtra(WebConstants.g, a2);
        if (a2) {
            ActionBarCustomizer.a(this);
        }
    }

    private void a(String str) {
        ActionBar actionBar;
        if (UrlUtils.a(str, WebConstants.h, false) && (actionBar = getActionBar()) != null) {
            actionBar.b().setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("url");
            setBackHome(!WebUtils.b(string), WebUtils.c(string));
            b(bundle);
        }
    }

    private void b(Bundle bundle) {
        if (this.mFragment == null) {
            this.mFragment = (BaseFragment) FragmentHelper.a(getSupportFragmentManager(), R.id.container, WebFragment.class, bundle, b);
        }
    }

    public void onHomeSelected() {
        if (this.mFragment == null || !this.mFragment.g()) {
            onBackPressed();
        }
    }

    public void onBackPressed() {
        if (this.mFragment != null) {
            this.mFragment.f();
        } else {
            setResult(-1, (Intent) null);
        }
        super.onBackPressed();
    }

    public void reload() {
        if (this.mFragment != null) {
            this.mFragment.e();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mFragment != null) {
            this.mFragment.onActivityResult(i, i2, intent);
        }
    }
}
