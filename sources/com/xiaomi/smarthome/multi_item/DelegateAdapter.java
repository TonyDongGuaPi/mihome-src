package com.xiaomi.smarthome.multi_item;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DelegateAdapter extends RecyclerView.Adapter {
    private static final String d = "DelegateAdapter";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<IAdapter> f20157a = new ArrayList();
    /* access modifiers changed from: private */
    public IAdapter b;
    private final RecyclerView.ItemDecoration c = new RecyclerView.ItemDecoration() {
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            try {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (int i = 0; i < gridLayoutManager.getChildCount(); i++) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(gridLayoutManager.getChildAt(i));
                    IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(childAdapterPosition));
                    Canvas canvas2 = canvas;
                    RecyclerView recyclerView2 = recyclerView;
                    a2.a(canvas2, recyclerView2, gridLayoutManager.getChildAt(childAdapterPosition), childAdapterPosition - DelegateAdapter.this.e(a2), state);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            try {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (int i = 0; i < gridLayoutManager.getChildCount(); i++) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(gridLayoutManager.getChildAt(i));
                    IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(childAdapterPosition));
                    Canvas canvas2 = canvas;
                    RecyclerView recyclerView2 = recyclerView;
                    a2.b(canvas2, recyclerView2, gridLayoutManager.getChildAt(childAdapterPosition), childAdapterPosition - DelegateAdapter.this.e(a2), state);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            try {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(childAdapterPosition));
                a2.a(rect, view, childAdapterPosition - DelegateAdapter.this.e(a2), recyclerView, state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void a(IAdapter iAdapter) {
        if (!this.f20157a.contains(iAdapter)) {
            iAdapter.a(this);
            this.f20157a.add(iAdapter);
        }
    }

    public void a(int i, IAdapter iAdapter, RecyclerView recyclerView) {
        if (!this.f20157a.contains(iAdapter)) {
            this.f20157a.add(i, iAdapter);
            iAdapter.a(this);
            iAdapter.onAttachedToRecyclerView(recyclerView);
            iAdapter.notifyItemRangeInserted(0, iAdapter.getItemCount());
        }
    }

    public void a(IAdapter iAdapter, RecyclerView recyclerView) {
        if (this.f20157a.contains(iAdapter)) {
            this.f20157a.remove(iAdapter);
            iAdapter.notifyItemRangeRemoved(0, iAdapter.getItemCount());
            iAdapter.onDetachedFromRecyclerView(recyclerView);
        }
    }

    public void a(IAdapter iAdapter, RecyclerView recyclerView, boolean z) {
        if (iAdapter != null && (this.f20157a.contains(iAdapter) || iAdapter == this.b)) {
            int e = e(iAdapter);
            if (!z) {
                e += iAdapter.getItemCount();
            }
            recyclerView.scrollToPosition(e);
        }
    }

    public void a(int i, RecyclerView recyclerView, boolean z) {
        IAdapter iAdapter;
        if (i <= 0 || i >= this.f20157a.size() - 1) {
            iAdapter = this.b;
        } else {
            iAdapter = this.f20157a.get(i);
        }
        if (iAdapter != null) {
            a(iAdapter, recyclerView, z);
        }
    }

    public void b(IAdapter iAdapter) {
        this.b = iAdapter;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return a(i).onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        IAdapter a2 = a(getItemViewType(i));
        a2.onBindViewHolder(viewHolder, i - e(a2));
    }

    /* access modifiers changed from: private */
    public IAdapter a(int i) {
        for (IAdapter next : this.f20157a) {
            if (next.hashCode() == i) {
                return next;
            }
        }
        boolean z = this.b == null;
        a(z, "illegal viewType: " + i);
        return this.b;
    }

    /* access modifiers changed from: private */
    public int e(IAdapter iAdapter) {
        int i = 0;
        for (IAdapter next : this.f20157a) {
            if (iAdapter == next) {
                return i;
            }
            i += next.getItemCount();
        }
        boolean z = this.b == null;
        a(z, "illegal target: " + iAdapter);
        return 0;
    }

    public int getItemViewType(int i) {
        boolean z = false;
        int i2 = 0;
        for (IAdapter next : this.f20157a) {
            i2 += next.getItemCount();
            if (i < i2) {
                return next.hashCode();
            }
        }
        if (this.b == null) {
            z = true;
        }
        a(z, "illegal position: " + i);
        return this.b.hashCode();
    }

    public int getItemCount() {
        int i = 0;
        for (IAdapter itemCount : this.f20157a) {
            i += itemCount.getItemCount();
        }
        return (i != 0 || this.b == null) ? i : this.b.getItemCount();
    }

    private int a() {
        int i;
        int[] iArr = new int[this.f20157a.size()];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f20157a.size(); i3++) {
            IAdapter iAdapter = this.f20157a.get(i3);
            i2 += iAdapter.getItemCount();
            iArr[i3] = iAdapter.a();
        }
        if (i2 != 0 || this.b == null) {
            i = MathUtils.a(iArr);
        } else {
            i = this.b.a();
        }
        return i * 2;
    }

    private void a(boolean z, String str) {
        if (z) {
            String deepToString = Arrays.deepToString(this.f20157a.toArray());
            throw new IllegalStateException(deepToString + " : " + str);
        }
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        for (IAdapter onAttachedToRecyclerView : this.f20157a) {
            onAttachedToRecyclerView.onAttachedToRecyclerView(recyclerView);
        }
        if (this.b != null) {
            this.b.onAttachedToRecyclerView(recyclerView);
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        if (gridLayoutManager != null) {
            recyclerView.addItemDecoration(this.c);
            final int a2 = a();
            gridLayoutManager.setSpanCount(a2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    return a2 / DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(i)).a();
                }
            });
            b(recyclerView);
            return;
        }
        throw new RuntimeException("setAdapter should be invoked after setLayoutManager");
    }

    private void a(@NonNull RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            private int b = Integer.MAX_VALUE;
            private int c = Integer.MIN_VALUE;

            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                try {
                    return DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(viewHolder.getAdapterPosition())).a(recyclerView, viewHolder);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                if (viewHolder.getClass() != viewHolder2.getClass()) {
                    return false;
                }
                int adapterPosition = viewHolder.getAdapterPosition();
                int adapterPosition2 = viewHolder2.getAdapterPosition();
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                IAdapter a3 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition2));
                if (a2 != a3) {
                    return false;
                }
                this.b = Math.min(Math.min(adapterPosition, adapterPosition2), this.b);
                this.c = Math.max(Math.max(adapterPosition, adapterPosition2), this.c);
                int a4 = DelegateAdapter.this.e(a2);
                return a3.a(adapterPosition - a4, adapterPosition2 - a4);
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                int adapterPosition = viewHolder.getAdapterPosition();
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                a2.a(viewHolder, adapterPosition - DelegateAdapter.this.e(a2), i);
            }

            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
                if (i == 0 && this.b != Integer.MAX_VALUE && this.c != Integer.MIN_VALUE) {
                    DelegateAdapter.this.notifyItemRangeChanged(this.b, (this.c - this.b) + 1);
                    this.b = Integer.MAX_VALUE;
                    this.c = Integer.MIN_VALUE;
                } else if (i == 2) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                    a2.a(viewHolder, adapterPosition - DelegateAdapter.this.e(a2));
                }
            }

            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void b(@NonNull RecyclerView recyclerView) {
        AnonymousClass3 r0 = new SimpleItemDelegateTouchHelperCallback(recyclerView.getContext()) {
            private int d = Integer.MAX_VALUE;
            private int e = Integer.MIN_VALUE;

            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                try {
                    return DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(viewHolder.getAdapterPosition())).a(recyclerView, viewHolder);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return 0;
                }
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                if (viewHolder.getClass() != viewHolder2.getClass()) {
                    return false;
                }
                int adapterPosition = viewHolder.getAdapterPosition();
                int adapterPosition2 = viewHolder2.getAdapterPosition();
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                IAdapter a3 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition2));
                if (a2 != a3) {
                    return false;
                }
                this.d = Math.min(Math.min(adapterPosition, adapterPosition2), this.d);
                this.e = Math.max(Math.max(adapterPosition, adapterPosition2), this.e);
                int a4 = DelegateAdapter.this.e(a2);
                return a3.a(adapterPosition - a4, adapterPosition2 - a4);
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                int adapterPosition = viewHolder.getAdapterPosition();
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                a2.a(viewHolder, adapterPosition - DelegateAdapter.this.e(a2), i);
            }

            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
                super.onSelectedChanged(viewHolder, i);
                if (i == 0) {
                    for (IAdapter e2 : DelegateAdapter.this.f20157a) {
                        e2.e();
                    }
                    if (DelegateAdapter.this.b != null) {
                        DelegateAdapter.this.b.e();
                    }
                }
                if (i == 0 && this.d != Integer.MAX_VALUE && this.e != Integer.MIN_VALUE) {
                    DelegateAdapter.this.notifyItemRangeChanged(this.d, (this.e - this.d) + 1);
                    this.d = Integer.MAX_VALUE;
                    this.e = Integer.MIN_VALUE;
                } else if (i == 2) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                    a2.a(viewHolder, adapterPosition - DelegateAdapter.this.e(a2));
                }
            }

            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                int adapterPosition = viewHolder.getAdapterPosition();
                IAdapter a2 = DelegateAdapter.this.a(DelegateAdapter.this.getItemViewType(adapterPosition));
                a2.a(recyclerView, viewHolder, adapterPosition - DelegateAdapter.this.e(a2));
            }
        };
        r0.getClass();
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return SimpleItemDelegateTouchHelperCallback.this.a(view, motionEvent);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(r0);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        for (IAdapter a2 : this.f20157a) {
            a2.a((SimpleItemDelegateTouchHelperCallback) r0, itemTouchHelper);
        }
    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        for (IAdapter onDetachedFromRecyclerView : this.f20157a) {
            onDetachedFromRecyclerView.onDetachedFromRecyclerView(recyclerView);
        }
        if (this.b != null) {
            this.b.onDetachedFromRecyclerView(recyclerView);
        }
        recyclerView.removeItemDecoration(this.c);
        recyclerView.setOnTouchListener((View.OnTouchListener) null);
    }

    /* access modifiers changed from: package-private */
    public void a(IAdapter iAdapter, int i, int i2) {
        LogUtil.a(d, "onChildAdapterItemRangeChanged: " + iAdapter);
        notifyItemRangeChanged(e(iAdapter) + i, i2);
    }

    /* access modifiers changed from: package-private */
    public void a(IAdapter iAdapter, int i, int i2, Object obj) {
        LogUtil.a(d, "onChildAdapterItemRangeChanged: " + iAdapter);
        notifyItemRangeChanged(e(iAdapter) + i, i2, obj);
    }

    /* access modifiers changed from: package-private */
    public void b(IAdapter iAdapter, int i, int i2) {
        LogUtil.a(d, "onChildAdapterItemRangeInserted: " + iAdapter);
        notifyItemRangeInserted(e(iAdapter) + i, i2);
    }

    /* access modifiers changed from: package-private */
    public void c(IAdapter iAdapter, int i, int i2) {
        LogUtil.a(d, "onChildAdapterItemRangeRemoved: " + iAdapter);
        notifyItemRangeRemoved(e(iAdapter) + i, i2);
    }

    /* access modifiers changed from: package-private */
    public void a(IAdapter iAdapter, int i, int i2, int i3) {
        LogUtil.a(d, "onChildAdapterItemRangeMoved: " + iAdapter);
        int e = e(iAdapter);
        notifyItemMoved(i + e, e + i2);
    }

    /* access modifiers changed from: package-private */
    public void c(IAdapter iAdapter) {
        LogUtil.a(d, "onChildAdapterChanged: " + iAdapter);
        notifyDataSetChanged();
    }

    public int d(IAdapter iAdapter) {
        return this.f20157a.indexOf(iAdapter);
    }
}
