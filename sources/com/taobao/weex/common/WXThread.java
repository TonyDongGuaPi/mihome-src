package com.taobao.weex.common;

import android.os.Handler;
import android.os.HandlerThread;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXThread extends HandlerThread {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Handler mHandler;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2089397895835609780L, "com/taobao/weex/common/WXThread", 31);
        $jacocoData = a2;
        return a2;
    }

    static class SafeRunnable implements Runnable {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        static final String TAG = "SafeRunnable";
        final Runnable mTask;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(769149237340079688L, "com/taobao/weex/common/WXThread$SafeRunnable", 10);
            $jacocoData = a2;
            return a2;
        }

        SafeRunnable(Runnable runnable) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mTask = runnable;
            $jacocoInit[0] = true;
        }

        public void run() {
            boolean[] $jacocoInit = $jacocoInit();
            try {
                if (this.mTask == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    this.mTask.run();
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            } catch (Throwable th) {
                $jacocoInit[5] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    WXLogUtils.w(TAG, th);
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[6] = true;
                    WXLogUtils.e(TAG, "SafeRunnable run throw expection:" + th.getMessage());
                    $jacocoInit[7] = true;
                    throw th;
                }
            }
            $jacocoInit[9] = true;
        }
    }

    static class SafeCallback implements Handler.Callback {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        static final String TAG = "SafeCallback";
        final Handler.Callback mCallback;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8627043381005628818L, "com/taobao/weex/common/WXThread$SafeCallback", 11);
            $jacocoData = a2;
            return a2;
        }

        SafeCallback(Handler.Callback callback) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mCallback = callback;
            $jacocoInit[0] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean handleMessage(android.os.Message r6) {
            /*
                r5 = this;
                boolean[] r0 = $jacocoInit()
                r1 = 1
                r2 = 0
                r0[r1] = r1     // Catch:{ Throwable -> 0x0026 }
                android.os.Handler$Callback r3 = r5.mCallback     // Catch:{ Throwable -> 0x0026 }
                if (r3 != 0) goto L_0x0011
                r6 = 2
                r0[r6] = r1     // Catch:{ Throwable -> 0x0026 }
                r6 = 0
                goto L_0x001d
            L_0x0011:
                r3 = 3
                r0[r3] = r1     // Catch:{ Throwable -> 0x0026 }
                android.os.Handler$Callback r3 = r5.mCallback     // Catch:{ Throwable -> 0x0026 }
                boolean r6 = r3.handleMessage(r6)     // Catch:{ Throwable -> 0x0026 }
                r2 = 4
                r0[r2] = r1     // Catch:{ Throwable -> 0x0021 }
            L_0x001d:
                r2 = 5
                r0[r2] = r1
                goto L_0x0034
            L_0x0021:
                r2 = move-exception
                r4 = r2
                r2 = r6
                r6 = r4
                goto L_0x0027
            L_0x0026:
                r6 = move-exception
            L_0x0027:
                r3 = 6
                r0[r3] = r1
                boolean r3 = com.taobao.weex.WXEnvironment.isApkDebugable()
                if (r3 != 0) goto L_0x0039
                r6 = 7
                r0[r6] = r1
                r6 = r2
            L_0x0034:
                r2 = 10
                r0[r2] = r1
                return r6
            L_0x0039:
                r2 = 8
                r0[r2] = r1
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "SafeCallback handleMessage throw expection:"
                r2.append(r3)
                java.lang.String r3 = r6.getMessage()
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.String r3 = "SafeCallback"
                com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r3, (java.lang.String) r2)
                r2 = 9
                r0[r2] = r1
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.common.WXThread.SafeCallback.handleMessage(android.os.Message):boolean");
        }
    }

    public static Runnable secure(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (runnable == null) {
            $jacocoInit[0] = true;
        } else if (runnable instanceof SafeRunnable) {
            $jacocoInit[1] = true;
        } else {
            SafeRunnable safeRunnable = new SafeRunnable(runnable);
            $jacocoInit[3] = true;
            return safeRunnable;
        }
        $jacocoInit[2] = true;
        return runnable;
    }

    public static Handler.Callback secure(Handler.Callback callback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (callback == null) {
            $jacocoInit[4] = true;
        } else if (callback instanceof SafeCallback) {
            $jacocoInit[5] = true;
        } else {
            SafeCallback safeCallback = new SafeCallback(callback);
            $jacocoInit[7] = true;
            return safeCallback;
        }
        $jacocoInit[6] = true;
        return callback;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXThread(String str) {
        super(str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[8] = true;
        start();
        $jacocoInit[9] = true;
        this.mHandler = new Handler(getLooper());
        $jacocoInit[10] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXThread(String str, Handler.Callback callback) {
        super(str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[11] = true;
        start();
        $jacocoInit[12] = true;
        this.mHandler = new Handler(getLooper(), secure(callback));
        $jacocoInit[13] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXThread(String str, int i, Handler.Callback callback) {
        super(str, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[14] = true;
        start();
        $jacocoInit[15] = true;
        this.mHandler = new Handler(getLooper(), secure(callback));
        $jacocoInit[16] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXThread(String str, int i) {
        super(str, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[17] = true;
        start();
        $jacocoInit[18] = true;
        this.mHandler = new Handler(getLooper());
        $jacocoInit[19] = true;
    }

    public Handler getHandler() {
        boolean[] $jacocoInit = $jacocoInit();
        Handler handler = this.mHandler;
        $jacocoInit[20] = true;
        return handler;
    }

    public boolean isWXThreadAlive() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHandler == null) {
            $jacocoInit[21] = true;
        } else if (getLooper() == null) {
            $jacocoInit[22] = true;
        } else if (!isAlive()) {
            $jacocoInit[23] = true;
        } else {
            $jacocoInit[24] = true;
            z = true;
            $jacocoInit[26] = true;
            return z;
        }
        z = false;
        $jacocoInit[25] = true;
        $jacocoInit[26] = true;
        return z;
    }

    public boolean quit() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHandler == null) {
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            this.mHandler.removeCallbacksAndMessages((Object) null);
            $jacocoInit[29] = true;
        }
        boolean quit = super.quit();
        $jacocoInit[30] = true;
        return quit;
    }
}
