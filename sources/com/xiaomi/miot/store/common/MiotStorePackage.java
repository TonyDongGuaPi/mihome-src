package com.xiaomi.miot.store.common;

import android.content.Intent;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import com.facebook.react.uimanager.ViewManager;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import com.lwansbrough.RCTCamera.RCTCameraViewManager;
import com.lwansbrough.RCTCamera.RCTScaleModule;
import com.reactnativecommunity.asyncstorage.AsyncStorageModule;
import com.reactnativecommunity.netinfo.NetInfoModule;
import com.reactnativecommunity.viewpager.ReactViewPagerManager;
import com.xiaomi.miot.store.component.image.ReactImageManager;
import com.xiaomi.miot.store.component.pullrefresh.YPPullRefreshLayoutManager;
import com.xiaomi.miot.store.component.scrollView.MiotReactHorizontalScrollViewManager;
import com.xiaomi.miot.store.component.scrollView.MiotReactScrollViewManager;
import com.xiaomi.miot.store.component.swiperefresh.MiotSwipeRefreshLayoutManager;
import com.xiaomi.miot.store.component.webview.RNCWebViewManager;
import com.xiaomi.miot.store.component.webview.RNCWebViewModule;
import com.xiaomi.miot.store.module.AlipayModule;
import com.xiaomi.miot.store.module.AppInfoModule;
import com.xiaomi.miot.store.module.BroadcastModule;
import com.xiaomi.miot.store.module.CommonBridgeModule;
import com.xiaomi.miot.store.module.CommonEventModule;
import com.xiaomi.miot.store.module.ConcurrentEventModule;
import com.xiaomi.miot.store.module.CookieManagerModule;
import com.xiaomi.miot.store.module.CustomToastModule;
import com.xiaomi.miot.store.module.LoginModule;
import com.xiaomi.miot.store.module.MIMCMsgBridgeModule;
import com.xiaomi.miot.store.module.MSExceptionManagerModule;
import com.xiaomi.miot.store.module.MessageCenterBridgeModule;
import com.xiaomi.miot.store.module.MipayModule;
import com.xiaomi.miot.store.module.ShareModule;
import com.xiaomi.miot.store.module.SystemUIModule;
import com.xiaomi.miot.store.module.UCashierModule;
import com.xiaomi.miot.store.module.UnionMiPayModule;
import com.xiaomi.miot.store.module.WxpayModule;
import com.xiaomi.miot.store.module.storage.FileAsyncStorage;
import com.xiaomi.miot.store.module.storage.MemoryAsyncStorage;
import com.xiaomi.miot.store.module.storage.SPAsyncStorage;
import com.xiaomiyoupin.toast.YPDToast;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.duplo.rn.YPDImageViewManager;
import java.util.Arrays;
import java.util.List;
import org.reactnative.camera.CameraModule;
import org.reactnative.camera.CameraViewManager;
import org.reactnative.facedetector.FaceDetectorModule;

public class MiotStorePackage implements ReactPackage {

    /* renamed from: a  reason: collision with root package name */
    ReactImageManager f11378a;
    RNCWebViewManager b;
    MiotReactScrollViewManager c;
    MiotReactHorizontalScrollViewManager d;
    MiotSwipeRefreshLayoutManager e;
    YPPullRefreshLayoutManager f;
    RCTCameraViewManager g;

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new NativeModule[]{new FileAsyncStorage(reactApplicationContext), new MemoryAsyncStorage(reactApplicationContext), new SPAsyncStorage(reactApplicationContext), new AlipayModule(reactApplicationContext), new WxpayModule(reactApplicationContext), new UCashierModule(reactApplicationContext), new MipayModule(reactApplicationContext), new UnionMiPayModule(reactApplicationContext), new LoginModule(reactApplicationContext), new ShareModule(reactApplicationContext), new AppInfoModule(reactApplicationContext), new CookieManagerModule(reactApplicationContext), new SystemUIModule(reactApplicationContext), new CommonBridgeModule(reactApplicationContext), new MessageCenterBridgeModule(reactApplicationContext), new BroadcastModule(reactApplicationContext), new CommonEventModule(reactApplicationContext), new RCTCameraModule(reactApplicationContext), new CameraModule(reactApplicationContext), new FaceDetectorModule(reactApplicationContext), new RCTScaleModule(reactApplicationContext), new MSExceptionManagerModule(new DisabledDevSupportManager()), ConcurrentEventModule.getInstance(), new CustomToastModule(reactApplicationContext), new MIMCMsgBridgeModule(reactApplicationContext), YPDToast.getInstance().createRNModuleInstance(reactApplicationContext), YPDImage.a().a(reactApplicationContext), new RNCWebViewModule(reactApplicationContext), new NetInfoModule(reactApplicationContext), new AsyncStorageModule(reactApplicationContext)});
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        if (this.f11378a == null) {
            this.f11378a = new ReactImageManager();
        }
        if (this.b == null) {
            this.b = new RNCWebViewManager();
        }
        if (this.c == null) {
            this.c = new MiotReactScrollViewManager();
        }
        if (this.e == null) {
            this.e = new MiotSwipeRefreshLayoutManager();
        }
        if (this.f == null) {
            this.f = new YPPullRefreshLayoutManager();
        }
        if (this.d == null) {
            this.d = new MiotReactHorizontalScrollViewManager();
        }
        if (this.g == null) {
            this.g = new RCTCameraViewManager();
        }
        return Arrays.asList(new ViewManager[]{this.f11378a, this.b, this.c, this.d, this.e, this.f, this.g, new YPDImageViewManager(), new ReactViewPagerManager(), new CameraViewManager()});
    }

    public void a(int i, int i2, Intent intent) {
        if (this.b != null) {
            this.b.onActivityResult(i, i2, intent);
        }
    }
}
