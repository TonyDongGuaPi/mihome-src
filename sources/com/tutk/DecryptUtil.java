package com.tutk;

import android.text.TextUtils;
import android.util.Log;
import com.tutk.IOTC.Packet;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.util.HashMap;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.encoders.Encoder;

public class DecryptUtil {
    private static final String TAG = "DecryptUtil";

    static {
        System.loadLibrary("sodiumjni");
    }

    public static void getKeyPair(byte[] bArr, byte[] bArr2) {
        Sodium.b(bArr, bArr2);
    }

    public static String byteToString(byte[] bArr) {
        return Encoder.b.a(bArr);
    }

    public static byte[] getShareKey(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        Sodium.c(bArr3, bArr, bArr2);
        return bArr3;
    }

    public static byte[] stringToByte(String str) {
        return Encoder.b.a(str);
    }

    public static byte[] getShareKeyApp(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        Sodium.c(bArr3, bArr, bArr2);
        return bArr3;
    }

    public static byte[] stringToByteApp(String str) {
        return Encoder.b.a(str);
    }

    public static void getKeyPairApp(byte[] bArr, byte[] bArr2) {
        Sodium.b(bArr, bArr2);
    }

    public static String byteToStringApp(byte[] bArr) {
        return Encoder.b.a(bArr);
    }

    public static byte[] decryptVideo(byte[] bArr, byte[] bArr2, boolean z, int i) throws ArrayIndexOutOfBoundsException {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        boolean z2 = z;
        if (bArr3 == null || bArr4 == null) {
            return bArr3;
        }
        HashMap hashMap = new HashMap();
        byte[] bArr5 = new byte[8];
        System.arraycopy(bArr3, 0, bArr5, 0, 8);
        byte b = bArr3[8] & 255;
        int i2 = b * 8;
        byte[] bArr6 = new byte[i2];
        System.arraycopy(bArr3, 9, bArr6, 0, bArr6.length);
        int i3 = i2 + 9;
        byte[] bArr7 = new byte[(bArr3.length - i3)];
        System.arraycopy(bArr3, i3, bArr7, 0, bArr7.length);
        int i4 = 0;
        int i5 = 0;
        while (i4 < b) {
            int byteArrayToInt = Packet.byteArrayToInt(bArr6, i5, z2);
            int i6 = i5 + 4;
            int byteArrayToInt2 = Packet.byteArrayToInt(bArr6, i6, z2);
            i5 = i6 + 4;
            hashMap.put(Integer.valueOf(byteArrayToInt), Integer.valueOf(byteArrayToInt2));
            int i7 = 32;
            while (i7 < byteArrayToInt2 - 16) {
                byte[] bArr8 = new byte[16];
                byte[] bArr9 = new byte[16];
                int i8 = i7 + byteArrayToInt;
                System.arraycopy(bArr7, i8, bArr9, 0, 16);
                Sodium.l(bArr8, bArr9, 16, bArr5, bArr4);
                System.arraycopy(bArr8, 0, bArr7, i8, 16);
                i7 += 160;
                boolean z3 = z;
            }
            i4++;
            z2 = z;
        }
        if (checkNal(bArr7, hashMap, i)) {
            return bArr7;
        }
        Log.e("Video", "fail");
        return null;
    }

    public static byte[] decryptVideoEx(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return bArr;
        }
        if (bArr.length <= 8) {
            return null;
        }
        byte[] bArr3 = new byte[8];
        System.arraycopy(bArr, 0, bArr3, 0, 8);
        int length = bArr.length - 8;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 8, bArr5, 0, length);
        Sodium.l(bArr4, bArr5, bArr5.length, bArr3, bArr2);
        return bArr4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
        if (r4 != 5) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0066, code lost:
        if (r4 != 19) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0069, code lost:
        r4 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean checkNal(byte[] r10, java.util.HashMap<java.lang.Integer, java.lang.Integer> r11, int r12) {
        /*
            r0 = 1
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r10.length
            int r1 = r1 + -3
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000a:
            r5 = 48
            if (r3 >= r1) goto L_0x006c
            byte r6 = r10[r3]
            int r7 = r3 + 1
            byte r8 = r10[r7]
            int r9 = r3 + 2
            byte r9 = r10[r9]
            if (r6 != 0) goto L_0x006a
            if (r8 != 0) goto L_0x006a
            if (r9 != r0) goto L_0x006a
            if (r4 == 0) goto L_0x004a
            int r6 = r3 + -1
            byte r8 = r10[r6]
            if (r8 != 0) goto L_0x0028
            int r6 = r6 - r4
            goto L_0x002a
        L_0x0028:
            int r6 = r3 - r4
        L_0x002a:
            if (r6 <= r5) goto L_0x004a
            java.lang.Integer r10 = java.lang.Integer.valueOf(r4)
            boolean r10 = r11.containsKey(r10)
            if (r10 == 0) goto L_0x0049
            java.lang.Integer r10 = java.lang.Integer.valueOf(r4)
            java.lang.Object r10 = r11.get(r10)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            if (r10 != r6) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r0 = 0
        L_0x0048:
            return r0
        L_0x0049:
            return r2
        L_0x004a:
            r4 = 80
            if (r12 != r4) goto L_0x005b
            int r3 = r3 + 3
            byte r4 = r10[r3]
            r4 = r4 & 31
            if (r4 == r0) goto L_0x0059
            r5 = 5
            if (r4 != r5) goto L_0x0069
        L_0x0059:
            r4 = r3
            goto L_0x006a
        L_0x005b:
            int r3 = r3 + 3
            byte r4 = r10[r3]
            r4 = r4 & 126(0x7e, float:1.77E-43)
            int r4 = r4 >> r0
            if (r4 == r0) goto L_0x0059
            r5 = 19
            if (r4 != r5) goto L_0x0069
            goto L_0x0059
        L_0x0069:
            r4 = 0
        L_0x006a:
            r3 = r7
            goto L_0x000a
        L_0x006c:
            if (r4 == 0) goto L_0x0090
            int r10 = r10.length
            int r10 = r10 - r4
            if (r10 <= r5) goto L_0x0090
            java.lang.Integer r12 = java.lang.Integer.valueOf(r4)
            boolean r12 = r11.containsKey(r12)
            if (r12 == 0) goto L_0x008f
            java.lang.Integer r12 = java.lang.Integer.valueOf(r4)
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r11 != r10) goto L_0x008d
            goto L_0x008e
        L_0x008d:
            r0 = 0
        L_0x008e:
            return r0
        L_0x008f:
            return r2
        L_0x0090:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tutk.DecryptUtil.checkNal(byte[], java.util.HashMap, int):boolean");
    }

    public static byte[] decryptAudioG711(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return bArr;
        }
        if (bArr.length <= 8) {
            return null;
        }
        byte[] bArr3 = new byte[8];
        System.arraycopy(bArr, 0, bArr3, 0, 8);
        int length = bArr.length - 8;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 8, bArr5, 0, length);
        Sodium.l(bArr4, bArr5, bArr5.length, bArr3, bArr2);
        return bArr4;
    }

    public static byte[] encryptAudio(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length <= 8) {
            return bArr;
        }
        byte[] bArr3 = new byte[8];
        byte[] bArr4 = new byte[bArr.length];
        Sodium.b(bArr3, 8);
        Sodium.l(bArr4, bArr, bArr.length, bArr3, bArr2);
        byte[] bArr5 = new byte[(bArr.length + 8)];
        System.arraycopy(bArr3, 0, bArr5, 0, 8);
        System.arraycopy(bArr4, 0, bArr5, 8, bArr.length);
        return bArr5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ad A[Catch:{ Exception -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b0 A[Catch:{ Exception -> 0x00b3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateTutkPwd(com.xiaomi.smarthome.device.api.DeviceStat r9, final com.xiaomi.smarthome.device.api.Callback<com.xiaomi.smarthome.camera.XmP2PInfo> r10) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            com.tutk.TuTkClient.mConnect_Public_Key = r0
            com.tutk.P2PInfoImp r0 = new com.tutk.P2PInfoImp
            r0.<init>()
            com.xiaomi.smarthome.device.api.XmPluginHostApi r1 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            java.lang.String r2 = r9.did
            java.lang.String r1 = r1.getDevicePincode(r2)
            java.lang.String r2 = r9.did
            r0.setDid(r2)
            java.lang.String r2 = r9.model
            r0.setModel(r2)
            boolean r2 = r9.isOnline
            r0.setOnLine(r2)
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r3 = r9.extrainfo     // Catch:{ Exception -> 0x00b3 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r3 = "fw_version"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ Exception -> 0x00b3 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00b3 }
            if (r3 != 0) goto L_0x00ce
            java.lang.String r3 = "_"
            int r3 = r2.lastIndexOf(r3)     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r2 = r2.substring(r3)     // Catch:{ Exception -> 0x00b3 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00b3 }
            r4 = 1
            if (r3 != 0) goto L_0x0054
            java.lang.String r3 = "_"
            boolean r3 = r2.startsWith(r3)     // Catch:{ Exception -> 0x00b3 }
            if (r3 == 0) goto L_0x0054
            java.lang.String r2 = r2.substring(r4)     // Catch:{ Exception -> 0x00b3 }
        L_0x0054:
            java.lang.String r3 = "DecryptUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b3 }
            r5.<init>()     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r6 = "fw_version_postfix:"
            r5.append(r6)     // Catch:{ Exception -> 0x00b3 }
            r5.append(r2)     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00b3 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r3, r5)     // Catch:{ Exception -> 0x00b3 }
            r3 = 0
            if (r9 == 0) goto L_0x007f
            java.lang.String r5 = "chuangmi.camera.ipc009"
            java.lang.String r6 = r9.model     // Catch:{ Exception -> 0x00b3 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00b3 }
            if (r5 == 0) goto L_0x007f
            java.lang.String r5 = "0300"
            int r5 = compareDeviceVersion(r2, r5)     // Catch:{ Exception -> 0x00b3 }
            if (r5 >= 0) goto L_0x0097
        L_0x007f:
            java.lang.String r5 = "chuangmi.camera.ipc019"
            java.lang.String r6 = r9.model     // Catch:{ Exception -> 0x00b3 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00b3 }
            if (r5 != 0) goto L_0x0097
            java.lang.String r5 = "chuangmi.camera.ipc021"
            java.lang.String r6 = r9.model     // Catch:{ Exception -> 0x00b3 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00b3 }
            if (r5 == 0) goto L_0x0094
            goto L_0x0097
        L_0x0094:
            r0.isIPC009VideoFullEncryption = r3     // Catch:{ Exception -> 0x00b3 }
            goto L_0x0099
        L_0x0097:
            r0.isIPC009VideoFullEncryption = r4     // Catch:{ Exception -> 0x00b3 }
        L_0x0099:
            if (r9 == 0) goto L_0x00b0
            java.lang.String r5 = "chuangmi.camera.ipc009"
            java.lang.String r9 = r9.model     // Catch:{ Exception -> 0x00b3 }
            boolean r9 = r5.equals(r9)     // Catch:{ Exception -> 0x00b3 }
            if (r9 == 0) goto L_0x00b0
            java.lang.String r9 = "0301"
            int r9 = compareDeviceVersion(r2, r9)     // Catch:{ Exception -> 0x00b3 }
            if (r9 < 0) goto L_0x00b0
            r0.isIPC009AudioFullEncryption = r4     // Catch:{ Exception -> 0x00b3 }
            goto L_0x00ce
        L_0x00b0:
            r0.isIPC009AudioFullEncryption = r3     // Catch:{ Exception -> 0x00b3 }
            goto L_0x00ce
        L_0x00b3:
            r9 = move-exception
            java.lang.String r2 = "DecryptUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "exception:"
            r3.append(r4)
            java.lang.String r9 = r9.getLocalizedMessage()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r2, r9)
        L_0x00ce:
            byte[] r9 = r0.mPublicKey
            byte[] r2 = r0.mPrivateKey
            getKeyPair(r9, r2)
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            java.lang.String r9 = "did"
            java.lang.String r2 = r0.getDid()     // Catch:{ Exception -> 0x00fa }
            r6.put(r9, r2)     // Catch:{ Exception -> 0x00fa }
            java.lang.String r9 = "toSignAppData"
            byte[] r2 = r0.mPublicKey     // Catch:{ Exception -> 0x00fa }
            java.lang.String r2 = byteToString(r2)     // Catch:{ Exception -> 0x00fa }
            r6.put(r9, r2)     // Catch:{ Exception -> 0x00fa }
            boolean r9 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00fa }
            if (r9 != 0) goto L_0x00fe
            java.lang.String r9 = "pincode"
            r6.put(r9, r1)     // Catch:{ Exception -> 0x00fa }
            goto L_0x00fe
        L_0x00fa:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00fe:
            com.xiaomi.smarthome.device.api.XmPluginHostApi r3 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            java.lang.String r4 = r0.getModel()
            java.lang.String r5 = "/device/devicepass"
            com.tutk.DecryptUtil$1 r7 = new com.tutk.DecryptUtil$1
            r7.<init>(r0, r10)
            com.xiaomi.smarthome.device.api.Parser<org.json.JSONObject> r8 = com.xiaomi.smarthome.device.api.Parser.DEFAULT_PARSER
            r3.callSmartHomeApi((java.lang.String) r4, (java.lang.String) r5, (org.json.JSONObject) r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tutk.DecryptUtil.updateTutkPwd(com.xiaomi.smarthome.device.api.DeviceStat, com.xiaomi.smarthome.device.api.Callback):void");
    }

    public static int compareDeviceVersion(String str, String str2) {
        LogUtil.a(TAG, "sourceVersion:" + str + " targetVersion:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        long j = 0;
        int i = 0;
        while (i < min) {
            j = Long.parseLong(split[i]) - Long.parseLong(split2[i]);
            if (j != 0) {
                break;
            }
            i++;
        }
        if (j == 0) {
            for (int i2 = i; i2 < split.length; i2++) {
                if (Long.parseLong(split[i2]) > 0) {
                    return 1;
                }
            }
            while (i < split2.length) {
                if (Long.parseLong(split2[i]) > 0) {
                    return -1;
                }
                i++;
            }
            return 0;
        } else if (j > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
