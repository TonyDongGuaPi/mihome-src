package com.alibaba.android.bindingx.plugin.weex;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.alibaba.android.bindingx.core.WeakRunnable;
import com.alibaba.android.bindingx.core.internal.Utils;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.view.WXTextView;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class WXViewUpdateService {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, IWXViewUpdater> f785a = new HashMap();
    private static final LayoutUpdater b = new LayoutUpdater();
    private static final NOpUpdater c = new NOpUpdater();
    private static final String d = "perspective";
    private static final String e = "transformOrigin";
    private static final String f = "width";
    private static final String g = "height";
    private static final String h = "margin-left";
    private static final String i = "margin-right";
    private static final String j = "margin-top";
    private static final String k = "margin-bottom";
    private static final String l = "padding-left";
    private static final String m = "padding-right";
    private static final String n = "padding-top";
    private static final String o = "padding-bottom";
    private static final List<String> p = Arrays.asList(new String[]{"width", "height", h, i, j, k, l, m, n, o});
    private static final Handler q = new Handler(Looper.getMainLooper());

    WXViewUpdateService() {
    }

    static {
        f785a.put("opacity", new OpacityUpdater());
        f785a.put("transform.translate", new TranslateUpdater());
        f785a.put("transform.translateX", new TranslateXUpdater());
        f785a.put("transform.translateY", new TranslateYUpdater());
        f785a.put("transform.scale", new ScaleUpdater());
        f785a.put("transform.scaleX", new ScaleXUpdater());
        f785a.put("transform.scaleY", new ScaleYUpdater());
        f785a.put("transform.rotate", new RotateUpdater());
        f785a.put("transform.rotateZ", new RotateUpdater());
        f785a.put("transform.rotateX", new RotateXUpdater());
        f785a.put("transform.rotateY", new RotateYUpdater());
        f785a.put("background-color", new BackgroundUpdater());
        f785a.put("color", new ColorUpdater());
        f785a.put("scroll.contentOffset", new ContentOffsetUpdater());
        f785a.put("scroll.contentOffsetX", new ContentOffsetXUpdater());
        f785a.put("scroll.contentOffsetY", new ContentOffsetYUpdater());
        f785a.put("border-top-left-radius", new BorderRadiusTopLeftUpdater());
        f785a.put("border-top-right-radius", new BorderRadiusTopRightUpdater());
        f785a.put("border-bottom-left-radius", new BorderRadiusBottomLeftUpdater());
        f785a.put("border-bottom-right-radius", new BorderRadiusBottomRightUpdater());
        f785a.put("border-radius", new BorderRadiusUpdater());
    }

    @NonNull
    static IWXViewUpdater a(@NonNull String str) {
        IWXViewUpdater iWXViewUpdater = f785a.get(str);
        if (iWXViewUpdater != null) {
            return iWXViewUpdater;
        }
        if (p.contains(str)) {
            b.a(str);
            return b;
        }
        LogProxy.e("unknown property [" + str + Operators.ARRAY_END_STR);
        return c;
    }

    private static final class NOpUpdater implements IWXViewUpdater {
        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
        }

        private NOpUpdater() {
        }
    }

    /* access modifiers changed from: private */
    public static void b(Runnable runnable) {
        q.post(new WeakRunnable(runnable));
    }

    public static void a() {
        q.removeCallbacksAndMessages((Object) null);
    }

    private static final class ContentOffsetUpdater implements IWXViewUpdater {
        private ContentOffsetUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            final View a2 = WXViewUpdateService.b(wXComponent);
            if (a2 != null) {
                if (obj instanceof Double) {
                    final double doubleValue = ((Double) obj).doubleValue();
                    final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                    WXViewUpdateService.b((Runnable) new Runnable() {
                        public void run() {
                            a2.setScrollX((int) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                            a2.setScrollY((int) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    });
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                        final double doubleValue2 = ((Double) arrayList.get(0)).doubleValue();
                        final double doubleValue3 = ((Double) arrayList.get(1)).doubleValue();
                        final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator3 = iDeviceResolutionTranslator;
                        WXViewUpdateService.b((Runnable) new Runnable() {
                            public void run() {
                                a2.setScrollX((int) WXViewUpdateService.b(doubleValue2, iDeviceResolutionTranslator3));
                                a2.setScrollY((int) WXViewUpdateService.b(doubleValue3, iDeviceResolutionTranslator3));
                            }
                        });
                    }
                }
            }
        }
    }

    private static final class ContentOffsetXUpdater implements IWXViewUpdater {
        private ContentOffsetXUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            final View a2 = WXViewUpdateService.b(wXComponent);
            if (a2 != null && (obj instanceof Double)) {
                final double doubleValue = ((Double) obj).doubleValue();
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        a2.setScrollX((int) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                    }
                });
            }
        }
    }

    private static final class ContentOffsetYUpdater implements IWXViewUpdater {
        private ContentOffsetYUpdater() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
            r2 = com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService.a(r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(@android.support.annotation.NonNull com.taobao.weex.ui.component.WXComponent r7, @android.support.annotation.NonNull android.view.View r8, @android.support.annotation.NonNull java.lang.Object r9, @android.support.annotation.NonNull com.alibaba.android.bindingx.core.PlatformManager.IDeviceResolutionTranslator r10, @android.support.annotation.NonNull java.util.Map<java.lang.String, java.lang.Object> r11) {
            /*
                r6 = this;
                boolean r8 = r9 instanceof java.lang.Double
                if (r8 != 0) goto L_0x0005
                return
            L_0x0005:
                android.view.View r2 = com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService.b((com.taobao.weex.ui.component.WXComponent) r7)
                if (r2 != 0) goto L_0x000c
                return
            L_0x000c:
                java.lang.Double r9 = (java.lang.Double) r9
                double r3 = r9.doubleValue()
                com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService$ContentOffsetYUpdater$1 r7 = new com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService$ContentOffsetYUpdater$1
                r0 = r7
                r1 = r6
                r5 = r10
                r0.<init>(r2, r3, r5)
                com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService.b((java.lang.Runnable) r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.plugin.weex.WXViewUpdateService.ContentOffsetYUpdater.a(com.taobao.weex.ui.component.WXComponent, android.view.View, java.lang.Object, com.alibaba.android.bindingx.core.PlatformManager$IDeviceResolutionTranslator, java.util.Map):void");
        }
    }

    private static final class OpacityUpdater implements IWXViewUpdater {
        private OpacityUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final float doubleValue = (float) ((Double) obj).doubleValue();
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        view.setAlpha(doubleValue);
                    }
                });
            }
        }
    }

    private static final class TranslateUpdater implements IWXViewUpdater {
        private TranslateUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                    final double doubleValue = ((Double) arrayList.get(0)).doubleValue();
                    final double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
                    final View view2 = view;
                    final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                    WXViewUpdateService.b((Runnable) new Runnable() {
                        public void run() {
                            view2.setTranslationX((float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                            view2.setTranslationY((float) WXViewUpdateService.b(doubleValue2, iDeviceResolutionTranslator2));
                        }
                    });
                }
            }
        }
    }

    private static final class TranslateXUpdater implements IWXViewUpdater {
        private TranslateXUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        view2.setTranslationX((float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                    }
                });
            }
        }
    }

    private static final class TranslateYUpdater implements IWXViewUpdater {
        private TranslateYUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        view2.setTranslationY((float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                    }
                });
            }
        }
    }

    private static final class ScaleUpdater implements IWXViewUpdater {
        private ScaleUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            WXViewUpdateService.b((Runnable) new Runnable() {
                public void run() {
                    int a2 = Utils.a(view.getContext(), WXUtils.getInt(map.get("perspective")));
                    Pair<Float, Float> a3 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                    if (a2 != 0) {
                        view.setCameraDistance((float) a2);
                    }
                    if (a3 != null) {
                        view.setPivotX(((Float) a3.first).floatValue());
                        view.setPivotY(((Float) a3.second).floatValue());
                    }
                    if (obj instanceof Double) {
                        float doubleValue = (float) ((Double) obj).doubleValue();
                        view.setScaleX(doubleValue);
                        view.setScaleY(doubleValue);
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                            double doubleValue2 = ((Double) arrayList.get(0)).doubleValue();
                            double doubleValue3 = ((Double) arrayList.get(1)).doubleValue();
                            view.setScaleX((float) doubleValue2);
                            view.setScaleY((float) doubleValue3);
                        }
                    }
                }
            });
        }
    }

    private static final class ScaleXUpdater implements IWXViewUpdater {
        private ScaleXUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            if (obj instanceof Double) {
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Pair<Float, Float> a2 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                        if (a2 != null) {
                            view.setPivotX(((Float) a2.first).floatValue());
                            view.setPivotY(((Float) a2.second).floatValue());
                        }
                        view.setScaleX((float) ((Double) obj).doubleValue());
                    }
                });
            }
        }
    }

    private static final class ScaleYUpdater implements IWXViewUpdater {
        private ScaleYUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            if (obj instanceof Double) {
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Pair<Float, Float> a2 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                        if (a2 != null) {
                            view.setPivotX(((Float) a2.first).floatValue());
                            view.setPivotY(((Float) a2.second).floatValue());
                        }
                        view.setScaleY((float) ((Double) obj).doubleValue());
                    }
                });
            }
        }
    }

    private static final class RotateUpdater implements IWXViewUpdater {
        private RotateUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            if (obj instanceof Double) {
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        int a2 = Utils.a(view.getContext(), WXUtils.getInt(map.get("perspective")));
                        Pair<Float, Float> a3 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                        if (a2 != 0) {
                            view.setCameraDistance((float) a2);
                        }
                        if (a3 != null) {
                            view.setPivotX(((Float) a3.first).floatValue());
                            view.setPivotY(((Float) a3.second).floatValue());
                        }
                        view.setRotation((float) ((Double) obj).doubleValue());
                    }
                });
            }
        }
    }

    private static final class RotateXUpdater implements IWXViewUpdater {
        private RotateXUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            if (obj instanceof Double) {
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        int a2 = Utils.a(view.getContext(), WXUtils.getInt(map.get("perspective")));
                        Pair<Float, Float> a3 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                        if (a2 != 0) {
                            view.setCameraDistance((float) a2);
                        }
                        if (a3 != null) {
                            view.setPivotX(((Float) a3.first).floatValue());
                            view.setPivotY(((Float) a3.second).floatValue());
                        }
                        view.setRotationX((float) ((Double) obj).doubleValue());
                    }
                });
            }
        }
    }

    private static final class RotateYUpdater implements IWXViewUpdater {
        private RotateYUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull final Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull final Map<String, Object> map) {
            if (obj instanceof Double) {
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        int a2 = Utils.a(view.getContext(), WXUtils.getInt(map.get("perspective")));
                        Pair<Float, Float> a3 = Utils.a(WXUtils.getString(map.get("transformOrigin"), (String) null), view);
                        if (a2 != 0) {
                            view.setCameraDistance((float) a2);
                        }
                        if (a3 != null) {
                            view.setPivotX(((Float) a3.first).floatValue());
                            view.setPivotY(((Float) a3.second).floatValue());
                        }
                        view.setRotationY((float) ((Double) obj).doubleValue());
                    }
                });
            }
        }
    }

    static final class LayoutUpdater implements IWXViewUpdater {

        /* renamed from: a  reason: collision with root package name */
        private String f798a;

        LayoutUpdater() {
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            this.f798a = str;
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            String str;
            if ((obj instanceof Double) && !TextUtils.isEmpty(this.f798a)) {
                double doubleValue = ((Double) obj).doubleValue();
                String str2 = this.f798a;
                char c = 65535;
                switch (str2.hashCode()) {
                    case -1502084711:
                        if (str2.equals(WXViewUpdateService.n)) {
                            c = 8;
                            break;
                        }
                        break;
                    case -1221029593:
                        if (str2.equals("height")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -887955139:
                        if (str2.equals(WXViewUpdateService.i)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -396426912:
                        if (str2.equals(WXViewUpdateService.m)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 113126854:
                        if (str2.equals("width")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 143541095:
                        if (str2.equals(WXViewUpdateService.o)) {
                            c = 9;
                            break;
                        }
                        break;
                    case 679766083:
                        if (str2.equals(WXViewUpdateService.l)) {
                            c = 6;
                            break;
                        }
                        break;
                    case 941004998:
                        if (str2.equals(WXViewUpdateService.h)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1970025654:
                        if (str2.equals(WXViewUpdateService.j)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 2086035242:
                        if (str2.equals(WXViewUpdateService.k)) {
                            c = 5;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        str = "width";
                        break;
                    case 1:
                        str = "height";
                        break;
                    case 2:
                        str = "marginLeft";
                        break;
                    case 3:
                        str = "marginRight";
                        break;
                    case 4:
                        str = "marginTop";
                        break;
                    case 5:
                        str = "marginBottom";
                        break;
                    case 6:
                        str = "paddingLeft";
                        break;
                    case 7:
                        str = "paddingRight";
                        break;
                    case 8:
                        str = "paddingTop";
                        break;
                    case 9:
                        str = "paddingBottom";
                        break;
                    default:
                        str = null;
                        break;
                }
                if (!TextUtils.isEmpty(str)) {
                    WXTransition.asynchronouslyUpdateLayout(wXComponent, str, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator));
                    this.f798a = null;
                }
            }
        }
    }

    private static final class BackgroundUpdater implements IWXViewUpdater {
        private BackgroundUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Integer) {
                final int intValue = ((Integer) obj).intValue();
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view.getBackground();
                        if (background == null) {
                            view.setBackgroundColor(intValue);
                        } else if (background instanceof BorderDrawable) {
                            ((BorderDrawable) background).setColor(intValue);
                        } else if (background instanceof ColorDrawable) {
                            ((ColorDrawable) background).setColor(intValue);
                        }
                    }
                });
            }
        }
    }

    private static final class ColorUpdater implements IWXViewUpdater {
        private ColorUpdater() {
        }

        public void a(@NonNull final WXComponent wXComponent, @NonNull final View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Integer) {
                final int intValue = ((Integer) obj).intValue();
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Layout textLayout;
                        CharSequence text;
                        if (view instanceof TextView) {
                            ((TextView) view).setTextColor(intValue);
                        } else if ((wXComponent instanceof WXText) && (view instanceof WXTextView) && (textLayout = ((WXTextView) view).getTextLayout()) != null && (text = textLayout.getText()) != null && (text instanceof SpannableString)) {
                            SpannableString spannableString = (SpannableString) text;
                            ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) spannableString.getSpans(0, text.length(), ForegroundColorSpan.class);
                            if (foregroundColorSpanArr != null && foregroundColorSpanArr.length == 1) {
                                spannableString.removeSpan(foregroundColorSpanArr[0]);
                                spannableString.setSpan(new ForegroundColorSpan(intValue), 0, text.length(), 17);
                                view.invalidate();
                            }
                        }
                    }
                });
            }
        }
    }

    private static final class BorderRadiusTopLeftUpdater implements IWXViewUpdater {
        private BorderRadiusTopLeftUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view2.getBackground();
                        if (background != null && (background instanceof BorderDrawable)) {
                            ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    }
                });
            }
        }
    }

    private static final class BorderRadiusTopRightUpdater implements IWXViewUpdater {
        private BorderRadiusTopRightUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view2.getBackground();
                        if (background != null && (background instanceof BorderDrawable)) {
                            ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    }
                });
            }
        }
    }

    private static final class BorderRadiusBottomLeftUpdater implements IWXViewUpdater {
        private BorderRadiusBottomLeftUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view2.getBackground();
                        if (background != null && (background instanceof BorderDrawable)) {
                            ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    }
                });
            }
        }
    }

    private static final class BorderRadiusBottomRightUpdater implements IWXViewUpdater {
        private BorderRadiusBottomRightUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view2.getBackground();
                        if (background != null && (background instanceof BorderDrawable)) {
                            ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    }
                });
            }
        }
    }

    private static final class BorderRadiusUpdater implements IWXViewUpdater {
        private BorderRadiusUpdater() {
        }

        public void a(@NonNull WXComponent wXComponent, @NonNull final View view, @NonNull Object obj, @NonNull final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map) {
            if (obj instanceof ArrayList) {
                final ArrayList arrayList = (ArrayList) obj;
                if (arrayList.size() == 4) {
                    WXViewUpdateService.b((Runnable) new Runnable() {
                        public void run() {
                            Drawable background = view.getBackground();
                            if (background != null && (background instanceof BorderDrawable)) {
                                double d2 = 0.0d;
                                double doubleValue = arrayList.get(0) instanceof Double ? ((Double) arrayList.get(0)).doubleValue() : 0.0d;
                                double doubleValue2 = arrayList.get(1) instanceof Double ? ((Double) arrayList.get(1)).doubleValue() : 0.0d;
                                double doubleValue3 = arrayList.get(2) instanceof Double ? ((Double) arrayList.get(2)).doubleValue() : 0.0d;
                                if (arrayList.get(3) instanceof Double) {
                                    d2 = ((Double) arrayList.get(3)).doubleValue();
                                }
                                BorderDrawable borderDrawable = (BorderDrawable) background;
                                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator));
                                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) WXViewUpdateService.b(doubleValue2, iDeviceResolutionTranslator));
                                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) WXViewUpdateService.b(doubleValue3, iDeviceResolutionTranslator));
                                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) WXViewUpdateService.b(d2, iDeviceResolutionTranslator));
                            }
                        }
                    });
                }
            } else if (obj instanceof Double) {
                final double doubleValue = ((Double) obj).doubleValue();
                final View view2 = view;
                final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
                WXViewUpdateService.b((Runnable) new Runnable() {
                    public void run() {
                        Drawable background = view2.getBackground();
                        if (background != null && (background instanceof BorderDrawable)) {
                            BorderDrawable borderDrawable = (BorderDrawable) background;
                            borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                            borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                            borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                            borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) WXViewUpdateService.b(doubleValue, iDeviceResolutionTranslator2));
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static double b(double d2, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator) {
        return iDeviceResolutionTranslator.a(d2, new Object[0]);
    }

    /* access modifiers changed from: private */
    @Nullable
    public static View b(@NonNull WXComponent wXComponent) {
        if (wXComponent instanceof WXScroller) {
            return ((WXScroller) wXComponent).getInnerView();
        }
        LogProxy.e("scroll offset only support on Scroller Component");
        return null;
    }
}
