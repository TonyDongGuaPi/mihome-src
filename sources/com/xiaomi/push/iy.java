package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.jg;
import com.xiaomi.push.jq;

public class iy {
    public static short a(Context context, ik ikVar) {
        int i = 0;
        int a2 = g.c(context, ikVar.b).a() + 0 + (ah.b(context) ? 4 : 0);
        if (ah.a(context)) {
            i = 8;
        }
        return (short) (a2 + i);
    }

    public static <T extends iz<T, ?>> void a(T t, byte[] bArr) {
        if (bArr != null) {
            new jd(new jq.a(true, true, bArr.length)).a(t, bArr);
            return;
        }
        throw new je("the message byte is empty.");
    }

    public static <T extends iz<T, ?>> byte[] a(T t) {
        if (t == null) {
            return null;
        }
        try {
            return new jf(new jg.a()).a(t);
        } catch (je e) {
            b.a("convertThriftObjectToBytes catch TException.", (Throwable) e);
            return null;
        }
    }
}
