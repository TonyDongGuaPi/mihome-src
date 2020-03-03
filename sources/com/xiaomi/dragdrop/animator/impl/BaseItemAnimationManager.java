package com.xiaomi.dragdrop.animator.impl;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.dragdrop.animator.BaseItemAnimator;
import com.xiaomi.dragdrop.animator.impl.ItemAnimationInfo;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseItemAnimationManager<T extends ItemAnimationInfo> {

    /* renamed from: a  reason: collision with root package name */
    protected final BaseItemAnimator f10118a;
    protected final List<T> b = new ArrayList();
    protected final List<List<T>> c = new ArrayList();
    protected final List<RecyclerView.ViewHolder> d = new ArrayList();

    public abstract void a(long j);

    /* access modifiers changed from: protected */
    public abstract void a(T t);

    /* access modifiers changed from: protected */
    public abstract void a(T t, RecyclerView.ViewHolder viewHolder);

    /* access modifiers changed from: protected */
    public abstract void b(T t, RecyclerView.ViewHolder viewHolder);

    /* access modifiers changed from: protected */
    public abstract void c(T t, RecyclerView.ViewHolder viewHolder);

    public abstract void d(T t, RecyclerView.ViewHolder viewHolder);

    public abstract long e();

    public abstract void e(T t, RecyclerView.ViewHolder viewHolder);

    /* access modifiers changed from: protected */
    public abstract boolean f(T t, RecyclerView.ViewHolder viewHolder);

    public BaseItemAnimationManager(BaseItemAnimator baseItemAnimator) {
        this.f10118a = baseItemAnimator;
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return this.f10118a.b();
    }

    public boolean b() {
        return !this.b.isEmpty();
    }

    public boolean c() {
        return !this.b.isEmpty() || !this.d.isEmpty() || !this.c.isEmpty();
    }

    public boolean b(RecyclerView.ViewHolder viewHolder) {
        return this.d.remove(viewHolder);
    }

    public void d() {
        List<RecyclerView.ViewHolder> list = this.d;
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(list.get(size).itemView).cancel();
        }
    }

    public void a(boolean z, long j) {
        final ArrayList<ItemAnimationInfo> arrayList = new ArrayList<>();
        arrayList.addAll(this.b);
        this.b.clear();
        if (z) {
            this.c.add(arrayList);
            ViewCompat.postOnAnimationDelayed(((ItemAnimationInfo) arrayList.get(0)).a().itemView, new Runnable() {
                public void run() {
                    for (ItemAnimationInfo b2 : arrayList) {
                        BaseItemAnimationManager.this.b(b2);
                    }
                    arrayList.clear();
                    BaseItemAnimationManager.this.c.remove(arrayList);
                }
            }, j);
            return;
        }
        for (ItemAnimationInfo b2 : arrayList) {
            b(b2);
        }
        arrayList.clear();
    }

    public void c(RecyclerView.ViewHolder viewHolder) {
        List<T> list = this.b;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (f((ItemAnimationInfo) list.get(size), viewHolder) && viewHolder != null) {
                list.remove(size);
            }
        }
        if (viewHolder == null) {
            list.clear();
        }
    }

    public void f() {
        c((RecyclerView.ViewHolder) null);
    }

    public void d(RecyclerView.ViewHolder viewHolder) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            List list = this.c.get(size);
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                if (f((ItemAnimationInfo) list.get(size2), viewHolder) && viewHolder != null) {
                    list.remove(size2);
                }
            }
            if (viewHolder == null) {
                list.clear();
            }
            if (list.isEmpty()) {
                this.c.remove(list);
            }
        }
    }

    public void g() {
        d((RecyclerView.ViewHolder) null);
    }

    /* access modifiers changed from: package-private */
    public void b(T t) {
        a(t);
    }

    /* access modifiers changed from: protected */
    public void e(RecyclerView.ViewHolder viewHolder) {
        this.f10118a.endAnimation(viewHolder);
    }

    /* access modifiers changed from: protected */
    public void h() {
        this.f10118a.a();
    }

    /* access modifiers changed from: protected */
    public void c(T t) {
        if (t != null) {
            this.b.add(t);
            return;
        }
        throw new IllegalStateException("info is null");
    }

    /* access modifiers changed from: protected */
    public void a(T t, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        viewPropertyAnimatorCompat.setListener(new BaseAnimatorListener(this, t, viewHolder, viewPropertyAnimatorCompat));
        a(viewHolder);
        viewPropertyAnimatorCompat.start();
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            this.d.add(viewHolder);
            return;
        }
        throw new IllegalStateException("item is null");
    }

    protected static class BaseAnimatorListener implements ViewPropertyAnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        private BaseItemAnimationManager f10120a;
        private ItemAnimationInfo b;
        private RecyclerView.ViewHolder c;
        private ViewPropertyAnimatorCompat d;

        public BaseAnimatorListener(BaseItemAnimationManager baseItemAnimationManager, ItemAnimationInfo itemAnimationInfo, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f10120a = baseItemAnimationManager;
            this.b = itemAnimationInfo;
            this.c = viewHolder;
            this.d = viewPropertyAnimatorCompat;
        }

        public void onAnimationStart(View view) {
            this.f10120a.d(this.b, this.c);
        }

        public void onAnimationEnd(View view) {
            BaseItemAnimationManager baseItemAnimationManager = this.f10120a;
            ItemAnimationInfo itemAnimationInfo = this.b;
            RecyclerView.ViewHolder viewHolder = this.c;
            this.d.setListener((ViewPropertyAnimatorListener) null);
            this.f10120a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            baseItemAnimationManager.c(itemAnimationInfo, viewHolder);
            baseItemAnimationManager.e(itemAnimationInfo, viewHolder);
            itemAnimationInfo.a(viewHolder);
            baseItemAnimationManager.d.remove(viewHolder);
            baseItemAnimationManager.h();
        }

        public void onAnimationCancel(View view) {
            this.f10120a.a(this.b, this.c);
        }
    }
}
