package com.xiaomi.dragdrop.annotation;

import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class RecyclerViewTouchActionGuardManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10124a = "ARVTouchActionGuardMgr";
    private static final boolean b = false;
    private static final boolean c = false;
    private RecyclerView.OnItemTouchListener d = new RecyclerView.OnItemTouchListener() {
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return RecyclerViewTouchActionGuardManager.this.a(recyclerView, motionEvent);
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            RecyclerViewTouchActionGuardManager.this.b(recyclerView, motionEvent);
        }
    };
    private RecyclerView e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;

    public boolean a() {
        return this.d == null;
    }

    public void a(@NonNull RecyclerView recyclerView) {
        if (a()) {
            throw new IllegalStateException("Accessing released object");
        } else if (this.e == null) {
            this.e = recyclerView;
            this.e.addOnItemTouchListener(this.d);
            this.i = ViewConfiguration.get(recyclerView.getContext()).getScaledTouchSlop();
        } else {
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
    }

    public void b() {
        if (!(this.e == null || this.d == null)) {
            this.e.removeOnItemTouchListener(this.d);
        }
        this.d = null;
        this.e = null;
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (!this.j) {
            return false;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                a(motionEvent);
                break;
            case 1:
            case 3:
                e();
                break;
            case 2:
                if (c(recyclerView, motionEvent)) {
                    return true;
                }
                break;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.j) {
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            if (actionMasked == 1 || actionMasked == 3) {
                e();
            }
        }
    }

    private boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (!this.f) {
            this.h = (int) (motionEvent.getY() + 0.5f);
            int i2 = this.h - this.g;
            if (this.k && Math.abs(i2) > this.i && b(recyclerView)) {
                this.f = true;
            }
        }
        return this.f;
    }

    private static boolean b(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        return itemAnimator != null && itemAnimator.isRunning();
    }

    private void e() {
        this.f = false;
        this.g = 0;
        this.h = 0;
    }

    private void a(MotionEvent motionEvent) {
        int y = (int) (motionEvent.getY() + 0.5f);
        this.h = y;
        this.g = y;
        this.f = false;
    }

    public void a(boolean z) {
        if (this.j != z) {
            this.j = z;
            if (!this.j) {
                e();
            }
        }
    }

    public boolean c() {
        return this.j;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public boolean d() {
        return this.k;
    }
}
