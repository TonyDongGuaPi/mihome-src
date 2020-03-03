package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.accountsdk.service.DeviceInfoResult;

public class eh extends ef {

    /* renamed from: a  reason: collision with root package name */
    private boolean f12708a;
    private boolean b;
    private boolean e;
    private boolean f;
    private boolean g;

    public eh(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        super(context, i);
        this.f12708a = z;
        this.b = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
    }

    private String f() {
        if (!this.f12708a) {
            return "off";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.d.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels + "," + displayMetrics.widthPixels;
        } catch (Throwable unused) {
            return "";
        }
    }

    private String g() {
        if (!this.b) {
            return "off";
        }
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable unused) {
            return "";
        }
    }

    private String h() {
        if (!this.e) {
            return "off";
        }
        try {
            return String.valueOf(Build.VERSION.SDK_INT);
        } catch (Throwable unused) {
            return "";
        }
    }

    private String i() {
        if (!this.f) {
            return "off";
        }
        try {
            return Settings.Secure.getString(this.d.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Throwable unused) {
            return "";
        }
    }

    private String j() {
        if (!this.g) {
            return "off";
        }
        try {
            return ((TelephonyManager) this.d.getSystemService("phone")).getSimOperator();
        } catch (Throwable unused) {
            return "";
        }
    }

    public int a() {
        return 3;
    }

    public String b() {
        return f() + "|" + g() + "|" + h() + "|" + i() + "|" + j();
    }

    public hq c() {
        return hq.DeviceInfoV2;
    }
}
