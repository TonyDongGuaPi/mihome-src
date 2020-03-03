package com.xiaomi.smarthome.library.common.widget;

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
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import java.util.HashSet;

public class RoundedDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18922a = "RoundedDrawable";
    public static final int b = -16777216;
    private final RectF c = new RectF();
    private final RectF d = new RectF();
    private final RectF e = new RectF();
    private final Bitmap f;
    private final Paint g;
    private final int h;
    private final int i;
    private final RectF j = new RectF();
    private final Paint k;
    private final Matrix l = new Matrix();
    private final RectF m = new RectF();
    private Shader.TileMode n = Shader.TileMode.CLAMP;
    private Shader.TileMode o = Shader.TileMode.CLAMP;
    private boolean p = true;
    private float q = 0.0f;
    private final boolean[] r = {true, true, true, true};
    private boolean s = false;
    private float t = 0.0f;
    private ColorStateList u = ColorStateList.valueOf(-16777216);
    private ImageView.ScaleType v = ImageView.ScaleType.FIT_CENTER;

    public int getOpacity() {
        return -3;
    }

    public RoundedDrawable(Bitmap bitmap) {
        this.f = bitmap;
        this.h = bitmap.getWidth();
        this.i = bitmap.getHeight();
        this.e.set(0.0f, 0.0f, (float) this.h, (float) this.i);
        this.g = new Paint();
        this.g.setStyle(Paint.Style.FILL);
        this.g.setAntiAlias(true);
        this.k = new Paint();
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setAntiAlias(true);
        this.k.setColor(this.u.getColorForState(getState(), -16777216));
        this.k.setStrokeWidth(this.t);
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
            Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
            return null;
        }
    }

    public Bitmap a() {
        return this.f;
    }

    public boolean isStateful() {
        return this.u.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int colorForState = this.u.getColorForState(iArr, 0);
        if (this.k.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.k.setColor(colorForState);
        return true;
    }

    /* renamed from: com.xiaomi.smarthome.library.common.widget.RoundedDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f18923a = new int[ImageView.ScaleType.values().length];

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
                f18923a = r0
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x004b }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f18923a     // Catch:{ NoSuchFieldError -> 0x0056 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.RoundedDrawable.AnonymousClass1.<clinit>():void");
        }
    }

    private void k() {
        float f2;
        float f3;
        float f4;
        switch (AnonymousClass1.f18923a[this.v.ordinal()]) {
            case 1:
                this.j.set(this.c);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.reset();
                this.l.setTranslate((float) ((int) (((this.j.width() - ((float) this.h)) * 0.5f) + 0.5f)), (float) ((int) (((this.j.height() - ((float) this.i)) * 0.5f) + 0.5f)));
                break;
            case 2:
                this.j.set(this.c);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
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
                this.l.postTranslate(((float) ((int) (f5 + 0.5f))) + (this.t / 2.0f), ((float) ((int) (f2 + 0.5f))) + (this.t / 2.0f));
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
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.END);
                this.l.mapRect(this.j);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.START);
                this.l.mapRect(this.j);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.j.set(this.c);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.reset();
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
            default:
                this.j.set(this.e);
                this.l.setRectToRect(this.e, this.c, Matrix.ScaleToFit.CENTER);
                this.l.mapRect(this.j);
                this.j.inset(this.t / 2.0f, this.t / 2.0f);
                this.l.setRectToRect(this.e, this.j, Matrix.ScaleToFit.FILL);
                break;
        }
        this.d.set(this.j);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(@NonNull Rect rect) {
        super.onBoundsChange(rect);
        this.c.set(rect);
        k();
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.p) {
            BitmapShader bitmapShader = new BitmapShader(this.f, this.n, this.o);
            if (this.n == Shader.TileMode.CLAMP && this.o == Shader.TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.l);
            }
            this.g.setShader(bitmapShader);
            this.p = false;
        }
        if (this.s) {
            if (this.t > 0.0f) {
                canvas.drawOval(this.d, this.g);
                canvas.drawOval(this.j, this.k);
                return;
            }
            canvas.drawOval(this.d, this.g);
        } else if (a(this.r)) {
            float f2 = this.q;
            if (this.t > 0.0f) {
                canvas.drawRoundRect(this.d, f2, f2, this.g);
                canvas.drawRoundRect(this.j, f2, f2, this.k);
                a(canvas);
                b(canvas);
                return;
            }
            canvas.drawRoundRect(this.d, f2, f2, this.g);
            a(canvas);
        } else {
            canvas.drawRect(this.d, this.g);
            if (this.t > 0.0f) {
                canvas.drawRect(this.j, this.k);
            }
        }
    }

    private void a(Canvas canvas) {
        if (!b(this.r) && this.q != 0.0f) {
            float f2 = this.d.left;
            float f3 = this.d.top;
            float width = this.d.width() + f2;
            float height = this.d.height() + f3;
            float f4 = this.q;
            if (!this.r[0]) {
                this.m.set(f2, f3, f2 + f4, f3 + f4);
                canvas.drawRect(this.m, this.g);
            }
            if (!this.r[1]) {
                this.m.set(width - f4, f3, width, f4);
                canvas.drawRect(this.m, this.g);
            }
            if (!this.r[2]) {
                this.m.set(width - f4, height - f4, width, height);
                canvas.drawRect(this.m, this.g);
            }
            if (!this.r[3]) {
                this.m.set(f2, height - f4, f4 + f2, height);
                canvas.drawRect(this.m, this.g);
            }
        }
    }

    private void b(Canvas canvas) {
        if (!b(this.r) && this.q != 0.0f) {
            float f2 = this.d.left;
            float f3 = this.d.top;
            float width = f2 + this.d.width();
            float height = f3 + this.d.height();
            float f4 = this.q;
            float f5 = this.t / 2.0f;
            if (!this.r[0]) {
                canvas.drawLine(f2 - f5, f3, f2 + f4, f3, this.k);
                canvas.drawLine(f2, f3 - f5, f2, f3 + f4, this.k);
            }
            if (!this.r[1]) {
                Canvas canvas2 = canvas;
                float f6 = width;
                canvas2.drawLine((width - f4) - f5, f3, f6, f3, this.k);
                canvas2.drawLine(width, f3 - f5, f6, f3 + f4, this.k);
            }
            if (!this.r[2]) {
                Canvas canvas3 = canvas;
                float f7 = height;
                canvas3.drawLine((width - f4) - f5, height, width + f5, f7, this.k);
                canvas3.drawLine(width, height - f4, width, f7, this.k);
            }
            if (!this.r[3]) {
                canvas.drawLine(f2 - f5, height, f2 + f4, height, this.k);
                canvas.drawLine(f2, height - f4, f2, height, this.k);
            }
        }
    }

    public int getAlpha() {
        return this.g.getAlpha();
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

    public float b() {
        return this.q;
    }

    public float a(int i2) {
        if (this.r[i2]) {
            return this.q;
        }
        return 0.0f;
    }

    public RoundedDrawable a(float f2) {
        a(f2, f2, f2, f2);
        return this;
    }

    public RoundedDrawable a(int i2, float f2) {
        if (f2 == 0.0f || this.q == 0.0f || this.q == f2) {
            if (f2 == 0.0f) {
                if (a(i2, this.r)) {
                    this.q = 0.0f;
                }
                this.r[i2] = false;
            } else {
                if (this.q == 0.0f) {
                    this.q = f2;
                }
                this.r[i2] = true;
            }
            return this;
        }
        throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
    }

    public RoundedDrawable a(float f2, float f3, float f4, float f5) {
        HashSet hashSet = new HashSet(4);
        hashSet.add(Float.valueOf(f2));
        hashSet.add(Float.valueOf(f3));
        hashSet.add(Float.valueOf(f4));
        hashSet.add(Float.valueOf(f5));
        hashSet.remove(Float.valueOf(0.0f));
        if (hashSet.size() <= 1) {
            if (!hashSet.isEmpty()) {
                float floatValue = ((Float) hashSet.iterator().next()).floatValue();
                if (Float.isInfinite(floatValue) || Float.isNaN(floatValue) || floatValue < 0.0f) {
                    throw new IllegalArgumentException("Invalid radius value: " + floatValue);
                }
                this.q = floatValue;
            } else {
                this.q = 0.0f;
            }
            boolean z = false;
            this.r[0] = f2 > 0.0f;
            this.r[1] = f3 > 0.0f;
            this.r[2] = f4 > 0.0f;
            boolean[] zArr = this.r;
            if (f5 > 0.0f) {
                z = true;
            }
            zArr[3] = z;
            return this;
        }
        throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
    }

    public float c() {
        return this.t;
    }

    public RoundedDrawable b(float f2) {
        this.t = f2;
        this.k.setStrokeWidth(this.t);
        return this;
    }

    public int d() {
        return this.u.getDefaultColor();
    }

    public RoundedDrawable b(@ColorInt int i2) {
        return a(ColorStateList.valueOf(i2));
    }

    public ColorStateList e() {
        return this.u;
    }

    public RoundedDrawable a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.u = colorStateList;
        this.k.setColor(this.u.getColorForState(getState(), -16777216));
        return this;
    }

    public boolean f() {
        return this.s;
    }

    public RoundedDrawable a(boolean z) {
        this.s = z;
        return this;
    }

    public ImageView.ScaleType g() {
        return this.v;
    }

    public RoundedDrawable a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.v != scaleType) {
            this.v = scaleType;
            k();
        }
        return this;
    }

    public Shader.TileMode h() {
        return this.n;
    }

    public RoundedDrawable a(Shader.TileMode tileMode) {
        if (this.n != tileMode) {
            this.n = tileMode;
            this.p = true;
            invalidateSelf();
        }
        return this;
    }

    public Shader.TileMode i() {
        return this.o;
    }

    public RoundedDrawable b(Shader.TileMode tileMode) {
        if (this.o != tileMode) {
            this.o = tileMode;
            this.p = true;
            invalidateSelf();
        }
        return this;
    }

    private static boolean a(int i2, boolean[] zArr) {
        int length = zArr.length;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= length) {
                return true;
            }
            boolean z2 = zArr[i3];
            if (i3 != i2) {
                z = false;
            }
            if (z2 != z) {
                return false;
            }
            i3++;
        }
    }

    private static boolean a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return false;
            }
        }
        return true;
    }

    public Bitmap j() {
        return b((Drawable) this);
    }
}
