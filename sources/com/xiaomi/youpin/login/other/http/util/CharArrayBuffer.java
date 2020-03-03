package com.xiaomi.youpin.login.other.http.util;

import com.xiaomi.youpin.login.other.http.protocol.HTTP;

public final class CharArrayBuffer {

    /* renamed from: a  reason: collision with root package name */
    private char[] f23591a;
    private int b;

    public CharArrayBuffer(int i) {
        if (i >= 0) {
            this.f23591a = new char[i];
            return;
        }
        throw new IllegalArgumentException("Buffer capacity may not be negative");
    }

    private void e(int i) {
        char[] cArr = new char[Math.max(this.f23591a.length << 1, i)];
        System.arraycopy(this.f23591a, 0, cArr, 0, this.b);
        this.f23591a = cArr;
    }

    public void a(char[] cArr, int i, int i2) {
        int i3;
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > cArr.length) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                int i4 = this.b + i2;
                if (i4 > this.f23591a.length) {
                    e(i4);
                }
                System.arraycopy(cArr, i, this.f23591a, this.b, i2);
                this.b = i4;
            }
        }
    }

    public void a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.b + length;
        if (i > this.f23591a.length) {
            e(i);
        }
        str.getChars(0, length, this.f23591a, this.b);
        this.b = i;
    }

    public void a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            a(charArrayBuffer.f23591a, i, i2);
        }
    }

    public void a(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer != null) {
            a(charArrayBuffer.f23591a, 0, charArrayBuffer.b);
        }
    }

    public void a(char c) {
        int i = this.b + 1;
        if (i > this.f23591a.length) {
            e(i);
        }
        this.f23591a[this.b] = c;
        this.b = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(byte[] r4, int r5, int r6) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            if (r5 < 0) goto L_0x0034
            int r0 = r4.length
            if (r5 > r0) goto L_0x0034
            if (r6 < 0) goto L_0x0034
            int r0 = r5 + r6
            if (r0 < 0) goto L_0x0034
            int r1 = r4.length
            if (r0 > r1) goto L_0x0034
            if (r6 != 0) goto L_0x0014
            return
        L_0x0014:
            int r0 = r3.b
            int r6 = r6 + r0
            char[] r1 = r3.f23591a
            int r1 = r1.length
            if (r6 <= r1) goto L_0x001f
            r3.e(r6)
        L_0x001f:
            if (r0 >= r6) goto L_0x0031
            byte r1 = r4[r5]
            if (r1 >= 0) goto L_0x0027
            int r1 = r1 + 256
        L_0x0027:
            char[] r2 = r3.f23591a
            char r1 = (char) r1
            r2[r0] = r1
            int r5 = r5 + 1
            int r0 = r0 + 1
            goto L_0x001f
        L_0x0031:
            r3.b = r6
            return
        L_0x0034:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.other.http.util.CharArrayBuffer.a(byte[], int, int):void");
    }

    public void a(ByteArrayBuffer byteArrayBuffer, int i, int i2) {
        if (byteArrayBuffer != null) {
            a(byteArrayBuffer.e(), i, i2);
        }
    }

    public void a(Object obj) {
        a(String.valueOf(obj));
    }

    public void a() {
        this.b = 0;
    }

    public char[] b() {
        char[] cArr = new char[this.b];
        if (this.b > 0) {
            System.arraycopy(this.f23591a, 0, cArr, 0, this.b);
        }
        return cArr;
    }

    public char a(int i) {
        return this.f23591a[i];
    }

    public char[] c() {
        return this.f23591a;
    }

    public int d() {
        return this.f23591a.length;
    }

    public int e() {
        return this.b;
    }

    public void b(int i) {
        if (i > this.f23591a.length - this.b) {
            e(this.b + i);
        }
    }

    public void c(int i) {
        if (i < 0 || i > this.f23591a.length) {
            throw new IndexOutOfBoundsException();
        }
        this.b = i;
    }

    public boolean f() {
        return this.b == 0;
    }

    public boolean g() {
        return this.b == this.f23591a.length;
    }

    public int a(int i, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > this.b) {
            i3 = this.b;
        }
        if (i2 > i3) {
            return -1;
        }
        while (i2 < i3) {
            if (this.f23591a[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public int d(int i) {
        return a(i, 0, this.b);
    }

    public String a(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 > this.b) {
            throw new IndexOutOfBoundsException();
        } else if (i <= i2) {
            return new String(this.f23591a, i, i2 - i);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String b(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 > this.b) {
            throw new IndexOutOfBoundsException();
        } else if (i <= i2) {
            while (i < i2 && HTTP.a(this.f23591a[i])) {
                i++;
            }
            while (i2 > i && HTTP.a(this.f23591a[i2 - 1])) {
                i2--;
            }
            return new String(this.f23591a, i, i2 - i);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString() {
        return new String(this.f23591a, 0, this.b);
    }
}
