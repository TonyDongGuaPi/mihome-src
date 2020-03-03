package com.ximalaya.ting.android.player.liveflv;

import cn.com.fmsh.communication.core.MessageHead;
import com.ximalaya.ting.android.player.Logger;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.UnixStat;

public class FlvAacParser {

    /* renamed from: a  reason: collision with root package name */
    private DataInputStream f2301a = null;
    private int b;
    private int c;
    private int d;
    private int e = 9;
    private int f = 4;
    private int g = 11;
    private int h = 7;
    private int i = 2;
    private int j = 8;
    private byte k;
    private byte l;
    private String m;

    private int a(int i2, int i3) {
        return i2 >> (32 - i3);
    }

    public long a(long j2, int i2, int i3) {
        return (j2 << i2) | (((long) i3) & (MessageHead.SERIAL_MAK >> (32 - i2)));
    }

    public FlvAacParser(InputStream inputStream) throws IOException {
        boolean z = true;
        this.k = 1;
        this.l = 4;
        this.m = "FLVPARSER";
        this.f2301a = new DataInputStream(inputStream);
        if (((char) this.f2301a.readByte()) == 'F' && ((char) this.f2301a.readByte()) == 'L' && ((char) this.f2301a.readByte()) == 'V') {
            this.f2301a.readUnsignedByte();
            int readUnsignedByte = this.f2301a.readUnsignedByte();
            boolean z2 = (this.k & readUnsignedByte) != 0;
            z = (this.l & readUnsignedByte) == 0 ? false : z;
            String str = this.m;
            Logger.b(str, "flv typeFlags=" + readUnsignedByte + " hasVideo=" + z2 + " hasAudio=" + z);
            if (z) {
                int readInt = this.f2301a.readInt();
                String str2 = this.m;
                Logger.b(str2, "Audio Stream len:" + readInt);
                if (readInt != this.e) {
                    throw new IOException("Unpexpected FLV header length: " + readInt);
                }
                return;
            }
            throw new IOException("No Audio Stream");
        }
        throw new IOException("The file is not a FLV file.");
    }

    public int a(byte[] bArr, int i2) throws IOException {
        int readInt = this.f2301a.readInt();
        String str = this.m;
        Logger.b(str, "previousTagSize=" + readInt + " previousReadBytes=" + i2 + "   result = " + ((i2 - this.h) + this.i + this.g));
        if (i2 == 0 || readInt == (i2 - this.h) + this.i + this.g) {
            int readUnsignedByte = this.f2301a.readUnsignedByte();
            String str2 = this.m;
            Logger.b(str2, "tagType0=" + readUnsignedByte);
            while (readUnsignedByte != this.j) {
                int b2 = b();
                String str3 = this.m;
                Logger.b(str3, "next data size =" + b2);
                this.f2301a.skipBytes(b2 + this.g);
                readUnsignedByte = this.f2301a.readUnsignedByte();
                String str4 = this.m;
                Logger.b(str4, "tagType=" + readUnsignedByte);
            }
            int b3 = b();
            int readInt2 = this.f2301a.readInt();
            int b4 = b();
            String str5 = this.m;
            Logger.b(str5, "previousTagSize=" + readInt + " dataSize = " + b3 + ", timestamps = " + readInt2 + ", streamId = " + b4);
            if (b3 == 0) {
                return a(bArr, 0);
            }
            int b5 = b(bArr, b3);
            return b5 == 0 ? a(bArr, 0) : b5;
        }
        throw new IOException("previousTagSize not equal previousReadBytes");
    }

    private boolean a(int i2) throws IOException {
        int readUnsignedByte = this.f2301a.readUnsignedByte();
        int readUnsignedByte2 = this.f2301a.readUnsignedByte();
        String str = this.m;
        Logger.b(str, "audioHeader=" + readUnsignedByte + " head=" + readUnsignedByte2);
        if (readUnsignedByte2 != 0) {
            return false;
        }
        int readByte = (((this.f2301a.readByte() & 255) * 256) + (this.f2301a.readByte() & 255)) << 16;
        if (i2 > 4) {
            this.f2301a.skipBytes(i2 - 4);
        }
        this.b = a(readByte, 5);
        int i3 = readByte << 5;
        this.c = a(i3, 4);
        this.d = a(i3 << 4, 4);
        String str2 = this.m;
        Logger.b(str2, "aacProf=" + this.b + " sampleRateIndex=" + this.c + " channelConfig=" + this.d);
        if (this.b < 0 || this.b > 3) {
            throw new IOException("Unsupported AAC profile.");
        } else if (this.c > 12) {
            throw new IOException("Invalid AAC sample rate index.");
        } else if (this.d <= 6) {
            return true;
        } else {
            throw new IOException("Invalid AAC channel configuration.");
        }
    }

    private int b(byte[] bArr, int i2) throws IOException {
        if (a(i2)) {
            return 0;
        }
        int i3 = i2 - this.i;
        long a2 = a(a(a(0, 12, UnixStat.f3272a), 3, 0), 1, 1);
        bArr[0] = (byte) ((int) (a2 >> 8));
        bArr[1] = (byte) ((int) a2);
        int i4 = i3 + 7;
        long a3 = a(a(a(a(a(a(0, 2, this.b - 1), 4, this.c), 1, 0), 3, this.d), 4, 0), 2, i4 & 6144);
        bArr[2] = (byte) ((int) (a3 >> 8));
        bArr[3] = (byte) ((int) a3);
        long a4 = a(a(a(0, 11, i4 & 2047), 11, 2047), 2, 0);
        bArr[4] = (byte) ((int) (a4 >> 16));
        bArr[5] = (byte) ((int) (a4 >> 8));
        bArr[6] = (byte) ((int) a4);
        this.f2301a.readFully(bArr, this.h, i3);
        return i3 + this.h;
    }

    private int b() throws IOException {
        return (this.f2301a.read() << 16) + (this.f2301a.read() << 8) + this.f2301a.read();
    }

    protected static void a(URLConnection uRLConnection) {
        for (Map.Entry next : uRLConnection.getHeaderFields().entrySet()) {
            for (String str : (List) next.getValue()) {
                PrintStream printStream = System.out;
                printStream.println("header: key=" + ((String) next.getKey()) + ", val=" + str);
            }
        }
    }

    public void a() throws IOException {
        if (this.f2301a != null) {
            this.f2301a.close();
        }
    }

    public static void a(String[] strArr) {
        try {
            FlvAacParser flvAacParser = new FlvAacParser(new FileInputStream("E:\\12990-6423.flv"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("E:\\output3.aac"));
            byte[] bArr = new byte[4096];
            int i2 = 0;
            for (int i3 = 0; i3 < 20000; i3++) {
                i2 = flvAacParser.a(bArr, i2);
                PrintStream printStream = System.out;
                printStream.println("bytesRead = " + i2);
                fileOutputStream.write(bArr, 0, i2);
            }
            fileOutputStream.close();
            flvAacParser.a();
        } catch (Exception e2) {
            System.out.println(e2);
            e2.printStackTrace();
        }
    }
}
