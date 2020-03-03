package com.xiaomi.push;

import cn.com.fmsh.tsm.business.constants.Constants;

public class dw {
    private static void a(byte[] bArr) {
        if (bArr.length >= 2) {
            bArr[0] = Constants.TagName.PAY_ORDER;
            bArr[1] = Constants.TagName.PAY_ORDER_LIST;
        }
    }

    public static byte[] a(String str, byte[] bArr) {
        byte[] c = bc.c(str);
        try {
            a(c);
            return h.a(c, bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] b(String str, byte[] bArr) {
        byte[] c = bc.c(str);
        try {
            a(c);
            return h.b(c, bArr);
        } catch (Exception unused) {
            return null;
        }
    }
}
