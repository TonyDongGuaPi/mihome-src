package com.tmall.wireless.vaf.virtualview.view.slider;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import com.tmall.wireless.vaf.virtualview.core.Adapter;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import java.util.ArrayList;
import java.util.List;

public class SliderView extends ViewGroup {
    protected static final int MAX_ITEM_COUNT = 5;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9423a = "SliderView_TMTEST";
    private int b;
    private int c;
    private int d;
    private int e;
    private int f = 1;
    private VelocityTracker g;
    private int h = ViewConfiguration.getMaximumFlingVelocity();
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    protected Adapter mAdapter;
    protected boolean mDataChanged = true;
    protected int mDownPos;
    protected SparseArray<List<Adapter.ViewHolder>> mItemCache = new SparseArray<>();
    protected Listener mListener;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private ObjectAnimator t;
    private int u;
    private int v;
    private int w;

    public interface Listener {
        void i(int i, int i2);
    }

    public void setSpan(int i2) {
        this.w = i2;
    }

    public SliderView(Context context) {
        super(context);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setOrientation(int i2) {
        this.f = i2;
    }

    public void setItemWidth(int i2) {
        this.e = i2;
        this.b = this.e >> 1;
        this.c = this.e << 1;
    }

    public void refresh() {
        if (this.mDataChanged) {
            removeAll();
            this.mDataChanged = false;
            this.o = this.mAdapter.a();
            this.p = ((this.o * this.e) + ((this.o - 1) * this.w)) - this.d;
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void add(int i2) {
        add(i2, -1);
    }

    /* access modifiers changed from: protected */
    public void add(int i2, int i3) {
        Adapter.ViewHolder viewHolder;
        int b2 = this.mAdapter.b(i2);
        List list = this.mItemCache.get(b2);
        if (list == null || list.size() <= 0) {
            Adapter.ViewHolder c2 = this.mAdapter.c(b2);
            c2.b = b2;
            c2.c = i2;
            viewHolder = c2;
        } else {
            viewHolder = (Adapter.ViewHolder) list.remove(0);
            viewHolder.c = i2;
        }
        this.mAdapter.a(viewHolder, i2);
        if (i3 < 0) {
            addView(viewHolder.f9380a);
        } else {
            addView(viewHolder.f9380a, i3);
        }
    }

    private void a() {
        int a2;
        if (this.mAdapter != null && (a2 = this.mAdapter.a()) > 0) {
            int i2 = 0;
            this.j = 0;
            this.m = 0;
            this.l = 0;
            int i3 = this.d + this.e + this.w;
            int i4 = a2 - 1;
            this.n = i4;
            int i5 = 0;
            while (true) {
                if (i2 >= a2) {
                    break;
                }
                add(i2);
                i5 += this.e;
                if (i2 < i4) {
                    i5 += this.w;
                }
                if (i5 >= i3) {
                    this.n = i2;
                    break;
                }
                i2++;
            }
            this.k = i5 - this.d;
        }
    }

    /* access modifiers changed from: protected */
    public void removeAll() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            b(i2);
        }
        removeAllViews();
    }

    private void a(int i2) {
        b(i2);
        removeViewAt(i2);
    }

    private void b(int i2) {
        Adapter.ViewHolder viewHolder = (Adapter.ViewHolder) getChildAt(i2).getTag();
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

    private void a(MotionEvent motionEvent) {
        if (this.g == null) {
            this.g = VelocityTracker.obtain();
        }
        this.g.addMovement(motionEvent);
    }

    private void b() {
        if (this.g != null) {
            this.g.clear();
            this.g.recycle();
            this.g = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a(motionEvent);
        b(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                if (1 == this.f) {
                    this.mDownPos = x;
                } else {
                    this.mDownPos = y;
                }
                this.q = x;
                this.r = y;
                this.s = motionEvent.getPointerId(0);
                this.i = x;
                if (this.t != null) {
                    this.t.cancel();
                    break;
                }
                break;
            case 2:
                int i2 = x - this.q;
                int i3 = y - this.r;
                if (1 == this.f) {
                    if (Math.abs(i2) > Math.abs(i3)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                } else if (Math.abs(i3) > Math.abs(i2)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                break;
        }
        return false;
    }

    private void c(int i2) {
        if (i2 < 0) {
            if (this.k + i2 < 0) {
                i2 = -this.k;
            }
        } else if (i2 <= 0) {
            return;
        } else {
            if (this.j - i2 < 0) {
                i2 = this.j;
            }
        }
        if (i2 != 0) {
            int i3 = -i2;
            this.l += i3;
            this.i += i2;
            scrollBy(i3, 0);
            this.j -= i2;
            this.k += i2;
            if (this.mListener != null) {
                this.mListener.i(this.l, this.p);
            }
        }
        if (this.j >= this.c) {
            if (this.m < getChildCount() - 1) {
                a(0);
                this.m++;
                this.j -= this.e + this.w;
                scrollBy((-this.e) - this.w, 0);
            }
        } else if (this.j <= this.b && this.m > 0) {
            int i4 = this.m - 1;
            this.m = i4;
            add(i4, 0);
            scrollBy(this.e + this.w, 0);
            this.j += this.e + this.w;
        }
        if (this.k >= this.c) {
            if (this.n > 0) {
                a(getChildCount() - 1);
                this.n--;
                this.k -= this.e + this.w;
            }
        } else if (this.k <= this.b && this.n < this.o - 1) {
            int i5 = this.n + 1;
            this.n = i5;
            add(i5);
            this.k += this.e + this.w;
        }
    }

    public void setAutoScrollX(int i2) {
        c(i2 - this.v);
        if (this.u < 0) {
            if (this.k == 0) {
                this.t.cancel();
            }
        } else if (this.j == 0) {
            this.t.cancel();
        }
        this.v = i2;
    }

    private void b(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        switch (motionEvent.getAction()) {
            case 0:
                this.i = x;
                if (this.t != null) {
                    this.t.cancel();
                    return;
                }
                return;
            case 1:
            case 3:
                this.g.computeCurrentVelocity(1, (float) this.h);
                float xVelocity = this.g.getXVelocity(this.s);
                this.g.getYVelocity(this.s);
                int i2 = ((int) xVelocity) * this.u;
                if (this.u > 0) {
                    i2 = -i2;
                }
                this.v = i2;
                this.t = ObjectAnimator.ofInt(this, "autoScrollX", new int[]{i2, 0});
                this.t.setInterpolator(new DecelerateInterpolator());
                this.t.setDuration(300).start();
                b();
                return;
            case 2:
                this.u = x - this.i;
                c(this.u);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.d = View.MeasureSpec.getSize(i2);
        int size = View.MeasureSpec.getSize(i3);
        refresh();
        measureChildren(View.MeasureSpec.makeMeasureSpec(this.e, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
        setMeasuredDimension(this.d, size);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingBottom = ((i5 - i3) - paddingTop) - getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        for (int i6 = 0; i6 < childCount; i6++) {
            getChildAt(i6).layout(paddingLeft, paddingTop, this.e + paddingLeft, paddingBottom);
            paddingLeft += this.e + this.w;
        }
    }
}
