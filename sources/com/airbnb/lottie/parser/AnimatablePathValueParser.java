package com.airbnb.lottie.parser;

import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatablePathValueParser {
    private AnimatablePathValueParser() {
    }

    public static AnimatablePathValue parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(PathKeyframeParser.parse(jsonReader, lottieComposition));
            }
            jsonReader.endArray();
            KeyframesParser.setEndFrames(arrayList);
        } else {
            arrayList.add(new Keyframe(JsonUtils.jsonToPoint(jsonReader, Utils.dpScale())));
        }
        return new AnimatablePathValue(arrayList);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        r1 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.animatable.AnimatableValue<android.graphics.PointF, android.graphics.PointF> parseSplitPath(android.util.JsonReader r10, com.airbnb.lottie.LottieComposition r11) throws java.io.IOException {
        /*
            r10.beginObject()
            r0 = 0
            r1 = 0
            r2 = r1
            r3 = r2
            r4 = r3
            r1 = 0
        L_0x0009:
            android.util.JsonToken r5 = r10.peek()
            android.util.JsonToken r6 = android.util.JsonToken.END_OBJECT
            if (r5 == r6) goto L_0x0073
            java.lang.String r5 = r10.nextName()
            r6 = -1
            int r7 = r5.hashCode()
            r8 = 107(0x6b, float:1.5E-43)
            r9 = 1
            if (r7 == r8) goto L_0x0039
            switch(r7) {
                case 120: goto L_0x002e;
                case 121: goto L_0x0023;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x0043
        L_0x0023:
            java.lang.String r7 = "y"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0043
            r5 = 2
            goto L_0x0044
        L_0x002e:
            java.lang.String r7 = "x"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0043
            r5 = 1
            goto L_0x0044
        L_0x0039:
            java.lang.String r7 = "k"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0043
            r5 = 0
            goto L_0x0044
        L_0x0043:
            r5 = -1
        L_0x0044:
            switch(r5) {
                case 0: goto L_0x006e;
                case 1: goto L_0x005c;
                case 2: goto L_0x004b;
                default: goto L_0x0047;
            }
        L_0x0047:
            r10.skipValue()
            goto L_0x0009
        L_0x004b:
            android.util.JsonToken r5 = r10.peek()
            android.util.JsonToken r6 = android.util.JsonToken.STRING
            if (r5 != r6) goto L_0x0057
            r10.skipValue()
            goto L_0x0067
        L_0x0057:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r10, r11)
            goto L_0x0009
        L_0x005c:
            android.util.JsonToken r5 = r10.peek()
            android.util.JsonToken r6 = android.util.JsonToken.STRING
            if (r5 != r6) goto L_0x0069
            r10.skipValue()
        L_0x0067:
            r1 = 1
            goto L_0x0009
        L_0x0069:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r10, r11)
            goto L_0x0009
        L_0x006e:
            com.airbnb.lottie.model.animatable.AnimatablePathValue r2 = parse(r10, r11)
            goto L_0x0009
        L_0x0073:
            r10.endObject()
            if (r1 == 0) goto L_0x007d
            java.lang.String r10 = "Lottie doesn't support expressions."
            r11.addWarning(r10)
        L_0x007d:
            if (r2 == 0) goto L_0x0080
            return r2
        L_0x0080:
            com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue r10 = new com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue
            r10.<init>(r3, r4)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.AnimatablePathValueParser.parseSplitPath(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.animatable.AnimatableValue");
    }
}
