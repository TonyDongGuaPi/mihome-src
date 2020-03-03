package com.mibi.common.component;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutWithDefaultTouchRecepient extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f7489a = new Rect();
    private View b;
    private onSizeChangedListener c;

    public interface onSizeChangedListener {
        void a(int i, int i2, int i3, int i4);
    }

    public LinearLayoutWithDefaultTouchRecepient(Context context) {
        super(context);
    }

    public LinearLayoutWithDefaultTouchRecepient(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDefaultTouchRecepient(View view) {
        this.b = view;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (super.dispatchTouchEvent(motionEvent)) {
            return true;
        }
        this.f7489a.set(0, 0, 0, 0);
        offsetRectIntoDescendantCoords(this.b, this.f7489a);
        motionEvent.setLocation(motionEvent.getX() + ((float) this.f7489a.left), motionEvent.getY() + ((float) this.f7489a.top));
        return this.b.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.c != null) {
            this.c.a(i, i2, i3, i4);
        }
    }

    public void setOnSizeChangedListener(onSizeChangedListener onsizechangedlistener) {
        this.c = onsizechangedlistener;
    }
}
