package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.surfaceview;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.art.ARTSurfaceView;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

@ReactModule(name = "MHRSurfaceView")
public class MHRSurfaceViewManager extends BaseViewManager<ARTSurfaceView, MHRSurfaceViewShadowNode> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = $$Lambda$MHRSurfaceViewManager$WuyLZe086Eis1O33FwASRRQUuPU.INSTANCE;
    protected static final String REACT_CLASS = "MHRSurfaceView";

    public String getName() {
        return REACT_CLASS;
    }

    public void setBackgroundColor(ARTSurfaceView aRTSurfaceView, int i) {
    }

    static /* synthetic */ long lambda$static$0(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        throw new IllegalStateException("SurfaceView should have explicit width and height set");
    }

    public MHRSurfaceViewShadowNode createShadowNodeInstance() {
        MHRSurfaceViewShadowNode mHRSurfaceViewShadowNode = new MHRSurfaceViewShadowNode();
        mHRSurfaceViewShadowNode.setMeasureFunction(MEASURE_FUNCTION);
        return mHRSurfaceViewShadowNode;
    }

    public Class<MHRSurfaceViewShadowNode> getShadowNodeClass() {
        return MHRSurfaceViewShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public ARTSurfaceView createViewInstance(ThemedReactContext themedReactContext) {
        return new ARTSurfaceView(themedReactContext);
    }

    public void updateExtraData(ARTSurfaceView aRTSurfaceView, Object obj) {
        aRTSurfaceView.setSurfaceTextureListener((MHRSurfaceViewShadowNode) obj);
    }
}
