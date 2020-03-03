package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.opengl.GLES20;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.xiaomi.smarthome.fastvideo.Param;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class GlslFilter extends Filter {
    private static final int b = 4;
    private static final float[] c = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final float[] d = {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    public static final int e = 36197;
    public static final int f = 3553;
    private static final float[] o = {-1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f};
    private static final float[] p = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final String q = "attribute vec4 a_position;\nattribute vec2 a_texcoord;\nuniform mat4 u_texture_mat; \nuniform mat4 u_model_view; \nvarying vec2 textureCoordinate;\nvoid main() {\n  gl_Position = u_model_view*a_position;\n  vec4 tmp = u_texture_mat*vec4(a_texcoord.x,a_texcoord.y,0.0,1.0);\n  textureCoordinate = tmp.xy;\n}\n";
    private static final String r = "precision mediump float;\nuniform sampler2D inputImageTexture;\nvarying vec2 textureCoordinate;\nvoid main() {\n  gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}\n";
    private int[] A = {0};

    /* renamed from: a  reason: collision with root package name */
    private final String f15882a = "camera_glsl";
    int g = f;
    protected int h;
    final float[] i = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    final float[] j = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    boolean k = false;
    GlslFilter l;
    Photo m;
    public ArrayList<Param> n = new ArrayList<>();
    private final String s = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES inputImageTexture;\nvarying vec2 textureCoordinate;\nvoid main() {\n  gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}\n";
    private int t;
    private int u;
    private int v;
    private FloatBuffer w;
    private FloatBuffer x;
    private int y;
    private int z;

    /* access modifiers changed from: protected */
    public void b() {
    }

    public String g() {
        return q;
    }

    public Filter a(Param param) {
        if (param == null) {
            return this;
        }
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            if (param.b.equals(this.n.get(i2).b)) {
                this.n.remove(i2);
                this.n.add(param);
                return this;
            }
        }
        this.n.add(param);
        return this;
    }

    public Param a(String str) {
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            if (str.equals(this.n.get(i2).b)) {
                return this.n.get(i2);
            }
        }
        return null;
    }

    public float b(String str) {
        Param a2 = a(str);
        if (a2 == null || !(a2 instanceof Param.VarFloatParam)) {
            return 0.0f;
        }
        return ((Param.VarFloatParam) a2).c;
    }

    public ArrayList<Param> f() {
        return this.n;
    }

    public GlslFilter(Context context) {
    }

    public String a() {
        return this.g == 3553 ? r : "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES inputImageTexture;\nvarying vec2 textureCoordinate;\nvoid main() {\n  gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}\n";
    }

    public void a(GlslFilter glslFilter) {
        this.l = glslFilter;
    }

    public final void a(int i2) {
        this.g = i2;
    }

    public final void d() {
        if (!this.k) {
            this.k = true;
            int a2 = a(35633, g());
            c("glCreateProgram");
            if (a2 != 0) {
                int a3 = a(35632, a());
                if (a3 != 0) {
                    this.h = GLES20.glCreateProgram();
                    if (this.h != 0) {
                        GLES20.glAttachShader(this.h, a2);
                        c("glAttachShader");
                        GLES20.glAttachShader(this.h, a3);
                        c("glAttachShader");
                        GLES20.glLinkProgram(this.h);
                        int[] iArr = new int[1];
                        GLES20.glGetProgramiv(this.h, 35714, iArr, 0);
                        if (iArr[0] == 1) {
                            this.t = GLES20.glGetUniformLocation(this.h, "inputImageTexture");
                            this.u = GLES20.glGetAttribLocation(this.h, "a_texcoord");
                            this.v = GLES20.glGetAttribLocation(this.h, "a_position");
                            this.y = GLES20.glGetUniformLocation(this.h, "u_texture_mat");
                            this.z = GLES20.glGetUniformLocation(this.h, "u_model_view");
                            if (this.g == 3553) {
                                this.w = c(c);
                            } else {
                                this.w = c(d);
                            }
                            this.x = c(o);
                            b();
                            if (this.l != null) {
                                this.l.d();
                                return;
                            }
                            return;
                        }
                        String glGetProgramInfoLog = GLES20.glGetProgramInfoLog(this.h);
                        GLES20.glDeleteProgram(this.h);
                        this.h = 0;
                        throw new RuntimeException("Could not link program: " + glGetProgramInfoLog);
                    }
                    throw new RuntimeException("Could not create program");
                }
                throw new RuntimeException("Could not load fragment shader: " + a());
            }
            throw new RuntimeException("Could not load vertex shader: " + g());
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.get(i2).a(this.h);
        }
    }

    /* access modifiers changed from: protected */
    public void h() {
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.get(i2).a();
        }
    }

    public final void e() {
        if (this.k) {
            this.k = false;
            if (this.m != null) {
                this.m.e();
                this.m = null;
            }
            if (this.l != null) {
                this.l.e();
            }
            h();
            if (this.h > 0) {
                GLES20.glDeleteProgram(this.h);
                this.h = 0;
            }
            if (this.A[0] > 0) {
                GLES20.glDeleteFramebuffers(1, this.A, 0);
                this.A[0] = 0;
            }
        }
    }

    public final void a(float[] fArr) {
        for (int i2 = 0; i2 < fArr.length; i2++) {
            this.j[i2] = fArr[i2];
        }
    }

    public final void i() {
        this.j[0] = -this.j[0];
        this.j[1] = -this.j[1];
        this.j[2] = -this.j[2];
        this.j[3] = -this.j[3];
    }

    public final void j() {
        this.j[4] = -this.j[4];
        this.j[5] = -this.j[5];
        this.j[6] = -this.j[6];
        this.j[7] = -this.j[7];
    }

    public final void b(int i2) {
        a(p);
        double d2 = (double) i2;
        Double.isNaN(d2);
        double d3 = (d2 * 3.1415926d) / 180.0d;
        float cos = (float) Math.cos(d3);
        float sin = (float) Math.sin(d3);
        this.j[0] = cos;
        this.j[1] = -sin;
        this.j[4] = sin;
        this.j[5] = cos;
    }

    public final void b(float[] fArr) {
        for (int i2 = 0; i2 < fArr.length; i2++) {
            this.i[i2] = fArr[i2];
        }
    }

    public void a(Photo photo, Photo photo2) {
        if (this.l == null) {
            b(photo, photo2);
            return;
        }
        if (this.m == null) {
            Photo photo3 = photo == null ? photo2 : photo;
            if (photo3 != null) {
                this.m = Photo.a(photo3.b(), photo3.c());
            }
        }
        b(photo, this.m);
        this.l.b(this.m, photo2);
    }

    private void b(Photo photo, Photo photo2) {
        if (this.h != 0) {
            if (photo2 == null) {
                GLES20.glBindFramebuffer(36160, 0);
            } else {
                if (this.A[0] == 0) {
                    GLES20.glGenFramebuffers(1, this.A, 0);
                }
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(f, photo2.a());
                GLES20.glTexParameteri(f, 10240, 9729);
                GLES20.glTexParameteri(f, 10241, 9729);
                GLES20.glTexParameteri(f, 10242, 33071);
                GLES20.glTexParameteri(f, 10243, 33071);
                GLES20.glTexImage2D(f, 0, 6408, photo2.b(), photo2.c(), 0, 6408, FujifilmMakernoteDirectory.H, (Buffer) null);
                GLES20.glBindFramebuffer(36160, this.A[0]);
                GLES20.glFramebufferTexture2D(36160, 36064, f, photo2.a(), 0);
                c("glBindFramebuffer");
            }
            GLES20.glUseProgram(this.h);
            c("glUseProgram");
            GLES20.glViewport(0, 0, photo2.b(), photo2.c());
            c("glViewport");
            GLES20.glDisable(Constants.TradeCode.BUSINESS_ORDER_SETTING_VER2);
            GLES20.glVertexAttribPointer(this.u, 2, FujifilmMakernoteDirectory.M, false, 0, this.w);
            GLES20.glEnableVertexAttribArray(this.u);
            GLES20.glVertexAttribPointer(this.v, 3, FujifilmMakernoteDirectory.M, false, 0, this.x);
            GLES20.glEnableVertexAttribArray(this.v);
            c("vertex attribute setup");
            if (photo != null && this.t >= 0) {
                GLES20.glActiveTexture(33984);
                c("glActiveTexture");
                GLES20.glBindTexture(this.g, photo.a());
                c("glBindTexture");
                GLES20.glTexParameteri(this.g, 10240, 9729);
                GLES20.glTexParameteri(this.g, 10241, 9729);
                GLES20.glTexParameteri(this.g, 10242, 33071);
                GLES20.glTexParameteri(this.g, 10243, 33071);
                GLES20.glUniform1i(this.t, 0);
                c("texSamplerHandle");
            }
            GLES20.glUniformMatrix4fv(this.y, 1, false, this.i, 0);
            c("texCoordMatHandle");
            GLES20.glUniformMatrix4fv(this.z, 1, false, this.j, 0);
            c("modelViewMatHandle");
            c();
            GLES20.glDrawArrays(6, 0, 4);
            c("glDrawArrays");
            GLES20.glFinish();
            if (photo2 != null) {
                GLES20.glFramebufferTexture2D(36160, 36064, f, 0, 0);
                GLES20.glBindFramebuffer(36160, 0);
            }
            c("after process");
        }
    }

    private static int a(int i2, String str) {
        int glCreateShader = GLES20.glCreateShader(i2);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
                GLES20.glDeleteShader(glCreateShader);
                throw new RuntimeException("Could not compile shader " + i2 + ":" + glGetShaderInfoLog);
            }
        }
        return glCreateShader;
    }

    private static FloatBuffer c(float[] fArr) {
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        asFloatBuffer.put(fArr).position(0);
        return asFloatBuffer;
    }

    public static void c(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new RuntimeException(a(str, glGetError));
        }
    }

    public static String a(String str, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" - ");
        if (i2 == 0) {
            stringBuffer.append("No errors.");
        } else if (i2 == 1286) {
            stringBuffer.append("OpenGL invalid framebuffer operation.");
        } else if (i2 == 36057) {
            stringBuffer.append("OpenGL framebuffer attached images must have same dimensions.");
        } else if (i2 != 36061) {
            switch (i2) {
                case 1280:
                    stringBuffer.append("Invalid enum");
                    break;
                case OlympusCameraSettingsMakernoteDirectory.C:
                    stringBuffer.append("Invalid value");
                    break;
                case OlympusCameraSettingsMakernoteDirectory.D:
                    stringBuffer.append("Invalid operation");
                    break;
                default:
                    switch (i2) {
                        case 36053:
                            stringBuffer.append("Framebuffer complete.");
                            break;
                        case 36054:
                            stringBuffer.append("OpenGL framebuffer attached images must have same format.");
                            break;
                        case 36055:
                            stringBuffer.append("OpenGL framebuffer missing attachment.");
                            break;
                        default:
                            stringBuffer.append("OpenGL error: " + i2);
                            break;
                    }
            }
        } else {
            stringBuffer.append("OpenGL framebuffer format not supported. ");
        }
        return stringBuffer.toString();
    }
}
