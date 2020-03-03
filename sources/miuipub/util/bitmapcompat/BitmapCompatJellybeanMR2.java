package miuipub.util.bitmapcompat;

import android.graphics.Bitmap;

class BitmapCompatJellybeanMR2 {
    BitmapCompatJellybeanMR2() {
    }

    public static boolean a(Bitmap bitmap) {
        return bitmap.hasMipMap();
    }

    public static void a(Bitmap bitmap, boolean z) {
        bitmap.setHasMipMap(z);
    }
}
