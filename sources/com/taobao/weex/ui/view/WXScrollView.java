package com.taobao.weex.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXReflectionUtils;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXScrollView extends ScrollView implements Handler.Callback, NestedScrollingChild, IWXScroller, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private NestedScrollingChildHelper childHelper;
    private int[] consumed = new int[2];
    private int mCheckTime = 100;
    private View mCurrentStickyView;
    private boolean mHasNotDoneActionDown = true;
    private int mInitialPosition;
    private boolean mRedirectTouchToStickyView;
    private Rect mScrollRect;
    private List<WXScrollViewListener> mScrollViewListeners;
    int mScrollX;
    int mScrollY;
    @SuppressLint({"HandlerLeak"})
    private Handler mScrollerTask;
    private int mStickyOffset;
    private int[] mStickyP = new int[2];
    private WXScroller mWAScroller;
    private int[] offsetInWindow = new int[2];
    private float ox;
    private float oy;
    private boolean scrollable = true;
    private int[] stickyScrollerP = new int[2];
    private int[] stickyViewP = new int[2];
    private WXGesture wxGesture;

    public interface WXScrollViewListener {
        void onScroll(WXScrollView wXScrollView, int i, int i2);

        void onScrollChanged(WXScrollView wXScrollView, int i, int i2, int i3, int i4);

        void onScrollStopped(WXScrollView wXScrollView, int i, int i2);

        void onScrollToBottom(WXScrollView wXScrollView, int i, int i2);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(380721470867984596L, "com/taobao/weex/ui/view/WXScrollView", 191);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXScrollView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mScrollViewListeners = new ArrayList();
        $jacocoInit[1] = true;
        init();
        try {
            $jacocoInit[2] = true;
            WXReflectionUtils.setValue(this, "mMinimumVelocity", 5);
            $jacocoInit[3] = true;
        } catch (Exception e) {
            $jacocoInit[4] = true;
            WXLogUtils.e("[WXScrollView] WXScrollView: ", (Throwable) e);
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    private void init() {
        boolean[] $jacocoInit = $jacocoInit();
        setWillNotDraw(false);
        $jacocoInit[7] = true;
        startScrollerTask();
        $jacocoInit[8] = true;
        setOverScrollMode(2);
        $jacocoInit[9] = true;
        this.childHelper = new NestedScrollingChildHelper(this);
        $jacocoInit[10] = true;
        this.childHelper.setNestedScrollingEnabled(true);
        $jacocoInit[11] = true;
    }

    public void startScrollerTask() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollerTask != null) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            this.mScrollerTask = new Handler(WXThread.secure((Handler.Callback) this));
            $jacocoInit[14] = true;
        }
        this.mInitialPosition = getScrollY();
        $jacocoInit[15] = true;
        this.mScrollerTask.sendEmptyMessageDelayed(0, (long) this.mCheckTime);
        $jacocoInit[16] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[17] = true;
        init();
        $jacocoInit[18] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[19] = true;
        setOverScrollMode(2);
        $jacocoInit[20] = true;
    }

    public void addScrollViewListener(WXScrollViewListener wXScrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollViewListeners.contains(wXScrollViewListener)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            this.mScrollViewListeners.add(wXScrollViewListener);
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }

    public void removeScrollViewListener(WXScrollViewListener wXScrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollViewListeners.remove(wXScrollViewListener);
        $jacocoInit[25] = true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (motionEvent.getAction() != 0) {
            $jacocoInit[26] = true;
        } else {
            this.mRedirectTouchToStickyView = true;
            $jacocoInit[27] = true;
        }
        if (!this.mRedirectTouchToStickyView) {
            $jacocoInit[28] = true;
        } else {
            boolean z2 = false;
            if (this.mCurrentStickyView != null) {
                $jacocoInit[29] = true;
                z = true;
            } else {
                $jacocoInit[30] = true;
                z = false;
            }
            this.mRedirectTouchToStickyView = z;
            if (!this.mRedirectTouchToStickyView) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                if (motionEvent.getY() > ((float) this.mCurrentStickyView.getHeight())) {
                    $jacocoInit[33] = true;
                } else {
                    $jacocoInit[34] = true;
                    if (motionEvent.getX() < ((float) this.mCurrentStickyView.getLeft())) {
                        $jacocoInit[35] = true;
                    } else {
                        $jacocoInit[36] = true;
                        if (motionEvent.getX() > ((float) this.mCurrentStickyView.getRight())) {
                            $jacocoInit[37] = true;
                        } else {
                            $jacocoInit[38] = true;
                            z2 = true;
                            this.mRedirectTouchToStickyView = z2;
                            $jacocoInit[40] = true;
                        }
                    }
                }
                $jacocoInit[39] = true;
                this.mRedirectTouchToStickyView = z2;
                $jacocoInit[40] = true;
            }
        }
        if (!this.mRedirectTouchToStickyView) {
            $jacocoInit[41] = true;
        } else {
            if (this.mScrollRect != null) {
                $jacocoInit[42] = true;
            } else {
                $jacocoInit[43] = true;
                this.mScrollRect = new Rect();
                $jacocoInit[44] = true;
                getGlobalVisibleRect(this.mScrollRect);
                $jacocoInit[45] = true;
            }
            this.mCurrentStickyView.getLocationOnScreen(this.stickyViewP);
            $jacocoInit[46] = true;
            motionEvent.offsetLocation(0.0f, (float) (this.stickyViewP[1] - this.mScrollRect.top));
            $jacocoInit[47] = true;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[48] = true;
        } else {
            $jacocoInit[49] = true;
            dispatchTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[50] = true;
        }
        $jacocoInit[51] = true;
        return dispatchTouchEvent;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        super.dispatchDraw(canvas);
        if (this.mCurrentStickyView == null) {
            $jacocoInit[52] = true;
        } else {
            $jacocoInit[53] = true;
            canvas.save();
            $jacocoInit[54] = true;
            this.mCurrentStickyView.getLocationOnScreen(this.mStickyP);
            if (this.mStickyOffset <= 0) {
                i = this.mStickyOffset;
                $jacocoInit[55] = true;
            } else {
                $jacocoInit[56] = true;
                i = 0;
            }
            $jacocoInit[57] = true;
            canvas.translate((float) this.mStickyP[0], (float) (getScrollY() + i));
            $jacocoInit[58] = true;
            int width = this.mCurrentStickyView.getWidth();
            View view = this.mCurrentStickyView;
            $jacocoInit[59] = true;
            int height = view.getHeight();
            $jacocoInit[60] = true;
            canvas.clipRect(0, i, width, height);
            $jacocoInit[61] = true;
            this.mCurrentStickyView.draw(canvas);
            $jacocoInit[62] = true;
            canvas.restore();
            $jacocoInit[63] = true;
        }
        $jacocoInit[64] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = r9.scrollable
            r2 = 1
            if (r1 != 0) goto L_0x000e
            r10 = 65
            r0[r10] = r2
            return r2
        L_0x000e:
            boolean r1 = r9.mRedirectTouchToStickyView
            if (r1 != 0) goto L_0x0017
            r1 = 66
            r0[r1] = r2
            goto L_0x0056
        L_0x0017:
            android.graphics.Rect r1 = r9.mScrollRect
            if (r1 == 0) goto L_0x0020
            r1 = 67
            r0[r1] = r2
            goto L_0x0038
        L_0x0020:
            r1 = 68
            r0[r1] = r2
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r9.mScrollRect = r1
            r1 = 69
            r0[r1] = r2
            android.graphics.Rect r1 = r9.mScrollRect
            r9.getGlobalVisibleRect(r1)
            r1 = 70
            r0[r1] = r2
        L_0x0038:
            android.view.View r1 = r9.mCurrentStickyView
            int[] r3 = r9.stickyViewP
            r1.getLocationOnScreen(r3)
            r1 = 71
            r0[r1] = r2
            r1 = 0
            int[] r3 = r9.stickyViewP
            r3 = r3[r2]
            android.graphics.Rect r4 = r9.mScrollRect
            int r4 = r4.top
            int r3 = r3 - r4
            int r3 = -r3
            float r3 = (float) r3
            r10.offsetLocation(r1, r3)
            r1 = 72
            r0[r1] = r2
        L_0x0056:
            int r1 = r10.getAction()
            r3 = 0
            if (r1 == 0) goto L_0x0062
            r1 = 73
            r0[r1] = r2
            goto L_0x0068
        L_0x0062:
            r9.mHasNotDoneActionDown = r3
            r1 = 74
            r0[r1] = r2
        L_0x0068:
            boolean r1 = r9.mHasNotDoneActionDown
            if (r1 != 0) goto L_0x0071
            r1 = 75
            r0[r1] = r2
            goto L_0x008d
        L_0x0071:
            r1 = 76
            r0[r1] = r2
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r10)
            r4 = 77
            r0[r4] = r2
            r1.setAction(r3)
            r9.mHasNotDoneActionDown = r3
            r4 = 78
            r0[r4] = r2
            r1.recycle()
            r1 = 79
            r0[r1] = r2
        L_0x008d:
            int r1 = r10.getAction()
            r4 = 3
            if (r1 == 0) goto L_0x0099
            r1 = 80
            r0[r1] = r2
            goto L_0x00b8
        L_0x0099:
            r1 = 81
            r0[r1] = r2
            float r1 = r10.getX()
            r9.ox = r1
            r1 = 82
            r0[r1] = r2
            float r1 = r10.getY()
            r9.oy = r1
            r1 = 83
            r0[r1] = r2
            r9.startNestedScroll(r4)
            r1 = 84
            r0[r1] = r2
        L_0x00b8:
            int r1 = r10.getAction()
            if (r1 != r2) goto L_0x00c3
            r1 = 85
            r0[r1] = r2
            goto L_0x00d2
        L_0x00c3:
            int r1 = r10.getAction()
            if (r1 == r4) goto L_0x00ce
            r1 = 86
            r0[r1] = r2
            goto L_0x00df
        L_0x00ce:
            r1 = 87
            r0[r1] = r2
        L_0x00d2:
            r9.mHasNotDoneActionDown = r2
            r1 = 88
            r0[r1] = r2
            r9.stopNestedScroll()
            r1 = 89
            r0[r1] = r2
        L_0x00df:
            int r1 = r10.getAction()
            r4 = 2
            if (r1 == r4) goto L_0x00eb
            r1 = 90
            r0[r1] = r2
            goto L_0x0141
        L_0x00eb:
            r1 = 91
            r0[r1] = r2
            float r1 = r10.getX()
            r4 = 92
            r0[r4] = r2
            float r4 = r10.getY()
            float r5 = r9.ox
            float r5 = r5 - r1
            int r5 = (int) r5
            float r6 = r9.oy
            float r6 = r6 - r4
            int r6 = (int) r6
            r7 = 93
            r0[r7] = r2
            int[] r7 = r9.consumed
            int[] r8 = r9.offsetInWindow
            boolean r5 = r9.dispatchNestedPreScroll(r5, r6, r7, r8)
            if (r5 != 0) goto L_0x0116
            r1 = 94
            r0[r1] = r2
            goto L_0x012d
        L_0x0116:
            r5 = 95
            r0[r5] = r2
            int[] r5 = r9.consumed
            r3 = r5[r3]
            float r3 = (float) r3
            float r1 = r1 + r3
            int[] r3 = r9.consumed
            r3 = r3[r2]
            float r3 = (float) r3
            float r4 = r4 + r3
            r10.setLocation(r1, r4)
            r1 = 96
            r0[r1] = r2
        L_0x012d:
            float r1 = r10.getX()
            r9.ox = r1
            r1 = 97
            r0[r1] = r2
            float r1 = r10.getY()
            r9.oy = r1
            r1 = 98
            r0[r1] = r2
        L_0x0141:
            boolean r10 = super.onTouchEvent(r10)
            r1 = 99
            r0[r1] = r2
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.WXScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setNestedScrollingEnabled(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.childHelper.setNestedScrollingEnabled(z);
        $jacocoInit[100] = true;
    }

    public boolean isNestedScrollingEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isNestedScrollingEnabled = this.childHelper.isNestedScrollingEnabled();
        $jacocoInit[101] = true;
        return isNestedScrollingEnabled;
    }

    public boolean startNestedScroll(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean startNestedScroll = this.childHelper.startNestedScroll(i);
        $jacocoInit[102] = true;
        return startNestedScroll;
    }

    public void stopNestedScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        this.childHelper.stopNestedScroll();
        $jacocoInit[103] = true;
    }

    public boolean hasNestedScrollingParent() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean hasNestedScrollingParent = this.childHelper.hasNestedScrollingParent();
        $jacocoInit[104] = true;
        return hasNestedScrollingParent;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.scrollable;
        $jacocoInit[105] = true;
        return z;
    }

    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.scrollable = z;
        $jacocoInit[106] = true;
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedScroll = this.childHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
        $jacocoInit[107] = true;
        return dispatchNestedScroll;
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedPreScroll = this.childHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        $jacocoInit[108] = true;
        return dispatchNestedPreScroll;
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedFling = this.childHelper.dispatchNestedFling(f, f2, z);
        $jacocoInit[109] = true;
        return dispatchNestedFling;
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedPreFling = this.childHelper.dispatchNestedPreFling(f, f2);
        $jacocoInit[110] = true;
        return dispatchNestedPreFling;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedPreFling = dispatchNestedPreFling(f, f2);
        $jacocoInit[111] = true;
        return dispatchNestedPreFling;
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedFling = dispatchNestedFling(f, f2, z);
        $jacocoInit[112] = true;
        return dispatchNestedFling;
    }

    public void fling(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.fling(i);
        if (this.mScrollerTask == null) {
            $jacocoInit[113] = true;
        } else {
            $jacocoInit[114] = true;
            this.mScrollerTask.removeMessages(0);
            $jacocoInit[115] = true;
        }
        startScrollerTask();
        $jacocoInit[116] = true;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        int i5;
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollX = getScrollX();
        $jacocoInit[117] = true;
        this.mScrollY = getScrollY();
        $jacocoInit[118] = true;
        onScroll(this, this.mScrollX, this.mScrollY);
        $jacocoInit[119] = true;
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null) {
            $jacocoInit[120] = true;
            return;
        }
        int bottom = childAt.getBottom();
        $jacocoInit[121] = true;
        if (bottom - (getHeight() + this.mScrollY) != 0) {
            $jacocoInit[122] = true;
        } else {
            $jacocoInit[123] = true;
            onScrollToBottom(this.mScrollX, this.mScrollY);
            $jacocoInit[124] = true;
        }
        int i6 = 0;
        if (this.mScrollViewListeners == null) {
            $jacocoInit[125] = true;
            i5 = 0;
        } else {
            i5 = this.mScrollViewListeners.size();
            $jacocoInit[126] = true;
        }
        $jacocoInit[127] = true;
        while (i6 < i5) {
            $jacocoInit[128] = true;
            this.mScrollViewListeners.get(i6).onScrollChanged(this, i, i2, i3, i4);
            i6++;
            $jacocoInit[129] = true;
        }
        showStickyView();
        $jacocoInit[130] = true;
    }

    /* access modifiers changed from: protected */
    public void onScroll(WXScrollView wXScrollView, int i, int i2) {
        int i3;
        boolean[] $jacocoInit = $jacocoInit();
        int i4 = 0;
        if (this.mScrollViewListeners == null) {
            $jacocoInit[131] = true;
            i3 = 0;
        } else {
            i3 = this.mScrollViewListeners.size();
            $jacocoInit[132] = true;
        }
        $jacocoInit[133] = true;
        while (i4 < i3) {
            $jacocoInit[134] = true;
            this.mScrollViewListeners.get(i4).onScroll(this, i, i2);
            i4++;
            $jacocoInit[135] = true;
        }
        $jacocoInit[136] = true;
    }

    /* access modifiers changed from: protected */
    public void onScrollToBottom(int i, int i2) {
        int i3;
        boolean[] $jacocoInit = $jacocoInit();
        int i4 = 0;
        if (this.mScrollViewListeners == null) {
            $jacocoInit[137] = true;
            i3 = 0;
        } else {
            i3 = this.mScrollViewListeners.size();
            $jacocoInit[138] = true;
        }
        $jacocoInit[139] = true;
        while (i4 < i3) {
            $jacocoInit[140] = true;
            this.mScrollViewListeners.get(i4).onScrollToBottom(this, i, i2);
            i4++;
            $jacocoInit[141] = true;
        }
        $jacocoInit[142] = true;
    }

    private void showStickyView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWAScroller == null) {
            $jacocoInit[143] = true;
            return;
        }
        View procSticky = procSticky(this.mWAScroller.getStickMap());
        if (procSticky != null) {
            this.mCurrentStickyView = procSticky;
            $jacocoInit[144] = true;
        } else {
            this.mCurrentStickyView = null;
            $jacocoInit[145] = true;
        }
        $jacocoInit[146] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View procSticky(java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.taobao.weex.ui.component.WXComponent>> r10) {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            r2 = 1
            if (r10 != 0) goto L_0x000d
            r10 = 147(0x93, float:2.06E-43)
            r0[r10] = r2
            return r1
        L_0x000d:
            com.taobao.weex.ui.component.WXScroller r3 = r9.mWAScroller
            java.lang.String r3 = r3.getRef()
            java.lang.Object r10 = r10.get(r3)
            java.util.Map r10 = (java.util.Map) r10
            if (r10 != 0) goto L_0x0020
            r10 = 148(0x94, float:2.07E-43)
            r0[r10] = r2
            return r1
        L_0x0020:
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
            r3 = 149(0x95, float:2.09E-43)
            r0[r3] = r2
        L_0x002c:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto L_0x00e7
            r3 = 150(0x96, float:2.1E-43)
            r0[r3] = r2
            java.lang.Object r3 = r10.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            r4 = 151(0x97, float:2.12E-43)
            r0[r4] = r2
            java.lang.Object r3 = r3.getValue()
            com.taobao.weex.ui.component.WXComponent r3 = (com.taobao.weex.ui.component.WXComponent) r3
            r4 = 152(0x98, float:2.13E-43)
            r0[r4] = r2
            int[] r4 = r9.stickyScrollerP
            r9.getLocationOnScreen(r4)
            r4 = 153(0x99, float:2.14E-43)
            r0[r4] = r2
            android.view.View r4 = r3.getHostView()
            int[] r5 = r9.stickyViewP
            r4.getLocationOnScreen(r5)
            r4 = 154(0x9a, float:2.16E-43)
            r0[r4] = r2
            com.taobao.weex.ui.component.WXVContainer r4 = r3.getParent()
            r5 = 0
            if (r4 != 0) goto L_0x006c
            r4 = 155(0x9b, float:2.17E-43)
            r0[r4] = r2
            goto L_0x007a
        L_0x006c:
            com.taobao.weex.ui.component.WXVContainer r4 = r3.getParent()
            android.view.ViewGroup r4 = r4.getRealView()
            if (r4 != 0) goto L_0x007c
            r4 = 156(0x9c, float:2.19E-43)
            r0[r4] = r2
        L_0x007a:
            r4 = 0
            goto L_0x0090
        L_0x007c:
            r4 = 157(0x9d, float:2.2E-43)
            r0[r4] = r2
            com.taobao.weex.ui.component.WXVContainer r4 = r3.getParent()
            android.view.ViewGroup r4 = r4.getRealView()
            int r4 = r4.getHeight()
            r6 = 158(0x9e, float:2.21E-43)
            r0[r6] = r2
        L_0x0090:
            android.view.View r6 = r3.getHostView()
            int r6 = r6.getHeight()
            int[] r7 = r9.stickyScrollerP
            r7 = r7[r2]
            int r4 = -r4
            int[] r8 = r9.stickyScrollerP
            r8 = r8[r2]
            int r4 = r4 + r8
            int r4 = r4 + r6
            int[] r8 = r9.stickyViewP
            r8 = r8[r2]
            if (r8 <= r7) goto L_0x00ae
            r4 = 159(0x9f, float:2.23E-43)
            r0[r4] = r2
            goto L_0x00ba
        L_0x00ae:
            int[] r7 = r9.stickyViewP
            r7 = r7[r2]
            int r6 = r4 - r6
            if (r7 >= r6) goto L_0x00c3
            r4 = 160(0xa0, float:2.24E-43)
            r0[r4] = r2
        L_0x00ba:
            r3.setStickyOffset(r5)
            r3 = 164(0xa4, float:2.3E-43)
            r0[r3] = r2
            goto L_0x002c
        L_0x00c3:
            int[] r10 = r9.stickyViewP
            r10 = r10[r2]
            int r10 = r10 - r4
            r9.mStickyOffset = r10
            r10 = 161(0xa1, float:2.26E-43)
            r0[r10] = r2
            int[] r10 = r9.stickyViewP
            r10 = r10[r2]
            int[] r1 = r9.stickyScrollerP
            r1 = r1[r2]
            int r10 = r10 - r1
            r3.setStickyOffset(r10)
            r10 = 162(0xa2, float:2.27E-43)
            r0[r10] = r2
            android.view.View r10 = r3.getHostView()
            r1 = 163(0xa3, float:2.28E-43)
            r0[r1] = r2
            return r10
        L_0x00e7:
            r10 = 165(0xa5, float:2.31E-43)
            r0[r10] = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.WXScrollView.procSticky(java.util.Map):android.view.View");
    }

    public boolean handleMessage(Message message) {
        boolean[] $jacocoInit = $jacocoInit();
        if (message.what != 0) {
            $jacocoInit[166] = true;
        } else {
            if (this.mScrollerTask == null) {
                $jacocoInit[167] = true;
            } else {
                $jacocoInit[168] = true;
                this.mScrollerTask.removeMessages(0);
                $jacocoInit[169] = true;
            }
            if (this.mInitialPosition - getScrollY() == 0) {
                $jacocoInit[170] = true;
                onScrollStopped(this, getScrollX(), getScrollY());
                $jacocoInit[171] = true;
            } else {
                onScroll(this, getScrollX(), getScrollY());
                $jacocoInit[172] = true;
                this.mInitialPosition = getScrollY();
                if (this.mScrollerTask == null) {
                    $jacocoInit[173] = true;
                } else {
                    $jacocoInit[174] = true;
                    this.mScrollerTask.sendEmptyMessageDelayed(0, (long) this.mCheckTime);
                    $jacocoInit[175] = true;
                }
            }
        }
        $jacocoInit[176] = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onScrollStopped(WXScrollView wXScrollView, int i, int i2) {
        int i3;
        boolean[] $jacocoInit = $jacocoInit();
        int i4 = 0;
        if (this.mScrollViewListeners == null) {
            $jacocoInit[177] = true;
            i3 = 0;
        } else {
            i3 = this.mScrollViewListeners.size();
            $jacocoInit[178] = true;
        }
        $jacocoInit[179] = true;
        while (i4 < i3) {
            $jacocoInit[180] = true;
            this.mScrollViewListeners.get(i4).onScrollStopped(this, i, i2);
            i4++;
            $jacocoInit[181] = true;
        }
        $jacocoInit[182] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollerTask == null) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            this.mScrollerTask.removeCallbacksAndMessages((Object) null);
            $jacocoInit[185] = true;
        }
        $jacocoInit[186] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[187] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[188] = true;
        return wXGesture;
    }

    public Rect getContentFrame() {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect(0, 0, computeHorizontalScrollRange(), computeVerticalScrollRange());
        $jacocoInit[189] = true;
        return rect;
    }

    public void setWAScroller(WXScroller wXScroller) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWAScroller = wXScroller;
        $jacocoInit[190] = true;
    }
}
