package com.taobao.weex.ui.animation;

import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TransformParser {
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
    public static Map<String, List<Property<View, Float>>> wxToAndroidMap;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7628153224752846504L, "com/taobao/weex/ui/animation/TransformParser", 67);
        $jacocoData = a2;
        return a2;
    }

    public TransformParser() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ float access$000(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        float parsePercentOrPx = parsePercentOrPx(str, i, i2);
        $jacocoInit[53] = true;
        return parsePercentOrPx;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        wxToAndroidMap = new ArrayMap();
        Map<String, List<Property<View, Float>>> map = wxToAndroidMap;
        Property[] propertyArr = {View.TRANSLATION_X, View.TRANSLATION_Y};
        $jacocoInit[54] = true;
        List asList = Arrays.asList(propertyArr);
        $jacocoInit[55] = true;
        map.put("translate", asList);
        $jacocoInit[56] = true;
        wxToAndroidMap.put("translateX", Collections.singletonList(View.TRANSLATION_X));
        $jacocoInit[57] = true;
        wxToAndroidMap.put("translateY", Collections.singletonList(View.TRANSLATION_Y));
        $jacocoInit[58] = true;
        wxToAndroidMap.put("rotate", Collections.singletonList(View.ROTATION));
        $jacocoInit[59] = true;
        wxToAndroidMap.put("rotateX", Collections.singletonList(View.ROTATION_X));
        $jacocoInit[60] = true;
        wxToAndroidMap.put("rotateY", Collections.singletonList(View.ROTATION_Y));
        $jacocoInit[61] = true;
        wxToAndroidMap.put("scale", Arrays.asList(new Property[]{View.SCALE_X, View.SCALE_Y}));
        $jacocoInit[62] = true;
        wxToAndroidMap.put("scaleX", Collections.singletonList(View.SCALE_X));
        $jacocoInit[63] = true;
        wxToAndroidMap.put("scaleY", Collections.singletonList(View.SCALE_Y));
        $jacocoInit[64] = true;
        wxToAndroidMap.put(Constants.Name.PERSPECTIVE, Collections.singletonList(CameraDistanceProperty.getInstance()));
        $jacocoInit[65] = true;
        wxToAndroidMap = Collections.unmodifiableMap(wxToAndroidMap);
        $jacocoInit[66] = true;
    }

    public static PropertyValuesHolder[] toHolders(Map<Property<View, Float>, Float> map) {
        boolean[] $jacocoInit = $jacocoInit();
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[map.size()];
        $jacocoInit[1] = true;
        $jacocoInit[2] = true;
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            $jacocoInit[3] = true;
            propertyValuesHolderArr[i] = PropertyValuesHolder.ofFloat((Property) next.getKey(), new float[]{((Float) next.getValue()).floatValue()});
            i++;
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
        return propertyValuesHolderArr;
    }

    public static Map<Property<View, Float>, Float> parseTransForm(String str, @Nullable String str2, final int i, final int i2, final int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (!TextUtils.isEmpty(str2)) {
                $jacocoInit[6] = true;
                FunctionParser functionParser = new FunctionParser(str2, new FunctionParser.Mapper<Property<View, Float>, Float>() {
                    private static transient /* synthetic */ boolean[] $jacocoData;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-3550301519618081685L, "com/taobao/weex/ui/animation/TransformParser$1", 78);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        $jacocoInit[0] = true;
                    }

                    public Map<Property<View, Float>, Float> map(String str, List<String> list) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (list == null) {
                            $jacocoInit[1] = true;
                        } else if (list.isEmpty()) {
                            $jacocoInit[2] = true;
                        } else {
                            $jacocoInit[3] = true;
                            if (!TransformParser.wxToAndroidMap.containsKey(str)) {
                                $jacocoInit[4] = true;
                            } else {
                                $jacocoInit[5] = true;
                                Map<Property<View, Float>, Float> convertParam = convertParam(i, i2, i3, TransformParser.wxToAndroidMap.get(str), list);
                                $jacocoInit[6] = true;
                                return convertParam;
                            }
                        }
                        HashMap hashMap = new HashMap();
                        $jacocoInit[7] = true;
                        return hashMap;
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:30:0x00dd  */
                    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e2  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    private java.util.Map<android.util.Property<android.view.View, java.lang.Float>, java.lang.Float> convertParam(int r11, int r12, int r13, @android.support.annotation.NonNull java.util.List<android.util.Property<android.view.View, java.lang.Float>> r14, @android.support.annotation.NonNull java.util.List<java.lang.String> r15) {
                        /*
                            r10 = this;
                            boolean[] r0 = $jacocoInit()
                            int r1 = r14.size()
                            java.util.HashMap r1 = com.taobao.weex.utils.WXDataStructureUtil.newHashMapWithExpectedSize(r1)
                            r2 = 1
                            r3 = 8
                            r0[r3] = r2
                            java.util.ArrayList r3 = new java.util.ArrayList
                            int r4 = r14.size()
                            r3.<init>(r4)
                            r4 = 9
                            r0[r4] = r2
                            android.util.Property r4 = android.view.View.ROTATION
                            boolean r4 = r14.contains(r4)
                            if (r4 == 0) goto L_0x002b
                            r11 = 10
                            r0[r11] = r2
                            goto L_0x004c
                        L_0x002b:
                            android.util.Property r4 = android.view.View.ROTATION_X
                            r5 = 11
                            r0[r5] = r2
                            boolean r4 = r14.contains(r4)
                            if (r4 == 0) goto L_0x003c
                            r11 = 12
                            r0[r11] = r2
                            goto L_0x004c
                        L_0x003c:
                            android.util.Property r4 = android.view.View.ROTATION_Y
                            r5 = 13
                            r0[r5] = r2
                            boolean r4 = r14.contains(r4)
                            if (r4 == 0) goto L_0x0059
                            r11 = 14
                            r0[r11] = r2
                        L_0x004c:
                            java.util.List r11 = r10.parseRotationZ(r15)
                            r3.addAll(r11)
                            r11 = 15
                            r0[r11] = r2
                            goto L_0x00d3
                        L_0x0059:
                            android.util.Property r4 = android.view.View.TRANSLATION_X
                            boolean r4 = r14.contains(r4)
                            if (r4 == 0) goto L_0x0066
                            r4 = 16
                            r0[r4] = r2
                            goto L_0x0076
                        L_0x0066:
                            android.util.Property r4 = android.view.View.TRANSLATION_Y
                            r5 = 17
                            r0[r5] = r2
                            boolean r4 = r14.contains(r4)
                            if (r4 == 0) goto L_0x0088
                            r4 = 18
                            r0[r4] = r2
                        L_0x0076:
                            r4 = r10
                            r5 = r14
                            r6 = r11
                            r7 = r12
                            r8 = r15
                            r9 = r13
                            java.util.List r11 = r4.parseTranslation(r5, r6, r7, r8, r9)
                            r3.addAll(r11)
                            r11 = 19
                            r0[r11] = r2
                            goto L_0x00d3
                        L_0x0088:
                            android.util.Property r11 = android.view.View.SCALE_X
                            boolean r11 = r14.contains(r11)
                            if (r11 == 0) goto L_0x0095
                            r11 = 20
                            r0[r11] = r2
                            goto L_0x00a5
                        L_0x0095:
                            android.util.Property r11 = android.view.View.SCALE_Y
                            r12 = 21
                            r0[r12] = r2
                            boolean r11 = r14.contains(r11)
                            if (r11 == 0) goto L_0x00b5
                            r11 = 22
                            r0[r11] = r2
                        L_0x00a5:
                            int r11 = r14.size()
                            java.util.List r11 = r10.parseScale(r11, r15)
                            r3.addAll(r11)
                            r11 = 23
                            r0[r11] = r2
                            goto L_0x00d3
                        L_0x00b5:
                            android.util.Property r11 = com.taobao.weex.ui.animation.CameraDistanceProperty.getInstance()
                            boolean r11 = r14.contains(r11)
                            if (r11 != 0) goto L_0x00c4
                            r11 = 24
                            r0[r11] = r2
                            goto L_0x00d3
                        L_0x00c4:
                            r11 = 25
                            r0[r11] = r2
                            java.lang.Float r11 = r10.parseCameraDistance(r15)
                            r3.add(r11)
                            r11 = 26
                            r0[r11] = r2
                        L_0x00d3:
                            int r11 = r14.size()
                            int r12 = r3.size()
                            if (r11 == r12) goto L_0x00e2
                            r11 = 27
                            r0[r11] = r2
                            goto L_0x00f5
                        L_0x00e2:
                            r11 = 28
                            r0[r11] = r2
                            r11 = 0
                            r12 = 29
                            r0[r12] = r2
                        L_0x00eb:
                            int r12 = r14.size()
                            if (r11 < r12) goto L_0x00fa
                            r11 = 30
                            r0[r11] = r2
                        L_0x00f5:
                            r11 = 33
                            r0[r11] = r2
                            return r1
                        L_0x00fa:
                            r12 = 31
                            r0[r12] = r2
                            java.lang.Object r12 = r14.get(r11)
                            java.lang.Object r13 = r3.get(r11)
                            r1.put(r12, r13)
                            int r11 = r11 + 1
                            r12 = 32
                            r0[r12] = r2
                            goto L_0x00eb
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.animation.TransformParser.AnonymousClass1.convertParam(int, int, int, java.util.List, java.util.List):java.util.Map");
                    }

                    private List<Float> parseScale(int i, @NonNull List<String> list) {
                        boolean[] $jacocoInit = $jacocoInit();
                        ArrayList arrayList = new ArrayList(list.size() * 2);
                        $jacocoInit[34] = true;
                        ArrayList arrayList2 = new ArrayList(list.size());
                        $jacocoInit[35] = true;
                        $jacocoInit[36] = true;
                        for (String fastGetFloat : list) {
                            $jacocoInit[37] = true;
                            arrayList2.add(Float.valueOf(WXUtils.fastGetFloat(fastGetFloat)));
                            $jacocoInit[38] = true;
                        }
                        arrayList.addAll(arrayList2);
                        $jacocoInit[39] = true;
                        if (i == 1) {
                            $jacocoInit[40] = true;
                        } else if (list.size() != 1) {
                            $jacocoInit[41] = true;
                        } else {
                            $jacocoInit[42] = true;
                            arrayList.addAll(arrayList2);
                            $jacocoInit[43] = true;
                        }
                        $jacocoInit[44] = true;
                        return arrayList;
                    }

                    @NonNull
                    private List<Float> parseRotationZ(@NonNull List<String> list) {
                        boolean[] $jacocoInit = $jacocoInit();
                        ArrayList arrayList = new ArrayList(1);
                        $jacocoInit[45] = true;
                        $jacocoInit[46] = true;
                        for (String next : list) {
                            $jacocoInit[47] = true;
                            int lastIndexOf = next.lastIndexOf(TransformParser.DEG);
                            if (lastIndexOf != -1) {
                                $jacocoInit[48] = true;
                                arrayList.add(Float.valueOf(WXUtils.fastGetFloat(next.substring(0, lastIndexOf))));
                                $jacocoInit[49] = true;
                            } else {
                                arrayList.add(Float.valueOf((float) Math.toDegrees(Double.parseDouble(next))));
                                $jacocoInit[50] = true;
                            }
                            $jacocoInit[51] = true;
                        }
                        $jacocoInit[52] = true;
                        return arrayList;
                    }

                    private List<Float> parseTranslation(List<Property<View, Float>> list, int i, int i2, @NonNull List<String> list2, int i3) {
                        boolean[] $jacocoInit = $jacocoInit();
                        ArrayList arrayList = new ArrayList(2);
                        $jacocoInit[53] = true;
                        String str = list2.get(0);
                        $jacocoInit[54] = true;
                        if (list.size() == 1) {
                            $jacocoInit[55] = true;
                            parseSingleTranslation(list, i, i2, arrayList, str, i3);
                            $jacocoInit[56] = true;
                        } else {
                            parseDoubleTranslation(i, i2, list2, arrayList, str, i3);
                            $jacocoInit[57] = true;
                        }
                        $jacocoInit[58] = true;
                        return arrayList;
                    }

                    private void parseSingleTranslation(List<Property<View, Float>> list, int i, int i2, List<Float> list2, String str, int i3) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (list.contains(View.TRANSLATION_X)) {
                            $jacocoInit[59] = true;
                            list2.add(Float.valueOf(TransformParser.access$000(str, i, i3)));
                            $jacocoInit[60] = true;
                        } else if (!list.contains(View.TRANSLATION_Y)) {
                            $jacocoInit[61] = true;
                        } else {
                            $jacocoInit[62] = true;
                            list2.add(Float.valueOf(TransformParser.access$000(str, i2, i3)));
                            $jacocoInit[63] = true;
                        }
                        $jacocoInit[64] = true;
                    }

                    private void parseDoubleTranslation(int i, int i2, @NonNull List<String> list, List<Float> list2, String str, int i3) {
                        String str2;
                        boolean[] $jacocoInit = $jacocoInit();
                        if (list.size() == 1) {
                            $jacocoInit[65] = true;
                            str2 = str;
                        } else {
                            str2 = list.get(1);
                            $jacocoInit[66] = true;
                        }
                        list2.add(Float.valueOf(TransformParser.access$000(str, i, i3)));
                        $jacocoInit[67] = true;
                        list2.add(Float.valueOf(TransformParser.access$000(str2, i2, i3)));
                        $jacocoInit[68] = true;
                    }

                    private Float parseCameraDistance(List<String> list) {
                        float f;
                        boolean[] $jacocoInit = $jacocoInit();
                        $jacocoInit[69] = true;
                        if (list.size() != 1) {
                            $jacocoInit[70] = true;
                        } else {
                            $jacocoInit[71] = true;
                            float realPxByWidth = WXViewUtils.getRealPxByWidth(WXUtils.getFloat(list.get(0)), i3);
                            $jacocoInit[72] = true;
                            float f2 = WXEnvironment.getApplication().getResources().getDisplayMetrics().density;
                            $jacocoInit[73] = true;
                            if (Float.isNaN(realPxByWidth)) {
                                $jacocoInit[74] = true;
                            } else if (realPxByWidth <= 0.0f) {
                                $jacocoInit[75] = true;
                            } else {
                                f = realPxByWidth * f2;
                                $jacocoInit[76] = true;
                                Float valueOf = Float.valueOf(f);
                                $jacocoInit[77] = true;
                                return valueOf;
                            }
                        }
                        f = Float.MAX_VALUE;
                        Float valueOf2 = Float.valueOf(f);
                        $jacocoInit[77] = true;
                        return valueOf2;
                    }
                });
                $jacocoInit[7] = true;
                LinkedHashMap parse = functionParser.parse();
                $jacocoInit[8] = true;
                return parse;
            }
            $jacocoInit[9] = true;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            $jacocoInit[15] = true;
            return linkedHashMap;
        } catch (Exception e) {
            $jacocoInit[10] = true;
            WXLogUtils.e("TransformParser", (Throwable) e);
            $jacocoInit[11] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_TRANSITION;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_RENDER_ERR_TRANSITION;
            $jacocoInit[12] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("parse transition error: ");
            sb.append(e.getMessage());
            String sb2 = sb.toString();
            $jacocoInit[13] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "parse animation transition", sb2, (Map<String, String>) null);
            $jacocoInit[14] = true;
        }
    }

    private static Pair<Float, Float> parsePivot(@Nullable String str, int i, int i2, int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            int indexOf = str.indexOf(32);
            if (indexOf == -1) {
                $jacocoInit[18] = true;
            } else {
                $jacocoInit[19] = true;
                int i4 = indexOf;
                while (true) {
                    if (i4 >= str.length()) {
                        $jacocoInit[20] = true;
                        break;
                    }
                    $jacocoInit[21] = true;
                    if (str.charAt(i4) != ' ') {
                        $jacocoInit[22] = true;
                        break;
                    }
                    i4++;
                    $jacocoInit[23] = true;
                }
                if (i4 >= str.length()) {
                    $jacocoInit[24] = true;
                } else if (str.charAt(i4) == ' ') {
                    $jacocoInit[25] = true;
                } else {
                    $jacocoInit[26] = true;
                    ArrayList arrayList = new ArrayList(2);
                    $jacocoInit[27] = true;
                    arrayList.add(str.substring(0, indexOf).trim());
                    $jacocoInit[28] = true;
                    arrayList.add(str.substring(i4, str.length()).trim());
                    $jacocoInit[29] = true;
                    Pair<Float, Float> parsePivot = parsePivot((List<String>) arrayList, i, i2, i3);
                    $jacocoInit[30] = true;
                    return parsePivot;
                }
            }
        }
        $jacocoInit[31] = true;
        return null;
    }

    private static Pair<Float, Float> parsePivot(@NonNull List<String> list, int i, int i2, int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[32] = true;
        Pair<Float, Float> pair = new Pair<>(Float.valueOf(parsePivotX(list.get(0), i, i3)), Float.valueOf(parsePivotY(list.get(1), i2, i3)));
        $jacocoInit[33] = true;
        return pair;
    }

    private static float parsePivotX(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[34] = true;
        if ("left".equals(str)) {
            str = ZERO;
            $jacocoInit[35] = true;
        } else if ("right".equals(str)) {
            str = FULL;
            $jacocoInit[36] = true;
        } else if (!"center".equals(str)) {
            $jacocoInit[37] = true;
        } else {
            str = HALF;
            $jacocoInit[38] = true;
        }
        float parsePercentOrPx = parsePercentOrPx(str, i, i2);
        $jacocoInit[39] = true;
        return parsePercentOrPx;
    }

    private static float parsePivotY(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[40] = true;
        if ("top".equals(str)) {
            str = ZERO;
            $jacocoInit[41] = true;
        } else if ("bottom".equals(str)) {
            str = FULL;
            $jacocoInit[42] = true;
        } else if (!"center".equals(str)) {
            $jacocoInit[43] = true;
        } else {
            str = HALF;
            $jacocoInit[44] = true;
        }
        float parsePercentOrPx = parsePercentOrPx(str, i, i2);
        $jacocoInit[45] = true;
        return parsePercentOrPx;
    }

    private static float parsePercentOrPx(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[46] = true;
        int lastIndexOf = str.lastIndexOf(37);
        if (lastIndexOf != -1) {
            $jacocoInit[47] = true;
            float parsePercent = parsePercent(str.substring(0, lastIndexOf), i, 1);
            $jacocoInit[48] = true;
            return parsePercent;
        }
        int lastIndexOf2 = str.lastIndexOf(PX);
        if (lastIndexOf2 != -1) {
            $jacocoInit[49] = true;
            float realPxByWidth = WXViewUtils.getRealPxByWidth(WXUtils.fastGetFloat(str.substring(0, lastIndexOf2), 1), i2);
            $jacocoInit[50] = true;
            return realPxByWidth;
        }
        float realPxByWidth2 = WXViewUtils.getRealPxByWidth(WXUtils.fastGetFloat(str, 1), i2);
        $jacocoInit[51] = true;
        return realPxByWidth2;
    }

    private static float parsePercent(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        float fastGetFloat = (WXUtils.fastGetFloat(str, i2) / 100.0f) * ((float) i);
        $jacocoInit[52] = true;
        return fastGetFloat;
    }
}
