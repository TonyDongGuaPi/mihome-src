package in.cashify.otex.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import in.cashify.otex.R;

public class CircleRoadProgress extends View implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public float f2612a;
    public float b;
    public int c;
    public float d;
    public float e;
    public int f;
    public float g;
    public float h;
    public boolean i;
    public int j;
    public float k;
    public float l;
    public int m;
    public float n;
    public float o;
    public Paint p;
    public Paint q;
    public Paint r;
    public Paint s;
    public float t;
    public ValueAnimator u;
    public b v;
    public ValueAnimator.AnimatorUpdateListener w = new a();

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float unused = CircleRoadProgress.this.t = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CircleRoadProgress circleRoadProgress = CircleRoadProgress.this;
            circleRoadProgress.a(circleRoadProgress.t);
        }
    }

    public interface b {
        void a();
    }

    public CircleRoadProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        setLayerType(1, (Paint) null);
    }

    public final ValueAnimator a(long j2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.t, 100.0f});
        ofFloat.setDuration(j2);
        ofFloat.addListener(this);
        ofFloat.addUpdateListener(this.w);
        return ofFloat;
    }

    public void a() {
        ValueAnimator valueAnimator = this.u;
        if (valueAnimator != null) {
            valueAnimator.removeListener(this);
            this.u.removeAllUpdateListeners();
            this.u.cancel();
            this.u = null;
        }
    }

    public void a(@FloatRange(from = 0.0d, to = 100.0d) float f2) {
        this.t = f2;
        postInvalidate();
    }

    public void a(long j2, b bVar) {
        this.v = bVar;
        ValueAnimator valueAnimator = this.u;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            a();
        }
        this.u = a(j2);
        this.u.start();
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleRoadProgress);
        this.f2612a = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_circleCenterPointX, 54.0f);
        this.b = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_circleCenterPointY, 54.0f);
        this.c = obtainStyledAttributes.getColor(R.styleable.CircleRoadProgress_roadColor, Color.parseColor("#29000000"));
        this.d = obtainStyledAttributes.getDimension(R.styleable.CircleRoadProgress_roadStrokeWidth, getContext().getResources().getDimension(R.dimen.dimen_3));
        this.e = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_roadRadius, 42.0f);
        this.f = obtainStyledAttributes.getColor(R.styleable.CircleRoadProgress_roadInnerCircleColor, Color.parseColor("#29000000"));
        this.g = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_roadInnerCircleStrokeWidth, 0.0f);
        this.h = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_roadInnerCircleRadius, 0.0f);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.CircleRoadProgress_isDrawInnerCircle, false);
        this.j = obtainStyledAttributes.getColor(R.styleable.CircleRoadProgress_roadOuterCircleColor, Color.parseColor("#FFFFFF"));
        this.k = obtainStyledAttributes.getDimension(R.styleable.CircleRoadProgress_roadOuterCircleStrokeWidth, getContext().getResources().getDimension(R.dimen.dimen_8));
        this.l = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_roadOuterCircleRadius, 42.0f);
        this.m = obtainStyledAttributes.getColor(R.styleable.CircleRoadProgress_arcLoadingColor, ContextCompat.getColor(getContext(), R.color.otexColorOrangeLight));
        this.n = obtainStyledAttributes.getDimension(R.styleable.CircleRoadProgress_arcLoadingStrokeWidth, getContext().getResources().getDimension(R.dimen.dimen_3));
        this.o = obtainStyledAttributes.getFloat(R.styleable.CircleRoadProgress_arcLoadingStartAngle, 270.0f);
        obtainStyledAttributes.recycle();
    }

    public final void a(Paint paint, Canvas canvas) {
        float f2 = this.f2612a;
        float f3 = f2 - this.e;
        float f4 = (f2 - (f3 / 2.0f)) * 2.0f;
        Canvas canvas2 = canvas;
        canvas2.drawArc(new RectF(f3, f3, f4, f4), this.o, this.t * 360.0f * 0.01f, false, paint);
    }

    public final void b() {
        this.r = new Paint(7);
        this.r.setDither(true);
        this.r.setColor(this.f);
        this.r.setStyle(Paint.Style.FILL);
        this.r.setStrokeWidth(this.g);
        this.p = new Paint(7);
        this.p.setDither(true);
        this.p.setColor(this.j);
        this.p.setStyle(Paint.Style.STROKE);
        this.p.setStrokeWidth(this.k);
        this.q = new Paint(7);
        this.q.setDither(true);
        this.q.setColor(this.c);
        this.q.setStyle(Paint.Style.STROKE);
        this.q.setStrokeWidth(this.d);
        this.s = new Paint(7);
        this.s.setColor(this.m);
        this.s.setStyle(Paint.Style.STROKE);
        this.s.setStrokeCap(Paint.Cap.ROUND);
        this.s.setStrokeWidth(this.n);
    }

    public void b(long j2, b bVar) {
        ValueAnimator valueAnimator = this.u;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            a();
        }
        a(0.0f);
        this.v = bVar;
        if (j2 > 0) {
            this.u = a(j2);
            this.u.start();
        }
    }

    public final void b(Paint paint, Canvas canvas) {
        canvas.drawCircle(this.f2612a, this.b, this.e, paint);
    }

    public final void c(Paint paint, Canvas canvas) {
        canvas.drawCircle(this.f2612a, this.b, this.h, paint);
    }

    public final void d(Paint paint, Canvas canvas) {
        canvas.drawCircle(this.f2612a, this.b, this.l, paint);
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        b bVar = this.v;
        if (bVar != null) {
            bVar.a();
        }
        this.u = null;
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.i) {
            c(this.r, canvas);
        }
        d(this.p, canvas);
        a(this.s, canvas);
        b(this.q, canvas);
    }

    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        float f2 = (float) (i2 >> 1);
        this.f2612a = f2;
        this.b = (float) (i3 >> 1);
        float f3 = this.d / 2.0f;
        this.e = f2 - f3;
        this.l = f2 - (this.k / 2.0f);
        this.h = (this.e - f3) + (this.g / 2.0f);
        b();
    }

    public void setArcLoadingColor(int i2) {
        this.m = i2;
        Paint paint = this.s;
        if (paint != null) {
            paint.setColor(i2);
            this.s.setStrokeWidth(this.n);
        }
    }

    public void setRoadColor(int i2) {
        this.c = i2;
        Paint paint = this.q;
        if (paint != null) {
            paint.setColor(i2);
            this.q.setStrokeWidth(this.d);
        }
    }
}
