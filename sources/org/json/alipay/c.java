package org.json.alipay;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private int f3646a;
    private Reader b;
    private char c;
    private boolean d;

    private c(Reader reader) {
        this.b = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.d = false;
        this.f3646a = 0;
    }

    public c(String str) {
        this((Reader) new StringReader(str));
    }

    private String a(int i) {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        if (this.d) {
            this.d = false;
            cArr[0] = this.c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException((Throwable) e);
            }
        }
        this.f3646a += i2;
        if (i2 >= i) {
            this.c = cArr[i - 1];
            return new String(cArr);
        }
        throw a("Substring bounds error");
    }

    public final JSONException a(String str) {
        return new JSONException(str + toString());
    }

    public final void a() {
        if (this.d || this.f3646a <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f3646a--;
        this.d = true;
    }

    public final char b() {
        if (this.d) {
            this.d = false;
            if (this.c != 0) {
                this.f3646a++;
            }
            return this.c;
        }
        try {
            int read = this.b.read();
            if (read <= 0) {
                this.c = 0;
                return 0;
            }
            this.f3646a++;
            this.c = (char) read;
            return this.c;
        } catch (IOException e) {
            throw new JSONException((Throwable) e);
        }
    }

    public final char c() {
        char b2;
        char b3;
        char b4;
        while (true) {
            b2 = b();
            if (b2 == '/') {
                char b5 = b();
                if (b5 != '*') {
                    if (b5 == '/') {
                        do {
                            b4 = b();
                            if (b4 == 10 || b4 == 13) {
                                break;
                            }
                        } while (b4 != 0);
                    } else {
                        a();
                        return IOUtils.f15883a;
                    }
                } else {
                    while (true) {
                        char b6 = b();
                        if (b6 != 0) {
                            if (b6 == '*') {
                                if (b() == '/') {
                                    break;
                                }
                                a();
                            }
                        } else {
                            throw a("Unclosed comment");
                        }
                    }
                }
            } else if (b2 == '#') {
                do {
                    b3 = b();
                    if (b3 == 10 || b3 == 13) {
                        break;
                    }
                } while (b3 != 0);
            } else if (b2 == 0 || b2 > ' ') {
                return b2;
            }
        }
        return b2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:53|54|55) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:56|57|58) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:(2:39|(2:48|49)(3:45|46|47))|50|51|52) */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b8, code lost:
        return new java.lang.Long(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00be, code lost:
        return new java.lang.Double(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bf, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x00ad */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00b3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x00b9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object d() {
        /*
            r10 = this;
            char r0 = r10.c()
            r1 = 34
            r2 = 8
            r3 = 120(0x78, float:1.68E-43)
            r4 = 2
            r5 = 16
            if (r0 == r1) goto L_0x00d9
            r1 = 91
            if (r0 == r1) goto L_0x00d0
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L_0x00c7
            switch(r0) {
                case 39: goto L_0x00d9;
                case 40: goto L_0x00d0;
                default: goto L_0x001a;
            }
        L_0x001a:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r6 = r0
        L_0x0020:
            r7 = 32
            if (r6 < r7) goto L_0x0034
            java.lang.String r7 = ",:]}/\\\"[{;=#"
            int r7 = r7.indexOf(r6)
            if (r7 >= 0) goto L_0x0034
            r1.append(r6)
            char r6 = r10.b()
            goto L_0x0020
        L_0x0034:
            r10.a()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            java.lang.String r6 = ""
            boolean r6 = r1.equals(r6)
            if (r6 != 0) goto L_0x00c0
            java.lang.String r6 = "true"
            boolean r6 = r1.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x0052
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            return r0
        L_0x0052:
            java.lang.String r6 = "false"
            boolean r6 = r1.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x005d
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            return r0
        L_0x005d:
            java.lang.String r6 = "null"
            boolean r6 = r1.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x0068
            java.lang.Object r0 = org.json.alipay.b.f3645a
            return r0
        L_0x0068:
            r6 = 48
            if (r0 < r6) goto L_0x0070
            r7 = 57
            if (r0 <= r7) goto L_0x007e
        L_0x0070:
            r7 = 46
            if (r0 == r7) goto L_0x007e
            r7 = 45
            if (r0 == r7) goto L_0x007e
            r7 = 43
            if (r0 != r7) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            return r1
        L_0x007e:
            if (r0 != r6) goto L_0x00ad
            int r0 = r1.length()
            if (r0 <= r4) goto L_0x00a3
            r0 = 1
            char r6 = r1.charAt(r0)
            if (r6 == r3) goto L_0x0095
            char r0 = r1.charAt(r0)
            r3 = 88
            if (r0 != r3) goto L_0x00a3
        L_0x0095:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00ad }
            java.lang.String r2 = r1.substring(r4)     // Catch:{ Exception -> 0x00ad }
            int r2 = java.lang.Integer.parseInt(r2, r5)     // Catch:{ Exception -> 0x00ad }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00ad }
            return r0
        L_0x00a3:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00ad }
            int r2 = java.lang.Integer.parseInt(r1, r2)     // Catch:{ Exception -> 0x00ad }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00ad }
            return r0
        L_0x00ad:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00b3 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00b3 }
            return r0
        L_0x00b3:
            java.lang.Long r0 = new java.lang.Long     // Catch:{ Exception -> 0x00b9 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00b9 }
            return r0
        L_0x00b9:
            java.lang.Double r0 = new java.lang.Double     // Catch:{ Exception -> 0x00bf }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00bf }
            return r0
        L_0x00bf:
            return r1
        L_0x00c0:
            java.lang.String r0 = "Missing value"
            org.json.alipay.JSONException r0 = r10.a((java.lang.String) r0)
            throw r0
        L_0x00c7:
            r10.a()
            org.json.alipay.b r0 = new org.json.alipay.b
            r0.<init>((org.json.alipay.c) r10)
            return r0
        L_0x00d0:
            r10.a()
            org.json.alipay.a r0 = new org.json.alipay.a
            r0.<init>((org.json.alipay.c) r10)
            return r0
        L_0x00d9:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
        L_0x00de:
            char r6 = r10.b()
            if (r6 == 0) goto L_0x0137
            r7 = 10
            if (r6 == r7) goto L_0x0137
            r8 = 13
            if (r6 == r8) goto L_0x0137
            r9 = 92
            if (r6 == r9) goto L_0x00fb
            if (r6 != r0) goto L_0x00f7
            java.lang.String r0 = r1.toString()
            return r0
        L_0x00f7:
            r1.append(r6)
            goto L_0x00de
        L_0x00fb:
            char r6 = r10.b()
            r9 = 98
            if (r6 == r9) goto L_0x0133
            r9 = 102(0x66, float:1.43E-43)
            if (r6 == r9) goto L_0x0130
            r9 = 110(0x6e, float:1.54E-43)
            if (r6 == r9) goto L_0x012c
            r7 = 114(0x72, float:1.6E-43)
            if (r6 == r7) goto L_0x0128
            if (r6 == r3) goto L_0x0123
            switch(r6) {
                case 116: goto L_0x0120;
                case 117: goto L_0x0115;
                default: goto L_0x0114;
            }
        L_0x0114:
            goto L_0x00f7
        L_0x0115:
            r6 = 4
            java.lang.String r6 = r10.a((int) r6)
        L_0x011a:
            int r6 = java.lang.Integer.parseInt(r6, r5)
            char r6 = (char) r6
            goto L_0x00f7
        L_0x0120:
            r6 = 9
            goto L_0x00f7
        L_0x0123:
            java.lang.String r6 = r10.a((int) r4)
            goto L_0x011a
        L_0x0128:
            r1.append(r8)
            goto L_0x00de
        L_0x012c:
            r1.append(r7)
            goto L_0x00de
        L_0x0130:
            r6 = 12
            goto L_0x00f7
        L_0x0133:
            r1.append(r2)
            goto L_0x00de
        L_0x0137:
            java.lang.String r0 = "Unterminated string"
            org.json.alipay.JSONException r0 = r10.a((java.lang.String) r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.c.d():java.lang.Object");
    }

    public final String toString() {
        return " at character " + this.f3646a;
    }
}
