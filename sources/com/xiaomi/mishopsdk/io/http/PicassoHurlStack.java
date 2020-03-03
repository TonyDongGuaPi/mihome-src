package com.xiaomi.mishopsdk.io.http;

import android.os.StatFs;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class PicassoHurlStack extends OkHttpStack {
    private static final int MAX_DISK_CACHE_SIZE = 157286400;
    private static final int MIN_DISK_CACHE_SIZE = 10485760;
    private static final String PICASSO_CACHE = "picasso-cache";
    public static final int PICASSO_CONNECT_TIMEOUT_SECOND = 15;
    public static final int PICASSO_READ_TIMEOUT_SECOND = 20;
    public static final int PICASSO_WRITE_TIMEOUT_SECOND = 20;
    private static final String TAG = "PicassoHurlStack";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return PicassoHurlStack.build_aroundBody0((PicassoHurlStack) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("PicassoHurlStack.java", PicassoHurlStack.class);
        ajc$tjp_0 = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 41);
    }

    /* access modifiers changed from: protected */
    public Cache createCache() {
        File createDefaultCacheDir = createDefaultCacheDir(ShopApp.instance, PICASSO_CACHE);
        return new Cache(createDefaultCacheDir.getAbsoluteFile(), calculateDiskCacheSize(createDefaultCacheDir));
    }

    /* access modifiers changed from: protected */
    public OkHttpClient createOkhttpClient(Cache cache) {
        OkHttpClient.Builder cache2 = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).cache(cache);
        JoinPoint a2 = Factory.a(ajc$tjp_0, (Object) this, (Object) cache2);
        return (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cache2, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient build_aroundBody0(PicassoHurlStack picassoHurlStack, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    private long calculateDiskCacheSize(File file) {
        long j;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            j = 10485760;
        }
        return Math.max(Math.min(j, 157286400), 10485760);
    }
}
