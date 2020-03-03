package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.taobao.weex.common.Constants;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class RNViewShotModule extends ReactContextBaseJavaModule {
    public static final String RNVIEW_SHOT = "RNViewShot";
    private static final String TEMP_FILE_PREFIX = "ReactNative-snapshot-image";
    private final ReactApplicationContext reactContext;

    public String getName() {
        return RNVIEW_SHOT;
    }

    public RNViewShotModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void releaseCapture(String str) {
        String path = Uri.parse(str).getPath();
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                File parentFile = file.getParentFile();
                if (parentFile.equals(this.reactContext.getExternalCacheDir()) || parentFile.equals(this.reactContext.getCacheDir())) {
                    file.delete();
                }
            }
        }
    }

    @ReactMethod
    public void captureRef(int i, ReadableMap readableMap, Promise promise) {
        int i2;
        Integer num;
        Integer num2;
        int i3 = i;
        ReadableMap readableMap2 = readableMap;
        DisplayMetrics displayMetrics = getReactApplicationContext().getResources().getDisplayMetrics();
        String string = readableMap2.getString(IjkMediaMeta.IJKM_KEY_FORMAT);
        if ("jpg".equals(string)) {
            i2 = 0;
        } else if ("webm".equals(string)) {
            i2 = 2;
        } else {
            i2 = ShareConstants.V.equals(string) ? -1 : 1;
        }
        double d = readableMap2.getDouble(Constants.Name.QUALITY);
        if (readableMap2.hasKey("width")) {
            double d2 = (double) displayMetrics.density;
            double d3 = readableMap2.getDouble("width");
            Double.isNaN(d2);
            num = Integer.valueOf((int) (d2 * d3));
        } else {
            num = null;
        }
        if (readableMap2.hasKey("height")) {
            double d4 = (double) displayMetrics.density;
            double d5 = readableMap2.getDouble("height");
            Double.isNaN(d4);
            num2 = Integer.valueOf((int) (d4 * d5));
        } else {
            num2 = null;
        }
        String string2 = readableMap2.getString("result");
        Boolean valueOf = Boolean.valueOf(readableMap2.getBoolean("snapshotContentContainer"));
        try {
            File createTempFile = ViewShot.Results.TEMP_FILE.equals(string2) ? createTempFile(getReactApplicationContext(), string) : null;
            Activity currentActivity = getCurrentActivity();
            ReactApplicationContext reactApplicationContext = this.reactContext;
            ViewShot viewShot = r2;
            Integer num3 = num;
            Integer num4 = num2;
            File file = createTempFile;
            UIManagerModule uIManagerModule = (UIManagerModule) this.reactContext.getNativeModule(UIManagerModule.class);
            ViewShot viewShot2 = new ViewShot(i, string, i2, d, num3, num4, file, string2, valueOf, reactApplicationContext, currentActivity, promise);
            uIManagerModule.addUIBlock(viewShot);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to snapshot view tag ");
            int i4 = i;
            sb.append(i4);
            Log.e(RNVIEW_SHOT, sb.toString(), th);
            promise.reject(ViewShot.f17488a, "Failed to snapshot view tag " + i4);
        }
    }

    @ReactMethod
    public void captureScreen(ReadableMap readableMap, Promise promise) {
        captureRef(-1, readableMap, promise);
    }

    private static class CleanTask extends GuardedAsyncTask<Void, Void> implements FilenameFilter {

        /* renamed from: a  reason: collision with root package name */
        private final File f17487a;
        private final File b;

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.f17487a = reactContext.getCacheDir();
            this.b = reactContext.getExternalCacheDir();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void doInBackgroundGuarded(Void... voidArr) {
            if (this.f17487a != null) {
                a(this.f17487a);
            }
            if (this.b != null) {
                a(this.b);
            }
        }

        public final boolean accept(File file, String str) {
            return str.startsWith(RNViewShotModule.TEMP_FILE_PREFIX);
        }

        private void a(@NonNull File file) {
            File[] listFiles = file.listFiles(this);
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.delete()) {
                        Log.d(RNViewShotModule.RNVIEW_SHOT, "deleted file: " + file2.getAbsolutePath());
                    }
                }
            }
        }
    }

    @NonNull
    private File createTempFile(@NonNull Context context, @NonNull String str) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null || (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
            externalCacheDir = cacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, "." + str, externalCacheDir);
    }
}
