package com.taobao.weex.base;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SystemMessageHandler extends Handler implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int SCHEDULED_WORK = 1;
    private static final String TAG = "SystemMessageHandler";
    private Method mMessageMethodSetAsynchronous;
    private long mMessagePumpDelegateNative = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6474971056639939339L, "com/taobao/weex/base/SystemMessageHandler", 27);
        $jacocoData = a2;
        return a2;
    }

    private native void nativeRunWork(long j);

    private SystemMessageHandler(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMessagePumpDelegateNative = j;
        try {
            $jacocoInit[0] = true;
            Class<?> cls = Class.forName("android.os.Message");
            $jacocoInit[1] = true;
            this.mMessageMethodSetAsynchronous = cls.getMethod("setAsynchronous", new Class[]{Boolean.TYPE});
            $jacocoInit[2] = true;
        } catch (ClassNotFoundException e) {
            $jacocoInit[3] = true;
            Log.e(TAG, "Failed to find android.os.Message class:" + e);
            $jacocoInit[4] = true;
        } catch (NoSuchMethodException e2) {
            $jacocoInit[5] = true;
            Log.e(TAG, "Failed to load Message.setAsynchronous method:" + e2);
            $jacocoInit[6] = true;
        } catch (RuntimeException e3) {
            $jacocoInit[7] = true;
            Log.e(TAG, "Exception while loading Message.setAsynchronous method: " + e3);
            $jacocoInit[8] = true;
        }
        $jacocoInit[9] = true;
    }

    @CalledByNative
    public static SystemMessageHandler create(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        SystemMessageHandler systemMessageHandler = new SystemMessageHandler(j);
        $jacocoInit[10] = true;
        return systemMessageHandler;
    }

    @CalledByNative
    private void scheduleWork() {
        boolean[] $jacocoInit = $jacocoInit();
        sendMessage(obtainAsyncMessage(1));
        $jacocoInit[11] = true;
    }

    @CalledByNative
    private void scheduleDelayedWork(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        sendMessageDelayed(obtainAsyncMessage(1), j);
        $jacocoInit[12] = true;
    }

    @CalledByNative
    private void stop() {
        boolean[] $jacocoInit = $jacocoInit();
        removeMessages(1);
        $jacocoInit[13] = true;
    }

    private Message obtainAsyncMessage(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtain = Message.obtain();
        obtain.what = i;
        if (this.mMessageMethodSetAsynchronous == null) {
            $jacocoInit[14] = true;
        } else {
            try {
                $jacocoInit[15] = true;
                this.mMessageMethodSetAsynchronous.invoke(obtain, new Object[]{true});
                $jacocoInit[16] = true;
            } catch (IllegalAccessException unused) {
                $jacocoInit[17] = true;
                Log.e(TAG, "Illegal access to asynchronous message creation, disabling.");
                this.mMessageMethodSetAsynchronous = null;
                $jacocoInit[18] = true;
            } catch (IllegalArgumentException unused2) {
                $jacocoInit[19] = true;
                Log.e(TAG, "Illegal argument for asynchronous message creation, disabling.");
                this.mMessageMethodSetAsynchronous = null;
                $jacocoInit[20] = true;
            } catch (InvocationTargetException unused3) {
                $jacocoInit[21] = true;
                Log.e(TAG, "Invocation exception during asynchronous message creation, disabling.");
                this.mMessageMethodSetAsynchronous = null;
                $jacocoInit[22] = true;
            } catch (RuntimeException unused4) {
                $jacocoInit[23] = true;
                Log.e(TAG, "Runtime exception during asynchronous message creation, disabling.");
                this.mMessageMethodSetAsynchronous = null;
                $jacocoInit[24] = true;
            }
        }
        $jacocoInit[25] = true;
        return obtain;
    }

    public void handleMessage(Message message) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeRunWork(this.mMessagePumpDelegateNative);
        $jacocoInit[26] = true;
    }
}
