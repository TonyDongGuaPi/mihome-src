package com.mijia.model.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.mijia.camera.Utils.Util;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public class LocalSettings {
    public static final String b = "only_wifi";
    public static final String c = "local_reset";
    private static final long e = 259200000;

    /* renamed from: a  reason: collision with root package name */
    public boolean f8058a = true;
    public long d = -1;
    private String f;
    private SharedPreferences g;
    private SharedPreferences h;
    private SharedPreferences i;
    private int j;
    private boolean k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private long r = 0;
    private int s;
    private boolean t;
    private boolean u = false;
    private int v = -1;
    private String w;

    public int a() {
        return this.j;
    }

    public void a(int i2) {
        if (i2 != this.j) {
            this.j = i2;
            this.g.edit().putInt(ViewProps.ROTATION, this.j).apply();
        }
    }

    public void a(boolean z) {
        if (this.o != z) {
            this.o = z;
            this.g.edit().putBoolean("correct_lens_distort", this.o).apply();
        }
    }

    public void b(boolean z) {
        if (this.t != z) {
            this.t = z;
            this.g.edit().putBoolean(b, this.t).apply();
        }
    }

    public boolean b() {
        return this.t;
    }

    public boolean c() {
        return this.o;
    }

    public LocalSettings(String str, String str2, String str3) {
        Context context = XmPluginHostApi.instance().context();
        this.g = context.getSharedPreferences("mijia_camera_" + str, 0);
        Context context2 = XmPluginHostApi.instance().context();
        this.h = context2.getSharedPreferences(str3 + str2, 0);
        Context context3 = XmPluginHostApi.instance().context();
        this.i = context3.getSharedPreferences(str + str2, 0);
        this.o = this.g.getBoolean("correct_lens_distort", true);
        this.j = this.g.getInt(ViewProps.ROTATION, 0);
        this.s = this.g.getInt("video_quality", 0);
        this.t = this.g.getBoolean(b, true);
        this.k = this.g.getBoolean("rote_save", false);
        this.m = this.g.getBoolean("privacy_need", true);
        this.n = this.g.getBoolean("alarm_guide", true);
        this.l = this.h.getBoolean("need_guide", true);
        this.p = this.h.getBoolean("need_cloud_guide", true);
        this.q = this.g.getBoolean("has_cloud_video_tips_clicked", false);
        this.v = this.g.getInt("cloud_vip", -1);
        this.w = this.g.getString("cloudVipEnd", "");
        this.f = this.i.getString("alarm_event_type", "Default");
    }

    public void b(int i2) {
        if (this.s != i2) {
            this.s = i2;
            this.g.edit().putInt("video_quality", this.s).apply();
        }
    }

    public int d() {
        return this.s;
    }

    public void c(boolean z) {
        this.g.edit().putBoolean("is_valid_cloud_video_user", z).apply();
    }

    public void d(boolean z) {
        this.g.edit().putBoolean("is_in_expire_window", !z).apply();
    }

    public boolean e() {
        return this.g.getBoolean("is_in_expire_window", false);
    }

    public boolean f() {
        if (!Util.e()) {
            return false;
        }
        this.u = this.g.getBoolean("is_valid_cloud_video_user", false);
        return this.u;
    }

    public boolean g() {
        return this.k;
    }

    public void e(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.g.edit().putBoolean("rote_save", z).apply();
        }
    }

    public boolean h() {
        return this.l;
    }

    public void f(boolean z) {
        if (this.l != z) {
            this.l = z;
            this.h.edit().putBoolean("need_guide", z).apply();
        }
    }

    public boolean i() {
        return this.m;
    }

    public void g(boolean z) {
        if (this.m != z) {
            this.m = z;
            this.g.edit().putBoolean("privacy_need", z).apply();
        }
    }

    public boolean j() {
        return this.n;
    }

    public void h(boolean z) {
        if (this.n != z) {
            this.n = z;
            this.g.edit().putBoolean("alarm_guide", z).apply();
        }
    }

    public long k() {
        return this.d;
    }

    public void a(long j2) {
        if (this.d != j2) {
            this.d = j2;
            this.g.edit().putLong("newAlarmFirstTime", j2).apply();
        }
    }

    public boolean l() {
        return this.p;
    }

    public void i(boolean z) {
        if (this.p != z) {
            this.p = z;
            this.h.edit().putBoolean("need_cloud_guide", z).apply();
        }
    }

    public boolean m() {
        return this.q;
    }

    public void j(boolean z) {
        if (this.q != z) {
            this.q = z;
            this.g.edit().putBoolean("has_cloud_video_tips_clicked", z).apply();
        }
    }

    public long n() {
        return this.r;
    }

    public void b(long j2) {
        this.r = j2;
        this.h.edit().putLong("first_cloud_video_show_time", j2).apply();
    }

    public boolean o() {
        if (m()) {
            return false;
        }
        long n2 = n();
        if (n2 == 0) {
            return true;
        }
        if (n2 <= 0 || System.currentTimeMillis() - n2 >= e) {
            return false;
        }
        return true;
    }

    public int p() {
        return this.v;
    }

    public void c(int i2) {
        this.v = i2;
    }

    public String q() {
        return this.w;
    }

    public void a(String str) {
        if (!str.equals(this.w)) {
            this.w = str;
            this.g.edit().putString("cloudVipEnd", str).apply();
        }
    }

    public String r() {
        return this.f;
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str) && !this.f.equals(str)) {
            this.f = str;
            this.i.edit().putString("alarm_event_type", str).apply();
        }
    }
}
