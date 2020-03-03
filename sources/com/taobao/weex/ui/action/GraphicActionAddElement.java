package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.performance.WXAnalyzerDataTransfer;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionAddElement extends GraphicActionAbstractAddElement {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXComponent child;
    private boolean isLayoutRTL;
    private GraphicPosition layoutPosition;
    private GraphicSize layoutSize;
    private WXVContainer parent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3936282468275625096L, "com/taobao/weex/ui/action/GraphicActionAddElement", 96);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAddElement(@NonNull WXSDKInstance wXSDKInstance, String str, String str2, String str3, int i, Map<String, String> map, Map<String, String> map2, Set<String> set, float[] fArr, float[] fArr2, float[] fArr3) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponentType = str2;
        this.mParentRef = str3;
        this.mIndex = i;
        this.mStyle = map;
        this.mAttributes = map2;
        this.mEvents = set;
        this.mPaddings = fArr2;
        this.mMargins = fArr;
        this.mBorders = fArr3;
        $jacocoInit[0] = true;
        if (wXSDKInstance.getContext() == null) {
            $jacocoInit[1] = true;
            return;
        }
        if (!WXAnalyzerDataTransfer.isInteractionLogOpen()) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            Log.d(WXAnalyzerDataTransfer.INTERACTION_TAG, "[client][addelementStart]" + wXSDKInstance.getInstanceId() + "," + str2 + "," + str);
            try {
                $jacocoInit[4] = true;
            } catch (ClassCastException unused) {
                $jacocoInit[17] = true;
                ArrayMap arrayMap = new ArrayMap();
                $jacocoInit[18] = true;
                WXRenderManager wXRenderManager = WXSDKManager.getInstance().getWXRenderManager();
                $jacocoInit[19] = true;
                WXComponent wXComponent = wXRenderManager.getWXComponent(getPageId(), this.mParentRef);
                $jacocoInit[20] = true;
                if (this.mStyle == null) {
                    $jacocoInit[21] = true;
                } else if (this.mStyle.isEmpty()) {
                    $jacocoInit[22] = true;
                } else {
                    $jacocoInit[23] = true;
                    arrayMap.put("child.style", this.mStyle.toString());
                    $jacocoInit[24] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[25] = true;
                } else if (wXComponent.getStyles() == null) {
                    $jacocoInit[26] = true;
                } else if (wXComponent.getStyles().isEmpty()) {
                    $jacocoInit[27] = true;
                } else {
                    $jacocoInit[28] = true;
                    arrayMap.put("parent.style", wXComponent.getStyles().toString());
                    $jacocoInit[29] = true;
                }
                if (this.mAttributes == null) {
                    $jacocoInit[30] = true;
                } else if (this.mAttributes.isEmpty()) {
                    $jacocoInit[31] = true;
                } else {
                    $jacocoInit[32] = true;
                    arrayMap.put("child.attr", this.mAttributes.toString());
                    $jacocoInit[33] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[34] = true;
                } else if (wXComponent.getAttrs() == null) {
                    $jacocoInit[35] = true;
                } else if (wXComponent.getAttrs().isEmpty()) {
                    $jacocoInit[36] = true;
                } else {
                    $jacocoInit[37] = true;
                    arrayMap.put("parent.attr", wXComponent.getAttrs().toString());
                    $jacocoInit[38] = true;
                }
                if (this.mEvents == null) {
                    $jacocoInit[39] = true;
                } else if (this.mEvents.isEmpty()) {
                    $jacocoInit[40] = true;
                } else {
                    $jacocoInit[41] = true;
                    arrayMap.put("child.event", this.mEvents.toString());
                    $jacocoInit[42] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[43] = true;
                } else if (wXComponent.getEvents() == null) {
                    $jacocoInit[44] = true;
                } else if (wXComponent.getEvents().isEmpty()) {
                    $jacocoInit[45] = true;
                } else {
                    $jacocoInit[46] = true;
                    arrayMap.put("parent.event", wXComponent.getEvents().toString());
                    $jacocoInit[47] = true;
                }
                if (this.mMargins == null) {
                    $jacocoInit[48] = true;
                } else if (this.mMargins.length <= 0) {
                    $jacocoInit[49] = true;
                } else {
                    $jacocoInit[50] = true;
                    arrayMap.put("child.margin", Arrays.toString(this.mMargins));
                    $jacocoInit[51] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[52] = true;
                } else if (wXComponent.getMargin() == null) {
                    $jacocoInit[53] = true;
                } else {
                    $jacocoInit[54] = true;
                    arrayMap.put("parent.margin", wXComponent.getMargin().toString());
                    $jacocoInit[55] = true;
                }
                if (this.mPaddings == null) {
                    $jacocoInit[56] = true;
                } else if (this.mPaddings.length <= 0) {
                    $jacocoInit[57] = true;
                } else {
                    $jacocoInit[58] = true;
                    arrayMap.put("child.padding", Arrays.toString(this.mPaddings));
                    $jacocoInit[59] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[60] = true;
                } else if (wXComponent.getPadding() == null) {
                    $jacocoInit[61] = true;
                } else {
                    $jacocoInit[62] = true;
                    arrayMap.put("parent.padding", wXComponent.getPadding().toString());
                    $jacocoInit[63] = true;
                }
                if (this.mBorders == null) {
                    $jacocoInit[64] = true;
                } else if (this.mBorders.length <= 0) {
                    $jacocoInit[65] = true;
                } else {
                    $jacocoInit[66] = true;
                    arrayMap.put("child.border", Arrays.toString(this.mBorders));
                    $jacocoInit[67] = true;
                }
                if (wXComponent == null) {
                    $jacocoInit[68] = true;
                } else if (wXComponent.getBorder() == null) {
                    $jacocoInit[69] = true;
                } else {
                    $jacocoInit[70] = true;
                    arrayMap.put("parent.border", wXComponent.getBorder().toString());
                    $jacocoInit[71] = true;
                }
                String instanceId = wXSDKInstance.getInstanceId();
                WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_CONTAINER_TYPE;
                Locale locale = Locale.ENGLISH;
                $jacocoInit[72] = true;
                Object[] objArr = {str2, WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), this.mParentRef).getComponentType()};
                $jacocoInit[73] = true;
                String format = String.format(locale, "You are trying to add a %s to a %2$s, which is illegal as %2$s is not a container", objArr);
                $jacocoInit[74] = true;
                WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, "GraphicActionAddElement", format, arrayMap);
                $jacocoInit[75] = true;
            }
        }
        WXRenderManager wXRenderManager2 = WXSDKManager.getInstance().getWXRenderManager();
        $jacocoInit[5] = true;
        this.parent = (WXVContainer) wXRenderManager2.getWXComponent(getPageId(), this.mParentRef);
        $jacocoInit[6] = true;
        BasicComponentData basicComponentData = new BasicComponentData(str, this.mComponentType, this.mParentRef);
        $jacocoInit[7] = true;
        this.child = createComponent(wXSDKInstance, this.parent, basicComponentData);
        $jacocoInit[8] = true;
        this.child.setTransition(WXTransition.fromMap(this.child.getStyles(), this.child));
        if (this.parent == null) {
            $jacocoInit[9] = true;
        } else if (!this.parent.isIgnoreInteraction) {
            $jacocoInit[10] = true;
        } else {
            this.child.isIgnoreInteraction = true;
            $jacocoInit[11] = true;
        }
        if (this.child == null) {
            $jacocoInit[12] = true;
        } else if (this.child.getAttrs() == null) {
            $jacocoInit[13] = true;
        } else if (!"1".equals(this.child.getAttrs().get("ignoreInteraction"))) {
            $jacocoInit[14] = true;
        } else {
            this.child.isIgnoreInteraction = true;
            $jacocoInit[15] = true;
        }
        $jacocoInit[16] = true;
        $jacocoInit[76] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setRTL(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isLayoutRTL = z;
        $jacocoInit[77] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setSize(GraphicSize graphicSize) {
        boolean[] $jacocoInit = $jacocoInit();
        this.layoutSize = graphicSize;
        $jacocoInit[78] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setPosition(GraphicPosition graphicPosition) {
        boolean[] $jacocoInit = $jacocoInit();
        this.layoutPosition = graphicPosition;
        $jacocoInit[79] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setIndex(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIndex = i;
        $jacocoInit[80] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        super.executeAction();
        try {
            $jacocoInit[81] = true;
            if (TextUtils.equals(this.mComponentType, "video")) {
                $jacocoInit[82] = true;
            } else if (TextUtils.equals(this.mComponentType, "videoplus")) {
                $jacocoInit[83] = true;
            } else {
                this.child.mIsAddElementToTree = true;
                $jacocoInit[84] = true;
            }
            this.parent.addChild(this.child, this.mIndex);
            $jacocoInit[85] = true;
            this.parent.createChildViewAt(this.mIndex);
            $jacocoInit[86] = true;
            this.child.setIsLayoutRTL(this.isLayoutRTL);
            if (this.layoutPosition == null) {
                $jacocoInit[87] = true;
            } else if (this.layoutSize == null) {
                $jacocoInit[88] = true;
            } else {
                $jacocoInit[89] = true;
                this.child.setDemission(this.layoutSize, this.layoutPosition);
                $jacocoInit[90] = true;
            }
            this.child.applyLayoutAndEvent(this.child);
            $jacocoInit[91] = true;
            this.child.bindData(this.child);
            $jacocoInit[92] = true;
        } catch (Exception e) {
            $jacocoInit[93] = true;
            WXLogUtils.e("add component failed.", (Throwable) e);
            $jacocoInit[94] = true;
        }
        $jacocoInit[95] = true;
    }
}
