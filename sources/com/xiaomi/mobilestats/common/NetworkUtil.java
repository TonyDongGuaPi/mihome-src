package com.xiaomi.mobilestats.common;

import android.os.Build;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.mobilestats.object.Msg;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {
    protected static final String ANDROID_VERSION = "Android-Ver";
    protected static final String CLIENT_ID = "Mishop-Client-Id";
    protected static final String ChANNEL_ID = "Mishop-Channel-Id";
    protected static final String DEVICE_ID = "Device-Id";
    protected static final String IS_PAD = "Mishop-Is-Pad";
    protected static final String NETWORK_STAT = "Network-Stat";
    protected static final String VERSION_CDOE = "Mishop-Client-VersionCode";
    protected static final String VERSION_NAME = "Mishop-Client-VersionName";
    protected static final String sAppChannel = CommonUtil.getAppChannel(StatService.sApplicationContext);
    protected static final String sClientId = CommonUtil.getClientId(StatService.sApplicationContext);
    protected static final String sDeviceId = DeviceUtil.getInstance().getUniqueId(StatService.sApplicationContext);
    protected static final int sMishopSdkVersionCode = CommonUtil.getMishopSdkVersionCode(StatService.sApplicationContext);
    protected static final String sMishopSdkVersionName = CommonUtil.getMishopSdkVersionName(StatService.sApplicationContext);

    private static void a(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Mishop-Client-Id", sClientId);
        httpURLConnection.setRequestProperty("Network-Stat", CommonUtil.getNetworkType(StatService.sApplicationContext));
        httpURLConnection.setRequestProperty("Mishop-Client-VersionCode", String.valueOf(sMishopSdkVersionCode));
        httpURLConnection.setRequestProperty("Mishop-Client-VersionName", sMishopSdkVersionName);
        httpURLConnection.setRequestProperty("Device-Id", sDeviceId);
        httpURLConnection.setRequestProperty("Mishop-Is-Pad", "0");
        httpURLConnection.setRequestProperty("Android-Ver", String.valueOf(Build.VERSION.SDK_INT));
        httpURLConnection.setRequestProperty("Mishop-Channel-Id", sAppChannel);
    }

    public static Msg postProtoInfo(int i, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Msg msg = new Msg();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(HostManager.PREURL).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            a(httpURLConnection);
            byte[] generateRequestBody = ProtobufUtil.getInstance().generateRequestBody(1, i, bArr.length, bArr);
            String encodeBody = EncodeUtil.getEncodeBody(generateRequestBody);
            if (StrUtil.isEmpty(encodeBody)) {
                encodeBody = "";
            }
            httpURLConnection.setRequestProperty("Mishop-Sign", encodeBody);
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(generateRequestBody);
            dataOutputStream.flush();
            dataOutputStream.close();
            if (httpURLConnection.getResponseCode() == 200) {
                msg.setFlag(true);
                if (i == 9) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    msg.setResponseBytes(toByteArray(inputStream));
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            } else {
                msg.setFlag(false);
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setFlag(false);
        }
        return msg;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x002f A[SYNTHETIC, Splitter:B:23:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003b A[SYNTHETIC, Splitter:B:30:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(java.io.InputStream r5) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0028, all -> 0x0025 }
            r1.<init>()     // Catch:{ IOException -> 0x0028, all -> 0x0025 }
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0023 }
        L_0x000a:
            r3 = -1
            int r4 = r5.read(r2)     // Catch:{ IOException -> 0x0023 }
            if (r3 == r4) goto L_0x0016
            r3 = 0
            r1.write(r2, r3, r4)     // Catch:{ IOException -> 0x0023 }
            goto L_0x000a
        L_0x0016:
            byte[] r5 = r1.toByteArray()     // Catch:{ IOException -> 0x0023 }
            r1.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0022:
            return r5
        L_0x0023:
            r5 = move-exception
            goto L_0x002a
        L_0x0025:
            r5 = move-exception
            r1 = r0
            goto L_0x0039
        L_0x0028:
            r5 = move-exception
            r1 = r0
        L_0x002a:
            r5.printStackTrace()     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0037:
            return r0
        L_0x0038:
            r5 = move-exception
        L_0x0039:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0043:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.NetworkUtil.toByteArray(java.io.InputStream):byte[]");
    }
}
