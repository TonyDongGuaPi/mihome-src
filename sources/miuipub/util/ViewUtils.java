package miuipub.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.View;

public class ViewUtils {

    /* renamed from: a  reason: collision with root package name */
    static final String f3026a = "ViewUtils";
    private static final int b = 10000;

    private ViewUtils() {
    }

    public static void a(View view, Rect rect) {
        rect.left = view.getScrollX() + view.getPaddingLeft();
        rect.top = view.getScrollY() + view.getPaddingTop();
        rect.right = (view.getWidth() - view.getPaddingRight()) - rect.left;
        rect.bottom = (view.getHeight() - view.getPaddingBottom()) - rect.top;
    }

    public static boolean b(View view, Rect rect) {
        return rect != null && view.getLeft() < rect.right && view.getTop() < rect.bottom && view.getRight() > rect.left && view.getBottom() > rect.top;
    }

    public static boolean a(View view, int i, int i2) {
        return i > view.getLeft() && i < view.getRight() && i2 > view.getTop() && i2 < view.getBottom();
    }

    public static int a(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return -1;
    }

    public static int b(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            return background.getIntrinsicWidth();
        }
        return -1;
    }

    public static Drawable a(Resources resources, Drawable drawable, Drawable drawable2, int i, int i2, boolean z) {
        Drawable drawable3;
        Drawable drawable4 = drawable;
        if ((drawable4 instanceof NinePatchDrawable) || (drawable4 instanceof BitmapDrawable)) {
            drawable3 = new ClipDrawable(a(resources, drawable, drawable2, i, i2), 3, 1);
        } else if (drawable4 instanceof ClipDrawable) {
            drawable3 = a(resources, drawable, drawable2, i, i2);
            if (drawable3 != drawable4) {
                return new ClipDrawable(drawable3, 3, 1);
            }
        } else {
            int i3 = 0;
            if (drawable4 instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable4;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i4 = 0; i4 < numberOfLayers; i4++) {
                    int id = layerDrawable.getId(i4);
                    Drawable drawable5 = layerDrawable.getDrawable(i4);
                    if (id == 16908301 || id == 16908303) {
                        drawableArr[i4] = a(resources, drawable5, drawable2, i, i2, z);
                    } else {
                        drawableArr[i4] = drawable5;
                    }
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                while (i3 < numberOfLayers) {
                    layerDrawable2.setId(i3, layerDrawable.getId(i3));
                    i3++;
                }
                return layerDrawable2;
            } else if (drawable4 instanceof StateListDrawable) {
                StateListDrawable stateListDrawable = (StateListDrawable) drawable4;
                return new StateListDrawable();
            } else if (drawable4 instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawable4;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                while (i3 < numberOfFrames) {
                    Drawable a2 = a(resources, animationDrawable.getFrame(i3), drawable2, i, i2, z);
                    a2.setLevel(10000);
                    animationDrawable2.addFrame(a2, animationDrawable.getDuration(i3));
                    i3++;
                }
                if (z) {
                    animationDrawable2.setLevel(10000);
                } else {
                    animationDrawable2.setLevel(animationDrawable.getLevel());
                }
                return animationDrawable2;
            } else {
                Log.w(f3026a, "Unknown Drawable subclass, src=" + drawable);
                return drawable4;
            }
        }
        return drawable3;
    }

    private static Drawable a(Resources resources, Drawable drawable, Drawable drawable2, int i, int i2) {
        if (drawable == null || drawable2 == null || i <= 0 || i2 <= 0) {
            return drawable;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        drawable.setLevel(10000);
        drawable.setBounds(0, 0, i, i2);
        drawable.draw(canvas);
        drawable2.draw(canvas);
        return new BitmapDrawable(resources, createBitmap);
    }
}
