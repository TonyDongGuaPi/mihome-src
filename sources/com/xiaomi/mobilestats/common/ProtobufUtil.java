package com.xiaomi.mobilestats.common;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ProtobufUtil {
    public static final String TAG = "ProtobufUtil";
    public static final int TYPE_CDETAIL_MSG_REQUEST = 15;
    public static final int TYPE_CDETAIL_MSG_RESPONSE = 16;
    public static final int TYPE_CLIENT_MSG_REQUEST = 9;
    public static final int TYPE_CLIENT_MSG_RESPONSE = 10;
    public static final int TYPE_CRASH_MSG_REQUEST = 13;
    public static final int TYPE_CRASH_MSG_RESPONSE = 14;
    public static final int TYPE_ERROR_MSG_REQUEST = 11;
    public static final int TYPE_ERROR_MSG_RESPONSE = 12;
    public static final int TYPE_EVENT_MSG_REQUEST = 7;
    public static final int TYPE_EVENT_MSG_RESPONSE = 8;
    public static final int TYPE_PAGE_MSG_REQUEST = 5;
    public static final int TYPE_PAGE_MSG_RESPONSE = 6;
    public static final int TYPE_VIEW_MSG_REQUEST = 17;

    private ProtobufUtil() {
    }

    private void a(byte[] bArr, int i) {
        bArr[0] = (byte) ((i >> 8) & 255);
        bArr[1] = (byte) (i & 255);
    }

    private void b(byte[] bArr, int i) {
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
    }

    public static ProtobufUtil getInstance() {
        return e.C;
    }

    public byte[] generateHeaders(int i, int i2, int i3) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        byte[] bArr3 = new byte[4];
        a(bArr, i);
        a(bArr2, i2);
        b(bArr3, i3);
        return generateRequestBody(bArr, bArr2, bArr3);
    }

    public byte[] generateRequestBody(int i, int i2, int i3, byte[] bArr) {
        return generateRequestBody(generateHeaders(i, i2, i3), bArr);
    }

    public byte[] generateRequestBody(byte[]... bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < bArr.length) {
            try {
                byte[] bArr2 = bArr[i];
                if (bArr2 != null) {
                    byteArrayOutputStream.write(bArr2);
                }
                i++;
            } catch (IOException unused) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public int ipAddressConvert2Int(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            byte b = 0;
            while (i < address.length) {
                try {
                    b = (b << 8) | (address[i] & 255);
                    i++;
                } catch (UnknownHostException unused) {
                    return b;
                }
            }
            return b;
        } catch (UnknownHostException unused2) {
            return 0;
        }
    }

    public String ipAddressConvert2String(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)}).getHostAddress();
        } catch (UnknownHostException unused) {
            return "";
        }
    }
}
