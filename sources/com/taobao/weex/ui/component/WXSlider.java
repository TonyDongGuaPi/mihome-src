package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXCircleIndicator;
import com.taobao.weex.ui.view.WXCirclePageAdapter;
import com.taobao.weex.ui.view.WXCircleViewPager;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXSlider extends WXVContainer<FrameLayout> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String INDEX = "index";
    public static final String INFINITE = "infinite";
    private int initIndex;
    private Runnable initRunnable;
    private boolean isInfinite;
    private boolean keepIndex;
    protected WXCirclePageAdapter mAdapter;
    protected WXIndicator mIndicator;
    protected ViewPager.OnPageChangeListener mPageChangeListener;
    protected boolean mShowIndicators;
    WXCircleViewPager mViewPager;
    private float offsetXAccuracy;
    Map<String, Object> params;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1955349320513317832L, "com/taobao/weex/ui/component/WXSlider", TbsListener.ErrorCode.COPY_INSTALL_SUCCESS);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ int access$000(WXSlider wXSlider) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = wXSlider.initIndex;
        $jacocoInit[216] = true;
        return i;
    }

    static /* synthetic */ int access$002(WXSlider wXSlider, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSlider.initIndex = i;
        $jacocoInit[214] = true;
        return i;
    }

    static /* synthetic */ int access$100(WXSlider wXSlider) {
        boolean[] $jacocoInit = $jacocoInit();
        int initIndex2 = wXSlider.getInitIndex();
        $jacocoInit[215] = true;
        return initIndex2;
    }

    static /* synthetic */ int access$200(WXSlider wXSlider, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int realIndex = wXSlider.getRealIndex(i);
        $jacocoInit[217] = true;
        return realIndex;
    }

    static /* synthetic */ Runnable access$302(WXSlider wXSlider, Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSlider.initRunnable = runnable;
        $jacocoInit[218] = true;
        return runnable;
    }

    static /* synthetic */ float access$400(WXSlider wXSlider) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = wXSlider.offsetXAccuracy;
        $jacocoInit[219] = true;
        return f;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(9098775107309377277L, "com/taobao/weex/ui/component/WXSlider$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXSlider wXSlider = new WXSlider(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXSlider;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXSlider(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSlider(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.isInfinite = true;
        $jacocoInit[1] = true;
        this.params = new HashMap();
        this.offsetXAccuracy = 0.1f;
        this.initIndex = -1;
        this.keepIndex = false;
        $jacocoInit[2] = true;
        this.mPageChangeListener = new SliderPageChangeListener(this);
        $jacocoInit[3] = true;
    }

    /* access modifiers changed from: protected */
    public FrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = new FrameLayout(context);
        $jacocoInit[4] = true;
        if (getAttrs() == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            Object obj = getAttrs().get("infinite");
            $jacocoInit[7] = true;
            this.isInfinite = WXUtils.getBoolean(obj, true).booleanValue();
            $jacocoInit[8] = true;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        $jacocoInit[9] = true;
        this.mViewPager = new WXCircleViewPager(context);
        $jacocoInit[10] = true;
        this.mViewPager.setCircle(this.isInfinite);
        $jacocoInit[11] = true;
        this.mViewPager.setLayoutParams(layoutParams);
        $jacocoInit[12] = true;
        this.mAdapter = new WXCirclePageAdapter(this.isInfinite);
        $jacocoInit[13] = true;
        this.mViewPager.setAdapter(this.mAdapter);
        $jacocoInit[14] = true;
        frameLayout.addView(this.mViewPager);
        $jacocoInit[15] = true;
        this.mViewPager.addOnPageChangeListener(this.mPageChangeListener);
        $jacocoInit[16] = true;
        registerActivityStateListener();
        $jacocoInit[17] = true;
        return frameLayout;
    }

    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent wXComponent, View view, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            $jacocoInit[18] = true;
            layoutParams = new FrameLayout.LayoutParams(i, i2);
            $jacocoInit[19] = true;
        } else {
            layoutParams.width = i7;
            layoutParams.height = i8;
            $jacocoInit[20] = true;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            $jacocoInit[21] = true;
        } else if (wXComponent instanceof WXIndicator) {
            $jacocoInit[22] = true;
            setMarginsSupportRTL((ViewGroup.MarginLayoutParams) layoutParams, i3, i5, i4, i6);
            $jacocoInit[23] = true;
        } else {
            setMarginsSupportRTL((ViewGroup.MarginLayoutParams) layoutParams, 0, 0, 0, 0);
            $jacocoInit[24] = true;
        }
        $jacocoInit[25] = true;
        return layoutParams;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[26] = true;
        if (!"scroll".equals(str)) {
            $jacocoInit[27] = true;
        } else if (this.mViewPager == null) {
            $jacocoInit[28] = true;
            return;
        } else {
            this.mViewPager.addOnPageChangeListener(new SliderOnScrollListener(this));
            $jacocoInit[29] = true;
        }
        $jacocoInit[30] = true;
    }

    public boolean containsGesture(WXGestureType wXGestureType) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsGesture = super.containsGesture(wXGestureType);
        $jacocoInit[31] = true;
        return containsGesture;
    }

    public ViewGroup getRealView() {
        boolean[] $jacocoInit = $jacocoInit();
        WXCircleViewPager wXCircleViewPager = this.mViewPager;
        $jacocoInit[32] = true;
        return wXCircleViewPager;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addSubView(android.view.View r5, int r6) {
        /*
            r4 = this;
            boolean[] r6 = $jacocoInit()
            r0 = 1
            if (r5 != 0) goto L_0x000c
            r5 = 33
            r6[r5] = r0
            goto L_0x0014
        L_0x000c:
            com.taobao.weex.ui.view.WXCirclePageAdapter r1 = r4.mAdapter
            if (r1 != 0) goto L_0x0019
            r5 = 34
            r6[r5] = r0
        L_0x0014:
            r5 = 35
            r6[r5] = r0
            return
        L_0x0019:
            boolean r1 = r5 instanceof com.taobao.weex.ui.view.WXCircleIndicator
            if (r1 == 0) goto L_0x0022
            r5 = 36
            r6[r5] = r0
            return
        L_0x0022:
            com.taobao.weex.ui.view.WXCirclePageAdapter r1 = r4.mAdapter
            r1.addPageView(r5)
            r5 = 37
            r6[r5] = r0
            r4.hackTwoItemsInfiniteScroll()
            r5 = 38
            r6[r5] = r0
            int r5 = r4.initIndex
            r1 = -1
            if (r5 != r1) goto L_0x003c
            r5 = 39
            r6[r5] = r0
            goto L_0x004a
        L_0x003c:
            com.taobao.weex.ui.view.WXCirclePageAdapter r5 = r4.mAdapter
            int r5 = r5.getRealCount()
            int r1 = r4.initIndex
            if (r5 > r1) goto L_0x0066
            r5 = 40
            r6[r5] = r0
        L_0x004a:
            boolean r5 = r4.keepIndex
            if (r5 == 0) goto L_0x0053
            r5 = 46
            r6[r5] = r0
            goto L_0x0096
        L_0x0053:
            r5 = 47
            r6[r5] = r0
            com.taobao.weex.ui.view.WXCircleViewPager r5 = r4.mViewPager
            r1 = 0
            int r1 = r4.getRealIndex(r1)
            r5.setCurrentItem(r1)
            r5 = 48
            r6[r5] = r0
            goto L_0x0096
        L_0x0066:
            java.lang.Runnable r5 = r4.initRunnable
            if (r5 == 0) goto L_0x006f
            r5 = 41
            r6[r5] = r0
            goto L_0x007e
        L_0x006f:
            r5 = 42
            r6[r5] = r0
            com.taobao.weex.ui.component.WXSlider$1 r5 = new com.taobao.weex.ui.component.WXSlider$1
            r5.<init>(r4)
            r4.initRunnable = r5
            r5 = 43
            r6[r5] = r0
        L_0x007e:
            com.taobao.weex.ui.view.WXCircleViewPager r5 = r4.mViewPager
            java.lang.Runnable r1 = r4.initRunnable
            r5.removeCallbacks(r1)
            r5 = 44
            r6[r5] = r0
            com.taobao.weex.ui.view.WXCircleViewPager r5 = r4.mViewPager
            java.lang.Runnable r1 = r4.initRunnable
            r2 = 50
            r5.postDelayed(r1, r2)
            r5 = 45
            r6[r5] = r0
        L_0x0096:
            com.taobao.weex.ui.component.WXIndicator r5 = r4.mIndicator
            if (r5 != 0) goto L_0x009f
            r5 = 49
            r6[r5] = r0
            goto L_0x00c1
        L_0x009f:
            r5 = 50
            r6[r5] = r0
            com.taobao.weex.ui.component.WXIndicator r5 = r4.mIndicator
            android.view.View r5 = r5.getHostView()
            com.taobao.weex.ui.view.WXCircleIndicator r5 = (com.taobao.weex.ui.view.WXCircleIndicator) r5
            r5.forceLayout()
            r5 = 51
            r6[r5] = r0
            com.taobao.weex.ui.component.WXIndicator r5 = r4.mIndicator
            android.view.View r5 = r5.getHostView()
            com.taobao.weex.ui.view.WXCircleIndicator r5 = (com.taobao.weex.ui.view.WXCircleIndicator) r5
            r5.requestLayout()
            r5 = 52
            r6[r5] = r0
        L_0x00c1:
            r5 = 53
            r6[r5] = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSlider.addSubView(android.view.View, int):void");
    }

    public void setLayout(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAdapter == null) {
            $jacocoInit[54] = true;
        } else {
            $jacocoInit[55] = true;
            this.mAdapter.setLayoutDirectionRTL(isLayoutRTL());
            $jacocoInit[56] = true;
        }
        super.setLayout(wXComponent);
        $jacocoInit[57] = true;
    }

    public void remove(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[58] = true;
        } else if (wXComponent.getHostView() == null) {
            $jacocoInit[59] = true;
        } else if (this.mAdapter == null) {
            $jacocoInit[60] = true;
        } else {
            this.mAdapter.removePageView(wXComponent.getHostView());
            $jacocoInit[62] = true;
            hackTwoItemsInfiniteScroll();
            $jacocoInit[63] = true;
            super.remove(wXComponent, z);
            $jacocoInit[64] = true;
            return;
        }
        $jacocoInit[61] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        if (this.mViewPager == null) {
            $jacocoInit[65] = true;
        } else {
            $jacocoInit[66] = true;
            this.mViewPager.stopAutoScroll();
            $jacocoInit[67] = true;
            this.mViewPager.removeAllViews();
            $jacocoInit[68] = true;
            this.mViewPager.destory();
            $jacocoInit[69] = true;
        }
        $jacocoInit[70] = true;
    }

    public void onActivityResume() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityResume();
        $jacocoInit[71] = true;
        if (this.mViewPager == null) {
            $jacocoInit[72] = true;
        } else if (!this.mViewPager.isAutoScroll()) {
            $jacocoInit[73] = true;
        } else {
            $jacocoInit[74] = true;
            this.mViewPager.startAutoScroll();
            $jacocoInit[75] = true;
        }
        $jacocoInit[76] = true;
    }

    public void onActivityStop() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityStop();
        if (this.mViewPager == null) {
            $jacocoInit[77] = true;
        } else {
            $jacocoInit[78] = true;
            this.mViewPager.pauseAutoScroll();
            $jacocoInit[79] = true;
        }
        $jacocoInit[80] = true;
    }

    public void addIndicator(WXIndicator wXIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = (FrameLayout) getHostView();
        if (frameLayout == null) {
            $jacocoInit[81] = true;
            return;
        }
        this.mIndicator = wXIndicator;
        $jacocoInit[82] = true;
        WXCircleIndicator wXCircleIndicator = (WXCircleIndicator) wXIndicator.getHostView();
        if (wXCircleIndicator == null) {
            $jacocoInit[83] = true;
        } else {
            $jacocoInit[84] = true;
            wXCircleIndicator.setCircleViewPager(this.mViewPager);
            $jacocoInit[85] = true;
            frameLayout.addView(wXCircleIndicator);
            $jacocoInit[86] = true;
        }
        $jacocoInit[87] = true;
    }

    private int getInitIndex() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = getAttrs().get("index");
        $jacocoInit[88] = true;
        int intValue = WXUtils.getInteger(obj, Integer.valueOf(this.initIndex)).intValue();
        $jacocoInit[89] = true;
        if (this.mAdapter == null) {
            $jacocoInit[90] = true;
        } else if (this.mAdapter.getCount() == 0) {
            $jacocoInit[91] = true;
        } else {
            if (intValue < this.mAdapter.getRealCount()) {
                $jacocoInit[93] = true;
            } else {
                $jacocoInit[94] = true;
                intValue %= this.mAdapter.getRealCount();
                $jacocoInit[95] = true;
            }
            $jacocoInit[96] = true;
            return intValue;
        }
        $jacocoInit[92] = true;
        return 0;
    }

    private int getRealIndex(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[97] = true;
        if (this.mAdapter.getRealCount() <= 0) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            if (i < this.mAdapter.getRealCount()) {
                $jacocoInit[100] = true;
            } else {
                i = this.mAdapter.getRealCount() - 1;
                $jacocoInit[101] = true;
            }
            if (!isLayoutRTL()) {
                $jacocoInit[102] = true;
            } else {
                $jacocoInit[103] = true;
                i = (this.mAdapter.getRealCount() - 1) - i;
                $jacocoInit[104] = true;
            }
        }
        int i2 = i + 0;
        $jacocoInit[105] = true;
        return i2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r6.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -1768064947: goto L_0x009d;
                case 66669991: goto L_0x008a;
                case 100346066: goto L_0x0077;
                case 111972721: goto L_0x0064;
                case 570418373: goto L_0x0051;
                case 996926241: goto L_0x003d;
                case 1438608771: goto L_0x0028;
                case 1565939262: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            r1 = 106(0x6a, float:1.49E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0013:
            java.lang.String r1 = "offsetXAccuracy"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 117(0x75, float:1.64E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0021:
            r1 = 5
            r4 = 118(0x76, float:1.65E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x0028:
            java.lang.String r1 = "autoPlay"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0036
            r1 = 109(0x6d, float:1.53E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0036:
            r1 = 110(0x6e, float:1.54E-43)
            r0[r1] = r3
            r1 = 1
            goto L_0x00b1
        L_0x003d:
            java.lang.String r1 = "showIndicators"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x004b
            r1 = 111(0x6f, float:1.56E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x004b:
            r1 = 2
            r4 = 112(0x70, float:1.57E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x0051:
            java.lang.String r1 = "interval"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x005e
            r1 = 113(0x71, float:1.58E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x005e:
            r1 = 3
            r4 = 114(0x72, float:1.6E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x0064:
            java.lang.String r1 = "value"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0071
            r1 = 107(0x6b, float:1.5E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0071:
            r1 = 108(0x6c, float:1.51E-43)
            r0[r1] = r3
            r1 = 0
            goto L_0x00b1
        L_0x0077:
            java.lang.String r1 = "index"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0084
            r1 = 115(0x73, float:1.61E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0084:
            r1 = 4
            r4 = 116(0x74, float:1.63E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x008a:
            java.lang.String r1 = "scrollable"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0097
            r1 = 119(0x77, float:1.67E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x0097:
            r1 = 6
            r4 = 120(0x78, float:1.68E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x009d:
            java.lang.String r1 = "keepIndex"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00aa
            r1 = 121(0x79, float:1.7E-43)
            r0[r1] = r3
            goto L_0x00b0
        L_0x00aa:
            r1 = 7
            r4 = 122(0x7a, float:1.71E-43)
            r0[r4] = r3
            goto L_0x00b1
        L_0x00b0:
            r1 = -1
        L_0x00b1:
            r4 = 0
            switch(r1) {
                case 0: goto L_0x018e;
                case 1: goto L_0x0173;
                case 2: goto L_0x0158;
                case 3: goto L_0x0139;
                case 4: goto L_0x011a;
                case 5: goto L_0x00e9;
                case 6: goto L_0x00d1;
                case 7: goto L_0x00be;
                default: goto L_0x00b5;
            }
        L_0x00b5:
            boolean r6 = super.setProperty(r6, r7)
            r7 = 151(0x97, float:2.12E-43)
            r0[r7] = r3
            return r6
        L_0x00be:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r5.keepIndex = r6
            r6 = 150(0x96, float:2.1E-43)
            r0[r6] = r3
            return r3
        L_0x00d1:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r7 = 148(0x94, float:2.07E-43)
            r0[r7] = r3
            r5.setScrollable(r6)
            r6 = 149(0x95, float:2.09E-43)
            r0[r6] = r3
            return r3
        L_0x00e9:
            r6 = 1036831949(0x3dcccccd, float:0.1)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            java.lang.Float r6 = com.taobao.weex.utils.WXUtils.getFloat(r7, r6)
            r7 = 143(0x8f, float:2.0E-43)
            r0[r7] = r3
            float r7 = r6.floatValue()
            r1 = 0
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x0106
            r6 = 144(0x90, float:2.02E-43)
            r0[r6] = r3
            goto L_0x0115
        L_0x0106:
            r7 = 145(0x91, float:2.03E-43)
            r0[r7] = r3
            float r6 = r6.floatValue()
            r5.setOffsetXAccuracy(r6)
            r6 = 146(0x92, float:2.05E-43)
            r0[r6] = r3
        L_0x0115:
            r6 = 147(0x93, float:2.06E-43)
            r0[r6] = r3
            return r3
        L_0x011a:
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r4)
            if (r6 != 0) goto L_0x0125
            r6 = 139(0x8b, float:1.95E-43)
            r0[r6] = r3
            goto L_0x0134
        L_0x0125:
            r7 = 140(0x8c, float:1.96E-43)
            r0[r7] = r3
            int r6 = r6.intValue()
            r5.setIndex(r6)
            r6 = 141(0x8d, float:1.98E-43)
            r0[r6] = r3
        L_0x0134:
            r6 = 142(0x8e, float:1.99E-43)
            r0[r6] = r3
            return r3
        L_0x0139:
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r4)
            if (r6 != 0) goto L_0x0144
            r6 = 135(0x87, float:1.89E-43)
            r0[r6] = r3
            goto L_0x0153
        L_0x0144:
            r7 = 136(0x88, float:1.9E-43)
            r0[r7] = r3
            int r6 = r6.intValue()
            r5.setInterval(r6)
            r6 = 137(0x89, float:1.92E-43)
            r0[r6] = r3
        L_0x0153:
            r6 = 138(0x8a, float:1.93E-43)
            r0[r6] = r3
            return r3
        L_0x0158:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x0163
            r6 = 131(0x83, float:1.84E-43)
            r0[r6] = r3
            goto L_0x016e
        L_0x0163:
            r7 = 132(0x84, float:1.85E-43)
            r0[r7] = r3
            r5.setShowIndicators(r6)
            r6 = 133(0x85, float:1.86E-43)
            r0[r6] = r3
        L_0x016e:
            r6 = 134(0x86, float:1.88E-43)
            r0[r6] = r3
            return r3
        L_0x0173:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x017e
            r6 = 127(0x7f, float:1.78E-43)
            r0[r6] = r3
            goto L_0x0189
        L_0x017e:
            r7 = 128(0x80, float:1.794E-43)
            r0[r7] = r3
            r5.setAutoPlay(r6)
            r6 = 129(0x81, float:1.81E-43)
            r0[r6] = r3
        L_0x0189:
            r6 = 130(0x82, float:1.82E-43)
            r0[r6] = r3
            return r3
        L_0x018e:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x0199
            r6 = 123(0x7b, float:1.72E-43)
            r0[r6] = r3
            goto L_0x01a4
        L_0x0199:
            r7 = 124(0x7c, float:1.74E-43)
            r0[r7] = r3
            r5.setValue(r6)
            r6 = 125(0x7d, float:1.75E-43)
            r0[r6] = r3
        L_0x01a4:
            r6 = 126(0x7e, float:1.77E-43)
            r0[r6] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSlider.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "value")
    @Deprecated
    public void setValue(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[152] = true;
        } else if (getHostView() != null) {
            $jacocoInit[153] = true;
            try {
                setIndex(Integer.parseInt(str));
                $jacocoInit[158] = true;
                return;
            } catch (NumberFormatException e) {
                $jacocoInit[156] = true;
                WXLogUtils.e("", (Throwable) e);
                $jacocoInit[157] = true;
                return;
            }
        } else {
            $jacocoInit[154] = true;
        }
        $jacocoInit[155] = true;
    }

    @WXComponentProp(name = "autoPlay")
    public void setAutoPlay(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[159] = true;
        } else if (str.equals("false")) {
            $jacocoInit[160] = true;
        } else {
            this.mViewPager.stopAutoScroll();
            $jacocoInit[162] = true;
            this.mViewPager.startAutoScroll();
            $jacocoInit[163] = true;
            $jacocoInit[164] = true;
        }
        this.mViewPager.stopAutoScroll();
        $jacocoInit[161] = true;
        $jacocoInit[164] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "showIndicators")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setShowIndicators(java.lang.String r4) {
        /*
            r3 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            r2 = 1
            if (r1 == 0) goto L_0x0010
            r4 = 165(0xa5, float:2.31E-43)
            r0[r4] = r2
            goto L_0x001c
        L_0x0010:
            java.lang.String r1 = "false"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0024
            r4 = 166(0xa6, float:2.33E-43)
            r0[r4] = r2
        L_0x001c:
            r4 = 0
            r3.mShowIndicators = r4
            r4 = 167(0xa7, float:2.34E-43)
            r0[r4] = r2
            goto L_0x002a
        L_0x0024:
            r3.mShowIndicators = r2
            r4 = 168(0xa8, float:2.35E-43)
            r0[r4] = r2
        L_0x002a:
            com.taobao.weex.ui.component.WXIndicator r4 = r3.mIndicator
            if (r4 != 0) goto L_0x0033
            r4 = 169(0xa9, float:2.37E-43)
            r0[r4] = r2
            return
        L_0x0033:
            com.taobao.weex.ui.component.WXIndicator r4 = r3.mIndicator
            boolean r1 = r3.mShowIndicators
            r4.setShowIndicators(r1)
            r4 = 170(0xaa, float:2.38E-43)
            r0[r4] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSlider.setShowIndicators(java.lang.String):void");
    }

    @WXComponentProp(name = "interval")
    public void setInterval(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mViewPager == null) {
            $jacocoInit[171] = true;
        } else if (i <= 0) {
            $jacocoInit[172] = true;
        } else {
            $jacocoInit[173] = true;
            this.mViewPager.setIntervalTime((long) i);
            $jacocoInit[174] = true;
        }
        $jacocoInit[175] = true;
    }

    @WXComponentProp(name = "index")
    public void setIndex(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mViewPager == null) {
            $jacocoInit[176] = true;
        } else if (this.mAdapter == null) {
            $jacocoInit[177] = true;
        } else {
            $jacocoInit[178] = true;
            if (i >= this.mAdapter.getRealCount()) {
                $jacocoInit[179] = true;
            } else if (i < 0) {
                $jacocoInit[180] = true;
            } else {
                int realIndex = getRealIndex(i);
                $jacocoInit[182] = true;
                this.mViewPager.setCurrentItem(realIndex);
                $jacocoInit[183] = true;
                if (this.mIndicator == null) {
                    $jacocoInit[184] = true;
                } else if (this.mIndicator.getHostView() == null) {
                    $jacocoInit[185] = true;
                } else {
                    WXIndicator wXIndicator = this.mIndicator;
                    $jacocoInit[186] = true;
                    if (((WXCircleIndicator) wXIndicator.getHostView()).getRealCurrentItem() == realIndex) {
                        $jacocoInit[187] = true;
                    } else {
                        $jacocoInit[188] = true;
                        WXLogUtils.d("setIndex >>>> correction indicator to " + realIndex);
                        $jacocoInit[189] = true;
                        ((WXCircleIndicator) this.mIndicator.getHostView()).setRealCurrentItem(realIndex);
                        $jacocoInit[190] = true;
                        ((WXCircleIndicator) this.mIndicator.getHostView()).invalidate();
                        if (this.mPageChangeListener == null) {
                            $jacocoInit[191] = true;
                        } else if (this.mAdapter == null) {
                            $jacocoInit[192] = true;
                        } else {
                            $jacocoInit[193] = true;
                            this.mPageChangeListener.onPageSelected(this.mAdapter.getFirst() + realIndex);
                            $jacocoInit[194] = true;
                        }
                    }
                }
            }
            this.initIndex = i;
            $jacocoInit[181] = true;
            return;
        }
        $jacocoInit[195] = true;
    }

    @WXComponentProp(name = "scrollable")
    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mViewPager == null) {
            $jacocoInit[196] = true;
        } else if (this.mAdapter == null) {
            $jacocoInit[197] = true;
        } else {
            $jacocoInit[198] = true;
            this.mViewPager.setScrollable(z);
            $jacocoInit[199] = true;
        }
        $jacocoInit[200] = true;
    }

    @WXComponentProp(name = "offsetXAccuracy")
    public void setOffsetXAccuracy(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.offsetXAccuracy = f;
        $jacocoInit[201] = true;
    }

    protected class SliderPageChangeListener implements ViewPager.OnPageChangeListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private int lastPos = -1;
        final /* synthetic */ WXSlider this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(7584833650102221727L, "com/taobao/weex/ui/component/WXSlider$SliderPageChangeListener", 32);
            $jacocoData = a2;
            return a2;
        }

        protected SliderPageChangeListener(WXSlider wXSlider) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXSlider;
            $jacocoInit[0] = true;
        }

        public void onPageScrolled(int i, float f, int i2) {
            $jacocoInit()[1] = true;
        }

        public void onPageSelected(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.this$0.mAdapter.getRealPosition(i) == this.lastPos) {
                $jacocoInit[2] = true;
                return;
            }
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                WXLogUtils.d("onPageSelected >>>>" + this.this$0.mAdapter.getRealPosition(i) + " lastPos: " + this.lastPos);
                $jacocoInit[5] = true;
            }
            if (this.this$0.mAdapter == null) {
                $jacocoInit[6] = true;
            } else if (this.this$0.mAdapter.getRealCount() == 0) {
                $jacocoInit[7] = true;
            } else {
                int realPosition = this.this$0.mAdapter.getRealPosition(i);
                $jacocoInit[9] = true;
                if (this.this$0.mChildren == null) {
                    $jacocoInit[10] = true;
                } else if (realPosition >= this.this$0.mChildren.size()) {
                    $jacocoInit[11] = true;
                } else if (this.this$0.getEvents().size() == 0) {
                    $jacocoInit[13] = true;
                    return;
                } else {
                    WXEvent events = this.this$0.getEvents();
                    $jacocoInit[14] = true;
                    String ref = this.this$0.getRef();
                    $jacocoInit[15] = true;
                    if (!events.contains(Constants.Event.CHANGE)) {
                        $jacocoInit[16] = true;
                    } else if (!WXViewUtils.onScreenArea(this.this$0.getHostView())) {
                        $jacocoInit[17] = true;
                    } else {
                        $jacocoInit[18] = true;
                        this.this$0.params.put("index", Integer.valueOf(realPosition));
                        $jacocoInit[19] = true;
                        HashMap hashMap = new HashMap();
                        $jacocoInit[20] = true;
                        HashMap hashMap2 = new HashMap();
                        $jacocoInit[21] = true;
                        hashMap2.put("index", Integer.valueOf(realPosition));
                        $jacocoInit[22] = true;
                        hashMap.put("attrs", hashMap2);
                        $jacocoInit[23] = true;
                        WXSDKManager.getInstance().fireEvent(this.this$0.getInstanceId(), ref, Constants.Event.CHANGE, this.this$0.params, hashMap);
                        $jacocoInit[24] = true;
                    }
                    this.this$0.mViewPager.requestLayout();
                    $jacocoInit[25] = true;
                    ((FrameLayout) this.this$0.getHostView()).invalidate();
                    $jacocoInit[26] = true;
                    this.lastPos = this.this$0.mAdapter.getRealPosition(i);
                    $jacocoInit[27] = true;
                    return;
                }
                $jacocoInit[12] = true;
                return;
            }
            $jacocoInit[8] = true;
        }

        public void onPageScrollStateChanged(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            FrameLayout frameLayout = (FrameLayout) this.this$0.getHostView();
            if (frameLayout == null) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
                frameLayout.invalidate();
                $jacocoInit[30] = true;
            }
            $jacocoInit[31] = true;
        }
    }

    protected static class SliderOnScrollListener implements ViewPager.OnPageChangeListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private float lastPositionOffset = 99.0f;
        private int selectedPosition;
        private WXSlider target;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(7497370115800707361L, "com/taobao/weex/ui/component/WXSlider$SliderOnScrollListener", 22);
            $jacocoData = a2;
            return a2;
        }

        public SliderOnScrollListener(WXSlider wXSlider) {
            boolean[] $jacocoInit = $jacocoInit();
            this.target = wXSlider;
            $jacocoInit[0] = true;
            this.selectedPosition = wXSlider.mViewPager.superGetCurrentItem();
            $jacocoInit[1] = true;
        }

        public void onPageScrolled(int i, float f, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.lastPositionOffset == 99.0f) {
                this.lastPositionOffset = f;
                $jacocoInit[2] = true;
                return;
            }
            $jacocoInit[3] = true;
            if (Math.abs(f - this.lastPositionOffset) < WXSlider.access$400(this.target)) {
                $jacocoInit[4] = true;
            } else {
                if (i == this.selectedPosition) {
                    $jacocoInit[5] = true;
                    HashMap hashMap = new HashMap(1);
                    $jacocoInit[6] = true;
                    hashMap.put(Constants.Name.OFFSET_X_RATIO, Float.valueOf(-f));
                    $jacocoInit[7] = true;
                    this.target.fireEvent("scroll", hashMap);
                    $jacocoInit[8] = true;
                } else if (i >= this.selectedPosition) {
                    $jacocoInit[9] = true;
                } else {
                    $jacocoInit[10] = true;
                    HashMap hashMap2 = new HashMap(1);
                    $jacocoInit[11] = true;
                    hashMap2.put(Constants.Name.OFFSET_X_RATIO, Float.valueOf(1.0f - f));
                    $jacocoInit[12] = true;
                    this.target.fireEvent("scroll", hashMap2);
                    $jacocoInit[13] = true;
                }
                this.lastPositionOffset = f;
                $jacocoInit[14] = true;
            }
            $jacocoInit[15] = true;
        }

        public void onPageSelected(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.selectedPosition = i;
            $jacocoInit[16] = true;
        }

        public void onPageScrollStateChanged(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            switch (i) {
                case 0:
                    this.lastPositionOffset = 99.0f;
                    $jacocoInit[18] = true;
                    this.target.fireEvent(Constants.Event.SCROLL_END);
                    $jacocoInit[19] = true;
                    break;
                case 1:
                    this.target.fireEvent(Constants.Event.SCROLL_START);
                    $jacocoInit[20] = true;
                    break;
                default:
                    $jacocoInit[17] = true;
                    break;
            }
            $jacocoInit[21] = true;
        }
    }

    private void hackTwoItemsInfiniteScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mViewPager == null) {
            $jacocoInit[202] = true;
        } else if (this.mAdapter == null) {
            $jacocoInit[203] = true;
        } else {
            if (!this.isInfinite) {
                $jacocoInit[205] = true;
            } else {
                $jacocoInit[206] = true;
                if (this.mAdapter.getRealCount() == 2) {
                    $jacocoInit[207] = true;
                    final GestureDetector gestureDetector = new GestureDetector(getContext(), new FlingGestureListener(this.mViewPager));
                    $jacocoInit[208] = true;
                    this.mViewPager.setOnTouchListener(new View.OnTouchListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ WXSlider this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(5677263961637722493L, "com/taobao/weex/ui/component/WXSlider$2", 2);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r2;
                            $jacocoInit[0] = true;
                        }

                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            boolean[] $jacocoInit = $jacocoInit();
                            boolean onTouchEvent = gestureDetector.onTouchEvent(motionEvent);
                            $jacocoInit[1] = true;
                            return onTouchEvent;
                        }
                    });
                    $jacocoInit[209] = true;
                } else {
                    this.mViewPager.setOnTouchListener((View.OnTouchListener) null);
                    $jacocoInit[210] = true;
                }
            }
            $jacocoInit[211] = true;
            return;
        }
        $jacocoInit[204] = true;
    }

    private static class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private static final int SWIPE_MAX_OFF_PATH = WXViewUtils.dip2px(250.0f);
        private static final int SWIPE_MIN_DISTANCE = WXViewUtils.dip2px(50.0f);
        private static final int SWIPE_THRESHOLD_VELOCITY = WXViewUtils.dip2px(200.0f);
        private WeakReference<WXCircleViewPager> pagerRef;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-8073737579089910239L, "com/taobao/weex/ui/component/WXSlider$FlingGestureListener", 26);
            $jacocoData = a2;
            return a2;
        }

        static {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[23] = true;
            $jacocoInit[24] = true;
            $jacocoInit[25] = true;
        }

        FlingGestureListener(WXCircleViewPager wXCircleViewPager) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.pagerRef = new WeakReference<>(wXCircleViewPager);
            $jacocoInit[1] = true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            boolean[] $jacocoInit = $jacocoInit();
            WXCircleViewPager wXCircleViewPager = (WXCircleViewPager) this.pagerRef.get();
            if (wXCircleViewPager != null) {
                $jacocoInit[2] = true;
                try {
                    if (Math.abs(motionEvent.getY() - motionEvent2.getY()) <= ((float) SWIPE_MAX_OFF_PATH)) {
                        $jacocoInit[4] = true;
                        if (motionEvent.getX() - motionEvent2.getX() <= ((float) SWIPE_MIN_DISTANCE)) {
                            $jacocoInit[6] = true;
                        } else {
                            $jacocoInit[7] = true;
                            if (Math.abs(f) <= ((float) SWIPE_THRESHOLD_VELOCITY)) {
                                $jacocoInit[8] = true;
                            } else {
                                $jacocoInit[9] = true;
                                if (wXCircleViewPager.superGetCurrentItem() != 1) {
                                    $jacocoInit[10] = true;
                                } else {
                                    $jacocoInit[11] = true;
                                    wXCircleViewPager.setCurrentItem(0, false);
                                    $jacocoInit[12] = true;
                                    return true;
                                }
                            }
                        }
                        if (motionEvent2.getX() - motionEvent.getX() <= ((float) SWIPE_MIN_DISTANCE)) {
                            $jacocoInit[13] = true;
                        } else {
                            $jacocoInit[14] = true;
                            if (Math.abs(f) <= ((float) SWIPE_THRESHOLD_VELOCITY)) {
                                $jacocoInit[15] = true;
                            } else {
                                $jacocoInit[16] = true;
                                if (wXCircleViewPager.superGetCurrentItem() != 0) {
                                    $jacocoInit[17] = true;
                                } else {
                                    $jacocoInit[18] = true;
                                    wXCircleViewPager.setCurrentItem(1, false);
                                    $jacocoInit[19] = true;
                                    return true;
                                }
                            }
                        }
                        $jacocoInit[20] = true;
                        $jacocoInit[22] = true;
                        return false;
                    }
                    $jacocoInit[5] = true;
                    return false;
                } catch (Exception unused) {
                    $jacocoInit[21] = true;
                }
            } else {
                $jacocoInit[3] = true;
                return false;
            }
        }
    }
}
