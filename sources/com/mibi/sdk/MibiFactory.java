package com.mibi.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mibi.sdk.app.MibiLocalAppImp;
import com.mibi.sdk.app.MibiSystemAppImp;
import com.mibi.sdk.common.Utils;
import com.mibi.sdk.web.MibiWebImp;

public class MibiFactory {
    public static final String A = "fullResult";
    public static final String B = "payment_is_no_account";
    public static final String C = "payment_payment_result";
    public static final String D = "payment_trade_balance";
    public static final String E = "intent";
    public static final String F = "rechargeAmount";
    public static final String G = "payment_fragment_arguments";
    public static final String H = "com.xiaomi.payment";
    public static final String I = "com.xiaomi.action.DEDUCT";
    public static final String J = "com.xiaomi.action.SIGN_DEDUCT";
    public static final String K = "deductSignOrder";
    public static final String L = "deductRequestCode";
    public static final String M = "order";
    private static final String O = "MibiFactory";

    /* renamed from: a  reason: collision with root package name */
    public static final int f7599a = 1033001;
    public static final int b = 425;
    public static final int c = 424;
    public static final int d = 426;
    public static final int e = 427;
    public static final int f = -1;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 5;
    public static final int l = 6;
    public static final int m = 7;
    public static final int n = 8;
    public static final int o = 9;
    public static final int p = 10;
    public static final int q = 11;
    public static final int r = 12;
    public static final int s = 13;
    public static final int t = 14;
    public static final int u = 15;
    public static final int v = 16;
    public static final int w = 17;
    public static final String x = "code";
    public static final String y = "message";
    public static final String z = "result";
    public IMibi N;

    private MibiFactory(Context context, boolean z2) {
        if (Utils.c()) {
            this.N = new MibiWebImp();
        } else if (!a(context) || !z2) {
            this.N = new MibiLocalAppImp();
        } else {
            this.N = new MibiSystemAppImp();
        }
    }

    public static boolean a(Context context) {
        return c(context) && d(context) > 1033001;
    }

    public static IMibi b(Context context) {
        return new MibiFactory(context, true).N;
    }

    public static IMibi a(Context context, boolean z2) {
        return new MibiFactory(context, z2).N;
    }

    public static boolean c(Context context) {
        try {
            if (!((context.getPackageManager().getPackageInfo("com.xiaomi.payment", 0).applicationInfo.flags & 1) != 0)) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static int d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.payment", 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }
}
