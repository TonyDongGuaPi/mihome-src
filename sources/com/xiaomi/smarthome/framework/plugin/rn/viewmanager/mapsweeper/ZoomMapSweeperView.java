package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class ZoomMapSweeperView extends RelativeLayout {
    public static final float MAX_ZOOM = 10.0f;

    /* renamed from: a  reason: collision with root package name */
    private static final float f17623a = 1.0f;
    private static final float b = 1.0f;
    private static final int u = 1001;
    private boolean c;
    private long d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private boolean j;
    private float k;
    private float l;
    ZoomViewListener listener;
    private float m;
    float maxZoom;
    private float n;
    private float o;
    private final Matrix p;
    private final Paint q;
    private Bitmap r;
    private MapSweeperView s;
    float smoothZoom;
    float smoothZoomX;
    float smoothZoomY;
    private int t;
    /* access modifiers changed from: private */
    public boolean v;
    private Handler w;
    float zoom;
    float zoomX;
    float zoomY;

    public interface ZoomViewListener {
        void a(float f, float f2, float f3);

        void b(float f, float f2, float f3);

        void c(float f, float f2, float f3);
    }

    private float b(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public ZoomMapSweeperView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ZoomMapSweeperView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZoomMapSweeperView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.zoom = 1.0f;
        this.maxZoom = 10.0f;
        this.smoothZoom = 1.0f;
        this.p = new Matrix();
        this.q = new Paint();
        this.v = true;
        this.w = new Handler() {
            public void handleMessage(Message message) {
                if (message != null && message.what == 1001) {
                    boolean unused = ZoomMapSweeperView.this.v = true;
                }
            }
        };
        a(context);
    }

    private void a(Context context) {
        this.s = new MapSweeperView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        addView(this.s, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int a2 = a(i2, i3);
        this.s.setHeight(a2);
        this.s.setWidth(a2);
        super.onMeasure(i2, i3);
    }

    private int a(int i2, int i3) {
        int i4 = this.t;
        int size = View.MeasureSpec.getSize(i3);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i3);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 1073741824 && (mode2 == 1073741824 || mode2 == Integer.MIN_VALUE)) {
            return size <= size2 ? size : size2;
        }
        return i4;
    }

    public MapSweeperView getMapSweeperView() {
        return this.s;
    }

    public void setScreenWidth(int i2) {
        this.t = i2;
    }

    public float getZoom() {
        return this.zoom;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(float f2) {
        if (f2 < 1.0f || f2 > 10.0f) {
            this.maxZoom = 10.0f;
        } else {
            this.maxZoom = f2;
        }
    }

    public void zoomTo(float f2, float f3, float f4) {
        this.zoom = Math.min(f2, this.maxZoom);
        this.zoomX = f3;
        this.zoomY = f4;
        smoothZoomTo(this.zoom, f3, f4);
    }

    public void smoothZoomTo(float f2, float f3, float f4) {
        this.smoothZoom = a(1.0f, f2, this.maxZoom);
        this.smoothZoomX = f3;
        this.smoothZoomY = f4;
        if (this.listener != null) {
            this.listener.a(this.smoothZoom, f3, f4);
        }
    }

    public ZoomViewListener getListener() {
        return this.listener;
    }

    public void setListner(ZoomViewListener zoomViewListener) {
        this.listener = zoomViewListener;
    }

    public float getZoomFocusX() {
        return this.zoomX * this.zoom;
    }

    public float getZoomFocusY() {
        return this.zoomY * this.zoom;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2 || this.zoom > 1.0f) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            a(motionEvent);
        }
        if (motionEvent.getPointerCount() == 2) {
            c(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (motionEvent.getPointerCount() == 2) {
                    this.v = false;
                    break;
                }
                break;
            case 1:
                this.w.sendEmptyMessageDelayed(1001, 10000);
                break;
        }
        getRootView().invalidate();
        invalidate();
        return true;
    }

    private void a(MotionEvent motionEvent) {
        b(motionEvent);
    }

    private void b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float hypot = (float) Math.hypot((double) (x - this.e), (double) (y - this.f));
        float f2 = x - this.g;
        float f3 = y - this.h;
        this.g = x;
        this.h = y;
        int action = motionEvent.getAction();
        if (action != 4) {
            switch (action) {
                case 0:
                    this.e = x;
                    this.f = y;
                    this.g = x;
                    this.h = y;
                    this.c = false;
                    break;
                case 1:
                    break;
                case 2:
                    if (this.c || hypot > 30.0f) {
                        if (!this.c) {
                            this.c = true;
                            motionEvent.setAction(3);
                            super.dispatchTouchEvent(motionEvent);
                        }
                        this.smoothZoomX -= f2 / this.zoom;
                        this.smoothZoomY -= f3 / this.zoom;
                        return;
                    }
            }
        }
        if (hypot < 30.0f) {
            performClick();
        }
        motionEvent.setLocation(this.zoomX + ((x - (((float) getWidth()) * 0.5f)) / this.zoom), this.zoomY + ((y - (((float) getHeight()) * 0.5f)) / this.zoom));
        super.dispatchTouchEvent(motionEvent);
    }

    private void c(MotionEvent motionEvent) {
        float x = motionEvent.getX(0);
        float f2 = x - this.l;
        this.l = x;
        float y = motionEvent.getY(0);
        float f3 = y - this.m;
        this.m = y;
        float x2 = motionEvent.getX(1);
        float f4 = x2 - this.n;
        this.n = x2;
        float y2 = motionEvent.getY(1);
        float f5 = y2 - this.o;
        this.o = y2;
        float hypot = (float) Math.hypot((double) (x2 - x), (double) (y2 - y));
        float f6 = hypot - this.k;
        this.k = hypot;
        float abs = Math.abs(hypot - this.i);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.i = hypot;
            this.j = false;
        } else if (action != 2) {
            if (action == 6 || action == 262) {
                getMapSweeperView().startCommonSweeperViewAnimatorDelayed(150);
            } else {
                this.j = false;
            }
        } else if (this.j || abs > 30.0f) {
            this.j = true;
            smoothZoomTo(Math.max(1.0f, (this.zoom * hypot) / (hypot - f6)), this.zoomX - (((f2 + f4) * 0.5f) / this.zoom), this.zoomY - (((f3 + f5) * 0.5f) / this.zoom));
        }
        motionEvent.setAction(3);
        super.dispatchTouchEvent(motionEvent);
    }

    private float a(float f2, float f3, float f4) {
        return Math.max(f2, Math.min(f3, f4));
    }

    private float c(float f2, float f3, float f4) {
        float f5 = f3 - f2;
        return Math.abs(f5) >= f4 ? f2 + (f4 * Math.signum(f5)) : f3;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (getChildCount() != 0) {
            this.zoom = b(c(this.zoom, this.smoothZoom, 0.05f), this.smoothZoom, 0.2f);
            this.smoothZoomX = a((((float) getWidth()) * 0.5f) / this.smoothZoom, this.smoothZoomX, ((float) getWidth()) - ((((float) getWidth()) * 0.5f) / this.smoothZoom));
            this.smoothZoomY = a((((float) getHeight()) * 0.5f) / this.smoothZoom, this.smoothZoomY, ((float) getHeight()) - ((((float) getHeight()) * 0.5f) / this.smoothZoom));
            this.zoomX = b(c(this.zoomX, this.smoothZoomX, 0.1f), this.smoothZoomX, 0.35f);
            this.zoomY = b(c(this.zoomY, this.smoothZoomY, 0.1f), this.smoothZoomY, 0.35f);
            if (!(this.zoom == this.smoothZoom || this.listener == null)) {
                this.listener.b(this.zoom, this.zoomX, this.zoomY);
            }
            this.p.setTranslate(((float) getWidth()) * 0.5f, ((float) getHeight()) * 0.5f);
            this.p.preScale(this.zoom, this.zoom);
            this.p.preTranslate(-a((((float) getWidth()) * 0.5f) / this.zoom, this.zoomX, ((float) getWidth()) - ((((float) getWidth()) * 0.5f) / this.zoom)), -a((((float) getHeight()) * 0.5f) / this.zoom, this.zoomY, ((float) getHeight()) - ((((float) getHeight()) * 0.5f) / this.zoom)));
            View childAt = getChildAt(0);
            this.p.preTranslate((float) childAt.getLeft(), (float) childAt.getTop());
            if (this.s != null) {
                this.s.updateViewByZoom(this.zoom);
            }
            this.r = null;
            canvas.save();
            canvas.concat(this.p);
            childAt.draw(canvas);
            canvas.restore();
        }
    }

    public boolean isScallEnable() {
        return this.v;
    }
}
