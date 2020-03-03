package com.xiaomi.smarthome.globalnavbutton;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GlobalNavButtonDragUtil {
    public static void a(View view) {
        a(view, 0);
    }

    public static void a(View view, long j) {
        view.setOnTouchListener(new TouchListener(j));
    }

    private static class TouchListener implements View.OnTouchListener {

        /* renamed from: a  reason: collision with root package name */
        private float f17905a;
        private float b;
        private long c;
        private long d;
        private ObjectAnimator e;

        private TouchListener() {
        }

        private TouchListener(long j) {
            this.d = j;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f17905a = motionEvent.getRawX();
                    this.b = motionEvent.getRawY();
                    this.c = System.currentTimeMillis();
                    break;
                case 1:
                    this.e = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{view.getTranslationX(), 0.0f});
                    this.e.setDuration(300);
                    this.e.start();
                    break;
                case 2:
                    float rawX = motionEvent.getRawX() - this.f17905a;
                    float rawY = motionEvent.getRawY() - this.b;
                    Log.d("hzd1", "getRawY=" + motionEvent.getRawY() + ",getY=" + motionEvent.getY() + ",yDistance=" + rawY);
                    view.setTranslationX(rawX);
                    view.setTranslationY(rawY);
                    break;
            }
            return false;
        }
    }
}
