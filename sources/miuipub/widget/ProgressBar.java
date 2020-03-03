package miuipub.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RemoteViews;
import com.miuipub.internal.variable.Android_Graphics_Drawable_AnimatedRotateDrawable_class;
import miuipub.util.ViewUtils;
import miuipub.v6.R;

@RemoteViews.RemoteView
public class ProgressBar extends android.widget.ProgressBar {
    static final String TAG = "ProgressBar";

    /* renamed from: a  reason: collision with root package name */
    private static final Android_Graphics_Drawable_AnimatedRotateDrawable_class f3104a = Android_Graphics_Drawable_AnimatedRotateDrawable_class.Factory.getInstance().get();
    private boolean b;
    private Drawable c;
    private Drawable d;
    private boolean e;
    private Drawable f;
    private boolean g;

    public ProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public ProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842871);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ProgressBar, i, 0);
        Drawable drawable = this.f;
        if (drawable != null && drawable.getClass().getName().equals(Android_Graphics_Drawable_AnimatedRotateDrawable_class.NAME)) {
            f3104a.setFramesCount(drawable, obtainStyledAttributes.getInt(R.styleable.V6_ProgressBar_v6_indeterminateFramesCount, 48));
            f3104a.setFramesDuration(drawable, obtainStyledAttributes.getInt(R.styleable.V6_ProgressBar_v6_indeterminateFramesDuration, 25));
        }
        this.b = true;
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.V6_ProgressBar_v6_progressMask);
        if (drawable2 != null) {
            a(drawable2);
            setProgressMaskDrawable(drawable2);
        } else {
            a();
        }
        obtainStyledAttributes.recycle();
    }

    public synchronized void setIndeterminate(boolean z) {
        boolean isIndeterminate = isIndeterminate();
        super.setIndeterminate(z);
        if (isIndeterminate != isIndeterminate()) {
            a();
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        if (this.d != drawable) {
            this.d = drawable;
            this.e = true;
            a();
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        if (this.f != drawable) {
            this.f = drawable;
            this.g = true;
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.e = true;
        this.g = true;
        a();
        super.onSizeChanged(i, i2, i3, i4);
    }

    public synchronized void setProgressMaskDrawable(Drawable drawable) {
        if (this.c != drawable) {
            this.c = drawable;
            this.e = true;
            this.g = true;
            a();
        }
    }

    public Drawable getProgressMaskDrawable() {
        return this.c;
    }

    private void a() {
        int i;
        int i2;
        if (this.b) {
            boolean isIndeterminate = isIndeterminate();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            if (height < width) {
                i = height;
                i2 = width;
            } else {
                i2 = height;
                i = width;
            }
            if (this.c != null) {
                this.c.setBounds(0, 0, i2, i);
            }
            if (isIndeterminate && this.g) {
                this.g = true;
                super.setIndeterminateDrawable(ViewUtils.a(getResources(), this.f, this.c, i2, i, true));
            } else if (!isIndeterminate && this.e) {
                this.e = true;
                super.setProgressDrawable(ViewUtils.a(getResources(), this.d, this.c, i2, i, false));
            }
        }
    }

    private void a(Drawable drawable) {
        Paint paint;
        if (drawable instanceof NinePatchDrawable) {
            paint = ((NinePatchDrawable) drawable).getPaint();
        } else {
            paint = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getPaint() : null;
        }
        if (paint != null) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            return;
        }
        Log.w(TAG, "The drawable should be NinePatchDrawable or BitmapDrawable. drawable=" + drawable);
    }
}
