package com.taobao.weex.yp_compoment.pullrefresh.youpinptr;

import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class PtrUIHandlerHolder implements PtrUIHandler {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private PtrUIHandler mHandler;
    private PtrUIHandlerHolder mNext;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4207606932049304594L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrUIHandlerHolder", 62);
        $jacocoData = a2;
        return a2;
    }

    private boolean contains(PtrUIHandler ptrUIHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (this.mHandler == null) {
            $jacocoInit[0] = true;
        } else if (this.mHandler != ptrUIHandler) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            z = true;
            $jacocoInit[4] = true;
            return z;
        }
        $jacocoInit[3] = true;
        $jacocoInit[4] = true;
        return z;
    }

    private PtrUIHandlerHolder() {
        $jacocoInit()[5] = true;
    }

    public boolean hasHandler() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHandler != null) {
            $jacocoInit[6] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return z;
    }

    private PtrUIHandler getHandler() {
        boolean[] $jacocoInit = $jacocoInit();
        PtrUIHandler ptrUIHandler = this.mHandler;
        $jacocoInit[9] = true;
        return ptrUIHandler;
    }

    public static void addHandler(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        if (ptrUIHandler == null) {
            $jacocoInit[10] = true;
        } else if (ptrUIHandlerHolder == null) {
            $jacocoInit[11] = true;
        } else if (ptrUIHandlerHolder.mHandler == null) {
            ptrUIHandlerHolder.mHandler = ptrUIHandler;
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            while (!ptrUIHandlerHolder.contains(ptrUIHandler)) {
                if (ptrUIHandlerHolder.mNext == null) {
                    PtrUIHandlerHolder ptrUIHandlerHolder2 = new PtrUIHandlerHolder();
                    ptrUIHandlerHolder2.mHandler = ptrUIHandler;
                    ptrUIHandlerHolder.mNext = ptrUIHandlerHolder2;
                    $jacocoInit[16] = true;
                    return;
                }
                ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
                $jacocoInit[15] = true;
            }
            $jacocoInit[14] = true;
        }
    }

    public static PtrUIHandlerHolder create() {
        boolean[] $jacocoInit = $jacocoInit();
        PtrUIHandlerHolder ptrUIHandlerHolder = new PtrUIHandlerHolder();
        $jacocoInit[17] = true;
        return ptrUIHandlerHolder;
    }

    public static PtrUIHandlerHolder removeHandler(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        if (ptrUIHandlerHolder == null) {
            $jacocoInit[18] = true;
        } else if (ptrUIHandler == null) {
            $jacocoInit[19] = true;
        } else if (ptrUIHandlerHolder.mHandler == null) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[22] = true;
            PtrUIHandlerHolder ptrUIHandlerHolder2 = ptrUIHandlerHolder;
            PtrUIHandlerHolder ptrUIHandlerHolder3 = null;
            while (true) {
                if (!ptrUIHandlerHolder.contains(ptrUIHandler)) {
                    PtrUIHandlerHolder ptrUIHandlerHolder4 = ptrUIHandlerHolder.mNext;
                    $jacocoInit[25] = true;
                    PtrUIHandlerHolder ptrUIHandlerHolder5 = ptrUIHandlerHolder4;
                    ptrUIHandlerHolder3 = ptrUIHandlerHolder;
                    ptrUIHandlerHolder = ptrUIHandlerHolder5;
                } else if (ptrUIHandlerHolder3 == null) {
                    ptrUIHandlerHolder2 = ptrUIHandlerHolder.mNext;
                    ptrUIHandlerHolder.mNext = null;
                    $jacocoInit[23] = true;
                    ptrUIHandlerHolder = ptrUIHandlerHolder2;
                } else {
                    ptrUIHandlerHolder3.mNext = ptrUIHandlerHolder.mNext;
                    ptrUIHandlerHolder.mNext = null;
                    ptrUIHandlerHolder = ptrUIHandlerHolder3.mNext;
                    $jacocoInit[24] = true;
                }
                if (ptrUIHandlerHolder == null) {
                    break;
                }
                $jacocoInit[26] = true;
            }
            if (ptrUIHandlerHolder2 != null) {
                $jacocoInit[27] = true;
            } else {
                $jacocoInit[28] = true;
                ptrUIHandlerHolder2 = new PtrUIHandlerHolder();
                $jacocoInit[29] = true;
            }
            $jacocoInit[30] = true;
            return ptrUIHandlerHolder2;
        }
        $jacocoInit[21] = true;
        return ptrUIHandlerHolder;
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[31] = true;
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        while (true) {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler == null) {
                $jacocoInit[32] = true;
            } else {
                $jacocoInit[33] = true;
                handler.onUIReset(ptrFrameLayout);
                $jacocoInit[34] = true;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder != null) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                return;
            }
        }
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!hasHandler()) {
            $jacocoInit[37] = true;
            return;
        }
        $jacocoInit[38] = true;
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        while (true) {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler == null) {
                $jacocoInit[39] = true;
            } else {
                $jacocoInit[40] = true;
                handler.onUIRefreshPrepare(ptrFrameLayout);
                $jacocoInit[41] = true;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder != null) {
                $jacocoInit[42] = true;
            } else {
                $jacocoInit[43] = true;
                return;
            }
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[44] = true;
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        while (true) {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler == null) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                handler.onUIRefreshBegin(ptrFrameLayout);
                $jacocoInit[47] = true;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder != null) {
                $jacocoInit[48] = true;
            } else {
                $jacocoInit[49] = true;
                return;
            }
        }
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[50] = true;
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        while (true) {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler == null) {
                $jacocoInit[51] = true;
            } else {
                $jacocoInit[52] = true;
                handler.onUIRefreshComplete(ptrFrameLayout);
                $jacocoInit[53] = true;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder != null) {
                $jacocoInit[54] = true;
            } else {
                $jacocoInit[55] = true;
                return;
            }
        }
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[56] = true;
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        while (true) {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler == null) {
                $jacocoInit[57] = true;
            } else {
                $jacocoInit[58] = true;
                handler.onUIPositionChange(ptrFrameLayout, z, b, ptrIndicator);
                $jacocoInit[59] = true;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder != null) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                return;
            }
        }
    }
}
