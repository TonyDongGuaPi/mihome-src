package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder;

public abstract class AbstractSwipeableItemViewHolder extends RecyclerView.ViewHolder implements SwipeableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f5736a;
    private int b = 0;
    private int c = 0;
    private float d;
    private float e;
    private float f = -65536.0f;
    private float g = -65537.0f;
    private float h = 65536.0f;
    private float i = 65537.0f;

    public void a(float f2, float f3, boolean z) {
    }

    public abstract View k();

    public AbstractSwipeableItemViewHolder(View view) {
        super(view);
    }

    public void b(int i2) {
        this.f5736a = i2;
    }

    public int b() {
        return this.f5736a;
    }

    public void c(int i2) {
        this.b = i2;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public void d(int i2) {
        this.c = i2;
    }

    public void b(float f2) {
        this.e = f2;
    }

    public float f() {
        return this.e;
    }

    public void a(float f2) {
        this.d = f2;
    }

    public float e() {
        return this.d;
    }

    public void c(float f2) {
        this.f = f2;
    }

    public float g() {
        return this.f;
    }

    public void d(float f2) {
        this.g = f2;
    }

    public float h() {
        return this.g;
    }

    public void e(float f2) {
        this.h = f2;
    }

    public float i() {
        return this.h;
    }

    public void f(float f2) {
        this.i = f2;
    }

    public float j() {
        return this.i;
    }
}
