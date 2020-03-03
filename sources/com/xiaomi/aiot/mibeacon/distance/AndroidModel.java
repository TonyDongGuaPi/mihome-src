package com.xiaomi.aiot.mibeacon.distance;

import android.os.Build;
import com.alipay.sdk.util.i;
import com.xiaomi.aiot.mibeacon.logging.LogManager;

public class AndroidModel {
    private static final String e = "AndroidModel";

    /* renamed from: a  reason: collision with root package name */
    String f9974a;
    String b;
    String c;
    String d;

    public AndroidModel(String str, String str2, String str3, String str4) {
        this.f9974a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public static AndroidModel a() {
        return new AndroidModel(Build.VERSION.RELEASE, Build.ID, Build.MODEL, Build.MANUFACTURER);
    }

    public String b() {
        return this.f9974a;
    }

    public void a(String str) {
        this.f9974a = str;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public int a(AndroidModel androidModel) {
        int equalsIgnoreCase = this.d.equalsIgnoreCase(androidModel.d);
        if (equalsIgnoreCase == 1 && this.c.equals(androidModel.c)) {
            equalsIgnoreCase = 2;
        }
        if (equalsIgnoreCase == 2 && this.b.equals(androidModel.b)) {
            equalsIgnoreCase = 3;
        }
        if (equalsIgnoreCase == 3 && this.f9974a.equals(androidModel.f9974a)) {
            equalsIgnoreCase = 4;
        }
        LogManager.b(e, "Score is %s for %s compared to %s", Integer.valueOf(equalsIgnoreCase), toString(), androidModel);
        return equalsIgnoreCase;
    }

    public String toString() {
        return "" + this.d + i.b + this.c + i.b + this.b + i.b + this.f9974a;
    }
}
