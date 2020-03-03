package com.xiaomi.shopviews.adapter.discover.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;

public class BannerLayoutManager extends RecyclerView.LayoutManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13123a = -1;
    public static final int b = 0;
    public static final int c = 1;
    protected static final int d = Integer.MAX_VALUE;
    private static final int n = -1;
    private static final int o = 0;
    private static final int p = 1;
    private int A;
    private int B;
    private Interpolator C;
    private int D;
    private View E;
    private int F;
    private float G;
    private float H;
    protected int e;
    protected int f;
    int g;
    protected int h;
    protected int i;
    protected float j;
    protected OrientationHelper k;
    protected float l;
    OnPageChangeListener m;
    private SparseArray<View> q;
    private boolean r;
    private boolean s;
    private boolean t;
    private int u;
    private SavedState v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;

    public interface OnPageChangeListener {
        void a(int i);

        void b(int i);
    }

    /* access modifiers changed from: protected */
    public float b(View view, float f2) {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public void i() {
    }

    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return null;
    }

    /* access modifiers changed from: protected */
    public float a() {
        if (this.H == 0.0f) {
            return Float.MAX_VALUE;
        }
        return 1.0f / this.H;
    }

    /* access modifiers changed from: protected */
    public float b() {
        return (((float) this.e) * (((this.G - 1.0f) / 2.0f) + 1.0f)) + ((float) this.F);
    }

    public void a(int i2) {
        this.F = i2;
    }

    public void a(float f2) {
        this.G = f2;
    }

    public void b(float f2) {
        assertNotInLayoutOrScroll((String) null);
        if (this.H != f2) {
            this.H = f2;
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, float f2) {
        float c2 = c(f2 + ((float) this.h));
        view.setScaleX(c2);
        view.setScaleY(c2);
    }

    private float c(float f2) {
        float abs = Math.abs(f2 - (((float) (this.k.getTotalSpace() - this.e)) / 2.0f));
        float f3 = 0.0f;
        if (((float) this.e) - abs > 0.0f) {
            f3 = ((float) this.e) - abs;
        }
        return (((this.G - 1.0f) / ((float) this.e)) * f3) + 1.0f;
    }

    public BannerLayoutManager(Context context) {
        this(context, 0, false);
    }

    public BannerLayoutManager(Context context, int i2) {
        this(context, i2, false);
    }

    public BannerLayoutManager(Context context, int i2, boolean z2) {
        this.q = new SparseArray<>();
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = -1;
        this.v = null;
        this.x = true;
        this.B = -1;
        this.D = Integer.MAX_VALUE;
        this.F = 20;
        this.G = 1.2f;
        this.H = 1.0f;
        e(true);
        c(3);
        b(i2);
        b(z2);
        setAutoMeasureEnabled(true);
        setItemPrefetchEnabled(false);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public boolean c() {
        return this.w;
    }

    public void a(boolean z2) {
        this.w = z2;
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.w) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public Parcelable onSaveInstanceState() {
        if (this.v != null) {
            return new SavedState(this.v);
        }
        SavedState savedState = new SavedState();
        savedState.f13124a = this.u;
        savedState.b = this.j;
        savedState.c = this.s;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.v = new SavedState((SavedState) parcelable);
            requestLayout();
        }
    }

    public boolean canScrollHorizontally() {
        return this.g == 0;
    }

    public boolean canScrollVertically() {
        return this.g == 1;
    }

    public int d() {
        return this.g;
    }

    public void b(int i2) {
        if (i2 == 0 || i2 == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i2 != this.g) {
                this.g = i2;
                this.k = null;
                this.D = Integer.MAX_VALUE;
                removeAllViews();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i2);
    }

    public int e() {
        return this.B;
    }

    public void c(int i2) {
        assertNotInLayoutOrScroll((String) null);
        if (this.B != i2) {
            this.B = i2;
            removeAllViews();
        }
    }

    private void t() {
        if (this.g == 0 && getLayoutDirection() == 1) {
            this.r = !this.r;
        }
    }

    public boolean f() {
        return this.r;
    }

    public void b(boolean z2) {
        assertNotInLayoutOrScroll((String) null);
        if (z2 != this.r) {
            this.r = z2;
            removeAllViews();
        }
    }

    public void a(Interpolator interpolator) {
        this.C = interpolator;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        int d2 = d(i2);
        if (this.g == 1) {
            recyclerView.smoothScrollBy(0, d2, this.C);
        } else {
            recyclerView.smoothScrollBy(d2, 0, this.C);
        }
    }

    public void scrollToPosition(int i2) {
        float f2;
        float f3;
        if (this.x || (i2 >= 0 && i2 < getItemCount())) {
            this.u = i2;
            if (this.s) {
                f2 = (float) i2;
                f3 = -this.l;
            } else {
                f2 = (float) i2;
                f3 = this.l;
            }
            this.j = f2 * f3;
            requestLayout();
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        float f2;
        float f3;
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            this.j = 0.0f;
            return;
        }
        h();
        t();
        View viewForPosition = recycler.getViewForPosition(0);
        measureChildWithMargins(viewForPosition, 0, 0);
        this.e = this.k.getDecoratedMeasurement(viewForPosition);
        this.f = this.k.getDecoratedMeasurementInOther(viewForPosition);
        this.h = (this.k.getTotalSpace() - this.e) / 2;
        if (this.D == Integer.MAX_VALUE) {
            this.i = (g() - this.f) / 2;
        } else {
            this.i = (g() - this.f) - this.D;
        }
        this.l = b();
        i();
        this.z = ((int) Math.abs(m() / this.l)) + 1;
        this.A = ((int) Math.abs(l() / this.l)) + 1;
        if (this.v != null) {
            this.s = this.v.c;
            this.u = this.v.f13124a;
            this.j = this.v.b;
        }
        if (this.u != -1) {
            if (this.s) {
                f2 = (float) this.u;
                f3 = -this.l;
            } else {
                f2 = (float) this.u;
                f3 = this.l;
            }
            this.j = f2 * f3;
        }
        detachAndScrapAttachedViews(recycler);
        a(recycler);
    }

    public int g() {
        if (this.g == 0) {
            return (getHeight() - getPaddingTop()) - getPaddingBottom();
        }
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.v = null;
        this.u = -1;
    }

    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i3) {
        int n2 = n();
        View findViewByPosition = findViewByPosition(n2);
        if (findViewByPosition == null) {
            return true;
        }
        if (recyclerView.hasFocus()) {
            int f2 = f(i2);
            if (f2 != -1) {
                recyclerView.smoothScrollToPosition(f2 == 1 ? n2 - 1 : n2 + 1);
            }
        } else {
            findViewByPosition.addFocusables(arrayList, i2, i3);
        }
        return true;
    }

    private int f(int i2) {
        if (this.g == 1) {
            if (i2 == 33) {
                return this.s ^ true ? 1 : 0;
            }
            if (i2 == 130) {
                return this.s ? 1 : 0;
            }
            return -1;
        } else if (i2 == 17) {
            return this.s ^ true ? 1 : 0;
        } else {
            if (i2 == 66) {
                return this.s ? 1 : 0;
            }
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        if (this.k == null) {
            this.k = OrientationHelper.createOrientationHelper(this, this.g);
        }
    }

    private float g(int i2) {
        float f2;
        float f3;
        if (this.s) {
            f2 = (float) i2;
            f3 = -this.l;
        } else {
            f2 = (float) i2;
            f3 = this.l;
        }
        return f2 * f3;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
        this.j = 0.0f;
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return u();
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return u();
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return v();
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return v();
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return w();
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return w();
    }

    private int u() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.t) {
            return !this.s ? n() : (getItemCount() - n()) - 1;
        }
        float z2 = z();
        return !this.s ? (int) z2 : (int) ((((float) (getItemCount() - 1)) * this.l) + z2);
    }

    private int v() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.t) {
            return 1;
        }
        return (int) this.l;
    }

    private int w() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.t) {
            return getItemCount();
        }
        return (int) (((float) getItemCount()) * this.l);
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.g == 1) {
            return 0;
        }
        return a(i2, recycler, state);
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.g == 0) {
            return 0;
        }
        return a(i2, recycler, state);
    }

    private int a(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        h();
        float f2 = (float) i2;
        float a2 = f2 / a();
        if (Math.abs(a2) < 1.0E-8f) {
            return 0;
        }
        float f3 = this.j + a2;
        if (!this.x && f3 < k()) {
            i2 = (int) (f2 - ((f3 - k()) * a()));
        } else if (!this.x && f3 > j()) {
            i2 = (int) ((j() - this.j) * a());
        }
        this.j += ((float) i2) / a();
        a(recycler);
        return i2;
    }

    private void a(RecyclerView.Recycler recycler) {
        int i2;
        int i3;
        int i4;
        detachAndScrapAttachedViews(recycler);
        this.q.clear();
        int itemCount = getItemCount();
        if (itemCount != 0) {
            int y2 = this.s ? -y() : y();
            int i5 = y2 - this.z;
            int i6 = this.A + y2;
            if (x()) {
                if (this.B % 2 == 0) {
                    int i7 = this.B / 2;
                    i2 = (y2 - i7) + 1;
                    i3 = i7 + y2 + 1;
                } else {
                    int i8 = (this.B - 1) / 2;
                    i2 = y2 - i8;
                    i3 = i8 + y2 + 1;
                }
            } else {
                i2 = i5;
                i3 = i6;
            }
            if (!this.x) {
                if (i2 < 0) {
                    if (x()) {
                        i3 = this.B;
                    }
                    i2 = 0;
                }
                if (i3 > itemCount) {
                    i3 = itemCount;
                }
            }
            float f2 = Float.MIN_VALUE;
            while (i2 < i3) {
                if (x() || !d(g(i2) - this.j)) {
                    if (i2 >= itemCount) {
                        i4 = i2 % itemCount;
                    } else if (i2 < 0) {
                        int i9 = (-i2) % itemCount;
                        if (i9 == 0) {
                            i9 = itemCount;
                        }
                        i4 = itemCount - i9;
                    } else {
                        i4 = i2;
                    }
                    View viewForPosition = recycler.getViewForPosition(i4);
                    measureChildWithMargins(viewForPosition, 0, 0);
                    a(viewForPosition);
                    float g2 = g(i2) - this.j;
                    e(viewForPosition, g2);
                    float b2 = this.y ? b(viewForPosition, g2) : (float) i4;
                    if (b2 > f2) {
                        addView(viewForPosition);
                    } else {
                        addView(viewForPosition, 0);
                    }
                    if (i2 == y2) {
                        this.E = viewForPosition;
                    }
                    this.q.put(i2, viewForPosition);
                    f2 = b2;
                }
                i2++;
            }
            this.E.requestFocus();
        }
    }

    private boolean x() {
        return this.B != -1;
    }

    private boolean d(float f2) {
        return f2 > l() || f2 < m();
    }

    private void a(View view) {
        view.setRotation(0.0f);
        view.setRotationY(0.0f);
        view.setRotationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setAlpha(1.0f);
    }

    /* access modifiers changed from: package-private */
    public float j() {
        if (!this.s) {
            return ((float) (getItemCount() - 1)) * this.l;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public float k() {
        if (!this.s) {
            return 0.0f;
        }
        return ((float) (-(getItemCount() - 1))) * this.l;
    }

    private void e(View view, float f2) {
        int c2 = c(view, f2);
        int d2 = d(view, f2);
        if (this.g == 1) {
            layoutDecorated(view, this.i + c2, this.h + d2, this.i + c2 + this.f, this.h + d2 + this.e);
        } else {
            layoutDecorated(view, this.h + c2, this.i + d2, this.h + c2 + this.e, this.i + d2 + this.f);
        }
        a(view, f2);
    }

    /* access modifiers changed from: protected */
    public int c(View view, float f2) {
        if (this.g == 1) {
            return 0;
        }
        return (int) f2;
    }

    /* access modifiers changed from: protected */
    public int d(View view, float f2) {
        if (this.g == 1) {
            return (int) f2;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public float l() {
        return (float) (this.k.getTotalSpace() - this.h);
    }

    /* access modifiers changed from: protected */
    public float m() {
        return (float) (((-this.e) - this.k.getStartAfterPadding()) - this.h);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int n() {
        /*
            r4 = this;
            int r0 = r4.getItemCount()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            int r0 = r4.y()
            boolean r2 = r4.x
            if (r2 != 0) goto L_0x0015
            int r0 = java.lang.Math.abs(r0)
            return r0
        L_0x0015:
            boolean r2 = r4.s
            if (r2 != 0) goto L_0x002d
            if (r0 < 0) goto L_0x0021
            int r2 = r4.getItemCount()
            int r0 = r0 % r2
            goto L_0x0040
        L_0x0021:
            int r2 = r4.getItemCount()
            int r3 = r4.getItemCount()
            int r0 = r0 % r3
            int r2 = r2 + r0
        L_0x002b:
            r0 = r2
            goto L_0x0040
        L_0x002d:
            if (r0 <= 0) goto L_0x003a
            int r2 = r4.getItemCount()
            int r3 = r4.getItemCount()
            int r0 = r0 % r3
            int r2 = r2 - r0
            goto L_0x002b
        L_0x003a:
            int r0 = -r0
            int r2 = r4.getItemCount()
            int r0 = r0 % r2
        L_0x0040:
            int r2 = r4.getItemCount()
            if (r0 != r2) goto L_0x0047
            r0 = 0
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.adapter.discover.widget.BannerLayoutManager.n():int");
    }

    public View findViewByPosition(int i2) {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return null;
        }
        for (int i3 = 0; i3 < this.q.size(); i3++) {
            int keyAt = this.q.keyAt(i3);
            if (keyAt < 0) {
                int i4 = keyAt % itemCount;
                if (i4 == 0) {
                    i4 = -itemCount;
                }
                if (i4 + itemCount == i2) {
                    return this.q.valueAt(i3);
                }
            } else if (i2 == keyAt % itemCount) {
                return this.q.valueAt(i3);
            }
        }
        return null;
    }

    private int y() {
        return Math.round(this.j / this.l);
    }

    private float z() {
        if (this.s) {
            if (!this.x) {
                return this.j;
            }
            if (this.j <= 0.0f) {
                return this.j % (this.l * ((float) getItemCount()));
            }
            return (((float) getItemCount()) * (-this.l)) + (this.j % (this.l * ((float) getItemCount())));
        } else if (!this.x) {
            return this.j;
        } else {
            if (this.j >= 0.0f) {
                return this.j % (this.l * ((float) getItemCount()));
            }
            return (((float) getItemCount()) * this.l) + (this.j % (this.l * ((float) getItemCount())));
        }
    }

    public int o() {
        if (this.x) {
            return (int) (((((float) y()) * this.l) - this.j) * a());
        }
        return (int) (((((float) n()) * (!this.s ? this.l : -this.l)) - this.j) * a());
    }

    public int d(int i2) {
        if (this.x) {
            return (int) (((((float) (y() + (!this.s ? i2 - n() : n() - i2))) * this.l) - this.j) * a());
        }
        return (int) (((((float) i2) * (!this.s ? this.l : -this.l)) - this.j) * a());
    }

    public void a(OnPageChangeListener onPageChangeListener) {
        this.m = onPageChangeListener;
    }

    public void c(boolean z2) {
        assertNotInLayoutOrScroll((String) null);
        if (z2 != this.x) {
            this.x = z2;
            requestLayout();
        }
    }

    public boolean p() {
        return this.x;
    }

    public int q() {
        return this.D == Integer.MAX_VALUE ? (g() - this.f) / 2 : this.D;
    }

    public void e(int i2) {
        assertNotInLayoutOrScroll((String) null);
        if (this.D != i2) {
            this.D = i2;
            removeAllViews();
        }
    }

    public void d(boolean z2) {
        this.t = z2;
    }

    public void e(boolean z2) {
        assertNotInLayoutOrScroll((String) null);
        if (this.y != z2) {
            this.y = z2;
            requestLayout();
        }
    }

    public boolean r() {
        return this.y;
    }

    public boolean s() {
        return this.t;
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f13124a;
        float b;
        boolean c;

        public int describeContents() {
            return 0;
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.f13124a = parcel.readInt();
            this.b = parcel.readFloat();
            this.c = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.f13124a = savedState.f13124a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f13124a);
            parcel.writeFloat(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }
}
