package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.support.v7.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

public class MoveAnimationInfo extends ItemAnimationInfo {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.ViewHolder f5694a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public MoveAnimationInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        this.f5694a = viewHolder;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public RecyclerView.ViewHolder a() {
        return this.f5694a;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.f5694a == viewHolder) {
            this.f5694a = null;
        }
    }

    public String toString() {
        return "MoveAnimationInfo{holder=" + this.f5694a + ", fromX=" + this.b + ", fromY=" + this.c + ", toX=" + this.d + ", toY=" + this.e + Operators.BLOCK_END;
    }
}
