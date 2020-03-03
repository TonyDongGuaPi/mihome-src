package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import com.alipay.android.phone.a.a.a;
import java.io.ByteArrayOutputStream;

public class du extends dw {

    /* renamed from: a  reason: collision with root package name */
    public static int f4418a = 13;
    public static int b = 6;
    private Context e;

    public du(Context context, dw dwVar) {
        super(dwVar);
        this.e = context;
    }

    /* access modifiers changed from: protected */
    public byte[] a(byte[] bArr) {
        byte[] a2 = a(this.e);
        byte[] bArr2 = new byte[(a2.length + bArr.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(bArr, 0, bArr2, a2.length, bArr.length);
        return bArr2;
    }

    private byte[] a(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            bz.a(byteArrayOutputStream, "1.2." + f4418a + "." + b);
            bz.a(byteArrayOutputStream, a.f813a);
            bz.a(byteArrayOutputStream, bt.u(context));
            bz.a(byteArrayOutputStream, bt.m(context));
            bz.a(byteArrayOutputStream, bt.h(context));
            bz.a(byteArrayOutputStream, Build.MANUFACTURER);
            bz.a(byteArrayOutputStream, Build.MODEL);
            bz.a(byteArrayOutputStream, Build.DEVICE);
            bz.a(byteArrayOutputStream, bt.w(context));
            bz.a(byteArrayOutputStream, bp.c(context));
            bz.a(byteArrayOutputStream, bp.d(context));
            bz.a(byteArrayOutputStream, bp.f(context));
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                th.printStackTrace();
                return byteArray;
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return bArr;
        throw th;
    }
}
