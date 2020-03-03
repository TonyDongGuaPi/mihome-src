package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import com.amap.api.services.core.AMapException;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.a;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.c.e;
import com.huawei.hms.c.g;
import com.huawei.hms.update.a.a.b;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.a.a.d;
import com.huawei.hms.update.a.f;
import com.huawei.hms.update.a.i;
import com.huawei.hms.update.e.e;
import com.huawei.hms.update.e.m;
import com.huawei.hms.update.provider.UpdateProvider;
import java.io.File;
import java.lang.ref.WeakReference;

public class q extends a implements a, b {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<Activity> f5933a;
    private com.huawei.hms.update.a.a.a b;
    private b c;
    private c d;
    private int e = -1;

    public void a(Activity activity, boolean z) {
        this.f5933a = new WeakReference<>(activity);
        if (z) {
            a((Class<? extends b>) i.class);
        } else {
            e();
        }
    }

    public void a() {
        g();
        j();
        com.huawei.hms.update.c.a.a((Class<?>) null);
        this.f5933a = null;
    }

    public int d() {
        if (this.e == 1) {
            return 2001;
        }
        if (this.e == 2) {
            return 2002;
        }
        if (this.e == 3) {
            return 2003;
        }
        return 2001;
    }

    public boolean a(int i, int i2, Intent intent) {
        Activity c2 = c();
        if (c2 == null || c2.isFinishing()) {
            return false;
        }
        if (this.e == 1 && i == 2001) {
            if (a(c2)) {
                a(0);
            } else {
                a(8);
            }
            return true;
        } else if (this.e == 2 && i == 2002) {
            if (a(c2)) {
                a(0);
            } else {
                a(8, this.e);
                b(c2);
            }
            return true;
        } else if (this.e != 3 || i != 2003) {
            return false;
        } else {
            if (a(c2)) {
                a(0);
            } else {
                a(8);
            }
            return true;
        }
    }

    private boolean a(Activity activity) {
        return new e(activity).b(HuaweiApiAvailability.SERVICES_PACKAGE) >= 20502300;
    }

    public void b() {
        if (this.c != null) {
            Class<?> cls = this.c.getClass();
            this.c.c();
            this.c = null;
            a((Class<? extends b>) cls);
        }
    }

    public void a(int i, c cVar) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("UpdateWizard", "Enter onCheckUpdate, status: " + d.a(i));
        }
        if (i == 1000) {
            this.d = cVar;
            a((Class<? extends b>) h.class);
            i();
        } else if (i != 1101) {
            switch (i) {
                case 1201:
                case 1202:
                case 1203:
                    a((Class<? extends b>) m.b.class);
                    return;
                default:
                    return;
            }
        }
    }

    public void a(int i, int i2, int i3, File file) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("UpdateWizard", "Enter onDownloadPackage, status: " + d.a(i) + ", reveived: " + i2 + ", total: " + i3);
        }
        if (i != 2000) {
            switch (i) {
                case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                    if (this.c != null && (this.c instanceof h)) {
                        ((h) this.c).a(i2, i3);
                        return;
                    }
                    return;
                case 2101:
                    return;
                default:
                    switch (i) {
                        case 2201:
                            if (!(this.d == null || this.b == null)) {
                                this.d.c(this.b.a());
                            }
                            a((Class<? extends b>) m.c.class);
                            return;
                        case AMapException.CODE_AMAP_CLIENT_NEARBY_NULL_RESULT:
                            a((Class<? extends b>) e.b.class);
                            return;
                        case AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT:
                        case AMapException.CODE_AMAP_CLIENT_UPLOAD_LOCATION_ERROR:
                            a((Class<? extends b>) m.d.class);
                            return;
                        default:
                            return;
                    }
            }
        } else {
            g();
            if (file == null) {
                a(8);
            } else {
                a(file);
            }
        }
    }

    private void a(File file) {
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            if (!new com.huawei.hms.c.e(c2).a(file.toString(), HuaweiApiAvailability.SERVICES_PACKAGE, HuaweiApiAvailability.SERVICES_SIGNATURE)) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to verify package archive.");
                a(8);
                return;
            }
            Uri a2 = a((Context) c2, file);
            if (a2 == null) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to creates a Uri from a file.");
                a(8);
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(a2, "application/vnd.android.package-archive");
            intent.setFlags(3);
            try {
                c2.startActivityForResult(intent, d());
            } catch (ActivityNotFoundException e2) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to start package installer." + e2.getMessage());
                a(8);
            }
        }
    }

    private static Uri a(Context context, File file) {
        com.huawei.hms.c.e eVar = new com.huawei.hms.c.e(context);
        String packageName = context.getPackageName();
        String str = packageName + UpdateProvider.AUTHORITIES_SUFFIX;
        boolean z = true;
        if (Build.VERSION.SDK_INT <= 23 || (context.getApplicationInfo().targetSdkVersion <= 23 && !eVar.a(packageName, str))) {
            z = false;
        }
        if (z) {
            return UpdateProvider.getUriForFile(context, str, file);
        }
        return Uri.fromFile(file);
    }

    /* access modifiers changed from: package-private */
    public Activity c() {
        if (this.f5933a == null) {
            return null;
        }
        return (Activity) this.f5933a.get();
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        com.huawei.hms.support.log.a.b("UpdateWizard", "Enter onCancel.");
        if (bVar instanceof i) {
            if (g.a()) {
                this.e = 1;
            } else {
                this.e = 2;
            }
            a(13);
        } else if (bVar instanceof d) {
            j();
            a(13);
        } else if (bVar instanceof h) {
            j();
            a((Class<? extends b>) e.c.class);
        } else if (bVar instanceof e.c) {
            a((Class<? extends b>) h.class);
            i();
        } else if (bVar instanceof e.b) {
            a(13);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(b bVar) {
        com.huawei.hms.support.log.a.b("UpdateWizard", "Enter onDoWork.");
        if (bVar instanceof i) {
            bVar.c();
            e();
        } else if (bVar instanceof e.c) {
            bVar.c();
            a(13);
        } else if (bVar instanceof e.b) {
            a((Class<? extends b>) h.class);
            i();
        } else if (bVar instanceof m.b) {
            a(8);
        } else if (bVar instanceof m.c) {
            a(8);
        } else if (bVar instanceof m.d) {
            a(8);
        }
    }

    private void e() {
        if (g.a()) {
            this.e = 1;
            a((Class<? extends b>) d.class);
            h();
            return;
        }
        f();
    }

    private void f() {
        this.e = 2;
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.huawei.hwid"));
                intent.setPackage("com.android.vending");
                c2.startActivityForResult(intent, d());
            } catch (ActivityNotFoundException unused) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "can not open google play");
            }
        }
    }

    private void b(Activity activity) {
        this.e = 3;
        try {
            activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.huawei.hwid")), d());
        } catch (ActivityNotFoundException unused) {
            com.huawei.hms.support.log.a.d("UpdateWizard", "can not find web to hold update hms apk");
        }
    }

    private void a(Class<? extends b> cls) {
        g();
        try {
            b bVar = (b) cls.newInstance();
            bVar.a((a) this);
            this.c = bVar;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e2) {
            com.huawei.hms.support.log.a.d("UpdateWizard", "In showDialog, Failed to show the dialog." + e2.getMessage());
        }
    }

    private void g() {
        if (this.c != null) {
            try {
                this.c.c();
                this.c = null;
            } catch (IllegalStateException e2) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "In dismissDialog, Failed to dismiss the dialog." + e2.getMessage());
            }
        }
    }

    private void h() {
        this.d = null;
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            j();
            this.b = new i(new com.huawei.hms.update.a.e(c2));
            this.b.a(this);
        }
    }

    private void i() {
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            j();
            this.b = new i(new f(c2));
            this.b.a(this, this.d);
        }
    }

    private void j() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
    }

    private void a(int i) {
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            a(i, this.e);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            c2.setResult(-1, intent);
            c2.finish();
        }
    }

    public void a(int i, KeyEvent keyEvent) {
        if (4 == i) {
            com.huawei.hms.support.log.a.b("UpdateWizard", "In onKeyUp, Call finish.");
            Activity c2 = c();
            if (c2 != null && !c2.isFinishing()) {
                c2.setResult(0, (Intent) null);
                c2.finish();
            }
        }
    }
}
