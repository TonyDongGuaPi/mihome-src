package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDate;
import java.util.ArrayList;
import java.util.List;

public class CloudVideoDateListViewAdapter extends RecyclerView.Adapter {
    public List<CloudVideoDate> cloudVideoDates;
    public IItemClickListener iItemClickListener;
    private InternalClickListener internalClickListener = new InternalClickListener();
    public RecyclerView recyclerView;
    public int selectedItemPosition = -1;

    public interface IItemClickListener<T> {
        void onItemClick(View view, int i, T t);
    }

    public CloudVideoDateListViewAdapter() {
        if (this.cloudVideoDates == null) {
            this.cloudVideoDates = new ArrayList();
        }
    }

    public CloudVideoDateListViewAdapter(List<CloudVideoDate> list) {
        if (list != null) {
            this.cloudVideoDates = list;
        } else {
            this.cloudVideoDates = new ArrayList();
        }
    }

    public CloudVideoDateListViewAdapter(List<CloudVideoDate> list, IItemClickListener iItemClickListener2) {
        if (list != null) {
            this.cloudVideoDates = list;
        } else {
            this.cloudVideoDates = new ArrayList();
        }
        this.iItemClickListener = iItemClickListener2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cs_view_date_list_item, viewGroup, false);
        inflate.setOnClickListener(this.internalClickListener);
        return new CloudVideoDateListViewHolder(inflate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (this.selectedItemPosition == i) {
            viewHolder.itemView.setSelected(true);
        } else {
            viewHolder.itemView.setSelected(false);
        }
        TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tvDay);
        TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.tvMonth);
        if (this.cloudVideoDates.get(i).isHasVideo) {
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.cs_icon_purple_dot_bg);
        } else {
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        textView.setText("" + this.cloudVideoDates.get(i).day);
        textView2.setText("" + this.cloudVideoDates.get(i).monthChinaPattern);
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public void setHasStableIds(boolean z) {
        super.setHasStableIds(z);
    }

    public long getItemId(int i) {
        return super.getItemId(i);
    }

    public int getItemCount() {
        if (this.cloudVideoDates != null) {
            return this.cloudVideoDates.size();
        }
        return 0;
    }

    public <T> T getItem(int i) {
        if (this.cloudVideoDates == null || this.cloudVideoDates.size() <= 0 || i < 0 || i >= this.cloudVideoDates.size()) {
            return null;
        }
        return this.cloudVideoDates.get(i);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return super.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        refreshSelectedStatus(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        refreshSelectedStatus(viewHolder);
    }

    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.registerAdapterDataObserver(adapterDataObserver);
    }

    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        super.onAttachedToRecyclerView(recyclerView2);
        this.recyclerView = recyclerView2;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = null;
        super.onDetachedFromRecyclerView(recyclerView2);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        super.onBindViewHolder(viewHolder, i, list);
    }

    private class InternalClickListener implements View.OnClickListener {
        private InternalClickListener() {
        }

        public void onClick(View view) {
            if (!(CloudVideoDateListViewAdapter.this.recyclerView == null || CloudVideoDateListViewAdapter.this.iItemClickListener == null)) {
                if (CloudVideoDateListViewAdapter.this.cloudVideoDates != null && CloudVideoDateListViewAdapter.this.cloudVideoDates.size() > 0) {
                    for (int i = 0; i < CloudVideoDateListViewAdapter.this.cloudVideoDates.size(); i++) {
                        View findViewByPosition = CloudVideoDateListViewAdapter.this.recyclerView.getLayoutManager().findViewByPosition(i);
                        if (findViewByPosition != null) {
                            findViewByPosition.setSelected(false);
                        }
                    }
                }
                CloudVideoDateListViewAdapter.this.selectedItemPosition = CloudVideoDateListViewAdapter.this.recyclerView.getChildAdapterPosition(view);
                CloudVideoDateListViewAdapter.this.iItemClickListener.onItemClick(view, CloudVideoDateListViewAdapter.this.selectedItemPosition, CloudVideoDateListViewAdapter.this.getItem(CloudVideoDateListViewAdapter.this.selectedItemPosition));
            }
            view.setSelected(true);
        }
    }

    private void refreshSelectedStatus(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            if (this.selectedItemPosition == this.recyclerView.getChildAdapterPosition(viewHolder.itemView)) {
                viewHolder.itemView.setSelected(true);
            } else {
                viewHolder.itemView.setSelected(false);
            }
        }
    }

    public boolean isAllDateDataUpdated() {
        if (this.cloudVideoDates == null || this.cloudVideoDates.size() <= 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (CloudVideoDate cloudVideoDate : this.cloudVideoDates) {
            if (currentTimeMillis - cloudVideoDate.lastUpdateTS > 60000) {
                return false;
            }
        }
        return true;
    }
}
