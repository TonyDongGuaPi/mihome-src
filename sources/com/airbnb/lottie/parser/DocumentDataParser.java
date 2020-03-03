package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.DocumentData;

public class DocumentDataParser implements ValueParser<DocumentData> {
    public static final DocumentDataParser INSTANCE = new DocumentDataParser();

    private DocumentDataParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.DocumentData parse(android.util.JsonReader r22, float r23) throws java.io.IOException {
        /*
            r21 = this;
            r22.beginObject()
            r0 = 1
            r1 = 0
            r2 = 0
            r4 = 0
            r6 = r1
            r7 = r6
            r8 = r2
            r12 = r8
            r14 = r12
            r18 = r14
            r10 = 0
            r11 = 0
            r16 = 0
            r17 = 0
            r20 = 1
        L_0x0017:
            boolean r1 = r22.hasNext()
            if (r1 == 0) goto L_0x011e
            java.lang.String r1 = r22.nextName()
            r2 = -1
            int r3 = r1.hashCode()
            r5 = 102(0x66, float:1.43E-43)
            if (r3 == r5) goto L_0x00ba
            r5 = 106(0x6a, float:1.49E-43)
            if (r3 == r5) goto L_0x00b0
            r5 = 3261(0xcbd, float:4.57E-42)
            if (r3 == r5) goto L_0x00a6
            r5 = 3452(0xd7c, float:4.837E-42)
            if (r3 == r5) goto L_0x009c
            r5 = 3463(0xd87, float:4.853E-42)
            if (r3 == r5) goto L_0x0092
            r5 = 3543(0xdd7, float:4.965E-42)
            if (r3 == r5) goto L_0x0087
            r5 = 3664(0xe50, float:5.134E-42)
            if (r3 == r5) goto L_0x007c
            r5 = 3684(0xe64, float:5.162E-42)
            if (r3 == r5) goto L_0x0070
            r5 = 3710(0xe7e, float:5.199E-42)
            if (r3 == r5) goto L_0x0065
            switch(r3) {
                case 115: goto L_0x005b;
                case 116: goto L_0x004f;
                default: goto L_0x004d;
            }
        L_0x004d:
            goto L_0x00c4
        L_0x004f:
            java.lang.String r3 = "t"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 0
            goto L_0x00c5
        L_0x005b:
            java.lang.String r3 = "s"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 2
            goto L_0x00c5
        L_0x0065:
            java.lang.String r3 = "tr"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 4
            goto L_0x00c5
        L_0x0070:
            java.lang.String r3 = "sw"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 9
            goto L_0x00c5
        L_0x007c:
            java.lang.String r3 = "sc"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 8
            goto L_0x00c5
        L_0x0087:
            java.lang.String r3 = "of"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 10
            goto L_0x00c5
        L_0x0092:
            java.lang.String r3 = "ls"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 6
            goto L_0x00c5
        L_0x009c:
            java.lang.String r3 = "lh"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 5
            goto L_0x00c5
        L_0x00a6:
            java.lang.String r3 = "fc"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 7
            goto L_0x00c5
        L_0x00b0:
            java.lang.String r3 = "j"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 3
            goto L_0x00c5
        L_0x00ba:
            java.lang.String r3 = "f"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c4
            r1 = 1
            goto L_0x00c5
        L_0x00c4:
            r1 = -1
        L_0x00c5:
            switch(r1) {
                case 0: goto L_0x0117;
                case 1: goto L_0x0110;
                case 2: goto L_0x0109;
                case 3: goto L_0x0102;
                case 4: goto L_0x00fb;
                case 5: goto L_0x00f4;
                case 6: goto L_0x00ed;
                case 7: goto L_0x00e5;
                case 8: goto L_0x00dd;
                case 9: goto L_0x00d5;
                case 10: goto L_0x00cd;
                default: goto L_0x00c8;
            }
        L_0x00c8:
            r22.skipValue()
            goto L_0x0017
        L_0x00cd:
            boolean r1 = r22.nextBoolean()
            r20 = r1
            goto L_0x0017
        L_0x00d5:
            double r1 = r22.nextDouble()
            r18 = r1
            goto L_0x0017
        L_0x00dd:
            int r1 = com.airbnb.lottie.parser.JsonUtils.jsonToColor(r22)
            r17 = r1
            goto L_0x0017
        L_0x00e5:
            int r1 = com.airbnb.lottie.parser.JsonUtils.jsonToColor(r22)
            r16 = r1
            goto L_0x0017
        L_0x00ed:
            double r1 = r22.nextDouble()
            r14 = r1
            goto L_0x0017
        L_0x00f4:
            double r1 = r22.nextDouble()
            r12 = r1
            goto L_0x0017
        L_0x00fb:
            int r1 = r22.nextInt()
            r11 = r1
            goto L_0x0017
        L_0x0102:
            int r1 = r22.nextInt()
            r10 = r1
            goto L_0x0017
        L_0x0109:
            double r1 = r22.nextDouble()
            r8 = r1
            goto L_0x0017
        L_0x0110:
            java.lang.String r1 = r22.nextString()
            r7 = r1
            goto L_0x0017
        L_0x0117:
            java.lang.String r1 = r22.nextString()
            r6 = r1
            goto L_0x0017
        L_0x011e:
            r22.endObject()
            com.airbnb.lottie.model.DocumentData r0 = new com.airbnb.lottie.model.DocumentData
            r5 = r0
            r5.<init>(r6, r7, r8, r10, r11, r12, r14, r16, r17, r18, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.DocumentDataParser.parse(android.util.JsonReader, float):com.airbnb.lottie.model.DocumentData");
    }
}
