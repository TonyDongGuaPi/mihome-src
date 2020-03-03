package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
import com.taobao.weex.el.parse.Operators;

public abstract class ItemChangeAnimationManager extends BaseItemAnimationManager<ChangeAnimationInfo> {
    private static final String e = "ARVItemChangeAnimMgr";

    /* access modifiers changed from: protected */
    public abstract void a(ChangeAnimationInfo changeAnimationInfo);

    public abstract boolean a(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    /* access modifiers changed from: protected */
    public abstract void b(ChangeAnimationInfo changeAnimationInfo);

    public ItemChangeAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public void d(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchChangeStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchChangeStarting(viewHolder, viewHolder == changeAnimationInfo.b);
    }

    public void e(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchChangeFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchChangeFinished(viewHolder, viewHolder == changeAnimationInfo.b);
    }

    public long e() {
        return this.f5690a.getChangeDuration();
    }

    public void a(long j) {
        this.f5690a.setChangeDuration(j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void a(ChangeAnimationInfo changeAnimationInfo) {
        if (!(changeAnimationInfo.b == null || changeAnimationInfo.b.itemView == null)) {
            a(changeAnimationInfo);
        }
        if (changeAnimationInfo.f5693a != null && changeAnimationInfo.f5693a.itemView != null) {
            b(changeAnimationInfo);
        }
    }

    /* access modifiers changed from: protected */
    public boolean f(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeAnimationInfo.b != null && (viewHolder == null || changeAnimationInfo.b == viewHolder)) {
            b(changeAnimationInfo, changeAnimationInfo.b);
            e(changeAnimationInfo, changeAnimationInfo.b);
            changeAnimationInfo.a(changeAnimationInfo.b);
        }
        if (changeAnimationInfo.f5693a != null && (viewHolder == null || changeAnimationInfo.f5693a == viewHolder)) {
            b(changeAnimationInfo, changeAnimationInfo.f5693a);
            e(changeAnimationInfo, changeAnimationInfo.f5693a);
            changeAnimationInfo.a(changeAnimationInfo.f5693a);
        }
        return changeAnimationInfo.b == null && changeAnimationInfo.f5693a == null;
    }
}
