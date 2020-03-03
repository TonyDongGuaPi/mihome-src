package com.taobao.weex.ui.component.pesudo;

import android.view.MotionEvent;
import android.view.View;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TouchActivePseudoListener implements View.OnTouchListener {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean mIsConsumeOnTouch;
    private OnActivePseudoListner mOnActivePseudoListner;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5290650763523277108L, "com/taobao/weex/ui/component/pesudo/TouchActivePseudoListener", 11);
        $jacocoData = a2;
        return a2;
    }

    public TouchActivePseudoListener(OnActivePseudoListner onActivePseudoListner, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOnActivePseudoListner = onActivePseudoListner;
        this.mIsConsumeOnTouch = z;
        $jacocoInit[0] = true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        int action = motionEvent.getAction();
        if (this.mOnActivePseudoListner == null) {
            $jacocoInit[1] = true;
        } else {
            if (action == 0) {
                $jacocoInit[2] = true;
            } else if (action == 5) {
                $jacocoInit[3] = true;
            } else {
                if (action == 3) {
                    $jacocoInit[5] = true;
                } else if (action == 1) {
                    $jacocoInit[6] = true;
                } else if (action != 6) {
                    $jacocoInit[7] = true;
                } else {
                    $jacocoInit[8] = true;
                }
                this.mOnActivePseudoListner.updateActivePseudo(false);
                $jacocoInit[9] = true;
            }
            this.mOnActivePseudoListner.updateActivePseudo(true);
            $jacocoInit[4] = true;
        }
        boolean z = this.mIsConsumeOnTouch;
        $jacocoInit[10] = true;
        return z;
    }
}
