package com.mi.blockcanary;

import android.os.Environment;
import android.os.Looper;
import com.mi.blockcanary.LooperMonitor;
import com.mi.blockcanary.internal.BlockInfo;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class BlockCanaryInternals {
    public static long d = 3000;
    private static BlockCanaryInternals e;
    private static BlockCanaryContext f;

    /* renamed from: a  reason: collision with root package name */
    LooperMonitor f6740a;
    StackSampler b = new StackSampler(Looper.getMainLooper().getThread(), d);
    CpuSampler c = new CpuSampler(d);
    /* access modifiers changed from: private */
    public List<BlockInterceptor> g = new LinkedList();

    public BlockCanaryInternals() {
        a(new LooperMonitor(new LooperMonitor.BlockListener() {
            public void a(long j, long j2, long j3, long j4) {
                long j5 = j;
                long j6 = j2;
                ArrayList<String> a2 = BlockCanaryInternals.this.b.a(j5, j6);
                if (!a2.isEmpty()) {
                    BlockInfo b = BlockInfo.a().a(j, j2, j3, j4).a(BlockCanaryInternals.this.c.a(j5, j6)).a(BlockCanaryInternals.this.c.d()).a(a2).b();
                    if (BlockCanaryContext.i().d()) {
                        LogWriter.a(b.toString());
                    }
                    if (BlockCanaryInternals.this.g.size() != 0) {
                        for (BlockInterceptor a3 : BlockCanaryInternals.this.g) {
                            a3.a(BlockCanaryInternals.c().j(), b);
                        }
                    }
                }
            }
        }, d, c().g()));
        LogWriter.a();
    }

    static BlockCanaryInternals a() {
        if (e == null) {
            synchronized (BlockCanaryInternals.class) {
                if (e == null) {
                    e = new BlockCanaryInternals();
                }
            }
        }
        return e;
    }

    public void a(long j) {
        d = j;
        this.f6740a.a(j);
        this.b.a(j);
        this.c.a(j);
    }

    public long b() {
        return d;
    }

    public static void a(BlockCanaryContext blockCanaryContext) {
        f = blockCanaryContext;
    }

    public static BlockCanaryContext c() {
        return f;
    }

    /* access modifiers changed from: package-private */
    public void a(BlockInterceptor blockInterceptor) {
        this.g.add(blockInterceptor);
    }

    private void a(LooperMonitor looperMonitor) {
        this.f6740a = looperMonitor;
    }

    static String d() {
        String str;
        String externalStorageState = Environment.getExternalStorageState();
        if (c() == null) {
            str = "";
        } else {
            str = c().l();
        }
        if (!"mounted".equals(externalStorageState) || !Environment.getExternalStorageDirectory().canWrite()) {
            return c().j().getFilesDir() + c().l();
        }
        return Environment.getExternalStorageDirectory().getPath() + str;
    }

    static File e() {
        File file = new File(d());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File[] f() {
        File e2 = e();
        if (!e2.exists() || !e2.isDirectory()) {
            return null;
        }
        return e2.listFiles(new BlockLogFileFilter());
    }

    private static class BlockLogFileFilter implements FilenameFilter {

        /* renamed from: a  reason: collision with root package name */
        private String f6742a = ".log";

        BlockLogFileFilter() {
        }

        public boolean accept(File file, String str) {
            return str.endsWith(this.f6742a);
        }
    }
}
