package com.taobao.weex.dom;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.Layout;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXStyle implements Cloneable, Map<String, Object> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int UNSET = -1;
    private static final long serialVersionUID = 611132641365274134L;
    private ArrayMap<String, Object> mBindingStyle;
    @Nullable
    private Map<String, Object> mPesudoResetStyleMap;
    @Nullable
    private Map<String, Map<String, Object>> mPesudoStyleMap;
    @NonNull
    private Map<String, Object> mStyles;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3726689613388141417L, "com/taobao/weex/dom/WXStyle", 238);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        boolean[] $jacocoInit = $jacocoInit();
        Object put = put((String) obj, obj2);
        $jacocoInit[237] = true;
        return put;
    }

    public WXStyle() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mStyles = new ArrayMap();
        $jacocoInit[1] = true;
    }

    public WXStyle(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyles = map;
        $jacocoInit[2] = true;
        processPesudoClasses(this.mStyles);
        $jacocoInit[3] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WXStyle(Map<String, Object> map, boolean z) {
        this();
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[4] = true;
        putAll(map, z);
        $jacocoInit[5] = true;
    }

    @Nullable
    public String getBlur() {
        boolean[] $jacocoInit = $jacocoInit();
        if (get("filter") == null) {
            $jacocoInit[6] = true;
            return null;
        }
        String trim = get("filter").toString().trim();
        $jacocoInit[7] = true;
        return trim;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.taobao.weex.ui.component.WXTextDecoration getTextDecoration(java.util.Map<java.lang.String, java.lang.Object> r5) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r5 != 0) goto L_0x000c
            r5 = 8
            r0[r5] = r1
            goto L_0x0018
        L_0x000c:
            java.lang.String r2 = "textDecoration"
            java.lang.Object r5 = r5.get(r2)
            if (r5 != 0) goto L_0x0020
            r5 = 9
            r0[r5] = r1
        L_0x0018:
            com.taobao.weex.ui.component.WXTextDecoration r5 = com.taobao.weex.ui.component.WXTextDecoration.NONE
            r2 = 10
            r0[r2] = r1
            goto L_0x0098
        L_0x0020:
            java.lang.String r5 = r5.toString()
            r2 = 11
            r0[r2] = r1
            r2 = -1
            int r3 = r5.hashCode()
            r4 = -1171789332(0xffffffffba27e9ec, float:-6.4054015E-4)
            if (r3 == r4) goto L_0x0067
            r4 = -1026963764(0xffffffffc2c9c6cc, float:-100.888275)
            if (r3 == r4) goto L_0x0054
            r4 = 3387192(0x33af38, float:4.746467E-39)
            if (r3 == r4) goto L_0x0041
            r5 = 12
            r0[r5] = r1
            goto L_0x0073
        L_0x0041:
            java.lang.String r3 = "none"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x004e
            r5 = 17
            r0[r5] = r1
            goto L_0x0073
        L_0x004e:
            r5 = 2
            r2 = 18
            r0[r2] = r1
            goto L_0x007a
        L_0x0054:
            java.lang.String r3 = "underline"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0061
            r5 = 13
            r0[r5] = r1
            goto L_0x0073
        L_0x0061:
            r5 = 0
            r2 = 14
            r0[r2] = r1
            goto L_0x007a
        L_0x0067:
            java.lang.String r3 = "line-through"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0075
            r5 = 15
            r0[r5] = r1
        L_0x0073:
            r5 = -1
            goto L_0x007a
        L_0x0075:
            r5 = 16
            r0[r5] = r1
            r5 = 1
        L_0x007a:
            switch(r5) {
                case 0: goto L_0x0092;
                case 1: goto L_0x008b;
                case 2: goto L_0x0084;
                default: goto L_0x007d;
            }
        L_0x007d:
            com.taobao.weex.ui.component.WXTextDecoration r5 = com.taobao.weex.ui.component.WXTextDecoration.INVALID
            r2 = 22
            r0[r2] = r1
            goto L_0x0098
        L_0x0084:
            com.taobao.weex.ui.component.WXTextDecoration r5 = com.taobao.weex.ui.component.WXTextDecoration.NONE
            r2 = 21
            r0[r2] = r1
            goto L_0x0098
        L_0x008b:
            com.taobao.weex.ui.component.WXTextDecoration r5 = com.taobao.weex.ui.component.WXTextDecoration.LINETHROUGH
            r2 = 20
            r0[r2] = r1
            goto L_0x0098
        L_0x0092:
            com.taobao.weex.ui.component.WXTextDecoration r5 = com.taobao.weex.ui.component.WXTextDecoration.UNDERLINE
            r2 = 19
            r0[r2] = r1
        L_0x0098:
            r2 = 23
            r0[r2] = r1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.WXStyle.getTextDecoration(java.util.Map):com.taobao.weex.ui.component.WXTextDecoration");
    }

    public static String getTextColor(Map<String, Object> map) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[24] = true;
            return "";
        }
        Object obj = map.get("color");
        $jacocoInit[25] = true;
        if (obj == null) {
            str = "";
            $jacocoInit[26] = true;
        } else {
            str = obj.toString();
            $jacocoInit[27] = true;
        }
        $jacocoInit[28] = true;
        return str;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0099, code lost:
        switch(r5) {
            case 0: goto L_0x00a1;
            case 1: goto L_0x00a1;
            case 2: goto L_0x00a1;
            case 3: goto L_0x00a1;
            case 4: goto L_0x00a1;
            default: goto L_0x009c;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009c, code lost:
        r0[45] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        r0[46] = true;
        r1 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFontWeight(java.util.Map<java.lang.String, java.lang.Object> r5) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 0
            r2 = 1
            if (r5 != 0) goto L_0x000e
            r5 = 29
            r0[r5] = r2
            goto L_0x00a6
        L_0x000e:
            r3 = 30
            r0[r3] = r2
            java.lang.String r3 = "fontWeight"
            java.lang.Object r5 = r5.get(r3)
            if (r5 != 0) goto L_0x0020
            r5 = 31
            r0[r5] = r2
            goto L_0x00a6
        L_0x0020:
            r3 = 32
            r0[r3] = r2
            java.lang.String r5 = r5.toString()
            r3 = 33
            r0[r3] = r2
            r3 = -1
            int r4 = r5.hashCode()
            switch(r4) {
                case 53430: goto L_0x0085;
                case 54391: goto L_0x0072;
                case 55352: goto L_0x005f;
                case 56313: goto L_0x004c;
                case 3029637: goto L_0x0039;
                default: goto L_0x0034;
            }
        L_0x0034:
            r5 = 34
            r0[r5] = r2
            goto L_0x0098
        L_0x0039:
            java.lang.String r4 = "bold"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0046
            r5 = 43
            r0[r5] = r2
            goto L_0x0098
        L_0x0046:
            r5 = 4
            r3 = 44
            r0[r3] = r2
            goto L_0x0099
        L_0x004c:
            java.lang.String r4 = "900"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0059
            r5 = 41
            r0[r5] = r2
            goto L_0x0098
        L_0x0059:
            r5 = 3
            r3 = 42
            r0[r3] = r2
            goto L_0x0099
        L_0x005f:
            java.lang.String r4 = "800"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x006c
            r5 = 39
            r0[r5] = r2
            goto L_0x0098
        L_0x006c:
            r5 = 2
            r3 = 40
            r0[r3] = r2
            goto L_0x0099
        L_0x0072:
            java.lang.String r4 = "700"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x007f
            r5 = 37
            r0[r5] = r2
            goto L_0x0098
        L_0x007f:
            r5 = 38
            r0[r5] = r2
            r5 = 1
            goto L_0x0099
        L_0x0085:
            java.lang.String r4 = "600"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0092
            r5 = 35
            r0[r5] = r2
            goto L_0x0098
        L_0x0092:
            r5 = 36
            r0[r5] = r2
            r5 = 0
            goto L_0x0099
        L_0x0098:
            r5 = -1
        L_0x0099:
            switch(r5) {
                case 0: goto L_0x00a1;
                case 1: goto L_0x00a1;
                case 2: goto L_0x00a1;
                case 3: goto L_0x00a1;
                case 4: goto L_0x00a1;
                default: goto L_0x009c;
            }
        L_0x009c:
            r5 = 45
            r0[r5] = r2
            goto L_0x00a6
        L_0x00a1:
            r5 = 46
            r0[r5] = r2
            r1 = 1
        L_0x00a6:
            r5 = 47
            r0[r5] = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.WXStyle.getFontWeight(java.util.Map):int");
    }

    public static int getFontStyle(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = 0;
        if (map == null) {
            $jacocoInit[48] = true;
            return 0;
        }
        Object obj = map.get("fontStyle");
        if (obj == null) {
            $jacocoInit[49] = true;
            return 0;
        }
        String obj2 = obj.toString();
        $jacocoInit[50] = true;
        if (!obj2.equals("italic")) {
            $jacocoInit[51] = true;
        } else {
            i = 2;
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
        return i;
    }

    public static int getFontSize(Map<String, Object> map, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[54] = true;
            int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(32.0f, i);
            $jacocoInit[55] = true;
            return realPxByWidth;
        }
        int i2 = WXUtils.getInt(map.get("fontSize"));
        if (i2 > 0) {
            $jacocoInit[56] = true;
        } else {
            i2 = 32;
            $jacocoInit[57] = true;
        }
        int realPxByWidth2 = (int) WXViewUtils.getRealPxByWidth((float) i2, i);
        $jacocoInit[58] = true;
        return realPxByWidth2;
    }

    public static String getFontFamily(Map<String, Object> map) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[59] = true;
        } else {
            $jacocoInit[60] = true;
            Object obj = map.get("fontFamily");
            if (obj == null) {
                $jacocoInit[61] = true;
            } else {
                $jacocoInit[62] = true;
                str = obj.toString();
                $jacocoInit[63] = true;
                $jacocoInit[64] = true;
                return str;
            }
        }
        str = null;
        $jacocoInit[64] = true;
        return str;
    }

    public static Layout.Alignment getTextAlignment(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Layout.Alignment textAlignment = getTextAlignment(map, false);
        $jacocoInit[65] = true;
        return textAlignment;
    }

    public static Layout.Alignment getTextAlignment(Map<String, Object> map, boolean z) {
        Layout.Alignment alignment;
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
            $jacocoInit[66] = true;
        } else {
            alignment = Layout.Alignment.ALIGN_NORMAL;
            $jacocoInit[67] = true;
        }
        $jacocoInit[68] = true;
        String str = (String) map.get("textAlign");
        $jacocoInit[69] = true;
        if (TextUtils.equals("left", str)) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
            $jacocoInit[70] = true;
        } else if (TextUtils.equals("center", str)) {
            alignment = Layout.Alignment.ALIGN_CENTER;
            $jacocoInit[71] = true;
        } else if (!TextUtils.equals("right", str)) {
            $jacocoInit[72] = true;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
            $jacocoInit[73] = true;
        }
        $jacocoInit[74] = true;
        return alignment;
    }

    public static TextUtils.TruncateAt getTextOverflow(Map<String, Object> map) {
        TextUtils.TruncateAt truncateAt;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[75] = true;
        $jacocoInit[76] = true;
        if (!TextUtils.equals(Constants.Name.ELLIPSIS, (String) map.get(Constants.Name.TEXT_OVERFLOW))) {
            $jacocoInit[77] = true;
            truncateAt = null;
        } else {
            truncateAt = TextUtils.TruncateAt.END;
            $jacocoInit[78] = true;
        }
        $jacocoInit[79] = true;
        return truncateAt;
    }

    public static int getLines(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = WXUtils.getInt(map.get(Constants.Name.LINES));
        $jacocoInit[80] = true;
        return i;
    }

    public static int getLineHeight(Map<String, Object> map, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[81] = true;
            return -1;
        }
        int i2 = WXUtils.getInt(map.get("lineHeight"));
        if (i2 <= 0) {
            $jacocoInit[82] = true;
            return -1;
        }
        int realPxByWidth = (int) WXViewUtils.getRealPxByWidth((float) i2, i);
        $jacocoInit[83] = true;
        return realPxByWidth;
    }

    public float getBorderRadius() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(get("borderRadius"));
        $jacocoInit[84] = true;
        if (WXUtils.isUndefined(f)) {
            $jacocoInit[85] = true;
            return Float.NaN;
        }
        $jacocoInit[86] = true;
        return f;
    }

    public String getBorderColor() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("borderColor");
        $jacocoInit[87] = true;
        if (obj == null) {
            str = null;
            $jacocoInit[88] = true;
        } else {
            str = obj.toString();
            $jacocoInit[89] = true;
        }
        $jacocoInit[90] = true;
        return str;
    }

    public String getBorderStyle() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.BORDER_STYLE);
        $jacocoInit[91] = true;
        if (obj == null) {
            str = null;
            $jacocoInit[92] = true;
        } else {
            str = obj.toString();
            $jacocoInit[93] = true;
        }
        $jacocoInit[94] = true;
        return str;
    }

    public boolean isSticky() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("position");
        if (obj == null) {
            $jacocoInit[95] = true;
            return false;
        }
        boolean equals = obj.toString().equals("sticky");
        $jacocoInit[96] = true;
        return equals;
    }

    public boolean isFixed() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("position");
        if (obj == null) {
            $jacocoInit[97] = true;
            return false;
        }
        boolean equals = obj.toString().equals(Constants.Value.FIXED);
        $jacocoInit[98] = true;
        return equals;
    }

    public float getLeft() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(get("left"));
        $jacocoInit[99] = true;
        if (WXUtils.isUndefined(f)) {
            $jacocoInit[100] = true;
            return Float.NaN;
        }
        $jacocoInit[101] = true;
        return f;
    }

    public float getRight() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(get("right"));
        $jacocoInit[102] = true;
        if (WXUtils.isUndefined(f)) {
            $jacocoInit[103] = true;
            return Float.NaN;
        }
        $jacocoInit[104] = true;
        return f;
    }

    public float getTop() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(get("top"));
        $jacocoInit[105] = true;
        if (WXUtils.isUndefined(f)) {
            $jacocoInit[106] = true;
            return Float.NaN;
        }
        $jacocoInit[107] = true;
        return f;
    }

    public float getBottom() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(get("bottom"));
        $jacocoInit[108] = true;
        if (WXUtils.isUndefined(f)) {
            $jacocoInit[109] = true;
            return Float.NaN;
        }
        $jacocoInit[110] = true;
        return f;
    }

    public String getBackgroundColor() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("backgroundColor");
        $jacocoInit[111] = true;
        if (obj == null) {
            str = "";
            $jacocoInit[112] = true;
        } else {
            str = obj.toString();
            $jacocoInit[113] = true;
        }
        $jacocoInit[114] = true;
        return str;
    }

    public int getTimeFontSize() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = WXUtils.getInt(get("timeFontSize"));
        if (i > 0) {
            $jacocoInit[115] = true;
        } else {
            i = 32;
            $jacocoInit[116] = true;
        }
        $jacocoInit[117] = true;
        return i;
    }

    public float getOpacity() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("opacity");
        if (obj == null) {
            $jacocoInit[118] = true;
            return 1.0f;
        }
        float f = WXUtils.getFloat(obj);
        $jacocoInit[119] = true;
        return f;
    }

    public String getOverflow() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("overflow");
        $jacocoInit[120] = true;
        if (obj == null) {
            str = "visible";
            $jacocoInit[121] = true;
        } else {
            str = obj.toString();
            $jacocoInit[122] = true;
        }
        $jacocoInit[123] = true;
        return str;
    }

    public boolean equals(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean equals = this.mStyles.equals(obj);
        $jacocoInit[124] = true;
        return equals;
    }

    public int hashCode() {
        boolean[] $jacocoInit = $jacocoInit();
        int hashCode = this.mStyles.hashCode();
        $jacocoInit[125] = true;
        return hashCode;
    }

    public void clear() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyles.clear();
        $jacocoInit[126] = true;
    }

    public boolean containsKey(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsKey = this.mStyles.containsKey(obj);
        $jacocoInit[127] = true;
        return containsKey;
    }

    public boolean containsValue(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsValue = this.mStyles.containsValue(obj);
        $jacocoInit[128] = true;
        return containsValue;
    }

    @NonNull
    public Set<Map.Entry<String, Object>> entrySet() {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry<String, Object>> entrySet = this.mStyles.entrySet();
        $jacocoInit[129] = true;
        return entrySet;
    }

    public Object get(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj2 = this.mStyles.get(obj);
        $jacocoInit[130] = true;
        return obj2;
    }

    public boolean isEmpty() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isEmpty = this.mStyles.isEmpty();
        $jacocoInit[131] = true;
        return isEmpty;
    }

    @NonNull
    public Set<String> keySet() {
        boolean[] $jacocoInit = $jacocoInit();
        Set<String> keySet = this.mStyles.keySet();
        $jacocoInit[132] = true;
        return keySet;
    }

    public Object put(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (addBindingStyleIfStatement(str, obj)) {
            $jacocoInit[133] = true;
            return null;
        }
        Object put = this.mStyles.put(str, obj);
        $jacocoInit[134] = true;
        return put;
    }

    public void putAll(Map<? extends String, ?> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyles.putAll(map);
        $jacocoInit[135] = true;
    }

    public void putAll(Map<? extends String, ?> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyles.putAll(map);
        if (z) {
            $jacocoInit[136] = true;
        } else {
            $jacocoInit[137] = true;
            processPesudoClasses(map);
            $jacocoInit[138] = true;
        }
        $jacocoInit[139] = true;
    }

    public void updateStyle(Map<? extends String, ?> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        parseBindingStylesStatements(map);
        $jacocoInit[140] = true;
        putAll(map, z);
        $jacocoInit[141] = true;
    }

    public Map<String, Object> getPesudoResetStyles() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPesudoResetStyleMap != null) {
            $jacocoInit[142] = true;
        } else {
            $jacocoInit[143] = true;
            this.mPesudoResetStyleMap = new ArrayMap();
            $jacocoInit[144] = true;
        }
        Map<String, Object> map = this.mPesudoResetStyleMap;
        $jacocoInit[145] = true;
        return map;
    }

    public Map<String, Map<String, Object>> getPesudoStyles() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPesudoStyleMap != null) {
            $jacocoInit[146] = true;
        } else {
            $jacocoInit[147] = true;
            this.mPesudoStyleMap = new ArrayMap();
            $jacocoInit[148] = true;
        }
        Map<String, Map<String, Object>> map = this.mPesudoStyleMap;
        $jacocoInit[149] = true;
        return map;
    }

    /* access modifiers changed from: package-private */
    public <T extends String, V> void processPesudoClasses(Map<T, V> map) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[150] = true;
        $jacocoInit[151] = true;
        ArrayMap arrayMap = null;
        for (Map.Entry next : map.entrySet()) {
            $jacocoInit[152] = true;
            String str = (String) next.getKey();
            $jacocoInit[153] = true;
            int indexOf = str.indexOf(":");
            if (indexOf <= 0) {
                $jacocoInit[154] = true;
            } else {
                $jacocoInit[155] = true;
                initPesudoMapsIfNeed(map);
                $jacocoInit[156] = true;
                String substring = str.substring(indexOf);
                $jacocoInit[157] = true;
                if (substring.equals(Constants.PSEUDO.ENABLED)) {
                    $jacocoInit[158] = true;
                    String substring2 = str.substring(0, indexOf);
                    if (arrayMap != null) {
                        $jacocoInit[159] = true;
                    } else {
                        $jacocoInit[160] = true;
                        arrayMap = new ArrayMap();
                        $jacocoInit[161] = true;
                    }
                    arrayMap.put(substring2, next.getValue());
                    $jacocoInit[162] = true;
                    this.mPesudoResetStyleMap.put(substring2, next.getValue());
                    $jacocoInit[163] = true;
                } else {
                    String replace = substring.replace(Constants.PSEUDO.ENABLED, "");
                    $jacocoInit[164] = true;
                    Map map2 = this.mPesudoStyleMap.get(replace);
                    if (map2 != null) {
                        $jacocoInit[165] = true;
                    } else {
                        $jacocoInit[166] = true;
                        map2 = new ArrayMap();
                        $jacocoInit[167] = true;
                        this.mPesudoStyleMap.put(replace, map2);
                        $jacocoInit[168] = true;
                    }
                    map2.put(str.substring(0, indexOf), next.getValue());
                    $jacocoInit[169] = true;
                }
            }
            $jacocoInit[170] = true;
        }
        if (arrayMap == null) {
            $jacocoInit[171] = true;
        } else if (arrayMap.isEmpty()) {
            $jacocoInit[172] = true;
        } else {
            $jacocoInit[173] = true;
            this.mStyles.putAll(arrayMap);
            $jacocoInit[174] = true;
        }
        $jacocoInit[175] = true;
    }

    public Object remove(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Object remove = this.mStyles.remove(obj);
        $jacocoInit[176] = true;
        return remove;
    }

    public int size() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.mStyles.size();
        $jacocoInit[177] = true;
        return size;
    }

    @NonNull
    public Collection<Object> values() {
        boolean[] $jacocoInit = $jacocoInit();
        Collection<Object> values = this.mStyles.values();
        $jacocoInit[178] = true;
        return values;
    }

    private void initPesudoMapsIfNeed(Map<? extends String, ?> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPesudoStyleMap != null) {
            $jacocoInit[179] = true;
        } else {
            $jacocoInit[180] = true;
            this.mPesudoStyleMap = new ArrayMap();
            $jacocoInit[181] = true;
        }
        if (this.mPesudoResetStyleMap != null) {
            $jacocoInit[182] = true;
        } else {
            $jacocoInit[183] = true;
            this.mPesudoResetStyleMap = new ArrayMap();
            $jacocoInit[184] = true;
        }
        if (!this.mPesudoResetStyleMap.isEmpty()) {
            $jacocoInit[185] = true;
        } else {
            $jacocoInit[186] = true;
            this.mPesudoResetStyleMap.putAll(map);
            $jacocoInit[187] = true;
        }
        $jacocoInit[188] = true;
    }

    public void parseStatements() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStyles == null) {
            $jacocoInit[189] = true;
        } else {
            $jacocoInit[190] = true;
            this.mStyles = parseBindingStylesStatements(this.mStyles);
            $jacocoInit[191] = true;
        }
        $jacocoInit[192] = true;
    }

    private Map<String, Object> parseBindingStylesStatements(Map map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[193] = true;
        } else if (map.size() == 0) {
            $jacocoInit[194] = true;
        } else {
            Set entrySet = map.entrySet();
            $jacocoInit[196] = true;
            Iterator it = entrySet.iterator();
            $jacocoInit[197] = true;
            while (it.hasNext()) {
                $jacocoInit[198] = true;
                Map.Entry entry = (Map.Entry) it.next();
                $jacocoInit[199] = true;
                if (!addBindingStyleIfStatement((String) entry.getKey(), entry.getValue())) {
                    $jacocoInit[200] = true;
                } else {
                    if (this.mPesudoStyleMap == null) {
                        $jacocoInit[201] = true;
                    } else {
                        $jacocoInit[202] = true;
                        this.mPesudoStyleMap.remove(entry.getKey());
                        $jacocoInit[203] = true;
                    }
                    if (this.mPesudoResetStyleMap == null) {
                        $jacocoInit[204] = true;
                    } else {
                        $jacocoInit[205] = true;
                        this.mPesudoResetStyleMap.remove(entry.getKey());
                        $jacocoInit[206] = true;
                    }
                    it.remove();
                    $jacocoInit[207] = true;
                }
                $jacocoInit[208] = true;
            }
            $jacocoInit[209] = true;
            return map;
        }
        $jacocoInit[195] = true;
        return map;
    }

    private boolean addBindingStyleIfStatement(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (ELUtils.isBinding(obj)) {
            if (this.mBindingStyle != null) {
                $jacocoInit[210] = true;
            } else {
                $jacocoInit[211] = true;
                this.mBindingStyle = new ArrayMap<>();
                $jacocoInit[212] = true;
            }
            Object bindingBlock = ELUtils.bindingBlock(obj);
            $jacocoInit[213] = true;
            this.mBindingStyle.put(str, bindingBlock);
            $jacocoInit[214] = true;
            return true;
        }
        $jacocoInit[215] = true;
        return false;
    }

    public ArrayMap<String, Object> getBindingStyle() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayMap<String, Object> arrayMap = this.mBindingStyle;
        $jacocoInit[216] = true;
        return arrayMap;
    }

    public WXStyle clone() {
        boolean[] $jacocoInit = $jacocoInit();
        WXStyle wXStyle = new WXStyle();
        $jacocoInit[217] = true;
        wXStyle.mStyles.putAll(this.mStyles);
        if (this.mBindingStyle == null) {
            $jacocoInit[218] = true;
        } else {
            $jacocoInit[219] = true;
            wXStyle.mBindingStyle = new ArrayMap<>((SimpleArrayMap) this.mBindingStyle);
            $jacocoInit[220] = true;
        }
        if (this.mPesudoStyleMap == null) {
            $jacocoInit[221] = true;
        } else {
            $jacocoInit[222] = true;
            wXStyle.mPesudoStyleMap = new ArrayMap();
            $jacocoInit[223] = true;
            $jacocoInit[224] = true;
            for (Map.Entry next : this.mPesudoStyleMap.entrySet()) {
                $jacocoInit[226] = true;
                ArrayMap arrayMap = new ArrayMap();
                $jacocoInit[227] = true;
                arrayMap.putAll((Map) next.getValue());
                $jacocoInit[228] = true;
                wXStyle.mPesudoStyleMap.put(next.getKey(), arrayMap);
                $jacocoInit[229] = true;
            }
            $jacocoInit[225] = true;
        }
        if (this.mPesudoResetStyleMap == null) {
            $jacocoInit[230] = true;
        } else {
            $jacocoInit[231] = true;
            wXStyle.mPesudoResetStyleMap = new ArrayMap();
            $jacocoInit[232] = true;
            wXStyle.mPesudoResetStyleMap.putAll(this.mPesudoResetStyleMap);
            $jacocoInit[233] = true;
        }
        $jacocoInit[234] = true;
        return wXStyle;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        String obj = this.mStyles.toString();
        $jacocoInit[235] = true;
        return obj;
    }
}
