package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;

public class BtnAndProgressView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18775a = 50000;
    private static final int b = 30000;
    private static final int c = 500;
    private static final int d = 300;
    private static final int e = 10;
    private static final int f = 10;
    private static final int g = 11;
    private static final int h = 153;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;
    private static final int l = 4;
    private ProgressCallBack A;
    private boolean B = false;
    /* access modifiers changed from: private */
    public Handler C = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 10:
                    BtnAndProgressView.this.invalidate();
                    BtnAndProgressView.this.C.sendEmptyMessageDelayed(10, 50);
                    return;
                case 11:
                    BtnAndProgressView.this.invalidate();
                    return;
                default:
                    return;
            }
        }
    };
    private Bitmap m;
    private NinePatch n;
    private Paint o = new Paint();
    private int p;
    private int q;
    private Bitmap r;
    private String s;
    private Paint t;
    private boolean u = false;
    private long v;
    private int w = 1;
    private int x = 0;
    private Paint y = new Paint();
    private Paint z = new Paint();

    public interface ProgressCallBack {
        void a();

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();
    }

    public BtnAndProgressView(Context context) {
        super(context);
        a();
    }

    public BtnAndProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BtnAndProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        this.m = BitmapFactory.decodeResource(getResources(), R.drawable.kuailian_btn_bg);
        this.n = new NinePatch(this.m, this.m.getNinePatchChunk(), (String) null);
        this.p = this.n.getWidth();
        this.q = this.n.getHeight();
        this.r = BitmapFactory.decodeResource(getResources(), R.drawable.kuailian_loading);
        this.s = getResources().getString(R.string.next_button);
        this.t = new Paint();
        this.t.setTextSize(getResources().getDimension(R.dimen.font_size_4));
        this.t.setColor(getResources().getColor(R.color.white));
        this.y.setFlags(5);
        this.o.setFlags(5);
        this.z.setFlags(5);
        this.z.setStyle(Paint.Style.STROKE);
        this.z.setStrokeWidth(4.0f);
        this.z.setColor(getResources().getColor(R.color.white));
    }

    public void setListener(ProgressCallBack progressCallBack) {
        this.A = progressCallBack;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                case 1:
                    break;
            }
        }
        this.C.sendEmptyMessage(11);
        return super.onTouchEvent(motionEvent);
    }

    public void reset() {
        this.x = 0;
        this.B = false;
        this.C.removeMessages(10);
        this.C.sendEmptyMessage(11);
        this.w = 1;
        this.u = false;
    }

    public void showProgressBar() {
        this.x = 0;
        this.u = true;
        if (!this.C.hasMessages(10)) {
            this.C.sendEmptyMessage(10);
        }
    }

    public void hideProgressBar() {
        this.x = 0;
        this.u = false;
        this.C.removeMessages(10);
        this.C.sendEmptyMessage(11);
    }

    public void switchToProgressBar() {
        this.v = System.currentTimeMillis();
        this.w = 2;
        if (!this.C.hasMessages(10)) {
            this.C.sendEmptyMessage(10);
        }
    }

    public void switchToProgressBarO() {
        this.v = System.currentTimeMillis();
        this.w = 2;
        this.B = false;
        if (!this.C.hasMessages(10)) {
            this.C.sendEmptyMessage(10);
        }
    }

    public void startProgress() {
        this.w = 3;
        this.v = System.currentTimeMillis();
        if (!this.C.hasMessages(10)) {
            this.C.sendEmptyMessage(10);
        }
    }

    public void switchBack() {
        this.x = 0;
        this.v = System.currentTimeMillis();
        this.u = false;
        this.w = 4;
        if (!this.C.hasMessages(10)) {
            this.C.sendEmptyMessage(10);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        if (this.w == 1) {
            this.t.setAlpha(255);
            if (!isEnabled()) {
                this.t.setAlpha(153);
                this.o.setAlpha(153);
            } else if (isPressed()) {
                this.t.setAlpha(153);
                this.o.setAlpha(153);
            } else {
                this.t.setAlpha(255);
                this.o.setAlpha(255);
            }
            this.n.draw(canvas2, new Rect(0, (getHeight() - this.q) / 2, getWidth(), (getHeight() + this.q) / 2), this.o);
            canvas2.drawText(this.s, (((float) getWidth()) - this.t.measureText(this.s)) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
            if (this.u) {
                this.x += 15;
                this.x %= 360;
                canvas.save();
                canvas2.translate(((((float) (getWidth() / 2)) + this.t.measureText(this.s)) / 2.0f) + ((float) (this.r.getWidth() / 2)), (float) (getHeight() / 2));
                canvas2.rotate((float) this.x);
                canvas2.drawBitmap(this.r, new Rect(0, 0, this.r.getWidth(), this.r.getHeight()), new RectF((float) ((-this.r.getWidth()) / 2), (float) ((-this.r.getHeight()) / 2), (float) (this.r.getWidth() / 2), (float) (this.r.getHeight() / 2)), this.y);
                canvas.restore();
            }
        } else if (this.w == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.v < 500) {
                int width = (int) (((long) getWidth()) - ((((long) (getWidth() - this.p)) * (currentTimeMillis - this.v)) / 500));
                canvas.save();
                canvas2.translate((float) (getWidth() / 2), 0.0f);
                this.o.setAlpha(153);
                this.n.draw(canvas2, new Rect((-width) / 2, (getHeight() - this.q) / 2, width / 2, (getHeight() + this.q) / 2), this.o);
                float measureText = this.t.measureText(this.s);
                this.t.setAlpha(255);
                if (((float) width) < measureText) {
                    canvas2.drawText("0%", (-this.t.measureText(this.s)) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
                } else {
                    canvas2.drawText(this.s, (-this.t.measureText(this.s)) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
                }
                canvas.restore();
            } else if ((currentTimeMillis - this.v) - 500 < 300) {
                int i2 = (int) (((long) this.p) + ((currentTimeMillis - this.v) - 500 < 150 ? ((((currentTimeMillis - this.v) - 500) * 10) * 2) / 300 : (((300 - ((currentTimeMillis - this.v) - 500)) * 10) * 2) / 300));
                canvas.save();
                canvas2.translate((float) (getWidth() / 2), 0.0f);
                this.o.setAlpha(153);
                this.n.draw(canvas2, new Rect((-i2) / 2, (getHeight() - this.q) / 2, i2 / 2, (getHeight() + this.q) / 2), this.o);
                this.t.setAlpha(255);
                canvas2.drawText("0%", (-this.t.measureText("0%")) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
                canvas.restore();
            } else {
                canvas.save();
                canvas2.translate((float) (getWidth() / 2), 0.0f);
                this.o.setAlpha(153);
                this.n.draw(canvas2, new Rect((-this.p) / 2, (getHeight() - this.q) / 2, this.p / 2, (getHeight() + this.q) / 2), this.o);
                canvas2.drawText("0%", (-this.t.measureText("0%")) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
                canvas.restore();
                startProgress();
                if (!this.B) {
                    this.B = true;
                    if (this.A != null) {
                        this.A.a();
                    }
                }
            }
        } else if (this.w == 3) {
            long currentTimeMillis2 = System.currentTimeMillis() - this.v;
            int i3 = -((int) ((360 * currentTimeMillis2) / 50000));
            long j2 = (currentTimeMillis2 * 100) / 50000;
            int i4 = (int) j2;
            if (i4 > 100) {
                if (this.A != null) {
                    this.A.f();
                }
                this.C.removeMessages(10);
            }
            if (i4 > 75) {
                if (this.A != null) {
                    this.A.e();
                }
            } else if (i4 > 50) {
                if (this.A != null) {
                    this.A.d();
                }
            } else if (i4 > 25) {
                if (this.A != null) {
                    this.A.c();
                }
            } else if (i4 > 0 && this.A != null) {
                this.A.b();
            }
            String str = String.valueOf(j2) + Operators.MOD;
            canvas.save();
            canvas2.translate((float) (getWidth() / 2), 0.0f);
            this.o.setAlpha(153);
            this.n.draw(canvas2, new Rect((-this.p) / 2, (getHeight() - this.q) / 2, this.p / 2, (getHeight() + this.q) / 2), this.o);
            this.t.setAlpha(255);
            canvas2.drawText(str, (-this.t.measureText(str)) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
            canvas.drawArc(new RectF((float) (((-this.p) / 2) + 2), (float) ((getHeight() - this.q) / 2), (float) ((this.p / 2) - 2), (float) ((getHeight() + this.q) / 2)), 270.0f, (float) i3, false, this.z);
            canvas.restore();
        } else if (this.w == 4) {
            long currentTimeMillis3 = System.currentTimeMillis() - this.v;
            if (currentTimeMillis3 >= 30000 && this.A != null) {
                this.A.g();
            }
            String valueOf = String.valueOf((30000 - currentTimeMillis3) / 1000);
            canvas.save();
            canvas2.translate((float) (getWidth() / 2), 0.0f);
            this.o.setAlpha(153);
            this.n.draw(canvas2, new Rect((-this.p) / 2, (getHeight() - this.q) / 2, this.p / 2, (getHeight() + this.q) / 2), this.o);
            this.t.setAlpha(255);
            canvas2.drawText(valueOf, (-this.t.measureText(valueOf)) / 2.0f, ((float) (getHeight() / 2)) + (getResources().getDimension(R.dimen.font_size_4) / 2.0f), this.t);
            canvas.drawArc(new RectF((float) (((-this.p) / 2) + 2), (float) ((getHeight() - this.q) / 2), (float) ((this.p / 2) - 2), (float) ((getHeight() + this.q) / 2)), 270.0f, (float) ((int) ((currentTimeMillis3 * 360) / 30000)), false, this.z);
            canvas.restore();
        }
    }
}
