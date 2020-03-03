package com.xiaomi.jr.account;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class AvatarUtils {
    public static Drawable a(Bitmap bitmap, int i) {
        OvalShape ovalShape = new OvalShape();
        ovalShape.resize((float) bitmap.getWidth(), (float) bitmap.getHeight());
        Bitmap b = b(bitmap, i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(ovalShape);
        shapeDrawable.getPaint().setShader(new BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        shapeDrawable.setBounds(0, 0, b.getWidth(), b.getHeight());
        shapeDrawable.setIntrinsicWidth(b.getWidth());
        shapeDrawable.setIntrinsicHeight(b.getHeight());
        return shapeDrawable;
    }

    private static Bitmap b(Bitmap bitmap, int i) {
        if (bitmap.getWidth() != i) {
            return Bitmap.createScaledBitmap(bitmap, i, i, true);
        }
        return null;
    }
}
