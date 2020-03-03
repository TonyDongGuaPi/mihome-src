package com.xiaomi.smarthome.mibrain.viewutil.floatview;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class MiBrainPreferenceManager {

    /* renamed from: a  reason: collision with root package name */
    private Context f10733a;

    public MiBrainPreferenceManager(Context context) {
        this.f10733a = context;
    }

    private SharedPreferences f() {
        if (!CoreApi.a().q()) {
            return this.f10733a.getSharedPreferences(MiBrainFloatViewConfig.f10732a, 0);
        }
        Context context = this.f10733a;
        return context.getSharedPreferences(CoreApi.a().s() + MiBrainFloatViewConfig.f10732a, 0);
    }

    private SharedPreferences.Editor g() {
        return f().edit();
    }

    public float a() {
        return f().getFloat(MiBrainFloatViewConfig.b, -1.0f);
    }

    public void a(float f) {
        SharedPreferences.Editor g = g();
        g.putFloat(MiBrainFloatViewConfig.b, f);
        g.commit();
    }

    public float b() {
        return f().getFloat(MiBrainFloatViewConfig.c, -1.0f);
    }

    public void b(float f) {
        SharedPreferences.Editor g = g();
        g.putFloat(MiBrainFloatViewConfig.c, f);
        g.commit();
    }

    public boolean c() {
        return f().getBoolean(MiBrainFloatViewConfig.d, true);
    }

    public void a(boolean z) {
        SharedPreferences.Editor g = g();
        g.putBoolean(MiBrainFloatViewConfig.d, z);
        g.commit();
    }

    public boolean d() {
        return f().getBoolean(MiBrainFloatViewConfig.e, true);
    }

    public void b(boolean z) {
        SharedPreferences.Editor g = g();
        g.putBoolean(MiBrainFloatViewConfig.e, z);
        g.commit();
    }

    public boolean e() {
        return f().getBoolean(MiBrainFloatViewConfig.f, true);
    }

    public void c(boolean z) {
        SharedPreferences.Editor g = g();
        g.putBoolean(MiBrainFloatViewConfig.f, false);
        g.commit();
    }
}
