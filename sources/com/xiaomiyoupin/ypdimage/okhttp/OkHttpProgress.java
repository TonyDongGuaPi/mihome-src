package com.xiaomiyoupin.ypdimage.okhttp;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.util.WeakHashMap;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class OkHttpProgress {

    interface ResponseProgressListener {
        void a(HttpUrl httpUrl, long j, long j2);
    }

    public interface UIonProgressListener {
        void a(long j, long j2);

        float j();
    }

    public static void a(String str) {
        DispatchingProgressListener.a(str);
    }

    public static void a(String str, UIonProgressListener uIonProgressListener) {
        DispatchingProgressListener.a(str, uIonProgressListener);
    }

    static class DispatchingProgressListener implements ResponseProgressListener {

        /* renamed from: a  reason: collision with root package name */
        private static final WeakHashMap<String, UIonProgressListener> f1798a = new WeakHashMap<>();
        private static final WeakHashMap<String, Long> b = new WeakHashMap<>();
        private final Handler c = new Handler(Looper.getMainLooper());

        DispatchingProgressListener() {
        }

        static void a(String str) {
            f1798a.remove(str);
            b.remove(str);
        }

        static void a(String str, UIonProgressListener uIonProgressListener) {
            f1798a.put(str, uIonProgressListener);
        }

        public void a(HttpUrl httpUrl, long j, long j2) {
            String httpUrl2 = httpUrl.toString();
            UIonProgressListener uIonProgressListener = f1798a.get(httpUrl2);
            if (uIonProgressListener != null) {
                if (j2 <= j) {
                    a(httpUrl2);
                }
                if (a(httpUrl2, j, j2, uIonProgressListener.j())) {
                    final UIonProgressListener uIonProgressListener2 = uIonProgressListener;
                    final long j3 = j;
                    final long j4 = j2;
                    this.c.post(new Runnable() {
                        public void run() {
                            uIonProgressListener2.a(j3, j4);
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
            Long l = b.get(str);
            if (l != null && j3 == l.longValue()) {
                return false;
            }
            b.put(str, Long.valueOf(j3));
            return true;
        }
    }

    static class OkHttpProgressResponseBody extends ResponseBody {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final HttpUrl f1800a;
        /* access modifiers changed from: private */
        public final ResponseBody b;
        /* access modifiers changed from: private */
        public final ResponseProgressListener c;
        private BufferedSource d;

        OkHttpProgressResponseBody(HttpUrl httpUrl, ResponseBody responseBody, ResponseProgressListener responseProgressListener) {
            this.f1800a = httpUrl;
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
                long f1801a = 0;

                public long read(Buffer buffer, long j) throws IOException {
                    long read = super.read(buffer, j);
                    long contentLength = OkHttpProgressResponseBody.this.b.contentLength();
                    if (read == -1) {
                        this.f1801a = contentLength;
                    } else {
                        this.f1801a += read;
                    }
                    OkHttpProgressResponseBody.this.c.a(OkHttpProgressResponseBody.this.f1800a, this.f1801a, contentLength);
                    return read;
                }
            };
        }
    }
}
