package com.andview.refreshview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.andview.refreshview.callback.IFooterCallBack;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.andview.refreshview.recyclerview.XSpanSizeLookup;
import com.andview.refreshview.utils.LogUtils;
import com.andview.refreshview.utils.Utils;

public class XRefreshContentView implements AbsListView.OnScrollListener, OnBottomLoadMoreTime, OnTopRefreshTime {
    private static final String x = "Recylerview的adapter请继承 BaseRecyclerAdapter,否则不能使用封装的Recyclerview的相关特性";
    private boolean A = false;
    private int B;
    private boolean C = true;
    private boolean D = true;

    /* renamed from: a  reason: collision with root package name */
    protected LAYOUT_MANAGER_TYPE f4761a;
    private View b;
    private int c;
    private OnTopRefreshTime d;
    private OnBottomLoadMoreTime e;
    /* access modifiers changed from: private */
    public XRefreshView f;
    private AbsListView.OnScrollListener g;
    /* access modifiers changed from: private */
    public RecyclerView.OnScrollListener h;
    /* access modifiers changed from: private */
    public XRefreshView.XRefreshViewListener i;
    private RecyclerView.OnScrollListener j;
    private int k = 0;
    private int l = 0;
    private int m;
    private int n;
    private boolean o;
    private IFooterCallBack p;
    private XRefreshViewState q = XRefreshViewState.STATE_NORMAL;
    /* access modifiers changed from: private */
    public boolean r = false;
    private int s;
    private XRefreshHolder t;
    private XRefreshView u;
    /* access modifiers changed from: private */
    public boolean v = false;
    private boolean w = false;
    private boolean y = true;
    /* access modifiers changed from: private */
    public boolean z = false;

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    private void d(BaseRecyclerAdapter baseRecyclerAdapter, RecyclerView.LayoutManager layoutManager) {
    }

    public void a(XRefreshView xRefreshView) {
        this.u = xRefreshView;
    }

    public void a(boolean z2, boolean z3) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        if (z2) {
            layoutParams.height = -1;
        }
        if (z3) {
            layoutParams.height = -1;
        }
        this.b.setLayoutParams(layoutParams);
    }

    public void a(View view) {
        this.b = view;
        view.setOverScrollMode(2);
    }

    public View a() {
        return this.b;
    }

    public void a(XRefreshHolder xRefreshHolder) {
        this.t = xRefreshHolder;
    }

    public void b(XRefreshView xRefreshView) {
        this.f = xRefreshView;
    }

    public void b() {
        if (this.b instanceof AbsListView) {
            ((AbsListView) this.b).setSelection(0);
        } else if (this.b instanceof RecyclerView) {
            ((RecyclerView) this.b).getLayoutManager().scrollToPosition(0);
        }
    }

    public void a(boolean z2) {
        this.v = z2;
    }

    public void c() {
        if (this.b instanceof AbsListView) {
            ((AbsListView) this.b).setOnScrollListener(this);
        } else if (this.b instanceof ScrollView) {
            s();
        } else if (this.b instanceof RecyclerView) {
            t();
        }
    }

    private void s() {
        if (this.b instanceof XScrollView) {
            ((XScrollView) this.b).setOnScrollListener(this.u, new XScrollView.OnScrollListener() {
                public void a(int i, int i2, int i3, int i4) {
                }

                public void a(ScrollView scrollView, int i, boolean z) {
                    if (i == 0 && z) {
                        if (XRefreshContentView.this.v) {
                            if (XRefreshContentView.this.i != null) {
                                XRefreshContentView.this.i.b(true);
                            }
                        } else if (XRefreshContentView.this.f != null && !XRefreshContentView.this.i()) {
                            XRefreshContentView.this.f.invokeLoadMore();
                        }
                    }
                }
            });
            return;
        }
        throw new RuntimeException("please use XScrollView instead of ScrollView!");
    }

    public void a(RecyclerView recyclerView, BaseRecyclerAdapter baseRecyclerAdapter, int i2, int i3, boolean z2) {
        if (this.h != null) {
            this.h.onScrolled(recyclerView, i2, i3);
        }
        if (this.p != null || this.v) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            a(layoutManager);
            d(baseRecyclerAdapter, layoutManager);
            LogUtils.a("test pre onScrolled mIsLoadingMore=" + this.o);
            if (w()) {
                if (!Utils.a(recyclerView) && this.y) {
                    this.p.onStateReady();
                    this.p.callWhenNotAutoLoadMore(this.u);
                }
            } else if (i3 == 0 && !z2) {
            } else {
                if (this.v) {
                    a(baseRecyclerAdapter, layoutManager);
                    return;
                }
                if (!x()) {
                    this.y = true;
                }
                if (this.u != null && !this.u.getPullLoadEnable() && !this.w) {
                    h(false);
                    this.w = true;
                }
                if (!this.w) {
                    f();
                    if (this.f != null) {
                        b(baseRecyclerAdapter, layoutManager);
                    } else if (this.f == null) {
                        c(baseRecyclerAdapter, layoutManager);
                    }
                }
            }
        }
    }

    private void t() {
        this.f4761a = null;
        RecyclerView recyclerView = (RecyclerView) this.b;
        if (recyclerView.getAdapter() != null) {
            if (!(recyclerView.getAdapter() instanceof BaseRecyclerAdapter)) {
                LogUtils.e(x);
                return;
            }
            final BaseRecyclerAdapter baseRecyclerAdapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
            baseRecyclerAdapter.a(this.u.getPullLoadEnable());
            recyclerView.removeOnScrollListener(this.j);
            this.j = new RecyclerView.OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (XRefreshContentView.this.h != null) {
                        XRefreshContentView.this.h.onScrollStateChanged(recyclerView, i);
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    XRefreshContentView.this.a(recyclerView, baseRecyclerAdapter, i, i2, false);
                }
            };
            recyclerView.addOnScrollListener(this.j);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null && (layoutManager instanceof GridLayoutManager)) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new XSpanSizeLookup(baseRecyclerAdapter, gridLayoutManager.getSpanCount()));
            }
            a(baseRecyclerAdapter, this.u);
        }
    }

    public void a(BaseRecyclerAdapter baseRecyclerAdapter, XRefreshView xRefreshView) {
        View e2;
        if (!this.v && baseRecyclerAdapter != null && (e2 = baseRecyclerAdapter.e()) != null) {
            this.p = (IFooterCallBack) e2;
            if (this.p != null) {
                this.p.onStateReady();
                this.p.callWhenNotAutoLoadMore(xRefreshView);
                if (xRefreshView != null && !xRefreshView.getPullLoadEnable()) {
                    this.p.show(false);
                }
            }
        }
    }

    private void a(BaseRecyclerAdapter baseRecyclerAdapter, RecyclerView.LayoutManager layoutManager) {
        if (!this.o && x() && !i() && this.i != null) {
            this.o = true;
            this.i.b(true);
        }
    }

    private void b(BaseRecyclerAdapter baseRecyclerAdapter, RecyclerView.LayoutManager layoutManager) {
        if (this.o || !x() || !this.y) {
            a(XRefreshViewState.STATE_NORMAL);
        } else {
            a(false, baseRecyclerAdapter, layoutManager);
        }
    }

    /* access modifiers changed from: private */
    public boolean u() {
        return (this.q == XRefreshViewState.STATE_COMPLETE || this.u == null || !this.u.getPullLoadEnable()) ? false : true;
    }

    private void c(BaseRecyclerAdapter baseRecyclerAdapter, RecyclerView.LayoutManager layoutManager) {
        if (this.o || !x() || !this.y) {
            a(XRefreshViewState.STATE_NORMAL);
        } else if (!i()) {
            v();
        } else {
            g();
        }
    }

    public void a(boolean z2, BaseRecyclerAdapter baseRecyclerAdapter, RecyclerView.LayoutManager layoutManager) {
        if (u() && !this.o && this.p != null) {
            if (!i()) {
                if (this.i != null) {
                    this.i.b(z2);
                }
                this.o = true;
                this.l = this.c;
                this.p.onStateRefreshing();
                a(XRefreshViewState.STATE_LOADING);
                return;
            }
            g();
        }
    }

    public void d() {
        if (this.o) {
            return;
        }
        if (!i()) {
            if (this.i != null) {
                this.i.b(false);
            }
            this.o = true;
            this.l = this.c;
            this.p.onStateRefreshing();
            a(XRefreshViewState.STATE_LOADING);
            return;
        }
        g();
    }

    public void b(boolean z2) {
        if (this.p != null && !this.o) {
            if (z2) {
                if (this.q != XRefreshViewState.STATE_RELEASE_TO_LOADMORE && !this.z) {
                    this.p.onReleaseToLoadMore();
                    a(XRefreshViewState.STATE_RELEASE_TO_LOADMORE);
                }
            } else if (this.y) {
                v();
            } else if (this.q != XRefreshViewState.STATE_READY) {
                this.p.onStateFinish(false);
                a(XRefreshViewState.STATE_READY);
            }
        }
    }

    private void v() {
        if (this.q != XRefreshViewState.STATE_READY && !this.z) {
            this.p.onStateReady();
            a(XRefreshViewState.STATE_READY);
        }
    }

    public void e() {
        BaseRecyclerAdapter a2;
        if (r() && (a2 = a((RecyclerView) this.b)) != null) {
            a2.notifyDataSetChanged();
        }
    }

    private boolean w() {
        return j() && this.p != null && u();
    }

    public void c(boolean z2) {
        this.r = z2;
        if (!z2) {
            this.q = XRefreshViewState.STATE_NORMAL;
        }
        this.o = false;
        this.w = false;
        if (!z2 && this.C && this.u != null && this.u.getPullLoadEnable()) {
            h(true);
        }
        y();
        if (r()) {
            g(z2);
        }
    }

    private void g(boolean z2) {
        if (this.p != null && u()) {
            RecyclerView recyclerView = (RecyclerView) this.b;
            if (z2) {
                this.y = true;
                this.p.onStateFinish(true);
                if (!Utils.a(recyclerView)) {
                    this.b.postDelayed(new Runnable() {
                        public void run() {
                            XRefreshContentView.this.g();
                        }
                    }, 200);
                    return;
                }
                int i2 = this.c;
                a(recyclerView.getLayoutManager());
                BaseRecyclerAdapter a2 = a(recyclerView);
                if (a2 != null) {
                    a(recyclerView, a2, 0, 0, true);
                }
            } else if (recyclerView != null && this.p != null) {
                if (!Utils.a(recyclerView)) {
                    this.p.onStateReady();
                    this.p.callWhenNotAutoLoadMore(this.u);
                    if (!this.p.isShowing()) {
                        this.p.show(true);
                        return;
                    }
                    return;
                }
                v();
            }
        }
    }

    private BaseRecyclerAdapter a(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseRecyclerAdapter) {
            return (BaseRecyclerAdapter) adapter;
        }
        LogUtils.e(x);
        return null;
    }

    public void d(boolean z2) {
        this.o = false;
        if (this.p != null) {
            this.p.onStateFinish(z2);
            if (z2 && r()) {
                if (((BaseRecyclerAdapter) ((RecyclerView) this.b).getAdapter()) != null) {
                    h(false);
                    y();
                    h(true);
                } else {
                    return;
                }
            }
        }
        this.y = z2;
        this.q = XRefreshViewState.STATE_FINISHED;
    }

    private boolean x() {
        return (this.c - 1) - this.B <= this.n;
    }

    public void f() {
        if (u() && this.p != null && !this.p.isShowing()) {
            this.p.show(true);
        }
    }

    public void a(RecyclerView.LayoutManager layoutManager) {
        if (this.f4761a == null) {
            if (layoutManager instanceof GridLayoutManager) {
                this.f4761a = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof LinearLayoutManager) {
                this.f4761a = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.f4761a = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        this.c = layoutManager.getItemCount();
        switch (this.f4761a) {
            case LINEAR:
                this.k = layoutManager.getChildCount();
                this.n = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID:
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(iArr);
                this.n = a(iArr);
                staggeredGridLayoutManager.findFirstVisibleItemPositions(iArr);
                this.m = b(iArr);
                return;
            default:
                return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        this.n = linearLayoutManager.findLastVisibleItemPosition();
        this.m = linearLayoutManager.findFirstVisibleItemPosition();
    }

    public void a(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.B = i2;
    }

    /* access modifiers changed from: protected */
    public void e(boolean z2) {
        this.C = z2;
    }

    public void g() {
        this.u.enablePullUp(true);
        if (this.q != XRefreshViewState.STATE_COMPLETE) {
            this.p.onStateComplete();
            a(XRefreshViewState.STATE_COMPLETE);
            int i2 = 1000;
            if (this.s >= 1000) {
                i2 = this.s;
            }
            this.s = i2;
            if (this.C) {
                this.b.postDelayed(new Runnable() {
                    public void run() {
                        XRefreshContentView.this.y();
                        if (XRefreshContentView.this.r) {
                            XRefreshContentView.this.h(false);
                        }
                    }
                }, (long) this.s);
            }
        }
    }

    /* access modifiers changed from: private */
    public void y() {
        if (this.u != null) {
            this.u.resetLayout();
        }
    }

    private void a(XRefreshViewState xRefreshViewState) {
        if (this.q != XRefreshViewState.STATE_COMPLETE) {
            this.q = xRefreshViewState;
        }
    }

    public XRefreshViewState h() {
        return this.q;
    }

    public boolean i() {
        return this.r;
    }

    /* access modifiers changed from: private */
    public void h(boolean z2) {
        if (this.b instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) this.b;
            final BaseRecyclerAdapter a2 = a(recyclerView);
            if (a2 != null && this.p != null) {
                if (z2) {
                    this.z = true;
                    recyclerView.post(new Runnable() {
                        public void run() {
                            if (recyclerView.indexOfChild(a2.e()) == -1) {
                                boolean unused = XRefreshContentView.this.z = false;
                                if (XRefreshContentView.this.u()) {
                                    a2.a();
                                    return;
                                }
                                return;
                            }
                            recyclerView.post(this);
                        }
                    });
                    return;
                }
                a2.c();
            }
        } else if (this.p != null) {
            this.p.show(z2);
        }
    }

    public void f(boolean z2) {
        BaseRecyclerAdapter a2;
        h(z2);
        this.w = false;
        this.o = false;
        if (z2) {
            z();
        }
        if (r() && (a2 = a((RecyclerView) this.b)) != null) {
            a2.a(z2);
        }
    }

    private void z() {
        RecyclerView recyclerView = (RecyclerView) this.b;
        if (w() && !Utils.a(recyclerView) && (this.b instanceof RecyclerView) && this.p != null && u()) {
            this.p.onStateReady();
            this.p.callWhenNotAutoLoadMore(this.u);
            if (!this.p.isShowing()) {
                this.p.show(true);
            }
        }
    }

    public void b(int i2) {
        this.s = i2;
    }

    public void a(AbsListView.OnScrollListener onScrollListener) {
        this.g = onScrollListener;
    }

    public void a(RecyclerView.OnScrollListener onScrollListener) {
        this.h = onScrollListener;
    }

    public void a(XRefreshView.XRefreshViewListener xRefreshViewListener) {
        this.i = xRefreshViewListener;
    }

    public boolean j() {
        if (this.d != null) {
            return this.d.j();
        }
        return m();
    }

    public boolean k() {
        if (this.e != null) {
            return this.e.k();
        }
        return n();
    }

    public void a(OnTopRefreshTime onTopRefreshTime) {
        this.d = onTopRefreshTime;
    }

    public void a(OnBottomLoadMoreTime onBottomLoadMoreTime) {
        this.e = onBottomLoadMoreTime;
    }

    public void onScrollStateChanged(AbsListView absListView, int i2) {
        if (this.u.isStopLoadMore() && i2 == 2) {
            this.D = true;
        }
        if (!this.D) {
            if (this.v) {
                if (this.i != null && !i() && !this.o && this.c - 1 <= absListView.getLastVisiblePosition() + this.B) {
                    this.i.b(true);
                    this.o = true;
                }
            } else if (this.f != null && !i() && i2 == 0) {
                if (this.B == 0) {
                    if (k() && !this.o) {
                        this.o = this.f.invokeLoadMore();
                    }
                } else if (this.c - 1 <= absListView.getLastVisiblePosition() + this.B && !this.o) {
                    this.o = this.f.invokeLoadMore();
                }
            }
            if (this.g != null) {
                this.g.onScrollStateChanged(absListView, i2);
            }
        } else if (!this.u.isStopLoadMore() && i2 == 0) {
            this.D = false;
        }
    }

    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        this.c = i4;
        if (this.g != null) {
            this.g.onScroll(absListView, i2, i3, i4);
        }
    }

    public int l() {
        return this.c;
    }

    public boolean m() {
        return !p();
    }

    public boolean n() {
        return !q();
    }

    public boolean o() {
        if (this.v) {
            return false;
        }
        return this.o;
    }

    public boolean p() {
        if (this.b instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.b;
            if (a(this.b, -1)) {
                return true;
            }
            if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                return false;
            }
            return true;
        } else if (a(this.b, -1) || this.b.getScrollY() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean q() {
        if (this.b instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.b;
            if (a(this.b, 1) || absListView.getLastVisiblePosition() != this.c - 1) {
                return true;
            }
            return false;
        } else if (this.b instanceof WebView) {
            WebView webView = (WebView) this.b;
            if (a(this.b, 1) || ((float) webView.getContentHeight()) * webView.getScale() != ((float) (webView.getHeight() + webView.getScrollY()))) {
                return true;
            }
            return false;
        } else if (!(this.b instanceof ScrollView)) {
            return a(this.b, 1);
        } else {
            ScrollView scrollView = (ScrollView) this.b;
            View childAt = scrollView.getChildAt(0);
            if (childAt == null || a(this.b, 1) || scrollView.getScrollY() < childAt.getHeight() - scrollView.getHeight()) {
                return true;
            }
            return false;
        }
    }

    public boolean a(View view, int i2) {
        return ViewCompat.canScrollVertically(view, i2);
    }

    public void c(int i2) {
        this.b.offsetTopAndBottom(i2);
    }

    public boolean r() {
        if (this.v || this.b == null || !(this.b instanceof RecyclerView)) {
            return false;
        }
        RecyclerView recyclerView = (RecyclerView) this.b;
        if (recyclerView.getAdapter() == null || (recyclerView.getAdapter() instanceof BaseRecyclerAdapter)) {
            return true;
        }
        return false;
    }

    private int a(int[] iArr) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    private int b(int[] iArr) {
        int i2 = Integer.MAX_VALUE;
        for (int i3 : iArr) {
            if (i3 != -1 && i3 < i2) {
                i2 = i3;
            }
        }
        return i2;
    }
}
