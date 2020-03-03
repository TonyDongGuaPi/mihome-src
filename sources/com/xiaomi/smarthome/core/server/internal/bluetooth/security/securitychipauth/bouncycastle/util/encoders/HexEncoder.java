package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.encoders;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class HexEncoder implements Encoder {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f14463a = {48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102};
    protected final byte[] b = new byte[128];

    private static boolean a(char c) {
        return c == 10 || c == 13 || c == 9 || c == ' ';
    }

    /* access modifiers changed from: protected */
    public void a() {
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = -1;
        }
        for (int i2 = 0; i2 < this.f14463a.length; i2++) {
            this.b[this.f14463a[i2]] = (byte) i2;
        }
        this.b[65] = this.b[97];
        this.b[66] = this.b[98];
        this.b[67] = this.b[99];
        this.b[68] = this.b[100];
        this.b[69] = this.b[101];
        this.b[70] = this.b[102];
    }

    public HexEncoder() {
        a();
    }

    public int a(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            byte b2 = bArr[i3] & 255;
            outputStream.write(this.f14463a[b2 >>> 4]);
            outputStream.write(this.f14463a[b2 & 15]);
        }
        return i2 * 2;
    }

    public int b(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i2 + i;
        while (i3 > i && a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        while (i < i3) {
            while (i < i3 && a((char) bArr[i])) {
                i++;
            }
            int i5 = i + 1;
            byte b2 = this.b[bArr[i]];
            while (i5 < i3 && a((char) bArr[i5])) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b3 = this.b[bArr[i5]];
            if ((b2 | b3) >= 0) {
                outputStream.write((b2 << 4) | b3);
                i4++;
                i = i6;
            } else {
                throw new IOException("invalid characters encountered in Hex data");
            }
        }
        return i4;
    }

    public int a(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && a(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && a(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            byte b2 = this.b[str.charAt(i)];
            while (i3 < length && a(str.charAt(i3))) {
                i3++;
            }
            int i4 = i3 + 1;
            byte b3 = this.b[str.charAt(i3)];
            if ((b2 | b3) >= 0) {
                outputStream.write((b2 << 4) | b3);
                i2++;
                i = i4;
            } else {
                throw new IOException("invalid characters encountered in Hex string");
            }
        }
        return i2;
    }
}
