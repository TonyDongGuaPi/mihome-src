package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class AddAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f5689a;

    public AddAnimationInfo(RecyclerView.ViewHolder viewHolder) {
        this.f5689a = viewHolder;
    }

    public RecyclerView.ViewHolder a() {
        return this.f5689a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.f5689a == null) {
            this.f5689a = null;
        }
    }

    public String toString() {
        return "AddAnimationInfo{holder=" + this.f5689a + Operators.BLOCK_END;
    }
}
