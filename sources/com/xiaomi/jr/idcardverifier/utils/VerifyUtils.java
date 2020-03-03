package com.xiaomi.jr.idcardverifier.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.widget.Toast;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.idcardverifier.R;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class VerifyUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10879a = "VerifyUtils";

    public static String a(Context context, IDCardQualityResult.IDCardFailedType iDCardFailedType, IDCardAttr.IDCardSide iDCardSide) {
        switch (iDCardFailedType) {
            case QUALITY_FAILED_TYPE_NOIDCARD:
                return context.getString(R.string.verify_scan_failed_type_no_id);
            case QUALITY_FAILED_TYPE_BLUR:
                return context.getString(R.string.verify_scan_failed_type_blur);
            case QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH:
                return context.getString(R.string.verify_scan_failed_type_brightness_too_high);
            case QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW:
                return context.getString(R.string.verify_scan_failed_type_brightness_too_low);
            case QUALITY_FAILED_TYPE_OUTSIDETHEROI:
                return context.getString(R.string.verify_scan_failed_type_outside_the_roi);
            case QUALITY_FAILED_TYPE_SIZETOOLARGE:
                return context.getString(R.string.verify_scan_failed_type_size_too_large);
            case QUALITY_FAILED_TYPE_SIZETOOSMALL:
                return context.getString(R.string.verify_scan_failed_type_size_too_small);
            case QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT:
                return context.getString(R.string.verify_scan_failed_type_specular_high_light);
            case QUALITY_FAILED_TYPE_TILT:
                return context.getString(R.string.verify_scan_failed_type_tilt);
            case QUALITY_FAILED_TYPE_SHADOW:
                return context.getString(R.string.verify_scan_failed_type_shadow);
            case QUALITY_FAILED_TYPE_WRONGSIDE:
                if (iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_BACK) {
                    return context.getString(R.string.verify_scan_failed_type_wrong_side_back);
                }
                return context.getString(R.string.verify_scan_failed_type_wrong_side_front);
            default:
                return null;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, int i3) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        Paint paint = new Paint(1);
        paint.setColor(-65536);
        float f = (float) i3;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        bitmapDrawable.setBounds(0, 0, i, i2);
        canvas.saveLayer(rectF, paint, 31);
        bitmapDrawable.draw(canvas);
        canvas.restore();
        return createBitmap;
    }

    public static void a(Context context, int i) {
        a(context, (CharSequence) context.getString(i));
    }

    public static void a(Context context, CharSequence charSequence) {
        Toast.makeText(context.getApplicationContext(), charSequence, 1).show();
    }

    public static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", context.getPackageName());
        hashMap.put("versionCode", String.valueOf(AppUtils.f(context)));
        hashMap.put("versionName", AppUtils.g(context));
        hashMap.put("model", Build.MODEL);
        hashMap.put("device", Build.DEVICE);
        hashMap.put("brand", Build.BRAND);
        hashMap.put("incremental", Build.VERSION.INCREMENTAL);
        hashMap.put("sdk", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("release", Build.VERSION.RELEASE);
        return hashMap;
    }

    public static byte[] b(Context context) {
        Throwable th;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = context.getAssets().open("idcard_model");
            try {
                byte[] a2 = FileUtils.a(inputStream);
                Utils.a((Closeable) inputStream);
                return a2;
            } catch (IOException e) {
                e = e;
                try {
                    e.printStackTrace();
                    Utils.a((Closeable) inputStream);
                    return null;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    inputStream2 = inputStream;
                    th = th3;
                    Utils.a((Closeable) inputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            e.printStackTrace();
            Utils.a((Closeable) inputStream);
            return null;
        } catch (Throwable th4) {
            th = th4;
            Utils.a((Closeable) inputStream2);
            throw th;
        }
    }
}
