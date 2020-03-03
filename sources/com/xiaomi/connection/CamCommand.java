package com.xiaomi.connection;

import com.debug.SDKLog;
import com.mijia.debug.Tag;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class CamCommand {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10088a = "CamCommand";

    public static byte[] a() {
        return CamProtocolUtils.a(10000, 0, (byte[]) null);
    }

    public static byte[] a(String str, boolean z, boolean z2) {
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[22];
        if (str.length() < 4) {
            str = str + "1234";
        }
        ByteOperator.a(bArr2, 0, bArr, 0, 15);
        ByteOperator.a(bArr2, 16, str.getBytes(), 0, 3);
        bArr2[20] = 0;
        bArr2[21] = 0;
        return CamProtocolUtils.a(10002, bArr2.length, bArr2);
    }

    public static byte[] a(long j) {
        return CamProtocolUtils.a(10092, 4, ByteOperator.a((int) j));
    }

    public static byte[] a(int i, boolean z) {
        SDKLog.b(Tag.c, "channel=" + i + ",isOpen=" + z);
        byte[] bArr = new byte[2];
        bArr[0] = (byte) i;
        bArr[1] = z ? (byte) 1 : 2;
        return CamProtocolUtils.a(10010, 2, bArr);
    }

    public static byte[] b() {
        return CamProtocolUtils.a(10050, 0, (byte[]) null);
    }

    public static byte[] a(int i, int i2, int i3, int i4, int i5) {
        SDKLog.b("CameraPlayerActivity", "bitRate=" + i + ",resolution=" + i2 + ",fps=" + i3 + ",horFlip=" + i4 + ",verFlip=" + i5);
        byte[] bArr = {0, 0, (byte) i2, (byte) i3, (byte) i4, (byte) i5};
        ByteOperator.a(bArr, 0, ByteOperator.b(i), 0, 1);
        return CamProtocolUtils.a(10052, bArr.length, bArr);
    }

    public static byte[] a(byte[] bArr) {
        return CamProtocolUtils.a(11082, bArr.length, bArr);
    }

    public static byte[] c() {
        return CamProtocolUtils.a(11080, 0, (byte[]) null);
    }

    public static byte[] d() {
        return CamProtocolUtils.a(10134, 0, (byte[]) null);
    }

    public static byte[] b(long j) {
        byte[] bArr;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", j + "");
            bArr = jSONObject.toString().getBytes(Charset.forName("UTF-8"));
        } catch (JSONException unused) {
            SDKLog.e(Tag.c, "doSendRecordVoiceSuccess fail ");
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        return CamProtocolUtils.a(10530, bArr.length, bArr);
    }

    public static byte[] e() {
        return CamProtocolUtils.a(10532, 0, (byte[]) null);
    }

    public static byte[] a(int i, String str) {
        byte[] bArr = new byte[21];
        bArr[0] = (byte) i;
        byte[] bytes = str.getBytes();
        ByteOperator.a(bArr, 1, bytes, 0, bytes.length);
        return CamProtocolUtils.a(10136, bArr.length, bArr);
    }

    public static byte[] a(int i, int i2) {
        return CamProtocolUtils.a(10534, 2, new byte[]{(byte) i, (byte) i2});
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[2];
        ByteOperator.a(bArr, ByteOperator.f(i));
        return CamProtocolUtils.a(10138, bArr.length, bArr);
    }

    public static byte[] f() {
        return CamProtocolUtils.a(10540, 0, (byte[]) null);
    }

    public static byte[] g() {
        return CamProtocolUtils.a(10536, 0, (byte[]) null);
    }
}
