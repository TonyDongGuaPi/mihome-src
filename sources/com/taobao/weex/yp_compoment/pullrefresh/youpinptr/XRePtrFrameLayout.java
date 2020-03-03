package com.taobao.weex.yp_compoment.pullrefresh.youpinptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class XRePtrFrameLayout extends PtrFrameLayout {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;
    private int mScaleTouchSlop;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6506457394130375239L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/XRePtrFrameLayout", 13);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public XRePtrFrameLayout(Context context) {
        this(context, (AttributeSet) null);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public XRePtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XRePtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        this.mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        $jacocoInit[3] = true;
    }

    public float getMoveX() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mMoveX;
        $jacocoInit[4] = true;
        return f;
    }

    public float getMoveY() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mMoveY;
        $jacocoInit[5] = true;
        return f;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mDownX = motionEvent.getX();
            $jacocoInit[7] = true;
            this.mDownY = motionEvent.getY();
            this.mMoveY = 0.0f;
            this.mMoveX = 0.0f;
            $jacocoInit[8] = true;
        } else if (action != 2) {
            $jacocoInit[6] = true;
        } else {
            float x = motionEvent.getX();
            $jacocoInit[9] = true;
            float abs = Math.abs(this.mDownX - x);
            $jacocoInit[10] = true;
            float abs2 = Math.abs(this.mDownY - motionEvent.getY());
            this.mMoveX = abs;
            this.mMoveY = abs2;
            $jacocoInit[11] = true;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        $jacocoInit[12] = true;
        return dispatchTouchEvent;
    }
}
