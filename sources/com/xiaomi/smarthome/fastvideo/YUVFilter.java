package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.opengl.GLES20;

public class YUVFilter extends GlslFilter {

    /* renamed from: a  reason: collision with root package name */
    int f15923a;
    int b;
    int c;
    int[] d;
    private final String o = "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nvoid main() {\n  float y = texture2D(y_tex, textureCoordinate).r;\n  float u = texture2D(u_tex, textureCoordinate).r - 0.5;\n  float v = texture2D(v_tex, textureCoordinate).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1.0);\n}\n";

    public String a() {
        return "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nvoid main() {\n  float y = texture2D(y_tex, textureCoordinate).r;\n  float u = texture2D(u_tex, textureCoordinate).r - 0.5;\n  float v = texture2D(v_tex, textureCoordinate).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1.0);\n}\n";
    }

    public YUVFilter(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void b() {
        super.b();
        this.f15923a = GLES20.glGetUniformLocation(this.h, "y_tex");
        this.b = GLES20.glGetUniformLocation(this.h, "u_tex");
        this.c = GLES20.glGetUniformLocation(this.h, "v_tex");
    }

    /* access modifiers changed from: protected */
    public void c() {
        super.c();
        c("setYuvTextures");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(GlslFilter.f, this.d[0]);
        GLES20.glTexParameterf(GlslFilter.f, 10241, 9729.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10240, 9729.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10242, 33071.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10243, 33071.0f);
        GLES20.glUniform1i(this.f15923a, 0);
        c("glBindTexture y");
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(GlslFilter.f, this.d[1]);
        GLES20.glTexParameterf(GlslFilter.f, 10240, 9729.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10242, 33071.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10243, 33071.0f);
        GLES20.glUniform1i(this.b, 1);
        c("glBindTexture u");
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(GlslFilter.f, this.d[2]);
        GLES20.glTexParameterf(GlslFilter.f, 10240, 9729.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10242, 33071.0f);
        GLES20.glTexParameterf(GlslFilter.f, 10243, 33071.0f);
        GLES20.glUniform1i(this.c, 2);
        c("glBindTexture v");
    }

    public void a(int[] iArr) {
        this.d = iArr;
    }
}
