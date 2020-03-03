package com.mi.global.shop.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.shop.ShopApp;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil extends com.mi.util.ImageUtil {
    public static String a(int i, int i2, String str) {
        String str2;
        if (str.indexOf(47) < 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, str.lastIndexOf(47)));
        String substring = str.substring(str.lastIndexOf(47));
        if (substring.contains("!")) {
            stringBuffer.append(substring.subSequence(0, substring.indexOf(33)));
            stringBuffer.append('!');
            stringBuffer.append(i);
            stringBuffer.append('x');
            stringBuffer.append(i2);
            if (substring.indexOf(33) != substring.lastIndexOf(33)) {
                stringBuffer.append(substring.substring(substring.lastIndexOf(33)));
            } else {
                stringBuffer.append('!');
                stringBuffer.append(a());
                if (substring.contains(".")) {
                    stringBuffer.append(substring.substring(substring.lastIndexOf(46)));
                }
            }
        } else if (substring.contains(".")) {
            stringBuffer.append(substring.subSequence(0, substring.lastIndexOf(46)));
            stringBuffer.append('!');
            stringBuffer.append(i);
            stringBuffer.append('x');
            stringBuffer.append(i2);
            stringBuffer.append('!');
            stringBuffer.append(a());
            stringBuffer.append(substring.substring(substring.lastIndexOf(46)));
        } else {
            stringBuffer.append(substring);
            stringBuffer.append('!');
            stringBuffer.append(i);
            stringBuffer.append('x');
            stringBuffer.append(i2);
            stringBuffer.append('!');
            stringBuffer.append(a());
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.startsWith("http:") || stringBuffer2.startsWith("https:")) {
            return stringBuffer2;
        }
        if (stringBuffer2.startsWith("//")) {
            str2 = "http:" + str;
        } else {
            str2 = ConnectionHelper.HTTP_PREFIX + str;
        }
        return UrlUtil.c(str2);
    }

    private static int a() {
        return NetworkUtil.b() ? 70 : 40;
    }

    public static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static void a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            bitmap.compress(compressFormat, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        if (i4 > i3) {
            return Math.round(((float) i3) / ((float) i2));
        }
        return Math.round(((float) i4) / ((float) i));
    }

    public static String a(int i) {
        return ShopApp.g().getCacheDir().getAbsolutePath() + "/comment_image_" + i + ".jpg";
    }
}
