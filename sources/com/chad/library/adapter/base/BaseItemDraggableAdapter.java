package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;
import java.util.List;

public abstract class BaseItemDraggableAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int x = 0;
    private static final String y = "Item drag and item swipe should pass the same ItemTouchHelper";

    /* renamed from: a  reason: collision with root package name */
    protected int f5115a = 0;
    protected ItemTouchHelper b;
    protected boolean c = false;
    protected boolean d = false;
    protected OnItemDragListener e;
    protected OnItemSwipeListener f;
    protected boolean g = true;
    protected View.OnTouchListener h;
    protected View.OnLongClickListener i;

    public BaseItemDraggableAdapter(List<T> list) {
        super(list);
    }

    public BaseItemDraggableAdapter(int i2, List<T> list) {
        super(i2, list);
    }

    /* renamed from: a */
    public void onBindViewHolder(K k, int i2) {
        super.onBindViewHolder(k, i2);
        int itemViewType = k.getItemViewType();
        if (this.b != null && this.c && itemViewType != 546 && itemViewType != 273 && itemViewType != 1365 && itemViewType != 819) {
            if (this.f5115a != 0) {
                View e2 = k.e(this.f5115a);
                if (e2 != null) {
                    e2.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                    if (this.g) {
                        e2.setOnLongClickListener(this.i);
                    } else {
                        e2.setOnTouchListener(this.h);
                    }
                }
            } else {
                k.itemView.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                k.itemView.setOnLongClickListener(this.i);
            }
        }
    }

    public void a_(int i2) {
        this.f5115a = i2;
    }

    public void a(boolean z) {
        this.g = z;
        if (this.g) {
            this.h = null;
            this.i = new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (BaseItemDraggableAdapter.this.b == null || !BaseItemDraggableAdapter.this.c) {
                        return true;
                    }
                    BaseItemDraggableAdapter.this.b.startDrag((RecyclerView.ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                    return true;
                }
            };
            return;
        }
        this.h = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0 || BaseItemDraggableAdapter.this.g) {
                    return false;
                }
                if (BaseItemDraggableAdapter.this.b == null || !BaseItemDraggableAdapter.this.c) {
                    return true;
                }
                BaseItemDraggableAdapter.this.b.startDrag((RecyclerView.ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                return true;
            }
        };
        this.i = null;
    }

    public void a(@NonNull ItemTouchHelper itemTouchHelper) {
        a(itemTouchHelper, 0, true);
    }

    public void a(@NonNull ItemTouchHelper itemTouchHelper, int i2, boolean z) {
        this.c = true;
        this.b = itemTouchHelper;
        a_(i2);
        a(z);
    }

    public void a() {
        this.c = false;
        this.b = null;
    }

    public boolean b() {
        return this.c;
    }

    public void c() {
        this.d = true;
    }

    public void d() {
        this.d = false;
    }

    public boolean e() {
        return this.d;
    }

    public void a(OnItemDragListener onItemDragListener) {
        this.e = onItemDragListener;
    }

    public int a(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - t();
    }

    public void b(RecyclerView.ViewHolder viewHolder) {
        if (this.e != null && this.c) {
            this.e.a(viewHolder, a(viewHolder));
        }
    }

    public void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int a2 = a(viewHolder);
        int a3 = a(viewHolder2);
        if (b(a2) && b(a3)) {
            if (a2 < a3) {
                int i2 = a2;
                while (i2 < a3) {
                    int i3 = i2 + 1;
                    Collections.swap(this.s, i2, i3);
                    i2 = i3;
                }
            } else {
                for (int i4 = a2; i4 > a3; i4--) {
                    Collections.swap(this.s, i4, i4 - 1);
                }
            }
            notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        }
        if (this.e != null && this.c) {
            this.e.a(viewHolder, a2, viewHolder2, a3);
        }
    }

    public void c(RecyclerView.ViewHolder viewHolder) {
        if (this.e != null && this.c) {
            this.e.b(viewHolder, a(viewHolder));
        }
    }

    public void a(OnItemSwipeListener onItemSwipeListener) {
        this.f = onItemSwipeListener;
    }

    public void d(RecyclerView.ViewHolder viewHolder) {
        if (this.f != null && this.d) {
            this.f.a(viewHolder, a(viewHolder));
        }
    }

    public void e(RecyclerView.ViewHolder viewHolder) {
        if (this.f != null && this.d) {
            this.f.b(viewHolder, a(viewHolder));
        }
    }

    public void f(RecyclerView.ViewHolder viewHolder) {
        if (this.f != null && this.d) {
            this.f.c(viewHolder, a(viewHolder));
        }
        int a2 = a(viewHolder);
        if (b(a2)) {
            this.s.remove(a2);
            notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }

    public void a(Canvas canvas, RecyclerView.ViewHolder viewHolder, float f2, float f3, boolean z) {
        if (this.f != null && this.d) {
            this.f.a(canvas, viewHolder, f2, f3, z);
        }
    }

    private boolean b(int i2) {
        return i2 >= 0 && i2 < this.s.size();
    }
}
