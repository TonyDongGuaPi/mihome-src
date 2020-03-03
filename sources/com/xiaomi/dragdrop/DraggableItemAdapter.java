package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

public interface DraggableItemAdapter<T extends RecyclerView.ViewHolder> {
    ItemDraggableRange a(T t, int i);

    void a(int i, int i2);

    boolean a(T t, int i, int i2, int i3);

    void b(int i, int i2);

    void b(T t, int i);

    boolean c(int i, int i2);

    boolean d(int i, int i2);
}
