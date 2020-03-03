package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import java.text.DecimalFormat;

public class NikonType2MakernoteDescriptor extends TagDescriptor<NikonType2MakernoteDirectory> {
    public NikonType2MakernoteDescriptor(@NotNull NikonType2MakernoteDirectory nikonType2MakernoteDirectory) {
        super(nikonType2MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 1:
                return x();
            case 2:
                return s();
            case 13:
                return l();
            case 14:
                return m();
            case 18:
                return n();
            case 23:
                return o();
            case 24:
                return p();
            case 28:
                return q();
            case 30:
                return g();
            case 34:
                return h();
            case 42:
                return i();
            case 131:
                return f();
            case 132:
                return t();
            case 134:
                return k();
            case 135:
                return c();
            case 136:
                return j();
            case 137:
                return e();
            case 139:
                return r();
            case 141:
                return w();
            case 146:
                return v();
            case 147:
                return d();
            case 177:
                return b();
            case 182:
                return a();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return d(182);
    }

    @Nullable
    public String b() {
        return a(177, "Off", "Minimal", "Low", null, "Normal", null, "High");
    }

    @Nullable
    public String c() {
        return a(135, "Flash Not Used", "Manual Flash", null, "Flash Not Ready", null, null, null, "External Flash", "Fired, Commander Mode", "Fired, TTL Mode");
    }

    @Nullable
    public String d() {
        return a(147, 1, "Lossy (Type 1)", null, "Uncompressed", null, null, null, "Lossless", "Lossy (Type 2)");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String e() {
        /*
            r6 = this;
            r0 = 8
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 2
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r3 = "Single Frame"
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "Continuous"
            r5 = 1
            r2[r5] = r3
            r0[r4] = r2
            java.lang.String r2 = "Delay"
            r0[r5] = r2
            r2 = 0
            r0[r1] = r2
            java.lang.String r1 = "PC Control"
            r2 = 3
            r0[r2] = r1
            java.lang.String r1 = "Exposure Bracketing"
            r2 = 4
            r0[r2] = r1
            java.lang.String r1 = "Auto ISO"
            r2 = 5
            r0[r2] = r1
            java.lang.String r1 = "White-Balance Bracketing"
            r2 = 6
            r0[r2] = r1
            java.lang.String r1 = "IR Control"
            r2 = 7
            r0[r2] = r1
            r1 = 137(0x89, float:1.92E-43)
            java.lang.String r0 = r6.a((int) r1, (java.lang.Object[]) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.makernotes.NikonType2MakernoteDescriptor.e():java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String f() {
        /*
            r6 = this;
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 2
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r3 = "AF"
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "MF"
            r5 = 1
            r2[r5] = r3
            r0[r4] = r2
            java.lang.String r2 = "D"
            r0[r5] = r2
            java.lang.String r2 = "G"
            r0[r1] = r2
            java.lang.String r1 = "VR"
            r2 = 3
            r0[r2] = r1
            r1 = 131(0x83, float:1.84E-43)
            java.lang.String r0 = r6.a((int) r1, (java.lang.Object[]) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.makernotes.NikonType2MakernoteDescriptor.f():java.lang.String");
    }

    @Nullable
    public String g() {
        return a(30, 1, "sRGB", "Adobe RGB");
    }

    @Nullable
    public String h() {
        Integer c = ((NikonType2MakernoteDirectory) this.f5211a).c(34);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 3) {
            return "Normal";
        }
        if (intValue == 5) {
            return "High";
        }
        if (intValue == 7) {
            return "Extra High";
        }
        if (intValue == 65535) {
            return "Auto";
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Light";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String i() {
        Integer c = ((NikonType2MakernoteDirectory) this.f5211a).c(42);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 3) {
            return "Normal";
        }
        if (intValue == 5) {
            return "High";
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Low";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String j() {
        int[] f = ((NikonType2MakernoteDirectory) this.f5211a).f(136);
        if (f == null) {
            return null;
        }
        if (f.length == 4 && f[0] == 0 && f[2] == 0 && f[3] == 0) {
            switch (f[1]) {
                case 0:
                    return "Centre";
                case 1:
                    return "Top";
                case 2:
                    return "Bottom";
                case 3:
                    return "Left";
                case 4:
                    return "Right";
                default:
                    return "Unknown (" + f[1] + Operators.BRACKET_END_STR;
            }
        } else {
            return "Unknown (" + ((NikonType2MakernoteDirectory) this.f5211a).s(136) + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String k() {
        Rational q = ((NikonType2MakernoteDirectory) this.f5211a).q(134);
        if (q == null) {
            return null;
        }
        if (q.intValue() == 1) {
            return "No digital zoom";
        }
        return q.toSimpleString(true) + "x digital zoom";
    }

    @Nullable
    public String l() {
        return j(13);
    }

    @Nullable
    public String m() {
        return j(14);
    }

    @Nullable
    public String n() {
        return j(18);
    }

    @Nullable
    public String o() {
        return j(23);
    }

    @Nullable
    public String p() {
        return j(24);
    }

    @Nullable
    public String q() {
        return j(28);
    }

    @Nullable
    public String r() {
        return j(139);
    }

    @Nullable
    private String j(int i) {
        int[] f = ((NikonType2MakernoteDirectory) this.f5211a).f(i);
        if (f == null || f.length < 2 || f.length < 3 || f[2] == 0) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        double d = (double) (f[0] * f[1]);
        double d2 = (double) f[2];
        Double.isNaN(d);
        Double.isNaN(d2);
        return decimalFormat.format(d / d2) + " EV";
    }

    @Nullable
    public String s() {
        int[] f = ((NikonType2MakernoteDirectory) this.f5211a).f(2);
        if (f == null) {
            return null;
        }
        if (f[0] != 0 || f[1] == 0) {
            return "Unknown (" + ((NikonType2MakernoteDirectory) this.f5211a).s(2) + Operators.BRACKET_END_STR;
        }
        return "ISO " + f[1];
    }

    @Nullable
    public String t() {
        return g(132);
    }

    @Nullable
    public String u() {
        int[] y = ((NikonType2MakernoteDirectory) this.f5211a).y(152);
        if (y == null || y.length < 11) {
            return null;
        }
        return String.format("%.2fm", new Object[]{Double.valueOf(k(y[10]))});
    }

    @Nullable
    public String v() {
        return c(146, "%s degrees");
    }

    @Nullable
    public String w() {
        String s = ((NikonType2MakernoteDirectory) this.f5211a).s(141);
        if (s == null) {
            return null;
        }
        return s.startsWith("MODE1") ? "Mode I (sRGB)" : s;
    }

    @Nullable
    public String x() {
        return a(1, 2);
    }

    private double k(int i) {
        if (i < 0) {
            i += 256;
        }
        return Math.pow(10.0d, (double) (((float) i) / 40.0f)) * 0.01d;
    }
}
