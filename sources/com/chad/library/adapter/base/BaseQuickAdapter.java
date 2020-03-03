package com.chad.library.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseQuickAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    public static final int m = 4;
    public static final int n = 5;
    protected static final String o = "BaseQuickAdapter";
    public static final int t = 273;
    public static final int u = 546;
    public static final int v = 819;
    public static final int w = 1365;
    private Interpolator A;
    private int B;
    private int C;
    private BaseAnimation D;
    private BaseAnimation E;
    private LinearLayout F;
    private LinearLayout G;
    private FrameLayout H;
    private boolean I;
    private boolean J;
    private boolean K;
    private RecyclerView L;
    private boolean M;
    private boolean N;
    private UpFetchListener O;
    private int P;
    private boolean Q;
    private boolean R;
    /* access modifiers changed from: private */
    public SpanSizeLookup S;
    private MultiTypeDelegate<T> T;
    private int U;

    /* renamed from: a  reason: collision with root package name */
    private boolean f5119a;
    private boolean b;
    private boolean c;
    /* access modifiers changed from: private */
    public LoadMoreView d;
    /* access modifiers changed from: private */
    public RequestLoadMoreListener e;
    /* access modifiers changed from: private */
    public boolean f;
    private OnItemClickListener g;
    private OnItemLongClickListener h;
    private OnItemChildClickListener i;
    protected Context p;
    protected int q;
    protected LayoutInflater r;
    protected List<T> s;
    private OnItemChildLongClickListener x;
    private boolean y;
    private boolean z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationType {
    }

    public interface OnItemChildClickListener {
        void a(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemChildLongClickListener {
        boolean a(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemClickListener {
        void a(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemLongClickListener {
        boolean a(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface RequestLoadMoreListener {
        void a();
    }

    public interface SpanSizeLookup {
        int a(GridLayoutManager gridLayoutManager, int i);
    }

    public interface UpFetchListener {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract void a(K k2, T t2);

    public long getItemId(int i2) {
        return (long) i2;
    }

    /* access modifiers changed from: protected */
    public boolean i(int i2) {
        return i2 == 1365 || i2 == 273 || i2 == 819 || i2 == 546;
    }

    /* access modifiers changed from: protected */
    public RecyclerView f() {
        return this.L;
    }

    private void c(RecyclerView recyclerView) {
        this.L = recyclerView;
    }

    private void a() {
        if (f() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    public void a(RecyclerView recyclerView) {
        if (f() == null) {
            c(recyclerView);
            f().setAdapter(this);
            return;
        }
        throw new RuntimeException("Don't bind twice");
    }

    @Deprecated
    public void a(RequestLoadMoreListener requestLoadMoreListener) {
        b(requestLoadMoreListener);
    }

    private void b(RequestLoadMoreListener requestLoadMoreListener) {
        this.e = requestLoadMoreListener;
        this.f5119a = true;
        this.b = true;
        this.c = false;
    }

    public void a(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        b(requestLoadMoreListener);
        if (f() == null) {
            c(recyclerView);
        }
    }

    public void g() {
        a();
        b(f());
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager;
        e(false);
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null) {
            if (layoutManager instanceof LinearLayoutManager) {
                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 != BaseQuickAdapter.this.getItemCount()) {
                            BaseQuickAdapter.this.e(true);
                        }
                    }
                }, 50);
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
                        staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(iArr);
                        if (BaseQuickAdapter.this.a(iArr) + 1 != BaseQuickAdapter.this.getItemCount()) {
                            BaseQuickAdapter.this.e(true);
                        }
                    }
                }, 50);
            }
        }
    }

    /* access modifiers changed from: private */
    public int a(int[] iArr) {
        int i2 = -1;
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    public void b(boolean z2) {
        this.M = z2;
    }

    public boolean h() {
        return this.M;
    }

    public void d(int i2) {
        this.P = i2;
    }

    private void b(int i2) {
        if (h() && !i() && i2 <= this.P && this.O != null) {
            this.O.a();
        }
    }

    public boolean i() {
        return this.N;
    }

    public void c(boolean z2) {
        this.N = z2;
    }

    public void a(UpFetchListener upFetchListener) {
        this.O = upFetchListener;
    }

    public void e(int i2) {
        this.C = i2;
    }

    public void a(LoadMoreView loadMoreView) {
        this.d = loadMoreView;
    }

    public int j() {
        if (this.e == null || !this.b) {
            return 0;
        }
        if ((this.f5119a || !this.d.b()) && this.s.size() != 0) {
            return 1;
        }
        return 0;
    }

    public int k() {
        return t() + this.s.size() + u();
    }

    public boolean l() {
        return this.c;
    }

    public void m() {
        d(false);
    }

    public void d(boolean z2) {
        if (j() != 0) {
            this.c = false;
            this.f5119a = false;
            this.d.a(z2);
            if (z2) {
                notifyItemRemoved(k());
                return;
            }
            this.d.a(4);
            notifyItemChanged(k());
        }
    }

    public void n() {
        if (j() != 0) {
            this.c = false;
            this.f5119a = true;
            this.d.a(1);
            notifyItemChanged(k());
        }
    }

    public void o() {
        if (j() != 0) {
            this.c = false;
            this.d.a(3);
            notifyItemChanged(k());
        }
    }

    public void e(boolean z2) {
        int j2 = j();
        this.b = z2;
        int j3 = j();
        if (j2 == 1) {
            if (j3 == 0) {
                notifyItemRemoved(k());
            }
        } else if (j3 == 1) {
            this.d.a(1);
            notifyItemInserted(k());
        }
    }

    public boolean p() {
        return this.b;
    }

    public void f(int i2) {
        this.B = i2;
    }

    public final void g(int i2) {
        notifyItemChanged(i2 + t());
    }

    public BaseQuickAdapter(@LayoutRes int i2, @Nullable List<T> list) {
        this.f5119a = false;
        this.b = false;
        this.c = false;
        this.d = new SimpleLoadMoreView();
        this.f = false;
        this.y = true;
        this.z = false;
        this.A = new LinearInterpolator();
        this.B = 300;
        this.C = -1;
        this.E = new AlphaInAnimation();
        this.I = true;
        this.P = 1;
        this.U = 1;
        this.s = list == null ? new ArrayList<>() : list;
        if (i2 != 0) {
            this.q = i2;
        }
    }

    public BaseQuickAdapter(@Nullable List<T> list) {
        this(0, list);
    }

    public BaseQuickAdapter(@LayoutRes int i2) {
        this(i2, (List) null);
    }

    public void a(@Nullable List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.s = list;
        if (this.e != null) {
            this.f5119a = true;
            this.b = true;
            this.c = false;
            this.d.a(1);
        }
        this.C = -1;
        notifyDataSetChanged();
    }

    @Deprecated
    public void a(@IntRange(from = 0) int i2, @NonNull T t2) {
        b(i2, t2);
    }

    public void b(@IntRange(from = 0) int i2, @NonNull T t2) {
        this.s.add(i2, t2);
        notifyItemInserted(i2 + t());
        p(1);
    }

    public void a(@NonNull T t2) {
        this.s.add(t2);
        notifyItemInserted(this.s.size() + t());
        p(1);
    }

    public void c(@IntRange(from = 0) int i2) {
        this.s.remove(i2);
        int t2 = i2 + t();
        notifyItemRemoved(t2);
        p(0);
        notifyItemRangeChanged(t2, this.s.size() - t2);
    }

    public void c(@IntRange(from = 0) int i2, @NonNull T t2) {
        this.s.set(i2, t2);
        notifyItemChanged(i2 + t());
    }

    public void a(@IntRange(from = 0) int i2, @NonNull Collection<? extends T> collection) {
        this.s.addAll(i2, collection);
        notifyItemRangeInserted(i2 + t(), collection.size());
        p(collection.size());
    }

    public void a(@NonNull Collection<? extends T> collection) {
        this.s.addAll(collection);
        notifyItemRangeInserted((this.s.size() - collection.size()) + t(), collection.size());
        p(collection.size());
    }

    public void b(@NonNull Collection<? extends T> collection) {
        if (collection != this.s) {
            this.s.clear();
            this.s.addAll(collection);
        }
        notifyDataSetChanged();
    }

    private void p(int i2) {
        if ((this.s == null ? 0 : this.s.size()) == i2) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    public List<T> q() {
        return this.s;
    }

    @Nullable
    public T h(@IntRange(from = 0) int i2) {
        if (i2 < 0 || i2 >= this.s.size()) {
            return null;
        }
        return this.s.get(i2);
    }

    @Deprecated
    public int r() {
        return t();
    }

    @Deprecated
    public int s() {
        return u();
    }

    public int t() {
        return (this.F == null || this.F.getChildCount() == 0) ? 0 : 1;
    }

    public int u() {
        return (this.G == null || this.G.getChildCount() == 0) ? 0 : 1;
    }

    public int v() {
        if (this.H == null || this.H.getChildCount() == 0 || !this.I || this.s.size() != 0) {
            return 0;
        }
        return 1;
    }

    public int getItemCount() {
        int i2 = 1;
        if (v() == 1) {
            if (this.J && t() != 0) {
                i2 = 2;
            }
            if (!this.K || u() == 0) {
                return i2;
            }
            return i2 + 1;
        }
        return j() + t() + this.s.size() + u();
    }

    public int getItemViewType(int i2) {
        boolean z2 = true;
        if (v() == 1) {
            if (!this.J || t() == 0) {
                z2 = false;
            }
            switch (i2) {
                case 0:
                    if (z2) {
                        return 273;
                    }
                    return w;
                case 1:
                    if (z2) {
                        return w;
                    }
                    return 819;
                case 2:
                    return 819;
                default:
                    return w;
            }
        } else {
            int t2 = t();
            if (i2 < t2) {
                return 273;
            }
            int i3 = i2 - t2;
            int size = this.s.size();
            if (i3 < size) {
                return a(i3);
            }
            if (i3 - size < u()) {
                return 819;
            }
            return u;
        }
    }

    /* access modifiers changed from: protected */
    public int a(int i2) {
        if (this.T != null) {
            return this.T.a(this.s, i2);
        }
        return super.getItemViewType(i2);
    }

    /* renamed from: b */
    public K onCreateViewHolder(ViewGroup viewGroup, int i2) {
        K k2;
        this.p = viewGroup.getContext();
        this.r = LayoutInflater.from(this.p);
        if (i2 == 273) {
            k2 = a((View) this.F);
        } else if (i2 == 546) {
            k2 = a(viewGroup);
        } else if (i2 == 819) {
            k2 = a((View) this.G);
        } else if (i2 != 1365) {
            k2 = a(viewGroup, i2);
            b((BaseViewHolder) k2);
        } else {
            k2 = a((View) this.H);
        }
        k2.a(this);
        return k2;
    }

    private K a(ViewGroup viewGroup) {
        K a2 = a(b(this.d.d(), viewGroup));
        a2.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BaseQuickAdapter.this.d.a() == 3) {
                    BaseQuickAdapter.this.w();
                }
                if (BaseQuickAdapter.this.f && BaseQuickAdapter.this.d.a() == 4) {
                    BaseQuickAdapter.this.w();
                }
            }
        });
        return a2;
    }

    public void w() {
        if (this.d.a() != 2) {
            this.d.a(1);
            notifyItemChanged(k());
        }
    }

    public void f(boolean z2) {
        this.f = z2;
    }

    /* renamed from: a */
    public void onViewAttachedToWindow(K k2) {
        super.onViewAttachedToWindow(k2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            g((RecyclerView.ViewHolder) k2);
        } else {
            a((RecyclerView.ViewHolder) k2);
        }
    }

    /* access modifiers changed from: protected */
    public void g(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    int itemViewType = BaseQuickAdapter.this.getItemViewType(i);
                    if (itemViewType == 273 && BaseQuickAdapter.this.x()) {
                        return 1;
                    }
                    if (itemViewType == 819 && BaseQuickAdapter.this.y()) {
                        return 1;
                    }
                    if (BaseQuickAdapter.this.S != null) {
                        return BaseQuickAdapter.this.i(itemViewType) ? gridLayoutManager.getSpanCount() : BaseQuickAdapter.this.S.a(gridLayoutManager, i - BaseQuickAdapter.this.t());
                    }
                    if (BaseQuickAdapter.this.i(itemViewType)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void g(boolean z2) {
        this.Q = z2;
    }

    public boolean x() {
        return this.Q;
    }

    public void h(boolean z2) {
        this.R = z2;
    }

    public boolean y() {
        return this.R;
    }

    public void a(SpanSizeLookup spanSizeLookup) {
        this.S = spanSizeLookup;
    }

    /* renamed from: a */
    public void onBindViewHolder(K k2, int i2) {
        b(i2);
        q(i2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 0) {
            a(k2, h(i2 - t()));
        } else if (itemViewType == 273) {
        } else {
            if (itemViewType == 546) {
                this.d.a((BaseViewHolder) k2);
            } else if (itemViewType != 819 && itemViewType != 1365) {
                a(k2, h(i2 - t()));
            }
        }
    }

    private void b(final BaseViewHolder baseViewHolder) {
        View view;
        if (baseViewHolder != null && (view = baseViewHolder.itemView) != null) {
            if (J() != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BaseQuickAdapter.this.a(view, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.t());
                    }
                });
            }
            if (I() != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return BaseQuickAdapter.this.b(view, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.t());
                    }
                });
            }
        }
    }

    public void a(View view, int i2) {
        J().a(this, view, i2);
    }

    public boolean b(View view, int i2) {
        return I().a(this, view, i2);
    }

    public void a(MultiTypeDelegate<T> multiTypeDelegate) {
        this.T = multiTypeDelegate;
    }

    public MultiTypeDelegate<T> z() {
        return this.T;
    }

    /* access modifiers changed from: protected */
    public K a(ViewGroup viewGroup, int i2) {
        int i3 = this.q;
        if (this.T != null) {
            i3 = this.T.a(i2);
        }
        return c(viewGroup, i3);
    }

    /* access modifiers changed from: protected */
    public K c(ViewGroup viewGroup, int i2) {
        return a(b(i2, viewGroup));
    }

    /* access modifiers changed from: protected */
    public K a(View view) {
        K k2;
        Class cls = getClass();
        Class cls2 = null;
        while (cls2 == null && cls != null) {
            cls2 = a(cls);
            cls = cls.getSuperclass();
        }
        if (cls2 == null) {
            k2 = new BaseViewHolder(view);
        } else {
            k2 = a(cls2, view);
        }
        return k2 != null ? k2 : new BaseViewHolder(view);
    }

    private K a(Class cls, View view) {
        try {
            if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{View.class});
                declaredConstructor.setAccessible(true);
                return (BaseViewHolder) declaredConstructor.newInstance(new Object[]{view});
            }
            Constructor declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{getClass(), View.class});
            declaredConstructor2.setAccessible(true);
            return (BaseViewHolder) declaredConstructor2.newInstance(new Object[]{this, view});
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    private Class a(Class cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
            if (type instanceof Class) {
                Class cls2 = (Class) type;
                if (BaseViewHolder.class.isAssignableFrom(cls2)) {
                    return cls2;
                }
            } else if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                if (rawType instanceof Class) {
                    Class cls3 = (Class) rawType;
                    if (BaseViewHolder.class.isAssignableFrom(cls3)) {
                        return cls3;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return null;
    }

    public LinearLayout A() {
        return this.F;
    }

    public LinearLayout B() {
        return this.G;
    }

    public int b(View view) {
        return c(view, -1);
    }

    public int c(View view, int i2) {
        return a(view, i2, 1);
    }

    public int a(View view, int i2, int i3) {
        int b2;
        if (this.F == null) {
            this.F = new LinearLayout(view.getContext());
            if (i3 == 1) {
                this.F.setOrientation(1);
                this.F.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.F.setOrientation(0);
                this.F.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.F.getChildCount();
        if (i2 < 0 || i2 > childCount) {
            i2 = childCount;
        }
        this.F.addView(view, i2);
        if (this.F.getChildCount() == 1 && (b2 = b()) != -1) {
            notifyItemInserted(b2);
        }
        return i2;
    }

    public int c(View view) {
        return b(view, 0, 1);
    }

    public int d(View view, int i2) {
        return b(view, i2, 1);
    }

    public int b(View view, int i2, int i3) {
        if (this.F == null || this.F.getChildCount() <= i2) {
            return a(view, i2, i3);
        }
        this.F.removeViewAt(i2);
        this.F.addView(view, i2);
        return i2;
    }

    public int d(View view) {
        return c(view, -1, 1);
    }

    public int e(View view, int i2) {
        return c(view, i2, 1);
    }

    public int c(View view, int i2, int i3) {
        int c2;
        if (this.G == null) {
            this.G = new LinearLayout(view.getContext());
            if (i3 == 1) {
                this.G.setOrientation(1);
                this.G.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.G.setOrientation(0);
                this.G.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.G.getChildCount();
        if (i2 < 0 || i2 > childCount) {
            i2 = childCount;
        }
        this.G.addView(view, i2);
        if (this.G.getChildCount() == 1 && (c2 = c()) != -1) {
            notifyItemInserted(c2);
        }
        return i2;
    }

    public int e(View view) {
        return d(view, 0, 1);
    }

    public int f(View view, int i2) {
        return d(view, i2, 1);
    }

    public int d(View view, int i2, int i3) {
        if (this.G == null || this.G.getChildCount() <= i2) {
            return c(view, i2, i3);
        }
        this.G.removeViewAt(i2);
        this.G.addView(view, i2);
        return i2;
    }

    public void f(View view) {
        int b2;
        if (t() != 0) {
            this.F.removeView(view);
            if (this.F.getChildCount() == 0 && (b2 = b()) != -1) {
                notifyItemRemoved(b2);
            }
        }
    }

    public void g(View view) {
        int c2;
        if (u() != 0) {
            this.G.removeView(view);
            if (this.G.getChildCount() == 0 && (c2 = c()) != -1) {
                notifyItemRemoved(c2);
            }
        }
    }

    public void C() {
        if (t() != 0) {
            this.F.removeAllViews();
            int b2 = b();
            if (b2 != -1) {
                notifyItemRemoved(b2);
            }
        }
    }

    public void D() {
        if (u() != 0) {
            this.G.removeAllViews();
            int c2 = c();
            if (c2 != -1) {
                notifyItemRemoved(c2);
            }
        }
    }

    private int b() {
        if (v() != 1 || this.J) {
            return 0;
        }
        return -1;
    }

    private int c() {
        int i2 = 1;
        if (v() != 1) {
            return t() + this.s.size();
        }
        if (this.J && t() != 0) {
            i2 = 2;
        }
        if (this.K) {
            return i2;
        }
        return -1;
    }

    public void a(int i2, ViewGroup viewGroup) {
        h(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }

    @Deprecated
    public void j(int i2) {
        a();
        a(i2, (ViewGroup) f());
    }

    public void h(View view) {
        boolean z2;
        int i2 = 0;
        if (this.H == null) {
            this.H = new FrameLayout(view.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.width = layoutParams2.width;
                layoutParams.height = layoutParams2.height;
            }
            this.H.setLayoutParams(layoutParams);
            z2 = true;
        } else {
            z2 = false;
        }
        this.H.removeAllViews();
        this.H.addView(view);
        this.I = true;
        if (z2 && v() == 1) {
            if (this.J && t() != 0) {
                i2 = 1;
            }
            notifyItemInserted(i2);
        }
    }

    public void i(boolean z2) {
        a(z2, false);
    }

    public void a(boolean z2, boolean z3) {
        this.J = z2;
        this.K = z3;
    }

    public void j(boolean z2) {
        this.I = z2;
    }

    public View E() {
        return this.H;
    }

    @Deprecated
    public void k(int i2) {
        l(i2);
    }

    public void l(int i2) {
        if (i2 > 1) {
            this.U = i2;
        }
    }

    private void q(int i2) {
        if (j() != 0 && i2 >= getItemCount() - this.U && this.d.a() == 1) {
            this.d.a(2);
            if (!this.c) {
                this.c = true;
                if (f() != null) {
                    f().post(new Runnable() {
                        public void run() {
                            BaseQuickAdapter.this.e.a();
                        }
                    });
                } else {
                    this.e.a();
                }
            }
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        BaseAnimation baseAnimation;
        if (!this.z) {
            return;
        }
        if (!this.y || viewHolder.getLayoutPosition() > this.C) {
            if (this.D != null) {
                baseAnimation = this.D;
            } else {
                baseAnimation = this.E;
            }
            for (Animator a2 : baseAnimation.a(viewHolder.itemView)) {
                a(a2, viewHolder.getLayoutPosition());
            }
            this.C = viewHolder.getLayoutPosition();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Animator animator, int i2) {
        animator.setDuration((long) this.B).start();
        animator.setInterpolator(this.A);
    }

    /* access modifiers changed from: protected */
    public View b(@LayoutRes int i2, ViewGroup viewGroup) {
        return this.r.inflate(i2, viewGroup, false);
    }

    public void m(int i2) {
        this.z = true;
        this.D = null;
        switch (i2) {
            case 1:
                this.E = new AlphaInAnimation();
                return;
            case 2:
                this.E = new ScaleInAnimation();
                return;
            case 3:
                this.E = new SlideInBottomAnimation();
                return;
            case 4:
                this.E = new SlideInLeftAnimation();
                return;
            case 5:
                this.E = new SlideInRightAnimation();
                return;
            default:
                return;
        }
    }

    public void a(BaseAnimation baseAnimation) {
        this.z = true;
        this.D = baseAnimation;
    }

    public void F() {
        this.z = true;
    }

    public void G() {
        this.z = false;
    }

    public void k(boolean z2) {
        this.y = z2;
    }

    @Nullable
    public View b(int i2, @IdRes int i3) {
        a();
        return a(f(), i2, i3);
    }

    @Nullable
    public View a(RecyclerView recyclerView, int i2, @IdRes int i3) {
        BaseViewHolder baseViewHolder;
        if (recyclerView == null || (baseViewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(i2)) == null) {
            return null;
        }
        return baseViewHolder.e(i3);
    }

    private int a(int i2, @NonNull List list) {
        int size = list.size();
        int size2 = (i2 + list.size()) - 1;
        int size3 = list.size() - 1;
        while (size3 >= 0) {
            if (list.get(size3) instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) list.get(size3);
                if (iExpandable.a() && a(iExpandable)) {
                    List b2 = iExpandable.b();
                    int i3 = size2 + 1;
                    this.s.addAll(i3, b2);
                    size += a(i3, b2);
                }
            }
            size3--;
            size2--;
        }
        return size;
    }

    public int a(@IntRange(from = 0) int i2, boolean z2, boolean z3) {
        int t2 = i2 - t();
        IExpandable s2 = s(t2);
        int i3 = 0;
        if (s2 == null) {
            return 0;
        }
        if (!a(s2)) {
            s2.a(true);
            notifyItemChanged(t2);
            return 0;
        }
        if (!s2.a()) {
            List b2 = s2.b();
            int i4 = t2 + 1;
            this.s.addAll(i4, b2);
            i3 = 0 + a(i4, b2);
            s2.a(true);
        }
        int t3 = t2 + t();
        if (z3) {
            if (z2) {
                notifyItemChanged(t3);
                notifyItemRangeInserted(t3 + 1, i3);
            } else {
                notifyDataSetChanged();
            }
        }
        return i3;
    }

    public int a(@IntRange(from = 0) int i2, boolean z2) {
        return a(i2, z2, true);
    }

    public int n(@IntRange(from = 0) int i2) {
        return a(i2, true, true);
    }

    public int b(int i2, boolean z2, boolean z3) {
        Object h2;
        int t2 = i2 - t();
        int i3 = t2 + 1;
        Object h3 = i3 < this.s.size() ? h(i3) : null;
        IExpandable s2 = s(t2);
        if (s2 == null) {
            return 0;
        }
        if (!a(s2)) {
            s2.a(true);
            notifyItemChanged(t2);
            return 0;
        }
        int a2 = a(t() + t2, false, false);
        while (i3 < this.s.size() && (h2 = h(i3)) != h3) {
            if (b(h2)) {
                a2 += a(t() + i3, false, false);
            }
            i3++;
        }
        if (z3) {
            if (z2) {
                notifyItemRangeInserted(t2 + t() + 1, a2);
            } else {
                notifyDataSetChanged();
            }
        }
        return a2;
    }

    public int b(int i2, boolean z2) {
        return b(i2, true, !z2);
    }

    public void H() {
        for (int size = (this.s.size() - 1) + t(); size >= t(); size--) {
            b(size, false, false);
        }
    }

    private int r(@IntRange(from = 0) int i2) {
        Object h2 = h(i2);
        int i3 = 0;
        if (!b(h2)) {
            return 0;
        }
        IExpandable iExpandable = (IExpandable) h2;
        if (iExpandable.a()) {
            List b2 = iExpandable.b();
            if (b2 == null) {
                return 0;
            }
            for (int size = b2.size() - 1; size >= 0; size--) {
                Object obj = b2.get(size);
                int d2 = d(obj);
                if (d2 >= 0) {
                    if (obj instanceof IExpandable) {
                        i3 += r(d2);
                    }
                    this.s.remove(d2);
                    i3++;
                }
            }
        }
        return i3;
    }

    public int c(@IntRange(from = 0) int i2, boolean z2, boolean z3) {
        int t2 = i2 - t();
        IExpandable s2 = s(t2);
        if (s2 == null) {
            return 0;
        }
        int r2 = r(t2);
        s2.a(false);
        int t3 = t2 + t();
        if (z3) {
            if (z2) {
                notifyItemChanged(t3);
                notifyItemRangeRemoved(t3 + 1, r2);
            } else {
                notifyDataSetChanged();
            }
        }
        return r2;
    }

    public int o(@IntRange(from = 0) int i2) {
        return c(i2, true, true);
    }

    public int c(@IntRange(from = 0) int i2, boolean z2) {
        return c(i2, z2, true);
    }

    private int d(T t2) {
        if (t2 == null || this.s == null || this.s.isEmpty()) {
            return -1;
        }
        return this.s.indexOf(t2);
    }

    private boolean a(IExpandable iExpandable) {
        List b2;
        if (iExpandable == null || (b2 = iExpandable.b()) == null || b2.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean b(T t2) {
        return t2 != null && (t2 instanceof IExpandable);
    }

    private IExpandable s(int i2) {
        Object h2 = h(i2);
        if (b(h2)) {
            return (IExpandable) h2;
        }
        return null;
    }

    public int c(@NonNull T t2) {
        int d2 = d(t2);
        if (d2 == -1) {
            return -1;
        }
        int d3 = t2 instanceof IExpandable ? ((IExpandable) t2).d() : Integer.MAX_VALUE;
        if (d3 == 0) {
            return d2;
        }
        if (d3 == -1) {
            return -1;
        }
        while (d2 >= 0) {
            T t3 = this.s.get(d2);
            if (t3 instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) t3;
                if (iExpandable.d() >= 0 && iExpandable.d() < d3) {
                    return d2;
                }
            }
            d2--;
        }
        return -1;
    }

    public void a(@Nullable OnItemClickListener onItemClickListener) {
        this.g = onItemClickListener;
    }

    public void a(OnItemChildClickListener onItemChildClickListener) {
        this.i = onItemChildClickListener;
    }

    public void a(OnItemLongClickListener onItemLongClickListener) {
        this.h = onItemLongClickListener;
    }

    public void a(OnItemChildLongClickListener onItemChildLongClickListener) {
        this.x = onItemChildLongClickListener;
    }

    public final OnItemLongClickListener I() {
        return this.h;
    }

    public final OnItemClickListener J() {
        return this.g;
    }

    @Nullable
    public final OnItemChildClickListener K() {
        return this.i;
    }

    @Nullable
    public final OnItemChildLongClickListener L() {
        return this.x;
    }
}
