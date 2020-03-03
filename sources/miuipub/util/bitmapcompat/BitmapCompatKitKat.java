package miuipub.util.bitmapcompat;

import android.graphics.Bitmap;

class BitmapCompatKitKat {
    BitmapCompatKitKat() {
    }

    static int a(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }
}
