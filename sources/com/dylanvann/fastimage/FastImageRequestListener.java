package com.dylanvann.fastimage;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class FastImageRequestListener implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    static final String f5285a = "onFastImageError";
    static final String b = "onFastImageLoad";
    static final String c = "onFastImageLoadEnd";
    private String d;

    FastImageRequestListener(String str) {
        this.d = str;
    }

    private static WritableMap a(Drawable drawable) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", drawable.getIntrinsicWidth());
        writableNativeMap.putInt("height", drawable.getIntrinsicHeight());
        return writableNativeMap;
    }

    public boolean a(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
        FastImageOkHttpProgressGlideModule.a(this.d);
        if (!(target instanceof ImageViewTarget)) {
            return false;
        }
        FastImageViewWithUrl fastImageViewWithUrl = (FastImageViewWithUrl) ((ImageViewTarget) target).j();
        RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ThemedReactContext) fastImageViewWithUrl.getContext()).getJSModule(RCTEventEmitter.class);
        int id = fastImageViewWithUrl.getId();
        rCTEventEmitter.receiveEvent(id, f5285a, new WritableNativeMap());
        rCTEventEmitter.receiveEvent(id, c, new WritableNativeMap());
        return false;
    }

    public boolean a(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
        if (!(target instanceof ImageViewTarget)) {
            return false;
        }
        FastImageViewWithUrl fastImageViewWithUrl = (FastImageViewWithUrl) ((ImageViewTarget) target).j();
        RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ThemedReactContext) fastImageViewWithUrl.getContext()).getJSModule(RCTEventEmitter.class);
        int id = fastImageViewWithUrl.getId();
        rCTEventEmitter.receiveEvent(id, b, a(drawable));
        rCTEventEmitter.receiveEvent(id, c, new WritableNativeMap());
        return false;
    }
}
