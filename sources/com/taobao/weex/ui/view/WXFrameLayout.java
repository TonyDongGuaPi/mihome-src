package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.flat.widget.Widget;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXFrameLayout extends FrameLayout implements IRenderResult<WXDiv>, IRenderStatus<WXDiv>, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WeakReference<WXDiv> mWeakReference;
    private List<Widget> mWidgets;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1213451416986241053L, "com/taobao/weex/ui/view/WXFrameLayout", 75);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void holdComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        holdComponent((WXDiv) wXComponent);
        $jacocoInit[73] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXFrameLayout(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[1] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[2] = true;
        return wXGesture;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            dispatchTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
        return dispatchTouchEvent;
    }

    public void holdComponent(WXDiv wXDiv) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWeakReference = new WeakReference<>(wXDiv);
        $jacocoInit[7] = true;
    }

    @Nullable
    public WXDiv getComponent() {
        WXDiv wXDiv;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWeakReference != null) {
            wXDiv = (WXDiv) this.mWeakReference.get();
            $jacocoInit[8] = true;
        } else {
            wXDiv = null;
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
        return wXDiv;
    }

    public void mountFlatGUI(List<Widget> list) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWidgets = list;
        if (this.mWidgets == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            setWillNotDraw(true);
            $jacocoInit[13] = true;
        }
        invalidate();
        $jacocoInit[14] = true;
    }

    public void unmountFlatGUI() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWidgets = null;
        $jacocoInit[15] = true;
        setWillNotDraw(false);
        $jacocoInit[16] = true;
        invalidate();
        $jacocoInit[17] = true;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(@NonNull Drawable drawable) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWidgets != null) {
            $jacocoInit[18] = true;
        } else if (super.verifyDrawable(drawable)) {
            $jacocoInit[19] = true;
        } else {
            z = false;
            $jacocoInit[21] = true;
            $jacocoInit[22] = true;
            return z;
        }
        $jacocoInit[20] = true;
        z = true;
        $jacocoInit[22] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            dispatchDrawInterval(canvas);
            $jacocoInit[23] = true;
        } catch (Throwable th) {
            $jacocoInit[24] = true;
            if (getComponent() == null) {
                $jacocoInit[25] = true;
            } else {
                $jacocoInit[26] = true;
                notifyLayerOverFlow();
                $jacocoInit[27] = true;
                if (getComponent() == null) {
                    $jacocoInit[28] = true;
                } else {
                    $jacocoInit[29] = true;
                    WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(getComponent().getInstanceId());
                    $jacocoInit[30] = true;
                    if (sDKInstance == null) {
                        $jacocoInit[31] = true;
                    } else if (sDKInstance.getApmForInstance() == null) {
                        $jacocoInit[32] = true;
                    } else if (sDKInstance.getApmForInstance().hasReportLayerOverDraw) {
                        $jacocoInit[33] = true;
                    } else {
                        $jacocoInit[34] = true;
                        sDKInstance.getApmForInstance().hasReportLayerOverDraw = true;
                        $jacocoInit[35] = true;
                        reportLayerOverFlowError();
                        $jacocoInit[36] = true;
                    }
                }
            }
            WXLogUtils.e("Layer overflow limit error", WXLogUtils.getStackTrace(th));
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    private int reportLayerOverFlowError() {
        boolean[] $jacocoInit = $jacocoInit();
        int calLayerDeep = calLayerDeep(this, 0);
        $jacocoInit[39] = true;
        if (getComponent() == null) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            String instanceId = getComponent().getInstanceId();
            WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_LAYER_OVERFLOW;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_RENDER_ERR_LAYER_OVERFLOW;
            $jacocoInit[42] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("Layer overflow limit error: ");
            sb.append(calLayerDeep);
            sb.append(" layers!");
            String sb2 = sb.toString();
            $jacocoInit[43] = true;
            WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, "draw android view", sb2, (Map<String, String>) null);
            $jacocoInit[44] = true;
        }
        $jacocoInit[45] = true;
        return calLayerDeep;
    }

    private void dispatchDrawInterval(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWidgets != null) {
            $jacocoInit[46] = true;
            canvas.save();
            $jacocoInit[47] = true;
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            $jacocoInit[48] = true;
            $jacocoInit[49] = true;
            for (Widget draw : this.mWidgets) {
                $jacocoInit[50] = true;
                draw.draw(canvas);
                $jacocoInit[51] = true;
            }
            canvas.restore();
            $jacocoInit[52] = true;
        } else {
            WXViewUtils.clipCanvasWithinBorderBox((View) this, canvas);
            $jacocoInit[53] = true;
            super.dispatchDraw(canvas);
            $jacocoInit[54] = true;
        }
        $jacocoInit[55] = true;
    }

    private int calLayerDeep(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int i2 = i + 1;
        $jacocoInit[56] = true;
        if (view.getParent() == null) {
            $jacocoInit[57] = true;
        } else if (!(view.getParent() instanceof View)) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            int calLayerDeep = calLayerDeep((View) view.getParent(), i2);
            $jacocoInit[60] = true;
            return calLayerDeep;
        }
        $jacocoInit[61] = true;
        return i2;
    }

    public void notifyLayerOverFlow() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getComponent() == null) {
            $jacocoInit[62] = true;
            return;
        }
        WXSDKInstance instance = getComponent().getInstance();
        if (instance == null) {
            $jacocoInit[63] = true;
        } else if (instance.getLayerOverFlowListeners() == null) {
            $jacocoInit[64] = true;
        } else {
            $jacocoInit[65] = true;
            for (String next : instance.getLayerOverFlowListeners()) {
                $jacocoInit[66] = true;
                WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(instance.getInstanceId(), next);
                $jacocoInit[67] = true;
                HashMap hashMap = new HashMap();
                $jacocoInit[68] = true;
                hashMap.put("ref", next);
                $jacocoInit[69] = true;
                hashMap.put(Constants.Weex.INSTANCEID, wXComponent.getInstanceId());
                $jacocoInit[70] = true;
                wXComponent.fireEvent(Constants.Event.LAYEROVERFLOW, hashMap);
                $jacocoInit[71] = true;
            }
            $jacocoInit[72] = true;
        }
    }
}
