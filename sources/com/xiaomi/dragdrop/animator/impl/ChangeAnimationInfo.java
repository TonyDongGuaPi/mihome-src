package com.xiaomi.dragdrop.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class ChangeAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f10121a;
    public RecyclerView.ViewHolder b;
    public int c;
    public int d;
    public int e;
    public int f;

    public ChangeAnimationInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        this.b = viewHolder;
        this.f10121a = viewHolder2;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public RecyclerView.ViewHolder a() {
        return this.b != null ? this.b : this.f10121a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.b == viewHolder) {
            this.b = null;
        }
        if (this.f10121a == viewHolder) {
            this.f10121a = null;
        }
        if (this.b == null && this.f10121a == null) {
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
        }
    }

    public String toString() {
        return "ChangeInfo{, oldHolder=" + this.b + ", newHolder=" + this.f10121a + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + Operators.BLOCK_END;
    }
}
