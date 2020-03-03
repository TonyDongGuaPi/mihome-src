package com.xiaomi.dragdrop.animator;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

public abstract class BaseItemAnimator extends SimpleItemAnimator {

    /* renamed from: a  reason: collision with root package name */
    private ItemAnimatorListener f10110a;

    public interface ItemAnimatorListener {
        void a(RecyclerView.ViewHolder viewHolder);

        void b(RecyclerView.ViewHolder viewHolder);

        void c(RecyclerView.ViewHolder viewHolder);

        void d(RecyclerView.ViewHolder viewHolder);
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void b(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void b(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void c(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void d(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void e(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void f(RecyclerView.ViewHolder viewHolder) {
    }

    public void a(ItemAnimatorListener itemAnimatorListener) {
        this.f10110a = itemAnimatorListener;
    }

    public final void onAddStarting(RecyclerView.ViewHolder viewHolder) {
        a(viewHolder);
    }

    public final void onAddFinished(RecyclerView.ViewHolder viewHolder) {
        b(viewHolder);
        if (this.f10110a != null) {
            this.f10110a.b(viewHolder);
        }
    }

    public final void onChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z) {
        a(viewHolder, z);
    }

    public final void onChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z) {
        b(viewHolder, z);
        if (this.f10110a != null) {
            this.f10110a.d(viewHolder);
        }
    }

    public final void onMoveStarting(RecyclerView.ViewHolder viewHolder) {
        c(viewHolder);
    }

    public final void onMoveFinished(RecyclerView.ViewHolder viewHolder) {
        d(viewHolder);
        if (this.f10110a != null) {
            this.f10110a.c(viewHolder);
        }
    }

    public final void onRemoveStarting(RecyclerView.ViewHolder viewHolder) {
        e(viewHolder);
    }

    public final void onRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        f(viewHolder);
        if (this.f10110a != null) {
            this.f10110a.a(viewHolder);
        }
    }

    public boolean a() {
        if (isRunning()) {
            return false;
        }
        dispatchAnimationsFinished();
        return true;
    }
}
