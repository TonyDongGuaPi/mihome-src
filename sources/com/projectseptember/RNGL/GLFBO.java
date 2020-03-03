package com.projectseptember.RNGL;

import android.opengl.GLES20;
import com.xiaomi.smarthome.fastvideo.GlslFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class GLFBO {

    /* renamed from: a  reason: collision with root package name */
    public final List<GLTexture> f8565a = new ArrayList();
    /* access modifiers changed from: private */
    public int b;
    private int c;
    private int d;
    private Executor e;

    public GLFBO(Executor executor) {
        this.c = 0;
        this.d = 0;
        this.e = executor;
        FBOState fBOState = new FBOState();
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        this.b = iArr[0];
        GLES20.glBindFramebuffer(36160, this.b);
        for (int i = 0; i < 1; i++) {
            this.f8565a.add(a(this.c, this.d, 36064 + i));
        }
        fBOState.a();
    }

    private GLTexture a(int i, int i2, int i3) {
        GLTexture gLTexture = new GLTexture(this.e);
        gLTexture.a();
        gLTexture.b(i, i2);
        GLES20.glFramebufferTexture2D(36160, i3, GlslFilter.f, gLTexture.c(), 0);
        return gLTexture;
    }

    class FBOState {
        private int b;
        private int c;
        private int d;

        public FBOState() {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = new int[1];
            GLES20.glGetIntegerv(36006, iArr, 0);
            GLES20.glGetIntegerv(36007, iArr2, 0);
            GLES20.glGetIntegerv(32873, iArr3, 0);
            this.b = iArr[0];
            this.c = iArr2[0];
            this.d = iArr3[0];
        }

        /* access modifiers changed from: private */
        public void a() {
            GLES20.glBindFramebuffer(36160, this.b);
            GLES20.glBindRenderbuffer(36161, this.c);
            GLES20.glBindTexture(GlslFilter.f, this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        this.e.execute(new Runnable() {
            public void run() {
                GLES20.glDeleteFramebuffers(1, new int[]{GLFBO.this.b}, 0);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a() {
        int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        if (glCheckFramebufferStatus != 36053) {
            switch (glCheckFramebufferStatus) {
                case 36054:
                    throw new RuntimeException("Framebuffer incomplete attachment");
                case 36055:
                    throw new RuntimeException("Framebuffer incomplete missing attachment");
                case 36057:
                    throw new RuntimeException("Framebuffer incomplete dimensions");
                case 36061:
                    throw new RuntimeException("Framebuffer unsupported");
                default:
                    throw new RuntimeException("Failed to create framebuffer: " + glCheckFramebufferStatus);
            }
        }
    }

    public void b() {
        GLES20.glBindFramebuffer(36160, this.b);
        GLES20.glViewport(0, 0, this.c, this.d);
    }

    public void a(int i, int i2) {
        if (i != this.c || i2 != this.d) {
            int[] iArr = new int[1];
            GLES20.glGetIntegerv(34024, iArr, 0);
            if (i < 0 || i > iArr[0] || i2 < 0 || i2 > iArr[0]) {
                throw new IllegalArgumentException("Can't resize framebuffer. Invalid dimensions");
            }
            this.c = i;
            this.d = i2;
            FBOState fBOState = new FBOState();
            for (GLTexture b2 : this.f8565a) {
                b2.b(i, i2);
            }
            GLES20.glBindFramebuffer(36160, this.b);
            a();
            fBOState.a();
        }
    }
}
