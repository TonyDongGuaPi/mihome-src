package com.drew.metadata.icc;

import android.support.media.ExifInterface;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;

public class IccDescriptor extends TagDescriptor<IccDirectory> {
    private static final int c = 1952807028;
    private static final int d = 1684370275;
    private static final int e = 1936287520;
    private static final int f = 1835360627;
    private static final int g = 1482250784;
    private static final int h = 1835824483;
    private static final int i = 1668641398;

    public IccDescriptor(@NotNull IccDirectory iccDirectory) {
        super(iccDirectory);
    }

    public String a(int i2) {
        if (i2 == 8) {
            return d();
        }
        if (i2 == 12) {
            return c();
        }
        if (i2 == 40) {
            return b();
        }
        if (i2 == 64) {
            return a();
        }
        if (i2 <= 538976288 || i2 >= 2054847098) {
            return super.a(i2);
        }
        return j(i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        return new java.lang.String(r0, 8, (r0.length - 8) - 1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0039 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String j(int r15) {
        /*
            r14 = this;
            com.drew.metadata.Directory r0 = r14.f5211a     // Catch:{ IOException -> 0x0216 }
            com.drew.metadata.icc.IccDirectory r0 = (com.drew.metadata.icc.IccDirectory) r0     // Catch:{ IOException -> 0x0216 }
            byte[] r0 = r0.g(r15)     // Catch:{ IOException -> 0x0216 }
            if (r0 != 0) goto L_0x0013
            com.drew.metadata.Directory r0 = r14.f5211a     // Catch:{ IOException -> 0x0216 }
            com.drew.metadata.icc.IccDirectory r0 = (com.drew.metadata.icc.IccDirectory) r0     // Catch:{ IOException -> 0x0216 }
            java.lang.String r15 = r0.s(r15)     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x0013:
            com.drew.lang.ByteArrayReader r15 = new com.drew.lang.ByteArrayReader     // Catch:{ IOException -> 0x0216 }
            r15.<init>(r0)     // Catch:{ IOException -> 0x0216 }
            r1 = 0
            int r2 = r15.j(r1)     // Catch:{ IOException -> 0x0216 }
            r3 = 7
            r4 = 16
            r5 = 3
            r6 = 2
            r7 = 12
            r8 = 1
            r9 = 8
            switch(r2) {
                case 1482250784: goto L_0x019d;
                case 1668641398: goto L_0x0169;
                case 1684370275: goto L_0x015e;
                case 1835360627: goto L_0x009a;
                case 1835824483: goto L_0x004b;
                case 1936287520: goto L_0x0042;
                case 1952807028: goto L_0x002e;
                default: goto L_0x002a;
            }     // Catch:{ IOException -> 0x0216 }
        L_0x002a:
            java.lang.String r15 = "%s (0x%08X): %d bytes"
            goto L_0x01fc
        L_0x002e:
            java.lang.String r15 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            int r1 = r0.length     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            int r1 = r1 - r9
            int r1 = r1 - r8
            java.lang.String r2 = "ASCII"
            r15.<init>(r0, r9, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            return r15
        L_0x0039:
            java.lang.String r15 = new java.lang.String     // Catch:{ IOException -> 0x0216 }
            int r1 = r0.length     // Catch:{ IOException -> 0x0216 }
            int r1 = r1 - r9
            int r1 = r1 - r8
            r15.<init>(r0, r9, r1)     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x0042:
            int r15 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r15 = com.drew.metadata.icc.IccReader.a(r15)     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x004b:
            int r2 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0216 }
            r3.<init>()     // Catch:{ IOException -> 0x0216 }
            r3.append(r2)     // Catch:{ IOException -> 0x0216 }
        L_0x0057:
            if (r1 >= r2) goto L_0x0095
            int r5 = r1 * 12
            int r5 = r5 + r4
            int r6 = r15.j(r5)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r6 = com.drew.metadata.icc.IccReader.a(r6)     // Catch:{ IOException -> 0x0216 }
            int r7 = r5 + 4
            int r7 = r15.j(r7)     // Catch:{ IOException -> 0x0216 }
            int r5 = r5 + 8
            int r5 = r15.j(r5)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r8 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0078 }
            java.lang.String r9 = "UTF-16BE"
            r8.<init>(r0, r5, r7, r9)     // Catch:{ UnsupportedEncodingException -> 0x0078 }
            goto L_0x007d
        L_0x0078:
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x0216 }
            r8.<init>(r0, r5, r7)     // Catch:{ IOException -> 0x0216 }
        L_0x007d:
            java.lang.String r5 = " "
            r3.append(r5)     // Catch:{ IOException -> 0x0216 }
            r3.append(r6)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = "("
            r3.append(r5)     // Catch:{ IOException -> 0x0216 }
            r3.append(r8)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = ")"
            r3.append(r5)     // Catch:{ IOException -> 0x0216 }
            int r1 = r1 + 1
            goto L_0x0057
        L_0x0095:
            java.lang.String r15 = r3.toString()     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x009a:
            int r0 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            float r2 = r15.l(r7)     // Catch:{ IOException -> 0x0216 }
            float r4 = r15.l(r4)     // Catch:{ IOException -> 0x0216 }
            r7 = 20
            float r7 = r15.l(r7)     // Catch:{ IOException -> 0x0216 }
            r9 = 24
            int r9 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            r10 = 28
            float r10 = r15.l(r10)     // Catch:{ IOException -> 0x0216 }
            r11 = 32
            int r15 = r15.j(r11)     // Catch:{ IOException -> 0x0216 }
            switch(r0) {
                case 0: goto L_0x00ca;
                case 1: goto L_0x00c7;
                case 2: goto L_0x00c4;
                default: goto L_0x00c1;
            }     // Catch:{ IOException -> 0x0216 }
        L_0x00c1:
            java.lang.String r11 = "Unknown %d"
            goto L_0x00cd
        L_0x00c4:
            java.lang.String r11 = "1964 10°"
            goto L_0x00d9
        L_0x00c7:
            java.lang.String r11 = "1931 2°"
            goto L_0x00d9
        L_0x00ca:
            java.lang.String r11 = "Unknown"
            goto L_0x00d9
        L_0x00cd:
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0216 }
            r12[r1] = r13     // Catch:{ IOException -> 0x0216 }
            java.lang.String r11 = java.lang.String.format(r11, r12)     // Catch:{ IOException -> 0x0216 }
        L_0x00d9:
            switch(r9) {
                case 0: goto L_0x00e5;
                case 1: goto L_0x00e2;
                case 2: goto L_0x00df;
                default: goto L_0x00dc;
            }     // Catch:{ IOException -> 0x0216 }
        L_0x00dc:
            java.lang.String r9 = "Unknown %d"
            goto L_0x00e8
        L_0x00df:
            java.lang.String r0 = "0/d or d/0"
            goto L_0x00f4
        L_0x00e2:
            java.lang.String r0 = "0/45 or 45/0"
            goto L_0x00f4
        L_0x00e5:
            java.lang.String r0 = "Unknown"
            goto L_0x00f4
        L_0x00e8:
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0216 }
            r12[r1] = r0     // Catch:{ IOException -> 0x0216 }
            java.lang.String r0 = java.lang.String.format(r9, r12)     // Catch:{ IOException -> 0x0216 }
        L_0x00f4:
            switch(r15) {
                case 0: goto L_0x0112;
                case 1: goto L_0x010f;
                case 2: goto L_0x010c;
                case 3: goto L_0x0109;
                case 4: goto L_0x0106;
                case 5: goto L_0x0103;
                case 6: goto L_0x0100;
                case 7: goto L_0x00fd;
                case 8: goto L_0x00fa;
                default: goto L_0x00f7;
            }     // Catch:{ IOException -> 0x0216 }
        L_0x00f7:
            java.lang.String r9 = "Unknown %d"
            goto L_0x0116
        L_0x00fa:
            java.lang.String r15 = "F8"
            goto L_0x0122
        L_0x00fd:
            java.lang.String r15 = "Equi-Power (E)"
            goto L_0x0122
        L_0x0100:
            java.lang.String r15 = "A"
            goto L_0x0122
        L_0x0103:
            java.lang.String r15 = "D55"
            goto L_0x0122
        L_0x0106:
            java.lang.String r15 = "F2"
            goto L_0x0122
        L_0x0109:
            java.lang.String r15 = "D93"
            goto L_0x0122
        L_0x010c:
            java.lang.String r15 = "D65"
            goto L_0x0122
        L_0x010f:
            java.lang.String r15 = "D50"
            goto L_0x0122
        L_0x0112:
            java.lang.String r15 = "unknown"
            goto L_0x0122
        L_0x0116:
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ IOException -> 0x0216 }
            r12[r1] = r15     // Catch:{ IOException -> 0x0216 }
            java.lang.String r15 = java.lang.String.format(r9, r12)     // Catch:{ IOException -> 0x0216 }
        L_0x0122:
            java.text.DecimalFormat r9 = new java.text.DecimalFormat     // Catch:{ IOException -> 0x0216 }
            java.lang.String r12 = "0.###"
            r9.<init>(r12)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r12 = "%s Observer, Backing (%s, %s, %s), Geometry %s, Flare %d%%, Illuminant %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0216 }
            r3[r1] = r11     // Catch:{ IOException -> 0x0216 }
            double r1 = (double) r2     // Catch:{ IOException -> 0x0216 }
            java.lang.String r1 = r9.format(r1)     // Catch:{ IOException -> 0x0216 }
            r3[r8] = r1     // Catch:{ IOException -> 0x0216 }
            double r1 = (double) r4     // Catch:{ IOException -> 0x0216 }
            java.lang.String r1 = r9.format(r1)     // Catch:{ IOException -> 0x0216 }
            r3[r6] = r1     // Catch:{ IOException -> 0x0216 }
            double r1 = (double) r7     // Catch:{ IOException -> 0x0216 }
            java.lang.String r1 = r9.format(r1)     // Catch:{ IOException -> 0x0216 }
            r3[r5] = r1     // Catch:{ IOException -> 0x0216 }
            r1 = 4
            r3[r1] = r0     // Catch:{ IOException -> 0x0216 }
            r0 = 5
            r1 = 1120403456(0x42c80000, float:100.0)
            float r10 = r10 * r1
            int r1 = java.lang.Math.round(r10)     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ IOException -> 0x0216 }
            r3[r0] = r1     // Catch:{ IOException -> 0x0216 }
            r0 = 6
            r3[r0] = r15     // Catch:{ IOException -> 0x0216 }
            java.lang.String r15 = java.lang.String.format(r12, r3)     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x015e:
            int r15 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x0216 }
            int r15 = r15 - r8
            r1.<init>(r0, r7, r15)     // Catch:{ IOException -> 0x0216 }
            return r1
        L_0x0169:
            int r0 = r15.j(r9)     // Catch:{ IOException -> 0x0216 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0216 }
            r2.<init>()     // Catch:{ IOException -> 0x0216 }
            r4 = 0
        L_0x0173:
            if (r4 >= r0) goto L_0x0198
            if (r4 == 0) goto L_0x017c
            java.lang.String r5 = ", "
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
        L_0x017c:
            int r5 = r4 * 2
            int r5 = r5 + r7
            int r5 = r15.f(r5)     // Catch:{ IOException -> 0x0216 }
            float r5 = (float) r5
            double r5 = (double) r5
            r8 = 4679239875398991872(0x40efffe000000000, double:65535.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r8
            java.lang.String r5 = a(r5, r3, r1)     // Catch:{ IOException -> 0x0216 }
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
            int r4 = r4 + 1
            goto L_0x0173
        L_0x0198:
            java.lang.String r15 = r2.toString()     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x019d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0216 }
            r2.<init>()     // Catch:{ IOException -> 0x0216 }
            java.text.DecimalFormat r3 = new java.text.DecimalFormat     // Catch:{ IOException -> 0x0216 }
            java.lang.String r4 = "0.####"
            r3.<init>(r4)     // Catch:{ IOException -> 0x0216 }
            int r0 = r0.length     // Catch:{ IOException -> 0x0216 }
            int r0 = r0 - r9
            int r0 = r0 / r7
        L_0x01ac:
            if (r1 >= r0) goto L_0x01f7
            int r4 = r1 * 12
            int r4 = r4 + r9
            float r5 = r15.l(r4)     // Catch:{ IOException -> 0x0216 }
            int r6 = r4 + 4
            float r6 = r15.l(r6)     // Catch:{ IOException -> 0x0216 }
            int r4 = r4 + 8
            float r4 = r15.l(r4)     // Catch:{ IOException -> 0x0216 }
            if (r1 <= 0) goto L_0x01c8
            java.lang.String r7 = ", "
            r2.append(r7)     // Catch:{ IOException -> 0x0216 }
        L_0x01c8:
            java.lang.String r7 = "("
            r2.append(r7)     // Catch:{ IOException -> 0x0216 }
            double r7 = (double) r5     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = r3.format(r7)     // Catch:{ IOException -> 0x0216 }
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = ", "
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
            double r5 = (double) r6     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = r3.format(r5)     // Catch:{ IOException -> 0x0216 }
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r5 = ", "
            r2.append(r5)     // Catch:{ IOException -> 0x0216 }
            double r4 = (double) r4     // Catch:{ IOException -> 0x0216 }
            java.lang.String r4 = r3.format(r4)     // Catch:{ IOException -> 0x0216 }
            r2.append(r4)     // Catch:{ IOException -> 0x0216 }
            java.lang.String r4 = ")"
            r2.append(r4)     // Catch:{ IOException -> 0x0216 }
            int r1 = r1 + 1
            goto L_0x01ac
        L_0x01f7:
            java.lang.String r15 = r2.toString()     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x01fc:
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0216 }
            java.lang.String r4 = com.drew.metadata.icc.IccReader.a(r2)     // Catch:{ IOException -> 0x0216 }
            r3[r1] = r4     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x0216 }
            r3[r8] = r1     // Catch:{ IOException -> 0x0216 }
            int r0 = r0.length     // Catch:{ IOException -> 0x0216 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0216 }
            r3[r6] = r0     // Catch:{ IOException -> 0x0216 }
            java.lang.String r15 = java.lang.String.format(r15, r3)     // Catch:{ IOException -> 0x0216 }
            return r15
        L_0x0216:
            r15 = 0
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.icc.IccDescriptor.j(int):java.lang.String");
    }

    @NotNull
    public static String a(double d2, int i2, boolean z) {
        boolean z2 = true;
        if (i2 < 1) {
            return "" + Math.round(d2);
        }
        long abs = Math.abs((long) d2);
        double abs2 = Math.abs(d2);
        double d3 = (double) abs;
        Double.isNaN(d3);
        long round = (long) ((int) Math.round((abs2 - d3) * Math.pow(10.0d, (double) i2)));
        String str = "";
        long j = round;
        while (i2 > 0) {
            byte abs3 = (byte) ((int) Math.abs(j % 10));
            j /= 10;
            if (str.length() > 0 || z || abs3 != 0 || i2 == 1) {
                str = abs3 + str;
            }
            i2--;
        }
        long j2 = abs + j;
        if (d2 >= 0.0d || (j2 == 0 && round == 0)) {
            z2 = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "-" : "");
        sb.append(j2);
        sb.append(".");
        sb.append(str);
        return sb.toString();
    }

    @Nullable
    private String a() {
        return a(64, "Perceptual", "Media-Relative Colorimetric", ExifInterface.TAG_SATURATION, "ICC-Absolute Colorimetric");
    }

    @Nullable
    private String b() {
        String s = ((IccDirectory) this.f5211a).s(40);
        if (s == null) {
            return null;
        }
        try {
            switch (a(s)) {
                case 1095782476:
                    return "Apple Computer, Inc.";
                case 1297303124:
                    return "Microsoft Corporation";
                case 1397180704:
                    return "Silicon Graphics, Inc.";
                case 1398099543:
                    return "Sun Microsystems, Inc.";
                case 1413959252:
                    return "Taligent, Inc.";
                default:
                    return String.format("Unknown (%s)", new Object[]{s});
            }
        } catch (IOException unused) {
            return s;
        }
    }

    @Nullable
    private String c() {
        String s = ((IccDirectory) this.f5211a).s(12);
        if (s == null) {
            return null;
        }
        try {
            switch (a(s)) {
                case 1633842036:
                    return "Abstract";
                case 1818848875:
                    return "DeviceLink";
                case 1835955314:
                    return "Display Device";
                case 1852662636:
                    return "Named Color";
                case 1886549106:
                    return "Output Device";
                case 1935896178:
                    return "Input Device";
                case 1936744803:
                    return "ColorSpace Conversion";
                default:
                    return String.format("Unknown (%s)", new Object[]{s});
            }
        } catch (IOException unused) {
            return s;
        }
    }

    @Nullable
    private String d() {
        Integer c2 = ((IccDirectory) this.f5211a).c(8);
        if (c2 == null) {
            return null;
        }
        return String.format("%d.%d.%d", new Object[]{Integer.valueOf((c2.intValue() & -16777216) >> 24), Integer.valueOf((c2.intValue() & 15728640) >> 20), Integer.valueOf((c2.intValue() & 983040) >> 16)});
    }

    private static int a(@NotNull String str) throws IOException {
        return new ByteArrayReader(str.getBytes()).j(0);
    }
}
