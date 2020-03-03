package org.apache.commons.lang.builder;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;

public class ToStringBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ToStringStyle f3380a = ToStringStyle.DEFAULT_STYLE;
    private final StringBuffer b;
    private final Object c;
    private final ToStringStyle d;

    public static ToStringStyle e() {
        return f3380a;
    }

    public static void a(ToStringStyle toStringStyle) {
        if (toStringStyle != null) {
            f3380a = toStringStyle;
            return;
        }
        throw new IllegalArgumentException("The style must not be null");
    }

    public static String c(Object obj) {
        return ReflectionToStringBuilder.a(obj);
    }

    public static String b(Object obj, ToStringStyle toStringStyle) {
        return ReflectionToStringBuilder.a(obj, toStringStyle);
    }

    public static String b(Object obj, ToStringStyle toStringStyle, boolean z) {
        return ReflectionToStringBuilder.a(obj, toStringStyle, z, false, (Class) null);
    }

    public static String b(Object obj, ToStringStyle toStringStyle, boolean z, Class cls) {
        return ReflectionToStringBuilder.a(obj, toStringStyle, z, false, cls);
    }

    public ToStringBuilder(Object obj) {
        this(obj, (ToStringStyle) null, (StringBuffer) null);
    }

    public ToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        this(obj, toStringStyle, (StringBuffer) null);
    }

    public ToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        toStringStyle = toStringStyle == null ? e() : toStringStyle;
        stringBuffer = stringBuffer == null ? new StringBuffer(512) : stringBuffer;
        this.b = stringBuffer;
        this.d = toStringStyle;
        this.c = obj;
        toStringStyle.appendStart(stringBuffer, obj);
    }

    public ToStringBuilder c(boolean z) {
        this.d.append(this.b, (String) null, z);
        return this;
    }

    public ToStringBuilder a(boolean[] zArr) {
        this.d.append(this.b, (String) null, zArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(byte b2) {
        this.d.append(this.b, (String) null, b2);
        return this;
    }

    public ToStringBuilder a(byte[] bArr) {
        this.d.append(this.b, (String) null, bArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(char c2) {
        this.d.append(this.b, (String) null, c2);
        return this;
    }

    public ToStringBuilder a(char[] cArr) {
        this.d.append(this.b, (String) null, cArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(double d2) {
        this.d.append(this.b, (String) null, d2);
        return this;
    }

    public ToStringBuilder a(double[] dArr) {
        this.d.append(this.b, (String) null, dArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(float f) {
        this.d.append(this.b, (String) null, f);
        return this;
    }

    public ToStringBuilder a(float[] fArr) {
        this.d.append(this.b, (String) null, fArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(int i) {
        this.d.append(this.b, (String) null, i);
        return this;
    }

    public ToStringBuilder a(int[] iArr) {
        this.d.append(this.b, (String) null, iArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(long j) {
        this.d.append(this.b, (String) null, j);
        return this;
    }

    public ToStringBuilder a(long[] jArr) {
        this.d.append(this.b, (String) null, jArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder d(Object obj) {
        this.d.append(this.b, (String) null, obj, (Boolean) null);
        return this;
    }

    public ToStringBuilder b(Object[] objArr) {
        this.d.append(this.b, (String) null, objArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(short s) {
        this.d.append(this.b, (String) null, s);
        return this;
    }

    public ToStringBuilder a(short[] sArr) {
        this.d.append(this.b, (String) null, sArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, boolean z) {
        this.d.append(this.b, str, z);
        return this;
    }

    public ToStringBuilder a(String str, boolean[] zArr) {
        this.d.append(this.b, str, zArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, boolean[] zArr, boolean z) {
        this.d.append(this.b, str, zArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, byte b2) {
        this.d.append(this.b, str, b2);
        return this;
    }

    public ToStringBuilder a(String str, byte[] bArr) {
        this.d.append(this.b, str, bArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, byte[] bArr, boolean z) {
        this.d.append(this.b, str, bArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, char c2) {
        this.d.append(this.b, str, c2);
        return this;
    }

    public ToStringBuilder a(String str, char[] cArr) {
        this.d.append(this.b, str, cArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, char[] cArr, boolean z) {
        this.d.append(this.b, str, cArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, double d2) {
        this.d.append(this.b, str, d2);
        return this;
    }

    public ToStringBuilder a(String str, double[] dArr) {
        this.d.append(this.b, str, dArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, double[] dArr, boolean z) {
        this.d.append(this.b, str, dArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, float f) {
        this.d.append(this.b, str, f);
        return this;
    }

    public ToStringBuilder a(String str, float[] fArr) {
        this.d.append(this.b, str, fArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, float[] fArr, boolean z) {
        this.d.append(this.b, str, fArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, int i) {
        this.d.append(this.b, str, i);
        return this;
    }

    public ToStringBuilder a(String str, int[] iArr) {
        this.d.append(this.b, str, iArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, int[] iArr, boolean z) {
        this.d.append(this.b, str, iArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, long j) {
        this.d.append(this.b, str, j);
        return this;
    }

    public ToStringBuilder a(String str, long[] jArr) {
        this.d.append(this.b, str, jArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, long[] jArr, boolean z) {
        this.d.append(this.b, str, jArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, Object obj) {
        this.d.append(this.b, str, obj, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, Object obj, boolean z) {
        this.d.append(this.b, str, obj, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, Object[] objArr) {
        this.d.append(this.b, str, objArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, Object[] objArr, boolean z) {
        this.d.append(this.b, str, objArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder a(String str, short s) {
        this.d.append(this.b, str, s);
        return this;
    }

    public ToStringBuilder a(String str, short[] sArr) {
        this.d.append(this.b, str, sArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder a(String str, short[] sArr, boolean z) {
        this.d.append(this.b, str, sArr, BooleanUtils.a(z));
        return this;
    }

    public ToStringBuilder e(Object obj) {
        ObjectUtils.a(g(), obj);
        return this;
    }

    public ToStringBuilder a(String str) {
        if (str != null) {
            this.d.appendSuper(this.b, str);
        }
        return this;
    }

    public ToStringBuilder b(String str) {
        if (str != null) {
            this.d.appendToString(this.b, str);
        }
        return this;
    }

    public Object f() {
        return this.c;
    }

    public StringBuffer g() {
        return this.b;
    }

    public ToStringStyle h() {
        return this.d;
    }

    public String toString() {
        if (f() == null) {
            g().append(h().getNullText());
        } else {
            this.d.appendEnd(g(), f());
        }
        return g().toString();
    }
}
