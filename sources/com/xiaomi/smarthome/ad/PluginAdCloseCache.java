package com.xiaomi.smarthome.ad;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.ad.api.Advertisement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PluginAdCloseCache {

    /* renamed from: a  reason: collision with root package name */
    private static volatile PluginAdCloseCache f13630a = null;
    private static final String b = "close_";
    private static final String c = "show_";
    private static final String d = "date_";
    private SharedPreferences e;

    public PluginAdCloseCache(Context context) {
        this.e = context.getSharedPreferences("xiaomi.plugin.ad.close", 0);
    }

    public static PluginAdCloseCache a(Context context) {
        if (f13630a == null) {
            synchronized (PluginAdCloseCache.class) {
                if (f13630a == null) {
                    f13630a = new PluginAdCloseCache(context);
                }
            }
        }
        return f13630a;
    }

    public void a(Advertisement advertisement) {
        SharedPreferences.Editor edit = this.e.edit();
        edit.putBoolean(b + String.valueOf(advertisement.a()), true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd", Locale.getDefault());
        edit.putString(b + advertisement.a() + d, simpleDateFormat.format(new Date()));
        edit.apply();
    }

    public void b(Advertisement advertisement) {
        SharedPreferences.Editor edit = this.e.edit();
        edit.putBoolean(c + String.valueOf(advertisement.a()), true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd", Locale.getDefault());
        edit.putString(c + advertisement.a() + d, simpleDateFormat.format(new Date()));
        edit.apply();
    }

    public boolean a(long j) {
        SharedPreferences sharedPreferences = this.e;
        return sharedPreferences.getBoolean(c + String.valueOf(j), false);
    }

    public boolean b(long j) {
        SharedPreferences sharedPreferences = this.e;
        return sharedPreferences.getBoolean(b + String.valueOf(j), false);
    }

    public String c(long j) {
        SharedPreferences sharedPreferences = this.e;
        return sharedPreferences.getString(c + j + d, "");
    }

    public String d(long j) {
        SharedPreferences sharedPreferences = this.e;
        return sharedPreferences.getString(b + j + d, "");
    }
}
