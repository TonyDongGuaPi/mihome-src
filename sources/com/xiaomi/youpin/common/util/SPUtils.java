package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@SuppressLint({"ApplySharedPref"})
public final class SPUtils {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleArrayMap<String, SPUtils> f23268a = new SimpleArrayMap<>();
    private SharedPreferences b;

    public static SPUtils a() {
        return a("", 0);
    }

    public static SPUtils a(int i) {
        return a("", i);
    }

    public static SPUtils a(String str) {
        return a(str, 0);
    }

    public static SPUtils a(String str, int i) {
        if (j(str)) {
            str = "spUtils";
        }
        SPUtils sPUtils = f23268a.get(str);
        if (sPUtils != null) {
            return sPUtils;
        }
        SPUtils sPUtils2 = new SPUtils(str, i);
        f23268a.put(str, sPUtils2);
        return sPUtils2;
    }

    private SPUtils(String str) {
        this.b = Utils.a().getSharedPreferences(str, 0);
    }

    private SPUtils(String str, int i) {
        this.b = Utils.a().getSharedPreferences(str, i);
    }

    public void a(@NonNull String str, String str2) {
        a(str, str2, false);
    }

    public void a(@NonNull String str, String str2, boolean z) {
        if (z) {
            this.b.edit().putString(str, str2).commit();
        } else {
            this.b.edit().putString(str, str2).apply();
        }
    }

    public String b(@NonNull String str) {
        return b(str, "");
    }

    public String b(@NonNull String str, String str2) {
        return this.b.getString(str, str2);
    }

    public void b(@NonNull String str, int i) {
        a(str, i, false);
    }

    public void a(@NonNull String str, int i, boolean z) {
        if (z) {
            this.b.edit().putInt(str, i).commit();
        } else {
            this.b.edit().putInt(str, i).apply();
        }
    }

    public int c(@NonNull String str) {
        return c(str, -1);
    }

    public int c(@NonNull String str, int i) {
        return this.b.getInt(str, i);
    }

    public void a(@NonNull String str, long j) {
        a(str, j, false);
    }

    public void a(@NonNull String str, long j, boolean z) {
        if (z) {
            this.b.edit().putLong(str, j).commit();
        } else {
            this.b.edit().putLong(str, j).apply();
        }
    }

    public long d(@NonNull String str) {
        return b(str, -1);
    }

    public long b(@NonNull String str, long j) {
        return this.b.getLong(str, j);
    }

    public void a(@NonNull String str, float f) {
        a(str, f, false);
    }

    public void a(@NonNull String str, float f, boolean z) {
        if (z) {
            this.b.edit().putFloat(str, f).commit();
        } else {
            this.b.edit().putFloat(str, f).apply();
        }
    }

    public float e(@NonNull String str) {
        return b(str, -1.0f);
    }

    public float b(@NonNull String str, float f) {
        return this.b.getFloat(str, f);
    }

    public void a(@NonNull String str, boolean z) {
        a(str, z, false);
    }

    public void a(@NonNull String str, boolean z, boolean z2) {
        if (z2) {
            this.b.edit().putBoolean(str, z).commit();
        } else {
            this.b.edit().putBoolean(str, z).apply();
        }
    }

    public boolean f(@NonNull String str) {
        return b(str, false);
    }

    public boolean b(@NonNull String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public void a(@NonNull String str, Set<String> set) {
        a(str, set, false);
    }

    public void a(@NonNull String str, Set<String> set, boolean z) {
        if (z) {
            this.b.edit().putStringSet(str, set).commit();
        } else {
            this.b.edit().putStringSet(str, set).apply();
        }
    }

    public Set<String> g(@NonNull String str) {
        return b(str, (Set<String>) Collections.emptySet());
    }

    public Set<String> b(@NonNull String str, Set<String> set) {
        return this.b.getStringSet(str, set);
    }

    public Map<String, ?> b() {
        return this.b.getAll();
    }

    public boolean h(@NonNull String str) {
        return this.b.contains(str);
    }

    public void i(@NonNull String str) {
        c(str, false);
    }

    public void c(@NonNull String str, boolean z) {
        if (z) {
            this.b.edit().remove(str).commit();
        } else {
            this.b.edit().remove(str).apply();
        }
    }

    public void c() {
        a(false);
    }

    public void a(boolean z) {
        if (z) {
            this.b.edit().clear().commit();
        } else {
            this.b.edit().clear().apply();
        }
    }

    private static boolean j(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
