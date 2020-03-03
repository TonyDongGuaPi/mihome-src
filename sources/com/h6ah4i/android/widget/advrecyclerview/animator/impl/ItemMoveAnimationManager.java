package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
import com.taobao.weex.el.parse.Operators;

public abstract class ItemMoveAnimationManager extends BaseItemAnimationManager<MoveAnimationInfo> {
    public static final String e = "ARVItemMoveAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);

    public ItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f5690a.getMoveDuration();
    }

    public void a(long j) {
        this.f5690a.setMoveDuration(j);
    }

    public void d(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d("ARVItemMoveAnimMgr", "dispatchMoveStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchMoveStarting(viewHolder);
    }

    public void e(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d("ARVItemMoveAnimMgr", "dispatchMoveFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f5690a.dispatchMoveFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (moveAnimationInfo.f5694a == null) {
            return false;
        }
        if (viewHolder != null && moveAnimationInfo.f5694a != viewHolder) {
            return false;
        }
        b(moveAnimationInfo, moveAnimationInfo.f5694a);
        e(moveAnimationInfo, moveAnimationInfo.f5694a);
        moveAnimationInfo.a(moveAnimationInfo.f5694a);
        return true;
    }
}
