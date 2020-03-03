package com.airbnb.lottie.parser;

import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.Layer;
import java.util.Collections;
import java.util.List;

public class LayerParser {
    private LayerParser() {
    }

    public static Layer parse(LottieComposition lottieComposition) {
        Rect bounds = lottieComposition.getBounds();
        List emptyList = Collections.emptyList();
        Layer.LayerType layerType = Layer.LayerType.PreComp;
        List emptyList2 = Collections.emptyList();
        AnimatableTransform animatableTransform = r5;
        AnimatableTransform animatableTransform2 = new AnimatableTransform();
        return new Layer(emptyList, lottieComposition, "__container", -1, layerType, -1, (String) null, emptyList2, animatableTransform, 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), (AnimatableTextFrame) null, (AnimatableTextProperties) null, Collections.emptyList(), Layer.MatteType.None, (AnimatableFloatValue) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x026c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.model.layer.Layer parse(android.util.JsonReader r36, com.airbnb.lottie.LottieComposition r37) throws java.io.IOException {
        /*
            r7 = r37
            java.lang.String r0 = "UNSET"
            com.airbnb.lottie.model.layer.Layer$MatteType r1 = com.airbnb.lottie.model.layer.Layer.MatteType.None
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r36.beginObject()
            r2 = 0
            r11 = 0
            r3 = 0
            r4 = 0
            r12 = -1
            r30 = r1
            r14 = r2
            r20 = r14
            r21 = r20
            r28 = r21
            r29 = r28
            r31 = r29
            r16 = r4
            r18 = r12
            r1 = 0
            r15 = 1065353216(0x3f800000, float:1.0)
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r12 = r0
            r13 = r31
            r0 = 0
        L_0x003e:
            boolean r2 = r36.hasNext()
            if (r2 == 0) goto L_0x0327
            java.lang.String r2 = r36.nextName()
            int r4 = r2.hashCode()
            r5 = 1
            switch(r4) {
                case -995424086: goto L_0x0149;
                case -903568142: goto L_0x013d;
                case 104: goto L_0x0132;
                case 116: goto L_0x0126;
                case 119: goto L_0x011a;
                case 3177: goto L_0x010f;
                case 3233: goto L_0x0104;
                case 3367: goto L_0x00f9;
                case 3432: goto L_0x00ee;
                case 3519: goto L_0x00e3;
                case 3553: goto L_0x00d7;
                case 3664: goto L_0x00cc;
                case 3669: goto L_0x00c0;
                case 3679: goto L_0x00b3;
                case 3681: goto L_0x00a6;
                case 3684: goto L_0x009a;
                case 3705: goto L_0x008d;
                case 3712: goto L_0x0080;
                case 3717: goto L_0x0074;
                case 104415: goto L_0x0069;
                case 108390670: goto L_0x005e;
                case 1441620890: goto L_0x0052;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x0153
        L_0x0052:
            java.lang.String r4 = "masksProperties"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 10
            goto L_0x0154
        L_0x005e:
            java.lang.String r4 = "refId"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 2
            goto L_0x0154
        L_0x0069:
            java.lang.String r4 = "ind"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 1
            goto L_0x0154
        L_0x0074:
            java.lang.String r4 = "ty"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 3
            goto L_0x0154
        L_0x0080:
            java.lang.String r4 = "tt"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 9
            goto L_0x0154
        L_0x008d:
            java.lang.String r4 = "tm"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 20
            goto L_0x0154
        L_0x009a:
            java.lang.String r4 = "sw"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 5
            goto L_0x0154
        L_0x00a6:
            java.lang.String r4 = "st"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 15
            goto L_0x0154
        L_0x00b3:
            java.lang.String r4 = "sr"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 14
            goto L_0x0154
        L_0x00c0:
            java.lang.String r4 = "sh"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 6
            goto L_0x0154
        L_0x00cc:
            java.lang.String r4 = "sc"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 7
            goto L_0x0154
        L_0x00d7:
            java.lang.String r4 = "op"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 19
            goto L_0x0154
        L_0x00e3:
            java.lang.String r4 = "nm"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 0
            goto L_0x0154
        L_0x00ee:
            java.lang.String r4 = "ks"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 8
            goto L_0x0154
        L_0x00f9:
            java.lang.String r4 = "ip"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 18
            goto L_0x0154
        L_0x0104:
            java.lang.String r4 = "ef"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 13
            goto L_0x0154
        L_0x010f:
            java.lang.String r4 = "cl"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 21
            goto L_0x0154
        L_0x011a:
            java.lang.String r4 = "w"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 16
            goto L_0x0154
        L_0x0126:
            java.lang.String r4 = "t"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 12
            goto L_0x0154
        L_0x0132:
            java.lang.String r4 = "h"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 17
            goto L_0x0154
        L_0x013d:
            java.lang.String r4 = "shapes"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 11
            goto L_0x0154
        L_0x0149:
            java.lang.String r4 = "parent"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0153
            r2 = 4
            goto L_0x0154
        L_0x0153:
            r2 = -1
        L_0x0154:
            switch(r2) {
                case 0: goto L_0x031e;
                case 1: goto L_0x0314;
                case 2: goto L_0x030d;
                case 3: goto L_0x02f5;
                case 4: goto L_0x02eb;
                case 5: goto L_0x02da;
                case 6: goto L_0x02c9;
                case 7: goto L_0x02be;
                case 8: goto L_0x02b6;
                case 9: goto L_0x02a8;
                case 10: goto L_0x0290;
                case 11: goto L_0x0276;
                case 12: goto L_0x0217;
                case 13: goto L_0x01b9;
                case 14: goto L_0x01b0;
                case 15: goto L_0x01a5;
                case 16: goto L_0x0193;
                case 17: goto L_0x0181;
                case 18: goto L_0x0178;
                case 19: goto L_0x016f;
                case 20: goto L_0x0167;
                case 21: goto L_0x015e;
                default: goto L_0x0157;
            }
        L_0x0157:
            r2 = r36
            r36.skipValue()
            goto L_0x0324
        L_0x015e:
            java.lang.String r2 = r36.nextString()
            r13 = r2
            r2 = r36
            goto L_0x0324
        L_0x0167:
            r2 = r36
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r31 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r2, r7, r3)
            goto L_0x0324
        L_0x016f:
            r2 = r36
            double r4 = r36.nextDouble()
            float r1 = (float) r4
            goto L_0x0324
        L_0x0178:
            r2 = r36
            double r4 = r36.nextDouble()
            float r0 = (float) r4
            goto L_0x0324
        L_0x0181:
            r2 = r36
            int r4 = r36.nextInt()
            float r4 = (float) r4
            float r5 = com.airbnb.lottie.utils.Utils.dpScale()
            float r4 = r4 * r5
            int r4 = (int) r4
            r27 = r4
            goto L_0x0324
        L_0x0193:
            r2 = r36
            int r4 = r36.nextInt()
            float r4 = (float) r4
            float r5 = com.airbnb.lottie.utils.Utils.dpScale()
            float r4 = r4 * r5
            int r4 = (int) r4
            r26 = r4
            goto L_0x0324
        L_0x01a5:
            r2 = r36
            double r4 = r36.nextDouble()
            float r4 = (float) r4
            r25 = r4
            goto L_0x0324
        L_0x01b0:
            r2 = r36
            double r4 = r36.nextDouble()
            float r15 = (float) r4
            goto L_0x0324
        L_0x01b9:
            r2 = r36
            r36.beginArray()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x01c3:
            boolean r5 = r36.hasNext()
            if (r5 == 0) goto L_0x01fe
            r36.beginObject()
        L_0x01cc:
            boolean r5 = r36.hasNext()
            if (r5 == 0) goto L_0x01f9
            java.lang.String r5 = r36.nextName()
            int r3 = r5.hashCode()
            r6 = 3519(0xdbf, float:4.931E-42)
            if (r3 == r6) goto L_0x01df
            goto L_0x01e9
        L_0x01df:
            java.lang.String r3 = "nm"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01e9
            r3 = 0
            goto L_0x01ea
        L_0x01e9:
            r3 = -1
        L_0x01ea:
            if (r3 == 0) goto L_0x01f0
            r36.skipValue()
            goto L_0x01f7
        L_0x01f0:
            java.lang.String r3 = r36.nextString()
            r4.add(r3)
        L_0x01f7:
            r3 = 0
            goto L_0x01cc
        L_0x01f9:
            r36.endObject()
            r3 = 0
            goto L_0x01c3
        L_0x01fe:
            r36.endArray()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r7.addWarning(r3)
            goto L_0x0324
        L_0x0217:
            r2 = r36
            r36.beginObject()
        L_0x021c:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0271
            java.lang.String r3 = r36.nextName()
            int r4 = r3.hashCode()
            r6 = 97
            if (r4 == r6) goto L_0x023d
            r6 = 100
            if (r4 == r6) goto L_0x0233
            goto L_0x0247
        L_0x0233:
            java.lang.String r4 = "d"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0247
            r3 = 0
            goto L_0x0248
        L_0x023d:
            java.lang.String r4 = "a"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0247
            r3 = 1
            goto L_0x0248
        L_0x0247:
            r3 = -1
        L_0x0248:
            switch(r3) {
                case 0: goto L_0x026c;
                case 1: goto L_0x024f;
                default: goto L_0x024b;
            }
        L_0x024b:
            r36.skipValue()
            goto L_0x021c
        L_0x024f:
            r36.beginArray()
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x025e
            com.airbnb.lottie.model.animatable.AnimatableTextProperties r3 = com.airbnb.lottie.parser.AnimatableTextPropertiesParser.parse(r36, r37)
            r29 = r3
        L_0x025e:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0268
            r36.skipValue()
            goto L_0x025e
        L_0x0268:
            r36.endArray()
            goto L_0x021c
        L_0x026c:
            com.airbnb.lottie.model.animatable.AnimatableTextFrame r28 = com.airbnb.lottie.parser.AnimatableValueParser.parseDocumentData(r36, r37)
            goto L_0x021c
        L_0x0271:
            r36.endObject()
            goto L_0x0324
        L_0x0276:
            r2 = r36
            r36.beginArray()
        L_0x027b:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x028b
            com.airbnb.lottie.model.content.ContentModel r3 = com.airbnb.lottie.parser.ContentModelParser.parse(r36, r37)
            if (r3 == 0) goto L_0x027b
            r8.add(r3)
            goto L_0x027b
        L_0x028b:
            r36.endArray()
            goto L_0x0324
        L_0x0290:
            r2 = r36
            r36.beginArray()
        L_0x0295:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x02a3
            com.airbnb.lottie.model.content.Mask r3 = com.airbnb.lottie.parser.MaskParser.parse(r36, r37)
            r10.add(r3)
            goto L_0x0295
        L_0x02a3:
            r36.endArray()
            goto L_0x0324
        L_0x02a8:
            r2 = r36
            com.airbnb.lottie.model.layer.Layer$MatteType[] r3 = com.airbnb.lottie.model.layer.Layer.MatteType.values()
            int r4 = r36.nextInt()
            r30 = r3[r4]
            goto L_0x0324
        L_0x02b6:
            r2 = r36
            com.airbnb.lottie.model.animatable.AnimatableTransform r21 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r36, r37)
            goto L_0x0324
        L_0x02be:
            r2 = r36
            java.lang.String r3 = r36.nextString()
            int r24 = android.graphics.Color.parseColor(r3)
            goto L_0x0324
        L_0x02c9:
            r2 = r36
            int r3 = r36.nextInt()
            float r3 = (float) r3
            float r4 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r4
            int r3 = (int) r3
            r23 = r3
            goto L_0x0324
        L_0x02da:
            r2 = r36
            int r3 = r36.nextInt()
            float r3 = (float) r3
            float r4 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r4
            int r3 = (int) r3
            r22 = r3
            goto L_0x0324
        L_0x02eb:
            r2 = r36
            int r3 = r36.nextInt()
            long r3 = (long) r3
            r18 = r3
            goto L_0x0324
        L_0x02f5:
            r2 = r36
            int r3 = r36.nextInt()
            com.airbnb.lottie.model.layer.Layer$LayerType r4 = com.airbnb.lottie.model.layer.Layer.LayerType.Unknown
            int r4 = r4.ordinal()
            if (r3 >= r4) goto L_0x030a
            com.airbnb.lottie.model.layer.Layer$LayerType[] r4 = com.airbnb.lottie.model.layer.Layer.LayerType.values()
            r14 = r4[r3]
            goto L_0x0324
        L_0x030a:
            com.airbnb.lottie.model.layer.Layer$LayerType r14 = com.airbnb.lottie.model.layer.Layer.LayerType.Unknown
            goto L_0x0324
        L_0x030d:
            r2 = r36
            java.lang.String r20 = r36.nextString()
            goto L_0x0324
        L_0x0314:
            r2 = r36
            int r3 = r36.nextInt()
            long r3 = (long) r3
            r16 = r3
            goto L_0x0324
        L_0x031e:
            r2 = r36
            java.lang.String r12 = r36.nextString()
        L_0x0324:
            r3 = 0
            goto L_0x003e
        L_0x0327:
            r2 = r36
            r36.endObject()
            float r32 = r0 / r15
            float r33 = r1 / r15
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r0 = (r32 > r11 ? 1 : (r32 == r11 ? 0 : -1))
            if (r0 <= 0) goto L_0x035a
            com.airbnb.lottie.value.Keyframe r5 = new com.airbnb.lottie.value.Keyframe
            java.lang.Float r2 = java.lang.Float.valueOf(r11)
            java.lang.Float r3 = java.lang.Float.valueOf(r11)
            r4 = 0
            r34 = 0
            java.lang.Float r35 = java.lang.Float.valueOf(r32)
            r0 = r5
            r1 = r37
            r9 = r5
            r5 = r34
            r11 = r6
            r6 = r35
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            goto L_0x035b
        L_0x035a:
            r11 = r6
        L_0x035b:
            r0 = 0
            int r1 = (r33 > r0 ? 1 : (r33 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0361
            goto L_0x0367
        L_0x0361:
            float r0 = r37.getEndFrame()
            r33 = r0
        L_0x0367:
            com.airbnb.lottie.value.Keyframe r9 = new com.airbnb.lottie.value.Keyframe
            r0 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r2 = java.lang.Float.valueOf(r0)
            java.lang.Float r3 = java.lang.Float.valueOf(r0)
            r4 = 0
            java.lang.Float r6 = java.lang.Float.valueOf(r33)
            r0 = r9
            r1 = r37
            r5 = r32
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            com.airbnb.lottie.value.Keyframe r9 = new com.airbnb.lottie.value.Keyframe
            r0 = 0
            java.lang.Float r2 = java.lang.Float.valueOf(r0)
            java.lang.Float r3 = java.lang.Float.valueOf(r0)
            r0 = 2139095039(0x7f7fffff, float:3.4028235E38)
            java.lang.Float r6 = java.lang.Float.valueOf(r0)
            r0 = r9
            r5 = r33
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            java.lang.String r0 = ".ai"
            boolean r0 = r12.endsWith(r0)
            if (r0 != 0) goto L_0x03ae
            java.lang.String r0 = "ai"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x03b3
        L_0x03ae:
            java.lang.String r0 = "Convert your Illustrator layers to shape layers."
            r7.addWarning(r0)
        L_0x03b3:
            com.airbnb.lottie.model.layer.Layer r32 = new com.airbnb.lottie.model.layer.Layer
            r0 = r32
            r1 = r8
            r2 = r37
            r3 = r12
            r4 = r16
            r6 = r14
            r7 = r18
            r9 = r20
            r33 = r11
            r11 = r21
            r12 = r22
            r13 = r23
            r14 = r24
            r16 = r25
            r17 = r26
            r18 = r27
            r19 = r28
            r20 = r29
            r21 = r33
            r22 = r30
            r23 = r31
            r0.<init>(r1, r2, r3, r4, r6, r7, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            return r32
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LayerParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.layer.Layer");
    }
}
