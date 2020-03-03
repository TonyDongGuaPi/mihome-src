package com.xiaomi.youpin.share.ui.assemble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.ui.BaseActivity;

public class ShareActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private ShareViewHelper f23739a;
    private ShareBaseHelper b;
    private ShareNormalHelper c;
    private SharePosterHelper d;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (YouPinShareApi.a() == null) {
            finish();
            return;
        }
        CustomDisplayDensity.a(getApplicationContext());
        this.f23739a = new ShareViewHelper(this);
        this.f23739a.a();
        this.b = new ShareBaseHelper(this, this.f23739a);
        this.b.i();
        this.c = new ShareNormalHelper(this.b);
        this.c.a();
        this.d = new SharePosterHelper(this.b);
        this.d.a();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.b.j();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.b.k();
        super.onResume();
        this.b.l();
        this.c.b();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.c.c();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.b != null) {
            this.b.m();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        this.f23739a.h();
        this.b.n();
    }

    public void finish() {
        CustomDisplayDensity.b(getApplicationContext());
        super.finish();
        overridePendingTransition(0, 0);
    }
}
