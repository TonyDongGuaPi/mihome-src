package com.xiaomi.mimc.cipher;

import com.xiaomi.smarthome.core.entity.Error;
import java.io.PrintStream;

public class RC4 {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f11162a = new byte[256];
    private int b = 0;
    private int c = 0;
    private int d = Error.f;

    public static int a(byte b2) {
        return b2 >= 0 ? b2 : b2 + 256;
    }

    private void a(int i, byte[] bArr, boolean z) {
        int length = bArr.length;
        for (int i2 = 0; i2 < 256; i2++) {
            this.f11162a[i2] = (byte) i2;
        }
        this.c = 0;
        this.b = 0;
        while (this.b < i) {
            this.c = ((this.c + a(this.f11162a[this.b])) + a(bArr[this.b % length])) % 256;
            a(this.f11162a, this.b, this.c);
            this.b++;
        }
        if (i != 256) {
            this.d = ((this.c + a(this.f11162a[i])) + a(bArr[i % length])) % 256;
        }
        if (z) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i3 = i - 1;
            sb.append(i3);
            sb.append(":");
            printStream.print(sb.toString());
            for (int i4 = 0; i4 <= i; i4++) {
                System.out.print(" " + a(this.f11162a[i4]));
            }
            System.out.print("   j_" + i3 + "=" + this.c);
            System.out.print("   j_" + i + "=" + this.d);
            System.out.print("   S_" + i3 + "[j_" + i3 + "]=" + a(this.f11162a[this.c]));
            System.out.print("   S_" + i3 + "[j_" + i + "]=" + a(this.f11162a[this.d]));
            if (this.f11162a[1] != 0) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    private void a(byte[] bArr) {
        a(256, bArr, false);
    }

    private void b() {
        this.c = 0;
        this.b = 0;
    }

    /* access modifiers changed from: package-private */
    public byte a() {
        this.b = (this.b + 1) % 256;
        this.c = (this.c + a(this.f11162a[this.b])) % 256;
        a(this.f11162a, this.b, this.c);
        return this.f11162a[(a(this.f11162a[this.b]) + a(this.f11162a[this.c])) % 256];
    }

    private static void a(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b2;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        RC4 rc4 = new RC4();
        rc4.a(bArr);
        rc4.b();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ rc4.a());
        }
        return bArr3;
    }

    public static byte[] a(String str, String str2) {
        byte[] a2 = Base64.a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[(a2.length + 1 + bytes.length)];
        for (int i = 0; i < a2.length; i++) {
            bArr[i] = a2[i];
        }
        bArr[a2.length] = 95;
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr[a2.length + 1 + i2] = bytes[i2];
        }
        return bArr;
    }
}
