package com.drew.metadata.exif.makernotes;

import cn.com.fmsh.communication.core.MessageHead;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class OlympusFocusInfoMakernoteDescriptor extends TagDescriptor<OlympusFocusInfoMakernoteDirectory> {
    public OlympusFocusInfoMakernoteDescriptor(@NotNull OlympusFocusInfoMakernoteDirectory olympusFocusInfoMakernoteDirectory) {
        super(olympusFocusInfoMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return a();
        }
        if (i == 521) {
            return b();
        }
        if (i == 773) {
            return c();
        }
        if (i == 776) {
            return d();
        }
        if (i == 4609) {
            return e();
        }
        if (i == 5376) {
            return j();
        }
        if (i == 5632) {
            return k();
        }
        switch (i) {
            case OlympusFocusInfoMakernoteDirectory.s /*4612*/:
                return f();
            case OlympusFocusInfoMakernoteDirectory.t /*4613*/:
                return g();
            default:
                switch (i) {
                    case OlympusFocusInfoMakernoteDirectory.v /*4617*/:
                        return h();
                    case OlympusFocusInfoMakernoteDirectory.w /*4618*/:
                        return i();
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    public String a() {
        return a(0, 4);
    }

    @Nullable
    public String b() {
        return a(521, "Off", "On");
    }

    @Nullable
    public String c() {
        Rational q = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).q(773);
        if (q == null || q.getNumerator() == MessageHead.SERIAL_MAK || q.getNumerator() == 0) {
            return "inf";
        }
        StringBuilder sb = new StringBuilder();
        double numerator = (double) q.getNumerator();
        Double.isNaN(numerator);
        sb.append(numerator / 1000.0d);
        sb.append(" m");
        return sb.toString();
    }

    @Nullable
    public String d() {
        Integer c = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).c(OlympusFocusInfoMakernoteDirectory.o);
        if (c == null) {
            return null;
        }
        return c.toString();
    }

    @Nullable
    public String e() {
        int[] f = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).f(4609);
        if (f == null || f.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Short.valueOf((short) f[0]), Short.valueOf((short) f[1])});
        if (format.equals("0 0")) {
            return "Off";
        }
        if (format.equals("1 0")) {
            return "On";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String f() {
        return a((int) OlympusFocusInfoMakernoteDirectory.s, "Bounce or Off", "Direct");
    }

    @Nullable
    public String g() {
        int[] f = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).f(OlympusFocusInfoMakernoteDirectory.t);
        if (f == null) {
            Integer c = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).c(OlympusFocusInfoMakernoteDirectory.t);
            if (c == null) {
                return null;
            }
            f = new int[]{c.intValue()};
        }
        if (f.length == 0) {
            return null;
        }
        String format = String.format("%d", new Object[]{Short.valueOf((short) f[0])});
        if (f.length > 1) {
            format = format + " " + String.format("%d", new Object[]{Short.valueOf((short) f[1])});
        }
        if (format.equals("0")) {
            return "Off";
        }
        if (format.equals("1")) {
            return "On";
        }
        if (format.equals("0 0")) {
            return "Off";
        }
        if (format.equals("1 0")) {
            return "On";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        int[] f = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).f(OlympusFocusInfoMakernoteDirectory.v);
        if (f == null) {
            return null;
        }
        if (((short) f[0]) == 0) {
            return "Off";
        }
        if (((short) f[1]) == 1) {
            return "Full";
        }
        return "On (1/" + ((short) f[1]) + " strength)";
    }

    @Nullable
    public String i() {
        return a((int) OlympusFocusInfoMakernoteDirectory.w, "Off", "On");
    }

    @Nullable
    public String j() {
        return ((OlympusFocusInfoMakernoteDirectory) this.f5211a).s(OlympusFocusInfoMakernoteDirectory.x);
    }

    @Nullable
    public String k() {
        byte[] g = ((OlympusFocusInfoMakernoteDirectory) this.f5211a).g(OlympusFocusInfoMakernoteDirectory.y);
        if (g == null) {
            return null;
        }
        if ((g[0] | g[1] | g[2] | g[3]) == 0) {
            return "Off";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("On, ");
        sb.append((g[43] & 1) > 0 ? "Mode 1" : "Mode 2");
        return sb.toString();
    }
}
