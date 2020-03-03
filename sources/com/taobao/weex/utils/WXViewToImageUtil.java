package com.taobao.weex.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.ColorInt;
import android.view.View;
import com.taobao.weex.WXSDKManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXViewToImageUtil {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public static int mBackgroundColor = 0;

    public interface OnImageSavedCallback {
        void onSaveFailed(String str);

        void onSaveSucceed(String str);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3725373431341813338L, "com/taobao/weex/utils/WXViewToImageUtil", 38);
        $jacocoData = a2;
        return a2;
    }

    public WXViewToImageUtil() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[37] = true;
    }

    public static void generateImage(final View view, final int i, @ColorInt int i2, final OnImageSavedCallback onImageSavedCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        mBackgroundColor = i2;
        $jacocoInit[1] = true;
        WXSDKManager.getInstance().getWXWorkThreadManager().post(new Thread(new Runnable() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(7886429643325153661L, "com/taobao/weex/utils/WXViewToImageUtil$1", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Bitmap bitmapFromImageView = WXViewToImageUtil.getBitmapFromImageView(view, i);
                if (bitmapFromImageView == null) {
                    if (onImageSavedCallback == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        onImageSavedCallback.onSaveFailed("Image is empty");
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                    return;
                }
                final String saveBitmapToGallery = WXViewToImageUtil.saveBitmapToGallery(view.getContext(), bitmapFromImageView, onImageSavedCallback);
                $jacocoInit[5] = true;
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AnonymousClass1 this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(7107528719383606168L, "com/taobao/weex/utils/WXViewToImageUtil$1$1", 6);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (onImageSavedCallback == null) {
                            $jacocoInit[1] = true;
                        } else {
                            $jacocoInit[2] = true;
                            onImageSavedCallback.onSaveSucceed(saveBitmapToGallery);
                            $jacocoInit[3] = true;
                            view.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(saveBitmapToGallery)));
                            $jacocoInit[4] = true;
                        }
                        $jacocoInit[5] = true;
                    }
                });
                $jacocoInit[6] = true;
            }
        }));
        $jacocoInit[2] = true;
    }

    public static String saveBitmapToGallery(Context context, Bitmap bitmap, OnImageSavedCallback onImageSavedCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        File file = new File(Environment.getExternalStorageDirectory(), "Weex");
        $jacocoInit[3] = true;
        if (file.exists()) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            file.mkdir();
            $jacocoInit[6] = true;
        }
        String str = System.currentTimeMillis() + ".jpg";
        $jacocoInit[7] = true;
        File file2 = new File(file, str);
        try {
            $jacocoInit[8] = true;
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            $jacocoInit[9] = true;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            $jacocoInit[10] = true;
            fileOutputStream.flush();
            $jacocoInit[11] = true;
            fileOutputStream.close();
            $jacocoInit[12] = true;
        } catch (FileNotFoundException e) {
            if (onImageSavedCallback == null) {
                $jacocoInit[13] = true;
            } else {
                $jacocoInit[14] = true;
                onImageSavedCallback.onSaveFailed("Image creation failed due to system reason");
                $jacocoInit[15] = true;
            }
            e.printStackTrace();
            $jacocoInit[16] = true;
        } catch (IOException e2) {
            if (onImageSavedCallback == null) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                onImageSavedCallback.onSaveFailed("Android IOException");
                $jacocoInit[19] = true;
            }
            e2.printStackTrace();
            try {
                $jacocoInit[20] = true;
            } catch (FileNotFoundException e3) {
                $jacocoInit[24] = true;
                e3.printStackTrace();
                $jacocoInit[25] = true;
            }
        }
        ContentResolver contentResolver = context.getContentResolver();
        $jacocoInit[21] = true;
        String absolutePath = file2.getAbsolutePath();
        $jacocoInit[22] = true;
        MediaStore.Images.Media.insertImage(contentResolver, absolutePath, str, (String) null);
        $jacocoInit[23] = true;
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath() + "/" + str)));
        $jacocoInit[26] = true;
        String absolutePath2 = file2.getAbsolutePath();
        $jacocoInit[27] = true;
        return absolutePath2;
    }

    public static Bitmap getBitmapFromImageView(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view.getWidth() <= 0) {
            $jacocoInit[28] = true;
        } else if (view.getHeight() > 0) {
            $jacocoInit[29] = true;
            view.setDrawingCacheEnabled(true);
            $jacocoInit[35] = true;
            Bitmap drawingCache = view.getDrawingCache();
            $jacocoInit[36] = true;
            return drawingCache;
        } else {
            $jacocoInit[30] = true;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        $jacocoInit[31] = true;
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        $jacocoInit[32] = true;
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        $jacocoInit[33] = true;
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        $jacocoInit[34] = true;
        view.setDrawingCacheEnabled(true);
        $jacocoInit[35] = true;
        Bitmap drawingCache2 = view.getDrawingCache();
        $jacocoInit[36] = true;
        return drawingCache2;
    }
}
