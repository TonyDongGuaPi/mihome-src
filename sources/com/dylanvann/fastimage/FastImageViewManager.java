package com.dylanvann.fastimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.BaseRequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

class FastImageViewManager extends SimpleViewManager<FastImageViewWithUrl> implements FastImageProgressListener {
    private static final String REACT_CLASS = "FastImageView";
    private static final String REACT_ON_LOAD_START_EVENT = "onFastImageLoadStart";
    private static final String REACT_ON_PROGRESS_EVENT = "onFastImageProgress";
    private static final Map<String, List<FastImageViewWithUrl>> VIEWS_FOR_URLS = new WeakHashMap();
    @Nullable
    private RequestManager requestManager = null;

    public float getGranularityPercentage() {
        return 0.5f;
    }

    public String getName() {
        return REACT_CLASS;
    }

    FastImageViewManager() {
    }

    /* access modifiers changed from: protected */
    public FastImageViewWithUrl createViewInstance(ThemedReactContext themedReactContext) {
        if (isValidContextForGlide(themedReactContext)) {
            this.requestManager = Glide.c(themedReactContext.getApplicationContext());
        }
        return new FastImageViewWithUrl(themedReactContext);
    }

    @ReactProp(name = "source")
    public void setSrc(FastImageViewWithUrl fastImageViewWithUrl, @Nullable ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("uri") || isNullOrEmpty(readableMap.getString("uri"))) {
            if (this.requestManager != null) {
                this.requestManager.a((View) fastImageViewWithUrl);
            }
            if (fastImageViewWithUrl.glideUrl != null) {
                FastImageOkHttpProgressGlideModule.a(fastImageViewWithUrl.glideUrl.b());
            }
            fastImageViewWithUrl.setImageDrawable((Drawable) null);
            return;
        }
        FastImageSource a2 = FastImageViewConverter.a(fastImageViewWithUrl.getContext(), readableMap);
        GlideUrl f = a2.f();
        fastImageViewWithUrl.glideUrl = f;
        if (this.requestManager != null) {
            this.requestManager.a((View) fastImageViewWithUrl);
        }
        String b = f.b();
        FastImageOkHttpProgressGlideModule.a(b, this);
        List list = VIEWS_FOR_URLS.get(b);
        if (list != null && !list.contains(fastImageViewWithUrl)) {
            list.add(fastImageViewWithUrl);
        } else if (list == null) {
            VIEWS_FOR_URLS.put(b, new ArrayList(Collections.singletonList(fastImageViewWithUrl)));
        }
        ((RCTEventEmitter) ((ThemedReactContext) fastImageViewWithUrl.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(fastImageViewWithUrl.getId(), REACT_ON_LOAD_START_EVENT, new WritableNativeMap());
        if (this.requestManager != null) {
            this.requestManager.a(a2.d()).b((BaseRequestOptions<?>) FastImageViewConverter.b(readableMap)).a(new FastImageRequestListener(b)).a((ImageView) fastImageViewWithUrl);
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(FastImageViewWithUrl fastImageViewWithUrl, String str) {
        fastImageViewWithUrl.setScaleType(FastImageViewConverter.a(str));
    }

    public void onDropViewInstance(FastImageViewWithUrl fastImageViewWithUrl) {
        if (this.requestManager != null) {
            this.requestManager.a((View) fastImageViewWithUrl);
        }
        if (fastImageViewWithUrl.glideUrl != null) {
            String glideUrl = fastImageViewWithUrl.glideUrl.toString();
            FastImageOkHttpProgressGlideModule.a(glideUrl);
            List list = VIEWS_FOR_URLS.get(glideUrl);
            if (list != null) {
                list.remove(fastImageViewWithUrl);
                if (list.size() == 0) {
                    VIEWS_FOR_URLS.remove(glideUrl);
                }
            }
        }
        super.onDropViewInstance(fastImageViewWithUrl);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(REACT_ON_LOAD_START_EVENT, MapBuilder.of("registrationName", REACT_ON_LOAD_START_EVENT)).put(REACT_ON_PROGRESS_EVENT, MapBuilder.of("registrationName", REACT_ON_PROGRESS_EVENT)).put("onFastImageLoad", MapBuilder.of("registrationName", "onFastImageLoad")).put("onFastImageError", MapBuilder.of("registrationName", "onFastImageError")).put("onFastImageLoadEnd", MapBuilder.of("registrationName", "onFastImageLoadEnd")).build();
    }

    public void onProgress(String str, long j, long j2) {
        List<FastImageViewWithUrl> list = VIEWS_FOR_URLS.get(str);
        if (list != null) {
            for (FastImageViewWithUrl fastImageViewWithUrl : list) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("loaded", (int) j);
                writableNativeMap.putInt("total", (int) j2);
                ((RCTEventEmitter) ((ThemedReactContext) fastImageViewWithUrl.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(fastImageViewWithUrl.getId(), REACT_ON_PROGRESS_EVENT, writableNativeMap);
            }
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean isValidContextForGlide(Context context) {
        if (context == null) {
            return false;
        }
        if ((context instanceof Activity) && isActivityDestroyed((Activity) context)) {
            return false;
        }
        if (context instanceof ThemedReactContext) {
            Context baseContext = ((ThemedReactContext) context).getBaseContext();
            if (baseContext instanceof Activity) {
                return !isActivityDestroyed((Activity) baseContext);
            }
        }
        return true;
    }

    private static boolean isActivityDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity.isDestroyed() || activity.isFinishing()) {
                return true;
            }
            return false;
        } else if (activity.isFinishing() || activity.isChangingConfigurations()) {
            return true;
        } else {
            return false;
        }
    }
}
