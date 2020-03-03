package com.imi.fastjson.serializer;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.CharTypes;
import com.imi.fastjson.util.Base64;
import com.imi.fastjson.util.IOUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;

public final class SerializeWriter extends Writer {
    private static final ThreadLocal<SoftReference<char[]>> c = new ThreadLocal<>();

    /* renamed from: a  reason: collision with root package name */
    protected char[] f6179a;
    protected int b;
    private int d;
    private final Writer e;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this.e = writer;
        this.d = JSON.DEFAULT_GENERATE_FEATURE;
        SoftReference softReference = c.get();
        if (softReference != null) {
            this.f6179a = (char[]) softReference.get();
            c.set((Object) null);
        }
        if (this.f6179a == null) {
            this.f6179a = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this((Writer) null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        this.e = writer;
        SoftReference softReference = c.get();
        if (softReference != null) {
            this.f6179a = (char[]) softReference.get();
            c.set((Object) null);
        }
        if (this.f6179a == null) {
            this.f6179a = new char[1024];
        }
        int i = 0;
        for (SerializerFeature mask : serializerFeatureArr) {
            i |= mask.getMask();
        }
        this.d = i;
    }

    public int a() {
        return this.f6179a.length;
    }

    public SerializeWriter(int i) {
        this((Writer) null, i);
    }

    public SerializeWriter(Writer writer, int i) {
        this.e = writer;
        if (i > 0) {
            this.f6179a = new char[i];
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    public void a(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.d = serializerFeature.getMask() | this.d;
            return;
        }
        this.d = (serializerFeature.getMask() ^ -1) & this.d;
    }

    public boolean a(SerializerFeature serializerFeature) {
        return SerializerFeature.isEnabled(this.d, serializerFeature);
    }

    public void write(int i) {
        int i2 = this.b + 1;
        if (i2 > this.f6179a.length) {
            if (this.e == null) {
                a(i2);
            } else {
                flush();
                i2 = 1;
            }
        }
        this.f6179a[this.b] = (char) i;
        this.b = i2;
    }

    public void a(char c2) {
        int i = this.b + 1;
        if (i > this.f6179a.length) {
            if (this.e == null) {
                a(i);
            } else {
                flush();
                i = 1;
            }
        }
        this.f6179a[this.b] = c2;
        this.b = i;
    }

    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i4 = this.b + i2;
            if (i4 > this.f6179a.length) {
                if (this.e == null) {
                    a(i4);
                } else {
                    do {
                        int length = this.f6179a.length - this.b;
                        System.arraycopy(cArr, i, this.f6179a, this.b, length);
                        this.b = this.f6179a.length;
                        flush();
                        i2 -= length;
                        i += length;
                    } while (i2 > this.f6179a.length);
                    i4 = i2;
                }
            }
            System.arraycopy(cArr, i, this.f6179a, this.b, i2);
            this.b = i4;
        }
    }

    public void a(int i) {
        int length = ((this.f6179a.length * 3) / 2) + 1;
        if (length >= i) {
            i = length;
        }
        char[] cArr = new char[i];
        System.arraycopy(this.f6179a, 0, cArr, 0, this.b);
        this.f6179a = cArr;
    }

    public void write(String str, int i, int i2) {
        int i3;
        int i4 = this.b + i2;
        if (i4 > this.f6179a.length) {
            if (this.e == null) {
                a(i4);
            } else {
                while (true) {
                    int length = this.f6179a.length - this.b;
                    i3 = i + length;
                    str.getChars(i, i3, this.f6179a, this.b);
                    this.b = this.f6179a.length;
                    flush();
                    i2 -= length;
                    if (i2 <= this.f6179a.length) {
                        break;
                    }
                    i = i3;
                }
                i4 = i2;
                i = i3;
            }
        }
        str.getChars(i, i2 + i, this.f6179a, this.b);
        this.b = i4;
    }

    public void a(Writer writer) throws IOException {
        if (this.e == null) {
            writer.write(this.f6179a, 0, this.b);
            return;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public void a(OutputStream outputStream, String str) throws IOException {
        a(outputStream, Charset.forName(str));
    }

    public void a(OutputStream outputStream, Charset charset) throws IOException {
        if (this.e == null) {
            outputStream.write(new String(this.f6179a, 0, this.b).getBytes(charset));
            return;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    /* renamed from: a */
    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "null" : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    /* renamed from: a */
    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    /* renamed from: b */
    public SerializeWriter append(char c2) {
        a(c2);
        return this;
    }

    public void b() {
        this.b = 0;
    }

    public char[] c() {
        if (this.e == null) {
            char[] cArr = new char[this.b];
            System.arraycopy(this.f6179a, 0, cArr, 0, this.b);
            return cArr;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public byte[] a(String str) {
        if (this.e == null) {
            if (str == null) {
                str = "UTF-8";
            }
            return new SerialWriterStringEncoder(Charset.forName(str)).a(this.f6179a, 0, this.b);
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public int d() {
        return this.b;
    }

    public String toString() {
        return new String(this.f6179a, 0, this.b);
    }

    public void close() {
        if (this.e != null && this.b > 0) {
            flush();
        }
        if (this.f6179a.length <= 8192) {
            c.set(new SoftReference(this.f6179a));
        }
        this.f6179a = null;
    }

    public void write(String str) {
        if (str == null) {
            e();
        } else {
            write(str, 0, str.length());
        }
    }

    public void b(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int a2 = i < 0 ? IOUtils.a(-i) + 1 : IOUtils.a(i);
        int i2 = this.b + a2;
        if (i2 > this.f6179a.length) {
            if (this.e == null) {
                a(i2);
            } else {
                char[] cArr = new char[a2];
                IOUtils.a(i, a2, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.a(i, i2, this.f6179a);
        this.b = i2;
    }

    public void a(byte[] bArr) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length == 0) {
            write("\"\"");
            return;
        }
        char[] cArr = Base64.f6187a;
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = this.b;
        int i4 = this.b + (((i2 / 3) + 1) << 2) + 2;
        int i5 = 0;
        if (i4 > this.f6179a.length) {
            if (this.e != null) {
                a('\"');
                int i6 = 0;
                while (i6 < i) {
                    int i7 = i6 + 1;
                    int i8 = i7 + 1;
                    byte b2 = ((bArr2[i6] & 255) << 16) | ((bArr2[i7] & 255) << 8) | (bArr2[i8] & 255);
                    a(cArr[(b2 >>> 18) & 63]);
                    a(cArr[(b2 >>> 12) & 63]);
                    a(cArr[(b2 >>> 6) & 63]);
                    a(cArr[b2 & Constants.TagName.CARD_APP_ACTIVATION_STATUS]);
                    i6 = i8 + 1;
                }
                int i9 = length - i;
                if (i9 > 0) {
                    int i10 = (bArr2[i] & 255) << 10;
                    if (i9 == 2) {
                        i5 = (bArr2[i2] & 255) << 2;
                    }
                    int i11 = i10 | i5;
                    a(cArr[i11 >> 12]);
                    a(cArr[(i11 >>> 6) & 63]);
                    a(i9 == 2 ? cArr[i11 & 63] : '=');
                    a('=');
                }
                a('\"');
                return;
            }
            a(i4);
        }
        this.b = i4;
        int i12 = i3 + 1;
        this.f6179a[i3] = '\"';
        int i13 = 0;
        while (i13 < i) {
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            byte b3 = ((bArr2[i13] & 255) << 16) | ((bArr2[i14] & 255) << 8);
            int i16 = i15 + 1;
            byte b4 = b3 | (bArr2[i15] & 255);
            int i17 = i12 + 1;
            this.f6179a[i12] = cArr[(b4 >>> 18) & 63];
            int i18 = i17 + 1;
            this.f6179a[i17] = cArr[(b4 >>> 12) & 63];
            int i19 = i18 + 1;
            this.f6179a[i18] = cArr[(b4 >>> 6) & 63];
            this.f6179a[i19] = cArr[b4 & Constants.TagName.CARD_APP_ACTIVATION_STATUS];
            i13 = i16;
            i12 = i19 + 1;
        }
        int i20 = length - i;
        if (i20 > 0) {
            int i21 = (bArr2[i] & 255) << 10;
            if (i20 == 2) {
                i5 = (bArr2[i2] & 255) << 2;
            }
            int i22 = i21 | i5;
            this.f6179a[i4 - 5] = cArr[i22 >> 12];
            this.f6179a[i4 - 4] = cArr[(i22 >>> 6) & 63];
            this.f6179a[i4 - 3] = i20 == 2 ? cArr[i22 & 63] : '=';
            this.f6179a[i4 - 2] = '=';
        }
        this.f6179a[i4 - 1] = '\"';
    }

    public void a(int i, char c2) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            a(c2);
            return;
        }
        int a2 = this.b + (i < 0 ? IOUtils.a(-i) + 1 : IOUtils.a(i));
        int i2 = a2 + 1;
        if (i2 > this.f6179a.length) {
            if (this.e != null) {
                b(i);
                a(c2);
                return;
            }
            a(i2);
        }
        IOUtils.a(i, a2, this.f6179a);
        this.f6179a[a2] = c2;
        this.b = i2;
    }

    public void a(long j, char c2) throws IOException {
        if (j == Long.MIN_VALUE) {
            write("-9223372036854775808");
            a(c2);
            return;
        }
        int a2 = this.b + (j < 0 ? IOUtils.a(-j) + 1 : IOUtils.a(j));
        int i = a2 + 1;
        if (i > this.f6179a.length) {
            if (this.e != null) {
                a(j);
                a(c2);
                return;
            }
            a(i);
        }
        IOUtils.a(j, a2, this.f6179a);
        this.f6179a[a2] = c2;
        this.b = i;
    }

    public void a(long j) {
        if (j == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        int a2 = j < 0 ? IOUtils.a(-j) + 1 : IOUtils.a(j);
        int i = this.b + a2;
        if (i > this.f6179a.length) {
            if (this.e == null) {
                a(i);
            } else {
                char[] cArr = new char[a2];
                IOUtils.a(j, a2, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.a(j, i, this.f6179a);
        this.b = i;
    }

    public void e() {
        write("null");
    }

    private void b(String str, char c2) {
        a(str, c2, true);
    }

    private void a(String str, char c2, boolean z) {
        int i;
        int i2;
        char c3;
        int i3;
        int i4;
        String str2 = str;
        char c4 = c2;
        if (str2 == null) {
            e();
            if (c4 != 0) {
                a(c4);
                return;
            }
            return;
        }
        int length = str.length();
        int i5 = this.b + length + 2;
        if (c4 != 0) {
            i5++;
        }
        int i6 = 0;
        char c5 = 12;
        if (i > this.f6179a.length) {
            if (this.e != null) {
                a('\"');
                while (i6 < str.length()) {
                    char charAt = str2.charAt(i6);
                    if (a(SerializerFeature.BrowserCompatible)) {
                        if (charAt == 8 || charAt == 12 || charAt == 10 || charAt == 13 || charAt == 9 || charAt == '\"' || charAt == '/' || charAt == '\\') {
                            a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                            a(CharTypes.f[charAt]);
                            i6++;
                        } else {
                            if (charAt < ' ') {
                                a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                                a('u');
                                a('0');
                                a('0');
                                int i7 = charAt * 2;
                                a(CharTypes.g[i7]);
                                a(CharTypes.g[i7 + 1]);
                            } else if (charAt >= 127) {
                                a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                                a('u');
                                a(CharTypes.f6084a[(charAt >>> 12) & 15]);
                                a(CharTypes.f6084a[(charAt >>> 8) & 15]);
                                a(CharTypes.f6084a[(charAt >>> 4) & 15]);
                                a(CharTypes.f6084a[charAt & 15]);
                            }
                            i6++;
                        }
                    } else if ((charAt < CharTypes.d.length && CharTypes.d[charAt]) || (charAt == '/' && a(SerializerFeature.WriteSlashAsSpecial))) {
                        a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                        a(CharTypes.f[charAt]);
                        i6++;
                    }
                    a(charAt);
                    i6++;
                }
                a('\"');
                if (c4 != 0) {
                    a(c4);
                    return;
                }
                return;
            }
            a(i);
        }
        int i8 = this.b + 1;
        int i9 = i8 + length;
        this.f6179a[this.b] = '\"';
        str2.getChars(0, length, this.f6179a, i8);
        this.b = i;
        if (a(SerializerFeature.BrowserCompatible)) {
            int i10 = -1;
            for (int i11 = i8; i11 < i9; i11++) {
                char c6 = this.f6179a[i11];
                if (c6 == '\"' || c6 == '/' || c6 == '\\') {
                    i++;
                } else if (c6 == 8 || c6 == 12 || c6 == 10 || c6 == 13 || c6 == 9) {
                    i++;
                } else if (c6 < ' ') {
                    i += 5;
                } else if (c6 >= 127) {
                    i += 5;
                }
                i10 = i11;
            }
            if (i > this.f6179a.length) {
                a(i);
            }
            this.b = i;
            while (i10 >= i8) {
                char c7 = this.f6179a[i10];
                if (c7 == 8 || c7 == c5 || c7 == 10 || c7 == 13 || c7 == 9) {
                    int i12 = i10 + 1;
                    System.arraycopy(this.f6179a, i12, this.f6179a, i10 + 2, (i9 - i10) - 1);
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i12] = CharTypes.f[c7];
                    i9++;
                } else if (c7 == '\"' || c7 == '/' || c7 == '\\') {
                    int i13 = i10 + 1;
                    System.arraycopy(this.f6179a, i13, this.f6179a, i10 + 2, (i9 - i10) - 1);
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i13] = c7;
                    i9++;
                } else if (c7 < ' ') {
                    int i14 = i10 + 1;
                    System.arraycopy(this.f6179a, i14, this.f6179a, i10 + 6, (i9 - i10) - 1);
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i14] = 'u';
                    this.f6179a[i10 + 2] = '0';
                    this.f6179a[i10 + 3] = '0';
                    int i15 = c7 * 2;
                    this.f6179a[i10 + 4] = CharTypes.g[i15];
                    this.f6179a[i10 + 5] = CharTypes.g[i15 + 1];
                    i9 += 5;
                } else if (c7 >= 127) {
                    int i16 = i10 + 1;
                    System.arraycopy(this.f6179a, i16, this.f6179a, i10 + 6, (i9 - i10) - 1);
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i16] = 'u';
                    this.f6179a[i10 + 2] = CharTypes.f6084a[(c7 >>> 12) & 15];
                    this.f6179a[i10 + 3] = CharTypes.f6084a[(c7 >>> 8) & 15];
                    this.f6179a[i10 + 4] = CharTypes.f6084a[(c7 >>> 4) & 15];
                    this.f6179a[i10 + 5] = CharTypes.f6084a[c7 & 15];
                    i9 += 5;
                }
                i10--;
                c5 = 12;
            }
            if (c4 != 0) {
                this.f6179a[this.b - 2] = '\"';
                this.f6179a[this.b - 1] = c4;
                return;
            }
            this.f6179a[this.b - 1] = '\"';
            return;
        }
        if (z) {
            int i17 = -1;
            c3 = 0;
            i2 = -1;
            for (int i18 = i8; i18 < i9; i18++) {
                char c8 = this.f6179a[i18];
                if (c8 < ']' && c8 != ' ' && ((c8 < '0' || c8 == '\\') && ((c8 < CharTypes.d.length && CharTypes.d[c8]) || (c8 == '/' && a(SerializerFeature.WriteSlashAsSpecial))))) {
                    i6++;
                    if (i2 == -1) {
                        i17 = i18;
                        i2 = i17;
                    } else {
                        i17 = i18;
                    }
                    c3 = c8;
                }
            }
            i3 = i17;
        } else {
            i3 = -1;
            c3 = 0;
            i2 = -1;
        }
        int i19 = i + i6;
        if (i19 > this.f6179a.length) {
            a(i19);
        }
        this.b = i19;
        if (i6 == 1) {
            int i20 = i3 + 1;
            System.arraycopy(this.f6179a, i20, this.f6179a, i3 + 2, (i9 - i3) - 1);
            this.f6179a[i3] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
            this.f6179a[i20] = CharTypes.f[c3];
        } else if (i6 > 1) {
            for (int i21 = i2 - i8; i21 < str.length(); i21++) {
                char charAt2 = str2.charAt(i21);
                if ((charAt2 >= CharTypes.d.length || !CharTypes.d[charAt2]) && (charAt2 != '/' || !a(SerializerFeature.WriteSlashAsSpecial))) {
                    this.f6179a[i2] = charAt2;
                    i4 = i2 + 1;
                } else {
                    int i22 = i2 + 1;
                    this.f6179a[i2] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i22] = CharTypes.f[charAt2];
                    i4 = i22 + 1;
                }
            }
        }
        if (c4 != 0) {
            this.f6179a[this.b - 2] = '\"';
            this.f6179a[this.b - 1] = c4;
            return;
        }
        this.f6179a[this.b - 1] = '\"';
    }

    public void a(boolean z) {
        if (z) {
            write("true");
        } else {
            write("false");
        }
    }

    public void a(String str, char c2) {
        if (a(SerializerFeature.UseSingleQuotes)) {
            d(str);
            a(c2);
            return;
        }
        b(str, c2);
    }

    public void b(String str) {
        if (a(SerializerFeature.UseSingleQuotes)) {
            d(str);
        } else {
            b(str, 0);
        }
    }

    private void d(String str) {
        int i = 0;
        if (str == null) {
            int i2 = this.b + 4;
            if (i2 > this.f6179a.length) {
                a(i2);
            }
            "null".getChars(0, 4, this.f6179a, this.b);
            this.b = i2;
            return;
        }
        int length = str.length();
        int i3 = this.b + length + 2;
        if (i3 > this.f6179a.length) {
            if (this.e != null) {
                a((char) Operators.SINGLE_QUOTE);
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt <= 13 || charAt == '\\' || charAt == '\'' || (charAt == '/' && a(SerializerFeature.WriteSlashAsSpecial))) {
                        a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                        a(CharTypes.f[charAt]);
                    } else {
                        a(charAt);
                    }
                    i++;
                }
                a((char) Operators.SINGLE_QUOTE);
                return;
            }
            a(i3);
        }
        int i4 = this.b + 1;
        int i5 = i4 + length;
        this.f6179a[this.b] = Operators.SINGLE_QUOTE;
        str.getChars(0, length, this.f6179a, i4);
        this.b = i3;
        int i6 = -1;
        char c2 = 0;
        for (int i7 = i4; i7 < i5; i7++) {
            char c3 = this.f6179a[i7];
            if (c3 <= 13 || c3 == '\\' || c3 == '\'' || (c3 == '/' && a(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i6 = i7;
                c2 = c3;
            }
        }
        int i8 = i3 + i;
        if (i8 > this.f6179a.length) {
            a(i8);
        }
        this.b = i8;
        if (i == 1) {
            int i9 = i6 + 1;
            System.arraycopy(this.f6179a, i9, this.f6179a, i6 + 2, (i5 - i6) - 1);
            this.f6179a[i6] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
            this.f6179a[i9] = CharTypes.f[c2];
        } else if (i > 1) {
            int i10 = i6 + 1;
            System.arraycopy(this.f6179a, i10, this.f6179a, i6 + 2, (i5 - i6) - 1);
            this.f6179a[i6] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
            this.f6179a[i10] = CharTypes.f[c2];
            int i11 = i5 + 1;
            for (int i12 = i10 - 2; i12 >= i4; i12--) {
                char c4 = this.f6179a[i12];
                if (c4 <= 13 || c4 == '\\' || c4 == '\'' || (c4 == '/' && a(SerializerFeature.WriteSlashAsSpecial))) {
                    int i13 = i12 + 1;
                    System.arraycopy(this.f6179a, i13, this.f6179a, i12 + 2, (i11 - i12) - 1);
                    this.f6179a[i12] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i13] = CharTypes.f[c4];
                    i11++;
                }
            }
        }
        this.f6179a[this.b - 1] = Operators.SINGLE_QUOTE;
    }

    public void c(String str) {
        a(str, false);
    }

    public void a(String str, boolean z) {
        if (str == null) {
            write("null:");
        } else if (a(SerializerFeature.UseSingleQuotes)) {
            if (a(SerializerFeature.QuoteFieldNames)) {
                d(str);
                a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            }
            f(str);
        } else if (a(SerializerFeature.QuoteFieldNames)) {
            a(str, (char) Operators.CONDITION_IF_MIDDLE, z);
        } else {
            e(str);
        }
    }

    private void e(String str) {
        String str2 = str;
        boolean[] zArr = CharTypes.d;
        int length = str.length();
        boolean z = true;
        int i = this.b + length + 1;
        int i2 = 0;
        if (i > this.f6179a.length) {
            if (this.e == null) {
                a(i);
            } else if (length == 0) {
                a('\"');
                a('\"');
                a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            } else {
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        char charAt = str2.charAt(i3);
                        if (charAt < zArr.length && zArr[charAt]) {
                            break;
                        }
                        i3++;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    a('\"');
                }
                while (i2 < length) {
                    char charAt2 = str2.charAt(i2);
                    if (charAt2 >= zArr.length || !zArr[charAt2]) {
                        a(charAt2);
                    } else {
                        a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                        a(CharTypes.f[charAt2]);
                    }
                    i2++;
                }
                if (z) {
                    a('\"');
                }
                a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            }
        }
        if (length == 0) {
            if (this.b + 3 > this.f6179a.length) {
                a(this.b + 3);
            }
            char[] cArr = this.f6179a;
            int i4 = this.b;
            this.b = i4 + 1;
            cArr[i4] = '\"';
            char[] cArr2 = this.f6179a;
            int i5 = this.b;
            this.b = i5 + 1;
            cArr2[i5] = '\"';
            char[] cArr3 = this.f6179a;
            int i6 = this.b;
            this.b = i6 + 1;
            cArr3[i6] = Operators.CONDITION_IF_MIDDLE;
            return;
        }
        int i7 = this.b;
        int i8 = i7 + length;
        str2.getChars(0, length, this.f6179a, i7);
        this.b = i;
        int i9 = i7;
        boolean z2 = false;
        while (i9 < i8) {
            char c2 = this.f6179a[i9];
            if (c2 < zArr.length && zArr[c2]) {
                if (!z2) {
                    i += 3;
                    if (i > this.f6179a.length) {
                        a(i);
                    }
                    this.b = i;
                    int i10 = i9 + 1;
                    System.arraycopy(this.f6179a, i10, this.f6179a, i9 + 3, (i8 - i9) - 1);
                    System.arraycopy(this.f6179a, i2, this.f6179a, 1, i9);
                    this.f6179a[i7] = '\"';
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    int i11 = i10 + 1;
                    this.f6179a[i11] = CharTypes.f[c2];
                    i8 += 2;
                    this.f6179a[this.b - 2] = '\"';
                    i9 = i11;
                    z2 = true;
                } else {
                    i++;
                    if (i > this.f6179a.length) {
                        a(i);
                    }
                    this.b = i;
                    int i12 = i9 + 1;
                    System.arraycopy(this.f6179a, i12, this.f6179a, i9 + 2, i8 - i9);
                    this.f6179a[i9] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i12] = CharTypes.f[c2];
                    i8++;
                    i9 = i12;
                }
            }
            i9++;
            i2 = 0;
        }
        this.f6179a[this.b - 1] = Operators.CONDITION_IF_MIDDLE;
    }

    private void f(String str) {
        String str2 = str;
        boolean[] zArr = CharTypes.e;
        int length = str.length();
        boolean z = true;
        int i = this.b + length + 1;
        int i2 = 0;
        if (i > this.f6179a.length) {
            if (this.e == null) {
                a(i);
            } else if (length == 0) {
                a((char) Operators.SINGLE_QUOTE);
                a((char) Operators.SINGLE_QUOTE);
                a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            } else {
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        char charAt = str2.charAt(i3);
                        if (charAt < zArr.length && zArr[charAt]) {
                            break;
                        }
                        i3++;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    a((char) Operators.SINGLE_QUOTE);
                }
                while (i2 < length) {
                    char charAt2 = str2.charAt(i2);
                    if (charAt2 >= zArr.length || !zArr[charAt2]) {
                        a(charAt2);
                    } else {
                        a((char) com.xiaomi.smarthome.fastvideo.IOUtils.b);
                        a(CharTypes.f[charAt2]);
                    }
                    i2++;
                }
                if (z) {
                    a((char) Operators.SINGLE_QUOTE);
                }
                a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            }
        }
        if (length == 0) {
            if (this.b + 3 > this.f6179a.length) {
                a(this.b + 3);
            }
            char[] cArr = this.f6179a;
            int i4 = this.b;
            this.b = i4 + 1;
            cArr[i4] = Operators.SINGLE_QUOTE;
            char[] cArr2 = this.f6179a;
            int i5 = this.b;
            this.b = i5 + 1;
            cArr2[i5] = Operators.SINGLE_QUOTE;
            char[] cArr3 = this.f6179a;
            int i6 = this.b;
            this.b = i6 + 1;
            cArr3[i6] = Operators.CONDITION_IF_MIDDLE;
            return;
        }
        int i7 = this.b;
        int i8 = i7 + length;
        str2.getChars(0, length, this.f6179a, i7);
        this.b = i;
        int i9 = i7;
        boolean z2 = false;
        while (i9 < i8) {
            char c2 = this.f6179a[i9];
            if (c2 < zArr.length && zArr[c2]) {
                if (!z2) {
                    i += 3;
                    if (i > this.f6179a.length) {
                        a(i);
                    }
                    this.b = i;
                    int i10 = i9 + 1;
                    System.arraycopy(this.f6179a, i10, this.f6179a, i9 + 3, (i8 - i9) - 1);
                    System.arraycopy(this.f6179a, i2, this.f6179a, 1, i9);
                    this.f6179a[i7] = Operators.SINGLE_QUOTE;
                    this.f6179a[i10] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    int i11 = i10 + 1;
                    this.f6179a[i11] = CharTypes.f[c2];
                    i8 += 2;
                    this.f6179a[this.b - 2] = Operators.SINGLE_QUOTE;
                    i9 = i11;
                    z2 = true;
                } else {
                    i++;
                    if (i > this.f6179a.length) {
                        a(i);
                    }
                    this.b = i;
                    int i12 = i9 + 1;
                    System.arraycopy(this.f6179a, i12, this.f6179a, i9 + 2, i8 - i9);
                    this.f6179a[i9] = com.xiaomi.smarthome.fastvideo.IOUtils.b;
                    this.f6179a[i12] = CharTypes.f[c2];
                    i8++;
                    i9 = i12;
                }
            }
            i9++;
            i2 = 0;
        }
        this.f6179a[i - 1] = Operators.CONDITION_IF_MIDDLE;
    }

    public void flush() {
        if (this.e != null) {
            try {
                this.e.write(this.f6179a, 0, this.b);
                this.e.flush();
                this.b = 0;
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        }
    }
}
