package com.dylanvann.fastimage;

import android.app.Activity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class FastImageViewModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "FastImageView";

    public String getName() {
        return REACT_CLASS;
    }

    FastImageViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void preload(final ReadableArray readableArray) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    String str;
                    for (int i = 0; i < readableArray.size(); i++) {
                        ReadableMap map = readableArray.getMap(i);
                        FastImageSource a2 = FastImageViewConverter.a(currentActivity, map);
                        RequestManager c2 = Glide.c(currentActivity.getApplicationContext());
                        if (a2.a()) {
                            str = a2.getSource();
                        } else {
                            str = a2.isResource() ? a2.getUri().toString() : a2.f().toString();
                        }
                        c2.a(str).b((BaseRequestOptions<?>) FastImageViewConverter.b(map)).c();
                    }
                }
            });
        }
    }
}
