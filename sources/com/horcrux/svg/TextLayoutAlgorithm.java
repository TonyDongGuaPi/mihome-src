package com.horcrux.svg;

import android.view.View;
import java.util.ArrayList;

class TextLayoutAlgorithm {
    TextLayoutAlgorithm() {
    }

    class CharacterInformation {

        /* renamed from: a  reason: collision with root package name */
        int f5835a;
        double b = 0.0d;
        double c = 0.0d;
        double d;
        char e;
        double f = 0.0d;
        TextView g;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;
        boolean m = true;
        boolean n = false;
        boolean o = false;
        boolean p = false;

        CharacterInformation(int i2, char c2) {
            this.f5835a = i2;
            this.e = c2;
        }
    }

    class LayoutInput {

        /* renamed from: a  reason: collision with root package name */
        TextView f5836a;
        boolean b;

        LayoutInput() {
        }
    }

    private void a(ArrayList<TextPathView> arrayList, ArrayList<TextView> arrayList2, StringBuilder sb, View view, TextPathView textPathView) {
        int i = 0;
        if (view instanceof TSpanView) {
            TSpanView tSpanView = (TSpanView) view;
            String str = tSpanView.b;
            if (str == null) {
                while (i < tSpanView.getChildCount()) {
                    a(arrayList, arrayList2, sb, tSpanView.getChildAt(i), textPathView);
                    i++;
                }
                return;
            }
            while (i < str.length()) {
                arrayList2.add(tSpanView);
                arrayList.add(textPathView);
                i++;
            }
            sb.append(str);
            return;
        }
        if (view instanceof TextPathView) {
            textPathView = (TextPathView) view;
        }
        while (i < textPathView.getChildCount()) {
            a(arrayList, arrayList2, sb, textPathView.getChildAt(i), textPathView);
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02d7, code lost:
        if (r9 > (r14 / 2.0d)) goto L_0x02d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01a4, code lost:
        if (r18 == Double.POSITIVE_INFINITY) goto L_0x01a9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[] a(com.horcrux.svg.TextLayoutAlgorithm.LayoutInput r31) {
        /*
            r30 = this;
            r7 = r30
            r0 = r31
            com.horcrux.svg.TextView r8 = r0.f5836a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r5 = 0
            r0 = r30
            r1 = r9
            r3 = r6
            r4 = r8
            r0.a(r1, r2, r3, r4, r5)
            java.lang.String r0 = r6.toString()
            char[] r0 = r0.toCharArray()
            int r10 = r0.length
            com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[] r11 = new com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[r10]
            r12 = 0
            r1 = 0
        L_0x002b:
            if (r1 >= r10) goto L_0x0039
            com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation r2 = new com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation
            char r3 = r0[r1]
            r2.<init>(r1, r3)
            r11[r1] = r2
            int r1 = r1 + 1
            goto L_0x002b
        L_0x0039:
            if (r10 != 0) goto L_0x003c
            return r11
        L_0x003c:
            android.graphics.PointF[] r13 = new android.graphics.PointF[r10]
            r0 = 0
        L_0x003f:
            r14 = 0
            if (r0 >= r10) goto L_0x004c
            android.graphics.PointF r1 = new android.graphics.PointF
            r1.<init>(r14, r14)
            r13[r0] = r1
            int r0 = r0 + 1
            goto L_0x003f
        L_0x004c:
            r0 = 0
        L_0x004d:
            r15 = 1
            if (r0 >= r10) goto L_0x0080
            r1 = r11[r0]
            r1.m = r15
            r1 = r11[r0]
            r1.i = r12
            r1 = r11[r0]
            if (r0 != 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r15 = 0
        L_0x005e:
            r1.n = r15
            r1 = r11[r0]
            boolean r1 = r1.m
            if (r1 == 0) goto L_0x0072
            r1 = r11[r0]
            boolean r1 = r1.i
            if (r1 != 0) goto L_0x0072
            r1 = r13[r0]
            r1.set(r14, r14)
            goto L_0x007d
        L_0x0072:
            if (r0 <= 0) goto L_0x007d
            r1 = r13[r0]
            int r2 = r0 + -1
            r2 = r13[r2]
            r1.set(r2)
        L_0x007d:
            int r0 = r0 + 1
            goto L_0x004d
        L_0x0080:
            java.lang.String[] r6 = new java.lang.String[r10]
            java.lang.String[] r5 = new java.lang.String[r10]
            java.lang.String[] r4 = new java.lang.String[r10]
            java.lang.String[] r3 = new java.lang.String[r10]
            com.horcrux.svg.TextLayoutAlgorithm$1CharacterPositioningResolver r0 = new com.horcrux.svg.TextLayoutAlgorithm$1CharacterPositioningResolver
            r1 = r30
            r2 = r11
            r16 = r3
            r3 = r6
            r17 = r4
            r4 = r5
            r18 = r5
            r5 = r17
            r17 = r6
            r6 = r16
            r0.<init>(r2, r3, r4, r5, r6)
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r14, r14)
            r1 = 0
        L_0x00a4:
            if (r1 >= r10) goto L_0x00f3
            r2 = r17[r1]
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00b4
            java.lang.String r2 = "0"
            r17[r1] = r2
        L_0x00b4:
            r2 = r18[r1]
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00c2
            java.lang.String r2 = "0"
            r18[r1] = r2
        L_0x00c2:
            float r2 = r0.x
            r3 = r17[r1]
            float r3 = java.lang.Float.parseFloat(r3)
            float r2 = r2 + r3
            r0.x = r2
            float r2 = r0.y
            r3 = r18[r1]
            float r3 = java.lang.Float.parseFloat(r3)
            float r2 = r2 + r3
            r0.y = r2
            r2 = r11[r1]
            r3 = r13[r1]
            float r3 = r3.x
            float r4 = r0.x
            float r3 = r3 + r4
            double r3 = (double) r3
            r2.b = r3
            r2 = r11[r1]
            r3 = r13[r1]
            float r3 = r3.y
            float r4 = r0.y
            float r3 = r3 + r4
            double r3 = (double) r3
            r2.c = r3
            int r1 = r1 + 1
            goto L_0x00a4
        L_0x00f3:
            com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver r1 = new com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver
            r1.<init>(r11)
            r1.a(r8)
            r0.set(r14, r14)
            r1 = 1
        L_0x00ff:
            if (r1 >= r10) goto L_0x0158
            r2 = r17[r1]
            if (r2 == 0) goto L_0x0113
            r2 = r17[r1]
            double r2 = java.lang.Double.parseDouble(r2)
            r4 = r11[r1]
            double r4 = r4.b
            double r2 = r2 - r4
            float r2 = (float) r2
            r0.x = r2
        L_0x0113:
            r2 = r18[r1]
            if (r2 == 0) goto L_0x0125
            r2 = r18[r1]
            double r2 = java.lang.Double.parseDouble(r2)
            r4 = r11[r1]
            double r4 = r4.c
            double r2 = r2 - r4
            float r2 = (float) r2
            r0.y = r2
        L_0x0125:
            r2 = r11[r1]
            double r3 = r2.b
            float r5 = r0.x
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            double r3 = r3 + r5
            r2.b = r3
            r2 = r11[r1]
            double r3 = r2.c
            float r5 = r0.y
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            double r3 = r3 + r5
            r2.c = r3
            r2 = r11[r1]
            boolean r2 = r2.i
            if (r2 == 0) goto L_0x014f
            r2 = r11[r1]
            boolean r2 = r2.n
            if (r2 == 0) goto L_0x014f
            r2 = r11[r1]
            r2.n = r12
        L_0x014f:
            int r1 = r1 + 1
            if (r1 >= r10) goto L_0x00ff
            r2 = r11[r1]
            r2.n = r15
            goto L_0x00ff
        L_0x0158:
            r4 = 0
            r5 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r8 = 0
            r16 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r18 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r20 = -4503599627370496(0xfff0000000000000, double:-Infinity)
        L_0x0162:
            r22 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r4 >= r10) goto L_0x0210
            r13 = r11[r4]
            boolean r13 = r13.m
            if (r13 != 0) goto L_0x016e
            goto L_0x0209
        L_0x016e:
            r13 = r11[r4]
            boolean r13 = r13.n
            if (r13 == 0) goto L_0x017b
            r18 = r5
            r0 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r5 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x017f
        L_0x017b:
            r0 = r16
            r16 = r20
        L_0x017f:
            r13 = r11[r4]
            double r12 = r13.b
            r15 = r11[r4]
            double r14 = r15.d
            double r14 = r14 + r12
            double r2 = java.lang.Math.min(r12, r14)
            double r2 = java.lang.Math.min(r5, r2)
            double r5 = java.lang.Math.max(r12, r14)
            double r0 = java.lang.Math.max(r0, r5)
            if (r4 <= 0) goto L_0x01a7
            r5 = r11[r4]
            boolean r5 = r5.n
            if (r5 == 0) goto L_0x01a7
            r5 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            int r12 = (r18 > r5 ? 1 : (r18 == r5 ? 0 : -1))
            if (r12 != 0) goto L_0x01ad
            goto L_0x01a9
        L_0x01a7:
            r5 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
        L_0x01a9:
            int r12 = r10 + -1
            if (r4 != r12) goto L_0x0204
        L_0x01ad:
            com.horcrux.svg.TextProperties$TextAnchor r12 = com.horcrux.svg.TextProperties.TextAnchor.start
            com.horcrux.svg.TextProperties$Direction r13 = com.horcrux.svg.TextProperties.Direction.ltr
            int r14 = r10 + -1
            if (r4 != r14) goto L_0x01b9
            r16 = r0
            r18 = r2
        L_0x01b9:
            r15 = r11[r8]
            double r5 = r15.b
            int[] r15 = com.horcrux.svg.TextLayoutAlgorithm.AnonymousClass1.f5832a
            int r12 = r12.ordinal()
            r12 = r15[r12]
            switch(r12) {
                case 1: goto L_0x01e5;
                case 2: goto L_0x01d4;
                case 3: goto L_0x01c9;
                default: goto L_0x01c8;
            }
        L_0x01c8:
            goto L_0x01ef
        L_0x01c9:
            com.horcrux.svg.TextProperties$Direction r12 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r13 != r12) goto L_0x01d0
            double r5 = r5 - r16
            goto L_0x01ef
        L_0x01d0:
            r12 = 0
            double r5 = r5 - r18
            goto L_0x01ef
        L_0x01d4:
            com.horcrux.svg.TextProperties$Direction r12 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r13 != r12) goto L_0x01de
            double r12 = r18 + r16
            double r12 = r12 / r22
            double r5 = r5 - r12
            goto L_0x01ef
        L_0x01de:
            r12 = 0
            double r12 = r18 + r16
            double r12 = r12 / r22
            double r5 = r5 - r12
            goto L_0x01ef
        L_0x01e5:
            com.horcrux.svg.TextProperties$Direction r12 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r13 != r12) goto L_0x01ec
            double r5 = r5 - r18
            goto L_0x01ef
        L_0x01ec:
            r12 = 0
            double r5 = r5 - r16
        L_0x01ef:
            if (r4 != r14) goto L_0x01f3
            r12 = r4
            goto L_0x01f5
        L_0x01f3:
            int r12 = r4 + -1
        L_0x01f5:
            if (r8 > r12) goto L_0x0201
            r13 = r11[r8]
            double r14 = r13.b
            double r14 = r14 + r5
            r13.b = r14
            int r8 = r8 + 1
            goto L_0x01f5
        L_0x0201:
            r5 = r2
            r8 = r4
            goto L_0x0205
        L_0x0204:
            r5 = r2
        L_0x0205:
            r20 = r16
            r16 = r0
        L_0x0209:
            int r4 = r4 + 1
            r12 = 0
            r14 = 0
            r15 = 1
            goto L_0x0162
        L_0x0210:
            android.graphics.PointF r0 = new android.graphics.PointF
            r1 = 0
            r0.<init>(r1, r1)
            android.graphics.PathMeasure r1 = new android.graphics.PathMeasure
            r1.<init>()
            r2 = 0
            r4 = r2
            r3 = 0
            r5 = 0
            r15 = 0
        L_0x0220:
            if (r3 >= r10) goto L_0x03d1
            java.lang.Object r6 = r9.get(r3)
            com.horcrux.svg.TextPathView r6 = (com.horcrux.svg.TextPathView) r6
            if (r6 == 0) goto L_0x0371
            r13 = r11[r3]
            boolean r13 = r13.m
            if (r13 == 0) goto L_0x0371
            android.graphics.Path r4 = r6.a(r2, r2)
            r5 = r11[r3]
            boolean r5 = r5.i
            if (r5 != 0) goto L_0x034b
            r6.g()
            com.horcrux.svg.TextProperties$TextPathSide r5 = com.horcrux.svg.TextProperties.TextPathSide.right
            r5 = 0
            r1.setPath(r4, r5)
            float r5 = r1.getLength()
            double r13 = (double) r5
            com.horcrux.svg.SVGLength r5 = r6.i()
            r24 = r13
            double r12 = r5.f5825a
            r5 = r11[r3]
            r26 = r4
            double r4 = r5.d
            r14 = r11[r3]
            r27 = r9
            r28 = r10
            double r9 = r14.b
            r14 = r11[r3]
            r29 = r15
            double r14 = r14.c
            r14 = r11[r3]
            double r14 = r14.f
            double r4 = r4 / r22
            double r9 = r9 + r4
            double r9 = r9 + r12
            boolean r4 = r1.isClosed()
            r12 = 0
            if (r4 != 0) goto L_0x0281
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 < 0) goto L_0x027c
            int r4 = (r9 > r24 ? 1 : (r9 == r24 ? 0 : -1))
            if (r4 <= 0) goto L_0x0281
        L_0x027c:
            r4 = r11[r3]
            r5 = 1
            r4.h = r5
        L_0x0281:
            boolean r4 = r1.isClosed()
            if (r4 == 0) goto L_0x0305
            com.horcrux.svg.TextProperties$TextAnchor r4 = com.horcrux.svg.TextProperties.TextAnchor.start
            com.horcrux.svg.TextProperties$Direction r5 = com.horcrux.svg.TextProperties.Direction.ltr
            r14 = r11[r8]
            double r14 = r14.b
            int[] r14 = com.horcrux.svg.TextLayoutAlgorithm.AnonymousClass1.f5832a
            int r4 = r4.ordinal()
            r4 = r14[r4]
            switch(r4) {
                case 1: goto L_0x02df;
                case 2: goto L_0x02c4;
                case 3: goto L_0x029c;
                default: goto L_0x029a;
            }
        L_0x029a:
            goto L_0x0305
        L_0x029c:
            com.horcrux.svg.TextProperties$Direction r4 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r5 != r4) goto L_0x02b4
            java.lang.Double.isNaN(r24)
            r14 = r24
            double r4 = -r14
            int r16 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r16 < 0) goto L_0x02ae
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0307
        L_0x02ae:
            r4 = r11[r3]
            r5 = 1
            r4.h = r5
            goto L_0x0307
        L_0x02b4:
            r14 = r24
            r5 = 1
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 < 0) goto L_0x02bf
            int r4 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x0307
        L_0x02bf:
            r4 = r11[r3]
            r4.h = r5
            goto L_0x0307
        L_0x02c4:
            r14 = r24
            java.lang.Double.isNaN(r14)
            double r4 = -r14
            double r4 = r4 / r22
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 < 0) goto L_0x02d9
            java.lang.Double.isNaN(r14)
            double r4 = r14 / r22
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 <= 0) goto L_0x0307
        L_0x02d9:
            r4 = r11[r3]
            r5 = 1
            r4.h = r5
            goto L_0x0307
        L_0x02df:
            r14 = r24
            com.horcrux.svg.TextProperties$Direction r4 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r5 != r4) goto L_0x02f3
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 < 0) goto L_0x02ed
            int r4 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x0307
        L_0x02ed:
            r4 = r11[r3]
            r5 = 1
            r4.h = r5
            goto L_0x0307
        L_0x02f3:
            java.lang.Double.isNaN(r14)
            double r4 = -r14
            int r16 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r16 < 0) goto L_0x02ff
            int r4 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0307
        L_0x02ff:
            r4 = r11[r3]
            r5 = 1
            r4.h = r5
            goto L_0x0307
        L_0x0305:
            r14 = r24
        L_0x0307:
            java.lang.Double.isNaN(r14)
            double r9 = r9 % r14
            r4 = r11[r3]
            boolean r4 = r4.h
            if (r4 != 0) goto L_0x036d
            r4 = 2
            float[] r5 = new float[r4]
            float[] r12 = new float[r4]
            float r4 = (float) r9
            r1.getPosTan(r4, r5, r12)
            r4 = 1
            r5 = r12[r4]
            double r4 = (double) r5
            r9 = 0
            r10 = r12[r9]
            double r12 = (double) r10
            double r4 = java.lang.Math.atan2(r4, r12)
            r12 = 4633260481411531256(0x404ca5dc1a63c1f8, double:57.29577951308232)
            double r4 = r4 * r12
            r12 = 4636033603912859648(0x4056800000000000, double:90.0)
            double r12 = r12 + r4
            r10 = 2
            double[] r14 = new double[r10]
            double r15 = java.lang.Math.cos(r12)
            r14[r9] = r15
            double r9 = java.lang.Math.sin(r12)
            r12 = 1
            r14[r12] = r9
            r9 = r11[r3]
            double r12 = r9.f
            double r12 = r12 + r4
            r9.f = r12
            goto L_0x036d
        L_0x034b:
            r26 = r4
            r27 = r9
            r28 = r10
            r29 = r15
            r4 = r11[r3]
            int r5 = r3 + -1
            r9 = r11[r5]
            double r9 = r9.b
            r4.b = r9
            r4 = r11[r3]
            r9 = r11[r5]
            double r9 = r9.c
            r4.c = r9
            r4 = r11[r3]
            r5 = r11[r5]
            double r9 = r5.f
            r4.f = r9
        L_0x036d:
            r4 = r26
            r12 = 1
            goto L_0x0378
        L_0x0371:
            r27 = r9
            r28 = r10
            r29 = r15
            r12 = r5
        L_0x0378:
            if (r6 != 0) goto L_0x03c4
            r5 = r11[r3]
            boolean r5 = r5.m
            if (r5 == 0) goto L_0x03c4
            if (r12 == 0) goto L_0x039c
            r5 = 0
            r1.setPath(r4, r5)
            r6 = 2
            float[] r6 = new float[r6]
            float r9 = r1.getLength()
            r1.getPosTan(r9, r6, r2)
            r9 = r6[r5]
            r15 = 1
            r6 = r6[r15]
            r0.set(r9, r6)
            r12 = 0
            r29 = 1
            goto L_0x039e
        L_0x039c:
            r5 = 0
            r15 = 1
        L_0x039e:
            if (r29 == 0) goto L_0x03c6
            r6 = r11[r3]
            boolean r6 = r6.n
            if (r6 == 0) goto L_0x03a9
            r29 = 0
            goto L_0x03c6
        L_0x03a9:
            r6 = r11[r3]
            double r9 = r6.b
            float r13 = r0.x
            double r13 = (double) r13
            java.lang.Double.isNaN(r13)
            double r9 = r9 + r13
            r6.b = r9
            r6 = r11[r3]
            double r9 = r6.c
            float r13 = r0.y
            double r13 = (double) r13
            java.lang.Double.isNaN(r13)
            double r9 = r9 + r13
            r6.c = r9
            goto L_0x03c6
        L_0x03c4:
            r5 = 0
            r15 = 1
        L_0x03c6:
            int r3 = r3 + 1
            r5 = r12
            r9 = r27
            r10 = r28
            r15 = r29
            goto L_0x0220
        L_0x03d1:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.a(com.horcrux.svg.TextLayoutAlgorithm$LayoutInput):com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[]");
    }
}
