package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import android.widget.ScrollView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionCreateBody extends GraphicActionAbstractAddElement {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXComponent component;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5007767067008122072L, "com/taobao/weex/ui/action/GraphicActionCreateBody", 23);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionCreateBody(@NonNull WXSDKInstance wXSDKInstance, String str, String str2, Map<String, String> map, Map<String, String> map2, Set<String> set, float[] fArr, float[] fArr2, float[] fArr3) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponentType = str2;
        this.mStyle = map;
        this.mAttributes = map2;
        this.mEvents = set;
        this.mMargins = fArr;
        this.mPaddings = fArr2;
        this.mBorders = fArr3;
        $jacocoInit[0] = true;
        if (wXSDKInstance.getContext() == null) {
            $jacocoInit[1] = true;
            return;
        }
        wXSDKInstance.getExceptionRecorder().setBeginRender(true);
        $jacocoInit[2] = true;
        BasicComponentData basicComponentData = new BasicComponentData(getRef(), this.mComponentType, (String) null);
        $jacocoInit[3] = true;
        this.component = createComponent(wXSDKInstance, (WXVContainer) null, basicComponentData);
        if (this.component == null) {
            $jacocoInit[4] = true;
            return;
        }
        this.component.setTransition(WXTransition.fromMap(this.component.getStyles(), this.component));
        $jacocoInit[5] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        super.executeAction();
        try {
            $jacocoInit[6] = true;
            this.component.createView();
            $jacocoInit[7] = true;
            this.component.applyLayoutAndEvent(this.component);
            $jacocoInit[8] = true;
            this.component.bindData(this.component);
            $jacocoInit[9] = true;
            WXSDKInstance wXSDKIntance = getWXSDKIntance();
            if (!(this.component instanceof WXScroller)) {
                $jacocoInit[10] = true;
            } else {
                WXScroller wXScroller = (WXScroller) this.component;
                $jacocoInit[11] = true;
                if (!(wXScroller.getInnerView() instanceof ScrollView)) {
                    $jacocoInit[12] = true;
                } else {
                    $jacocoInit[13] = true;
                    wXSDKIntance.setRootScrollView((ScrollView) wXScroller.getInnerView());
                    $jacocoInit[14] = true;
                }
            }
            wXSDKIntance.onRootCreated(this.component);
            $jacocoInit[15] = true;
            if (wXSDKIntance.getRenderStrategy() == WXRenderStrategy.APPEND_ONCE) {
                $jacocoInit[16] = true;
            } else {
                $jacocoInit[17] = true;
                wXSDKIntance.onCreateFinish();
                $jacocoInit[18] = true;
            }
            $jacocoInit[19] = true;
        } catch (Exception e) {
            $jacocoInit[20] = true;
            WXLogUtils.e("create body failed.", (Throwable) e);
            $jacocoInit[21] = true;
        }
        $jacocoInit[22] = true;
    }
}
