package miuipub.graphics;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.mi.global.shop.model.Tags;
import com.miuipub.internal.util.PackageConstants;
import java.io.IOException;
import java.util.regex.Pattern;
import miuipub.io.ResettableInputStream;
import miuipub.v6.R;

public class BitmapFactory extends android.graphics.BitmapFactory {

    /* renamed from: a  reason: collision with root package name */
    static RenderScript f2929a = null;
    static Object b = new Object();
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    private static byte[] f = {Constants.TagName.COMPANY_CODE, Constants.TagName.ORDER_BRIEF_INFO_LIST, 78, Constants.TagName.ACTIVITY_INFO, 13, 10, 26, 10};
    private static final ThreadLocal<Canvas> g = new ThreadLocal<>();
    private static final Paint h = new Paint(1);
    private static final Pattern i = Pattern.compile("[㄀-ㄭㆠ-ㆺ一-鿌㐀-䶵豈-龎⼀-⿕⺀-⻳㇀-㇣ᄀ-ᇿꥠ-ꥼힰ-ퟻㄱ-ㆎ가-힣぀-ゟ゠-ヿㇰ-ㇿ㆐-㆟ꀀ-ꒌ꒐-꓆]");
    private static final String[] j = {"老师", Tags.MiHome.MAN, "老板", "仔", "手机", "叔", "阿姨", "宅", "伯", "伯母", "伯父", "哥", "姐", "弟", "妹", "舅", "姑", "父", "主任", "经理", "工作", "同事", "律师", "司机", "师傅", "师父", "爷", "奶", "中介", "董", "总", "太太", "保姆", "某", "秘书", "处长", "局长", "班长", "兄", "助理"};

    private static native void native_fastBlur(Bitmap bitmap, Bitmap bitmap2, int i2);

    static {
        if (Build.VERSION.SDK_INT < 17) {
            System.loadLibrary("miuiimageutilities");
        }
        h.setFilterBitmap(true);
        h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    protected BitmapFactory() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static int a(ResettableInputStream resettableInputStream, int i2) {
        if (i2 <= 0) {
            return 1;
        }
        BitmapFactory.Options a2 = a(resettableInputStream);
        double d2 = (double) a2.outWidth;
        double d3 = (double) a2.outHeight;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = (double) i2;
        Double.isNaN(d4);
        return (int) Math.sqrt((d2 * d3) / d4);
    }

    public static BitmapFactory.Options a(ResettableInputStream resettableInputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeStream(resettableInputStream, (Rect) null, options);
        return options;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.BitmapFactory.Options a(java.lang.String r2) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0011 }
            android.graphics.BitmapFactory$Options r2 = a((miuipub.io.ResettableInputStream) r1)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(java.lang.String):android.graphics.BitmapFactory$Options");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.BitmapFactory.Options a(android.content.Context r2, android.net.Uri r3) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((android.content.Context) r2, (android.net.Uri) r3)     // Catch:{ all -> 0x0011 }
            android.graphics.BitmapFactory$Options r2 = a((miuipub.io.ResettableInputStream) r1)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(android.content.Context, android.net.Uri):android.graphics.BitmapFactory$Options");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.lang.String r2, boolean r3) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0012 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0012 }
            r2 = -1
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r2, (boolean) r3)     // Catch:{ all -> 0x000f }
            r1.close()
            return r2
        L_0x000f:
            r2 = move-exception
            r0 = r1
            goto L_0x0013
        L_0x0012:
            r2 = move-exception
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.close()
        L_0x0018:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(java.lang.String, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.lang.String r2, int r3, int r4, boolean r5) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0011 }
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r3, (int) r4, (boolean) r5)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(java.lang.String, int, int, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r2, android.net.Uri r3, boolean r4) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0012 }
            r1.<init>((android.content.Context) r2, (android.net.Uri) r3)     // Catch:{ all -> 0x0012 }
            r2 = -1
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r2, (boolean) r4)     // Catch:{ all -> 0x000f }
            r1.close()
            return r2
        L_0x000f:
            r2 = move-exception
            r0 = r1
            goto L_0x0013
        L_0x0012:
            r2 = move-exception
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.close()
        L_0x0018:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(android.content.Context, android.net.Uri, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r2, android.net.Uri r3, int r4, int r5, boolean r6) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((android.content.Context) r2, (android.net.Uri) r3)     // Catch:{ all -> 0x0011 }
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r4, (int) r5, (boolean) r6)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(android.content.Context, android.net.Uri, int, int, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.lang.String r2, int r3, boolean r4) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0011 }
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r3, (boolean) r4)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(java.lang.String, int, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r2, android.net.Uri r3, int r4, boolean r5) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((android.content.Context) r2, (android.net.Uri) r3)     // Catch:{ all -> 0x0011 }
            android.graphics.Bitmap r2 = a((miuipub.io.ResettableInputStream) r1, (int) r4, (boolean) r5)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(android.content.Context, android.net.Uri, int, boolean):android.graphics.Bitmap");
    }

    public static Bitmap a(ResettableInputStream resettableInputStream, int i2, boolean z) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        int i3 = 0;
        options.inScaled = false;
        options.inSampleSize = a(resettableInputStream, i2);
        while (true) {
            int i4 = i3 + 1;
            if (i3 >= 3) {
                return null;
            }
            try {
                resettableInputStream.reset();
                return decodeStream(resettableInputStream, (Rect) null, options);
            } catch (OutOfMemoryError e2) {
                if (z) {
                    options.inSampleSize *= 2;
                    i3 = i4;
                } else {
                    throw e2;
                }
            }
        }
    }

    public static Bitmap a(ResettableInputStream resettableInputStream, int i2, int i3, boolean z) throws IOException {
        int i4 = i2 * i3;
        if (i2 <= 0 || i3 <= 0) {
            i4 = -1;
        }
        Bitmap a2 = a(resettableInputStream, i4, z);
        if (a2 == null) {
            return null;
        }
        if (i4 <= 0) {
            return a2;
        }
        Bitmap a3 = a(a2, i2, i3);
        if (a2 != a3) {
            a2.recycle();
        }
        return a3;
    }

    public static Bitmap a(Bitmap bitmap, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getWidth() == i2 && bitmap.getHeight() == i3) {
            return bitmap;
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, config);
        a(bitmap, createBitmap);
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        if (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight()) {
            return bitmap;
        }
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), paint);
        return bitmap2;
    }

    public static Bitmap a(Bitmap bitmap, CropOption cropOption) {
        if (bitmap != null) {
            return a(bitmap, a(bitmap), cropOption);
        }
        return null;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, CropOption cropOption) {
        Bitmap bitmap3 = bitmap2;
        if (bitmap == null || bitmap3 == null) {
            return null;
        }
        CropOption cropOption2 = cropOption == null ? new CropOption() : cropOption;
        Rect rect = cropOption2.e;
        if (rect == null) {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        int a2 = a(0, bitmap.getWidth() - 1, rect.left);
        int a3 = a(a2, bitmap.getWidth(), rect.right);
        int a4 = a(0, bitmap.getHeight() - 1, rect.top);
        int a5 = a(a4, bitmap.getHeight(), rect.bottom);
        int i2 = a3 - a2;
        int i3 = a5 - a4;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        cropOption2.c = a(0, Math.min(width, height) / 2, cropOption2.c);
        cropOption2.f2930a = a(0, width / 2, cropOption2.f2930a);
        cropOption2.b = a(0, height / 2, cropOption2.b);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawARGB(0, 0, 0, 0);
        if (cropOption2.f2930a - cropOption2.c > 0 && cropOption2.b - cropOption2.c > 0) {
            canvas.drawRoundRect(new RectF((float) cropOption2.c, (float) cropOption2.c, (float) (width - cropOption2.c), (float) (height - cropOption2.c)), (float) (cropOption2.f2930a - cropOption2.c), (float) (cropOption2.b - cropOption2.c), paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        float f2 = (float) i2;
        float f3 = (float) (width - (cropOption2.c * 2));
        float f4 = (float) i3;
        float f5 = (float) (height - (cropOption2.c * 2));
        float min = Math.min((f2 * 1.0f) / f3, (1.0f * f4) / f5);
        int i4 = (int) ((f2 - (f3 * min)) / 2.0f);
        int i5 = (int) ((f4 - (f5 * min)) / 2.0f);
        canvas.drawBitmap(bitmap, new Rect(a2 + i4, a4 + i5, a3 - i4, a5 - i5), new Rect(cropOption2.c, cropOption2.c, width - cropOption2.c, height - cropOption2.c), paint);
        if (cropOption2.c > 0 && (cropOption2.d >>> 24) != 0) {
            paint.setColor(cropOption2.d);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), (float) cropOption2.f2930a, (float) cropOption2.b, paint);
        }
        return bitmap2;
    }

    private static int a(int i2, int i3, int i4) {
        return Math.min(i3, Math.max(i2, i4));
    }

    public static boolean a(Bitmap bitmap, String str) throws IOException {
        return a(bitmap, str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.graphics.Bitmap r2, java.lang.String r3, boolean r4) throws java.io.IOException {
        /*
            if (r2 == 0) goto L_0x0023
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x001b }
            r1.<init>(r3)     // Catch:{ all -> 0x001b }
            if (r4 == 0) goto L_0x000f
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x000d }
            goto L_0x0011
        L_0x000d:
            r2 = move-exception
            goto L_0x001d
        L_0x000f:
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x000d }
        L_0x0011:
            r4 = 100
            r2.compress(r3, r4, r1)     // Catch:{ all -> 0x000d }
            r1.close()
            r2 = 1
            return r2
        L_0x001b:
            r2 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()
        L_0x0022:
            throw r2
        L_0x0023:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.a(android.graphics.Bitmap, java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(android.content.Context r2, android.net.Uri r3) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((android.content.Context) r2, (android.net.Uri) r3)     // Catch:{ all -> 0x0011 }
            boolean r2 = b((miuipub.io.ResettableInputStream) r1)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.b(android.content.Context, android.net.Uri):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r2) throws java.io.IOException {
        /*
            r0 = 0
            miuipub.io.ResettableInputStream r1 = new miuipub.io.ResettableInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0011 }
            boolean r2 = b((miuipub.io.ResettableInputStream) r1)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.b(java.lang.String):boolean");
    }

    public static boolean b(ResettableInputStream resettableInputStream) throws IOException {
        byte[] bArr = new byte[f.length];
        if (resettableInputStream.read(bArr) >= bArr.length) {
            return a(bArr);
        }
        return false;
    }

    private static boolean a(byte[] bArr) {
        if (bArr == null || bArr.length < f.length) {
            return false;
        }
        for (int i2 = 0; i2 < f.length; i2++) {
            if (bArr[i2] != f[i2]) {
                return false;
            }
        }
        return true;
    }

    private static Bitmap a(Bitmap bitmap) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        return Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
    }

    public static Bitmap a(Bitmap bitmap, int i2) {
        Bitmap a2 = a(bitmap);
        a(bitmap, a2, i2);
        return a2;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, int i2) {
        if (bitmap == null) {
            return null;
        }
        if (!(bitmap2 != null && bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight())) {
            bitmap2 = a(bitmap);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            b(bitmap, bitmap2, i2);
        } else {
            native_fastBlur(bitmap, bitmap2, i2);
        }
        return bitmap2;
    }

    private static Bitmap b(Bitmap bitmap, Bitmap bitmap2, int i2) {
        Bitmap bitmap3;
        int i3 = 1;
        while (i2 > 25) {
            i3 *= 2;
            i2 /= 2;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i3 == 1) {
            bitmap3 = bitmap;
        } else {
            bitmap3 = a(bitmap, width / i3, height / i3);
        }
        Context a2 = PackageConstants.a();
        if (a2.getApplicationContext() == null) {
            a2 = new ContextWrapper(a2) {
                public Context getApplicationContext() {
                    return this;
                }
            };
        }
        synchronized (b) {
            if (f2929a == null) {
                f2929a = RenderScript.create(a2);
            }
            Bitmap bitmap4 = i3 == 1 ? bitmap2 : bitmap3;
            if (bitmap3.getRowBytes() != bitmap4.getRowBytes()) {
                bitmap3 = bitmap3.copy(Bitmap.Config.ARGB_8888, true);
            }
            Allocation createFromBitmap = Allocation.createFromBitmap(f2929a, bitmap3);
            Allocation createTyped = Allocation.createTyped(f2929a, createFromBitmap.getType());
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(f2929a, Element.U8_4(f2929a));
            create.setRadius((float) i2);
            create.setInput(createFromBitmap);
            create.forEach(createTyped);
            createTyped.copyTo(bitmap4);
            if (bitmap4 != bitmap2) {
                a(bitmap4, bitmap2);
            }
            if (bitmap3 != bitmap) {
                bitmap3.recycle();
            }
            if (bitmap4 != bitmap2) {
                bitmap4.recycle();
            }
        }
        return bitmap2;
    }

    public static class CropOption {

        /* renamed from: a  reason: collision with root package name */
        public int f2930a;
        public int b;
        public int c;
        public int d;
        public Rect e;

        public CropOption() {
        }

        public CropOption(int i, int i2, int i3, int i4) {
            this.f2930a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public CropOption(CropOption cropOption) {
            this.f2930a = cropOption.f2930a;
            this.b = cropOption.b;
            this.c = cropOption.c;
            this.d = cropOption.d;
            this.e = cropOption.e;
        }
    }

    private static Canvas a() {
        Canvas canvas = g.get();
        if (canvas != null) {
            return canvas;
        }
        Canvas canvas2 = new Canvas();
        g.set(canvas2);
        return canvas2;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3) {
        Rect rect;
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (bitmap2 != null) {
            rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        } else {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        return a(bitmap, bitmap2, drawable, drawable2, drawable3, rect2, rect);
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, int i2) {
        return a(bitmap, bitmap2, drawable, drawable2, drawable3, (Rect) null, new Rect(0, 0, i2, i2));
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, Rect rect, Rect rect2) {
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Bitmap a2 = a(bitmap, drawable, (Bitmap) null, rect, rect2);
        Canvas a3 = a();
        a3.setBitmap(bitmap2);
        if (drawable3 != null) {
            drawable3.setBounds(rect2);
            drawable3.draw(a3);
        }
        a3.drawBitmap(a2, rect2, rect2, (Paint) null);
        a2.recycle();
        if (drawable2 != null) {
            drawable2.setBounds(rect2);
            drawable2.draw(a3);
        }
        return bitmap2;
    }

    public static Bitmap a(Bitmap bitmap, Drawable drawable, Bitmap bitmap2, Rect rect, Rect rect2) {
        int i2;
        int i3;
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        int i4 = 0;
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Canvas a2 = a();
        a2.setBitmap(bitmap2);
        a2.drawColor(0, PorterDuff.Mode.CLEAR);
        if (drawable != null) {
            drawable.setBounds(rect2);
            drawable.draw(a2);
        }
        if (rect == null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = rect2.width();
            float height2 = (float) rect2.height();
            float f2 = (float) width2;
            float f3 = height2 / f2;
            float f4 = (float) width;
            float f5 = f4 / f2;
            float f6 = (float) height;
            float f7 = f6 / height2;
            if (f5 > f7) {
                int i5 = (int) (f6 / f3);
                i4 = (width - i5) / 2;
                width = i5;
            } else if (f5 < f7) {
                i3 = (int) (f3 * f4);
                i2 = (height - i3) / 2;
                rect = new Rect(i4, i2, width + i4, i3 + i2);
            }
            i3 = height;
            i2 = 0;
            rect = new Rect(i4, i2, width + i4, i3 + i2);
        }
        a2.drawBitmap(bitmap, rect, rect2, h);
        return bitmap2;
    }

    public static Bitmap a(Context context, String str, int i2) {
        return a(context, str, i2, 0, 0);
    }

    public static Bitmap a(Context context, String str, int i2, int i3, int i4) {
        if (str == null) {
            return null;
        }
        String c2 = c(str.trim());
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        if (i3 == 0) {
            i3 = R.drawable.v6_word_photo_bg;
        }
        Drawable drawable = context.getResources().getDrawable(i3);
        drawable.setBounds(new Rect(0, 0, i2, i2));
        if (i4 == 0) {
            i4 = R.color.v6_word_photo_color;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.draw(canvas);
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        paint.setColor(context.getResources().getColor(i4));
        paint.setTextSize(((float) i2) * 0.6f);
        Rect rect = new Rect();
        paint.getTextBounds(c2, 0, c2.length(), rect);
        double d2 = (double) (i2 - (rect.right + rect.left));
        Double.isNaN(d2);
        int i5 = (int) (d2 * 0.5d);
        double d3 = (double) (i2 - (rect.top + rect.bottom));
        Double.isNaN(d3);
        canvas.drawText(c2, (float) i5, (float) ((int) (d3 * 0.5d)), paint);
        return createBitmap;
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!d(str)) {
            return str.substring(0, 1).toUpperCase();
        }
        String e2 = e(str);
        if (TextUtils.isEmpty(e2)) {
            return null;
        }
        int length = e2.length();
        return e2.substring(length - 1, length).trim();
    }

    private static boolean d(String str) {
        return i.matcher(str).find();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[LOOP:1: B:5:0x000d->B:16:0x0054, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0057 A[EDGE_INSN: B:28:0x0057->B:17:0x0057 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String e(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x0008
            r6 = 0
            return r6
        L_0x0008:
            r0 = r6
        L_0x0009:
            r1 = 0
            r2 = r0
            r0 = 0
            r3 = 0
        L_0x000d:
            java.lang.String[] r4 = j
            int r4 = r4.length
            r5 = 1
            if (r0 >= r4) goto L_0x0057
            java.lang.String[] r4 = j
            r4 = r4[r0]
            boolean r4 = r2.endsWith(r4)
            if (r4 == 0) goto L_0x0030
            int r3 = r2.length()
            java.lang.String[] r4 = j
            r4 = r4[r0]
            int r4 = r4.length()
            int r3 = r3 - r4
            java.lang.String r2 = r2.substring(r1, r3)
        L_0x002e:
            r3 = 1
            goto L_0x004d
        L_0x0030:
            int r4 = r2.length()
            int r4 = r4 - r5
            char r4 = r2.charAt(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            boolean r4 = d(r4)
            if (r4 != 0) goto L_0x004d
            int r3 = r2.length()
            int r3 = r3 - r5
            java.lang.String r2 = r2.substring(r1, r3)
            goto L_0x002e
        L_0x004d:
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 == 0) goto L_0x0054
            goto L_0x0057
        L_0x0054:
            int r0 = r0 + 1
            goto L_0x000d
        L_0x0057:
            r0 = r2
            if (r3 == 0) goto L_0x0060
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0009
        L_0x0060:
            if (r0 == 0) goto L_0x0066
            java.lang.String r0 = r0.trim()
        L_0x0066:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0075
            int r0 = r6.length()
            int r0 = r0 - r5
            java.lang.String r0 = r6.substring(r0)
        L_0x0075:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.graphics.BitmapFactory.e(java.lang.String):java.lang.String");
    }

    public static int b(Bitmap bitmap, int i2) {
        Bitmap bitmap2 = bitmap;
        int height = bitmap.getHeight() / i2;
        int width = bitmap.getWidth() / i2;
        int i3 = (width * height) / 5;
        Bitmap a2 = a(bitmap2, width, height);
        int i4 = 2;
        int i5 = 0;
        for (int i6 = 0; i6 < width; i6++) {
            int i7 = i4;
            int i8 = 0;
            while (true) {
                if (i8 >= height) {
                    i4 = i7;
                    break;
                }
                int pixel = a2.getPixel(i6, i8);
                double d2 = (double) ((float) ((16711680 & pixel) >> 16));
                Double.isNaN(d2);
                double d3 = (double) ((float) ((65280 & pixel) >> 8));
                Double.isNaN(d3);
                double d4 = (double) ((float) (pixel & 255));
                Double.isNaN(d4);
                if (((int) ((d2 * 0.3d) + (d3 * 0.59d) + (d4 * 0.11d))) < 180) {
                    i5++;
                    if (i5 > i3) {
                        i7 = 1;
                    }
                    if (i5 > i3 * 2) {
                        i4 = 0;
                        break;
                    }
                }
                i8++;
            }
        }
        if (a2 != bitmap2) {
            a2.recycle();
        }
        return i4;
    }
}
