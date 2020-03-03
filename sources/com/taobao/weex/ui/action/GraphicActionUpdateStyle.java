package com.taobao.weex.ui.action;

import android.support.v4.util.ArrayMap;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionUpdateStyle extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXComponent component;
    private boolean mIsCausedByPesudo;
    private Map<String, Object> mStyle;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4623056613060836578L, "com/taobao/weex/ui/action/GraphicActionUpdateStyle", 57);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GraphicActionUpdateStyle(WXSDKInstance wXSDKInstance, String str, Map<String, Object> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) {
        this(wXSDKInstance, str, map, map2, map3, map4, false);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionUpdateStyle(WXSDKInstance wXSDKInstance, String str, Map<String, Object> map, CSSShorthand cSSShorthand, CSSShorthand cSSShorthand2, CSSShorthand cSSShorthand3, boolean z) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyle = map;
        this.mIsCausedByPesudo = z;
        $jacocoInit[1] = true;
        this.component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (this.component == null) {
            $jacocoInit[2] = true;
            return;
        }
        if (this.mStyle == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            this.component.updateStyle(this.mStyle, this.mIsCausedByPesudo);
            $jacocoInit[5] = true;
            if (!map.containsKey("transform")) {
                $jacocoInit[6] = true;
            } else if (this.component.getTransition() != null) {
                $jacocoInit[7] = true;
            } else {
                $jacocoInit[8] = true;
                ArrayMap arrayMap = new ArrayMap(2);
                $jacocoInit[9] = true;
                arrayMap.put("transform", map.get("transform"));
                $jacocoInit[10] = true;
                arrayMap.put(Constants.Name.TRANSFORM_ORIGIN, map.get(Constants.Name.TRANSFORM_ORIGIN));
                $jacocoInit[11] = true;
                this.component.addAnimationForElement(arrayMap);
                $jacocoInit[12] = true;
            }
        }
        if (cSSShorthand == null) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            this.component.setPaddings(cSSShorthand);
            $jacocoInit[15] = true;
        }
        if (cSSShorthand2 == null) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            this.component.setMargins(cSSShorthand2);
            $jacocoInit[18] = true;
        }
        if (cSSShorthand3 == null) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            this.component.setBorders(cSSShorthand3);
            $jacocoInit[21] = true;
        }
        $jacocoInit[22] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionUpdateStyle(WXSDKInstance wXSDKInstance, String str, Map<String, Object> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, boolean z) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyle = map;
        this.mIsCausedByPesudo = z;
        $jacocoInit[23] = true;
        this.component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (this.component == null) {
            $jacocoInit[24] = true;
            return;
        }
        if (this.mStyle == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            this.component.addStyle(this.mStyle, this.mIsCausedByPesudo);
            $jacocoInit[27] = true;
            if (!map.containsKey("transform")) {
                $jacocoInit[28] = true;
            } else if (this.component.getTransition() != null) {
                $jacocoInit[29] = true;
            } else {
                $jacocoInit[30] = true;
                ArrayMap arrayMap = new ArrayMap(2);
                $jacocoInit[31] = true;
                arrayMap.put("transform", map.get("transform"));
                $jacocoInit[32] = true;
                arrayMap.put(Constants.Name.TRANSFORM_ORIGIN, map.get(Constants.Name.TRANSFORM_ORIGIN));
                $jacocoInit[33] = true;
                this.component.addAnimationForElement(arrayMap);
                $jacocoInit[34] = true;
                WXBridgeManager.getInstance().markDirty(this.component.getInstanceId(), this.component.getRef(), true);
                $jacocoInit[35] = true;
            }
        }
        if (map2 == null) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            this.component.addShorthand(map2);
            $jacocoInit[38] = true;
        }
        if (map3 == null) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            this.component.addShorthand(map3);
            $jacocoInit[41] = true;
        }
        if (map4 == null) {
            $jacocoInit[42] = true;
        } else {
            $jacocoInit[43] = true;
            this.component.addShorthand(map4);
            $jacocoInit[44] = true;
        }
        $jacocoInit[45] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component == null) {
            $jacocoInit[46] = true;
        } else if (this.mStyle == null) {
            $jacocoInit[47] = true;
        } else {
            if (this.component.getTransition() != null) {
                $jacocoInit[49] = true;
                this.component.getTransition().updateTranstionParams(this.mStyle);
                $jacocoInit[50] = true;
                if (!this.component.getTransition().hasTransitionProperty(this.mStyle)) {
                    $jacocoInit[51] = true;
                } else {
                    $jacocoInit[52] = true;
                    this.component.getTransition().startTransition(this.mStyle);
                    $jacocoInit[53] = true;
                }
            } else {
                this.component.setTransition(WXTransition.fromMap(this.mStyle, this.component));
                $jacocoInit[54] = true;
                this.component.updateStyles(this.mStyle);
                $jacocoInit[55] = true;
            }
            $jacocoInit[56] = true;
            return;
        }
        $jacocoInit[48] = true;
    }
}
