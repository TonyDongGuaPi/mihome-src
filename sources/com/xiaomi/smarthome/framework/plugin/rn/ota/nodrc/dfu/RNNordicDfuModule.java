package com.xiaomi.smarthome.framework.plugin.rn.ota.nodrc.dfu;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.util.List;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;

public class RNNordicDfuModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    public static final String LOG_TAG = "RNNordicDfu";
    private static final String name = "RNNordicDfu";
    private final String dfuStateEvent = "DFUStateChanged";
    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        public void onDeviceConnecting(String str) {
            RnPluginLog.b("rn-nodrc-dfu onDeviceConnecting, onEnablingDfuMode: " + str);
            RNNordicDfuModule.this.sendStateUpdate("CONNECTING", str);
        }

        public void onDfuProcessStarting(String str) {
            RnPluginLog.b("rn-nodrc-dfu onDfuProcessStarting, onEnablingDfuMode: " + str);
            RNNordicDfuModule.this.sendStateUpdate("DFU_PROCESS_STARTING", str);
        }

        public void onEnablingDfuMode(String str) {
            RnPluginLog.b("rn-nodrc-dfu onEnablingDfuMode, deviceAddress: " + str);
            RNNordicDfuModule.this.sendStateUpdate("ENABLING_DFU_MODE", str);
        }

        public void onFirmwareValidating(String str) {
            RnPluginLog.b("rn-nodrc-dfu onFirmwareValidating, deviceAddress: " + str);
            RNNordicDfuModule.this.sendStateUpdate("FIRMWARE_VALIDATING", str);
        }

        public void onDeviceDisconnecting(String str) {
            RnPluginLog.b("rn-nodrc-dfu onDeviceDisconnecting, deviceAddress: " + str);
            RNNordicDfuModule.this.sendStateUpdate("DEVICE_DISCONNECTING", str);
        }

        public void onDfuCompleted(String str) {
            RnPluginLog.b("rn-nodrc-dfu onDfuCompleted, deviceAddress: " + str);
            if (RNNordicDfuModule.this.mPromise != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("deviceAddress", str);
                RNNordicDfuModule.this.mPromise.resolve(writableNativeMap);
                Promise unused = RNNordicDfuModule.this.mPromise = null;
            }
            RNNordicDfuModule.this.sendStateUpdate("DFU_COMPLETED", str);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ((NotificationManager) RNNordicDfuModule.this.reactContext.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancel(283);
                }
            }, 200);
        }

        public void onDfuAborted(String str) {
            RnPluginLog.b("rn-nodrc-dfu onDfuAborted, deviceAddress: " + str);
            RNNordicDfuModule.this.sendStateUpdate("DFU_ABORTED", str);
            if (RNNordicDfuModule.this.mPromise != null) {
                RNNordicDfuModule.this.mPromise.reject("2", "DFU ABORTED");
                Promise unused = RNNordicDfuModule.this.mPromise = null;
            }
        }

        public void onProgressChanged(String str, int i, float f, float f2, int i2, int i3) {
            RnPluginLog.b("rn-nodrc-dfu onProgressChanged, percent: " + i);
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("deviceAddress", str);
            writableNativeMap.putInt("percent", i);
            writableNativeMap.putDouble("speed", (double) f);
            writableNativeMap.putDouble("avgSpeed", (double) f2);
            writableNativeMap.putInt("currentPart", i2);
            writableNativeMap.putInt("partsTotal", i3);
            RNNordicDfuModule.this.sendEvent("DFUProgress", writableNativeMap);
        }

        public void onError(String str, int i, int i2, String str2) {
            RnPluginLog.b("rn-nodrc-dfu onError, deviceAddress: " + str + "  error： " + i + "  errorType：" + i2 + "  message：" + str2);
            RNNordicDfuModule.this.sendStateUpdate("DFU_FAILED", str);
            if (RNNordicDfuModule.this.mPromise != null) {
                RNNordicDfuModule.this.mPromise.reject(Integer.toString(i), str2);
                Promise unused = RNNordicDfuModule.this.mPromise = null;
            }
        }
    };
    /* access modifiers changed from: private */
    public Promise mPromise = null;
    private final String progressEvent = "DFUProgress";
    /* access modifiers changed from: private */
    public final ReactApplicationContext reactContext;

    public String getName() {
        return "RNNordicDfu";
    }

    public void onHostPause() {
    }

    public RNNordicDfuModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addLifecycleEventListener(this);
        this.reactContext = reactApplicationContext;
        if (Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(reactApplicationContext);
        }
    }

    @ReactMethod
    public void startDFU(String str, String str2, String str3, Promise promise) {
        this.mPromise = promise;
        DfuServiceInitiator keepBond = new DfuServiceInitiator(str).setKeepBond(false);
        if (str2 != null) {
            keepBond.setDeviceName(str2);
        }
        keepBond.setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
        keepBond.setZip(str3);
        Class dfuServiceClass = getDfuServiceClass(getCurrentActivity());
        if (dfuServiceClass != null) {
            RnPluginLog.a("rn-nodrc-dfu, will start a service, " + dfuServiceClass.getName());
            keepBond.start(this.reactContext, dfuServiceClass);
            return;
        }
        RnPluginLog.b("rn-nodrc-dfu, service is null...");
    }

    /* access modifiers changed from: private */
    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) getReactApplicationContext().getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    /* access modifiers changed from: private */
    public void sendStateUpdate(String str, String str2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Log.d("RNNordicDfu", "State: " + str);
        writableNativeMap.putString("state", str);
        writableNativeMap.putString("deviceAddress", str2);
        sendEvent("DFUStateChanged", writableNativeMap);
    }

    public void onHostResume() {
        DfuServiceListenerHelper.registerProgressListener(this.reactContext, this.mDfuProgressListener);
    }

    public void onHostDestroy() {
        DfuServiceListenerHelper.unregisterProgressListener(this.reactContext, this.mDfuProgressListener);
    }

    private Class getDfuServiceClass(Context context) {
        String str;
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        int size = runningAppProcesses.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                str = null;
                break;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
            if (runningAppProcessInfo.pid == myPid) {
                str = runningAppProcessInfo.processName;
                break;
            }
            i++;
        }
        RnPluginLog.a("rn-nodrc-dfu, processName: " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.endsWith(CoreManager.j)) {
            return DfuService.class;
        }
        if (str.endsWith(CoreManager.k)) {
            return DfuService1.class;
        }
        if (str.endsWith(CoreManager.l)) {
            return DfuService2.class;
        }
        if (str.endsWith(CoreManager.m)) {
            return DfuService3.class;
        }
        return null;
    }
}
