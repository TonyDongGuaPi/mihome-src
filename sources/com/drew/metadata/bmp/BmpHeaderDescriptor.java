package com.drew.metadata.bmp;

import cn.com.fmsh.communication.core.MessageHead;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.bmp.BmpHeaderDirectory;
import java.text.DecimalFormat;

public class BmpHeaderDescriptor extends TagDescriptor<BmpHeaderDirectory> {
    public BmpHeaderDescriptor(@NotNull BmpHeaderDirectory bmpHeaderDirectory) {
        super(bmpHeaderDirectory);
    }

    public String a(int i) {
        if (i == -2) {
            return a();
        }
        if (i == 5) {
            return b();
        }
        switch (i) {
            case 10:
                return c();
            case 11:
                return d();
            case 12:
            case 13:
            case 14:
            case 15:
                return a(((BmpHeaderDirectory) this.f5211a).m(i), 8);
            case 16:
                return e();
            case 17:
            case 18:
            case 19:
                return a(((BmpHeaderDirectory) this.f5211a).m(i));
            case 20:
                return f();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        BmpHeaderDirectory.BitmapType j = ((BmpHeaderDirectory) this.f5211a).j();
        if (j == null) {
            return null;
        }
        return j.toString();
    }

    @Nullable
    public String b() {
        BmpHeaderDirectory.Compression k = ((BmpHeaderDirectory) this.f5211a).k();
        if (k != null) {
            return k.toString();
        }
        Integer c = ((BmpHeaderDirectory) this.f5211a).c(5);
        if (c == null) {
            return null;
        }
        return "Illegal value 0x" + Integer.toHexString(c.intValue());
    }

    @Nullable
    public String c() {
        BmpHeaderDirectory.RenderingHalftoningAlgorithm l = ((BmpHeaderDirectory) this.f5211a).l();
        if (l == null) {
            return null;
        }
        return l.toString();
    }

    @Nullable
    public String d() {
        BmpHeaderDirectory.ColorEncoding m = ((BmpHeaderDirectory) this.f5211a).m();
        if (m == null) {
            return null;
        }
        return m.toString();
    }

    @Nullable
    public String e() {
        BmpHeaderDirectory.ColorSpaceType n = ((BmpHeaderDirectory) this.f5211a).n();
        if (n == null) {
            return null;
        }
        return n.toString();
    }

    @Nullable
    public String f() {
        BmpHeaderDirectory.RenderingIntent o = ((BmpHeaderDirectory) this.f5211a).o();
        if (o == null) {
            return null;
        }
        return o.toString();
    }

    @Nullable
    public static String a(@Nullable Integer num, int i) {
        if (num == null) {
            return null;
        }
        return a(((long) num.intValue()) & MessageHead.SERIAL_MAK, i);
    }

    @NotNull
    public static String c(int i, int i2) {
        return a(((long) i) & MessageHead.SERIAL_MAK, i2);
    }

    @Nullable
    public static String a(@Nullable Long l, int i) {
        if (l == null) {
            return null;
        }
        return a(l.longValue(), i);
    }

    @NotNull
    public static String a(long j, int i) {
        return String.format("0x%0" + i + "X", new Object[]{Long.valueOf(j)});
    }

    @Nullable
    public static String a(Integer num) {
        if (num == null) {
            return null;
        }
        return a(((long) num.intValue()) & MessageHead.SERIAL_MAK);
    }

    @NotNull
    public static String j(int i) {
        return a(((long) i) & MessageHead.SERIAL_MAK);
    }

    @Nullable
    public static String a(Long l) {
        if (l == null) {
            return null;
        }
        return a(l.longValue());
    }

    @NotNull
    public static String a(long j) {
        double d = (double) j;
        Double.isNaN(d);
        return new DecimalFormat("0.###").format(Double.valueOf(d / 65536.0d));
    }
}
