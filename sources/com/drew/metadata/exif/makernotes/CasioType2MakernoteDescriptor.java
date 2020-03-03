package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;

public class CasioType2MakernoteDescriptor extends TagDescriptor<CasioType2MakernoteDirectory> {
    public CasioType2MakernoteDescriptor(@NotNull CasioType2MakernoteDirectory casioType2MakernoteDirectory) {
        super(casioType2MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 2:
                return z();
            case 3:
                return y();
            case 4:
                return x();
            case 8:
                return w();
            case 9:
                return v();
            case 13:
                return u();
            case 20:
                return t();
            case 25:
                return s();
            case 29:
                return r();
            case 31:
                return q();
            case 32:
                return p();
            case 33:
                return o();
            case 8192:
                return n();
            case 8209:
                return m();
            case 8210:
                return l();
            case 8226:
                return k();
            case CasioType2MakernoteDirectory.v /*8244*/:
                return j();
            case 12288:
                return i();
            case 12289:
                return h();
            case 12290:
                return g();
            case 12291:
                return f();
            case 12294:
                return e();
            case 12308:
                return d();
            case 12309:
                return c();
            case 12310:
                return b();
            case CasioType2MakernoteDirectory.F /*12311*/:
                return a();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a((int) CasioType2MakernoteDirectory.F, "Off");
    }

    @Nullable
    public String b() {
        return a(12310, "Off");
    }

    @Nullable
    public String c() {
        return a(12309, "Off");
    }

    @Nullable
    public String d() {
        return a(12308, "Off", "On");
    }

    @Nullable
    public String e() {
        return ((CasioType2MakernoteDirectory) this.f5211a).s(12294);
    }

    @Nullable
    public String f() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(12291);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 1) {
            return "Fixation";
        }
        if (intValue == 6) {
            return "Multi-Area Focus";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String g() {
        return a(12290, 3, "Fine");
    }

    @Nullable
    public String h() {
        return a(12289, 1, "Off");
    }

    @Nullable
    public String i() {
        return a(12288, 2, "Normal");
    }

    @Nullable
    public String j() {
        return a((int) CasioType2MakernoteDirectory.v, "Off");
    }

    @Nullable
    public String k() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(8226);
        if (c == null) {
            return null;
        }
        return Integer.toString(c.intValue()) + " mm";
    }

    @Nullable
    public String l() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(8210);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 4 || intValue == 12) {
            return ExifInterface.TAG_FLASH;
        }
        switch (intValue) {
            case 0:
                return "Manual";
            case 1:
                return "Auto";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String m() {
        return ((CasioType2MakernoteDirectory) this.f5211a).s(8209);
    }

    @Nullable
    public String n() {
        byte[] g = ((CasioType2MakernoteDirectory) this.f5211a).g(8192);
        if (g == null) {
            return null;
        }
        return "<" + g.length + " bytes of image data>";
    }

    @Nullable
    public String o() {
        return a(33, "-1", "Normal", "+1");
    }

    @Nullable
    public String p() {
        return a(32, "-1", "Normal", "+1");
    }

    @Nullable
    public String q() {
        return a(31, "-1", "Normal", "+1");
    }

    @Nullable
    public String r() {
        Double i = ((CasioType2MakernoteDirectory) this.f5211a).i(29);
        if (i == null) {
            return null;
        }
        return b(i.doubleValue() / 10.0d);
    }

    @Nullable
    public String s() {
        return a(25, "Auto", "Daylight", "Shade", "Tungsten", "Florescent", "Manual");
    }

    @Nullable
    public String t() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(20);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 6) {
            return "100";
        }
        if (intValue == 9) {
            return XmlyAuthErrorNoConstants.g;
        }
        switch (intValue) {
            case 3:
                return "50";
            case 4:
                return Tags.Phone.MRED_PHONE;
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String u() {
        return a(13, "Normal", "Macro");
    }

    @Nullable
    public String v() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(9);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "640 x 480 pixels";
        }
        if (intValue == 36) {
            return "3008 x 2008 pixels";
        }
        switch (intValue) {
            case 4:
                return "1600 x 1200 pixels";
            case 5:
                return "2048 x 1536 pixels";
            default:
                switch (intValue) {
                    case 20:
                        return "2288 x 1712 pixels";
                    case 21:
                        return "2592 x 1944 pixels";
                    case 22:
                        return "2304 x 1728 pixels";
                    default:
                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String w() {
        return a(8, 1, "Fine", "Super Fine");
    }

    @Nullable
    public String x() {
        return ((CasioType2MakernoteDirectory) this.f5211a).s(4);
    }

    @Nullable
    public String y() {
        Integer c = ((CasioType2MakernoteDirectory) this.f5211a).c(3);
        if (c == null) {
            return null;
        }
        return Integer.toString(c.intValue()) + " bytes";
    }

    @Nullable
    public String z() {
        int[] f = ((CasioType2MakernoteDirectory) this.f5211a).f(2);
        if (f == null || f.length != 2) {
            return ((CasioType2MakernoteDirectory) this.f5211a).s(2);
        }
        return f[0] + " x " + f[1] + " pixels";
    }
}
