package com.taobao.weex.ui.view.listview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class RecyclerViewBaseAdapter<T extends ListBaseViewHolder> extends RecyclerView.Adapter<T> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private IRecyclerAdapterListener iRecyclerAdapterListener;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3790420394041719361L, "com/taobao/weex/ui/view/listview/adapter/RecyclerViewBaseAdapter", 41);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        onBindViewHolder((ListBaseViewHolder) viewHolder, i);
        $jacocoInit[39] = true;
    }

    public /* synthetic */ boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onFailedToRecycleView = onFailedToRecycleView((ListBaseViewHolder) viewHolder);
        $jacocoInit[37] = true;
        return onFailedToRecycleView;
    }

    public /* synthetic */ void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        onViewAttachedToWindow((ListBaseViewHolder) viewHolder);
        $jacocoInit[36] = true;
    }

    public /* synthetic */ void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        onViewDetachedFromWindow((ListBaseViewHolder) viewHolder);
        $jacocoInit[35] = true;
    }

    public /* synthetic */ void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        onViewRecycled((ListBaseViewHolder) viewHolder);
        $jacocoInit[38] = true;
    }

    public RecyclerViewBaseAdapter(IRecyclerAdapterListener iRecyclerAdapterListener2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.iRecyclerAdapterListener = iRecyclerAdapterListener2;
        $jacocoInit[0] = true;
    }

    public T onCreateViewHolder(ViewGroup viewGroup, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener != null) {
            $jacocoInit[1] = true;
            T t = (ListBaseViewHolder) this.iRecyclerAdapterListener.onCreateViewHolder(viewGroup, i);
            $jacocoInit[2] = true;
            return t;
        }
        $jacocoInit[3] = true;
        return null;
    }

    public void onViewAttachedToWindow(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onViewAttachedToWindow(t);
        $jacocoInit[4] = true;
        if (t == null) {
            $jacocoInit[5] = true;
        } else if (!t.isFullSpan()) {
            $jacocoInit[6] = true;
        } else {
            $jacocoInit[7] = true;
            ViewGroup.LayoutParams layoutParams = t.itemView.getLayoutParams();
            if (layoutParams == null) {
                $jacocoInit[8] = true;
            } else if (!(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                $jacocoInit[11] = true;
            }
        }
        $jacocoInit[12] = true;
    }

    public void onViewDetachedFromWindow(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onViewDetachedFromWindow(t);
        if (t == null) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            t.setComponentUsing(false);
            $jacocoInit[15] = true;
        }
        $jacocoInit[16] = true;
    }

    public void onBindViewHolder(T t, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener == null) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            this.iRecyclerAdapterListener.onBindViewHolder(t, i);
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
    }

    public int getItemViewType(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener != null) {
            $jacocoInit[21] = true;
            int itemViewType = this.iRecyclerAdapterListener.getItemViewType(i);
            $jacocoInit[22] = true;
            return itemViewType;
        }
        $jacocoInit[23] = true;
        return i;
    }

    public long getItemId(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        long itemId = this.iRecyclerAdapterListener.getItemId(i);
        $jacocoInit[24] = true;
        return itemId;
    }

    public int getItemCount() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener != null) {
            $jacocoInit[25] = true;
            int itemCount = this.iRecyclerAdapterListener.getItemCount();
            $jacocoInit[26] = true;
            return itemCount;
        }
        $jacocoInit[27] = true;
        return 0;
    }

    public void onViewRecycled(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener == null) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            this.iRecyclerAdapterListener.onViewRecycled(t);
            $jacocoInit[30] = true;
        }
        super.onViewRecycled(t);
        $jacocoInit[31] = true;
    }

    public boolean onFailedToRecycleView(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.iRecyclerAdapterListener != null) {
            $jacocoInit[32] = true;
            boolean onFailedToRecycleView = this.iRecyclerAdapterListener.onFailedToRecycleView(t);
            $jacocoInit[33] = true;
            return onFailedToRecycleView;
        }
        boolean onFailedToRecycleView2 = super.onFailedToRecycleView(t);
        $jacocoInit[34] = true;
        return onFailedToRecycleView2;
    }
}
