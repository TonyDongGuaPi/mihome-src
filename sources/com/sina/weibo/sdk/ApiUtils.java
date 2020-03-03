package com.sina.weibo.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;

public class ApiUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8817a = 10350;
    public static final int b = 10351;
    public static final int c = 10352;
    public static final int d = 10353;
    public static final int e = 10355;
    public static final int f = 10772;
    private static final String g = "com.sina.weibo.sdk.ApiUtils";

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return a(context.getPackageManager().getPackageInfo(str, 64).signatures, WBConstants.f8820a);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature byteArray : signatureArr) {
            if (str.equals(MD5.a(byteArray.toByteArray()))) {
                LogUtil.a(g, "check pass");
                return true;
            }
        }
        return false;
    }
}
