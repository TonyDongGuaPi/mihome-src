package com.sina.weibo.sdk.utils;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class BitmapHelper {
    public static boolean a(Rect rect) {
        return (rect.width() * rect.height()) * 2 <= 5242880;
    }

    public static int b(Rect rect) {
        double width = (double) rect.width();
        double height = (double) rect.height();
        Double.isNaN(width);
        Double.isNaN(height);
        double d = ((width * height) * 2.0d) / 5242880.0d;
        if (d >= 1.0d) {
            return (int) d;
        }
        return 1;
    }

    public static int a(int i, int i2, int i3, int i4) {
        if (i2 == 0 || i == 0) {
            return 1;
        }
        return Math.min(Math.max(i3 / i, i4 / i2), Math.max(i4 / i, i3 / i2));
    }

    public static boolean a(byte[] bArr) {
        return a((InputStream) new ByteArrayInputStream(bArr));
    }

    public static boolean a(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (options.outHeight <= 0 || options.outWidth <= 0) {
            return false;
        }
        return true;
    }

    public static boolean a(String str) {
        try {
            return a((InputStream) new FileInputStream(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
