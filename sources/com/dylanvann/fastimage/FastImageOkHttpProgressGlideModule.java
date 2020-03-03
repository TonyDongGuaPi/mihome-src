package com.dylanvann.fastimage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.LibraryGlideModule;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.xiaomiyoupin.ypdimage.okhttp.OkHttpUrlLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

@GlideModule
public class FastImageOkHttpProgressGlideModule extends LibraryGlideModule {

    /* renamed from: a  reason: collision with root package name */
    private static DispatchingProgressListener f5279a = new DispatchingProgressListener();

    private interface ResponseProgressListener {
        void a(String str, long j, long j2);
    }

    public void a(Context context, Glide glide, Registry registry) {
        registry.c(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getOkHttpClient().newBuilder().addInterceptor(a((ResponseProgressListener) f5279a)).build()));
    }

    private static Interceptor a(final ResponseProgressListener responseProgressListener) {
        return new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                return proceed.newBuilder().body(new OkHttpProgressResponseBody(request.url().toString(), proceed.body(), responseProgressListener)).build();
            }
        };
    }

    static void a(String str) {
        f5279a.a(str);
    }

    static void a(String str, FastImageProgressListener fastImageProgressListener) {
        f5279a.a(str, fastImageProgressListener);
    }

    private static class DispatchingProgressListener implements ResponseProgressListener {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, FastImageProgressListener> f5281a = new WeakHashMap();
        private final Map<String, Long> b = new HashMap();
        private final Handler c = new Handler(Looper.getMainLooper());

        DispatchingProgressListener() {
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            this.f5281a.remove(str);
            this.b.remove(str);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, FastImageProgressListener fastImageProgressListener) {
            this.f5281a.put(str, fastImageProgressListener);
        }

        public void a(String str, long j, long j2) {
            String str2 = str;
            FastImageProgressListener fastImageProgressListener = this.f5281a.get(str);
            if (fastImageProgressListener != null) {
                if (j2 <= j) {
                    a(str);
                }
                if (a(str, j, j2, fastImageProgressListener.getGranularityPercentage())) {
                    final FastImageProgressListener fastImageProgressListener2 = fastImageProgressListener;
                    final String str3 = str;
                    final long j3 = j;
                    final long j4 = j2;
                    this.c.post(new Runnable() {
                        public void run() {
                            fastImageProgressListener2.onProgress(str3, j3, j4);
                        }
                    });
                }
            }
        }

        private boolean a(String str, long j, long j2, float f) {
            if (f == 0.0f || j == 0 || j2 == j) {
                return true;
            }
            long j3 = (long) (((((float) j) * 100.0f) / ((float) j2)) / f);
            Long l = this.b.get(str);
            if (l != null && j3 == l.longValue()) {
                return false;
            }
            this.b.put(str, Long.valueOf(j3));
            return true;
        }
    }

    private static class OkHttpProgressResponseBody extends ResponseBody {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final String f5283a;
        /* access modifiers changed from: private */
        public final ResponseBody b;
        /* access modifiers changed from: private */
        public final ResponseProgressListener c;
        private BufferedSource d;

        OkHttpProgressResponseBody(String str, ResponseBody responseBody, ResponseProgressListener responseProgressListener) {
            this.f5283a = str;
            this.b = responseBody;
            this.c = responseProgressListener;
        }

        public MediaType contentType() {
            return this.b.contentType();
        }

        public long contentLength() {
            return this.b.contentLength();
        }

        public BufferedSource source() {
            if (this.d == null) {
                this.d = Okio.buffer(a((Source) this.b.source()));
            }
            return this.d;
        }

        private Source a(Source source) {
            return new ForwardingSource(source) {

                /* renamed from: a  reason: collision with root package name */
                long f5284a = 0;

                public long read(Buffer buffer, long j) throws IOException {
                    long read = super.read(buffer, j);
                    long contentLength = OkHttpProgressResponseBody.this.b.contentLength();
                    if (read == -1) {
                        this.f5284a = contentLength;
                    } else {
                        this.f5284a += read;
                    }
                    OkHttpProgressResponseBody.this.c.a(OkHttpProgressResponseBody.this.f5283a, this.f5284a, contentLength);
                    return read;
                }
            };
        }
    }
}
