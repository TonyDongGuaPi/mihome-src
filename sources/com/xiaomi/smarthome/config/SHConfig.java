package com.xiaomi.smarthome.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.Map;

public class SHConfig {
    private static SHConfig c;

    /* renamed from: a  reason: collision with root package name */
    private Context f13952a = CommonApplication.getAppContext();
    private SharedPreferences b;

    public static SHConfig a() {
        if (c == null) {
            c = new SHConfig();
        }
        return c;
    }

    private SHConfig() {
        if (CoreApi.a().q()) {
            SharedPreferences sharedPreferences = this.f13952a.getSharedPreferences("SHConfig", 0);
            Context context = this.f13952a;
            this.b = context.getSharedPreferences("SHConfig_" + CoreApi.a().s(), 0);
            Map<String, ?> all = sharedPreferences.getAll();
            if (all != null) {
                SharedPreferences.Editor edit = this.b.edit();
                for (Map.Entry next : all.entrySet()) {
                    Object value = next.getValue();
                    if (value instanceof Integer) {
                        edit.putInt((String) next.getKey(), ((Integer) next.getValue()).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong((String) next.getKey(), ((Long) next.getValue()).longValue());
                    } else if (value instanceof String) {
                        edit.putString((String) next.getKey(), (String) next.getValue());
                    }
                }
                edit.commit();
            }
            SharedPreferences.Editor edit2 = sharedPreferences.edit();
            edit2.clear();
            edit2.commit();
            return;
        }
        this.b = this.f13952a.getSharedPreferences("SHConfig", 0);
    }

    public void b() {
        if (CoreApi.a().q()) {
            Context appContext = CommonApplication.getAppContext();
            this.b = appContext.getSharedPreferences("SHConfig_" + CoreApi.a().s(), 0);
            return;
        }
        this.b = CommonApplication.getAppContext().getSharedPreferences("SHConfig", 0);
    }

    public int a(String str) {
        return this.b.getInt(str, -1);
    }

    public void a(int i, String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putInt(str, i);
        edit.apply();
    }

    public long b(String str) {
        return this.b.getLong(str, -1);
    }

    public void a(String str, int i) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putInt(str, i);
        edit.apply();
    }

    public void a(String str, long j) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putLong(str, j);
        edit.apply();
    }

    public String c(String str) {
        return this.b.getString(str, "");
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putString(str, str2);
        edit.apply();
    }
}
