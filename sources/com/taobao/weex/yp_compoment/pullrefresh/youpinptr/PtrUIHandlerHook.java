package com.taobao.weex.yp_compoment.pullrefresh.youpinptr;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class PtrUIHandlerHook implements Runnable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final byte STATUS_IN_HOOK = 1;
    private static final byte STATUS_PREPARE = 0;
    private static final byte STATUS_RESUMED = 2;
    private Runnable mResumeAction;
    private byte mStatus = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2559056872871426164L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrUIHandlerHook", 16);
        $jacocoData = a2;
        return a2;
    }

    public PtrUIHandlerHook() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void takeOver() {
        boolean[] $jacocoInit = $jacocoInit();
        takeOver((Runnable) null);
        $jacocoInit[1] = true;
    }

    public void takeOver(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (runnable == null) {
            $jacocoInit[2] = true;
        } else {
            this.mResumeAction = runnable;
            $jacocoInit[3] = true;
        }
        switch (this.mStatus) {
            case 0:
                this.mStatus = 1;
                $jacocoInit[5] = true;
                run();
                $jacocoInit[6] = true;
                break;
            case 1:
                $jacocoInit[7] = true;
                break;
            case 2:
                resume();
                $jacocoInit[8] = true;
                break;
            default:
                $jacocoInit[4] = true;
                break;
        }
        $jacocoInit[9] = true;
    }

    public void reset() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatus = 0;
        $jacocoInit[10] = true;
    }

    public void resume() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mResumeAction == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            this.mResumeAction.run();
            $jacocoInit[13] = true;
        }
        this.mStatus = 2;
        $jacocoInit[14] = true;
    }

    public void setResumeAction(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mResumeAction = runnable;
        $jacocoInit[15] = true;
    }
}
