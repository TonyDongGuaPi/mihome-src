package com.xiaomi.mimc.json;

import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.lang.CharUtils;

public class JSONTokener {

    /* renamed from: a  reason: collision with root package name */
    private long f11203a;
    private boolean b;
    private long c;
    private long d;
    private char e;
    private final Reader f;
    private boolean g;
    private long h;

    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return c2 - '7';
        }
        if (c2 < 'a' || c2 > 'f') {
            return -1;
        }
        return c2 - 'W';
    }

    public JSONTokener(Reader reader) {
        this.f = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.b = false;
        this.g = false;
        this.e = 0;
        this.c = 0;
        this.f11203a = 1;
        this.h = 0;
        this.d = 1;
    }

    public JSONTokener(InputStream inputStream) {
        this((Reader) new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this((Reader) new StringReader(str));
    }

    public void a() throws JSONException {
        if (this.g || this.c <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        g();
        this.g = true;
        this.b = false;
    }

    private void g() {
        this.c--;
        if (this.e == 13 || this.e == 10) {
            this.d--;
            this.f11203a = this.h;
        } else if (this.f11203a > 0) {
            this.f11203a--;
        }
    }

    public boolean b() {
        return this.b && !this.g;
    }

    public boolean c() throws JSONException {
        if (this.g) {
            return true;
        }
        try {
            this.f.mark(1);
            try {
                if (this.f.read() <= 0) {
                    this.b = true;
                    return false;
                }
                this.f.reset();
                return true;
            } catch (IOException e2) {
                throw new JSONException("Unable to read the next character from the stream", e2);
            }
        } catch (IOException e3) {
            throw new JSONException("Unable to preserve stream position", e3);
        }
    }

    public char d() throws JSONException {
        int i;
        if (this.g) {
            this.g = false;
            i = this.e;
        } else {
            try {
                i = this.f.read();
            } catch (IOException e2) {
                throw new JSONException((Throwable) e2);
            }
        }
        if (i <= 0) {
            this.b = true;
            return 0;
        }
        b(i);
        this.e = (char) i;
        return this.e;
    }

    private void b(int i) {
        if (i > 0) {
            this.c++;
            if (i == 13) {
                this.d++;
                this.h = this.f11203a;
                this.f11203a = 0;
            } else if (i == 10) {
                if (this.e != 13) {
                    this.d++;
                    this.h = this.f11203a;
                }
                this.f11203a = 0;
            } else {
                this.f11203a++;
            }
        }
    }

    public char b(char c2) throws JSONException {
        char d2 = d();
        if (d2 == c2) {
            return d2;
        }
        if (d2 > 0) {
            throw b("Expected '" + c2 + "' and instead saw '" + d2 + "'");
        }
        throw b("Expected '" + c2 + "' and instead saw ''");
    }

    public String a(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        while (i2 < i) {
            cArr[i2] = d();
            if (!b()) {
                i2++;
            } else {
                throw b("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public char e() throws com.xiaomi.mimc.json.JSONException {
        /*
            r2 = this;
        L_0x0000:
            char r0 = r2.d()
            if (r0 == 0) goto L_0x000a
            r1 = 32
            if (r0 <= r1) goto L_0x0000
        L_0x000a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.json.JSONTokener.e():char");
    }

    public String c(char c2) throws JSONException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            char d2 = d();
            if (d2 != 0 && d2 != 10 && d2 != 13) {
                if (d2 == '\\') {
                    char d3 = d();
                    if (d3 == '\"' || d3 == '\'' || d3 == '/' || d3 == '\\') {
                        sb.append(d3);
                    } else if (d3 == 'b') {
                        sb.append(8);
                    } else if (d3 == 'f') {
                        sb.append(12);
                    } else if (d3 == 'n') {
                        sb.append(10);
                    } else if (d3 != 'r') {
                        switch (d3) {
                            case 't':
                                sb.append(9);
                                break;
                            case 'u':
                                try {
                                    sb.append((char) Integer.parseInt(a(4), 16));
                                    break;
                                } catch (NumberFormatException e2) {
                                    throw a("Illegal escape.", e2);
                                }
                            default:
                                throw b("Illegal escape.");
                        }
                    } else {
                        sb.append(CharUtils.b);
                    }
                } else if (d2 == c2) {
                    return sb.toString();
                } else {
                    sb.append(d2);
                }
            }
        }
        throw b("Unterminated string");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String d(char r4) throws com.xiaomi.mimc.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x0005:
            char r1 = r3.d()
            if (r1 == r4) goto L_0x001a
            if (r1 == 0) goto L_0x001a
            r2 = 10
            if (r1 == r2) goto L_0x001a
            r2 = 13
            if (r1 != r2) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            r0.append(r1)
            goto L_0x0005
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r3.a()
        L_0x001f:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.json.JSONTokener.d(char):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r4) throws com.xiaomi.mimc.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x0005:
            char r1 = r3.d()
            int r2 = r4.indexOf(r1)
            if (r2 >= 0) goto L_0x001e
            if (r1 == 0) goto L_0x001e
            r2 = 10
            if (r1 == r2) goto L_0x001e
            r2 = 13
            if (r1 != r2) goto L_0x001a
            goto L_0x001e
        L_0x001a:
            r0.append(r1)
            goto L_0x0005
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r3.a()
        L_0x0023:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.json.JSONTokener.a(java.lang.String):java.lang.String");
    }

    public Object f() throws JSONException {
        char e2 = e();
        if (e2 == '\"' || e2 == '\'') {
            return c(e2);
        }
        if (e2 == '[') {
            a();
            return new JSONArray(this);
        } else if (e2 != '{') {
            StringBuilder sb = new StringBuilder();
            while (e2 >= ' ' && ",:]}/\\\"[{;=#".indexOf(e2) < 0) {
                sb.append(e2);
                e2 = d();
            }
            a();
            String trim = sb.toString().trim();
            if (!"".equals(trim)) {
                return JSONObject.F(trim);
            }
            throw b("Missing value");
        } else {
            a();
            return new JSONObject(this);
        }
    }

    public char e(char c2) throws JSONException {
        char d2;
        try {
            long j = this.c;
            long j2 = this.f11203a;
            long j3 = this.d;
            this.f.mark(1000000);
            do {
                d2 = d();
                if (d2 == 0) {
                    this.f.reset();
                    this.c = j;
                    this.f11203a = j2;
                    this.d = j3;
                    return 0;
                }
            } while (d2 != c2);
            this.f.mark(1);
            a();
            return d2;
        } catch (IOException e2) {
            throw new JSONException((Throwable) e2);
        }
    }

    public JSONException b(String str) {
        return new JSONException(str + toString());
    }

    public JSONException a(String str, Throwable th) {
        return new JSONException(str + toString(), th);
    }

    public String toString() {
        return " at " + this.c + " [character " + this.f11203a + " line " + this.d + Operators.ARRAY_END_STR;
    }
}
