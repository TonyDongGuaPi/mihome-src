package com.google.android.exoplayer2.ui.spherical;

import android.annotation.TargetApi;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.text.TextUtils;
import com.google.android.exoplayer2.util.Log;
import com.xiaomi.smarthome.fastvideo.GlslFilter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

final class GlUtil {
    private static final String TAG = "Spherical.Utils";

    private GlUtil() {
    }

    public static void checkGlError() {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            do {
                Log.e(TAG, "glError " + GLU.gluErrorString(glGetError));
                glGetError = GLES20.glGetError();
            } while (glGetError != 0);
        }
    }

    public static int compileProgram(String[] strArr, String[] strArr2) {
        checkGlError();
        int glCreateShader = GLES20.glCreateShader(35633);
        GLES20.glShaderSource(glCreateShader, TextUtils.join("\n", strArr));
        GLES20.glCompileShader(glCreateShader);
        checkGlError();
        int glCreateShader2 = GLES20.glCreateShader(35632);
        GLES20.glShaderSource(glCreateShader2, TextUtils.join("\n", strArr2));
        GLES20.glCompileShader(glCreateShader2);
        checkGlError();
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, glCreateShader);
        GLES20.glAttachShader(glCreateProgram, glCreateShader2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            Log.e(TAG, "Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(glCreateProgram));
        }
        checkGlError();
        return glCreateProgram;
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    @TargetApi(15)
    public static int createExternalTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(iArr));
        GLES20.glBindTexture(GlslFilter.e, iArr[0]);
        GLES20.glTexParameteri(GlslFilter.e, 10241, 9729);
        GLES20.glTexParameteri(GlslFilter.e, 10240, 9729);
        GLES20.glTexParameteri(GlslFilter.e, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.e, 10243, 33071);
        checkGlError();
        return iArr[0];
    }
}
