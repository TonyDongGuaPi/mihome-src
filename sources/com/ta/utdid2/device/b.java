package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.a.a.d;
import com.ta.utdid2.a.a.f;
import java.util.zip.Adler32;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static a f8972a;
    static final Object d = new Object();

    static long a(a aVar) {
        if (aVar == null) {
            return 0;
        }
        String format = String.format("%s%s%s%s%s", new Object[]{aVar.getUtdid(), aVar.getDeviceId(), Long.valueOf(aVar.a()), aVar.getImsi(), aVar.getImei()});
        if (f.isEmpty(format)) {
            return 0;
        }
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(format.getBytes());
        return adler32.getValue();
    }

    private static a a(Context context) {
        if (context == null) {
            return null;
        }
        synchronized (d) {
            String value = c.a(context).getValue();
            if (f.isEmpty(value)) {
                return null;
            }
            if (value.endsWith("\n")) {
                value = value.substring(0, value.length() - 1);
            }
            a aVar = new a();
            long currentTimeMillis = System.currentTimeMillis();
            String imei = d.getImei(context);
            String imsi = d.getImsi(context);
            aVar.b(imei);
            aVar.setImei(imei);
            aVar.b(currentTimeMillis);
            aVar.setImsi(imsi);
            aVar.c(value);
            aVar.a(a(aVar));
            return aVar;
        }
    }

    public static synchronized a b(Context context) {
        synchronized (b.class) {
            if (f8972a != null) {
                a aVar = f8972a;
                return aVar;
            } else if (context == null) {
                return null;
            } else {
                a a2 = a(context);
                f8972a = a2;
                return a2;
            }
        }
    }
}
