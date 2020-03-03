package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.Cif;
import com.xiaomi.push.bc;
import com.xiaomi.push.h;
import com.xiaomi.push.ho;
import com.xiaomi.push.id;
import com.xiaomi.push.ie;
import com.xiaomi.push.ij;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.ip;
import com.xiaomi.push.iq;
import com.xiaomi.push.ir;
import com.xiaomi.push.it;
import com.xiaomi.push.iv;
import com.xiaomi.push.ix;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import java.nio.ByteBuffer;

public class ap {
    protected static <T extends iz<T, ?>> ik a(Context context, T t, ho hoVar) {
        return a(context, t, hoVar, !hoVar.equals(ho.Registration), context.getPackageName(), b.a(context).c());
    }

    protected static <T extends iz<T, ?>> ik a(Context context, T t, ho hoVar, boolean z, String str, String str2) {
        byte[] bArr;
        String str3;
        byte[] a2 = iy.a(t);
        if (a2 == null) {
            str3 = "invoke convertThriftObjectToBytes method, return null.";
        } else {
            ik ikVar = new ik();
            if (z) {
                String f = b.a(context).f();
                if (TextUtils.isEmpty(f)) {
                    str3 = "regSecret is empty, return null";
                } else {
                    try {
                        bArr = h.b(bc.c(f), a2);
                    } catch (Exception unused) {
                        b.d("encryption error. ");
                    }
                    id idVar = new id();
                    idVar.f12796a = 5;
                    idVar.f138a = "fakeid";
                    ikVar.a(idVar);
                    ikVar.a(ByteBuffer.wrap(bArr));
                    ikVar.a(hoVar);
                    ikVar.b(true);
                    ikVar.b(str);
                    ikVar.a(z);
                    ikVar.a(str2);
                    return ikVar;
                }
            }
            bArr = a2;
            id idVar2 = new id();
            idVar2.f12796a = 5;
            idVar2.f138a = "fakeid";
            ikVar.a(idVar2);
            ikVar.a(ByteBuffer.wrap(bArr));
            ikVar.a(hoVar);
            ikVar.b(true);
            ikVar.b(str);
            ikVar.a(z);
            ikVar.a(str2);
            return ikVar;
        }
        b.a(str3);
        return null;
    }

    public static iz a(Context context, ik ikVar) {
        byte[] bArr;
        if (ikVar.b()) {
            try {
                bArr = h.a(bc.c(b.a(context).f()), ikVar.a());
            } catch (Exception e) {
                throw new t("the aes decrypt failed.", e);
            }
        } else {
            bArr = ikVar.a();
        }
        iz a2 = a(ikVar.a(), ikVar.f172b);
        if (a2 != null) {
            iy.a(a2, bArr);
        }
        return a2;
    }

    private static iz a(ho hoVar, boolean z) {
        switch (aq.f11534a[hoVar.ordinal()]) {
            case 1:
                return new ip();
            case 2:
                return new iv();
            case 3:
                return new it();
            case 4:
                return new ix();
            case 5:
                return new ir();
            case 6:
                return new ie();
            case 7:
                return new ij();
            case 8:
                return new iq();
            case 9:
                if (z) {
                    return new in();
                }
                Cif ifVar = new Cif();
                ifVar.a(true);
                return ifVar;
            case 10:
                return new ij();
            default:
                return null;
        }
    }
}
