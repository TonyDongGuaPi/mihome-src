package com.xiaomiyoupin.ypdimage.duplo.weex;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.YPDImageView;
import com.xiaomiyoupin.ypdimage.YPDSource;
import com.xiaomiyoupin.ypdimage.data.InnerVersionMessage;
import com.xiaomiyoupin.ypdimage.data.Message;
import com.xiaomiyoupin.ypdimage.utils.Colors;
import java.util.Map;
import javax.annotation.Nullable;

public class YPDImageViewComponentWX extends WXComponent<ImageView> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public YPDImageView f1793a;
    /* access modifiers changed from: private */
    public YPDSource b = new YPDSource();
    private YPDImageViewEventEmitter c = new YPDImageViewEventEmitter(this);
    private InnerVersionMessage d;

    public void setBorderStyle(String str, String str2) {
    }

    public YPDImageViewComponentWX(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    @JSMethod(uiThread = true)
    public void a(JSCallback jSCallback) {
        if (jSCallback != null) {
            if (this.d == null) {
                this.d = new InnerVersionMessage();
                this.d.setError(new Message.Error());
                this.d.setResult(new InnerVersionMessage.Result());
            }
            this.d.getResult().setInnerVersion(String.valueOf(YPDImage.b));
            jSCallback.invoke(this.d);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public ImageView initComponentHostView(@NonNull Context context) {
        this.f1793a = new YPDImageView(context);
        this.f1793a.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return this.f1793a;
    }

    @WXComponentProp(name = "placeholder")
    public void a(String str) {
        final YPDSource yPDSource = this.b;
        String f = YPDSource.f(str);
        if (!TextUtils.equals(f, yPDSource.f())) {
            yPDSource.c(f);
            YPDSource.a(this.f1793a, f, new YPDSource.OnLoadPlaceholder() {
                public void a(Drawable drawable) {
                    if (drawable != null) {
                        yPDSource.a(drawable);
                        YPDImageViewComponentWX.this.b.a((ImageView) YPDImageViewComponentWX.this.f1793a);
                    }
                }
            });
        }
    }

    @WXComponentProp(name = "source")
    public void b(String str) {
        YPDSource yPDSource = this.b;
        if (yPDSource != null) {
            if (!yPDSource.a()) {
                yPDSource.a(this.c.a());
            }
            yPDSource.a(YPDSource.f(str));
            yPDSource.b(YPDSource.a(str, "cache"));
        }
    }

    @WXComponentProp(name = "resizeMode")
    public void c(@Nullable String str) {
        YPDSource yPDSource = this.b;
        if (yPDSource != null) {
            yPDSource.d(str);
        }
    }

    @WXComponentProp(name = "blurRadius")
    public void a(float f) {
        YPDSource yPDSource = this.b;
        if (yPDSource != null) {
            yPDSource.a(f);
        }
    }

    @WXComponentProp(name = "preprocessEnable")
    public void a(boolean z) {
        YPDSource yPDSource = this.b;
        if (yPDSource != null) {
            yPDSource.a(z);
        }
    }

    public void setBorderRadius(String str, float f) {
        b(f);
    }

    private void b(float f) {
        if (this.f1793a != null) {
            this.f1793a.setRadius(f);
        }
    }

    public void setBorderColor(String str, String str2) {
        d(str2);
    }

    private void d(String str) {
        if (this.f1793a != null) {
            this.f1793a.setBorderColor(Colors.a(str));
        }
    }

    public void setPadding(CSSShorthand cSSShorthand, CSSShorthand cSSShorthand2) {
        int i = (int) cSSShorthand.get(CSSShorthand.EDGE.LEFT);
        int i2 = (int) cSSShorthand.get(CSSShorthand.EDGE.TOP);
        int i3 = (int) cSSShorthand.get(CSSShorthand.EDGE.RIGHT);
        int i4 = (int) cSSShorthand.get(CSSShorthand.EDGE.BOTTOM);
        if (this.f1793a != null) {
            this.f1793a.setPadding(i, i2, i3, i4);
        }
        c(cSSShorthand2.get(CSSShorthand.EDGE.LEFT));
    }

    public void setBorderWidth(String str, float f) {
        c(f);
    }

    private void c(float f) {
        if (this.f1793a != null) {
            this.f1793a.setBorderWidth(f);
        }
    }

    public void updateAttrs(Map<String, Object> map) {
        super.updateAttrs(map);
        if (this.b != null) {
            if (map.size() != 1 || !map.containsKey("source")) {
                if (map.size() == 1 && map.keySet().toArray()[0].toString().contains("border")) {
                    return;
                }
            } else if (TextUtils.equals(this.b.b(), YPDSource.f((String) map.get("source")))) {
                return;
            }
            this.b.a((ImageView) this.f1793a);
        }
    }

    public void updateAttrs(WXComponent wXComponent) {
        super.updateAttrs(wXComponent);
        if (this.b != null) {
            this.b.a((ImageView) this.f1793a);
        }
    }
}
