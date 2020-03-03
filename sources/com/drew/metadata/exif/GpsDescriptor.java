package com.drew.metadata.exif;

import android.support.media.ExifInterface;
import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import java.text.DecimalFormat;

public class GpsDescriptor extends TagDescriptor<GpsDirectory> {
    public GpsDescriptor(@NotNull GpsDirectory gpsDirectory) {
        super(gpsDirectory);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        return j(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        return k(r2);
     */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(int r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x005a
            r0 = 2
            if (r2 == r0) goto L_0x0055
            r0 = 12
            if (r2 == r0) goto L_0x0050
            r0 = 30
            if (r2 == r0) goto L_0x004b
            switch(r2) {
                case 4: goto L_0x0046;
                case 5: goto L_0x0041;
                case 6: goto L_0x003c;
                case 7: goto L_0x0037;
                default: goto L_0x0010;
            }
        L_0x0010:
            switch(r2) {
                case 9: goto L_0x0032;
                case 10: goto L_0x002d;
                default: goto L_0x0013;
            }
        L_0x0013:
            switch(r2) {
                case 14: goto L_0x0028;
                case 15: goto L_0x0023;
                case 16: goto L_0x0028;
                case 17: goto L_0x0023;
                default: goto L_0x0016;
            }
        L_0x0016:
            switch(r2) {
                case 23: goto L_0x0028;
                case 24: goto L_0x0023;
                case 25: goto L_0x001e;
                default: goto L_0x0019;
            }
        L_0x0019:
            java.lang.String r2 = super.a((int) r2)
            return r2
        L_0x001e:
            java.lang.String r2 = r1.d()
            return r2
        L_0x0023:
            java.lang.String r2 = r1.j(r2)
            return r2
        L_0x0028:
            java.lang.String r2 = r1.k(r2)
            return r2
        L_0x002d:
            java.lang.String r2 = r1.f()
            return r2
        L_0x0032:
            java.lang.String r2 = r1.g()
            return r2
        L_0x0037:
            java.lang.String r2 = r1.c()
            return r2
        L_0x003c:
            java.lang.String r2 = r1.i()
            return r2
        L_0x0041:
            java.lang.String r2 = r1.h()
            return r2
        L_0x0046:
            java.lang.String r2 = r1.b()
            return r2
        L_0x004b:
            java.lang.String r2 = r1.j()
            return r2
        L_0x0050:
            java.lang.String r2 = r1.e()
            return r2
        L_0x0055:
            java.lang.String r2 = r1.a()
            return r2
        L_0x005a:
            java.lang.String r2 = r1.l()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.GpsDescriptor.a(int):java.lang.String");
    }

    @Nullable
    private String l() {
        return a(0, 1);
    }

    @Nullable
    public String a() {
        GeoLocation j = ((GpsDirectory) this.f5211a).j();
        if (j == null) {
            return null;
        }
        return GeoLocation.a(j.a());
    }

    @Nullable
    public String b() {
        GeoLocation j = ((GpsDirectory) this.f5211a).j();
        if (j == null) {
            return null;
        }
        return GeoLocation.a(j.b());
    }

    @Nullable
    public String c() {
        Rational[] r = ((GpsDirectory) this.f5211a).r(7);
        DecimalFormat decimalFormat = new DecimalFormat("00.000");
        if (r == null) {
            return null;
        }
        return String.format("%02d:%02d:%s UTC", new Object[]{Integer.valueOf(r[0].intValue()), Integer.valueOf(r[1].intValue()), decimalFormat.format(r[2].doubleValue())});
    }

    @Nullable
    public String d() {
        String s = ((GpsDirectory) this.f5211a).s(25);
        if (s == null) {
            return null;
        }
        String trim = s.trim();
        if ("K".equalsIgnoreCase(trim)) {
            return "kilometers";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "miles";
        }
        if ("N".equalsIgnoreCase(trim)) {
            return "knots";
        }
        return "Unknown (" + trim + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String j(int i) {
        Rational q = ((GpsDirectory) this.f5211a).q(i);
        String format = q != null ? new DecimalFormat("0.##").format(q.doubleValue()) : ((GpsDirectory) this.f5211a).s(i);
        if (format == null || format.trim().length() == 0) {
            return null;
        }
        return format.trim() + " degrees";
    }

    @Nullable
    public String k(int i) {
        String s = ((GpsDirectory) this.f5211a).s(i);
        if (s == null) {
            return null;
        }
        String trim = s.trim();
        if (ExifInterface.GPS_DIRECTION_TRUE.equalsIgnoreCase(trim)) {
            return "True direction";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "Magnetic direction";
        }
        return "Unknown (" + trim + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String e() {
        String s = ((GpsDirectory) this.f5211a).s(12);
        if (s == null) {
            return null;
        }
        String trim = s.trim();
        if ("K".equalsIgnoreCase(trim)) {
            return "kph";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "mph";
        }
        if ("N".equalsIgnoreCase(trim)) {
            return "knots";
        }
        return "Unknown (" + trim + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String f() {
        String s = ((GpsDirectory) this.f5211a).s(10);
        if (s == null) {
            return null;
        }
        String trim = s.trim();
        if ("2".equalsIgnoreCase(trim)) {
            return "2-dimensional measurement";
        }
        if ("3".equalsIgnoreCase(trim)) {
            return "3-dimensional measurement";
        }
        return "Unknown (" + trim + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String g() {
        String s = ((GpsDirectory) this.f5211a).s(9);
        if (s == null) {
            return null;
        }
        String trim = s.trim();
        if ("A".equalsIgnoreCase(trim)) {
            return "Active (Measurement in progress)";
        }
        if ("V".equalsIgnoreCase(trim)) {
            return "Void (Measurement Interoperability)";
        }
        return "Unknown (" + trim + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        return a(5, "Sea level", "Below sea level");
    }

    @Nullable
    public String i() {
        Rational q = ((GpsDirectory) this.f5211a).q(6);
        if (q == null) {
            return null;
        }
        return q.intValue() + " metres";
    }

    @Nullable
    public String j() {
        return a(30, "No Correction", "Differential Corrected");
    }

    @Nullable
    public String k() {
        GeoLocation j = ((GpsDirectory) this.f5211a).j();
        if (j == null) {
            return null;
        }
        return j.d();
    }
}
