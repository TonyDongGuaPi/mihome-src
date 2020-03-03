package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.dragdrop.animator.BaseItemAnimator;

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
        this.f10118a.dispatchChangeStarting(viewHolder, viewHolder == changeAnimationInfo.b);
    }

    public void e(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (a()) {
            Log.d(e, "dispatchChangeFinished(" + viewHolder + Operators.BRACKET_END_STR);
        }
        this.f10118a.dispatchChangeFinished(viewHolder, viewHolder == changeAnimationInfo.b);
    }

    public long e() {
        return this.f10118a.getChangeDuration();
    }

    public void a(long j) {
        this.f10118a.setChangeDuration(j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void a(ChangeAnimationInfo changeAnimationInfo) {
        if (!(changeAnimationInfo.b == null || changeAnimationInfo.b.itemView == null)) {
            a(changeAnimationInfo);
        }
        if (changeAnimationInfo.f10121a != null && changeAnimationInfo.f10121a.itemView != null) {
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
        if (changeAnimationInfo.f10121a != null && (viewHolder == null || changeAnimationInfo.f10121a == viewHolder)) {
            b(changeAnimationInfo, changeAnimationInfo.f10121a);
            e(changeAnimationInfo, changeAnimationInfo.f10121a);
            changeAnimationInfo.a(changeAnimationInfo.f10121a);
        }
        return changeAnimationInfo.b == null && changeAnimationInfo.f10121a == null;
    }
}
