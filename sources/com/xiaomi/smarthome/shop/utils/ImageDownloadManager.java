package com.xiaomi.smarthome.shop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloadManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22191a = 10;
    private static final String b = "ImageDownloadManager";
    /* access modifiers changed from: private */
    public static final String c = (SHApplication.getAppContext().getCacheDir().getPath() + "/img-cache");
    /* access modifiers changed from: private */
    public Map<String, AsyncTask<Void, Void, Void>> d;

    private static class Instance {

        /* renamed from: a  reason: collision with root package name */
        static ImageDownloadManager f22195a = new ImageDownloadManager();

        private Instance() {
        }
    }

    public static ImageDownloadManager a() {
        return Instance.f22195a;
    }

    private ImageDownloadManager() {
        this.d = Collections.synchronizedMap(new HashMap());
        b();
    }

    public void b() {
        Miio.h(b, "notifyStart()");
        f();
    }

    private void f() {
        File file = new File(c);
        if (file.exists() && file.isDirectory()) {
            return;
        }
        if (file.mkdirs()) {
            Miio.h(b, "createCacheDirectoryIfNeeded(): create img-cache directory success");
        } else {
            Miio.h(b, "createCacheDirectoryIfNeeded(): create img-cache directory failed");
        }
    }

    public void c() {
        Miio.h(b, "notifyExit()");
        d();
        this.d.clear();
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                boolean unused = ImageDownloadManager.this.g();
                return null;
            }
        }, new Void[0]);
    }

    public boolean a(String str) {
        boolean e = e(str);
        Miio.h(b, "isDownloaded(): " + e + ", url = " + str);
        return e;
    }

    private boolean e(String str) {
        Miio.h(b, "findCacheImageByUrl(): " + str);
        return f(h(str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [int] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean f(java.lang.String r6) {
        /*
            r5 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = c
            r0.<init>(r1)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 == 0) goto L_0x002d
            boolean r1 = r0.isDirectory()
            if (r1 == 0) goto L_0x002d
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r3 = 0
        L_0x001a:
            if (r2 >= r1) goto L_0x002c
            r4 = r0[r2]
            java.lang.String r4 = r4.getName()
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0029
            r3 = 1
        L_0x0029:
            int r2 = r2 + 1
            goto L_0x001a
        L_0x002c:
            r2 = r3
        L_0x002d:
            java.lang.String r0 = "ImageDownloadManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "findCacheImageByKey(): "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r3 = ", diskKey = "
            r1.append(r3)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.xiaomi.smarthome.miio.Miio.h(r0, r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.utils.ImageDownloadManager.f(java.lang.String):boolean");
    }

    public boolean a(String str, final ImageView imageView) {
        Miio.h(b, "load(): url = " + str);
        if (str == null) {
            return false;
        }
        int g = g(str);
        if (g < 0) {
            b(str);
        } else if (g == 0 && imageView != null) {
            imageView.setImageResource(R.drawable.startup_icon);
        } else if (g == 1 && imageView != null) {
            final String str2 = c + "/" + h(str);
            Miio.h(b, "load(): diskFile = " + str2);
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Bitmap>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Bitmap doInBackground(Void... voidArr) {
                    try {
                        Bitmap decodeFile = BitmapFactory.decodeFile(str2);
                        if (decodeFile != null) {
                            return decodeFile;
                        }
                        try {
                            Miio.h(ImageDownloadManager.b, "load(): decode file failed: " + str2);
                            if (new File(str2).delete()) {
                                Miio.h(ImageDownloadManager.b, "load(): clean success, " + str2);
                                return decodeFile;
                            }
                            Miio.h(ImageDownloadManager.b, "load(): clean failed, " + str2);
                            return decodeFile;
                        } catch (OutOfMemoryError unused) {
                            return decodeFile;
                        }
                    } catch (OutOfMemoryError unused2) {
                        return null;
                    }
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Bitmap bitmap) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setImageResource(R.drawable.startup_icon);
                    }
                }
            }, new Void[0]);
            return true;
        }
        return false;
    }

    private int g(String str) {
        int i;
        if (this.d.get(str) != null) {
            i = 0;
        } else {
            i = e(str) ? 1 : -1;
        }
        Miio.h(b, "checkStatus(): " + i);
        return i;
    }

    public void b(String str) {
        int g;
        Miio.h(b, "fetch(): url = " + str);
        if (str != null && (g = g(str)) != 1) {
            if (!a(SHApplication.getAppContext())) {
                Miio.h(b, "fetch(): no available network");
                Toast.makeText(SHApplication.getAppContext(), R.string.device_shop_no_available_network, 0).show();
            } else if (g < 0) {
                DownloadTask downloadTask = new DownloadTask(str);
                this.d.put(str, downloadTask);
                downloadTask.execute(new Void[0]);
            }
        }
    }

    public void c(String str) {
        Miio.h(b, "cancel(): url = " + str);
        if (g(str) == 0) {
            ((DownloadTask) this.d.get(str)).cancel(false);
        }
    }

    public void d() {
        Miio.h(b, "cancelAll()");
        for (Map.Entry<String, AsyncTask<Void, Void, Void>> value : this.d.entrySet()) {
            ((AsyncTask) value.getValue()).cancel(false);
        }
    }

    /* access modifiers changed from: private */
    public String h(String str) {
        int lastIndexOf = str.lastIndexOf("id=");
        String substring = lastIndexOf > 0 ? str.substring(lastIndexOf + 3) : null;
        Miio.h(b, "generateDiskKey(): " + substring);
        return substring;
    }

    public String d(String str) {
        Miio.h(b, "MD5_32(): passwd = " + str);
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            instance.update(str.getBytes(), 0, str.length());
            byte[] digest = instance.digest();
            for (byte a2 : digest) {
                sb.append(a(a2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private String a(byte b2) {
        int i = (b2 & Byte.MAX_VALUE) + (b2 < 0 ? (byte) 128 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append(i < 16 ? "0" : "");
        sb.append(Integer.toHexString(i).toLowerCase());
        return sb.toString();
    }

    private class DownloadTask extends AsyncTask<Void, Void, Void> {
        private final String b;

        public DownloadTask(String str) {
            this.b = str;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            String str = ImageDownloadManager.c + "/" + ImageDownloadManager.this.h(this.b);
            Miio.h(ImageDownloadManager.b, "doInBackground(): url = " + this.b + ", diskPath = " + str);
            if (NetworkUtils.a(SHApplication.getAppContext(), this.b, new File(str), (NetworkUtils.OnDownloadProgress) null, false, false).b != 3) {
                return null;
            }
            ImageDownloadManager.this.d.remove(this.b);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean g() {
        Miio.h(b, "cleanCacheIfNeeded()");
        File file = new File(c);
        if (!file.exists() || !file.isDirectory() || file.listFiles().length <= 10) {
            return false;
        }
        for (File file2 : file.listFiles()) {
            if (file2.delete()) {
                Miio.h(b, "cleanCacheIfNeeded(): clean success, " + file2.getName());
            } else {
                Miio.h(b, "cleanCacheIfNeeded(): clean failed, " + file2.getName());
            }
        }
        return true;
    }

    private boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
