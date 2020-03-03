package com.mi.blockcanary;

import android.content.ComponentName;
import android.content.Context;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Printer;
import com.mi.blockcanary.ui.DisplayActivity;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class BlockCanary {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6737a = "BlockCanary";
    private static BlockCanary b;
    private static final Executor e = a("File-IO");
    private BlockCanaryInternals c;
    private boolean d = false;

    private BlockCanary() {
        BlockCanaryInternals.a(BlockCanaryContext.i());
        this.c = BlockCanaryInternals.a();
        this.c.a((BlockInterceptor) BlockCanaryContext.i());
        if (BlockCanaryContext.i().d()) {
            this.c.a((BlockInterceptor) new DisplayService());
        }
        if (BlockCanaryContext.i().h()) {
            this.c.a((BlockInterceptor) new UploadBlockService());
        }
    }

    public static BlockCanary a(Context context, BlockCanaryContext blockCanaryContext) {
        BlockCanaryContext.a(context, blockCanaryContext);
        c(context, DisplayActivity.class, BlockCanaryContext.i().d());
        return a();
    }

    public static BlockCanary a() {
        if (b == null) {
            synchronized (BlockCanary.class) {
                if (b == null) {
                    b = new BlockCanary();
                }
            }
        }
        return b;
    }

    public void b() {
        if (!this.d) {
            this.d = true;
            Looper.getMainLooper().setMessageLogging(this.c.f6740a);
        }
    }

    public void c() {
        if (this.d) {
            this.d = false;
            Looper.getMainLooper().setMessageLogging((Printer) null);
            this.c.b.b();
            this.c.c.b();
        }
    }

    public void d() {
        Uploader.a();
    }

    public void e() {
        PreferenceManager.getDefaultSharedPreferences(BlockCanaryContext.i().j()).edit().putLong("BlockCanary_StartTime", System.currentTimeMillis()).commit();
    }

    public void a(long j) {
        if (j > 0 && this.c != null) {
            this.c.a(j);
        }
    }

    public boolean f() {
        long j = PreferenceManager.getDefaultSharedPreferences(BlockCanaryContext.i().j()).getLong("BlockCanary_StartTime", 0);
        return j != 0 && System.currentTimeMillis() - j > ((long) ((BlockCanaryContext.i().k() * 3600) * 1000));
    }

    /* access modifiers changed from: private */
    public static void b(Context context, Class<?> cls, boolean z) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls), z ? 1 : 2, 1);
    }

    private static void a(Runnable runnable) {
        e.execute(runnable);
    }

    private static Executor a(String str) {
        return Executors.newSingleThreadExecutor(new SingleThreadFactory(str));
    }

    private static void c(Context context, final Class<?> cls, final boolean z) {
        final Context applicationContext = context.getApplicationContext();
        a((Runnable) new Runnable() {
            public void run() {
                BlockCanary.b(applicationContext, cls, z);
            }
        });
    }
}
