package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.httputil.util.freeflow.FreeFlowServiceUtil;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.player.MD5;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public class FileUtilBase {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f2255a = null;
    private static String b = null;
    private static String c = null;
    private static ExecutorService d = Executors.newCachedThreadPool();
    /* access modifiers changed from: private */
    public static Bitmap e = null;
    /* access modifiers changed from: private */
    public static Map<String, Set<RequestCallBackModel>> f = new ConcurrentHashMap();
    private static Config g = null;
    private static OkHttpClient h = null;
    private static Cache i = null;
    private static final String j = "picasso-cache";
    private static final int k = 5242880;
    private static final int l = 52428800;

    public interface IBitmapDownOkCallBack {
        void a(Bitmap bitmap);
    }

    private static int a(int i2) {
        if (i2 == 6) {
            return 90;
        }
        if (i2 == 3) {
            return 180;
        }
        return i2 == 8 ? 270 : 0;
    }

    public static String a(Context context, String str) {
        String str2;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            str2 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/files/soundtiepian";
        } else {
            str2 = context.getFilesDir().getPath() + "/soundtiepian";
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2 + File.separator + str;
    }

    public static String b(Context context, String str) {
        String str2;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            str2 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/files/assets";
        } else {
            str2 = context.getFilesDir().getPath() + "/assets";
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2 + File.separator + str;
    }

    static class RequestCallBackModel {

        /* renamed from: a  reason: collision with root package name */
        public IBitmapDownOkCallBack f2258a;
        public int b;
        public int c;

        public RequestCallBackModel(IBitmapDownOkCallBack iBitmapDownOkCallBack, int i, int i2) {
            this.f2258a = iBitmapDownOkCallBack;
            this.b = i;
            this.c = i2;
        }
    }

    private static String b(Track track) {
        if (!BaseUtil.d()) {
            return c(track);
        }
        String str = "";
        if (track == null) {
            return str;
        }
        if (!TextUtils.isEmpty(track.V())) {
            str = track.V();
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return track.a() + "";
    }

    /* access modifiers changed from: private */
    public static String c(Track track) {
        if (track == null) {
            return "";
        }
        if (!TextUtils.isEmpty(track.V())) {
            return track.V();
        }
        if (!TextUtils.isEmpty(track.U())) {
            return track.U();
        }
        if (!TextUtils.isEmpty(track.T())) {
            return track.T();
        }
        return "";
    }

    public static void a(Context context, Track track, int i2, int i3, IBitmapDownOkCallBack iBitmapDownOkCallBack) {
        a(context, track, b(track), i2, i3, iBitmapDownOkCallBack, true);
    }

    public static void b(Context context, Track track, int i2, int i3, IBitmapDownOkCallBack iBitmapDownOkCallBack) {
        a(context, track, b(track), i2, i3, iBitmapDownOkCallBack, false);
    }

    private static void a(Context context, Track track, String str, int i2, int i3, IBitmapDownOkCallBack iBitmapDownOkCallBack, boolean z) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            if (iBitmapDownOkCallBack != null) {
                iBitmapDownOkCallBack.a((Bitmap) null);
            }
        } else if (context != null) {
            synchronized (FileUtilBase.class) {
                if (z) {
                    try {
                        if (d == null || d.isShutdown()) {
                            d = Executors.newCachedThreadPool();
                        }
                        d.execute(new GetBitmapRunnable(context, str, i2, i3, iBitmapDownOkCallBack, track));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    boolean z2 = false;
                    if (!str.equals(f2255a)) {
                        f2255a = str;
                        f.clear();
                        e = null;
                    } else if (!(e == null || iBitmapDownOkCallBack == null)) {
                        iBitmapDownOkCallBack.a(e);
                        return;
                    }
                    Set set = f.get(str);
                    if (set == null) {
                        set = new CopyOnWriteArraySet();
                        z2 = true;
                    }
                    set.add(new RequestCallBackModel(iBitmapDownOkCallBack, i2, i3));
                    f.put(str, set);
                    if (z2) {
                        if (d == null || d.isShutdown()) {
                            d = Executors.newCachedThreadPool();
                        }
                        try {
                            d.execute(new GetBitmapRunnable(context, str, track));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        } else if (iBitmapDownOkCallBack != null) {
            iBitmapDownOkCallBack.a((Bitmap) null);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Bitmap bitmap, String str) {
        Set<RequestCallBackModel> set = f.get(str);
        if (set != null) {
            for (RequestCallBackModel requestCallBackModel : set) {
                if (requestCallBackModel == null || requestCallBackModel.f2258a == null || (bitmap != null && (bitmap == null || bitmap.isRecycled()))) {
                    if (requestCallBackModel != null) {
                        a(requestCallBackModel.f2258a, (Bitmap) null);
                    }
                } else if (requestCallBackModel.b <= 0 || requestCallBackModel.c <= 0) {
                    a(requestCallBackModel.f2258a, bitmap);
                } else {
                    a(requestCallBackModel.f2258a, ThumbnailUtils.extractThumbnail(bitmap, requestCallBackModel.b, requestCallBackModel.c));
                }
            }
            f.remove(str);
        }
    }

    static void a(final IBitmapDownOkCallBack iBitmapDownOkCallBack, final Bitmap bitmap) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (iBitmapDownOkCallBack == null) {
                    return;
                }
                if (bitmap == null || bitmap.isRecycled()) {
                    iBitmapDownOkCallBack.a((Bitmap) null);
                    return;
                }
                try {
                    iBitmapDownOkCallBack.a(bitmap);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    static class GetBitmapRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f2257a;
        int b;
        IBitmapDownOkCallBack c;
        private Context d;
        private String e;
        private Track f;
        private long g;

        public GetBitmapRunnable(Context context, String str, int i, int i2, IBitmapDownOkCallBack iBitmapDownOkCallBack, Track track) {
            this(context, str, track);
            this.f2257a = i;
            this.b = i2;
            this.c = iBitmapDownOkCallBack;
        }

        public GetBitmapRunnable(Context context, String str, int i, int i2, Track track) {
            this(context, str, i, i2, (IBitmapDownOkCallBack) null, track);
        }

        public GetBitmapRunnable(Context context, String str, Track track) {
            this.d = context;
            this.e = str;
            this.f = track;
            if (track != null) {
                this.g = track.a();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0091, code lost:
            if ((r6.g + "").equals(com.ximalaya.ting.android.opensdk.util.FileUtilBase.b()) != false) goto L_0x009e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0068  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                java.lang.String r0 = r6.e
                if (r0 == 0) goto L_0x004a
                java.lang.String r0 = r6.e
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                long r2 = r6.g
                r1.append(r2)
                java.lang.String r2 = ""
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x004a
                long r0 = r6.g
                com.ximalaya.ting.android.opensdk.model.track.Track r0 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequestForMain.a((long) r0)
                if (r0 == 0) goto L_0x0042
                com.ximalaya.ting.android.opensdk.model.track.Track r1 = r6.f
                java.lang.String r2 = r0.V()
                r1.m((java.lang.String) r2)
                com.ximalaya.ting.android.opensdk.model.track.Track r1 = r6.f
                java.lang.String r2 = r0.U()
                r1.l((java.lang.String) r2)
                com.ximalaya.ting.android.opensdk.model.track.Track r1 = r6.f
                java.lang.String r2 = r0.T()
                r1.k((java.lang.String) r2)
            L_0x0042:
                java.lang.String r0 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.c((com.ximalaya.ting.android.opensdk.model.track.Track) r0)
                r6.e = r0
                r0 = 1
                goto L_0x004b
            L_0x004a:
                r0 = 0
            L_0x004b:
                r1 = 0
                android.content.Context r2 = r6.d     // Catch:{ Exception -> 0x005a }
                java.lang.String r3 = r6.e     // Catch:{ Exception -> 0x005a }
                int r4 = r6.f2257a     // Catch:{ Exception -> 0x005a }
                int r5 = r6.b     // Catch:{ Exception -> 0x005a }
                android.graphics.Bitmap r2 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.a(r2, r3, r4, r5)     // Catch:{ Exception -> 0x005a }
                r1 = r2
                goto L_0x005e
            L_0x005a:
                r2 = move-exception
                r2.printStackTrace()
            L_0x005e:
                com.ximalaya.ting.android.opensdk.util.FileUtilBase$IBitmapDownOkCallBack r2 = r6.c
                if (r2 == 0) goto L_0x0068
                com.ximalaya.ting.android.opensdk.util.FileUtilBase$IBitmapDownOkCallBack r0 = r6.c
                com.ximalaya.ting.android.opensdk.util.FileUtilBase.a((com.ximalaya.ting.android.opensdk.util.FileUtilBase.IBitmapDownOkCallBack) r0, (android.graphics.Bitmap) r1)
                return
            L_0x0068:
                java.lang.String r2 = r6.e
                java.lang.String r3 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.f2255a
                boolean r2 = r2.equals(r3)
                if (r2 != 0) goto L_0x009e
                if (r0 == 0) goto L_0x0094
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                long r2 = r6.g
                r0.append(r2)
                java.lang.String r2 = ""
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                java.lang.String r2 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.f2255a
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0094
                goto L_0x009e
            L_0x0094:
                java.util.Map r0 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.f
                java.lang.String r1 = r6.e
                r0.remove(r1)
                goto L_0x00ac
            L_0x009e:
                android.graphics.Bitmap unused = com.ximalaya.ting.android.opensdk.util.FileUtilBase.e = r1
                android.graphics.Bitmap r0 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.e
                java.lang.String r1 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.f2255a
                com.ximalaya.ting.android.opensdk.util.FileUtilBase.b((android.graphics.Bitmap) r0, (java.lang.String) r1)
            L_0x00ac:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.util.FileUtilBase.GetBitmapRunnable.run():void");
        }
    }

    @WorkerThread
    static Bitmap a(Context context, String str, int i2, int i3) {
        boolean z;
        CacheControl cacheControl;
        b = context.getCacheDir().getAbsolutePath() + File.separator + j;
        File externalFilesDir = context.getExternalFilesDir("images");
        if (externalFilesDir == null || !externalFilesDir.exists()) {
            c = b;
        } else {
            c = externalFilesDir.getAbsolutePath() + File.separator + "images";
        }
        String str2 = b + File.separator + MD5.a(str) + ".1";
        boolean exists = new File(str2).exists();
        if (!exists) {
            String str3 = c + File.separator + a(str);
            z = new File(str3).exists();
            if (z) {
                str2 = str3;
            }
        } else {
            z = exists;
        }
        if (z) {
            return a(str2, i2, i3);
        }
        if (!NetworkType.b(context)) {
            cacheControl = CacheControl.FORCE_CACHE;
        } else {
            cacheControl = new CacheControl.Builder().build();
        }
        try {
            Request.Builder tag = BaseBuilder.a(c(context, str)).tag(str);
            if (cacheControl != null) {
                tag.cacheControl(cacheControl);
            }
            try {
                Response execute = c(context).newCall(tag.build()).execute();
                if (execute != null && execute.code() == 200) {
                    if (new File(str2).exists()) {
                        execute.body().close();
                        return a(str2, i2, i3);
                    }
                    InputStream byteStream = execute.body().byteStream();
                    Bitmap decodeStream = BitmapFactory.decodeStream(byteStream);
                    Util.closeQuietly((Closeable) byteStream);
                    return decodeStream;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        } catch (XimalayaException unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static void a() {
        f2255a = null;
        b = null;
        e = null;
        f.clear();
        h = null;
        i = null;
    }

    private static OkHttpClient c(Context context) {
        if (h == null) {
            synchronized (FileUtilBase.class) {
                if (h == null) {
                    h = BaseCall.a().a((URL) null);
                    OkHttpClient.Builder newBuilder = h.newBuilder();
                    newBuilder.cache(a(context));
                    h = newBuilder.build();
                }
            }
        }
        return h;
    }

    public static void a(Context context, Config config) {
        if (h != null) {
            OkHttpClient.Builder newBuilder = h.newBuilder();
            FreeFlowServiceUtil.a(context, config, newBuilder, false);
            h = newBuilder.build();
        }
    }

    public static Cache a(Context context) {
        if (i == null) {
            synchronized (FileUtilBase.class) {
                if (i == null) {
                    File b2 = b(context);
                    i = new Cache(b2, a(b2));
                }
            }
        }
        return i;
    }

    public static File b(Context context) {
        File file = new File(context.getApplicationContext().getCacheDir(), j);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static long a(File file) {
        long j2;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j2 = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            j2 = 5242880;
        }
        return Math.max(Math.min(j2, 52428800), 5242880);
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return null;
        }
        return MD5.a(str) + str.substring(lastIndexOf + 1);
    }

    @Nullable
    @WorkerThread
    public static Bitmap a(@Nullable String str, int i2, int i3) {
        Bitmap bitmap;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i2 > 0 || i3 > 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = a(options, i2, i3);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(str, options);
        } else {
            try {
                bitmap = BitmapFactory.decodeFile(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        ExifInterface b2 = b(str);
        return b2 != null ? a(bitmap, a(b2.getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1))) : bitmap;
    }

    public static Bitmap a(@Nullable Bitmap bitmap, int i2) {
        if (bitmap == null || i2 == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i2, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap != createBitmap ? createBitmap : bitmap;
    }

    public static int a(@NonNull BitmapFactory.Options options, int i2, int i3) {
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        int i6 = 1;
        if (i4 > i3 || i5 > i2) {
            while (true) {
                if (i4 / i6 <= i3 && i5 / i6 <= i2) {
                    break;
                }
                i6 *= 2;
            }
        }
        return i6;
    }

    @Nullable
    private static ExifInterface b(@NonNull String str) {
        try {
            return new ExifInterface(str);
        } catch (IOException unused) {
            return null;
        }
    }

    public static void b(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File b2 : listFiles) {
                        b(b2);
                    }
                }
                file.delete();
                return;
            }
            file.delete();
        }
    }

    public static String c(Context context, String str) {
        return (TextUtils.isEmpty(str) || str.startsWith(ConnectionHelper.HTTP_PREFIX)) ? str : str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0060 A[SYNTHETIC, Splitter:B:45:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006a A[SYNTHETIC, Splitter:B:50:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0075 A[SYNTHETIC, Splitter:B:56:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x007f A[SYNTHETIC, Splitter:B:61:0x007f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.res.AssetManager r6, java.lang.String r7, java.lang.String r8) {
        /*
            r0 = 0
            r1 = 0
            java.io.InputStream r6 = r6.open(r7)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x0054 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0054 }
            boolean r8 = r7.exists()     // Catch:{ Exception -> 0x0054 }
            if (r8 == 0) goto L_0x0026
            long r2 = r7.length()     // Catch:{ Exception -> 0x0054 }
            r4 = 0
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x0026
            if (r6 == 0) goto L_0x0025
            r6.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0025:
            return r1
        L_0x0026:
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0054 }
            r8.<init>(r7)     // Catch:{ Exception -> 0x0054 }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x004f, all -> 0x004c }
        L_0x002f:
            int r0 = r6.read(r7)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            if (r0 <= 0) goto L_0x003b
            if (r0 <= 0) goto L_0x002f
            r8.write(r7, r1, r0)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            goto L_0x002f
        L_0x003b:
            r8.flush()     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r8.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0046:
            if (r6 == 0) goto L_0x0072
            r6.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0072
        L_0x004c:
            r7 = move-exception
            r0 = r8
            goto L_0x0073
        L_0x004f:
            r7 = move-exception
            r0 = r8
            goto L_0x005b
        L_0x0052:
            r7 = move-exception
            goto L_0x0073
        L_0x0054:
            r7 = move-exception
            goto L_0x005b
        L_0x0056:
            r7 = move-exception
            r6 = r0
            goto L_0x0073
        L_0x0059:
            r7 = move-exception
            r6 = r0
        L_0x005b:
            r7.printStackTrace()     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0068
            r0.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0068:
            if (r6 == 0) goto L_0x0072
            r6.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0072
        L_0x006e:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0072:
            return r1
        L_0x0073:
            if (r0 == 0) goto L_0x007d
            r0.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007d
        L_0x0079:
            r8 = move-exception
            r8.printStackTrace()
        L_0x007d:
            if (r6 == 0) goto L_0x0087
            r6.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0087
        L_0x0083:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0087:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.util.FileUtilBase.a(android.content.res.AssetManager, java.lang.String, java.lang.String):int");
    }
}
