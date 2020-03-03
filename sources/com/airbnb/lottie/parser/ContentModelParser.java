package com.airbnb.lottie.parser;

class ContentModelParser {
    private ContentModelParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b5, code lost:
        if (r2.equals("gs") != false) goto L_0x00e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0042 A[SYNTHETIC] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ContentModel parse(android.util.JsonReader r9, com.airbnb.lottie.LottieComposition r10) throws java.io.IOException {
        /*
            r9.beginObject()
            r0 = 2
            r1 = 2
        L_0x0005:
            boolean r2 = r9.hasNext()
            r3 = 0
            r4 = 1
            r5 = -1
            r6 = 0
            if (r2 == 0) goto L_0x0047
            java.lang.String r2 = r9.nextName()
            int r7 = r2.hashCode()
            r8 = 100
            if (r7 == r8) goto L_0x002b
            r8 = 3717(0xe85, float:5.209E-42)
            if (r7 == r8) goto L_0x0020
            goto L_0x0035
        L_0x0020:
            java.lang.String r7 = "ty"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x0035
            r2 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r7 = "d"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x0035
            r2 = 1
            goto L_0x0036
        L_0x0035:
            r2 = -1
        L_0x0036:
            switch(r2) {
                case 0: goto L_0x0042;
                case 1: goto L_0x003d;
                default: goto L_0x0039;
            }
        L_0x0039:
            r9.skipValue()
            goto L_0x0005
        L_0x003d:
            int r1 = r9.nextInt()
            goto L_0x0005
        L_0x0042:
            java.lang.String r2 = r9.nextString()
            goto L_0x0048
        L_0x0047:
            r2 = r6
        L_0x0048:
            if (r2 != 0) goto L_0x004b
            return r6
        L_0x004b:
            int r7 = r2.hashCode()
            switch(r7) {
                case 3239: goto L_0x00d6;
                case 3270: goto L_0x00cc;
                case 3295: goto L_0x00c2;
                case 3307: goto L_0x00b8;
                case 3308: goto L_0x00af;
                case 3488: goto L_0x00a4;
                case 3633: goto L_0x0099;
                case 3646: goto L_0x008e;
                case 3669: goto L_0x0084;
                case 3679: goto L_0x0079;
                case 3681: goto L_0x006d;
                case 3705: goto L_0x0060;
                case 3710: goto L_0x0054;
                default: goto L_0x0052;
            }
        L_0x0052:
            goto L_0x00e0
        L_0x0054:
            java.lang.String r0 = "tr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 5
            goto L_0x00e1
        L_0x0060:
            java.lang.String r0 = "tm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 9
            goto L_0x00e1
        L_0x006d:
            java.lang.String r0 = "st"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 1
            goto L_0x00e1
        L_0x0079:
            java.lang.String r0 = "sr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 10
            goto L_0x00e1
        L_0x0084:
            java.lang.String r0 = "sh"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 6
            goto L_0x00e1
        L_0x008e:
            java.lang.String r0 = "rp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 12
            goto L_0x00e1
        L_0x0099:
            java.lang.String r0 = "rc"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 8
            goto L_0x00e1
        L_0x00a4:
            java.lang.String r0 = "mm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 11
            goto L_0x00e1
        L_0x00af:
            java.lang.String r3 = "gs"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00e0
            goto L_0x00e1
        L_0x00b8:
            java.lang.String r0 = "gr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 0
            goto L_0x00e1
        L_0x00c2:
            java.lang.String r0 = "gf"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 4
            goto L_0x00e1
        L_0x00cc:
            java.lang.String r0 = "fl"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 3
            goto L_0x00e1
        L_0x00d6:
            java.lang.String r0 = "el"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 7
            goto L_0x00e1
        L_0x00e0:
            r0 = -1
        L_0x00e1:
            switch(r0) {
                case 0: goto L_0x013c;
                case 1: goto L_0x0137;
                case 2: goto L_0x0132;
                case 3: goto L_0x012d;
                case 4: goto L_0x0128;
                case 5: goto L_0x0123;
                case 6: goto L_0x011e;
                case 7: goto L_0x0119;
                case 8: goto L_0x0114;
                case 9: goto L_0x010f;
                case 10: goto L_0x010a;
                case 11: goto L_0x0100;
                case 12: goto L_0x00fb;
                default: goto L_0x00e4;
            }
        L_0x00e4:
            java.lang.String r10 = "LOTTIE"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown shape type "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r10, r0)
            goto L_0x0140
        L_0x00fb:
            com.airbnb.lottie.model.content.Repeater r6 = com.airbnb.lottie.parser.RepeaterParser.parse(r9, r10)
            goto L_0x0140
        L_0x0100:
            com.airbnb.lottie.model.content.MergePaths r6 = com.airbnb.lottie.parser.MergePathsParser.parse(r9)
            java.lang.String r0 = "Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove()."
            r10.addWarning(r0)
            goto L_0x0140
        L_0x010a:
            com.airbnb.lottie.model.content.PolystarShape r6 = com.airbnb.lottie.parser.PolystarShapeParser.parse(r9, r10)
            goto L_0x0140
        L_0x010f:
            com.airbnb.lottie.model.content.ShapeTrimPath r6 = com.airbnb.lottie.parser.ShapeTrimPathParser.parse(r9, r10)
            goto L_0x0140
        L_0x0114:
            com.airbnb.lottie.model.content.RectangleShape r6 = com.airbnb.lottie.parser.RectangleShapeParser.parse(r9, r10)
            goto L_0x0140
        L_0x0119:
            com.airbnb.lottie.model.content.CircleShape r6 = com.airbnb.lottie.parser.CircleShapeParser.parse(r9, r10, r1)
            goto L_0x0140
        L_0x011e:
            com.airbnb.lottie.model.content.ShapePath r6 = com.airbnb.lottie.parser.ShapePathParser.parse(r9, r10)
            goto L_0x0140
        L_0x0123:
            com.airbnb.lottie.model.animatable.AnimatableTransform r6 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r9, r10)
            goto L_0x0140
        L_0x0128:
            com.airbnb.lottie.model.content.GradientFill r6 = com.airbnb.lottie.parser.GradientFillParser.parse(r9, r10)
            goto L_0x0140
        L_0x012d:
            com.airbnb.lottie.model.content.ShapeFill r6 = com.airbnb.lottie.parser.ShapeFillParser.parse(r9, r10)
            goto L_0x0140
        L_0x0132:
            com.airbnb.lottie.model.content.GradientStroke r6 = com.airbnb.lottie.parser.GradientStrokeParser.parse(r9, r10)
            goto L_0x0140
        L_0x0137:
            com.airbnb.lottie.model.content.ShapeStroke r6 = com.airbnb.lottie.parser.ShapeStrokeParser.parse(r9, r10)
            goto L_0x0140
        L_0x013c:
            com.airbnb.lottie.model.content.ShapeGroup r6 = com.airbnb.lottie.parser.ShapeGroupParser.parse(r9, r10)
        L_0x0140:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x014a
            r9.skipValue()
            goto L_0x0140
        L_0x014a:
            r9.endObject()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ContentModelParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ContentModel");
    }
}
