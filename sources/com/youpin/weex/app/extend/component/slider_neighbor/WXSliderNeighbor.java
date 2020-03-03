package com.youpin.weex.app.extend.component.slider_neighbor;

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
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleIndicator;
import com.youpin.weex.app.extend.component.slider_neighbor.view.WXCirclePageAdapter;
import com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class WXSliderNeighbor extends WXSlider {
    public static final String CURRENT_ITEM_SCALE = "currentItemScale";
    private static final float DEFAULT_CURRENT_ITEM_SCALE = 0.9f;
    private static final float DEFAULT_NEIGHBOR_ALPHA = 0.6f;
    private static final float DEFAULT_NEIGHBOR_SCALE = 0.8f;
    private static final int DEFAULT_NEIGHBOR_SPACE = 25;
    public static final String NEIGHBOR_ALPHA = "neighborAlpha";
    public static final String NEIGHBOR_SCALE = "neighborScale";
    public static final String NEIGHBOR_SPACE = "neighborSpace";
    private boolean isInfinite = true;
    private ZoomTransformer mCachedTransformer;
    /* access modifiers changed from: private */
    public float mCurrentItemScale = DEFAULT_CURRENT_ITEM_SCALE;
    /* access modifiers changed from: private */
    public float mNeighborAlpha = DEFAULT_NEIGHBOR_ALPHA;
    /* access modifiers changed from: private */
    public float mNeighborScale = 0.8f;
    private float mNeighborSpace = 25.0f;

    public WXSliderNeighbor(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    public static class Creator implements ComponentCreator {
        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            return new WXSliderNeighbor(wXSDKInstance, wXVContainer, basicComponentData);
        }
    }

    public void bindData(WXComponent wXComponent) {
        super.bindData(wXComponent);
    }

    /* access modifiers changed from: protected */
    public FrameLayout initComponentHostView(@NonNull Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        if (getAttrs() != null) {
            this.isInfinite = WXUtils.getBoolean(getAttrs().get("infinite"), true).booleanValue();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.mViewPager = new WXCircleViewPager(getContext());
        this.mViewPager.setCircle(this.isInfinite);
        this.mViewPager.setLayoutParams(layoutParams);
        this.mAdapter = new WXCirclePageAdapter();
        this.mAdapter.setLoop(this.isInfinite);
        this.mViewPager.setAdapter(this.mAdapter);
        frameLayout.addView(this.mViewPager);
        this.mViewPager.addOnPageChangeListener(this.mPageChangeListener);
        this.mViewPager.setOverScrollMode(2);
        registerActivityStateListener();
        this.mViewPager.setPageTransformer(false, createTransformer());
        return frameLayout;
    }

    /* access modifiers changed from: package-private */
    public ZoomTransformer createTransformer() {
        if (this.mCachedTransformer == null) {
            this.mCachedTransformer = new ZoomTransformer();
        }
        return this.mCachedTransformer;
    }

    public void addSubView(View view, final int i) {
        if (view != null && this.mAdapter != null && !(view instanceof WXCircleIndicator)) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            view.setLayoutParams(layoutParams);
            frameLayout.addView(view);
            super.addSubView(frameLayout, i);
            updateAdapterScaleAndAlpha(this.mNeighborAlpha, this.mNeighborScale);
            if (this.mAdapter.getActualCount() <= 4) {
                this.mViewPager.setOffscreenPageLimit(1);
            } else {
                this.mViewPager.setOffscreenPageLimit(2);
            }
            this.mViewPager.postDelayed(WXThread.secure((Runnable) new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                    r2.this$0.mViewPager.endFakeDrag();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
                    throw r0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
                    r0 = r2.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0030 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r2 = this;
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r0 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager r0 = r0.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        int r0 = r0.getRealCount()     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        if (r0 <= 0) goto L_0x001f
                        int r0 = r5     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        r1 = 2
                        if (r0 <= r1) goto L_0x001f
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r0 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager r0 = r0.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        r0.beginFakeDrag()     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r0 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager r0 = r0.mViewPager     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                        r1 = 1065353216(0x3f800000, float:1.0)
                        r0.fakeDragBy(r1)     // Catch:{ IndexOutOfBoundsException -> 0x0030, all -> 0x0027 }
                    L_0x001f:
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r0 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ Exception -> 0x0033 }
                    L_0x0021:
                        com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager r0 = r0.mViewPager     // Catch:{ Exception -> 0x0033 }
                        r0.endFakeDrag()     // Catch:{ Exception -> 0x0033 }
                        goto L_0x0033
                    L_0x0027:
                        r0 = move-exception
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r1 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ Exception -> 0x002f }
                        com.youpin.weex.app.extend.component.slider_neighbor.view.WXCircleViewPager r1 = r1.mViewPager     // Catch:{ Exception -> 0x002f }
                        r1.endFakeDrag()     // Catch:{ Exception -> 0x002f }
                    L_0x002f:
                        throw r0
                    L_0x0030:
                        com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor r0 = com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.this     // Catch:{ Exception -> 0x0033 }
                        goto L_0x0021
                    L_0x0033:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.AnonymousClass1.run():void");
                }
            }), 50);
        }
    }

    public void remove(WXComponent wXComponent, boolean z) {
        super.remove(wXComponent, z);
        if (this.mAdapter.getActualCount() <= 4) {
            this.mViewPager.setOffscreenPageLimit(1);
        } else {
            this.mViewPager.setOffscreenPageLimit(2);
        }
        updateAdapterScaleAndAlpha(this.mNeighborAlpha, this.mNeighborScale);
    }

    private void updateScaleAndAlpha(View view, float f, float f2) {
        if (view != null) {
            if (f >= 0.0f && view.getAlpha() != f) {
                view.setAlpha(f);
            }
            if (f2 >= 0.0f && view.getScaleX() != f2) {
                view.setScaleX(f2);
                view.setScaleY(f2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateAdapterScaleAndAlpha(final float f, final float f2) {
        List<View> views = this.mAdapter.getViews();
        int currentItem = this.mViewPager.getCurrentItem();
        if (views.size() > 0) {
            final View view = views.get(currentItem);
            ViewGroup viewGroup = (ViewGroup) view;
            updateScaleAndAlpha(viewGroup.getChildAt(0), 1.0f, this.mCurrentItemScale);
            if (views.size() >= 2) {
                if (view.getMeasuredWidth() <= 0 || ((view instanceof ViewGroup) && viewGroup.getChildAt(0).getMeasuredWidth() <= 0)) {
                    view.postDelayed(WXThread.secure((Runnable) new Runnable() {
                        public void run() {
                            WXSliderNeighbor.this.updateNeighbor(view, f, f2);
                        }
                    }), 17);
                } else {
                    updateNeighbor(view, f, f2);
                }
                int size = currentItem == 0 ? views.size() - 1 : currentItem - 1;
                int i = currentItem == views.size() - 1 ? 0 : currentItem + 1;
                for (int i2 = 0; i2 < this.mAdapter.getRealCount(); i2++) {
                    if (!(i2 == size || i2 == currentItem || i2 == i)) {
                        ((ViewGroup) views.get(i2)).getChildAt(0).setAlpha(f);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateNeighbor(View view, float f, float f2) {
        List<View> views = this.mAdapter.getViews();
        int currentItem = this.mViewPager.getCurrentItem();
        int actualCount = this.mAdapter.getActualCount();
        float calculateTranslation = calculateTranslation(view);
        int i = currentItem + actualCount;
        View view2 = views.get((i - 1) % actualCount);
        View view3 = views.get((i + 1) % actualCount);
        if (actualCount != 2) {
            if (this.isInfinite || currentItem >= 1) {
                moveLeft(view2, calculateTranslation, f, f2);
            }
            if (this.isInfinite || currentItem < actualCount - 1) {
                moveRight(view3, calculateTranslation, f, f2);
            }
            if (actualCount >= 5) {
                View view4 = views.get((i - 2) % actualCount);
                View view5 = views.get((i + 2) % actualCount);
                if (this.isInfinite || currentItem >= 2) {
                    moveLeft(view4, calculateTranslation, f, f2);
                }
                if (this.isInfinite || currentItem < actualCount - 2) {
                    moveRight(view5, calculateTranslation, f, f2);
                }
            }
        } else if (currentItem == 0) {
            moveRight(view3, calculateTranslation, f, f2);
        } else if (currentItem == 1) {
            moveLeft(view2, calculateTranslation, f, f2);
        }
    }

    private void moveLeft(View view, float f, float f2, float f3) {
        ViewGroup viewGroup = (ViewGroup) view;
        updateScaleAndAlpha(viewGroup.getChildAt(0), f2, f3);
        view.setTranslationX(f);
        viewGroup.getChildAt(0).setTranslationX(f);
    }

    private void moveRight(View view, float f, float f2, float f3) {
        moveLeft(view, -f, f2, f3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborScale")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborScale(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x000b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r2 = 1061997773(0x3f4ccccd, float:0.8)
        L_0x000e:
            float r0 = r1.mNeighborScale
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x001b
            r1.mNeighborScale = r2
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1.updateAdapterScaleAndAlpha(r0, r2)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.setNeighborScale(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborAlpha")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborAlpha(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x000b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r2 = 1058642330(0x3f19999a, float:0.6)
        L_0x000e:
            float r0 = r1.mNeighborAlpha
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x001b
            r1.mNeighborAlpha = r2
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1.updateAdapterScaleAndAlpha(r2, r0)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.setNeighborAlpha(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0013  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "neighborSpace")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNeighborSpace(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x000b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x000b }
            goto L_0x000d
        L_0x000b:
            r2 = 1103626240(0x41c80000, float:25.0)
        L_0x000d:
            float r0 = r1.mNeighborSpace
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0015
            r1.mNeighborSpace = r2
        L_0x0015:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.setNeighborSpace(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "currentItemScale")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCurrentItemScale(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x000b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r2 = 1063675494(0x3f666666, float:0.9)
        L_0x000e:
            float r0 = r1.mCurrentItemScale
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x001b
            r1.mCurrentItemScale = r2
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1.updateAdapterScaleAndAlpha(r2, r2)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.setCurrentItemScale(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r4, java.lang.Object r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -1763701364(0xffffffff96e00d8c, float:-3.61977E-25)
            r2 = 1
            if (r0 == r1) goto L_0x0038
            r1 = -1747360392(0xffffffff97d96578, float:-1.4048911E-24)
            if (r0 == r1) goto L_0x002e
            r1 = -1746973388(0xffffffff97df4d34, float:-1.4430526E-24)
            if (r0 == r1) goto L_0x0024
            r1 = -1013904258(0xffffffffc3910c7e, float:-290.0976)
            if (r0 == r1) goto L_0x001a
            goto L_0x0042
        L_0x001a:
            java.lang.String r0 = "currentItemScale"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0042
            r0 = 3
            goto L_0x0043
        L_0x0024:
            java.lang.String r0 = "neighborSpace"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0042
            r0 = 2
            goto L_0x0043
        L_0x002e:
            java.lang.String r0 = "neighborScale"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0042
            r0 = 0
            goto L_0x0043
        L_0x0038:
            java.lang.String r0 = "neighborAlpha"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = -1
        L_0x0043:
            r1 = 0
            switch(r0) {
                case 0: goto L_0x006a;
                case 1: goto L_0x0060;
                case 2: goto L_0x0056;
                case 3: goto L_0x004c;
                default: goto L_0x0047;
            }
        L_0x0047:
            boolean r4 = super.setProperty(r4, r5)
            return r4
        L_0x004c:
            java.lang.String r4 = com.taobao.weex.utils.WXUtils.getString(r5, r1)
            if (r4 == 0) goto L_0x0055
            r3.setCurrentItemScale(r4)
        L_0x0055:
            return r2
        L_0x0056:
            java.lang.String r4 = com.taobao.weex.utils.WXUtils.getString(r5, r1)
            if (r4 == 0) goto L_0x005f
            r3.setNeighborSpace(r4)
        L_0x005f:
            return r2
        L_0x0060:
            java.lang.String r4 = com.taobao.weex.utils.WXUtils.getString(r5, r1)
            if (r4 == 0) goto L_0x0069
            r3.setNeighborAlpha(r4)
        L_0x0069:
            return r2
        L_0x006a:
            java.lang.String r4 = com.taobao.weex.utils.WXUtils.getString(r5, r1)
            if (r4 == 0) goto L_0x0073
            r3.setNeighborScale(r4)
        L_0x0073:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    /* access modifiers changed from: private */
    public float calculateTranslation(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return 0.0f;
        }
        View childAt = ((ViewGroup) view).getChildAt(0);
        return ((((float) view.getMeasuredWidth()) - (((float) childAt.getMeasuredWidth()) * this.mNeighborScale)) / 4.0f) + ((((((float) view.getMeasuredWidth()) - (((float) childAt.getMeasuredWidth()) * this.mCurrentItemScale)) / 2.0f) - WXViewUtils.getRealPxByWidth(this.mNeighborSpace, getInstance().getInstanceViewPortWidth())) / 2.0f);
    }

    class ZoomTransformer implements ViewPager.PageTransformer {
        ZoomTransformer() {
        }

        public void transformPage(View view, float f) {
            View childAt;
            int pagePosition = WXSliderNeighbor.this.mAdapter.getPagePosition(view);
            int currentItem = WXSliderNeighbor.this.mViewPager.getCurrentItem();
            int realCount = WXSliderNeighbor.this.mAdapter.getRealCount();
            boolean z = (currentItem == 0 || currentItem == realCount + -1 || Math.abs(pagePosition - currentItem) <= 1) ? false : true;
            if (currentItem == 0 && pagePosition < realCount - 1 && pagePosition > 1) {
                z = true;
            }
            int i = realCount - 1;
            if (currentItem == i && pagePosition < realCount - 2 && pagePosition > 0) {
                z = true;
            }
            if (!z && (childAt = ((ViewGroup) view).getChildAt(0)) != null) {
                if (f <= ((float) ((-realCount) + 1))) {
                    f += (float) realCount;
                }
                if (f >= ((float) i)) {
                    f -= (float) realCount;
                }
                if (f >= -1.0f && f <= 1.0f) {
                    float abs = Math.abs(Math.abs(f) - 1.0f);
                    float access$100 = WXSliderNeighbor.this.mNeighborScale + ((WXSliderNeighbor.this.mCurrentItemScale - WXSliderNeighbor.this.mNeighborScale) * abs);
                    float access$300 = ((1.0f - WXSliderNeighbor.this.mNeighborAlpha) * abs) + WXSliderNeighbor.this.mNeighborAlpha;
                    float access$400 = WXSliderNeighbor.this.calculateTranslation(view);
                    if (f > 0.0f) {
                        float f2 = -(f * access$400);
                        childAt.setTranslationX(f2);
                        view.setTranslationX(f2);
                    } else if (f == 0.0f) {
                        view.setTranslationX(0.0f);
                        childAt.setTranslationX(0.0f);
                        WXSliderNeighbor.this.updateAdapterScaleAndAlpha(WXSliderNeighbor.this.mNeighborAlpha, WXSliderNeighbor.this.mNeighborScale);
                    } else if (realCount != 2 || Math.abs(f) != 1.0f) {
                        float f3 = (-f) * access$400;
                        childAt.setTranslationX(f3);
                        view.setTranslationX(f3);
                    } else {
                        return;
                    }
                    childAt.setScaleX(access$100);
                    childAt.setScaleY(access$100);
                    childAt.setAlpha(access$300);
                }
            }
        }
    }
}
