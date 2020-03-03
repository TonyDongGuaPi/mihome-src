package com.xiaomi.smarthome.library.common.widget.viewflow;

import android.content.Context;
import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Scroller;
import java.util.EnumSet;
import java.util.LinkedList;

public class ViewFlow extends AdapterView<Adapter> {
    static final int CANCLE_MSG = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final int f19080a = 1000;
    private static final int b = -1;
    private static final int c = 0;
    private static final int d = 1;
    private boolean A;
    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener B;
    private LinkedList<View> e;
    private LinkedList<View> f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public int h;
    private int i;
    private Scroller j;
    private VelocityTracker k;
    /* access modifiers changed from: private */
    public int l;
    private float m;
    Handler mHandler;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private ViewSwitchListener s;
    private ViewLazyInitializeListener t;
    private EnumSet<LazyInit> u;
    /* access modifiers changed from: private */
    public Adapter v;
    /* access modifiers changed from: private */
    public int w;
    private AdapterDataSetObserver x;
    private FlowIndicator y;
    private int z;

    enum LazyInit {
        LEFT,
        RIGHT
    }

    public interface ViewLazyInitializeListener {
        void a(View view, int i);
    }

    public interface ViewSwitchListener {
        void a(View view, int i);
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public float getLeftFadingEdgeStrength() {
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getRightFadingEdgeStrength() {
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    public ViewFlow(Context context) {
        super(context);
        this.i = 2;
        this.l = 0;
        this.q = -1;
        this.r = true;
        this.u = EnumSet.allOf(LazyInit.class);
        this.z = -1;
        this.A = false;
        this.B = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewFlow.this.getViewTreeObserver().removeGlobalOnLayoutListener(ViewFlow.this.B);
                ViewFlow.this.setSelection(ViewFlow.this.h);
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    ViewFlow.this.mHandler.removeMessages(1);
                    ViewFlow.this.b();
                    int unused = ViewFlow.this.l = 0;
                }
            }
        };
        this.i = 3;
        a();
    }

    public ViewFlow(Context context, int i2) {
        super(context);
        this.i = 2;
        this.l = 0;
        this.q = -1;
        this.r = true;
        this.u = EnumSet.allOf(LazyInit.class);
        this.z = -1;
        this.A = false;
        this.B = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewFlow.this.getViewTreeObserver().removeGlobalOnLayoutListener(ViewFlow.this.B);
                ViewFlow.this.setSelection(ViewFlow.this.h);
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    ViewFlow.this.mHandler.removeMessages(1);
                    ViewFlow.this.b();
                    int unused = ViewFlow.this.l = 0;
                }
            }
        };
        this.i = i2;
        a();
    }

    public ViewFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 2;
        this.l = 0;
        this.q = -1;
        this.r = true;
        this.u = EnumSet.allOf(LazyInit.class);
        this.z = -1;
        this.A = false;
        this.B = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewFlow.this.getViewTreeObserver().removeGlobalOnLayoutListener(ViewFlow.this.B);
                ViewFlow.this.setSelection(ViewFlow.this.h);
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    ViewFlow.this.mHandler.removeMessages(1);
                    ViewFlow.this.b();
                    int unused = ViewFlow.this.l = 0;
                }
            }
        };
        this.i = 3;
        a();
    }

    private void a() {
        this.e = new LinkedList<>();
        this.f = new LinkedList<>();
        this.j = new Scroller(getContext());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.n = viewConfiguration.getScaledTouchSlop();
        this.o = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation != this.z) {
            this.z = configuration.orientation;
            getViewTreeObserver().addOnGlobalLayoutListener(this.B);
        }
    }

    public int getViewsCount() {
        return this.v.getCount();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int widthPadding = getWidthPadding();
        int heightPadding = getHeightPadding();
        int i6 = 0;
        if ((this.v == null ? 0 : this.v.getCount()) > 0) {
            View c2 = c(0);
            measureChild(c2, i2, i3);
            i5 = c2.getMeasuredWidth();
            i4 = c2.getMeasuredHeight();
            if (Build.VERSION.SDK_INT >= 14) {
                i6 = c2.getMeasuredState();
            }
            this.f.add(c2);
        } else {
            i5 = 0;
            i4 = 0;
        }
        if (mode == Integer.MIN_VALUE) {
            size = (i5 + widthPadding) | i6;
        } else if (mode == 0) {
            size = i5 + widthPadding;
        } else if (mode == 1073741824 && size < i5 + widthPadding) {
            size |= 16777216;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = (i6 >> 16) | (i4 + heightPadding);
        } else if (mode2 == 0) {
            size2 = i4 + heightPadding;
        } else if (mode2 == 1073741824 && size2 < i4 + heightPadding) {
            size2 |= 16777216;
        }
        setMeasuredDimension(size, mode2 == 0 ? heightPadding + i4 : size2 | (-16777216 & i6));
    }

    private int getWidthPadding() {
        return getPaddingLeft() + getPaddingRight() + (getHorizontalFadingEdgeLength() * 2);
    }

    public int getChildWidth() {
        return getWidth() - getWidthPadding();
    }

    private int getHeightPadding() {
        return getPaddingTop() + getPaddingBottom();
    }

    public int getChildHeight() {
        return getHeight() - getHeightPadding();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            getChildAt(i6).measure(View.MeasureSpec.makeMeasureSpec(getChildWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getChildHeight(), 1073741824));
        }
        if (this.r) {
            this.j.startScroll(0, 0, this.p * getChildWidth(), 0, 0);
            this.r = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int paddingLeft = getPaddingLeft() + getHorizontalFadingEdgeLength();
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                childAt.layout(paddingLeft, getPaddingTop(), measuredWidth, getPaddingTop() + childAt.getMeasuredHeight());
                paddingLeft = measuredWidth;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int right;
        if (getChildCount() == 0) {
            return false;
        }
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
        this.k.addMovement(motionEvent);
        this.mHandler.removeMessages(1);
        int action = motionEvent.getAction();
        float x2 = motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.j.isFinished()) {
                    this.j.abortAnimation();
                }
                this.m = x2;
                this.l = this.j.isFinished() ^ true ? 1 : 0;
                break;
            case 1:
                if (this.l == 1) {
                    VelocityTracker velocityTracker = this.k;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.o);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    if (xVelocity > 1000 && this.p > 0) {
                        a(this.p - 1);
                    } else if (xVelocity >= -1000 || this.p >= getChildCount() - 1) {
                        b();
                    } else {
                        a(this.p + 1);
                    }
                    if (this.k != null) {
                        this.k.recycle();
                        this.k = null;
                    }
                }
                this.l = 0;
                break;
            case 2:
                int i2 = (int) (this.m - x2);
                if (Math.abs(i2) > this.n) {
                    this.l = 1;
                    if (this.t != null) {
                        a((float) i2);
                    }
                }
                if (this.l == 1) {
                    this.m = x2;
                    int scrollX = getScrollX();
                    if (i2 < 0) {
                        if (scrollX > 0) {
                            scrollBy(Math.max(-scrollX, i2), 0);
                        }
                    } else if (i2 > 0 && (right = (((getChildAt(getChildCount() - 1).getRight() - getPaddingRight()) - getHorizontalFadingEdgeLength()) - scrollX) - getWidth()) > 0) {
                        scrollBy(Math.min(right, i2), 0);
                    }
                    return true;
                }
                break;
            case 3:
                this.l = 0;
                break;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int right;
        if (getChildCount() == 0) {
            return false;
        }
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
        this.k.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x2 = motionEvent.getX();
        this.mHandler.removeMessages(1);
        switch (action) {
            case 0:
                if (!this.j.isFinished()) {
                    this.j.abortAnimation();
                }
                this.m = x2;
                this.l = this.j.isFinished() ^ true ? 1 : 0;
                break;
            case 1:
                if (this.l == 1) {
                    VelocityTracker velocityTracker = this.k;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.o);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    if (xVelocity > 1000 && this.p > 0) {
                        a(this.p - 1);
                    } else if (xVelocity >= -1000 || this.p >= getChildCount() - 1) {
                        b();
                    } else {
                        a(this.p + 1);
                    }
                    if (this.k != null) {
                        this.k.recycle();
                        this.k = null;
                    }
                } else {
                    if (this.y != null) {
                        this.y.a(this.p);
                    }
                    b();
                }
                this.l = 0;
                break;
            case 2:
                int i2 = (int) (this.m - x2);
                if (Math.abs(i2) > this.n) {
                    this.l = 1;
                    if (this.t != null) {
                        a((float) i2);
                    }
                }
                if (this.l == 1) {
                    this.m = x2;
                    int scrollX = getScrollX();
                    if (i2 < 0) {
                        if (scrollX > 0) {
                            scrollBy(Math.max(-scrollX, i2), 0);
                        }
                    } else if (i2 > 0 && (right = (((getChildAt(getChildCount() - 1).getRight() - getPaddingRight()) - getHorizontalFadingEdgeLength()) - scrollX) - getChildWidth()) > 0) {
                        scrollBy(Math.min(right, i2), 0);
                    }
                    this.mHandler.sendEmptyMessageDelayed(1, 200);
                    return true;
                }
                break;
            case 3:
                b();
                this.l = 0;
                break;
        }
        return true;
    }

    private void a(float f2) {
        if (f2 > 0.0f) {
            if (this.u.contains(LazyInit.RIGHT)) {
                this.u.remove(LazyInit.RIGHT);
                if (this.g + 1 < this.e.size()) {
                    this.t.a(this.e.get(this.g + 1), this.h + 1);
                }
            }
        } else if (this.u.contains(LazyInit.LEFT)) {
            this.u.remove(LazyInit.LEFT);
            if (this.g > 0) {
                this.t.a(this.e.get(this.g - 1), this.h - 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (this.y != null) {
            this.y.a(i2 + ((this.h - this.g) * getChildWidth()), i3, i4, i5);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        int childWidth = getChildWidth();
        a((getScrollX() + (childWidth / 2)) / childWidth);
    }

    private void a(int i2) {
        this.w = i2 - this.p;
        if (this.j.isFinished()) {
            int max = Math.max(0, Math.min(i2, getChildCount() - 1));
            this.q = max;
            int childWidth = (max * getChildWidth()) - getScrollX();
            this.j.startScroll(getScrollX(), 0, childWidth, 0, Math.abs(childWidth) * 2);
            invalidate();
        }
    }

    public void computeScroll() {
        if (this.j.computeScrollOffset()) {
            scrollTo(this.j.getCurrX(), this.j.getCurrY());
            postInvalidate();
        } else if (this.q != -1) {
            this.p = Math.max(0, Math.min(this.q, getChildCount() - 1));
            this.q = -1;
            post(new Runnable() {
                public void run() {
                    ViewFlow.this.b(ViewFlow.this.w);
                }
            });
        }
    }

    public boolean isViewFlowMoving() {
        return this.l == 1;
    }

    private void a(int i2, boolean z2) {
        this.p = Math.max(0, Math.min(i2, getChildCount() - 1));
        int childWidth = (this.p * getChildWidth()) - this.j.getCurrX();
        this.j.startScroll(this.j.getCurrX(), this.j.getCurrY(), childWidth, 0, 0);
        if (childWidth == 0) {
            onScrollChanged(this.j.getCurrX() + childWidth, this.j.getCurrY(), this.j.getCurrX() + childWidth, this.j.getCurrY());
        }
        if (z2) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public void setOnViewSwitchListener(ViewSwitchListener viewSwitchListener) {
        this.s = viewSwitchListener;
    }

    public void setOnViewLazyInitializeListener(ViewLazyInitializeListener viewLazyInitializeListener) {
        this.t = viewLazyInitializeListener;
    }

    public Adapter getAdapter() {
        return this.v;
    }

    public void setAdapter(Adapter adapter) {
        setAdapter(adapter, 0);
    }

    public void setAdapter(Adapter adapter, int i2) {
        if (this.v != null) {
            this.v.unregisterDataSetObserver(this.x);
        }
        this.v = adapter;
        if (this.v != null) {
            this.x = new AdapterDataSetObserver();
            this.v.registerDataSetObserver(this.x);
        }
        if (this.v != null && this.v.getCount() != 0) {
            setSelection(i2);
        }
    }

    public View getSelectedView() {
        if (this.g < this.e.size()) {
            return this.e.get(this.g);
        }
        return null;
    }

    public int getSelectedItemPosition() {
        return this.h;
    }

    public void setFlowIndicator(FlowIndicator flowIndicator) {
        this.y = flowIndicator;
        this.y.a(this);
    }

    /* access modifiers changed from: protected */
    public void recycleViews() {
        while (!this.e.isEmpty()) {
            recycleView(this.e.remove());
        }
    }

    /* access modifiers changed from: protected */
    public void recycleView(View view) {
        if (view != null) {
            this.f.addFirst(view);
            detachViewFromParent(view);
        }
    }

    /* access modifiers changed from: protected */
    public View getRecycledView() {
        if (this.f.isEmpty()) {
            return null;
        }
        return this.f.remove();
    }

    public void setSelection(int i2) {
        this.q = -1;
        this.j.forceFinished(true);
        if (this.v != null) {
            int min = Math.min(Math.max(i2, 0), this.v.getCount() > 0 ? this.v.getCount() - 1 : 0);
            recycleViews();
            View b2 = b(min, true);
            this.e.addLast(b2);
            if (this.t != null) {
                this.t.a(b2, min);
            }
            for (int i3 = 1; this.i - i3 >= 0; i3++) {
                int i4 = min - i3;
                int i5 = min + i3;
                if (i4 >= 0) {
                    this.e.addFirst(b(i4, false));
                }
                if (i5 < this.v.getCount()) {
                    this.e.addLast(b(i5, true));
                }
            }
            this.g = this.e.indexOf(b2);
            this.h = min;
            requestLayout();
            a(this.g, false);
            if (this.y != null) {
                this.y.a(b2, this.h);
            }
            if (this.s != null) {
                this.s.a(b2, this.h);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        d();
        recycleViews();
        removeAllViewsInLayout();
        this.u.addAll(EnumSet.allOf(LazyInit.class));
        for (int max = Math.max(0, this.h - this.i); max < Math.min(this.v.getCount(), this.h + this.i + 1); max++) {
            this.e.addLast(b(max, true));
            if (max == this.h) {
                this.g = this.e.size() - 1;
                if (this.t != null) {
                    this.t.a(this.e.getLast(), this.h);
                }
            }
        }
        if (this.v.getCount() > 0) {
            setSelection(this.h);
        }
        d();
        requestLayout();
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        if (i2 != 0) {
            if (i2 > 0) {
                this.h++;
                this.g++;
                this.u.remove(LazyInit.LEFT);
                this.u.add(LazyInit.RIGHT);
                if (this.h > this.i) {
                    recycleView(this.e.removeFirst());
                    this.g--;
                }
                int i3 = this.h + this.i;
                if (i3 < this.v.getCount()) {
                    this.e.addLast(b(i3, true));
                }
            } else {
                this.h--;
                this.g--;
                this.u.add(LazyInit.LEFT);
                this.u.remove(LazyInit.RIGHT);
                if ((this.v.getCount() - 1) - this.h > this.i) {
                    recycleView(this.e.removeLast());
                }
                int i4 = this.h - this.i;
                if (i4 > -1) {
                    this.e.addFirst(b(i4, false));
                    this.g++;
                }
            }
            requestLayout();
            if (this.g >= this.e.size()) {
                this.g = this.e.size() - 1;
            }
            if (this.g < 0) {
                this.g = 0;
            }
            a(this.g, true);
            if (this.y != null && this.g >= 0 && this.g < this.e.size()) {
                this.y.a(this.e.get(this.g), this.h);
            }
            if (this.s != null && this.g > 0 && this.g < this.e.size()) {
                this.s.a(this.e.get(this.g), this.h);
            }
            d();
        }
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(getChildMeasureSpec(i2, getWidthPadding(), layoutParams.width), getChildMeasureSpec(i3, getHeightPadding(), layoutParams.height));
    }

    private View a(View view, boolean z2, boolean z3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(getChildWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getChildHeight(), 1073741824));
        int i2 = 0;
        if (z3) {
            if (z2) {
                i2 = -1;
            }
            attachViewToParent(view, i2, layoutParams);
        } else {
            if (z2) {
                i2 = -1;
            }
            addViewInLayout(view, i2, layoutParams, true);
        }
        return view;
    }

    private View b(int i2, boolean z2) {
        return a(c(i2), z2, this.A);
    }

    private View c(int i2) {
        View recycledView = getRecycledView();
        View view = this.v.getView(i2, recycledView, this);
        if (!(view == recycledView || recycledView == null)) {
            this.f.add(recycledView);
        }
        this.A = view == recycledView;
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        }
        return view;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        public void onInvalidated() {
        }

        AdapterDataSetObserver() {
        }

        public void onChanged() {
            View childAt = ViewFlow.this.getChildAt(ViewFlow.this.g);
            if (childAt != null) {
                int i = 0;
                while (true) {
                    if (i >= ViewFlow.this.v.getCount()) {
                        break;
                    } else if (childAt.equals(ViewFlow.this.v.getItem(i))) {
                        int unused = ViewFlow.this.h = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (ViewFlow.this.h < 0) {
                int unused2 = ViewFlow.this.h = 0;
            }
            if (ViewFlow.this.h >= ViewFlow.this.v.getCount()) {
                int unused3 = ViewFlow.this.h = ViewFlow.this.v.getCount() - 1;
            }
            ViewFlow.this.c();
        }
    }

    private void d() {
        Log.d("viewflow", "Size of mLoadedViews: " + this.e.size() + ", Size of mRecycledViews: " + this.f.size() + ", X: " + this.j.getCurrX() + ", Y: " + this.j.getCurrY());
        StringBuilder sb = new StringBuilder();
        sb.append("IndexInAdapter: ");
        sb.append(this.h);
        sb.append(", IndexInBuffer: ");
        sb.append(this.g);
        Log.d("viewflow", sb.toString());
    }
}
