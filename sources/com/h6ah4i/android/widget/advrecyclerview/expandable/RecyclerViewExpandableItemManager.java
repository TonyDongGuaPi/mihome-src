package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;

public class RecyclerViewExpandableItemManager implements ExpandableItemConstants {
    public static final long f = -1;
    private static final String g = "ARVExpandableItemMgr";
    private SavedState h;
    private RecyclerView i;
    private ExpandableRecyclerViewWrapperAdapter j;
    private RecyclerView.OnItemTouchListener k = new RecyclerView.OnItemTouchListener() {
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }

        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return RecyclerViewExpandableItemManager.this.a(recyclerView, motionEvent);
        }
    };
    private OnGroupExpandListener l;
    private OnGroupCollapseListener m;
    private long n = -1;
    private int o;
    private int p;
    private int q;

    public interface OnGroupCollapseListener {
        void a(int i, boolean z);
    }

    public interface OnGroupExpandListener {
        void b(int i, boolean z);
    }

    public RecyclerViewExpandableItemManager(@Nullable Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.h = (SavedState) parcelable;
        }
    }

    public boolean a() {
        return this.k == null;
    }

    public void a(@NonNull RecyclerView recyclerView) {
        if (a()) {
            throw new IllegalStateException("Accessing released object");
        } else if (this.i == null) {
            this.i = recyclerView;
            this.i.addOnItemTouchListener(this.k);
            this.o = ViewConfiguration.get(this.i.getContext()).getScaledTouchSlop();
        } else {
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
    }

    public void b() {
        if (!(this.i == null || this.k == null)) {
            this.i.removeOnItemTouchListener(this.k);
        }
        this.k = null;
        this.l = null;
        this.m = null;
        this.i = null;
        this.h = null;
    }

    public RecyclerView.Adapter a(@NonNull RecyclerView.Adapter adapter) {
        if (!adapter.hasStableIds()) {
            throw new IllegalArgumentException("The passed adapter does not support stable IDs");
        } else if (this.j == null) {
            int[] iArr = this.h != null ? this.h.f5717a : null;
            this.h = null;
            this.j = new ExpandableRecyclerViewWrapperAdapter(this, adapter, iArr);
            this.j.a(this.l);
            this.l = null;
            this.j.a(this.m);
            this.m = null;
            return this.j;
        } else {
            throw new IllegalStateException("already have a wrapped adapter");
        }
    }

    public Parcelable c() {
        return new SavedState(this.j != null ? this.j.e() : null);
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.j == null) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    b(recyclerView, motionEvent);
                    break;
                case 1:
                    break;
            }
        }
        return c(recyclerView, motionEvent) ? false : false;
    }

    private void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder b = CustomRecyclerViewUtils.b(recyclerView, motionEvent.getX(), motionEvent.getY());
        this.p = (int) (motionEvent.getX() + 0.5f);
        this.q = (int) (motionEvent.getY() + 0.5f);
        if (b instanceof ExpandableItemViewHolder) {
            this.n = b.getItemId();
        } else {
            this.n = -1;
        }
    }

    private boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder b;
        int a2;
        long j2 = this.n;
        int i2 = this.p;
        int i3 = this.q;
        this.n = -1;
        this.p = 0;
        this.q = 0;
        if (j2 == -1 || MotionEventCompat.getActionMasked(motionEvent) != 1) {
            return false;
        }
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        int i4 = y - i3;
        if (Math.abs(x - i2) >= this.o || Math.abs(i4) >= this.o || (b = CustomRecyclerViewUtils.b(recyclerView, motionEvent.getX(), motionEvent.getY())) == null || b.getItemId() != j2 || (a2 = CustomRecyclerViewUtils.a(b)) == -1) {
            return false;
        }
        View view = b.itemView;
        int left = view.getLeft();
        int top = y - (view.getTop() + ((int) (ViewCompat.getTranslationY(view) + 0.5f)));
        return this.j.c(b, a2, x - (left + ((int) (ViewCompat.getTranslationX(view) + 0.5f))), top);
    }

    public void d() {
        if (this.j != null) {
            this.j.c();
        }
    }

    public void e() {
        if (this.j != null) {
            this.j.d();
        }
    }

    public boolean a(int i2) {
        return this.j != null && this.j.b(i2, false);
    }

    public boolean b(int i2) {
        return this.j != null && this.j.a(i2, false);
    }

    public long c(int i2) {
        if (this.j == null) {
            return -1;
        }
        return this.j.b(i2);
    }

    public int a(long j2) {
        if (this.j == null) {
            return -1;
        }
        return this.j.a(j2);
    }

    public static int b(long j2) {
        return ExpandableAdapterHelper.a(j2);
    }

    public static long a(int i2, int i3) {
        return ExpandableAdapterHelper.a(i2, i3);
    }

    public static long d(int i2) {
        return ExpandableAdapterHelper.a(i2);
    }

    public static int c(long j2) {
        return ExpandableAdapterHelper.b(j2);
    }

    public boolean e(int i2) {
        return this.j != null && this.j.a(i2);
    }

    public static long a(long j2, long j3) {
        return ExpandableAdapterHelper.a(j2, j3);
    }

    public static long d(long j2) {
        return ExpandableAdapterHelper.c(j2);
    }

    public static boolean f(int i2) {
        return ExpandableAdapterHelper.b(i2);
    }

    public static int g(int i2) {
        return ExpandableAdapterHelper.c(i2);
    }

    public static int h(int i2) {
        return ExpandableAdapterHelper.d(i2);
    }

    public void a(@Nullable OnGroupExpandListener onGroupExpandListener) {
        if (this.j != null) {
            this.j.a(onGroupExpandListener);
        } else {
            this.l = onGroupExpandListener;
        }
    }

    public void a(@Nullable OnGroupCollapseListener onGroupCollapseListener) {
        if (this.j != null) {
            this.j.a(onGroupCollapseListener);
        } else {
            this.m = onGroupCollapseListener;
        }
    }

    public void a(@Nullable Parcelable parcelable) {
        a(parcelable, false, false);
    }

    public void a(@Nullable Parcelable parcelable, boolean z, boolean z2) {
        if (parcelable != null) {
            if (!(parcelable instanceof SavedState)) {
                throw new IllegalArgumentException("Illegal saved state object passed");
            } else if (this.j == null || this.i == null) {
                throw new IllegalStateException("RecyclerView has not been attached");
            } else {
                this.j.a(((SavedState) parcelable).f5717a, z, z2);
            }
        }
    }

    public void i(int i2) {
        this.j.c(i2);
    }

    public void j(int i2) {
        this.j.a(i2, (Object) null);
    }

    public void a(int i2, Object obj) {
        this.j.a(i2, obj);
    }

    public void k(int i2) {
        this.j.b(i2, (Object) null);
    }

    public void b(int i2, Object obj) {
        this.j.b(i2, obj);
    }

    public void b(int i2, int i3) {
        this.j.a(i2, i3, (Object) null);
    }

    public void a(int i2, int i3, Object obj) {
        this.j.a(i2, i3, obj);
    }

    public void a(int i2, int i3, int i4) {
        this.j.a(i2, i3, i4, (Object) null);
    }

    public void a(int i2, int i3, int i4, Object obj) {
        this.j.a(i2, i3, i4, obj);
    }

    public void l(int i2) {
        a(i2, false);
    }

    public void a(int i2, boolean z) {
        this.j.c(i2, z);
    }

    public void c(int i2, int i3) {
        a(i2, i3, false);
    }

    public void a(int i2, int i3, boolean z) {
        this.j.a(i2, i3, z);
    }

    public void d(int i2, int i3) {
        this.j.d(i2, i3);
    }

    public void b(int i2, int i3, int i4) {
        this.j.a(i2, i3, i4);
    }

    public void m(int i2) {
        this.j.d(i2);
    }

    public void e(int i2, int i3) {
        this.j.f(i2, i3);
    }

    public void f(int i2, int i3) {
        this.j.e(i2, i3);
    }

    public void c(int i2, int i3, int i4) {
        this.j.c(i2, i3, i4);
    }

    public int f() {
        return this.j.f();
    }

    public int n(int i2) {
        return this.j.e(i2);
    }

    public void g(int i2, int i3) {
        a(i2, i3, 0, 0);
    }

    public void a(int i2, int i3, int i4, int i5) {
        b(i2, n(i2) * i3, i4, i5);
    }

    public void b(int i2, int i3, int i4, int i5) {
        int a2 = a(d(i2));
        RecyclerView.ViewHolder findViewHolderForLayoutPosition = this.i.findViewHolderForLayoutPosition(a2);
        if (findViewHolderForLayoutPosition != null) {
            if (!e(i2)) {
                i3 = 0;
            }
            int top = findViewHolderForLayoutPosition.itemView.getTop();
            int height = this.i.getHeight() - findViewHolderForLayoutPosition.itemView.getBottom();
            if (top <= i4) {
                int paddingTop = i4 - this.i.getPaddingTop();
                ((LinearLayoutManager) this.i.getLayoutManager()).scrollToPositionWithOffset(a2, paddingTop - ((RecyclerView.LayoutParams) findViewHolderForLayoutPosition.itemView.getLayoutParams()).topMargin);
                return;
            }
            int i6 = i3 + i5;
            if (height < i6) {
                this.i.smoothScrollBy(0, Math.min(top - i4, Math.max(0, i6 - height)));
            }
        }
    }

    public int g() {
        return this.j.g();
    }

    public int h() {
        return this.j.h();
    }

    public boolean i() {
        return this.j.i();
    }

    public boolean j() {
        return this.j.j();
    }

    public static class SavedState implements Parcelable {
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
        final int[] f5717a;

        public int describeContents() {
            return 0;
        }

        public SavedState(int[] iArr) {
            this.f5717a = iArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeIntArray(this.f5717a);
        }

        SavedState(Parcel parcel) {
            this.f5717a = parcel.createIntArray();
        }
    }
}
