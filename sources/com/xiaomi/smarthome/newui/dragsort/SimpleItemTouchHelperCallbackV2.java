package com.xiaomi.smarthome.newui.dragsort;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleItemTouchHelperCallbackV2 extends ItemTouchHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20653a = "SimpleItemTouchHelperCallbackV2";
    private ItemTouchHelperAdapter b;
    private WeakReference<Context> c;
    private Vibrator d;
    private AtomicBoolean e = new AtomicBoolean(false);
    private AtomicBoolean f = new AtomicBoolean(false);

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public SimpleItemTouchHelperCallbackV2(Context context, RecyclerView recyclerView) {
        this.c = new WeakReference<>(context);
        a(context);
    }

    public void a(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.b = itemTouchHelperAdapter;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return makeMovementFlags(15, 0);
        }
        return makeMovementFlags(3, 0);
    }

    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
        return ((int) Math.signum((float) i2)) * 10;
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Log.d(f20653a, "onMove");
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        this.b.a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.b.a(viewHolder.getAdapterPosition());
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i, boolean z) {
        super.onChildDraw(canvas, recyclerView, viewHolder, f2, f3, i, z);
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        String str = f20653a;
        Log.d(str, "onSelectedChanged " + i);
        super.onSelectedChanged(viewHolder, i);
        switch (i) {
            case 0:
                c();
                return;
            case 2:
                this.f.set(true);
                if (viewHolder instanceof ItemTouchHelperViewHolder) {
                    ((ItemTouchHelperViewHolder) viewHolder).a();
                }
                if (this.b != null) {
                    this.b.b();
                }
                this.d.vibrate(50);
                return;
            default:
                return;
        }
    }

    private void c() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        this.f.set(false);
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f2, f3, i, z);
    }

    private void a(Context context) {
        this.d = (Vibrator) context.getSystemService("vibrator");
    }

    public void a() {
        this.e.set(true);
    }

    public boolean b() {
        return this.f.get();
    }
}
