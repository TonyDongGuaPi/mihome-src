package com.xiaomi.smarthome.device.choosedevice;

import android.content.Context;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import java.util.LinkedList;

public class XLinearLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private SparseArray<LinkedList<RecyclerView.ViewHolder>> f15366a = new SparseArray<>();
    private SparseArray<LinkedList<View>> b = new SparseArray<>();

    public XLinearLayout(Context context) {
        super(context);
        init((AttributeSet) null, 0, 0);
    }

    public XLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public XLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    public void init(AttributeSet attributeSet, int i, int i2) {
        setSoundEffectsEnabled(false);
    }

    public XLinearLayout setAdapter(RecyclerView.Adapter adapter, boolean z) {
        RecyclerView.ViewHolder viewHolder;
        if (z) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                Object tag = getChildAt(i).getTag();
                if (tag instanceof CacheItemData) {
                    CacheItemData cacheItemData = (CacheItemData) tag;
                    LinkedList linkedList = this.f15366a.get(cacheItemData.f15369a);
                    if (linkedList == null) {
                        SparseArray<LinkedList<RecyclerView.ViewHolder>> sparseArray = this.f15366a;
                        int i2 = cacheItemData.f15369a;
                        LinkedList linkedList2 = new LinkedList();
                        sparseArray.put(i2, linkedList2);
                        linkedList = linkedList2;
                    }
                    linkedList.add(cacheItemData.b);
                }
            }
            removeAllViews();
        }
        int itemCount = adapter.getItemCount();
        for (int i3 = 0; i3 < itemCount; i3++) {
            int itemViewType = adapter.getItemViewType(i3);
            LinkedList linkedList3 = this.f15366a.get(itemViewType);
            if (linkedList3 == null || linkedList3.size() == 0) {
                viewHolder = adapter.createViewHolder(this, itemViewType);
            } else {
                viewHolder = (RecyclerView.ViewHolder) linkedList3.removeLast();
            }
            adapter.bindViewHolder(viewHolder, i3);
            View view = viewHolder.itemView;
            view.setTag(new CacheItemData(itemViewType, viewHolder));
            addView(view);
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.device.choosedevice.XLinearLayout setAdapter(android.widget.BaseAdapter r7, boolean r8) {
        /*
            r6 = this;
            r0 = 0
            if (r8 == 0) goto L_0x003c
            int r8 = r6.getChildCount()
            r1 = 0
        L_0x0008:
            if (r1 >= r8) goto L_0x0039
            android.view.View r2 = r6.getChildAt(r1)
            java.lang.Object r2 = r2.getTag()
            boolean r3 = r2 instanceof com.xiaomi.smarthome.device.choosedevice.XLinearLayout.CacheItemData
            if (r3 == 0) goto L_0x0036
            com.xiaomi.smarthome.device.choosedevice.XLinearLayout$CacheItemData r2 = (com.xiaomi.smarthome.device.choosedevice.XLinearLayout.CacheItemData) r2
            android.util.SparseArray<java.util.LinkedList<android.view.View>> r3 = r6.b
            int r4 = r2.f15369a
            java.lang.Object r3 = r3.get(r4)
            java.util.LinkedList r3 = (java.util.LinkedList) r3
            if (r3 != 0) goto L_0x0031
            android.util.SparseArray<java.util.LinkedList<android.view.View>> r3 = r6.b
            int r4 = r2.f15369a
            java.util.LinkedList r5 = new java.util.LinkedList
            r5.<init>()
            r3.put(r4, r5)
            r3 = r5
        L_0x0031:
            android.view.View r2 = r2.c
            r3.add(r2)
        L_0x0036:
            int r1 = r1 + 1
            goto L_0x0008
        L_0x0039:
            r6.removeAllViews()
        L_0x003c:
            int r8 = r7.getCount()
        L_0x0040:
            r1 = 3
            int r1 = java.lang.Math.min(r8, r1)
            if (r0 >= r1) goto L_0x0084
            int r1 = r7.getItemViewType(r0)
            android.util.SparseArray<java.util.LinkedList<android.view.View>> r2 = r6.b
            java.lang.Object r2 = r2.get(r1)
            java.util.LinkedList r2 = (java.util.LinkedList) r2
            r3 = 0
            if (r2 == 0) goto L_0x0063
            int r4 = r2.size()
            if (r4 != 0) goto L_0x0063
            java.lang.Object r2 = r2.removeLast()
            r3 = r2
            android.view.View r3 = (android.view.View) r3
        L_0x0063:
            android.view.View r2 = r7.getView(r0, r3, r6)
            com.xiaomi.smarthome.device.choosedevice.XLinearLayout$CacheItemData r3 = new com.xiaomi.smarthome.device.choosedevice.XLinearLayout$CacheItemData
            r3.<init>((int) r1, (android.view.View) r2)
            r2.setTag(r3)
            android.view.ViewGroup$LayoutParams r1 = r2.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r1 = (android.widget.LinearLayout.LayoutParams) r1
            if (r1 == 0) goto L_0x007e
            r3 = 16
            r1.gravity = r3
            r2.setLayoutParams(r1)
        L_0x007e:
            r6.addView(r2)
            int r0 = r0 + 1
            goto L_0x0040
        L_0x0084:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.choosedevice.XLinearLayout.setAdapter(android.widget.BaseAdapter, boolean):com.xiaomi.smarthome.device.choosedevice.XLinearLayout");
    }

    public void setOnItemClickListener(final AdapterView.OnItemClickListener onItemClickListener) {
        int childCount = getChildCount();
        for (final int i = 0; i < childCount; i++) {
            getChildAt(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    onItemClickListener.onItemClick((AdapterView) null, view, i, (long) view.getId());
                }
            });
        }
    }

    public void playSoundEffect(final int i) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.playSoundEffect(i);
        } else {
            post(new Runnable() {
                public void run() {
                    XLinearLayout.super.playSoundEffect(i);
                }
            });
        }
    }

    private class CacheItemData {

        /* renamed from: a  reason: collision with root package name */
        int f15369a;
        RecyclerView.ViewHolder b;
        View c;

        public CacheItemData(int i, RecyclerView.ViewHolder viewHolder) {
            this.f15369a = i;
            this.b = viewHolder;
        }

        public CacheItemData(int i, View view) {
            this.f15369a = i;
            this.c = view;
        }
    }
}
