package com.mi.global.bbs.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;
import com.mi.global.bbs.R;
import com.mi.log.LogUtil;
import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtils {
    private static final String DEFAULT_APK_PATH = "/MiCommunity/";
    private static final String DEFAULT_ATTACH_PATH = "attachments/";
    public static final String DEFAULT_IMAGE_CACHE_PATH = "Mi_Comm_image_cache/";
    private static final String DEFAULT_IMAGE_PATH = "Mi_Comm_image/";
    private static final String DEFAULT_IMAGE_TYPE = ".png";

    public static String getImageFileName(String str) {
        return getFolderName(DEFAULT_IMAGE_PATH) + str + DEFAULT_IMAGE_TYPE;
    }

    public static String getImageFileNameWithSuffix(String str) {
        return getFolderName(DEFAULT_IMAGE_PATH) + str;
    }

    public static String getAttachFileDir() {
        return getFolderName(DEFAULT_ATTACH_PATH);
    }

    public static String getImageCacheFileName(String str) {
        try {
            File file = new File(getFolderName(DEFAULT_IMAGE_CACHE_PATH) + str + DEFAULT_IMAGE_TYPE);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFolderName(String str) {
        String str2 = getAppPath() + str;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        return str2;
    }

    public static String getFileFullName(String str) {
        return getAppPath() + str;
    }

    public static String getAppPath() {
        String str;
        if (Environment.getExternalStorageState().equals("mounted")) {
            str = Environment.getExternalStorageDirectory() + DEFAULT_APK_PATH;
        } else {
            str = null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        return str;
    }

    public static void clearCacheImage() {
        File[] listFiles = new File(getFolderName(DEFAULT_IMAGE_CACHE_PATH)).listFiles();
        if (listFiles.length > 0) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    public static String getMIMEType(File file) {
        String name = file.getName();
        String lowerCase = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        if (lowerCase.equals("pdf")) {
            return "application/pdf";
        }
        if (lowerCase.equals("m4a") || lowerCase.equals("mp3") || lowerCase.equals(Constants.MID) || lowerCase.equals("xmf") || lowerCase.equals("ogg") || lowerCase.equals("wav")) {
            return "audio/*";
        }
        if (lowerCase.equals("3gp") || lowerCase.equals("mp4")) {
            return ShareObject.e;
        }
        if (lowerCase.equals("jpg") || lowerCase.equals("gif") || lowerCase.equals("png") || lowerCase.equals("jpeg") || lowerCase.equals("bmp")) {
            return ShareObject.d;
        }
        return lowerCase.equals("apk") ? "application/vnd.android.package-archive" : "*/*";
    }

    public static void saveImageToDisk(final Context context, final String str, final String str2) {
        new Thread(new Runnable() {
            public void run() {
                InputStream inputStream = FileUtils.getInputStream(str);
                byte[] bArr = new byte[1024];
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(str2);
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    new Handler().post(new Runnable() {
                        public void run() {
                            Toast.makeText(context, R.string.saved_successfully, 0);
                        }
                    });
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e2) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            Toast.makeText(context, R.string.saved_failed, 0);
                        }
                    });
                    e2.printStackTrace();
                    LogUtil.a("saveimage  fileutil>>>>  " + e2.toString());
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }).start();
    }

    public static InputStream getInputStream(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                return httpURLConnection.getInputStream();
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
