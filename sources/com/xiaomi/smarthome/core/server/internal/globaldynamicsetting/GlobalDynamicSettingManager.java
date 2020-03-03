package com.xiaomi.smarthome.core.server.internal.globaldynamicsetting;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.util.Locale;

public class GlobalDynamicSettingManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14576a = "com.xiaomi.smarthome.globaldynamicsetting";
    public static final String b = "prefs_disclaim";
    private static GlobalDynamicSettingManager c;
    private static Object d = new Object();
    private boolean e = false;
    private SharedPreferences f;
    private SharedPreferences g;
    private Context h = CommonApplication.getAppContext();
    private boolean i;
    private boolean j;
    private ServerBean k;
    private String l;
    private Locale m;

    public interface IsCTAReadyCallback {
        void a();
    }

    private GlobalDynamicSettingManager() {
        i();
    }

    public static GlobalDynamicSettingManager a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new GlobalDynamicSettingManager();
                }
            }
        }
        return c;
    }

    public static GlobalDynamicSettingManager a(Context context) {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new GlobalDynamicSettingManager();
                }
            }
        }
        return c;
    }

    private void i() {
        boolean z;
        synchronized (d) {
            z = this.e;
            if (!this.e) {
                this.e = true;
            }
        }
        if (!z) {
            this.g = this.h.getSharedPreferences(b, 0);
            this.i = this.g.getBoolean(GlobalDynamicSettingConst.f14575a, true);
            this.j = this.g.getBoolean(GlobalDynamicSettingConst.c, false);
            this.f = this.h.getSharedPreferences(f14576a, 0);
            this.k = ServerCompact.b(this.h);
            this.l = this.f.getString(GlobalDynamicSettingConst.f, "release");
            String string = this.f.getString(GlobalDynamicSettingConst.g, "");
            String string2 = this.f.getString(GlobalDynamicSettingConst.h, "");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                h();
            } else {
                this.m = new Locale(string, string2);
            }
        }
    }

    public synchronized boolean b() {
        return this.i;
    }

    public synchronized boolean c() {
        return this.j;
    }

    public synchronized void a(boolean z, boolean z2) {
        this.i = z;
        this.j = z2;
        j();
    }

    private synchronized void j() {
        SharePrefsManager.a(this.g, GlobalDynamicSettingConst.f14575a, this.i);
        SharePrefsManager.a(this.g, GlobalDynamicSettingConst.b, this.i);
        SharePrefsManager.a(this.g, GlobalDynamicSettingConst.c, this.j);
    }

    @Nullable
    public synchronized ServerBean d() {
        return this.k;
    }

    public synchronized void a(ServerBean serverBean) {
        this.k = serverBean;
        k();
    }

    public synchronized boolean e() {
        return ServerCompact.b(this.k);
    }

    private synchronized void k() {
        ServerCompact.a(this.h, this.k);
    }

    public synchronized String f() {
        return this.l;
    }

    public synchronized void a(String str) {
        if (b(str)) {
            this.l = str;
            l();
        }
    }

    private synchronized void l() {
        if (!TextUtils.isEmpty(this.l)) {
            SharePrefsManager.a(this.f, GlobalDynamicSettingConst.f, this.l);
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equalsIgnoreCase("release") || str.equalsIgnoreCase("preview")) {
            return true;
        }
        return false;
    }

    public synchronized Locale g() {
        return this.m;
    }

    public synchronized void a(Locale locale) {
        if (locale != null) {
            this.m = locale;
            m();
        }
    }

    private synchronized void m() {
        if (this.m != null) {
            String language = this.m.getLanguage();
            String country = this.m.getCountry();
            SharePrefsManager.a(this.f, GlobalDynamicSettingConst.g, language);
            SharePrefsManager.a(this.f, GlobalDynamicSettingConst.h, country);
        }
    }

    public synchronized void h() {
        SharedPreferences.Editor edit = this.f.edit();
        edit.remove(GlobalDynamicSettingConst.g);
        edit.remove(GlobalDynamicSettingConst.h);
        edit.apply();
        this.m = null;
    }
}
