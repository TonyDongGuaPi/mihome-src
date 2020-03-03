package com.xiaomi.smarthome.framework.page.verify;

import android.app.Activity;
import android.content.Intent;

public final class DeviceVerifyHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17053a = "extra_device_did";
    private static final String b = "xiaomi.smarthome.desc";
    private static final String c = "xiaomi.smarthome.old_pin";
    private static final String d = "xiaomi.smarthome.pincode";

    public static void a(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, DevicePinMotifyOpenActivity.class);
        a(intent, str);
        b(intent, str2);
        activity.startActivityForResult(intent, i);
    }

    public static void b(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyChangeActivity.class);
        a(intent, str);
        b(intent, str2);
        activity.startActivityForResult(intent, i);
    }

    public static void a(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyCloseActivity.class);
        a(intent, str);
        activity.startActivityForResult(intent, i);
    }

    public static void c(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyEnterActivity.class);
        a(intent, str);
        b(intent, str2);
        activity.startActivityForResult(intent, i);
    }

    public static void d(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, FingerPrintVerifyActivity.class);
        a(intent, str);
        b(intent, str2);
        activity.startActivityForResult(intent, i);
    }

    public static void b(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyFPOpenActivity.class);
        a(intent, str);
        activity.startActivityForResult(intent, i);
    }

    public static void c(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyFPReOpenActivity.class);
        a(intent, str);
        activity.startActivityForResult(intent, i);
    }

    static void a(Intent intent, String str) {
        intent.putExtra("extra_device_did", str);
    }

    static String a(Intent intent) {
        return intent.getStringExtra("extra_device_did");
    }

    static void b(Intent intent, String str) {
        intent.putExtra(b, str);
    }

    static String b(Intent intent) {
        return intent.getStringExtra(b);
    }

    static void c(Intent intent, String str) {
        intent.putExtra(c, str);
    }

    static String c(Intent intent) {
        return intent.getStringExtra(c);
    }

    static void d(Intent intent, String str) {
        intent.putExtra(d, str);
    }

    static String d(Intent intent) {
        return intent.getStringExtra(d);
    }
}
