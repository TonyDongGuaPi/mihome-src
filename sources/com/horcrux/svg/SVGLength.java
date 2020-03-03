package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import java.util.ArrayList;

class SVGLength {

    /* renamed from: a  reason: collision with root package name */
    final double f5825a;
    final SVGLengthUnitType b;

    private SVGLength() {
        this.f5825a = 0.0d;
        this.b = SVGLengthUnitType.SVG_LENGTHTYPE_UNKNOWN;
    }

    SVGLength(double d) {
        this.f5825a = d;
        this.b = SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SVGLength(java.lang.String r8) {
        /*
            r7 = this;
            r7.<init>()
            java.lang.String r8 = r8.trim()
            int r0 = r8.length()
            int r1 = r0 + -1
            if (r0 == 0) goto L_0x0103
            java.lang.String r2 = "normal"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0019
            goto L_0x0103
        L_0x0019:
            int r2 = r8.codePointAt(r1)
            r3 = 37
            r4 = 0
            if (r2 != r3) goto L_0x0036
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE
            r7.b = r0
            java.lang.String r8 = r8.substring(r4, r1)
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r0 = r8.doubleValue()
            r7.f5825a = r0
            goto L_0x010b
        L_0x0036:
            int r1 = r0 + -2
            if (r1 <= 0) goto L_0x00f4
            java.lang.String r2 = r8.substring(r1)
            r3 = -1
            int r5 = r2.hashCode()
            r6 = 3178(0xc6a, float:4.453E-42)
            if (r5 == r6) goto L_0x00aa
            r6 = 3240(0xca8, float:4.54E-42)
            if (r5 == r6) goto L_0x00a0
            r6 = 3251(0xcb3, float:4.556E-42)
            if (r5 == r6) goto L_0x0096
            r6 = 3365(0xd25, float:4.715E-42)
            if (r5 == r6) goto L_0x008c
            r6 = 3488(0xda0, float:4.888E-42)
            if (r5 == r6) goto L_0x0082
            r6 = 3571(0xdf3, float:5.004E-42)
            if (r5 == r6) goto L_0x0078
            r6 = 3588(0xe04, float:5.028E-42)
            if (r5 == r6) goto L_0x006e
            r6 = 3592(0xe08, float:5.033E-42)
            if (r5 == r6) goto L_0x0064
            goto L_0x00b4
        L_0x0064:
            java.lang.String r5 = "px"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 0
            goto L_0x00b5
        L_0x006e:
            java.lang.String r5 = "pt"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 3
            goto L_0x00b5
        L_0x0078:
            java.lang.String r5 = "pc"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 4
            goto L_0x00b5
        L_0x0082:
            java.lang.String r5 = "mm"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 5
            goto L_0x00b5
        L_0x008c:
            java.lang.String r5 = "in"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 7
            goto L_0x00b5
        L_0x0096:
            java.lang.String r5 = "ex"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 2
            goto L_0x00b5
        L_0x00a0:
            java.lang.String r5 = "em"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 1
            goto L_0x00b5
        L_0x00aa:
            java.lang.String r5 = "cm"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00b4
            r2 = 6
            goto L_0x00b5
        L_0x00b4:
            r2 = -1
        L_0x00b5:
            switch(r2) {
                case 0: goto L_0x00e0;
                case 1: goto L_0x00db;
                case 2: goto L_0x00d6;
                case 3: goto L_0x00d1;
                case 4: goto L_0x00cc;
                case 5: goto L_0x00c7;
                case 6: goto L_0x00c2;
                case 7: goto L_0x00bd;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER
            r7.b = r1
            goto L_0x00e5
        L_0x00bd:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_IN
            r7.b = r0
            goto L_0x00e4
        L_0x00c2:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_CM
            r7.b = r0
            goto L_0x00e4
        L_0x00c7:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_MM
            r7.b = r0
            goto L_0x00e4
        L_0x00cc:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PC
            r7.b = r0
            goto L_0x00e4
        L_0x00d1:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PT
            r7.b = r0
            goto L_0x00e4
        L_0x00d6:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EXS
            r7.b = r0
            goto L_0x00e4
        L_0x00db:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EMS
            r7.b = r0
            goto L_0x00e4
        L_0x00e0:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER
            r7.b = r0
        L_0x00e4:
            r0 = r1
        L_0x00e5:
            java.lang.String r8 = r8.substring(r4, r0)
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r0 = r8.doubleValue()
            r7.f5825a = r0
            goto L_0x010b
        L_0x00f4:
            com.horcrux.svg.SVGLengthUnitType r0 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER
            r7.b = r0
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r0 = r8.doubleValue()
            r7.f5825a = r0
            goto L_0x010b
        L_0x0103:
            com.horcrux.svg.SVGLengthUnitType r8 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_UNKNOWN
            r7.b = r8
            r0 = 0
            r7.f5825a = r0
        L_0x010b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SVGLength.<init>(java.lang.String):void");
    }

    static SVGLength a(Dynamic dynamic) {
        switch (dynamic.getType()) {
            case Number:
                return new SVGLength(dynamic.asDouble());
            case String:
                return new SVGLength(dynamic.asString());
            default:
                return new SVGLength();
        }
    }

    static String b(Dynamic dynamic) {
        switch (dynamic.getType()) {
            case Number:
                return String.valueOf(dynamic.asDouble());
            case String:
                return dynamic.asString();
            default:
                return null;
        }
    }

    static ArrayList<SVGLength> c(Dynamic dynamic) {
        switch (dynamic.getType()) {
            case Number:
                ArrayList<SVGLength> arrayList = new ArrayList<>(1);
                arrayList.add(new SVGLength(dynamic.asDouble()));
                return arrayList;
            case String:
                ArrayList<SVGLength> arrayList2 = new ArrayList<>(1);
                arrayList2.add(new SVGLength(dynamic.asString()));
                return arrayList2;
            case Array:
                ReadableArray asArray = dynamic.asArray();
                int size = asArray.size();
                ArrayList<SVGLength> arrayList3 = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    arrayList3.add(a(asArray.getDynamic(i)));
                }
                return arrayList3;
            default:
                return null;
        }
    }
}
