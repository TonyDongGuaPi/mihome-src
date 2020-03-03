package com.taobao.weex;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.taobao.weex.WeexFrameRateControl;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class RenderContainer extends FrameLayout implements WeexFrameRateControl.VSyncListener {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WeexFrameRateControl mFrameRateControl;
    private boolean mHasConsumeEvent = false;
    private WeakReference<WXSDKInstance> mSDKInstance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3700783117281190975L, "com/taobao/weex/RenderContainer", 39);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RenderContainer(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mFrameRateControl = new WeexFrameRateControl(this);
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RenderContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        this.mFrameRateControl = new WeexFrameRateControl(this);
        $jacocoInit[3] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RenderContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[4] = true;
        this.mFrameRateControl = new WeexFrameRateControl(this);
        $jacocoInit[5] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @TargetApi(21)
    public RenderContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[6] = true;
        this.mFrameRateControl = new WeexFrameRateControl(this);
        $jacocoInit[7] = true;
    }

    public void setSDKInstance(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mSDKInstance = new WeakReference<>(wXSDKInstance);
        $jacocoInit[8] = true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onSizeChanged(i, i2, i3, i4);
        $jacocoInit[9] = true;
        if (this.mSDKInstance == null) {
            $jacocoInit[10] = true;
        } else {
            WXSDKInstance wXSDKInstance = (WXSDKInstance) this.mSDKInstance.get();
            if (wXSDKInstance == null) {
                $jacocoInit[11] = true;
            } else {
                $jacocoInit[12] = true;
                wXSDKInstance.setSize(i, i2);
                $jacocoInit[13] = true;
            }
        }
        $jacocoInit[14] = true;
    }

    public void onAttachedToWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAttachedToWindow();
        if (this.mFrameRateControl == null) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            this.mFrameRateControl.start();
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDetachedFromWindow();
        if (this.mFrameRateControl == null) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            this.mFrameRateControl.stop();
            $jacocoInit[21] = true;
        }
        $jacocoInit[22] = true;
    }

    public void dispatchWindowVisibilityChanged(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.dispatchWindowVisibilityChanged(i);
        if (i == 8) {
            if (this.mFrameRateControl == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                this.mFrameRateControl.stop();
                $jacocoInit[25] = true;
            }
        } else if (i != 0) {
            $jacocoInit[26] = true;
        } else if (this.mFrameRateControl == null) {
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            this.mFrameRateControl.start();
            $jacocoInit[29] = true;
        }
        $jacocoInit[30] = true;
    }

    public void OnVSync() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mSDKInstance == null) {
            $jacocoInit[31] = true;
        } else if (this.mSDKInstance.get() == null) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            ((WXSDKInstance) this.mSDKInstance.get()).OnVSync();
            $jacocoInit[34] = true;
        }
        $jacocoInit[35] = true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mHasConsumeEvent = true;
        $jacocoInit[36] = true;
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        $jacocoInit[37] = true;
        return dispatchTouchEvent;
    }

    public boolean hasConsumeEvent() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mHasConsumeEvent;
        $jacocoInit[38] = true;
        return z;
    }
}
