package com.mipay.common.base;

import android.os.Bundle;
import com.mipay.common.data.b;

public class BaseActivity extends StepActivity {
    private final p e = new p();
    private boolean f;

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (!this.f) {
            this.f = true;
            this.e.d();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        b(bundle);
        this.e.c();
    }

    /* access modifiers changed from: protected */
    public void b() {
        super.b();
        if (this.f) {
            this.f = false;
            if (!b.c()) {
                this.e.e();
            } else if (isChangingConfigurations()) {
                this.e.f();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void c() {
        super.c();
        this.e.g();
    }

    public final s getTaskManager() {
        return this.e;
    }
}
