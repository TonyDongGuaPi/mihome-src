package cn.com.xm.bt.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.xm.bt.a.a;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.tutk.IOTC.AVIOCTRLDEFs;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.lang.reflect.Field;
import java.util.UUID;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final UUID f562a = a("2902");
    public static final UUID b = a("2901");
    public static UUID c = new UUID(303230942, 1523193452336828707L);
    public static UUID d = new UUID(-1152921504534413312L, -5764607523034234880L);
    private static final String e = String.format("0000%4s-0000-1000-8000-00805f9b34fb", new Object[]{"...."});
    private static final UUID f = new UUID(13586, 2384656044284446464L);
    private static final int[] g = {0, 49345, 49537, CommonUtils.x, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, PhotoshopDirectory.aA, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, CanonMakernoteDirectory.AFInfo.b, 4800, 4992, 54081, FujifilmMakernoteDirectory.z, 53697, 53377, 4160, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_PLAYBACK_CTRL_REQ, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, HomeRoomAddNewUserDeviceAdapter.f18333a, 16320, RecordDevice.PCM_FREQUENCE_16K, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, AsrRequest.d, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, SonyType1MakernoteDirectory.ac, CacheDataSink.DEFAULT_BUFFER_SIZE, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, StringUtil.b, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, FujifilmMakernoteDirectory.P, 33217, 32897, 16448};
    private static Field h = null;

    public static UUID a(String str) {
        return UUID.fromString(String.format("0000%4s-0000-1000-8000-00805f9b34fb", new Object[]{str}));
    }

    public static UUID a(int i) {
        return a(f, (short) i);
    }

    public static UUID b(String str) {
        return UUID.fromString(str);
    }

    private static UUID a(UUID uuid, short s) {
        return new UUID((uuid.getMostSignificantBits() & -281470681743361L) | ((((long) s) & 65535) << 32), uuid.getLeastSignificantBits());
    }

    public static String a(byte[] bArr) {
        a.a((Object) bArr);
        if (bArr == null || bArr.length == 0) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x ", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static int b(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        byte b2 = 0;
        while (i < length) {
            byte b3 = b2 ^ (bArr[i] & 255);
            for (int i2 = 0; i2 < 8; i2++) {
                b3 = (b3 & 1) != 0 ? ((b3 >> 1) & 255) ^ Constants.TagName.PREDEPOSIT_STATUS : (b3 >> 1) & 255;
            }
            i++;
            b2 = b3;
        }
        return b2;
    }

    private static int a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 |= (bArr[i + i4] & 255) << (i4 * 8);
        }
        return i3;
    }

    public static int a(byte[] bArr, int i) {
        return a(bArr, i, 4);
    }

    private static String b(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) > 0) {
            sb.append("BROADCAST ");
        }
        if ((i & 128) > 0) {
            sb.append("EXTENDED_PROPS ");
        }
        if ((i & 32) > 0) {
            sb.append("INDICATE ");
        }
        if ((i & 16) > 0) {
            sb.append("NOTIFY ");
        }
        if ((i & 2) > 0) {
            sb.append("READ ");
        }
        if ((i & 64) > 0) {
            sb.append("SIGNED_WRITE ");
        }
        if ((i & 8) > 0) {
            sb.append("WRITE ");
        }
        if ((i & 4) > 0) {
            sb.append("WRITE_NO_RESPONSE ");
        }
        return sb.toString();
    }

    public static String a(UUID uuid) {
        String uuid2 = uuid.toString();
        return uuid2.matches(e) ? uuid2.substring(4, 8) : uuid2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ae, code lost:
        r4 = r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.com.xm.bt.d.a c(byte[] r27) {
        /*
            r0 = r27
            int r1 = r0.length
            r2 = 0
            r3 = 1
            r4 = 62
            if (r1 != r4) goto L_0x000b
            r1 = 1
            goto L_0x000c
        L_0x000b:
            r1 = 0
        L_0x000c:
            cn.com.xm.bt.a.a.b((boolean) r1)
            cn.com.xm.bt.d.a r1 = new cn.com.xm.bt.d.a
            r1.<init>()
            r4 = 0
        L_0x0015:
            r5 = 62
            if (r4 < r5) goto L_0x001a
            goto L_0x0027
        L_0x001a:
            int r5 = r4 + 1
            byte r4 = r0[r4]
            if (r4 != 0) goto L_0x0021
            goto L_0x0027
        L_0x0021:
            int r6 = r4 + r5
            r7 = 62
            if (r6 <= r7) goto L_0x0028
        L_0x0027:
            return r1
        L_0x0028:
            int r6 = r5 + 1
            byte r5 = r0[r5]
            r7 = -1
            if (r5 == r7) goto L_0x04d9
            r7 = 2
            switch(r5) {
                case 1: goto L_0x04a7;
                case 2: goto L_0x0445;
                case 3: goto L_0x03e3;
                default: goto L_0x0033;
            }
        L_0x0033:
            r12 = 11
            r14 = 12
            r15 = 3
            r16 = 13
            r17 = 14
            r18 = 15
            r9 = 16
            switch(r5) {
                case 6: goto L_0x02f0;
                case 7: goto L_0x0201;
                case 8: goto L_0x01d2;
                case 9: goto L_0x01a3;
                case 10: goto L_0x0175;
                default: goto L_0x0043;
            }
        L_0x0043:
            switch(r5) {
                case 20: goto L_0x012a;
                case 21: goto L_0x00df;
                case 22: goto L_0x00b1;
                default: goto L_0x0046;
            }
        L_0x0046:
            int r4 = r4 + -1
            byte[] r7 = new byte[r4]
            r8 = r6
            r6 = 0
        L_0x004c:
            if (r6 >= r4) goto L_0x0058
            int r9 = r8 + 1
            byte r8 = r0[r8]
            r7[r6] = r8
            int r6 = r6 + 1
            r8 = r9
            goto L_0x004c
        L_0x0058:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "[0x%02x]"
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.Byte r10 = java.lang.Byte.valueOf(r5)
            r9[r2] = r10
            java.lang.String r6 = java.lang.String.format(r6, r9)
            r4.append(r6)
            java.lang.String r6 = ": "
            r4.append(r6)
            java.lang.String r6 = a((byte[]) r7)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r1.n = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "      "
            r4.append(r6)
            java.lang.String r6 = "[0x%02x]"
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r9[r2] = r5
            java.lang.String r5 = java.lang.String.format(r6, r9)
            r4.append(r5)
            java.lang.String r5 = ": "
            r4.append(r5)
            java.lang.String r5 = a((byte[]) r7)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
        L_0x00ae:
            r4 = r8
            goto L_0x0015
        L_0x00b1:
            int r4 = r4 + -1
            byte[] r5 = new byte[r4]
            r8 = r6
            r6 = 0
        L_0x00b7:
            if (r6 >= r4) goto L_0x00c3
            int r9 = r8 + 1
            byte r8 = r0[r8]
            r5[r6] = r8
            int r6 = r6 + 1
            r8 = r9
            goto L_0x00b7
        L_0x00c3:
            java.lang.String r4 = "%02X%02X"
            java.lang.Object[] r6 = new java.lang.Object[r7]
            byte r7 = r5[r3]
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)
            r6[r2] = r7
            byte r5 = r5[r2]
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r6[r3] = r5
            java.lang.String r4 = java.lang.String.format(r4, r6)
            a((java.lang.String) r4)
            goto L_0x00ae
        L_0x00df:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r7 = r6
            r6 = 0
        L_0x00e6:
            int r8 = r4 + -1
            if (r6 >= r8) goto L_0x010a
            byte[] r8 = new byte[r9]
            r10 = r7
            r7 = 0
        L_0x00ee:
            if (r7 >= r9) goto L_0x00fa
            int r11 = r10 + 1
            byte r10 = r0[r10]
            r8[r7] = r10
            int r7 = r7 + 1
            r10 = r11
            goto L_0x00ee
        L_0x00fa:
            java.lang.String r7 = a((byte[]) r8)
            r5.append(r7)
            java.lang.String r7 = "; "
            r5.append(r7)
            int r6 = r6 + 16
            r7 = r10
            goto L_0x00e6
        L_0x010a:
            java.lang.String r4 = r5.toString()
            r1.i = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "  solicit128: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x0505
        L_0x012a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r8 = r6
            r6 = 0
        L_0x0131:
            int r9 = r4 + -1
            if (r6 >= r9) goto L_0x0155
            byte[] r9 = new byte[r7]
            r10 = r8
            r8 = 0
        L_0x0139:
            if (r8 >= r7) goto L_0x0145
            int r11 = r10 + 1
            byte r10 = r0[r10]
            r9[r8] = r10
            int r8 = r8 + 1
            r10 = r11
            goto L_0x0139
        L_0x0145:
            java.lang.String r8 = a((byte[]) r9)
            r5.append(r8)
            java.lang.String r8 = "; "
            r5.append(r8)
            int r6 = r6 + 2
            r8 = r10
            goto L_0x0131
        L_0x0155:
            java.lang.String r4 = r5.toString()
            r1.h = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "   solicit16: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x00ae
        L_0x0175:
            int r4 = r4 + -1
            byte[] r5 = new byte[r4]
            r7 = r6
            r6 = 0
        L_0x017b:
            if (r6 >= r4) goto L_0x0187
            int r8 = r7 + 1
            byte r7 = r0[r7]
            r5[r6] = r7
            int r6 = r6 + 1
            r7 = r8
            goto L_0x017b
        L_0x0187:
            java.lang.String r4 = a((byte[]) r5)
            r1.l = r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "    tx level: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x0505
        L_0x01a3:
            int r4 = r4 + -1
            byte[] r5 = new byte[r4]
            r7 = r6
            r6 = 0
        L_0x01a9:
            if (r6 >= r4) goto L_0x01b5
            int r8 = r7 + 1
            byte r7 = r0[r7]
            r5[r6] = r7
            int r6 = r6 + 1
            r7 = r8
            goto L_0x01a9
        L_0x01b5:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
            r1.j = r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "        name: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x0505
        L_0x01d2:
            int r4 = r4 + -1
            byte[] r5 = new byte[r4]
            r7 = r6
            r6 = 0
        L_0x01d8:
            if (r6 >= r4) goto L_0x01e4
            int r8 = r7 + 1
            byte r7 = r0[r7]
            r5[r6] = r7
            int r6 = r6 + 1
            r7 = r8
            goto L_0x01d8
        L_0x01e4:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
            r1.k = r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "     (*)name: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x0505
        L_0x0201:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r20 = r6
            r6 = 0
        L_0x0209:
            int r8 = r4 + -1
            if (r6 >= r8) goto L_0x02d4
            byte[] r8 = new byte[r9]
            r11 = 0
        L_0x0210:
            if (r11 >= r9) goto L_0x021d
            int r23 = r20 + 1
            byte r20 = r0[r20]
            r8[r11] = r20
            int r11 = r11 + 1
            r20 = r23
            goto L_0x0210
        L_0x021d:
            java.util.ArrayList<java.lang.String> r11 = r1.f
            java.lang.String r10 = "%02x%02x%02x%02x-%02x%02x-%02x%02x-%02x%02x-%02x%02x%02x%02x%02x%02x"
            java.lang.Object[] r13 = new java.lang.Object[r9]
            byte r23 = r8[r18]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r2] = r23
            byte r23 = r8[r17]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r3] = r23
            byte r23 = r8[r16]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r7] = r23
            byte r23 = r8[r14]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r15] = r23
            byte r23 = r8[r12]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r25 = 4
            r13[r25] = r23
            r23 = 10
            byte r26 = r8[r23]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r26)
            r22 = 5
            r13[r22] = r23
            r21 = 9
            byte r23 = r8[r21]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r19 = 6
            r13[r19] = r23
            r23 = 7
            r26 = 8
            byte r26 = r8[r26]
            java.lang.Byte r26 = java.lang.Byte.valueOf(r26)
            r13[r23] = r26
            r23 = 8
            r26 = 7
            byte r26 = r8[r26]
            java.lang.Byte r26 = java.lang.Byte.valueOf(r26)
            r13[r23] = r26
            byte r23 = r8[r19]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r21 = 9
            r13[r21] = r23
            r22 = 5
            byte r23 = r8[r22]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r24 = 10
            r13[r24] = r23
            r23 = 4
            byte r26 = r8[r23]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r26)
            r13[r12] = r23
            byte r23 = r8[r15]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r14] = r23
            byte r23 = r8[r7]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r16] = r23
            byte r23 = r8[r3]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r17] = r23
            byte r23 = r8[r2]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r13[r18] = r23
            java.lang.String r10 = java.lang.String.format(r10, r13)
            r11.add(r10)
            java.lang.String r8 = a((byte[]) r8)
            r5.append(r8)
            java.lang.String r8 = "; "
            r5.append(r8)
            int r6 = r6 + 16
            goto L_0x0209
        L_0x02d4:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "     serv128: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            r4 = r20
            goto L_0x0015
        L_0x02f0:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r8 = r6
            r6 = 0
        L_0x02f7:
            int r10 = r4 + -1
            if (r6 >= r10) goto L_0x03c9
            byte[] r10 = new byte[r9]
            r11 = r8
            r8 = 0
        L_0x02ff:
            if (r8 >= r9) goto L_0x030b
            int r13 = r11 + 1
            byte r11 = r0[r11]
            r10[r8] = r11
            int r8 = r8 + 1
            r11 = r13
            goto L_0x02ff
        L_0x030b:
            java.util.ArrayList<java.lang.String> r8 = r1.g
            java.lang.String r13 = "%02x%02x%02x%02x-%02x%02x-%02x%02x-%02x%02x-%02x%02x%02x%02x%02x%02x"
            java.lang.Object[] r12 = new java.lang.Object[r9]
            byte r20 = r10[r18]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r20)
            r12[r2] = r20
            byte r20 = r10[r17]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r20)
            r12[r3] = r20
            byte r20 = r10[r16]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r20)
            r12[r7] = r20
            byte r20 = r10[r14]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r20)
            r12[r15] = r20
            r20 = 11
            byte r23 = r10[r20]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r23)
            r23 = 4
            r12[r23] = r20
            r20 = 10
            byte r23 = r10[r20]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r23)
            r22 = 5
            r12[r22] = r20
            r20 = 9
            byte r23 = r10[r20]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r23)
            r19 = 6
            r12[r19] = r20
            r20 = 7
            r23 = 8
            byte r23 = r10[r23]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r12[r20] = r23
            r20 = 8
            r23 = 7
            byte r23 = r10[r23]
            java.lang.Byte r23 = java.lang.Byte.valueOf(r23)
            r12[r20] = r23
            byte r20 = r10[r19]
            java.lang.Byte r20 = java.lang.Byte.valueOf(r20)
            r21 = 9
            r12[r21] = r20
            r20 = 5
            byte r22 = r10[r20]
            java.lang.Byte r22 = java.lang.Byte.valueOf(r22)
            r23 = 10
            r12[r23] = r22
            r22 = 4
            byte r24 = r10[r22]
            java.lang.Byte r24 = java.lang.Byte.valueOf(r24)
            r25 = 11
            r12[r25] = r24
            byte r24 = r10[r15]
            java.lang.Byte r24 = java.lang.Byte.valueOf(r24)
            r12[r14] = r24
            byte r24 = r10[r7]
            java.lang.Byte r24 = java.lang.Byte.valueOf(r24)
            r12[r16] = r24
            byte r24 = r10[r3]
            java.lang.Byte r24 = java.lang.Byte.valueOf(r24)
            r12[r17] = r24
            byte r24 = r10[r2]
            java.lang.Byte r24 = java.lang.Byte.valueOf(r24)
            r12[r18] = r24
            java.lang.String r12 = java.lang.String.format(r13, r12)
            r8.add(r12)
            java.lang.String r8 = a((byte[]) r10)
            r5.append(r8)
            java.lang.String r8 = "; "
            r5.append(r8)
            int r6 = r6 + 16
            r8 = r11
            r12 = 11
            goto L_0x02f7
        L_0x03c9:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "  (*)serv128: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x00ae
        L_0x03e3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r8 = r6
            r6 = 0
        L_0x03ea:
            int r9 = r4 + -1
            if (r6 >= r9) goto L_0x042b
            byte[] r9 = new byte[r7]
            r10 = r8
            r8 = 0
        L_0x03f2:
            if (r8 >= r7) goto L_0x03fe
            int r11 = r10 + 1
            byte r10 = r0[r10]
            r9[r8] = r10
            int r8 = r8 + 1
            r10 = r11
            goto L_0x03f2
        L_0x03fe:
            java.util.ArrayList<java.lang.String> r8 = r1.d
            java.lang.String r11 = "%02x%02x"
            java.lang.Object[] r12 = new java.lang.Object[r7]
            byte r13 = r9[r3]
            java.lang.Byte r13 = java.lang.Byte.valueOf(r13)
            r12[r2] = r13
            byte r13 = r9[r2]
            java.lang.Byte r13 = java.lang.Byte.valueOf(r13)
            r12[r3] = r13
            java.lang.String r11 = java.lang.String.format(r11, r12)
            r8.add(r11)
            java.lang.String r8 = a((byte[]) r9)
            r5.append(r8)
            java.lang.String r8 = "; "
            r5.append(r8)
            int r6 = r6 + 2
            r8 = r10
            goto L_0x03ea
        L_0x042b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "      serv16: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x00ae
        L_0x0445:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r8 = r6
            r6 = 0
        L_0x044c:
            int r9 = r4 + -1
            if (r6 >= r9) goto L_0x048d
            byte[] r9 = new byte[r7]
            r10 = r8
            r8 = 0
        L_0x0454:
            if (r8 >= r7) goto L_0x0460
            int r11 = r10 + 1
            byte r10 = r0[r10]
            r9[r8] = r10
            int r8 = r8 + 1
            r10 = r11
            goto L_0x0454
        L_0x0460:
            java.util.ArrayList<java.lang.String> r8 = r1.e
            java.lang.String r11 = "%02x%02x"
            java.lang.Object[] r12 = new java.lang.Object[r7]
            byte r13 = r9[r3]
            java.lang.Byte r13 = java.lang.Byte.valueOf(r13)
            r12[r2] = r13
            byte r13 = r9[r2]
            java.lang.Byte r13 = java.lang.Byte.valueOf(r13)
            r12[r3] = r13
            java.lang.String r11 = java.lang.String.format(r11, r12)
            r8.add(r11)
            java.lang.String r8 = a((byte[]) r9)
            r5.append(r8)
            java.lang.String r8 = "; "
            r5.append(r8)
            int r6 = r6 + 2
            r8 = r10
            goto L_0x044c
        L_0x048d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "   (*)serv16: "
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
            goto L_0x00ae
        L_0x04a7:
            if (r4 != r7) goto L_0x04ab
            r4 = 1
            goto L_0x04ac
        L_0x04ab:
            r4 = 0
        L_0x04ac:
            cn.com.xm.bt.a.a.b((boolean) r4)
            int r4 = r6 + 1
            byte r5 = r0[r6]
            r1.c = r5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "        flag: "
            r6.append(r7)
            java.lang.String r7 = "%02x"
            java.lang.Object[] r8 = new java.lang.Object[r3]
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r8[r2] = r5
            java.lang.String r5 = java.lang.String.format(r7, r8)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r5)
            goto L_0x0015
        L_0x04d9:
            int r4 = r4 + -1
            byte[] r5 = new byte[r4]
            r7 = r6
            r6 = 0
        L_0x04df:
            if (r6 >= r4) goto L_0x04eb
            int r8 = r7 + 1
            byte r7 = r0[r7]
            r5[r6] = r7
            int r6 = r6 + 1
            r7 = r8
            goto L_0x04df
        L_0x04eb:
            java.lang.String r4 = a((byte[]) r5)
            r1.m = r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "    manufact: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            cn.com.xm.bt.a.a.a((java.lang.String) r4)
        L_0x0505:
            r4 = r7
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.c.c.c(byte[]):cn.com.xm.bt.d.a");
    }

    public static int a(BluetoothGatt bluetoothGatt) {
        if (h == null) {
            try {
                h = BluetoothGatt.class.getDeclaredField("mClientIf");
                h.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
        try {
            return h.getInt(bluetoothGatt);
        } catch (IllegalAccessException | IllegalArgumentException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    public static void a(BluetoothGattService bluetoothGattService) {
        StringBuilder sb = new StringBuilder();
        sb.append(bluetoothGattService.getType() == 0 ? "Primary" : "Secondary");
        sb.append(" service: ");
        sb.append(a(bluetoothGattService.getUuid()));
        a.a("GattUtils", sb.toString());
        for (BluetoothGattCharacteristic next : bluetoothGattService.getCharacteristics()) {
            a.a("GattUtils", "  Characteristic: " + a(next.getUuid()));
            a.a("GattUtils", "    - Properties: " + b(next.getProperties()));
            for (BluetoothGattDescriptor uuid : next.getDescriptors()) {
                a.a("GattUtils", "    Descriptor: " + a(uuid.getUuid()));
            }
        }
    }

    public static boolean a() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static byte[] c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static String d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.toString();
    }
}
