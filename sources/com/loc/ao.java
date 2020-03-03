package com.loc;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public final class ao {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6481a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    static final String e = "i";
    public static final String f = "g";
    public static final String g = "h";
    public static final String h = "e";
    public static final String i = "f";
    public static final String j = "j";

    public static String a(Context context) {
        return c(context, e);
    }

    public static String a(Context context, String str) {
        return context.getSharedPreferences("AMSKLG_CFG", 0).getString(str, "");
    }

    @TargetApi(9)
    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    static boolean a(String[] strArr, String str) {
        if (!(strArr == null || str == null)) {
            try {
                for (String trim : str.split("\n")) {
                    String trim2 = trim.trim();
                    if (!TextUtils.isEmpty(trim2) && trim2.contains("uncaughtException")) {
                        return false;
                    }
                    if (b(strArr, trim2)) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public static void b(final Context context) {
        try {
            ExecutorService d2 = aq.d();
            if (d2 == null) {
                return;
            }
            if (!d2.isShutdown()) {
                d2.submit(new Runnable() {
                    public final void run() {
                        try {
                            bn.a(context);
                            ar.b(context);
                            ar.d(context);
                            ar.c(context);
                            br.a(context);
                            bp.a(context);
                        } catch (RejectedExecutionException unused) {
                        } catch (Throwable th) {
                            aq.b(th, "Lg", "proL");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            aq.b(th, "Lg", "proL");
        }
    }

    public static void b(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.remove(str);
        edit.apply();
    }

    static boolean b(String[] strArr, String str) {
        if (!(strArr == null || str == null)) {
            try {
                String str2 = str;
                for (String str3 : strArr) {
                    str2 = str2.trim();
                    if (str2.startsWith("at ")) {
                        if (str2.contains(str3 + ".") && str2.endsWith(Operators.BRACKET_END_STR) && !str2.contains("uncaughtException")) {
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public static String c(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + f6481a + str;
    }

    static List<ac> c(Context context) {
        List<ac> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    List<ac> a2 = new ba(context, false).a();
                    try {
                        return a2;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        list = a2;
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th4.printStackTrace();
            return list;
        }
    }
}
