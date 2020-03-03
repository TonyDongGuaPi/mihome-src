package com.taobao.weex.dom;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.UiThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXImageSharpen;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.WXStatement;
import com.taobao.weex.el.parse.Parser;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXAttr implements Cloneable, Map<String, Object> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long serialVersionUID = -2619357510079360946L;
    @NonNull
    private Map<String, Object> attr;
    private ArrayMap<String, Object> mBindingAttrs;
    private WXStatement mStatement;
    private Map<String, Object> writeAttr;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7688278248196942939L, "com/taobao/weex/dom/WXAttr", TbsListener.ErrorCode.RENAME_EXCEPTION);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        boolean[] $jacocoInit = $jacocoInit();
        Object put = put((String) obj, obj2);
        $jacocoInit[218] = true;
        return put;
    }

    public WXAttr() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.attr = new HashMap();
        $jacocoInit[1] = true;
    }

    public WXAttr(@NonNull Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.attr = map;
        $jacocoInit[2] = true;
    }

    public WXAttr(@NonNull Map<String, Object> map, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.attr = map;
        $jacocoInit[3] = true;
    }

    public static String getPrefix(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[4] = true;
            return null;
        }
        Object obj = map.get(Constants.Name.PREFIX);
        if (obj == null) {
            $jacocoInit[5] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[6] = true;
        return obj2;
    }

    public static String getSuffix(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[7] = true;
            return null;
        }
        Object obj = map.get(Constants.Name.SUFFIX);
        if (obj == null) {
            $jacocoInit[8] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[9] = true;
        return obj2;
    }

    public static String getValue(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[10] = true;
            return null;
        }
        Object obj = map.get("value");
        if (obj != null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            obj = map.get("content");
            if (obj != null) {
                $jacocoInit[13] = true;
            } else {
                $jacocoInit[14] = true;
                return null;
            }
        }
        String obj2 = obj.toString();
        $jacocoInit[15] = true;
        return obj2;
    }

    public WXImageQuality getImageQuality() {
        Object obj;
        WXImageQuality wXImageQuality;
        boolean[] $jacocoInit = $jacocoInit();
        if (containsKey(Constants.Name.QUALITY)) {
            obj = get(Constants.Name.QUALITY);
            $jacocoInit[16] = true;
        } else {
            obj = get(Constants.Name.IMAGE_QUALITY);
            $jacocoInit[17] = true;
        }
        WXImageQuality wXImageQuality2 = WXImageQuality.AUTO;
        $jacocoInit[18] = true;
        if (obj == null) {
            $jacocoInit[19] = true;
        } else {
            String obj2 = obj.toString();
            if (TextUtils.isEmpty(obj2)) {
                $jacocoInit[20] = true;
            } else {
                try {
                    $jacocoInit[21] = true;
                    wXImageQuality = WXImageQuality.valueOf(obj2.toUpperCase(Locale.US));
                    $jacocoInit[22] = true;
                } catch (IllegalArgumentException unused) {
                    $jacocoInit[23] = true;
                    WXLogUtils.e(CommonShareActivity.SHARE_IMAGE, "Invalid value image quality. Only low, normal, high, original are valid");
                    $jacocoInit[24] = true;
                }
                $jacocoInit[25] = true;
                return wXImageQuality;
            }
        }
        wXImageQuality = wXImageQuality2;
        $jacocoInit[25] = true;
        return wXImageQuality;
    }

    public WXImageSharpen getImageSharpen() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.SHARPEN);
        if (obj != null) {
            $jacocoInit[26] = true;
        } else {
            $jacocoInit[27] = true;
            obj = get(Constants.Name.IMAGE_SHARPEN);
            $jacocoInit[28] = true;
        }
        if (obj == null) {
            WXImageSharpen wXImageSharpen = WXImageSharpen.UNSHARPEN;
            $jacocoInit[29] = true;
            return wXImageSharpen;
        }
        String obj2 = obj.toString();
        WXImageSharpen wXImageSharpen2 = WXImageSharpen.UNSHARPEN;
        $jacocoInit[30] = true;
        if (!obj2.equals(Constants.Name.SHARPEN)) {
            $jacocoInit[31] = true;
        } else {
            wXImageSharpen2 = WXImageSharpen.SHARPEN;
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
        return wXImageSharpen2;
    }

    public String getImageSrc() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("src");
        if (obj == null) {
            $jacocoInit[34] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[35] = true;
        return obj2;
    }

    public boolean canRecycled() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.RECYCLE);
        if (obj != null) {
            $jacocoInit[36] = true;
            try {
                boolean parseBoolean = Boolean.parseBoolean(String.valueOf(obj));
                $jacocoInit[38] = true;
                return parseBoolean;
            } catch (Exception e) {
                $jacocoInit[39] = true;
                WXLogUtils.e("[WXAttr] recycle:", (Throwable) e);
                $jacocoInit[40] = true;
                return true;
            }
        } else {
            $jacocoInit[37] = true;
            return true;
        }
    }

    public boolean showIndicators() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.SHOW_INDICATORS);
        if (obj != null) {
            $jacocoInit[41] = true;
            try {
                boolean parseBoolean = Boolean.parseBoolean(String.valueOf(obj));
                $jacocoInit[43] = true;
                return parseBoolean;
            } catch (Exception e) {
                $jacocoInit[44] = true;
                WXLogUtils.e("[WXAttr] showIndicators:", (Throwable) e);
                $jacocoInit[45] = true;
                return true;
            }
        } else {
            $jacocoInit[42] = true;
            return true;
        }
    }

    public boolean autoPlay() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.AUTO_PLAY);
        if (obj != null) {
            $jacocoInit[46] = true;
            try {
                boolean parseBoolean = Boolean.parseBoolean(String.valueOf(obj));
                $jacocoInit[48] = true;
                return parseBoolean;
            } catch (Exception e) {
                $jacocoInit[49] = true;
                WXLogUtils.e("[WXAttr] autoPlay:", (Throwable) e);
                $jacocoInit[50] = true;
                return false;
            }
        } else {
            $jacocoInit[47] = true;
            return false;
        }
    }

    public String getScope() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("scope");
        if (obj == null) {
            $jacocoInit[51] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[52] = true;
        return obj2;
    }

    public String getLoadMoreRetry() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.LOADMORERETRY);
        if (obj == null) {
            $jacocoInit[53] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[54] = true;
        return obj2;
    }

    public String getLoadMoreOffset() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("loadmoreoffset");
        if (obj == null) {
            $jacocoInit[55] = true;
            return null;
        }
        String obj2 = obj.toString();
        $jacocoInit[56] = true;
        return obj2;
    }

    public String optString(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!containsKey(str)) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            Object obj = get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                $jacocoInit[59] = true;
                return str2;
            } else if (obj == null) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                String valueOf = String.valueOf(obj);
                $jacocoInit[62] = true;
                return valueOf;
            }
        }
        $jacocoInit[63] = true;
        return "";
    }

    public boolean getIsRecycleImage() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.RECYCLE_IMAGE);
        if (obj != null) {
            $jacocoInit[64] = true;
            try {
                boolean parseBoolean = Boolean.parseBoolean(String.valueOf(obj));
                $jacocoInit[66] = true;
                return parseBoolean;
            } catch (Exception e) {
                $jacocoInit[67] = true;
                WXLogUtils.e("[WXAttr] recycleImage:", (Throwable) e);
                $jacocoInit[68] = true;
                return false;
            }
        } else {
            $jacocoInit[65] = true;
            return true;
        }
    }

    public String getScrollDirection() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.SCROLL_DIRECTION);
        if (obj == null) {
            $jacocoInit[69] = true;
            return "vertical";
        }
        String obj2 = obj.toString();
        $jacocoInit[70] = true;
        return obj2;
    }

    public int getOrientation() {
        boolean[] $jacocoInit = $jacocoInit();
        String scrollDirection = getScrollDirection();
        $jacocoInit[71] = true;
        if (TextUtils.isEmpty(scrollDirection)) {
            $jacocoInit[72] = true;
        } else {
            $jacocoInit[73] = true;
            if (!scrollDirection.equals("horizontal")) {
                $jacocoInit[74] = true;
            } else {
                $jacocoInit[75] = true;
                return 0;
            }
        }
        Object obj = get("orientation");
        $jacocoInit[76] = true;
        if (obj == null) {
            $jacocoInit[77] = true;
        } else if (!"horizontal".equals(obj.toString())) {
            $jacocoInit[78] = true;
        } else {
            $jacocoInit[79] = true;
            return 0;
        }
        $jacocoInit[80] = true;
        return 1;
    }

    public float getElevation(int i) {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get("elevation");
        if (obj == null) {
            $jacocoInit[81] = true;
            f = Float.NaN;
        } else {
            $jacocoInit[82] = true;
            String obj2 = obj.toString();
            $jacocoInit[83] = true;
            if (!TextUtils.isEmpty(obj2)) {
                $jacocoInit[84] = true;
                f = WXViewUtils.getRealSubPxByWidth(WXUtils.getFloat(obj2), i);
                $jacocoInit[85] = true;
            } else {
                f = 0.0f;
                $jacocoInit[86] = true;
            }
        }
        $jacocoInit[87] = true;
        return f;
    }

    public float getColumnWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.COLUMN_WIDTH);
        if (obj == null) {
            $jacocoInit[88] = true;
            return -1.0f;
        }
        String valueOf = String.valueOf(obj);
        $jacocoInit[89] = true;
        if (!"auto".equals(valueOf)) {
            $jacocoInit[90] = true;
            try {
                float parseFloat = Float.parseFloat(valueOf);
                if (parseFloat > 0.0f) {
                    $jacocoInit[92] = true;
                } else {
                    $jacocoInit[93] = true;
                    parseFloat = 0.0f;
                }
                $jacocoInit[94] = true;
                return parseFloat;
            } catch (Exception e) {
                $jacocoInit[95] = true;
                WXLogUtils.e("[WXAttr] getColumnWidth:", (Throwable) e);
                $jacocoInit[96] = true;
                return -1.0f;
            }
        } else {
            $jacocoInit[91] = true;
            return -1.0f;
        }
    }

    public int getColumnCount() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.COLUMN_COUNT);
        if (obj == null) {
            $jacocoInit[97] = true;
            return -1;
        }
        String valueOf = String.valueOf(obj);
        $jacocoInit[98] = true;
        if (!"auto".equals(valueOf)) {
            $jacocoInit[99] = true;
            try {
                int parseInt = Integer.parseInt(valueOf);
                if (parseInt > 0) {
                    $jacocoInit[101] = true;
                } else {
                    $jacocoInit[102] = true;
                    parseInt = -1;
                }
                $jacocoInit[103] = true;
                return parseInt;
            } catch (Exception e) {
                $jacocoInit[104] = true;
                WXLogUtils.e("[WXAttr] getColumnCount:", (Throwable) e);
                $jacocoInit[105] = true;
                return -1;
            }
        } else {
            $jacocoInit[100] = true;
            return -1;
        }
    }

    public float getColumnGap() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = get(Constants.Name.COLUMN_GAP);
        if (obj == null) {
            $jacocoInit[106] = true;
            return 32.0f;
        }
        String valueOf = String.valueOf(obj);
        $jacocoInit[107] = true;
        if (!"normal".equals(valueOf)) {
            $jacocoInit[108] = true;
            try {
                float parseFloat = Float.parseFloat(valueOf);
                if (parseFloat >= 0.0f) {
                    $jacocoInit[110] = true;
                } else {
                    parseFloat = -1.0f;
                    $jacocoInit[111] = true;
                }
                $jacocoInit[112] = true;
                return parseFloat;
            } catch (Exception e) {
                $jacocoInit[113] = true;
                WXLogUtils.e("[WXAttr] getColumnGap:", (Throwable) e);
                $jacocoInit[114] = true;
                return 32.0f;
            }
        } else {
            $jacocoInit[109] = true;
            return 32.0f;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[Catch:{ Exception -> 0x006c }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057 A[Catch:{ Exception -> 0x006c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getLayoutType() {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            java.lang.String r1 = "layout"
            java.lang.Object r1 = r6.get(r1)
            r2 = 1
            if (r1 == 0) goto L_0x007b
            r3 = 115(0x73, float:1.61E-43)
            r0[r3] = r2
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x006c }
            r3 = -1
            int r4 = r1.hashCode()     // Catch:{ Exception -> 0x006c }
            r5 = 3181382(0x308b46, float:4.458066E-39)
            if (r4 == r5) goto L_0x003c
            r5 = 674874986(0x2839c66a, float:1.0312587E-14)
            if (r4 == r5) goto L_0x0029
            r1 = 117(0x75, float:1.64E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
            goto L_0x0048
        L_0x0029:
            java.lang.String r4 = "multi-column"
            boolean r1 = r1.equals(r4)     // Catch:{ Exception -> 0x006c }
            if (r1 != 0) goto L_0x0036
            r1 = 118(0x76, float:1.65E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
            goto L_0x0048
        L_0x0036:
            r1 = 0
            r3 = 119(0x77, float:1.67E-43)
            r0[r3] = r2     // Catch:{ Exception -> 0x006c }
            goto L_0x004f
        L_0x003c:
            java.lang.String r4 = "grid"
            boolean r1 = r1.equals(r4)     // Catch:{ Exception -> 0x006c }
            if (r1 != 0) goto L_0x004a
            r1 = 120(0x78, float:1.68E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
        L_0x0048:
            r1 = -1
            goto L_0x004f
        L_0x004a:
            r1 = 121(0x79, float:1.7E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
            r1 = 1
        L_0x004f:
            switch(r1) {
                case 0: goto L_0x0061;
                case 1: goto L_0x0057;
                default: goto L_0x0052;
            }     // Catch:{ Exception -> 0x006c }
        L_0x0052:
            r1 = 122(0x7a, float:1.71E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
            goto L_0x0067
        L_0x0057:
            r1 = 123(0x7b, float:1.72E-43)
            r0[r1] = r2     // Catch:{ Exception -> 0x006c }
            r1 = 2
            r3 = 125(0x7d, float:1.75E-43)
            r0[r3] = r2
            return r1
        L_0x0061:
            r1 = 3
            r3 = 124(0x7c, float:1.74E-43)
            r0[r3] = r2
            return r1
        L_0x0067:
            r1 = 126(0x7e, float:1.77E-43)
            r0[r1] = r2
            return r2
        L_0x006c:
            r1 = move-exception
            r3 = 127(0x7f, float:1.78E-43)
            r0[r3] = r2
            java.lang.String r3 = "[WXAttr] getLayoutType:"
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r3, (java.lang.Throwable) r1)
            r1 = 128(0x80, float:1.794E-43)
            r0[r1] = r2
            return r2
        L_0x007b:
            r1 = 116(0x74, float:1.63E-43)
            r0[r1] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.WXAttr.getLayoutType():int");
    }

    public boolean equals(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean equals = this.attr.equals(obj);
        $jacocoInit[129] = true;
        return equals;
    }

    public int hashCode() {
        boolean[] $jacocoInit = $jacocoInit();
        int hashCode = this.attr.hashCode();
        $jacocoInit[130] = true;
        return hashCode;
    }

    public void clear() {
        boolean[] $jacocoInit = $jacocoInit();
        this.attr.clear();
        $jacocoInit[131] = true;
    }

    public boolean containsKey(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsKey = this.attr.containsKey(obj);
        $jacocoInit[132] = true;
        return containsKey;
    }

    public boolean containsValue(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsValue = this.attr.containsValue(obj);
        $jacocoInit[133] = true;
        return containsValue;
    }

    @NonNull
    public Set<Map.Entry<String, Object>> entrySet() {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry<String, Object>> entrySet = this.attr.entrySet();
        $jacocoInit[134] = true;
        return entrySet;
    }

    public Object get(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, Object> map = this.writeAttr;
        if (map == null) {
            $jacocoInit[135] = true;
        } else {
            $jacocoInit[136] = true;
            Object obj2 = map.get(obj);
            if (obj2 == null) {
                $jacocoInit[137] = true;
            } else {
                $jacocoInit[138] = true;
                return obj2;
            }
        }
        Object obj3 = this.attr.get(obj);
        $jacocoInit[139] = true;
        return obj3;
    }

    public boolean isEmpty() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isEmpty = this.attr.isEmpty();
        $jacocoInit[140] = true;
        return isEmpty;
    }

    @NonNull
    public Set<String> keySet() {
        boolean[] $jacocoInit = $jacocoInit();
        Set<String> keySet = this.attr.keySet();
        $jacocoInit[141] = true;
        return keySet;
    }

    public Object put(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (addBindingAttrIfStatement(str, obj)) {
            $jacocoInit[142] = true;
            return null;
        }
        Object put = this.attr.put(str, obj);
        $jacocoInit[143] = true;
        return put;
    }

    public void putAll(Map<? extends String, ?> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.writeAttr != null) {
            $jacocoInit[144] = true;
        } else {
            $jacocoInit[145] = true;
            this.writeAttr = new ArrayMap();
            $jacocoInit[146] = true;
        }
        this.writeAttr.putAll(map);
        $jacocoInit[147] = true;
    }

    public Object remove(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Object remove = this.attr.remove(obj);
        $jacocoInit[148] = true;
        return remove;
    }

    public int size() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.attr.size();
        $jacocoInit[149] = true;
        return size;
    }

    @NonNull
    public Collection<Object> values() {
        boolean[] $jacocoInit = $jacocoInit();
        Collection<Object> values = this.attr.values();
        $jacocoInit[150] = true;
        return values;
    }

    public ArrayMap<String, Object> getBindingAttrs() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayMap<String, Object> arrayMap = this.mBindingAttrs;
        $jacocoInit[151] = true;
        return arrayMap;
    }

    public WXStatement getStatement() {
        boolean[] $jacocoInit = $jacocoInit();
        WXStatement wXStatement = this.mStatement;
        $jacocoInit[152] = true;
        return wXStatement;
    }

    public void setBindingAttrs(ArrayMap<String, Object> arrayMap) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBindingAttrs = arrayMap;
        $jacocoInit[153] = true;
    }

    public void setStatement(WXStatement wXStatement) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatement = wXStatement;
        $jacocoInit[154] = true;
    }

    public void parseStatements() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.attr == null) {
            $jacocoInit[155] = true;
        } else {
            $jacocoInit[156] = true;
            this.attr = filterStatementsFromAttrs(this.attr);
            $jacocoInit[157] = true;
        }
        $jacocoInit[158] = true;
    }

    private Map<String, Object> filterStatementsFromAttrs(Map map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[159] = true;
        } else if (map.size() == 0) {
            $jacocoInit[160] = true;
        } else {
            Set entrySet = map.entrySet();
            $jacocoInit[162] = true;
            Iterator it = entrySet.iterator();
            $jacocoInit[163] = true;
            while (it.hasNext()) {
                $jacocoInit[164] = true;
                Map.Entry entry = (Map.Entry) it.next();
                $jacocoInit[165] = true;
                if (ELUtils.COMPONENT_PROPS.equals(entry.getKey())) {
                    $jacocoInit[166] = true;
                    Object bindingBlock = ELUtils.bindingBlock(entry.getValue());
                    $jacocoInit[167] = true;
                    entry.setValue(bindingBlock);
                    $jacocoInit[168] = true;
                } else {
                    if (!addBindingAttrIfStatement((String) entry.getKey(), entry.getValue())) {
                        $jacocoInit[169] = true;
                    } else {
                        $jacocoInit[170] = true;
                        it.remove();
                        $jacocoInit[171] = true;
                    }
                    $jacocoInit[172] = true;
                }
            }
            $jacocoInit[173] = true;
            return map;
        }
        $jacocoInit[161] = true;
        return map;
    }

    private boolean addBindingAttrIfStatement(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        String[] strArr = ELUtils.EXCLUDES_BINDING;
        int length = strArr.length;
        $jacocoInit[174] = true;
        int i = 0;
        while (i < length) {
            String str2 = strArr[i];
            $jacocoInit[175] = true;
            if (str.equals(str2)) {
                $jacocoInit[176] = true;
                return false;
            }
            i++;
            $jacocoInit[177] = true;
        }
        if (ELUtils.isBinding(obj)) {
            if (this.mBindingAttrs != null) {
                $jacocoInit[178] = true;
            } else {
                $jacocoInit[179] = true;
                this.mBindingAttrs = new ArrayMap<>();
                $jacocoInit[180] = true;
            }
            Object bindingBlock = ELUtils.bindingBlock(obj);
            $jacocoInit[181] = true;
            this.mBindingAttrs.put(str, bindingBlock);
            $jacocoInit[182] = true;
            return true;
        } else if (WXStatement.WX_IF.equals(str)) {
            if (this.mStatement != null) {
                $jacocoInit[183] = true;
            } else {
                $jacocoInit[184] = true;
                this.mStatement = new WXStatement();
                $jacocoInit[185] = true;
            }
            if (obj == null) {
                $jacocoInit[186] = true;
            } else {
                $jacocoInit[187] = true;
                this.mStatement.put(str, Parser.parse(obj.toString()));
                $jacocoInit[188] = true;
            }
            $jacocoInit[189] = true;
            return true;
        } else {
            if (!WXStatement.WX_FOR.equals(str)) {
                $jacocoInit[190] = true;
            } else {
                if (this.mStatement != null) {
                    $jacocoInit[191] = true;
                } else {
                    $jacocoInit[192] = true;
                    this.mStatement = new WXStatement();
                    $jacocoInit[193] = true;
                }
                Object vforBlock = ELUtils.vforBlock(obj);
                if (vforBlock == null) {
                    $jacocoInit[194] = true;
                } else {
                    $jacocoInit[195] = true;
                    this.mStatement.put(str, vforBlock);
                    $jacocoInit[196] = true;
                    return true;
                }
            }
            if (!WXStatement.WX_ONCE.equals(str)) {
                $jacocoInit[197] = true;
            } else {
                if (this.mStatement != null) {
                    $jacocoInit[198] = true;
                } else {
                    $jacocoInit[199] = true;
                    this.mStatement = new WXStatement();
                    $jacocoInit[200] = true;
                }
                this.mStatement.put(str, true);
                $jacocoInit[201] = true;
            }
            $jacocoInit[202] = true;
            return false;
        }
    }

    public void skipFilterPutAll(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.attr.putAll(map);
        $jacocoInit[203] = true;
    }

    @UiThread
    public void mergeAttr() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.writeAttr == null) {
            $jacocoInit[204] = true;
        } else {
            $jacocoInit[205] = true;
            this.attr.putAll(this.writeAttr);
            this.writeAttr = null;
            $jacocoInit[206] = true;
        }
        $jacocoInit[207] = true;
    }

    public WXAttr clone() {
        boolean[] $jacocoInit = $jacocoInit();
        WXAttr wXAttr = new WXAttr();
        $jacocoInit[208] = true;
        wXAttr.skipFilterPutAll(this.attr);
        if (this.mBindingAttrs == null) {
            $jacocoInit[209] = true;
        } else {
            $jacocoInit[210] = true;
            wXAttr.mBindingAttrs = new ArrayMap<>((SimpleArrayMap) this.mBindingAttrs);
            $jacocoInit[211] = true;
        }
        if (this.mStatement == null) {
            $jacocoInit[212] = true;
        } else {
            $jacocoInit[213] = true;
            wXAttr.mStatement = new WXStatement(this.mStatement);
            $jacocoInit[214] = true;
        }
        $jacocoInit[215] = true;
        return wXAttr;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        String obj = this.attr.toString();
        $jacocoInit[216] = true;
        return obj;
    }
}
