package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.imaging.PhotographicConversions;
import com.drew.lang.DateUtil;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class OlympusMakernoteDescriptor extends TagDescriptor<OlympusMakernoteDirectory> {
    public OlympusMakernoteDescriptor(@NotNull OlympusMakernoteDirectory olympusMakernoteDirectory) {
        super(olympusMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 257:
                return Y();
            case 258:
                return X();
            case 259:
                return W();
            default:
                switch (i) {
                    case 512:
                        return au();
                    case 513:
                        return at();
                    case 514:
                        return ar();
                    case 515:
                        return as();
                    case 516:
                        return aj();
                    case 517:
                        return ak();
                    default:
                        switch (i) {
                            case 4096:
                                return ao();
                            case 4097:
                                return ap();
                            case 4098:
                                return aq();
                            default:
                                switch (i) {
                                    case 4106:
                                        return ah();
                                    case 4107:
                                        return ag();
                                    default:
                                        switch (i) {
                                            case OlympusMakernoteDirectory.al /*4119*/:
                                                return ac();
                                            case OlympusMakernoteDirectory.am /*4120*/:
                                                return ad();
                                            default:
                                                switch (i) {
                                                    case 61442:
                                                        return a();
                                                    case 61443:
                                                        return b();
                                                    case OlympusMakernoteDirectory.CameraSettings.c /*61444*/:
                                                        return c();
                                                    case 61445:
                                                        return d();
                                                    case 61446:
                                                        return e();
                                                    case OlympusMakernoteDirectory.CameraSettings.f /*61447*/:
                                                        return f();
                                                    case OlympusMakernoteDirectory.CameraSettings.g /*61448*/:
                                                        return g();
                                                    case OlympusMakernoteDirectory.CameraSettings.h /*61449*/:
                                                        return h();
                                                    case OlympusMakernoteDirectory.CameraSettings.i /*61450*/:
                                                        return i();
                                                    case OlympusMakernoteDirectory.CameraSettings.j /*61451*/:
                                                        return j();
                                                    case OlympusMakernoteDirectory.CameraSettings.k /*61452*/:
                                                        return k();
                                                    case OlympusMakernoteDirectory.CameraSettings.l /*61453*/:
                                                        return l();
                                                    case OlympusMakernoteDirectory.CameraSettings.m /*61454*/:
                                                        return m();
                                                    case OlympusMakernoteDirectory.CameraSettings.n /*61455*/:
                                                        return n();
                                                    default:
                                                        switch (i) {
                                                            case OlympusMakernoteDirectory.CameraSettings.o /*61457*/:
                                                                return o();
                                                            case OlympusMakernoteDirectory.CameraSettings.p /*61458*/:
                                                                return p();
                                                            case OlympusMakernoteDirectory.CameraSettings.q /*61459*/:
                                                                return q();
                                                            case OlympusMakernoteDirectory.CameraSettings.r /*61460*/:
                                                                return r();
                                                            case OlympusMakernoteDirectory.CameraSettings.s /*61461*/:
                                                                return s();
                                                            case OlympusMakernoteDirectory.CameraSettings.t /*61462*/:
                                                                return t();
                                                            case OlympusMakernoteDirectory.CameraSettings.u /*61463*/:
                                                                return u();
                                                            case OlympusMakernoteDirectory.CameraSettings.v /*61464*/:
                                                                return v();
                                                            default:
                                                                switch (i) {
                                                                    case OlympusMakernoteDirectory.CameraSettings.w /*61467*/:
                                                                        return w();
                                                                    case OlympusMakernoteDirectory.CameraSettings.x /*61468*/:
                                                                        return x();
                                                                    case OlympusMakernoteDirectory.CameraSettings.y /*61469*/:
                                                                        return y();
                                                                    case OlympusMakernoteDirectory.CameraSettings.z /*61470*/:
                                                                        return z();
                                                                    case OlympusMakernoteDirectory.CameraSettings.A /*61471*/:
                                                                        return A();
                                                                    case OlympusMakernoteDirectory.CameraSettings.B /*61472*/:
                                                                        return B();
                                                                    case OlympusMakernoteDirectory.CameraSettings.C /*61473*/:
                                                                        return C();
                                                                    case OlympusMakernoteDirectory.CameraSettings.D /*61474*/:
                                                                        return D();
                                                                    case OlympusMakernoteDirectory.CameraSettings.E /*61475*/:
                                                                        return E();
                                                                    case OlympusMakernoteDirectory.CameraSettings.F /*61476*/:
                                                                        return F();
                                                                    case OlympusMakernoteDirectory.CameraSettings.G /*61477*/:
                                                                        return G();
                                                                    case OlympusMakernoteDirectory.CameraSettings.H /*61478*/:
                                                                        return H();
                                                                    case OlympusMakernoteDirectory.CameraSettings.I /*61479*/:
                                                                        return I();
                                                                    case OlympusMakernoteDirectory.CameraSettings.J /*61480*/:
                                                                        return J();
                                                                    case OlympusMakernoteDirectory.CameraSettings.K /*61481*/:
                                                                        return K();
                                                                    case OlympusMakernoteDirectory.CameraSettings.L /*61482*/:
                                                                        return L();
                                                                    case OlympusMakernoteDirectory.CameraSettings.M /*61483*/:
                                                                        return M();
                                                                    case OlympusMakernoteDirectory.CameraSettings.N /*61484*/:
                                                                        return N();
                                                                    case OlympusMakernoteDirectory.CameraSettings.O /*61485*/:
                                                                        return O();
                                                                    case OlympusMakernoteDirectory.CameraSettings.P /*61486*/:
                                                                        return P();
                                                                    case OlympusMakernoteDirectory.CameraSettings.Q /*61487*/:
                                                                        return Q();
                                                                    case OlympusMakernoteDirectory.CameraSettings.R /*61488*/:
                                                                        return R();
                                                                    case OlympusMakernoteDirectory.CameraSettings.S /*61489*/:
                                                                        return S();
                                                                    case OlympusMakernoteDirectory.CameraSettings.T /*61490*/:
                                                                        return T();
                                                                    case OlympusMakernoteDirectory.CameraSettings.U /*61491*/:
                                                                        return U();
                                                                    default:
                                                                        switch (i) {
                                                                            case 0:
                                                                                return V();
                                                                            case 519:
                                                                                return al();
                                                                            case 521:
                                                                                return am();
                                                                            case 770:
                                                                                return an();
                                                                            case 4100:
                                                                                return ai();
                                                                            case OlympusMakernoteDirectory.ae /*4111*/:
                                                                                return Z();
                                                                            case 4113:
                                                                                return aa();
                                                                            case OlympusMakernoteDirectory.ak /*4117*/:
                                                                                return ab();
                                                                            case OlympusMakernoteDirectory.aD /*4137*/:
                                                                                return ae();
                                                                            case OlympusMakernoteDirectory.aO /*4149*/:
                                                                                return af();
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

    @Nullable
    public String a() {
        return a(61442, "P", "A", "S", "M");
    }

    @Nullable
    public String b() {
        return a(61443, "Normal", "Red-eye reduction", "Rear flash sync", "Wireless");
    }

    @Nullable
    public String c() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.c, "Auto", "Daylight", "Cloudy", "Tungsten", null, "Custom", null, "Fluorescent", "Fluorescent 2", null, null, "Custom 2", "Custom 3");
    }

    @Nullable
    public String d() {
        return a(61445, "2560 x 1920", "1600 x 1200", "1280 x 960", "640 x 480");
    }

    @Nullable
    public String e() {
        return a(61446, "Raw", "Super Fine", "Fine", "Standard", "Economy", "Extra Fine");
    }

    @Nullable
    public String f() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.f, "Single", "Continuous", "Self Timer", null, "Bracketing", "Interval", "UHS Continuous", "HS Continuous");
    }

    @Nullable
    public String g() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.g, "Multi-Segment", "Centre Weighted", "Spot");
    }

    @Nullable
    public String h() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.h);
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(Math.pow((longValue / 8.0d) - 1.0d, 2.0d) * 3.125d);
    }

    @Nullable
    public String i() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.i);
        if (m == null) {
            return null;
        }
        double longValue = (double) (49 - m.longValue());
        Double.isNaN(longValue);
        double pow = Math.pow(longValue / 8.0d, 2.0d);
        DecimalFormat decimalFormat = new DecimalFormat("0.###");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(pow) + " sec";
    }

    @Nullable
    public String j() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.j);
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return a(Math.pow((longValue / 16.0d) - 0.5d, 2.0d));
    }

    @Nullable
    public String k() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.k, "Off", "On");
    }

    @Nullable
    public String l() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.l, "Off", "Electronic magnification", "Digital zoom 2x");
    }

    @Nullable
    public String m() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.m);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        sb.append(decimalFormat.format((longValue / 3.0d) - 2.0d));
        sb.append(" EV");
        return sb.toString();
    }

    @Nullable
    public String n() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.n, "1/3 EV", "2/3 EV", "1 EV");
    }

    @Nullable
    public String o() {
        if (!((OlympusMakernoteDirectory) this.f5211a).j()) {
            return SensorsDataManager.u;
        }
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.o);
        if (m == null) {
            return null;
        }
        return m + " min";
    }

    @Nullable
    public String p() {
        if (!((OlympusMakernoteDirectory) this.f5211a).j()) {
            return SensorsDataManager.u;
        }
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.p);
        if (m == null) {
            return null;
        }
        return Long.toString(m.longValue());
    }

    @Nullable
    public String q() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.q);
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return b(longValue / 256.0d);
    }

    @Nullable
    public String r() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.r);
        if (m == null) {
            return null;
        }
        if (m.longValue() == 0) {
            return "Infinity";
        }
        return m + " mm";
    }

    @Nullable
    public String s() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.s, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    public String t() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.t);
        if (m == null) {
            return null;
        }
        int longValue = (int) (m.longValue() & 255);
        int longValue2 = (int) ((m.longValue() >> 16) & 255);
        int longValue3 = ((int) (255 & (m.longValue() >> 8))) + 1970;
        if (!DateUtil.a(longValue3, longValue2, longValue)) {
            return "Invalid date";
        }
        return String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(longValue3), Integer.valueOf(longValue2 + 1), Integer.valueOf(longValue)});
    }

    @Nullable
    public String u() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.u);
        if (m == null) {
            return null;
        }
        int longValue = (int) ((m.longValue() >> 8) & 255);
        int longValue2 = (int) ((m.longValue() >> 16) & 255);
        int longValue3 = (int) (255 & m.longValue());
        if (!DateUtil.b(longValue, longValue2, longValue3)) {
            return "Invalid time";
        }
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(longValue), Integer.valueOf(longValue2), Integer.valueOf(longValue3)});
    }

    @Nullable
    public String v() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.u);
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return a(Math.pow((longValue / 16.0d) - 0.5d, 2.0d));
    }

    @Nullable
    public String w() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.w, "Off", "On");
    }

    @Nullable
    public String x() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.x);
        if (m == null) {
            return null;
        }
        return m.longValue() == 0 ? "File Number Memory Off" : Long.toString(m.longValue());
    }

    @Nullable
    public String y() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.y);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return decimalFormat.format(longValue / 256.0d);
    }

    @Nullable
    public String z() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.z);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return decimalFormat.format(longValue / 256.0d);
    }

    @Nullable
    public String A() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.A);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return decimalFormat.format(longValue / 256.0d);
    }

    @Nullable
    public String B() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.B);
        if (m == null) {
            return null;
        }
        return Long.toString(m.longValue() - 3);
    }

    @Nullable
    public String C() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.C);
        if (m == null) {
            return null;
        }
        return Long.toString(m.longValue() - 3);
    }

    @Nullable
    public String D() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.D, "Hard", "Normal", "Soft");
    }

    @Nullable
    public String E() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.E, "None", "Portrait", "Text", "Night Portrait", "Sunset", "Sports Action");
    }

    @Nullable
    public String F() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.F);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        double longValue = (double) (m.longValue() - 6);
        Double.isNaN(longValue);
        sb.append(decimalFormat.format(longValue / 3.0d));
        sb.append(" EV");
        return sb.toString();
    }

    @Nullable
    public String G() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.G, "100", XmlyAuthErrorNoConstants.g, "400", "800", "Auto", Tags.Phone.MRED_PHONE);
    }

    @Nullable
    public String H() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.H, "DiMAGE 7", "DiMAGE 5", "DiMAGE S304", "DiMAGE S404", "DiMAGE 7i", "DiMAGE 7Hi", "DiMAGE A1", "DiMAGE S414");
    }

    @Nullable
    public String I() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.I, "Still Image", "Time Lapse Movie");
    }

    @Nullable
    public String J() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.J, "Standard Form", "Data Form");
    }

    @Nullable
    public String K() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.K, "Natural Color", "Black & White", "Vivid Color", "Solarization", "AdobeRGB");
    }

    @Nullable
    public String L() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.L);
        if (m == null) {
            return null;
        }
        return Long.toString(m.longValue() - 3);
    }

    @Nullable
    public String M() {
        return super.a((int) OlympusMakernoteDirectory.CameraSettings.M);
    }

    @Nullable
    public String N() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.N, "Did Not Fire", "Fired");
    }

    @Nullable
    public String O() {
        Long m = ((OlympusMakernoteDirectory) this.f5211a).m(OlympusMakernoteDirectory.CameraSettings.O);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        Double.isNaN(longValue);
        return decimalFormat.format((longValue / 8.0d) - 6.0d);
    }

    @Nullable
    public String P() {
        return super.a((int) OlympusMakernoteDirectory.CameraSettings.P);
    }

    @Nullable
    public String Q() {
        return super.a((int) OlympusMakernoteDirectory.CameraSettings.Q);
    }

    @Nullable
    public String R() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.R, "No Zone or AF Failed", "Center Zone (Horizontal Orientation)", "Center Zone (Vertical Orientation)", "Left Zone", "Right Zone");
    }

    @Nullable
    public String S() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.S, "Auto Focus", "Manual Focus");
    }

    @Nullable
    public String T() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.T, "Wide Focus (Normal)", "Spot Focus");
    }

    @Nullable
    public String U() {
        return a((int) OlympusMakernoteDirectory.CameraSettings.U, "Exposure", ExifInterface.TAG_CONTRAST, ExifInterface.TAG_SATURATION, "Filter");
    }

    @Nullable
    public String V() {
        return a(0, 2);
    }

    @Nullable
    public String W() {
        return a(259, "Raw", "Super Fine", "Fine", "Standard", "Extra Fine");
    }

    @Nullable
    public String X() {
        return a(258, "Raw", "Super Fine", "Fine", "Standard", "Extra Fine");
    }

    @Nullable
    public String Y() {
        return a(257, "Natural Colour", "Black & White", "Vivid Colour", "Solarization", "AdobeRGB");
    }

    @Nullable
    public String Z() {
        return a((int) OlympusMakernoteDirectory.ae, "Normal", "Hard", "Soft");
    }

    @Nullable
    public String aa() {
        int[] f = ((OlympusMakernoteDirectory) this.f5211a).f(4113);
        if (f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            sb.append((short) f[i]);
            if (i < f.length - 1) {
                sb.append(" ");
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public String ab() {
        int[] f = ((OlympusMakernoteDirectory) this.f5211a).f(OlympusMakernoteDirectory.ak);
        if (f == null) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1])});
        if (format.equals("1 0")) {
            return "Auto";
        }
        if (format.equals("1 2")) {
            return "Auto (2)";
        }
        if (format.equals("1 4")) {
            return "Auto (4)";
        }
        if (format.equals("2 2")) {
            return "3000 Kelvin";
        }
        if (format.equals("2 3")) {
            return "3700 Kelvin";
        }
        if (format.equals("2 4")) {
            return "4000 Kelvin";
        }
        if (format.equals("2 5")) {
            return "4500 Kelvin";
        }
        if (format.equals("2 6")) {
            return "5500 Kelvin";
        }
        if (format.equals("2 7")) {
            return "6500 Kelvin";
        }
        if (format.equals("2 8")) {
            return "7500 Kelvin";
        }
        if (format.equals("3 0")) {
            return "One-touch";
        }
        return "Unknown " + format;
    }

    @Nullable
    public String ac() {
        int[] f = ((OlympusMakernoteDirectory) this.f5211a).f(OlympusMakernoteDirectory.al);
        if (f == null) {
            return null;
        }
        double d = (double) ((short) f[0]);
        Double.isNaN(d);
        return String.valueOf(d / 256.0d);
    }

    @Nullable
    public String ad() {
        int[] f = ((OlympusMakernoteDirectory) this.f5211a).f(OlympusMakernoteDirectory.am);
        if (f == null) {
            return null;
        }
        double d = (double) ((short) f[0]);
        Double.isNaN(d);
        return String.valueOf(d / 256.0d);
    }

    @Nullable
    public String ae() {
        return a((int) OlympusMakernoteDirectory.aD, "High", "Normal", "Low");
    }

    @Nullable
    public String af() {
        return a((int) OlympusMakernoteDirectory.aO, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    public String ag() {
        return a(4107, "Auto", "Manual");
    }

    @Nullable
    public String ah() {
        return a(4106, "Normal", "Macro");
    }

    @Nullable
    public String ai() {
        return a(4100, null, null, "On", "Off");
    }

    @Nullable
    public String aj() {
        Rational q = ((OlympusMakernoteDirectory) this.f5211a).q(516);
        if (q == null) {
            return null;
        }
        return q.toSimpleString(false);
    }

    @Nullable
    public String ak() {
        Rational q = ((OlympusMakernoteDirectory) this.f5211a).q(517);
        if (q == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.###");
        return decimalFormat.format(q.doubleValue()) + " mm";
    }

    @Nullable
    public String al() {
        String s = ((OlympusMakernoteDirectory) this.f5211a).s(519);
        if (s == null) {
            return null;
        }
        return OlympusMakernoteDirectory.bi.containsKey(s) ? OlympusMakernoteDirectory.bi.get(s) : s;
    }

    @Nullable
    public String am() {
        byte[] g = ((OlympusMakernoteDirectory) this.f5211a).g(521);
        if (g == null) {
            return null;
        }
        return new String(g);
    }

    @Nullable
    public String an() {
        return a(770, "Off", "On", "On (Preset)");
    }

    @Nullable
    public String ao() {
        return super.i(4096);
    }

    @Nullable
    public String ap() {
        Rational q = ((OlympusMakernoteDirectory) this.f5211a).q(4097);
        if (q == null) {
            return null;
        }
        return String.valueOf(Math.round(Math.pow(2.0d, q.doubleValue() - 5.0d) * 100.0d));
    }

    @Nullable
    public String aq() {
        Double i = ((OlympusMakernoteDirectory) this.f5211a).i(4098);
        if (i == null) {
            return null;
        }
        return a(PhotographicConversions.a(i.doubleValue()));
    }

    @Nullable
    public String ar() {
        return a(514, "Normal (no macro)", "Macro");
    }

    @Nullable
    public String as() {
        return a(515, "Off", "On");
    }

    @Nullable
    public String at() {
        String s = ((OlympusMakernoteDirectory) this.f5211a).s(519);
        if (s != null) {
            Integer c = ((OlympusMakernoteDirectory) this.f5211a).c(513);
            if (c == null) {
                return null;
            }
            if ((!s.startsWith("SX") || s.startsWith("SX151")) && !s.startsWith("D4322")) {
                int intValue = c.intValue();
                if (intValue == 33) {
                    return "Uncompressed";
                }
                switch (intValue) {
                    case 0:
                        return "Standard Quality (Low)";
                    case 1:
                        return "High Quality (Normal)";
                    case 2:
                        return "Super High Quality (Fine)";
                    default:
                        switch (intValue) {
                            case 4:
                                return "RAW";
                            case 5:
                                return "Medium-Fine";
                            case 6:
                                return "Small-Fine";
                            default:
                                return "Unknown (" + c.toString() + Operators.BRACKET_END_STR;
                        }
                }
            } else {
                int intValue2 = c.intValue();
                if (intValue2 == 6) {
                    return "RAW";
                }
                switch (intValue2) {
                    case 0:
                        return "Standard Quality (Low)";
                    case 1:
                        return "High Quality (Normal)";
                    case 2:
                        return "Super High Quality (Fine)";
                    default:
                        return "Unknown (" + c.toString() + Operators.BRACKET_END_STR;
                }
            }
        } else {
            return a(513, 1, "Standard Quality", "High Quality", "Super High Quality");
        }
    }

    @Nullable
    public String au() {
        long[] jArr = (long[]) ((OlympusMakernoteDirectory) this.f5211a).u(512);
        if (jArr == null) {
            return null;
        }
        if (jArr.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        switch ((int) jArr[0]) {
            case 0:
                sb.append("Normal picture taking mode");
                break;
            case 1:
                sb.append("Unknown picture taking mode");
                break;
            case 2:
                sb.append("Fast picture taking mode");
                break;
            case 3:
                sb.append("Panorama picture taking mode");
                break;
            default:
                sb.append("Unknown picture taking mode");
                break;
        }
        if (jArr.length >= 2) {
            switch ((int) jArr[1]) {
                case 0:
                    break;
                case 1:
                    sb.append(" / 1st in a sequence");
                    break;
                case 2:
                    sb.append(" / 2nd in a sequence");
                    break;
                case 3:
                    sb.append(" / 3rd in a sequence");
                    break;
                default:
                    sb.append(" / ");
                    sb.append(jArr[1]);
                    sb.append("th in a sequence");
                    break;
            }
        }
        if (jArr.length >= 3) {
            switch ((int) jArr[2]) {
                case 1:
                    sb.append(" / Left to right panorama direction");
                    break;
                case 2:
                    sb.append(" / Right to left panorama direction");
                    break;
                case 3:
                    sb.append(" / Bottom to top panorama direction");
                    break;
                case 4:
                    sb.append(" / Top to bottom panorama direction");
                    break;
            }
        }
        return sb.toString();
    }
}
