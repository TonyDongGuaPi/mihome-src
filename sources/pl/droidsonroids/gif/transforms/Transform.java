package pl.droidsonroids.gif.transforms;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public interface Transform {
    void a(Canvas canvas, Paint paint, Bitmap bitmap);

    void a(Rect rect);
}
