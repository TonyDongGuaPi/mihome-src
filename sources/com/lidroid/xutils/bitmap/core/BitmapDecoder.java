package com.lidroid.xutils.bitmap.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.lidroid.xutils.util.LogUtils;
import java.io.FileDescriptor;

public class BitmapDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f6301a = new Object();

    private BitmapDecoder() {
    }

    public static Bitmap a(Resources resources, int i, BitmapSize bitmapSize, Bitmap.Config config) {
        Bitmap decodeResource;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeResource(resources, i, options);
            options.inSampleSize = a(options, bitmapSize.a(), bitmapSize.b());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            try {
                decodeResource = BitmapFactory.decodeResource(resources, i, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeResource;
    }

    public static Bitmap a(String str, BitmapSize bitmapSize, Bitmap.Config config) {
        Bitmap decodeFile;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = a(options, bitmapSize.a(), bitmapSize.b());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            try {
                decodeFile = BitmapFactory.decodeFile(str, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeFile;
    }

    public static Bitmap a(FileDescriptor fileDescriptor, BitmapSize bitmapSize, Bitmap.Config config) {
        Bitmap decodeFileDescriptor;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
            options.inSampleSize = a(options, bitmapSize.a(), bitmapSize.b());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            try {
                decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeFileDescriptor;
    }

    public static Bitmap a(byte[] bArr, BitmapSize bitmapSize, Bitmap.Config config) {
        Bitmap decodeByteArray;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = a(options, bitmapSize.a(), bitmapSize.b());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            try {
                decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeByteArray;
    }

    public static Bitmap a(Resources resources, int i) {
        Bitmap decodeResource;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                decodeResource = BitmapFactory.decodeResource(resources, i, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeResource;
    }

    public static Bitmap a(String str) {
        Bitmap decodeFile;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                decodeFile = BitmapFactory.decodeFile(str, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeFile;
    }

    public static Bitmap a(FileDescriptor fileDescriptor) {
        Bitmap decodeFileDescriptor;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeFileDescriptor;
    }

    public static Bitmap a(byte[] bArr) {
        Bitmap decodeByteArray;
        synchronized (f6301a) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
                return null;
            }
        }
        return decodeByteArray;
    }

    public static int a(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i5 <= i && i4 <= i2) {
            return 1;
        }
        if (i5 > i4) {
            i3 = Math.round(((float) i4) / ((float) i2));
        } else {
            i3 = Math.round(((float) i5) / ((float) i));
        }
        while (((float) (i5 * i4)) / ((float) (i3 * i3)) > ((float) (i * i2 * 2))) {
            i3++;
        }
        return i3;
    }
}
