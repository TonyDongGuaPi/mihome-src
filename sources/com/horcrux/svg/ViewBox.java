package com.horcrux.svg;

class ViewBox {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5838a = 0;
    private static final int b = 1;
    private static final int c = 2;

    ViewBox() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Matrix a(android.graphics.RectF r22, android.graphics.RectF r23, java.lang.String r24, int r25) {
        /*
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            float r4 = r0.left
            double r4 = (double) r4
            float r6 = r0.top
            double r6 = (double) r6
            float r8 = r22.width()
            double r8 = (double) r8
            float r0 = r22.height()
            double r10 = (double) r0
            float r0 = r1.left
            double r12 = (double) r0
            float r0 = r1.top
            double r14 = (double) r0
            float r0 = r23.width()
            double r0 = (double) r0
            float r2 = r23.height()
            double r2 = (double) r2
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r8)
            r16 = r14
            double r14 = r0 / r8
            java.lang.Double.isNaN(r2)
            java.lang.Double.isNaN(r10)
            r18 = r8
            double r8 = r2 / r10
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r14
            java.lang.Double.isNaN(r12)
            double r12 = r12 - r4
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            java.lang.Double.isNaN(r16)
            double r4 = r16 - r6
            r6 = 2
            r16 = r2
            r2 = r25
            if (r2 != r6) goto L_0x0095
            double r2 = java.lang.Math.min(r14, r8)
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0079
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            java.lang.Double.isNaN(r18)
            double r0 = r0 - r18
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 / r6
            double r12 = r12 - r0
            java.lang.Double.isNaN(r16)
            double r0 = r16 / r2
            java.lang.Double.isNaN(r10)
            double r0 = r0 - r10
            double r0 = r0 / r6
            double r4 = r4 - r0
            goto L_0x0092
        L_0x0079:
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            java.lang.Double.isNaN(r18)
            double r8 = r18 * r2
            java.lang.Double.isNaN(r0)
            double r0 = r0 - r8
            double r0 = r0 / r6
            double r12 = r12 - r0
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r2
            java.lang.Double.isNaN(r16)
            double r0 = r16 - r10
            double r0 = r0 / r6
            double r4 = r4 - r0
        L_0x0092:
            r14 = r2
            goto L_0x010d
        L_0x0095:
            java.lang.String r3 = "none"
            r6 = r24
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00a7
            if (r2 != 0) goto L_0x00a7
            double r14 = java.lang.Math.min(r14, r8)
        L_0x00a5:
            r2 = r14
            goto L_0x00b9
        L_0x00a7:
            java.lang.String r3 = "none"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00b7
            r3 = 1
            if (r2 != r3) goto L_0x00b7
            double r14 = java.lang.Math.max(r14, r8)
            goto L_0x00a5
        L_0x00b7:
            r2 = r14
            r14 = r8
        L_0x00b9:
            java.lang.String r7 = "xMid"
            boolean r7 = r6.contains(r7)
            if (r7 == 0) goto L_0x00d1
            java.lang.Double.isNaN(r18)
            double r8 = r18 * r2
            java.lang.Double.isNaN(r0)
            double r7 = r0 - r8
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r7 = r7 / r20
            double r12 = r12 + r7
        L_0x00d1:
            java.lang.String r7 = "xMax"
            boolean r7 = r6.contains(r7)
            if (r7 == 0) goto L_0x00e4
            java.lang.Double.isNaN(r18)
            double r8 = r18 * r2
            java.lang.Double.isNaN(r0)
            double r0 = r0 - r8
            double r12 = r12 + r0
        L_0x00e4:
            java.lang.String r0 = "YMid"
            boolean r0 = r6.contains(r0)
            if (r0 == 0) goto L_0x00fa
            java.lang.Double.isNaN(r10)
            double r0 = r10 * r14
            java.lang.Double.isNaN(r16)
            double r0 = r16 - r0
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 / r7
            double r4 = r4 + r0
        L_0x00fa:
            java.lang.String r0 = "YMax"
            boolean r0 = r6.contains(r0)
            if (r0 == 0) goto L_0x010d
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r14
            java.lang.Double.isNaN(r16)
            double r0 = r16 - r10
            double r4 = r4 + r0
        L_0x010d:
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            float r1 = (float) r12
            float r4 = (float) r4
            r0.postTranslate(r1, r4)
            float r1 = (float) r2
            float r2 = (float) r14
            r0.preScale(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.ViewBox.a(android.graphics.RectF, android.graphics.RectF, java.lang.String, int):android.graphics.Matrix");
    }
}
