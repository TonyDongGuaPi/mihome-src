package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class AddAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f10117a;

    public AddAnimationInfo(RecyclerView.ViewHolder viewHolder) {
        this.f10117a = viewHolder;
    }

    public RecyclerView.ViewHolder a() {
        return this.f10117a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.f10117a == null) {
            this.f10117a = null;
        }
    }

    public String toString() {
        return "AddAnimationInfo{holder=" + this.f10117a + Operators.BLOCK_END;
    }
}
