package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.file.FileUtil;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloadManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18675a = 10;
    private static final String b = "ImageDownloadManager";
    /* access modifiers changed from: private */
    public static final String c = (SHApplication.getAppContext().getCacheDir().getPath() + "/img-cache");
    /* access modifiers changed from: private */
    public Map<String, AsyncTask<Void, Void, Bitmap>> d;

    public interface ImageCallback {
        void a();

        void a(Bitmap bitmap);
    }

    private static class Instance {

        /* renamed from: a  reason: collision with root package name */
        static ImageDownloadManager f18680a = new ImageDownloadManager();

        private Instance() {
        }
    }

    public static ImageDownloadManager a() {
        return Instance.f18680a;
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
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(String str) {
        boolean f = f(str);
        Miio.h(b, "isDownloaded(): " + f + ", url = " + str);
        return f;
    }

    private boolean f(String str) {
        Miio.h(b, "findCacheImageByUrl(): " + str);
        return g(i(str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [int] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean g(java.lang.String r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.ImageDownloadManager.g(java.lang.String):boolean");
    }

    public void a(String str, @NonNull final ImageCallback imageCallback) {
        Miio.h(b, "load(): url = " + str);
        if (str == null) {
            imageCallback.a();
            return;
        }
        int h = h(str);
        if (h < 0) {
            b(str, imageCallback);
        } else if (h == 0) {
            imageCallback.a();
        } else if (h == 1) {
            final String str2 = c + "/" + i(str);
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
                    if (bitmap == null) {
                        imageCallback.a();
                    } else {
                        imageCallback.a(bitmap);
                    }
                }
            }, new Void[0]);
        }
    }

    public boolean a(String str, final ImageView imageView) {
        Miio.h(b, "load(): url = " + str);
        if (str == null) {
            return false;
        }
        int h = h(str);
        if (h < 0) {
            b(str, (ImageCallback) null);
        } else if (h == 0 && imageView != null) {
            imageView.setImageResource(R.drawable.startup_icon);
        } else if (h == 1 && imageView != null) {
            final String str2 = c + "/" + i(str);
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

    private int h(String str) {
        int i;
        if (this.d.get(str) != null) {
            i = 0;
        } else {
            i = f(str) ? 1 : -1;
        }
        Miio.h(b, "checkStatus(): " + i);
        return i;
    }

    public void b(String str, ImageCallback imageCallback) {
        int h;
        Miio.h(b, "fetch(): url = " + str);
        if (str != null && (h = h(str)) != 1) {
            if (!a(SHApplication.getAppContext())) {
                Miio.h(b, "fetch(): no available network");
            } else if (h < 0) {
                DownloadTask downloadTask = new DownloadTask(str, imageCallback);
                this.d.put(str, downloadTask);
                downloadTask.execute(new Void[0]);
            }
        }
    }

    @WorkerThread
    public synchronized Bitmap b(String str) {
        Bitmap bitmap;
        bitmap = null;
        if (!TextUtils.isEmpty(str)) {
            String str2 = c + "/" + i(str);
            try {
                File file = new File(str2);
                if (file.exists()) {
                    bitmap = BitmapFactory.decodeFile(str2);
                }
                if (bitmap == null) {
                    if (file.exists()) {
                        FileUtil.b(file);
                    }
                    String str3 = str2 + "_temp";
                    File file2 = new File(str3);
                    if (NetworkUtils.a(SHApplication.getAppContext(), str, file2, (NetworkUtils.OnDownloadProgress) null, false, false).b == 3) {
                        Bitmap decodeFile = BitmapFactory.decodeFile(str3);
                        try {
                            file2.renameTo(file);
                            bitmap = decodeFile;
                        } catch (Exception e) {
                            Exception exc = e;
                            bitmap = decodeFile;
                            e = exc;
                            e.printStackTrace();
                            return bitmap;
                        }
                    }
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return bitmap;
            }
        }
        return bitmap;
    }

    @WorkerThread
    public synchronized String c(String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = c + "/" + i(str);
            try {
                if (new File(str2).exists()) {
                    return str2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void d(String str) {
        Miio.h(b, "cancel(): url = " + str);
        if (h(str) == 0) {
            ((DownloadTask) this.d.get(str)).cancel(false);
        }
    }

    public void d() {
        Miio.h(b, "cancelAll()");
        for (Map.Entry<String, AsyncTask<Void, Void, Bitmap>> value : this.d.entrySet()) {
            ((AsyncTask) value.getValue()).cancel(false);
        }
    }

    /* access modifiers changed from: private */
    public String i(String str) {
        String str2;
        int lastIndexOf = str.lastIndexOf("id=");
        if (lastIndexOf > 0) {
            str2 = str.substring(lastIndexOf + 3);
        } else {
            str2 = MD5.a(str);
        }
        Miio.h(b, "generateDiskKey(): " + str2);
        return str2;
    }

    public String e(String str) {
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

    private class DownloadTask extends AsyncTask<Void, Void, Bitmap> {
        private final String b;
        private final ImageCallback c;

        public DownloadTask(String str, ImageCallback imageCallback) {
            this.b = str;
            this.c = imageCallback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Void... voidArr) {
            String str = ImageDownloadManager.c + "/" + ImageDownloadManager.this.i(this.b);
            Miio.h(ImageDownloadManager.b, "doInBackground(): url = " + this.b + ", diskPath = " + str);
            if (NetworkUtils.a(SHApplication.getAppContext(), this.b, new File(str), (NetworkUtils.OnDownloadProgress) null, false, false).b != 3) {
                return null;
            }
            ImageDownloadManager.this.d.remove(this.b);
            try {
                Bitmap decodeFile = BitmapFactory.decodeFile(str);
                if (decodeFile != null) {
                    return decodeFile;
                }
                try {
                    if (new File(str).delete()) {
                        Miio.h(ImageDownloadManager.b, "load(): clean success, " + str);
                        return decodeFile;
                    }
                    Miio.h(ImageDownloadManager.b, "load(): clean failed, " + str);
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
            if (this.c != null) {
                if (bitmap != null) {
                    this.c.a(bitmap);
                } else {
                    this.c.a();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean g() {
        Miio.h(b, "cleanCacheIfNeeded()");
        File file = new File(c);
        if (!file.exists() || !file.isDirectory() || file.listFiles() == null || file.listFiles().length <= 10) {
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
