package com.airbnb.lottie.parser;

class ShapeStrokeParser {
    private ShapeStrokeParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0096 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0162  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ShapeStroke parse(android.util.JsonReader r16, com.airbnb.lottie.LottieComposition r17) throws java.io.IOException {
        /*
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1 = 0
            r1 = 0
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x000e:
            boolean r10 = r16.hasNext()
            if (r10 == 0) goto L_0x0168
            java.lang.String r10 = r16.nextName()
            int r11 = r10.hashCode()
            r13 = 111(0x6f, float:1.56E-43)
            r0 = 1
            if (r11 == r13) goto L_0x0080
            r12 = 119(0x77, float:1.67E-43)
            if (r11 == r12) goto L_0x0075
            r12 = 3447(0xd77, float:4.83E-42)
            if (r11 == r12) goto L_0x006b
            r12 = 3454(0xd7e, float:4.84E-42)
            if (r11 == r12) goto L_0x0061
            r12 = 3487(0xd9f, float:4.886E-42)
            if (r11 == r12) goto L_0x0057
            r12 = 3519(0xdbf, float:4.931E-42)
            if (r11 == r12) goto L_0x004d
            switch(r11) {
                case 99: goto L_0x0043;
                case 100: goto L_0x0039;
                default: goto L_0x0038;
            }
        L_0x0038:
            goto L_0x008a
        L_0x0039:
            java.lang.String r11 = "d"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 7
            goto L_0x008b
        L_0x0043:
            java.lang.String r11 = "c"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 1
            goto L_0x008b
        L_0x004d:
            java.lang.String r11 = "nm"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 0
            goto L_0x008b
        L_0x0057:
            java.lang.String r11 = "ml"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 6
            goto L_0x008b
        L_0x0061:
            java.lang.String r11 = "lj"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 5
            goto L_0x008b
        L_0x006b:
            java.lang.String r11 = "lc"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 4
            goto L_0x008b
        L_0x0075:
            java.lang.String r11 = "w"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 2
            goto L_0x008b
        L_0x0080:
            java.lang.String r11 = "o"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x008a
            r12 = 3
            goto L_0x008b
        L_0x008a:
            r12 = -1
        L_0x008b:
            switch(r12) {
                case 0: goto L_0x0162;
                case 1: goto L_0x015c;
                case 2: goto L_0x0156;
                case 3: goto L_0x0150;
                case 4: goto L_0x0143;
                case 5: goto L_0x0136;
                case 6: goto L_0x012f;
                case 7: goto L_0x0093;
                default: goto L_0x008e;
            }
        L_0x008e:
            r16.skipValue()
            goto L_0x000e
        L_0x0093:
            r16.beginArray()
        L_0x0096:
            boolean r10 = r16.hasNext()
            if (r10 == 0) goto L_0x011c
            r16.beginObject()
            r10 = 0
            r11 = 0
        L_0x00a1:
            boolean r12 = r16.hasNext()
            if (r12 == 0) goto L_0x00df
            java.lang.String r12 = r16.nextName()
            int r14 = r12.hashCode()
            r15 = 110(0x6e, float:1.54E-43)
            if (r14 == r15) goto L_0x00c3
            r15 = 118(0x76, float:1.65E-43)
            if (r14 == r15) goto L_0x00b8
            goto L_0x00cd
        L_0x00b8:
            java.lang.String r14 = "v"
            boolean r12 = r12.equals(r14)
            if (r12 == 0) goto L_0x00cd
            r12 = 1
            goto L_0x00ce
        L_0x00c3:
            java.lang.String r14 = "n"
            boolean r12 = r12.equals(r14)
            if (r12 == 0) goto L_0x00cd
            r12 = 0
            goto L_0x00ce
        L_0x00cd:
            r12 = -1
        L_0x00ce:
            switch(r12) {
                case 0: goto L_0x00da;
                case 1: goto L_0x00d5;
                default: goto L_0x00d1;
            }
        L_0x00d1:
            r16.skipValue()
            goto L_0x00a1
        L_0x00d5:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r11 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r16, r17)
            goto L_0x00a1
        L_0x00da:
            java.lang.String r10 = r16.nextString()
            goto L_0x00a1
        L_0x00df:
            r16.endObject()
            int r12 = r10.hashCode()
            r14 = 100
            if (r12 == r14) goto L_0x0105
            r14 = 103(0x67, float:1.44E-43)
            if (r12 == r14) goto L_0x00fb
            if (r12 == r13) goto L_0x00f1
            goto L_0x010f
        L_0x00f1:
            java.lang.String r12 = "o"
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x010f
            r10 = 0
            goto L_0x0110
        L_0x00fb:
            java.lang.String r12 = "g"
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x010f
            r10 = 2
            goto L_0x0110
        L_0x0105:
            java.lang.String r12 = "d"
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x010f
            r10 = 1
            goto L_0x0110
        L_0x010f:
            r10 = -1
        L_0x0110:
            switch(r10) {
                case 0: goto L_0x0119;
                case 1: goto L_0x0114;
                case 2: goto L_0x0114;
                default: goto L_0x0113;
            }
        L_0x0113:
            goto L_0x0096
        L_0x0114:
            r3.add(r11)
            goto L_0x0096
        L_0x0119:
            r2 = r11
            goto L_0x0096
        L_0x011c:
            r16.endArray()
            int r10 = r3.size()
            if (r10 != r0) goto L_0x000e
            r0 = 0
            java.lang.Object r0 = r3.get(r0)
            r3.add(r0)
            goto L_0x000e
        L_0x012f:
            double r9 = r16.nextDouble()
            float r9 = (float) r9
            goto L_0x000e
        L_0x0136:
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType[] r8 = com.airbnb.lottie.model.content.ShapeStroke.LineJoinType.values()
            int r10 = r16.nextInt()
            int r10 = r10 - r0
            r8 = r8[r10]
            goto L_0x000e
        L_0x0143:
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType[] r7 = com.airbnb.lottie.model.content.ShapeStroke.LineCapType.values()
            int r10 = r16.nextInt()
            int r10 = r10 - r0
            r7 = r7[r10]
            goto L_0x000e
        L_0x0150:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r5 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r16, r17)
            goto L_0x000e
        L_0x0156:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r16, r17)
            goto L_0x000e
        L_0x015c:
            com.airbnb.lottie.model.animatable.AnimatableColorValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseColor(r16, r17)
            goto L_0x000e
        L_0x0162:
            java.lang.String r1 = r16.nextString()
            goto L_0x000e
        L_0x0168:
            com.airbnb.lottie.model.content.ShapeStroke r10 = new com.airbnb.lottie.model.content.ShapeStroke
            r0 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ShapeStrokeParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ShapeStroke");
    }
}
