package com.huawei.hms.update.a;

import android.content.Context;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.c.e;
import com.huawei.hms.update.f.a;

class o {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5916a;
    private int b;
    private String c;

    public o(Context context) {
        if (context != null) {
            this.f5916a = context;
            c();
            return;
        }
        throw new NullPointerException("context must not be null.");
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    private void c() {
        e eVar = new e(this.f5916a);
        int b2 = eVar.b(HuaweiApiAvailability.SERVICES_PACKAGE);
        String c2 = eVar.c(HuaweiApiAvailability.SERVICES_PACKAGE);
        if (b2 == 0 || c2.isEmpty() || eVar.a(HuaweiApiAvailability.SERVICES_PACKAGE) == e.a.NOT_INSTALLED) {
            this.b = 20101000;
            d();
            return;
        }
        this.b = b2;
        if (c2.endsWith("OVE")) {
            this.c = c2;
        } else if (c2.endsWith("EU")) {
            this.c = "2.1.1.0_OVE";
        } else if (b2 < 20101302) {
            d();
        } else {
            this.c = c2;
        }
    }

    private void d() {
        if (a.c(this.f5916a)) {
            this.c = "2.1.1.0";
        } else {
            this.c = "2.1.1.0_OVE";
        }
    }
}
