package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import android.view.View;
import com.libra.virtualview.common.StringBase;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import com.taobao.weex.utils.WXUtils;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.CharUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BasicComponentData<T extends View> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXAttr mAttributes;
    private CSSShorthand mBorders;
    public String mComponentType;
    private WXEvent mEvents;
    private CSSShorthand mMargins;
    private CSSShorthand mPaddings;
    public String mParentRef;
    public String mRef;
    private WXStyle mStyles;
    private long renderObjectPr = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8253397271324015709L, "com/taobao/weex/ui/action/BasicComponentData", 159);
        $jacocoData = a2;
        return a2;
    }

    public BasicComponentData(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRef = str;
        this.mComponentType = str2;
        this.mParentRef = str3;
        $jacocoInit[0] = true;
    }

    public void addStyle(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        addStyle(map, false);
        $jacocoInit[1] = true;
    }

    public final void addStyle(Map<String, Object> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[2] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[3] = true;
        } else {
            if (this.mStyles == null) {
                $jacocoInit[5] = true;
                this.mStyles = new WXStyle(map);
                $jacocoInit[6] = true;
            } else {
                this.mStyles.putAll(map, z);
                $jacocoInit[7] = true;
            }
            $jacocoInit[8] = true;
            return;
        }
        $jacocoInit[4] = true;
    }

    public final void addAttr(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[9] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[10] = true;
        } else {
            if (this.mAttributes == null) {
                $jacocoInit[12] = true;
                this.mAttributes = new WXAttr(map, 0);
                $jacocoInit[13] = true;
            } else {
                this.mAttributes.putAll(map);
                $jacocoInit[14] = true;
            }
            $jacocoInit[15] = true;
            return;
        }
        $jacocoInit[11] = true;
    }

    public final void addEvent(Set<String> set) {
        boolean[] $jacocoInit = $jacocoInit();
        if (set == null) {
            $jacocoInit[16] = true;
        } else if (set.isEmpty()) {
            $jacocoInit[17] = true;
        } else {
            if (this.mEvents != null) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
                this.mEvents = new WXEvent();
                $jacocoInit[21] = true;
            }
            this.mEvents.addAll(set);
            $jacocoInit[22] = true;
            return;
        }
        $jacocoInit[18] = true;
    }

    public final void addShorthand(float[] fArr, CSSShorthand.TYPE type) {
        boolean[] $jacocoInit = $jacocoInit();
        if (fArr != null) {
            $jacocoInit[23] = true;
        } else {
            fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
            $jacocoInit[24] = true;
        }
        if (fArr.length == 4) {
            $jacocoInit[26] = true;
            switch (type) {
                case MARGIN:
                    if (this.mMargins != null) {
                        this.mMargins.replace(fArr);
                        $jacocoInit[30] = true;
                        break;
                    } else {
                        $jacocoInit[28] = true;
                        this.mMargins = new CSSShorthand(fArr);
                        $jacocoInit[29] = true;
                        break;
                    }
                case PADDING:
                    if (this.mPaddings != null) {
                        this.mPaddings.replace(fArr);
                        $jacocoInit[33] = true;
                        break;
                    } else {
                        $jacocoInit[31] = true;
                        this.mPaddings = new CSSShorthand(fArr);
                        $jacocoInit[32] = true;
                        break;
                    }
                case BORDER:
                    if (this.mBorders != null) {
                        this.mBorders.replace(fArr);
                        $jacocoInit[36] = true;
                        break;
                    } else {
                        $jacocoInit[34] = true;
                        this.mBorders = new CSSShorthand(fArr);
                        $jacocoInit[35] = true;
                        break;
                    }
                default:
                    $jacocoInit[27] = true;
                    break;
            }
        } else {
            $jacocoInit[25] = true;
        }
        $jacocoInit[37] = true;
    }

    public final void addShorthand(Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[38] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            $jacocoInit[41] = true;
            for (Map.Entry<String, String> key : map.entrySet()) {
                $jacocoInit[43] = true;
                String str = (String) key.getKey();
                $jacocoInit[44] = true;
                char c = 65535;
                switch (str.hashCode()) {
                    case -1971292586:
                        if (str.equals("borderRightWidth")) {
                            c = 7;
                            $jacocoInit[61] = true;
                            break;
                        } else {
                            $jacocoInit[60] = true;
                            break;
                        }
                    case StringBase.q:
                        if (str.equals("paddingLeft")) {
                            c = 11;
                            $jacocoInit[69] = true;
                            break;
                        } else {
                            $jacocoInit[68] = true;
                            break;
                        }
                    case -1452542531:
                        if (str.equals("borderTopWidth")) {
                            c = 6;
                            $jacocoInit[59] = true;
                            break;
                        } else {
                            $jacocoInit[58] = true;
                            break;
                        }
                    case -1290574193:
                        if (str.equals("borderBottomWidth")) {
                            c = 8;
                            $jacocoInit[63] = true;
                            break;
                        } else {
                            $jacocoInit[62] = true;
                            break;
                        }
                    case -1081309778:
                        if (str.equals("margin")) {
                            c = 0;
                            $jacocoInit[47] = true;
                            break;
                        } else {
                            $jacocoInit[46] = true;
                            break;
                        }
                    case -1044792121:
                        if (str.equals("marginTop")) {
                            c = 2;
                            $jacocoInit[51] = true;
                            break;
                        } else {
                            $jacocoInit[50] = true;
                            break;
                        }
                    case StringBase.cf:
                        if (str.equals("padding")) {
                            c = 10;
                            $jacocoInit[67] = true;
                            break;
                        } else {
                            $jacocoInit[66] = true;
                            break;
                        }
                    case -289173127:
                        if (str.equals("marginBottom")) {
                            c = 4;
                            $jacocoInit[55] = true;
                            break;
                        } else {
                            $jacocoInit[54] = true;
                            break;
                        }
                    case -223992013:
                        if (str.equals("borderLeftWidth")) {
                            c = 9;
                            $jacocoInit[65] = true;
                            break;
                        } else {
                            $jacocoInit[64] = true;
                            break;
                        }
                    case StringBase.s:
                        if (str.equals("paddingTop")) {
                            c = 12;
                            $jacocoInit[71] = true;
                            break;
                        } else {
                            $jacocoInit[70] = true;
                            break;
                        }
                    case StringBase.t:
                        if (str.equals("paddingBottom")) {
                            c = 14;
                            $jacocoInit[75] = true;
                            break;
                        } else {
                            $jacocoInit[74] = true;
                            break;
                        }
                    case StringBase.r:
                        if (str.equals("paddingRight")) {
                            c = CharUtils.b;
                            $jacocoInit[73] = true;
                            break;
                        } else {
                            $jacocoInit[72] = true;
                            break;
                        }
                    case StringBase.bI:
                        if (str.equals("borderWidth")) {
                            c = 5;
                            $jacocoInit[57] = true;
                            break;
                        } else {
                            $jacocoInit[56] = true;
                            break;
                        }
                    case 975087886:
                        if (str.equals("marginRight")) {
                            c = 3;
                            $jacocoInit[53] = true;
                            break;
                        } else {
                            $jacocoInit[52] = true;
                            break;
                        }
                    case 1970934485:
                        if (str.equals("marginLeft")) {
                            $jacocoInit[49] = true;
                            c = 1;
                            break;
                        } else {
                            $jacocoInit[48] = true;
                            break;
                        }
                    default:
                        $jacocoInit[45] = true;
                        break;
                }
                switch (c) {
                    case 0:
                        addMargin(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[77] = true;
                        break;
                    case 1:
                        addMargin(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[78] = true;
                        break;
                    case 2:
                        addMargin(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[79] = true;
                        break;
                    case 3:
                        addMargin(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[80] = true;
                        break;
                    case 4:
                        addMargin(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[81] = true;
                        break;
                    case 5:
                        addBorder(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[82] = true;
                        break;
                    case 6:
                        addBorder(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[83] = true;
                        break;
                    case 7:
                        addBorder(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[84] = true;
                        break;
                    case 8:
                        addBorder(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[85] = true;
                        break;
                    case 9:
                        addBorder(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[86] = true;
                        break;
                    case 10:
                        addPadding(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[87] = true;
                        break;
                    case 11:
                        addPadding(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[88] = true;
                        break;
                    case 12:
                        addPadding(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[89] = true;
                        break;
                    case 13:
                        addPadding(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[90] = true;
                        break;
                    case 14:
                        addPadding(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(str)));
                        $jacocoInit[91] = true;
                        break;
                    default:
                        $jacocoInit[76] = true;
                        break;
                }
                $jacocoInit[92] = true;
            }
            $jacocoInit[42] = true;
        }
        $jacocoInit[93] = true;
    }

    private void addMargin(CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMargins != null) {
            $jacocoInit[94] = true;
        } else {
            $jacocoInit[95] = true;
            this.mMargins = new CSSShorthand();
            $jacocoInit[96] = true;
        }
        this.mMargins.set(edge, f);
        $jacocoInit[97] = true;
    }

    private void addPadding(CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPaddings != null) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            this.mPaddings = new CSSShorthand();
            $jacocoInit[100] = true;
        }
        this.mPaddings.set(edge, f);
        $jacocoInit[101] = true;
    }

    private void addBorder(CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorders != null) {
            $jacocoInit[102] = true;
        } else {
            $jacocoInit[103] = true;
            this.mBorders = new CSSShorthand();
            $jacocoInit[104] = true;
        }
        this.mBorders.set(edge, f);
        $jacocoInit[105] = true;
    }

    @NonNull
    public final WXStyle getStyles() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStyles != null) {
            $jacocoInit[106] = true;
        } else {
            $jacocoInit[107] = true;
            this.mStyles = new WXStyle();
            $jacocoInit[108] = true;
        }
        WXStyle wXStyle = this.mStyles;
        $jacocoInit[109] = true;
        return wXStyle;
    }

    @NonNull
    public final WXAttr getAttrs() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAttributes != null) {
            $jacocoInit[110] = true;
        } else {
            $jacocoInit[111] = true;
            this.mAttributes = new WXAttr();
            $jacocoInit[112] = true;
        }
        WXAttr wXAttr = this.mAttributes;
        $jacocoInit[113] = true;
        return wXAttr;
    }

    @NonNull
    public final WXEvent getEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEvents != null) {
            $jacocoInit[114] = true;
        } else {
            $jacocoInit[115] = true;
            this.mEvents = new WXEvent();
            $jacocoInit[116] = true;
        }
        WXEvent wXEvent = this.mEvents;
        $jacocoInit[117] = true;
        return wXEvent;
    }

    @NonNull
    public final CSSShorthand getMargin() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMargins != null) {
            $jacocoInit[118] = true;
        } else {
            $jacocoInit[119] = true;
            this.mMargins = new CSSShorthand();
            $jacocoInit[120] = true;
        }
        CSSShorthand cSSShorthand = this.mMargins;
        $jacocoInit[121] = true;
        return cSSShorthand;
    }

    @NonNull
    public final CSSShorthand getPadding() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPaddings != null) {
            $jacocoInit[122] = true;
        } else {
            $jacocoInit[123] = true;
            this.mPaddings = new CSSShorthand();
            $jacocoInit[124] = true;
        }
        CSSShorthand cSSShorthand = this.mPaddings;
        $jacocoInit[125] = true;
        return cSSShorthand;
    }

    @NonNull
    public CSSShorthand getBorder() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorders != null) {
            $jacocoInit[126] = true;
        } else {
            $jacocoInit[127] = true;
            this.mBorders = new CSSShorthand();
            $jacocoInit[128] = true;
        }
        CSSShorthand cSSShorthand = this.mBorders;
        $jacocoInit[129] = true;
        return cSSShorthand;
    }

    public final void setMargins(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMargins = cSSShorthand;
        $jacocoInit[130] = true;
    }

    public final void setPaddings(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPaddings = cSSShorthand;
        $jacocoInit[131] = true;
    }

    public final void setBorders(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBorders = cSSShorthand;
        $jacocoInit[132] = true;
    }

    public BasicComponentData clone() throws CloneNotSupportedException {
        boolean[] $jacocoInit = $jacocoInit();
        BasicComponentData basicComponentData = new BasicComponentData(this.mRef, this.mComponentType, this.mParentRef);
        $jacocoInit[133] = true;
        basicComponentData.setBorders(getBorder().clone());
        $jacocoInit[134] = true;
        basicComponentData.setMargins(getMargin().clone());
        $jacocoInit[135] = true;
        basicComponentData.setPaddings(getPadding().clone());
        if (this.mAttributes == null) {
            $jacocoInit[136] = true;
        } else {
            $jacocoInit[137] = true;
            basicComponentData.mAttributes = this.mAttributes.clone();
            $jacocoInit[138] = true;
        }
        if (this.mStyles == null) {
            $jacocoInit[139] = true;
        } else {
            $jacocoInit[140] = true;
            basicComponentData.mStyles = this.mStyles.clone();
            $jacocoInit[141] = true;
        }
        if (this.mEvents == null) {
            $jacocoInit[142] = true;
        } else {
            $jacocoInit[143] = true;
            basicComponentData.mEvents = this.mEvents.clone();
            $jacocoInit[144] = true;
        }
        if (this.renderObjectPr == 0) {
            $jacocoInit[145] = true;
        } else {
            $jacocoInit[146] = true;
            basicComponentData.setRenderObjectPr(NativeRenderObjectUtils.nativeCopyRenderObject(this.renderObjectPr));
            $jacocoInit[147] = true;
        }
        $jacocoInit[148] = true;
        return basicComponentData;
    }

    public long getRenderObjectPr() {
        boolean[] $jacocoInit = $jacocoInit();
        long j = this.renderObjectPr;
        $jacocoInit[149] = true;
        return j;
    }

    public boolean isRenderPtrEmpty() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.renderObjectPr == 0) {
            $jacocoInit[150] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[151] = true;
        }
        $jacocoInit[152] = true;
        return z;
    }

    public synchronized void setRenderObjectPr(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.renderObjectPr == j) {
            $jacocoInit[153] = true;
        } else if (this.renderObjectPr == 0) {
            this.renderObjectPr = j;
            $jacocoInit[156] = true;
        } else {
            $jacocoInit[154] = true;
            RuntimeException runtimeException = new RuntimeException("RenderObjectPr has " + j + " old renderObjectPtr " + this.renderObjectPr);
            $jacocoInit[155] = true;
            throw runtimeException;
        }
        $jacocoInit[157] = true;
    }
}
