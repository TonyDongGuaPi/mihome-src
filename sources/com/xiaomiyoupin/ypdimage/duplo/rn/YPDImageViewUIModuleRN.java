package com.xiaomiyoupin.ypdimage.duplo.rn;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.data.InnerVersionMessage;
import com.xiaomiyoupin.ypdimage.data.Message;
import com.xiaomiyoupin.ypdimage.data.PrefetchMessage;
import java.util.HashMap;

@ReactModule(name = "YPDImageLoaderModule")
public class YPDImageViewUIModuleRN extends ReactContextBaseJavaModule {
    private InnerVersionMessage innerVersionMessage;
    private HashMap<String, Target> targetHashMap = new HashMap<>();

    @ReactMethod
    public void recycle(String str) {
    }

    public YPDImageViewUIModuleRN(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public String getName() {
        return YPDImage.a().c();
    }

    @ReactMethod
    public void getInnerVersion(Callback callback) {
        if (callback != null) {
            YPDCallback yPDCallback = new YPDCallback(callback);
            if (this.innerVersionMessage == null) {
                this.innerVersionMessage = new InnerVersionMessage();
                this.innerVersionMessage.setError(new Message.Error());
                this.innerVersionMessage.setResult(new InnerVersionMessage.Result());
            }
            this.innerVersionMessage.getResult().setInnerVersion(String.valueOf(YPDImage.b));
            yPDCallback.invoke(this.innerVersionMessage.getError().toString(), this.innerVersionMessage.getResult().toString());
        }
    }

    @ReactMethod
    public void prefetch(final String str, Callback callback) {
        RequestBuilder<Drawable> b = Glide.c(getReactApplicationContext()).a(str);
        if (callback != null) {
            final YPDCallback yPDCallback = new YPDCallback(callback);
            b = b.a((RequestListener<Drawable>) new RequestListener<Drawable>() {
                public boolean a(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    String str;
                    PrefetchMessage prefetchMessage = new PrefetchMessage();
                    Message.Error error = new Message.Error();
                    error.setCode(-1);
                    if (glideException == null) {
                        str = "unknown";
                    } else {
                        str = glideException.getMessage();
                    }
                    error.setMessage(str);
                    prefetchMessage.setError(error);
                    yPDCallback.invoke(prefetchMessage.getError().toString(), null);
                    return false;
                }

                public boolean a(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    PrefetchMessage prefetchMessage = new PrefetchMessage();
                    PrefetchMessage.Result result = new PrefetchMessage.Result();
                    result.setFinished(true);
                    result.setUrl(str);
                    prefetchMessage.setResult(result);
                    prefetchMessage.setError(new Message.Error());
                    yPDCallback.invoke(prefetchMessage.getError().toString(), prefetchMessage.getResult().toString());
                    return false;
                }
            });
        }
        this.targetHashMap.put(str, b.c());
    }

    @ReactMethod
    public void abortPrefetch(String str) {
        Target target = this.targetHashMap.get(str);
        if (target != null) {
            Glide.c(getReactApplicationContext()).a((Target<?>) target);
        }
    }
}
