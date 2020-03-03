package com.taobao.weex.ui.action;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.animation.BackgroundColorProperty;
import com.taobao.weex.ui.animation.HeightProperty;
import com.taobao.weex.ui.animation.WXAnimationBean;
import com.taobao.weex.ui.animation.WXAnimationModule;
import com.taobao.weex.ui.animation.WidthProperty;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.SingleFunctionParser;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.HashMap;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionAnimation extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "GraphicActionAnimation";
    @Nullable
    private final String callback;
    @Nullable
    private WXAnimationBean mAnimationBean;
    private final boolean styleNeedInit;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8548079105847897332L, "com/taobao/weex/ui/action/GraphicActionAnimation", 128);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAnimation(@NonNull WXSDKInstance wXSDKInstance, @NonNull String str, @NonNull WXAnimationBean wXAnimationBean) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.styleNeedInit = false;
        this.callback = null;
        this.mAnimationBean = wXAnimationBean;
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAnimation(@NonNull WXSDKInstance wXSDKInstance, @NonNull String str, @Nullable String str2, @Nullable String str3) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.styleNeedInit = true;
        this.callback = str3;
        $jacocoInit[1] = true;
        if (TextUtils.isEmpty(str2)) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.mAnimationBean = (WXAnimationBean) JSONObject.parseObject(str2, WXAnimationBean.class);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAnimation(@NonNull WXSDKInstance wXSDKInstance, @NonNull String str, @NonNull WXAnimationBean wXAnimationBean, @Nullable String str2) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.styleNeedInit = false;
        this.mAnimationBean = wXAnimationBean;
        this.callback = str2;
        $jacocoInit[6] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAnimationBean == null) {
            $jacocoInit[7] = true;
            return;
        }
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent != null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            if (!TemplateDom.isVirtualDomRef(getRef())) {
                $jacocoInit[10] = true;
                return;
            }
            wXComponent = TemplateDom.findVirtualComponentByVRef(getPageId(), getRef());
            if (wXComponent != null) {
                $jacocoInit[11] = true;
            } else {
                $jacocoInit[12] = true;
                return;
            }
        }
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getWXRenderManager().getWXSDKInstance(getPageId());
        if (wXSDKInstance == null) {
            $jacocoInit[13] = true;
            return;
        }
        if (this.mAnimationBean.styles == null) {
            $jacocoInit[14] = true;
        } else {
            if (!this.styleNeedInit) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                String str = (String) wXComponent.getStyles().get(Constants.Name.TRANSFORM_ORIGIN);
                $jacocoInit[17] = true;
                if (!TextUtils.isEmpty(this.mAnimationBean.styles.transformOrigin)) {
                    $jacocoInit[18] = true;
                } else {
                    this.mAnimationBean.styles.transformOrigin = str;
                    $jacocoInit[19] = true;
                }
                WXAnimationBean.Style style = this.mAnimationBean.styles;
                String str2 = this.mAnimationBean.styles.transformOrigin;
                String str3 = this.mAnimationBean.styles.transform;
                $jacocoInit[20] = true;
                $jacocoInit[21] = true;
                $jacocoInit[22] = true;
                int instanceViewPortWidth = wXSDKInstance.getInstanceViewPortWidth();
                $jacocoInit[23] = true;
                style.init(str2, str3, (int) wXComponent.getLayoutWidth(), (int) wXComponent.getLayoutHeight(), instanceViewPortWidth, wXSDKInstance);
                $jacocoInit[24] = true;
            }
            startAnimation(wXSDKInstance, wXComponent);
            $jacocoInit[25] = true;
        }
        $jacocoInit[26] = true;
    }

    private void startAnimation(@NonNull WXSDKInstance wXSDKInstance, @Nullable WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[27] = true;
        } else {
            if (this.mAnimationBean == null) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
                wXComponent.setNeedLayoutOnAnimation(this.mAnimationBean.needLayout);
                $jacocoInit[30] = true;
            }
            if (wXComponent.getHostView() != null) {
                $jacocoInit[31] = true;
                try {
                    View hostView = wXComponent.getHostView();
                    $jacocoInit[35] = true;
                    int instanceViewPortWidth = wXSDKInstance.getInstanceViewPortWidth();
                    $jacocoInit[36] = true;
                    ObjectAnimator createAnimator = createAnimator(hostView, instanceViewPortWidth);
                    if (createAnimator == null) {
                        $jacocoInit[37] = true;
                    } else {
                        $jacocoInit[38] = true;
                        Animator.AnimatorListener createAnimatorListener = createAnimatorListener(wXSDKInstance, this.callback);
                        if (Build.VERSION.SDK_INT >= 18) {
                            $jacocoInit[39] = true;
                        } else {
                            $jacocoInit[40] = true;
                            if (!wXComponent.isLayerTypeEnabled()) {
                                $jacocoInit[41] = true;
                            } else {
                                $jacocoInit[42] = true;
                                wXComponent.getHostView().setLayerType(2, (Paint) null);
                                $jacocoInit[43] = true;
                            }
                        }
                        Interpolator createTimeInterpolator = createTimeInterpolator();
                        if (createAnimatorListener == null) {
                            $jacocoInit[44] = true;
                        } else {
                            $jacocoInit[45] = true;
                            createAnimator.addListener(createAnimatorListener);
                            $jacocoInit[46] = true;
                        }
                        if (createTimeInterpolator == null) {
                            $jacocoInit[47] = true;
                        } else {
                            $jacocoInit[48] = true;
                            createAnimator.setInterpolator(createTimeInterpolator);
                            $jacocoInit[49] = true;
                        }
                        wXComponent.getHostView().setCameraDistance(this.mAnimationBean.styles.getCameraDistance());
                        $jacocoInit[50] = true;
                        createAnimator.setDuration(this.mAnimationBean.duration);
                        $jacocoInit[51] = true;
                        createAnimator.start();
                        $jacocoInit[52] = true;
                    }
                    $jacocoInit[53] = true;
                } catch (RuntimeException e) {
                    $jacocoInit[54] = true;
                    WXLogUtils.e(TAG, WXLogUtils.getStackTrace(e));
                    $jacocoInit[55] = true;
                }
            } else {
                $jacocoInit[32] = true;
                WXAnimationModule.AnimationHolder animationHolder = new WXAnimationModule.AnimationHolder(this.mAnimationBean, this.callback);
                $jacocoInit[33] = true;
                wXComponent.postAnimation(animationHolder);
                $jacocoInit[34] = true;
            }
        }
        $jacocoInit[56] = true;
    }

    @Nullable
    private ObjectAnimator createAnimator(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[57] = true;
            return null;
        }
        WXAnimationBean.Style style = this.mAnimationBean.styles;
        if (style != null) {
            $jacocoInit[58] = true;
            List<PropertyValuesHolder> holders = style.getHolders();
            $jacocoInit[59] = true;
            if (TextUtils.isEmpty(style.backgroundColor)) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(view);
                if (borderDrawable != null) {
                    $jacocoInit[62] = true;
                    BackgroundColorProperty backgroundColorProperty = new BackgroundColorProperty();
                    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                    $jacocoInit[63] = true;
                    String str = style.backgroundColor;
                    $jacocoInit[64] = true;
                    Integer[] numArr = {Integer.valueOf(borderDrawable.getColor()), Integer.valueOf(WXResourceUtils.getColor(str))};
                    $jacocoInit[65] = true;
                    holders.add(PropertyValuesHolder.ofObject(backgroundColorProperty, argbEvaluator, numArr));
                    $jacocoInit[66] = true;
                } else if (!(view.getBackground() instanceof ColorDrawable)) {
                    $jacocoInit[67] = true;
                } else {
                    $jacocoInit[68] = true;
                    BackgroundColorProperty backgroundColorProperty2 = new BackgroundColorProperty();
                    ArgbEvaluator argbEvaluator2 = new ArgbEvaluator();
                    $jacocoInit[69] = true;
                    String str2 = style.backgroundColor;
                    $jacocoInit[70] = true;
                    Integer[] numArr2 = {Integer.valueOf(((ColorDrawable) view.getBackground()).getColor()), Integer.valueOf(WXResourceUtils.getColor(str2))};
                    $jacocoInit[71] = true;
                    holders.add(PropertyValuesHolder.ofObject(backgroundColorProperty2, argbEvaluator2, numArr2));
                    $jacocoInit[72] = true;
                }
            }
            if (view.getLayoutParams() == null) {
                $jacocoInit[73] = true;
            } else {
                String str3 = style.width;
                $jacocoInit[74] = true;
                if (!TextUtils.isEmpty(str3)) {
                    $jacocoInit[75] = true;
                } else if (TextUtils.isEmpty(style.height)) {
                    $jacocoInit[76] = true;
                } else {
                    $jacocoInit[77] = true;
                }
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                $jacocoInit[78] = true;
                if (TextUtils.isEmpty(style.width)) {
                    $jacocoInit[79] = true;
                } else {
                    $jacocoInit[80] = true;
                    WidthProperty widthProperty = new WidthProperty();
                    String str4 = style.width;
                    $jacocoInit[81] = true;
                    int[] iArr = {layoutParams.width, (int) WXViewUtils.getRealPxByWidth(WXUtils.getFloat(str4), i)};
                    $jacocoInit[82] = true;
                    holders.add(PropertyValuesHolder.ofInt(widthProperty, iArr));
                    $jacocoInit[83] = true;
                }
                if (TextUtils.isEmpty(style.height)) {
                    $jacocoInit[84] = true;
                } else {
                    $jacocoInit[85] = true;
                    HeightProperty heightProperty = new HeightProperty();
                    String str5 = style.height;
                    $jacocoInit[86] = true;
                    int[] iArr2 = {layoutParams.height, (int) WXViewUtils.getRealPxByWidth(WXUtils.getFloat(str5), i)};
                    $jacocoInit[87] = true;
                    holders.add(PropertyValuesHolder.ofInt(heightProperty, iArr2));
                    $jacocoInit[88] = true;
                }
            }
            if (style.getPivot() == null) {
                $jacocoInit[89] = true;
            } else {
                $jacocoInit[90] = true;
                Pair<Float, Float> pivot = style.getPivot();
                $jacocoInit[91] = true;
                view.setPivotX(((Float) pivot.first).floatValue());
                $jacocoInit[92] = true;
                view.setPivotY(((Float) pivot.second).floatValue());
                $jacocoInit[93] = true;
            }
            $jacocoInit[94] = true;
            $jacocoInit[95] = true;
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, (PropertyValuesHolder[]) holders.toArray(new PropertyValuesHolder[holders.size()]));
            $jacocoInit[96] = true;
            ofPropertyValuesHolder.setStartDelay(this.mAnimationBean.delay);
            $jacocoInit[97] = true;
            return ofPropertyValuesHolder;
        }
        $jacocoInit[98] = true;
        return null;
    }

    @Nullable
    private Animator.AnimatorListener createAnimatorListener(final WXSDKInstance wXSDKInstance, @Nullable final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[99] = true;
            AnonymousClass1 r1 = new AnimatorListenerAdapter(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ GraphicActionAnimation this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-7563333389135417525L, "com/taobao/weex/ui/action/GraphicActionAnimation$1", 6);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void onAnimationEnd(Animator animator) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (wXSDKInstance == null) {
                        $jacocoInit[1] = true;
                    } else if (wXSDKInstance.isDestroy()) {
                        $jacocoInit[2] = true;
                    } else {
                        WXSDKManager.getInstance().callback(wXSDKInstance.getInstanceId(), str, new HashMap());
                        $jacocoInit[4] = true;
                        $jacocoInit[5] = true;
                    }
                    WXLogUtils.e("RenderContextImpl-onAnimationEnd WXSDKInstance == null NPE or instance is destroyed");
                    $jacocoInit[3] = true;
                    $jacocoInit[5] = true;
                }
            };
            $jacocoInit[100] = true;
            return r1;
        }
        $jacocoInit[101] = true;
        return null;
    }

    @Nullable
    private Interpolator createTimeInterpolator() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mAnimationBean.timingFunction;
        $jacocoInit[102] = true;
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[103] = true;
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1965120668) {
                if (hashCode != -1102672091) {
                    if (hashCode != -789192465) {
                        if (hashCode != -361990811) {
                            $jacocoInit[104] = true;
                        } else if (!str.equals("ease-in-out")) {
                            $jacocoInit[109] = true;
                        } else {
                            $jacocoInit[110] = true;
                            c = 2;
                        }
                    } else if (!str.equals("ease-out")) {
                        $jacocoInit[107] = true;
                    } else {
                        $jacocoInit[108] = true;
                        c = 1;
                    }
                } else if (!str.equals("linear")) {
                    $jacocoInit[111] = true;
                } else {
                    $jacocoInit[112] = true;
                    c = 3;
                }
            } else if (!str.equals("ease-in")) {
                $jacocoInit[105] = true;
            } else {
                $jacocoInit[106] = true;
                c = 0;
            }
            switch (c) {
                case 0:
                    AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
                    $jacocoInit[114] = true;
                    return accelerateInterpolator;
                case 1:
                    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
                    $jacocoInit[115] = true;
                    return decelerateInterpolator;
                case 2:
                    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
                    $jacocoInit[116] = true;
                    return accelerateDecelerateInterpolator;
                case 3:
                    LinearInterpolator linearInterpolator = new LinearInterpolator();
                    $jacocoInit[117] = true;
                    return linearInterpolator;
                default:
                    $jacocoInit[113] = true;
                    try {
                        SingleFunctionParser singleFunctionParser = new SingleFunctionParser(this.mAnimationBean.timingFunction, new SingleFunctionParser.FlatMapper<Float>(this) {
                            private static transient /* synthetic */ boolean[] $jacocoData;
                            final /* synthetic */ GraphicActionAnimation this$0;

                            private static /* synthetic */ boolean[] $jacocoInit() {
                                boolean[] zArr = $jacocoData;
                                if (zArr != null) {
                                    return zArr;
                                }
                                boolean[] a2 = Offline.a(7847648773263568479L, "com/taobao/weex/ui/action/GraphicActionAnimation$2", 3);
                                $jacocoData = a2;
                                return a2;
                            }

                            {
                                boolean[] $jacocoInit = $jacocoInit();
                                this.this$0 = r3;
                                $jacocoInit[0] = true;
                            }

                            public Float map(String str) {
                                boolean[] $jacocoInit = $jacocoInit();
                                Float valueOf = Float.valueOf(Float.parseFloat(str));
                                $jacocoInit[1] = true;
                                return valueOf;
                            }
                        });
                        $jacocoInit[118] = true;
                        List parse = singleFunctionParser.parse("cubic-bezier");
                        $jacocoInit[119] = true;
                        if (parse == null) {
                            $jacocoInit[120] = true;
                        } else if (parse.size() != 4) {
                            $jacocoInit[121] = true;
                        } else {
                            $jacocoInit[122] = true;
                            float floatValue = ((Float) parse.get(0)).floatValue();
                            float floatValue2 = ((Float) parse.get(1)).floatValue();
                            float floatValue3 = ((Float) parse.get(2)).floatValue();
                            float floatValue4 = ((Float) parse.get(3)).floatValue();
                            $jacocoInit[123] = true;
                            Interpolator create = PathInterpolatorCompat.create(floatValue, floatValue2, floatValue3, floatValue4);
                            $jacocoInit[124] = true;
                            return create;
                        }
                        $jacocoInit[125] = true;
                        return null;
                    } catch (RuntimeException unused) {
                        $jacocoInit[126] = true;
                        return null;
                    }
            }
        } else {
            $jacocoInit[127] = true;
            return null;
        }
    }
}
