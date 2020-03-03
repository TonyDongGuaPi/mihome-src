package com.seek.biscuit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ImageCompressor implements Compressor {
    private static final String d = "ImageCompressor";

    /* renamed from: a  reason: collision with root package name */
    String f8810a;
    CompressException b;
    final Biscuit c;
    private ImagePath e;
    private String f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private long k;

    public ImageCompressor(String str, String str2, int i2, int i3, boolean z, boolean z2, long j2, Biscuit biscuit) {
        this.e = new ImagePath(str);
        this.f = str2;
        this.g = i2;
        this.h = i3;
        this.i = z;
        this.j = z2;
        this.k = j2;
        this.c = biscuit;
    }

    public boolean a() {
        long elapsedRealtime;
        String str;
        StringBuilder sb;
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        if (b()) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.e.f8811a, options);
        if (options.outHeight <= 0 || options.outWidth <= 0) {
            a("an error occurs when trying to decode!");
            return false;
        }
        boolean z = this.h == 1;
        if (z) {
            options.inSampleSize = a(options);
        }
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = this.i ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(this.e.f8811a, options);
            if (decodeFile == null) {
                a("the image data could not be decoded!");
                return false;
            }
            if (!z) {
                float b2 = b(options);
                Utils.a(d, "scale : " + b2);
                if (b2 != 1.0f) {
                    Matrix matrix = new Matrix();
                    matrix.setScale(b2, b2);
                    decodeFile = a(decodeFile, matrix);
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            boolean compress = decodeFile.compress(Bitmap.CompressFormat.JPEG, this.g, byteArrayOutputStream);
            decodeFile.recycle();
            if (!compress) {
                a("unsuccessfully compressed to the specified stream!");
                return false;
            }
            this.f8810a = e();
            Utils.a(d, "the image data will be saved at " + this.f8810a);
            FileChannel fileChannel = null;
            try {
                File file = new File(this.f8810a);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileChannel channel = new FileOutputStream(file).getChannel();
                try {
                    channel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                    channel.close();
                    byteArrayOutputStream.close();
                    a(byteArrayOutputStream, channel);
                    elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime2;
                    str = d;
                    sb = new StringBuilder();
                } catch (IOException e2) {
                    FileChannel fileChannel2 = channel;
                    e = e2;
                    fileChannel = fileChannel2;
                    try {
                        Utils.a(d, "there is an exception when trying to save the compressed image!");
                        this.b = new CompressException("there is an exception when trying to save the compressed image!", this.e.f8811a, e);
                        a(byteArrayOutputStream, fileChannel);
                        Utils.a(d, "the compression time is " + (SystemClock.elapsedRealtime() - elapsedRealtime2));
                        return false;
                    } catch (Throwable unused) {
                        a(byteArrayOutputStream, fileChannel);
                        elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime2;
                        str = d;
                        sb = new StringBuilder();
                        sb.append("the compression time is ");
                        sb.append(elapsedRealtime);
                        Utils.a(str, sb.toString());
                        return true;
                    }
                } catch (Throwable unused2) {
                    fileChannel = channel;
                    a(byteArrayOutputStream, fileChannel);
                    elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime2;
                    str = d;
                    sb = new StringBuilder();
                    sb.append("the compression time is ");
                    sb.append(elapsedRealtime);
                    Utils.a(str, sb.toString());
                    return true;
                }
                sb.append("the compression time is ");
                sb.append(elapsedRealtime);
                Utils.a(str, sb.toString());
                return true;
            } catch (IOException e3) {
                e = e3;
                Utils.a(d, "there is an exception when trying to save the compressed image!");
                this.b = new CompressException("there is an exception when trying to save the compressed image!", this.e.f8811a, e);
                a(byteArrayOutputStream, fileChannel);
                Utils.a(d, "the compression time is " + (SystemClock.elapsedRealtime() - elapsedRealtime2));
                return false;
            }
        } catch (OutOfMemoryError unused3) {
            a("no enough memory!");
            return false;
        }
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, FileChannel fileChannel) {
        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (fileChannel != null) {
            fileChannel.close();
        }
    }

    private boolean b() {
        if (this.k <= 0) {
            return false;
        }
        File file = new File(this.e.f8811a);
        if (!file.exists()) {
            a("No such file : " + this.e.f8811a);
            return true;
        }
        long length = file.length();
        Utils.a(d, "original size : " + (length >> 10) + " KB");
        if (length > (this.k << 10)) {
            return false;
        }
        this.f8810a = this.e.f8811a;
        return true;
    }

    private void c() {
        if (this.c != null) {
            this.c.c.a(this);
        }
    }

    private void d() {
        if (this.c != null) {
            this.c.c.b(this);
        }
    }

    private void a(String str) {
        Utils.a(d, str);
        this.b = new CompressException(str, this.e.f8811a);
    }

    private String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f);
        if (!this.j || TextUtils.isEmpty(this.e.c)) {
            sb.append("biscuitCache");
            sb.append(System.currentTimeMillis());
        } else {
            sb.append(this.e.c);
        }
        sb.append(TextUtils.isEmpty(this.e.b) ? Utils.b : this.e.b);
        return sb.toString();
    }

    public void run() {
        boolean a2 = a();
        if (this.b == null || a2) {
            c();
        } else {
            d();
        }
    }

    private int a(BitmapFactory.Options options) {
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 < i3) {
            return a(1, i2);
        }
        return a(1, i3);
    }

    private int a(int i2, int i3) {
        float f2 = ((float) i3) / Utils.g;
        if (f2 <= 1.5f || f2 > 3.0f) {
            return f2 > 3.0f ? i2 << 2 : i2;
        }
        return i2 << 1;
    }

    private float b(BitmapFactory.Options options) {
        float pow;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int max = Math.max(i2, i3);
        int min = Math.min(i2, i3);
        float f2 = (float) min;
        float f3 = (float) max;
        float f4 = f3 * 1.0f;
        float f5 = f2 / f4;
        if (f5 < 0.5f) {
            int i4 = max / min;
            if (i4 >= 10) {
                pow = (i4 > 10 ? 0.01f : 0.03f) + (1.0f - (((float) ((int) Math.pow((double) i4, 2.0d))) / Utils.i));
                if (f2 * pow < Utils.j) {
                    return 1.0f;
                }
            } else if (f2 <= Utils.i) {
                return 1.0f;
            } else {
                pow = 1.0f - (f5 / 2.0f);
                if (f2 * pow <= Utils.i) {
                    return 1.0f;
                }
            }
            return pow;
        } else if (f3 > Utils.h) {
            return Utils.h / f4;
        } else {
            return 1.0f;
        }
    }

    private Bitmap a(@NonNull Bitmap bitmap, @NonNull Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            if (!bitmap.sameAs(createBitmap)) {
                return createBitmap;
            }
            return bitmap;
        } catch (OutOfMemoryError e2) {
            Utils.a(d, "transformBitmap: " + e2);
            return bitmap;
        }
    }
}
