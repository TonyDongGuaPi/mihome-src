package org.json.a.a;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.lang.CharUtils;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private int f3643a;
    private Reader b;
    private char c;
    private boolean d;

    public e(Reader reader) {
        this.b = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.d = false;
        this.f3643a = 0;
    }

    public e(String str) {
        this((Reader) new StringReader(str));
    }

    public void a() {
        if (this.d || this.f3643a <= 0) {
            throw new b("Stepping back two steps is not supported");
        }
        this.f3643a--;
        this.d = true;
    }

    public char b() {
        if (this.d) {
            this.d = false;
            if (this.c != 0) {
                this.f3643a++;
            }
            return this.c;
        }
        try {
            int read = this.b.read();
            if (read <= 0) {
                this.c = 0;
                return 0;
            }
            this.f3643a++;
            this.c = (char) read;
            return this.c;
        } catch (IOException e) {
            throw new b((Throwable) e);
        }
    }

    public String a(int i) {
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
                throw new b((Throwable) e);
            }
        }
        this.f3643a += i2;
        if (i2 >= i) {
            this.c = cArr[i - 1];
            return new String(cArr);
        }
        throw a("Substring bounds error");
    }

    public char c() {
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
                        if (b6 == 0) {
                            throw a("Unclosed comment");
                        } else if (b6 == '*') {
                            if (b() == '/') {
                                break;
                            }
                            a();
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

    public String a(char c2) {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            char b2 = b();
            if (b2 != 0 && b2 != 10 && b2 != 13) {
                if (b2 == '\\') {
                    char b3 = b();
                    if (b3 == 'b') {
                        stringBuffer.append(8);
                    } else if (b3 == 'f') {
                        stringBuffer.append(12);
                    } else if (b3 == 'n') {
                        stringBuffer.append(10);
                    } else if (b3 == 'r') {
                        stringBuffer.append(CharUtils.b);
                    } else if (b3 != 'x') {
                        switch (b3) {
                            case 't':
                                stringBuffer.append(9);
                                break;
                            case 'u':
                                stringBuffer.append((char) Integer.parseInt(a(4), 16));
                                break;
                            default:
                                stringBuffer.append(b3);
                                break;
                        }
                    } else {
                        stringBuffer.append((char) Integer.parseInt(a(2), 16));
                    }
                } else if (b2 == c2) {
                    return stringBuffer.toString();
                } else {
                    stringBuffer.append(b2);
                }
            }
        }
        throw a("Unterminated string");
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
    public java.lang.Object d() {
        /*
            r5 = this;
            char r0 = r5.c()
            r1 = 34
            if (r0 == r1) goto L_0x00d9
            r1 = 91
            if (r0 == r1) goto L_0x00d0
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L_0x00c7
            switch(r0) {
                case 39: goto L_0x00d9;
                case 40: goto L_0x00d0;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r2 = r0
        L_0x0019:
            r3 = 32
            if (r2 < r3) goto L_0x002d
            java.lang.String r3 = ",:]}/\\\"[{;=#"
            int r3 = r3.indexOf(r2)
            if (r3 >= 0) goto L_0x002d
            r1.append(r2)
            char r2 = r5.b()
            goto L_0x0019
        L_0x002d:
            r5.a()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            java.lang.String r2 = ""
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x00c0
            java.lang.String r2 = "true"
            boolean r2 = r1.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x004b
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            return r0
        L_0x004b:
            java.lang.String r2 = "false"
            boolean r2 = r1.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x0056
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            return r0
        L_0x0056:
            java.lang.String r2 = "null"
            boolean r2 = r1.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x0061
            java.lang.Object r0 = org.json.a.a.c.f3642a
            return r0
        L_0x0061:
            r2 = 48
            if (r0 < r2) goto L_0x0069
            r3 = 57
            if (r0 <= r3) goto L_0x0077
        L_0x0069:
            r3 = 46
            if (r0 == r3) goto L_0x0077
            r3 = 45
            if (r0 == r3) goto L_0x0077
            r3 = 43
            if (r0 != r3) goto L_0x0076
            goto L_0x0077
        L_0x0076:
            return r1
        L_0x0077:
            if (r0 != r2) goto L_0x00ad
            int r0 = r1.length()
            r2 = 2
            if (r0 <= r2) goto L_0x00a1
            r0 = 1
            char r3 = r1.charAt(r0)
            r4 = 120(0x78, float:1.68E-43)
            if (r3 == r4) goto L_0x0091
            char r0 = r1.charAt(r0)
            r3 = 88
            if (r0 != r3) goto L_0x00a1
        L_0x0091:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00ad }
            java.lang.String r2 = r1.substring(r2)     // Catch:{ Exception -> 0x00ad }
            r3 = 16
            int r2 = java.lang.Integer.parseInt(r2, r3)     // Catch:{ Exception -> 0x00ad }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00ad }
            return r0
        L_0x00a1:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00ad }
            r2 = 8
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
            org.json.a.a.b r0 = r5.a((java.lang.String) r0)
            throw r0
        L_0x00c7:
            r5.a()
            org.json.a.a.c r0 = new org.json.a.a.c
            r0.<init>((org.json.a.a.e) r5)
            return r0
        L_0x00d0:
            r5.a()
            org.json.a.a.a r0 = new org.json.a.a.a
            r0.<init>((org.json.a.a.e) r5)
            return r0
        L_0x00d9:
            java.lang.String r0 = r5.a((char) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.a.a.e.d():java.lang.Object");
    }

    public b a(String str) {
        return new b(str + toString());
    }

    public String toString() {
        return " at character " + this.f3643a;
    }
}
