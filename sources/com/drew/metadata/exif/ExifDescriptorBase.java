package com.drew.metadata.exif;

import android.support.media.ExifInterface;
import com.drew.imaging.PhotographicConversions;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import miuipub.reflect.Field;

public abstract class ExifDescriptorBase<T extends Directory> extends TagDescriptor<T> {
    private final boolean c = true;

    public ExifDescriptorBase(@NotNull T t) {
        super(t);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 1:
                return b();
            case 2:
                return a();
            default:
                switch (i) {
                    case ExifDirectoryBase.g:
                        return w();
                    case 255:
                        return x();
                    case 256:
                        return u();
                    case 257:
                        return v();
                    case 258:
                        return t();
                    case 259:
                        return aj();
                    default:
                        switch (i) {
                            case 262:
                                return s();
                            case 263:
                                return y();
                            default:
                                switch (i) {
                                    case 277:
                                        return p();
                                    case 278:
                                        return q();
                                    case 279:
                                        return r();
                                    default:
                                        switch (i) {
                                            case 282:
                                                return e();
                                            case 283:
                                                return d();
                                            case 284:
                                                return o();
                                            default:
                                                switch (i) {
                                                    case 530:
                                                        return n();
                                                    case 531:
                                                        return f();
                                                    case 532:
                                                        return c();
                                                    default:
                                                        switch (i) {
                                                            case 37121:
                                                                return aq();
                                                            case ExifDirectoryBase.aK:
                                                                return al();
                                                            default:
                                                                switch (i) {
                                                                    case ExifDirectoryBase.aL:
                                                                        return an();
                                                                    case ExifDirectoryBase.aM:
                                                                        return X();
                                                                    default:
                                                                        switch (i) {
                                                                            case ExifDirectoryBase.aO:
                                                                                return V();
                                                                            case ExifDirectoryBase.aP:
                                                                                return W();
                                                                            case ExifDirectoryBase.aQ:
                                                                                return ak();
                                                                            case ExifDirectoryBase.aR:
                                                                                return ai();
                                                                            case 37384:
                                                                                return ah();
                                                                            case ExifDirectoryBase.aU:
                                                                                return ag();
                                                                            case ExifDirectoryBase.aV:
                                                                                return af();
                                                                            default:
                                                                                switch (i) {
                                                                                    case ExifDirectoryBase.bm:
                                                                                        return l();
                                                                                    case ExifDirectoryBase.bn:
                                                                                        return j();
                                                                                    case ExifDirectoryBase.bo:
                                                                                        return i();
                                                                                    case ExifDirectoryBase.bp:
                                                                                        return k();
                                                                                    case ExifDirectoryBase.bq:
                                                                                        return m();
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 40960:
                                                                                                return Q();
                                                                                            case 40961:
                                                                                                return ae();
                                                                                            case ExifDirectoryBase.bt:
                                                                                                return ac();
                                                                                            case ExifDirectoryBase.bu:
                                                                                                return ad();
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case ExifDirectoryBase.by:
                                                                                                        return Z();
                                                                                                    case ExifDirectoryBase.bz:
                                                                                                        return aa();
                                                                                                    case ExifDirectoryBase.bA:
                                                                                                        return ab();
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case ExifDirectoryBase.bE:
                                                                                                                return U();
                                                                                                            case ExifDirectoryBase.bF:
                                                                                                                return R();
                                                                                                            case ExifDirectoryBase.bG:
                                                                                                                return S();
                                                                                                            default:
                                                                                                                switch (i) {
                                                                                                                    case ExifDirectoryBase.bH:
                                                                                                                        return M();
                                                                                                                    case ExifDirectoryBase.bI:
                                                                                                                        return L();
                                                                                                                    case ExifDirectoryBase.bJ:
                                                                                                                        return K();
                                                                                                                    case ExifDirectoryBase.bK:
                                                                                                                        return J();
                                                                                                                    case ExifDirectoryBase.bL:
                                                                                                                        return I();
                                                                                                                    case ExifDirectoryBase.bM:
                                                                                                                        return H();
                                                                                                                    case ExifDirectoryBase.bN:
                                                                                                                        return G();
                                                                                                                    case ExifDirectoryBase.bO:
                                                                                                                        return F();
                                                                                                                    case ExifDirectoryBase.bP:
                                                                                                                        return E();
                                                                                                                    case ExifDirectoryBase.bQ:
                                                                                                                        return D();
                                                                                                                    default:
                                                                                                                        switch (i) {
                                                                                                                            case 266:
                                                                                                                                return z();
                                                                                                                            case 274:
                                                                                                                                return g();
                                                                                                                            case 296:
                                                                                                                                return h();
                                                                                                                            case 512:
                                                                                                                                return ar();
                                                                                                                            case ExifDirectoryBase.an:
                                                                                                                                return T();
                                                                                                                            case ExifDirectoryBase.aq:
                                                                                                                                return am();
                                                                                                                            case ExifDirectoryBase.ar:
                                                                                                                                return ao();
                                                                                                                            case ExifDirectoryBase.au:
                                                                                                                                return Y();
                                                                                                                            case ExifDirectoryBase.aw:
                                                                                                                                return O();
                                                                                                                            case ExifDirectoryBase.aB:
                                                                                                                                return B();
                                                                                                                            case 36864:
                                                                                                                                return P();
                                                                                                                            case ExifDirectoryBase.bi:
                                                                                                                                return N();
                                                                                                                            case ExifDirectoryBase.bD:
                                                                                                                                return ap();
                                                                                                                            case ExifDirectoryBase.bS:
                                                                                                                                return A();
                                                                                                                            case ExifDirectoryBase.bW:
                                                                                                                                return C();
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
        }
    }

    @Nullable
    public String a() {
        return a(2, 2);
    }

    @Nullable
    public String b() {
        String s = this.f5211a.s(1);
        if (s == null) {
            return null;
        }
        if ("R98".equalsIgnoreCase(s.trim())) {
            return "Recommended Exif Interoperability Rules (ExifR98)";
        }
        return "Unknown (" + s + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String c() {
        int[] f = this.f5211a.f(532);
        if (f == null || f.length < 6) {
            Object u = this.f5211a.u(532);
            if (u == null || !(u instanceof long[])) {
                return null;
            }
            long[] jArr = (long[]) u;
            if (jArr.length < 6) {
                return null;
            }
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            f = iArr;
        }
        int i2 = f[0];
        int i3 = f[1];
        int i4 = f[2];
        int i5 = f[3];
        return String.format("[%d,%d,%d] [%d,%d,%d]", new Object[]{Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(f[4]), Integer.valueOf(i3), Integer.valueOf(i5), Integer.valueOf(f[5])});
    }

    @Nullable
    public String d() {
        Rational q = this.f5211a.q(283);
        if (q == null) {
            return null;
        }
        String h = h();
        Object[] objArr = new Object[2];
        objArr[0] = q.toSimpleString(true);
        objArr[1] = h == null ? "unit" : h.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }

    @Nullable
    public String e() {
        Rational q = this.f5211a.q(282);
        if (q == null) {
            return null;
        }
        String h = h();
        Object[] objArr = new Object[2];
        objArr[0] = q.toSimpleString(true);
        objArr[1] = h == null ? "unit" : h.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }

    @Nullable
    public String f() {
        return a(531, 1, "Center of pixel array", "Datum point");
    }

    @Nullable
    public String g() {
        return super.h(274);
    }

    @Nullable
    public String h() {
        return a(296, 1, "(No unit)", "Inch", "cm");
    }

    @Nullable
    private String j(int i) {
        byte[] g = this.f5211a.g(i);
        if (g == null) {
            return null;
        }
        try {
            return new String(g, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public String i() {
        return j(ExifDirectoryBase.bo);
    }

    @Nullable
    public String j() {
        return j(ExifDirectoryBase.bn);
    }

    @Nullable
    public String k() {
        return j(ExifDirectoryBase.bp);
    }

    @Nullable
    public String l() {
        return j(ExifDirectoryBase.bm);
    }

    @Nullable
    public String m() {
        return j(ExifDirectoryBase.bq);
    }

    @Nullable
    public String n() {
        int[] f = this.f5211a.f(530);
        if (f == null || f.length < 2) {
            return null;
        }
        if (f[0] == 2 && f[1] == 1) {
            return "YCbCr4:2:2";
        }
        return (f[0] == 2 && f[1] == 2) ? "YCbCr4:2:0" : "(Unknown)";
    }

    @Nullable
    public String o() {
        return a(284, 1, "Chunky (contiguous for each subsampling pixel)", "Separate (Y-plane/Cb-plane/Cr-plane format)");
    }

    @Nullable
    public String p() {
        String s = this.f5211a.s(277);
        if (s == null) {
            return null;
        }
        return s + " samples/pixel";
    }

    @Nullable
    public String q() {
        String s = this.f5211a.s(278);
        if (s == null) {
            return null;
        }
        return s + " rows/strip";
    }

    @Nullable
    public String r() {
        String s = this.f5211a.s(279);
        if (s == null) {
            return null;
        }
        return s + " bytes";
    }

    @Nullable
    public String s() {
        Integer c2 = this.f5211a.c(262);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 32803) {
            return "Color Filter Array";
        }
        if (intValue == 32892) {
            return "Linear Raw";
        }
        switch (intValue) {
            case 0:
                return "WhiteIsZero";
            case 1:
                return "BlackIsZero";
            case 2:
                return "RGB";
            case 3:
                return "RGB Palette";
            case 4:
                return "Transparency Mask";
            case 5:
                return "CMYK";
            case 6:
                return "YCbCr";
            default:
                switch (intValue) {
                    case 8:
                        return "CIELab";
                    case 9:
                        return "ICCLab";
                    case 10:
                        return "ITULab";
                    default:
                        switch (intValue) {
                            case 32844:
                                return "Pixar LogL";
                            case 32845:
                                return "Pixar LogLuv";
                            default:
                                return "Unknown colour space";
                        }
                }
        }
    }

    @Nullable
    public String t() {
        String s = this.f5211a.s(258);
        if (s == null) {
            return null;
        }
        return s + " bits/component/pixel";
    }

    @Nullable
    public String u() {
        String s = this.f5211a.s(256);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    @Nullable
    public String v() {
        String s = this.f5211a.s(257);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    @Nullable
    public String w() {
        return a(ExifDirectoryBase.g, 0, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image", "Single page of multi-page reduced-resolution image", "Transparency mask", "Transparency mask of reduced-resolution image", "Transparency mask of multi-page image", "Transparency mask of reduced-resolution multi-page image");
    }

    @Nullable
    public String x() {
        return a(255, 1, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image");
    }

    @Nullable
    public String y() {
        return a(263, 1, "No dithering or halftoning", "Ordered dither or halftone", "Randomized dither");
    }

    @Nullable
    public String z() {
        return a(266, 1, "Normal", "Reversed");
    }

    @Nullable
    public String A() {
        return a((int) ExifDirectoryBase.bS, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN, "Macro", "Close view", "Distant view");
    }

    @Nullable
    public String B() {
        return a((int) ExifDirectoryBase.aB, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN, "Standard Output Sensitivity", "Recommended Exposure Index", "ISO Speed", "Standard Output Sensitivity and Recommended Exposure Index", "Standard Output Sensitivity and ISO Speed", "Recommended Exposure Index and ISO Speed", "Standard Output Sensitivity, Recommended Exposure Index and ISO Speed");
    }

    @Nullable
    public String C() {
        return g(ExifDirectoryBase.bW);
    }

    @Nullable
    public String D() {
        return a((int) ExifDirectoryBase.bQ, "None", "Low", "Hard");
    }

    @Nullable
    public String E() {
        return a((int) ExifDirectoryBase.bP, "None", "Low saturation", "High saturation");
    }

    @Nullable
    public String F() {
        return a((int) ExifDirectoryBase.bO, "None", "Soft", "Hard");
    }

    @Nullable
    public String G() {
        return a((int) ExifDirectoryBase.bN, "None", "Low gain up", "Low gain down", "High gain up", "High gain down");
    }

    @Nullable
    public String H() {
        return a((int) ExifDirectoryBase.bM, "Standard", "Landscape", "Portrait", "Night scene");
    }

    @Nullable
    public String I() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.bL);
        if (c2 == null) {
            return null;
        }
        return c2.intValue() == 0 ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : b((double) c2.intValue());
    }

    @Nullable
    public String J() {
        Rational q = this.f5211a.q(ExifDirectoryBase.bK);
        if (q == null) {
            return null;
        }
        return q.getNumerator() == 0 ? "Digital zoom not used" : new DecimalFormat("0.#").format(q.doubleValue());
    }

    @Nullable
    public String K() {
        return a((int) ExifDirectoryBase.bJ, "Auto white balance", "Manual white balance");
    }

    @Nullable
    public String L() {
        return a((int) ExifDirectoryBase.bI, "Auto exposure", "Manual exposure", "Auto bracket");
    }

    @Nullable
    public String M() {
        return a((int) ExifDirectoryBase.bH, "Normal process", "Custom process");
    }

    @Nullable
    public String N() {
        byte[] g = this.f5211a.g(ExifDirectoryBase.bi);
        if (g == null) {
            return null;
        }
        if (g.length == 0) {
            return "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("ASCII", System.getProperty("file.encoding"));
        hashMap.put("UNICODE", "UTF-16LE");
        hashMap.put("JIS", "Shift-JIS");
        try {
            if (g.length >= 10) {
                String str = new String(g, 0, 10);
                for (Map.Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    String str3 = (String) entry.getValue();
                    if (str.startsWith(str2)) {
                        for (int length = str2.length(); length < 10; length++) {
                            byte b = g[length];
                            if (b != 0 && b != 32) {
                                return new String(g, length, g.length - length, str3).trim();
                            }
                        }
                        return new String(g, 10, g.length - 10, str3).trim();
                    }
                }
            }
            return new String(g, System.getProperty("file.encoding")).trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public String O() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.aw);
        if (c2 != null) {
            return Integer.toString(c2.intValue());
        }
        return null;
    }

    @Nullable
    public String P() {
        return a(36864, 2);
    }

    @Nullable
    public String Q() {
        return a(40960, 2);
    }

    @Nullable
    public String R() {
        return a(ExifDirectoryBase.bF, 1, "Directly photographed image");
    }

    @Nullable
    public String S() {
        return a(k(ExifDirectoryBase.bG));
    }

    @Nullable
    public String T() {
        byte[] g = this.f5211a.g(ExifDirectoryBase.an);
        if (g == null) {
            return null;
        }
        int[] f = this.f5211a.f(ExifDirectoryBase.am);
        if (f == null) {
            return String.format("Repeat Pattern not found for CFAPattern (%s)", new Object[]{super.a((int) ExifDirectoryBase.an)});
        } else if (f.length == 2 && g.length == f[0] * f[1]) {
            int[] iArr = new int[(g.length + 2)];
            iArr[0] = f[0];
            iArr[1] = f[1];
            for (int i = 0; i < g.length; i++) {
                iArr[i + 2] = g[i] & 255;
            }
            return a(iArr);
        } else {
            return String.format("Unknown Pattern (%s)", new Object[]{super.a((int) ExifDirectoryBase.an)});
        }
    }

    @Nullable
    private static String a(@Nullable int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length < 2) {
            return "<truncated data>";
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            return "<zero pattern size>";
        }
        int i = (iArr[0] * iArr[1]) + 2;
        if (i > iArr.length) {
            return "<invalid pattern size>";
        }
        String[] strArr = {"Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "White"};
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        for (int i2 = 2; i2 < i; i2++) {
            if (iArr[i2] <= strArr.length - 1) {
                sb.append(strArr[iArr[i2]]);
            } else {
                sb.append(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            }
            if ((i2 - 2) % iArr[1] == 0) {
                sb.append(",");
            } else if (i2 != i - 1) {
                sb.append("][");
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    @Nullable
    private int[] k(int i) {
        byte[] g = this.f5211a.g(i);
        if (g == 0) {
            return null;
        }
        if (g.length < 4) {
            int[] iArr = new int[g.length];
            for (int i2 = 0; i2 < g.length; i2++) {
                iArr[i2] = g[i2];
            }
            return iArr;
        }
        int[] iArr2 = new int[(g.length - 2)];
        try {
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int g2 = byteArrayReader.g(0);
            int g3 = byteArrayReader.g(2);
            Boolean bool = false;
            if ((g2 * g3) + 2 > g.length) {
                byteArrayReader.a(!byteArrayReader.b());
                g2 = byteArrayReader.g(0);
                g3 = byteArrayReader.g(2);
                if (g.length >= (g2 * g3) + 2) {
                    bool = true;
                }
            } else {
                bool = true;
            }
            if (bool.booleanValue()) {
                iArr2[0] = g2;
                iArr2[1] = g3;
                for (int i3 = 4; i3 < g.length; i3++) {
                    iArr2[i3 - 2] = byteArrayReader.e(i3);
                }
            }
        } catch (IOException e) {
            Directory directory = this.f5211a;
            directory.a("IO exception processing data: " + e.getMessage());
        }
        return iArr2;
    }

    @Nullable
    public String U() {
        return a(ExifDirectoryBase.bE, 1, "Film Scanner", "Reflection Print Scanner", "Digital Still Camera (DSC)");
    }

    @Nullable
    public String V() {
        Rational q = this.f5211a.q(ExifDirectoryBase.aO);
        if (q == null) {
            return null;
        }
        return q.toSimpleString(true) + " EV";
    }

    @Nullable
    public String W() {
        Double i = this.f5211a.i(ExifDirectoryBase.aP);
        if (i == null) {
            return null;
        }
        return a(PhotographicConversions.a(i.doubleValue()));
    }

    @Nullable
    public String X() {
        Double i = this.f5211a.i(ExifDirectoryBase.aM);
        if (i == null) {
            return null;
        }
        return a(PhotographicConversions.a(i.doubleValue()));
    }

    @Nullable
    public String Y() {
        return a(ExifDirectoryBase.au, 1, "Manual control", "Program normal", "Aperture priority", "Shutter priority", "Program creative (slow program)", "Program action (high-speed program)", "Portrait mode", "Landscape mode");
    }

    @Nullable
    public String Z() {
        String str;
        Rational q = this.f5211a.q(ExifDirectoryBase.by);
        if (q == null) {
            return null;
        }
        String ab = ab();
        StringBuilder sb = new StringBuilder();
        sb.append(q.getReciprocal().toSimpleString(true));
        if (ab == null) {
            str = "";
        } else {
            str = " " + ab.toLowerCase();
        }
        sb.append(str);
        return sb.toString();
    }

    @Nullable
    public String aa() {
        String str;
        Rational q = this.f5211a.q(ExifDirectoryBase.bz);
        if (q == null) {
            return null;
        }
        String ab = ab();
        StringBuilder sb = new StringBuilder();
        sb.append(q.getReciprocal().toSimpleString(true));
        if (ab == null) {
            str = "";
        } else {
            str = " " + ab.toLowerCase();
        }
        sb.append(str);
        return sb.toString();
    }

    @Nullable
    public String ab() {
        return a(ExifDirectoryBase.bA, 1, "(No unit)", "Inches", "cm");
    }

    @Nullable
    public String ac() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.bt);
        if (c2 == null) {
            return null;
        }
        return c2 + " pixels";
    }

    @Nullable
    public String ad() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.bu);
        if (c2 == null) {
            return null;
        }
        return c2 + " pixels";
    }

    @Nullable
    public String ae() {
        Integer c2 = this.f5211a.c(40961);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 1) {
            return "sRGB";
        }
        if (c2.intValue() == 65535) {
            return "Undefined";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String af() {
        Rational q = this.f5211a.q(ExifDirectoryBase.aV);
        if (q == null) {
            return null;
        }
        return b(q.doubleValue());
    }

    @Nullable
    public String ag() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.aU);
        if (c2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if ((c2.intValue() & 1) != 0) {
            sb.append("Flash fired");
        } else {
            sb.append("Flash did not fire");
        }
        if ((c2.intValue() & 4) != 0) {
            if ((c2.intValue() & 2) != 0) {
                sb.append(", return detected");
            } else {
                sb.append(", return not detected");
            }
        }
        if ((c2.intValue() & 16) != 0) {
            sb.append(", auto");
        }
        if ((c2.intValue() & 64) != 0) {
            sb.append(", red-eye reduction");
        }
        return sb.toString();
    }

    @Nullable
    public String ah() {
        Integer c2 = this.f5211a.c(37384);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 255) {
            return "(Other)";
        }
        switch (intValue) {
            case 0:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 1:
                return "Daylight";
            case 2:
                return "Florescent";
            case 3:
                return "Tungsten";
            case 4:
                return ExifInterface.TAG_FLASH;
            default:
                switch (intValue) {
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
                        return "Standard light";
                    case 18:
                        return "Standard light (B)";
                    case 19:
                        return "Standard light (C)";
                    case 20:
                        return "D55";
                    case 21:
                        return "D65";
                    case 22:
                        return "D75";
                    case 23:
                        return "D50";
                    case 24:
                        return "Studio Tungsten";
                    default:
                        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String ai() {
        Integer c2 = this.f5211a.c(ExifDirectoryBase.aR);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 255) {
            return "(Other)";
        }
        switch (intValue) {
            case 0:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 1:
                return "Average";
            case 2:
                return "Center weighted average";
            case 3:
                return "Spot";
            case 4:
                return "Multi-spot";
            case 5:
                return "Multi-segment";
            case 6:
                return "Partial";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String aj() {
        Integer c2 = this.f5211a.c(259);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        switch (intValue) {
            case 1:
                return "Uncompressed";
            case 2:
                return "CCITT 1D";
            case 3:
                return "T4/Group 3 Fax";
            case 4:
                return "T6/Group 4 Fax";
            case 5:
                return "LZW";
            case 6:
                return "JPEG (old-style)";
            case 7:
                return "JPEG";
            case 8:
                return "Adobe Deflate";
            case 9:
                return "JBIG B&W";
            case 10:
                return "JBIG Color";
            default:
                switch (intValue) {
                    case 32766:
                        return "Next";
                    case CLASS_MASK:
                        return "Sony ARW Compressed";
                    default:
                        switch (intValue) {
                            case PanasonicMakernoteDirectory.aP /*32769*/:
                                return "Packed RAW";
                            case FujifilmMakernoteDirectory.T /*32770*/:
                                return "Samsung SRW Compressed";
                            case FujifilmMakernoteDirectory.U /*32771*/:
                                return "CCIRLEW";
                            case PanasonicMakernoteDirectory.aQ /*32772*/:
                                return "Samsung SRW Compressed 2";
                            case 32773:
                                return "PackBits";
                            default:
                                switch (intValue) {
                                    case 32895:
                                        return "IT8CTPAD";
                                    case 32896:
                                        return "IT8LW";
                                    case 32897:
                                        return "IT8MP";
                                    case 32898:
                                        return "IT8BL";
                                    default:
                                        switch (intValue) {
                                            case 32908:
                                                return "PixarFilm";
                                            case 32909:
                                                return "PixarLog";
                                            default:
                                                switch (intValue) {
                                                    case 32946:
                                                        return "Deflate";
                                                    case 32947:
                                                        return "DCS";
                                                    default:
                                                        switch (intValue) {
                                                            case 34676:
                                                                return "SGILog";
                                                            case 34677:
                                                                return "SGILog24";
                                                            default:
                                                                switch (intValue) {
                                                                    case 34712:
                                                                        return "JPEG 2000";
                                                                    case 34713:
                                                                        return "Nikon NEF Compressed";
                                                                    default:
                                                                        switch (intValue) {
                                                                            case 34718:
                                                                                return "Microsoft Document Imaging (MDI) Binary Level Codec";
                                                                            case 34719:
                                                                                return "Microsoft Document Imaging (MDI) Progressive Transform Codec";
                                                                            case 34720:
                                                                                return "Microsoft Document Imaging (MDI) Vector";
                                                                            default:
                                                                                switch (intValue) {
                                                                                    case 99:
                                                                                        return "JPEG";
                                                                                    case 262:
                                                                                        return "Kodak 262";
                                                                                    case 32809:
                                                                                        return "Thunderscan";
                                                                                    case 32867:
                                                                                        return "Kodak KDC Compressed";
                                                                                    case 34661:
                                                                                        return "JBIG";
                                                                                    case 34715:
                                                                                        return "JBIG2 TIFF FX";
                                                                                    case ExifInterface.DATA_LOSSY_JPEG:
                                                                                        return "Lossy JPEG";
                                                                                    case 65000:
                                                                                        return "Kodak DCR Compressed";
                                                                                    case 65535:
                                                                                        return "Pentax PEF Compressed";
                                                                                    default:
                                                                                        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
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
    public String ak() {
        Rational q = this.f5211a.q(ExifDirectoryBase.aQ);
        if (q == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.0##");
        return decimalFormat.format(q.doubleValue()) + " metres";
    }

    @Nullable
    public String al() {
        String str;
        StringBuilder sb;
        Rational q = this.f5211a.q(ExifDirectoryBase.aK);
        if (q == null) {
            return null;
        }
        String simpleString = q.toSimpleString(true);
        if (!q.isInteger() || q.intValue() != 1) {
            sb = new StringBuilder();
            sb.append(simpleString);
            str = " bits/pixel";
        } else {
            sb = new StringBuilder();
            sb.append(simpleString);
            str = " bit/pixel";
        }
        sb.append(str);
        return sb.toString();
    }

    @Nullable
    public String am() {
        String s = this.f5211a.s(ExifDirectoryBase.aq);
        if (s == null) {
            return null;
        }
        return s + " sec";
    }

    @Nullable
    public String an() {
        return super.i(ExifDirectoryBase.aL);
    }

    @Nullable
    public String ao() {
        Rational q = this.f5211a.q(ExifDirectoryBase.ar);
        if (q == null) {
            return null;
        }
        return a(q.doubleValue());
    }

    @Nullable
    public String ap() {
        return a(ExifDirectoryBase.bD, 1, "(Not defined)", "One-chip color area sensor", "Two-chip color area sensor", "Three-chip color area sensor", "Color sequential area sensor", null, "Trilinear sensor", "Color sequential linear sensor");
    }

    @Nullable
    public String aq() {
        int[] f = this.f5211a.f(37121);
        if (f == null) {
            return null;
        }
        String[] strArr = {"", "Y", "Cb", "Cr", "R", "G", Field.b};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(4, f.length); i++) {
            int i2 = f[i];
            if (i2 > 0 && i2 < strArr.length) {
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    @Nullable
    public String ar() {
        Integer c2 = this.f5211a.c(512);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 1) {
            return "Baseline";
        }
        if (intValue == 14) {
            return "Lossless";
        }
        return "Unknown (" + c2 + Operators.BRACKET_END_STR;
    }
}
