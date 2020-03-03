package com.projectseptember.RNGL;

import android.opengl.GLES20;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GLShader {

    /* renamed from: a  reason: collision with root package name */
    private final String f8572a;
    private final String b;
    private final String c;
    private Map<String, Integer> d;
    private List<String> e;
    private int f;
    private int[] g;
    private int h;
    private Map<String, Integer> i;
    private Map<String, Integer> j;
    private Integer k;
    private RNGLContext l;
    private GLShaderCompilationFailed m;

    public GLShader(GLShaderData gLShaderData, Integer num, RNGLContext rNGLContext) {
        this.f8572a = gLShaderData.f8573a;
        this.b = gLShaderData.b;
        this.c = gLShaderData.c;
        this.k = num;
        this.l = rNGLContext;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (this.g != null) {
            GLES20.glDeleteProgram(this.f);
            GLES20.glDeleteBuffers(1, this.g, 0);
        }
    }

    public void a(String str) {
        throw new GLShaderCompilationFailed(this.f8572a, str);
    }

    public void a() {
        h();
        if (!GLES20.glIsProgram(this.f)) {
            a("not a program");
        }
        GLES20.glUseProgram(this.f);
        GLES20.glBindBuffer(34962, this.g[0]);
        GLES20.glEnableVertexAttribArray(this.h);
        GLES20.glVertexAttribPointer(this.h, 2, FujifilmMakernoteDirectory.M, false, 0, 0);
    }

    public void b() {
        GLES20.glValidateProgram(this.f);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(this.f, 35715, iArr, 0);
        if (iArr[0] == 0) {
            GLES20.glGetProgramInfoLog(this.f);
            a(GLES20.glGetProgramInfoLog(this.f));
        }
    }

    public void a(String str, Integer num) {
        GLES20.glUniform1i(this.i.get(str).intValue(), num.intValue());
    }

    public void a(String str, Float f2) {
        GLES20.glUniform1f(this.i.get(str).intValue(), f2.floatValue());
    }

    public void a(String str, FloatBuffer floatBuffer, int i2) {
        switch (i2) {
            case 35664:
                GLES20.glUniform2fv(this.i.get(str).intValue(), 1, floatBuffer);
                return;
            case 35665:
                GLES20.glUniform3fv(this.i.get(str).intValue(), 1, floatBuffer);
                return;
            case 35666:
                GLES20.glUniform4fv(this.i.get(str).intValue(), 1, floatBuffer);
                return;
            default:
                switch (i2) {
                    case 35674:
                        GLES20.glUniformMatrix2fv(this.i.get(str).intValue(), 1, false, floatBuffer);
                        return;
                    case 35675:
                        GLES20.glUniformMatrix3fv(this.i.get(str).intValue(), 1, false, floatBuffer);
                        return;
                    case 35676:
                        GLES20.glUniformMatrix4fv(this.i.get(str).intValue(), 1, false, floatBuffer);
                        return;
                    default:
                        a("Unsupported case: uniform '" + str + "' type: " + i2);
                        return;
                }
        }
    }

    public void a(String str, IntBuffer intBuffer, int i2) {
        switch (i2) {
            case 35667:
            case 35671:
                GLES20.glUniform2iv(this.i.get(str).intValue(), 1, intBuffer);
                return;
            case 35668:
            case 35672:
                GLES20.glUniform3iv(this.i.get(str).intValue(), 1, intBuffer);
                return;
            case 35669:
            case 35673:
                GLES20.glUniform4iv(this.i.get(str).intValue(), 1, intBuffer);
                return;
            default:
                a("Unsupported case: uniform '" + str + "' type: " + i2);
                return;
        }
    }

    public String c() {
        return this.f8572a;
    }

    public Map<String, Integer> d() {
        return this.d;
    }

    public Map<String, Integer> e() {
        return this.j;
    }

    public List<String> f() {
        return this.e;
    }

    private int a(String str, int i2) {
        int glCreateShader = GLES20.glCreateShader(i2);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        a(GLES20.glGetShaderInfoLog(glCreateShader));
        return -1;
    }

    private void i() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        GLES20.glGetProgramiv(this.f, 35718, iArr, 0);
        int i2 = 0;
        while (i2 < iArr[0]) {
            int i3 = i2;
            String glGetActiveUniform = GLES20.glGetActiveUniform(this.f, i2, iArr3, 0, iArr2, 0);
            int i4 = iArr3[0];
            int i5 = iArr2[0];
            if (glGetActiveUniform.contains("[0]")) {
                glGetActiveUniform = glGetActiveUniform.substring(0, glGetActiveUniform.length() - 3);
            }
            arrayList.add(glGetActiveUniform);
            hashMap.put(glGetActiveUniform, Integer.valueOf(i5));
            hashMap3.put(glGetActiveUniform, Integer.valueOf(i4));
            if (i4 == 1) {
                hashMap2.put(glGetActiveUniform, Integer.valueOf(GLES20.glGetUniformLocation(this.f, glGetActiveUniform)));
            } else {
                for (int i6 = 0; i6 < i4; i6++) {
                    String str = glGetActiveUniform + Operators.ARRAY_START_STR + i6 + Operators.ARRAY_END_STR;
                    hashMap2.put(str, Integer.valueOf(GLES20.glGetUniformLocation(this.f, str)));
                    hashMap.put(str, Integer.valueOf(i5));
                    hashMap3.put(str, 1);
                }
            }
            i2 = i3 + 1;
        }
        this.d = hashMap;
        this.i = hashMap2;
        this.j = hashMap3;
        this.e = arrayList;
    }

    private void j() throws GLShaderCompilationFailed {
        int a2;
        int a3 = a(this.b, 35633);
        if (a3 != -1 && (a2 = a(this.c, 35632)) != -1) {
            this.f = GLES20.glCreateProgram();
            GLES20.glAttachShader(this.f, a3);
            GLES20.glAttachShader(this.f, a2);
            GLES20.glLinkProgram(this.f);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(this.f, 35714, iArr, 0);
            if (iArr[0] == 0) {
                a(GLES20.glGetProgramInfoLog(this.f));
            }
            GLES20.glUseProgram(this.f);
            b();
            i();
            this.h = GLES20.glGetAttribLocation(this.f, "position");
            this.g = new int[1];
            GLES20.glGenBuffers(1, this.g, 0);
            GLES20.glBindBuffer(34962, this.g[0]);
            float[] fArr = {-1.0f, -1.0f, -1.0f, 4.0f, 4.0f, -1.0f};
            FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            asFloatBuffer.put(fArr).position(0);
            GLES20.glBufferData(34962, fArr.length * 4, asFloatBuffer, 35044);
        }
    }

    public boolean g() {
        return (this.g == null || this.i == null) ? false : true;
    }

    public boolean h() {
        if (!g()) {
            if (this.m == null) {
                try {
                    j();
                    this.l.shaderSucceedToCompile(this.k, this.d);
                } catch (GLShaderCompilationFailed e2) {
                    this.m = e2;
                    this.l.shaderFailedToCompile(this.k, e2);
                    throw e2;
                }
            } else {
                throw this.m;
            }
        }
        return g();
    }
}
