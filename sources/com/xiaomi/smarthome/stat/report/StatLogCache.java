package com.xiaomi.smarthome.stat.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.core.server.CoreService;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StatLogCache {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22761a = "STAT_LOG_CACHE_STATUS";
    private static final String c = "stats/";
    private static String d = null;
    private static final int e = 86400000;
    private static final int g = 3;
    private SharedPreferences b = null;
    private String f;
    private int h;
    private int i;
    private Map<Integer, StatLogVisitor> j = new ConcurrentHashMap();
    private int k;

    public static final String a(Context context) {
        File file;
        if (d != null) {
            return d;
        }
        if (context == null) {
            context = CoreService.getAppContext();
        }
        if (context == null) {
            return null;
        }
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            file = context.getExternalCacheDir();
        } else {
            file = null;
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file == null) {
            return null;
        }
        d = file.getPath();
        if (!d.endsWith("/")) {
            d += "/";
        }
        d += c;
        a(d);
        return d;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    private static int f() {
        return (int) (System.currentTimeMillis() / 86400000);
    }

    public static void a(File file) {
        if (file != null) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        a(file2);
                    } else {
                        file2.delete();
                    }
                }
            }
            file.delete();
        }
    }

    public StatLogCache(Context context) {
        this.k = 0;
        this.b = context.getSharedPreferences(f22761a, 0);
        this.f = a(context);
        String[] list = new File(this.f).list();
        this.k = f();
        this.i = this.k;
        this.h = this.i - 3;
        if (list != null) {
            for (String str : list) {
                if (str.matches("^d\\d+$")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    if (parseInt < this.h) {
                        a(parseInt);
                    } else {
                        this.j.put(Integer.valueOf(parseInt), new StatLogVisitor(this.b, this.f, parseInt));
                    }
                }
            }
        }
    }

    private void a(int i2) {
        a(new File(this.f + "d" + i2));
        StatLogVisitor remove = this.j.remove(Integer.valueOf(i2));
        if (remove != null) {
            remove.b();
        }
    }

    public Pair<Integer, StatLogVisitor> a() {
        int d2;
        this.k = f();
        for (int i2 = this.h; i2 < this.k; i2++) {
            StatLogVisitor statLogVisitor = this.j.get(Integer.valueOf(i2));
            if (statLogVisitor == null) {
                this.h++;
            } else {
                int d3 = statLogVisitor.d();
                if (d3 >= 0) {
                    return new Pair<>(Integer.valueOf(d3), statLogVisitor);
                }
                this.h++;
                a(i2);
            }
        }
        StatLogVisitor statLogVisitor2 = this.j.get(Integer.valueOf(this.k));
        if (statLogVisitor2 == null || (d2 = statLogVisitor2.d()) < 0) {
            return null;
        }
        return new Pair<>(Integer.valueOf(d2), statLogVisitor2);
    }

    public int b() {
        return this.k;
    }

    public StatLogVisitor c() {
        this.k = f();
        StatLogVisitor statLogVisitor = this.j.get(Integer.valueOf(this.k));
        if (statLogVisitor == null) {
            synchronized (this) {
                if (!this.j.containsKey(Integer.valueOf(this.k))) {
                    statLogVisitor = new StatLogVisitor(this.b, this.f, this.k);
                    this.j.put(Integer.valueOf(this.k), statLogVisitor);
                }
            }
        }
        return statLogVisitor;
    }

    public boolean d() {
        switch (this.j.size()) {
            case 0:
                return true;
            case 1:
                StatLogVisitor statLogVisitor = this.j.get(Integer.valueOf(this.k));
                if (statLogVisitor == null) {
                    return false;
                }
                return statLogVisitor.a();
            default:
                return false;
        }
    }

    public static LinkedList<String> e() {
        return StatLogFile.b();
    }

    public static boolean a(Context context, String str) {
        return StatLogFile.a(context, StatLogVisitor.a(a(context), f()), str);
    }
}
