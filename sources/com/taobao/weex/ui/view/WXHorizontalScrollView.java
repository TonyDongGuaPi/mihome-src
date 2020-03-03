package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXHorizontalScrollView extends HorizontalScrollView implements IWXScroller, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private ScrollViewListener mScrollViewListener;
    private List<ScrollViewListener> mScrollViewListeners;
    private boolean scrollable = true;
    private WXGesture wxGesture;

    public interface ScrollViewListener {
        void onScrollChanged(WXHorizontalScrollView wXHorizontalScrollView, int i, int i2, int i3, int i4);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4169240282799874203L, "com/taobao/weex/ui/view/WXHorizontalScrollView", 37);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXHorizontalScrollView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        init();
        $jacocoInit[1] = true;
    }

    private void init() {
        boolean[] $jacocoInit = $jacocoInit();
        setWillNotDraw(false);
        $jacocoInit[2] = true;
        setOverScrollMode(2);
        $jacocoInit[3] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[4] = true;
        init();
        $jacocoInit[5] = true;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onScrollChanged(i, i2, i3, i4);
        if (this.mScrollViewListener == null) {
            $jacocoInit[6] = true;
        } else {
            $jacocoInit[7] = true;
            this.mScrollViewListener.onScrollChanged(this, i, i2, i3, i4);
            $jacocoInit[8] = true;
        }
        if (this.mScrollViewListeners == null) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            $jacocoInit[11] = true;
            for (ScrollViewListener onScrollChanged : this.mScrollViewListeners) {
                $jacocoInit[13] = true;
                onScrollChanged.onScrollChanged(this, i, i2, i3, i4);
                $jacocoInit[14] = true;
            }
            $jacocoInit[12] = true;
        }
        $jacocoInit[15] = true;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollViewListener = scrollViewListener;
        $jacocoInit[16] = true;
    }

    public void destroy() {
        $jacocoInit()[17] = true;
    }

    public void addScrollViewListener(ScrollViewListener scrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollViewListeners != null) {
            $jacocoInit[18] = true;
        } else {
            $jacocoInit[19] = true;
            this.mScrollViewListeners = new ArrayList();
            $jacocoInit[20] = true;
        }
        if (this.mScrollViewListeners.contains(scrollViewListener)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            this.mScrollViewListeners.add(scrollViewListener);
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }

    public void removeScrollViewListener(ScrollViewListener scrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollViewListeners.remove(scrollViewListener);
        $jacocoInit[25] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[26] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[27] = true;
        return wXGesture;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            dispatchTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[30] = true;
        }
        $jacocoInit[31] = true;
        return dispatchTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.scrollable) {
            $jacocoInit[32] = true;
            return true;
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        $jacocoInit[33] = true;
        return onTouchEvent;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.scrollable;
        $jacocoInit[34] = true;
        return z;
    }

    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.scrollable = z;
        $jacocoInit[35] = true;
    }

    public Rect getContentFrame() {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect(0, 0, computeHorizontalScrollRange(), computeVerticalScrollRange());
        $jacocoInit[36] = true;
        return rect;
    }
}
