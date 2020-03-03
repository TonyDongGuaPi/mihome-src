package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.dragdrop.animator.CubeRotationAnimation;

public class TouchOnItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    RecyclerView.ViewHolder f10109a;
    RecyclerView b;
    Interpolator c = new LinearInterpolator();
    float d;
    float e;
    float f;
    float g;
    float h;

    public void a(RecyclerView recyclerView, MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder) {
        this.f10109a = viewHolder;
        this.b = recyclerView;
        float x = motionEvent.getX();
        this.d = x;
        this.f = x;
        float y = motionEvent.getY();
        this.e = y;
        this.g = y;
        int top = this.f10109a.itemView.getTop() + (this.f10109a.itemView.getHeight() / 2);
        this.h = 30.0f;
        if (this.g > ((float) top)) {
            this.h = -this.h;
        }
        CubeRotationAnimation cubeRotationAnimation = new CubeRotationAnimation();
        cubeRotationAnimation.a(0.0f, this.h);
        cubeRotationAnimation.setDuration(150);
        cubeRotationAnimation.setFillAfter(true);
        this.f10109a.itemView.startAnimation(cubeRotationAnimation);
    }

    public void a() {
        CubeRotationAnimation cubeRotationAnimation = new CubeRotationAnimation();
        cubeRotationAnimation.a(this.h, 0.0f);
        cubeRotationAnimation.setDuration(150);
        cubeRotationAnimation.setFillAfter(true);
        this.f10109a.itemView.startAnimation(cubeRotationAnimation);
    }

    public void a(MotionEvent motionEvent) {
        this.f = motionEvent.getX();
        this.g = motionEvent.getY();
    }
}
