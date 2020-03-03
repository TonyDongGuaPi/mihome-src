package miuipub.util.bitmapcompat;

import android.graphics.Bitmap;
import android.os.Build;

public class BitmapCompat {

    /* renamed from: a  reason: collision with root package name */
    static final BitmapImpl f3049a;

    interface BitmapImpl {
        void a(Bitmap bitmap, boolean z);

        boolean a(Bitmap bitmap);

        int b(Bitmap bitmap);
    }

    static class BaseBitmapImpl implements BitmapImpl {
        public void a(Bitmap bitmap, boolean z) {
        }

        public boolean a(Bitmap bitmap) {
            return false;
        }

        BaseBitmapImpl() {
        }

        public int b(Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    static class HcMr1BitmapCompatImpl extends BaseBitmapImpl {
        HcMr1BitmapCompatImpl() {
        }

        public int b(Bitmap bitmap) {
            return BitmapCompatHoneycombMr1.a(bitmap);
        }
    }

    static class JbMr2BitmapCompatImpl extends HcMr1BitmapCompatImpl {
        JbMr2BitmapCompatImpl() {
        }

        public boolean a(Bitmap bitmap) {
            return BitmapCompatJellybeanMR2.a(bitmap);
        }

        public void a(Bitmap bitmap, boolean z) {
            BitmapCompatJellybeanMR2.a(bitmap, z);
        }
    }

    static class KitKatBitmapCompatImpl extends JbMr2BitmapCompatImpl {
        KitKatBitmapCompatImpl() {
        }

        public int b(Bitmap bitmap) {
            return BitmapCompatKitKat.a(bitmap);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            f3049a = new KitKatBitmapCompatImpl();
        } else if (i >= 18) {
            f3049a = new JbMr2BitmapCompatImpl();
        } else if (i >= 12) {
            f3049a = new HcMr1BitmapCompatImpl();
        } else {
            f3049a = new BaseBitmapImpl();
        }
    }

    public static boolean a(Bitmap bitmap) {
        return f3049a.a(bitmap);
    }

    public static void a(Bitmap bitmap, boolean z) {
        f3049a.a(bitmap, z);
    }

    public static int b(Bitmap bitmap) {
        return f3049a.b(bitmap);
    }
}
