package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.util.ArrayList;

@ReactModule(name = "MHSweepingMapManager")
public class MiotMapSweeperModule extends ReactContextBaseJavaModule {
    public MiotMapSweeperModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        RnPluginLog.a("MHSweepingMapManager init ...");
        return "MHSweepingMapManager";
    }

    @ReactMethod
    public void positionWithImageName(String str, int i, Callback callback) {
        ZoomMapSweeperView zoomMapSweeperView = (ZoomMapSweeperView) getCurrentActivity().findViewById(i);
        if (callback != null) {
            if (zoomMapSweeperView == null) {
                callback.invoke(false, generateWritableMap(-1, "cannot get view by view id, view id is " + i));
                return;
            }
            WritableMap createMap = Arguments.createMap();
            MapPoint imagePostion = zoomMapSweeperView.getMapSweeperView().getImagePostion(str);
            if (imagePostion == null) {
                callback.invoke(false, generateWritableMap(-1, "cannot get image from imageName, imageName is " + str));
                return;
            }
            createMap.putInt("code", 0);
            createMap.putInt("x", imagePostion.f17616a);
            createMap.putInt(Constants.Name.Y, imagePostion.b);
            callback.invoke(true, createMap);
        }
    }

    @ReactMethod
    public void cleanMapView(int i) {
        final ZoomMapSweeperView zoomMapSweeperView = (ZoomMapSweeperView) getCurrentActivity().findViewById(i);
        if (zoomMapSweeperView == null) {
            RnPluginLog.b("can not findView by id, id is " + i);
            return;
        }
        zoomMapSweeperView.post(new Runnable() {
            public void run() {
                MiotMapSweeperModule.this.cleanMapView(zoomMapSweeperView);
            }
        });
    }

    /* access modifiers changed from: private */
    public void cleanMapView(ZoomMapSweeperView zoomMapSweeperView) {
        Image image = new Image((ReadableArray) null, (ReadableArray) null, "", 1, 1, 0, 1, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(image);
        zoomMapSweeperView.getMapSweeperView().refreshCommonSweeperViews(arrayList);
        zoomMapSweeperView.getMapSweeperView().cleanMapSweeperLines();
        zoomMapSweeperView.getMapSweeperView().cleanFloorAndSquare();
        zoomMapSweeperView.zoomTo(1.0f, 0.0f, 0.0f);
        zoomMapSweeperView.getRootView().invalidate();
        zoomMapSweeperView.invalidate();
    }

    private WritableMap generateWritableMap(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("code", i);
        createMap.putString("message", str);
        return createMap;
    }
}
