package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Age;
import com.drew.metadata.Face;
import com.drew.metadata.TagDescriptor;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.io.IOException;
import java.text.DecimalFormat;

public class PanasonicMakernoteDescriptor extends TagDescriptor<PanasonicMakernoteDirectory> {
    private static final String[] c = {"Normal", "Portrait", "Scenery", "Sports", "Night Portrait", "Program", "Aperture Priority", "Shutter Priority", "Macro", "Spot", "Manual", "Movie Preview", "Panning", "Simple", "Color Effects", "Self Portrait", "Economy", "Fireworks", "Party", "Snow", "Night Scenery", "Food", "Baby", "Soft Skin", "Candlelight", "Starry Night", "High Sensitivity", "Panorama Assist", "Underwater", "Beach", "Aerial Photo", "Sunset", "Pet", "Intelligent ISO", ClipboardModule.NAME, "High Speed Continuous Shooting", "Intelligent Auto", null, "Multi-aspect", null, "Transform", "Flash Burst", "Pin Hole", "Film Grain", "My Color", "Photo Frame", null, null, null, null, "HDR"};

    public PanasonicMakernoteDescriptor(@NotNull PanasonicMakernoteDirectory panasonicMakernoteDirectory) {
        super(panasonicMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 1:
                return ai();
            case 2:
                return aj();
            case 3:
                return an();
            case 7:
                return ag();
            case 15:
                return ah();
            case 26:
                return g();
            case 28:
                return e();
            case 31:
                return ae();
            case 32:
                return h();
            case 33:
                return Q();
            case 37:
                return am();
            case 38:
                return al();
            case 40:
                return R();
            case 41:
                return S();
            case 42:
                return T();
            case 44:
                return U();
            case 45:
                return V();
            case 46:
                return W();
            case 48:
                return X();
            case 49:
                return Y();
            case 50:
                return Z();
            case 51:
                return ao();
            case 52:
                return aa();
            case 53:
                return ab();
            case 57:
                return N();
            case 58:
                return O();
            case 59:
                return a();
            case 61:
                return P();
            case 62:
                return b();
            case 69:
                return r();
            case 72:
                return s();
            case 73:
                return t();
            case 78:
                return ac();
            case 89:
                return i();
            case 93:
                return k();
            case 96:
                return u();
            case 97:
                return ad();
            case 98:
                return l();
            case 101:
                return q();
            case 102:
                return K();
            case 103:
                return L();
            case 105:
                return m();
            case 107:
                return n();
            case 109:
                return o();
            case 111:
                return p();
            case 112:
                return M();
            case 121:
                return v();
            case 124:
                return w();
            case 137:
                return x();
            case 138:
                return y();
            case 140:
                return z();
            case 141:
                return A();
            case 142:
                return B();
            case 143:
                return C();
            case 144:
                return D();
            case 145:
                return E();
            case 147:
                return F();
            case 150:
                return G();
            case 158:
                return H();
            case 159:
                return I();
            case 171:
                return J();
            case 32768:
                return ak();
            case PanasonicMakernoteDirectory.aP /*32769*/:
                return af();
            case PanasonicMakernoteDirectory.aT /*32775*/:
                return f();
            case PanasonicMakernoteDirectory.aU /*32776*/:
                return c();
            case PanasonicMakernoteDirectory.aV /*32777*/:
                return d();
            case PanasonicMakernoteDirectory.aW /*32784*/:
                return ap();
            case PanasonicMakernoteDirectory.aX /*32786*/:
                return j();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(59, 1, "Off", "On");
    }

    @Nullable
    public String b() {
        return a(62, 1, "Off", "On");
    }

    @Nullable
    public String c() {
        return a(PanasonicMakernoteDirectory.aU, 1, "Off", "On");
    }

    @Nullable
    public String d() {
        return a(PanasonicMakernoteDirectory.aV, 1, "Off", "On");
    }

    @Nullable
    public String e() {
        return a(28, 1, "Off", "On");
    }

    @Nullable
    public String f() {
        return a(PanasonicMakernoteDirectory.aT, 1, "Off", "On");
    }

    @Nullable
    public String g() {
        return a(26, 2, "On, Mode 1", "Off", "On, Mode 2");
    }

    @Nullable
    public String h() {
        return a(32, 1, "Off", "On");
    }

    @Nullable
    public String i() {
        return j(89);
    }

    @Nullable
    public String j() {
        return j(PanasonicMakernoteDirectory.aX);
    }

    @Nullable
    private String j(int i) {
        byte[] g = ((PanasonicMakernoteDirectory) this.f5211a).g(i);
        if (g == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(g);
        try {
            int f = byteArrayReader.f(0);
            int f2 = byteArrayReader.f(2);
            if (f == -1 && f2 == 1) {
                return "Slim Low";
            }
            if (f == -3 && f2 == 2) {
                return "Slim High";
            }
            if (f == 0 && f2 == 0) {
                return "Off";
            }
            if (f == 1 && f2 == 1) {
                return "Stretch Low";
            }
            if (f == 3 && f2 == 2) {
                return "Stretch High";
            }
            return "Unknown (" + f + " " + f2 + Operators.BRACKET_END_STR;
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String k() {
        return a(93, "Off", "Low", "Standard", "High");
    }

    @Nullable
    public String l() {
        return a(98, IntentIntegrator.e, "Yes (Flash required but disabled)");
    }

    @Nullable
    private static String a(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    @Nullable
    public String m() {
        return a(a(105, Charsets.f5194a));
    }

    @Nullable
    public String n() {
        return a(a(107, Charsets.f5194a));
    }

    @Nullable
    public String o() {
        return a(a(109, Charsets.f5194a));
    }

    @Nullable
    public String p() {
        return a(a(111, Charsets.f5194a));
    }

    @Nullable
    public String q() {
        return a(a(101, Charsets.f5194a));
    }

    @Nullable
    public String r() {
        return a(69, "No Bracket", "3 Images, Sequence 0/-/+", "3 Images, Sequence -/0/+", "5 Images, Sequence 0/-/+", "5 Images, Sequence -/0/+", "7 Images, Sequence 0/-/+", "7 Images, Sequence -/0/+");
    }

    @Nullable
    public String s() {
        return a(72, "n/a", "1st", "2nd");
    }

    @Nullable
    public String t() {
        return a(73, 1, "Off", "On");
    }

    @Nullable
    public String u() {
        byte[] g = ((PanasonicMakernoteDirectory) this.f5211a).g(96);
        if (g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < g.length; i++) {
            sb.append(g[i]);
            if (i < g.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    @Nullable
    public String v() {
        return a(121, "Off", "Low", "Standard", "High");
    }

    @Nullable
    public String w() {
        return a(124, "Off", "On");
    }

    @Nullable
    public String x() {
        return a(137, "Auto", "Standard or Custom", "Vivid", "Natural", "Monochrome", "Scenery", "Portrait");
    }

    @Nullable
    public String y() {
        return a(138, "Off", "On");
    }

    @Nullable
    public String z() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(140);
        if (c2 == null) {
            return null;
        }
        return String.valueOf(c2.shortValue());
    }

    @Nullable
    public String A() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(141);
        if (c2 == null) {
            return null;
        }
        return String.valueOf(c2.shortValue());
    }

    @Nullable
    public String B() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(142);
        if (c2 == null) {
            return null;
        }
        return String.valueOf(c2.shortValue());
    }

    @Nullable
    public String C() {
        return a(143, "Normal", "Rotate CW", "Rotate 180", "Rotate CCW", "Tilt Upwards", "Tile Downwards");
    }

    @Nullable
    public String D() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(144);
        if (c2 == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        double shortValue = (double) c2.shortValue();
        Double.isNaN(shortValue);
        return decimalFormat.format(shortValue / 10.0d);
    }

    @Nullable
    public String E() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(145);
        if (c2 == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        double d = (double) (-c2.shortValue());
        Double.isNaN(d);
        return decimalFormat.format(d / 10.0d);
    }

    @Nullable
    public String F() {
        return a(147, "Off", "Left to Right", "Right to Left", "Top to Bottom", "Bottom to Top");
    }

    @Nullable
    public String G() {
        return a(150, "Off", "Time Lapse", "Stop-motion Animation");
    }

    @Nullable
    public String H() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(158);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 0) {
            return "Off";
        }
        if (intValue == 100) {
            return "1 EV";
        }
        if (intValue == 200) {
            return "2 EV";
        }
        if (intValue == 300) {
            return "3 EV";
        }
        if (intValue == 32868) {
            return "1 EV (Auto)";
        }
        if (intValue == 32968) {
            return "2 EV (Auto)";
        }
        if (intValue == 33068) {
            return "3 EV (Auto)";
        }
        return String.format("Unknown (%d)", new Object[]{c2});
    }

    @Nullable
    public String I() {
        return a(159, "Mechanical", "Electronic", "Hybrid");
    }

    @Nullable
    public String J() {
        return a(171, "Off", "On");
    }

    @Nullable
    public String K() {
        return a(a(102, Charsets.f5194a));
    }

    @Nullable
    public String L() {
        return a(a(103, Charsets.f5194a));
    }

    @Nullable
    public String M() {
        return a(112, "Off", null, "Auto", "On");
    }

    @Nullable
    public String N() {
        return a(57, "Normal");
    }

    @Nullable
    public String O() {
        return a(58, 1, Constants.WebViewURL.PAGE_HOME, "Destination");
    }

    @Nullable
    public String P() {
        return a(61, 1, "Normal", "Outdoor/Illuminations/Flower/HDR Art", "Indoor/Architecture/Objects/HDR B&W", "Creative", "Auto", null, "Expressive", "Retro", "Pure", "Elegant", null, "Monochrome", "Dynamic Art", "Silhouette");
    }

    @Nullable
    public String Q() {
        return b(33);
    }

    @Nullable
    public String R() {
        return a(40, 1, "Off", "Warm", "Cool", "Black & White", "Sepia");
    }

    @Nullable
    public String S() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(41);
        if (c2 == null) {
            return null;
        }
        return (((float) c2.intValue()) / 100.0f) + " s";
    }

    @Nullable
    public String T() {
        return a(42, "Off", null, "On", "Indefinite", "Unlimited");
    }

    @Nullable
    public String U() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(44);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 256) {
            return "Low";
        }
        if (intValue == 272) {
            return "Normal";
        }
        if (intValue == 288) {
            return "High";
        }
        switch (intValue) {
            case 0:
                return "Normal";
            case 1:
                return "Low";
            case 2:
                return "High";
            default:
                switch (intValue) {
                    case 6:
                        return "Medium Low";
                    case 7:
                        return "Medium High";
                    default:
                        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String V() {
        return a(45, "Standard (0)", "Low (-1)", "High (+1)", "Lowest (-2)", "Highest (+2)");
    }

    @Nullable
    public String W() {
        return a(46, 1, "Off", "10 s", "2 s");
    }

    @Nullable
    public String X() {
        Integer c2 = ((PanasonicMakernoteDirectory) this.f5211a).c(48);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 1) {
            return "Horizontal";
        }
        if (intValue == 3) {
            return "Rotate 180";
        }
        if (intValue == 6) {
            return "Rotate 90 CW";
        }
        if (intValue == 8) {
            return "Rotate 270 CW";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String Y() {
        return a(49, 1, "Fired", "Enabled but not used", "Disabled but required", "Disabled and not required");
    }

    @Nullable
    public String Z() {
        return a(50, "Normal", "Natural", "Vivid");
    }

    @Nullable
    public String aa() {
        return a(52, 1, "Standard", "Extended");
    }

    @Nullable
    public String ab() {
        return a(53, 1, "Off", "Wide", "Telephoto", "Macro");
    }

    @Nullable
    public String ac() {
        return a(((PanasonicMakernoteDirectory) this.f5211a).j());
    }

    @Nullable
    public String ad() {
        return a(((PanasonicMakernoteDirectory) this.f5211a).k());
    }

    @Nullable
    private String a(@Nullable Face[] faceArr) {
        if (faceArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < faceArr.length) {
            sb.append("Face ");
            int i2 = i + 1;
            sb.append(i2);
            sb.append(": ");
            sb.append(faceArr[i].toString());
            sb.append("\n");
            i = i2;
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }

    @Nullable
    public String ae() {
        return a(31, 1, c);
    }

    @Nullable
    public String af() {
        return a(PanasonicMakernoteDirectory.aP, 1, c);
    }

    @Nullable
    public String ag() {
        return a(7, 1, "Auto", "Manual", null, "Auto, Focus Button", "Auto, Continuous");
    }

    @Nullable
    public String ah() {
        int[] f = ((PanasonicMakernoteDirectory) this.f5211a).f(15);
        if (f == null || f.length < 2) {
            return null;
        }
        int i = f[0];
        if (i == 16) {
            int i2 = f[1];
            if (i2 == 0) {
                return "1-area";
            }
            if (i2 == 16) {
                return "1-area (high speed)";
            }
            return "Unknown (" + f[0] + " " + f[1] + Operators.BRACKET_END_STR;
        } else if (i == 32) {
            switch (f[1]) {
                case 0:
                    return "Auto or Face Detect";
                case 1:
                    return "3-area (left)";
                case 2:
                    return "3-area (center)";
                case 3:
                    return "3-area (right)";
                default:
                    return "Unknown (" + f[0] + " " + f[1] + Operators.BRACKET_END_STR;
            }
        } else if (i == 64) {
            return "Face Detect";
        } else {
            switch (i) {
                case 0:
                    int i3 = f[1];
                    if (i3 == 1) {
                        return "Spot Mode On";
                    }
                    if (i3 == 16) {
                        return "Spot Mode Off";
                    }
                    return "Unknown (" + f[0] + " " + f[1] + Operators.BRACKET_END_STR;
                case 1:
                    switch (f[1]) {
                        case 0:
                            return "Spot Focusing";
                        case 1:
                            return "5-area";
                        default:
                            return "Unknown (" + f[0] + " " + f[1] + Operators.BRACKET_END_STR;
                    }
                default:
                    return "Unknown (" + f[0] + " " + f[1] + Operators.BRACKET_END_STR;
            }
        }
    }

    @Nullable
    public String ai() {
        return a(1, 2, "High", "Normal", null, null, "Very High", "Raw", null, "Motion Picture");
    }

    @Nullable
    public String aj() {
        return a(2, 2);
    }

    @Nullable
    public String ak() {
        return a(32768, 2);
    }

    @Nullable
    public String al() {
        return a(38, 2);
    }

    @Nullable
    public String am() {
        return e(37);
    }

    @Nullable
    public String an() {
        return a(3, 1, "Auto", "Daylight", "Cloudy", "Incandescent", "Manual", null, null, ExifInterface.TAG_FLASH, null, "Black & White", "Manual", "Shade");
    }

    @Nullable
    public String ao() {
        Age y = ((PanasonicMakernoteDirectory) this.f5211a).y(51);
        if (y == null) {
            return null;
        }
        return y.g();
    }

    @Nullable
    public String ap() {
        Age y = ((PanasonicMakernoteDirectory) this.f5211a).y(PanasonicMakernoteDirectory.aW);
        if (y == null) {
            return null;
        }
        return y.g();
    }
}
