package com.xiaomi.yp_pic_pick.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.xiaomi.youpin.common.util.FileFitUtils;

public class FileUtils {
    public static void a(Context context, Uri uri) {
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
    }

    public static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = i3 / i;
        if (i5 >= i4 / i2) {
            if (i5 > 0) {
                while (i3 / i5 > i) {
                    i5++;
                }
            }
        } else if (i5 > 0) {
            while (i4 / i5 > i2) {
                i5++;
            }
        }
        if (i5 > 0) {
            while (i3 / i5 > i) {
                i5++;
            }
        }
        return i5;
    }

    public static String a(Context context, Bitmap bitmap) {
        return FileFitUtils.a(context, bitmap, "youpin_export_" + System.currentTimeMillis(), "", "");
    }
}
