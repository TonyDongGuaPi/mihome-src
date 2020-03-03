package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.dragdrop.animator.BaseItemAnimator;

public abstract class ItemMoveAnimationManager extends BaseItemAnimationManager<MoveAnimationInfo> {
    public static final String e = "ARVItemMoveAnimMgr";

    public abstract boolean a(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);

    public ItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public long e() {
        return this.f10118a.getMoveDuration();
    }

    public void a(long j) {
        this.f10118a.setMoveDuration(j);
    }

    public void d(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d("ARVItemMoveAnimMgr", "dispatchMoveStarting(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchMoveStarting(viewHolder);
    }

    public void e(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d("ARVItemMoveAnimMgr", "dispatchMoveFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchMoveFinished(viewHolder);
    }

    /* access modifiers changed from: protected */
    public boolean f(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (moveAnimationInfo.f10122a == null) {
            return false;
        }
        if (viewHolder != null && moveAnimationInfo.f10122a != viewHolder) {
            return false;
        }
        b(moveAnimationInfo, moveAnimationInfo.f10122a);
        e(moveAnimationInfo, moveAnimationInfo.f10122a);
        moveAnimationInfo.a(moveAnimationInfo.f10122a);
        return true;
    }
}
