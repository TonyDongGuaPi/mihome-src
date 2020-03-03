package com.mi.blockcanary;

import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class Uploader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6752a = "Uploader";
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);

    private Uploader() {
        throw new InstantiationError("Must not instantiate this class");
    }

    /* access modifiers changed from: private */
    public static File c() {
        String l = Long.toString(System.currentTimeMillis());
        try {
            l = b.format(new Date());
        } catch (Throwable th) {
            Log.e(f6752a, "zip: ", th);
        }
        File b2 = LogWriter.b("BlockCanary-" + l);
        BlockCanaryInternals.c().a(BlockCanaryInternals.f(), b2);
        LogWriter.b();
        return b2;
    }

    public static void a() {
        HandlerThreadFactory.b().post(new Runnable() {
            public void run() {
                File b = Uploader.c();
                if (b.exists()) {
                    BlockCanaryInternals.c().a(b);
                }
            }
        });
    }
}
