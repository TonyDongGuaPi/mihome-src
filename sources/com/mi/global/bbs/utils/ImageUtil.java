package com.mi.global.bbs.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.model.PhotographyExifModel;
import com.seek.biscuit.Biscuit;
import com.seek.biscuit.CompressListener;
import com.seek.biscuit.OnCompressCompletedListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ImageUtil {
    private static final String TAG = "ImageUtil";

    public static Bitmap decodeFile(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static void saveBitmap(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i) {
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

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
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

    public static File saveBitmap(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        String imageFileName = FileUtils.getImageFileName(str);
        if (TextUtils.isEmpty(imageFileName)) {
            return null;
        }
        File file = new File(imageFileName);
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        if (!file.exists()) {
            return null;
        }
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (fileOutputStream == null) {
            return null;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return file;
    }

    public static File checkExistBoforeSaveBitmap(BaseActivity baseActivity, String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        String imageFileName = FileUtils.getImageFileName(str);
        if (fileExists(imageFileName)) {
            baseActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(BBSApplication.getInstance(), BBSApplication.getInstance().getResources().getString(R.string.You_have_saved_this_picture), 0).show();
                }
            });
            return null;
        } else if (TextUtils.isEmpty(imageFileName)) {
            return null;
        } else {
            File file = new File(imageFileName);
            try {
                file.createNewFile();
            } catch (IOException unused) {
            }
            if (!file.exists()) {
                return null;
            }
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileOutputStream = null;
            }
            if (fileOutputStream == null) {
                return null;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            return file;
        }
    }

    public static File checkExistBoforeSaveBitmapAndExif(BaseActivity baseActivity, String str, Bitmap bitmap, String str2, String str3) {
        FileOutputStream fileOutputStream;
        String imageFileNameWithSuffix = FileUtils.getImageFileNameWithSuffix(str);
        if (fileExists(imageFileNameWithSuffix)) {
            baseActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(BBSApplication.getInstance(), BBSApplication.getInstance().getResources().getString(R.string.You_have_saved_this_picture), 0).show();
                }
            });
            return null;
        } else if (TextUtils.isEmpty(imageFileNameWithSuffix)) {
            return null;
        } else {
            File file = new File(imageFileNameWithSuffix);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!file.exists()) {
                return null;
            }
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                fileOutputStream = null;
            }
            if (fileOutputStream == null) {
                return null;
            }
            try {
                if (str3.equals(".webp")) {
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream);
                } else if (str3.equals(".png")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            try {
                saveExifPhotoGraphyWithExifModel(PhotographyExifModel.parseStringToExifModel(str2), imageFileNameWithSuffix);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            return file;
        }
    }

    public static boolean fileExists(String str) {
        return new File(str).exists();
    }

    public static String saveCacheBitmap(String str, Bitmap bitmap) {
        String str2;
        try {
            str2 = FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH) + str + ".jpeg";
            try {
                File file = new File(str2);
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            str2 = null;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static String saveCacheBitmap70(String str, Bitmap bitmap) {
        String str2;
        try {
            str2 = FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH) + str + ".jpeg";
            try {
                File file = new File(str2);
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            str2 = null;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static Bitmap compressSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        float f = (float) options.outWidth;
        int round = f > 776.0f ? Math.round(f / 776.0f) : 1;
        if (round > 0) {
            i = round;
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap compressQuality(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i2 = 90;
        while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            byteArrayOutputStream.reset();
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                i2 -= 10;
            }
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
    }

    public static Bitmap compressQuality(Bitmap bitmap, float f) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            int i = 90;
            int round = Math.round(((float) (byteArrayOutputStream.toByteArray().length / 1024)) * f);
            while (byteArrayOutputStream.toByteArray().length / 1024 > round && i > 0) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                i -= 10;
            }
            return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int readPictureDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static boolean isGIf(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(".gif");
    }

    public static Drawable getDrawableFromPath(Resources resources, String str) {
        Drawable createFromPath = Drawable.createFromPath(str);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (displayMetrics.widthPixels >= 1080) {
            ((BitmapDrawable) createFromPath).setTargetDensity(((int) displayMetrics.density) * 160);
        }
        return createFromPath;
    }

    public static void compress(Context context, List<String> list, CompressListener compressListener) {
        Biscuit.a(context).a(list).c(false).a(compressListener).a(FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH)).a(100).a().a();
    }

    public static void compress(Context context, List<String> list, OnCompressCompletedListener onCompressCompletedListener) {
        Biscuit.a(context).a(list).c(false).a(onCompressCompletedListener).a(FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH)).a(100).a().a();
    }

    public static String processExifTransform(String str) {
        Bitmap bitmap;
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 0);
            Matrix matrix = null;
            int width = decodeFile.getWidth();
            int height = decodeFile.getHeight();
            int i = width >> 1;
            int i2 = height >> 1;
            switch (attributeInt) {
                case 2:
                    matrix = new Matrix();
                    matrix.setTranslate((float) width, 0.0f);
                    matrix.setScale(-1.0f, 1.0f);
                    break;
                case 3:
                    matrix = new Matrix();
                    matrix.setRotate(180.0f, (float) i, (float) i2);
                    break;
                case 4:
                    matrix = new Matrix();
                    matrix.setTranslate(0.0f, (float) height);
                    matrix.setScale(1.0f, -1.0f);
                    break;
                case 5:
                    matrix = new Matrix();
                    matrix.setTranslate((float) width, (float) height);
                    matrix.setScale(-1.0f, -1.0f);
                    break;
                case 6:
                    matrix = new Matrix();
                    matrix.postRotate(90.0f, (float) i, (float) i2);
                    matrix.postTranslate((float) (i2 - i), (float) (i - i2));
                    break;
                case 7:
                    break;
                case 8:
                    matrix = new Matrix();
                    matrix.postRotate(270.0f, (float) i, (float) i2);
                    matrix.postTranslate((float) (i2 - i), (float) (i - i2));
                    break;
            }
            int i3 = height;
            height = width;
            width = i3;
            if (matrix == null || matrix.isIdentity()) {
                bitmap = decodeFile;
            } else {
                bitmap = Bitmap.createBitmap(width, height, decodeFile.getConfig());
                new Canvas(bitmap).drawBitmap(decodeFile, matrix, new Paint());
                decodeFile.recycle();
            }
            return saveBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String saveBitmap(Bitmap bitmap) {
        String str = FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH) + "temp.jpeg";
        try {
            File file = new File(str);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004e, code lost:
        r4 = r1[r3].get(r7).toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveExifPhotoGraphy(java.lang.String r6, java.lang.String r7) throws java.lang.Exception {
        /*
            android.media.ExifInterface r0 = new android.media.ExifInterface
            r0.<init>(r6)
            android.media.ExifInterface r6 = new android.media.ExifInterface
            r6.<init>(r7)
            java.lang.Class<android.media.ExifInterface> r7 = android.media.ExifInterface.class
            java.lang.reflect.Field[] r1 = r7.getFields()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r3 = "TAG_MAKE"
            r2.add(r3)
            java.lang.String r3 = "TAG_MODEL"
            r2.add(r3)
            java.lang.String r3 = "TAG_FOCAL_LENGTH"
            r2.add(r3)
            java.lang.String r3 = "TAG_APERTURE"
            r2.add(r3)
            java.lang.String r3 = "TAG_EXPOSURE_TIME"
            r2.add(r3)
            java.lang.String r3 = "TAG_ISO"
            r2.add(r3)
            java.lang.String r3 = "TAG_DATETIME"
            r2.add(r3)
            r3 = 0
        L_0x0039:
            int r4 = r1.length
            if (r3 >= r4) goto L_0x0064
            r4 = r1[r3]
            java.lang.String r4 = r4.getName()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0061
            boolean r4 = r2.contains(r4)
            if (r4 == 0) goto L_0x0061
            r4 = r1[r3]
            java.lang.Object r4 = r4.get(r7)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = r0.getAttribute(r4)
            if (r5 == 0) goto L_0x0061
            r6.setAttribute(r4, r5)
        L_0x0061:
            int r3 = r3 + 1
            goto L_0x0039
        L_0x0064:
            r6.saveAttributes()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.ImageUtil.saveExifPhotoGraphy(java.lang.String, java.lang.String):void");
    }

    public static void saveExifPhotoGraphyWithExifModel(PhotographyExifModel photographyExifModel, String str) throws Exception {
        ExifInterface exifInterface = new ExifInterface(str);
        Class<ExifInterface> cls = ExifInterface.class;
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (!TextUtils.isEmpty(name) && name.contains("TAG_MAKE")) {
                String obj = fields[i].get(cls).toString();
                if (photographyExifModel.getManufacturer() != null) {
                    exifInterface.setAttribute(obj, photographyExifModel.getManufacturer());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_MODEL")) {
                String obj2 = fields[i].get(cls).toString();
                if (photographyExifModel.getModel() != null) {
                    exifInterface.setAttribute(obj2, photographyExifModel.getModel());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_FOCAL_LENGTH")) {
                String obj3 = fields[i].get(cls).toString();
                if (photographyExifModel.getFocalLength() != null) {
                    exifInterface.setAttribute(obj3, photographyExifModel.getFocalLength());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_APERTURE")) {
                String obj4 = fields[i].get(cls).toString();
                if (photographyExifModel.getAperture() != null) {
                    exifInterface.setAttribute(obj4, photographyExifModel.getAperture());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_EXPOSURE_TIME")) {
                String obj5 = fields[i].get(cls).toString();
                if (photographyExifModel.getExposure_time() != null) {
                    exifInterface.setAttribute(obj5, photographyExifModel.getExposure_time());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_ISO")) {
                String obj6 = fields[i].get(cls).toString();
                if (photographyExifModel.getIso() != null) {
                    exifInterface.setAttribute(obj6, photographyExifModel.getIso());
                }
            }
            if (!TextUtils.isEmpty(name) && name.contains("TAG_DATETIME")) {
                String obj7 = fields[i].get(cls).toString();
                if (photographyExifModel.getDate() != null) {
                    exifInterface.setAttribute(obj7, photographyExifModel.getDate());
                }
            }
        }
        exifInterface.saveAttributes();
    }
}
