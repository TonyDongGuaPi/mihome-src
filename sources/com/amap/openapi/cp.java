package com.amap.openapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.amap.location.common.log.ALLog;
import com.taobao.weex.el.parse.Operators;
import java.util.Calendar;

public class cp {

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences f4666a = null;
    private static int b = -1;
    private static int c = -1;
    private static int d = -1;
    private static int e = -1;
    private static int f = -1;
    private static int g = -1;
    private static long h = -1;

    public static boolean a(Context context) {
        try {
            e(context);
            if (d == -1) {
                d = f4666a.getInt("first_downloaded", 0);
            }
            return d == 1;
        } catch (Throwable unused) {
            return true;
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean a(Context context, int i) {
        try {
            e(context);
            if (b == -1) {
                b = f4666a.getInt("last_upload_time", 0);
            }
            if (c == -1) {
                c = f4666a.getInt("uploaded_count", 0);
            }
            int i2 = Calendar.getInstance().get(6);
            ALLog.d("@_18_8_@", "@_18_8_1_@(" + b + "," + i2 + "," + c + Operators.BRACKET_END_STR);
            if (i2 == b) {
                return c < i;
            }
            c = 0;
            SharedPreferences.Editor edit = f4666a.edit();
            edit.putInt("uploaded_count", c);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
            return true;
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean a(Context context, long j) {
        try {
            e(context);
            if (h == -1) {
                h = f4666a.getLong("config_time", 0);
            }
            boolean z = h != j;
            if (z) {
                h = j;
                SharedPreferences.Editor edit = f4666a.edit();
                edit.putLong("config_time", h);
                if (Build.VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            }
            return z;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int b(Context context, int i) {
        try {
            e(context);
            if (c == -1) {
                c = f4666a.getInt("uploaded_count", 0);
            }
            return Math.max(0, i - c);
        } catch (Throwable unused) {
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    public static void b(Context context) {
        try {
            e(context);
            d = 1;
            SharedPreferences.Editor edit = f4666a.edit();
            edit.putInt("first_downloaded", d);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static void c(Context context) {
        try {
            e(context);
            if (f == -1) {
                f = f4666a.getInt("downloaded_count", 0);
            }
            f++;
            SharedPreferences.Editor edit = f4666a.edit();
            edit.putInt("downloaded_count", f);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static void c(Context context, int i) {
        try {
            e(context);
            int i2 = Calendar.getInstance().get(6);
            ALLog.d("@_18_8_@", "@_18_8_2_@(" + i2 + "," + i + Operators.BRACKET_END_STR);
            b = i2;
            c = c + i;
            SharedPreferences.Editor edit = f4666a.edit();
            edit.putInt("last_upload_time", b);
            edit.putInt("uploaded_count", c);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static void d(Context context) {
        try {
            e(context);
            if (g == -1) {
                g = f4666a.getInt("nonwifi_downloaded_count", 0);
            }
            g++;
            SharedPreferences.Editor edit = f4666a.edit();
            edit.putInt("nonwifi_downloaded_count", g);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean d(Context context, int i) {
        try {
            e(context);
            if (((long) e) == -1) {
                e = f4666a.getInt("last_download_time", 0);
            }
            int i2 = Calendar.getInstance().get(6);
            ALLog.d("@_18_8_@", "@_18_8_3_@(" + e + "," + i2 + Operators.BRACKET_END_STR);
            if (i2 != e) {
                e = i2;
                f = 0;
                g = 0;
                SharedPreferences.Editor edit = f4666a.edit();
                edit.putInt("last_download_time", e);
                edit.putInt("downloaded_count", f);
                edit.putInt("nonwifi_downloaded_count", g);
                if (Build.VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
                return true;
            }
            if (f == -1) {
                f = f4666a.getInt("downloaded_count", 0);
            }
            ALLog.d("@_18_8_@", "@_18_8_4_@" + f);
            return f < i;
        } catch (Throwable unused) {
        }
    }

    private static void e(Context context) {
        if (f4666a == null) {
            f4666a = context.getSharedPreferences("location_offline", 0);
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean e(Context context, int i) {
        try {
            e(context);
            if (((long) e) == -1) {
                e = f4666a.getInt("last_download_time", 0);
            }
            int i2 = Calendar.getInstance().get(6);
            ALLog.d("@_18_8_@", "@_18_8_3_@(" + e + "," + i2 + Operators.BRACKET_END_STR);
            if (i2 != e) {
                e = i2;
                f = 0;
                g = 0;
                SharedPreferences.Editor edit = f4666a.edit();
                edit.putInt("last_download_time", e);
                edit.putInt("downloaded_count", f);
                edit.putInt("nonwifi_downloaded_count", g);
                if (Build.VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
                return true;
            }
            if (g == -1) {
                g = f4666a.getInt("nonwifi_downloaded_count", 0);
            }
            ALLog.d("@_18_8_@", "@_18_8_5_@" + g);
            return g < i;
        } catch (Throwable unused) {
        }
    }
}
