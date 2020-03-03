package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ISO8601Converter {
    private ISO8601Converter() {
    }

    public static XMPDateTime a(String str) throws XMPException {
        return a(str, new XMPDateTimeImpl());
    }

    /* JADX WARNING: Removed duplicated region for block: B:127:0x021a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x021b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.adobe.xmp.XMPDateTime a(java.lang.String r11, com.adobe.xmp.XMPDateTime r12) throws com.adobe.xmp.XMPException {
        /*
            if (r11 == 0) goto L_0x0223
            int r0 = r11.length()
            if (r0 != 0) goto L_0x0009
            return r12
        L_0x0009:
            com.adobe.xmp.impl.ParseState r0 = new com.adobe.xmp.impl.ParseState
            r0.<init>(r11)
            r11 = 0
            char r1 = r0.a(r11)
            r2 = 45
            if (r1 != r2) goto L_0x001a
            r0.d()
        L_0x001a:
            java.lang.String r1 = "Invalid year in date string"
            r3 = 9999(0x270f, float:1.4012E-41)
            int r1 = r0.a(r1, r3)
            boolean r3 = r0.b()
            r4 = 5
            if (r3 == 0) goto L_0x0038
            char r3 = r0.c()
            if (r3 != r2) goto L_0x0030
            goto L_0x0038
        L_0x0030:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after year"
            r11.<init>(r12, r4)
            throw r11
        L_0x0038:
            char r3 = r0.a(r11)
            if (r3 != r2) goto L_0x003f
            int r1 = -r1
        L_0x003f:
            r12.a((int) r1)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x0049
            return r12
        L_0x0049:
            r0.d()
            java.lang.String r1 = "Invalid month in date string"
            r3 = 12
            int r1 = r0.a(r1, r3)
            boolean r3 = r0.b()
            if (r3 == 0) goto L_0x0069
            char r3 = r0.c()
            if (r3 != r2) goto L_0x0061
            goto L_0x0069
        L_0x0061:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after month"
            r11.<init>(r12, r4)
            throw r11
        L_0x0069:
            r12.b(r1)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x0073
            return r12
        L_0x0073:
            r0.d()
            java.lang.String r1 = "Invalid day in date string"
            r3 = 31
            int r1 = r0.a(r1, r3)
            boolean r3 = r0.b()
            if (r3 == 0) goto L_0x0095
            char r3 = r0.c()
            r5 = 84
            if (r3 != r5) goto L_0x008d
            goto L_0x0095
        L_0x008d:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after day"
            r11.<init>(r12, r4)
            throw r11
        L_0x0095:
            r12.c(r1)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x009f
            return r12
        L_0x009f:
            r0.d()
            java.lang.String r1 = "Invalid hour in date string"
            r3 = 23
            int r1 = r0.a(r1, r3)
            r12.d(r1)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x00b4
            return r12
        L_0x00b4:
            char r1 = r0.c()
            r5 = 59
            r6 = 58
            r7 = 43
            r8 = 90
            if (r1 != r6) goto L_0x00f5
            r0.d()
            java.lang.String r1 = "Invalid minute in date string"
            int r1 = r0.a(r1, r5)
            boolean r9 = r0.b()
            if (r9 == 0) goto L_0x00f2
            char r9 = r0.c()
            if (r9 == r6) goto L_0x00f2
            char r9 = r0.c()
            if (r9 == r8) goto L_0x00f2
            char r9 = r0.c()
            if (r9 == r7) goto L_0x00f2
            char r9 = r0.c()
            if (r9 != r2) goto L_0x00ea
            goto L_0x00f2
        L_0x00ea:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after minute"
            r11.<init>(r12, r4)
            throw r11
        L_0x00f2:
            r12.e(r1)
        L_0x00f5:
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x00fc
            return r12
        L_0x00fc:
            boolean r1 = r0.b()
            if (r1 == 0) goto L_0x018d
            char r1 = r0.c()
            if (r1 != r6) goto L_0x018d
            r0.d()
            java.lang.String r1 = "Invalid whole seconds in date string"
            int r1 = r0.a(r1, r5)
            boolean r9 = r0.b()
            r10 = 46
            if (r9 == 0) goto L_0x013a
            char r9 = r0.c()
            if (r9 == r10) goto L_0x013a
            char r9 = r0.c()
            if (r9 == r8) goto L_0x013a
            char r9 = r0.c()
            if (r9 == r7) goto L_0x013a
            char r9 = r0.c()
            if (r9 != r2) goto L_0x0132
            goto L_0x013a
        L_0x0132:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after whole seconds"
            r11.<init>(r12, r4)
            throw r11
        L_0x013a:
            r12.f(r1)
            char r1 = r0.c()
            if (r1 != r10) goto L_0x01a8
            r0.d()
            int r1 = r0.e()
            java.lang.String r9 = "Invalid fractional seconds in date string"
            r10 = 999999999(0x3b9ac9ff, float:0.004723787)
            int r9 = r0.a(r9, r10)
            boolean r10 = r0.b()
            if (r10 == 0) goto L_0x0174
            char r10 = r0.c()
            if (r10 == r8) goto L_0x0174
            char r10 = r0.c()
            if (r10 == r7) goto L_0x0174
            char r10 = r0.c()
            if (r10 != r2) goto L_0x016c
            goto L_0x0174
        L_0x016c:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after fractional second"
            r11.<init>(r12, r4)
            throw r11
        L_0x0174:
            int r10 = r0.e()
            int r10 = r10 - r1
        L_0x0179:
            r1 = 9
            if (r10 <= r1) goto L_0x0182
            int r9 = r9 / 10
            int r10 = r10 + -1
            goto L_0x0179
        L_0x0182:
            if (r10 >= r1) goto L_0x0189
            int r9 = r9 * 10
            int r10 = r10 + 1
            goto L_0x0182
        L_0x0189:
            r12.g(r9)
            goto L_0x01a8
        L_0x018d:
            char r1 = r0.c()
            if (r1 == r8) goto L_0x01a8
            char r1 = r0.c()
            if (r1 == r7) goto L_0x01a8
            char r1 = r0.c()
            if (r1 != r2) goto L_0x01a0
            goto L_0x01a8
        L_0x01a0:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after time"
            r11.<init>(r12, r4)
            throw r11
        L_0x01a8:
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x01af
            return r12
        L_0x01af:
            char r1 = r0.c()
            if (r1 != r8) goto L_0x01b9
            r0.d()
            goto L_0x01fd
        L_0x01b9:
            boolean r1 = r0.b()
            if (r1 == 0) goto L_0x01fd
            char r1 = r0.c()
            if (r1 != r7) goto L_0x01c7
            r1 = 1
            goto L_0x01ce
        L_0x01c7:
            char r1 = r0.c()
            if (r1 != r2) goto L_0x01f5
            r1 = -1
        L_0x01ce:
            r0.d()
            java.lang.String r2 = "Invalid time zone hour in date string"
            int r2 = r0.a(r2, r3)
            boolean r3 = r0.b()
            if (r3 == 0) goto L_0x01ff
            char r11 = r0.c()
            if (r11 != r6) goto L_0x01ed
            r0.d()
            java.lang.String r11 = "Invalid time zone minute in date string"
            int r11 = r0.a(r11, r5)
            goto L_0x01ff
        L_0x01ed:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after time zone hour"
            r11.<init>(r12, r4)
            throw r11
        L_0x01f5:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Time zone must begin with 'Z', '+', or '-'"
            r11.<init>(r12, r4)
            throw r11
        L_0x01fd:
            r1 = 0
            r2 = 0
        L_0x01ff:
            int r2 = r2 * 3600
            int r2 = r2 * 1000
            int r11 = r11 * 60
            int r11 = r11 * 1000
            int r2 = r2 + r11
            int r2 = r2 * r1
            java.util.SimpleTimeZone r11 = new java.util.SimpleTimeZone
            java.lang.String r1 = ""
            r11.<init>(r2, r1)
            r12.a((java.util.TimeZone) r11)
            boolean r11 = r0.b()
            if (r11 != 0) goto L_0x021b
            return r12
        L_0x021b:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            java.lang.String r12 = "Invalid date string, extra chars at end"
            r11.<init>(r12, r4)
            throw r11
        L_0x0223:
            com.adobe.xmp.XMPException r11 = new com.adobe.xmp.XMPException
            r12 = 4
            java.lang.String r0 = "Parameter must not be null"
            r11.<init>(r0, r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ISO8601Converter.a(java.lang.String, com.adobe.xmp.XMPDateTime):com.adobe.xmp.XMPDateTime");
    }

    public static String a(XMPDateTime xMPDateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        if (xMPDateTime.i()) {
            DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.a()));
            if (xMPDateTime.b() == 0) {
                return stringBuffer.toString();
            }
            decimalFormat.applyPattern("'-'00");
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.b()));
            if (xMPDateTime.c() == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.c()));
            if (xMPDateTime.j()) {
                stringBuffer.append(PatternFormatter.THROWABLE_CONVERSION_CHAR);
                decimalFormat.applyPattern("00");
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.d()));
                stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.e()));
                if (!(xMPDateTime.f() == 0 && xMPDateTime.g() == 0)) {
                    double f = (double) xMPDateTime.f();
                    double g = (double) xMPDateTime.g();
                    Double.isNaN(g);
                    Double.isNaN(f);
                    decimalFormat.applyPattern(":00.#########");
                    stringBuffer.append(decimalFormat.format(f + (g / 1.0E9d)));
                }
                if (xMPDateTime.k()) {
                    int offset = xMPDateTime.h().getOffset(xMPDateTime.l().getTimeInMillis());
                    if (offset == 0) {
                        stringBuffer.append('Z');
                    } else {
                        int i = offset / 3600000;
                        int abs = Math.abs((offset % 3600000) / 60000);
                        decimalFormat.applyPattern("+00;-00");
                        stringBuffer.append(decimalFormat.format((long) i));
                        decimalFormat.applyPattern(":00");
                        stringBuffer.append(decimalFormat.format((long) abs));
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
