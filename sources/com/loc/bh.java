package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class bh extends bj {

    /* renamed from: a  reason: collision with root package name */
    protected Context f6509a;
    protected ac b;

    public bh(Context context, ac acVar) {
        if (context != null) {
            this.f6509a = context.getApplicationContext();
        }
        this.b = acVar;
    }

    protected static byte[] a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    private static byte[] n() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(ad.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                an.a(th, "bre", "gbh");
            }
            return byteArray;
        } catch (Throwable th2) {
            an.a(th2, "bre", "gbh");
            return null;
        }
        throw th;
    }

    private byte[] o() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (e()) {
                bArr = w.a(this.f6509a, j());
                byteArrayOutputStream.write(a(bArr));
            } else {
                bArr = new byte[]{0, 0};
            }
            byteArrayOutputStream.write(bArr);
            byte[] a2 = ad.a(g());
            if (a2 == null || a2.length <= 0) {
                a2 = new byte[]{0, 0};
            } else {
                byteArrayOutputStream.write(a(a2));
            }
            byteArrayOutputStream.write(a2);
            byte[] a3 = ad.a(f());
            if (a3 == null || a3.length <= 0) {
                a3 = new byte[]{0, 0};
            } else {
                byteArrayOutputStream.write(a(a3));
            }
            byteArrayOutputStream.write(a3);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                an.a(th, "bre", "gred");
            }
            return byteArray;
        } catch (Throwable th2) {
            an.a(th2, "bre", "gred");
        }
        return new byte[]{0};
        throw th;
    }

    private byte[] p() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] h = h();
            if (h != null) {
                if (h.length != 0) {
                    byteArrayOutputStream.write(new byte[]{1});
                    byteArrayOutputStream.write(a(h));
                    byteArrayOutputStream.write(h);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th) {
                        an.a(th, "bre", "grrd");
                    }
                    return byteArray;
                }
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                an.a(th2, "bre", "grrd");
            }
            return byteArray2;
        } catch (Throwable th3) {
            an.a(th3, "bre", "grrd");
        }
        return new byte[]{0};
        throw th;
    }

    private byte[] q() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] i = i();
            if (i != null) {
                if (i.length != 0) {
                    byteArrayOutputStream.write(new byte[]{1});
                    byte[] a2 = y.a(i);
                    byteArrayOutputStream.write(a(a2));
                    byteArrayOutputStream.write(a2);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th) {
                        an.a(th, "bre", "gred");
                    }
                    return byteArray;
                }
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                an.a(th2, "bre", "gred");
            }
            return byteArray2;
        } catch (Throwable th3) {
            an.a(th3, "bre", "gred");
        }
        return new byte[]{0};
        throw th;
    }

    public Map<String, String> b() {
        String f = u.f(this.f6509a);
        String a2 = w.a();
        Context context = this.f6509a;
        String a3 = w.a(context, a2, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a2);
        hashMap.put("key", f);
        hashMap.put("scode", a3);
        return hashMap;
    }

    public final byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(n());
            byteArrayOutputStream.write(o());
            byteArrayOutputStream.write(p());
            byteArrayOutputStream.write(q());
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                an.a(th, "bre", "geb");
            }
            return byteArray;
        } catch (Throwable th2) {
            an.a(th2, "bre", "geb");
            return null;
        }
        throw th;
    }

    public boolean e() {
        return true;
    }

    public String f() {
        return String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.b.c(), this.b.a()});
    }

    /* access modifiers changed from: protected */
    public String g() {
        return "2.1";
    }

    public abstract byte[] h();

    public abstract byte[] i();

    /* access modifiers changed from: protected */
    public boolean j() {
        return false;
    }
}
