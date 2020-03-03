package cn.fraudmetrix.octopus.aspirit.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.fraudmetrix.octopus.aspirit.R;
import com.taobao.weex.el.parse.Operators;

public class CircleProgerssView extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f657a = 15;
    private int b = 16316665;
    private int c = 2395135;
    private int d = 5000;
    private int e = 10;
    private int f = 2395135;
    private int g = 7269375;
    private int h = 20;
    private int i = 2395135;
    private Paint j;
    private Paint k;
    private Paint l;
    /* access modifiers changed from: private */
    public Point m;
    private int n = 200;
    private int o = 0;
    /* access modifiers changed from: private */
    public Matrix p;
    /* access modifiers changed from: private */
    public int q = 5000;
    /* access modifiers changed from: private */
    public Handler r = new Handler();
    private int s = 0;
    /* access modifiers changed from: private */
    public Runnable t = new Runnable() {
        public void run() {
            CircleProgerssView.this.p.postRotate((360.0f / ((float) CircleProgerssView.this.q)) * 16.0f, (float) CircleProgerssView.this.m.x, (float) CircleProgerssView.this.m.y);
            CircleProgerssView.this.postInvalidate();
            CircleProgerssView.this.r.postDelayed(CircleProgerssView.this.t, 16);
        }
    };

    public CircleProgerssView(Context context) {
        super(context);
    }

    public CircleProgerssView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.f657a = 23;
        this.b = resources.getColor(R.color.color_out_circle_bottom);
        this.c = resources.getColor(R.color.color_out_circle_progress);
        this.d = 4000;
        this.e = 10;
        this.f = resources.getColor(R.color.color_inner_circle);
        this.g = resources.getColor(R.color.color_inner_circle_scanline);
        this.h = 100;
        this.i = this.c;
        this.j = new Paint();
        this.k = new Paint();
        this.l = new Paint();
        this.m = new Point();
        this.p = new Matrix();
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(this.n, View.MeasureSpec.getSize(i2));
        }
        if (mode == 0) {
            return this.n;
        }
        if (mode != 1073741824) {
            return 0;
        }
        return View.MeasureSpec.getSize(i2);
    }

    private void a(Canvas canvas) {
        this.k.setColor(-16777216);
        this.k.setStyle(Paint.Style.FILL_AND_STROKE);
        this.k.setAlpha(100);
        this.k.setShader(new SweepGradient((float) this.m.x, (float) this.m.y, getResources().getColor(R.color.color_inner_circle_scanlineend), this.g));
        canvas.concat(this.p);
        canvas.drawCircle((float) this.m.x, (float) this.m.y, (float) (this.o / 5), this.k);
        this.j.setStrokeWidth((float) this.e);
        this.j.setAntiAlias(true);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setColor(this.f);
        canvas.drawCircle((float) this.m.x, (float) this.m.y, (float) (((this.o / 5) + this.e) - 4), this.j);
    }

    public int getProgress() {
        return this.s;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.j.setStrokeWidth((float) this.f657a);
        this.j.setAntiAlias(true);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setColor(this.b);
        canvas.drawCircle((float) this.m.x, (float) this.m.y, (float) (this.o - this.f657a), this.j);
        this.j.setColor(this.c);
        canvas.drawArc(new RectF((float) ((this.m.x - this.o) + this.f657a), (float) ((this.m.x - this.o) + this.f657a), (float) ((this.m.x + this.o) - this.f657a), (float) ((this.m.x + this.o) - this.f657a)), -90.0f, (float) ((this.s * 360) / 100), false, this.j);
        this.l.setColor(this.i);
        this.l.setAntiAlias(true);
        this.l.setTextAlign(Paint.Align.CENTER);
        this.l.setTextSize((float) this.h);
        this.l.setFakeBoldText(true);
        canvas.drawText(this.s + "", (float) this.m.x, (float) (this.m.y + ((this.o / 5) * 3) + this.e), this.l);
        this.l.setTextSize((float) (this.h / 2));
        canvas.drawText(Operators.MOD, (float) (this.m.x + this.h), (float) (this.m.y + ((this.o / 5) * 3) + this.e), this.l);
        a(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.m.set(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        this.o = Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        new Thread(this.t).start();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(a(i2), a(i2));
    }

    public void setProgress(int i2) {
        this.s = i2;
    }
}
