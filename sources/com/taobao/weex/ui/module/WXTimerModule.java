package com.taobao.weex.ui.module;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.VisibleForTesting;
import android.util.SparseArray;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXHashMap;
import com.taobao.weex.bridge.WXJSObject;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.utils.WXJsonUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTimerModule extends WXModule implements Handler.Callback, Destroyable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "timer";
    private SparseArray<Integer> antiIntAutoBoxing;
    private Handler handler = new Handler(WXBridgeManager.getInstance().getJSLooper(), this);

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(120327567677688568L, "com/taobao/weex/ui/module/WXTimerModule", 80);
        $jacocoData = a2;
        return a2;
    }

    public WXTimerModule() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.antiIntAutoBoxing = new SparseArray<>();
        $jacocoInit[2] = true;
    }

    @JSMethod(uiThread = false)
    public void setTimeout(@IntRange(from = 1) int i, @FloatRange(from = 0.0d) float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            postOrHoldMessage(11, i, (int) f, WXUtils.parseInt(this.mWXSDKInstance.getInstanceId()));
            $jacocoInit[5] = true;
            if (this.mWXSDKInstance.getWXPerformance() == null) {
                $jacocoInit[6] = true;
            } else {
                $jacocoInit[7] = true;
                this.mWXSDKInstance.getWXPerformance().timerInvokeCount++;
                $jacocoInit[8] = true;
            }
            this.mWXSDKInstance.getApmForInstance().updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_TIMER_NUM, 1.0d);
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
    }

    @JSMethod(uiThread = false)
    public void setInterval(@IntRange(from = 1) int i, @FloatRange(from = 0.0d) float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            postOrHoldMessage(12, i, (int) f, WXUtils.parseInt(this.mWXSDKInstance.getInstanceId()));
            $jacocoInit[13] = true;
            if (this.mWXSDKInstance.getWXPerformance() == null) {
                $jacocoInit[14] = true;
            } else {
                $jacocoInit[15] = true;
                this.mWXSDKInstance.getWXPerformance().timerInvokeCount++;
                $jacocoInit[16] = true;
            }
            this.mWXSDKInstance.getApmForInstance().updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_TIMER_NUM, 1.0d);
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }

    @JSMethod(uiThread = false)
    public void clearTimeout(@IntRange(from = 1) int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i <= 0) {
            $jacocoInit[19] = true;
            return;
        }
        removeOrHoldMessage(11, i);
        $jacocoInit[20] = true;
    }

    @JSMethod(uiThread = false)
    public void clearInterval(@IntRange(from = 1) int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i <= 0) {
            $jacocoInit[21] = true;
            return;
        }
        removeOrHoldMessage(12, i);
        $jacocoInit[22] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.handler == null) {
            $jacocoInit[23] = true;
        } else {
            $jacocoInit[24] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[25] = true;
            } else {
                $jacocoInit[26] = true;
                WXLogUtils.d(TAG, "Timer Module removeAllMessages: ");
                $jacocoInit[27] = true;
            }
            this.handler.removeCallbacksAndMessages((Object) null);
            $jacocoInit[28] = true;
            this.antiIntAutoBoxing.clear();
            $jacocoInit[29] = true;
        }
        $jacocoInit[30] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00d1, code lost:
        r1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleMessage(android.os.Message r12) {
        /*
            r11 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            r2 = 1
            if (r12 != 0) goto L_0x000e
            r12 = 31
            r0[r12] = r2
            goto L_0x00d2
        L_0x000e:
            int r3 = r12.what
            r4 = 32
            r0[r4] = r2
            boolean r4 = com.taobao.weex.WXEnvironment.isApkDebugable()
            if (r4 != 0) goto L_0x001f
            r4 = 33
            r0[r4] = r2
            goto L_0x003f
        L_0x001f:
            r4 = 34
            r0[r4] = r2
            java.lang.String r4 = "timer"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Timer Module handleMessage : "
            r5.append(r6)
            int r6 = r12.what
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r4, (java.lang.String) r5)
            r4 = 35
            r0[r4] = r2
        L_0x003f:
            switch(r3) {
                case 11: goto L_0x0098;
                case 12: goto L_0x0048;
                default: goto L_0x0042;
            }
        L_0x0042:
            r12 = 36
            r0[r12] = r2
            goto L_0x00d2
        L_0x0048:
            java.lang.Object r3 = r12.obj
            if (r3 != 0) goto L_0x0052
            r12 = 41
            r0[r12] = r2
            goto L_0x00d2
        L_0x0052:
            int r1 = r12.arg1
            r11.checkIfTimerInBack(r1)
            r1 = 42
            r0[r1] = r2
            r1 = 12
            java.lang.Object r3 = r12.obj
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            int r4 = r12.arg2
            int r5 = r12.arg1
            r11.postMessage(r1, r3, r4, r5)
            r1 = 43
            r0[r1] = r2
            int r1 = r12.arg1
            java.lang.Object r3 = r12.obj
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.taobao.weex.bridge.WXJSObject[] r8 = r11.createTimerArgs(r1, r3, r2)
            r1 = 44
            r0[r1] = r2
            com.taobao.weex.bridge.WXBridgeManager r4 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            int r12 = r12.arg1
            java.lang.String r5 = java.lang.String.valueOf(r12)
            r6 = 0
            java.lang.String r7 = "callJS"
            r9 = 1
            r4.invokeExecJS(r5, r6, r7, r8, r9)
            r12 = 45
            r0[r12] = r2
            goto L_0x00d1
        L_0x0098:
            java.lang.Object r3 = r12.obj
            if (r3 != 0) goto L_0x00a1
            r12 = 37
            r0[r12] = r2
            goto L_0x00d2
        L_0x00a1:
            int r3 = r12.arg1
            r11.checkIfTimerInBack(r3)
            r3 = 38
            r0[r3] = r2
            int r3 = r12.arg1
            java.lang.Object r4 = r12.obj
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            com.taobao.weex.bridge.WXJSObject[] r9 = r11.createTimerArgs(r3, r4, r1)
            r1 = 39
            r0[r1] = r2
            com.taobao.weex.bridge.WXBridgeManager r5 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            int r12 = r12.arg1
            java.lang.String r6 = java.lang.String.valueOf(r12)
            r7 = 0
            java.lang.String r8 = "callJS"
            r10 = 1
            r5.invokeExecJS(r6, r7, r8, r9, r10)
            r12 = 40
            r0[r12] = r2
        L_0x00d1:
            r1 = 1
        L_0x00d2:
            r12 = 46
            r0[r12] = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXTimerModule.handleMessage(android.os.Message):boolean");
    }

    private void checkIfTimerInBack(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(String.valueOf(i));
        if (sDKInstance == null) {
            $jacocoInit[47] = true;
            return;
        }
        if (!sDKInstance.isViewDisAppear()) {
            $jacocoInit[48] = true;
        } else {
            $jacocoInit[49] = true;
            sDKInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_TIMER_BACK_NUM, 1.0d);
            $jacocoInit[50] = true;
        }
        $jacocoInit[51] = true;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setHandler(Handler handler2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.handler = handler2;
        $jacocoInit[52] = true;
    }

    private WXJSObject[] createTimerArgs(int i, int i2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList();
        $jacocoInit[53] = true;
        arrayList.add(Integer.valueOf(i2));
        $jacocoInit[54] = true;
        arrayList.add(new HashMap());
        $jacocoInit[55] = true;
        arrayList.add(Boolean.valueOf(z));
        $jacocoInit[56] = true;
        WXHashMap wXHashMap = new WXHashMap();
        $jacocoInit[57] = true;
        wXHashMap.put("method", "callback");
        $jacocoInit[58] = true;
        wXHashMap.put("args", arrayList);
        $jacocoInit[59] = true;
        $jacocoInit[60] = true;
        WXJSObject[] wXJSObjectArr = {new WXJSObject(2, String.valueOf(i)), new WXJSObject(3, WXJsonUtils.fromObjectToJSONString(new Object[]{wXHashMap}))};
        $jacocoInit[61] = true;
        return wXJSObjectArr;
    }

    private void postOrHoldMessage(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance.isPreRenderMode()) {
            $jacocoInit[62] = true;
            postMessage(i, i2, i3, i4);
            $jacocoInit[63] = true;
        } else {
            postMessage(i, i2, i3, i4);
            $jacocoInit[64] = true;
        }
        $jacocoInit[65] = true;
    }

    private void removeOrHoldMessage(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance.isPreRenderMode()) {
            $jacocoInit[66] = true;
            this.handler.removeMessages(i, this.antiIntAutoBoxing.get(i2, Integer.valueOf(i2)));
            $jacocoInit[67] = true;
        } else {
            this.handler.removeMessages(i, this.antiIntAutoBoxing.get(i2, Integer.valueOf(i2)));
            $jacocoInit[68] = true;
        }
        $jacocoInit[69] = true;
    }

    private void postMessage(int i, @IntRange(from = 1) int i2, @IntRange(from = 0) int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i3 < 0) {
            $jacocoInit[70] = true;
        } else if (i2 <= 0) {
            $jacocoInit[71] = true;
        } else {
            if (this.antiIntAutoBoxing.get(i2) != null) {
                $jacocoInit[73] = true;
            } else {
                $jacocoInit[74] = true;
                this.antiIntAutoBoxing.put(i2, Integer.valueOf(i2));
                $jacocoInit[75] = true;
            }
            Handler handler2 = this.handler;
            SparseArray<Integer> sparseArray = this.antiIntAutoBoxing;
            $jacocoInit[76] = true;
            Message obtainMessage = handler2.obtainMessage(i, i4, i3, sparseArray.get(i2));
            $jacocoInit[77] = true;
            this.handler.sendMessageDelayed(obtainMessage, (long) i3);
            $jacocoInit[78] = true;
            $jacocoInit[79] = true;
        }
        WXLogUtils.e(TAG, "interval < 0 or funcId <=0");
        $jacocoInit[72] = true;
        $jacocoInit[79] = true;
    }
}
