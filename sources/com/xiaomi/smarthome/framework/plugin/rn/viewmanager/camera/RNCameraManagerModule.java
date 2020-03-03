package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.os.Handler;
import android.os.Looper;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "MHCameraSDK")
public class RNCameraManagerModule extends ReactContextBaseJavaModule {
    Handler mMainHandler = new Handler(Looper.getMainLooper());

    public String getName() {
        return "MHCameraSDK";
    }

    public RNCameraManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void startConnect(String str, final String str2) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(deviceByDid, str2);
                }
            });
        }
    }

    @ReactMethod
    public void sendServerCmd(String str, int i, String str2, Callback callback) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            final int i2 = i;
            final String str3 = str2;
            final Callback callback2 = callback;
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(deviceByDid, i2, str3, callback2);
                }
            });
        }
    }

    @ReactMethod
    public void release(final String str) {
        if (XmPluginHostApi.instance().getDeviceByDid(str) != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(str);
                }
            });
        }
    }

    @ReactMethod
    public void bindP2PCommandReceiveWithDid(String str, final String str2) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().b(deviceByDid, str2);
                }
            });
        }
    }

    @ReactMethod
    public void bindRDTDataReceiveCallback(String str, final String str2) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().c(deviceByDid, str2);
                }
            });
        }
    }

    @ReactMethod
    public void sendRDTCommandToDevice(String str, final String str2, final Callback callback) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(deviceByDid, str2, callback);
                }
            });
        }
    }

    @ReactMethod
    public void pausePlay(String str) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().d(deviceByDid);
                }
            });
        }
    }

    @ReactMethod
    public void isConnected(String str, String str2) {
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            RNCameraManager.a().e(deviceByDid);
        }
    }

    @ReactMethod
    public void showAlarmVideos(String str, final int i) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().b(deviceByDid, i);
                }
            });
        }
    }

    @ReactMethod
    public void showCloudStorage(String str, final boolean z, final boolean z2) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(deviceByDid, z, z2);
                }
            });
        }
    }

    @ReactMethod
    public void showCloudStorageSetting(String str) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().f(deviceByDid);
                }
            });
        }
    }

    @ReactMethod
    public void showFaceRecognize(String str, final boolean z) {
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraManager.a().a(deviceByDid, z);
                }
            });
        }
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return super.getConstants();
    }
}
