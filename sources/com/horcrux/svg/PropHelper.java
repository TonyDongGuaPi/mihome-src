package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.taobao.weex.common.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PropHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5816a = 6;

    PropHelper() {
    }

    static int a(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static double a(java.lang.String r7, double r8, double r10, double r12) {
        /*
            java.lang.String r7 = r7.trim()
            int r0 = r7.length()
            int r1 = r0 + -1
            if (r0 == 0) goto L_0x00dd
            java.lang.String r2 = "normal"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x0016
            goto L_0x00dd
        L_0x0016:
            int r2 = r7.codePointAt(r1)
            r3 = 37
            r4 = 0
            if (r2 != r3) goto L_0x0031
            java.lang.String r7 = r7.substring(r4, r1)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r10 = r7.doubleValue()
            r12 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r10 = r10 / r12
            double r10 = r10 * r8
            return r10
        L_0x0031:
            int r8 = r0 + -2
            if (r8 <= 0) goto L_0x00d2
            java.lang.String r9 = r7.substring(r8)
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3 = -1
            int r5 = r9.hashCode()
            r6 = 3178(0xc6a, float:4.453E-42)
            if (r5 == r6) goto L_0x0099
            r6 = 3240(0xca8, float:4.54E-42)
            if (r5 == r6) goto L_0x008f
            r6 = 3365(0xd25, float:4.715E-42)
            if (r5 == r6) goto L_0x0085
            r6 = 3488(0xda0, float:4.888E-42)
            if (r5 == r6) goto L_0x007b
            r6 = 3571(0xdf3, float:5.004E-42)
            if (r5 == r6) goto L_0x0071
            r6 = 3588(0xe04, float:5.028E-42)
            if (r5 == r6) goto L_0x0067
            r6 = 3592(0xe08, float:5.033E-42)
            if (r5 == r6) goto L_0x005d
            goto L_0x00a3
        L_0x005d:
            java.lang.String r5 = "px"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 0
            goto L_0x00a4
        L_0x0067:
            java.lang.String r5 = "pt"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 2
            goto L_0x00a4
        L_0x0071:
            java.lang.String r5 = "pc"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 3
            goto L_0x00a4
        L_0x007b:
            java.lang.String r5 = "mm"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 4
            goto L_0x00a4
        L_0x0085:
            java.lang.String r5 = "in"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 6
            goto L_0x00a4
        L_0x008f:
            java.lang.String r5 = "em"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 1
            goto L_0x00a4
        L_0x0099:
            java.lang.String r5 = "cm"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto L_0x00a3
            r9 = 5
            goto L_0x00a4
        L_0x00a3:
            r9 = -1
        L_0x00a4:
            switch(r9) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x00c1;
                case 2: goto L_0x00bf;
                case 3: goto L_0x00bc;
                case 4: goto L_0x00b6;
                case 5: goto L_0x00b0;
                case 6: goto L_0x00aa;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            r8 = r0
        L_0x00a8:
            r12 = r1
            goto L_0x00c1
        L_0x00aa:
            r12 = 4636033603912859648(0x4056800000000000, double:90.0)
            goto L_0x00c1
        L_0x00b0:
            r12 = 4630183578586017914(0x4041b76ed677707a, double:35.43307)
            goto L_0x00c1
        L_0x00b6:
            r12 = 4615161236842447043(0x400c58b1572580c3, double:3.543307)
            goto L_0x00c1
        L_0x00bc:
            r12 = 4624633867356078080(0x402e000000000000, double:15.0)
            goto L_0x00c1
        L_0x00bf:
            r12 = 4608308318706860032(0x3ff4000000000000, double:1.25)
        L_0x00c1:
            java.lang.String r7 = r7.substring(r4, r8)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r7 = r7.doubleValue()
            double r7 = r7 * r12
            double r7 = r7 * r10
            return r7
        L_0x00d2:
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r7 = r7.doubleValue()
            double r7 = r7 * r10
            return r7
        L_0x00dd:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.a(java.lang.String, double, double, double):double");
    }

    static double a(SVGLength sVGLength, double d, double d2, double d3, double d4) {
        if (sVGLength == null) {
            return d2;
        }
        SVGLengthUnitType sVGLengthUnitType = sVGLength.b;
        double d5 = sVGLength.f5825a;
        switch (sVGLengthUnitType) {
            case SVG_LENGTHTYPE_NUMBER:
            case SVG_LENGTHTYPE_PX:
                d4 = 1.0d;
                break;
            case SVG_LENGTHTYPE_PERCENTAGE:
                return ((d5 / 100.0d) * d) + d2;
            case SVG_LENGTHTYPE_EMS:
                break;
            case SVG_LENGTHTYPE_EXS:
                d4 /= 2.0d;
                break;
            case SVG_LENGTHTYPE_CM:
                d4 = 35.43307d;
                break;
            case SVG_LENGTHTYPE_MM:
                d4 = 3.543307d;
                break;
            case SVG_LENGTHTYPE_IN:
                d4 = 90.0d;
                break;
            case SVG_LENGTHTYPE_PT:
                d4 = 1.25d;
                break;
            case SVG_LENGTHTYPE_PC:
                d4 = 15.0d;
                break;
            default:
                return (d5 * d3) + d2;
        }
        return (d5 * d4 * d3) + d2;
    }

    static class PathParser {

        /* renamed from: a  reason: collision with root package name */
        private static final Pattern f5818a = Pattern.compile("[a-df-z]|[\\-+]?(?:[\\d.]e[\\-+]?|[^\\s\\-+,a-z])+", 2);
        private static final Pattern b = Pattern.compile("(\\.\\d+)(?=-?\\.)");
        private Matcher c;
        private Path d;
        private final String e;
        private float f = 0.0f;
        private float g = 0.0f;
        private float h;
        private float i;
        private float j = 0.0f;
        private float k = 0.0f;
        private final float l;
        private boolean m = true;
        private boolean n = false;
        private String o;
        private String p;
        private WritableArray q;
        private WritableMap r;

        PathParser(String str, float f2) {
            this.l = f2;
            this.e = str;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(java.lang.String r9) {
            /*
                r8 = this;
                int r0 = r9.hashCode()
                switch(r0) {
                    case 65: goto L_0x00de;
                    case 67: goto L_0x00d3;
                    case 72: goto L_0x00c9;
                    case 76: goto L_0x00bf;
                    case 77: goto L_0x00b5;
                    case 81: goto L_0x00aa;
                    case 83: goto L_0x009f;
                    case 84: goto L_0x0094;
                    case 86: goto L_0x008a;
                    case 90: goto L_0x007f;
                    case 97: goto L_0x0073;
                    case 99: goto L_0x0067;
                    case 104: goto L_0x005c;
                    case 108: goto L_0x0051;
                    case 109: goto L_0x0046;
                    case 113: goto L_0x003a;
                    case 115: goto L_0x002e;
                    case 116: goto L_0x0022;
                    case 118: goto L_0x0016;
                    case 122: goto L_0x0009;
                    default: goto L_0x0007;
                }
            L_0x0007:
                goto L_0x00e9
            L_0x0009:
                java.lang.String r0 = "z"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 19
                goto L_0x00ea
            L_0x0016:
                java.lang.String r0 = "v"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 6
                goto L_0x00ea
            L_0x0022:
                java.lang.String r0 = "t"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 14
                goto L_0x00ea
            L_0x002e:
                java.lang.String r0 = "s"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 10
                goto L_0x00ea
            L_0x003a:
                java.lang.String r0 = "q"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 12
                goto L_0x00ea
            L_0x0046:
                java.lang.String r0 = "m"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 0
                goto L_0x00ea
            L_0x0051:
                java.lang.String r0 = "l"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 2
                goto L_0x00ea
            L_0x005c:
                java.lang.String r0 = "h"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 4
                goto L_0x00ea
            L_0x0067:
                java.lang.String r0 = "c"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 8
                goto L_0x00ea
            L_0x0073:
                java.lang.String r0 = "a"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 16
                goto L_0x00ea
            L_0x007f:
                java.lang.String r0 = "Z"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 18
                goto L_0x00ea
            L_0x008a:
                java.lang.String r0 = "V"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 7
                goto L_0x00ea
            L_0x0094:
                java.lang.String r0 = "T"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 15
                goto L_0x00ea
            L_0x009f:
                java.lang.String r0 = "S"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 11
                goto L_0x00ea
            L_0x00aa:
                java.lang.String r0 = "Q"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 13
                goto L_0x00ea
            L_0x00b5:
                java.lang.String r0 = "M"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 1
                goto L_0x00ea
            L_0x00bf:
                java.lang.String r0 = "L"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 3
                goto L_0x00ea
            L_0x00c9:
                java.lang.String r0 = "H"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 5
                goto L_0x00ea
            L_0x00d3:
                java.lang.String r0 = "C"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 9
                goto L_0x00ea
            L_0x00de:
                java.lang.String r0 = "A"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x00e9
                r0 = 17
                goto L_0x00ea
            L_0x00e9:
                r0 = -1
            L_0x00ea:
                r1 = 0
                switch(r0) {
                    case 0: goto L_0x0230;
                    case 1: goto L_0x0224;
                    case 2: goto L_0x0218;
                    case 3: goto L_0x020c;
                    case 4: goto L_0x0204;
                    case 5: goto L_0x01fa;
                    case 6: goto L_0x01f2;
                    case 7: goto L_0x01e8;
                    case 8: goto L_0x01cb;
                    case 9: goto L_0x01ad;
                    case 10: goto L_0x0198;
                    case 11: goto L_0x0183;
                    case 12: goto L_0x016e;
                    case 13: goto L_0x0159;
                    case 14: goto L_0x014c;
                    case 15: goto L_0x013f;
                    case 16: goto L_0x011d;
                    case 17: goto L_0x00fb;
                    case 18: goto L_0x00f6;
                    case 19: goto L_0x00f6;
                    default: goto L_0x00ee;
                }
            L_0x00ee:
                r8.p = r9
                java.lang.String r0 = r8.o
                r8.a((java.lang.String) r0)
                return
            L_0x00f6:
                r8.d()
                goto L_0x023b
            L_0x00fb:
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                boolean r4 = r8.b()
                boolean r5 = r8.b()
                float r6 = r8.c()
                float r7 = r8.c()
                r0 = r8
                r0.b(r1, r2, r3, r4, r5, r6, r7)
                goto L_0x023b
            L_0x011d:
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                boolean r4 = r8.b()
                boolean r5 = r8.b()
                float r6 = r8.c()
                float r7 = r8.c()
                r0 = r8
                r0.a(r1, r2, r3, r4, r5, r6, r7)
                goto L_0x023b
            L_0x013f:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.g(r0, r1)
                goto L_0x023b
            L_0x014c:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.f(r0, r1)
                goto L_0x023b
            L_0x0159:
                float r0 = r8.c()
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                r8.d(r0, r1, r2, r3)
                goto L_0x023b
            L_0x016e:
                float r0 = r8.c()
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                r8.c(r0, r1, r2, r3)
                goto L_0x023b
            L_0x0183:
                float r0 = r8.c()
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                r8.b(r0, r1, r2, r3)
                goto L_0x023b
            L_0x0198:
                float r0 = r8.c()
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                r8.a(r0, r1, r2, r3)
                goto L_0x023b
            L_0x01ad:
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                float r4 = r8.c()
                float r5 = r8.c()
                float r6 = r8.c()
                r0 = r8
                r0.b(r1, r2, r3, r4, r5, r6)
                goto L_0x023b
            L_0x01cb:
                float r1 = r8.c()
                float r2 = r8.c()
                float r3 = r8.c()
                float r4 = r8.c()
                float r5 = r8.c()
                float r6 = r8.c()
                r0 = r8
                r0.a(r1, r2, r3, r4, r5, r6)
                goto L_0x023b
            L_0x01e8:
                float r0 = r8.f
                float r1 = r8.c()
                r8.e(r0, r1)
                goto L_0x023b
            L_0x01f2:
                float r0 = r8.c()
                r8.d(r1, r0)
                goto L_0x023b
            L_0x01fa:
                float r0 = r8.c()
                float r1 = r8.g
                r8.e(r0, r1)
                goto L_0x023b
            L_0x0204:
                float r0 = r8.c()
                r8.d(r0, r1)
                goto L_0x023b
            L_0x020c:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.e(r0, r1)
                goto L_0x023b
            L_0x0218:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.d(r0, r1)
                goto L_0x023b
            L_0x0224:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.c(r0, r1)
                goto L_0x023b
            L_0x0230:
                float r0 = r8.c()
                float r1 = r8.c()
                r8.b(r0, r1)
            L_0x023b:
                r8.o = r9
                java.lang.String r0 = "m"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x024a
                java.lang.String r0 = "l"
                r8.o = r0
                goto L_0x0256
            L_0x024a:
                java.lang.String r0 = "M"
                boolean r0 = r9.equals(r0)
                if (r0 == 0) goto L_0x0256
                java.lang.String r0 = "L"
                r8.o = r0
            L_0x0256:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.PathParser.a(java.lang.String):void");
        }

        /* access modifiers changed from: package-private */
        public Path a() {
            this.d = new Path();
            this.q = Arguments.createArray();
            this.c = f5818a.matcher(b.matcher(this.e).replaceAll("$1,"));
            while (this.c.find() && this.m) {
                a(this.c.group());
            }
            return this.d;
        }

        private WritableMap a(float f2, float f3) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (f2 * this.l));
            createMap.putDouble(Constants.Name.Y, (double) (f3 * this.l));
            return createMap;
        }

        private WritableMap a(WritableMap writableMap) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", writableMap.getDouble("x"));
            createMap.putDouble(Constants.Name.Y, writableMap.getDouble(Constants.Name.Y));
            return createMap;
        }

        private boolean b() {
            if (this.c.find()) {
                return this.c.group().equals("1");
            }
            this.m = false;
            this.d = new Path();
            return false;
        }

        private float c() {
            if (this.p != null) {
                String str = this.p;
                this.p = null;
                return Float.parseFloat(str);
            } else if (this.c.find()) {
                return Float.parseFloat(this.c.group());
            } else {
                this.m = false;
                this.d = new Path();
                return 0.0f;
            }
        }

        private void b(float f2, float f3) {
            c(f2 + this.f, f3 + this.g);
        }

        private void c(float f2, float f3) {
            this.f = f2;
            this.j = f2;
            this.h = f2;
            this.g = f3;
            this.k = f3;
            this.i = f3;
            this.d.moveTo(this.l * f2, this.l * f3);
            this.r = a(f2, f3);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            this.q.pushArray(createArray);
        }

        private void d(float f2, float f3) {
            e(f2 + this.f, f3 + this.g);
        }

        private void e(float f2, float f3) {
            e();
            this.f = f2;
            this.j = f2;
            this.g = f3;
            this.k = f3;
            this.d.lineTo(this.l * f2, this.l * f3);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f2, f3));
            this.q.pushArray(createArray);
        }

        private void a(float f2, float f3, float f4, float f5, float f6, float f7) {
            b(f2 + this.f, f3 + this.g, f4 + this.f, f5 + this.g, f6 + this.f, f7 + this.g);
        }

        private void b(float f2, float f3, float f4, float f5, float f6, float f7) {
            this.j = f4;
            this.k = f5;
            c(f2, f3, f4, f5, f6, f7);
        }

        private void c(float f2, float f3, float f4, float f5, float f6, float f7) {
            e();
            this.f = f6;
            this.g = f7;
            this.d.cubicTo(this.l * f2, this.l * f3, this.l * f4, this.l * f5, this.l * f6, this.l * f7);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f4, f5));
            createArray.pushMap(a(f6, f7));
            this.q.pushArray(createArray);
        }

        private void a(float f2, float f3, float f4, float f5) {
            b(f2 + this.f, f3 + this.g, f4 + this.f, f5 + this.g);
        }

        private void b(float f2, float f3, float f4, float f5) {
            this.j = f2;
            this.k = f3;
            c((this.f * 2.0f) - this.j, (this.g * 2.0f) - this.k, f2, f3, f4, f5);
        }

        private void c(float f2, float f3, float f4, float f5) {
            d(f2 + this.f, f3 + this.g, f4 + this.f, f5 + this.g);
        }

        private void d(float f2, float f3, float f4, float f5) {
            this.j = f2;
            this.k = f3;
            float f6 = f2 * 2.0f;
            float f7 = f3 * 2.0f;
            float f8 = (this.f + f6) / 3.0f;
            float f9 = (this.g + f7) / 3.0f;
            c(f8, f9, (f4 + f6) / 3.0f, (f5 + f7) / 3.0f, f4, f5);
        }

        private void f(float f2, float f3) {
            g(f2 + this.f, f3 + this.g);
        }

        private void g(float f2, float f3) {
            d((this.f * 2.0f) - this.j, (this.g * 2.0f) - this.k, f2, f3);
        }

        private void a(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            b(f2, f3, f4, z, z2, f5 + this.f, f6 + this.g);
        }

        private void b(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            float f7;
            float f8;
            float f9;
            boolean z3 = z;
            boolean z4 = z2;
            float f10 = f5;
            float f11 = f6;
            float f12 = this.f;
            float f13 = this.g;
            float abs = Math.abs(f3 == 0.0f ? f2 == 0.0f ? f11 - f13 : f2 : f3);
            float abs2 = Math.abs(f2 == 0.0f ? f10 - f12 : f2);
            if (abs2 == 0.0f || abs == 0.0f || (f10 == f12 && f11 == f13)) {
                e(f10, f11);
                return;
            }
            float radians = (float) Math.toRadians((double) f4);
            double d2 = (double) radians;
            float cos = (float) Math.cos(d2);
            float sin = (float) Math.sin(d2);
            float f14 = f10 - f12;
            float f15 = f11 - f13;
            float f16 = ((cos * f14) / 2.0f) + ((sin * f15) / 2.0f);
            float f17 = -sin;
            float f18 = ((f17 * f14) / 2.0f) + ((cos * f15) / 2.0f);
            float f19 = abs2 * abs2;
            float f20 = f19 * abs * abs;
            float f21 = abs * abs * f16 * f16;
            float f22 = f19 * f18 * f18;
            float f23 = (f20 - f22) - f21;
            if (f23 < 0.0f) {
                float sqrt = (float) Math.sqrt((double) (1.0f - (f23 / f20)));
                abs *= sqrt;
                f7 = f14 / 2.0f;
                f8 = abs2 * sqrt;
                f9 = f15 / 2.0f;
                boolean z5 = z2;
            } else {
                float sqrt2 = (float) Math.sqrt((double) (f23 / (f22 + f21)));
                if (z3 == z2) {
                    sqrt2 = -sqrt2;
                }
                float f24 = (((-sqrt2) * f18) * abs2) / abs;
                float f25 = ((sqrt2 * f16) * abs) / abs2;
                f7 = ((cos * f24) - (sin * f25)) + (f14 / 2.0f);
                f8 = abs2;
                f9 = (f15 / 2.0f) + (f24 * sin) + (f25 * cos);
            }
            float f26 = cos / f8;
            float f27 = sin / f8;
            float f28 = f17 / abs;
            float f29 = cos / abs;
            float f30 = -f7;
            float f31 = -f9;
            float f32 = radians;
            float f33 = abs;
            float f34 = f8;
            float atan2 = (float) Math.atan2((double) ((f28 * f30) + (f29 * f31)), (double) ((f30 * f26) + (f31 * f27)));
            float f35 = f14 - f7;
            float f36 = f15 - f9;
            float atan22 = (float) Math.atan2((double) ((f28 * f35) + (f29 * f36)), (double) ((f26 * f35) + (f27 * f36)));
            float f37 = f7 + f12;
            float f38 = f9 + f13;
            float f39 = f14 + f12;
            float f40 = f15 + f13;
            e();
            this.j = f39;
            this.f = f39;
            this.k = f40;
            this.g = f40;
            if (f34 == f33 && f32 == 0.0f) {
                float degrees = (float) Math.toDegrees((double) atan2);
                float abs3 = Math.abs((degrees - ((float) Math.toDegrees((double) atan22))) % 360.0f);
                if (z) {
                    if (abs3 < 180.0f) {
                        abs3 = 360.0f - abs3;
                    }
                } else if (abs3 > 180.0f) {
                    abs3 = 360.0f - abs3;
                }
                if (!z2) {
                    abs3 = -abs3;
                }
                this.d.arcTo(new RectF((f37 - f34) * this.l, (f38 - f34) * this.l, (f37 + f34) * this.l, (f38 + f34) * this.l), degrees, abs3);
                return;
            }
            boolean z6 = z2;
            a(f37, f38, f34, f33, atan2, atan22, z2, f32);
        }

        private void d() {
            if (this.n) {
                this.f = this.h;
                this.g = this.i;
                this.n = false;
                this.d.close();
                WritableArray createArray = Arguments.createArray();
                createArray.pushMap(a(this.r));
                createArray.pushMap(a(this.r));
                createArray.pushMap(a(this.r));
                this.q.pushArray(createArray);
            }
        }

        private void a(float f2, float f3, float f4, float f5, float f6, float f7, boolean z, float f8) {
            float f9 = f6;
            double d2 = (double) f8;
            float cos = (float) Math.cos(d2);
            float sin = (float) Math.sin(d2);
            float f10 = cos * f4;
            float f11 = (-sin) * f5;
            float f12 = sin * f4;
            float f13 = cos * f5;
            float f14 = f7 - f9;
            if (f14 < 0.0f && z) {
                double d3 = (double) f14;
                Double.isNaN(d3);
                f14 = (float) (d3 + 6.283185307179586d);
            } else if (f14 > 0.0f && !z) {
                double d4 = (double) f14;
                Double.isNaN(d4);
                f14 = (float) (d4 - 6.283185307179586d);
            }
            double d5 = (double) f14;
            Double.isNaN(d5);
            int ceil = (int) Math.ceil(Math.abs(a(d5 / 1.5707963267948966d)));
            float f15 = f14 / ((float) ceil);
            float tan = (float) (Math.tan((double) (f15 / 4.0f)) * 1.3333333333333333d);
            double d6 = (double) f9;
            float cos2 = (float) Math.cos(d6);
            float sin2 = (float) Math.sin(d6);
            int i2 = 0;
            while (i2 < ceil) {
                float f16 = cos2 - (tan * sin2);
                float f17 = sin2 + (cos2 * tan);
                f9 += f15;
                float f18 = f15;
                int i3 = ceil;
                double d7 = (double) f9;
                int i4 = i2;
                cos2 = (float) Math.cos(d7);
                float sin3 = (float) Math.sin(d7);
                float f19 = (tan * sin3) + cos2;
                float f20 = sin3 - (tan * cos2);
                Path path = this.d;
                path.cubicTo((f2 + (f10 * f16) + (f11 * f17)) * this.l, (f3 + (f16 * f12) + (f17 * f13)) * this.l, (f2 + (f10 * f19) + (f11 * f20)) * this.l, (f3 + (f19 * f12) + (f20 * f13)) * this.l, (f2 + (f10 * cos2) + (f11 * sin3)) * this.l, (f3 + (f12 * cos2) + (f13 * sin3)) * this.l);
                i2 = i4 + 1;
                sin2 = sin3;
                ceil = i3;
                f15 = f18;
            }
        }

        private void e() {
            if (!this.n) {
                this.h = this.f;
                this.i = this.g;
                this.n = true;
            }
        }

        private double a(double d2) {
            double pow = Math.pow(10.0d, 4.0d);
            double round = (double) Math.round(d2 * pow);
            Double.isNaN(round);
            return round / pow;
        }
    }
}
