package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BleMessageParser {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14100a = 5;
    public static final byte b = 1;
    public static final byte c = 0;
    public static final byte d = 1;
    public static final byte e = 2;
    public static final byte f = 3;
    public static final byte g = 4;
    public static final byte h = 0;
    public static final byte i = 1;
    public static final byte j = 0;
    public static final byte k = 0;
    public static final byte l = 1;
    private static Map<String, IBleResponse> m = new ConcurrentHashMap();

    public static void a(String str, IBleResponse iBleResponse) {
        if (!TextUtils.isEmpty(str) && iBleResponse != null) {
            m.put(str, iBleResponse);
        }
    }

    public static void a(String str) {
        m.remove(str);
    }

    public static byte[] a(byte[] bArr) {
        return a(new MiBTChannelData(0, (byte) 3, (byte) 1, bArr), false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser.MiBTChannelData r4, boolean r5) {
        /*
            byte[] r0 = r4.d
            if (r5 == 0) goto L_0x003b
            byte[] r1 = r4.d
            if (r1 == 0) goto L_0x003b
            byte[] r1 = r4.d
            int r1 = r1.length
            if (r1 <= 0) goto L_0x003b
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x0037 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0037 }
            byte[] r3 = r4.d     // Catch:{ IOException -> 0x0037 }
            r2.write(r3)     // Catch:{ IOException -> 0x0037 }
            r2.finish()     // Catch:{ IOException -> 0x0037 }
            r2.close()     // Catch:{ IOException -> 0x0037 }
            byte[] r2 = r1.toByteArray()     // Catch:{ IOException -> 0x0037 }
            r1.close()     // Catch:{ IOException -> 0x0033 }
            r0 = r2
            goto L_0x003b
        L_0x0033:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x0038
        L_0x0037:
            r1 = move-exception
        L_0x0038:
            r1.printStackTrace()
        L_0x003b:
            r1 = 5
            if (r0 == 0) goto L_0x0041
            int r2 = r0.length
            int r2 = r2 + r1
            goto L_0x0042
        L_0x0041:
            r2 = 5
        L_0x0042:
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            r3 = 1
            r2.put(r3)
            short r3 = r4.f14103a
            r2.putShort(r3)
            byte r3 = r4.b()
            int r3 = r3 << 4
            byte r4 = r4.c()
            r4 = r4 | r3
            byte r4 = (byte) r4
            r2.put(r4)
            int r4 = r5 << 5
            byte r4 = (byte) r4
            r2.put(r4)
            if (r0 == 0) goto L_0x0071
            r2.put(r0)
        L_0x0071:
            byte[] r4 = r2.array()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser.a(com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser$MiBTChannelData, boolean):byte[]");
    }

    private static MiBTChannelData b(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            BluetoothMyLogger.d("MiBTChannelUtil parse data invalid");
            return null;
        }
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        order.get();
        short s = order.getShort();
        byte b2 = order.get();
        byte b3 = order.get();
        byte[] bArr2 = new byte[0];
        if (bArr.length > 5) {
            bArr2 = Arrays.copyOfRange(bArr, 5, bArr.length);
        }
        byte b4 = (byte) (b2 & 15);
        byte b5 = (byte) ((b2 & 255) >> 4);
        if (((byte) ((b3 & 255) >> 5)) == 1 && bArr2.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                byte[] bArr3 = new byte[256];
                while (true) {
                    int read = gZIPInputStream.read(bArr3);
                    if (read < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr3, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                    bArr2 = byteArray;
                } catch (IOException e2) {
                    e = e2;
                    bArr2 = byteArray;
                    e.printStackTrace();
                    return new MiBTChannelData(s, b5, b4, bArr2);
                }
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return new MiBTChannelData(s, b5, b4, bArr2);
            }
        }
        return new MiBTChannelData(s, b5, b4, bArr2);
    }

    public static void a(String str, byte[] bArr, IBleChannelWriter iBleChannelWriter) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        MiBTChannelData b2 = b(bArr);
        if (b2 != null) {
            switch (b2.b()) {
                case 1:
                    try {
                        jSONObject = new JSONObject(new String(b2.d()));
                    } catch (Exception unused) {
                        jSONObject = null;
                    }
                    a(b2.a(), jSONObject, iBleChannelWriter);
                    return;
                case 2:
                    try {
                        jSONArray = new JSONArray(new String(b2.d()));
                    } catch (Exception unused2) {
                        jSONArray = null;
                    }
                    a(b2.a(), jSONArray, iBleChannelWriter);
                    return;
                case 3:
                    a(str, b2.d());
                    return;
                case 4:
                    a(b2.a(), b2.d(), iBleChannelWriter);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(final short r11, org.json.JSONObject r12, final com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter r13) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            if (r12 != 0) goto L_0x000e
            java.lang.String r12 = "doRpc failure, jsonObject is null"
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.c(r12)
            b(r2, r11, r1, r0, r13)
            return
        L_0x000e:
            java.lang.String r3 = "did"
            java.lang.String r3 = r12.optString(r3)
            java.lang.String r4 = "method"
            java.lang.String r4 = r12.optString(r4)
            java.lang.String r5 = "params"
            java.lang.String r12 = r12.optString(r5)
            java.lang.String r5 = ""
            boolean r6 = android.text.TextUtils.isEmpty(r12)
            if (r6 != 0) goto L_0x0054
            java.lang.String r6 = r12.substring(r1, r2)
            java.lang.String r7 = "["
            boolean r7 = android.text.TextUtils.equals(r6, r7)
            if (r7 == 0) goto L_0x003f
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ JSONException -> 0x003a }
            r6.<init>(r12)     // Catch:{ JSONException -> 0x003a }
            goto L_0x0055
        L_0x003a:
            r12 = move-exception
            r12.printStackTrace()
            goto L_0x0054
        L_0x003f:
            java.lang.String r7 = "{"
            boolean r6 = android.text.TextUtils.equals(r6, r7)
            if (r6 == 0) goto L_0x0052
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x004d }
            r6.<init>(r12)     // Catch:{ JSONException -> 0x004d }
            goto L_0x0055
        L_0x004d:
            r12 = move-exception
            r12.printStackTrace()
            goto L_0x0054
        L_0x0052:
            r6 = r12
            goto L_0x0055
        L_0x0054:
            r6 = r5
        L_0x0055:
            com.xiaomi.smarthome.core.server.internal.device.DeviceManager r12 = com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a()
            com.xiaomi.smarthome.core.entity.device.Device r12 = r12.a((java.lang.String) r3)
            if (r12 == 0) goto L_0x009a
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "id"
            double r7 = java.lang.Math.random()     // Catch:{ JSONException -> 0x0081 }
            r9 = 4681608360884174848(0x40f86a0000000000, double:100000.0)
            double r7 = r7 * r9
            double r7 = r7 + r9
            int r2 = (int) r7     // Catch:{ JSONException -> 0x0081 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r1 = "method"
            r0.put(r1, r4)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r1 = "params"
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0085:
            com.xiaomi.smarthome.core.server.internal.device.api.DeviceApiInternal r1 = com.xiaomi.smarthome.core.server.internal.device.api.DeviceApiInternal.a()
            java.lang.String r12 = r12.s()
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser$1 r2 = new com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser$1
            r2.<init>(r11, r13)
            r1.a(r3, r12, r0, r2)
            goto L_0x00a2
        L_0x009a:
            java.lang.String r12 = "doRpc failure, device is null"
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.c(r12)
            b(r2, r11, r1, r0, r13)
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser.a(short, org.json.JSONObject, com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter):void");
    }

    private static void a(final short s, final JSONArray jSONArray, final IBleChannelWriter iBleChannelWriter) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            BluetoothMyLogger.c("getDeviceProp failure, jsonArray is null");
            b((byte) 2, s, false, (byte[]) null, iBleChannelWriter);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONArray.toString()));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/device/batchdevicedatas").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                JSONObject jSONObject;
                try {
                    jSONObject = new JSONObject(netResult.c).optJSONObject("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("props", jSONObject);
                    JSONObject jSONObject3 = new JSONObject();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        try {
                            JSONObject optJSONObject = jSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("did");
                                if (!TextUtils.isEmpty(optString)) {
                                    Device a2 = DeviceManager.a().a(optString);
                                    if (a2 != null) {
                                        jSONObject3.put(optString, a2.o() ? 1 : 0);
                                    } else {
                                        jSONObject3.put(optString, 0);
                                    }
                                }
                            }
                            i++;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    jSONObject2.put("onlines", jSONObject3);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                BleMessageParser.b((byte) 2, s, true, jSONObject2.toString().getBytes(), iBleChannelWriter);
            }

            public void a(NetError netError) {
                BluetoothMyLogger.c("getDeviceProp failure, " + netError.b());
                BleMessageParser.b((byte) 2, s, false, netError.b() != null ? netError.b().getBytes() : null, iBleChannelWriter);
            }
        });
    }

    private static void a(String str, byte[] bArr) {
        IBleResponse iBleResponse = m.get(str);
        if (iBleResponse != null) {
            Bundle bundle = new Bundle();
            bundle.putByteArray(IBluetoothService.P, bArr);
            try {
                iBleResponse.onResponse(0, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    private static void a(short s, byte[] bArr, IBleChannelWriter iBleChannelWriter) {
        b((byte) 4, s, true, bArr, iBleChannelWriter);
    }

    /* access modifiers changed from: private */
    public static void b(byte b2, short s, boolean z, byte[] bArr, IBleChannelWriter iBleChannelWriter) {
        b(b2, s, z, bArr, false, iBleChannelWriter);
    }

    /* access modifiers changed from: private */
    public static void b(byte b2, short s, boolean z, byte[] bArr, boolean z2, IBleChannelWriter iBleChannelWriter) {
        String str = "";
        if (bArr != null) {
            str = new String(bArr);
        }
        BluetoothMyLogger.d(String.format("BleMessageParser onResult msg_type = %d, id = %d, result = %b, returnMsg = %s", new Object[]{Byte.valueOf(b2), Short.valueOf(s), Boolean.valueOf(z), str}));
        byte[] a2 = a(new MiBTChannelData(s, b2, z ? (byte) 1 : 0, bArr), z2);
        if (iBleChannelWriter != null) {
            try {
                iBleChannelWriter.write(a2, 0, new IBleResponse.Stub() {
                    public void onResponse(int i, Bundle bundle) throws RemoteException {
                    }
                });
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static class MiBTChannelData {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public short f14103a = 0;
        private byte b = 0;
        private byte c = 0;
        /* access modifiers changed from: private */
        public byte[] d;

        public MiBTChannelData(short s, byte b2, byte[] bArr) {
            this.f14103a = s;
            this.b = b2;
            this.d = bArr;
        }

        public MiBTChannelData(short s, byte b2, byte b3, byte[] bArr) {
            this.f14103a = s;
            this.b = b2;
            this.c = b3;
            this.d = bArr;
        }

        public short a() {
            return this.f14103a;
        }

        public byte b() {
            return this.b;
        }

        public byte c() {
            return this.c;
        }

        public byte[] d() {
            return this.d;
        }

        public boolean e() {
            return this.c == 1;
        }

        public String toString() {
            return "MiBTChannelUtil{id=" + this.f14103a + ", type=" + this.b + ", data='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }
}
