package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.os.RemoteException;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;
import com.xiaomi.smarthome.device.api.spec.operation.controller.DeviceController;
import java.util.HashMap;

public class MIOTSpecModule extends MIOTBaseJavaModule {
    private static final String TAG = "MIOTSpecModule";
    private HashMap<String, DeviceController> mCacheController = new HashMap<>();

    public String getName() {
        return "MIOTSpec";
    }

    public MIOTSpecModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getSpecString(String str, Callback callback) {
        try {
            callback.invoke(true, PluginServiceManager.a().b().getSpecInstanceStr(str));
        } catch (RemoteException e) {
            callback.invoke(false, Log.getStackTraceString(e));
        }
    }

    @ReactMethod
    public void getProperty(String str, int i, int i2, Callback callback) {
        try {
            Object propertyValue = getSpecDeviceController(str).getPropertyValue(i, i2);
            Object[] objArr = new Object[2];
            objArr[0] = true;
            objArr[1] = propertyValue == null ? "" : propertyValue.toString();
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public void setProperty(String str, int i, int i2, int i3, String str2) {
        PropertyParam newPropertyParam = getSpecDeviceController(str).newPropertyParam(i, i2);
        newPropertyParam.setResultCode(i3);
        newPropertyParam.setValue(str2);
        getSpecDeviceController(str).getPropertyController(i, i2).updateValue(newPropertyParam, false);
    }

    @ReactMethod
    public void getCurrentSpecValueWithDid(String str, Promise promise) {
        try {
            promise.resolve(PluginServiceManager.a().b().getSpecProptyValueFromSpecCard(str));
        } catch (RemoteException e) {
            promise.reject((Throwable) e);
        }
    }

    private DeviceController getSpecDeviceController(String str) {
        DeviceController deviceController = this.mCacheController.get(str);
        if (deviceController != null) {
            return deviceController;
        }
        DeviceController specDeviceController = XmPluginHostApi.instance().getSpecDeviceController(str);
        this.mCacheController.put(str, specDeviceController);
        return specDeviceController;
    }
}
