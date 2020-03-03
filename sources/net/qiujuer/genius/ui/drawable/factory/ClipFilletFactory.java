package net.qiujuer.genius.ui.drawable.factory;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import net.qiujuer.genius.ui.drawable.TouchEffectDrawable;

public class ClipFilletFactory extends TouchEffectDrawable.ClipFactory {

    /* renamed from: a  reason: collision with root package name */
    private Path f3140a;
    private float[] b;

    public ClipFilletFactory(float f) {
        a(f);
    }

    public ClipFilletFactory(float[] fArr) {
        a(fArr);
    }

    public void a(float f) {
        this.b = new float[]{f, f, f, f, f, f, f, f};
    }

    public void a(float[] fArr) {
        if (fArr == null || fArr.length < 8) {
            throw new ArrayIndexOutOfBoundsException("radii must have >= 8 values");
        }
        this.b = fArr;
    }

    public void a(int i, int i2) {
        if (this.b == null) {
            this.f3140a = null;
            return;
        }
        if (this.f3140a == null) {
            this.f3140a = new Path();
        } else {
            this.f3140a.reset();
        }
        if (Build.VERSION.SDK_INT < 21) {
            this.f3140a.addRoundRect(new RectF(0.0f, 0.0f, (float) i, (float) i2), this.b, Path.Direction.CW);
        } else {
            this.f3140a.addRoundRect(0.0f, 0.0f, (float) i, (float) i2, this.b, Path.Direction.CW);
        }
    }

    public boolean a(Canvas canvas) {
        return canvas.clipPath(this.f3140a);
    }
}
