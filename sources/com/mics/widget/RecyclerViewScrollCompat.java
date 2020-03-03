package com.mics.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewScrollCompat extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private View.OnTouchListener f7815a;
    private List<Trigger> b;
    private int c = 0;

    public RecyclerViewScrollCompat(Context context) {
        super(context);
    }

    public RecyclerViewScrollCompat(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecyclerViewScrollCompat(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onScrolled(int i, int i2) {
        super.onScrolled(i, i2);
        this.c += i2;
        this.c = this.c > 0 ? 0 : this.c;
        if (this.b != null) {
            for (Trigger next : this.b) {
                boolean a2 = next.a(getScrollOffset());
                if (a2 != next.a()) {
                    next.b(a2);
                    next.a(a2);
                }
            }
        }
    }

    public void scrollToPosition(int i) {
        super.scrollToPosition(i);
        if ((getLayoutManager() instanceof LinearLayoutManager) && i == getLayoutManager().getItemCount() - 1) {
            this.c = 0;
        }
    }

    public void touchDown() {
        if (this.f7815a != null) {
            this.f7815a.onTouch(this, MotionEvent.obtain(0, 0, 0, 0.0f, 0.0f, 0));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f7815a != null) {
            this.f7815a.onTouch(this, motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setTouchListener(View.OnTouchListener onTouchListener) {
        this.f7815a = onTouchListener;
    }

    public float getScrollOffset() {
        int height = getHeight();
        if (height == 0) {
            return -1.0f;
        }
        return (((float) Math.abs(this.c)) * 1.0f) / ((float) height);
    }

    public int getScrollDistance() {
        return this.c;
    }

    public void addTrigger(Trigger trigger) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(trigger);
    }

    public static abstract class Trigger {

        /* renamed from: a  reason: collision with root package name */
        private boolean f7816a;

        public abstract void a(boolean z);

        public abstract boolean a(float f);

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f7816a;
        }

        /* access modifiers changed from: package-private */
        public void b(boolean z) {
            this.f7816a = z;
        }
    }
}
