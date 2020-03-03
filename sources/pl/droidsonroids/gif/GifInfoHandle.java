package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.view.Surface;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.text.Typography;

final class GifInfoHandle {

    /* renamed from: a  reason: collision with root package name */
    private volatile long f11954a;

    private static native void bindSurface(long j, Surface surface, long[] jArr);

    static native int createTempNativeFileDescriptor() throws GifIOException;

    static native int extractNativeFileDescriptor(FileDescriptor fileDescriptor, boolean z) throws GifIOException;

    private static native void free(long j);

    private static native long getAllocationByteCount(long j);

    private static native String getComment(long j);

    private static native int getCurrentFrameIndex(long j);

    private static native int getCurrentLoop(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getFrameDuration(long j, int i);

    private static native int getHeight(long j);

    private static native int getLoopCount(long j);

    private static native long getMetadataByteCount(long j);

    private static native int getNativeErrorCode(long j);

    private static native int getNumberOfFrames(long j);

    private static native long[] getSavedState(long j);

    private static native long getSourceLength(long j);

    private static native int getWidth(long j);

    private static native void glTexImage2D(long j, int i, int i2);

    private static native void glTexSubImage2D(long j, int i, int i2);

    private static native void initTexImageDescriptor(long j);

    private static native boolean isAnimationCompleted(long j);

    private static native boolean isOpaque(long j);

    static native long openByteArray(byte[] bArr) throws GifIOException;

    static native long openDirectByteBuffer(ByteBuffer byteBuffer) throws GifIOException;

    static native long openFile(String str) throws GifIOException;

    static native long openNativeFileDescriptor(int i, long j) throws GifIOException;

    static native long openStream(InputStream inputStream) throws GifIOException;

    private static native void postUnbindSurface(long j);

    private static native long renderFrame(long j, Bitmap bitmap);

    private static native boolean reset(long j);

    private static native long restoreRemainder(long j);

    private static native int restoreSavedState(long j, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j);

    private static native void seekToFrame(long j, int i, Bitmap bitmap);

    private static native void seekToFrameGL(long j, int i);

    private static native void seekToTime(long j, int i, Bitmap bitmap);

    private static native void setLoopCount(long j, char c);

    private static native void setOptions(long j, char c, boolean z);

    private static native void setSpeedFactor(long j, float f);

    private static native void startDecoderThread(long j);

    private static native void stopDecoderThread(long j);

    static {
        LibraryLoader.a();
    }

    GifInfoHandle() {
    }

    GifInfoHandle(FileDescriptor fileDescriptor) throws GifIOException {
        this.f11954a = a(fileDescriptor, 0, true);
    }

    GifInfoHandle(byte[] bArr) throws GifIOException {
        this.f11954a = openByteArray(bArr);
    }

    GifInfoHandle(ByteBuffer byteBuffer) throws GifIOException {
        this.f11954a = openDirectByteBuffer(byteBuffer);
    }

    GifInfoHandle(String str) throws GifIOException {
        this.f11954a = openFile(str);
    }

    GifInfoHandle(InputStream inputStream) throws GifIOException {
        if (inputStream.markSupported()) {
            this.f11954a = openStream(inputStream);
            return;
        }
        throw new IllegalArgumentException("InputStream does not support marking");
    }

    GifInfoHandle(AssetFileDescriptor assetFileDescriptor) throws IOException {
        try {
            this.f11954a = a(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), false);
        } finally {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    private static long a(FileDescriptor fileDescriptor, long j, boolean z) throws GifIOException {
        int i;
        if (Build.VERSION.SDK_INT > 27) {
            try {
                i = a(fileDescriptor, z);
            } catch (Exception e) {
                throw new GifIOException(GifError.OPEN_FAILED.errorCode, e.getMessage());
            }
        } else {
            i = extractNativeFileDescriptor(fileDescriptor, z);
        }
        return openNativeFileDescriptor(i, j);
    }

    @RequiresApi(21)
    private static int a(FileDescriptor fileDescriptor, boolean z) throws GifIOException, ErrnoException {
        try {
            int createTempNativeFileDescriptor = createTempNativeFileDescriptor();
            Os.dup2(fileDescriptor, createTempNativeFileDescriptor);
            return createTempNativeFileDescriptor;
        } finally {
            if (z) {
                Os.close(fileDescriptor);
            }
        }
    }

    static GifInfoHandle a(ContentResolver contentResolver, Uri uri) throws IOException {
        if ("file".equals(uri.getScheme())) {
            return new GifInfoHandle(uri.getPath());
        }
        AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (openAssetFileDescriptor != null) {
            return new GifInfoHandle(openAssetFileDescriptor);
        }
        throw new IOException("Could not open AssetFileDescriptor for " + uri);
    }

    /* access modifiers changed from: package-private */
    public synchronized long a(Bitmap bitmap) {
        return renderFrame(this.f11954a, bitmap);
    }

    /* access modifiers changed from: package-private */
    public void a(Surface surface, long[] jArr) {
        bindSurface(this.f11954a, surface, jArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        free(this.f11954a);
        this.f11954a = 0;
    }

    /* access modifiers changed from: package-private */
    public synchronized long b() {
        return restoreRemainder(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean c() {
        return reset(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        saveRemainder(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized String e() {
        return getComment(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int f() {
        return getLoopCount(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void a(@IntRange(from = 0, to = 65535) int i) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("Loop count of range <0, 65535>");
        }
        synchronized (this) {
            setLoopCount(this.f11954a, (char) i);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized long g() {
        return getSourceLength(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int h() {
        return getNativeErrorCode(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void a(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Speed factor is not positive");
        }
        if (f < 4.656613E-10f) {
            f = 4.656613E-10f;
        }
        synchronized (this) {
            setSpeedFactor(this.f11954a, f);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized int i() {
        return getDuration(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int j() {
        return getCurrentPosition(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int k() {
        return getCurrentFrameIndex(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int l() {
        return getCurrentLoop(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(@IntRange(from = 0, to = 2147483647L) int i, Bitmap bitmap) {
        seekToTime(this.f11954a, i, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(@IntRange(from = 0, to = 2147483647L) int i, Bitmap bitmap) {
        seekToFrame(this.f11954a, i, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized long m() {
        return getAllocationByteCount(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized long n() {
        return getMetadataByteCount(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean o() {
        return this.f11954a == 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            a();
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void p() {
        postUnbindSurface(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean q() {
        return isAnimationCompleted(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized long[] r() {
        return getSavedState(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int a(long[] jArr, Bitmap bitmap) {
        return restoreSavedState(this.f11954a, jArr, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized int b(@IntRange(from = 0) int i) {
        d(i);
        return getFrameDuration(this.f11954a, i);
    }

    /* access modifiers changed from: package-private */
    public void a(char c, boolean z) {
        setOptions(this.f11954a, c, z);
    }

    /* access modifiers changed from: package-private */
    public synchronized int s() {
        return getWidth(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int t() {
        return getHeight(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized int u() {
        return getNumberOfFrames(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean v() {
        return isOpaque(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) {
        glTexImage2D(this.f11954a, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void b(int i, int i2) {
        glTexSubImage2D(this.f11954a, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void w() {
        startDecoderThread(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void x() {
        stopDecoderThread(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void y() {
        initTexImageDescriptor(this.f11954a);
    }

    /* access modifiers changed from: package-private */
    public void c(@IntRange(from = 0) int i) {
        d(i);
        seekToFrameGL(this.f11954a, i);
    }

    private void d(@IntRange(from = 0) int i) {
        int numberOfFrames = getNumberOfFrames(this.f11954a);
        if (i < 0 || i >= numberOfFrames) {
            throw new IndexOutOfBoundsException("Frame index is not in range <0;" + numberOfFrames + Typography.e);
        }
    }
}
