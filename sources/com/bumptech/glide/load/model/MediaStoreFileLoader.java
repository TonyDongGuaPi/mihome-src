package com.bumptech.glide.load.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.io.FileNotFoundException;

public final class MediaStoreFileLoader implements ModelLoader<Uri, File> {

    /* renamed from: a  reason: collision with root package name */
    private final Context f4952a;

    public MediaStoreFileLoader(Context context) {
        this.f4952a = context;
    }

    public ModelLoader.LoadData<File> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), new FilePathFetcher(this.f4952a, uri));
    }

    public boolean a(@NonNull Uri uri) {
        return MediaStoreUtil.a(uri);
    }

    private static class FilePathFetcher implements DataFetcher<File> {

        /* renamed from: a  reason: collision with root package name */
        private static final String[] f4954a = {Downloads._DATA};
        private final Context b;
        private final Uri c;

        public void b() {
        }

        public void c() {
        }

        FilePathFetcher(Context context, Uri uri) {
            this.b = context;
            this.c = uri;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super File> dataCallback) {
            Cursor query = this.b.getContentResolver().query(this.c, f4954a, (String) null, (String[]) null, (String) null);
            String str = null;
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndexOrThrow(Downloads._DATA));
                    }
                } finally {
                    query.close();
                }
            }
            if (TextUtils.isEmpty(str)) {
                dataCallback.a((Exception) new FileNotFoundException("Failed to find file path for: " + this.c));
                return;
            }
            dataCallback.a(new File(str));
        }

        @NonNull
        public Class<File> a() {
            return File.class;
        }

        @NonNull
        public DataSource d() {
            return DataSource.LOCAL;
        }
    }

    public static final class Factory implements ModelLoaderFactory<Uri, File> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f4953a;

        public void a() {
        }

        public Factory(Context context) {
            this.f4953a = context;
        }

        @NonNull
        public ModelLoader<Uri, File> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreFileLoader(this.f4953a);
        }
    }
}
