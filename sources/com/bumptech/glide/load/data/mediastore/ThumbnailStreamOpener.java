package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

class ThumbnailStreamOpener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4851a = "ThumbStreamOpener";
    private static final FileService b = new FileService();
    private final FileService c;
    private final ThumbnailQuery d;
    private final ArrayPool e;
    private final ContentResolver f;
    private final List<ImageHeaderParser> g;

    ThumbnailStreamOpener(List<ImageHeaderParser> list, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver) {
        this(list, b, thumbnailQuery, arrayPool, contentResolver);
    }

    ThumbnailStreamOpener(List<ImageHeaderParser> list, FileService fileService, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver) {
        this.c = fileService;
        this.d = thumbnailQuery;
        this.e = arrayPool;
        this.f = contentResolver;
        this.g = list;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0027 A[Catch:{ all -> 0x0044 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003f A[SYNTHETIC, Splitter:B:21:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0047 A[SYNTHETIC, Splitter:B:26:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(android.net.Uri r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.ContentResolver r1 = r6.f     // Catch:{ IOException | NullPointerException -> 0x001a, all -> 0x0017 }
            java.io.InputStream r1 = r1.openInputStream(r7)     // Catch:{ IOException | NullPointerException -> 0x001a, all -> 0x0017 }
            java.util.List<com.bumptech.glide.load.ImageHeaderParser> r0 = r6.g     // Catch:{ IOException | NullPointerException -> 0x0015 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r2 = r6.e     // Catch:{ IOException | NullPointerException -> 0x0015 }
            int r0 = com.bumptech.glide.load.ImageHeaderParserUtils.b(r0, r1, r2)     // Catch:{ IOException | NullPointerException -> 0x0015 }
            if (r1 == 0) goto L_0x0014
            r1.close()     // Catch:{ IOException -> 0x0014 }
        L_0x0014:
            return r0
        L_0x0015:
            r0 = move-exception
            goto L_0x001e
        L_0x0017:
            r7 = move-exception
            r1 = r0
            goto L_0x0045
        L_0x001a:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x001e:
            java.lang.String r2 = "ThumbStreamOpener"
            r3 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r3)     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x003d
            java.lang.String r2 = "ThumbStreamOpener"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r3.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = "Failed to open uri: "
            r3.append(r4)     // Catch:{ all -> 0x0044 }
            r3.append(r7)     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x0044 }
            android.util.Log.d(r2, r7, r0)     // Catch:{ all -> 0x0044 }
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            r7 = -1
            return r7
        L_0x0044:
            r7 = move-exception
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.mediastore.ThumbnailStreamOpener.a(android.net.Uri):int");
    }

    public InputStream b(Uri uri) throws FileNotFoundException {
        String c2 = c(uri);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        File a2 = this.c.a(c2);
        if (!a(a2)) {
            return null;
        }
        Uri fromFile = Uri.fromFile(a2);
        try {
            return this.f.openInputStream(fromFile);
        } catch (NullPointerException e2) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + " -> " + fromFile).initCause(e2));
        }
    }

    @Nullable
    private String c(@NonNull Uri uri) {
        Cursor a2 = this.d.a(uri);
        if (a2 != null) {
            try {
                if (a2.moveToFirst()) {
                    return a2.getString(0);
                }
            } finally {
                if (a2 != null) {
                    a2.close();
                }
            }
        }
        if (a2 != null) {
            a2.close();
        }
        return null;
    }

    private boolean a(File file) {
        return this.c.a(file) && 0 < this.c.b(file);
    }
}
