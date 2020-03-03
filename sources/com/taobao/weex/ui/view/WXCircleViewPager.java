package com.taobao.weex.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.Field;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@SuppressLint({"HandlerLeak"})
public class WXCircleViewPager extends ViewPager implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final int SCROLL_TO_NEXT = 1;
    private long intervalTime = 3000;
    private boolean isAutoScroll;
    private Handler mAutoScrollHandler;
    private WXSmoothScroller mScroller;
    private int mState = 0;
    private boolean needLoop = true;
    private boolean scrollable = true;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-281164900203493812L, "com/taobao/weex/ui/view/WXCircleViewPager", 114);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(WXCircleViewPager wXCircleViewPager) {
        boolean[] $jacocoInit = $jacocoInit();
        wXCircleViewPager.showNextItem();
        $jacocoInit[108] = true;
    }

    static /* synthetic */ long access$100(WXCircleViewPager wXCircleViewPager) {
        boolean[] $jacocoInit = $jacocoInit();
        long j = wXCircleViewPager.intervalTime;
        $jacocoInit[109] = true;
        return j;
    }

    static /* synthetic */ int access$202(WXCircleViewPager wXCircleViewPager, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXCircleViewPager.mState = i;
        $jacocoInit[110] = true;
        return i;
    }

    static /* synthetic */ int access$301(WXCircleViewPager wXCircleViewPager) {
        boolean[] $jacocoInit = $jacocoInit();
        int currentItem = super.getCurrentItem();
        $jacocoInit[111] = true;
        return currentItem;
    }

    static /* synthetic */ boolean access$400(WXCircleViewPager wXCircleViewPager) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXCircleViewPager.needLoop;
        $jacocoInit[112] = true;
        return z;
    }

    static /* synthetic */ void access$500(WXCircleViewPager wXCircleViewPager, int i, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXCircleViewPager.superSetCurrentItem(i, z);
        $jacocoInit[113] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @SuppressLint({"NewApi"})
    public WXCircleViewPager(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mAutoScrollHandler = new Handler(this, Looper.getMainLooper()) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXCircleViewPager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5245752916956836875L, "com/taobao/weex/ui/view/WXCircleViewPager$1", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void handleMessage(Message message) {
                boolean[] $jacocoInit = $jacocoInit();
                if (message.what == 1) {
                    $jacocoInit[1] = true;
                    WXLogUtils.d("[CircleViewPager] trigger auto play action");
                    $jacocoInit[2] = true;
                    WXCircleViewPager.access$000(this.this$0);
                    $jacocoInit[3] = true;
                    sendEmptyMessageDelayed(1, WXCircleViewPager.access$100(this.this$0));
                    $jacocoInit[4] = true;
                    return;
                }
                super.handleMessage(message);
                $jacocoInit[5] = true;
            }
        };
        $jacocoInit[1] = true;
        init();
        $jacocoInit[2] = true;
    }

    private void init() {
        boolean[] $jacocoInit = $jacocoInit();
        setOverScrollMode(2);
        $jacocoInit[3] = true;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXCircleViewPager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6243270000986287406L, "com/taobao/weex/ui/view/WXCircleViewPager$2", 16);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onPageScrolled(int i, float f, int i2) {
                $jacocoInit()[1] = true;
            }

            public void onPageSelected(int i) {
                $jacocoInit()[2] = true;
            }

            public void onPageScrollStateChanged(int i) {
                boolean[] $jacocoInit = $jacocoInit();
                WXCircleViewPager.access$202(this.this$0, i);
                $jacocoInit[3] = true;
                WXCirclePageAdapter circlePageAdapter = this.this$0.getCirclePageAdapter();
                $jacocoInit[4] = true;
                int access$301 = WXCircleViewPager.access$301(this.this$0);
                $jacocoInit[5] = true;
                if (!WXCircleViewPager.access$400(this.this$0)) {
                    $jacocoInit[6] = true;
                } else if (i != 0) {
                    $jacocoInit[7] = true;
                } else if (circlePageAdapter.getCount() <= 1) {
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[9] = true;
                    if (access$301 == circlePageAdapter.getCount() - 1) {
                        $jacocoInit[10] = true;
                        WXCircleViewPager.access$500(this.this$0, 1, false);
                        $jacocoInit[11] = true;
                    } else if (access$301 != 0) {
                        $jacocoInit[12] = true;
                    } else {
                        $jacocoInit[13] = true;
                        WXCircleViewPager.access$500(this.this$0, circlePageAdapter.getCount() - 2, false);
                        $jacocoInit[14] = true;
                    }
                }
                $jacocoInit[15] = true;
            }
        });
        $jacocoInit[4] = true;
        postInitViewPager();
        $jacocoInit[5] = true;
    }

    private void postInitViewPager() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isInEditMode()) {
            $jacocoInit[6] = true;
            try {
                Field declaredField = ViewPager.class.getDeclaredField("mScroller");
                $jacocoInit[8] = true;
                declaredField.setAccessible(true);
                $jacocoInit[9] = true;
                Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
                $jacocoInit[10] = true;
                declaredField2.setAccessible(true);
                $jacocoInit[11] = true;
                Context context = getContext();
                $jacocoInit[12] = true;
                this.mScroller = new WXSmoothScroller(context, (Interpolator) declaredField2.get((Object) null));
                $jacocoInit[13] = true;
                declaredField.set(this, this.mScroller);
                $jacocoInit[14] = true;
            } catch (Exception e) {
                $jacocoInit[15] = true;
                WXLogUtils.e("[CircleViewPager] postInitViewPager: ", (Throwable) e);
                $jacocoInit[16] = true;
            }
            $jacocoInit[17] = true;
            return;
        }
        $jacocoInit[7] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @SuppressLint({"NewApi"})
    public WXCircleViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[18] = true;
        this.mAutoScrollHandler = new Handler(this, Looper.getMainLooper()) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXCircleViewPager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5245752916956836875L, "com/taobao/weex/ui/view/WXCircleViewPager$1", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void handleMessage(Message message) {
                boolean[] $jacocoInit = $jacocoInit();
                if (message.what == 1) {
                    $jacocoInit[1] = true;
                    WXLogUtils.d("[CircleViewPager] trigger auto play action");
                    $jacocoInit[2] = true;
                    WXCircleViewPager.access$000(this.this$0);
                    $jacocoInit[3] = true;
                    sendEmptyMessageDelayed(1, WXCircleViewPager.access$100(this.this$0));
                    $jacocoInit[4] = true;
                    return;
                }
                super.handleMessage(message);
                $jacocoInit[5] = true;
            }
        };
        $jacocoInit[19] = true;
        init();
        $jacocoInit[20] = true;
    }

    public int getCurrentItem() {
        boolean[] $jacocoInit = $jacocoInit();
        int realCurrentItem = getRealCurrentItem();
        $jacocoInit[21] = true;
        return realCurrentItem;
    }

    public int superGetCurrentItem() {
        boolean[] $jacocoInit = $jacocoInit();
        int currentItem = super.getCurrentItem();
        $jacocoInit[22] = true;
        return currentItem;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        try {
            if (!this.scrollable) {
                $jacocoInit[23] = true;
            } else if (!super.onInterceptTouchEvent(motionEvent)) {
                $jacocoInit[24] = true;
            } else {
                $jacocoInit[25] = true;
                z = true;
                $jacocoInit[27] = true;
                return z;
            }
            $jacocoInit[26] = true;
            $jacocoInit[27] = true;
            return z;
        } catch (IllegalArgumentException e) {
            $jacocoInit[28] = true;
            e.printStackTrace();
            $jacocoInit[29] = true;
            $jacocoInit[32] = true;
            return false;
        } catch (ArrayIndexOutOfBoundsException e2) {
            $jacocoInit[30] = true;
            e2.printStackTrace();
            $jacocoInit[31] = true;
            $jacocoInit[32] = true;
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.scrollable) {
            $jacocoInit[33] = true;
            return true;
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        $jacocoInit[34] = true;
        return onTouchEvent;
    }

    public void scrollTo(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.scrollable) {
            $jacocoInit[35] = true;
        } else if (this.mState == 1) {
            $jacocoInit[36] = true;
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[37] = true;
        }
        super.scrollTo(i, i2);
        $jacocoInit[38] = true;
        $jacocoInit[39] = true;
    }

    public void startAutoScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        this.isAutoScroll = true;
        $jacocoInit[40] = true;
        this.mAutoScrollHandler.removeCallbacksAndMessages((Object) null);
        $jacocoInit[41] = true;
        this.mAutoScrollHandler.sendEmptyMessageDelayed(1, this.intervalTime);
        $jacocoInit[42] = true;
    }

    public void pauseAutoScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAutoScrollHandler.removeCallbacksAndMessages((Object) null);
        $jacocoInit[43] = true;
    }

    public void stopAutoScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        this.isAutoScroll = false;
        $jacocoInit[44] = true;
        this.mAutoScrollHandler.removeCallbacksAndMessages((Object) null);
        $jacocoInit[45] = true;
    }

    public boolean isAutoScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isAutoScroll;
        $jacocoInit[46] = true;
        return z;
    }

    public void setCurrentItem(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        setRealCurrentItem(i);
        $jacocoInit[47] = true;
    }

    public WXCirclePageAdapter getCirclePageAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        WXCirclePageAdapter wXCirclePageAdapter = (WXCirclePageAdapter) getAdapter();
        $jacocoInit[48] = true;
        return wXCirclePageAdapter;
    }

    public void setCirclePageAdapter(WXCirclePageAdapter wXCirclePageAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        setAdapter(wXCirclePageAdapter);
        $jacocoInit[49] = true;
    }

    public long getIntervalTime() {
        boolean[] $jacocoInit = $jacocoInit();
        long j = this.intervalTime;
        $jacocoInit[50] = true;
        return j;
    }

    public void setIntervalTime(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.intervalTime = j;
        $jacocoInit[51] = true;
    }

    public void setCircle(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.needLoop = z;
        $jacocoInit[52] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r6.getAction()
            r2 = 1
            switch(r1) {
                case 0: goto L_0x002c;
                case 1: goto L_0x0011;
                case 2: goto L_0x002c;
                case 3: goto L_0x0011;
                default: goto L_0x000c;
            }
        L_0x000c:
            r1 = 53
            r0[r1] = r2
            goto L_0x0036
        L_0x0011:
            boolean r1 = r5.isAutoScroll()
            if (r1 != 0) goto L_0x001c
            r1 = 55
            r0[r1] = r2
            goto L_0x0036
        L_0x001c:
            r1 = 56
            r0[r1] = r2
            android.os.Handler r1 = r5.mAutoScrollHandler
            long r3 = r5.intervalTime
            r1.sendEmptyMessageDelayed(r2, r3)
            r1 = 57
            r0[r1] = r2     // Catch:{ Exception -> 0x0057 }
            goto L_0x0036
        L_0x002c:
            android.os.Handler r1 = r5.mAutoScrollHandler
            r3 = 0
            r1.removeCallbacksAndMessages(r3)
            r1 = 54
            r0[r1] = r2
        L_0x0036:
            boolean r1 = super.dispatchTouchEvent(r6)     // Catch:{ Exception -> 0x0057 }
            com.taobao.weex.ui.view.gesture.WXGesture r3 = r5.wxGesture     // Catch:{ Exception -> 0x0057 }
            if (r3 != 0) goto L_0x0043
            r6 = 58
            r0[r6] = r2     // Catch:{ Exception -> 0x0057 }
            goto L_0x0052
        L_0x0043:
            r3 = 59
            r0[r3] = r2     // Catch:{ Exception -> 0x0057 }
            com.taobao.weex.ui.view.gesture.WXGesture r3 = r5.wxGesture     // Catch:{ Exception -> 0x0057 }
            boolean r6 = r3.onTouch(r5, r6)     // Catch:{ Exception -> 0x0057 }
            r1 = r1 | r6
            r6 = 60
            r0[r6] = r2     // Catch:{ Exception -> 0x0057 }
        L_0x0052:
            r6 = 61
            r0[r6] = r2
            return r1
        L_0x0057:
            r6 = 0
            r1 = 62
            r0[r1] = r2
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.WXCircleViewPager.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void destory() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAutoScrollHandler.removeCallbacksAndMessages((Object) null);
        $jacocoInit[63] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[64] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[65] = true;
        return wXGesture;
    }

    public int getRealCurrentItem() {
        boolean[] $jacocoInit = $jacocoInit();
        int currentItem = super.getCurrentItem();
        $jacocoInit[66] = true;
        int realPosition = ((WXCirclePageAdapter) getAdapter()).getRealPosition(currentItem);
        $jacocoInit[67] = true;
        return realPosition;
    }

    private void setRealCurrentItem(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        superSetCurrentItem(((WXCirclePageAdapter) getAdapter()).getFirst() + i, false);
        $jacocoInit[68] = true;
    }

    private void superSetCurrentItem(int i, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            super.setCurrentItem(i, z);
            $jacocoInit[69] = true;
        } catch (IllegalStateException e) {
            $jacocoInit[70] = true;
            WXLogUtils.e(e.toString());
            $jacocoInit[71] = true;
            if (getAdapter() == null) {
                $jacocoInit[72] = true;
            } else {
                $jacocoInit[73] = true;
                getAdapter().notifyDataSetChanged();
                $jacocoInit[74] = true;
                super.setCurrentItem(i, z);
                $jacocoInit[75] = true;
            }
        }
        $jacocoInit[76] = true;
    }

    public int getRealCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int realCount = ((WXCirclePageAdapter) getAdapter()).getRealCount();
        $jacocoInit[77] = true;
        return realCount;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            super.onMeasure(i, i2);
            $jacocoInit[78] = true;
        } catch (IllegalStateException e) {
            $jacocoInit[79] = true;
            WXLogUtils.e(e.toString());
            $jacocoInit[80] = true;
            if (getAdapter() == null) {
                $jacocoInit[81] = true;
            } else {
                $jacocoInit[82] = true;
                getAdapter().notifyDataSetChanged();
                $jacocoInit[83] = true;
                super.onMeasure(i, i2);
                $jacocoInit[84] = true;
            }
        }
        $jacocoInit[85] = true;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.scrollable;
        $jacocoInit[86] = true;
        return z;
    }

    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.scrollable = z;
        $jacocoInit[87] = true;
    }

    private void showNextItem() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getCirclePageAdapter() == null) {
            $jacocoInit[88] = true;
        } else if (!getCirclePageAdapter().isRTL) {
            $jacocoInit[89] = true;
        } else {
            $jacocoInit[90] = true;
            if (this.needLoop) {
                $jacocoInit[91] = true;
            } else if (superGetCurrentItem() != 0) {
                $jacocoInit[92] = true;
            } else {
                $jacocoInit[93] = true;
                return;
            }
            if (getRealCount() != 2) {
                $jacocoInit[94] = true;
            } else if (superGetCurrentItem() != 0) {
                $jacocoInit[95] = true;
            } else {
                $jacocoInit[96] = true;
                superSetCurrentItem(1, true);
                $jacocoInit[97] = true;
                $jacocoInit[107] = true;
            }
            superSetCurrentItem(superGetCurrentItem() - 1, true);
            $jacocoInit[98] = true;
            $jacocoInit[107] = true;
        }
        if (this.needLoop) {
            $jacocoInit[99] = true;
        } else if (superGetCurrentItem() != getRealCount() - 1) {
            $jacocoInit[100] = true;
        } else {
            $jacocoInit[101] = true;
            return;
        }
        if (getRealCount() != 2) {
            $jacocoInit[102] = true;
        } else if (superGetCurrentItem() != 1) {
            $jacocoInit[103] = true;
        } else {
            $jacocoInit[104] = true;
            superSetCurrentItem(0, true);
            $jacocoInit[105] = true;
            $jacocoInit[107] = true;
        }
        superSetCurrentItem(superGetCurrentItem() + 1, true);
        $jacocoInit[106] = true;
        $jacocoInit[107] = true;
    }
}
