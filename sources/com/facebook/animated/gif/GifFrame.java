package com.facebook.animated.gif;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GifFrame implements AnimatedImageFrame {
    @DoNotStrip
    private long mNativeContext;

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDisposalMode();

    private native int nativeGetDurationMs();

    private native int nativeGetHeight();

    private native int nativeGetTransparentPixelColor();

    private native int nativeGetWidth();

    private native int nativeGetXOffset();

    private native int nativeGetYOffset();

    private native boolean nativeHasTransparency();

    private native void nativeRenderFrame(int i, int i2, Bitmap bitmap);

    @DoNotStrip
    GifFrame(long j) {
        this.mNativeContext = j;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        nativeFinalize();
    }

    public void dispose() {
        nativeDispose();
    }

    public void renderFrame(int i, int i2, Bitmap bitmap) {
        nativeRenderFrame(i, i2, bitmap);
    }

    public int getDurationMs() {
        return nativeGetDurationMs();
    }

    public int getWidth() {
        return nativeGetWidth();
    }

    public int getHeight() {
        return nativeGetHeight();
    }

    public int getXOffset() {
        return nativeGetXOffset();
    }

    public int getYOffset() {
        return nativeGetYOffset();
    }

    public boolean hasTransparency() {
        return nativeHasTransparency();
    }

    public int getTransparentPixelColor() {
        return nativeGetTransparentPixelColor();
    }

    public int getDisposalMode() {
        return nativeGetDisposalMode();
    }
}
