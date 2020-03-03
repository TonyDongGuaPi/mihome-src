package miuipub.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

public class CircleProgressBar extends ProgressBar {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3068a = 10;
    private static final int b = 300;
    private static final int c = 300;
    private RectF d;
    private Animator e;
    private int[] f;
    private Drawable[] g;
    private Drawable[] h;
    private Drawable[] i;
    private OnProgressChangedListener j;
    private int k;
    private Bitmap l;
    private Canvas m;
    private Paint n;
    private int o;
    private int p;
    private int q;
    private Drawable r;

    public interface OnProgressChangedListener {
        void a();
    }

    public CircleProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.q = 300;
        setIndeterminate(false);
        this.n = new Paint();
        this.n.setColor(-16777216);
    }

    public void setProgressLevels(int[] iArr) {
        this.f = iArr;
    }

    public int getProgressLevelCount() {
        if (this.f == null) {
            return 1;
        }
        return 1 + this.f.length;
    }

    public void setDrawablesForLevels(Drawable[] drawableArr, Drawable[] drawableArr2, Drawable[] drawableArr3) {
        this.g = drawableArr;
        this.h = drawableArr2;
        this.i = drawableArr3;
        if (drawableArr != null) {
            for (Drawable mutate : drawableArr) {
                mutate.mutate();
            }
        }
        if (drawableArr2 != null) {
            for (Drawable mutate2 : drawableArr2) {
                mutate2.mutate();
            }
        }
        if (drawableArr3 != null) {
            for (Drawable mutate3 : drawableArr3) {
                mutate3.mutate();
            }
        }
        for (BitmapDrawable bitmapDrawable : drawableArr2) {
            if (bitmapDrawable instanceof BitmapDrawable) {
                bitmapDrawable.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            } else if (bitmapDrawable instanceof NinePatchDrawable) {
                ((NinePatchDrawable) bitmapDrawable).getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            } else {
                throw new IllegalArgumentException("'middles' must a bitmap or nine patch drawable.");
            }
        }
        this.d = new RectF((float) (drawableArr2[0].getBounds().left - 5), (float) (drawableArr2[0].getBounds().top - 5), (float) (drawableArr2[0].getBounds().right + 5), (float) (drawableArr2[0].getBounds().bottom + 5));
    }

    public void setDrawablesForLevels(int[] iArr, int[] iArr2, int[] iArr3) {
        setDrawablesForLevels(a(iArr), a(iArr2), a(iArr3));
    }

    private Drawable[] a(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        Resources resources = getContext().getResources();
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            drawableArr[i2] = resources.getDrawable(iArr[i2]);
            drawableArr[i2].setBounds(0, 0, drawableArr[i2].getIntrinsicWidth(), drawableArr[i2].getIntrinsicHeight());
        }
        return drawableArr;
    }

    private Drawable a(int i2) {
        if (this.g == null) {
            return null;
        }
        return this.g[i2];
    }

    private Drawable b(int i2) {
        if (this.h == null) {
            return null;
        }
        return this.h[i2];
    }

    private Drawable c(int i2) {
        if (this.i == null) {
            return null;
        }
        return this.i[i2];
    }

    public void setRotateVelocity(int i2) {
        this.q = i2;
    }

    public void setProgressByAnimator(int i2) {
        setProgressByAnimator(i2, (Animator.AnimatorListener) null);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.j = onProgressChangedListener;
    }

    public void setProgressByAnimator(int i2, Animator.AnimatorListener animatorListener) {
        stopProgressAnimator();
        int abs = Math.abs((int) ((((float) (i2 - getProgress())) / ((float) getMax())) * 360.0f));
        this.e = ObjectAnimator.ofInt(this, NotificationCompat.CATEGORY_PROGRESS, new int[]{i2});
        this.e.setDuration((long) d(abs));
        this.e.setInterpolator(getInterpolator());
        if (animatorListener != null) {
            this.e.addListener(animatorListener);
        }
        this.e.start();
    }

    public void stopProgressAnimator() {
        if (this.e != null && this.e.isRunning()) {
            this.e.cancel();
        }
    }

    private int d(int i2) {
        return (i2 * 1000) / this.q;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int progressLevelCount = getProgressLevelCount();
        for (int i2 = 0; i2 < progressLevelCount; i2++) {
            if (this.g != null) {
                this.g[i2].setState(getDrawableState());
            }
            if (this.h != null) {
                this.h[i2].setState(getDrawableState());
            }
            if (this.i != null) {
                this.i[i2].setState(getDrawableState());
            }
        }
        invalidate();
    }

    public synchronized void setProgress(int i2) {
        int i3;
        super.setProgress(i2);
        if (this.f == null) {
            i3 = 0;
        } else {
            i3 = this.f.length;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    i4 = -1;
                    break;
                } else if (i2 < this.f[i4]) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 != -1) {
                i3 = i4;
            }
        }
        if (i3 != this.k) {
            this.o = this.k;
            this.k = i3;
            setPrevAlpha(255);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "prevAlpha", new int[]{0});
            ofInt.setDuration(300);
            ofInt.setInterpolator(new LinearInterpolator());
            ofInt.start();
        }
        if (this.j != null) {
            this.j.a();
        }
    }

    private float getRate() {
        return ((float) getProgress()) / ((float) getMax());
    }

    private int getIntrinsicWidth() {
        int intrinsicWidth = b(0).getIntrinsicWidth();
        if (this.i != null) {
            intrinsicWidth = Math.max(intrinsicWidth, this.i[0].getIntrinsicWidth());
        }
        return this.g != null ? Math.max(intrinsicWidth, this.g[0].getIntrinsicWidth()) : intrinsicWidth;
    }

    private int getIntrinsicHeight() {
        int intrinsicHeight = b(0).getIntrinsicHeight();
        if (this.i != null) {
            intrinsicHeight = Math.max(intrinsicHeight, this.i[0].getIntrinsicHeight());
        }
        return this.g != null ? Math.max(intrinsicHeight, this.g[0].getIntrinsicHeight()) : intrinsicHeight;
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i2, int i3) {
        setMeasuredDimension(getIntrinsicWidth(), getIntrinsicHeight());
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        a(canvas, a(this.k), c(this.k), b(this.k), getRate(), 255 - this.p);
        if (this.p >= 10) {
            a(canvas, a(this.o), c(this.o), b(this.o), getRate(), this.p);
        }
    }

    private void a(Canvas canvas, Drawable drawable, Drawable drawable2, Drawable drawable3, float f2, int i2) {
        Canvas canvas2 = canvas;
        Drawable drawable4 = drawable;
        Drawable drawable5 = drawable2;
        Drawable drawable6 = drawable3;
        int i3 = i2;
        if (drawable4 != null) {
            drawable4.setAlpha(i3);
            drawable4.draw(canvas2);
        }
        if (canvas.isHardwareAccelerated()) {
            Canvas canvas3 = canvas;
            canvas3.saveLayer((float) drawable3.getBounds().left, (float) drawable3.getBounds().top, (float) drawable3.getBounds().right, (float) drawable3.getBounds().bottom, (Paint) null, 16);
            canvas3.drawArc(this.d, -90.0f, f2 * 360.0f, true, this.n);
            drawable6.setAlpha(i3);
            drawable6.draw(canvas2);
            canvas.restore();
        } else {
            if (this.l == null) {
                this.l = Bitmap.createBitmap(drawable3.getBounds().width(), drawable3.getBounds().height(), Bitmap.Config.ARGB_8888);
                this.m = new Canvas(this.l);
            }
            this.l.eraseColor(0);
            this.m.save();
            this.m.translate((float) (-drawable3.getBounds().left), (float) (-drawable3.getBounds().top));
            this.m.drawArc(this.d, -90.0f, f2 * 360.0f, true, this.n);
            drawable6.setAlpha(i3);
            drawable6.draw(this.m);
            this.m.restore();
            canvas2.drawBitmap(this.l, (float) drawable3.getBounds().left, (float) drawable3.getBounds().top, (Paint) null);
        }
        Drawable drawable7 = this.r;
        if (drawable7 != null) {
            canvas.save();
            int width = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2;
            int height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2;
            int intrinsicWidth = drawable7.getIntrinsicWidth();
            int intrinsicHeight = drawable7.getIntrinsicHeight();
            canvas2.rotate((((float) getProgress()) * 360.0f) / ((float) getMax()), (float) width, (float) height);
            int i4 = intrinsicWidth / 2;
            int i5 = intrinsicHeight / 2;
            drawable7.setBounds(width - i4, height - i5, width + i4, height + i5);
            drawable7.draw(canvas2);
            canvas.restore();
        }
        if (drawable5 != null) {
            drawable5.setAlpha(i3);
            drawable5.draw(canvas2);
        }
    }

    public void setPrevAlpha(int i2) {
        this.p = i2;
        invalidate();
    }

    public int getPrevAlpha() {
        return this.p;
    }

    public void setThumb(int i2) {
        setThumb(getResources().getDrawable(i2));
    }

    public void setThumb(Drawable drawable) {
        this.r = drawable;
    }
}
