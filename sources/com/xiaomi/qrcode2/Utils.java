package com.xiaomi.qrcode2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.PlanarYUVLuminanceSource;
import com.xiaomi.zxing.R;
import com.xiaomi.zxing.RGBLuminanceSource;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.HybridBinarizer;
import com.xiaomi.zxing.qrcode.QRCodeReader;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

public class Utils {
    public static boolean a(Activity activity) {
        return a(activity.getWindow(), true);
    }

    public static void a(Activity activity, View view) {
        if (view != null) {
            int b = b(view.getContext());
            if (b == -1) {
                b = view.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding);
            }
            a(b, activity, view);
        }
    }

    private static void a(int i, Activity activity, View view) {
        if (a(activity) && view != null) {
            if (view.getLayoutParams().height >= 0) {
                view.getLayoutParams().height += i;
            }
            view.setPadding(0, i, 0, 0);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    private static boolean a(Window window, boolean z) {
        if (Build.BRAND.equalsIgnoreCase("htc") && Build.MODEL.contains("M8w")) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 19 || !c(window, z)) {
            if (Build.VERSION.SDK_INT >= 19 && b(window, z)) {
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                return true;
            } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                return true;
            } else if (Build.VERSION.SDK_INT < 21) {
                return false;
            } else {
                window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                if (Build.VERSION.SDK_INT < 23 || !z) {
                    window.getDecorView().setSystemUiVisibility(1280);
                } else {
                    window.getDecorView().setSystemUiVisibility(9472);
                }
                window.addFlags(Integer.MIN_VALUE);
                if (!z || (!Build.BRAND.equalsIgnoreCase("oppo") && !Build.BRAND.equalsIgnoreCase("vivo"))) {
                    window.setStatusBarColor(0);
                    return true;
                }
                window.setStatusBarColor(33554431);
                return true;
            }
        } else if (z) {
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            window.addFlags(Integer.MIN_VALUE);
            window.getDecorView().setSystemUiVisibility(8192);
            return true;
        } else {
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
            return true;
        }
    }

    private static boolean b(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt((Object) null);
            int i2 = declaredField2.getInt(attributes);
            declaredField2.setInt(attributes, z ? i2 | i : (i ^ -1) & i2);
            window.setAttributes(attributes);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean c(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            if (z) {
                int i3 = i2 | i;
                method.invoke(window, new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
            } else {
                method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i2 | i)});
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static int b(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String a(Context context, int i, int i2, String str) throws IOException, WriterException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix a2 = new QRCodeWriter().a(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
        int[] iArr = new int[(i * i2)];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                if (a2.a(i4, i3)) {
                    iArr[(i3 * i) + i4] = -16777216;
                } else {
                    iArr[(i3 * i) + i4] = -1;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        String str2 = context.getCacheDir().getAbsolutePath() + "/barcode.jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
        createBitmap.recycle();
        fileOutputStream.close();
        return str2;
    }

    public static void a(Activity activity, int i) {
        try {
            activity.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), i);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String a(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return KKImagePathUtils.a(context, uri);
        }
        return b(context, uri);
    }

    public static String b(Context context, Uri uri) {
        String[] strArr = {Downloads._DATA};
        Cursor query = context.getContentResolver().query(uri, strArr, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex(strArr[0]));
        query.close();
        return string;
    }

    public static Result a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        bitmap.recycle();
        Result b = b(iArr, width, height);
        if (b != null) {
            return b;
        }
        Log.e("qrcode", "new scan no result");
        return a(iArr, width, height);
    }

    public static Result a(int[] iArr, int i, int i2) {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(i, i2, iArr)));
        QRCodeReader qRCodeReader = new QRCodeReader();
        Hashtable hashtable = new Hashtable();
        hashtable.put(DecodeHintType.CHARACTER_SET, "utf-8");
        try {
            return qRCodeReader.a(binaryBitmap, (Map<DecodeHintType, ?>) hashtable);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (ChecksumException e2) {
            e2.printStackTrace();
            return null;
        } catch (FormatException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Result b(int[] iArr, int i, int i2) {
        return a(c(iArr, i, i2), i, i2);
    }

    private static Result a(byte[] bArr, int i, int i2) {
        if (bArr == null || i <= 0 || i2 <= 0) {
            return null;
        }
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, true)));
            QRCodeReader qRCodeReader = new QRCodeReader();
            Hashtable hashtable = new Hashtable();
            hashtable.put(DecodeHintType.CHARACTER_SET, "utf-8");
            return qRCodeReader.a(binaryBitmap, hashtable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] c(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[((i * i2) + (((i % 2 == 0 ? i : i + 1) * (i2 % 2 == 0 ? i2 : i2 + 1)) / 2))];
        a(bArr, iArr, i, i2);
        return bArr;
    }

    private static void a(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int i5 = i3 * i4;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i4) {
            int i9 = i5;
            int i10 = i7;
            int i11 = 0;
            while (i11 < i3) {
                int i12 = iArr[i8];
                int i13 = (iArr[i8] & 16711680) >> 16;
                int i14 = (iArr[i8] & 65280) >> 8;
                int i15 = iArr[i8] & 255;
                i8++;
                int max = Math.max(0, Math.min((((((i13 * 66) + (i14 * 129)) + (i15 * 25)) + 128) >> 8) + 16, 255));
                int max2 = Math.max(0, Math.min((((((i13 * -38) - (i14 * 74)) + (i15 * 112)) + 128) >> 8) + 128, 255));
                int max3 = Math.max(0, Math.min((((((i13 * 112) - (i14 * 94)) - (i15 * 18)) + 128) >> 8) + 128, 255));
                int i16 = i10 + 1;
                bArr[i10] = (byte) max;
                if (i6 % 2 == 0 && i11 % 2 == 0) {
                    int i17 = i9 + 1;
                    bArr[i9] = (byte) max3;
                    i9 = i17 + 1;
                    bArr[i17] = (byte) max2;
                }
                i11++;
                i10 = i16;
            }
            i6++;
            i7 = i10;
            i5 = i9;
        }
    }

    public static void a(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2, float f) {
        if (resultPoint != null && resultPoint2 != null) {
            canvas.drawLine(f * resultPoint.a(), f * resultPoint.b(), f * resultPoint2.a(), f * resultPoint2.b(), paint);
        }
    }

    public static String a(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
