package com.drew.metadata;

import android.support.media.ExifInterface;
import com.drew.lang.Rational;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.stat.b;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TagDescriptor<T extends Directory> {
    static final /* synthetic */ boolean b = (!TagDescriptor.class.desiredAssertionStatus());
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    protected final T f5211a;

    public TagDescriptor(@NotNull T t) {
        this.f5211a = t;
    }

    @Nullable
    public String a(int i) {
        int length;
        Object u = this.f5211a.u(i);
        if (u == null) {
            return null;
        }
        if (u.getClass().isArray() && (length = Array.getLength(u)) > 16) {
            return String.format("[%d values]", new Object[]{Integer.valueOf(length)});
        } else if (u instanceof Date) {
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").format((Date) u).replaceAll("([0-9]{2} [^ ]+)$", ":$1");
        } else {
            return this.f5211a.s(i);
        }
    }

    @Nullable
    public static String a(@Nullable int[] iArr, int i) {
        if (iArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < 4 && i2 < iArr.length) {
            if (i2 == i) {
                sb.append('.');
            }
            char c = (char) iArr[i2];
            if (c < '0') {
                c = (char) (c + '0');
            }
            if (i2 != 0 || c != '0') {
                sb.append(c);
            }
            i2++;
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, int i2) {
        int[] f = this.f5211a.f(i);
        if (f == null) {
            return null;
        }
        return a(f, i2);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, @NotNull String... strArr) {
        return a(i, 0, strArr);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, int i2, @NotNull String... strArr) {
        String str;
        Long m = this.f5211a.m(i);
        if (m == null) {
            return null;
        }
        long longValue = m.longValue() - ((long) i2);
        if (longValue >= 0 && longValue < ((long) strArr.length) && (str = strArr[(int) longValue]) != null) {
            return str;
        }
        return "Unknown (" + m + Operators.BRACKET_END_STR;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String b(int i) {
        byte[] g = this.f5211a.g(i);
        if (g == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(g.length);
        objArr[1] = g.length == 1 ? "" : "s";
        return String.format("(%d byte%s)", objArr);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String c(int i) {
        Rational q = this.f5211a.q(i);
        if (q == null) {
            return null;
        }
        return q.toSimpleString(true);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String b(int i, int i2) {
        Rational q = this.f5211a.q(i);
        if (q == null) {
            return null;
        }
        return String.format("%." + i2 + "f", new Object[]{Double.valueOf(q.doubleValue())});
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, @NotNull String str) {
        Integer c = this.f5211a.c(i);
        if (c == null) {
            return null;
        }
        return String.format(str, new Object[]{c});
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String b(int i, @NotNull String str) {
        Float k = this.f5211a.k(i);
        if (k == null) {
            return null;
        }
        return String.format(str, new Object[]{k});
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String c(int i, @NotNull String str) {
        String s = this.f5211a.s(i);
        if (s == null) {
            return null;
        }
        return String.format(str, new Object[]{s});
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String d(int i) {
        Long m = this.f5211a.m(i);
        if (m == null) {
            return null;
        }
        return new Date(m.longValue()).toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, @NotNull Object... objArr) {
        Integer c = this.f5211a.c(i);
        if (c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Integer num = c;
        for (int i2 = 0; objArr.length > i2; i2++) {
            String[] strArr = objArr[i2];
            if (strArr != null) {
                char c2 = (num.intValue() & 1) == 1 ? (char) 1 : 0;
                if (strArr instanceof String[]) {
                    String[] strArr2 = strArr;
                    if (b || strArr2.length == 2) {
                        arrayList.add(strArr2[c2]);
                    } else {
                        throw new AssertionError();
                    }
                } else if (c2 != 0 && (strArr instanceof String)) {
                    arrayList.add((String) strArr);
                }
            }
            num = Integer.valueOf(num.intValue() >> 1);
        }
        return StringUtil.a((Iterable<? extends CharSequence>) arrayList, ", ");
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String e(int i) {
        byte[] g = this.f5211a.g(i);
        if (g == null) {
            return null;
        }
        int length = g.length;
        int i2 = 0;
        while (true) {
            if (i2 >= g.length) {
                break;
            }
            byte b2 = g[i2] & 255;
            if (b2 == 0 || b2 > Byte.MAX_VALUE) {
                length = i2;
            } else {
                i2++;
            }
        }
        return new String(g, 0, length);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(int i, Charset charset) {
        byte[] g = this.f5211a.g(i);
        if (g == null) {
            return null;
        }
        try {
            return new String(g, charset.name()).trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String f(int i) {
        Rational q = this.f5211a.q(i);
        if (q != null) {
            return q.toSimpleString(true);
        }
        Double i2 = this.f5211a.i(i);
        if (i2 != null) {
            return new DecimalFormat("0.###").format(i2);
        }
        return null;
    }

    @Nullable
    protected static String a(double d) {
        DecimalFormat decimalFormat = new DecimalFormat(b.m);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return "f/" + decimalFormat.format(d);
    }

    @Nullable
    protected static String b(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(d) + " mm";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String g(int i) {
        Rational[] r = this.f5211a.r(i);
        if (r == null || r.length != 4) {
            return null;
        }
        if (r[0].isZero() && r[2].isZero()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (r[0].equals(r[1])) {
            sb.append(r[0].toSimpleString(true));
            sb.append("mm");
        } else {
            sb.append(r[0].toSimpleString(true));
            sb.append('-');
            sb.append(r[1].toSimpleString(true));
            sb.append("mm");
        }
        if (!r[2].isZero()) {
            sb.append(' ');
            DecimalFormat decimalFormat = new DecimalFormat(b.m);
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            if (r[2].equals(r[3])) {
                sb.append(a(r[2].doubleValue()));
            } else {
                sb.append("f/");
                sb.append(decimalFormat.format(r[2].doubleValue()));
                sb.append('-');
                sb.append(decimalFormat.format(r[3].doubleValue()));
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String h(int i) {
        return a(i, 1, "Top, left side (Horizontal / normal)", "Top, right side (Mirror horizontal)", "Bottom, right side (Rotate 180)", "Bottom, left side (Mirror vertical)", "Left side, top (Mirror horizontal and rotate 270 CW)", "Right side, top (Rotate 90 CW)", "Right side, bottom (Mirror horizontal and rotate 90 CW)", "Left side, bottom (Rotate 270 CW)");
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String i(int i) {
        Float k = this.f5211a.k(i);
        if (k == null) {
            return null;
        }
        if (k.floatValue() <= 1.0f) {
            double floatValue = (double) k.floatValue();
            double log = Math.log(2.0d);
            Double.isNaN(floatValue);
            double exp = (double) ((float) (1.0d / Math.exp(floatValue * log)));
            Double.isNaN(exp);
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            return decimalFormat.format((double) (((float) Math.round(exp * 10.0d)) / 10.0f)) + " sec";
        }
        double floatValue2 = (double) k.floatValue();
        double log2 = Math.log(2.0d);
        Double.isNaN(floatValue2);
        int exp2 = (int) Math.exp(floatValue2 * log2);
        return "1/" + exp2 + " sec";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String a(short s) {
        if (s == 255) {
            return "Other";
        }
        switch (s) {
            case 0:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 1:
                return "Daylight";
            case 2:
                return "Fluorescent";
            case 3:
                return "Tungsten (Incandescent)";
            case 4:
                return ExifInterface.TAG_FLASH;
            default:
                switch (s) {
                    case 9:
                        return "Fine Weather";
                    case 10:
                        return "Cloudy";
                    case 11:
                        return "Shade";
                    case 12:
                        return "Daylight Fluorescent";
                    case 13:
                        return "Day White Fluorescent";
                    case 14:
                        return "Cool White Fluorescent";
                    case 15:
                        return "White Fluorescent";
                    case 16:
                        return "Warm White Fluorescent";
                    case 17:
                        return "Standard Light A";
                    case 18:
                        return "Standard Light B";
                    case 19:
                        return "Standard Light C";
                    case 20:
                        return "D55";
                    case 21:
                        return "D65";
                    case 22:
                        return "D75";
                    case 23:
                        return "D50";
                    case 24:
                        return "ISO Studio Tungsten";
                    default:
                        return a((int) s);
                }
        }
    }
}
