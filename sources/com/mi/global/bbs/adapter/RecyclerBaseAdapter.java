package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class RecyclerBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext = null;
    protected int mCount = 0;
    protected LayoutInflater mInflater;

    public Object getItem(int i) {
        return null;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public void onBindViewHolder(VH vh, int i) {
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public RecyclerBaseAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public int getItemCount() {
        return this.mCount;
    }
}
