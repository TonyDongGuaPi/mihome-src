package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.IOException;

public abstract class AssetPathFetcher<T> implements DataFetcher<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4837a = "AssetPathFetcher";
    private final String b;
    private final AssetManager c;
    private T d;

    /* access modifiers changed from: protected */
    public abstract T a(AssetManager assetManager, String str) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void a(T t) throws IOException;

    public void c() {
    }

    public AssetPathFetcher(AssetManager assetManager, String str) {
        this.c = assetManager;
        this.b = str;
    }

    public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            this.d = a(this.c, this.b);
            dataCallback.a(this.d);
        } catch (IOException e) {
            if (Log.isLoggable(f4837a, 3)) {
                Log.d(f4837a, "Failed to load data from asset manager", e);
            }
            dataCallback.a((Exception) e);
        }
    }

    public void b() {
        if (this.d != null) {
            try {
                a(this.d);
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public DataSource d() {
        return DataSource.LOCAL;
    }
}
