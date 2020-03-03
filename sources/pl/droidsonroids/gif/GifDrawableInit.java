package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import pl.droidsonroids.gif.GifDrawableInit;
import pl.droidsonroids.gif.InputSource;
import pl.droidsonroids.gif.annotations.Beta;

public abstract class GifDrawableInit<T extends GifDrawableInit<T>> {

    /* renamed from: a  reason: collision with root package name */
    private InputSource f11953a;
    private GifDrawable b;
    private ScheduledThreadPoolExecutor c;
    private boolean d = true;
    private GifOptions e = new GifOptions();

    /* access modifiers changed from: protected */
    public abstract T b();

    public T a(@IntRange(from = 1, to = 65535) int i) {
        this.e.a(i);
        return b();
    }

    public GifDrawable c() throws IOException {
        if (this.f11953a != null) {
            return this.f11953a.a(this.b, this.c, this.d, this.e);
        }
        throw new NullPointerException("Source is not set");
    }

    public T a(GifDrawable gifDrawable) {
        this.b = gifDrawable;
        return b();
    }

    public T b(int i) {
        this.c = new ScheduledThreadPoolExecutor(i);
        return b();
    }

    public T a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.c = scheduledThreadPoolExecutor;
        return b();
    }

    public T a(boolean z) {
        this.d = z;
        return b();
    }

    public T b(boolean z) {
        return a(z);
    }

    @Beta
    public T a(@Nullable GifOptions gifOptions) {
        this.e.a(gifOptions);
        return b();
    }

    public T a(InputStream inputStream) {
        this.f11953a = new InputSource.InputStreamSource(inputStream);
        return b();
    }

    public T a(AssetFileDescriptor assetFileDescriptor) {
        this.f11953a = new InputSource.AssetFileDescriptorSource(assetFileDescriptor);
        return b();
    }

    public T a(FileDescriptor fileDescriptor) {
        this.f11953a = new InputSource.FileDescriptorSource(fileDescriptor);
        return b();
    }

    public T a(AssetManager assetManager, String str) {
        this.f11953a = new InputSource.AssetSource(assetManager, str);
        return b();
    }

    public T a(ContentResolver contentResolver, Uri uri) {
        this.f11953a = new InputSource.UriSource(contentResolver, uri);
        return b();
    }

    public T a(File file) {
        this.f11953a = new InputSource.FileSource(file);
        return b();
    }

    public T a(String str) {
        this.f11953a = new InputSource.FileSource(str);
        return b();
    }

    public T a(byte[] bArr) {
        this.f11953a = new InputSource.ByteArraySource(bArr);
        return b();
    }

    public T a(ByteBuffer byteBuffer) {
        this.f11953a = new InputSource.DirectByteBufferSource(byteBuffer);
        return b();
    }

    public T a(Resources resources, int i) {
        this.f11953a = new InputSource.ResourcesSource(resources, i);
        return b();
    }

    public InputSource d() {
        return this.f11953a;
    }

    public GifDrawable e() {
        return this.b;
    }

    public ScheduledThreadPoolExecutor f() {
        return this.c;
    }

    public boolean g() {
        return this.d;
    }

    public GifOptions h() {
        return this.e;
    }
}
