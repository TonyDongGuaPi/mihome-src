package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.dragdrop.animator.BaseItemAnimator;

public abstract class ItemAddAnimationManager extends BaseItemAnimationManager<AddAnimationInfo> {
    private static final String e = "ARVItemAddAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder);

    public ItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f10118a.getAddDuration();
    }

    public void a(long j) {
        this.f10118a.setAddDuration(j);
    }

    public void d(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchAddStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchAddStarting(viewHolder);
    }

    public void e(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchAddFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchAddFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (addAnimationInfo.f10117a == null) {
            return false;
        }
        if (viewHolder != null && addAnimationInfo.f10117a != viewHolder) {
            return false;
        }
        b(addAnimationInfo, addAnimationInfo.f10117a);
        e(addAnimationInfo, addAnimationInfo.f10117a);
        addAnimationInfo.a(addAnimationInfo.f10117a);
        return true;
    }
}
