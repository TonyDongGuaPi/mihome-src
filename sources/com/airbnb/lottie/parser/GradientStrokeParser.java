package com.airbnb.lottie.parser;

class GradientStrokeParser {
    private GradientStrokeParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.GradientStroke parse(android.util.JsonReader r19, com.airbnb.lottie.LottieComposition r20) throws java.io.IOException {
        /*
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r1 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
        L_0x0011:
            boolean r13 = r19.hasNext()
            if (r13 == 0) goto L_0x01e5
            java.lang.String r13 = r19.nextName()
            int r14 = r13.hashCode()
            r16 = -1
            switch(r14) {
                case 100: goto L_0x008f;
                case 101: goto L_0x0085;
                case 103: goto L_0x007b;
                case 111: goto L_0x0071;
                case 115: goto L_0x0067;
                case 116: goto L_0x005c;
                case 119: goto L_0x0051;
                case 3447: goto L_0x0047;
                case 3454: goto L_0x003c;
                case 3487: goto L_0x0031;
                case 3519: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x009a
        L_0x0026:
            java.lang.String r14 = "nm"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 0
            goto L_0x009b
        L_0x0031:
            java.lang.String r14 = "ml"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 9
            goto L_0x009b
        L_0x003c:
            java.lang.String r14 = "lj"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 8
            goto L_0x009b
        L_0x0047:
            java.lang.String r14 = "lc"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 7
            goto L_0x009b
        L_0x0051:
            java.lang.String r14 = "w"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 6
            goto L_0x009b
        L_0x005c:
            java.lang.String r14 = "t"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 3
            goto L_0x009b
        L_0x0067:
            java.lang.String r14 = "s"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 4
            goto L_0x009b
        L_0x0071:
            java.lang.String r14 = "o"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 2
            goto L_0x009b
        L_0x007b:
            java.lang.String r14 = "g"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 1
            goto L_0x009b
        L_0x0085:
            java.lang.String r14 = "e"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 5
            goto L_0x009b
        L_0x008f:
            java.lang.String r14 = "d"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x009a
            r13 = 10
            goto L_0x009b
        L_0x009a:
            r13 = -1
        L_0x009b:
            switch(r13) {
                case 0: goto L_0x01db;
                case 1: goto L_0x0181;
                case 2: goto L_0x0177;
                case 3: goto L_0x0169;
                case 4: goto L_0x0164;
                case 5: goto L_0x015f;
                case 6: goto L_0x015a;
                case 7: goto L_0x014d;
                case 8: goto L_0x0140;
                case 9: goto L_0x013a;
                case 10: goto L_0x00a7;
                default: goto L_0x009e;
            }
        L_0x009e:
            r0 = r19
            r13 = r20
            r19.skipValue()
            goto L_0x0011
        L_0x00a7:
            r19.beginArray()
        L_0x00aa:
            boolean r13 = r19.hasNext()
            if (r13 == 0) goto L_0x011e
            r19.beginObject()
            r13 = 0
            r14 = 0
        L_0x00b5:
            boolean r17 = r19.hasNext()
            if (r17 == 0) goto L_0x00f9
            java.lang.String r15 = r19.nextName()
            int r0 = r15.hashCode()
            r18 = r12
            r12 = 110(0x6e, float:1.54E-43)
            if (r0 == r12) goto L_0x00d9
            r12 = 118(0x76, float:1.65E-43)
            if (r0 == r12) goto L_0x00ce
            goto L_0x00e3
        L_0x00ce:
            java.lang.String r0 = "v"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00e3
            r0 = 1
            goto L_0x00e4
        L_0x00d9:
            java.lang.String r0 = "n"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00e3
            r0 = 0
            goto L_0x00e4
        L_0x00e3:
            r0 = -1
        L_0x00e4:
            switch(r0) {
                case 0: goto L_0x00f1;
                case 1: goto L_0x00eb;
                default: goto L_0x00e7;
            }
        L_0x00e7:
            r19.skipValue()
            goto L_0x00f6
        L_0x00eb:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r0 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r19, r20)
            r14 = r0
            goto L_0x00f6
        L_0x00f1:
            java.lang.String r0 = r19.nextString()
            r13 = r0
        L_0x00f6:
            r12 = r18
            goto L_0x00b5
        L_0x00f9:
            r18 = r12
            r19.endObject()
            java.lang.String r0 = "o"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0108
            r12 = r14
            goto L_0x00aa
        L_0x0108:
            java.lang.String r0 = "d"
            boolean r0 = r13.equals(r0)
            if (r0 != 0) goto L_0x0118
            java.lang.String r0 = "g"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x011b
        L_0x0118:
            r11.add(r14)
        L_0x011b:
            r12 = r18
            goto L_0x00aa
        L_0x011e:
            r18 = r12
            r19.endArray()
            int r0 = r11.size()
            r13 = 1
            if (r0 != r13) goto L_0x0132
            r0 = 0
            java.lang.Object r0 = r11.get(r0)
            r11.add(r0)
        L_0x0132:
            r0 = r19
            r13 = r20
            r12 = r18
            goto L_0x0011
        L_0x013a:
            double r13 = r19.nextDouble()
            float r10 = (float) r13
            goto L_0x017b
        L_0x0140:
            r13 = 1
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType[] r0 = com.airbnb.lottie.model.content.ShapeStroke.LineJoinType.values()
            int r9 = r19.nextInt()
            int r9 = r9 - r13
            r9 = r0[r9]
            goto L_0x017b
        L_0x014d:
            r13 = 1
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType[] r0 = com.airbnb.lottie.model.content.ShapeStroke.LineCapType.values()
            int r8 = r19.nextInt()
            int r8 = r8 - r13
            r8 = r0[r8]
            goto L_0x017b
        L_0x015a:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r7 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r19, r20)
            goto L_0x017b
        L_0x015f:
            com.airbnb.lottie.model.animatable.AnimatablePointValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parsePoint(r19, r20)
            goto L_0x017b
        L_0x0164:
            com.airbnb.lottie.model.animatable.AnimatablePointValue r5 = com.airbnb.lottie.parser.AnimatableValueParser.parsePoint(r19, r20)
            goto L_0x017b
        L_0x0169:
            r13 = 1
            int r0 = r19.nextInt()
            if (r0 != r13) goto L_0x0174
            com.airbnb.lottie.model.content.GradientType r0 = com.airbnb.lottie.model.content.GradientType.Linear
        L_0x0172:
            r2 = r0
            goto L_0x017b
        L_0x0174:
            com.airbnb.lottie.model.content.GradientType r0 = com.airbnb.lottie.model.content.GradientType.Radial
            goto L_0x0172
        L_0x0177:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r19, r20)
        L_0x017b:
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0181:
            r0 = 0
            r13 = 1
            r19.beginObject()
            r14 = -1
        L_0x0187:
            boolean r15 = r19.hasNext()
            if (r15 == 0) goto L_0x01d2
            java.lang.String r15 = r19.nextName()
            int r0 = r15.hashCode()
            r13 = 107(0x6b, float:1.5E-43)
            if (r0 == r13) goto L_0x01a8
            r13 = 112(0x70, float:1.57E-43)
            if (r0 == r13) goto L_0x019e
            goto L_0x01b2
        L_0x019e:
            java.lang.String r0 = "p"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x01b2
            r0 = 0
            goto L_0x01b3
        L_0x01a8:
            java.lang.String r0 = "k"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x01b2
            r0 = 1
            goto L_0x01b3
        L_0x01b2:
            r0 = -1
        L_0x01b3:
            switch(r0) {
                case 0: goto L_0x01c7;
                case 1: goto L_0x01be;
                default: goto L_0x01b6;
            }
        L_0x01b6:
            r0 = r19
            r13 = r20
            r19.skipValue()
            goto L_0x01cf
        L_0x01be:
            r0 = r19
            r13 = r20
            com.airbnb.lottie.model.animatable.AnimatableGradientColorValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseGradientColor(r0, r13, r14)
            goto L_0x01cf
        L_0x01c7:
            r0 = r19
            r13 = r20
            int r14 = r19.nextInt()
        L_0x01cf:
            r0 = 0
            r13 = 1
            goto L_0x0187
        L_0x01d2:
            r0 = r19
            r13 = r20
            r19.endObject()
            goto L_0x0011
        L_0x01db:
            r0 = r19
            r13 = r20
            java.lang.String r1 = r19.nextString()
            goto L_0x0011
        L_0x01e5:
            com.airbnb.lottie.model.content.GradientStroke r13 = new com.airbnb.lottie.model.content.GradientStroke
            r0 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.GradientStrokeParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.GradientStroke");
    }
}
