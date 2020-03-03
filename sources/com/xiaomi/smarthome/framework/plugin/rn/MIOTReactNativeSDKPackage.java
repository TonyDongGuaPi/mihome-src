package com.xiaomi.smarthome.framework.plugin.rn;

import android.util.Log;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewManager;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.ClassicBluetoothModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTBluetoothModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTDeviceModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTHostModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTMapSearchModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPackageModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTRoomManageModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTServiceModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTSpecModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTXimalayaModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.HmPaceScalesModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.MHMiBandCardModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.chuangmi.CmTJInfraModule;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.AMapViewManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.FakeMapViewManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.InputDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.LoadingDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MHNavigationBar;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MessageDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MiotImageCapInsetManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MultiChoseDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.NumberPickerManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.ProgressDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.SingleChoseDialogManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.StringPickerManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.image.MHImageViewManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview.MIOTScrollLayoutManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview.MIOTScrollViewManager;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.surfaceview.MHRSurfaceViewManager;
import java.util.ArrayList;
import java.util.List;

public class MIOTReactNativeSDKPackage implements ReactPackage {

    /* renamed from: a  reason: collision with root package name */
    private boolean f17215a;
    private MIOTPackageModule b;
    private MIOTDeviceModule c;
    private MIOTPersistModule d;
    private final long[] e = {0};

    MIOTReactNativeSDKPackage(boolean z) {
        this.f17215a = z;
        a();
    }

    public void a(boolean z) {
        this.f17215a = z;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.e[0] = System.currentTimeMillis();
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        this.b = new MIOTPackageModule(reactApplicationContext, this.f17215a);
        this.c = new MIOTDeviceModule(reactApplicationContext);
        arrayList.add(this.b);
        arrayList.add(this.c);
        arrayList.add(new MIOTBluetoothModule(reactApplicationContext));
        arrayList.add(new MIOTServiceModule(reactApplicationContext));
        arrayList.add(new MIOTHostModule(reactApplicationContext));
        arrayList.add(new MIOTMapSearchModule(reactApplicationContext));
        this.d = new MIOTPersistModule(reactApplicationContext);
        arrayList.add(this.d);
        arrayList.add(new MIOTAudioModule(reactApplicationContext));
        arrayList.add(new MIOTSpecModule(reactApplicationContext));
        arrayList.add(new MIOTXimalayaModule(reactApplicationContext));
        arrayList.add(new MIOTRoomManageModule(reactApplicationContext));
        arrayList.add(new HmPaceScalesModule(reactApplicationContext));
        arrayList.add(new CmTJInfraModule(reactApplicationContext));
        arrayList.add(new MHMiBandCardModule(reactApplicationContext));
        arrayList.add(new ClassicBluetoothModule(reactApplicationContext));
        return arrayList;
    }

    public ReadableMap b() {
        WritableMap createMap = Arguments.createMap();
        if (this.b != null) {
            createMap.putMap("package", Arguments.makeNativeMap(this.b.getConstants()));
            createMap.putMap("device", Arguments.makeNativeMap(this.c.getConstants()));
            createMap.putMap("file", Arguments.makeNativeMap(this.d.getConstants()));
        }
        return createMap;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MHNavigationBar());
        arrayList.add(new MessageDialogManager());
        arrayList.add(new InputDialogManager());
        arrayList.add(new SingleChoseDialogManager());
        arrayList.add(new MultiChoseDialogManager());
        arrayList.add(new LoadingDialogManager());
        arrayList.add(new ProgressDialogManager());
        arrayList.add(new MiotImageCapInsetManager());
        try {
            getClass().getClassLoader().loadClass("com.amap.api.maps2d.MapView");
            arrayList.add(new AMapViewManager());
        } catch (Throwable th) {
            arrayList.add(new FakeMapViewManager());
            LogUtilGrey.a("MIOTReactNativeSDKPackage", Log.getStackTraceString(th));
        }
        arrayList.add(new NumberPickerManager());
        arrayList.add(new MIOTScrollViewManager());
        arrayList.add(new MIOTScrollLayoutManager());
        arrayList.add(new MHImageViewManager());
        arrayList.add(new StringPickerManager());
        arrayList.add(new MHRSurfaceViewManager());
        return arrayList;
    }
}
