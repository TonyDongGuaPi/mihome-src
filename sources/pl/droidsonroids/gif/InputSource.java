package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class InputSource {
    /* access modifiers changed from: package-private */
    public abstract GifInfoHandle a() throws IOException;

    private InputSource() {
    }

    /* access modifiers changed from: package-private */
    public final GifDrawable a(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z, GifOptions gifOptions) throws IOException {
        return new GifDrawable(a(gifOptions), gifDrawable, scheduledThreadPoolExecutor, z);
    }

    /* access modifiers changed from: package-private */
    public final GifInfoHandle a(@NonNull GifOptions gifOptions) throws IOException {
        GifInfoHandle a2 = a();
        a2.a(gifOptions.f11955a, gifOptions.b);
        return a2;
    }

    public static final class DirectByteBufferSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final ByteBuffer f11968a;

        public DirectByteBufferSource(@NonNull ByteBuffer byteBuffer) {
            super();
            this.f11968a = byteBuffer;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws GifIOException {
            return new GifInfoHandle(this.f11968a);
        }
    }

    public static final class ByteArraySource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final byte[] f11967a;

        public ByteArraySource(@NonNull byte[] bArr) {
            super();
            this.f11967a = bArr;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws GifIOException {
            return new GifInfoHandle(this.f11967a);
        }
    }

    public static final class FileSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final String f11970a;

        public FileSource(@NonNull File file) {
            super();
            this.f11970a = file.getPath();
        }

        public FileSource(@NonNull String str) {
            super();
            this.f11970a = str;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws GifIOException {
            return new GifInfoHandle(this.f11970a);
        }
    }

    public static final class UriSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f11973a;
        private final Uri b;

        public UriSource(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
            super();
            this.f11973a = contentResolver;
            this.b = uri;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return GifInfoHandle.a(this.f11973a, this.b);
        }
    }

    public static final class AssetSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final AssetManager f11966a;
        private final String b;

        public AssetSource(@NonNull AssetManager assetManager, @NonNull String str) {
            super();
            this.f11966a = assetManager;
            this.b = str;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return new GifInfoHandle(this.f11966a.openFd(this.b));
        }
    }

    public static final class FileDescriptorSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final FileDescriptor f11969a;

        public FileDescriptorSource(@NonNull FileDescriptor fileDescriptor) {
            super();
            this.f11969a = fileDescriptor;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return new GifInfoHandle(this.f11969a);
        }
    }

    public static final class InputStreamSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final InputStream f11971a;

        public InputStreamSource(@NonNull InputStream inputStream) {
            super();
            this.f11971a = inputStream;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return new GifInfoHandle(this.f11971a);
        }
    }

    public static class ResourcesSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final Resources f11972a;
        private final int b;

        public ResourcesSource(@NonNull Resources resources, @RawRes @DrawableRes int i) {
            super();
            this.f11972a = resources;
            this.b = i;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return new GifInfoHandle(this.f11972a.openRawResourceFd(this.b));
        }
    }

    public static class AssetFileDescriptorSource extends InputSource {

        /* renamed from: a  reason: collision with root package name */
        private final AssetFileDescriptor f11965a;

        public AssetFileDescriptorSource(@NonNull AssetFileDescriptor assetFileDescriptor) {
            super();
            this.f11965a = assetFileDescriptor;
        }

        /* access modifiers changed from: package-private */
        public GifInfoHandle a() throws IOException {
            return new GifInfoHandle(this.f11965a);
        }
    }
}
