package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
import com.taobao.weex.el.parse.Operators;

public abstract class ItemAddAnimationManager extends BaseItemAnimationManager<AddAnimationInfo> {
    private static final String e = "ARVItemAddAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder);

    public ItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f5690a.getAddDuration();
    }

    public void a(long j) {
        this.f5690a.setAddDuration(j);
    }

    public void d(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchAddStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchAddStarting(viewHolder);
    }

    public void e(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchAddFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchAddFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (addAnimationInfo.f5689a == null) {
            return false;
        }
        if (viewHolder != null && addAnimationInfo.f5689a != viewHolder) {
            return false;
        }
        b(addAnimationInfo, addAnimationInfo.f5689a);
        e(addAnimationInfo, addAnimationInfo.f5689a);
        addAnimationInfo.a(addAnimationInfo.f5689a);
        return true;
    }
}
