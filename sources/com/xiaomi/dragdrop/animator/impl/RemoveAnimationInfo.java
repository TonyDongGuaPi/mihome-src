package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class RemoveAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f10123a;

    public RemoveAnimationInfo(RecyclerView.ViewHolder viewHolder) {
        this.f10123a = viewHolder;
    }

    public RecyclerView.ViewHolder a() {
        return this.f10123a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.f10123a == viewHolder) {
            this.f10123a = null;
        }
    }

    public String toString() {
        return "RemoveAnimationInfo{holder=" + this.f10123a + Operators.BLOCK_END;
    }
}
