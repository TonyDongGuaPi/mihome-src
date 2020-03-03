package com.xiaomi.smarthome.newui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public class HeaderAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20392a = 100000;
    private static final int b = 200000;
    private SparseArrayCompat<View> c = new SparseArrayCompat<>();
    private SparseArrayCompat<View> d = new SparseArrayCompat<>();
    private RecyclerView.Adapter e;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.e = adapter;
    }

    private boolean a(int i) {
        return i < a();
    }

    private boolean b(int i) {
        return i >= a() + c();
    }

    public void a(View view) {
        this.c.put(this.c.size() + 100000, view);
    }

    public void b(View view) {
        this.d.put(this.d.size() + b, view);
    }

    public int a() {
        return this.c.size();
    }

    public int b() {
        return this.d.size();
    }

    private int c() {
        return this.e.getItemCount();
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (this.c.get(i) != null) {
            return new ViewHolder(this.c.get(i));
        }
        if (this.d.get(i) != null) {
            return new ViewHolder(this.d.get(i));
        }
        return this.e.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (!a(i) && !b(i)) {
            this.e.onBindViewHolder(viewHolder, i - a());
        }
    }

    public int getItemCount() {
        return a() + b() + c();
    }

    public int getItemViewType(int i) {
        if (a(i)) {
            return this.c.keyAt(i);
        }
        if (b(i)) {
            return this.d.keyAt((i - a()) - c());
        }
        return this.e.getItemViewType(i - a());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
