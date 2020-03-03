package com.xiaomi.mishopsdk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public abstract class BaseDataAdapter<T> extends BaseAdapter {
    private static final String TAG = "BaseDataAdapter";
    protected Context mContext;
    protected ArrayList<T> mData;
    protected boolean mDataValid = false;

    /* access modifiers changed from: protected */
    @SuppressLint({"ResourceAsColor"})
    public void bindBackground(View view, int i) {
    }

    public abstract void bindView(View view, int i, T t);

    public abstract View newView(Context context, T t, ViewGroup viewGroup);

    public BaseDataAdapter(Context context) {
        this.mContext = context;
    }

    public void updateData(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.mDataValid = true;
            this.mData = arrayList;
            notifyDataSetChanged();
            return;
        }
        this.mDataValid = false;
        notifyDataSetInvalidated();
    }

    public ArrayList<T> getData() {
        ArrayList<T> arrayList = new ArrayList<>();
        if (this.mData != null) {
            arrayList.addAll(this.mData);
        }
        return arrayList;
    }

    public int getCount() {
        if (!this.mDataValid || this.mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    public Object getItem(int i) {
        if (!this.mDataValid || this.mData == null) {
            return null;
        }
        return this.mData.get(i);
    }

    public long getItemId(int i) {
        if (!this.mDataValid || this.mData == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.mData.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.mData.get(i);
            if (view == null) {
                view = newView(this.mContext, t, viewGroup, i);
            }
            bindView(view, i, t);
            return view;
        }
    }

    /* access modifiers changed from: protected */
    public View newView(Context context, T t, ViewGroup viewGroup, int i) {
        return newView(context, t, viewGroup);
    }
}
