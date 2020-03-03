package com.unionpay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.security.MessageDigest;
import miuipub.security.DigestUtils;

public class UPUtils {
    public static String a(Context context, String str) {
        String string = context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, "");
        String b = b(string, ("00000000" + "23456789abcdef12123456786789abcd").substring(0, 32));
        return (b != null && b.endsWith("00000000")) ? b.substring(0, b.length() - "00000000".length()) : "";
    }

    public static String a(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(DigestUtils.b);
            instance.reset();
            instance.update(bytes);
            return a.a(instance.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(String str, String str2) {
        try {
            return a.a(d.a(a.a(str2), str.getBytes("utf-8")));
        } catch (Exception unused) {
            return "";
        }
    }

    public static void a(Context context, long j, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static void a(Context context, String str, String str2) {
        String substring = ("00000000" + "23456789abcdef12123456786789abcd").substring(0, 32);
        String a2 = a(str + "00000000", substring);
        if (a2 == null) {
            a2 = "";
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putString(str2, a2);
        edit.commit();
    }

    private static String b(String str, String str2) {
        try {
            return new String(d.b(a.a(str2), a.a(str)), "utf-8").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void b(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public static long c(Context context, String str) {
        return context.getSharedPreferences("UnionPayPluginEx.pref", 0).getLong(str, 0);
    }

    public static native String forConfig(int i, String str);

    public static native String forUrl(int i);

    public static native String forWap(int i, String str);

    public static native String getIssuer(int i);

    public static native String getSubject(int i);

    public static native String getTalkingDataIdForAssist(int i);
}
