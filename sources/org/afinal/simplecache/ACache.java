package org.afinal.simplecache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.taobao.weex.annotation.JSMethod;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

public class ACache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3189a = 3600;
    public static final int b = 86400;
    private static final int c = 50000000;
    private static final int d = Integer.MAX_VALUE;
    private static Map<String, ACache> e = new HashMap();
    private ACacheManager f;

    public static ACache a(Context context) {
        return a(context, "ACache");
    }

    public static ACache a(Context context, String str) {
        return a(new File(context.getCacheDir(), str), 50000000, Integer.MAX_VALUE);
    }

    public static ACache a(File file) {
        return a(file, 50000000, Integer.MAX_VALUE);
    }

    public static ACache a(Context context, long j, int i) {
        return a(new File(context.getCacheDir(), "ACache"), j, i);
    }

    public static ACache a(File file, long j, int i) {
        Map<String, ACache> map = e;
        ACache aCache = map.get(file.getAbsoluteFile() + b());
        if (aCache != null) {
            return aCache;
        }
        ACache aCache2 = new ACache(file, j, i);
        Map<String, ACache> map2 = e;
        map2.put(String.valueOf(file.getAbsolutePath()) + b(), aCache2);
        return aCache2;
    }

    private static String b() {
        return JSMethod.NOT_SET + Process.myPid();
    }

    private ACache(File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.f = new ACacheManager(this, file, j, i, (ACacheManager) null);
            return;
        }
        throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002d A[SYNTHETIC, Splitter:B:17:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            org.afinal.simplecache.ACache$ACacheManager r0 = r4.f
            java.io.File r5 = r0.b((java.lang.String) r5)
            r0 = 0
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0027 }
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ IOException -> 0x0027 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0027 }
            r3 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r3)     // Catch:{ IOException -> 0x0027 }
            r1.write(r6)     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r1.flush()     // Catch:{ IOException -> 0x001d }
            r1.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0038
        L_0x001d:
            r6 = move-exception
            goto L_0x0035
        L_0x001f:
            r6 = move-exception
            r0 = r1
            goto L_0x003e
        L_0x0022:
            r6 = move-exception
            r0 = r1
            goto L_0x0028
        L_0x0025:
            r6 = move-exception
            goto L_0x003e
        L_0x0027:
            r6 = move-exception
        L_0x0028:
            r6.printStackTrace()     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0038
            r0.flush()     // Catch:{ IOException -> 0x0034 }
            r0.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r6 = move-exception
        L_0x0035:
            r6.printStackTrace()
        L_0x0038:
            org.afinal.simplecache.ACache$ACacheManager r6 = r4.f
            r6.a((java.io.File) r5)
            return
        L_0x003e:
            if (r0 == 0) goto L_0x004b
            r0.flush()     // Catch:{ IOException -> 0x0047 }
            r0.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004b:
            org.afinal.simplecache.ACache$ACacheManager r0 = r4.f
            r0.a((java.io.File) r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.afinal.simplecache.ACache.a(java.lang.String, java.lang.String):void");
    }

    public void a(String str, String str2, int i) {
        a(str, Utils.b(i, str2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x005c A[SYNTHETIC, Splitter:B:33:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0068 A[SYNTHETIC, Splitter:B:40:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            org.afinal.simplecache.ACache$ACacheManager r0 = r5.f
            java.io.File r0 = r0.a((java.lang.String) r6)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto L_0x000e
            return r2
        L_0x000e:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r0 = ""
        L_0x001a:
            java.lang.String r3 = r1.readLine()     // Catch:{ IOException -> 0x0050 }
            if (r3 != 0) goto L_0x003f
            boolean r3 = org.afinal.simplecache.ACache.Utils.c((java.lang.String) r0)     // Catch:{ IOException -> 0x0050 }
            if (r3 != 0) goto L_0x0033
            java.lang.String r6 = org.afinal.simplecache.ACache.Utils.d((java.lang.String) r0)     // Catch:{ IOException -> 0x0050 }
            r1.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0032:
            return r6
        L_0x0033:
            r1.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003b:
            r5.i(r6)
            return r2
        L_0x003f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0050 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0050 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0050 }
            r4.append(r3)     // Catch:{ IOException -> 0x0050 }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x0050 }
            goto L_0x001a
        L_0x0050:
            r6 = move-exception
            goto L_0x0057
        L_0x0052:
            r6 = move-exception
            r1 = r2
            goto L_0x0066
        L_0x0055:
            r6 = move-exception
            r1 = r2
        L_0x0057:
            r6.printStackTrace()     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0064:
            return r2
        L_0x0065:
            r6 = move-exception
        L_0x0066:
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0070:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.afinal.simplecache.ACache.a(java.lang.String):java.lang.String");
    }

    public void a(String str, JSONObject jSONObject) {
        a(str, jSONObject.toString());
    }

    public void a(String str, JSONObject jSONObject, int i) {
        a(str, jSONObject.toString(), i);
    }

    public JSONObject b(String str) {
        try {
            return new JSONObject(a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(String str, JSONArray jSONArray) {
        a(str, jSONArray.toString());
    }

    public void a(String str, JSONArray jSONArray, int i) {
        a(str, jSONArray.toString(), i);
    }

    public JSONArray c(String str) {
        try {
            return new JSONArray(a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[SYNTHETIC, Splitter:B:17:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0039 A[SYNTHETIC, Splitter:B:24:0x0039] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r3, byte[] r4) {
        /*
            r2 = this;
            org.afinal.simplecache.ACache$ACacheManager r0 = r2.f
            java.io.File r3 = r0.b((java.lang.String) r3)
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0020 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0020 }
            r1.write(r4)     // Catch:{ Exception -> 0x001b, all -> 0x0018 }
            r1.flush()     // Catch:{ IOException -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x0031
        L_0x0016:
            r4 = move-exception
            goto L_0x002e
        L_0x0018:
            r4 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x001b:
            r4 = move-exception
            r0 = r1
            goto L_0x0021
        L_0x001e:
            r4 = move-exception
            goto L_0x0037
        L_0x0020:
            r4 = move-exception
        L_0x0021:
            r4.printStackTrace()     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x0031
            r0.flush()     // Catch:{ IOException -> 0x002d }
            r0.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r4 = move-exception
        L_0x002e:
            r4.printStackTrace()
        L_0x0031:
            org.afinal.simplecache.ACache$ACacheManager r4 = r2.f
            r4.a((java.io.File) r3)
            return
        L_0x0037:
            if (r0 == 0) goto L_0x0044
            r0.flush()     // Catch:{ IOException -> 0x0040 }
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0044:
            org.afinal.simplecache.ACache$ACacheManager r0 = r2.f
            r0.a((java.io.File) r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.afinal.simplecache.ACache.a(java.lang.String, byte[]):void");
    }

    public void a(String str, byte[] bArr, int i) {
        a(str, Utils.b(i, bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0049 A[SYNTHETIC, Splitter:B:28:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0056 A[SYNTHETIC, Splitter:B:36:0x0056] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] d(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            org.afinal.simplecache.ACache$ACacheManager r1 = r5.f     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            java.io.File r1 = r1.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            if (r2 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            java.lang.String r3 = "r"
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            long r3 = r2.length()     // Catch:{ Exception -> 0x003e }
            int r1 = (int) r3     // Catch:{ Exception -> 0x003e }
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x003e }
            r2.read(r1)     // Catch:{ Exception -> 0x003e }
            boolean r3 = org.afinal.simplecache.ACache.Utils.d((byte[]) r1)     // Catch:{ Exception -> 0x003e }
            if (r3 != 0) goto L_0x0032
            byte[] r6 = org.afinal.simplecache.ACache.Utils.e(r1)     // Catch:{ Exception -> 0x003e }
            r2.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0031:
            return r6
        L_0x0032:
            r2.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003a:
            r5.i(r6)
            return r0
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x0054
        L_0x0042:
            r6 = move-exception
            r2 = r0
        L_0x0044:
            r6.printStackTrace()     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0051
            r2.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0051:
            return r0
        L_0x0052:
            r6 = move-exception
            r0 = r2
        L_0x0054:
            if (r0 == 0) goto L_0x005e
            r0.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.afinal.simplecache.ACache.d(java.lang.String):byte[]");
    }

    public void a(String str, Serializable serializable) {
        a(str, serializable, -1);
    }

    public void a(String str, Serializable serializable, int i) {
        ObjectOutputStream objectOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream2.writeObject(serializable);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    a(str, byteArray, i);
                } else {
                    a(str, byteArray);
                }
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = objectOutputStream2;
                try {
                    e.printStackTrace();
                    objectOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        objectOutputStream.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream = objectOutputStream2;
                objectOutputStream.close();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            objectOutputStream.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0038 A[SYNTHETIC, Splitter:B:28:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0042 A[SYNTHETIC, Splitter:B:33:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0051 A[SYNTHETIC, Splitter:B:41:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x005b A[SYNTHETIC, Splitter:B:46:0x005b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object e(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = r4.d(r5)
            r0 = 0
            if (r5 == 0) goto L_0x0064
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.Object r2 = r5.readObject()     // Catch:{ Exception -> 0x0026 }
            r1.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001d:
            r5.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0025:
            return r2
        L_0x0026:
            r2 = move-exception
            goto L_0x0033
        L_0x0028:
            r5 = move-exception
            goto L_0x004f
        L_0x002a:
            r2 = move-exception
            r5 = r0
            goto L_0x0033
        L_0x002d:
            r5 = move-exception
            r1 = r0
            goto L_0x004f
        L_0x0030:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L_0x0033:
            r2.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0040:
            if (r5 == 0) goto L_0x004a
            r5.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004a:
            return r0
        L_0x004b:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L_0x004f:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0059:
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0063:
            throw r5
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.afinal.simplecache.ACache.e(java.lang.String):java.lang.Object");
    }

    public void a(String str, Bitmap bitmap) {
        a(str, Utils.c(bitmap));
    }

    public void a(String str, Bitmap bitmap, int i) {
        a(str, Utils.c(bitmap), i);
    }

    public Bitmap f(String str) {
        if (d(str) == null) {
            return null;
        }
        return Utils.h(d(str));
    }

    public void a(String str, Drawable drawable) {
        a(str, Utils.b(drawable));
    }

    public void a(String str, Drawable drawable, int i) {
        a(str, Utils.b(drawable), i);
    }

    public Drawable g(String str) {
        if (d(str) == null) {
            return null;
        }
        return Utils.d(Utils.h(d(str)));
    }

    public File h(String str) {
        File a2 = this.f.b(str);
        if (a2.exists()) {
            return a2;
        }
        return null;
    }

    public boolean i(String str) {
        return this.f.c(str);
    }

    public void a() {
        this.f.b();
    }

    public class ACacheManager {

        /* renamed from: a  reason: collision with root package name */
        protected File f3190a;
        /* access modifiers changed from: private */
        public final AtomicLong c;
        /* access modifiers changed from: private */
        public final AtomicInteger d;
        private final long e;
        private final int f;
        /* access modifiers changed from: private */
        public final Map<File, Long> g;

        private ACacheManager(File file, long j, int i) {
            this.g = Collections.synchronizedMap(new HashMap());
            this.f3190a = file;
            this.e = j;
            this.f = i;
            this.c = new AtomicLong();
            this.d = new AtomicInteger();
            a();
        }

        /* synthetic */ ACacheManager(ACache aCache, File file, long j, int i, ACacheManager aCacheManager) {
            this(file, j, i);
        }

        private void a() {
            new Thread(new Runnable() {
                public void run() {
                    File[] listFiles = ACacheManager.this.f3190a.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : listFiles) {
                            i = (int) (((long) i) + ACacheManager.this.b(file));
                            i2++;
                            ACacheManager.this.g.put(file, Long.valueOf(file.lastModified()));
                        }
                        ACacheManager.this.c.set((long) i);
                        ACacheManager.this.d.set(i2);
                    }
                }
            }).start();
        }

        /* access modifiers changed from: private */
        public void a(File file) {
            int i = this.d.get();
            while (i + 1 > this.f) {
                this.c.addAndGet(-c());
                i = this.d.addAndGet(-1);
            }
            this.d.addAndGet(1);
            long b2 = b(file);
            long j = this.c.get();
            while (j + b2 > this.e) {
                j = this.c.addAndGet(-c());
            }
            this.c.addAndGet(b2);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.g.put(file, valueOf);
        }

        /* access modifiers changed from: private */
        public File a(String str) {
            File b2 = b(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            b2.setLastModified(valueOf.longValue());
            this.g.put(b2, valueOf);
            return b2;
        }

        /* access modifiers changed from: private */
        public File b(String str) {
            return new File(this.f3190a, new StringBuilder(String.valueOf(str.hashCode())).toString());
        }

        /* access modifiers changed from: private */
        public boolean c(String str) {
            return a(str).delete();
        }

        /* access modifiers changed from: private */
        public void b() {
            this.g.clear();
            this.c.set(0);
            File[] listFiles = this.f3190a.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        private long c() {
            File file;
            if (this.g.isEmpty()) {
                return 0;
            }
            Set<Map.Entry<File, Long>> entrySet = this.g.entrySet();
            synchronized (this.g) {
                file = null;
                Long l = null;
                for (Map.Entry next : entrySet) {
                    if (file == null) {
                        file = (File) next.getKey();
                        l = (Long) next.getValue();
                    } else {
                        Long l2 = (Long) next.getValue();
                        if (l2.longValue() < l.longValue()) {
                            file = (File) next.getKey();
                            l = l2;
                        }
                    }
                }
            }
            long b2 = b(file);
            if (file.delete()) {
                this.g.remove(file);
            }
            return b2;
        }

        /* access modifiers changed from: private */
        public long b(File file) {
            return file.length();
        }
    }

    private static class Utils {

        /* renamed from: a  reason: collision with root package name */
        private static final char f3192a = ' ';

        private Utils() {
        }

        /* access modifiers changed from: private */
        public static boolean c(String str) {
            return d(str.getBytes());
        }

        /* access modifiers changed from: private */
        public static boolean d(byte[] bArr) {
            String[] g = g(bArr);
            if (g != null && g.length == 2) {
                String str = g[0];
                while (str.startsWith("0")) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(g[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static String b(int i, String str) {
            return String.valueOf(a(i)) + str;
        }

        /* access modifiers changed from: private */
        public static byte[] b(int i, byte[] bArr) {
            byte[] bytes = a(i).getBytes();
            byte[] bArr2 = new byte[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* access modifiers changed from: private */
        public static String d(String str) {
            return (str == null || !f(str.getBytes())) ? str : str.substring(str.indexOf(32) + 1, str.length());
        }

        /* access modifiers changed from: private */
        public static byte[] e(byte[] bArr) {
            return f(bArr) ? a(bArr, a(bArr, ' ') + 1, bArr.length) : bArr;
        }

        private static boolean f(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && a(bArr, ' ') > 14;
        }

        private static String[] g(byte[] bArr) {
            if (!f(bArr)) {
                return null;
            }
            return new String[]{new String(a(bArr, 0, 13)), new String(a(bArr, 14, a(bArr, ' ')))};
        }

        private static int a(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(String.valueOf(i) + " > " + i2);
        }

        private static String a(int i) {
            String sb = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
            while (sb.length() < 13) {
                sb = "0" + sb;
            }
            return String.valueOf(sb) + "-" + i + ' ';
        }

        /* access modifiers changed from: private */
        public static byte[] c(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        /* access modifiers changed from: private */
        public static Bitmap h(byte[] bArr) {
            if (bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        /* access modifiers changed from: private */
        public static Bitmap b(Drawable drawable) {
            Bitmap.Config config;
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (drawable.getOpacity() != -1) {
                config = Bitmap.Config.ARGB_8888;
            } else {
                config = Bitmap.Config.RGB_565;
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        /* access modifiers changed from: private */
        public static Drawable d(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            return new BitmapDrawable(bitmap);
        }
    }
}
