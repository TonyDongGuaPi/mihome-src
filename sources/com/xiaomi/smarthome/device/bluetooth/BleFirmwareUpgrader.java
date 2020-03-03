package com.xiaomi.smarthome.device.bluetooth;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.imagecache.ImageCacheUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.FileAsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import java.io.File;
import java.net.URI;
import java.util.HashMap;

public class BleFirmwareUpgrader {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static HashMap<String, UpgradeRequest> f15081a = new HashMap<>();
    private static Handler b;

    private interface UpgradeResponse extends Response.BleResponse<Bundle> {
        void a(int i);
    }

    private static Handler b() {
        if (b == null) {
            b = new Handler(Looper.getMainLooper());
        }
        return b;
    }

    public static void a(String str) {
        UpgradeRequest remove;
        if (!TextUtils.isEmpty(str) && (remove = f15081a.remove(str)) != null) {
            remove.a();
        }
    }

    public static void a(String str, final Response.FirmwareUpgradeResponse firmwareUpgradeResponse) {
        BluetoothLog.c(String.format("downloadFirmware v2", new Object[0]));
        a(str, (UpgradeResponse) new UpgradeResponse() {
            public void a(int i) {
                firmwareUpgradeResponse.onProgress(i);
            }

            /* renamed from: a */
            public void onResponse(int i, Bundle bundle) {
                String str;
                String str2 = null;
                if (bundle != null) {
                    str2 = bundle.getString("path");
                    str = bundle.getString("md5");
                } else {
                    str = null;
                }
                BluetoothLog.c(String.format("onResponse code = %d, path = %s, md5 = %s", new Object[]{Integer.valueOf(i), str2, str}));
                firmwareUpgradeResponse.onResponse(i, str2, str);
            }
        });
    }

    public static void a(String str, final Response.BleUpgradeResponse bleUpgradeResponse) {
        BluetoothLog.c(String.format("downloadFirmware v1", new Object[0]));
        a(str, (UpgradeResponse) new UpgradeResponse() {
            public void a(int i) {
                bleUpgradeResponse.onProgress(i);
            }

            /* renamed from: a */
            public void onResponse(int i, Bundle bundle) {
                String string = bundle != null ? bundle.getString("path") : null;
                BluetoothLog.c(String.format("onResponse code = %d, path = %s", new Object[]{Integer.valueOf(i), string}));
                bleUpgradeResponse.onResponse(i, string);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(final UpgradeResponse upgradeResponse, final int i, final String str) {
        b().post(new Runnable() {
            public void run() {
                if (i != 0) {
                    upgradeResponse.onResponse(i, null);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("path", str);
                bundle.putString("md5", BleFirmwareUpgrader.d(str));
                upgradeResponse.onResponse(0, bundle);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(final UpgradeResponse upgradeResponse, final int i) {
        b().post(new Runnable() {
            public void run() {
                upgradeResponse.a(i);
            }
        });
    }

    private static void a(final String str, final UpgradeResponse upgradeResponse) {
        if (TextUtils.isEmpty(str)) {
            upgradeResponse.onResponse(-3, null);
        } else if (f15081a.containsKey(str)) {
            upgradeResponse.onResponse(-13, null);
        } else {
            try {
                File c = c(str);
                Request a2 = new Request.Builder().a("GET").b(str).a();
                final String absolutePath = c.getAbsolutePath();
                HttpAsyncHandle a3 = HttpApi.a(a2, (AsyncHandler) new FileAsyncHandler(c) {
                    /* renamed from: a */
                    public void onSuccess(File file, okhttp3.Response response) {
                        BleFirmwareUpgrader.f15081a.remove(str);
                        BleFirmwareUpgrader.b(upgradeResponse, 0, absolutePath);
                    }

                    public void onFailure(Error error, Exception exc, okhttp3.Response response) {
                        FileUtils.d(absolutePath);
                        BleFirmwareUpgrader.f15081a.remove(str);
                        BleFirmwareUpgrader.b(upgradeResponse, -1, (String) null);
                    }

                    public void onProgress(long j, long j2) {
                        float f = j2 > 0 ? ((float) j) / ((float) j2) : 0.0f;
                        if (f < 0.0f) {
                            f = 0.0f;
                        } else if (1.0f < f) {
                            f = 1.0f;
                        }
                        BleFirmwareUpgrader.b(upgradeResponse, (int) (f * 100.0f));
                    }
                });
                if (a3 != null) {
                    f15081a.put(str, new UpgradeRequest(a3, upgradeResponse));
                }
            } catch (Throwable th) {
                upgradeResponse.onResponse(-1, null);
                BluetoothLog.a(th);
            }
        }
    }

    private static File c(String str) {
        String c = c();
        if (!TextUtils.isEmpty(c)) {
            File file = new File(c);
            if (!file.exists() && !file.mkdirs()) {
                throw new IllegalStateException("downloadDir not exist or mkdirs failed");
            } else if (!file.isFile() || (file.delete() && file.mkdirs())) {
                String str2 = c + File.separator + e(str);
                FileUtils.d(str2);
                File h = FileUtils.h(str2);
                if (h != null) {
                    return h;
                }
                FileUtils.d(str2);
                throw new IllegalStateException("create download file failed");
            } else {
                throw new IllegalStateException("downloadDir is file, delete failed or mkdirs failed");
            }
        } else {
            throw new IllegalStateException("downloadDir null");
        }
    }

    /* access modifiers changed from: private */
    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        File file = new File(str);
        return (!file.exists() || !file.isFile()) ? "" : MD5Util.a(file);
    }

    private static String c() {
        String str;
        if ("mounted".equals(Environment.getExternalStorageState()) || !ImageCacheUtils.b()) {
            str = ImageCacheUtils.a(SHApplication.getAppContext()).getPath();
        } else {
            str = SHApplication.getAppContext().getCacheDir().getPath() + File.separator + "app";
        }
        return new File(str, "ble").getAbsolutePath();
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return MD5Util.a(str);
        }
        String path = URI.create(str).getPath();
        int lastIndexOf = path.lastIndexOf("/");
        return lastIndexOf >= 0 ? path.substring(lastIndexOf + 1) : path;
    }

    private static class UpgradeRequest {

        /* renamed from: a  reason: collision with root package name */
        HttpAsyncHandle f15087a;
        UpgradeResponse b;

        UpgradeRequest(HttpAsyncHandle httpAsyncHandle, UpgradeResponse upgradeResponse) {
            this.f15087a = httpAsyncHandle;
            this.b = upgradeResponse;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.f15087a != null) {
                this.f15087a.a();
            }
        }
    }
}
