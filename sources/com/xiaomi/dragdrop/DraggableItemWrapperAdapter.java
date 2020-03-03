package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.ViewGroup;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class DraggableItemWrapperAdapter<VH extends RecyclerView.ViewHolder> extends BaseWrapperAdapter<VH> {
    private static final String c = "ARVDraggableWrapper";
    private static final int d = -1;
    private static final boolean e = true;
    private static final boolean f = true;
    private static final boolean g = true;
    private static final boolean h = false;
    boolean b = false;
    private RecyclerViewDragDropManager i;
    private DraggableItemAdapter j;
    private RecyclerView.ViewHolder k;
    private DraggingItemInfo l;
    private ItemDraggableRange m;
    private int n = -1;
    private int o = -1;
    private int p = -1;

    private interface Constants extends DraggableItemConstants {
    }

    protected static int c(int i2, int i3, int i4) {
        return (i3 < 0 || i4 < 0 || i3 == i4 || (i2 < i3 && i2 < i4) || (i2 > i3 && i2 > i4)) ? i2 : i4 < i3 ? i2 == i4 ? i3 : i2 - 1 : i2 == i4 ? i3 : i2 + 1;
    }

    public DraggableItemWrapperAdapter(RecyclerViewDragDropManager recyclerViewDragDropManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.j = a((RecyclerView.Adapter) adapter);
        if (a((RecyclerView.Adapter) adapter) == null) {
            throw new IllegalArgumentException("adapter does not implement DraggableItemAdapter");
        } else if (recyclerViewDragDropManager != null) {
            this.i = recyclerViewDragDropManager;
        } else {
            throw new IllegalArgumentException("manager cannot be null");
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        super.c();
        this.k = null;
        this.j = null;
        this.i = null;
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        VH onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        if (onCreateViewHolder instanceof DraggableItemViewHolder) {
            ((DraggableItemViewHolder) onCreateViewHolder).a(-1);
        }
        return onCreateViewHolder;
    }

    public void onBindViewHolder(VH vh, int i2, List<Object> list) {
        if (g()) {
            long j2 = this.l.c;
            long itemId = vh.getItemId();
            int c2 = c(i2, this.n, this.o);
            if (itemId == j2 && vh != this.k) {
                if (this.k != null) {
                    m();
                }
                Log.i(c, "a new view holder object for the currently dragging item is assigned");
                this.k = vh;
                this.i.a((RecyclerView.ViewHolder) vh);
            }
            int i3 = 1;
            if (itemId == j2) {
                i3 = 3;
            }
            if (this.m.a(i2)) {
                i3 |= 4;
            }
            c((RecyclerView.ViewHolder) vh, i3);
            super.onBindViewHolder(vh, c2, list);
            return;
        }
        c((RecyclerView.ViewHolder) vh, 0);
        super.onBindViewHolder(vh, i2, list);
    }

    public long getItemId(int i2) {
        if (g()) {
            return super.getItemId(c(i2, this.n, this.o));
        }
        return super.getItemId(i2);
    }

    public int getItemViewType(int i2) {
        if (g()) {
            return super.getItemViewType(c(i2, this.n, this.o));
        }
        return super.getItemViewType(i2);
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (k()) {
            l();
        } else {
            super.e();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3) {
        if (k()) {
            l();
        } else {
            super.a(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3) {
        if (k()) {
            l();
        } else {
            super.b(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void c(int i2, int i3) {
        if (k()) {
            l();
        } else {
            super.c(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3, int i4) {
        if (k()) {
            l();
        } else {
            super.a(i2, i3, i4);
        }
    }

    private boolean k() {
        return g();
    }

    private void l() {
        if (this.i != null) {
            this.i.j();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        Log.d(c, "onDragItemStarted(holder = " + viewHolder + Operators.BRACKET_END_STR);
        if (viewHolder.getItemId() != -1) {
            int adapterPosition = viewHolder.getAdapterPosition();
            this.p = adapterPosition;
            this.o = adapterPosition;
            this.n = adapterPosition;
            this.l = draggingItemInfo;
            this.k = viewHolder;
            this.m = itemDraggableRange;
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("dragging target must provides valid ID");
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, boolean z2) {
        Log.d(c, "onDragItemFinished(result = " + z + Operators.BRACKET_END_STR);
        if (z2) {
            if (!(!z || this.o == this.n || this.o == -1)) {
                ((DraggableItemAdapter) WrapperAdapterUtils.a(d(), DraggableItemAdapter.class)).a(this.n, this.o);
            }
        } else if (!(!z || this.p == this.n || this.p == -1)) {
            ((DraggableItemAdapter) WrapperAdapterUtils.a(d(), DraggableItemAdapter.class)).b(this.n, this.p);
        }
        this.b = false;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.m = null;
        this.l = null;
        this.k = null;
        notifyDataSetChanged();
    }

    public void onViewRecycled(VH vh) {
        if (g() && vh == this.k) {
            m();
        }
        super.onViewRecycled(vh);
    }

    private void m() {
        Log.i(c, "a view holder object which is bound to currently dragging item is recycled");
        this.k = null;
        this.i.o();
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4) {
        Log.v(c, "canStartDrag(holder = " + viewHolder + ", position = " + i2 + ", x = " + i3 + ", y = " + i4 + Operators.BRACKET_END_STR);
        return this.j.a(viewHolder, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public boolean g(int i2, int i3) {
        Log.v(c, "canDropItems(draggingPosition = " + i2 + ", dropPosition = " + i3 + Operators.BRACKET_END_STR);
        return this.j.c(i2, i3);
    }

    /* access modifiers changed from: package-private */
    public boolean h(int i2, int i3) {
        Log.v(c, "onCheckCanMerge(draggingPosition = " + i2 + ", dropPosition = " + i3 + Operators.BRACKET_END_STR);
        return this.j.d(i2, i3);
    }

    /* access modifiers changed from: package-private */
    public ItemDraggableRange a(RecyclerView.ViewHolder viewHolder, int i2) {
        Log.v(c, "getItemDraggableRange(holder = " + viewHolder + ", position = " + i2 + Operators.BRACKET_END_STR);
        return this.j.a(viewHolder, i2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.b = true;
    }

    /* access modifiers changed from: package-private */
    public void i(int i2, int i3) {
        Log.d(c, "moveMerge(fromPosition = " + i2 + ", toPosition = " + i3 + Operators.BRACKET_END_STR);
        this.b = false;
        c(i2, this.n, this.o);
        this.p = i3;
    }

    /* access modifiers changed from: package-private */
    public void j(int i2, int i3) {
        Log.d(c, "onMoveItem(fromPosition = " + i2 + ", toPosition = " + i3 + Operators.BRACKET_END_STR);
        this.b = false;
        this.p = -1;
        this.o = i3;
        notifyItemMoved(i2, i3);
    }

    public void b(VH vh, int i2) {
        ((DraggableItemAdapter) WrapperAdapterUtils.a(d(), DraggableItemAdapter.class)).b(vh, i2);
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return this.l != null;
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public int i() {
        if (this.p >= 0) {
            return this.p;
        }
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public int j() {
        return this.o;
    }

    private static void c(RecyclerView.ViewHolder viewHolder, int i2) {
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

    private int b(int i2) {
        return g() ? c(i2, this.n, this.o) : i2;
    }
}
