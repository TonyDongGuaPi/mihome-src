package com.taobao.weex.ui.view;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSwitchView extends SwitchCompat implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2910230898627293988L, "com/taobao/weex/ui/view/WXSwitchView", 9);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSwitchView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        setShowText(false);
        $jacocoInit[1] = true;
        setGravity(16);
        $jacocoInit[2] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[3] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[4] = true;
        return wXGesture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            onTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return onTouchEvent;
    }
}
