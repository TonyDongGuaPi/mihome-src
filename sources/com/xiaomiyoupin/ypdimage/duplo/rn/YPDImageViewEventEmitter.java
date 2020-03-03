package com.xiaomiyoupin.ypdimage.duplo.rn;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomiyoupin.ypdimage.YPDImageView;
import com.xiaomiyoupin.ypdimage.YPDSource;

class YPDImageViewEventEmitter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1789a = "onLoadEvent";
    private YPDSource.OnLoadEvent b;

    YPDImageViewEventEmitter(YPDImageView yPDImageView) {
        this.b = new OnLoadEventImpl(yPDImageView);
    }

    /* access modifiers changed from: package-private */
    public YPDSource.OnLoadEvent a() {
        return this.b;
    }

    private static class OnLoadEventImpl implements YPDSource.OnLoadEvent {

        /* renamed from: a  reason: collision with root package name */
        private YPDImageView f1790a;

        OnLoadEventImpl(YPDImageView yPDImageView) {
            this.f1790a = yPDImageView;
        }

        public void a() {
            YPDImageViewEventEmitter.e(this.f1790a);
        }

        public void b() {
            YPDImageViewEventEmitter.f(this.f1790a);
        }

        public void c() {
            YPDImageViewEventEmitter.d(this.f1790a);
        }

        public void a(float f, float f2) {
            YPDImageViewEventEmitter.b(this.f1790a, f, f2);
        }
    }

    /* access modifiers changed from: private */
    public static void d(YPDImageView yPDImageView) {
        if (yPDImageView != null && (yPDImageView.getContext() instanceof ThemedReactContext)) {
            int id = yPDImageView.getId();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("name", "onLoadStart");
            ((RCTEventEmitter) ((ThemedReactContext) yPDImageView.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(id, f1789a, writableNativeMap);
        }
    }

    /* access modifiers changed from: private */
    public static void b(YPDImageView yPDImageView, float f, float f2) {
        if (yPDImageView != null && (yPDImageView.getContext() instanceof ThemedReactContext)) {
            int id = yPDImageView.getId();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("name", "onLoadProgress");
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            writableNativeMap2.putDouble("current", (double) f);
            writableNativeMap2.putDouble("total", (double) f2);
            writableNativeMap.putMap("params", writableNativeMap2);
            ((RCTEventEmitter) ((ThemedReactContext) yPDImageView.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(id, f1789a, writableNativeMap);
        }
    }

    /* access modifiers changed from: private */
    public static void e(YPDImageView yPDImageView) {
        if (yPDImageView != null && (yPDImageView.getContext() instanceof ThemedReactContext)) {
            int id = yPDImageView.getId();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("name", "onLoadFinish");
            ((RCTEventEmitter) ((ThemedReactContext) yPDImageView.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(id, f1789a, writableNativeMap);
        }
    }

    /* access modifiers changed from: private */
    public static void f(YPDImageView yPDImageView) {
        if (yPDImageView != null && (yPDImageView.getContext() instanceof ThemedReactContext)) {
            int id = yPDImageView.getId();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("name", "onLoadFailed");
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeMap writableNativeMap3 = new WritableNativeMap();
            writableNativeMap3.putInt("code", -1);
            writableNativeMap3.putString("message", "图片加载失败");
            writableNativeMap2.putMap("error", writableNativeMap3);
            writableNativeMap.putMap("params", writableNativeMap2);
            ((RCTEventEmitter) ((ThemedReactContext) yPDImageView.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(id, f1789a, writableNativeMap);
        }
    }
}
