package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class CircleBitmapDisplayer implements BitmapDisplayer {

    /* renamed from: a  reason: collision with root package name */
    protected final Integer f8484a;
    protected final float b;

    public CircleBitmapDisplayer() {
        this((Integer) null);
    }

    public CircleBitmapDisplayer(Integer num) {
        this(num, 0.0f);
    }

    public CircleBitmapDisplayer(Integer num, float f) {
        this.f8484a = num;
        this.b = f;
    }

    public void a(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (imageAware instanceof ImageViewAware) {
            imageAware.a((Drawable) new CircleDrawable(bitmap, this.f8484a, this.b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }

    public static class CircleDrawable extends Drawable {

        /* renamed from: a  reason: collision with root package name */
        protected float f8485a;
        protected final RectF b = new RectF();
        protected final RectF c;
        protected final BitmapShader d;
        protected final Paint e;
        protected final Paint f;
        protected final float g;
        protected float h;

        public int getOpacity() {
            return -3;
        }

        public CircleDrawable(Bitmap bitmap, Integer num, float f2) {
            this.f8485a = (float) (Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2);
            this.d = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.c = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setShader(this.d);
            this.e.setFilterBitmap(true);
            this.e.setDither(true);
            if (num == null) {
                this.f = null;
            } else {
                this.f = new Paint();
                this.f.setStyle(Paint.Style.STROKE);
                this.f.setColor(num.intValue());
                this.f.setStrokeWidth(f2);
                this.f.setAntiAlias(true);
            }
            this.g = f2;
            this.h = this.f8485a - (f2 / 2.0f);
        }

        /* access modifiers changed from: protected */
        public void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.b.set(0.0f, 0.0f, (float) rect.width(), (float) rect.height());
            this.f8485a = (float) (Math.min(rect.width(), rect.height()) / 2);
            this.h = this.f8485a - (this.g / 2.0f);
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.c, this.b, Matrix.ScaleToFit.FILL);
            this.d.setLocalMatrix(matrix);
        }

        public void draw(Canvas canvas) {
            canvas.drawCircle(this.f8485a, this.f8485a, this.f8485a, this.e);
            if (this.f != null) {
                canvas.drawCircle(this.f8485a, this.f8485a, this.h, this.f);
            }
        }

        public void setAlpha(int i) {
            this.e.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.e.setColorFilter(colorFilter);
        }
    }
}
