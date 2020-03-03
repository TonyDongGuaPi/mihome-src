package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class RemoveAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f5695a;

    public RemoveAnimationInfo(RecyclerView.ViewHolder viewHolder) {
        this.f5695a = viewHolder;
    }

    public RecyclerView.ViewHolder a() {
        return this.f5695a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.f5695a == viewHolder) {
            this.f5695a = null;
        }
    }

    public String toString() {
        return "RemoveAnimationInfo{holder=" + this.f5695a + Operators.BLOCK_END;
    }
}
