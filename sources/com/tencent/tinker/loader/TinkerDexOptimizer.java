package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class TinkerDexOptimizer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9228a = "Tinker.ParallelDex";
    private static final String b = "interpret.lock";

    public interface ResultCallback {
        void a(File file, File file2);

        void a(File file, File file2, File file3);

        void a(File file, File file2, Throwable th);
    }

    public static boolean a(Context context, Collection<File> collection, File file, ResultCallback resultCallback) {
        return a(context, collection, file, false, (String) null, resultCallback);
    }

    public static boolean a(Context context, Collection<File> collection, File file, boolean z, String str, ResultCallback resultCallback) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, new Comparator<File>() {
            /* renamed from: a */
            public int compare(File file, File file2) {
                long length = file.length();
                long length2 = file2.length();
                if (length < length2) {
                    return 1;
                }
                return length == length2 ? 0 : -1;
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!new OptimizeWorker(context, (File) it.next(), file, z, str, resultCallback).a()) {
                return false;
            }
        }
        return true;
    }

    private static class OptimizeWorker {

        /* renamed from: a  reason: collision with root package name */
        private static String f9229a;
        private final Context b;
        private final File c;
        private final File d;
        private final boolean e;
        private final ResultCallback f;

        OptimizeWorker(Context context, File file, File file2, boolean z, String str, ResultCallback resultCallback) {
            this.b = context;
            this.c = file;
            this.d = file2;
            this.e = z;
            this.f = resultCallback;
            f9229a = str;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            try {
                if (SharePatchFileUtil.a(this.c) || this.f == null) {
                    if (this.f != null) {
                        this.f.a(this.c, this.d);
                    }
                    String b2 = SharePatchFileUtil.b(this.c, this.d);
                    if (!ShareTinkerInternals.c()) {
                        if (this.e) {
                            a(this.c.getAbsolutePath(), b2);
                        } else {
                            if (Build.VERSION.SDK_INT < 28) {
                                if (Build.VERSION.SDK_INT < 27 || Build.VERSION.PREVIEW_SDK_INT == 0) {
                                    DexFile.loadDex(this.c.getAbsolutePath(), b2, 0);
                                }
                            }
                            AndroidNClassLoader.a(this.b, this.c.getAbsolutePath());
                        }
                    }
                    if (this.f == null) {
                        return true;
                    }
                    this.f.a(this.c, this.d, new File(b2));
                    return true;
                }
                ResultCallback resultCallback = this.f;
                File file = this.c;
                File file2 = this.d;
                resultCallback.a(file, file2, (Throwable) new IOException("dex file " + this.c.getAbsolutePath() + " is not exist!"));
                return false;
            } catch (Throwable th) {
                Log.e(TinkerDexOptimizer.f9228a, "Failed to optimize dex: " + this.c.getAbsolutePath(), th);
                if (this.f == null) {
                    return true;
                }
                this.f.a(this.c, this.d, th);
                return false;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x00fe A[SYNTHETIC, Splitter:B:34:0x00fe] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(java.lang.String r5, java.lang.String r6) throws java.io.IOException {
            /*
                r4 = this;
                java.io.File r0 = new java.io.File
                r0.<init>(r6)
                boolean r1 = r0.exists()
                if (r1 != 0) goto L_0x0012
                java.io.File r1 = r0.getParentFile()
                r1.mkdirs()
            L_0x0012:
                java.io.File r1 = new java.io.File
                java.io.File r0 = r0.getParentFile()
                java.lang.String r2 = "interpret.lock"
                r1.<init>(r0, r2)
                r0 = 0
                com.tencent.tinker.loader.shareutil.ShareFileLockHelper r1 = com.tencent.tinker.loader.shareutil.ShareFileLockHelper.a(r1)     // Catch:{ all -> 0x00fa }
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00f8 }
                r0.<init>()     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "dex2oat"
                r0.add(r2)     // Catch:{ all -> 0x00f8 }
                int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00f8 }
                r3 = 24
                if (r2 < r3) goto L_0x0046
                java.lang.String r2 = "--runtime-arg"
                r0.add(r2)     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "-classpath"
                r0.add(r2)     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "--runtime-arg"
                r0.add(r2)     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "&"
                r0.add(r2)     // Catch:{ all -> 0x00f8 }
            L_0x0046:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
                r2.<init>()     // Catch:{ all -> 0x00f8 }
                java.lang.String r3 = "--dex-file="
                r2.append(r3)     // Catch:{ all -> 0x00f8 }
                r2.append(r5)     // Catch:{ all -> 0x00f8 }
                java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x00f8 }
                r0.add(r5)     // Catch:{ all -> 0x00f8 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
                r5.<init>()     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "--oat-file="
                r5.append(r2)     // Catch:{ all -> 0x00f8 }
                r5.append(r6)     // Catch:{ all -> 0x00f8 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00f8 }
                r0.add(r5)     // Catch:{ all -> 0x00f8 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
                r5.<init>()     // Catch:{ all -> 0x00f8 }
                java.lang.String r6 = "--instruction-set="
                r5.append(r6)     // Catch:{ all -> 0x00f8 }
                java.lang.String r6 = f9229a     // Catch:{ all -> 0x00f8 }
                r5.append(r6)     // Catch:{ all -> 0x00f8 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00f8 }
                r0.add(r5)     // Catch:{ all -> 0x00f8 }
                int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00f8 }
                r6 = 25
                if (r5 <= r6) goto L_0x0090
                java.lang.String r5 = "--compiler-filter=quicken"
                r0.add(r5)     // Catch:{ all -> 0x00f8 }
                goto L_0x0095
            L_0x0090:
                java.lang.String r5 = "--compiler-filter=interpret-only"
                r0.add(r5)     // Catch:{ all -> 0x00f8 }
            L_0x0095:
                java.lang.ProcessBuilder r5 = new java.lang.ProcessBuilder     // Catch:{ all -> 0x00f8 }
                r5.<init>(r0)     // Catch:{ all -> 0x00f8 }
                r6 = 1
                r5.redirectErrorStream(r6)     // Catch:{ all -> 0x00f8 }
                java.lang.Process r5 = r5.start()     // Catch:{ all -> 0x00f8 }
                java.io.InputStream r6 = r5.getInputStream()     // Catch:{ all -> 0x00f8 }
                com.tencent.tinker.loader.TinkerDexOptimizer.StreamConsumer.a(r6)     // Catch:{ all -> 0x00f8 }
                java.io.InputStream r6 = r5.getErrorStream()     // Catch:{ all -> 0x00f8 }
                com.tencent.tinker.loader.TinkerDexOptimizer.StreamConsumer.a(r6)     // Catch:{ all -> 0x00f8 }
                int r5 = r5.waitFor()     // Catch:{ InterruptedException -> 0x00dc }
                if (r5 != 0) goto L_0x00c5
                if (r1 == 0) goto L_0x00c4
                r1.close()     // Catch:{ IOException -> 0x00bc }
                goto L_0x00c4
            L_0x00bc:
                r5 = move-exception
                java.lang.String r6 = "Tinker.ParallelDex"
                java.lang.String r0 = "release interpret Lock error"
                android.util.Log.w(r6, r0, r5)
            L_0x00c4:
                return
            L_0x00c5:
                java.io.IOException r6 = new java.io.IOException     // Catch:{ InterruptedException -> 0x00dc }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00dc }
                r0.<init>()     // Catch:{ InterruptedException -> 0x00dc }
                java.lang.String r2 = "dex2oat works unsuccessfully, exit code: "
                r0.append(r2)     // Catch:{ InterruptedException -> 0x00dc }
                r0.append(r5)     // Catch:{ InterruptedException -> 0x00dc }
                java.lang.String r5 = r0.toString()     // Catch:{ InterruptedException -> 0x00dc }
                r6.<init>(r5)     // Catch:{ InterruptedException -> 0x00dc }
                throw r6     // Catch:{ InterruptedException -> 0x00dc }
            L_0x00dc:
                r5 = move-exception
                java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x00f8 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
                r0.<init>()     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = "dex2oat is interrupted, msg: "
                r0.append(r2)     // Catch:{ all -> 0x00f8 }
                java.lang.String r2 = r5.getMessage()     // Catch:{ all -> 0x00f8 }
                r0.append(r2)     // Catch:{ all -> 0x00f8 }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00f8 }
                r6.<init>(r0, r5)     // Catch:{ all -> 0x00f8 }
                throw r6     // Catch:{ all -> 0x00f8 }
            L_0x00f8:
                r5 = move-exception
                goto L_0x00fc
            L_0x00fa:
                r5 = move-exception
                r1 = r0
            L_0x00fc:
                if (r1 == 0) goto L_0x010a
                r1.close()     // Catch:{ IOException -> 0x0102 }
                goto L_0x010a
            L_0x0102:
                r6 = move-exception
                java.lang.String r0 = "Tinker.ParallelDex"
                java.lang.String r1 = "release interpret Lock error"
                android.util.Log.w(r0, r1, r6)
            L_0x010a:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.TinkerDexOptimizer.OptimizeWorker.a(java.lang.String, java.lang.String):void");
        }
    }

    private static class StreamConsumer {

        /* renamed from: a  reason: collision with root package name */
        static final Executor f9230a = Executors.newSingleThreadExecutor();

        private StreamConsumer() {
        }

        static void a(final InputStream inputStream) {
            f9230a.execute(new Runnable() {
                /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0012 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r2 = this;
                        java.io.InputStream r0 = r2
                        if (r0 != 0) goto L_0x0005
                        return
                    L_0x0005:
                        r0 = 256(0x100, float:3.59E-43)
                        byte[] r0 = new byte[r0]
                    L_0x0009:
                        java.io.InputStream r1 = r2     // Catch:{ IOException -> 0x0012, all -> 0x0018 }
                        int r1 = r1.read(r0)     // Catch:{ IOException -> 0x0012, all -> 0x0018 }
                        if (r1 <= 0) goto L_0x0012
                        goto L_0x0009
                    L_0x0012:
                        java.io.InputStream r0 = r2     // Catch:{ Exception -> 0x001f }
                        r0.close()     // Catch:{ Exception -> 0x001f }
                        goto L_0x001f
                    L_0x0018:
                        r0 = move-exception
                        java.io.InputStream r1 = r2     // Catch:{ Exception -> 0x001e }
                        r1.close()     // Catch:{ Exception -> 0x001e }
                    L_0x001e:
                        throw r0
                    L_0x001f:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.TinkerDexOptimizer.StreamConsumer.AnonymousClass1.run():void");
                }
            });
        }
    }
}
