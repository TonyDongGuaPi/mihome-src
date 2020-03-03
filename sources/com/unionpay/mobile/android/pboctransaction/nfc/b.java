package com.unionpay.mobile.android.pboctransaction.nfc;

import android.nfc.tech.IsoDep;
import android.util.Log;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f9647a = {0};
    protected byte[] b;

    public static final class a extends b {
        public static final byte[] c = new byte[0];
        public static final byte[] d = {Constants.TagName.ELECTRONIC_END_TIME, 0};

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public a(byte[] r3) {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0006
                int r0 = r3.length
                r1 = 2
                if (r0 >= r1) goto L_0x0008
            L_0x0006:
                byte[] r3 = d
            L_0x0008:
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.nfc.b.a.<init>(byte[]):void");
        }

        public final byte[] a() {
            return b() ? Arrays.copyOfRange(this.b, 0, this.b.length - 2) : c;
        }

        public final boolean b() {
            byte[] bArr = this.b;
            int length = bArr.length;
            return ((short) ((bArr[length - 1] & 255) | (bArr[length + -2] << 8))) == -28672;
        }
    }

    /* renamed from: com.unionpay.mobile.android.pboctransaction.nfc.b$b  reason: collision with other inner class name */
    public static final class C0075b {

        /* renamed from: a  reason: collision with root package name */
        private final IsoDep f9648a;

        public C0075b(IsoDep isoDep) {
            this.f9648a = isoDep;
        }

        public static String c(byte[] bArr) {
            String str = "";
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str = str + hexString.toUpperCase() + " ";
            }
            return str;
        }

        public final a a(byte... bArr) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
            allocate.put((byte) 0).put(ScriptToolsConst.TagName.CommandMultiple).put((byte) 4).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
            Log.e("PBOC Transceive", c(allocate.array()));
            a aVar = new a(b(allocate.array()));
            Log.e("PBOC receive", c(aVar.a()));
            return aVar;
        }

        public final void a() {
            try {
                this.f9648a.connect();
            } catch (Exception unused) {
            }
        }

        public final byte[] b(byte[] bArr) {
            try {
                return this.f9648a.transceive(bArr);
            } catch (Exception unused) {
                return a.d;
            }
        }
    }

    protected b(byte[] bArr) {
        this.b = bArr == null ? f9647a : bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public String toString() {
        return c.a(this.b, this.b.length);
    }
}
