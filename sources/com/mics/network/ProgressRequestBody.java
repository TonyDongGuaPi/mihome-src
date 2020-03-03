package com.mics.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class ProgressRequestBody extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private MediaType f7772a = MultipartBody.MIXED;
    private ProgressListener b;
    private File c;

    public interface ProgressListener {
        void a(long j, long j2, File file);
    }

    public ProgressRequestBody(MediaType mediaType, File file, ProgressListener progressListener) {
        this.f7772a = mediaType;
        this.c = file;
        this.b = progressListener;
    }

    @Nullable
    public MediaType contentType() {
        return this.f7772a;
    }

    public long contentLength() throws IOException {
        return this.c.length();
    }

    public String a() {
        return this.c.getName();
    }

    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {
        long contentLength = contentLength();
        try {
            Source source = Okio.source(this.c);
            Buffer buffer = new Buffer();
            long j = 0;
            while (true) {
                long read = source.read(buffer, 2048);
                if (read != -1) {
                    bufferedSink.write(buffer, read);
                    bufferedSink.flush();
                    long j2 = j + read;
                    if (this.b != null) {
                        this.b.a(contentLength, j2, this.c);
                    }
                    j = j2;
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
