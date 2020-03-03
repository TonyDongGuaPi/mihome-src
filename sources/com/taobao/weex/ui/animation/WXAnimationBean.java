package com.taobao.weex.ui.animation;

import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXAnimationBean {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String CUBIC_BEZIER = "cubic-bezier";
    public static final String EASE_IN = "ease-in";
    public static final String EASE_IN_OUT = "ease-in-out";
    public static final String EASE_OUT = "ease-out";
    public static final String LINEAR = "linear";
    public static final int NUM_CUBIC_PARAM = 4;
    public long delay;
    public long duration;
    public boolean needLayout;
    @Nullable
    public Style styles;
    public String timingFunction;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1641890252302367799L, "com/taobao/weex/ui/animation/WXAnimationBean", 1);
        $jacocoData = a2;
        return a2;
    }

    public WXAnimationBean() {
        $jacocoInit()[0] = true;
    }

    public static class Style {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        public static final String BACKGROUND_COLOR = "backgroundColor";
        public static final String BOTTOM = "bottom";
        public static final String CENTER = "center";
        private static final String DEG = "deg";
        private static final String FULL = "100%";
        private static final String HALF = "50%";
        public static final String HEIGHT = "height";
        public static final String LEFT = "left";
        private static final String PX = "px";
        public static final String RIGHT = "right";
        public static final String TOP = "top";
        public static final String WIDTH = "width";
        public static final String WX_ROTATE = "rotate";
        public static final String WX_ROTATE_X = "rotateX";
        public static final String WX_ROTATE_Y = "rotateY";
        public static final String WX_SCALE = "scale";
        public static final String WX_SCALE_X = "scaleX";
        public static final String WX_SCALE_Y = "scaleY";
        public static final String WX_TRANSLATE = "translate";
        public static final String WX_TRANSLATE_X = "translateX";
        public static final String WX_TRANSLATE_Y = "translateY";
        private static final String ZERO = "0%";
        private static Map<Property<View, Float>, Float> defaultMap = new ArrayMap();
        public static Map<String, List<Property<View, Float>>> wxToAndroidMap;
        public String backgroundColor;
        private float cameraDistance;
        public String height;
        private List<PropertyValuesHolder> holders;
        public String opacity;
        private Pair<Float, Float> pivot;
        public String transform;
        private Map<Property<View, Float>, Float> transformMap = new LinkedHashMap();
        public String transformOrigin;
        public String width;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8714326562082052402L, "com/taobao/weex/ui/animation/WXAnimationBean$Style", 89);
            $jacocoData = a2;
            return a2;
        }

        public Style() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
            this.holders = new LinkedList();
            this.cameraDistance = Float.MAX_VALUE;
            $jacocoInit[2] = true;
        }

        static {
            boolean[] $jacocoInit = $jacocoInit();
            wxToAndroidMap = new ArrayMap();
            $jacocoInit[68] = true;
            Map<String, List<Property<View, Float>>> map = wxToAndroidMap;
            Property[] propertyArr = {View.TRANSLATION_X, View.TRANSLATION_Y};
            $jacocoInit[69] = true;
            List asList = Arrays.asList(propertyArr);
            $jacocoInit[70] = true;
            map.put("translate", asList);
            $jacocoInit[71] = true;
            wxToAndroidMap.put("translateX", Collections.singletonList(View.TRANSLATION_X));
            $jacocoInit[72] = true;
            wxToAndroidMap.put("translateY", Collections.singletonList(View.TRANSLATION_Y));
            $jacocoInit[73] = true;
            wxToAndroidMap.put("rotate", Collections.singletonList(View.ROTATION));
            $jacocoInit[74] = true;
            wxToAndroidMap.put("rotateX", Collections.singletonList(View.ROTATION_X));
            $jacocoInit[75] = true;
            wxToAndroidMap.put("rotateY", Collections.singletonList(View.ROTATION_Y));
            $jacocoInit[76] = true;
            wxToAndroidMap.put("scale", Arrays.asList(new Property[]{View.SCALE_X, View.SCALE_Y}));
            $jacocoInit[77] = true;
            wxToAndroidMap.put("scaleX", Collections.singletonList(View.SCALE_X));
            $jacocoInit[78] = true;
            wxToAndroidMap.put("scaleY", Collections.singletonList(View.SCALE_Y));
            $jacocoInit[79] = true;
            wxToAndroidMap.put(Constants.Name.PERSPECTIVE, Collections.singletonList(CameraDistanceProperty.getInstance()));
            $jacocoInit[80] = true;
            wxToAndroidMap = Collections.unmodifiableMap(wxToAndroidMap);
            $jacocoInit[81] = true;
            defaultMap.put(View.TRANSLATION_X, Float.valueOf(0.0f));
            $jacocoInit[82] = true;
            defaultMap.put(View.TRANSLATION_Y, Float.valueOf(0.0f));
            $jacocoInit[83] = true;
            defaultMap.put(View.SCALE_X, Float.valueOf(1.0f));
            $jacocoInit[84] = true;
            defaultMap.put(View.SCALE_Y, Float.valueOf(1.0f));
            $jacocoInit[85] = true;
            defaultMap.put(View.ROTATION, Float.valueOf(0.0f));
            $jacocoInit[86] = true;
            defaultMap.put(View.ROTATION_X, Float.valueOf(0.0f));
            $jacocoInit[87] = true;
            defaultMap.put(View.ROTATION_Y, Float.valueOf(0.0f));
            $jacocoInit[88] = true;
        }

        private static Pair<Float, Float> parsePivot(@Nullable String str, int i, int i2, int i3) {
            boolean[] $jacocoInit = $jacocoInit();
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                int indexOf = str.indexOf(32);
                if (indexOf == -1) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    int i4 = indexOf;
                    while (true) {
                        if (i4 >= str.length()) {
                            $jacocoInit[7] = true;
                            break;
                        }
                        $jacocoInit[8] = true;
                        if (str.charAt(i4) != ' ') {
                            $jacocoInit[9] = true;
                            break;
                        }
                        i4++;
                        $jacocoInit[10] = true;
                    }
                    if (i4 >= str.length()) {
                        $jacocoInit[11] = true;
                    } else if (str.charAt(i4) == ' ') {
                        $jacocoInit[12] = true;
                    } else {
                        $jacocoInit[13] = true;
                        ArrayList arrayList = new ArrayList(2);
                        $jacocoInit[14] = true;
                        arrayList.add(str.substring(0, indexOf).trim());
                        $jacocoInit[15] = true;
                        arrayList.add(str.substring(i4, str.length()).trim());
                        $jacocoInit[16] = true;
                        Pair<Float, Float> parsePivot = parsePivot((List<String>) arrayList, i, i2, i3);
                        $jacocoInit[17] = true;
                        return parsePivot;
                    }
                }
            }
            $jacocoInit[18] = true;
            return null;
        }

        private static Pair<Float, Float> parsePivot(@NonNull List<String> list, int i, int i2, int i3) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[19] = true;
            Pair<Float, Float> pair = new Pair<>(Float.valueOf(parsePivotX(list.get(0), i, i3)), Float.valueOf(parsePivotY(list.get(1), i2, i3)));
            $jacocoInit[20] = true;
            return pair;
        }

        private static float parsePivotX(String str, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[21] = true;
            if ("left".equals(str)) {
                str = ZERO;
                $jacocoInit[22] = true;
            } else if ("right".equals(str)) {
                str = FULL;
                $jacocoInit[23] = true;
            } else if (!"center".equals(str)) {
                $jacocoInit[24] = true;
            } else {
                str = HALF;
                $jacocoInit[25] = true;
            }
            float parsePercentOrPx = parsePercentOrPx(str, i, i2);
            $jacocoInit[26] = true;
            return parsePercentOrPx;
        }

        private static float parsePivotY(String str, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[27] = true;
            if ("top".equals(str)) {
                str = ZERO;
                $jacocoInit[28] = true;
            } else if ("bottom".equals(str)) {
                str = FULL;
                $jacocoInit[29] = true;
            } else if (!"center".equals(str)) {
                $jacocoInit[30] = true;
            } else {
                str = HALF;
                $jacocoInit[31] = true;
            }
            float parsePercentOrPx = parsePercentOrPx(str, i, i2);
            $jacocoInit[32] = true;
            return parsePercentOrPx;
        }

        private static float parsePercentOrPx(String str, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[33] = true;
            int lastIndexOf = str.lastIndexOf(37);
            if (lastIndexOf != -1) {
                $jacocoInit[34] = true;
                float parsePercent = parsePercent(str.substring(0, lastIndexOf), i, 1);
                $jacocoInit[35] = true;
                return parsePercent;
            }
            int lastIndexOf2 = str.lastIndexOf(PX);
            if (lastIndexOf2 != -1) {
                $jacocoInit[36] = true;
                float realPxByWidth = WXViewUtils.getRealPxByWidth(WXUtils.fastGetFloat(str.substring(0, lastIndexOf2), 1), i2);
                $jacocoInit[37] = true;
                return realPxByWidth;
            }
            float realPxByWidth2 = WXViewUtils.getRealPxByWidth(WXUtils.fastGetFloat(str, 1), i2);
            $jacocoInit[38] = true;
            return realPxByWidth2;
        }

        private static float parsePercent(String str, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            float fastGetFloat = (WXUtils.fastGetFloat(str, i2) / 100.0f) * ((float) i);
            $jacocoInit[39] = true;
            return fastGetFloat;
        }

        private void resetToDefaultIfAbsent() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[40] = true;
            for (Map.Entry next : defaultMap.entrySet()) {
                $jacocoInit[41] = true;
                if (this.transformMap.containsKey(next.getKey())) {
                    $jacocoInit[42] = true;
                } else {
                    $jacocoInit[43] = true;
                    this.transformMap.put(next.getKey(), next.getValue());
                    $jacocoInit[44] = true;
                }
                $jacocoInit[45] = true;
            }
            $jacocoInit[46] = true;
        }

        @Nullable
        public Pair<Float, Float> getPivot() {
            boolean[] $jacocoInit = $jacocoInit();
            Pair<Float, Float> pair = this.pivot;
            $jacocoInit[47] = true;
            return pair;
        }

        public void init(@Nullable String str, @Nullable String str2, int i, int i2, int i3, WXSDKInstance wXSDKInstance) {
            boolean[] $jacocoInit = $jacocoInit();
            this.pivot = parsePivot(str, i, i2, i3);
            $jacocoInit[48] = true;
            this.transformMap.putAll(TransformParser.parseTransForm(wXSDKInstance.getInstanceId(), str2, i, i2, i3));
            $jacocoInit[49] = true;
            resetToDefaultIfAbsent();
            $jacocoInit[50] = true;
            if (!this.transformMap.containsKey(CameraDistanceProperty.getInstance())) {
                $jacocoInit[51] = true;
            } else {
                $jacocoInit[52] = true;
                this.cameraDistance = this.transformMap.remove(CameraDistanceProperty.getInstance()).floatValue();
                $jacocoInit[53] = true;
            }
            initHolders();
            $jacocoInit[54] = true;
        }

        public void init(@NonNull Map<Property<View, Float>, Pair<Float, Float>> map) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[55] = true;
            for (Map.Entry next : map.entrySet()) {
                $jacocoInit[56] = true;
                this.holders.add(PropertyValuesHolder.ofFloat((Property) next.getKey(), new float[]{((Float) ((Pair) next.getValue()).first).floatValue(), ((Float) ((Pair) next.getValue()).second).floatValue()}));
                $jacocoInit[57] = true;
            }
            $jacocoInit[58] = true;
        }

        private void initHolders() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[59] = true;
            for (Map.Entry next : this.transformMap.entrySet()) {
                $jacocoInit[60] = true;
                this.holders.add(PropertyValuesHolder.ofFloat((Property) next.getKey(), new float[]{((Float) next.getValue()).floatValue()}));
                $jacocoInit[61] = true;
            }
            if (TextUtils.isEmpty(this.opacity)) {
                $jacocoInit[62] = true;
            } else {
                $jacocoInit[63] = true;
                this.holders.add(PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{WXUtils.fastGetFloat(this.opacity, 3)}));
                $jacocoInit[64] = true;
            }
            $jacocoInit[65] = true;
        }

        public List<PropertyValuesHolder> getHolders() {
            boolean[] $jacocoInit = $jacocoInit();
            List<PropertyValuesHolder> list = this.holders;
            $jacocoInit[66] = true;
            return list;
        }

        public float getCameraDistance() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.cameraDistance;
            $jacocoInit[67] = true;
            return f;
        }
    }
}
