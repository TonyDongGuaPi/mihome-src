package com.xiaomi.smarthome.device.bluetooth;

import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.device.bluetooth.advertise.BleAdvertisement;
import com.xiaomi.smarthome.device.bluetooth.advertise.Pdu;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;

public class MikeyAdvParser {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15109a = 10;
    private static final byte[] b = {1, Constants.TagName.PAY_ORDER_LIST, 5, -38};
    private static final byte[] c = {0, 31, -111, -2};

    public static ParseResult a(XmBluetoothDevice xmBluetoothDevice) {
        try {
            for (Pdu next : new BleAdvertisement(xmBluetoothDevice).a()) {
                if (a(next)) {
                    byte[] h = next.h();
                    int e = next.e() + b.length;
                    int i = e + 1;
                    ParseResult parseResult = new ParseResult(h[e], ByteUtils.a(h, i, i + 5), h[i + 6]);
                    try {
                        if (parseResult.a()) {
                            return parseResult;
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static boolean b(XmBluetoothDevice xmBluetoothDevice) {
        try {
            for (Pdu b2 : new BleAdvertisement(xmBluetoothDevice).a()) {
                if (b(b2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(Pdu pdu) {
        return (pdu.b() & 255) == 255 && pdu.c() > 12 && ByteUtils.a(pdu.h(), pdu.e(), b);
    }

    private static boolean b(Pdu pdu) {
        return (pdu.b() & 255) == 2 && pdu.c() >= 5 && ByteUtils.a(pdu.h(), pdu.e(), c);
    }

    public static class ParseResult {

        /* renamed from: a  reason: collision with root package name */
        private String f15110a;
        private int b;
        private int c;
        private XmBluetoothDevice d;

        public ParseResult(byte b2, byte[] bArr, byte b3) {
            this.c = b2 & 255;
            this.b = b3 & 255;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < bArr.length) {
                Object[] objArr = new Object[2];
                objArr[0] = i != 0 ? ":" : "";
                objArr[1] = Integer.valueOf(bArr[i] & 255);
                sb.append(String.format("%s%02X", objArr));
                i++;
            }
            this.f15110a = sb.toString();
        }

        public boolean a() {
            if (TextUtils.isEmpty(this.f15110a) || this.c != 10) {
                return false;
            }
            if (this.b == 1 || this.b == 2 || this.b == 3) {
                return true;
            }
            return false;
        }

        public String b() {
            return XMStringUtils.i(this.f15110a);
        }

        public void a(String str) {
            this.f15110a = str;
        }

        public int c() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public int d() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public XmBluetoothDevice e() {
            return this.d;
        }

        public void a(XmBluetoothDevice xmBluetoothDevice) {
            this.d = xmBluetoothDevice;
        }

        public int f() {
            if (this.d != null) {
                return this.d.rssi;
            }
            return Integer.MIN_VALUE;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("type = " + this.c);
            sb.append(", mac = " + this.f15110a);
            sb.append(", event = " + this.b);
            sb.append(", rssi = " + this.d.rssi);
            return sb.toString();
        }
    }
}
