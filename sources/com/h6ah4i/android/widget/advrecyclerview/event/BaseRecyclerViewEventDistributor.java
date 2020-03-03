package com.h6ah4i.android.widget.advrecyclerview.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewEventDistributor<T> {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f5711a;
    protected RecyclerView b;
    protected List<T> c;
    protected boolean d;

    public RecyclerView a() {
        return this.b;
    }

    public void b() {
        if (!this.f5711a) {
            this.f5711a = true;
            a(true);
            f();
        }
    }

    public boolean c() {
        return this.f5711a;
    }

    public void a(RecyclerView recyclerView) {
        if (recyclerView != null) {
            b("attachRecyclerView()");
            a("attachRecyclerView()");
            b(recyclerView);
            return;
        }
        throw new IllegalArgumentException("RecyclerView cannot be null");
    }

    public boolean a(T t) {
        return a(t, -1);
    }

    public boolean a(@NonNull T t, int i) {
        b("add()");
        a("add()");
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.c.contains(t)) {
            return true;
        }
        if (i < 0) {
            this.c.add(t);
        } else {
            this.c.add(i, t);
        }
        if (!(t instanceof RecyclerViewEventDistributorListener)) {
            return true;
        }
        ((RecyclerViewEventDistributorListener) t).a(this);
        return true;
    }

    public boolean b(@NonNull T t) {
        a("remove()");
        b("remove()");
        if (this.c == null) {
            return false;
        }
        boolean remove = this.c.remove(t);
        if (remove && (t instanceof RecyclerViewEventDistributorListener)) {
            ((RecyclerViewEventDistributorListener) t).b(this);
        }
        return remove;
    }

    public void d() {
        a(false);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void a(boolean z) {
        if (!z) {
            b("clear()");
        }
        a("clear()");
        if (this.c != null) {
            try {
                this.d = true;
                for (int size = this.c.size() - 1; size >= 0; size--) {
                    T remove = this.c.remove(size);
                    if (remove instanceof RecyclerViewEventDistributorListener) {
                        ((RecyclerViewEventDistributorListener) remove).b(this);
                    }
                }
                this.d = false;
            } catch (Throwable th) {
                this.d = false;
                throw th;
            }
        }
    }

    public int e() {
        if (this.c != null) {
            return this.c.size();
        }
        return 0;
    }

    public boolean c(T t) {
        if (this.c != null) {
            return this.c.contains(t);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void f() {
        this.b = null;
        this.c = null;
        this.d = false;
    }

    /* access modifiers changed from: protected */
    public void b(RecyclerView recyclerView) {
        this.b = recyclerView;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        if (this.d) {
            throw new IllegalStateException(str + " can not be called while performing the clear() method");
        }
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        if (this.f5711a) {
            throw new IllegalStateException(str + " can not be called after release() method called");
        }
    }
}
