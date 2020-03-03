package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.ExifOrientationStream;
import com.xiaomi.smarthome.download.Downloads;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ThumbFetcher implements DataFetcher<InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4848a = "MediaStoreThumbFetcher";
    private final Uri b;
    private final ThumbnailStreamOpener c;
    private InputStream d;

    public void c() {
    }

    public static ThumbFetcher a(Context context, Uri uri) {
        return a(context, uri, new ImageThumbnailQuery(context.getContentResolver()));
    }

    public static ThumbFetcher b(Context context, Uri uri) {
        return a(context, uri, new VideoThumbnailQuery(context.getContentResolver()));
    }

    private static ThumbFetcher a(Context context, Uri uri, ThumbnailQuery thumbnailQuery) {
        return new ThumbFetcher(uri, new ThumbnailStreamOpener(Glide.b(context).j().a(), thumbnailQuery, Glide.b(context).c(), context.getContentResolver()));
    }

    @VisibleForTesting
    ThumbFetcher(Uri uri, ThumbnailStreamOpener thumbnailStreamOpener) {
        this.b = uri;
        this.c = thumbnailStreamOpener;
    }

    public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> dataCallback) {
        try {
            this.d = e();
            dataCallback.a(this.d);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(f4848a, 3)) {
                Log.d(f4848a, "Failed to find thumbnail file", e);
            }
            dataCallback.a((Exception) e);
        }
    }

    private InputStream e() throws FileNotFoundException {
        InputStream b2 = this.c.b(this.b);
        int a2 = b2 != null ? this.c.a(this.b) : -1;
        return a2 != -1 ? new ExifOrientationStream(b2, a2) : b2;
    }

    public void b() {
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @NonNull
    public DataSource d() {
        return DataSource.LOCAL;
    }

    static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] b = {Downloads._DATA};
        private static final String c = "kind = 1 AND video_id = ?";

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f4850a;

        VideoThumbnailQuery(ContentResolver contentResolver) {
            this.f4850a = contentResolver;
        }

        public Cursor a(Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return this.f4850a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, b, c, new String[]{lastPathSegment}, (String) null);
        }
    }

    static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] b = {Downloads._DATA};
        private static final String c = "kind = 1 AND image_id = ?";

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f4849a;

        ImageThumbnailQuery(ContentResolver contentResolver) {
            this.f4849a = contentResolver;
        }

        public Cursor a(Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return this.f4849a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, b, c, new String[]{lastPathSegment}, (String) null);
        }
    }
}
