package com.alibaba.android.bindingx.plugin.weex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.BindingXEventType;
import com.alibaba.android.bindingx.core.IEventHandler;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WXBindingXModule extends WXSDKEngine.DestroyableModule {
    /* access modifiers changed from: private */
    public BindingXCore mBindingXCore;
    private PlatformManager mPlatformManager;

    public WXBindingXModule() {
    }

    @VisibleForTesting
    WXBindingXModule(BindingXCore bindingXCore) {
        this.mBindingXCore = bindingXCore;
    }

    private void prepareInternal() {
        if (this.mPlatformManager == null) {
            this.mPlatformManager = createPlatformManager(this.mWXSDKInstance);
        }
        if (this.mBindingXCore == null) {
            this.mBindingXCore = new BindingXCore(this.mPlatformManager);
            this.mBindingXCore.a("scroll", (BindingXCore.ObjectCreator<IEventHandler, Context, PlatformManager>) new BindingXCore.ObjectCreator<IEventHandler, Context, PlatformManager>() {
                public IEventHandler a(@NonNull Context context, @NonNull PlatformManager platformManager, Object... objArr) {
                    return new BindingXScrollHandler(context, platformManager, objArr);
                }
            });
        }
    }

    @JSMethod(uiThread = false)
    public void prepare(Map<String, Object> map) {
        prepareInternal();
    }

    @JSMethod(uiThread = false)
    public Map<String, String> bind(Map<String, Object> map, final JSCallback jSCallback) {
        Context context;
        prepareInternal();
        BindingXCore bindingXCore = this.mBindingXCore;
        String str = null;
        if (this.mWXSDKInstance == null) {
            context = null;
        } else {
            context = this.mWXSDKInstance.getContext();
        }
        if (this.mWXSDKInstance != null) {
            str = this.mWXSDKInstance.getInstanceId();
        }
        if (map == null) {
            map = Collections.emptyMap();
        }
        String a2 = bindingXCore.a(context, str, map, new BindingXCore.JavaScriptCallback() {
            public void a(Object obj) {
                if (jSCallback != null) {
                    jSCallback.invokeAndKeepAlive(obj);
                }
            }
        });
        HashMap hashMap = new HashMap(2);
        hashMap.put("token", a2);
        return hashMap;
    }

    @JSMethod(uiThread = false)
    public void unbind(Map<String, Object> map) {
        if (this.mBindingXCore != null) {
            this.mBindingXCore.a(map);
        }
    }

    @JSMethod(uiThread = false)
    public void unbindAll() {
        if (this.mBindingXCore != null) {
            this.mBindingXCore.a();
        }
    }

    @JSMethod(uiThread = false)
    public List<String> supportFeatures() {
        return Arrays.asList(new String[]{"pan", "orientation", BindingXEventType.d, "scroll"});
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x022e  */
    @com.taobao.weex.annotation.JSMethod(uiThread = false)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.Object> getComputedStyle(@android.support.annotation.Nullable java.lang.String r17) {
        /*
            r16 = this;
            r0 = r16
            r16.prepareInternal()
            com.alibaba.android.bindingx.core.PlatformManager r1 = r0.mPlatformManager
            com.alibaba.android.bindingx.core.PlatformManager$IDeviceResolutionTranslator r1 = r1.a()
            com.taobao.weex.WXSDKInstance r2 = r0.mWXSDKInstance
            java.lang.String r2 = r2.getInstanceId()
            r3 = r17
            com.taobao.weex.ui.component.WXComponent r2 = com.alibaba.android.bindingx.plugin.weex.WXModuleUtils.b(r2, r3)
            if (r2 != 0) goto L_0x001e
            java.util.Map r1 = java.util.Collections.emptyMap()
            return r1
        L_0x001e:
            android.view.View r3 = r2.getHostView()
            if (r3 != 0) goto L_0x0029
            java.util.Map r1 = java.util.Collections.emptyMap()
            return r1
        L_0x0029:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r5 = "width"
            float r6 = r2.getLayoutWidth()
            double r6 = (double) r6
            r8 = 0
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "height"
            float r6 = r2.getLayoutHeight()
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "padding-left"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "padding-top"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "padding-right"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "padding-bottom"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "margin-left"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "margin-top"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "margin-right"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "margin-bottom"
            com.taobao.weex.dom.CSSShorthand r6 = r2.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "translateX"
            float r6 = r3.getTranslationX()
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "translateY"
            float r6 = r3.getTranslationY()
            double r6 = (double) r6
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "rotateX"
            float r6 = r3.getRotationX()
            float r6 = com.alibaba.android.bindingx.core.internal.Utils.a((float) r6)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "rotateY"
            float r6 = r3.getRotationY()
            float r6 = com.alibaba.android.bindingx.core.internal.Utils.a((float) r6)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "rotateZ"
            float r6 = r3.getRotation()
            float r6 = com.alibaba.android.bindingx.core.internal.Utils.a((float) r6)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "scaleX"
            float r6 = r3.getScaleX()
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "scaleY"
            float r6 = r3.getScaleY()
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            java.lang.String r5 = "opacity"
            float r6 = r3.getAlpha()
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4.put(r5, r6)
            android.graphics.drawable.Drawable r5 = r3.getBackground()
            r6 = 4
            r7 = 2
            r9 = 0
            if (r5 == 0) goto L_0x01e3
            boolean r11 = r5 instanceof com.taobao.weex.ui.view.border.BorderDrawable
            if (r11 == 0) goto L_0x01e3
            com.taobao.weex.ui.view.border.BorderDrawable r5 = (com.taobao.weex.ui.view.border.BorderDrawable) r5
            android.graphics.RectF r11 = new android.graphics.RectF
            int r12 = r3.getWidth()
            float r12 = (float) r12
            int r13 = r3.getHeight()
            float r13 = (float) r13
            r14 = 0
            r11.<init>(r14, r14, r12, r13)
            float[] r5 = r5.getBorderRadius((android.graphics.RectF) r11)
            int r11 = r5.length
            r12 = 8
            if (r11 != r12) goto L_0x01e3
            r9 = r5[r8]
            double r9 = (double) r9
            r11 = r5[r7]
            double r11 = (double) r11
            r13 = 6
            r13 = r5[r13]
            double r13 = (double) r13
            r5 = r5[r6]
            double r6 = (double) r5
            goto L_0x01e6
        L_0x01e3:
            r6 = r9
            r11 = r6
            r13 = r11
        L_0x01e6:
            java.lang.String r5 = "border-top-left-radius"
            java.lang.Object[] r15 = new java.lang.Object[r8]
            double r9 = r1.b(r9, r15)
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            r4.put(r5, r9)
            java.lang.String r5 = "border-top-right-radius"
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r9 = r1.b(r11, r9)
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            r4.put(r5, r9)
            java.lang.String r5 = "border-bottom-left-radius"
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r9 = r1.b(r13, r9)
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            r4.put(r5, r9)
            java.lang.String r5 = "border-bottom-right-radius"
            java.lang.Object[] r9 = new java.lang.Object[r8]
            double r6 = r1.b(r6, r9)
            java.lang.Double r1 = java.lang.Double.valueOf(r6)
            r4.put(r5, r1)
            android.graphics.drawable.Drawable r1 = r3.getBackground()
            r6 = 4643176031446892544(0x406fe00000000000, double:255.0)
            r9 = 1
            if (r1 == 0) goto L_0x0297
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            android.graphics.drawable.Drawable r10 = r3.getBackground()
            boolean r10 = r10 instanceof android.graphics.drawable.ColorDrawable
            if (r10 == 0) goto L_0x0243
            android.graphics.drawable.Drawable r1 = r3.getBackground()
            android.graphics.drawable.ColorDrawable r1 = (android.graphics.drawable.ColorDrawable) r1
            int r1 = r1.getColor()
            goto L_0x0255
        L_0x0243:
            android.graphics.drawable.Drawable r10 = r3.getBackground()
            boolean r10 = r10 instanceof com.taobao.weex.ui.view.border.BorderDrawable
            if (r10 == 0) goto L_0x0255
            android.graphics.drawable.Drawable r1 = r3.getBackground()
            com.taobao.weex.ui.view.border.BorderDrawable r1 = (com.taobao.weex.ui.view.border.BorderDrawable) r1
            int r1 = r1.getColor()
        L_0x0255:
            int r10 = android.graphics.Color.alpha(r1)
            double r10 = (double) r10
            java.lang.Double.isNaN(r10)
            double r10 = r10 / r6
            int r12 = android.graphics.Color.red(r1)
            int r13 = android.graphics.Color.green(r1)
            int r1 = android.graphics.Color.blue(r1)
            java.lang.String r14 = "background-color"
            java.util.Locale r15 = java.util.Locale.getDefault()
            java.lang.String r6 = "rgba(%d,%d,%d,%f)"
            r7 = 4
            java.lang.Object[] r5 = new java.lang.Object[r7]
            r7 = r15
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r5[r8] = r12
            java.lang.Integer r12 = java.lang.Integer.valueOf(r13)
            r5[r9] = r12
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r12 = 2
            r5[r12] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r10)
            r10 = 3
            r5[r10] = r1
            java.lang.String r1 = java.lang.String.format(r7, r6, r5)
            r4.put(r14, r1)
        L_0x0297:
            boolean r1 = r2 instanceof com.taobao.weex.ui.component.WXText
            if (r1 == 0) goto L_0x0311
            boolean r1 = r3 instanceof com.taobao.weex.ui.view.WXTextView
            if (r1 == 0) goto L_0x0311
            com.taobao.weex.ui.view.WXTextView r3 = (com.taobao.weex.ui.view.WXTextView) r3
            android.text.Layout r1 = r3.getTextLayout()
            if (r1 == 0) goto L_0x0311
            java.lang.CharSequence r1 = r1.getText()
            if (r1 == 0) goto L_0x0311
            boolean r2 = r1 instanceof android.text.SpannableString
            if (r2 == 0) goto L_0x0311
            r2 = r1
            android.text.SpannableString r2 = (android.text.SpannableString) r2
            int r1 = r1.length()
            java.lang.Class<android.text.style.ForegroundColorSpan> r3 = android.text.style.ForegroundColorSpan.class
            java.lang.Object[] r1 = r2.getSpans(r8, r1, r3)
            android.text.style.ForegroundColorSpan[] r1 = (android.text.style.ForegroundColorSpan[]) r1
            if (r1 == 0) goto L_0x0311
            int r2 = r1.length
            if (r2 != r9) goto L_0x0311
            r1 = r1[r8]
            int r1 = r1.getForegroundColor()
            int r2 = android.graphics.Color.alpha(r1)
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            r5 = 4643176031446892544(0x406fe00000000000, double:255.0)
            double r2 = r2 / r5
            int r5 = android.graphics.Color.red(r1)
            int r6 = android.graphics.Color.green(r1)
            int r1 = android.graphics.Color.blue(r1)
            java.lang.String r7 = "color"
            java.util.Locale r10 = java.util.Locale.getDefault()
            java.lang.String r11 = "rgba(%d,%d,%d,%f)"
            r12 = 4
            java.lang.Object[] r12 = new java.lang.Object[r12]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r12[r8] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            r12[r9] = r5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r5 = 2
            r12[r5] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r2)
            r2 = 3
            r12[r2] = r1
            java.lang.String r1 = java.lang.String.format(r10, r11, r12)
            r4.put(r7, r1)
        L_0x0311:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.plugin.weex.WXBindingXModule.getComputedStyle(java.lang.String):java.util.Map");
    }

    public void destroy() {
        WXBridgeManager.getInstance().post(new Runnable() {
            public void run() {
                if (WXBindingXModule.this.mBindingXCore != null) {
                    WXBindingXModule.this.mBindingXCore.a();
                    BindingXCore unused = WXBindingXModule.this.mBindingXCore = null;
                }
                WXViewUpdateService.a();
            }
        }, (Object) null);
    }

    @NonNull
    static PlatformManager createPlatformManager(WXSDKInstance wXSDKInstance) {
        final int instanceViewPortWidth = wXSDKInstance == null ? 750 : wXSDKInstance.getInstanceViewPortWidth();
        return new PlatformManager.Builder().a((PlatformManager.IViewFinder) new PlatformManager.IViewFinder() {
            @Nullable
            public View a(String str, Object... objArr) {
                if (objArr.length <= 0 || !(objArr[0] instanceof String)) {
                    return null;
                }
                return WXModuleUtils.a(objArr[0], str);
            }
        }).a((PlatformManager.IViewUpdater) new PlatformManager.IViewUpdater() {
            public void a(@NonNull View view, @NonNull String str, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map, Object... objArr) {
                if (objArr != null && objArr.length >= 2 && (objArr[0] instanceof String) && (objArr[1] instanceof String)) {
                    String str2 = objArr[0];
                    String str3 = objArr[1];
                    WXComponent b = WXModuleUtils.b(str3, str2);
                    if (b == null) {
                        LogProxy.e("unexpected error. component not found [ref:" + str2 + ",instanceId:" + str3 + Operators.ARRAY_END_STR);
                        return;
                    }
                    WXViewUpdateService.a(str).a(b, view, obj, iDeviceResolutionTranslator, map);
                }
            }
        }).a((PlatformManager.IDeviceResolutionTranslator) new PlatformManager.IDeviceResolutionTranslator() {
            public double a(double d, Object... objArr) {
                return (double) WXViewUtils.getRealPxByWidth((float) d, instanceViewPortWidth);
            }

            public double b(double d, Object... objArr) {
                return (double) WXViewUtils.getWebPxByWidth((float) d, instanceViewPortWidth);
            }
        }).a();
    }

    public void onActivityPause() {
        WXBridgeManager.getInstance().post(new Runnable() {
            public void run() {
                if (WXBindingXModule.this.mBindingXCore != null) {
                    WXBindingXModule.this.mBindingXCore.b();
                }
            }
        }, (Object) null);
    }

    public void onActivityResume() {
        WXBridgeManager.getInstance().post(new Runnable() {
            public void run() {
                if (WXBindingXModule.this.mBindingXCore != null) {
                    WXBindingXModule.this.mBindingXCore.c();
                }
            }
        }, (Object) null);
    }
}
