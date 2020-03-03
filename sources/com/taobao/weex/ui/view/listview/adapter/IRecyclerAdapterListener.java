package com.taobao.weex.ui.view.listview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public interface IRecyclerAdapterListener<T extends RecyclerView.ViewHolder> {
    int getItemCount();

    long getItemId(int i);

    int getItemViewType(int i);

    void onBindViewHolder(T t, int i);

    T onCreateViewHolder(ViewGroup viewGroup, int i);

    boolean onFailedToRecycleView(T t);

    void onViewRecycled(T t);
}
