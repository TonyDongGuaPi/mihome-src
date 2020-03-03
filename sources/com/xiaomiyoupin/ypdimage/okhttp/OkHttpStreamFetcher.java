package com.xiaomiyoupin.ypdimage.okhttp;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpStreamFetcher implements DataFetcher<InputStream>, Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1802a = "OkHttpFetcher";
    private final Call.Factory b;
    private final GlideUrl c;
    private InputStream d;
    private ResponseBody e;
    private DataFetcher.DataCallback<? super InputStream> f;
    private volatile Call g;

    public OkHttpStreamFetcher(Call.Factory factory, GlideUrl glideUrl) {
        this.b = factory;
        this.c = glideUrl;
    }

    public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> dataCallback) {
        Request.Builder url = new Request.Builder().url(this.c.b());
        for (Map.Entry next : this.c.c().entrySet()) {
            url.addHeader((String) next.getKey(), (String) next.getValue());
        }
        Request build = url.build();
        this.f = dataCallback;
        this.g = this.b.newCall(build);
        this.g.enqueue(this);
    }

    public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
        if (Log.isLoggable(f1802a, 3)) {
            Log.d(f1802a, "OkHttp failed to obtain result", iOException);
        }
        this.f.a((Exception) iOException);
    }

    public void onResponse(@NonNull Call call, @NonNull Response response) {
        this.e = response.body();
        if (response.isSuccessful()) {
            this.d = ContentLengthInputStream.a(this.e.byteStream(), ((ResponseBody) Preconditions.a(this.e)).contentLength());
            this.f.a(this.d);
            return;
        }
        this.f.a((Exception) new HttpException(response.message(), response.code()));
    }

    public void b() {
        try {
            if (this.d != null) {
                this.d.close();
            }
        } catch (IOException unused) {
        }
        if (this.e != null) {
            this.e.close();
        }
        this.f = null;
    }

    public void c() {
        Call call = this.g;
        if (call != null) {
            call.cancel();
        }
    }

    @NonNull
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @NonNull
    public DataSource d() {
        return DataSource.REMOTE;
    }
}
