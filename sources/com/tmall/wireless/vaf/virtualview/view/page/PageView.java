package com.tmall.wireless.vaf.virtualview.view.page;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.tmall.wireless.vaf.virtualview.core.Adapter;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import java.util.ArrayList;
import java.util.List;

public class PageView extends ViewGroup {
    protected static final int DEFAULT_ANIMATOR_TIME_INTERVAL = 100;
    protected static final int DEFAULT_AUTO_SWITCH_TIME_INTERVAL = 500;
    protected static final int DEFAULT_STAY_TIME = 2500;
    protected static final int MAX_ITEM_COUNT = 5;
    protected static final int MSG_AUTO_SWITCH = 1;
    protected static final int VEL_THRESHOLD = 2000;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9410a = "PageView_TMTEST";
    private ObjectAnimator b;
    private int c;
    private int d;
    private VelocityTracker e;
    private int f;
    private int g = ViewConfiguration.getMaximumFlingVelocity();
    protected Adapter mAdapter;
    protected MyAnimatorListener mAniListener = new MyAnimatorListener();
    protected int mAnimationStyle = 0;
    protected int mAnimatorTimeInterval = 100;
    protected boolean mAutoSwitch = false;
    protected long mAutoSwitchDelay = 0;
    protected Handler mAutoSwitchHandler = new Handler() {
        public void handleMessage(Message message) {
            if (1 == message.what) {
                PageView.this.autoSwitch();
            }
        }
    };
    protected int mAutoSwitchTimeInterval = 500;
    protected boolean mCanSlide = true;
    protected boolean mCanSwitch = true;
    protected int mCurPos = 0;
    protected boolean mDataChanged = true;
    protected int mDownPos;
    protected boolean mIsHorizontal = true;
    protected boolean mIsNext;
    protected SparseArray<List<Adapter.ViewHolder>> mItemCache = new SparseArray<>();
    protected boolean mLayoutNormal = true;
    protected Listener mListener;
    protected int mStayTime = 2500;

    public interface Listener {
        void i(int i, int i2);
    }

    public PageView(Context context) {
        super(context);
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setSlide(boolean z) {
        this.mCanSlide = z;
    }

    public void setAutoSwitchTimeInterval(int i) {
        this.mAutoSwitchTimeInterval = i;
    }

    public void setAnimatorTimeInterval(int i) {
        this.mAnimatorTimeInterval = i;
    }

    public void setAnimationStyle(int i) {
        this.mAnimationStyle = i;
    }

    public void setLayoutOrientation(boolean z) {
        this.mLayoutNormal = z;
    }

    public void setStayTime(int i) {
        this.mStayTime = i;
    }

    public void setOrientation(boolean z) {
        this.mIsHorizontal = z;
    }

    public void setAutoSwitch(boolean z) {
        this.mAutoSwitch = z;
    }

    public void setAutoSwitchDelay(long j) {
        this.mAutoSwitchDelay = j;
    }

    public void autoSwitch() {
        this.mIsNext = true;
        if (this.mIsHorizontal) {
            if (this.mLayoutNormal) {
                this.b = ObjectAnimator.ofInt(this, "scrollX", new int[]{0, getMeasuredWidth()});
            } else {
                this.b = ObjectAnimator.ofInt(this, "scrollX", new int[]{0, -getMeasuredWidth()});
            }
        } else if (this.mLayoutNormal) {
            this.b = ObjectAnimator.ofInt(this, "scrollY", new int[]{0, getMeasuredHeight()});
        } else {
            this.b = ObjectAnimator.ofInt(this, "scrollY", new int[]{0, -getMeasuredHeight()});
        }
        this.b.setDuration((long) this.mAutoSwitchTimeInterval).addListener(this.mAniListener);
        this.b.setInterpolator(getTimeInterpolater());
        this.b.setStartDelay(this.mAutoSwitchDelay);
        this.b.start();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            this.mCanSwitch = false;
            this.mAutoSwitchHandler.removeMessages(1);
            return;
        }
        this.mCanSwitch = true;
        if (this.mAutoSwitch && this.mAdapter.a() > 1) {
            this.mAutoSwitchHandler.removeMessages(1);
            this.mAutoSwitchHandler.sendEmptyMessageDelayed(1, (long) this.mStayTime);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCanSwitch = false;
        this.mAutoSwitchHandler.removeMessages(1);
    }

    public void refresh() {
        this.mCanSwitch = true;
        if (this.mDataChanged) {
            if (this.b != null) {
                this.b.cancel();
            }
            removeAll();
            this.mDataChanged = false;
            a();
        }
        if (this.mAutoSwitch && this.mAdapter.a() > 1) {
            this.mAutoSwitchHandler.removeMessages(1);
            this.mAutoSwitchHandler.sendEmptyMessageDelayed(1, (long) this.mStayTime);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        refresh();
    }

    private void a() {
        int i = 0;
        this.mCurPos = 0;
        int a2 = this.mAdapter.a();
        if (1 == a2) {
            if (getChildCount() == 0) {
                add(this.mCurPos);
            } else {
                a(this.mCurPos);
            }
        } else if (a2 > 1) {
            int i2 = this.mCurPos - 1;
            if (i2 < 0) {
                i2 += a2;
            }
            int i3 = (this.mCurPos + 1) % a2;
            if (this.mLayoutNormal) {
                if (getChildCount() == 0) {
                    if (this.mCanSlide) {
                        add(i2);
                    }
                    add(this.mCurPos);
                    add(i3);
                } else {
                    if (this.mCanSlide) {
                        replace(i2, 0);
                        i = 1;
                    }
                    replace(this.mCurPos, i);
                    replace(i3, i + 1);
                }
            } else if (getChildCount() == 0) {
                add(i3);
                add(this.mCurPos);
                if (this.mCanSlide) {
                    add(i2);
                }
            } else {
                replace(i3, 0);
                replace(this.mCurPos, 1);
                if (this.mCanSlide) {
                    replace(i2, 2);
                }
            }
        }
        if (a2 > 0 && this.mListener != null) {
            this.mListener.i(1, a2);
        }
    }

    /* access modifiers changed from: protected */
    public void add(int i) {
        add(i, -1);
    }

    /* access modifiers changed from: protected */
    public void add(int i, int i2) {
        Adapter.ViewHolder viewHolder;
        int b2 = this.mAdapter.b(i);
        List list = this.mItemCache.get(b2);
        if (list == null || list.size() <= 0) {
            Adapter.ViewHolder c2 = this.mAdapter.c(b2);
            c2.b = b2;
            c2.c = i;
            viewHolder = c2;
        } else {
            viewHolder = (Adapter.ViewHolder) list.remove(0);
            viewHolder.c = i;
        }
        this.mAdapter.a(viewHolder, i);
        if (i2 < 0) {
            addView(viewHolder.f9380a);
        } else {
            addView(viewHolder.f9380a, i2);
        }
    }

    private void a(int i) {
        replace(i, -1);
    }

    /* access modifiers changed from: protected */
    public void replace(int i, int i2) {
        View view;
        int childCount = getChildCount();
        if (childCount == 0 || i2 >= childCount) {
            Log.d(f9410a, "childCount == 0 or index >= childCount should not happen here");
            return;
        }
        if (i2 == -1) {
            view = getChildAt(childCount - 1);
        } else {
            view = getChildAt(i2);
        }
        Adapter.ViewHolder viewHolder = (Adapter.ViewHolder) view.getTag();
        if (viewHolder == null) {
            Log.d(f9410a, "view holder == null, should not happen ");
        } else {
            this.mAdapter.a(viewHolder, i);
        }
    }

    private void a(MotionEvent motionEvent) {
        if (this.e == null) {
            this.e = VelocityTracker.obtain();
        }
        this.e.addMovement(motionEvent);
    }

    private void b() {
        if (this.e != null) {
            this.e.clear();
            this.e.recycle();
            this.e = null;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mCanSlide) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                if (this.mIsHorizontal) {
                    this.mDownPos = x;
                } else {
                    this.mDownPos = y;
                }
                this.c = x;
                this.d = y;
                this.f = motionEvent.getPointerId(0);
            } else if (action == 2) {
                int i = x - this.c;
                int i2 = y - this.d;
                if (this.mIsHorizontal) {
                    if (Math.abs(i) > Math.abs(i2)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                } else if (Math.abs(i2) > Math.abs(i)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
            }
        }
        return false;
    }

    private void b(MotionEvent motionEvent) {
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.mDownPos = y;
                return;
            case 1:
            case 3:
                this.e.computeCurrentVelocity(1000, (float) this.g);
                float yVelocity = this.e.getYVelocity(this.f);
                int scrollY = getScrollY();
                int measuredHeight = getMeasuredHeight();
                if (Math.abs(scrollY) > measuredHeight / 2 || Math.abs(yVelocity) > 2000.0f) {
                    if (scrollY < 0) {
                        this.mIsNext = false;
                        this.b = ObjectAnimator.ofInt(this, "scrollY", new int[]{scrollY, -measuredHeight});
                    } else {
                        this.mIsNext = true;
                        this.b = ObjectAnimator.ofInt(this, "scrollY", new int[]{scrollY, measuredHeight});
                    }
                    this.b.setDuration((long) this.mAnimatorTimeInterval).addListener(this.mAniListener);
                    this.b.setInterpolator(getTimeInterpolater());
                    this.b.start();
                } else {
                    ObjectAnimator.ofInt(this, "scrollY", new int[]{scrollY, 0}).setDuration((long) this.mAnimatorTimeInterval).start();
                }
                b();
                return;
            case 2:
                setScrollY(-(y - this.mDownPos));
                return;
            default:
                return;
        }
    }

    private void c(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        switch (motionEvent.getAction()) {
            case 0:
                this.mDownPos = x;
                return;
            case 1:
            case 3:
                this.e.computeCurrentVelocity(1000, (float) this.g);
                float xVelocity = this.e.getXVelocity(this.f);
                this.e.getYVelocity(this.f);
                int scrollX = getScrollX();
                int measuredWidth = getMeasuredWidth();
                if (Math.abs(scrollX) > measuredWidth / 2 || Math.abs(xVelocity) > 2000.0f) {
                    if (scrollX < 0) {
                        this.mIsNext = false;
                        this.b = ObjectAnimator.ofInt(this, "scrollX", new int[]{scrollX, -measuredWidth});
                    } else {
                        this.mIsNext = true;
                        this.b = ObjectAnimator.ofInt(this, "scrollX", new int[]{scrollX, measuredWidth});
                    }
                    this.b.setDuration((long) this.mAnimatorTimeInterval).addListener(this.mAniListener);
                    this.b.setInterpolator(getTimeInterpolater());
                    this.b.start();
                } else {
                    ObjectAnimator.ofInt(this, "scrollX", new int[]{scrollX, 0}).setDuration((long) this.mAnimatorTimeInterval).start();
                }
                b();
                return;
            case 2:
                setScrollX(-(x - this.mDownPos));
                return;
            default:
                return;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(motionEvent);
        }
        a(motionEvent);
        if (this.mIsHorizontal) {
            c(motionEvent);
            return true;
        }
        b(motionEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public void removeAll() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            b(i);
        }
        removeAllViews();
    }

    private void b(int i) {
        Adapter.ViewHolder viewHolder = (Adapter.ViewHolder) getChildAt(i).getTag();
        ((IContainer) viewHolder.f9380a).getVirtualView().e();
        List list = this.mItemCache.get(viewHolder.b);
        if (list == null) {
            list = new ArrayList();
            this.mItemCache.put(viewHolder.b, list);
        }
        if (list.size() >= 5) {
            list.remove(0);
        }
        list.add(viewHolder);
    }

    private void c(int i) {
        b(i);
        removeViewAt(i);
    }

    /* access modifiers changed from: private */
    public void c() {
        int a2;
        if (this.mAdapter != null && (a2 = this.mAdapter.a()) > 0 && getChildCount() > 0) {
            if (this.mIsNext) {
                if (this.mLayoutNormal) {
                    c(0);
                } else {
                    c(getChildCount() - 1);
                }
                this.mCurPos = (this.mCurPos + 1) % a2;
                int i = (this.mCurPos + 1) % a2;
                if (this.mLayoutNormal) {
                    add(i);
                } else {
                    add(i, 0);
                }
            } else {
                if (this.mLayoutNormal) {
                    c(getChildCount() - 1);
                } else {
                    c(0);
                }
                this.mCurPos--;
                if (this.mCurPos < 0) {
                    this.mCurPos += a2;
                }
                int i2 = this.mCurPos - 1;
                if (i2 < 0) {
                    i2 += a2;
                }
                if (this.mLayoutNormal) {
                    add(i2, 0);
                } else {
                    add(i2);
                }
            }
            requestLayout();
            if (this.mIsHorizontal) {
                setScrollX(0);
            } else {
                setScrollY(0);
            }
            if (this.mAutoSwitch) {
                this.mAutoSwitchHandler.removeMessages(1);
                if (this.mCanSwitch) {
                    this.mAutoSwitchHandler.sendEmptyMessageDelayed(1, (long) this.mStayTime);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.mIsHorizontal) {
            setScrollX(0);
        } else {
            setScrollY(0);
        }
    }

    class MyAnimatorListener implements Animator.AnimatorListener {
        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        MyAnimatorListener() {
        }

        public void onAnimationEnd(Animator animator) {
            PageView.this.c();
            if (PageView.this.mListener != null) {
                PageView.this.mListener.i(PageView.this.mCurPos + 1, PageView.this.mAdapter.a());
            }
        }

        public void onAnimationCancel(Animator animator) {
            PageView.this.d();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        measureChildren(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0045 A[LOOP:1: B:26:0x0043->B:27:0x0045, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            r3 = this;
            int r4 = r3.getChildCount()
            int r7 = r7 - r5
            int r8 = r8 - r6
            boolean r5 = r3.mIsHorizontal
            r6 = 0
            if (r5 == 0) goto L_0x002d
            boolean r5 = r3.mLayoutNormal
            if (r5 == 0) goto L_0x0015
            boolean r5 = r3.mCanSlide
            if (r5 == 0) goto L_0x0015
            int r5 = -r7
            goto L_0x001c
        L_0x0015:
            boolean r5 = r3.mLayoutNormal
            if (r5 != 0) goto L_0x001b
            int r5 = -r7
            goto L_0x001c
        L_0x001b:
            r5 = 0
        L_0x001c:
            r0 = r5
            r5 = 0
        L_0x001e:
            if (r5 >= r4) goto L_0x0052
            android.view.View r1 = r3.getChildAt(r5)
            int r2 = r0 + r7
            r1.layout(r0, r6, r2, r8)
            int r5 = r5 + 1
            r0 = r2
            goto L_0x001e
        L_0x002d:
            r5 = 1
            if (r4 <= r5) goto L_0x0040
            boolean r5 = r3.mLayoutNormal
            if (r5 == 0) goto L_0x003a
            boolean r5 = r3.mCanSlide
            if (r5 == 0) goto L_0x003a
            int r5 = -r8
            goto L_0x0041
        L_0x003a:
            boolean r5 = r3.mLayoutNormal
            if (r5 != 0) goto L_0x0040
            int r5 = -r8
            goto L_0x0041
        L_0x0040:
            r5 = 0
        L_0x0041:
            r0 = r5
            r5 = 0
        L_0x0043:
            if (r5 >= r4) goto L_0x0052
            android.view.View r1 = r3.getChildAt(r5)
            int r2 = r0 + r8
            r1.layout(r6, r0, r7, r2)
            int r5 = r5 + 1
            r0 = r2
            goto L_0x0043
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.view.page.PageView.onLayout(boolean, int, int, int, int):void");
    }

    private TimeInterpolator getTimeInterpolater() {
        switch (this.mAnimationStyle) {
            case 0:
                return new LinearInterpolator();
            case 1:
                return new DecelerateInterpolator();
            case 2:
                return new AccelerateInterpolator();
            case 3:
                return new AccelerateDecelerateInterpolator();
            case 4:
                return new SpringInterpolator();
            default:
                return new LinearInterpolator();
        }
    }

    public static class SpringInterpolator implements TimeInterpolator {

        /* renamed from: a  reason: collision with root package name */
        private static final float f9414a = 4.0f;

        public float getInterpolation(float f) {
            double pow = Math.pow(2.0d, (double) (-10.0f * f));
            double d = (double) (f - 1.0f);
            Double.isNaN(d);
            return (float) ((pow * Math.sin((d * 6.283185307179586d) / 4.0d)) + 1.0d);
        }
    }

    public static class DecelerateInterpolator implements TimeInterpolator {

        /* renamed from: a  reason: collision with root package name */
        private static final float f9412a = 5.0f;

        public float getInterpolation(float f) {
            return (float) (1.0d - Math.pow((double) (1.0f - f), 10.0d));
        }
    }
}
