package com.airbnb.lottie.parser;

class FontCharacterParser {
    private FontCharacterParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.FontCharacter parse(android.util.JsonReader r13, com.airbnb.lottie.LottieComposition r14) throws java.io.IOException {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r13.beginObject()
            r0 = 0
            r2 = 0
            r4 = 0
            r9 = r0
            r10 = r9
            r5 = r2
            r7 = r5
            r2 = 0
        L_0x0011:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x00da
            java.lang.String r0 = r13.nextName()
            r3 = -1
            int r11 = r0.hashCode()
            r12 = -1866931350(0xffffffff90b8e36a, float:-7.292559E-29)
            if (r11 == r12) goto L_0x0071
            r12 = 119(0x77, float:1.67E-43)
            if (r11 == r12) goto L_0x0066
            r12 = 3173(0xc65, float:4.446E-42)
            if (r11 == r12) goto L_0x005c
            r12 = 3076010(0x2eefaa, float:4.310408E-39)
            if (r11 == r12) goto L_0x0052
            r12 = 3530753(0x35e001, float:4.947639E-39)
            if (r11 == r12) goto L_0x0048
            r12 = 109780401(0x68b1db1, float:5.2329616E-35)
            if (r11 == r12) goto L_0x003d
            goto L_0x007b
        L_0x003d:
            java.lang.String r11 = "style"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 3
            goto L_0x007c
        L_0x0048:
            java.lang.String r11 = "size"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 1
            goto L_0x007c
        L_0x0052:
            java.lang.String r11 = "data"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 5
            goto L_0x007c
        L_0x005c:
            java.lang.String r11 = "ch"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 0
            goto L_0x007c
        L_0x0066:
            java.lang.String r11 = "w"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 2
            goto L_0x007c
        L_0x0071:
            java.lang.String r11 = "fFamily"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x007b
            r0 = 4
            goto L_0x007c
        L_0x007b:
            r0 = -1
        L_0x007c:
            switch(r0) {
                case 0: goto L_0x00d0;
                case 1: goto L_0x00ca;
                case 2: goto L_0x00c4;
                case 3: goto L_0x00be;
                case 4: goto L_0x00b8;
                case 5: goto L_0x0083;
                default: goto L_0x007f;
            }
        L_0x007f:
            r13.skipValue()
            goto L_0x0011
        L_0x0083:
            r13.beginObject()
        L_0x0086:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x00b3
            java.lang.String r0 = "shapes"
            java.lang.String r3 = r13.nextName()
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00af
            r13.beginArray()
        L_0x009b:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x00ab
            com.airbnb.lottie.model.content.ContentModel r0 = com.airbnb.lottie.parser.ContentModelParser.parse(r13, r14)
            com.airbnb.lottie.model.content.ShapeGroup r0 = (com.airbnb.lottie.model.content.ShapeGroup) r0
            r1.add(r0)
            goto L_0x009b
        L_0x00ab:
            r13.endArray()
            goto L_0x0086
        L_0x00af:
            r13.skipValue()
            goto L_0x0086
        L_0x00b3:
            r13.endObject()
            goto L_0x0011
        L_0x00b8:
            java.lang.String r10 = r13.nextString()
            goto L_0x0011
        L_0x00be:
            java.lang.String r9 = r13.nextString()
            goto L_0x0011
        L_0x00c4:
            double r7 = r13.nextDouble()
            goto L_0x0011
        L_0x00ca:
            double r5 = r13.nextDouble()
            goto L_0x0011
        L_0x00d0:
            java.lang.String r0 = r13.nextString()
            char r2 = r0.charAt(r4)
            goto L_0x0011
        L_0x00da:
            r13.endObject()
            com.airbnb.lottie.model.FontCharacter r13 = new com.airbnb.lottie.model.FontCharacter
            r0 = r13
            r3 = r5
            r5 = r7
            r7 = r9
            r8 = r10
            r0.<init>(r1, r2, r3, r5, r7, r8)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.FontCharacterParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.FontCharacter");
    }
}
