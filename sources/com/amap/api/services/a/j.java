package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import java.util.Map;

public abstract class j<T, V> extends df {

    /* renamed from: a  reason: collision with root package name */
    protected T f4426a;
    protected int b = 1;
    protected String c = "";
    protected Context d;
    protected String e = "";
    private int i = 1;

    /* access modifiers changed from: protected */
    public abstract V a(String str) throws AMapException;

    public Map<String, String> d() {
        return null;
    }

    public Map<String, String> e() {
        return null;
    }

    /* access modifiers changed from: protected */
    public V f() {
        return null;
    }

    public j(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.d = context;
        this.f4426a = t;
        this.b = 1;
        b(ServiceSettings.getInstance().getSoTimeOut());
        a(ServiceSettings.getInstance().getConnectionTimeOut());
    }

    public String a() {
        return this.e;
    }

    public String b() {
        String i2 = i();
        if (i2 == null) {
            return null;
        }
        try {
            int indexOf = i2.indexOf(".com/");
            int indexOf2 = i2.indexOf("?");
            if (indexOf2 == -1) {
                return i2.substring(indexOf + ".com/".length());
            }
            return i2.substring(indexOf + ".com/".length(), indexOf2);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public V a(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e2) {
            s.a(e2, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        s.b(str);
        return a(str);
    }

    public V c() throws AMapException {
        if (this.f4426a == null) {
            return null;
        }
        try {
            return g();
        } catch (AMapException e2) {
            ar.a(b(), a(), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011c, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0127, code lost:
        throw new com.amap.api.services.core.AMapException(com.amap.api.services.core.AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066 A[Catch:{ InterruptedException -> 0x0088, AMapException -> 0x0128, Throwable -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[SYNTHETIC, Splitter:B:24:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011b A[ExcHandler: Throwable (r0v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:16:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0067 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private V g() throws com.amap.api.services.core.AMapException {
        /*
            r13 = this;
            r0 = 0
            r1 = 0
            r2 = r1
            r1 = 0
        L_0x0004:
            int r3 = r13.b     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r1 >= r3) goto L_0x011a
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r5 = 1
            com.amap.api.services.core.ServiceSettings r6 = com.amap.api.services.core.ServiceSettings.getInstance()     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            int r6 = r6.getProtocol()     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            com.amap.api.services.a.bv r7 = com.amap.api.services.a.bv.a()     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            android.content.Context r8 = r13.d     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            r7.a((android.content.Context) r8)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            com.amap.api.services.a.de r7 = com.amap.api.services.a.de.a(r0)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            android.content.Context r8 = r13.d     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            java.net.Proxy r8 = com.amap.api.services.a.bx.a((android.content.Context) r8)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            r13.a((java.net.Proxy) r8)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            byte[] r6 = r13.a(r6, r7, r13)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            java.lang.Object r6 = r13.b(r6)     // Catch:{ bo -> 0x0068, AMapException -> 0x0050, Throwable -> 0x011b }
            android.content.Context r2 = r13.d     // Catch:{ bo -> 0x004b, AMapException -> 0x0047, Throwable -> 0x011b }
            java.lang.String r9 = r13.b()     // Catch:{ bo -> 0x004b, AMapException -> 0x0047, Throwable -> 0x011b }
            r10 = 0
            long r7 = r7 - r3
            com.amap.api.services.a.ar.a(r2, r9, r7, r5)     // Catch:{ bo -> 0x004b, AMapException -> 0x0047, Throwable -> 0x011b }
            int r2 = r13.b     // Catch:{ bo -> 0x004b, AMapException -> 0x0047, Throwable -> 0x011b }
            r1 = r2
            r2 = r6
            goto L_0x0004
        L_0x0047:
            r2 = move-exception
            r5 = r2
            r2 = r6
            goto L_0x0051
        L_0x004b:
            r2 = move-exception
            r12 = r6
            r6 = r2
            r2 = r12
            goto L_0x0069
        L_0x0050:
            r5 = move-exception
        L_0x0051:
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            android.content.Context r8 = r13.d     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r9 = r13.b()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r10 = 0
            long r6 = r6 - r3
            com.amap.api.services.a.ar.a(r8, r9, r6, r0)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            int r1 = r1 + 1
            int r3 = r13.b     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r1 >= r3) goto L_0x0067
            goto L_0x0004
        L_0x0067:
            throw r5     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
        L_0x0068:
            r6 = move-exception
        L_0x0069:
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            android.content.Context r9 = r13.d     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r10 = r13.b()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r11 = 0
            long r7 = r7 - r3
            com.amap.api.services.a.ar.a(r9, r10, r7, r0)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            int r1 = r1 + 1
            int r3 = r13.b     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r1 >= r3) goto L_0x00c9
            int r3 = r13.i     // Catch:{ InterruptedException -> 0x0088 }
            int r3 = r3 * 1000
            long r3 = (long) r3     // Catch:{ InterruptedException -> 0x0088 }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x0088 }
            goto L_0x0004
        L_0x0088:
            java.lang.String r0 = "http连接失败 - ConnectionException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 != 0) goto L_0x00bd
            java.lang.String r0 = "socket 连接异常 - SocketException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 != 0) goto L_0x00bd
            java.lang.String r0 = "服务器连接失败 - UnknownServiceException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 == 0) goto L_0x00af
            goto L_0x00bd
        L_0x00af:
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r1 = r6.a()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r2 = r6.c()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r0.<init>(r1, r5, r2)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            throw r0     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
        L_0x00bd:
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r1 = "http或socket连接失败 - ConnectionException"
            java.lang.String r2 = r6.c()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r0.<init>(r1, r5, r2)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            throw r0     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
        L_0x00c9:
            r13.f()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r0 = "http连接失败 - ConnectionException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 != 0) goto L_0x010e
            java.lang.String r0 = "socket 连接异常 - SocketException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 != 0) goto L_0x010e
            java.lang.String r0 = "未知的错误"
            java.lang.String r1 = r6.a()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 != 0) goto L_0x010e
            java.lang.String r0 = "服务器连接失败 - UnknownServiceException"
            java.lang.String r1 = r6.getMessage()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            boolean r0 = r0.equals(r1)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            if (r0 == 0) goto L_0x0100
            goto L_0x010e
        L_0x0100:
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r1 = r6.a()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r2 = r6.c()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r0.<init>(r1, r5, r2)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            throw r0     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
        L_0x010e:
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            java.lang.String r1 = "http或socket连接失败 - ConnectionException"
            java.lang.String r2 = r6.c()     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            r0.<init>(r1, r5, r2)     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
            throw r0     // Catch:{ AMapException -> 0x0128, Throwable -> 0x011b }
        L_0x011a:
            return r2
        L_0x011b:
            r0 = move-exception
            r0.printStackTrace()
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException
            java.lang.String r1 = "未知错误"
            r0.<init>(r1)
            throw r0
        L_0x0128:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.j.g():java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public byte[] a(int i2, de deVar, df dfVar) throws bo {
        dh dhVar;
        if (i2 == 1) {
            dhVar = deVar.a(dfVar, false);
        } else {
            dhVar = i2 == 2 ? deVar.a(dfVar, true) : null;
        }
        if (dhVar == null) {
            return null;
        }
        byte[] bArr = dhVar.f4403a;
        this.e = dhVar.d;
        return bArr;
    }

    private V b(byte[] bArr) throws AMapException {
        return a(bArr);
    }
}
