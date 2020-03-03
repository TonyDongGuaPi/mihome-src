package com.huawei.hms.api.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.KeyEvent;
import com.huawei.android.hms.base.R;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.b.f;
import com.huawei.hms.c.g;

public class a implements ServiceConnection, com.huawei.hms.activity.a {

    /* renamed from: a  reason: collision with root package name */
    private Activity f5856a;
    private boolean b = true;
    /* access modifiers changed from: private */
    public C0051a c;
    private Handler d = null;

    public int c() {
        return 2003;
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public void a(Activity activity, boolean z) {
        this.f5856a = activity;
        d.f5859a.a(this.f5856a);
        a(activity);
    }

    private void a(Activity activity) {
        Intent intent = new Intent();
        intent.setClassName(HuaweiApiAvailability.SERVICES_PACKAGE, HuaweiApiAvailability.ACTIVITY_NAME);
        try {
            activity.startActivityForResult(intent, c());
        } catch (ActivityNotFoundException e) {
            com.huawei.hms.support.log.a.d("BindingFailedResolution", "ActivityNotFoundException：" + e.getMessage());
            e();
        }
    }

    public void a() {
        h();
        d.f5859a.b(this.f5856a);
        this.f5856a = null;
    }

    public boolean a(int i, int i2, Intent intent) {
        if (i != c()) {
            return false;
        }
        e();
        return true;
    }

    private void e() {
        if (f()) {
            g();
            return;
        }
        com.huawei.hms.support.log.a.d("BindingFailedResolution", "In connect, bind core try fail");
        b(false);
    }

    public void b() {
        if (this.c != null) {
            com.huawei.hms.support.log.a.b("BindingFailedResolution", "re show prompt dialog");
            i();
        }
    }

    public void a(int i, KeyEvent keyEvent) {
        com.huawei.hms.support.log.a.b("BindingFailedResolution", "On key up when resolve conn error");
    }

    /* access modifiers changed from: protected */
    public Activity d() {
        return this.f5856a;
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        if (this.b) {
            this.b = false;
            a(z);
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        if (d() != null) {
            if (z) {
                a(0);
            } else {
                i();
            }
        }
    }

    /* renamed from: com.huawei.hms.api.internal.a$a  reason: collision with other inner class name */
    private static class C0051a extends f {
        private C0051a() {
        }

        /* synthetic */ C0051a(b bVar) {
            this();
        }

        /* access modifiers changed from: protected */
        public String a(Context context) {
            String a2 = g.a(context, (String) null);
            String a3 = g.a(context, HuaweiApiAvailability.SERVICES_PACKAGE);
            return context.getResources().getString(R.string.hms_bindfaildlg_message, new Object[]{a2, a3});
        }

        /* access modifiers changed from: protected */
        public String b(Context context) {
            return context.getResources().getString(R.string.hms_confirm);
        }
    }

    private boolean f() {
        Activity d2 = d();
        if (d2 == null) {
            return false;
        }
        Intent intent = new Intent(HuaweiApiAvailability.SERVICES_ACTION);
        intent.setPackage(HuaweiApiAvailability.SERVICES_PACKAGE);
        return d2.bindService(intent, this, 1);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        h();
        b(true);
        Activity d2 = d();
        if (d2 != null) {
            g.a((Context) d2, (ServiceConnection) this);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        Activity d2 = d();
        if (d2 != null && !d2.isFinishing()) {
            com.huawei.hms.support.log.a.b("BindingFailedResolution", "finishBridgeActivity：" + i);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            d2.setResult(-1, intent);
            d2.finish();
        }
    }

    private void g() {
        if (this.d != null) {
            this.d.removeMessages(2);
        } else {
            this.d = new Handler(Looper.getMainLooper(), new b(this));
        }
        this.d.sendEmptyMessageDelayed(2, 3000);
    }

    private void h() {
        if (this.d != null) {
            this.d.removeMessages(2);
            this.d = null;
        }
    }

    private void i() {
        Activity d2 = d();
        if (d2 != null && !d2.isFinishing()) {
            if (this.c == null) {
                this.c = new C0051a((b) null);
            } else {
                this.c.b();
            }
            com.huawei.hms.support.log.a.d("BindingFailedResolution", "showPromptdlg to resolve conn error");
            this.c.a(d2, new c(this));
        }
    }
}
