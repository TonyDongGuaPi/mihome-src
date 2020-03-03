package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.smarthome.download.Downloads;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class CanonMakernoteDescriptor extends TagDescriptor<CanonMakernoteDirectory> {
    private static final HashMap<Integer, String> c = new HashMap<>();

    public CanonMakernoteDescriptor(@NotNull CanonMakernoteDirectory canonMakernoteDirectory) {
        super(canonMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case CanonMakernoteDirectory.CameraSettings.f5221a /*49409*/:
                return A();
            case CanonMakernoteDirectory.CameraSettings.b /*49410*/:
                return z();
            case CanonMakernoteDirectory.CameraSettings.c /*49411*/:
                return B();
            case CanonMakernoteDirectory.CameraSettings.d /*49412*/:
                return y();
            case CanonMakernoteDirectory.CameraSettings.e /*49413*/:
                return x();
            default:
                switch (i) {
                    case CanonMakernoteDirectory.CameraSettings.i /*49417*/:
                        return D();
                    case CanonMakernoteDirectory.CameraSettings.j /*49418*/:
                        return v();
                    case CanonMakernoteDirectory.CameraSettings.k /*49419*/:
                        return u();
                    case CanonMakernoteDirectory.CameraSettings.l /*49420*/:
                        return C();
                    case CanonMakernoteDirectory.CameraSettings.m /*49421*/:
                        return t();
                    case CanonMakernoteDirectory.CameraSettings.n /*49422*/:
                        return s();
                    case CanonMakernoteDirectory.CameraSettings.o /*49423*/:
                        return r();
                    case CanonMakernoteDirectory.CameraSettings.p /*49424*/:
                        return q();
                    case CanonMakernoteDirectory.CameraSettings.q /*49425*/:
                        return p();
                    case CanonMakernoteDirectory.CameraSettings.r /*49426*/:
                        return E();
                    case CanonMakernoteDirectory.CameraSettings.s /*49427*/:
                        return o();
                    case CanonMakernoteDirectory.CameraSettings.t /*49428*/:
                        return k();
                    default:
                        switch (i) {
                            case CanonMakernoteDirectory.CameraSettings.v /*49430*/:
                                return l();
                            case CanonMakernoteDirectory.CameraSettings.w /*49431*/:
                                return j();
                            case CanonMakernoteDirectory.CameraSettings.x /*49432*/:
                                return i();
                            case CanonMakernoteDirectory.CameraSettings.y /*49433*/:
                                return h();
                            case CanonMakernoteDirectory.CameraSettings.z /*49434*/:
                                return m();
                            case CanonMakernoteDirectory.CameraSettings.A /*49435*/:
                                return n();
                            case CanonMakernoteDirectory.CameraSettings.B /*49436*/:
                                return F();
                            case CanonMakernoteDirectory.CameraSettings.C /*49437*/:
                                return g();
                            case CanonMakernoteDirectory.CameraSettings.D /*49438*/:
                                return G();
                            case CanonMakernoteDirectory.CameraSettings.E /*49439*/:
                                return H();
                            case CanonMakernoteDirectory.CameraSettings.F /*49440*/:
                                return f();
                            case CanonMakernoteDirectory.CameraSettings.G /*49441*/:
                                return I();
                            default:
                                switch (i) {
                                    case CanonMakernoteDirectory.CameraSettings.J /*49445*/:
                                        return J();
                                    case CanonMakernoteDirectory.CameraSettings.K /*49446*/:
                                        return K();
                                    case CanonMakernoteDirectory.CameraSettings.L /*49447*/:
                                        return L();
                                    default:
                                        switch (i) {
                                            case CanonMakernoteDirectory.FocalLength.c /*49678*/:
                                                return c();
                                            case CanonMakernoteDirectory.FocalLength.d /*49679*/:
                                                return b();
                                            default:
                                                switch (i) {
                                                    case 12:
                                                        return a();
                                                    case CanonMakernoteDirectory.CameraSettings.g /*49415*/:
                                                        return w();
                                                    case CanonMakernoteDirectory.CameraSettings.M /*49449*/:
                                                        return M();
                                                    case CanonMakernoteDirectory.CameraSettings.N /*49453*/:
                                                        return N();
                                                    case CanonMakernoteDirectory.FocalLength.f5222a /*49671*/:
                                                        return e();
                                                    case CanonMakernoteDirectory.AFInfo.k /*53770*/:
                                                        return d();
                                                    default:
                                                        return super.a(i);
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String a() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(12);
        if (c2 == null) {
            return null;
        }
        return String.format("%04X%05d", new Object[]{Integer.valueOf((c2.intValue() >> 8) & 255), Integer.valueOf(c2.intValue() & 255)});
    }

    @Nullable
    public String b() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.FocalLength.d);
        if (c2 == null) {
            return null;
        }
        boolean z = false;
        if (c2.intValue() > 61440) {
            c2 = Integer.valueOf(Integer.valueOf(65535 - c2.intValue()).intValue() + 1);
            z = true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "-" : "");
        sb.append(Float.toString(((float) c2.intValue()) / 32.0f));
        sb.append(" EV");
        return sb.toString();
    }

    @Nullable
    public String c() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.FocalLength.c);
        if (c2 == null) {
            return null;
        }
        if ((c2.intValue() & 7) == 0) {
            return "Right";
        }
        if ((c2.intValue() & 7) == 1) {
            return "Centre";
        }
        if ((c2.intValue() & 7) == 2) {
            return "Left";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String d() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.AFInfo.k);
        if (c2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if ((c2.intValue() & (1 << i)) != 0) {
                if (sb.length() != 0) {
                    sb.append(',');
                }
                sb.append(i);
            }
        }
        return sb.length() == 0 ? "None" : sb.toString();
    }

    @Nullable
    public String e() {
        return a((int) CanonMakernoteDirectory.FocalLength.f5222a, "Auto", "Sunny", "Cloudy", "Tungsten", "Florescent", ExifInterface.TAG_FLASH, "Custom");
    }

    @Nullable
    public String f() {
        return a((int) CanonMakernoteDirectory.CameraSettings.F, "Single", "Continuous");
    }

    @Nullable
    public String g() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.C);
        if (c2 == null) {
            return null;
        }
        if (((c2.intValue() >> 14) & 1) != 0) {
            return "External E-TTL";
        }
        if (((c2.intValue() >> 13) & 1) != 0) {
            return "Internal flash";
        }
        if (((c2.intValue() >> 11) & 1) != 0) {
            return "FP sync used";
        }
        if (((c2.intValue() >> 4) & 1) != 0) {
            return "FP sync enabled";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.y);
        if (c2 == null) {
            return null;
        }
        return c2.intValue() != 0 ? Integer.toString(c2.intValue()) : "";
    }

    @Nullable
    public String i() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.x);
        if (c2 == null) {
            return null;
        }
        String h = h();
        return Integer.toString(c2.intValue()) + " " + h;
    }

    @Nullable
    public String j() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.w);
        if (c2 == null) {
            return null;
        }
        String h = h();
        return Integer.toString(c2.intValue()) + " " + h;
    }

    @Nullable
    public String k() {
        return a((int) CanonMakernoteDirectory.CameraSettings.t, "Easy shooting", "Program", "Tv-priority", "Av-priority", "Manual", "A-DEP");
    }

    @Nullable
    public String l() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.v);
        if (c2 == null) {
            return null;
        }
        if (c.containsKey(c2)) {
            return c.get(c2);
        }
        return String.format("Unknown (%d)", new Object[]{c2});
    }

    @Nullable
    public String m() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.z);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() <= 512) {
            return a(Math.exp((j(c2.intValue()) * Math.log(2.0d)) / 2.0d));
        }
        return String.format("Unknown (%d)", new Object[]{c2});
    }

    @Nullable
    public String n() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.A);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() <= 512) {
            return a(Math.exp((j(c2.intValue()) * Math.log(2.0d)) / 2.0d));
        }
        return String.format("Unknown (%d)", new Object[]{c2});
    }

    @Nullable
    public String o() {
        return a(CanonMakernoteDirectory.CameraSettings.s, 12288, "None (MF)", "Auto selected", "Right", "Centre", "Left");
    }

    @Nullable
    public String p() {
        return a(CanonMakernoteDirectory.CameraSettings.q, 3, "Evaluative", "Partial", "Centre weighted");
    }

    @Nullable
    public String q() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.p);
        if (c2 == null) {
            return null;
        }
        if ((c2.intValue() & 16384) != 0) {
            return "" + (c2.intValue() & -16385);
        }
        int intValue = c2.intValue();
        if (intValue == 0) {
            return "Not specified (see ISOSpeedRatings tag)";
        }
        switch (intValue) {
            case 15:
                return "Auto";
            case 16:
                return "50";
            case 17:
                return "100";
            case 18:
                return XmlyAuthErrorNoConstants.g;
            case 19:
                return "400";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String r() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.o);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 65535) {
            return "Low";
        }
        switch (intValue) {
            case 0:
                return "Normal";
            case 1:
                return "High";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String s() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.n);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 65535) {
            return "Low";
        }
        switch (intValue) {
            case 0:
                return "Normal";
            case 1:
                return "High";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String t() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.m);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 65535) {
            return "Low";
        }
        switch (intValue) {
            case 0:
                return "Normal";
            case 1:
                return "High";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String u() {
        return a((int) CanonMakernoteDirectory.CameraSettings.k, "Full auto", "Manual", "Landscape", "Fast shutter", "Slow shutter", "Night", "B&W", "Sepia", "Portrait", "Sports", "Macro / Closeup", "Pan focus");
    }

    @Nullable
    public String v() {
        return a((int) CanonMakernoteDirectory.CameraSettings.j, "Large", "Medium", "Small");
    }

    @Nullable
    public String w() {
        return a((int) CanonMakernoteDirectory.CameraSettings.g, "One-shot", "AI Servo", "AI Focus", "Manual Focus", "Single", "Continuous", "Manual Focus");
    }

    @Nullable
    public String x() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.e);
        if (c2 == null) {
            return null;
        }
        switch (c2.intValue()) {
            case 0:
                Integer c3 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.b);
                if (c3 != null) {
                    return c3.intValue() == 0 ? "Single shot" : "Single shot with self-timer";
                }
                return "Continuous";
            case 1:
                return "Continuous";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String y() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.d);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 16) {
            return "External flash";
        }
        switch (intValue) {
            case 0:
                return "No flash fired";
            case 1:
                return "Auto";
            case 2:
                return "On";
            case 3:
                return "Red-eye reduction";
            case 4:
                return "Slow-synchro";
            case 5:
                return "Auto and red-eye reduction";
            case 6:
                return "On and red-eye reduction";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String z() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.b);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 0) {
            return "Self timer not used";
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        StringBuilder sb = new StringBuilder();
        double intValue = (double) c2.intValue();
        Double.isNaN(intValue);
        sb.append(decimalFormat.format(intValue * 0.1d));
        sb.append(" sec");
        return sb.toString();
    }

    @Nullable
    public String A() {
        return a(CanonMakernoteDirectory.CameraSettings.f5221a, 1, "Macro", "Normal");
    }

    @Nullable
    public String B() {
        return a(CanonMakernoteDirectory.CameraSettings.c, 2, "Normal", "Fine", null, "Superfine");
    }

    @Nullable
    public String C() {
        return a((int) CanonMakernoteDirectory.CameraSettings.l, "No digital zoom", "2x", "4x");
    }

    @Nullable
    public String D() {
        return a(CanonMakernoteDirectory.CameraSettings.i, 1, "JPEG", "CRW+THM", "AVI+THM", "TIF", "TIF+JPEG", "CR2", "CR2+JPEG", null, "MOV", "MP4");
    }

    @Nullable
    public String E() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.r);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 3) {
            return "Close-up (Macro)";
        }
        if (intValue == 8) {
            return "Locked (Pan Mode)";
        }
        switch (intValue) {
            case 0:
                return "Manual";
            case 1:
                return "Auto";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String F() {
        return a((int) CanonMakernoteDirectory.CameraSettings.B, "Flash did not fire", "Flash fired");
    }

    @Nullable
    public String G() {
        return a(CanonMakernoteDirectory.CameraSettings.D, 0, "Single", "Continuous", null, null, null, null, null, null, "Manual");
    }

    @Nullable
    public String H() {
        return a(CanonMakernoteDirectory.CameraSettings.E, 0, "Normal AE", "Exposure Compensation", "AE Lock", "AE Lock + Exposure Comp.", "No AE");
    }

    @Nullable
    public String I() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.G);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 65535) {
            return c2.toString();
        }
        return a((double) (((float) c2.intValue()) / 10.0f));
    }

    @Nullable
    public String J() {
        return a(CanonMakernoteDirectory.CameraSettings.J, 0, "Center", "AF Point");
    }

    @Nullable
    public String K() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.K);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 100) {
            return "My Color Data";
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Vivid";
            case 2:
                return "Neutral";
            case 3:
                return "Smooth";
            case 4:
                return "Sepia";
            case 5:
                return "B&W";
            case 6:
                return "Custom";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String L() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.L);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 0) {
            return "n/a";
        }
        if (intValue == 1280) {
            return "Full";
        }
        if (intValue == 1282) {
            return "Medium";
        }
        if (intValue == 1284) {
            return "Low";
        }
        if (intValue == 32767) {
            return "n/a";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String M() {
        Integer c2 = ((CanonMakernoteDirectory) this.f5211a).c(CanonMakernoteDirectory.CameraSettings.M);
        if (c2 == null) {
            return null;
        }
        return c2.intValue() == 32767 ? "n/a" : c2.toString();
    }

    @Nullable
    public String N() {
        return a(CanonMakernoteDirectory.CameraSettings.N, 0, "n/a", "sRAW1 (mRAW)", "sRAW2 (sRAW)");
    }

    private double j(int i) {
        int i2;
        if (i < 0) {
            i = -i;
            i2 = -1;
        } else {
            i2 = 1;
        }
        int i3 = i & 31;
        int i4 = i - i3;
        if (i3 == 12) {
            i3 = 10;
        } else if (i3 == 20) {
            i3 = 21;
        }
        double d = (double) (i2 * (i4 + i3));
        Double.isNaN(d);
        return d / 32.0d;
    }

    static {
        c.put(1, "Canon EF 50mm f/1.8");
        c.put(2, "Canon EF 28mm f/2.8");
        c.put(3, "Canon EF 135mm f/2.8 Soft");
        c.put(4, "Canon EF 35-105mm f/3.5-4.5 or Sigma Lens");
        c.put(5, "Canon EF 35-70mm f/3.5-4.5");
        c.put(6, "Canon EF 28-70mm f/3.5-4.5 or Sigma or Tokina Lens");
        c.put(7, "Canon EF 100-300mm f/5.6L");
        c.put(8, "Canon EF 100-300mm f/5.6 or Sigma or Tokina Lens");
        c.put(9, "Canon EF 70-210mm f/4");
        c.put(10, "Canon EF 50mm f/2.5 Macro or Sigma Lens");
        c.put(11, "Canon EF 35mm f/2");
        c.put(13, "Canon EF 15mm f/2.8 Fisheye");
        c.put(14, "Canon EF 50-200mm f/3.5-4.5L");
        c.put(15, "Canon EF 50-200mm f/3.5-4.5");
        c.put(16, "Canon EF 35-135mm f/3.5-4.5");
        c.put(17, "Canon EF 35-70mm f/3.5-4.5A");
        c.put(18, "Canon EF 28-70mm f/3.5-4.5");
        c.put(20, "Canon EF 100-200mm f/4.5A");
        c.put(21, "Canon EF 80-200mm f/2.8L");
        c.put(22, "Canon EF 20-35mm f/2.8L or Tokina Lens");
        c.put(23, "Canon EF 35-105mm f/3.5-4.5");
        c.put(24, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        c.put(25, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        c.put(26, "Canon EF 100mm f/2.8 Macro or Other Lens");
        c.put(27, "Canon EF 35-80mm f/4-5.6");
        c.put(28, "Canon EF 80-200mm f/4.5-5.6 or Tamron Lens");
        c.put(29, "Canon EF 50mm f/1.8 II");
        c.put(30, "Canon EF 35-105mm f/4.5-5.6");
        c.put(31, "Canon EF 75-300mm f/4-5.6 or Tamron Lens");
        c.put(32, "Canon EF 24mm f/2.8 or Sigma Lens");
        c.put(33, "Voigtlander or Carl Zeiss Lens");
        c.put(35, "Canon EF 35-80mm f/4-5.6");
        c.put(36, "Canon EF 38-76mm f/4.5-5.6");
        c.put(37, "Canon EF 35-80mm f/4-5.6 or Tamron Lens");
        c.put(38, "Canon EF 80-200mm f/4.5-5.6");
        c.put(39, "Canon EF 75-300mm f/4-5.6");
        c.put(40, "Canon EF 28-80mm f/3.5-5.6");
        c.put(41, "Canon EF 28-90mm f/4-5.6");
        c.put(42, "Canon EF 28-200mm f/3.5-5.6 or Tamron Lens");
        c.put(43, "Canon EF 28-105mm f/4-5.6");
        c.put(44, "Canon EF 90-300mm f/4.5-5.6");
        c.put(45, "Canon EF-S 18-55mm f/3.5-5.6 [II]");
        c.put(46, "Canon EF 28-90mm f/4-5.6");
        c.put(47, "Zeiss Milvus 35mm f/2 or 50mm f/2");
        c.put(48, "Canon EF-S 18-55mm f/3.5-5.6 IS");
        c.put(49, "Canon EF-S 55-250mm f/4-5.6 IS");
        c.put(50, "Canon EF-S 18-200mm f/3.5-5.6 IS");
        c.put(51, "Canon EF-S 18-135mm f/3.5-5.6 IS");
        c.put(52, "Canon EF-S 18-55mm f/3.5-5.6 IS II");
        c.put(53, "Canon EF-S 18-55mm f/3.5-5.6 III");
        c.put(54, "Canon EF-S 55-250mm f/4-5.6 IS II");
        c.put(94, "Canon TS-E 17mm f/4L");
        c.put(95, "Canon TS-E 24.0mm f/3.5 L II");
        c.put(124, "Canon MP-E 65mm f/2.8 1-5x Macro Photo");
        c.put(125, "Canon TS-E 24mm f/3.5L");
        c.put(126, "Canon TS-E 45mm f/2.8");
        c.put(127, "Canon TS-E 90mm f/2.8");
        c.put(129, "Canon EF 300mm f/2.8L");
        c.put(130, "Canon EF 50mm f/1.0L");
        c.put(131, "Canon EF 28-80mm f/2.8-4L or Sigma Lens");
        c.put(132, "Canon EF 1200mm f/5.6L");
        c.put(134, "Canon EF 600mm f/4L IS");
        c.put(135, "Canon EF 200mm f/1.8L");
        c.put(136, "Canon EF 300mm f/2.8L");
        c.put(137, "Canon EF 85mm f/1.2L or Sigma or Tamron Lens");
        c.put(138, "Canon EF 28-80mm f/2.8-4L");
        c.put(139, "Canon EF 400mm f/2.8L");
        c.put(140, "Canon EF 500mm f/4.5L");
        c.put(141, "Canon EF 500mm f/4.5L");
        c.put(142, "Canon EF 300mm f/2.8L IS");
        c.put(143, "Canon EF 500mm f/4L IS or Sigma Lens");
        c.put(144, "Canon EF 35-135mm f/4-5.6 USM");
        c.put(145, "Canon EF 100-300mm f/4.5-5.6 USM");
        c.put(146, "Canon EF 70-210mm f/3.5-4.5 USM");
        c.put(147, "Canon EF 35-135mm f/4-5.6 USM");
        c.put(148, "Canon EF 28-80mm f/3.5-5.6 USM");
        c.put(149, "Canon EF 100mm f/2 USM");
        c.put(150, "Canon EF 14mm f/2.8L or Sigma Lens");
        c.put(151, "Canon EF 200mm f/2.8L");
        c.put(152, "Canon EF 300mm f/4L IS or Sigma Lens");
        c.put(153, "Canon EF 35-350mm f/3.5-5.6L or Sigma or Tamron Lens");
        c.put(154, "Canon EF 20mm f/2.8 USM or Zeiss Lens");
        c.put(155, "Canon EF 85mm f/1.8 USM");
        c.put(156, "Canon EF 28-105mm f/3.5-4.5 USM or Tamron Lens");
        c.put(160, "Canon EF 20-35mm f/3.5-4.5 USM or Tamron or Tokina Lens");
        c.put(161, "Canon EF 28-70mm f/2.8L or Sigma or Tamron Lens");
        c.put(162, "Canon EF 200mm f/2.8L");
        c.put(163, "Canon EF 300mm f/4L");
        c.put(164, "Canon EF 400mm f/5.6L");
        c.put(165, "Canon EF 70-200mm f/2.8 L");
        c.put(166, "Canon EF 70-200mm f/2.8 L + 1.4x");
        c.put(167, "Canon EF 70-200mm f/2.8 L + 2x");
        c.put(168, "Canon EF 28mm f/1.8 USM or Sigma Lens");
        c.put(169, "Canon EF 17-35mm f/2.8L or Sigma Lens");
        c.put(170, "Canon EF 200mm f/2.8L II");
        c.put(171, "Canon EF 300mm f/4L");
        c.put(172, "Canon EF 400mm f/5.6L or Sigma Lens");
        c.put(173, "Canon EF 180mm Macro f/3.5L or Sigma Lens");
        c.put(174, "Canon EF 135mm f/2L or Other Lens");
        c.put(175, "Canon EF 400mm f/2.8L");
        c.put(176, "Canon EF 24-85mm f/3.5-4.5 USM");
        c.put(177, "Canon EF 300mm f/4L IS");
        c.put(178, "Canon EF 28-135mm f/3.5-5.6 IS");
        c.put(179, "Canon EF 24mm f/1.4L");
        c.put(180, "Canon EF 35mm f/1.4L or Other Lens");
        c.put(181, "Canon EF 100-400mm f/4.5-5.6L IS + 1.4x or Sigma Lens");
        c.put(182, "Canon EF 100-400mm f/4.5-5.6L IS + 2x or Sigma Lens");
        c.put(183, "Canon EF 100-400mm f/4.5-5.6L IS or Sigma Lens");
        c.put(184, "Canon EF 400mm f/2.8L + 2x");
        c.put(185, "Canon EF 600mm f/4L IS");
        c.put(Integer.valueOf(Opcodes.cW), "Canon EF 70-200mm f/4L");
        c.put(187, "Canon EF 70-200mm f/4L + 1.4x");
        c.put(188, "Canon EF 70-200mm f/4L + 2x");
        c.put(189, "Canon EF 70-200mm f/4L + 2.8x");
        c.put(190, "Canon EF 100mm f/2.8 Macro USM");
        c.put(191, "Canon EF 400mm f/4 DO IS");
        c.put(193, "Canon EF 35-80mm f/4-5.6 USM");
        c.put(194, "Canon EF 80-200mm f/4.5-5.6 USM");
        c.put(195, "Canon EF 35-105mm f/4.5-5.6 USM");
        c.put(Integer.valueOf(Downloads.STATUS_QUEUED_FOR_WIFI), "Canon EF 75-300mm f/4-5.6 USM");
        c.put(Integer.valueOf(Opcodes.dg), "Canon EF 75-300mm f/4-5.6 IS USM");
        c.put(Integer.valueOf(Opcodes.dh), "Canon EF 50mm f/1.4 USM or Zeiss Lens");
        c.put(199, "Canon EF 28-80mm f/3.5-5.6 USM");
        c.put(200, "Canon EF 75-300mm f/4-5.6 USM");
        c.put(201, "Canon EF 28-80mm f/3.5-5.6 USM");
        c.put(202, "Canon EF 28-80mm f/3.5-5.6 USM IV");
        c.put(208, "Canon EF 22-55mm f/4-5.6 USM");
        c.put(209, "Canon EF 55-200mm f/4.5-5.6");
        c.put(210, "Canon EF 28-90mm f/4-5.6 USM");
        c.put(211, "Canon EF 28-200mm f/3.5-5.6 USM");
        c.put(Integer.valueOf(TbsListener.ErrorCode.COPY_FAIL), "Canon EF 28-105mm f/4-5.6 USM");
        c.put(Integer.valueOf(TbsListener.ErrorCode.COPY_SRCDIR_ERROR), "Canon EF 90-300mm f/4.5-5.6 USM or Tamron Lens");
        c.put(Integer.valueOf(TbsListener.ErrorCode.COPY_TMPDIR_ERROR), "Canon EF-S 18-55mm f/3.5-5.6 USM");
        c.put(215, "Canon EF 55-200mm f/4.5-5.6 II USM");
        c.put(217, "Tamron AF 18-270mm f/3.5-6.3 Di II VC PZD");
        c.put(224, "Canon EF 70-200mm f/2.8L IS");
        c.put(225, "Canon EF 70-200mm f/2.8L IS + 1.4x");
        c.put(Integer.valueOf(TbsListener.ErrorCode.DEXOAT_EXCEPTION), "Canon EF 70-200mm f/2.8L IS + 2x");
        c.put(Integer.valueOf(TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL), "Canon EF 70-200mm f/2.8L IS + 2.8x");
        c.put(Integer.valueOf(TbsListener.ErrorCode.INCR_ERROR_DETAIL), "Canon EF 28-105mm f/3.5-4.5 USM");
        c.put(Integer.valueOf(TbsListener.ErrorCode.INSTALL_FROM_UNZIP), "Canon EF 16-35mm f/2.8L");
        c.put(Integer.valueOf(TbsListener.ErrorCode.RENAME_SUCCESS), "Canon EF 24-70mm f/2.8L");
        c.put(Integer.valueOf(TbsListener.ErrorCode.RENAME_FAIL), "Canon EF 17-40mm f/4L");
        c.put(232, "Canon EF 70-300mm f/4.5-5.6 DO IS USM");
        c.put(233, "Canon EF 28-300mm f/3.5-5.6L IS");
        c.put(234, "Canon EF-S 17-85mm f/4-5.6 IS USM or Tokina Lens");
        c.put(235, "Canon EF-S 10-22mm f/3.5-4.5 USM");
        c.put(236, "Canon EF-S 60mm f/2.8 Macro USM");
        c.put(237, "Canon EF 24-105mm f/4L IS");
        c.put(238, "Canon EF 70-300mm f/4-5.6 IS USM");
        c.put(239, "Canon EF 85mm f/1.2L II");
        c.put(Integer.valueOf(PsExtractor.VIDEO_STREAM_MASK), "Canon EF-S 17-55mm f/2.8 IS USM");
        c.put(241, "Canon EF 50mm f/1.2L");
        c.put(242, "Canon EF 70-200mm f/4L IS");
        c.put(243, "Canon EF 70-200mm f/4L IS + 1.4x");
        c.put(Integer.valueOf(IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE), "Canon EF 70-200mm f/4L IS + 2x");
        c.put(245, "Canon EF 70-200mm f/4L IS + 2.8x");
        c.put(246, "Canon EF 16-35mm f/2.8L II");
        c.put(247, "Canon EF 14mm f/2.8L II USM");
        c.put(248, "Canon EF 200mm f/2L IS or Sigma Lens");
        c.put(249, "Canon EF 800mm f/5.6L IS");
        c.put(250, "Canon EF 24mm f/1.4L II or Sigma Lens");
        c.put(251, "Canon EF 70-200mm f/2.8L IS II USM");
        c.put(252, "Canon EF 70-200mm f/2.8L IS II USM + 1.4x");
        c.put(253, "Canon EF 70-200mm f/2.8L IS II USM + 2x");
        c.put(Integer.valueOf(ExifDirectoryBase.g), "Canon EF 100mm f/2.8L Macro IS USM");
        c.put(255, "Sigma 24-105mm f/4 DG OS HSM | A or Other Sigma Lens");
        c.put(488, "Canon EF-S 15-85mm f/3.5-5.6 IS USM");
        c.put(Integer.valueOf(Downloads.STATUS_CANNOT_RESUME), "Canon EF 70-300mm f/4-5.6L IS USM");
        c.put(Integer.valueOf(Downloads.STATUS_CANCELED), "Canon EF 8-15mm f/4L Fisheye USM");
        c.put(Integer.valueOf(Downloads.STATUS_UNKNOWN_ERROR), "Canon EF 300mm f/2.8L IS II USM");
        c.put(Integer.valueOf(Downloads.STATUS_FILE_ERROR), "Canon EF 400mm f/2.8L IS II USM");
        c.put(493, "Canon EF 500mm f/4L IS II USM or EF 24-105mm f4L IS USM");
        c.put(Integer.valueOf(Downloads.STATUS_UNHANDLED_HTTP_CODE), "Canon EF 600mm f/4.0L IS II USM");
        c.put(Integer.valueOf(Downloads.STATUS_HTTP_DATA_ERROR), "Canon EF 24-70mm f/2.8L II USM");
        c.put(Integer.valueOf(Downloads.STATUS_HTTP_EXCEPTION), "Canon EF 200-400mm f/4L IS USM");
        c.put(Integer.valueOf(Downloads.STATUS_DEVICE_NOT_FOUND_ERROR), "Canon EF 200-400mm f/4L IS USM + 1.4x");
        c.put(502, "Canon EF 28mm f/2.8 IS USM");
        c.put(503, "Canon EF 24mm f/2.8 IS USM");
        c.put(504, "Canon EF 24-70mm f/4L IS USM");
        c.put(505, "Canon EF 35mm f/2 IS USM");
        c.put(506, "Canon EF 400mm f/4 DO IS II USM");
        c.put(507, "Canon EF 16-35mm f/4L IS USM");
        c.put(508, "Canon EF 11-24mm f/4L USM");
        c.put(747, "Canon EF 100-400mm f/4.5-5.6L IS II USM");
        c.put(750, "Canon EF 35mm f/1.4L II USM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aI), "Canon EF-S 18-135mm f/3.5-5.6 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aJ), "Canon EF-M 18-55mm f/3.5-5.6 IS STM or Tamron Lens");
        c.put(4144, "Canon EF 40mm f/2.8 STM");
        c.put(4145, "Canon EF-M 22mm f/2 STM");
        c.put(4146, "Canon EF-S 18-55mm f/3.5-5.6 IS STM");
        c.put(4147, "Canon EF-M 11-22mm f/4-5.6 IS STM");
        c.put(4148, "Canon EF-S 55-250mm f/4-5.6 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aO), "Canon EF-M 55-200mm f/4.5-6.3 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aP), "Canon EF-S 10-18mm f/4.5-5.6 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aR), "Canon EF 24-105mm f/3.5-5.6 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aS), "Canon EF-M 15-45mm f/3.5-6.3 IS STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aT), "Canon EF-S 24mm f/2.8 STM");
        c.put(Integer.valueOf(OlympusMakernoteDirectory.aV), "Canon EF 50mm f/1.8 STM");
        c.put(36912, "Canon EF-S 18-135mm f/3.5-5.6 IS USM");
        c.put(65535, SensorsDataManager.u);
    }
}
