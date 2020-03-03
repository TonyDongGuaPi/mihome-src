package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXCircleIndicator;
import com.taobao.weex.ui.view.WXCirclePageAdapter;
import com.taobao.weex.ui.view.WXCircleViewPager;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSliderNeighbor extends WXSlider {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String CURRENT_ITEM_SCALE = "currentItemScale";
    private static final float DEFAULT_CURRENT_ITEM_SCALE = 0.9f;
    private static final float DEFAULT_NEIGHBOR_ALPHA = 0.6f;
    private static final float DEFAULT_NEIGHBOR_SCALE = 0.8f;
    private static final int DEFAULT_NEIGHBOR_SPACE = 25;
    public static final String NEIGHBOR_ALPHA = "neighborAlpha";
    public static final String NEIGHBOR_SCALE = "neighborScale";
    public static final String NEIGHBOR_SPACE = "neighborSpace";
    private ZoomTransformer mCachedTransformer;
    private float mCurrentItemScale = DEFAULT_CURRENT_ITEM_SCALE;
    private float mNeighborAlpha = DEFAULT_NEIGHBOR_ALPHA;
    private float mNeighborScale = 0.8f;
    private float mNeighborSpace = 25.0f;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8615169992003376423L, "com/taobao/weex/ui/component/WXSliderNeighbor", 157);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(WXSliderNeighbor wXSliderNeighbor, View view, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSliderNeighbor.updateNeighbor(view, f, f2);
        $jacocoInit[151] = true;
    }

    static /* synthetic */ float access$100(WXSliderNeighbor wXSliderNeighbor) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = wXSliderNeighbor.mNeighborScale;
        $jacocoInit[152] = true;
        return f;
    }

    static /* synthetic */ float access$200(WXSliderNeighbor wXSliderNeighbor) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = wXSliderNeighbor.mCurrentItemScale;
        $jacocoInit[153] = true;
        return f;
    }

    static /* synthetic */ float access$300(WXSliderNeighbor wXSliderNeighbor) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = wXSliderNeighbor.mNeighborAlpha;
        $jacocoInit[154] = true;
        return f;
    }

    static /* synthetic */ float access$400(WXSliderNeighbor wXSliderNeighbor, View view) {
        boolean[] $jacocoInit = $jacocoInit();
        float calculateTranslation = wXSliderNeighbor.calculateTranslation(view);
        $jacocoInit[155] = true;
        return calculateTranslation;
    }

    static /* synthetic */ void access$500(WXSliderNeighbor wXSliderNeighbor, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSliderNeighbor.updateAdapterScaleAndAlpha(f, f2);
        $jacocoInit[156] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSliderNeighbor(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4685497596871162829L, "com/taobao/weex/ui/component/WXSliderNeighbor$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXSliderNeighbor wXSliderNeighbor = new WXSliderNeighbor(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXSliderNeighbor;
        }
    }

    public void bindData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.bindData(wXComponent);
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public FrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = new FrameLayout(context);
        $jacocoInit[2] = true;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        $jacocoInit[3] = true;
        this.mViewPager = new WXCircleViewPager(getContext());
        $jacocoInit[4] = true;
        this.mViewPager.setLayoutParams(layoutParams);
        $jacocoInit[5] = true;
        this.mAdapter = new WXCirclePageAdapter();
        $jacocoInit[6] = true;
        this.mViewPager.setAdapter(this.mAdapter);
        $jacocoInit[7] = true;
        frameLayout.addView(this.mViewPager);
        $jacocoInit[8] = true;
        this.mViewPager.addOnPageChangeListener(this.mPageChangeListener);
        $jacocoInit[9] = true;
        this.mViewPager.setOverScrollMode(2);
        $jacocoInit[10] = true;
        registerActivityStateListener();
        $jacocoInit[11] = true;
        this.mViewPager.setPageTransformer(false, createTransformer());
        $jacocoInit[12] = true;
        return frameLayout;
    }

    /* access modifiers changed from: package-private */
    public ZoomTransformer createTransformer() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCachedTransformer != null) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            this.mCachedTransformer = new ZoomTransformer(this);
            $jacocoInit[15] = true;
        }
        ZoomTransformer zoomTransformer = this.mCachedTransformer;
        $jacocoInit[16] = true;
        return zoomTransformer;
    }

    public void addSubView(View view, final int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[17] = true;
        } else if (this.mAdapter == null) {
            $jacocoInit[18] = true;
        } else if (view instanceof WXCircleIndicator) {
            $jacocoInit[20] = true;
            return;
        } else {
            FrameLayout frameLayout = new FrameLayout(getContext());
            $jacocoInit[21] = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            $jacocoInit[22] = true;
            view.setLayoutParams(layoutParams);
            $jacocoInit[23] = true;
            frameLayout.addView(view);
            $jacocoInit[24] = true;
            super.addSubView(frameLayout, i);
            $jacocoInit[25] = true;
            updateAdapterScaleAndAlpha(this.mNeighborAlpha, this.mNeighborScale);
            $jacocoInit[26] = true;
            this.mViewPager.postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSliderNeighbor this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(6467401261863826020L, "com/taobao/weex/ui/component/WXSliderNeighbor$1", 16);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r4 = this;
                        boolean[] r0 = $jacocoInit()
                        r1 = 1
                        com.taobao.weex.ui.component.WXSliderNeighbor r2 = r4.this$0     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        com.taobao.weex.ui.view.WXCircleViewPager r2 = r2.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        int r2 = r2.getRealCount()     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        if (r2 > 0) goto L_0x0012
                        r0[r1] = r1     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        goto L_0x0033
                    L_0x0012:
                        int r2 = r8     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        r3 = 2
                        if (r2 > r3) goto L_0x001a
                        r0[r3] = r1     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        goto L_0x0033
                    L_0x001a:
                        r2 = 3
                        r0[r2] = r1     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        com.taobao.weex.ui.component.WXSliderNeighbor r2 = r4.this$0     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        com.taobao.weex.ui.view.WXCircleViewPager r2 = r2.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        r2.beginFakeDrag()     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        r2 = 4
                        r0[r2] = r1     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        com.taobao.weex.ui.component.WXSliderNeighbor r2 = r4.this$0     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        com.taobao.weex.ui.view.WXCircleViewPager r2 = r2.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        r3 = 1065353216(0x3f800000, float:1.0)
                        r2.fakeDragBy(r3)     // Catch:{ IndexOutOfBoundsException -> 0x005c, all -> 0x0042 }
                        r2 = 5
                        r0[r2] = r1     // Catch:{ Exception -> 0x003e }
                    L_0x0033:
                        com.taobao.weex.ui.component.WXSliderNeighbor r2 = r4.this$0     // Catch:{  }
                        com.taobao.weex.ui.view.WXCircleViewPager r2 = r2.mViewPager     // Catch:{  }
                        r2.endFakeDrag()     // Catch:{  }
                        r2 = 6
                        r0[r2] = r1
                        goto L_0x0070
                    L_0x003e:
                        r2 = 7
                        r0[r2] = r1
                        goto L_0x0070
                    L_0x0042:
                        r2 = move-exception
                        r3 = 11
                        r0[r3] = r1     // Catch:{ Exception -> 0x0053 }
                        com.taobao.weex.ui.component.WXSliderNeighbor r3 = r4.this$0     // Catch:{ Exception -> 0x0053 }
                        com.taobao.weex.ui.view.WXCircleViewPager r3 = r3.mViewPager     // Catch:{ Exception -> 0x0053 }
                        r3.endFakeDrag()     // Catch:{ Exception -> 0x0053 }
                        r3 = 12
                        r0[r3] = r1
                        goto L_0x0057
                    L_0x0053:
                        r3 = 13
                        r0[r3] = r1
                    L_0x0057:
                        r3 = 14
                        r0[r3] = r1
                        throw r2
                    L_0x005c:
                        r2 = 8
                        r0[r2] = r1     // Catch:{ Exception -> 0x006c }
                        com.taobao.weex.ui.component.WXSliderNeighbor r2 = r4.this$0     // Catch:{ Exception -> 0x006c }
                        com.taobao.weex.ui.view.WXCircleViewPager r2 = r2.mViewPager     // Catch:{ Exception -> 0x006c }
                        r2.endFakeDrag()     // Catch:{ Exception -> 0x006c }
                        r2 = 9
                        r0[r2] = r1
                        goto L_0x0070
                    L_0x006c:
                        r2 = 10
                        r0[r2] = r1
                    L_0x0070:
                        r2 = 15
                        r0[r2] = r1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.AnonymousClass1.run():void");
                }
            }), 50);
            $jacocoInit[27] = true;
            return;
        }
        $jacocoInit[19] = true;
    }

    private void updateScaleAndAlpha(View view, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[28] = true;
            return;
        }
        if (f < 0.0f) {
            $jacocoInit[29] = true;
        } else if (view.getAlpha() == f) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            view.setAlpha(f);
            $jacocoInit[32] = true;
        }
        if (f2 < 0.0f) {
            $jacocoInit[33] = true;
        } else if (view.getScaleX() == f2) {
            $jacocoInit[34] = true;
        } else {
            $jacocoInit[35] = true;
            view.setScaleX(f2);
            $jacocoInit[36] = true;
            view.setScaleY(f2);
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    private void updateAdapterScaleAndAlpha(final float f, final float f2) {
        int i;
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        List<View> views = this.mAdapter.getViews();
        $jacocoInit[39] = true;
        int currentItem = this.mViewPager.getCurrentItem();
        $jacocoInit[40] = true;
        if (views.size() <= 0) {
            $jacocoInit[41] = true;
        } else {
            $jacocoInit[42] = true;
            final View view = views.get(currentItem);
            $jacocoInit[43] = true;
            updateScaleAndAlpha(((ViewGroup) view).getChildAt(0), 1.0f, this.mCurrentItemScale);
            $jacocoInit[44] = true;
            if (views.size() < 2) {
                $jacocoInit[45] = true;
                return;
            }
            view.postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSliderNeighbor this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-9062025155307666493L, "com/taobao/weex/ui/component/WXSliderNeighbor$2", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    WXSliderNeighbor.access$000(this.this$0, view, f, f2);
                    $jacocoInit[1] = true;
                }
            }), 17);
            $jacocoInit[46] = true;
            if (currentItem == 0) {
                i = views.size() - 1;
                $jacocoInit[47] = true;
            } else {
                i = currentItem - 1;
                $jacocoInit[48] = true;
            }
            $jacocoInit[49] = true;
            if (currentItem == views.size() - 1) {
                $jacocoInit[50] = true;
                i2 = 0;
            } else {
                i2 = currentItem + 1;
                $jacocoInit[51] = true;
            }
            $jacocoInit[52] = true;
            $jacocoInit[53] = true;
            int i3 = 0;
            while (i3 < this.mAdapter.getRealCount()) {
                if (i3 == i) {
                    $jacocoInit[55] = true;
                } else if (i3 == currentItem) {
                    $jacocoInit[56] = true;
                } else if (i3 == i2) {
                    $jacocoInit[57] = true;
                } else {
                    $jacocoInit[58] = true;
                    ((ViewGroup) views.get(i3)).getChildAt(0).setAlpha(0.0f);
                    $jacocoInit[59] = true;
                }
                i3++;
                $jacocoInit[60] = true;
            }
            $jacocoInit[54] = true;
        }
        $jacocoInit[61] = true;
    }

    private void updateNeighbor(View view, float f, float f2) {
        int i;
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        List<View> views = this.mAdapter.getViews();
        $jacocoInit[62] = true;
        int currentItem = this.mViewPager.getCurrentItem();
        $jacocoInit[63] = true;
        float calculateTranslation = calculateTranslation(view);
        $jacocoInit[64] = true;
        if (currentItem == 0) {
            i = views.size() - 1;
            $jacocoInit[65] = true;
        } else {
            i = currentItem - 1;
            $jacocoInit[66] = true;
        }
        $jacocoInit[67] = true;
        View view2 = views.get(i);
        $jacocoInit[68] = true;
        if (currentItem == views.size() - 1) {
            i2 = 0;
            $jacocoInit[69] = true;
        } else {
            i2 = currentItem + 1;
            $jacocoInit[70] = true;
        }
        $jacocoInit[71] = true;
        View view3 = views.get(i2);
        $jacocoInit[72] = true;
        if (views.size() != 2) {
            moveLeft(view2, calculateTranslation, f, f2);
            $jacocoInit[78] = true;
            moveRight(view3, calculateTranslation, f, f2);
            $jacocoInit[79] = true;
        } else if (currentItem == 0) {
            $jacocoInit[73] = true;
            moveRight(view3, calculateTranslation, f, f2);
            $jacocoInit[74] = true;
        } else if (currentItem != 1) {
            $jacocoInit[75] = true;
        } else {
            $jacocoInit[76] = true;
            moveLeft(view2, calculateTranslation, f, f2);
            $jacocoInit[77] = true;
        }
        $jacocoInit[80] = true;
    }

    private void moveLeft(View view, float f, float f2, float f3) {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) view;
        updateScaleAndAlpha(viewGroup.getChildAt(0), f2, f3);
        $jacocoInit[81] = true;
        view.setTranslationX(f);
        $jacocoInit[82] = true;
        viewGroup.getChildAt(0).setTranslationX(f);
        $jacocoInit[83] = true;
    }

    private void moveRight(View view, float f, float f2, float f3) {
        boolean[] $jacocoInit = $jacocoInit();
        moveLeft(view, -f, f2, f3);
        $jacocoInit[84] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborScale")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborScale(java.lang.String r4) {
        /*
            r3 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 85
            r0[r2] = r1
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 == 0) goto L_0x0014
            r4 = 86
            r0[r4] = r1
            goto L_0x0025
        L_0x0014:
            r2 = 87
            r0[r2] = r1     // Catch:{ NumberFormatException -> 0x0021 }
            float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x0021 }
            r2 = 88
            r0[r2] = r1
            goto L_0x0028
        L_0x0021:
            r4 = 89
            r0[r4] = r1
        L_0x0025:
            r4 = 1061997773(0x3f4ccccd, float:0.8)
        L_0x0028:
            float r2 = r3.mNeighborScale
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0033
            r4 = 90
            r0[r4] = r1
            goto L_0x0042
        L_0x0033:
            r3.mNeighborScale = r4
            r2 = 91
            r0[r2] = r1
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3.updateAdapterScaleAndAlpha(r2, r4)
            r4 = 92
            r0[r4] = r1
        L_0x0042:
            r4 = 93
            r0[r4] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.setNeighborScale(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborAlpha")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborAlpha(java.lang.String r4) {
        /*
            r3 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 94
            r0[r2] = r1
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 == 0) goto L_0x0014
            r4 = 95
            r0[r4] = r1
            goto L_0x0025
        L_0x0014:
            r2 = 96
            r0[r2] = r1     // Catch:{ NumberFormatException -> 0x0021 }
            float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x0021 }
            r2 = 97
            r0[r2] = r1
            goto L_0x0028
        L_0x0021:
            r4 = 98
            r0[r4] = r1
        L_0x0025:
            r4 = 1058642330(0x3f19999a, float:0.6)
        L_0x0028:
            float r2 = r3.mNeighborAlpha
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0033
            r4 = 99
            r0[r4] = r1
            goto L_0x0042
        L_0x0033:
            r3.mNeighborAlpha = r4
            r2 = 100
            r0[r2] = r1
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3.updateAdapterScaleAndAlpha(r4, r2)
            r4 = 101(0x65, float:1.42E-43)
            r0[r4] = r1
        L_0x0042:
            r4 = 102(0x66, float:1.43E-43)
            r0[r4] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.setNeighborAlpha(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborSpace")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborSpace(java.lang.String r4) {
        /*
            r3 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 103(0x67, float:1.44E-43)
            r0[r2] = r1
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 == 0) goto L_0x0014
            r4 = 104(0x68, float:1.46E-43)
            r0[r4] = r1
            goto L_0x0025
        L_0x0014:
            r2 = 105(0x69, float:1.47E-43)
            r0[r2] = r1     // Catch:{ NumberFormatException -> 0x0021 }
            float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x0021 }
            r2 = 106(0x6a, float:1.49E-43)
            r0[r2] = r1
            goto L_0x0027
        L_0x0021:
            r4 = 107(0x6b, float:1.5E-43)
            r0[r4] = r1
        L_0x0025:
            r4 = 1103626240(0x41c80000, float:25.0)
        L_0x0027:
            float r2 = r3.mNeighborSpace
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0032
            r4 = 108(0x6c, float:1.51E-43)
            r0[r4] = r1
            goto L_0x0038
        L_0x0032:
            r3.mNeighborSpace = r4
            r4 = 109(0x6d, float:1.53E-43)
            r0[r4] = r1
        L_0x0038:
            r4 = 110(0x6e, float:1.54E-43)
            r0[r4] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.setNeighborSpace(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "currentItemScale")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCurrentItemScale(java.lang.String r4) {
        /*
            r3 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 111(0x6f, float:1.56E-43)
            r0[r2] = r1
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 == 0) goto L_0x0014
            r4 = 112(0x70, float:1.57E-43)
            r0[r4] = r1
            goto L_0x0025
        L_0x0014:
            r2 = 113(0x71, float:1.58E-43)
            r0[r2] = r1     // Catch:{ NumberFormatException -> 0x0021 }
            float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x0021 }
            r2 = 114(0x72, float:1.6E-43)
            r0[r2] = r1
            goto L_0x0028
        L_0x0021:
            r4 = 115(0x73, float:1.61E-43)
            r0[r4] = r1
        L_0x0025:
            r4 = 1063675494(0x3f666666, float:0.9)
        L_0x0028:
            float r2 = r3.mCurrentItemScale
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0033
            r4 = 116(0x74, float:1.63E-43)
            r0[r4] = r1
            goto L_0x0042
        L_0x0033:
            r3.mCurrentItemScale = r4
            r4 = 117(0x75, float:1.64E-43)
            r0[r4] = r1
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3.updateAdapterScaleAndAlpha(r4, r4)
            r4 = 118(0x76, float:1.65E-43)
            r0[r4] = r1
        L_0x0042:
            r4 = 119(0x77, float:1.67E-43)
            r0[r4] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.setCurrentItemScale(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = -1763701364(0xffffffff96e00d8c, float:-3.61977E-25)
            r3 = 1
            if (r1 == r2) goto L_0x005b
            r2 = -1747360392(0xffffffff97d96578, float:-1.4048911E-24)
            if (r1 == r2) goto L_0x0048
            r2 = -1746973388(0xffffffff97df4d34, float:-1.4430526E-24)
            if (r1 == r2) goto L_0x0035
            r2 = -1013904258(0xffffffffc3910c7e, float:-290.0976)
            if (r1 == r2) goto L_0x0022
            r1 = 120(0x78, float:1.68E-43)
            r0[r1] = r3
            goto L_0x0067
        L_0x0022:
            java.lang.String r1 = "currentItemScale"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002f
            r1 = 127(0x7f, float:1.78E-43)
            r0[r1] = r3
            goto L_0x0067
        L_0x002f:
            r1 = 3
            r2 = 128(0x80, float:1.794E-43)
            r0[r2] = r3
            goto L_0x006e
        L_0x0035:
            java.lang.String r1 = "neighborSpace"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0042
            r1 = 125(0x7d, float:1.75E-43)
            r0[r1] = r3
            goto L_0x0067
        L_0x0042:
            r1 = 2
            r2 = 126(0x7e, float:1.77E-43)
            r0[r2] = r3
            goto L_0x006e
        L_0x0048:
            java.lang.String r1 = "neighborScale"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0055
            r1 = 121(0x79, float:1.7E-43)
            r0[r1] = r3
            goto L_0x0067
        L_0x0055:
            r1 = 0
            r2 = 122(0x7a, float:1.71E-43)
            r0[r2] = r3
            goto L_0x006e
        L_0x005b:
            java.lang.String r1 = "neighborAlpha"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0069
            r1 = 123(0x7b, float:1.72E-43)
            r0[r1] = r3
        L_0x0067:
            r1 = -1
            goto L_0x006e
        L_0x0069:
            r1 = 124(0x7c, float:1.74E-43)
            r0[r1] = r3
            r1 = 1
        L_0x006e:
            r2 = 0
            switch(r1) {
                case 0: goto L_0x00cc;
                case 1: goto L_0x00b1;
                case 2: goto L_0x0096;
                case 3: goto L_0x007b;
                default: goto L_0x0072;
            }
        L_0x0072:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 145(0x91, float:2.03E-43)
            r0[r6] = r3
            return r5
        L_0x007b:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x0086
            r5 = 141(0x8d, float:1.98E-43)
            r0[r5] = r3
            goto L_0x0091
        L_0x0086:
            r6 = 142(0x8e, float:1.99E-43)
            r0[r6] = r3
            r4.setCurrentItemScale(r5)
            r5 = 143(0x8f, float:2.0E-43)
            r0[r5] = r3
        L_0x0091:
            r5 = 144(0x90, float:2.02E-43)
            r0[r5] = r3
            return r3
        L_0x0096:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x00a1
            r5 = 137(0x89, float:1.92E-43)
            r0[r5] = r3
            goto L_0x00ac
        L_0x00a1:
            r6 = 138(0x8a, float:1.93E-43)
            r0[r6] = r3
            r4.setNeighborSpace(r5)
            r5 = 139(0x8b, float:1.95E-43)
            r0[r5] = r3
        L_0x00ac:
            r5 = 140(0x8c, float:1.96E-43)
            r0[r5] = r3
            return r3
        L_0x00b1:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x00bc
            r5 = 133(0x85, float:1.86E-43)
            r0[r5] = r3
            goto L_0x00c7
        L_0x00bc:
            r6 = 134(0x86, float:1.88E-43)
            r0[r6] = r3
            r4.setNeighborAlpha(r5)
            r5 = 135(0x87, float:1.89E-43)
            r0[r5] = r3
        L_0x00c7:
            r5 = 136(0x88, float:1.9E-43)
            r0[r5] = r3
            return r3
        L_0x00cc:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x00d7
            r5 = 129(0x81, float:1.81E-43)
            r0[r5] = r3
            goto L_0x00e2
        L_0x00d7:
            r6 = 130(0x82, float:1.82E-43)
            r0[r6] = r3
            r4.setNeighborScale(r5)
            r5 = 131(0x83, float:1.84E-43)
            r0[r5] = r3
        L_0x00e2:
            r5 = 132(0x84, float:1.85E-43)
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    private float calculateTranslation(@NonNull View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(view instanceof ViewGroup)) {
            $jacocoInit[146] = true;
            return 0.0f;
        }
        View childAt = ((ViewGroup) view).getChildAt(0);
        $jacocoInit[147] = true;
        $jacocoInit[148] = true;
        float measuredWidth = ((((float) view.getMeasuredWidth()) - (((float) childAt.getMeasuredWidth()) * this.mNeighborScale)) / 4.0f) + ((((((float) view.getMeasuredWidth()) - (((float) childAt.getMeasuredWidth()) * this.mCurrentItemScale)) / 2.0f) - WXViewUtils.getRealPxByWidth(this.mNeighborSpace, getInstance().getInstanceViewPortWidth())) / 2.0f);
        $jacocoInit[149] = true;
        return measuredWidth;
    }

    class ZoomTransformer implements ViewPager.PageTransformer {
        private static transient /* synthetic */ boolean[] $jacocoData;
        final /* synthetic */ WXSliderNeighbor this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(234241470670415998L, "com/taobao/weex/ui/component/WXSliderNeighbor$ZoomTransformer", 45);
            $jacocoData = a2;
            return a2;
        }

        ZoomTransformer(WXSliderNeighbor wXSliderNeighbor) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXSliderNeighbor;
            $jacocoInit[0] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0065  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0081  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0086  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void transformPage(android.view.View r12, float r13) {
            /*
                r11 = this;
                boolean[] r0 = $jacocoInit()
                com.taobao.weex.ui.component.WXSliderNeighbor r1 = r11.this$0
                com.taobao.weex.ui.view.WXCirclePageAdapter r1 = r1.mAdapter
                int r1 = r1.getPagePosition(r12)
                r2 = 1
                r0[r2] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r3 = r11.this$0
                com.taobao.weex.ui.view.WXCircleViewPager r3 = r3.mViewPager
                int r3 = r3.getCurrentItem()
                r4 = 2
                r0[r4] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r5 = r11.this$0
                com.taobao.weex.ui.view.WXCirclePageAdapter r5 = r5.mAdapter
                int r5 = r5.getRealCount()
                r6 = 3
                r0[r6] = r2
                r6 = 0
                if (r3 != 0) goto L_0x002c
                r7 = 4
                r0[r7] = r2
                goto L_0x003f
            L_0x002c:
                int r7 = r5 + -1
                if (r3 != r7) goto L_0x0034
                r7 = 5
                r0[r7] = r2
                goto L_0x003f
            L_0x0034:
                int r7 = r1 - r3
                int r7 = java.lang.Math.abs(r7)
                if (r7 > r2) goto L_0x0041
                r7 = 6
                r0[r7] = r2
            L_0x003f:
                r7 = 0
                goto L_0x0045
            L_0x0041:
                r7 = 7
                r0[r7] = r2
                r7 = 1
            L_0x0045:
                if (r3 == 0) goto L_0x004c
                r8 = 8
                r0[r8] = r2
                goto L_0x0061
            L_0x004c:
                int r8 = r5 + -1
                if (r1 < r8) goto L_0x0055
                r8 = 9
                r0[r8] = r2
                goto L_0x0061
            L_0x0055:
                if (r1 > r2) goto L_0x005c
                r8 = 10
                r0[r8] = r2
                goto L_0x0061
            L_0x005c:
                r7 = 11
                r0[r7] = r2
                r7 = 1
            L_0x0061:
                int r8 = r5 + -1
                if (r3 == r8) goto L_0x006a
                r1 = 12
                r0[r1] = r2
                goto L_0x007f
            L_0x006a:
                int r3 = r5 + -2
                if (r1 < r3) goto L_0x0073
                r1 = 13
                r0[r1] = r2
                goto L_0x007f
            L_0x0073:
                if (r1 > 0) goto L_0x007a
                r1 = 14
                r0[r1] = r2
                goto L_0x007f
            L_0x007a:
                r1 = 15
                r0[r1] = r2
                r7 = 1
            L_0x007f:
                if (r7 == 0) goto L_0x0086
                r12 = 16
                r0[r12] = r2
                return
            L_0x0086:
                r1 = r12
                android.view.ViewGroup r1 = (android.view.ViewGroup) r1
                android.view.View r1 = r1.getChildAt(r6)
                if (r1 != 0) goto L_0x0094
                r12 = 17
                r0[r12] = r2
                return
            L_0x0094:
                int r3 = -r5
                int r3 = r3 + r2
                float r3 = (float) r3
                int r3 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
                if (r3 <= 0) goto L_0x00a0
                r3 = 18
                r0[r3] = r2
                goto L_0x00a6
            L_0x00a0:
                float r3 = (float) r5
                float r13 = r13 + r3
                r3 = 19
                r0[r3] = r2
            L_0x00a6:
                float r3 = (float) r8
                int r3 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
                if (r3 >= 0) goto L_0x00b0
                r3 = 20
                r0[r3] = r2
                goto L_0x00b6
            L_0x00b0:
                float r3 = (float) r5
                float r13 = r13 - r3
                r3 = 21
                r0[r3] = r2
            L_0x00b6:
                r3 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
                if (r3 >= 0) goto L_0x00c2
                r12 = 22
                r0[r12] = r2
                goto L_0x0198
            L_0x00c2:
                r3 = 1065353216(0x3f800000, float:1.0)
                int r6 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
                if (r6 <= 0) goto L_0x00ce
                r12 = 23
                r0[r12] = r2
                goto L_0x0198
            L_0x00ce:
                r6 = 24
                r0[r6] = r2
                float r6 = java.lang.Math.abs(r13)
                float r6 = r6 - r3
                float r6 = java.lang.Math.abs(r6)
                r7 = 25
                r0[r7] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r7 = r11.this$0
                float r7 = com.taobao.weex.ui.component.WXSliderNeighbor.access$100(r7)
                com.taobao.weex.ui.component.WXSliderNeighbor r8 = r11.this$0
                float r8 = com.taobao.weex.ui.component.WXSliderNeighbor.access$200(r8)
                com.taobao.weex.ui.component.WXSliderNeighbor r9 = r11.this$0
                float r9 = com.taobao.weex.ui.component.WXSliderNeighbor.access$100(r9)
                float r8 = r8 - r9
                float r8 = r8 * r6
                float r7 = r7 + r8
                r8 = 26
                r0[r8] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r8 = r11.this$0
                float r8 = com.taobao.weex.ui.component.WXSliderNeighbor.access$300(r8)
                float r8 = r3 - r8
                float r8 = r8 * r6
                com.taobao.weex.ui.component.WXSliderNeighbor r6 = r11.this$0
                float r6 = com.taobao.weex.ui.component.WXSliderNeighbor.access$300(r6)
                float r8 = r8 + r6
                r6 = 27
                r0[r6] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r6 = r11.this$0
                float r6 = com.taobao.weex.ui.component.WXSliderNeighbor.access$400(r6, r12)
                r9 = 0
                int r10 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
                if (r10 <= 0) goto L_0x012f
                float r13 = r13 * r6
                r3 = 28
                r0[r3] = r2
                float r13 = -r13
                r1.setTranslationX(r13)
                r3 = 29
                r0[r3] = r2
                r12.setTranslationX(r13)
                r12 = 30
                r0[r12] = r2
                goto L_0x0183
            L_0x012f:
                int r10 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
                if (r10 != 0) goto L_0x015b
                r13 = 31
                r0[r13] = r2
                r12.setTranslationX(r9)
                r12 = 32
                r0[r12] = r2
                r1.setTranslationX(r9)
                r12 = 33
                r0[r12] = r2
                com.taobao.weex.ui.component.WXSliderNeighbor r12 = r11.this$0
                com.taobao.weex.ui.component.WXSliderNeighbor r13 = r11.this$0
                float r13 = com.taobao.weex.ui.component.WXSliderNeighbor.access$300(r13)
                com.taobao.weex.ui.component.WXSliderNeighbor r3 = r11.this$0
                float r3 = com.taobao.weex.ui.component.WXSliderNeighbor.access$100(r3)
                com.taobao.weex.ui.component.WXSliderNeighbor.access$500(r12, r13, r3)
                r12 = 34
                r0[r12] = r2
                goto L_0x0183
            L_0x015b:
                if (r5 == r4) goto L_0x0162
                r3 = 35
                r0[r3] = r2
                goto L_0x016e
            L_0x0162:
                float r4 = java.lang.Math.abs(r13)
                int r3 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
                if (r3 == 0) goto L_0x019d
                r3 = 36
                r0[r3] = r2
            L_0x016e:
                float r13 = -r13
                float r13 = r13 * r6
                r3 = 38
                r0[r3] = r2
                r1.setTranslationX(r13)
                r3 = 39
                r0[r3] = r2
                r12.setTranslationX(r13)
                r12 = 40
                r0[r12] = r2
            L_0x0183:
                r1.setScaleX(r7)
                r12 = 41
                r0[r12] = r2
                r1.setScaleY(r7)
                r12 = 42
                r0[r12] = r2
                r1.setAlpha(r8)
                r12 = 43
                r0[r12] = r2
            L_0x0198:
                r12 = 44
                r0[r12] = r2
                return
            L_0x019d:
                r12 = 37
                r0[r12] = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSliderNeighbor.ZoomTransformer.transformPage(android.view.View, float):void");
        }
    }
}
