package com.andview.refreshview.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;
import com.andview.refreshview.utils.LogUtils;
import com.andview.refreshview.utils.Utils;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /* renamed from: a  reason: collision with root package name */
    protected View f4780a = null;
    protected View b = null;
    private boolean c = true;
    private boolean d = false;
    private final RecyclerViewDataObserver e = new RecyclerViewDataObserver();
    private XRefreshView f;

    public abstract VH a(View view);

    public abstract VH a(ViewGroup viewGroup, int i, boolean z);

    public abstract void a(VH vh, int i, boolean z);

    public int c(int i) {
        return -4;
    }

    public abstract int g();

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        a(this.f4780a, false);
        if (i == -1) {
            Utils.a(this.f4780a);
            return a(this.f4780a);
        } else if (i != -3) {
            return a(viewGroup, i, true);
        } else {
            Utils.a(this.b);
            return a(this.b);
        }
    }

    private void a(View view, boolean z) {
        if (this.c && view != null && (view instanceof IFooterCallBack)) {
            IFooterCallBack iFooterCallBack = (IFooterCallBack) view;
            if (z) {
                if (!iFooterCallBack.isShowing()) {
                    iFooterCallBack.show(z);
                }
            } else if (g() == 0 && iFooterCallBack.isShowing()) {
                iFooterCallBack.show(false);
            } else if (g() != 0 && !iFooterCallBack.isShowing()) {
                iFooterCallBack.show(true);
            }
        }
    }

    public void a() {
        LogUtils.a("test addFooterView");
        if (this.d) {
            notifyItemInserted(getItemCount());
            this.d = false;
            a(this.f4780a, true);
        }
    }

    public boolean b() {
        return !this.d;
    }

    public void c() {
        LogUtils.a("test removeFooterView");
        if (!this.d) {
            notifyItemRemoved(getItemCount() - 1);
            this.d = true;
        }
    }

    public boolean d() {
        return g() == 0;
    }

    public final void onBindViewHolder(VH vh, int i) {
        int f2 = f();
        if (!b(i) && !a(i)) {
            a(vh, i - f2, true);
        }
    }

    public void onViewAttachedToWindow(VH vh) {
        super.onViewAttachedToWindow(vh);
        int layoutPosition = vh.getLayoutPosition();
        ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(a(layoutPosition) || b(layoutPosition));
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ViewParent parent = recyclerView.getParent();
        if (parent != null && (parent instanceof XRefreshView)) {
            this.f = (XRefreshView) recyclerView.getParent();
            if (this.f != null && !this.e.b()) {
                this.e.a(this, this.f);
                this.e.a();
                registerAdapterDataObserver(this.e);
            }
        }
    }

    public void b(View view) {
        if (view instanceof IFooterCallBack) {
            this.f4780a = view;
            Utils.a(this.f4780a);
            if (!(this.f == null || this.f.getContentView() == null)) {
                this.f.getContentView().a(this, this.f);
            }
            a(this.f4780a, false);
            notifyDataSetChanged();
            return;
        }
        throw new RuntimeException("footerView must be implementes IFooterCallBack!");
    }

    public void a(View view, RecyclerView recyclerView) {
        if (recyclerView != null) {
            Utils.a(view);
            this.b = view;
            notifyDataSetChanged();
        }
    }

    public View a(@LayoutRes int i, RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }
        Context context = recyclerView.getContext();
        if (context.getResources().getResourceTypeName(i).contains("layout")) {
            this.b = LayoutInflater.from(context).inflate(i, new FrameLayout(recyclerView.getContext()), false);
            notifyDataSetChanged();
            return this.b;
        }
        throw new RuntimeException(context.getResources().getResourceName(i) + " is a illegal layoutid , please check your layout id first !");
    }

    public boolean a(int i) {
        return this.f4780a != null && i >= g() + f();
    }

    public boolean b(int i) {
        return f() > 0 && i == 0;
    }

    public View e() {
        return this.f4780a;
    }

    public final int getItemViewType(int i) {
        if (b(i)) {
            return -3;
        }
        if (a(i)) {
            return -1;
        }
        if (f() > 0) {
            i--;
        }
        return c(i);
    }

    public int f() {
        return this.b == null ? 0 : 1;
    }

    public final int getItemCount() {
        int g = g() + f();
        return (this.f4780a == null || this.d) ? g : g + 1;
    }

    public void a(List<?> list, int i, int i2) {
        Collections.swap(list, i, i2);
    }

    public void a(boolean z) {
        this.c = z;
    }

    public <T> void a(List<T> list, T t, int i) {
        list.add(i, t);
        notifyItemInserted(i + f());
    }

    public void a(List<?> list, int i) {
        if (list.size() > 0) {
            notifyItemRemoved(i + f());
        }
    }

    public void a(List<?> list) {
        int f2 = f();
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(f2, size);
    }

    protected class VIEW_TYPES {

        /* renamed from: a  reason: collision with root package name */
        public static final int f4781a = -1;
        public static final int b = -3;
        public static final int c = -4;

        protected VIEW_TYPES() {
        }
    }
}
