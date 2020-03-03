package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BaseImageDecoder implements ImageDecoder {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f8480a = "Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]";
    protected static final String b = "Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]";
    protected static final String c = "Rotate image on %1$d° [%2$s]";
    protected static final String d = "Flip image horizontally [%s]";
    protected static final String e = "No stream for image [%s]";
    protected static final String f = "Image can't be decoded [%s]";
    protected final boolean g;

    public BaseImageDecoder(boolean z) {
        this.g = z;
    }

    public Bitmap a(ImageDecodingInfo imageDecodingInfo) throws IOException {
        InputStream inputStream;
        InputStream b2 = b(imageDecodingInfo);
        if (b2 == null) {
            L.d(e, imageDecodingInfo.a());
            return null;
        }
        try {
            ImageFileInfo a2 = a(b2, imageDecodingInfo);
            inputStream = b(b2, imageDecodingInfo);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, a(a2.f8482a, imageDecodingInfo));
                IoUtils.a((Closeable) inputStream);
                if (decodeStream != null) {
                    return a(decodeStream, imageDecodingInfo, a2.b.f8481a, a2.b.b);
                }
                L.d(f, imageDecodingInfo.a());
                return decodeStream;
            } catch (Throwable th) {
                th = th;
                IoUtils.a((Closeable) inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = b2;
            IoUtils.a((Closeable) inputStream);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public InputStream b(ImageDecodingInfo imageDecodingInfo) throws IOException {
        return imageDecodingInfo.g().getStream(imageDecodingInfo.b(), imageDecodingInfo.h());
    }

    /* access modifiers changed from: protected */
    public ImageFileInfo a(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        ExifInfo exifInfo;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        String b2 = imageDecodingInfo.b();
        if (!imageDecodingInfo.i() || !a(b2, options.outMimeType)) {
            exifInfo = new ExifInfo();
        } else {
            exifInfo = a(b2);
        }
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, exifInfo.f8481a), exifInfo);
    }

    private boolean a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && ImageDownloader.Scheme.ofUri(str) == ImageDownloader.Scheme.FILE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: int} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        r1 = r0;
        r0 = 90;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        r1 = r0;
        r0 = 270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        r1 = r0;
        r0 = 180;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.nostra13.universalimageloader.core.decode.BaseImageDecoder.ExifInfo a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            android.media.ExifInterface r2 = new android.media.ExifInterface     // Catch:{ IOException -> 0x002c }
            com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme r3 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE     // Catch:{ IOException -> 0x002c }
            java.lang.String r3 = r3.crop(r5)     // Catch:{ IOException -> 0x002c }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002c }
            java.lang.String r3 = "Orientation"
            int r2 = r2.getAttributeInt(r3, r1)     // Catch:{ IOException -> 0x002c }
            switch(r2) {
                case 1: goto L_0x0035;
                case 2: goto L_0x0036;
                case 3: goto L_0x0026;
                case 4: goto L_0x0025;
                case 5: goto L_0x001e;
                case 6: goto L_0x0018;
                case 7: goto L_0x0017;
                case 8: goto L_0x001f;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x0035
        L_0x0017:
            r0 = 1
        L_0x0018:
            r5 = 90
            r1 = r0
            r0 = 90
            goto L_0x0036
        L_0x001e:
            r0 = 1
        L_0x001f:
            r5 = 270(0x10e, float:3.78E-43)
            r1 = r0
            r0 = 270(0x10e, float:3.78E-43)
            goto L_0x0036
        L_0x0025:
            r0 = 1
        L_0x0026:
            r5 = 180(0xb4, float:2.52E-43)
            r1 = r0
            r0 = 180(0xb4, float:2.52E-43)
            goto L_0x0036
        L_0x002c:
            java.lang.String r2 = "Can't read EXIF tags from file [%s]"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r5
            com.nostra13.universalimageloader.utils.L.c(r2, r1)
        L_0x0035:
            r1 = 0
        L_0x0036:
            com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo r5 = new com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo
            r5.<init>(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.decode.BaseImageDecoder.a(java.lang.String):com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo");
    }

    /* access modifiers changed from: protected */
    public BitmapFactory.Options a(ImageSize imageSize, ImageDecodingInfo imageDecodingInfo) {
        int i;
        ImageScaleType e2 = imageDecodingInfo.e();
        if (e2 == ImageScaleType.NONE) {
            i = 1;
        } else if (e2 == ImageScaleType.NONE_SAFE) {
            i = ImageSizeUtils.a(imageSize);
        } else {
            i = ImageSizeUtils.a(imageSize, imageDecodingInfo.d(), imageDecodingInfo.f(), e2 == ImageScaleType.IN_SAMPLE_POWER_OF_2);
        }
        if (i > 1 && this.g) {
            L.a(f8480a, imageSize, imageSize.a(i), Integer.valueOf(i), imageDecodingInfo.a());
        }
        BitmapFactory.Options j = imageDecodingInfo.j();
        j.inSampleSize = i;
        return j;
    }

    /* access modifiers changed from: protected */
    public InputStream b(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException unused) {
            }
        }
        IoUtils.a((Closeable) inputStream);
        return b(imageDecodingInfo);
    }

    /* access modifiers changed from: protected */
    public Bitmap a(Bitmap bitmap, ImageDecodingInfo imageDecodingInfo, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType e2 = imageDecodingInfo.e();
        if (e2 == ImageScaleType.EXACTLY || e2 == ImageScaleType.EXACTLY_STRETCHED) {
            ImageSize imageSize = new ImageSize(bitmap.getWidth(), bitmap.getHeight(), i);
            float b2 = ImageSizeUtils.b(imageSize, imageDecodingInfo.d(), imageDecodingInfo.f(), e2 == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(b2, 1.0f) != 0) {
                matrix.setScale(b2, b2);
                if (this.g) {
                    L.a(b, imageSize, imageSize.a(b2), Float.valueOf(b2), imageDecodingInfo.a());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.g) {
                L.a(d, imageDecodingInfo.a());
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.g) {
                L.a(c, Integer.valueOf(i), imageDecodingInfo.a());
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    protected static class ExifInfo {

        /* renamed from: a  reason: collision with root package name */
        public final int f8481a;
        public final boolean b;

        protected ExifInfo() {
            this.f8481a = 0;
            this.b = false;
        }

        protected ExifInfo(int i, boolean z) {
            this.f8481a = i;
            this.b = z;
        }
    }

    protected static class ImageFileInfo {

        /* renamed from: a  reason: collision with root package name */
        public final ImageSize f8482a;
        public final ExifInfo b;

        protected ImageFileInfo(ImageSize imageSize, ExifInfo exifInfo) {
            this.f8482a = imageSize;
            this.b = exifInfo;
        }
    }
}
