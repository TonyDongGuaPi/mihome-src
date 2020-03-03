package com.taobao.weex.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class WXRenderHandler extends Handler {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2033191928069442002L, "com/taobao/weex/ui/WXRenderHandler", 5);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRenderHandler() {
        super(Looper.getMainLooper());
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public final boolean post(String str, Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtain = Message.obtain(this, runnable);
        $jacocoInit[1] = true;
        obtain.what = str.hashCode();
        $jacocoInit[2] = true;
        boolean sendMessageDelayed = sendMessageDelayed(obtain, 0);
        $jacocoInit[3] = true;
        return sendMessageDelayed;
    }

    public void handleMessage(Message message) {
        boolean[] $jacocoInit = $jacocoInit();
        super.handleMessage(message);
        $jacocoInit[4] = true;
    }
}
