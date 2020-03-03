package com.xiaomi.smarthome.frame.login.util;

import android.content.Intent;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;

public class LoginIntentUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16350a = "login_type";
    private static final String b = "login_timestamp";
    private static final String c = "account_id";
    private static final String d = "is_dual_sim_to_choose";
    private static final String e = "local_phone_info";
    private static final String f = "register_phone_info";
    private static final String g = "phoneNumber";
    private static final String h = "smsCode";
    private static final String i = "is_login_for_bind_wx";
    private static final String j = "password";

    public static void a(Intent intent, int i2) {
        intent.putExtra("login_type", i2);
    }

    public static int a(Intent intent) {
        return intent.getIntExtra("login_type", 1);
    }

    public static void a(Intent intent, long j2) {
        intent.putExtra("login_timestamp", j2);
    }

    public static long b(Intent intent) {
        return intent.getLongExtra("login_timestamp", 1);
    }

    public static void a(Intent intent, String str) {
        intent.putExtra("account_id", str);
    }

    public static String c(Intent intent) {
        return intent.getStringExtra("account_id");
    }

    public static void a(Intent intent, boolean z) {
        intent.putExtra(d, z);
    }

    public static boolean d(Intent intent) {
        return intent.getBooleanExtra(d, false);
    }

    public static void a(Intent intent, LocalPhoneDetailInfo localPhoneDetailInfo) {
        intent.putExtra(e, localPhoneDetailInfo);
    }

    public static LocalPhoneDetailInfo e(Intent intent) {
        return (LocalPhoneDetailInfo) intent.getParcelableExtra(e);
    }

    public static void a(Intent intent, RegisterUserInfo registerUserInfo) {
        intent.putExtra(f, registerUserInfo);
    }

    public static RegisterUserInfo f(Intent intent) {
        return (RegisterUserInfo) intent.getParcelableExtra(f);
    }

    public static void b(Intent intent, String str) {
        intent.putExtra("phoneNumber", str);
    }

    public static String g(Intent intent) {
        return intent.getStringExtra("phoneNumber");
    }

    public static void c(Intent intent, String str) {
        intent.putExtra("smsCode", str);
    }

    public static String h(Intent intent) {
        return intent.getStringExtra("smsCode");
    }

    public static void b(Intent intent, boolean z) {
        intent.putExtra(i, z);
    }

    public static boolean i(Intent intent) {
        return intent.getBooleanExtra(i, false);
    }

    public static void d(Intent intent, String str) {
        intent.putExtra("password", str);
    }

    public static String j(Intent intent) {
        return intent.getStringExtra("password");
    }
}
