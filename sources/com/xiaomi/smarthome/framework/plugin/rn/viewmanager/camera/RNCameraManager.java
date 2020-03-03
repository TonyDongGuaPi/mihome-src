package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.HandlerThread;
import android.util.Base64;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.imagepicker.ResponseHelper;
import com.mijia.app.Event;
import com.mijia.camera.MijiaCameraDevice;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmGuideActivity;
import com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoActivity;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoWebActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class RNCameraManager {
    private static final String c = "RnCameraManager";
    private static RNCameraManager d;

    /* renamed from: a  reason: collision with root package name */
    HandlerThread f17579a = new HandlerThread("rn_camera_manager");
    ResponseHelper b = new ResponseHelper();
    private HashMap<String, Object> e = new HashMap<>();
    private HashMap<String, View> f = new HashMap<>();
    private HashMap<String, RNCameraCallbackComponent> g = new HashMap<>();

    interface RNCallback {
        void a(int i);

        void a(int i, String str);

        void a(String str, Object obj);
    }

    public void a(DeviceStat deviceStat) {
    }

    public static RNCameraManager a() {
        if (d == null) {
            d = new RNCameraManager();
        }
        return d;
    }

    public void a(final DeviceStat deviceStat, String str) {
        RnPluginLog.d("rncamera startconnect");
        final RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx == null) {
            RNCameraPlayerEx rNCameraPlayerEx2 = new RNCameraPlayerEx(MijiaCameraDevice.a(deviceStat));
            this.e.put(deviceStat.did, rNCameraPlayerEx2);
            rNCameraPlayerEx = rNCameraPlayerEx2;
        }
        d(deviceStat, str);
        rNCameraPlayerEx.a(deviceStat, (RNCallback) new RNCallback() {
            public void a(int i) {
            }

            public void a(String str, Object obj) {
                RNCameraManager.this.a(deviceStat, rNCameraPlayerEx.d() ? 2 : 1, 0);
            }

            public void a(int i, String str) {
                RNCameraManager.this.a(deviceStat, 0, i);
            }
        });
    }

    private void d(DeviceStat deviceStat, String str) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            rNCameraCallbackComponent = new RNCameraCallbackComponent();
            this.g.put(deviceStat.did, rNCameraCallbackComponent);
        }
        rNCameraCallbackComponent.a(str);
    }

    private String g(DeviceStat deviceStat) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            return "";
        }
        return rNCameraCallbackComponent.a();
    }

    private void e(DeviceStat deviceStat, String str) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            rNCameraCallbackComponent = new RNCameraCallbackComponent();
            this.g.put(deviceStat.did, rNCameraCallbackComponent);
        }
        rNCameraCallbackComponent.b(str);
    }

    private String h(DeviceStat deviceStat) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            return "";
        }
        return rNCameraCallbackComponent.b();
    }

    private void f(DeviceStat deviceStat, String str) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            rNCameraCallbackComponent = new RNCameraCallbackComponent();
            this.g.put(deviceStat.did, rNCameraCallbackComponent);
        }
        rNCameraCallbackComponent.c(str);
    }

    private String i(DeviceStat deviceStat) {
        RNCameraCallbackComponent rNCameraCallbackComponent = this.g.get(deviceStat.did);
        if (rNCameraCallbackComponent == null) {
            return "";
        }
        return rNCameraCallbackComponent.c();
    }

    public void a(DeviceStat deviceStat, int i) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.a(i);
        }
    }

    public void b(DeviceStat deviceStat) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.e();
        }
    }

    public void a(DeviceStat deviceStat, int i, int i2, int i3, int i4) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.a(i, i2, i3, i4);
        }
    }

    public void c(DeviceStat deviceStat) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.b();
        }
    }

    public void a(DeviceStat deviceStat, String str, final Callback callback) {
        byte[] bArr;
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            try {
                new JSONObject(str);
                bArr = str.getBytes();
            } catch (JSONException unused) {
                bArr = Base64.decode(str, 2);
            }
            rNCameraPlayerEx.a(bArr, (RNCallback) new RNCallback() {
                public void a(int i) {
                }

                public void a(String str, Object obj) {
                    RNCameraManager.this.a(str, callback);
                }

                public void a(int i, String str) {
                    RNCameraManager.this.a(String.valueOf(i), str, callback);
                }
            });
        }
    }

    public void a(DeviceStat deviceStat, int i, String str, final Callback callback) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.a(i, str, new RNCallback() {
                public void a(int i) {
                }

                public void a(String str, Object obj) {
                    RNCameraManager.this.a(str, callback);
                }

                public void a(int i, String str) {
                    RNCameraManager.this.a(String.valueOf(i), str, callback);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void a(CameraDevice cameraDevice, VideoFrame videoFrame) {
        if (cameraDevice != null && videoFrame != null) {
            String did = cameraDevice.getDid();
            if (this.f.get(did) != null) {
                View view = this.f.get(did);
                if (view instanceof RNVideoViewEx) {
                    ((RNVideoViewEx) view).drawVideoFrame(videoFrame);
                }
            }
        }
    }

    public void a(View view, String str, boolean z, int i) {
        if (!StringUtil.c(str) && view != null) {
            this.f.put(str, view);
            if (view instanceof RNVideoViewEx) {
                ((RNVideoViewEx) view).initInVideoView(z, i);
            }
        }
    }

    public void d(DeviceStat deviceStat) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.c();
        }
    }

    public boolean e(DeviceStat deviceStat) {
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.get(deviceStat.did);
        if (rNCameraPlayerEx == null) {
            return false;
        }
        return rNCameraPlayerEx.d();
    }

    public void a(DeviceStat deviceStat, int i, int i2) {
        RnPluginLog.d("rncamera connectState changed " + i);
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("state", i);
        createMap.putString("error", String.valueOf(i2));
        a(createMap, g(deviceStat));
    }

    public void a(DeviceStat deviceStat, int i, byte[] bArr) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(CommandMessage.COMMAND, String.valueOf(i));
        try {
            createMap.putString("data", new JSONObject(new String(bArr)).toString());
        } catch (Exception unused) {
            createMap.putString("data", Base64.encodeToString(bArr, 2));
        }
        a(createMap, h(deviceStat));
    }

    public void a(DeviceStat deviceStat, byte[] bArr) {
        WritableMap createMap = Arguments.createMap();
        try {
            String str = new String(bArr);
            new JSONObject(str);
            createMap.putString("data", str);
        } catch (Exception unused) {
            createMap.putString("data", Base64.encodeToString(bArr, 2));
        }
        a(createMap, i(deviceStat));
    }

    private void a(WritableMap writableMap, String str) {
        ReactContext currentReactContext;
        ReactInstanceManager l = RNRuntime.a().l();
        if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
            RnPluginLog.b("rncamera ReactInstanceManager is null, can not send event, eventName: " + str);
        } else if (RNRuntime.a().n()) {
            if (writableMap == null) {
                writableMap = Arguments.createMap();
            }
            RnPluginLog.a("rncamera will send event, eventName: " + str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        } else {
            RnPluginLog.b("rncamera can not send event, eventName: " + str);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Callback callback) {
        callback.invoke(true, str);
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, Callback callback) {
        callback.invoke(false, str);
    }

    public void a(String str) {
        this.g.remove(str);
        RNCameraPlayerEx rNCameraPlayerEx = (RNCameraPlayerEx) this.e.remove(str);
        if (rNCameraPlayerEx != null) {
            rNCameraPlayerEx.b(true);
        }
        RNVideoViewEx rNVideoViewEx = (RNVideoViewEx) this.f.remove(str);
        if (rNVideoViewEx != null) {
            rNVideoViewEx.release();
        }
    }

    public void b(DeviceStat deviceStat, String str) {
        e(deviceStat, str);
    }

    public void c(DeviceStat deviceStat, String str) {
        f(deviceStat, str);
    }

    public void b(DeviceStat deviceStat, int i) {
        ReactContext currentReactContext;
        ReactInstanceManager l = RNRuntime.a().l();
        if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
            RnPluginLog.b("rncamera ReactInstanceManager is null, can not show alarm videos");
            return;
        }
        Activity currentActivity = currentReactContext.getCurrentActivity();
        if (currentActivity == null) {
            RnPluginLog.b("the activity to which the reactContext currently attached is null");
            return;
        }
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        Event.a(Event.d);
        Event.a(Event.az);
        Intent intent = new Intent();
        intent.putExtra("extra_device_did", deviceStat.did);
        intent.putExtra("is_v4", false);
        if (!a2.e().j() || a2.isReadOnlyShared()) {
            intent.setClass(currentReactContext, AlarmVideoActivity.class);
        } else {
            intent.setClass(currentReactContext, AlarmGuideActivity.class);
        }
        currentActivity.startActivity(intent);
    }

    public void a(DeviceStat deviceStat, boolean z, boolean z2) {
        ReactContext currentReactContext;
        ReactInstanceManager l = RNRuntime.a().l();
        if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
            RnPluginLog.b("rncamera ReactInstanceManager is null, can not show cloud storage");
            return;
        }
        Activity currentActivity = currentReactContext.getCurrentActivity();
        if (currentActivity == null) {
            RnPluginLog.b("the activity to which the reactContext currently attached is null");
            return;
        }
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        Event.a(Event.aC);
        if (a2.e().f() || a2.e().e()) {
            FrameManager.b().k().openCloudVideoListActivity(currentActivity, a2.getDid(), a2.getName());
        } else {
            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(currentActivity, a2.getDid());
        }
        a2.e().j(true);
    }

    public void f(DeviceStat deviceStat) {
        ReactContext currentReactContext;
        ReactInstanceManager l = RNRuntime.a().l();
        if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
            RnPluginLog.b("rncamera ReactInstanceManager is null, can not show cloud storage setting");
            return;
        }
        Activity currentActivity = currentReactContext.getCurrentActivity();
        if (currentActivity == null) {
            RnPluginLog.b("the activity to which the reactContext currently attached is null");
            return;
        }
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        if (!CoreApi.a().D() && !a2.isShared()) {
            Intent intent = new Intent(currentActivity, CloudVideoWebActivity.class);
            intent.putExtra("title", currentActivity.getString(R.string.cs_my_service));
            intent.putExtra("url", CloudVideoWebActivity.REQUEST_CLOUD_MANAGEMENT);
            intent.putExtra("did", deviceStat.did);
            Event.a(Event.bf);
            currentActivity.startActivity(intent);
        }
    }

    public void a(DeviceStat deviceStat, boolean z) {
        ReactContext currentReactContext;
        ReactInstanceManager l = RNRuntime.a().l();
        if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
            RnPluginLog.b("rncamera ReactInstanceManager is null, can not show face recognize");
            return;
        }
        Activity currentActivity = currentReactContext.getCurrentActivity();
        if (currentActivity == null) {
            RnPluginLog.b("the activity to which the reactContext currently attached is null");
            return;
        }
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        Event.a(Event.aD);
        if (!z) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("is_vip_user", false);
                jSONObject.put("is_from_camera", true);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            FrameManager.b().k().openFaceManagerActivity(BaseQuickAdapter.u, currentActivity, a2.getDid(), jSONObject.toString());
            return;
        }
        FrameManager.b().k().openFaceManagerActivity(currentActivity, a2.getDid());
    }

    public void b(String str) {
        if (!StringUtil.c(str)) {
            View remove = this.f.remove(str);
            if (remove instanceof RNVideoViewEx) {
                ((RNVideoViewEx) remove).releaseSelf();
            }
        }
    }
}
