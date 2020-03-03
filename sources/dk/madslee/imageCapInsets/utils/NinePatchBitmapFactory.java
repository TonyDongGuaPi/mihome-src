package dk.madslee.imageCapInsets.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NinePatchBitmapFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final int f2581a = 1;
    private static final int b = 0;

    public static NinePatchDrawable a(Resources resources, Bitmap bitmap, int i, int i2, int i3, int i4, String str) {
        return new NinePatchDrawable(resources, bitmap, a(i, i2, i3, i4).array(), new Rect(), str);
    }

    public static NinePatch b(Resources resources, Bitmap bitmap, int i, int i2, int i3, int i4, String str) {
        return new NinePatch(bitmap, a(i, i2, i3, i4).array(), str);
    }

    private static ByteBuffer a(int i, int i2, int i3, int i4) {
        ByteBuffer order = ByteBuffer.allocate(84).order(ByteOrder.nativeOrder());
        order.put((byte) 1);
        order.put((byte) 2);
        order.put((byte) 2);
        order.put((byte) 9);
        order.putInt(0);
        order.putInt(0);
        order.putInt(0);
        order.putInt(0);
        order.putInt(0);
        order.putInt(0);
        order.putInt(0);
        order.putInt(i2);
        order.putInt(i4);
        order.putInt(i);
        order.putInt(i3);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        order.putInt(1);
        return order;
    }
}
