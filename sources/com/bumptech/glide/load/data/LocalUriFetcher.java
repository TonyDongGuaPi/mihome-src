package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T> implements DataFetcher<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4845a = "LocalUriFetcher";
    private final Uri b;
    private final ContentResolver c;
    private T d;

    /* access modifiers changed from: protected */
    public abstract void a(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    public void c() {
    }

    public LocalUriFetcher(ContentResolver contentResolver, Uri uri) {
        this.c = contentResolver;
        this.b = uri;
    }

    public final void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            this.d = b(this.b, this.c);
            dataCallback.a(this.d);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(f4845a, 3)) {
                Log.d(f4845a, "Failed to open Uri", e);
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
