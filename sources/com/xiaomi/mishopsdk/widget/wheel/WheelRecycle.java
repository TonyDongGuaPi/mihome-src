package com.xiaomi.mishopsdk.widget.wheel;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

public class WheelRecycle {
    private List<View> emptyItems;
    private List<View> items;
    private WheelView wheel;

    public WheelRecycle(WheelView wheelView) {
        this.wheel = wheelView;
    }

    public int recycleItems(LinearLayout linearLayout, int i, ItemsRange itemsRange) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (!itemsRange.contains(i)) {
                recycleView(linearLayout.getChildAt(i2), i);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i3++;
                }
            } else {
                i2++;
            }
            i++;
        }
        return i3;
    }

    public View getItem() {
        return getCachedView(this.items);
    }

    public View getEmptyItem() {
        return getCachedView(this.emptyItems);
    }

    public void clearAll() {
        if (this.items != null) {
            this.items.clear();
        }
        if (this.emptyItems != null) {
            this.emptyItems.clear();
        }
    }

    private List<View> addView(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(view);
        return list;
    }

    private void recycleView(View view, int i) {
        int itemsCount = this.wheel.getViewAdapter().getItemsCount();
        if ((i < 0 || i >= itemsCount) && !this.wheel.isCyclic()) {
            this.emptyItems = addView(view, this.emptyItems);
            return;
        }
        while (i < 0) {
            i += itemsCount;
        }
        int i2 = i % itemsCount;
        this.items = addView(view, this.items);
    }

    private View getCachedView(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = list.get(0);
        list.remove(0);
        return view;
    }
}
