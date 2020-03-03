package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;

public class PieProgressBar extends ImageView {
    private final int MSG_INVALIDATE = 1;
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private boolean mOri = true;
    private RectF mOval;
    private float mPercent = 0.0f;
    private PieProgressBarAnim mProgressBarAnim;
    private Bitmap mProgressBmp;
    private PieProgressTxtAnim mProgressTxtAnim;
    /* access modifiers changed from: private */
    public TextView mTxtView = null;
    private Paint mXferPaint = new Paint(1);

    public PieProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mXferPaint.setStyle(Paint.Style.FILL);
        this.mXferPaint.setColor(-65536);
        this.mXferPaint.setXfermode(this.mMode);
        this.mOval = new RectF();
        this.mOval.left = 0.0f;
        this.mOval.top = 0.0f;
        this.mProgressBmp = ((BitmapDrawable) getDrawable()).getBitmap();
        this.mProgressBarAnim = new PieProgressBarAnim();
        this.mProgressBarAnim.setInterpolator(new LinearInterpolator());
        this.mProgressTxtAnim = new PieProgressTxtAnim();
    }

    public void setPercent(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 100.0f) {
            f = 100.0f;
        }
        this.mPercent = f;
        if (this.mTxtView != null) {
            TextView textView = this.mTxtView;
            textView.setText(((int) this.mPercent) + Operators.MOD);
        }
        invalidate();
    }

    public float getPercent() {
        return this.mPercent;
    }

    public float getPercentAnim() {
        return (float) this.mProgressBarAnim.d;
    }

    public void setFromPercent(int i) {
        this.mProgressBarAnim.a(i);
        this.mProgressTxtAnim.a(i);
    }

    public void setToPercent(int i) {
        this.mProgressBarAnim.b(i);
        this.mProgressTxtAnim.b(i);
    }

    public void setDuration(long j) {
        this.mProgressBarAnim.setDuration(j);
        this.mProgressTxtAnim.setDuration((j * 1) / 2);
    }

    public void startPercentAnim() {
        startAnimation(this.mProgressBarAnim);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mProgressBarAnim.setInterpolator(interpolator);
    }

    public void setInterpolator(Context context, int i) {
        this.mProgressBarAnim.setInterpolator(context, i);
    }

    public void setOri(boolean z) {
        this.mOri = z;
    }

    public void setPercentView(TextView textView) {
        if (textView != null) {
            this.mTxtView = textView;
            TextView textView2 = this.mTxtView;
            textView2.setText(((int) this.mPercent) + Operators.MOD);
        }
    }

    public void stopPercentAnim() {
        this.mProgressBarAnim.cancel();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int saveLayer;
        if (isInEditMode()) {
            super.onDraw(canvas);
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) (getWidth() + 0), (float) (getHeight() + 0), (Paint) null);
        } else {
            saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) (getWidth() + 0), (float) (getHeight() + 0), (Paint) null, 31);
        }
        int i = saveLayer;
        this.mOval.right = (float) getWidth();
        this.mOval.bottom = (float) getHeight();
        this.mXferPaint.setXfermode((Xfermode) null);
        if (this.mOri) {
            canvas.drawArc(this.mOval, -90.0f, (this.mPercent * 360.0f) / 100.0f, true, this.mXferPaint);
        } else {
            canvas.drawArc(this.mOval, -90.0f, (this.mPercent * -360.0f) / 100.0f, true, this.mXferPaint);
        }
        this.mXferPaint.setXfermode(this.mMode);
        canvas.drawBitmap(this.mProgressBmp, new Rect(0, 0, this.mProgressBmp.getWidth(), this.mProgressBmp.getHeight()), new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), this.mXferPaint);
        canvas.restoreToCount(i);
    }

    public class PieProgressTxtAnim extends Animation {
        private int b = 0;
        private int c = 0;
        private int d = 0;

        public PieProgressTxtAnim() {
        }

        public void a(int i) {
            if (i < 1) {
                i = 1;
            } else if (i > 100) {
                i = 100;
            }
            this.b = i;
            this.d = i;
        }

        public void b(int i) {
            if (i < 1) {
                i = 1;
            } else if (i > 100) {
                i = 100;
            }
            this.c = i;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            int i = (int) (((float) this.b) + (((float) (this.c - this.b)) * f));
            if (this.d != i) {
                this.d = i;
                if (PieProgressBar.this.mTxtView != null) {
                    TextView access$100 = PieProgressBar.this.mTxtView;
                    access$100.setText(i + Operators.MOD);
                }
                Log.e("PieProgressBar", "" + this.d);
            }
        }
    }

    public class PieProgressBarAnim extends Animation {
        private int b = 0;
        private int c = 0;
        /* access modifiers changed from: private */
        public int d = 0;

        public PieProgressBarAnim() {
        }

        public void a(int i) {
            if (i < 1) {
                i = 1;
            } else if (i > 100) {
                i = 100;
            }
            this.b = i;
            this.d = i;
            PieProgressBar.this.setPercent((float) i);
        }

        public void b(int i) {
            if (i < 1) {
                i = 1;
            } else if (i > 100) {
                i = 100;
            }
            this.c = i;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            int i = (int) (((float) this.b) + (((float) (this.c - this.b)) * f));
            if (this.d != i) {
                this.d = i;
                PieProgressBar.this.setPercent((float) i);
            }
        }
    }
}
