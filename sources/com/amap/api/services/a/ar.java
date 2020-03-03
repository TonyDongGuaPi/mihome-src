package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.taobao.weex.el.parse.Operators;

public class ar {

    /* renamed from: a  reason: collision with root package name */
    static dn f4293a;

    public static void a(Context context, String str, long j, boolean z) {
        try {
            String a2 = a(str, j, z);
            if (a2 == null) {
                return;
            }
            if (a2.length() >= 1) {
                if (f4293a == null) {
                    f4293a = new dn(context, "sea", "6.9.3", "O002");
                }
                f4293a.a(a2);
                Cdo.a(f4293a, context);
            }
        } catch (Throwable th) {
            s.a(th, "StatisticsUtil", "recordResponseAction");
        }
    }

    private static String a(String str, long j, boolean z) {
        try {
            return Operators.BLOCK_START_STR + "\"RequestPath\":" + "\"" + str + "\"" + "," + "\"ResponseTime\":" + j + "," + "\"Success\":" + z + "}";
        } catch (Throwable th) {
            s.a(th, "StatisticsUtil", "generateNetWorkResponseStatisticsEntity");
            return null;
        }
    }

    public static void a(String str, String str2, AMapException aMapException) {
        if (str != null && aMapException != null) {
            String errorType = aMapException.getErrorType();
            String a2 = a(aMapException);
            if (a2 != null && a2.length() >= 1) {
                cl.a(r.a(true), str, errorType, str2, a2);
            }
        }
    }

    private static String a(AMapException aMapException) {
        if (aMapException == null) {
            return null;
        }
        if (aMapException.getErrorLevel() == 0) {
            int errorCode = aMapException.getErrorCode();
            if (errorCode == 0) {
                return "4";
            }
            int pow = (int) Math.pow(10.0d, Math.floor(Math.log10((double) errorCode)));
            int i = (errorCode % pow) + (pow * 4);
            return i + "";
        }
        return aMapException.getErrorCode() + "";
    }
}
