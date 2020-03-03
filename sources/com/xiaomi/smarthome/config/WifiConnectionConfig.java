package com.xiaomi.smarthome.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WifiConnectionConfig {
    private static final int c = 100;
    private static WifiConnectionConfig d;

    /* renamed from: a  reason: collision with root package name */
    private Context f13954a = SHApplication.getAppContext();
    private SharedPreferences b;

    public static WifiConnectionConfig a() {
        if (d == null) {
            d = new WifiConnectionConfig();
        }
        return d;
    }

    private WifiConnectionConfig() {
    }

    private void e() {
        if (this.b == null) {
            a(this.f13954a);
        }
    }

    private void a(Context context) {
        if (CoreApi.a().q()) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("WifiConnectionConfig_v2", 0);
            this.b = context.getSharedPreferences("WifiConnectionConfig_v2_" + CoreApi.a().s(), 0);
            Map<String, ?> all = sharedPreferences.getAll();
            if (all != null) {
                SharedPreferences.Editor edit = this.b.edit();
                for (Map.Entry next : all.entrySet()) {
                    edit.putString((String) next.getKey(), (String) next.getValue());
                }
                edit.commit();
            }
            SharedPreferences.Editor edit2 = sharedPreferences.edit();
            edit2.clear();
            edit2.commit();
            return;
        }
        this.b = SHApplication.getAppContext().getSharedPreferences("WifiConnectionConfig_v2", 0);
    }

    public void b() {
        if (CoreApi.a().q()) {
            Context appContext = SHApplication.getAppContext();
            this.b = appContext.getSharedPreferences("WifiConnectionConfig_v2_" + CoreApi.a().s(), 0);
            return;
        }
        this.b = SHApplication.getAppContext().getSharedPreferences("WifiConnectionConfig_v2", 0);
    }

    public void a(String str) {
        e();
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(String.valueOf(System.currentTimeMillis()), str);
        edit.apply();
    }

    public void a(String str, String str2) {
        e();
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(String.valueOf(str), str2);
        edit.apply();
    }

    public TreeMap<String, String> c() {
        e();
        TreeMap<String, String> treeMap = new TreeMap<>(new Comparator() {
            public int compare(Object obj, Object obj2) {
                if (obj == null || obj2 == null) {
                    return 0;
                }
                return String.valueOf(obj2).compareTo(String.valueOf(obj));
            }
        });
        treeMap.putAll(this.b.getAll());
        return treeMap;
    }

    public void d() {
        e();
        SharedPreferences.Editor edit = this.b.edit();
        edit.clear();
        edit.commit();
    }
}
