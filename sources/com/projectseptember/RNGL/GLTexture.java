package com.projectseptember.RNGL;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.xiaomi.smarthome.fastvideo.GlslFilter;
import java.nio.Buffer;
import java.util.concurrent.Executor;

public class GLTexture {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f8574a;
    private Bitmap b = null;
    private Executor c;

    public GLTexture(Executor executor) {
        this.c = executor;
        d();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        this.b = null;
        this.c.execute(new Runnable() {
            public void run() {
                GLES20.glDeleteTextures(1, new int[]{GLTexture.this.f8574a}, 0);
            }
        });
    }

    private void d() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.f8574a = iArr[0];
        GLES20.glBindTexture(GlslFilter.f, this.f8574a);
        GLES20.glTexParameteri(GlslFilter.f, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.f, 10243, 33071);
    }

    public int a(int i) {
        GLES20.glActiveTexture(33984 + i);
        GLES20.glBindTexture(GlslFilter.f, this.f8574a);
        return i;
    }

    public void a() {
        GLES20.glBindTexture(GlslFilter.f, this.f8574a);
    }

    public void a(Bitmap bitmap) {
        if (bitmap != this.b) {
            this.b = bitmap;
            a();
            GLUtils.texImage2D(GlslFilter.f, 0, bitmap, 0);
        }
    }

    public void a(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                createBitmap.setPixel(i3, i4, Color.rgb((int) (Math.random() * 255.0d), (int) (Math.random() * 255.0d), (int) (Math.random() * 255.0d)));
            }
        }
        a(createBitmap);
    }

    public void b() {
        a(Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888));
    }

    public void b(int i, int i2) {
        a();
        GLES20.glTexImage2D(GlslFilter.f, 0, 6408, i, i2, 0, 6408, FujifilmMakernoteDirectory.H, (Buffer) null);
    }

    public int c() {
        return this.f8574a;
    }
}
