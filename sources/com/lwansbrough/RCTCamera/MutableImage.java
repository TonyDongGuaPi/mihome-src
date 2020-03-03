package com.lwansbrough.RCTCamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.media.ExifInterface;
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
    private static final String TAG = "RNCamera";
    private Bitmap currentRepresentation;
    private boolean hasBeenReoriented = false;
    private boolean hasBeenScaled = false;
    private final byte[] originalImageData;
    private Metadata originalImageMetaData;

    public MutableImage(byte[] bArr) {
        this.originalImageData = bArr;
        this.currentRepresentation = toBitmap(bArr);
    }

    public int getWidth() {
        return this.currentRepresentation.getWidth();
    }

    public int getHeight() {
        return this.currentRepresentation.getHeight();
    }

    public void mirrorImage() throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap != null) {
            this.currentRepresentation = createBitmap;
            return;
        }
        throw new ImageMutationFailedException("failed to mirror");
    }

    public void fixOrientation() throws ImageMutationFailedException {
        int b;
        try {
            ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) originalImageMetaData().b(ExifIFD0Directory.class);
            if (exifIFD0Directory != null && exifIFD0Directory.a(274) && (b = exifIFD0Directory.b(274)) != 1) {
                rotate(b);
                exifIFD0Directory.a(274, 1);
            }
        } catch (ImageProcessingException | MetadataException | IOException e) {
            throw new ImageMutationFailedException("failed to fix orientation", e);
        }
    }

    public void cropToPreview(double d) throws IllegalArgumentException {
        int i;
        int i2;
        int width = getWidth();
        int height = getHeight();
        double d2 = (double) height;
        Double.isNaN(d2);
        double d3 = d2 * d;
        double d4 = (double) width;
        if (d3 > d4) {
            Double.isNaN(d4);
            i = (int) (d4 / d);
            i2 = width;
        } else {
            i2 = (int) d3;
            i = height;
        }
        this.currentRepresentation = Bitmap.createBitmap(this.currentRepresentation, (width - i2) / 2, (height - i) / 2, i2, i);
    }

    private void rotate(int i) throws ImageMutationFailedException {
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
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap != null) {
            this.currentRepresentation = createBitmap;
            this.hasBeenReoriented = true;
            return;
        }
        throw new ImageMutationFailedException("failed to rotate");
    }

    private static Bitmap toBitmap(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream);
            byteArrayInputStream.close();
            return decodeStream;
        } catch (IOException e) {
            throw new IllegalStateException("Will not happen", e);
        }
    }

    public String toBase64(int i, int i2, int i3) {
        return Base64.encodeToString(toJpeg(this.currentRepresentation, i, i2, i3), 0);
    }

    public void writeDataToFile(File file, ReadableMap readableMap, int i, int i2, int i3) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(toJpeg(this.currentRepresentation, i, i2, i3));
        fileOutputStream.close();
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            for (Directory next : originalImageMetaData().a()) {
                for (Tag next2 : next.d()) {
                    exifInterface.setAttribute(next2.e(), next.u(next2.a()).toString());
                }
            }
            ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) originalImageMetaData().b(ExifSubIFDDirectory.class);
            for (Tag next3 : exifSubIFDDirectory.d()) {
                int a2 = next3.a();
                String replaceAll = next3.e().replaceAll(" ", "");
                Object u = exifSubIFDDirectory.u(a2);
                if (replaceAll.equals(ExifInterface.TAG_EXPOSURE_TIME)) {
                    exifInterface.setAttribute(replaceAll, convertExposureTimeToDoubleFormat(u.toString()));
                } else {
                    exifInterface.setAttribute(replaceAll, u.toString());
                }
            }
            writeLocationExifData(readableMap, exifInterface);
            if (this.hasBeenReoriented) {
                rewriteOrientation(exifInterface);
            }
            exifInterface.saveAttributes();
        } catch (ImageProcessingException | IOException e) {
            Log.e(TAG, "failed to save exif data", e);
        }
    }

    private String convertExposureTimeToDoubleFormat(String str) {
        if (!str.contains("/")) {
            return "";
        }
        return Double.toString(1.0d / Double.parseDouble(str.split("/")[1]));
    }

    private void rewriteOrientation(ExifInterface exifInterface) {
        exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(1));
    }

    private void writeLocationExifData(ReadableMap readableMap, ExifInterface exifInterface) {
        if (readableMap.hasKey(TtmlNode.TAG_METADATA)) {
            ReadableMap map = readableMap.getMap(TtmlNode.TAG_METADATA);
            if (map.hasKey("location")) {
                ReadableMap map2 = map.getMap("location");
                if (map2.hasKey("coords")) {
                    try {
                        ReadableMap map3 = map2.getMap("coords");
                        GPS.writeExifData(map3.getDouble("latitude"), map3.getDouble("longitude"), exifInterface);
                    } catch (IOException e) {
                        Log.e(TAG, "Couldn't write location data", e);
                    }
                }
            }
        }
    }

    private Metadata originalImageMetaData() throws ImageProcessingException, IOException {
        if (this.originalImageMetaData == null) {
            this.originalImageMetaData = ImageMetadataReader.a(new BufferedInputStream(new ByteArrayInputStream(this.originalImageData)), (long) this.originalImageData.length);
        }
        return this.originalImageMetaData;
    }

    private byte[] toJpeg(Bitmap bitmap, int i, int i2, int i3) throws OutOfMemoryError {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i3 <= 0 || i2 <= 0) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        } else {
            Bitmap scale = scale(bitmap, i2, i3);
            scale.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            this.currentRepresentation = scale;
            this.hasBeenScaled = true;
        }
        try {
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                str = "problem compressing jpeg";
                Log.e(TAG, str, e);
            }
        }
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) throws OutOfMemoryError {
        float max = (i2 <= 0 || i <= 0) ? 1.0f : Math.max(((float) bitmap.getWidth()) / ((float) i), ((float) bitmap.getHeight()) / ((float) i2));
        if (max <= 1.0f) {
            return bitmap;
        }
        int width = (int) (((float) bitmap.getWidth()) / max);
        int height = (int) (((float) bitmap.getHeight()) / max);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        new Canvas(createBitmap).drawBitmap(bitmap, (Rect) null, new Rect(0, 0, width, height), (Paint) null);
        return createBitmap;
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
        private static String latitudeRef(double d) {
            return d < 0.0d ? "S" : "N";
        }

        private static String longitudeRef(double d) {
            return d < 0.0d ? "W" : ExifInterface.LONGITUDE_EAST;
        }

        private GPS() {
        }

        public static void writeExifData(double d, double d2, ExifInterface exifInterface) throws IOException {
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, toDegreeMinuteSecods(d));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, latitudeRef(d));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, toDegreeMinuteSecods(d2));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, longitudeRef(d2));
        }

        private static String toDegreeMinuteSecods(double d) {
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
