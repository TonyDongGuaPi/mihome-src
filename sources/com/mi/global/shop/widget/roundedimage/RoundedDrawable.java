package com.mi.global.shop.widget.roundedimage;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.widget.ImageView;

public class RoundedDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7281a = "RoundedDrawable";
    public static final int b = -16777216;
    private final RectF c = new RectF();
    private final RectF d = new RectF();
    private final RectF e = new RectF();
    private final BitmapShader f;
    private final Paint g;
    private final int h;
    private final int i;
    private final RectF j = new RectF();
    private final Paint k;
    private final Matrix l = new Matrix();
    private float m = 0.0f;
    private boolean n = false;
    private float o = 0.0f;
    private ColorStateList p = ColorStateList.valueOf(-16777216);
    private ImageView.ScaleType q = ImageView.ScaleType.FIT_CENTER;

    public int getOpacity() {
        return -3;
    }

    public RoundedDrawable(Bitmap bitmap) {
        this.h = bitmap.getWidth();
        this.i = bitmap.getHeight();
        this.e.set(0.0f, 0.0f, (float) this.h, (float) this.i);
        this.f = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.f.setLocalMatrix(this.l);
        this.g = new Paint();
        this.g.setStyle(Paint.Style.FILL);
        this.g.setAntiAlias(true);
        this.g.setShader(this.f);
        this.k = new Paint();
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setAntiAlias(true);
        this.k.setColor(this.p.getColorForState(getState(), -16777216));
        this.k.setStrokeWidth(this.o);
    }

    public static RoundedDrawable a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    public static Drawable a(Drawable drawable) {
        if (drawable == null || (drawable instanceof RoundedDrawable)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i2), a(layerDrawable.getDrawable(i2)));
            }
            return layerDrawable;
        }
        Bitmap b2 = b(drawable);
        if (b2 != null) {
            return new RoundedDrawable(b2);
        }
        Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
        return drawable;
    }

    public static Bitmap b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean isStateful() {
        return this.p.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int colorForState = this.p.getColorForState(iArr, 0);
        if (this.k.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.k.setColor(colorForState);
        return true;
    }

    /* renamed from: com.mi.global.shop.widget.roundedimage.RoundedDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7282a = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f7282a = r0
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x004b }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f7282a     // Catch:{ NoSuchFieldError -> 0x0056 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.roundedimage.RoundedDrawable.AnonymousClass1.<clinit>():void");
        }
    }

    private void h() {
        float f2;
        float f3;
        float f4;
        switch (AnonymousClass1.f7282a[this.q.ordinal()]) {
            case 1:
                this.j.set(this.c);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.reset();
                this.l.setTranslate((float) ((int) (((this.j.width() - ((float) this.h)) * 0.5f) + 0.5f)), (float) ((int) (((this.j.height() - ((float) this.i)) * 0.5f) + 0.5f)));
                break;
            case 2:
                this.j.set(this.c);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.reset();
                float f5 = 0.0f;
                if (((float) this.h) * this.j.height() > this.j.width() * ((float) this.i)) {
                    f3 = this.j.height() / ((float) this.i);
                    f5 = (this.j.width() - (((float) this.h) * f3)) * 0.5f;
                    f2 = 0.0f;
                } else {
                    f3 = this.j.width() / ((float) this.h);
                    f2 = (this.j.height() - (((float) this.i) * f3)) * 0.5f;
                }
                this.l.setScale(f3, f3);
                this.l.postTranslate(((float) ((int) (f5 + 0.5f))) + this.o, ((float) ((int) (f2 + 0.5f))) + this.o);
                break;
            case 3:
                this.l.reset();
                if (((float) this.h) > this.c.width() || ((float) this.i) > this.c.height()) {
                    f4 = Math.min(this.c.width() / ((float) this.h), this.c.height() / ((float) this.i));
                } else {
                    f4 = 1.0f;
                }
                this.l.setScale(f4, f4);
                this.l.postTranslate((float) ((int) (((this.c.width() - (((float) this.h) * f4)) * 0.5f) + 0.5f)), (float) ((int) (((this.c.height() - (((float) this.i) * f4)) * 0.5f) + 0.5f)));
                this.j.set(this.e);
                this.l.mapRect(this.j);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.END);
                this.l.mapRect(this.j);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.START);
                this.l.mapRect(this.j);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.j.set(this.c);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.reset();
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            default:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.CENTER);
                this.l.mapRect(this.j);
                this.j.inset(this.o / 2.0f, this.o / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
        }
        this.d.set(this.j);
        this.f.setLocalMatrix(this.l);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.c.set(rect);
        h();
    }

    public void draw(Canvas canvas) {
        if (this.n) {
            if (this.o > 0.0f) {
                canvas.drawOval(this.d, this.g);
                canvas.drawOval(this.j, this.k);
                return;
            }
            canvas.drawOval(this.d, this.g);
        } else if (this.o > 0.0f) {
            canvas.drawRoundRect(this.d, Math.max(this.m, 0.0f), Math.max(this.m, 0.0f), this.g);
            canvas.drawRoundRect(this.j, this.m, this.m, this.k);
        } else {
            canvas.drawRoundRect(this.d, this.m, this.m, this.g);
        }
    }

    public void setAlpha(int i2) {
        this.g.setAlpha(i2);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.g.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.g.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.g.setFilterBitmap(z);
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.h;
    }

    public int getIntrinsicHeight() {
        return this.i;
    }

    public float a() {
        return this.m;
    }

    public RoundedDrawable a(float f2) {
        this.m = f2;
        return this;
    }

    public float b() {
        return this.o;
    }

    public RoundedDrawable b(float f2) {
        this.o = f2;
        this.k.setStrokeWidth(this.o);
        return this;
    }

    public int c() {
        return this.p.getDefaultColor();
    }

    public RoundedDrawable a(int i2) {
        return a(ColorStateList.valueOf(i2));
    }

    public ColorStateList d() {
        return this.p;
    }

    public RoundedDrawable a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.p = colorStateList;
        this.k.setColor(this.p.getColorForState(getState(), -16777216));
        return this;
    }

    public boolean e() {
        return this.n;
    }

    public RoundedDrawable a(boolean z) {
        this.n = z;
        return this;
    }

    public ImageView.ScaleType f() {
        return this.q;
    }

    public RoundedDrawable a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.q != scaleType) {
            this.q = scaleType;
            h();
        }
        return this;
    }

    public Bitmap g() {
        return b((Drawable) this);
    }
}
