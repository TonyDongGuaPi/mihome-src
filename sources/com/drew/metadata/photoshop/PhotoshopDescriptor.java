package com.drew.metadata.photoshop;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class PhotoshopDescriptor extends TagDescriptor<PhotoshopDirectory> {
    public PhotoshopDescriptor(@NotNull PhotoshopDirectory photoshopDirectory) {
        super(photoshopDirectory);
    }

    public String a(int i) {
        switch (i) {
            case 1002:
            case 1035:
                return o(i);
            case 1005:
                return d();
            case 1028:
                return p(i);
            case 1030:
                return a();
            case PhotoshopDirectory.F /*1033*/:
            case PhotoshopDirectory.I /*1036*/:
                return j(i);
            case PhotoshopDirectory.G /*1034*/:
                return m(i);
            case 1037:
            case PhotoshopDirectory.P /*1044*/:
            case PhotoshopDirectory.T /*1049*/:
            case PhotoshopDirectory.Y /*1054*/:
                return n(i);
            case PhotoshopDirectory.U /*1050*/:
                return f();
            case PhotoshopDirectory.Z /*1057*/:
                return e();
            case PhotoshopDirectory.ae /*1062*/:
                return c();
            case PhotoshopDirectory.af /*1064*/:
                return b();
            case PhotoshopDirectory.aB /*2999*/:
                return k(i);
            default:
                if (i < 2000 || i > 2998) {
                    return super.a(i);
                }
                return l(i);
        }
    }

    @Nullable
    public String a() {
        String str;
        String str2;
        String str3;
        Object[] objArr;
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(1030);
            if (g == null) {
                return ((PhotoshopDirectory) this.f5211a).s(1030);
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int f = byteArrayReader.f(0);
            int f2 = byteArrayReader.f(2);
            int f3 = byteArrayReader.f(4);
            int i = (f > 65535 || f < 65533) ? f <= 8 ? f + 4 : f : f - 65532;
            switch (f) {
                case 0:
                    str = "Low";
                    break;
                case 1:
                case 2:
                case 3:
                    str = "Medium";
                    break;
                case 4:
                case 5:
                    str = "High";
                    break;
                case 6:
                case 7:
                case 8:
                    str = "Maximum";
                    break;
                default:
                    switch (f) {
                        case 65533:
                        case 65534:
                        case 65535:
                            break;
                        default:
                            str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                            break;
                    }
                    str = "Low";
                    break;
            }
            if (f2 != 257) {
                switch (f2) {
                    case 0:
                        str2 = "Standard";
                        break;
                    case 1:
                        str2 = "Optimised";
                        break;
                    default:
                        str2 = String.format("Unknown 0x%04X", new Object[]{Integer.valueOf(f2)});
                        break;
                }
            } else {
                str2 = "Progressive";
            }
            if (f3 < 1 || f3 > 3) {
                str3 = "Unknown 0x%04X";
                objArr = new Object[]{Integer.valueOf(f3)};
            } else {
                str3 = "%d";
                objArr = new Object[]{Integer.valueOf(f3 + 2)};
            }
            return String.format("%d (%s), %s format, %s scans", new Object[]{Integer.valueOf(i), str, str2, String.format(str3, objArr)});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String b() {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(PhotoshopDirectory.af);
            if (g == null) {
                return null;
            }
            return Double.toString(new ByteArrayReader(g).n(4));
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String c() {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(PhotoshopDirectory.ae);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int j = byteArrayReader.j(0);
            float m = byteArrayReader.m(2);
            float m2 = byteArrayReader.m(6);
            float m3 = byteArrayReader.m(10);
            switch (j) {
                case 0:
                    return "Centered, Scale " + m3;
                case 1:
                    return "Size to fit";
                case 2:
                    return String.format("User defined, X:%s Y:%s, Scale:%s", new Object[]{Float.valueOf(m), Float.valueOf(m2), Float.valueOf(m3)});
                default:
                    return String.format("Unknown %04X, X:%s Y:%s, Scale:%s", new Object[]{Integer.valueOf(j), Float.valueOf(m), Float.valueOf(m2), Float.valueOf(m3)});
            }
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String d() {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(1005);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            float l = byteArrayReader.l(0);
            float l2 = byteArrayReader.l(8);
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            return decimalFormat.format((double) l) + "x" + decimalFormat.format((double) l2) + " DPI";
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String e() {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(PhotoshopDirectory.Z);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int j = byteArrayReader.j(0);
            int j2 = byteArrayReader.j(5) * 2;
            String a2 = byteArrayReader.a(9, j2, "UTF-16");
            int i = 9 + j2;
            int j3 = byteArrayReader.j(i);
            int i2 = i + 4;
            int i3 = j3 * 2;
            return String.format("%d (%s, %s) %d", new Object[]{Integer.valueOf(j), a2, byteArrayReader.a(i2, i3, "UTF-16"), Integer.valueOf(byteArrayReader.j(i2 + i3))});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String f() {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(PhotoshopDirectory.U);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int j = byteArrayReader.j(20) * 2;
            return String.format("%s (%d,%d,%d,%d) %d Slices", new Object[]{byteArrayReader.a(24, j, "UTF-16"), Integer.valueOf(byteArrayReader.j(4)), Integer.valueOf(byteArrayReader.j(8)), Integer.valueOf(byteArrayReader.j(12)), Integer.valueOf(byteArrayReader.j(16)), Integer.valueOf(byteArrayReader.j(j + 24))});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String j(int i) {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            int j = byteArrayReader.j(0);
            int j2 = byteArrayReader.j(4);
            int j3 = byteArrayReader.j(8);
            int j4 = byteArrayReader.j(16);
            int j5 = byteArrayReader.j(20);
            int j6 = byteArrayReader.j(24);
            Object[] objArr = new Object[6];
            objArr[0] = j == 1 ? "JpegRGB" : "RawRGB";
            objArr[1] = Integer.valueOf(j2);
            objArr[2] = Integer.valueOf(j3);
            objArr[3] = Integer.valueOf(j4);
            objArr[4] = Integer.valueOf(j6);
            objArr[5] = Integer.valueOf(j5);
            return String.format("%s, %dx%d, Decomp %d bytes, %d bpp, %d bytes", objArr);
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    private String m(int i) {
        byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
        if (g == null || g.length == 0) {
            return null;
        }
        return g[0] == 0 ? IntentIntegrator.e : IntentIntegrator.d;
    }

    @Nullable
    private String n(int i) {
        byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
        if (g == null) {
            return null;
        }
        try {
            return String.format("%d", new Object[]{Integer.valueOf(new ByteArrayReader(g).j(0))});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    private String o(int i) {
        byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
        if (g == null) {
            return null;
        }
        return new String(g);
    }

    @Nullable
    private String p(int i) {
        byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
        if (g == null) {
            return null;
        }
        return String.format("%d bytes binary data", new Object[]{Integer.valueOf(g.length)});
    }

    @Nullable
    public String k(int i) {
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            return new String(byteArrayReader.c(1, byteArrayReader.b(0)), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String l(int i) {
        int i2;
        ArrayList arrayList;
        Subpath subpath;
        ArrayList arrayList2;
        Knot knot;
        Knot knot2;
        try {
            byte[] g = ((PhotoshopDirectory) this.f5211a).g(i);
            if (g == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(g);
            short s = 1;
            int a2 = ((int) ((byteArrayReader.a() - ((long) byteArrayReader.b(((int) byteArrayReader.a()) - 1))) - 1)) / 26;
            Subpath subpath2 = new Subpath();
            Subpath subpath3 = new Subpath();
            ArrayList arrayList3 = new ArrayList();
            String str = null;
            Subpath subpath4 = subpath3;
            Subpath subpath5 = subpath2;
            int i3 = 0;
            while (i3 < a2) {
                int i4 = i3 * 26;
                short g2 = byteArrayReader.g(i4);
                switch (g2) {
                    case 0:
                        i2 = i3;
                        Subpath subpath6 = subpath5;
                        arrayList2 = arrayList3;
                        if (subpath6.a() != 0) {
                            arrayList2.add(subpath6);
                        }
                        subpath = new Subpath("Closed Subpath");
                        break;
                    case 1:
                    case 2:
                        i2 = i3;
                        Subpath subpath7 = subpath5;
                        arrayList2 = arrayList3;
                        if (g2 == 1) {
                            knot = new Knot("Linked");
                        } else {
                            knot = new Knot("Unlinked");
                        }
                        int i5 = 0;
                        while (i5 < 6) {
                            int i6 = i5 * 4;
                            double e = (double) byteArrayReader.e(i6 + 2 + i4);
                            double h = (double) byteArrayReader.h(i6 + 3 + i4);
                            int i7 = i4;
                            double pow = Math.pow(2.0d, 24.0d);
                            Double.isNaN(h);
                            Double.isNaN(e);
                            knot.a(i5, e + (h / pow));
                            i5++;
                            i4 = i7;
                        }
                        subpath = subpath7;
                        subpath.a(knot);
                        break;
                    case 3:
                        i2 = i3;
                        Subpath subpath8 = subpath5;
                        ArrayList arrayList4 = arrayList3;
                        if (subpath4.a() != 0) {
                            arrayList = arrayList4;
                            arrayList.add(subpath4);
                        } else {
                            arrayList = arrayList4;
                        }
                        subpath4 = new Subpath("Open Subpath");
                        subpath5 = subpath8;
                        continue;
                    case 4:
                    case 5:
                        if (g2 == 4) {
                            knot2 = new Knot("Linked");
                        } else {
                            knot2 = new Knot("Unlinked");
                        }
                        int i8 = 0;
                        for (int i9 = 6; i8 < i9; i9 = 6) {
                            int i10 = i8 * 4;
                            ArrayList arrayList5 = arrayList3;
                            double e2 = (double) byteArrayReader.e(i10 + 2 + i4);
                            int i11 = i3;
                            Subpath subpath9 = subpath5;
                            double h2 = (double) byteArrayReader.h(i10 + 3 + i4);
                            double pow2 = Math.pow(2.0d, 24.0d);
                            Double.isNaN(h2);
                            Double.isNaN(e2);
                            knot2.a(i8, e2 + (h2 / pow2));
                            i8++;
                            arrayList3 = arrayList5;
                            i3 = i11;
                            subpath5 = subpath9;
                        }
                        i2 = i3;
                        subpath4.a(knot2);
                        arrayList2 = arrayList3;
                        subpath = subpath5;
                        break;
                    case 8:
                        try {
                            str = byteArrayReader.g(i4 + 2) == s ? "with all pixels" : "without all pixels";
                            i2 = i3;
                            arrayList = arrayList3;
                            continue;
                        } catch (Exception unused) {
                            return null;
                        }
                    default:
                        i2 = i3;
                        subpath = subpath5;
                        arrayList2 = arrayList3;
                        break;
                }
                subpath5 = subpath;
                arrayList3 = arrayList;
                s = 1;
                i3 = i2 + 1;
            }
            Subpath subpath10 = subpath5;
            ArrayList arrayList6 = arrayList3;
            if (subpath10.a() != 0) {
                arrayList6.add(subpath10);
            }
            if (subpath4.a() != 0) {
                arrayList6.add(subpath4);
            }
            byte b = byteArrayReader.b(((int) byteArrayReader.a()) - 1);
            String b2 = byteArrayReader.b((((int) byteArrayReader.a()) - b) - 1, b, Charsets.d);
            StringBuilder sb = new StringBuilder();
            sb.append('\"');
            sb.append(b2);
            sb.append('\"');
            sb.append(" having ");
            if (str != null) {
                sb.append("initial fill rule \"");
                sb.append(str);
                sb.append("\" and ");
            }
            sb.append(arrayList6.size());
            sb.append(arrayList6.size() == 1 ? " subpath:" : " subpaths:");
            Iterator it = arrayList6.iterator();
            while (it.hasNext()) {
                Subpath subpath11 = (Subpath) it.next();
                sb.append("\n- ");
                sb.append(subpath11.c());
                sb.append(" with ");
                sb.append(arrayList6.size());
                sb.append(arrayList6.size() == 1 ? " knot:" : " knots:");
                for (Knot next : subpath11.b()) {
                    sb.append("\n  - ");
                    sb.append(next.a());
                    sb.append(" (");
                    sb.append(next.a(0));
                    sb.append(",");
                    sb.append(next.a(1));
                    sb.append(Operators.BRACKET_END_STR);
                    sb.append(" (");
                    sb.append(next.a(2));
                    sb.append(",");
                    sb.append(next.a(3));
                    sb.append(Operators.BRACKET_END_STR);
                    sb.append(" (");
                    sb.append(next.a(4));
                    sb.append(",");
                    sb.append(next.a(5));
                    sb.append(Operators.BRACKET_END_STR);
                }
            }
            return sb.toString();
        } catch (Exception unused2) {
            return null;
        }
    }
}
