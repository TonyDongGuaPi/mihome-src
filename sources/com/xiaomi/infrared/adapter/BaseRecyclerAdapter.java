package com.xiaomi.infrared.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    protected Context f10218a;
    protected List<T> b;
    /* access modifiers changed from: private */
    public OnItemClickListener c;
    /* access modifiers changed from: private */
    public OnItemLongClickListener d;
    /* access modifiers changed from: private */
    public RecyclerView e;

    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int i);
    }

    public interface OnItemLongClickListener {
        boolean a(RecyclerView recyclerView, View view, int i);
    }

    public abstract void a(ViewHolder viewHolder, T t, int i);

    public abstract int c(int i);

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.f10218a = context;
        this.b = list;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.e = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.e = null;
    }

    public void a(T t, int i) {
        this.b.add(i, t);
        notifyItemInserted(i);
    }

    public void a(int i) {
        this.b.remove(i);
        notifyItemRemoved(i);
    }

    public T b(int i) {
        if (i < this.b.size()) {
            return this.b.get(i);
        }
        return null;
    }

    /* renamed from: a */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return ViewHolder.a(a(i, viewGroup));
    }

    /* renamed from: a */
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BaseRecyclerAdapter.this.c != null) {
                    BaseRecyclerAdapter.this.c.onItemClick(BaseRecyclerAdapter.this.e, view, viewHolder.getAdapterPosition());
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (BaseRecyclerAdapter.this.d == null) {
                    return false;
                }
                BaseRecyclerAdapter.this.d.a(BaseRecyclerAdapter.this.e, view, viewHolder.getAdapterPosition());
                return true;
            }
        });
        a(viewHolder, b(i), i);
    }

    public int getItemCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }

    public void a(OnItemLongClickListener onItemLongClickListener) {
        this.d = onItemLongClickListener;
    }

    public View a(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(this.f10218a).inflate(c(i), viewGroup, false);
    }

    public void a(List<T> list) {
        if (list != null) {
            this.b = list;
            notifyDataSetChanged();
        }
    }
}
