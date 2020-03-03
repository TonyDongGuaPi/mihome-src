package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.utils.BaseWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

class ExpandableRecyclerViewWrapperAdapter extends BaseWrapperAdapter<RecyclerView.ViewHolder> implements DraggableItemAdapter<RecyclerView.ViewHolder>, SwipeableItemAdapter<RecyclerView.ViewHolder> {
    private static final String b = "ARVExpandableWrapper";
    private static final int c = Integer.MIN_VALUE;
    private static final int d = -1;
    private ExpandableItemAdapter e;
    private RecyclerViewExpandableItemManager f;
    private ExpandablePositionTranslator g;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private int k = -1;
    private RecyclerViewExpandableItemManager.OnGroupExpandListener l;
    private RecyclerViewExpandableItemManager.OnGroupCollapseListener m;

    private interface Constants extends ExpandableItemConstants {
    }

    public ExpandableRecyclerViewWrapperAdapter(RecyclerViewExpandableItemManager recyclerViewExpandableItemManager, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, int[] iArr) {
        super(adapter);
        this.e = a((RecyclerView.Adapter) adapter);
        if (this.e == null) {
            throw new IllegalArgumentException("adapter does not implement RecyclerViewExpandableListManager");
        } else if (recyclerViewExpandableItemManager != null) {
            this.f = recyclerViewExpandableItemManager;
            this.g = new ExpandablePositionTranslator();
            this.g.a(this.e, false);
            if (iArr != null) {
                this.g.a(iArr, (ExpandableItemAdapter) null, (RecyclerViewExpandableItemManager.OnGroupExpandListener) null, (RecyclerViewExpandableItemManager.OnGroupCollapseListener) null);
            }
        } else {
            throw new IllegalArgumentException("manager cannot be null");
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.e = null;
        this.f = null;
        this.l = null;
        this.m = null;
    }

    public int getItemCount() {
        return this.g.b();
    }

    public long getItemId(int i2) {
        if (this.e == null) {
            return -1;
        }
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        int a2 = ExpandableAdapterHelper.a(f2);
        if (a2 == -1) {
            return ExpandableAdapterHelper.c(this.e.b(b2));
        }
        return ExpandableAdapterHelper.a(this.e.b(b2), this.e.c(b2, a2));
    }

    public int getItemViewType(int i2) {
        int i3;
        if (this.e == null) {
            return 0;
        }
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        int a2 = ExpandableAdapterHelper.a(f2);
        if (a2 == -1) {
            i3 = this.e.c(b2);
        } else {
            i3 = this.e.d(b2, a2);
        }
        if ((i3 & Integer.MIN_VALUE) == 0) {
            return a2 == -1 ? i3 | Integer.MIN_VALUE : i3;
        }
        throw new IllegalStateException("Illegal view type (type = " + Integer.toHexString(i3) + Operators.BRACKET_END_STR);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder viewHolder;
        if (this.e == null) {
            return null;
        }
        int i3 = Integer.MAX_VALUE & i2;
        if ((i2 & Integer.MIN_VALUE) != 0) {
            viewHolder = this.e.a(viewGroup, i3);
        } else {
            viewHolder = this.e.b(viewGroup, i3);
        }
        if (viewHolder instanceof ExpandableItemViewHolder) {
            ((ExpandableItemViewHolder) viewHolder).c_(-1);
        }
        return viewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
        if (this.e != null) {
            long f2 = this.g.f(i2);
            int b2 = ExpandableAdapterHelper.b(f2);
            int a2 = ExpandableAdapterHelper.a(f2);
            int itemViewType = viewHolder.getItemViewType() & Integer.MAX_VALUE;
            int i3 = a2 == -1 ? 1 : 2;
            if (this.g.a(b2)) {
                i3 |= 4;
            }
            b(viewHolder, i3);
            c(viewHolder, b2, a2);
            if (a2 == -1) {
                this.e.b(viewHolder, b2, itemViewType);
            } else {
                this.e.b(viewHolder, b2, a2, itemViewType);
            }
        }
    }

    private void o() {
        if (this.g != null) {
            int[] a2 = this.g.a();
            this.g.a(this.e, false);
            this.g.a(a2, (ExpandableItemAdapter) null, (RecyclerViewExpandableItemManager.OnGroupExpandListener) null, (RecyclerViewExpandableItemManager.OnGroupCollapseListener) null);
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ExpandableItemViewHolder) {
            ((ExpandableItemViewHolder) viewHolder).c_(-1);
        }
        super.onViewRecycled(viewHolder);
    }

    /* access modifiers changed from: protected */
    public void b() {
        o();
        super.b();
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3) {
        super.a(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void a_(int i2, int i3) {
        o();
        super.a_(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void c(int i2, int i3) {
        if (i3 == 1) {
            long f2 = this.g.f(i2);
            int b2 = ExpandableAdapterHelper.b(f2);
            int a2 = ExpandableAdapterHelper.a(f2);
            if (a2 == -1) {
                this.g.g(b2);
            } else {
                this.g.b(b2, a2);
            }
        } else {
            o();
        }
        super.c(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3, int i4) {
        o();
        super.b(i2, i3, i4);
    }

    public boolean a(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4) {
        boolean z;
        if (!(this.e instanceof ExpandableDraggableItemAdapter)) {
            return false;
        }
        ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) this.e;
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        int a2 = ExpandableAdapterHelper.a(f2);
        if (a2 == -1) {
            z = expandableDraggableItemAdapter.a(viewHolder, b2, i3, i4);
        } else {
            z = expandableDraggableItemAdapter.a(viewHolder, b2, a2, i3, i4);
        }
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = -1;
        return z;
    }

    public ItemDraggableRange a(RecyclerView.ViewHolder viewHolder, int i2) {
        if (!(this.e instanceof ExpandableDraggableItemAdapter) || this.e.a() < 1) {
            return null;
        }
        ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) this.e;
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        int a2 = ExpandableAdapterHelper.a(f2);
        if (a2 == -1) {
            ItemDraggableRange a3 = expandableDraggableItemAdapter.a(viewHolder, b2);
            if (a3 == null) {
                return new ItemDraggableRange(0, Math.max(0, (this.g.b() - this.g.c(Math.max(0, this.e.a() - 1))) - 1));
            } else if (a(a3)) {
                long a4 = ExpandableAdapterHelper.a(a3.a());
                long a5 = ExpandableAdapterHelper.a(a3.b());
                int a6 = this.g.a(a4);
                int a7 = this.g.a(a5);
                if (a3.b() > b2) {
                    a7 += this.g.c(a3.b());
                }
                this.h = a3.a();
                this.i = a3.b();
                return new ItemDraggableRange(a6, a7);
            } else {
                throw new IllegalStateException("Invalid range specified: " + a3);
            }
        } else {
            ItemDraggableRange a8 = expandableDraggableItemAdapter.a(viewHolder, b2, a2);
            if (a8 == null) {
                return new ItemDraggableRange(1, Math.max(1, this.g.b() - 1));
            }
            if (a(a8)) {
                long a9 = ExpandableAdapterHelper.a(a8.a());
                int a10 = this.g.a(ExpandableAdapterHelper.a(a8.b())) + this.g.c(a8.b());
                int min = Math.min(this.g.a(a9) + 1, a10);
                this.h = a8.a();
                this.i = a8.b();
                return new ItemDraggableRange(min, a10);
            } else if (b(a8)) {
                int max = Math.max(this.g.c(b2) - 1, 0);
                int min2 = Math.min(a8.a(), max);
                int min3 = Math.min(a8.b(), max);
                long a11 = ExpandableAdapterHelper.a(b2, min2);
                long a12 = ExpandableAdapterHelper.a(b2, min3);
                int a13 = this.g.a(a11);
                int a14 = this.g.a(a12);
                this.j = min2;
                this.k = min3;
                return new ItemDraggableRange(a13, a14);
            } else {
                throw new IllegalStateException("Invalid range specified: " + a8);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(int r10, int r11) {
        /*
            r9 = this;
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r0 = r9.e
            boolean r0 = r0 instanceof com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter
            r1 = 1
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r0 = r9.e
            int r0 = r0.a()
            r2 = 0
            if (r0 >= r1) goto L_0x0012
            return r2
        L_0x0012:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r0 = r9.e
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter r0 = (com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter) r0
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r3 = r9.g
            long r3 = r3.f(r10)
            int r5 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.b((long) r3)
            int r3 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((long) r3)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r4 = r9.g
            long r6 = r4.f(r11)
            int r4 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.b((long) r6)
            int r6 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((long) r6)
            r7 = -1
            if (r3 != r7) goto L_0x0037
            r8 = 1
            goto L_0x0038
        L_0x0037:
            r8 = 0
        L_0x0038:
            if (r6 != r7) goto L_0x003c
            r7 = 1
            goto L_0x003d
        L_0x003c:
            r7 = 0
        L_0x003d:
            if (r8 == 0) goto L_0x0063
            if (r5 != r4) goto L_0x0042
            goto L_0x005b
        L_0x0042:
            if (r10 >= r11) goto L_0x005b
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r10 = r9.g
            boolean r10 = r10.a((int) r4)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r11 = r9.g
            int r11 = r11.c(r4)
            if (r7 == 0) goto L_0x0055
            r10 = r10 ^ r1
            r7 = r10
            goto L_0x005b
        L_0x0055:
            int r11 = r11 - r1
            if (r6 != r11) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r1 = 0
        L_0x005a:
            r7 = r1
        L_0x005b:
            if (r7 == 0) goto L_0x0062
            boolean r10 = r0.b(r5, r4)
            return r10
        L_0x0062:
            return r2
        L_0x0063:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r8 = r9.g
            boolean r8 = r8.a((int) r4)
            if (r10 >= r11) goto L_0x0079
            if (r7 == 0) goto L_0x0087
            if (r8 == 0) goto L_0x0071
            r6 = 0
            goto L_0x0087
        L_0x0071:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r10 = r9.g
            int r10 = r10.b(r4)
        L_0x0077:
            r6 = r10
            goto L_0x0087
        L_0x0079:
            if (r7 == 0) goto L_0x0087
            if (r4 <= 0) goto L_0x0086
            int r4 = r4 + -1
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r10 = r9.g
            int r10 = r10.b(r4)
            goto L_0x0077
        L_0x0086:
            r1 = 0
        L_0x0087:
            if (r1 == 0) goto L_0x008e
            boolean r10 = r0.b(r5, r3, r4, r6)
            return r10
        L_0x008e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableRecyclerViewWrapperAdapter.b(int, int):boolean");
    }

    private static boolean a(ItemDraggableRange itemDraggableRange) {
        if (!itemDraggableRange.getClass().equals(GroupPositionItemDraggableRange.class) && !itemDraggableRange.getClass().equals(ItemDraggableRange.class)) {
            return false;
        }
        return true;
    }

    private static boolean b(ItemDraggableRange itemDraggableRange) {
        return itemDraggableRange.getClass().equals(ChildPositionItemDraggableRange.class);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b_(int r11, int r12) {
        /*
            r10 = this;
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r0 = r10.e
            boolean r0 = r0 instanceof com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r0 = -1
            r10.h = r0
            r10.i = r0
            r10.j = r0
            r10.k = r0
            if (r11 != r12) goto L_0x0013
            return
        L_0x0013:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r1 = r10.e
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter r1 = (com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter) r1
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r2 = r10.g
            long r2 = r2.f(r11)
            int r4 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.b((long) r2)
            int r2 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((long) r2)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r3 = r10.g
            long r5 = r3.f(r12)
            int r3 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.b((long) r5)
            int r5 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((long) r5)
            r6 = 0
            r7 = 1
            if (r2 != r0) goto L_0x0039
            r8 = 1
            goto L_0x003a
        L_0x0039:
            r8 = 0
        L_0x003a:
            if (r5 != r0) goto L_0x003e
            r9 = 1
            goto L_0x003f
        L_0x003e:
            r9 = 0
        L_0x003f:
            if (r8 == 0) goto L_0x004d
            if (r9 == 0) goto L_0x004d
            r1.a((int) r4, (int) r3)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r1 = r10.g
            r1.a((int) r4, (int) r3)
            goto L_0x00cd
        L_0x004d:
            if (r8 != 0) goto L_0x006c
            if (r9 != 0) goto L_0x006c
            if (r4 != r3) goto L_0x0054
            goto L_0x0058
        L_0x0054:
            if (r11 >= r12) goto L_0x0058
            int r5 = r5 + 1
        L_0x0058:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r12 = r10.g
            long r6 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((int) r4, (int) r5)
            int r12 = r12.a((long) r6)
            r1.a((int) r4, (int) r2, (int) r3, (int) r5)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r1 = r10.g
            r1.a((int) r4, (int) r2, (int) r3, (int) r5)
            goto L_0x00cd
        L_0x006c:
            if (r8 != 0) goto L_0x00b7
            if (r12 >= r11) goto L_0x007e
            if (r3 != 0) goto L_0x0075
        L_0x0072:
            r5 = r3
            r8 = 0
            goto L_0x008f
        L_0x0075:
            int r5 = r3 + -1
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r8 = r10.g
            int r8 = r8.b(r5)
            goto L_0x008f
        L_0x007e:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r5 = r10.g
            boolean r5 = r5.a((int) r3)
            if (r5 == 0) goto L_0x0087
            goto L_0x0072
        L_0x0087:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r5 = r10.g
            int r5 = r5.b(r3)
            r8 = r5
            r5 = r3
        L_0x008f:
            if (r4 != r5) goto L_0x00a0
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r9 = r10.g
            int r9 = r9.b(r5)
            int r9 = r9 - r7
            int r6 = java.lang.Math.max(r6, r9)
            int r8 = java.lang.Math.min(r8, r6)
        L_0x00a0:
            if (r4 != r5) goto L_0x00a4
            if (r2 == r8) goto L_0x00cc
        L_0x00a4:
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r6 = r10.g
            boolean r3 = r6.a((int) r3)
            if (r3 == 0) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r12 = -1
        L_0x00ae:
            r1.a((int) r4, (int) r2, (int) r5, (int) r8)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r1 = r10.g
            r1.a((int) r4, (int) r2, (int) r5, (int) r8)
            goto L_0x00cd
        L_0x00b7:
            if (r4 == r3) goto L_0x00cc
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r12 = r10.g
            long r5 = com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableAdapterHelper.a((int) r3)
            int r12 = r12.a((long) r5)
            r1.a((int) r4, (int) r3)
            com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator r1 = r10.g
            r1.a((int) r4, (int) r3)
            goto L_0x00cd
        L_0x00cc:
            r12 = r11
        L_0x00cd:
            if (r12 == r11) goto L_0x00d8
            if (r12 == r0) goto L_0x00d5
            r10.notifyItemMoved(r11, r12)
            goto L_0x00d8
        L_0x00d5:
            r10.notifyItemRemoved(r11)
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableRecyclerViewWrapperAdapter.b_(int, int):void");
    }

    public int b(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4) {
        if (!(this.e instanceof BaseExpandableSwipeableItemAdapter)) {
            return 0;
        }
        BaseExpandableSwipeableItemAdapter baseExpandableSwipeableItemAdapter = (BaseExpandableSwipeableItemAdapter) this.e;
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        int a2 = ExpandableAdapterHelper.a(f2);
        if (a2 == -1) {
            return baseExpandableSwipeableItemAdapter.a(viewHolder, b2, i3, i4);
        }
        return baseExpandableSwipeableItemAdapter.a(viewHolder, b2, a2, i3, i4);
    }

    public void a(RecyclerView.ViewHolder viewHolder, int i2, int i3) {
        if (this.e instanceof BaseExpandableSwipeableItemAdapter) {
            BaseExpandableSwipeableItemAdapter baseExpandableSwipeableItemAdapter = (BaseExpandableSwipeableItemAdapter) this.e;
            long f2 = this.g.f(i2);
            int b2 = ExpandableAdapterHelper.b(f2);
            int a2 = ExpandableAdapterHelper.a(f2);
            if (a2 == -1) {
                baseExpandableSwipeableItemAdapter.a(viewHolder, b2, i3);
            } else {
                baseExpandableSwipeableItemAdapter.b(viewHolder, b2, a2, i3);
            }
        }
    }

    public SwipeResultAction b(RecyclerView.ViewHolder viewHolder, int i2, int i3) {
        if (!(this.e instanceof BaseExpandableSwipeableItemAdapter) || i2 == -1) {
            return null;
        }
        long f2 = this.g.f(i2);
        return ExpandableSwipeableItemInternalUtils.a((BaseExpandableSwipeableItemAdapter) this.e, viewHolder, ExpandableAdapterHelper.b(f2), ExpandableAdapterHelper.a(f2), i3);
    }

    /* access modifiers changed from: package-private */
    public boolean c(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4) {
        if (this.e == null) {
            return false;
        }
        long f2 = this.g.f(i2);
        int b2 = ExpandableAdapterHelper.b(f2);
        if (ExpandableAdapterHelper.a(f2) != -1) {
            return false;
        }
        boolean z = !this.g.a(b2);
        if (!this.e.a(viewHolder, b2, i3, i4, z)) {
            return false;
        }
        if (z) {
            b(b2, true);
        } else {
            a(b2, true);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (!this.g.g() && !this.g.e()) {
            this.g.a(this.e, true);
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (!this.g.g() && !this.g.f()) {
            this.g.a(this.e, false);
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, boolean z) {
        if (!this.g.a(i2) || !this.e.b(i2, z)) {
            return false;
        }
        if (this.g.d(i2)) {
            notifyItemRangeRemoved(this.g.a(ExpandableAdapterHelper.a(i2)) + 1, this.g.b(i2));
        }
        notifyItemChanged(this.g.a(ExpandableAdapterHelper.a(i2)));
        if (this.m != null) {
            this.m.a(i2, z);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2, boolean z) {
        if (this.g.a(i2) || !this.e.a(i2, z)) {
            return false;
        }
        if (this.g.e(i2)) {
            notifyItemRangeInserted(this.g.a(ExpandableAdapterHelper.a(i2)) + 1, this.g.b(i2));
        }
        notifyItemChanged(this.g.a(ExpandableAdapterHelper.a(i2)));
        if (this.l != null) {
            this.l.b(i2, z);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        return this.g.a(i2);
    }

    /* access modifiers changed from: package-private */
    public long b(int i2) {
        return this.g.f(i2);
    }

    /* access modifiers changed from: package-private */
    public int a(long j2) {
        return this.g.a(j2);
    }

    /* access modifiers changed from: package-private */
    public int[] e() {
        if (this.g != null) {
            return this.g.a();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerViewExpandableItemManager.OnGroupExpandListener onGroupExpandListener) {
        this.l = onGroupExpandListener;
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerViewExpandableItemManager.OnGroupCollapseListener onGroupCollapseListener) {
        this.m = onGroupCollapseListener;
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr, boolean z, boolean z2) {
        ExpandablePositionTranslator expandablePositionTranslator = this.g;
        RecyclerViewExpandableItemManager.OnGroupCollapseListener onGroupCollapseListener = null;
        ExpandableItemAdapter expandableItemAdapter = z ? this.e : null;
        RecyclerViewExpandableItemManager.OnGroupExpandListener onGroupExpandListener = z2 ? this.l : null;
        if (z2) {
            onGroupCollapseListener = this.m;
        }
        expandablePositionTranslator.a(iArr, expandableItemAdapter, onGroupExpandListener, onGroupCollapseListener);
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2));
        if (a2 != -1) {
            notifyItemChanged(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, Object obj) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2));
        int c2 = this.g.c(i2);
        if (a2 != -1) {
            notifyItemRangeChanged(a2, c2 + 1, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, Object obj) {
        int a2;
        int c2 = this.g.c(i2);
        if (c2 > 0 && (a2 = this.g.a(ExpandableAdapterHelper.a(i2, 0))) != -1) {
            notifyItemRangeChanged(a2, c2, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, Object obj) {
        a(i2, i3, 1, obj);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, Object obj) {
        int a2;
        int c2 = this.g.c(i2);
        if (c2 > 0 && i3 < c2 && (a2 = this.g.a(ExpandableAdapterHelper.a(i2, 0))) != -1) {
            notifyItemRangeChanged(a2 + i3, Math.min(i4, c2 - i3), obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(int i2, int i3) {
        this.g.c(i2, i3);
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2, i3));
        if (a2 != -1) {
            notifyItemInserted(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4) {
        this.g.b(i2, i3, i4);
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2, i3));
        if (a2 != -1) {
            notifyItemRangeInserted(a2, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void e(int i2, int i3) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2, i3));
        this.g.b(i2, i3);
        if (a2 != -1) {
            notifyItemRemoved(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(int i2, int i3, int i4) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2, i3));
        this.g.a(i2, i3, i4);
        if (a2 != -1) {
            notifyItemRangeRemoved(a2, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(int i2, boolean z) {
        if (this.g.a(i2, z) > 0) {
            notifyItemInserted(this.g.a(ExpandableAdapterHelper.a(i2)));
            b(i2, 1, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, boolean z) {
        int a2 = this.g.a(i2, i3, z);
        if (a2 > 0) {
            notifyItemRangeInserted(this.g.a(ExpandableAdapterHelper.a(i2)), a2);
            b(i2, i3, false);
        }
    }

    private void b(int i2, int i3, boolean z) {
        if (this.l != null) {
            for (int i4 = 0; i4 < i3; i4++) {
                this.l.b(i2 + i4, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(int i2) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2));
        int g2 = this.g.g(i2);
        if (g2 > 0) {
            notifyItemRangeRemoved(a2, g2);
        }
    }

    /* access modifiers changed from: package-private */
    public void f(int i2, int i3) {
        int a2 = this.g.a(ExpandableAdapterHelper.a(i2));
        int d2 = this.g.d(i2, i3);
        if (d2 > 0) {
            notifyItemRangeRemoved(a2, d2);
        }
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.e.a();
    }

    /* access modifiers changed from: package-private */
    public int e(int i2) {
        return this.e.a(i2);
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.g.c();
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.g.d();
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.g.e();
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.g.f();
    }

    private static ExpandableItemAdapter a(RecyclerView.Adapter adapter) {
        return (ExpandableItemAdapter) WrapperAdapterUtils.a(adapter, ExpandableItemAdapter.class);
    }

    private static void b(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder instanceof ExpandableItemViewHolder) {
            ExpandableItemViewHolder expandableItemViewHolder = (ExpandableItemViewHolder) viewHolder;
            int K_ = expandableItemViewHolder.K_();
            if (!(K_ == -1 || ((K_ ^ i2) & 4) == 0)) {
                i2 |= 8;
            }
            if (K_ == -1 || ((K_ ^ i2) & Integer.MAX_VALUE) != 0) {
                i2 |= Integer.MIN_VALUE;
            }
            expandableItemViewHolder.c_(i2);
        }
    }

    private void c(RecyclerView.ViewHolder viewHolder, int i2, int i3) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            DraggableItemViewHolder draggableItemViewHolder = (DraggableItemViewHolder) viewHolder;
            boolean z = false;
            boolean z2 = (this.h == -1 || this.i == -1) ? false : true;
            boolean z3 = (this.j == -1 || this.k == -1) ? false : true;
            boolean z4 = i2 >= this.h && i2 <= this.i;
            boolean z5 = i2 != -1 && i3 >= this.j && i3 <= this.k;
            int a2 = draggableItemViewHolder.a();
            if ((a2 & 1) != 0 && (a2 & 4) == 0 && ((!z2 || z4) && (!z3 || (z3 && z5)))) {
                z = true;
            }
            if (z) {
                draggableItemViewHolder.a(a2 | 4 | Integer.MIN_VALUE);
            }
        }
    }
}
