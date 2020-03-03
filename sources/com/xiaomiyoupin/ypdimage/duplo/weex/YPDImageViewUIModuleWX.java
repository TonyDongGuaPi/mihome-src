package com.xiaomiyoupin.ypdimage.duplo.weex;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.data.InnerVersionMessage;
import com.xiaomiyoupin.ypdimage.data.Message;
import com.xiaomiyoupin.ypdimage.data.PrefetchMessage;
import java.util.HashMap;

public class YPDImageViewUIModuleWX extends WXModule {
    private InnerVersionMessage innerVersionMessage;
    private HashMap<String, Target> targetHashMap = new HashMap<>();

    @JSMethod(uiThread = false)
    public int getInnerVersionSync() {
        return YPDImage.b;
    }

    @JSMethod(uiThread = true)
    public void recycle(String str) {
    }

    @JSMethod(uiThread = true)
    public void getInnerVersion(JSCallback jSCallback) {
        if (jSCallback != null) {
            if (this.innerVersionMessage == null) {
                this.innerVersionMessage = new InnerVersionMessage();
                this.innerVersionMessage.setError(new Message.Error());
                this.innerVersionMessage.setResult(new InnerVersionMessage.Result());
            }
            this.innerVersionMessage.getResult().setInnerVersion(String.valueOf(YPDImage.b));
            jSCallback.invoke(this.innerVersionMessage);
        }
    }

    @JSMethod(uiThread = true)
    public void prefetch(final String str, final JSCallback jSCallback) {
        RequestBuilder<Drawable> b = Glide.c(this.mWXSDKInstance.getContext()).a(str);
        if (jSCallback != null) {
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
                    jSCallback.invoke(prefetchMessage);
                    return false;
                }

                public boolean a(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    PrefetchMessage prefetchMessage = new PrefetchMessage();
                    PrefetchMessage.Result result = new PrefetchMessage.Result();
                    prefetchMessage.setError(new Message.Error());
                    result.setFinished(true);
                    result.setUrl(str);
                    prefetchMessage.setResult(result);
                    jSCallback.invoke(prefetchMessage);
                    return false;
                }
            });
        }
        this.targetHashMap.put(str, b.c());
    }

    @JSMethod(uiThread = true)
    public void abortPrefetch(String str) {
        Target target = this.targetHashMap.get(str);
        if (target != null) {
            Glide.c(this.mWXSDKInstance.getContext()).a((Target<?>) target);
            this.targetHashMap.remove(str);
        }
    }
}
