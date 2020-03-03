package com.chad.library.adapter.base.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;

public class ItemDragAndSwipeCallback extends ItemTouchHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    private BaseItemDraggableAdapter f5137a;
    private float b = 0.1f;
    private float c = 0.7f;
    private int d = 15;
    private int e = 32;

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter baseItemDraggableAdapter) {
        this.f5137a = baseItemDraggableAdapter;
    }

    public boolean isItemViewSwipeEnabled() {
        return this.f5137a.e();
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2 && !a(viewHolder)) {
            this.f5137a.b(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, true);
        } else if (i == 1 && !a(viewHolder)) {
            this.f5137a.d(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, true);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!a(viewHolder)) {
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support)).booleanValue()) {
                this.f5137a.c(viewHolder);
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, false);
            }
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support)).booleanValue()) {
                this.f5137a.e(viewHolder);
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, false);
            }
        }
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (a(viewHolder)) {
            return makeMovementFlags(0, 0);
        }
        return makeMovementFlags(this.d, this.e);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return viewHolder.getItemViewType() == viewHolder2.getItemViewType();
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        this.f5137a.a(viewHolder, viewHolder2);
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        if (!a(viewHolder)) {
            this.f5137a.f(viewHolder);
        }
    }

    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.b;
    }

    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.c;
    }

    public void a(float f) {
        this.c = f;
    }

    public void b(float f) {
        this.b = f;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        if (i == 1 && !a(viewHolder)) {
            View view = viewHolder.itemView;
            canvas.save();
            if (f > 0.0f) {
                canvas.clipRect((float) view.getLeft(), (float) view.getTop(), ((float) view.getLeft()) + f, (float) view.getBottom());
                canvas.translate((float) view.getLeft(), (float) view.getTop());
            } else {
                canvas.clipRect(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
                canvas.translate(((float) view.getRight()) + f, (float) view.getTop());
            }
            this.f5137a.a(canvas, viewHolder, f, f2, z);
            canvas.restore();
        }
    }

    private boolean a(RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getItemViewType();
        return itemViewType == 273 || itemViewType == 546 || itemViewType == 819 || itemViewType == 1365;
    }
}
