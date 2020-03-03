package com.drew.metadata.exif.makernotes;

import com.alipay.zoloz.toyger.BuildConfig;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.text.DecimalFormat;
import java.util.HashMap;

public class OlympusCameraSettingsMakernoteDescriptor extends TagDescriptor<OlympusCameraSettingsMakernoteDirectory> {
    private static final HashMap<Integer, String> c = new HashMap<>();
    private static final HashMap<Integer, String> d = new HashMap<>();

    public OlympusCameraSettingsMakernoteDescriptor(@NotNull OlympusCameraSettingsMakernoteDirectory olympusCameraSettingsMakernoteDirectory) {
        super(olympusCameraSettingsMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 512:
                return c();
            case 513:
                return d();
            case 514:
                return e();
            case 515:
                return f();
            case 516:
                return g();
            default:
                switch (i) {
                    case 768:
                        return h();
                    case 769:
                        return i();
                    case 770:
                        return j();
                    case 771:
                        return k();
                    case 772:
                        return l();
                    case 773:
                        return m();
                    case 774:
                        return n();
                    default:
                        switch (i) {
                            case 1027:
                                return p();
                            case 1028:
                                return q();
                            case 1029:
                                return r();
                            case 1030:
                                return s();
                            default:
                                switch (i) {
                                    case 1280:
                                        return t();
                                    case OlympusCameraSettingsMakernoteDirectory.C:
                                        return u();
                                    default:
                                        switch (i) {
                                            case OlympusCameraSettingsMakernoteDirectory.E:
                                                return v();
                                            case OlympusCameraSettingsMakernoteDirectory.F:
                                                return w();
                                            case OlympusCameraSettingsMakernoteDirectory.G:
                                                return x();
                                            case OlympusCameraSettingsMakernoteDirectory.H:
                                                return y();
                                            case OlympusCameraSettingsMakernoteDirectory.I:
                                                return z();
                                            default:
                                                switch (i) {
                                                    case OlympusCameraSettingsMakernoteDirectory.J:
                                                        return A();
                                                    case OlympusCameraSettingsMakernoteDirectory.K:
                                                        return B();
                                                    case OlympusCameraSettingsMakernoteDirectory.L:
                                                        return C();
                                                    case OlympusCameraSettingsMakernoteDirectory.M:
                                                        return D();
                                                    default:
                                                        switch (i) {
                                                            case OlympusCameraSettingsMakernoteDirectory.P:
                                                                return F();
                                                            case OlympusCameraSettingsMakernoteDirectory.Q:
                                                                return G();
                                                            default:
                                                                switch (i) {
                                                                    case OlympusCameraSettingsMakernoteDirectory.S:
                                                                        return H();
                                                                    case OlympusCameraSettingsMakernoteDirectory.T:
                                                                        return I();
                                                                    case OlympusCameraSettingsMakernoteDirectory.U:
                                                                        return J();
                                                                    case OlympusCameraSettingsMakernoteDirectory.V:
                                                                        return K();
                                                                    case OlympusCameraSettingsMakernoteDirectory.W:
                                                                        return L();
                                                                    default:
                                                                        switch (i) {
                                                                            case OlympusCameraSettingsMakernoteDirectory.Y:
                                                                                return N();
                                                                            case OlympusCameraSettingsMakernoteDirectory.Z:
                                                                                return O();
                                                                            case OlympusCameraSettingsMakernoteDirectory.aa:
                                                                                return P();
                                                                            case OlympusCameraSettingsMakernoteDirectory.ab:
                                                                                return Q();
                                                                            default:
                                                                                switch (i) {
                                                                                    case 1536:
                                                                                        return S();
                                                                                    case 1537:
                                                                                        return T();
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 1539:
                                                                                                return U();
                                                                                            case 1540:
                                                                                                return V();
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case OlympusCameraSettingsMakernoteDirectory.ai:
                                                                                                        return X();
                                                                                                    case OlympusCameraSettingsMakernoteDirectory.aj:
                                                                                                        return Y();
                                                                                                    case OlympusCameraSettingsMakernoteDirectory.ak:
                                                                                                        return Z();
                                                                                                    case OlympusCameraSettingsMakernoteDirectory.al:
                                                                                                        return aa();
                                                                                                    case OlympusCameraSettingsMakernoteDirectory.am:
                                                                                                        return ab();
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case 0:
                                                                                                                return a();
                                                                                                            case 256:
                                                                                                                return b();
                                                                                                            case 1024:
                                                                                                                return o();
                                                                                                            case OlympusCameraSettingsMakernoteDirectory.O:
                                                                                                                return E();
                                                                                                            case OlympusCameraSettingsMakernoteDirectory.X:
                                                                                                                return M();
                                                                                                            case OlympusCameraSettingsMakernoteDirectory.ac:
                                                                                                                return R();
                                                                                                            case OlympusCameraSettingsMakernoteDirectory.ah:
                                                                                                                return W();
                                                                                                            case OlympusCameraSettingsMakernoteDirectory.an:
                                                                                                                return ac();
                                                                                                            default:
                                                                                                                return super.a(i);
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String a() {
        return a(0, 4);
    }

    @Nullable
    public String b() {
        return a(256, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    public String c() {
        return a(512, 1, "Manual", "Program", "Aperture-priority AE", "Shutter speed priority", "Program-shift");
    }

    @Nullable
    public String d() {
        return a(513, "Off", "On");
    }

    @Nullable
    public String e() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(514);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 5) {
            return "ESP";
        }
        if (intValue == 261) {
            return "Pattern+AF";
        }
        if (intValue == 515) {
            return "Spot+Highlight control";
        }
        if (intValue == 1027) {
            return "Spot+Shadow control";
        }
        switch (intValue) {
            case 2:
                return "Center-weighted average";
            case 3:
                return "Spot";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String f() {
        return f(515);
    }

    @Nullable
    public String g() {
        return a(516, "Off", "On");
    }

    @Nullable
    public String h() {
        return a(768, "Off", "On", "Super Macro");
    }

    @Nullable
    public String i() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(769);
        if (f == null) {
            Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(769);
            if (c2 == null) {
                return null;
            }
            f = new int[]{c2.intValue()};
        }
        if (f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = f[0];
        if (i != 10) {
            switch (i) {
                case 0:
                    sb.append("Single AF");
                    break;
                case 1:
                    sb.append("Sequential shooting AF");
                    break;
                case 2:
                    sb.append("Continuous AF");
                    break;
                case 3:
                    sb.append("Multi AF");
                    break;
                case 4:
                    sb.append("Face detect");
                    break;
                default:
                    sb.append("Unknown (" + f[0] + Operators.BRACKET_END_STR);
                    break;
            }
        } else {
            sb.append("MF");
        }
        if (f.length > 1) {
            sb.append("; ");
            int i2 = f[1];
            if (i2 == 0) {
                sb.append("(none)");
            } else {
                if ((i2 & 1) > 0) {
                    sb.append("S-AF, ");
                }
                if (((i2 >> 2) & 1) > 0) {
                    sb.append("C-AF, ");
                }
                if (((i2 >> 4) & 1) > 0) {
                    sb.append("MF, ");
                }
                if (((i2 >> 5) & 1) > 0) {
                    sb.append("Face detect, ");
                }
                if (((i2 >> 6) & 1) > 0) {
                    sb.append("Imager AF, ");
                }
                if (((i2 >> 7) & 1) > 0) {
                    sb.append("Live View Magnification Frame, ");
                }
                if (((i2 >> 8) & 1) > 0) {
                    sb.append("AF sensor, ");
                }
                sb.setLength(sb.length() - 2);
            }
        }
        return sb.toString();
    }

    @Nullable
    public String j() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(770);
        if (f == null) {
            Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(770);
            if (c2 == null) {
                return null;
            }
            f = new int[]{c2.intValue()};
        }
        if (f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        switch (f[0]) {
            case 0:
                sb.append("AF not used");
                break;
            case 1:
                sb.append("AF used");
                break;
            default:
                sb.append("Unknown (" + f[0] + Operators.BRACKET_END_STR);
                break;
        }
        if (f.length > 1) {
            sb.append("; " + f[1]);
        }
        return sb.toString();
    }

    @Nullable
    public String k() {
        return a(771, "Not Ready", "Ready");
    }

    @Nullable
    public String l() {
        Object u = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).u(772);
        if (u == null || !(u instanceof long[])) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (long j : (long[]) u) {
            if (j != 0) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                if (j == 913916549) {
                    sb.append("Left ");
                } else if (j == 2038007173) {
                    sb.append("Center ");
                } else if (j == 3178875269L) {
                    sb.append("Right ");
                }
                sb.append(String.format("(%d/255,%d/255)-(%d/255,%d/255)", new Object[]{Long.valueOf((j >> 24) & 255), Long.valueOf((j >> 16) & 255), Long.valueOf((j >> 8) & 255), Long.valueOf(j & 255)}));
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public String m() {
        Rational[] r = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).r(773);
        if (r == null) {
            return "n/a";
        }
        if (r.length < 4) {
            return null;
        }
        int i = (r.length == 5 && r[0].longValue() == 0) ? 1 : 0;
        int doubleValue = (int) (r[i].doubleValue() * 100.0d);
        int doubleValue2 = (int) (r[i + 1].doubleValue() * 100.0d);
        int doubleValue3 = (int) (r[i + 2].doubleValue() * 100.0d);
        int doubleValue4 = (int) (r[i + 3].doubleValue() * 100.0d);
        if (doubleValue + doubleValue2 + doubleValue3 + doubleValue4 == 0) {
            return "n/a";
        }
        return String.format("(%d%%,%d%%) (%d%%,%d%%)", new Object[]{Integer.valueOf(doubleValue), Integer.valueOf(doubleValue2), Integer.valueOf(doubleValue3), Integer.valueOf(doubleValue4)});
    }

    @Nullable
    public String n() {
        return a(774, "Off", "On");
    }

    @Nullable
    public String o() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(1024);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 0) {
            return "Off";
        }
        StringBuilder sb = new StringBuilder();
        int intValue = c2.intValue();
        if ((intValue & 1) != 0) {
            sb.append("On, ");
        }
        if (((intValue >> 1) & 1) != 0) {
            sb.append("Fill-in, ");
        }
        if (((intValue >> 2) & 1) != 0) {
            sb.append("Red-eye, ");
        }
        if (((intValue >> 3) & 1) != 0) {
            sb.append("Slow-sync, ");
        }
        if (((intValue >> 4) & 1) != 0) {
            sb.append("Forced On, ");
        }
        if (((intValue >> 5) & 1) != 0) {
            sb.append("2nd Curtain, ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String p() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(1027);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Channel 1, Low";
            case 2:
                return "Channel 2, Low";
            case 3:
                return "Channel 3, Low";
            case 4:
                return "Channel 4, Low";
            default:
                switch (intValue) {
                    case 9:
                        return "Channel 1, Mid";
                    case 10:
                        return "Channel 2, Mid";
                    case 11:
                        return "Channel 3, Mid";
                    case 12:
                        return "Channel 4, Mid";
                    default:
                        switch (intValue) {
                            case 17:
                                return "Channel 1, High";
                            case 18:
                                return "Channel 2, High";
                            case 19:
                                return "Channel 3, High";
                            case 20:
                                return "Channel 4, High";
                            default:
                                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
                        }
                }
        }
    }

    @Nullable
    public String q() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(1028);
        if (f == null || f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = f[0];
        if (i != 0) {
            switch (i) {
                case 3:
                    sb.append("TTL");
                    break;
                case 4:
                    sb.append("Auto");
                    break;
                case 5:
                    sb.append("Manual");
                    break;
                default:
                    sb.append("Unknown (");
                    sb.append(f[0]);
                    sb.append(Operators.BRACKET_END_STR);
                    break;
            }
        } else {
            sb.append("Off");
        }
        for (int i2 = 1; i2 < f.length; i2++) {
            sb.append("; ");
            sb.append(f[i2]);
        }
        return sb.toString();
    }

    @Nullable
    public String r() {
        Rational[] r = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).r(1029);
        if (r == null || r.length == 0) {
            return null;
        }
        if (r.length == 3) {
            if (r[0].getDenominator() == 0 && r[1].getDenominator() == 0 && r[2].getDenominator() == 0) {
                return "n/a";
            }
        } else if (r.length == 4 && r[0].getDenominator() == 0 && r[1].getDenominator() == 0 && r[2].getDenominator() == 0 && r[3].getDenominator() == 0) {
            return "n/a (x4)";
        }
        StringBuilder sb = new StringBuilder();
        for (Rational append : r) {
            sb.append(append);
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String s() {
        Rational[] r = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).r(1030);
        if (r == null || r.length == 0) {
            return "n/a";
        }
        if (r.length == 3) {
            if (r[0].getDenominator() == 0 && r[1].getDenominator() == 0 && r[2].getDenominator() == 0) {
                return "n/a";
            }
        } else if (r.length == 4 && r[0].getDenominator() == 0 && r[1].getDenominator() == 0 && r[2].getDenominator() == 0 && r[3].getDenominator() == 0) {
            return "n/a (x4)";
        }
        StringBuilder sb = new StringBuilder();
        for (Rational append : r) {
            sb.append(append);
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String t() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(1280);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 48) {
            return "3600K (Tungsten light-like)";
        }
        if (intValue == 67) {
            return "Underwater";
        }
        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "Auto (Keep Warm Color Off)";
            default:
                switch (intValue) {
                    case 16:
                        return "7500K (Fine Weather with Shade)";
                    case 17:
                        return "6000K (Cloudy)";
                    case 18:
                        return "5300K (Fine Weather)";
                    default:
                        switch (intValue) {
                            case 20:
                                return "3000K (Tungsten light)";
                            case 21:
                                return "3600K (Tungsten light-like)";
                            case 22:
                                return "Auto Setup";
                            case 23:
                                return "5500K (Flash)";
                            default:
                                switch (intValue) {
                                    case 33:
                                        return "6600K (Daylight fluorescent)";
                                    case 34:
                                        return "4500K (Neutral white fluorescent)";
                                    case 35:
                                        return "4000K (Cool white fluorescent)";
                                    case 36:
                                        return "White Fluorescent";
                                    default:
                                        switch (intValue) {
                                            case 256:
                                                return "One Touch WB 1";
                                            case 257:
                                                return "One Touch WB 2";
                                            case 258:
                                                return "One Touch WB 3";
                                            case 259:
                                                return "One Touch WB 4";
                                            default:
                                                switch (intValue) {
                                                    case 512:
                                                        return "Custom WB 1";
                                                    case 513:
                                                        return "Custom WB 2";
                                                    case 514:
                                                        return "Custom WB 3";
                                                    case 515:
                                                        return "Custom WB 4";
                                                    default:
                                                        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String u() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(OlympusCameraSettingsMakernoteDirectory.C);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 0) {
            return "Auto";
        }
        return c2.toString();
    }

    @Nullable
    public String v() {
        return j(OlympusCameraSettingsMakernoteDirectory.E);
    }

    @Nullable
    public String w() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.F, "Off", "CM1 (Red Enhance)", "CM2 (Green Enhance)", "CM3 (Blue Enhance)", "CM4 (Skin Tones)");
    }

    @Nullable
    public String x() {
        return j(OlympusCameraSettingsMakernoteDirectory.G);
    }

    @Nullable
    public String y() {
        return j(OlympusCameraSettingsMakernoteDirectory.H);
    }

    @Nullable
    public String z() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.I, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    @Nullable
    public String A() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(OlympusCameraSettingsMakernoteDirectory.J);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 0) {
            return "Standard";
        }
        if (intValue == 54) {
            return "Face Portrait";
        }
        if (intValue == 57) {
            return "Bulb";
        }
        if (intValue == 142) {
            return "Hand-held Starlight";
        }
        if (intValue == 154) {
            return "HDR";
        }
        switch (intValue) {
            case 6:
                return "Auto";
            case 7:
                return "Sport";
            case 8:
                return "Portrait";
            case 9:
                return "Landscape+Portrait";
            case 10:
                return "Landscape";
            case 11:
                return "Night Scene";
            case 12:
                return "Self Portrait";
            case 13:
                return "Panorama";
            case 14:
                return "2 in 1";
            case 15:
                return "Movie";
            case 16:
                return "Landscape+Portrait";
            case 17:
                return "Night+Portrait";
            case 18:
                return "Indoor";
            case 19:
                return "Fireworks";
            case 20:
                return "Sunset";
            case 21:
                return "Beauty Skin";
            case 22:
                return "Macro";
            case 23:
                return "Super Macro";
            case 24:
                return "Food";
            case 25:
                return "Documents";
            case 26:
                return "Museum";
            case 27:
                return "Shoot & Select";
            case 28:
                return "Beach & Snow";
            case 29:
                return "Self Portrait+Timer";
            case 30:
                return "Candle";
            case 31:
                return "Available Light";
            case 32:
                return "Behind Glass";
            case 33:
                return "My Mode";
            case 34:
                return "Pet";
            case 35:
                return "Underwater Wide1";
            case 36:
                return "Underwater Macro";
            case 37:
                return "Shoot & Select1";
            case 38:
                return "Shoot & Select2";
            case 39:
                return "High Key";
            case 40:
                return "Digital Image Stabilization";
            case 41:
                return "Auction";
            case 42:
                return "Beach";
            case 43:
                return "Snow";
            case 44:
                return "Underwater Wide2";
            case 45:
                return "Low Key";
            case 46:
                return "Children";
            case 47:
                return "Vivid";
            case 48:
                return "Nature Macro";
            case 49:
                return "Underwater Snapshot";
            case 50:
                return "Shooting Guide";
            default:
                switch (intValue) {
                    case 59:
                        return "Smile Shot";
                    case 60:
                        return "Quick Shutter";
                    default:
                        switch (intValue) {
                            case 63:
                                return "Slow Shutter";
                            case 64:
                                return "Bird Watching";
                            case 65:
                                return "Multiple Exposure";
                            case 66:
                                return "e-Portrait";
                            case 67:
                                return "Soft Background Shot";
                            default:
                                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
                        }
                }
        }
    }

    @Nullable
    public String B() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(OlympusCameraSettingsMakernoteDirectory.K);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int intValue = c2.intValue();
        if ((intValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((intValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((intValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        if (((intValue >> 3) & 1) != 0) {
            sb.append("Auto, ");
        }
        return sb.length() != 0 ? sb.substring(0, sb.length() - 2) : "(none)";
    }

    @Nullable
    public String C() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.L, "Off", "On");
    }

    @Nullable
    public String D() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.M, "Off", "On");
    }

    @Nullable
    public String E() {
        String str;
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.O);
        if (f == null || f.length < 3) {
            return null;
        }
        String format = String.format("%d %d %d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1]), Integer.valueOf(f[2])});
        if (format.equals("0 0 0")) {
            str = "n/a";
        } else if (format.equals("-1 -1 1")) {
            str = "Low Key";
        } else if (format.equals("0 -1 1")) {
            str = "Normal";
        } else if (format.equals("1 -1 1")) {
            str = "High Key";
        } else {
            str = "Unknown (" + format + Operators.BRACKET_END_STR;
        }
        if (f.length <= 3) {
            return str;
        }
        if (f[3] == 0) {
            return str + "; User-Selected";
        } else if (f[3] != 1) {
            return str;
        } else {
            return str + "; Auto-Override";
        }
    }

    @Nullable
    public String F() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.P);
        if (f == null) {
            Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(OlympusCameraSettingsMakernoteDirectory.K);
            if (c2 == null) {
                return null;
            }
            f = new int[]{c2.intValue()};
        }
        if (f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = f[0];
        if (i == 256) {
            sb.append("Monotone");
        } else if (i != 512) {
            switch (i) {
                case 1:
                    sb.append("Vivid");
                    break;
                case 2:
                    sb.append("Natural");
                    break;
                case 3:
                    sb.append("Muted");
                    break;
                case 4:
                    sb.append("Portrait");
                    break;
                case 5:
                    sb.append("i-Enhance");
                    break;
                default:
                    sb.append("Unknown (");
                    sb.append(f[0]);
                    sb.append(Operators.BRACKET_END_STR);
                    break;
            }
        } else {
            sb.append("Sepia");
        }
        if (f.length > 1) {
            sb.append("; ");
            sb.append(f[1]);
        }
        return sb.toString();
    }

    @Nullable
    public String G() {
        return j(OlympusCameraSettingsMakernoteDirectory.Q);
    }

    @Nullable
    public String H() {
        return j(OlympusCameraSettingsMakernoteDirectory.S);
    }

    @Nullable
    public String I() {
        return j(OlympusCameraSettingsMakernoteDirectory.T);
    }

    @Nullable
    public String J() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.U, "n/a", "Neutral", "Yellow", BuildConfig.UI_ORANGE, "Red", "Green");
    }

    @Nullable
    public String K() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.V, "n/a", "Neutral", "Sepia", "Blue", "Purple", "Green");
    }

    @Nullable
    public String L() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.W);
        if (f == null) {
            return null;
        }
        String format = String.format("%d %d %d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1]), Integer.valueOf(f[2])});
        if (format.equals("0 0 0")) {
            return "n/a";
        }
        if (format.equals("-2 -2 1")) {
            return "Off";
        }
        if (format.equals("-1 -2 1")) {
            return "Low";
        }
        if (format.equals("0 -2 1")) {
            return "Standard";
        }
        if (format.equals("1 -2 1")) {
            return "High";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String M() {
        return k(OlympusCameraSettingsMakernoteDirectory.X);
    }

    @Nullable
    public String N() {
        return k(OlympusCameraSettingsMakernoteDirectory.Y);
    }

    @Nullable
    public String O() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.Z);
        if (f == null) {
            return null;
        }
        String format = String.format("%d %d %d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1]), Integer.valueOf(f[2])});
        if (format.equals("0 0 0")) {
            return "n/a";
        }
        if (format.equals("-1 -1 1")) {
            return "Low";
        }
        if (format.equals("0 -1 1")) {
            return "Standard";
        }
        if (format.equals("1 -1 1")) {
            return "High";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String P() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.aa);
        if (f == null || f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24) {
                sb.append(c.get(Integer.valueOf(f[i])));
                sb.append("; ");
            } else {
                sb.append(f[i]);
                sb.append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String Q() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.ab);
        if (f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            if (i == 0) {
                sb.append(d.containsKey(Integer.valueOf(f[i])) ? d.get(Integer.valueOf(f[i])) : "[unknown]");
                sb.append("; ");
            } else if (i == 3) {
                sb.append("Partial Color ");
                sb.append(f[i]);
                sb.append("; ");
            } else if (i == 4) {
                int i2 = f[i];
                if (i2 == 0) {
                    sb.append("No Effect");
                } else if (i2 == 32784) {
                    sb.append("Star Light");
                } else if (i2 == 32800) {
                    sb.append("Pin Hole");
                } else if (i2 == 32816) {
                    sb.append("Frame");
                } else if (i2 == 32832) {
                    sb.append("Soft Focus");
                } else if (i2 == 32848) {
                    sb.append("White Edge");
                } else if (i2 != 32864) {
                    sb.append("Unknown (");
                    sb.append(f[i]);
                    sb.append(Operators.BRACKET_END_STR);
                } else {
                    sb.append("B&W");
                }
                sb.append("; ");
            } else if (i == 6) {
                switch (f[i]) {
                    case 0:
                        sb.append("No Color Filter");
                        break;
                    case 1:
                        sb.append("Yellow Color Filter");
                        break;
                    case 2:
                        sb.append("Orange Color Filter");
                        break;
                    case 3:
                        sb.append("Red Color Filter");
                        break;
                    case 4:
                        sb.append("Green Color Filter");
                        break;
                    default:
                        sb.append("Unknown (");
                        sb.append(f[i]);
                        sb.append(Operators.BRACKET_END_STR);
                        break;
                }
                sb.append("; ");
            } else {
                sb.append(f[i]);
                sb.append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String R() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.ac);
        if (f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            if (i == 0) {
                sb.append("Color ");
                sb.append(f[i]);
                sb.append("; ");
            } else if (i == 3) {
                sb.append("Strength ");
                sb.append(f[i]);
                sb.append("; ");
            } else {
                sb.append(f[i]);
                sb.append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String S() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(1536);
        if (f == null) {
            return null;
        }
        if (f.length == 0 || f[0] == 0) {
            return "Single Shot";
        }
        StringBuilder sb = new StringBuilder();
        if (f[0] != 5 || f.length < 3) {
            switch (f[0]) {
                case 1:
                    sb.append("Continuous Shooting");
                    break;
                case 2:
                    sb.append("Exposure Bracketing");
                    break;
                case 3:
                    sb.append("White Balance Bracketing");
                    break;
                case 4:
                    sb.append("Exposure+WB Bracketing");
                    break;
                default:
                    sb.append("Unknown (");
                    sb.append(f[0]);
                    sb.append(Operators.BRACKET_END_STR);
                    break;
            }
        } else {
            int i = f[2];
            if ((i & 1) > 0) {
                sb.append("AE");
            }
            if (((i >> 1) & 1) > 0) {
                sb.append("WB");
            }
            if (((i >> 2) & 1) > 0) {
                sb.append("FL");
            }
            if (((i >> 3) & 1) > 0) {
                sb.append("MF");
            }
            if (((i >> 6) & 1) > 0) {
                sb.append("Focus");
            }
            sb.append(" Bracketing");
        }
        sb.append(", Shot ");
        sb.append(f[1]);
        return sb.toString();
    }

    @Nullable
    public String T() {
        String str;
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(1537);
        if (f == null) {
            return null;
        }
        if (f.length == 0 || f[0] == 0) {
            return "Off";
        }
        switch (f[0]) {
            case 1:
                str = "Left to Right";
                break;
            case 2:
                str = "Right to Left";
                break;
            case 3:
                str = "Bottom to Top";
                break;
            case 4:
                str = "Top to Bottom";
                break;
            default:
                str = "Unknown (" + f[0] + Operators.BRACKET_END_STR;
                break;
        }
        return String.format("%s, Shot %d", new Object[]{str, Integer.valueOf(f[1])});
    }

    @Nullable
    public String U() {
        return a(1539, 1, "SQ", "HQ", "SHQ", "RAW", "SQ (5)");
    }

    @Nullable
    public String V() {
        return a(1540, "Off", "On, Mode 1", "On, Mode 2", "On, Mode 3", "On, Mode 4");
    }

    @Nullable
    public String W() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.ah);
        if (f == null || f.length < 2) {
            return null;
        }
        int i = f[0];
        int i2 = f[1];
        if (i == 0 && i2 == 0) {
            return IntentIntegrator.e;
        }
        if (i == 9 && i2 == 8) {
            return "Focus-stacked (8 images)";
        }
        return String.format("Unknown (%d %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    @Nullable
    public String X() {
        Integer c2 = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).c(OlympusCameraSettingsMakernoteDirectory.ai);
        if (c2 == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double intValue = (double) c2.intValue();
        Double.isNaN(intValue);
        return String.format("%s kPa", new Object[]{decimalFormat.format(intValue / 10.0d)});
    }

    @Nullable
    public String Y() {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.aj);
        if (f == null || f.length < 2) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double d2 = (double) f[0];
        Double.isNaN(d2);
        double d3 = (double) f[1];
        Double.isNaN(d3);
        return String.format("%s m, %s ft", new Object[]{decimalFormat.format(d2 / 10.0d), decimalFormat.format(d3 / 10.0d)});
    }

    @Nullable
    public String Z() {
        return a((int) OlympusCameraSettingsMakernoteDirectory.ak, "Off", "On");
    }

    @Nullable
    public String aa() {
        String str;
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.al);
        if (f == null || f.length < 2) {
            return null;
        }
        if (f[0] != 0) {
            double d2 = (double) (-f[0]);
            Double.isNaN(d2);
            str = Double.toString(d2 / 10.0d);
        } else {
            str = "n/a";
        }
        return String.format("%s %d", new Object[]{str, Integer.valueOf(f[1])});
    }

    @Nullable
    public String ab() {
        String str;
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(OlympusCameraSettingsMakernoteDirectory.am);
        if (f == null || f.length < 2) {
            return null;
        }
        if (f[0] != 0) {
            double d2 = (double) f[0];
            Double.isNaN(d2);
            str = Double.toString(d2 / 10.0d);
        } else {
            str = "n/a";
        }
        return String.format("%s %d", new Object[]{str, Integer.valueOf(f[1])});
    }

    @Nullable
    public String ac() {
        Object u = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).u(OlympusCameraSettingsMakernoteDirectory.an);
        if (u == null) {
            return null;
        }
        return u.toString();
    }

    @Nullable
    private String j(int i) {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(i);
        if (f == null || f.length < 3) {
            return null;
        }
        return String.format("%d (min %d, max %d)", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1]), Integer.valueOf(f[2])});
    }

    @Nullable
    private String k(int i) {
        int[] f = ((OlympusCameraSettingsMakernoteDirectory) this.f5211a).f(i);
        if (f == null || f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < f.length; i2++) {
            if (i2 == 0) {
                sb.append(d.containsKey(Integer.valueOf(f[i2])) ? d.get(Integer.valueOf(f[i2])) : "[unknown]");
            } else {
                sb.append(f[i2]);
            }
            sb.append("; ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    static {
        d.put(0, "Off");
        d.put(1, "Soft Focus");
        d.put(2, "Pop Art");
        d.put(3, "Pale & Light Color");
        d.put(4, "Light Tone");
        d.put(5, "Pin Hole");
        d.put(6, "Grainy Film");
        d.put(9, "Diorama");
        d.put(10, "Cross Process");
        d.put(12, "Fish Eye");
        d.put(13, "Drawing");
        d.put(14, "Gentle Sepia");
        d.put(15, "Pale & Light Color II");
        d.put(16, "Pop Art II");
        d.put(17, "Pin Hole II");
        d.put(18, "Pin Hole III");
        d.put(19, "Grainy Film II");
        d.put(20, "Dramatic Tone");
        d.put(21, "Punk");
        d.put(22, "Soft Focus 2");
        d.put(23, "Sparkle");
        d.put(24, "Watercolor");
        d.put(25, "Key Line");
        d.put(26, "Key Line II");
        d.put(27, "Miniature");
        d.put(28, "Reflection");
        d.put(29, "Fragmented");
        d.put(31, "Cross Process II");
        d.put(32, "Dramatic Tone II");
        d.put(33, "Watercolor I");
        d.put(34, "Watercolor II");
        d.put(35, "Diorama II");
        d.put(36, "Vintage");
        d.put(37, "Vintage II");
        d.put(38, "Vintage III");
        d.put(39, "Partial Color");
        d.put(40, "Partial Color II");
        d.put(41, "Partial Color III");
        c.put(0, "0");
        c.put(-31999, "Highlights ");
        c.put(-31998, "Shadows ");
        c.put(-31997, "Midtones ");
    }
}
