package com.megvii.livenessdetection.obf;

import android.graphics.Rect;
import android.os.Build;
import com.megvii.livenessdetection.bean.FaceInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private float[] f6686a = new float[5];
    private int b = 0;
    private boolean c = false;

    public static String a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(bArr);
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 2) {
                    sb.append(hexString);
                } else {
                    sb.append("0");
                    sb.append(hexString);
                }
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", Build.VERSION.SDK_INT);
            jSONObject.put("release", Build.VERSION.RELEASE);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("brand", Build.BRAND);
            jSONObject.put("manufacturer", Build.MANUFACTURER);
            jSONObject.put("arch", Build.CPU_ABI);
            jSONObject.put("fingerprint", Build.FINGERPRINT);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] a(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        try {
            fileInputStream = new FileInputStream(str);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byteArrayOutputStream.close();
                        a((InputStream) fileInputStream);
                        return byteArrayOutputStream.toByteArray();
                    }
                } catch (IOException unused) {
                    a((InputStream) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    a((InputStream) fileInputStream);
                    throw th;
                }
            }
        } catch (IOException unused2) {
            fileInputStream = null;
            a((InputStream) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            a((InputStream) fileInputStream);
            throw th;
        }
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        try {
            System.load(str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            d.b("load dynamic library failed, please check library path");
            return false;
        }
    }

    public static String a(Rect rect) {
        return "245 " + rect.left + "," + rect.top + "," + rect.right + "," + rect.bottom;
    }

    public synchronized void a(boolean z) {
        this.c = true;
        for (int i = 0; i < 5; i++) {
            this.f6686a[i] = 0.0f;
        }
        this.b = 0;
    }

    public void a(FaceInfo faceInfo) {
        if (faceInfo == null) {
            if (this.c) {
                float[] fArr = this.f6686a;
                int i = this.b;
                this.b = i + 1;
                fArr[i % 5] = 0.0f;
            }
        } else if (this.c) {
            float[] fArr2 = this.f6686a;
            int i2 = this.b;
            this.b = i2 + 1;
            fArr2[i2 % 5] = faceInfo.faceQuality;
            float f = 100.0f;
            for (float f2 : this.f6686a) {
                if (f2 < f) {
                    f = f2;
                }
            }
            faceInfo.smoothQuality = f;
        } else {
            faceInfo.smoothQuality = faceInfo.faceQuality;
        }
    }
}
