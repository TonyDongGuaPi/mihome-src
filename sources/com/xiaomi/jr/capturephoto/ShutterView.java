package com.xiaomi.jr.capturephoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShutterView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10350a = 1;
    private static final int b = 2;
    private static final int c = 200;
    private static final int d = 10;
    /* access modifiers changed from: private */
    public float e = (((this.i - this.h) / 200.0f) * 10.0f);
    private float f = (((float) getResources().getDimensionPixelSize(R.dimen.shutter_view_size)) / 2.0f);
    private float g = (((float) getResources().getDimensionPixelSize(R.dimen.shutter_ring_diameter)) / 2.0f);
    /* access modifiers changed from: private */
    public float h = (((float) getResources().getDimensionPixelSize(R.dimen.shutter_min_diameter)) / 2.0f);
    /* access modifiers changed from: private */
    public float i = (((float) getResources().getDimensionPixelSize(R.dimen.shutter_max_diameter)) / 2.0f);
    /* access modifiers changed from: private */
    public float j;
    private Paint k = new Paint();
    private Paint l;
    private Bitmap m;
    private PaintFlagsDrawFilter n;
    /* access modifiers changed from: private */
    public Handler o;

    enum State {
        NORMAL,
        PRESS_ANIM,
        PRESSED,
        RELEASE_ANIM
    }

    private class AnimHandlerThread extends HandlerThread implements Handler.Callback {
        public AnimHandlerThread(String str) {
            super(str);
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                float unused = ShutterView.this.j = ShutterView.this.j - ShutterView.this.e;
                if (ShutterView.this.j <= ShutterView.this.h) {
                    ShutterView.this.a(State.PRESSED);
                    return true;
                }
            } else if (message.what == 2) {
                float unused2 = ShutterView.this.j = ShutterView.this.j + ShutterView.this.e;
                if (ShutterView.this.j >= ShutterView.this.i) {
                    ShutterView.this.a(State.NORMAL);
                    return true;
                }
            }
            ShutterView.this.postInvalidate();
            ShutterView.this.o.sendEmptyMessageDelayed(message.what, 10);
            return true;
        }
    }

    public ShutterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setColor(-1);
        this.k.setStrokeWidth(3.0f);
        this.k.setAntiAlias(true);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.m = a(getResources().getDrawable(R.drawable.capture_photo_shutter));
        this.n = new PaintFlagsDrawFilter(0, 3);
        AnimHandlerThread animHandlerThread = new AnimHandlerThread("shutter_anim");
        animHandlerThread.start();
        this.o = new Handler(animHandlerThread.getLooper(), animHandlerThread);
        a(State.NORMAL);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            a(State.PRESS_ANIM);
        } else if (action == 1) {
            if (motionEvent.getX() > 0.0f && motionEvent.getX() < ((float) getWidth()) && motionEvent.getY() > 0.0f && motionEvent.getY() < ((float) getHeight())) {
                performClick();
            }
            a(State.RELEASE_ANIM);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void a(State state) {
        if (state == State.NORMAL) {
            this.o.removeMessages(1);
            this.o.removeMessages(2);
            this.j = this.i;
            postInvalidate();
        } else if (state == State.PRESS_ANIM) {
            this.o.removeMessages(2);
            this.o.sendEmptyMessage(1);
        } else if (state == State.PRESSED) {
            this.o.removeMessages(1);
            this.o.removeMessages(2);
            this.j = this.h;
            postInvalidate();
        } else if (state == State.RELEASE_ANIM) {
            this.o.removeMessages(1);
            this.o.sendEmptyMessage(2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.n);
        canvas.drawBitmap(this.m, (Rect) null, new RectF(this.f - this.j, this.f - this.j, this.f + this.j, this.f + this.j), this.l);
        canvas.drawCircle(this.f, this.f, this.g, this.k);
        super.onDraw(canvas);
    }

    private Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}
