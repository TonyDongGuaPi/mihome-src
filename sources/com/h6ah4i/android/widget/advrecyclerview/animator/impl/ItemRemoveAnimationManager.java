package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
import com.taobao.weex.el.parse.Operators;

public abstract class ItemRemoveAnimationManager extends BaseItemAnimationManager<RemoveAnimationInfo> {
    private static final String e = "ARVItemRemoveAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder);

    public ItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f5690a.getRemoveDuration();
    }

    public void a(long j) {
        this.f5690a.setRemoveDuration(j);
    }

    public void d(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchRemoveStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchRemoveStarting(viewHolder);
    }

    public void e(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchRemoveFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchRemoveFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (removeAnimationInfo.f5695a == null) {
            return false;
        }
        if (viewHolder != null && removeAnimationInfo.f5695a != viewHolder) {
            return false;
        }
        b(removeAnimationInfo, removeAnimationInfo.f5695a);
        e(removeAnimationInfo, removeAnimationInfo.f5695a);
        removeAnimationInfo.a(removeAnimationInfo.f5695a);
        return true;
    }
}
