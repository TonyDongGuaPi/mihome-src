package com.taobao.weex.ui.component.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.taobao.weex.ui.component.WXComponent;

interface DragHelper {
    boolean isDragExcluded(@NonNull RecyclerView.ViewHolder viewHolder);

    boolean isDraggable();

    boolean isLongPressDragEnabled();

    void onDragEnd(@NonNull WXComponent wXComponent, int i, int i2);

    void onDragStart(@NonNull WXComponent wXComponent, int i);

    void onDragging(int i, int i2);

    void setDragExcluded(@NonNull RecyclerView.ViewHolder viewHolder, boolean z);

    void setDraggable(boolean z);

    void setLongPressDragEnabled(boolean z);

    void startDrag(@NonNull RecyclerView.ViewHolder viewHolder);
}
