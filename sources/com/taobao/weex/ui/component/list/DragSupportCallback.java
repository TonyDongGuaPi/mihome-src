package com.taobao.weex.ui.component.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class DragSupportCallback extends ItemTouchHelper.Callback {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "WXListExComponent";
    private int dragFrom = -1;
    private int dragTo = -1;
    private final DragHelper mDragHelper;
    private boolean mEnableDifferentViewTypeDrag = false;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5827511351156462462L, "com/taobao/weex/ui/component/list/DragSupportCallback", 45);
        $jacocoData = a2;
        return a2;
    }

    DragSupportCallback(@NonNull DragHelper dragHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDragHelper = dragHelper;
        this.mEnableDifferentViewTypeDrag = false;
        $jacocoInit[0] = true;
    }

    DragSupportCallback(@NonNull DragHelper dragHelper, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDragHelper = dragHelper;
        this.mEnableDifferentViewTypeDrag = z;
        $jacocoInit[1] = true;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            $jacocoInit[2] = true;
        } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[6] = true;
            int makeMovementFlags = makeMovementFlags(3, 48);
            $jacocoInit[7] = true;
            return makeMovementFlags;
        }
        $jacocoInit[4] = true;
        int makeMovementFlags2 = makeMovementFlags(15, 0);
        $jacocoInit[5] = true;
        return makeMovementFlags2;
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (viewHolder == null) {
            $jacocoInit[8] = true;
        } else if (viewHolder2 == null) {
            $jacocoInit[9] = true;
        } else {
            if (this.mEnableDifferentViewTypeDrag) {
                $jacocoInit[11] = true;
            } else if (viewHolder.getItemViewType() == viewHolder2.getItemViewType()) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                return false;
            }
            if (!this.mDragHelper.isDragExcluded(viewHolder)) {
                $jacocoInit[14] = true;
                try {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    $jacocoInit[16] = true;
                    int adapterPosition2 = viewHolder2.getAdapterPosition();
                    if (this.dragFrom != -1) {
                        $jacocoInit[17] = true;
                    } else {
                        this.dragFrom = adapterPosition;
                        $jacocoInit[18] = true;
                    }
                    this.dragTo = adapterPosition2;
                    $jacocoInit[19] = true;
                    this.mDragHelper.onDragging(adapterPosition, adapterPosition2);
                    $jacocoInit[20] = true;
                    return true;
                } catch (Exception e) {
                    $jacocoInit[21] = true;
                    WXLogUtils.e(TAG, e.getMessage());
                    $jacocoInit[22] = true;
                    return false;
                }
            } else {
                $jacocoInit[15] = true;
                return false;
            }
        }
        $jacocoInit[10] = true;
        return false;
    }

    public boolean isLongPressDragEnabled() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mDragHelper.isDraggable()) {
            $jacocoInit[23] = true;
        } else if (!this.mDragHelper.isLongPressDragEnabled()) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            z = true;
            $jacocoInit[27] = true;
            return z;
        }
        z = false;
        $jacocoInit[26] = true;
        $jacocoInit[27] = true;
        return z;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        $jacocoInit()[28] = true;
    }

    public boolean isItemViewSwipeEnabled() {
        $jacocoInit()[29] = true;
        return false;
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == 0) {
            $jacocoInit[30] = true;
        } else if (!(viewHolder instanceof ListBaseViewHolder)) {
            $jacocoInit[31] = true;
        } else {
            ListBaseViewHolder listBaseViewHolder = (ListBaseViewHolder) viewHolder;
            $jacocoInit[32] = true;
            if (listBaseViewHolder.getComponent() == null) {
                $jacocoInit[33] = true;
            } else {
                $jacocoInit[34] = true;
                this.mDragHelper.onDragStart(listBaseViewHolder.getComponent(), listBaseViewHolder.getAdapterPosition());
                $jacocoInit[35] = true;
            }
        }
        super.onSelectedChanged(viewHolder, i);
        $jacocoInit[36] = true;
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        super.clearView(recyclerView, viewHolder);
        if (!(viewHolder instanceof ListBaseViewHolder)) {
            $jacocoInit[37] = true;
        } else {
            ListBaseViewHolder listBaseViewHolder = (ListBaseViewHolder) viewHolder;
            $jacocoInit[38] = true;
            if (listBaseViewHolder.getComponent() == null) {
                $jacocoInit[39] = true;
            } else if (this.dragFrom == -1) {
                $jacocoInit[40] = true;
            } else if (this.dragTo == -1) {
                $jacocoInit[41] = true;
            } else {
                $jacocoInit[42] = true;
                this.mDragHelper.onDragEnd(listBaseViewHolder.getComponent(), this.dragFrom, this.dragTo);
                $jacocoInit[43] = true;
            }
        }
        this.dragTo = -1;
        this.dragFrom = -1;
        $jacocoInit[44] = true;
    }
}
