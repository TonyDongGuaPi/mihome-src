package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.BaseSwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemInternalUtils;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.utils.BaseWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.util.List;

class DraggableItemWrapperAdapter<VH extends RecyclerView.ViewHolder> extends BaseWrapperAdapter<VH> implements SwipeableItemAdapter<VH> {
    private static final String b = "ARVDraggableWrapper";
    private static final int c = -1;
    private static final boolean d = false;
    private static final boolean e = false;
    private static final boolean f = true;
    private static final boolean g = false;
    private RecyclerViewDragDropManager h;
    private DraggableItemAdapter i;
    private RecyclerView.ViewHolder j;
    private DraggingItemInfo k;
    private ItemDraggableRange l;
    private int m = -1;
    private int n = -1;

    private interface Constants extends DraggableItemConstants {
    }

    protected static int a(int i2, int i3, int i4) {
        return (i3 < 0 || i4 < 0 || i3 == i4 || (i2 < i3 && i2 < i4) || (i2 > i3 && i2 > i4)) ? i2 : i4 < i3 ? i2 == i4 ? i3 : i2 - 1 : i2 == i4 ? i3 : i2 + 1;
    }

    public DraggableItemWrapperAdapter(RecyclerViewDragDropManager recyclerViewDragDropManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.i = a((RecyclerView.Adapter) adapter);
        if (a((RecyclerView.Adapter) adapter) == null) {
            throw new IllegalArgumentException("adapter does not implement DraggableItemAdapter");
        } else if (recyclerViewDragDropManager != null) {
            this.h = recyclerViewDragDropManager;
        } else {
            throw new IllegalArgumentException("manager cannot be null");
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.j = null;
        this.i = null;
        this.h = null;
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        VH onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        if (onCreateViewHolder instanceof DraggableItemViewHolder) {
            ((DraggableItemViewHolder) onCreateViewHolder).a(-1);
        }
        return onCreateViewHolder;
    }

    public void onBindViewHolder(VH vh, int i2, List<Object> list) {
        if (c()) {
            long j2 = this.k.c;
            long itemId = vh.getItemId();
            int a2 = a(i2, this.m, this.n);
            if (itemId == j2 && vh != this.j) {
                if (this.j != null) {
                    h();
                }
                Log.i(b, "a new view holder object for the currently dragging item is assigned");
                this.j = vh;
                this.h.a((RecyclerView.ViewHolder) vh);
            }
            int i3 = 1;
            if (itemId == j2) {
                i3 = 3;
            }
            if (this.l.a(i2)) {
                i3 |= 4;
            }
            b(vh, i3);
            super.onBindViewHolder(vh, a2, list);
            return;
        }
        b(vh, 0);
        super.onBindViewHolder(vh, i2, list);
    }

    public long getItemId(int i2) {
        if (c()) {
            return super.getItemId(a(i2, this.m, this.n));
        }
        return super.getItemId(i2);
    }

    public int getItemViewType(int i2) {
        if (c()) {
            return super.getItemViewType(a(i2, this.m, this.n));
        }
        return super.getItemViewType(i2);
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (f()) {
            g();
        } else {
            super.b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3) {
        if (f()) {
            g();
        } else {
            super.a(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void a_(int i2, int i3) {
        if (f()) {
            g();
        } else {
            super.a_(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void c(int i2, int i3) {
        if (f()) {
            g();
        } else {
            super.c(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3, int i4) {
        if (f()) {
            g();
        } else {
            super.b(i2, i3, i4);
        }
    }

    private boolean f() {
        return c();
    }

    private void g() {
        if (this.h != null) {
            this.h.j();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        if (viewHolder.getItemId() != -1) {
            int adapterPosition = viewHolder.getAdapterPosition();
            this.n = adapterPosition;
            this.m = adapterPosition;
            this.k = draggingItemInfo;
            this.j = viewHolder;
            this.l = itemDraggableRange;
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("dragging target must provides valid ID");
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (z && this.n != this.m) {
            ((DraggableItemAdapter) WrapperAdapterUtils.a(m(), DraggableItemAdapter.class)).b_(this.m, this.n);
        }
        this.m = -1;
        this.n = -1;
        this.l = null;
        this.k = null;
        this.j = null;
        notifyDataSetChanged();
    }

    public void onViewRecycled(VH vh) {
        if (c() && vh == this.j) {
            h();
        }
        super.onViewRecycled(vh);
    }

    private void h() {
        Log.i(b, "a view holder object which is bound to currently dragging item is recycled");
        this.j = null;
        this.h.o();
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4) {
        return this.i.a(viewHolder, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public boolean d(int i2, int i3) {
        return this.i.b(i2, i3);
    }

    /* access modifiers changed from: package-private */
    public ItemDraggableRange a(RecyclerView.ViewHolder viewHolder, int i2) {
        return this.i.a(viewHolder, i2);
    }

    /* access modifiers changed from: package-private */
    public void e(int i2, int i3) {
        int a2 = a(i2, this.m, this.n);
        if (a2 == this.m) {
            this.n = i3;
            notifyItemMoved(i2, i3);
            return;
        }
        throw new IllegalStateException("onMoveItem() - may be a bug or has duplicate IDs  --- mDraggingItemInitialPosition = " + this.m + ", " + "mDraggingItemCurrentPosition = " + this.n + ", " + "origFromPosition = " + a2 + ", " + "fromPosition = " + i2 + ", " + "toPosition = " + i3);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.k != null;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.n;
    }

    private static void b(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            DraggableItemViewHolder draggableItemViewHolder = (DraggableItemViewHolder) viewHolder;
            int a2 = draggableItemViewHolder.a();
            if (a2 == -1 || ((a2 ^ i2) & Integer.MAX_VALUE) != 0) {
                i2 |= Integer.MIN_VALUE;
            }
            draggableItemViewHolder.a(i2);
        }
    }

    private static DraggableItemAdapter a(RecyclerView.Adapter adapter) {
        return (DraggableItemAdapter) WrapperAdapterUtils.a(adapter, DraggableItemAdapter.class);
    }

    private int a(int i2) {
        return c() ? a(i2, this.m, this.n) : i2;
    }

    public int b(VH vh, int i2, int i3, int i4) {
        RecyclerView.Adapter m2 = m();
        if (!(m2 instanceof BaseSwipeableItemAdapter)) {
            return 0;
        }
        return ((BaseSwipeableItemAdapter) m2).b(vh, a(i2), i3, i4);
    }

    public void a(VH vh, int i2, int i3) {
        RecyclerView.Adapter m2 = m();
        if (m2 instanceof BaseSwipeableItemAdapter) {
            ((BaseSwipeableItemAdapter) m2).a(vh, a(i2), i3);
        }
    }

    public SwipeResultAction b(VH vh, int i2, int i3) {
        RecyclerView.Adapter m2 = m();
        if (!(m2 instanceof BaseSwipeableItemAdapter)) {
            return new SwipeResultActionDefault();
        }
        return SwipeableItemInternalUtils.a((BaseSwipeableItemAdapter) m2, vh, a(i2), i3);
    }
}
