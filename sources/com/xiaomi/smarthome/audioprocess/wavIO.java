package com.xiaomi.smarthome.audioprocess;

import com.drew.metadata.wav.WavDirectory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class wavIO {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f13755a;
    private String b;
    private long c;
    private long d;
    private int e;
    private long f;
    private long g;
    private long h;
    private int i;
    private int j;
    private long k;

    public wavIO() {
        this.b = "";
    }

    public wavIO(String str) {
        this.b = str;
    }

    public static int a(byte[] bArr) {
        return ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    public static long b(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            bArr2[i2] = bArr[i3];
            i2++;
        }
        long j2 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 32; i5 += 8) {
            j2 |= ((long) (bArr2[i4] & 255)) << i5;
            i4++;
        }
        return j2;
    }

    private static byte[] a(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)};
    }

    public static byte[] a(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >>> 8) & 255)};
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean b() {
        this.f13755a = null;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[2];
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(this.b));
            "" + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte());
            dataInputStream.read(bArr);
            this.c = b(bArr);
            "" + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte());
            "" + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte());
            dataInputStream.read(bArr);
            this.d = b(bArr);
            dataInputStream.read(bArr2);
            this.e = a(bArr2);
            dataInputStream.read(bArr2);
            this.f = (long) a(bArr2);
            dataInputStream.read(bArr);
            this.g = b(bArr);
            dataInputStream.read(bArr);
            this.h = b(bArr);
            dataInputStream.read(bArr2);
            this.i = a(bArr2);
            dataInputStream.read(bArr2);
            this.j = a(bArr2);
            "" + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte()) + ((char) dataInputStream.readByte());
            dataInputStream.read(bArr);
            this.k = b(bArr);
            this.f13755a = new byte[((int) this.k)];
            dataInputStream.read(this.f13755a);
            dataInputStream.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean c() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(this.b));
            dataOutputStream.writeBytes("RIFF");
            dataOutputStream.write(a((int) this.c), 0, 4);
            dataOutputStream.writeBytes(WavDirectory.x);
            dataOutputStream.writeBytes(WavDirectory.u);
            dataOutputStream.write(a((int) this.d), 0, 4);
            dataOutputStream.write(a((short) this.e), 0, 2);
            dataOutputStream.write(a((short) ((int) this.f)), 0, 2);
            dataOutputStream.write(a((int) this.g), 0, 4);
            dataOutputStream.write(a((int) this.h), 0, 4);
            dataOutputStream.write(a((short) this.i), 0, 2);
            dataOutputStream.write(a((short) this.j), 0, 2);
            dataOutputStream.writeBytes("data");
            dataOutputStream.write(a((int) this.k), 0, 4);
            dataOutputStream.write(this.f13755a);
            return true;
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            return false;
        }
    }

    public String d() {
        return "<html>Format: " + this.e + "<br>" + "Channels: " + this.f + "<br>" + "SampleRate: " + this.g + "<br>" + "ByteRate: " + this.h + "<br>" + "BlockAlign: " + this.i + "<br>" + "BitsPerSample: " + this.j + "<br>" + "DataSize: " + this.k + "</html>";
    }
}
