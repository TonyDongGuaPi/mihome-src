package com.drew.metadata;

import com.coloros.mcssdk.mode.CommandMessage;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.lang.annotations.SuppressWarnings;
import com.taobao.weex.el.parse.Operators;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public abstract class Directory {
    static final /* synthetic */ boolean d = (!Directory.class.desiredAssertionStatus());
    private static final String e = "0.###";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    protected final Map<Integer, Object> f5205a = new HashMap();
    @NotNull
    protected final Collection<Tag> b = new ArrayList();
    protected TagDescriptor c;
    @NotNull
    private final Collection<String> f = new ArrayList(4);
    @Nullable
    private Directory g;

    @NotNull
    public abstract String a();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract HashMap<Integer, String> b();

    protected Directory() {
    }

    public boolean c() {
        return this.f.isEmpty() && this.b.isEmpty();
    }

    public boolean a(int i) {
        return this.f5205a.containsKey(Integer.valueOf(i));
    }

    @NotNull
    public Collection<Tag> d() {
        return Collections.unmodifiableCollection(this.b);
    }

    public int e() {
        return this.b.size();
    }

    public void a(@NotNull TagDescriptor tagDescriptor) {
        if (tagDescriptor != null) {
            this.c = tagDescriptor;
            return;
        }
        throw new NullPointerException("cannot set a null descriptor");
    }

    public void a(@NotNull String str) {
        this.f.add(str);
    }

    public boolean f() {
        return this.f.size() > 0;
    }

    @NotNull
    public Iterable<String> g() {
        return Collections.unmodifiableCollection(this.f);
    }

    public int h() {
        return this.f.size();
    }

    @Nullable
    public Directory i() {
        return this.g;
    }

    public void a(@NotNull Directory directory) {
        this.g = directory;
    }

    public void a(int i, int i2) {
        a(i, (Object) Integer.valueOf(i2));
    }

    public void a(int i, @NotNull int[] iArr) {
        b(i, (Object) iArr);
    }

    public void a(int i, float f2) {
        a(i, (Object) Float.valueOf(f2));
    }

    public void a(int i, @NotNull float[] fArr) {
        b(i, (Object) fArr);
    }

    public void a(int i, double d2) {
        a(i, (Object) Double.valueOf(d2));
    }

    public void a(int i, @NotNull double[] dArr) {
        b(i, (Object) dArr);
    }

    public void a(int i, @NotNull StringValue stringValue) {
        if (stringValue != null) {
            a(i, (Object) stringValue);
            return;
        }
        throw new NullPointerException("cannot set a null StringValue");
    }

    public void a(int i, @NotNull String str) {
        if (str != null) {
            a(i, (Object) str);
            return;
        }
        throw new NullPointerException("cannot set a null String");
    }

    public void a(int i, @NotNull String[] strArr) {
        b(i, (Object) strArr);
    }

    public void a(int i, @NotNull StringValue[] stringValueArr) {
        b(i, (Object) stringValueArr);
    }

    public void a(int i, boolean z) {
        a(i, (Object) Boolean.valueOf(z));
    }

    public void a(int i, long j) {
        a(i, (Object) Long.valueOf(j));
    }

    public void a(int i, @NotNull Date date) {
        a(i, (Object) date);
    }

    public void a(int i, @NotNull Rational rational) {
        a(i, (Object) rational);
    }

    public void a(int i, @NotNull Rational[] rationalArr) {
        b(i, (Object) rationalArr);
    }

    public void a(int i, @NotNull byte[] bArr) {
        b(i, (Object) bArr);
    }

    public void a(int i, @NotNull Object obj) {
        if (obj != null) {
            if (!this.f5205a.containsKey(Integer.valueOf(i))) {
                this.b.add(new Tag(i, this));
            }
            this.f5205a.put(Integer.valueOf(i), obj);
            return;
        }
        throw new NullPointerException("cannot set a null object");
    }

    public void b(int i, @NotNull Object obj) {
        a(i, obj);
    }

    public int b(int i) throws MetadataException {
        Integer c2 = c(i);
        if (c2 != null) {
            return c2.intValue();
        }
        Object u = u(i);
        if (u == null) {
            throw new MetadataException("Tag '" + v(i) + "' has not been set -- check using containsTag() first");
        }
        throw new MetadataException("Tag '" + i + "' cannot be converted to int.  It is of type '" + u.getClass() + "'.");
    }

    @Nullable
    public Integer c(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof Number) {
            return Integer.valueOf(((Number) u).intValue());
        }
        if ((u instanceof String) || (u instanceof StringValue)) {
            try {
                return Integer.valueOf(Integer.parseInt(u.toString()));
            } catch (NumberFormatException unused) {
                long j = 0;
                for (byte b2 : u.toString().getBytes()) {
                    j = (j << 8) + ((long) (b2 & 255));
                }
                return Integer.valueOf((int) j);
            }
        } else {
            if (u instanceof Rational[]) {
                Rational[] rationalArr = (Rational[]) u;
                if (rationalArr.length == 1) {
                    return Integer.valueOf(rationalArr[0].intValue());
                }
            } else if (u instanceof byte[]) {
                byte[] bArr = (byte[]) u;
                if (bArr.length == 1) {
                    return Integer.valueOf(bArr[0]);
                }
            } else if (u instanceof int[]) {
                int[] iArr = (int[]) u;
                if (iArr.length == 1) {
                    return Integer.valueOf(iArr[0]);
                }
            } else if (u instanceof short[]) {
                short[] sArr = (short[]) u;
                if (sArr.length == 1) {
                    return Integer.valueOf(sArr[0]);
                }
            }
            return null;
        }
    }

    @Nullable
    public String[] d(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof String[]) {
            return (String[]) u;
        }
        int i2 = 0;
        if (u instanceof String) {
            return new String[]{(String) u};
        } else if (u instanceof StringValue) {
            return new String[]{u.toString()};
        } else if (u instanceof StringValue[]) {
            StringValue[] stringValueArr = (StringValue[]) u;
            String[] strArr = new String[stringValueArr.length];
            while (i2 < strArr.length) {
                strArr[i2] = stringValueArr[i2].toString();
                i2++;
            }
            return strArr;
        } else if (u instanceof int[]) {
            int[] iArr = (int[]) u;
            String[] strArr2 = new String[iArr.length];
            while (i2 < strArr2.length) {
                strArr2[i2] = Integer.toString(iArr[i2]);
                i2++;
            }
            return strArr2;
        } else if (u instanceof byte[]) {
            byte[] bArr = (byte[]) u;
            String[] strArr3 = new String[bArr.length];
            while (i2 < strArr3.length) {
                strArr3[i2] = Byte.toString(bArr[i2]);
                i2++;
            }
            return strArr3;
        } else if (!(u instanceof Rational[])) {
            return null;
        } else {
            Rational[] rationalArr = (Rational[]) u;
            String[] strArr4 = new String[rationalArr.length];
            for (int i3 = 0; i3 < strArr4.length; i3++) {
                strArr4[i3] = rationalArr[i3].toSimpleString(false);
            }
            return strArr4;
        }
    }

    @Nullable
    public StringValue[] e(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof StringValue[]) {
            return (StringValue[]) u;
        }
        if (!(u instanceof StringValue)) {
            return null;
        }
        return new StringValue[]{(StringValue) u};
    }

    @Nullable
    public int[] f(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof int[]) {
            return (int[]) u;
        }
        int i2 = 0;
        if (u instanceof Rational[]) {
            Rational[] rationalArr = (Rational[]) u;
            int[] iArr = new int[rationalArr.length];
            while (i2 < iArr.length) {
                iArr[i2] = rationalArr[i2].intValue();
                i2++;
            }
            return iArr;
        } else if (u instanceof short[]) {
            short[] sArr = (short[]) u;
            int[] iArr2 = new int[sArr.length];
            while (i2 < sArr.length) {
                iArr2[i2] = sArr[i2];
                i2++;
            }
            return iArr2;
        } else if (u instanceof byte[]) {
            byte[] bArr = (byte[]) u;
            int[] iArr3 = new int[bArr.length];
            while (i2 < bArr.length) {
                iArr3[i2] = bArr[i2];
                i2++;
            }
            return iArr3;
        } else if (u instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) u;
            int[] iArr4 = new int[charSequence.length()];
            while (i2 < charSequence.length()) {
                iArr4[i2] = charSequence.charAt(i2);
                i2++;
            }
            return iArr4;
        } else if (!(u instanceof Integer)) {
            return null;
        } else {
            return new int[]{((Integer) u).intValue()};
        }
    }

    @Nullable
    public byte[] g(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof StringValue) {
            return ((StringValue) u).a();
        }
        int i2 = 0;
        if (u instanceof Rational[]) {
            Rational[] rationalArr = (Rational[]) u;
            byte[] bArr = new byte[rationalArr.length];
            while (i2 < bArr.length) {
                bArr[i2] = rationalArr[i2].byteValue();
                i2++;
            }
            return bArr;
        } else if (u instanceof byte[]) {
            return (byte[]) u;
        } else {
            if (u instanceof int[]) {
                int[] iArr = (int[]) u;
                byte[] bArr2 = new byte[iArr.length];
                while (i2 < iArr.length) {
                    bArr2[i2] = (byte) iArr[i2];
                    i2++;
                }
                return bArr2;
            } else if (u instanceof short[]) {
                short[] sArr = (short[]) u;
                byte[] bArr3 = new byte[sArr.length];
                while (i2 < sArr.length) {
                    bArr3[i2] = (byte) sArr[i2];
                    i2++;
                }
                return bArr3;
            } else if (u instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) u;
                byte[] bArr4 = new byte[charSequence.length()];
                while (i2 < charSequence.length()) {
                    bArr4[i2] = (byte) charSequence.charAt(i2);
                    i2++;
                }
                return bArr4;
            } else if (!(u instanceof Integer)) {
                return null;
            } else {
                return new byte[]{((Integer) u).byteValue()};
            }
        }
    }

    public double h(int i) throws MetadataException {
        Double i2 = i(i);
        if (i2 != null) {
            return i2.doubleValue();
        }
        Object u = u(i);
        if (u == null) {
            throw new MetadataException("Tag '" + v(i) + "' has not been set -- check using containsTag() first");
        }
        throw new MetadataException("Tag '" + i + "' cannot be converted to a double.  It is of type '" + u.getClass() + "'.");
    }

    @Nullable
    public Double i(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if ((u instanceof String) || (u instanceof StringValue)) {
            try {
                return Double.valueOf(Double.parseDouble(u.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (u instanceof Number) {
            return Double.valueOf(((Number) u).doubleValue());
        } else {
            return null;
        }
    }

    public float j(int i) throws MetadataException {
        Float k = k(i);
        if (k != null) {
            return k.floatValue();
        }
        Object u = u(i);
        if (u == null) {
            throw new MetadataException("Tag '" + v(i) + "' has not been set -- check using containsTag() first");
        }
        throw new MetadataException("Tag '" + i + "' cannot be converted to a float.  It is of type '" + u.getClass() + "'.");
    }

    @Nullable
    public Float k(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if ((u instanceof String) || (u instanceof StringValue)) {
            try {
                return Float.valueOf(Float.parseFloat(u.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (u instanceof Number) {
            return Float.valueOf(((Number) u).floatValue());
        } else {
            return null;
        }
    }

    public long l(int i) throws MetadataException {
        Long m = m(i);
        if (m != null) {
            return m.longValue();
        }
        Object u = u(i);
        if (u == null) {
            throw new MetadataException("Tag '" + v(i) + "' has not been set -- check using containsTag() first");
        }
        throw new MetadataException("Tag '" + i + "' cannot be converted to a long.  It is of type '" + u.getClass() + "'.");
    }

    @Nullable
    public Long m(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof Number) {
            return Long.valueOf(((Number) u).longValue());
        }
        if ((u instanceof String) || (u instanceof StringValue)) {
            try {
                return Long.valueOf(Long.parseLong(u.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else {
            if (u instanceof Rational[]) {
                Rational[] rationalArr = (Rational[]) u;
                if (rationalArr.length == 1) {
                    return Long.valueOf(rationalArr[0].longValue());
                }
            } else if (u instanceof byte[]) {
                byte[] bArr = (byte[]) u;
                if (bArr.length == 1) {
                    return Long.valueOf((long) bArr[0]);
                }
            } else if (u instanceof int[]) {
                int[] iArr = (int[]) u;
                if (iArr.length == 1) {
                    return Long.valueOf((long) iArr[0]);
                }
            } else if (u instanceof short[]) {
                short[] sArr = (short[]) u;
                if (sArr.length == 1) {
                    return Long.valueOf((long) sArr[0]);
                }
            }
            return null;
        }
    }

    public boolean n(int i) throws MetadataException {
        Boolean o = o(i);
        if (o != null) {
            return o.booleanValue();
        }
        Object u = u(i);
        if (u == null) {
            throw new MetadataException("Tag '" + v(i) + "' has not been set -- check using containsTag() first");
        }
        throw new MetadataException("Tag '" + i + "' cannot be converted to a boolean.  It is of type '" + u.getClass() + "'.");
    }

    @Nullable
    @SuppressWarnings(justification = "keep API interface consistent", value = "NP_BOOLEAN_RETURN_NULL")
    public Boolean o(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof Boolean) {
            return (Boolean) u;
        }
        if ((u instanceof String) || (u instanceof StringValue)) {
            try {
                return Boolean.valueOf(Boolean.getBoolean(u.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!(u instanceof Number)) {
            return null;
        } else {
            return Boolean.valueOf(((Number) u).doubleValue() != 0.0d);
        }
    }

    @Nullable
    public Date p(int i) {
        return a(i, (String) null, (TimeZone) null);
    }

    @Nullable
    public Date a(int i, @Nullable TimeZone timeZone) {
        return a(i, (String) null, timeZone);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e8  */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date a(int r9, @com.drew.lang.annotations.Nullable java.lang.String r10, @com.drew.lang.annotations.Nullable java.util.TimeZone r11) {
        /*
            r8 = this;
            java.lang.Object r9 = r8.u(r9)
            boolean r0 = r9 instanceof java.util.Date
            if (r0 == 0) goto L_0x000b
            java.util.Date r9 = (java.util.Date) r9
            return r9
        L_0x000b:
            boolean r0 = r9 instanceof java.lang.String
            r1 = 0
            if (r0 != 0) goto L_0x0018
            boolean r0 = r9 instanceof com.drew.metadata.StringValue
            if (r0 == 0) goto L_0x0015
            goto L_0x0018
        L_0x0015:
            r4 = r1
            goto L_0x00e5
        L_0x0018:
            r0 = 12
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r2 = "yyyy:MM:dd HH:mm:ss"
            r3 = 0
            r0[r3] = r2
            java.lang.String r2 = "yyyy:MM:dd HH:mm"
            r4 = 1
            r0[r4] = r2
            java.lang.String r2 = "yyyy-MM-dd HH:mm:ss"
            r5 = 2
            r0[r5] = r2
            r2 = 3
            java.lang.String r6 = "yyyy-MM-dd HH:mm"
            r0[r2] = r6
            r2 = 4
            java.lang.String r6 = "yyyy.MM.dd HH:mm:ss"
            r0[r2] = r6
            r2 = 5
            java.lang.String r6 = "yyyy.MM.dd HH:mm"
            r0[r2] = r6
            r2 = 6
            java.lang.String r6 = "yyyy-MM-dd'T'HH:mm:ss"
            r0[r2] = r6
            r2 = 7
            java.lang.String r6 = "yyyy-MM-dd'T'HH:mm"
            r0[r2] = r6
            r2 = 8
            java.lang.String r6 = "yyyy-MM-dd"
            r0[r2] = r6
            r2 = 9
            java.lang.String r6 = "yyyy-MM"
            r0[r2] = r6
            r2 = 10
            java.lang.String r6 = "yyyyMMdd"
            r0[r2] = r6
            r2 = 11
            java.lang.String r6 = "yyyy"
            r0[r2] = r6
            java.lang.String r9 = r9.toString()
            java.lang.String r2 = "(\\d\\d:\\d\\d:\\d\\d)(\\.\\d+)"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.regex.Matcher r2 = r2.matcher(r9)
            boolean r6 = r2.find()
            if (r6 == 0) goto L_0x008d
            java.lang.String r9 = r2.group(r5)
            java.lang.String r9 = r9.substring(r4)
            java.lang.String r10 = "$1"
            java.lang.String r10 = r2.replaceAll(r10)
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x008d:
            java.lang.String r2 = "(Z|[+-]\\d\\d:\\d\\d)$"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.regex.Matcher r2 = r2.matcher(r9)
            boolean r4 = r2.find()
            if (r4 == 0) goto L_0x00c4
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "GMT"
            r9.append(r11)
            java.lang.String r11 = r2.group()
            java.lang.String r4 = "Z"
            java.lang.String r5 = ""
            java.lang.String r11 = r11.replaceAll(r4, r5)
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            java.util.TimeZone r11 = java.util.TimeZone.getTimeZone(r9)
            java.lang.String r9 = ""
            java.lang.String r9 = r2.replaceAll(r9)
        L_0x00c4:
            int r2 = r0.length
        L_0x00c5:
            if (r3 >= r2) goto L_0x0015
            r4 = r0[r3]
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ ParseException -> 0x00e2 }
            r5.<init>(r4)     // Catch:{ ParseException -> 0x00e2 }
            if (r11 == 0) goto L_0x00d4
            r5.setTimeZone(r11)     // Catch:{ ParseException -> 0x00e2 }
            goto L_0x00dd
        L_0x00d4:
            java.lang.String r4 = "GMT"
            java.util.TimeZone r4 = java.util.TimeZone.getTimeZone(r4)     // Catch:{ ParseException -> 0x00e2 }
            r5.setTimeZone(r4)     // Catch:{ ParseException -> 0x00e2 }
        L_0x00dd:
            java.util.Date r4 = r5.parse(r9)     // Catch:{ ParseException -> 0x00e2 }
            goto L_0x00e5
        L_0x00e2:
            int r3 = r3 + 1
            goto L_0x00c5
        L_0x00e5:
            if (r4 != 0) goto L_0x00e8
            return r1
        L_0x00e8:
            if (r10 != 0) goto L_0x00eb
            return r4
        L_0x00eb:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0120 }
            r9.<init>()     // Catch:{ NumberFormatException -> 0x0120 }
            java.lang.String r11 = "."
            r9.append(r11)     // Catch:{ NumberFormatException -> 0x0120 }
            r9.append(r10)     // Catch:{ NumberFormatException -> 0x0120 }
            java.lang.String r9 = r9.toString()     // Catch:{ NumberFormatException -> 0x0120 }
            double r9 = java.lang.Double.parseDouble(r9)     // Catch:{ NumberFormatException -> 0x0120 }
            r0 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ NumberFormatException -> 0x0120 }
            if (r9 < 0) goto L_0x011f
            r10 = 1000(0x3e8, float:1.401E-42)
            if (r9 >= r10) goto L_0x011f
            java.util.Calendar r10 = java.util.Calendar.getInstance()     // Catch:{ NumberFormatException -> 0x0120 }
            r10.setTime(r4)     // Catch:{ NumberFormatException -> 0x0120 }
            r11 = 14
            r10.set(r11, r9)     // Catch:{ NumberFormatException -> 0x0120 }
            java.util.Date r9 = r10.getTime()     // Catch:{ NumberFormatException -> 0x0120 }
            return r9
        L_0x011f:
            return r4
        L_0x0120:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.Directory.a(int, java.lang.String, java.util.TimeZone):java.util.Date");
    }

    @Nullable
    public Rational q(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof Rational) {
            return (Rational) u;
        }
        if (u instanceof Integer) {
            return new Rational((long) ((Integer) u).intValue(), 1);
        }
        if (u instanceof Long) {
            return new Rational(((Long) u).longValue(), 1);
        }
        return null;
    }

    @Nullable
    public Rational[] r(int i) {
        Object u = u(i);
        if (u != null && (u instanceof Rational[])) {
            return (Rational[]) u;
        }
        return null;
    }

    @Nullable
    public String s(int i) {
        Object u = u(i);
        if (u == null) {
            return null;
        }
        if (u instanceof Rational) {
            return ((Rational) u).toSimpleString(true);
        }
        if (u.getClass().isArray()) {
            int length = Array.getLength(u);
            Class<?> componentType = u.getClass().getComponentType();
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            if (Object.class.isAssignableFrom(componentType)) {
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    sb.append(Array.get(u, i2).toString());
                    i2++;
                }
            } else if (componentType.getName().equals("int")) {
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    sb.append(Array.getInt(u, i2));
                    i2++;
                }
            } else if (componentType.getName().equals("short")) {
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    sb.append(Array.getShort(u, i2));
                    i2++;
                }
            } else if (componentType.getName().equals("long")) {
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    sb.append(Array.getLong(u, i2));
                    i2++;
                }
            } else if (componentType.getName().equals("float")) {
                DecimalFormat decimalFormat = new DecimalFormat(e);
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    String format = decimalFormat.format((double) Array.getFloat(u, i2));
                    if (format.equals("-0")) {
                        format = "0";
                    }
                    sb.append(format);
                    i2++;
                }
            } else if (componentType.getName().equals("double")) {
                DecimalFormat decimalFormat2 = new DecimalFormat(e);
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    String format2 = decimalFormat2.format(Array.getDouble(u, i2));
                    if (format2.equals("-0")) {
                        format2 = "0";
                    }
                    sb.append(format2);
                    i2++;
                }
            } else if (componentType.getName().equals("byte")) {
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(' ');
                    }
                    sb.append(Array.getByte(u, i2) & 255);
                    i2++;
                }
            } else {
                a("Unexpected array component type: " + componentType.getName());
            }
            return sb.toString();
        } else if (u instanceof Double) {
            return new DecimalFormat(e).format(((Double) u).doubleValue());
        } else {
            if (u instanceof Float) {
                return new DecimalFormat(e).format((double) ((Float) u).floatValue());
            }
            return u.toString();
        }
    }

    @Nullable
    public String b(int i, String str) {
        byte[] g2 = g(i);
        if (g2 == null) {
            return null;
        }
        try {
            return new String(g2, str);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public StringValue t(int i) {
        Object u = u(i);
        if (u instanceof StringValue) {
            return (StringValue) u;
        }
        return null;
    }

    @Nullable
    public Object u(int i) {
        return this.f5205a.get(Integer.valueOf(i));
    }

    @NotNull
    public String v(int i) {
        HashMap<Integer, String> b2 = b();
        if (b2.containsKey(Integer.valueOf(i))) {
            return b2.get(Integer.valueOf(i));
        }
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }
        return "Unknown tag (0x" + hexString + Operators.BRACKET_END_STR;
    }

    public boolean w(int i) {
        return b().containsKey(Integer.valueOf(i));
    }

    @Nullable
    public String x(int i) {
        if (d || this.c != null) {
            return this.c.a(i);
        }
        throw new AssertionError();
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = a();
        objArr[1] = Integer.valueOf(this.f5205a.size());
        objArr[2] = this.f5205a.size() == 1 ? "tag" : CommandMessage.TYPE_TAGS;
        return String.format("%s Directory (%d %s)", objArr);
    }
}
