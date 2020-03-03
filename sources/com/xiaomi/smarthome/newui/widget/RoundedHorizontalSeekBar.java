package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.xiaomi.smarthome.miotcard.R;

public class RoundedHorizontalSeekBar extends AppCompatSeekBar {

    /* renamed from: a  reason: collision with root package name */
    private ProgressDrawable f20885a;
    private Bitmap b;
    private CharSequence c;
    private float d;
    private Paint e;
    private boolean f;
    private PorterDuffXfermode g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;

    public RoundedHorizontalSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoundedHorizontalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedHorizontalSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = true;
        this.g = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.h = true;
        setProgressDrawable((Drawable) null);
        a(context, attributeSet, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        this.f20885a = new ProgressDrawable();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedHorizontalSeekBar, i2, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.RoundedHorizontalSeekBar_progress_color_my, getResources().getColor(R.color.seekbar_progress_blue));
        int color2 = obtainStyledAttributes.getColor(R.styleable.RoundedHorizontalSeekBar_bg_color_my, getResources().getColor(R.color.seekbar_bg));
        this.i = color2;
        this.j = color;
        this.m = this.i;
        this.n = this.j;
        this.k = getResources().getColor(R.color.seekbar_bg_unable);
        this.l = getResources().getColor(R.color.seekbar_progress_unable);
        this.o = this.k;
        this.p = this.l;
        this.f20885a.b(color);
        this.f20885a.a(color2);
        this.f = obtainStyledAttributes.getBoolean(R.styleable.RoundedHorizontalSeekBar_seek_enable, true);
        if (obtainStyledAttributes.hasValue(R.styleable.RoundedHorizontalSeekBar_img_icon_my)) {
            setImageIcon(obtainStyledAttributes.getResourceId(R.styleable.RoundedHorizontalSeekBar_img_icon_my, R.drawable.ic_launcher));
            this.d = obtainStyledAttributes.getDimension(R.styleable.RoundedHorizontalSeekBar_icon_padding_left_my, 0.0f);
        } else {
            CharSequence text = obtainStyledAttributes.getText(R.styleable.RoundedHorizontalSeekBar_text_my);
            if (text == null) {
                text = "";
            }
            this.d = obtainStyledAttributes.getDimension(R.styleable.RoundedHorizontalSeekBar_text_padding_left_my, 0.0f);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.RoundedHorizontalSeekBar_text_size_my, 20.0f);
            int color3 = obtainStyledAttributes.getColor(R.styleable.RoundedHorizontalSeekBar_text_color_my, getResources().getColor(17170443));
            setTextInfo(text);
            this.e = new Paint(1);
            this.e.setTextSize(dimension);
            this.e.setTextAlign(Paint.Align.LEFT);
            this.e.setColor(color3);
        }
        this.f20885a.a(0, getMax());
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.f20885a.c(getProgress());
        this.f20885a.draw(canvas);
        a(canvas);
        super.onDraw(canvas);
    }

    private void a(Canvas canvas) {
        if (this.b != null) {
            canvas.drawBitmap(this.b, this.d, (float) ((getHeight() - this.b.getHeight()) / 2), (Paint) null);
        } else if (this.c != null) {
            Paint.FontMetricsInt fontMetricsInt = this.e.getFontMetricsInt();
            int i2 = fontMetricsInt.bottom - fontMetricsInt.top;
            int height = (getHeight() - i2) / 2;
            Rect rect = new Rect((int) this.d, height, (int) this.e.measureText(this.c.toString()), i2 + height);
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            this.e.setColor(getTextColor());
            canvas2.drawText(this.c.toString(), this.d, (float) ((((rect.bottom + rect.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2), this.e);
            this.e.setXfermode(this.g);
            this.e.setColor(getTextProcessedColor());
            canvas2.drawRect(new RectF(0.0f, 0.0f, (float) ((int) (((float) (getWidth() * getProgress())) / 100.0f)), (float) getHeight()), this.e);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
            this.e.setXfermode((Xfermode) null);
            if (!createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
        }
    }

    private int getTextColor() {
        if (this.h) {
            return this.n;
        }
        return this.p;
    }

    private int getTextProcessedColor() {
        if (this.h) {
            return this.m;
        }
        return this.o;
    }

    public void setImageIcon(@DrawableRes int i2) {
        this.b = a(i2);
        this.c = null;
    }

    public void setImageIcon(Bitmap bitmap) {
        this.b = bitmap;
        this.c = null;
    }

    public void setTextInfo(CharSequence charSequence) {
        this.c = charSequence;
        this.b = null;
    }

    public void updateEnableUI(boolean z) {
        this.h = z;
        if (z) {
            this.f20885a.b(this.j);
            this.f20885a.a(this.i);
        } else {
            this.f20885a.b(this.l);
            this.f20885a.a(this.k);
        }
        invalidate();
    }

    public void setCanSeek(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f20885a.setBounds(new Rect(0, 0, i2, i3));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.b != null && !this.b.isRecycled()) {
            this.b.recycle();
        }
        super.onDetachedFromWindow();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private Bitmap a(int i2) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i2);
        if (decodeResource != null) {
            return Bitmap.createScaledBitmap(decodeResource, decodeResource.getWidth(), decodeResource.getHeight(), true);
        }
        Log.e("Seekbar", "icon==null");
        return decodeResource;
    }
}
