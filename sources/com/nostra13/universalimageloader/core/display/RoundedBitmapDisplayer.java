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

public class RoundedBitmapDisplayer implements BitmapDisplayer {

    /* renamed from: a  reason: collision with root package name */
    protected final int f8487a;
    protected final int b;

    public RoundedBitmapDisplayer(int i) {
        this(i, 0);
    }

    public RoundedBitmapDisplayer(int i, int i2) {
        this.f8487a = i;
        this.b = i2;
    }

    public void a(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (imageAware instanceof ImageViewAware) {
            imageAware.a((Drawable) new RoundedDrawable(bitmap, this.f8487a, this.b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }

    public static class RoundedDrawable extends Drawable {

        /* renamed from: a  reason: collision with root package name */
        protected final float f8488a;
        protected final int b;
        protected final RectF c = new RectF();
        protected final RectF d;
        protected final BitmapShader e;
        protected final Paint f;

        public int getOpacity() {
            return -3;
        }

        public RoundedDrawable(Bitmap bitmap, int i, int i2) {
            this.f8488a = (float) i;
            this.b = i2;
            this.e = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            float f2 = (float) i2;
            this.d = new RectF(f2, f2, (float) (bitmap.getWidth() - i2), (float) (bitmap.getHeight() - i2));
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setShader(this.e);
            this.f.setFilterBitmap(true);
            this.f.setDither(true);
        }

        /* access modifiers changed from: protected */
        public void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.c.set((float) this.b, (float) this.b, (float) (rect.width() - this.b), (float) (rect.height() - this.b));
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.d, this.c, Matrix.ScaleToFit.FILL);
            this.e.setLocalMatrix(matrix);
        }

        public void draw(Canvas canvas) {
            canvas.drawRoundRect(this.c, this.f8488a, this.f8488a, this.f);
        }

        public void setAlpha(int i) {
            this.f.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.f.setColorFilter(colorFilter);
        }
    }
}
