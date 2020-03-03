package com.xiaomi.smarthome.fastvideo;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.renderscript.Matrix4f;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.mode.CommandMessage;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class RendererUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15897a = "RendererUtils";
    private static int[] b = new int[1];
    private static final float[] c = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final float[] d = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final String e = "attribute vec4 a_position;\nattribute vec2 a_texcoord;\nuniform mat4 u_model_view; \nvarying vec2 v_texcoord;\nvoid main() {\n  gl_Position = u_model_view*a_position;\n  v_texcoord = a_texcoord;\n}\n";
    private static final String f = "precision mediump float;\nuniform sampler2D tex_sampler;\nuniform float alpha;\nvarying vec2 v_texcoord;\nvoid main() {\nvec4 color = texture2D(tex_sampler, v_texcoord);\ngl_FragColor = vec4(color.rgb, color.a * alpha);\n}\n";
    private static final int g = 4;
    private static final float h = 0.017453292f;

    public static class FilterContext {

        /* renamed from: a  reason: collision with root package name */
        public int f15898a;
        public int b;
        public int c;
        public int d;
        public FloatBuffer e;
        public FloatBuffer f;
    }

    public static class RenderContext {

        /* renamed from: a  reason: collision with root package name */
        float[] f15899a = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public FloatBuffer g;
        /* access modifiers changed from: private */
        public FloatBuffer h;
        /* access modifiers changed from: private */
        public float i = 1.0f;
        /* access modifiers changed from: private */
        public int j;
    }

    public static int a() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(iArr.length, iArr, 0);
        a("glGenTextures");
        return iArr[0];
    }

    public static int a(Bitmap bitmap) {
        int a2 = a();
        GLES20.glBindTexture(GlslFilter.f, a2);
        GLUtils.texImage2D(GlslFilter.f, 0, GLUtils.getInternalFormat(bitmap), bitmap, GLUtils.getType(bitmap), 0);
        a("texImage2D");
        GLES20.glTexParameteri(GlslFilter.f, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.f, 10243, 33071);
        a("texImage2D");
        return a2;
    }

    public static int a(int i, Bitmap bitmap) {
        if (i < 0) {
            i = a();
        }
        GLES20.glBindTexture(GlslFilter.f, i);
        GLUtils.texImage2D(GlslFilter.f, 0, GLUtils.getInternalFormat(bitmap), bitmap, GLUtils.getType(bitmap), 0);
        GLES20.glTexParameteri(GlslFilter.f, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.f, 10243, 33071);
        a("texImage2D");
        return i;
    }

    public static Bitmap a(int i, int i2, int i3) {
        if (i < 0) {
            return null;
        }
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        a("glGenFramebuffers");
        GLES20.glBindFramebuffer(36160, iArr[0]);
        a("glBindFramebuffer");
        GLES20.glFramebufferTexture2D(36160, 36064, GlslFilter.f, i, 0);
        a("glFramebufferTexture2D");
        ByteBuffer allocate = ByteBuffer.allocate(i2 * i3 * 4);
        GLES20.glReadPixels(0, 0, i2, i3, 6408, FujifilmMakernoteDirectory.H, allocate);
        a("glReadPixels");
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(allocate);
        GLES20.glBindFramebuffer(36160, 0);
        a("glBindFramebuffer");
        GLES20.glDeleteFramebuffers(1, iArr, 0);
        a("glDeleteFramebuffer");
        return createBitmap;
    }

    public static void a(int i) {
        int[] iArr = {i};
        GLES20.glDeleteTextures(iArr.length, iArr, 0);
        a("glDeleteTextures");
    }

    private static float[] a(int i, int i2, int i3, int i4) {
        float f2 = (((float) i3) / ((float) i4)) / (((float) i) / ((float) i2));
        float[] fArr = new float[8];
        System.arraycopy(d, 0, fArr, 0, fArr.length);
        if (f2 > 1.0f) {
            fArr[0] = fArr[0] / f2;
            fArr[2] = fArr[2] / f2;
            fArr[4] = fArr[4] / f2;
            fArr[6] = fArr[6] / f2;
        } else {
            fArr[1] = fArr[1] * f2;
            fArr[3] = fArr[3] * f2;
            fArr[5] = fArr[5] * f2;
            fArr[7] = fArr[7] * f2;
        }
        return fArr;
    }

    public static void a(RenderContext renderContext, float[] fArr) {
        renderContext.f15899a = fArr;
    }

    public static void a(RenderContext renderContext, int i, int i2, int i3, int i4) {
        FloatBuffer unused = renderContext.h = a(a(i, i2, i3, i4));
    }

    public static void a(RenderContext renderContext, int i, int i2, int i3, int i4, float f2, float f3, float f4) {
        Matrix4f matrix4f = new Matrix4f();
        float f5 = (float) i;
        float f6 = (float) i2;
        float f7 = f5 / f6;
        float f8 = ((float) i3) / ((float) i4);
        float f9 = f8 / f7;
        if (f9 > 1.0f) {
            matrix4f.scale((f7 / f8) * f4, f4, 0.0f);
            float f10 = f6 * f4;
            matrix4f.translate((-f2) / f10, f3 / f10, 0.0f);
        } else {
            matrix4f.scale(f4, f9 * f4, 0.0f);
            float f11 = f5 * f4;
            matrix4f.translate((-f2) / f11, f3 / f11, 0.0f);
        }
        renderContext.f15899a = matrix4f.getArray();
    }

    public static void a(RenderContext renderContext, int i) {
        float unused = renderContext.i = ((float) i) / 255.0f;
    }

    public static void a(RenderContext renderContext, int i, int i2, int i3, int i4, float f2) {
        double d2 = (double) ((-f2) * h);
        float cos = (float) Math.cos(d2);
        float sin = (float) Math.sin(d2);
        float f3 = (float) i;
        float f4 = cos * f3;
        float f5 = f3 * sin;
        float f6 = (float) i2;
        float f7 = cos * f6;
        float f8 = sin * f6;
        float[] fArr = new float[8];
        fArr[0] = (-f4) + f8;
        fArr[1] = (-f5) - f7;
        fArr[2] = f4 + f8;
        fArr[3] = f5 - f7;
        fArr[4] = -fArr[2];
        fArr[5] = -fArr[3];
        fArr[6] = -fArr[0];
        fArr[7] = -fArr[1];
        float f9 = (float) i3;
        float f10 = (float) i4;
        float min = Math.min(f9 / Math.max(Math.abs(fArr[0]), Math.abs(fArr[2])), f10 / Math.max(Math.abs(fArr[1]), Math.abs(fArr[3])));
        for (int i5 = 0; i5 < 8; i5 += 2) {
            fArr[i5] = fArr[i5] * (min / f9);
            int i6 = i5 + 1;
            fArr[i6] = fArr[i6] * (min / f10);
        }
        FloatBuffer unused = renderContext.h = a(fArr);
    }

    public static void a(RenderContext renderContext, int i, int i2, int i3, int i4, float f2, float f3) {
        float f4 = f2;
        float f5 = f3;
        float[] a2 = a(i, i2, i3, i4);
        int i5 = ((int) f4) / 180;
        if (i5 % 2 != 0) {
            a2[0] = -a2[0];
            a2[4] = a2[0];
            a2[2] = -a2[2];
            a2[6] = a2[2];
        }
        int i6 = ((int) f5) / 180;
        if (i6 % 2 != 0) {
            a2[1] = -a2[1];
            a2[3] = a2[1];
            a2[5] = -a2[5];
            a2[7] = a2[5];
        }
        float[] fArr = new float[8];
        System.arraycopy(a2, 0, fArr, 0, fArr.length);
        if (f4 % 180.0f != 0.0f) {
            double d2 = (double) ((f4 - ((float) (i5 * 180))) * h);
            float cos = (float) Math.cos(d2);
            float sin = (float) Math.sin(d2);
            float f6 = 5.0f / ((a2[0] * sin) + 5.0f);
            fArr[0] = a2[0] * cos * f6;
            fArr[1] = a2[1] * f6;
            fArr[4] = fArr[0];
            fArr[5] = a2[5] * f6;
            float f7 = 5.0f / ((sin * a2[2]) + 5.0f);
            fArr[2] = cos * a2[2] * f7;
            fArr[3] = a2[3] * f7;
            fArr[6] = fArr[2];
            fArr[7] = a2[7] * f7;
        }
        if (f5 % 180.0f != 0.0f) {
            double d3 = (double) ((f5 - ((float) (i6 * 180))) * h);
            float cos2 = (float) Math.cos(d3);
            float sin2 = (float) Math.sin(d3);
            float f8 = 5.0f / ((a2[1] * sin2) + 5.0f);
            fArr[0] = a2[0] * f8;
            fArr[1] = a2[1] * cos2 * f8;
            fArr[2] = a2[2] * f8;
            fArr[3] = fArr[1];
            float f9 = 5.0f / ((sin2 * a2[5]) + 5.0f);
            fArr[4] = a2[4] * f9;
            fArr[5] = cos2 * a2[5] * f9;
            fArr[6] = a2[6] * f9;
            fArr[7] = fArr[5];
        }
        FloatBuffer unused = renderContext.h = a(fArr);
    }

    public static void a(RenderContext renderContext) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, renderContext.i);
        GLES20.glClear(16384);
    }

    public static void a(RenderContext renderContext, float f2, float f3, float f4) {
        GLES20.glClearColor(f2, f3, f4, renderContext.i);
        GLES20.glClear(16384);
    }

    public static void a(RenderContext renderContext, int i, int i2, int i3) {
        GLES20.glUseProgram(renderContext.b);
        if (GLES20.glGetError() != 0) {
            b();
            a("createProgram");
        }
        GLES20.glViewport(0, 0, i2, i3);
        a("glViewport");
        GLES20.glDisable(Constants.TradeCode.BUSINESS_ORDER_SETTING_VER2);
        GLES20.glVertexAttribPointer(renderContext.e, 2, FujifilmMakernoteDirectory.M, false, 0, renderContext.g);
        GLES20.glEnableVertexAttribArray(renderContext.e);
        GLES20.glVertexAttribPointer(renderContext.f, 2, FujifilmMakernoteDirectory.M, false, 0, renderContext.h);
        GLES20.glEnableVertexAttribArray(renderContext.f);
        a("vertex attribute setup");
        GLES20.glActiveTexture(33984);
        a("glActiveTexture");
        GLES20.glBindTexture(GlslFilter.f, i);
        GLES20.glTexParameteri(GlslFilter.f, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.f, 10243, 33071);
        a("glBindTexture");
        GLES20.glUniform1i(renderContext.c, 0);
        GLES20.glUniform1f(renderContext.d, renderContext.i);
        GLES20.glUniformMatrix4fv(renderContext.j, 1, false, renderContext.f15899a, 0);
        a("modelViewMatHandle");
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFinish();
    }

    private static RenderContext a(float[] fArr, float[] fArr2) {
        int a2;
        int a3 = a(35633, e);
        if (a3 == 0 || (a2 = a(35632, (String) f)) == 0) {
            return null;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, a3);
            a("glAttachShader");
            GLES20.glAttachShader(glCreateProgram, a2);
            a("glAttachShader");
            GLES20.glLinkProgram(glCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                String glGetProgramInfoLog = GLES20.glGetProgramInfoLog(glCreateProgram);
                GLES20.glDeleteProgram(glCreateProgram);
                throw new RuntimeException("Could not link program: " + glGetProgramInfoLog);
            }
        }
        RenderContext renderContext = new RenderContext();
        int unused = renderContext.c = GLES20.glGetUniformLocation(glCreateProgram, "tex_sampler");
        int unused2 = renderContext.d = GLES20.glGetUniformLocation(glCreateProgram, "alpha");
        int unused3 = renderContext.e = GLES20.glGetAttribLocation(glCreateProgram, "a_texcoord");
        int unused4 = renderContext.f = GLES20.glGetAttribLocation(glCreateProgram, "a_position");
        int unused5 = renderContext.j = GLES20.glGetUniformLocation(glCreateProgram, "u_model_view");
        FloatBuffer unused6 = renderContext.g = a(fArr2);
        FloatBuffer unused7 = renderContext.h = a(fArr);
        int unused8 = renderContext.b = glCreateProgram;
        return renderContext;
    }

    public static RenderContext b() {
        return a(d, c);
    }

    public static void b(RenderContext renderContext) {
        if (renderContext != null && renderContext.b > 0) {
            GLES20.glDeleteProgram(renderContext.b);
            int unused = renderContext.b = 0;
        }
    }

    private static int a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
                GLES20.glDeleteShader(glCreateShader);
                throw new RuntimeException("Could not compile shader " + i + ":" + glGetShaderInfoLog);
            }
        }
        return glCreateShader;
    }

    public static FloatBuffer a(float[] fArr) {
        if (fArr.length == 8) {
            FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            asFloatBuffer.put(fArr).position(0);
            return asFloatBuffer;
        }
        throw new RuntimeException("Number of vertices should be four.");
    }

    public static String b(int i) {
        switch (i) {
            case 12288:
                return "EGL_SUCCESS";
            case 12289:
                return "EGL_NOT_INITIALIZED";
            case 12290:
                return "EGL_BAD_ACCESS";
            case 12291:
                return "EGL_BAD_ALLOC";
            case CommandMessage.COMMAND_SET_ALIAS:
                return "EGL_BAD_ATTRIBUTE";
            case CommandMessage.COMMAND_GET_ALIAS:
                return "EGL_BAD_CONFIG";
            case 12294:
                return "EGL_BAD_CONTEXT";
            case 12295:
                return "EGL_BAD_CURRENT_SURFACE";
            case CommandMessage.COMMAND_GET_TAGS:
                return "EGL_BAD_DISPLAY";
            case CommandMessage.COMMAND_UNSET_TAGS:
                return "EGL_BAD_MATCH";
            case CommandMessage.COMMAND_SET_PUSH_TIME:
                return "EGL_BAD_NATIVE_PIXMAP";
            case CommandMessage.COMMAND_PAUSE_PUSH:
                return "EGL_BAD_NATIVE_WINDOW";
            case CommandMessage.COMMAND_RESUME_PUSH:
                return "EGL_BAD_PARAMETER";
            case CommandMessage.COMMAND_SET_ACCOUNTS:
                return "EGL_BAD_SURFACE";
            case CommandMessage.COMMAND_GET_ACCOUNTS:
                return "EGL_CONTEXT_LOST";
            default:
                return " " + i;
        }
    }

    public static void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            LogUtil.c(f15897a, "" + str + ": glError " + b(glGetError));
            for (StackTraceElement stackTraceElement : Thread.getAllStackTraces().get(Thread.currentThread())) {
                LogUtil.c(f15897a, "SS     " + stackTraceElement.toString());
            }
        }
    }

    public static void c() {
        GLES20.glGenFramebuffers(1, b, 0);
        a("glGenFramebuffers");
    }

    public static void d() {
        GLES20.glDeleteFramebuffers(1, b, 0);
        a("glDeleteFramebuffer");
    }

    public static int a(String str, String str2) {
        int a2;
        if (str == null) {
            str = e;
        }
        int a3 = a(35633, str);
        if (a3 == 0 || (a2 = a(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, a3);
            a("glAttachShader");
            GLES20.glAttachShader(glCreateProgram, a2);
            a("glAttachShader");
            GLES20.glLinkProgram(glCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                String glGetProgramInfoLog = GLES20.glGetProgramInfoLog(glCreateProgram);
                GLES20.glDeleteProgram(glCreateProgram);
                throw new RuntimeException("Could not link program: " + glGetProgramInfoLog);
            }
        }
        return glCreateProgram;
    }

    public static void a(FilterContext filterContext, int i, int i2, int i3, int i4) {
        FilterContext filterContext2 = filterContext;
        int i5 = i2;
        GLES20.glActiveTexture(33984);
        a("glActiveTexture");
        GLES20.glBindTexture(GlslFilter.f, i5);
        a("glBindTexture");
        GLES20.glTexImage2D(GlslFilter.f, 0, 6408, i3, i4, 0, 6408, FujifilmMakernoteDirectory.H, (Buffer) null);
        a("glTexImage2D");
        GLES20.glBindFramebuffer(36160, b[0]);
        a("glBindFramebuffer");
        GLES20.glFramebufferTexture2D(36160, 36064, GlslFilter.f, i5, 0);
        a("glFramebufferTexture2D");
        GLES20.glViewport(0, 0, i3, i4);
        a("glViewport");
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        GLES20.glUseProgram(filterContext2.f15898a);
        if (GLES20.glGetError() != 0) {
            a("createProgram");
        }
        GLES20.glVertexAttribPointer(filterContext2.c, 2, FujifilmMakernoteDirectory.M, false, 0, filterContext2.e);
        GLES20.glEnableVertexAttribArray(filterContext2.c);
        GLES20.glVertexAttribPointer(filterContext2.d, 2, FujifilmMakernoteDirectory.M, false, 0, filterContext2.f);
        GLES20.glEnableVertexAttribArray(filterContext2.d);
        a("vertex attribute setup");
        GLES20.glUniform1i(filterContext2.b, 0);
        a("glUniform1i");
        GLES20.glActiveTexture(33984);
        a("glActiveTexture");
        int i6 = i;
        GLES20.glBindTexture(GlslFilter.f, i);
        a("glBindTexture");
        GLES20.glTexParameteri(GlslFilter.f, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.f, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.f, 10243, 33071);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFinish();
        GLES20.glBindFramebuffer(36160, 0);
        a("glBindFramebuffer");
        c(filterContext2.f15898a);
    }

    public static void c(int i) {
        GLES20.glDeleteProgram(i);
    }
}
