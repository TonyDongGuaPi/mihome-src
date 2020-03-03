package com.google.android.exoplayer2.util;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.support.annotation.Nullable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TargetApi(17)
public final class EGLSurfaceTexture implements SurfaceTexture.OnFrameAvailableListener, Runnable {
    private static final int[] EGL_CONFIG_ATTRIBUTES = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    private static final int EGL_PROTECTED_CONTENT_EXT = 12992;
    private static final int EGL_SURFACE_HEIGHT = 1;
    private static final int EGL_SURFACE_WIDTH = 1;
    public static final int SECURE_MODE_NONE = 0;
    public static final int SECURE_MODE_PROTECTED_PBUFFER = 2;
    public static final int SECURE_MODE_SURFACELESS_CONTEXT = 1;
    @Nullable
    private final TextureImageListener callback;
    @Nullable
    private EGLContext context;
    @Nullable
    private EGLDisplay display;
    private final Handler handler;
    @Nullable
    private EGLSurface surface;
    @Nullable
    private SurfaceTexture texture;
    private final int[] textureIdHolder;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SecureMode {
    }

    public interface TextureImageListener {
        void onFrameAvailable();
    }

    public static final class GlException extends RuntimeException {
        private GlException(String str) {
            super(str);
        }
    }

    public EGLSurfaceTexture(Handler handler2) {
        this(handler2, (TextureImageListener) null);
    }

    public EGLSurfaceTexture(Handler handler2, @Nullable TextureImageListener textureImageListener) {
        this.handler = handler2;
        this.callback = textureImageListener;
        this.textureIdHolder = new int[1];
    }

    public void init(int i) {
        this.display = getDefaultDisplay();
        EGLConfig chooseEGLConfig = chooseEGLConfig(this.display);
        this.context = createEGLContext(this.display, chooseEGLConfig, i);
        this.surface = createEGLSurface(this.display, chooseEGLConfig, this.context, i);
        generateTextureIds(this.textureIdHolder);
        this.texture = new SurfaceTexture(this.textureIdHolder[0]);
        this.texture.setOnFrameAvailableListener(this);
    }

    public void release() {
        this.handler.removeCallbacks(this);
        try {
            if (this.texture != null) {
                this.texture.release();
                GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
            }
        } finally {
            if (this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglMakeCurrent(this.display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            }
            if (this.surface != null && !this.surface.equals(EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.display, this.surface);
            }
            if (this.context != null) {
                EGL14.eglDestroyContext(this.display, this.context);
            }
            if (Util.SDK_INT >= 19) {
                EGL14.eglReleaseThread();
            }
            if (this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglTerminate(this.display);
            }
            this.display = null;
            this.context = null;
            this.surface = null;
            this.texture = null;
        }
    }

    public SurfaceTexture getSurfaceTexture() {
        return (SurfaceTexture) Assertions.checkNotNull(this.texture);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.handler.post(this);
    }

    public void run() {
        dispatchOnFrameAvailable();
        if (this.texture != null) {
            try {
                this.texture.updateTexImage();
            } catch (RuntimeException unused) {
            }
        }
    }

    private void dispatchOnFrameAvailable() {
        if (this.callback != null) {
            this.callback.onFrameAvailable();
        }
    }

    private static EGLDisplay getDefaultDisplay() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != null) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                return eglGetDisplay;
            }
            throw new GlException("eglInitialize failed");
        }
        throw new GlException("eglGetDisplay failed");
    }

    private static EGLConfig chooseEGLConfig(EGLDisplay eGLDisplay) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        boolean eglChooseConfig = EGL14.eglChooseConfig(eGLDisplay, EGL_CONFIG_ATTRIBUTES, 0, eGLConfigArr, 0, 1, iArr, 0);
        if (eglChooseConfig && iArr[0] > 0 && eGLConfigArr[0] != null) {
            return eGLConfigArr[0];
        }
        throw new GlException(Util.formatInvariant("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", Boolean.valueOf(eglChooseConfig), Integer.valueOf(iArr[0]), eGLConfigArr[0]));
    }

    private static EGLContext createEGLContext(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
        int[] iArr;
        if (i == 0) {
            iArr = new int[]{12440, 2, 12344};
        } else {
            iArr = new int[]{12440, 2, EGL_PROTECTED_CONTENT_EXT, 1, 12344};
        }
        EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
        if (eglCreateContext != null) {
            return eglCreateContext;
        }
        throw new GlException("eglCreateContext failed");
    }

    private static EGLSurface createEGLSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, EGLContext eGLContext, int i) {
        EGLSurface eGLSurface;
        int[] iArr;
        if (i == 1) {
            eGLSurface = EGL14.EGL_NO_SURFACE;
        } else {
            if (i == 2) {
                iArr = new int[]{12375, 1, 12374, 1, EGL_PROTECTED_CONTENT_EXT, 1, 12344};
            } else {
                iArr = new int[]{12375, 1, 12374, 1, 12344};
            }
            eGLSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, iArr, 0);
            if (eGLSurface == null) {
                throw new GlException("eglCreatePbufferSurface failed");
            }
        }
        if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext)) {
            return eGLSurface;
        }
        throw new GlException("eglMakeCurrent failed");
    }

    private static void generateTextureIds(int[] iArr) {
        GLES20.glGenTextures(1, iArr, 0);
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new GlException("glGenTextures failed. Error: " + Integer.toHexString(glGetError));
        }
    }
}