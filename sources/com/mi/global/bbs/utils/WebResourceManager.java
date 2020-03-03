package com.mi.global.bbs.utils;

import android.content.Context;
import android.util.Log;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import java.io.File;

public class WebResourceManager {
    private static final String TAG = "com.mi.global.bbs.utils.WebResourceManager";
    private static WebResourceManager sInstance;

    private WebResourceManager() {
    }

    public static synchronized WebResourceManager getInstance() {
        WebResourceManager webResourceManager;
        synchronized (WebResourceManager.class) {
            if (sInstance == null) {
                sInstance = new WebResourceManager();
            }
            webResourceManager = sInstance;
        }
        return webResourceManager;
    }

    public void downloadLatest(Context context) {
        File file = null;
        try {
            Log.d(TAG, "Start to update web view resources");
            File file2 = new File(Constants.WebViewRes.getTempDirectoryRoot(context));
            try {
                if (!ensureExists(file2)) {
                    if (!Utils.Files.deleteFile(file2) && BBSApplication.DEBUG) {
                        Log.e(TAG, "Delete temp web view resources fails");
                    }
                } else if (!downloadPackageFile(context, false)) {
                    if (!Utils.Files.deleteFile(file2) && BBSApplication.DEBUG) {
                        Log.e(TAG, "Delete temp web view resources fails");
                    }
                } else if (!copyZipFile(context)) {
                    if (BBSApplication.DEBUG) {
                        Log.e(TAG, "Copy zip file failed");
                    }
                    if (!Utils.Files.deleteFile(file2) && BBSApplication.DEBUG) {
                        Log.e(TAG, "Delete temp web view resources fails");
                    }
                } else if (!extractPackageFile(context)) {
                    if (BBSApplication.DEBUG) {
                        Log.e(TAG, "Extract package files fails");
                    }
                    if (!Utils.Files.deleteFile(file2) && BBSApplication.DEBUG) {
                        Log.e(TAG, "Delete temp web view resources fails");
                    }
                } else {
                    cleanup(context);
                    if (Utils.Files.deleteFile(file2) || !BBSApplication.DEBUG) {
                        return;
                    }
                    Log.e(TAG, "Delete temp web view resources fails");
                }
            } catch (Exception e) {
                e = e;
                file = file2;
                try {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th = th;
                    file2 = file;
                    if (!Utils.Files.deleteFile(file2) && BBSApplication.DEBUG) {
                        Log.e(TAG, "Delete temp web view resources fails");
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Log.e(TAG, "Delete temp web view resources fails");
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (Utils.Files.deleteFile(file) || !BBSApplication.DEBUG) {
                return;
            }
            Log.e(TAG, "Delete temp web view resources fails");
        }
    }

    public void cleanup(Context context) {
        if (BBSApplication.DEBUG) {
            Log.d(TAG, "start cleanup process");
        }
        cleanFileRecursively(new File(Constants.WebViewRes.getTempDirectoryRoot(context)));
        cleanFileRecursively(new File(Constants.WebViewRes.getPackageFilePath(context)));
    }

    private boolean copyZipFile(Context context) {
        return Utils.Files.copyFile(Constants.WebViewRes.getTempPackageFilePath(context), Constants.WebViewRes.getPackageFilePath(context));
    }

    private boolean ensureExists(File file) {
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        if (!BBSApplication.DEBUG) {
            return false;
        }
        String str = TAG;
        Log.e(str, "create " + file.getName() + " failed");
        return false;
    }

    private boolean downloadPackageFile(Context context, boolean z) {
        return Utils.Files.downloadPackageFile(context, ConnectionHelper.getUpdateUrl(), Constants.WebViewRes.getTempPackageFilePath(context), z);
    }

    private boolean extractPackageFile(Context context) {
        return Utils.Files.unZip(Constants.WebViewRes.getPackageFilePath(context), Constants.WebViewRes.getDirectoryRoot(context));
    }

    private void cleanFileRecursively(File file) {
        if (file.isFile()) {
            if (BBSApplication.DEBUG) {
                Log.d(TAG, file + " is useless, delete");
            }
            file.delete();
        }
        if (file.isDirectory()) {
            for (File cleanFileRecursively : file.listFiles()) {
                cleanFileRecursively(cleanFileRecursively);
            }
            if (file.list().length == 0) {
                if (BBSApplication.DEBUG) {
                    Log.d(TAG, file + " is empty, delete");
                }
                file.delete();
            }
        }
    }
}
