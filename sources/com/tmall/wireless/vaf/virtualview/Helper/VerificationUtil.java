package com.tmall.wireless.vaf.virtualview.Helper;

import android.text.TextUtils;
import com.libra.virtualview.common.Common;
import com.tmall.wireless.vaf.virtualview.loader.CodeReader;

public class VerificationUtil {

    /* renamed from: a  reason: collision with root package name */
    private static CodeReader f9372a = new CodeReader();

    public static boolean a(byte[] bArr) {
        int length;
        if (bArr != null && bArr.length >= (length = Common.f6266a.length())) {
            return TextUtils.equals(Common.f6266a, new String(bArr, 0, length));
        }
        return false;
    }

    public static boolean b(byte[] bArr) {
        f9372a.b();
        f9372a.a(bArr);
        f9372a.b(Common.f6266a.length());
        return f9372a.h() == 1 && f9372a.h() == 0;
    }

    public static boolean a(byte[] bArr, short s) {
        f9372a.b();
        f9372a.a(bArr);
        f9372a.b(Common.f6266a.length() + 4);
        return f9372a.h() == s;
    }

    public static boolean c(byte[] bArr) {
        return bArr != null && bArr.length >= 58;
    }
}
