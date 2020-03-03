package com.huawei.hms.update.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import com.huawei.hms.support.log.a;

public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private AlertDialog f5921a;
    private a b;

    /* access modifiers changed from: protected */
    public abstract AlertDialog a();

    public void a(a aVar) {
        this.b = aVar;
        if (f() == null || f().isFinishing()) {
            a.d("AbstractDialog", "In show, The activity is null or finishing.");
            return;
        }
        this.f5921a = a();
        this.f5921a.setCanceledOnTouchOutside(false);
        this.f5921a.setOnCancelListener(new c(this));
        this.f5921a.show();
    }

    public void b() {
        if (this.f5921a != null) {
            this.f5921a.cancel();
        }
    }

    public void c() {
        if (this.f5921a != null) {
            this.f5921a.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.b != null) {
            this.b.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.b != null) {
            this.b.b(this);
        }
    }

    /* access modifiers changed from: protected */
    public Activity f() {
        if (this.b != null) {
            return this.b.c();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int g() {
        return (a((Context) f()) == 0 || Build.VERSION.SDK_INT < 16) ? 3 : 0;
    }

    private static int a(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", (String) null, (String) null);
    }
}
