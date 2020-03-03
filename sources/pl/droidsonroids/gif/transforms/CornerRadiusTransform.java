package pl.droidsonroids.gif.transforms;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

public class CornerRadiusTransform implements Transform {

    /* renamed from: a  reason: collision with root package name */
    private float f11978a;
    private Shader b;
    private final RectF c = new RectF();

    public CornerRadiusTransform(@FloatRange(from = 0.0d) float f) {
        b(f);
    }

    public void a(@FloatRange(from = 0.0d) float f) {
        b(f);
    }

    private void b(@FloatRange(from = 0.0d) float f) {
        float max = Math.max(0.0f, f);
        if (max != this.f11978a) {
            this.f11978a = max;
            this.b = null;
        }
    }

    @FloatRange(from = 0.0d)
    public float a() {
        return this.f11978a;
    }

    @NonNull
    public RectF b() {
        return this.c;
    }

    public void a(Rect rect) {
        this.c.set(rect);
        this.b = null;
    }

    public void a(Canvas canvas, Paint paint, Bitmap bitmap) {
        if (this.f11978a == 0.0f) {
            canvas.drawBitmap(bitmap, (Rect) null, this.c, paint);
            return;
        }
        if (this.b == null) {
            this.b = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            matrix.setTranslate(this.c.left, this.c.top);
            matrix.preScale(this.c.width() / ((float) bitmap.getWidth()), this.c.height() / ((float) bitmap.getHeight()));
            this.b.setLocalMatrix(matrix);
        }
        paint.setShader(this.b);
        canvas.drawRoundRect(this.c, this.f11978a, this.f11978a, paint);
    }
}
