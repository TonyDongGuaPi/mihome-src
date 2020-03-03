package com.xiaomiyoupin.ypdimage.duplo.rn;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.xiaomiyoupin.ypdimage.R;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.YPDImageView;
import com.xiaomiyoupin.ypdimage.YPDSource;
import com.xiaomiyoupin.ypdimage.data.InnerVersionMessage;
import com.xiaomiyoupin.ypdimage.data.Message;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONObject;

public class YPDImageViewManager extends SimpleViewManager<YPDImageView> {
    private static final String ON_LOAD_EVENT = "onLoadEvent";
    private InnerVersionMessage innerVersionMessage;

    public String getName() {
        return YPDImage.a().b();
    }

    /* access modifiers changed from: protected */
    public YPDImageView createViewInstance(ThemedReactContext themedReactContext) {
        YPDImageView yPDImageView = new YPDImageView(themedReactContext);
        yPDImageView.setTag(R.id.ypd_image_source, new YPDSource());
        return yPDImageView;
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

    @ReactProp(name = "placeholder")
    public void setPlaceHolder(YPDImageView yPDImageView, @Nullable ReadableMap readableMap) {
        final YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null && readableMap != null) {
            String f = YPDSource.f(new JSONObject(readableMap.toHashMap()).toString());
            if (!TextUtils.equals(f, yPDSource.f())) {
                yPDSource.c(f);
                YPDSource.a(yPDImageView, f, new YPDSource.OnLoadPlaceholder() {
                    public void a(Drawable drawable) {
                        if (drawable != null) {
                            yPDSource.a(drawable);
                        }
                    }
                });
            }
        }
    }

    @ReactProp(name = "placeholderResizeMode")
    public void setPlaceholderResizeMode(YPDImageView yPDImageView, @Nullable String str) {
        YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null) {
            yPDSource.e(str);
        }
    }

    @ReactProp(name = "source")
    public void setSource(YPDImageView yPDImageView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            JSONObject jSONObject = new JSONObject(readableMap.toHashMap());
            YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
            if (yPDSource != null) {
                if (!yPDSource.a()) {
                    yPDSource.a(new YPDImageViewEventEmitter(yPDImageView).a());
                }
                String jSONObject2 = jSONObject.toString();
                yPDSource.a(YPDSource.f(jSONObject2));
                yPDSource.b(YPDSource.a(jSONObject2, "cache"));
            }
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(YPDImageView yPDImageView, @Nullable String str) {
        YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null) {
            yPDSource.d(str);
        }
    }

    @ReactProp(name = "blurRadius")
    public void setBlurRadius(YPDImageView yPDImageView, float f) {
        YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null) {
            yPDSource.a(f);
        }
    }

    @ReactProp(defaultBoolean = false, name = "preprocessEnable")
    public void setPreprocessEnable(YPDImageView yPDImageView, boolean z) {
        YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null) {
            yPDSource.a(z);
        }
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(YPDImageView yPDImageView, float f) {
        yPDImageView.setRadius(f);
    }

    @ReactProp(name = "borderColor")
    public void setBorderColor(YPDImageView yPDImageView, float f) {
        yPDImageView.setBorderColor((int) f);
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(YPDImageView yPDImageView, float f) {
        yPDImageView.setBorderWidth(f);
    }

    @ReactProp(name = "padding")
    public void setPadding(YPDImageView yPDImageView, float f) {
        int i = (int) f;
        yPDImageView.setPadding(i, i, i, i);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(YPDImageView yPDImageView) {
        super.onAfterUpdateTransaction(yPDImageView);
        YPDSource yPDSource = (YPDSource) yPDImageView.getTag(R.id.ypd_image_source);
        if (yPDSource != null) {
            yPDSource.a((ImageView) yPDImageView);
        }
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ON_LOAD_EVENT, MapBuilder.of("registrationName", ON_LOAD_EVENT)).build();
    }
}
