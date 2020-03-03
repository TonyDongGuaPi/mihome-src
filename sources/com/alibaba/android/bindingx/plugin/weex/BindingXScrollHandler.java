package com.alibaba.android.bindingx.plugin.weex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.alibaba.android.bindingx.core.internal.AbstractScrollEventHandler;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.alibaba.android.bindingx.core.internal.ExpressionPair;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.view.WXHorizontalScrollView;
import com.taobao.weex.ui.view.WXScrollView;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BindingXScrollHandler extends AbstractScrollEventHandler {
    /* access modifiers changed from: private */
    public static HashMap<String, ContentOffsetHolder> r = new HashMap<>();
    private RecyclerView.OnScrollListener m;
    private WXSwipeLayout.OnRefreshOffsetChangedListener n;
    private WXScrollView.WXScrollViewListener o;
    private WXHorizontalScrollView.ScrollViewListener p;
    private AppBarLayout.OnOffsetChangedListener q;
    /* access modifiers changed from: private */
    public String s;

    /* access modifiers changed from: private */
    public boolean a(int i, int i2) {
        return (i > 0 && i2 > 0) || (i < 0 && i2 < 0);
    }

    public void b() {
    }

    public void b(@NonNull String str, @NonNull String str2) {
    }

    public void c() {
    }

    public BindingXScrollHandler(Context context, PlatformManager platformManager, Object... objArr) {
        super(context, platformManager, objArr);
    }

    public boolean a(@NonNull String str, @NonNull String str2) {
        WXSwipeLayout swipeLayout;
        WXComponent b = WXModuleUtils.b(TextUtils.isEmpty(this.f) ? this.e : this.f, str);
        if (b == null) {
            LogProxy.e("[ExpressionScrollHandler]source component not found.");
            return false;
        }
        this.s = str;
        if (b instanceof WXScroller) {
            WXScroller wXScroller = (WXScroller) b;
            ViewGroup viewGroup = (ViewGroup) wXScroller.getHostView();
            if (!(viewGroup == null || !(viewGroup instanceof BounceScrollerView) || (swipeLayout = ((BounceScrollerView) viewGroup).getSwipeLayout()) == null)) {
                this.n = new InnerSwipeOffsetListener();
                swipeLayout.addOnRefreshOffsetChangedListener(this.n);
            }
            ViewGroup innerView = wXScroller.getInnerView();
            if (innerView != null && (innerView instanceof WXScrollView)) {
                this.o = new InnerScrollViewListener();
                ((WXScrollView) innerView).addScrollViewListener(this.o);
                return true;
            } else if (innerView != null && (innerView instanceof WXHorizontalScrollView)) {
                this.p = new InnerScrollViewListener();
                ((WXHorizontalScrollView) innerView).addScrollViewListener(this.p);
                return true;
            }
        } else if (b instanceof WXListComponent) {
            WXListComponent wXListComponent = (WXListComponent) b;
            BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) wXListComponent.getHostView();
            if (bounceRecyclerView != null) {
                WXSwipeLayout swipeLayout2 = bounceRecyclerView.getSwipeLayout();
                if (swipeLayout2 != null) {
                    this.n = new InnerSwipeOffsetListener();
                    swipeLayout2.addOnRefreshOffsetChangedListener(this.n);
                }
                WXRecyclerView wXRecyclerView = (WXRecyclerView) bounceRecyclerView.getInnerView();
                boolean z = wXListComponent.getOrientation() == 1;
                if (wXRecyclerView != null) {
                    if (r != null && r.get(str) == null) {
                        r.put(str, new ContentOffsetHolder(0, 0));
                    }
                    this.m = new InnerListScrollListener(z, new WeakReference(wXListComponent));
                    wXRecyclerView.addOnScrollListener(this.m);
                    return true;
                }
            }
        } else if (b.getHostView() != null && (b.getHostView() instanceof AppBarLayout)) {
            this.q = new InnerAppBarOffsetChangedListener();
            ((AppBarLayout) b.getHostView()).addOnOffsetChangedListener(this.q);
            return true;
        }
        return false;
    }

    public void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback) {
        super.a(str, map, expressionPair, list, javaScriptCallback);
    }

    public boolean c(@NonNull String str, @NonNull String str2) {
        BounceRecyclerView bounceRecyclerView;
        WXSwipeLayout swipeLayout;
        ContentOffsetHolder contentOffsetHolder;
        super.c(str, str2);
        if (!(r == null || TextUtils.isEmpty(this.s) || (contentOffsetHolder = r.get(this.s)) == null)) {
            contentOffsetHolder.f767a = this.k;
            contentOffsetHolder.b = this.l;
        }
        WXComponent b = WXModuleUtils.b(TextUtils.isEmpty(this.f) ? this.e : this.f, str);
        if (b == null) {
            LogProxy.e("[ExpressionScrollHandler]source component not found.");
            return false;
        }
        if (b instanceof WXScroller) {
            WXScroller wXScroller = (WXScroller) b;
            ViewGroup viewGroup = (ViewGroup) wXScroller.getHostView();
            if (!(viewGroup == null || !(viewGroup instanceof BounceScrollerView) || (swipeLayout = ((BounceScrollerView) viewGroup).getSwipeLayout()) == null || this.n == null)) {
                swipeLayout.removeOnRefreshOffsetChangedListener(this.n);
            }
            ViewGroup innerView = wXScroller.getInnerView();
            if (innerView != null && (innerView instanceof WXScrollView) && this.o != null) {
                ((WXScrollView) innerView).removeScrollViewListener(this.o);
                return true;
            } else if (!(innerView == null || !(innerView instanceof WXHorizontalScrollView) || this.p == null)) {
                ((WXHorizontalScrollView) innerView).removeScrollViewListener(this.p);
                return true;
            }
        } else if ((b instanceof WXListComponent) && (bounceRecyclerView = (BounceRecyclerView) ((WXListComponent) b).getHostView()) != null) {
            if (!(bounceRecyclerView.getSwipeLayout() == null || this.n == null)) {
                bounceRecyclerView.getSwipeLayout().removeOnRefreshOffsetChangedListener(this.n);
            }
            WXRecyclerView wXRecyclerView = (WXRecyclerView) bounceRecyclerView.getInnerView();
            if (!(wXRecyclerView == null || this.m == null)) {
                wXRecyclerView.removeOnScrollListener(this.m);
                return true;
            }
        }
        return false;
    }

    public void a() {
        super.a();
        this.m = null;
        this.o = null;
        this.q = null;
        if (r != null) {
            r.clear();
        }
    }

    private class InnerAppBarOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        /* access modifiers changed from: private */
        public int b;
        private int c;
        private int d;

        private InnerAppBarOffsetChangedListener() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
        }

        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            boolean z;
            int i2 = -i;
            final int i3 = i2 - this.b;
            this.b = i2;
            if (i3 != 0) {
                if (!BindingXScrollHandler.this.a(i3, this.d)) {
                    this.c = this.b;
                    z = true;
                } else {
                    z = false;
                }
                final int i4 = this.b - this.c;
                this.d = i3;
                if (z) {
                    BindingXScrollHandler.super.a(BindingXConstants.g, 0.0d, (double) this.b, 0.0d, (double) i3, 0.0d, (double) i4, new Object[0]);
                }
                WXBridgeManager.getInstance().post(new Runnable() {
                    public void run() {
                        InnerAppBarOffsetChangedListener.super.a(0, InnerAppBarOffsetChangedListener.this.b, 0, i3, 0, i4);
                    }
                }, BindingXScrollHandler.this.e);
            }
        }
    }

    private class InnerScrollViewListener implements WXHorizontalScrollView.ScrollViewListener, WXScrollView.WXScrollViewListener {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        private int d;
        private int e;
        private int f;
        private int g;

        public void onScrollChanged(WXScrollView wXScrollView, int i, int i2, int i3, int i4) {
        }

        public void onScrollStopped(WXScrollView wXScrollView, int i, int i2) {
        }

        public void onScrollToBottom(WXScrollView wXScrollView, int i, int i2) {
        }

        private InnerScrollViewListener() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
        }

        public void onScroll(WXScrollView wXScrollView, int i, int i2) {
            a(i, i2);
        }

        public void onScrollChanged(WXHorizontalScrollView wXHorizontalScrollView, int i, int i2, int i3, int i4) {
            a(i, i2);
        }

        private void a(int i, int i2) {
            boolean z;
            int i3;
            int i4;
            int i5 = i;
            int i6 = i2;
            int i7 = i5 - this.b;
            int i8 = i6 - this.c;
            this.b = i5;
            this.c = i6;
            if (i7 != 0 || i8 != 0) {
                if (!BindingXScrollHandler.this.a(i8, this.g)) {
                    this.e = this.c;
                    z = true;
                } else {
                    z = false;
                }
                final int i9 = this.b - this.d;
                final int i10 = this.c - this.e;
                this.f = i7;
                this.g = i8;
                if (z) {
                    i4 = i7;
                    i3 = i8;
                    BindingXScrollHandler.super.a(BindingXConstants.g, (double) this.b, (double) this.c, (double) i7, (double) i8, (double) i9, (double) i10, new Object[0]);
                } else {
                    i4 = i7;
                    i3 = i8;
                }
                final int i11 = i4;
                final int i12 = i3;
                WXBridgeManager.getInstance().post(new Runnable() {
                    public void run() {
                        InnerScrollViewListener.super.a(InnerScrollViewListener.this.b, InnerScrollViewListener.this.c, i11, i12, i9, i10);
                    }
                }, BindingXScrollHandler.this.e);
            }
        }
    }

    private class InnerSwipeOffsetListener implements WXSwipeLayout.OnRefreshOffsetChangedListener {
        /* access modifiers changed from: private */
        public int b;
        private int c;
        private int d;

        private InnerSwipeOffsetListener() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
        }

        public void onOffsetChanged(int i) {
            boolean z;
            int i2 = -i;
            final int i3 = i2 - this.b;
            this.b = i2;
            if (i3 != 0) {
                if (!BindingXScrollHandler.this.a(i3, this.d)) {
                    this.c = this.b;
                    z = true;
                } else {
                    z = false;
                }
                final int i4 = this.b - this.c;
                this.d = i3;
                if (z) {
                    BindingXScrollHandler.super.a(BindingXConstants.g, (double) BindingXScrollHandler.this.k, (double) this.b, 0.0d, (double) i3, 0.0d, (double) i4, new Object[0]);
                }
                WXBridgeManager.getInstance().post(new Runnable() {
                    public void run() {
                        InnerSwipeOffsetListener.super.a(BindingXScrollHandler.this.k, InnerSwipeOffsetListener.this.b, 0, i3, 0, i4);
                    }
                }, BindingXScrollHandler.this.e);
            }
        }
    }

    private class InnerListScrollListener extends RecyclerView.OnScrollListener {
        /* access modifiers changed from: private */
        public int b = 0;
        /* access modifiers changed from: private */
        public int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private int g = 0;
        private boolean h;
        private WeakReference<WXListComponent> i;

        InnerListScrollListener(boolean z, WeakReference<WXListComponent> weakReference) {
            ContentOffsetHolder contentOffsetHolder;
            this.h = z;
            this.i = weakReference;
            if (!TextUtils.isEmpty(BindingXScrollHandler.this.s) && BindingXScrollHandler.r != null && (contentOffsetHolder = (ContentOffsetHolder) BindingXScrollHandler.r.get(BindingXScrollHandler.this.s)) != null) {
                this.b = contentOffsetHolder.f767a;
                this.c = contentOffsetHolder.b;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            boolean z;
            int i4;
            int i5 = i2;
            int i6 = i3;
            if (!ViewCompat.isInLayout(recyclerView) || this.i == null || this.i.get() == null) {
                this.c += i6;
            } else {
                this.c = Math.abs(((WXListComponent) this.i.get()).calcContentOffset(recyclerView));
            }
            this.b += i5;
            if (BindingXScrollHandler.this.a(i5, this.f) || this.h) {
                z = false;
            } else {
                this.d = this.b;
                z = true;
            }
            if (!BindingXScrollHandler.this.a(i6, this.g) && this.h) {
                this.e = this.c;
                z = true;
            }
            int i7 = this.b - this.d;
            int i8 = this.c - this.e;
            this.f = i5;
            this.g = i6;
            if (z) {
                i4 = i7;
                BindingXScrollHandler.this.a(BindingXConstants.g, (double) this.b, (double) this.c, (double) i5, (double) i6, (double) i7, (double) i8, new Object[0]);
            } else {
                i4 = i7;
            }
            final int i9 = i2;
            final int i10 = i3;
            final int i11 = i4;
            final int i12 = i8;
            WXBridgeManager.getInstance().post(new Runnable() {
                public void run() {
                    InnerListScrollListener.super.a(InnerListScrollListener.this.b, InnerListScrollListener.this.c, i9, i10, i11, i12);
                }
            }, BindingXScrollHandler.this.e);
        }
    }

    private static class ContentOffsetHolder {

        /* renamed from: a  reason: collision with root package name */
        int f767a;
        int b;

        ContentOffsetHolder(int i, int i2) {
            this.f767a = i;
            this.b = i2;
        }
    }
}
