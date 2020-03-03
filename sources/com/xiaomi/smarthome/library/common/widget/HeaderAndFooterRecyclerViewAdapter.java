package com.xiaomi.smarthome.library.common.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class HeaderAndFooterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18845a = Integer.MIN_VALUE;
    private static final int b = 1073741823;
    private static final int c = 0;
    private RecyclerView.Adapter d;
    private ArrayList<View> e = new ArrayList<>(2);
    private ArrayList<View> f = new ArrayList<>(1);
    private RecyclerView.AdapterDataObserver g = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            HeaderAndFooterRecyclerViewAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeChanged(i + HeaderAndFooterRecyclerViewAdapter.this.b(), i2);
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeInserted(i + HeaderAndFooterRecyclerViewAdapter.this.b(), i2);
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeRemoved(i + HeaderAndFooterRecyclerViewAdapter.this.b(), i2);
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            int b = HeaderAndFooterRecyclerViewAdapter.this.b();
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeChanged(i + b, i2 + b + i3);
        }
    };

    public HeaderAndFooterRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        a(adapter);
    }

    public void a(RecyclerView.Adapter adapter) {
        if (this.d != null) {
            notifyItemRangeRemoved(b(), this.d.getItemCount());
            this.d.unregisterAdapterDataObserver(this.g);
        }
        this.d = adapter;
        this.d.registerAdapterDataObserver(this.g);
        notifyItemRangeInserted(b(), this.d.getItemCount());
    }

    public RecyclerView.Adapter a() {
        return this.d;
    }

    public HeaderAndFooterRecyclerViewAdapter a(View view) {
        if (view != null) {
            int indexOf = this.e.indexOf(view);
            if (indexOf <= -1) {
                this.e.add(view);
                notifyItemInserted(this.e.size() - 1);
                notifyItemRangeChanged(this.e.size(), getItemCount() - 1);
            } else if (indexOf != this.e.size() - 1) {
                notifyItemMoved(indexOf, this.e.size() - 1);
            }
            return this;
        }
        throw new RuntimeException("header is null");
    }

    public HeaderAndFooterRecyclerViewAdapter a(int i, View view) {
        if (view != null) {
            int indexOf = this.e.indexOf(view);
            if (indexOf > -1) {
                if (indexOf != i) {
                    notifyItemMoved(indexOf, i);
                }
            } else if (i > this.e.size() - 1) {
                this.e.add(view);
                notifyItemInserted(this.e.size() - 1);
                notifyItemRangeChanged(this.e.size(), getItemCount() - 1);
            } else {
                this.e.add(i, view);
                notifyItemInserted(i);
                notifyItemRangeChanged(i + 1, getItemCount() - 1);
            }
            return this;
        }
        throw new RuntimeException("header is null");
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        int indexOf = this.e.indexOf(viewHolder);
        if (indexOf >= 0) {
            notifyItemChanged(indexOf);
        }
    }

    public void b(RecyclerView.ViewHolder viewHolder) {
        int indexOf = this.e.indexOf(viewHolder);
        if (indexOf >= 0) {
            this.e.remove(indexOf);
            notifyDataSetChanged();
        }
    }

    public void b(View view) {
        if (view != null) {
            int indexOf = this.f.indexOf(view);
            if (indexOf <= -1) {
                this.f.add(view);
                notifyItemInserted(getItemCount());
            } else if (indexOf != this.f.size() - 1) {
                notifyItemMoved(indexOf, this.f.size() - 1);
            }
        } else {
            throw new RuntimeException("footer is null");
        }
    }

    public void c(RecyclerView.ViewHolder viewHolder) {
        if (this.f.remove(viewHolder)) {
            notifyDataSetChanged();
        }
    }

    public int b() {
        return this.e.size();
    }

    public int c() {
        return this.f.size();
    }

    public boolean a(int i) {
        return b() > 0 && i == 0;
    }

    public boolean b(int i) {
        int itemCount = getItemCount() - 1;
        if (c() <= 0 || i != itemCount) {
            return false;
        }
        return true;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int b2 = b();
        if (i < b2 || i >= this.d.getItemCount() + b2) {
            ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                return;
            }
            return;
        }
        this.d.onBindViewHolder(viewHolder, i - b2);
    }

    public int getItemCount() {
        return b() + c() + this.d.getItemCount();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i < this.e.size() - 2147483648) {
            return new RecyclerView.ViewHolder(this.e.get(i - Integer.MIN_VALUE)) {
            };
        }
        if (i < b || this.f.size() <= 0) {
            return this.d.onCreateViewHolder(viewGroup, i + 0);
        }
        return new RecyclerView.ViewHolder(this.f.get(i - b)) {
        };
    }

    public int getItemViewType(int i) {
        int size = this.e.size();
        if (i < size) {
            return i - 2147483648;
        }
        if (size > i || i >= this.d.getItemCount() + size) {
            return ((i - size) - this.d.getItemCount()) + b;
        }
        return this.d.getItemViewType(i - size) + 0;
    }
}
