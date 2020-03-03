package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.dragdrop.animator.BaseItemAnimator;

public abstract class ItemRemoveAnimationManager extends BaseItemAnimationManager<RemoveAnimationInfo> {
    private static final String e = "ARVItemRemoveAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder);

    public ItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f10118a.getRemoveDuration();
    }

    public void a(long j) {
        this.f10118a.setRemoveDuration(j);
    }

    public void d(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchRemoveStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchRemoveStarting(viewHolder);
    }

    public void e(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchRemoveFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchRemoveFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (removeAnimationInfo.f10123a == null) {
            return false;
        }
        if (viewHolder != null && removeAnimationInfo.f10123a != viewHolder) {
            return false;
        }
        b(removeAnimationInfo, removeAnimationInfo.f10123a);
        e(removeAnimationInfo, removeAnimationInfo.f10123a);
        removeAnimationInfo.a(removeAnimationInfo.f10123a);
        return true;
    }
}
