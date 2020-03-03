package com.xiaomiyoupin.ypdimage.okhttp;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.xiaomiyoupin.ypdimage.okhttp.OkHttpProgress;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final Call.Factory f1803a;

    public boolean a(@NonNull GlideUrl glideUrl) {
        return true;
    }

    public OkHttpUrlLoader(@NonNull Call.Factory factory) {
        this.f1803a = factory;
    }

    public ModelLoader.LoadData<InputStream> a(@NonNull GlideUrl glideUrl, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(glideUrl, new OkHttpStreamFetcher(this.f1803a, glideUrl));
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private static volatile Call.Factory f1804a;
        private final Call.Factory b;

        public void a() {
        }

        private static Call.Factory b() {
            if (f1804a == null) {
                synchronized (Factory.class) {
                    if (f1804a == null) {
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        builder.addNetworkInterceptor(new Interceptor() {
                            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
                                Request request = chain.request();
                                Response proceed = chain.proceed(request);
                                return proceed.newBuilder().body(new OkHttpProgress.OkHttpProgressResponseBody(request.url(), proceed.body(), new OkHttpProgress.DispatchingProgressListener())).build();
                            }
                        });
                        if (YPDImage.a().h() != null) {
                            YPDImage.a().h().a(builder);
                        }
                        f1804a = builder.build();
                    }
                }
            }
            return f1804a;
        }

        public Factory() {
            this(b());
        }

        public Factory(@NonNull Call.Factory factory) {
            this.b = factory;
        }

        @NonNull
        public ModelLoader<GlideUrl, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new OkHttpUrlLoader(this.b);
        }
    }
}
