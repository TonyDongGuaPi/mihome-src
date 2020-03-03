package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.io.IOException;
import java.io.InputStream;

public class Param {

    /* renamed from: a  reason: collision with root package name */
    public int f15885a;
    public String b;

    public void a() {
    }

    public Param(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.f15885a = GLES20.glGetUniformLocation(i, this.b);
    }

    public String toString() {
        return this.b;
    }

    public static class FloatParam extends Param {
        float c;

        public FloatParam(String str, float f) {
            super(str);
            this.c = f;
        }

        public void a(float f) {
            this.c = f;
        }

        public float b() {
            return this.c;
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a >= 0) {
                GLES20.glUniform1f(this.f15885a, this.c);
            }
        }

        public String toString() {
            return this.b + "=" + this.c;
        }
    }

    public static class IntParam extends Param {
        int c;

        public String toString() {
            return this.b + "=" + this.c;
        }

        public IntParam(String str, int i) {
            super(str);
            this.c = i;
        }

        public void b(int i) {
            this.c = i;
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a >= 0) {
                GLES20.glUniform1i(this.f15885a, this.c);
            }
        }
    }

    public static class FloatsParam extends Param {
        float[] c;

        public FloatsParam(String str, float[] fArr) {
            super(str);
            this.c = fArr;
        }

        public String toString() {
            return this.b + "=" + this.c.toString();
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a >= 0) {
                int length = this.c.length;
                if (length == 9) {
                    GLES20.glUniformMatrix3fv(this.f15885a, 1, false, this.c, 0);
                } else if (length != 16) {
                    switch (length) {
                        case 1:
                            GLES20.glUniform1f(this.f15885a, this.c[0]);
                            return;
                        case 2:
                            GLES20.glUniform2fv(this.f15885a, 1, this.c, 0);
                            return;
                        case 3:
                            GLES20.glUniform3fv(this.f15885a, 1, this.c, 0);
                            return;
                        case 4:
                            GLES20.glUniform4fv(this.f15885a, 1, this.c, 0);
                            return;
                        default:
                            return;
                    }
                } else {
                    GLES20.glUniformMatrix4fv(this.f15885a, 1, false, this.c, 0);
                }
            }
        }
    }

    public static class RectParam extends Param {
        float[] c;

        public RectParam(String str, float[] fArr) {
            super(str);
            this.c = fArr;
        }

        public String toString() {
            return this.b + "=" + this.c.toString();
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a >= 0) {
                GLES20.glUniform4fv(this.f15885a, this.c.length / 4, this.c, 0);
            }
        }
    }

    public static class VarFloatParam extends FloatParam {
        float d;
        float e;

        public VarFloatParam(String str, float f, float f2, float f3) {
            super(str, f);
            this.d = f2;
            this.e = f3;
        }

        public void a(float f) {
            this.c = f;
        }

        public float c() {
            return this.c;
        }

        public String d() {
            return this.b;
        }

        public void b(int i) {
            this.c = this.d + ((((float) i) * (this.e - this.d)) / 100.0f);
        }

        public int e() {
            return (int) (((this.c - this.d) * 100.0f) / (this.e - this.d));
        }
    }

    public static class TextureParam extends Param {
        Bitmap e;
        int f;
        int[] g = {0};

        public TextureParam(String str, Bitmap bitmap, int i) {
            super(str);
            this.e = bitmap;
            this.f = i;
        }

        public void a() {
            Param.super.a();
            GLES20.glActiveTexture(this.f);
            GLES20.glDeleteTextures(1, this.g, 0);
            this.g[0] = 0;
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a != 0 && this.e != null) {
                GLES20.glActiveTexture(this.f);
                int i2 = 1;
                GLES20.glGenTextures(1, this.g, 0);
                GLES20.glBindTexture(GlslFilter.f, this.g[0]);
                GLES20.glTexParameterf(GlslFilter.f, 10240, 9728.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10241, 9728.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10242, 33071.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10243, 33071.0f);
                GLUtils.texImage2D(GlslFilter.f, 0, this.e, 0);
                GlslFilter.c("texImage2D");
                switch (this.f) {
                    case 33985:
                        break;
                    case 33986:
                        i2 = 2;
                        break;
                    case 33987:
                        i2 = 3;
                        break;
                    case 33988:
                        i2 = 4;
                        break;
                    case 33989:
                        i2 = 5;
                        break;
                    case 33990:
                        i2 = 6;
                        break;
                    case 33991:
                        i2 = 7;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                GLES20.glUniform1i(this.f15885a, i2);
                GlslFilter.c("set texture:" + i2);
            }
        }
    }

    public static class TextureValueParam extends Param {
        int c;
        int d;

        public TextureValueParam(String str, int i, int i2) {
            super(str);
            this.c = i;
            this.d = i2;
        }

        public void a() {
            Param.super.a();
            GLES20.glActiveTexture(this.d);
        }

        public void a(int i) {
            Param.super.a(i);
            if (this.f15885a != 0 && this.c != 0) {
                GLES20.glActiveTexture(this.d);
                GLES20.glBindTexture(GlslFilter.f, this.c);
                GLES20.glTexParameterf(GlslFilter.f, 10240, 9728.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10241, 9728.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10242, 33071.0f);
                GLES20.glTexParameterf(GlslFilter.f, 10243, 33071.0f);
                GlslFilter.c("texImage2D");
                int i2 = 0;
                switch (this.d) {
                    case 33985:
                        i2 = 1;
                        break;
                    case 33986:
                        i2 = 2;
                        break;
                    case 33987:
                        i2 = 3;
                        break;
                    case 33988:
                        i2 = 4;
                        break;
                    case 33989:
                        i2 = 5;
                        break;
                    case 33990:
                        i2 = 6;
                        break;
                    case 33991:
                        i2 = 7;
                        break;
                }
                GLES20.glUniform1i(this.f15885a, i2);
                GlslFilter.c("set texture:" + i2);
            }
        }
    }

    public static class TextureFileParam extends TextureParam {
        Context c;
        String d;

        public TextureFileParam(String str, String str2, Context context, int i) {
            super(str, (Bitmap) null, i);
            this.c = context;
            this.d = str2;
        }

        public void a(int i) {
            if (this.e == null) {
                try {
                    InputStream open = this.c.getAssets().open(this.d);
                    this.e = BitmapFactory.decodeStream(open);
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.a(i);
        }
    }

    static class VarFloatParamLens extends VarFloatParam {
        public VarFloatParamLens(String str, float f, float f2, float f3) {
            super(str, f, f2, f3);
        }

        public void a(int i) {
            this.f15885a = GLES20.glGetUniformLocation(i, this.b);
            if (this.f15885a >= 0) {
                float c = c();
                if (c == 0.0f) {
                    c = 1.0E-5f;
                }
                GLES20.glUniform1f(this.f15885a, c);
            }
        }
    }
}
