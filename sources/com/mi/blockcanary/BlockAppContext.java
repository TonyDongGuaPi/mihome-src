package com.mi.blockcanary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.NetworkUtils;
import com.taobao.weex.annotation.JSMethod;
import java.util.List;

public class BlockAppContext extends BlockCanaryContext {
    private static final String b = "AppContext";

    /* renamed from: a  reason: collision with root package name */
    Context f6736a;

    public boolean d() {
        return false;
    }

    public boolean g() {
        return true;
    }

    public boolean h() {
        return true;
    }

    public BlockAppContext(Context context) {
        this.f6736a = context;
    }

    public String a() {
        try {
            PackageInfo packageInfo = this.f6736a.getPackageManager().getPackageInfo(this.f6736a.getPackageName(), 0);
            return "" + packageInfo.versionCode + JSMethod.NOT_SET + packageInfo.versionName + "_YYB";
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(b, "provideQualifier exception", e);
            return "";
        }
    }

    public String b() {
        return ApplicationContextHolder.f();
    }

    public String c() {
        return NetworkUtils.b(this.f6736a.getApplicationContext());
    }

    public List<String> e() {
        List<String> f = super.f();
        f.add(this.f6736a.getPackageName());
        return f;
    }

    public List<String> f() {
        List<String> f = super.f();
        f.add("com.whitelist");
        return f;
    }
}
