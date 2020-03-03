package com.tiqiaa.icontrol.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ForeImageLoader {
    private static final String TAG = "ForeImageLoader";
    public static final String URL_EXTENSION_EXTRA_CHAR = "x";
    private static String base_cache_path = (String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + "/zazaSdk/temp/cache");
    private static PooledMap<String, Bitmap> bitMapCache = null;
    private static int discCacheImgSize = 1000;
    private static int discCacheMaxSize = 2000;
    private static int discCacheMinSize = 100;
    static String[] extensions = {".jpg", ".jpeg", ".png", ".bmp", ".gif"};
    private static boolean isDiscCacheEnabled = true;
    private static boolean isMenCacheEnabled = true;
    private static int menCacheImgCount = 120;
    private static int menCacheMaxSize = 400;
    private static int menCacheminSize = 40;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap loadImg(java.lang.String r4) {
        /*
            com.tiqiaa.icontrol.util.PooledMap<java.lang.String, android.graphics.Bitmap> r0 = bitMapCache
            if (r0 != 0) goto L_0x0011
            boolean r0 = isMenCacheEnabled
            if (r0 == 0) goto L_0x0011
            com.tiqiaa.icontrol.util.PooledMap r0 = new com.tiqiaa.icontrol.util.PooledMap
            int r1 = menCacheImgCount
            r0.<init>(r1)
            bitMapCache = r0
        L_0x0011:
            r0 = 0
            boolean r1 = isMenCacheEnabled
            if (r1 == 0) goto L_0x0021
            com.tiqiaa.icontrol.util.PooledMap<java.lang.String, android.graphics.Bitmap> r0 = bitMapCache
            java.lang.Object r0 = r0.get(r4)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            if (r0 == 0) goto L_0x0021
            return r0
        L_0x0021:
            java.lang.String r1 = convertImgURLToLocalCachePath(r4)
            if (r1 == 0) goto L_0x0059
            boolean r2 = isDiscCacheEnabled     // Catch:{ Exception -> 0x0057 }
            if (r2 == 0) goto L_0x0059
            android.graphics.Bitmap r2 = loadImgFromLocal(r1)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r0 = "ForeImageLoader"
            java.lang.String r3 = "load_img...#########..从本地图片缓存文件夹获取到图片"
            com.tiqiaa.icontrol.util.LogUtil.i(r0, r3)     // Catch:{ Exception -> 0x0054 }
            if (r2 != 0) goto L_0x0052
            java.lang.String r0 = "ForeImageLoader"
            java.lang.String r3 = "load_img..~~~~~~~~...下载服务器上的图片并保存"
            com.tiqiaa.icontrol.util.LogUtil.v(r0, r3)     // Catch:{ Exception -> 0x0054 }
            java.io.InputStream r0 = com.tiqiaa.icontrol.util.UrlPoster.requestUrl(r4)     // Catch:{ Exception -> 0x0054 }
            saveFile(r1, r0)     // Catch:{ Exception -> 0x0054 }
            android.graphics.Bitmap r0 = loadImgFromLocal(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r1 = "ForeImageLoader"
            java.lang.String r2 = "load_img..@@@@@@@@@@...获取下载到的保存后的图片"
            com.tiqiaa.icontrol.util.LogUtil.v(r1, r2)     // Catch:{ Exception -> 0x0057 }
            goto L_0x0069
        L_0x0052:
            r0 = r2
            goto L_0x0069
        L_0x0054:
            r4 = move-exception
            r0 = r2
            goto L_0x0075
        L_0x0057:
            r4 = move-exception
            goto L_0x0075
        L_0x0059:
            java.lang.String r1 = "ForeImageLoader"
            java.lang.String r2 = "load_img..~~~~~~~~...直接下载并使用服务器图片"
            com.tiqiaa.icontrol.util.LogUtil.w(r1, r2)     // Catch:{ Exception -> 0x0057 }
            java.io.InputStream r1 = com.tiqiaa.icontrol.util.UrlPoster.requestUrl(r4)     // Catch:{ Exception -> 0x0057 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ Exception -> 0x0057 }
            r0 = r1
        L_0x0069:
            boolean r1 = isMenCacheEnabled     // Catch:{ Exception -> 0x0057 }
            if (r1 == 0) goto L_0x0078
            if (r0 == 0) goto L_0x0078
            com.tiqiaa.icontrol.util.PooledMap<java.lang.String, android.graphics.Bitmap> r1 = bitMapCache     // Catch:{ Exception -> 0x0057 }
            r1.put(r4, r0)     // Catch:{ Exception -> 0x0057 }
            goto L_0x0078
        L_0x0075:
            com.tiqiaa.icontrol.util.LogUtil.printException(r4)
        L_0x0078:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.icontrol.util.ForeImageLoader.loadImg(java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getBitmapFromCache(String str) {
        if (bitMapCache == null && isMenCacheEnabled) {
            bitMapCache = new PooledMap<>(menCacheImgCount);
        }
        Bitmap bitmap = null;
        try {
            if (isMenCacheEnabled) {
                bitmap = bitMapCache.get(str);
            }
            if (bitmap != null || !isDiscCacheEnabled) {
                return bitmap;
            }
            Bitmap loadImgFromLocal = loadImgFromLocal(convertImgURLToLocalCachePath(str));
            if (loadImgFromLocal != null) {
                try {
                    if (isMenCacheEnabled) {
                        bitMapCache.put(str, loadImgFromLocal);
                    }
                } catch (Exception e) {
                    e = e;
                    bitmap = loadImgFromLocal;
                    LogUtil.printException(e);
                    return bitmap;
                }
            }
            return loadImgFromLocal;
        } catch (Exception e2) {
            e = e2;
            LogUtil.printException(e);
            return bitmap;
        }
    }

    public static void displayImg(final ImageView imageView, final String str) {
        final AnonymousClass1 r0 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                Bitmap bitmap = (Bitmap) message.obj;
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                Bitmap loadImg = ForeImageLoader.loadImg(str);
                Message message = new Message();
                message.obj = loadImg;
                r0.sendMessage(message);
            }
        }).start();
    }

    private static String convertImgURLToLocalCachePath(String str) {
        if (str == null || str.length() == 0 || base_cache_path == null) {
            return null;
        }
        String str2 = String.valueOf(str) + "x";
        int lastIndexOf = str2.lastIndexOf("/");
        if (lastIndexOf < 0) {
            return null;
        }
        String str3 = String.valueOf(base_cache_path) + "/" + str2.substring(lastIndexOf + 1, str2.length());
        LogUtil.d(TAG, "convertToLocalCachePath......cache_path = " + str3);
        return str3;
    }

    public static void configMenCacheCount(int i) {
        if (i <= 0) {
            isMenCacheEnabled = false;
            return;
        }
        isMenCacheEnabled = true;
        if (i > menCacheminSize && i < menCacheMaxSize) {
            menCacheImgCount = i;
        }
    }

    public static void configDiscCacheCount(int i) {
        if (i <= 0) {
            isDiscCacheEnabled = false;
            return;
        }
        isDiscCacheEnabled = true;
        if (i > discCacheMinSize && i < discCacheMaxSize) {
            discCacheImgSize = i;
        }
    }

    public static void configDiscCachePath(String str) {
        base_cache_path = str;
    }

    public static Bitmap loadImgFromLocal(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
            file.setLastModified(System.currentTimeMillis());
            return decodeStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static File saveFile(String str, InputStream inputStream) throws IOException {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        } else {
            File file2 = new File(str.substring(0, str.lastIndexOf("/")));
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File[] listFiles = file2.listFiles();
            if (listFiles != null && listFiles.length >= discCacheImgSize) {
                File file3 = listFiles[0];
                File file4 = file3;
                for (File file5 : listFiles) {
                    if (file4.lastModified() > file5.lastModified()) {
                        file4 = file5;
                    }
                }
                file4.delete();
            }
        }
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[256];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return file;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }
}
