package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.utils.BaseWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.util.List;

class SwipeableItemWrapperAdapter<VH extends RecyclerView.ViewHolder> extends BaseWrapperAdapter<VH> {
    private static final String b = "ARVSwipeableWrapper";
    private static final int c = -1;
    private static final boolean d = false;
    private static final boolean e = false;
    private BaseSwipeableItemAdapter f;
    private RecyclerViewSwipeManager g;
    private long h = -1;

    private interface Constants extends SwipeableItemConstants {
    }

    private static float d(int i, int i2) {
        switch (i2) {
            case 0:
                return 0.0f;
            case 1:
            case 2:
                switch (i) {
                    case 2:
                        return -65536.0f;
                    case 3:
                        return -65537.0f;
                    case 4:
                        return 65536.0f;
                    case 5:
                        return 65537.0f;
                    default:
                        return 0.0f;
                }
            default:
                return 0.0f;
        }
    }

    public SwipeableItemWrapperAdapter(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.f = a(adapter);
        if (this.f == null) {
            throw new IllegalArgumentException("adapter does not implement SwipeableItemAdapter");
        } else if (recyclerViewSwipeManager != null) {
            this.g = recyclerViewSwipeManager;
        } else {
            throw new IllegalArgumentException("manager cannot be null");
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.f = null;
        this.g = null;
        this.h = -1;
    }

    public void onViewRecycled(VH vh) {
        super.onViewRecycled(vh);
        if (this.h != -1 && this.h == vh.getItemId()) {
            this.g.d();
        }
        if (vh instanceof SwipeableItemViewHolder) {
            if (this.g != null) {
                this.g.b((RecyclerView.ViewHolder) vh);
            }
            SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) vh;
            swipeableItemViewHolder.a(0.0f);
            swipeableItemViewHolder.b(0.0f);
            View k = swipeableItemViewHolder.k();
            if (k != null) {
                ViewCompat.animate(k).cancel();
                ViewCompat.setTranslationX(k, 0.0f);
                ViewCompat.setTranslationY(k, 0.0f);
            }
        }
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        VH onCreateViewHolder = super.onCreateViewHolder(viewGroup, i);
        if (onCreateViewHolder instanceof SwipeableItemViewHolder) {
            ((SwipeableItemViewHolder) onCreateViewHolder).b(-1);
        }
        return onCreateViewHolder;
    }

    public void onBindViewHolder(VH vh, int i, List<Object> list) {
        boolean z = vh instanceof SwipeableItemViewHolder;
        float a2 = z ? a((SwipeableItemViewHolder) vh, e()) : 0.0f;
        if (c()) {
            int i2 = 1;
            if (vh.getItemId() == this.h) {
                i2 = 3;
            }
            a((RecyclerView.ViewHolder) vh, i2);
            super.onBindViewHolder(vh, i, list);
        } else {
            a((RecyclerView.ViewHolder) vh, 0);
            super.onBindViewHolder(vh, i, list);
        }
        if (z) {
            float a3 = a((SwipeableItemViewHolder) vh, e());
            boolean c2 = this.g.c();
            boolean a4 = this.g.a((RecyclerView.ViewHolder) vh);
            if (a2 != a3 || (!c2 && !a4)) {
                this.g.a(vh, i, a2, a3, e(), true, c2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (c()) {
            d();
        } else {
            super.b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
        if (c()) {
            d();
        } else {
            super.a(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void a_(int i, int i2) {
        if (c()) {
            d();
        } else {
            super.a_(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2) {
        if (c()) {
            d();
        } else {
            super.c(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3) {
        if (c()) {
            d();
        } else {
            super.b(i, i2, i3);
        }
    }

    private void d() {
        if (this.g != null) {
            this.g.d();
        }
    }

    /* access modifiers changed from: package-private */
    public int a(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        return this.f.b(viewHolder, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder, int i, boolean z, float f2, boolean z2, int i2) {
        this.f.a(viewHolder, i, i2);
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        float f3 = 0.0f;
        float f4 = z ? f2 : 0.0f;
        if (!z) {
            f3 = f2;
        }
        swipeableItemViewHolder.a(f4, f3, z2);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder, int i, boolean z, float f2, boolean z2) {
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        float f3 = 0.0f;
        float f4 = z ? f2 : 0.0f;
        if (!z) {
            f3 = f2;
        }
        swipeableItemViewHolder.a(f4, f3, z2);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.ViewHolder viewHolder, long j) {
        this.h = j;
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public SwipeResultAction a(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        this.h = -1;
        return SwipeableItemInternalUtils.a(this.f, viewHolder, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, SwipeResultAction swipeResultAction) {
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        swipeableItemViewHolder.c(i2);
        swipeableItemViewHolder.d(i3);
        a(swipeableItemViewHolder, d(i2, i3), e());
        swipeResultAction.b();
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.h != -1;
    }

    private boolean e() {
        return this.g.h();
    }

    private static float a(SwipeableItemViewHolder swipeableItemViewHolder, boolean z) {
        if (z) {
            return swipeableItemViewHolder.e();
        }
        return swipeableItemViewHolder.f();
    }

    private static void a(SwipeableItemViewHolder swipeableItemViewHolder, float f2, boolean z) {
        if (z) {
            swipeableItemViewHolder.a(f2);
        } else {
            swipeableItemViewHolder.b(f2);
        }
    }

    private static void a(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
            int b2 = swipeableItemViewHolder.b();
            if (b2 == -1 || ((b2 ^ i) & Integer.MAX_VALUE) != 0) {
                i |= Integer.MIN_VALUE;
            }
            swipeableItemViewHolder.b(i);
        }
    }

    private static BaseSwipeableItemAdapter a(RecyclerView.Adapter adapter) {
        return (BaseSwipeableItemAdapter) WrapperAdapterUtils.a(adapter, BaseSwipeableItemAdapter.class);
    }
}
