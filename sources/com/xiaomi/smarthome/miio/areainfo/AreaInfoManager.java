package com.xiaomi.smarthome.miio.areainfo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.util.List;

public class AreaInfoManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11897a = "update_area_info_action";
    public static final String b = "request_area_info_action";
    public static final String c = "update_area_fail";
    public static final String d = "location_shared_prefs";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final long i = 3600000;
    private static final Object j = new Object();
    private static AreaInfoManager k;
    private MainlandAreaInfo l = new MainlandAreaInfo();
    private MainlandAreaInfoOld m = new MainlandAreaInfoOld();
    private String n;

    public static AreaInfoManager a() {
        AreaInfoManager areaInfoManager;
        synchronized (j) {
            if (k == null) {
                k = new AreaInfoManager();
            }
            areaInfoManager = k;
        }
        return areaInfoManager;
    }

    private AreaInfoManager() {
        c();
    }

    public void b() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(b));
    }

    public void c() {
        this.l = new MainlandAreaInfo();
        this.m = new MainlandAreaInfoOld();
    }

    public void d() {
        synchronized (j) {
            this.l.B();
            k = null;
        }
    }

    public int e() {
        return I().b();
    }

    public String f() {
        return I().c();
    }

    public String g() {
        return I().d();
    }

    public String h() {
        return I().e();
    }

    public int a(int i2) {
        return I().a(i2);
    }

    public List<Integer> i() {
        return I().f();
    }

    public boolean b(int i2) {
        return I().b(i2);
    }

    public boolean c(int i2) {
        return I().c(i2);
    }

    public boolean j() {
        return I().g();
    }

    public String a(Home home) {
        return (home == null || home != HomeManager.a().m()) ? "" : I().h();
    }

    public String b(Home home) {
        return (home == null || home != HomeManager.a().m()) ? "" : I().i();
    }

    public String k() {
        return I().h();
    }

    public String l() {
        return I().i();
    }

    public String m() {
        return I().j();
    }

    public String n() {
        return I().k();
    }

    public String o() {
        return I().l();
    }

    public String p() {
        return I().m();
    }

    public String q() {
        return I().n();
    }

    public String r() {
        int z = I().z();
        if (z < 0) {
            return "-";
        }
        return z + "";
    }

    public String s() {
        return I().o();
    }

    public String t() {
        return I().p();
    }

    public String u() {
        return I().q();
    }

    public String v() {
        return I().r();
    }

    public String w() {
        return I().s();
    }

    public String x() {
        return I().t();
    }

    public String y() {
        return I().u();
    }

    public String z() {
        return I().v();
    }

    public String A() {
        return I().w();
    }

    public String B() {
        return I().x();
    }

    public String C() {
        return I().y();
    }

    public String D() {
        Integer valueOf = Integer.valueOf(I().z());
        if (valueOf.intValue() == -1) {
            return "-";
        }
        if (valueOf.intValue() <= 50) {
            return SHApplication.getAppContext().getString(R.string.air_50);
        }
        if (valueOf.intValue() <= 100) {
            return SHApplication.getAppContext().getString(R.string.air_100);
        }
        if (valueOf.intValue() <= 150) {
            return SHApplication.getAppContext().getString(R.string.air_150);
        }
        if (valueOf.intValue() <= 200) {
            return SHApplication.getAppContext().getString(R.string.air_200);
        }
        if (valueOf.intValue() <= 300) {
            return SHApplication.getAppContext().getString(R.string.air_300);
        }
        return SHApplication.getAppContext().getString(R.string.air_x);
    }

    public int E() {
        Integer valueOf = Integer.valueOf(I().z());
        if (valueOf.intValue() == -1 || valueOf.intValue() <= 50) {
            return R.drawable.location_bg_weather_50;
        }
        if (valueOf.intValue() <= 100) {
            return R.drawable.location_bg_weather_100;
        }
        if (valueOf.intValue() <= 150) {
            return R.drawable.location_bg_weather_150;
        }
        if (valueOf.intValue() <= 200) {
            return R.drawable.location_bg_weather_200;
        }
        return valueOf.intValue() <= 300 ? R.drawable.location_bg_weather_300 : R.drawable.location_bg_weather_x;
    }

    public int F() {
        Integer valueOf = Integer.valueOf(I().z());
        int intValue = valueOf.intValue();
        int i2 = R.color.location_weather_50;
        if (intValue != -1 && valueOf.intValue() > 50) {
            i2 = valueOf.intValue() <= 100 ? R.color.location_weather_100 : valueOf.intValue() <= 150 ? R.color.location_weather_150 : valueOf.intValue() <= 200 ? R.color.location_weather_200 : valueOf.intValue() <= 300 ? R.color.location_weather_300 : R.color.location_weather_x;
        }
        return SHApplication.getAppContext().getResources().getColor(i2);
    }

    public int G() {
        Integer valueOf = Integer.valueOf(I().z());
        int intValue = valueOf.intValue();
        int i2 = R.color.location_weather_50_2;
        if (intValue != -1 && valueOf.intValue() > 50) {
            i2 = valueOf.intValue() <= 100 ? R.color.location_weather_100_2 : valueOf.intValue() <= 150 ? R.color.location_weather_150_2 : valueOf.intValue() <= 200 ? R.color.location_weather_200_2 : valueOf.intValue() <= 300 ? R.color.location_weather_300_2 : R.color.location_weather_x_2;
        }
        return SHApplication.getAppContext().getResources().getColor(i2);
    }

    public int a(boolean z) {
        int c2 = I().c(z);
        if (c2 == -1) {
            return z ? R.drawable.std_home_icon_noweather_white : R.drawable.std_home_icon_noweather_black;
        }
        return c2;
    }

    public void a(Context context, boolean z) {
        I().a(context, z);
    }

    public void a(Context context) {
        I().a(context);
    }

    public void b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("yellowpage://openweb?url=http://jump.luna.58.com/s?spm=b-24788170229511-ms-f-xiaomi&ch=huangye"));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }

    public AreaPropInfo H() {
        return I().A();
    }

    public static String[] a(String str) {
        if (StringUtil.c(str)) {
            return null;
        }
        return str.split(Constants.J);
    }

    public AreaInfo I() {
        if (CoreApi.a().D() || !HomeManager.a().h() || HomeManager.a().m() == null) {
            return this.m;
        }
        if (this.n == null) {
            this.n = HomeManager.a().l();
        } else if (!TextUtils.equals(this.n, HomeManager.a().l())) {
            this.n = HomeManager.a().l();
            this.l = new MainlandAreaInfo();
        }
        return this.l;
    }
}
