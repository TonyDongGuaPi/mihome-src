package com.reactnative.camera.RCTCamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.facebook.react.bridge.ReadableMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MutableImage {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8649a = "RNCamera";
    private final byte[] b;
    private Bitmap c;
    private Metadata d;
    private boolean e = false;

    public MutableImage(byte[] bArr) {
        this.b = bArr;
        this.c = a(bArr);
    }

    public int a() {
        return this.c.getWidth();
    }

    public int b() {
        return this.c.getHeight();
    }

    public void c() throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(this.c, 0, 0, a(), b(), matrix, false);
        if (createBitmap != null) {
            this.c = createBitmap;
            return;
        }
        throw new ImageMutationFailedException("failed to mirror");
    }

    public void d() throws ImageMutationFailedException {
        int b2;
        try {
            ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) e().b(ExifIFD0Directory.class);
            if (exifIFD0Directory != null && exifIFD0Directory.a(274) && (b2 = exifIFD0Directory.b(274)) != 1) {
                b(b2);
                exifIFD0Directory.a(274, 1);
            }
        } catch (ImageProcessingException | MetadataException | IOException e2) {
            throw new ImageMutationFailedException("failed to fix orientation", e2);
        }
    }

    public void a(double d2) throws IllegalArgumentException {
        int i;
        int i2;
        int a2 = a();
        int b2 = b();
        double d3 = (double) b2;
        Double.isNaN(d3);
        double d4 = d3 * d2;
        double d5 = (double) a2;
        if (d4 > d5) {
            Double.isNaN(d5);
            i = (int) (d5 / d2);
            i2 = a2;
        } else {
            i2 = (int) d4;
            i = b2;
        }
        this.c = Bitmap.createBitmap(this.c, (a2 - i2) / 2, (b2 - i) / 2, i2, i);
    }

    private void b(int i) throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        switch (i) {
            case 1:
                return;
            case 2:
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.postRotate(180.0f);
                break;
            case 4:
                matrix.postRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.postRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.postRotate(90.0f);
                break;
            case 7:
                matrix.postRotate(270.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.postRotate(270.0f);
                break;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.c, 0, 0, a(), b(), matrix, false);
        if (createBitmap != null) {
            this.c = createBitmap;
            this.e = true;
            return;
        }
        throw new ImageMutationFailedException("failed to rotate");
    }

    private static Bitmap a(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream);
            byteArrayInputStream.close();
            return decodeStream;
        } catch (IOException e2) {
            throw new IllegalStateException("Will not happen", e2);
        }
    }

    public String a(int i) {
        return Base64.encodeToString(a(this.c, i), 0);
    }

    public void a(File file, ReadableMap readableMap, int i) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(a(this.c, i));
        fileOutputStream.close();
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            for (Directory next : e().a()) {
                for (Tag next2 : next.d()) {
                    exifInterface.setAttribute(next2.e(), next.u(next2.a()).toString());
                }
            }
            ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) e().b(ExifSubIFDDirectory.class);
            for (Tag next3 : exifSubIFDDirectory.d()) {
                exifInterface.setAttribute(next3.e().replaceAll(" ", ""), exifSubIFDDirectory.u(next3.a()).toString());
            }
            a(readableMap, exifInterface);
            if (this.e) {
                a(exifInterface);
            }
            exifInterface.saveAttributes();
        } catch (ImageProcessingException | IOException e2) {
            Log.e(f8649a, "failed to save exif data", e2);
        }
    }

    private void a(ExifInterface exifInterface) {
        exifInterface.setAttribute(android.support.media.ExifInterface.TAG_ORIENTATION, String.valueOf(1));
    }

    private void a(ReadableMap readableMap, ExifInterface exifInterface) {
        if (readableMap.hasKey(TtmlNode.TAG_METADATA)) {
            ReadableMap map = readableMap.getMap(TtmlNode.TAG_METADATA);
            if (map.hasKey("location")) {
                ReadableMap map2 = map.getMap("location");
                if (map2.hasKey("coords")) {
                    try {
                        ReadableMap map3 = map2.getMap("coords");
                        GPS.a(map3.getDouble("latitude"), map3.getDouble("longitude"), exifInterface);
                    } catch (IOException e2) {
                        Log.e(f8649a, "Couldn't write location data", e2);
                    }
                }
            }
        }
    }

    private Metadata e() throws ImageProcessingException, IOException {
        if (this.d == null) {
            this.d = ImageMetadataReader.a(new BufferedInputStream(new ByteArrayInputStream(this.b)), (long) this.b.length);
        }
        return this.d;
    }

    private static byte[] a(Bitmap bitmap, int i) throws OutOfMemoryError {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                str = "problem compressing jpeg";
                Log.e(f8649a, str, e2);
            }
        }
    }

    public static class ImageMutationFailedException extends Exception {
        public ImageMutationFailedException(String str, Throwable th) {
            super(str, th);
        }

        public ImageMutationFailedException(String str) {
            super(str);
        }
    }

    private static class GPS {
        private static String a(double d) {
            return d < 0.0d ? "S" : "N";
        }

        private static String b(double d) {
            return d < 0.0d ? "W" : android.support.media.ExifInterface.LONGITUDE_EAST;
        }

        private GPS() {
        }

        public static void a(double d, double d2, ExifInterface exifInterface) throws IOException {
            exifInterface.setAttribute(android.support.media.ExifInterface.TAG_GPS_LATITUDE, c(d));
            exifInterface.setAttribute(android.support.media.ExifInterface.TAG_GPS_LATITUDE_REF, a(d));
            exifInterface.setAttribute(android.support.media.ExifInterface.TAG_GPS_LONGITUDE, c(d2));
            exifInterface.setAttribute(android.support.media.ExifInterface.TAG_GPS_LONGITUDE_REF, b(d2));
        }

        private static String c(double d) {
            double abs = Math.abs(d);
            int i = (int) abs;
            double d2 = (double) i;
            Double.isNaN(d2);
            double d3 = (abs * 60.0d) - (d2 * 60.0d);
            int i2 = (int) d3;
            double d4 = (double) i2;
            Double.isNaN(d4);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i);
            stringBuffer.append("/1,");
            stringBuffer.append(i2);
            stringBuffer.append("/1,");
            stringBuffer.append((int) (((d3 * 60.0d) - (d4 * 60.0d)) * 1000.0d));
            stringBuffer.append("/1000,");
            return stringBuffer.toString();
        }
    }
}
