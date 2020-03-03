package com.ximalaya.ting.android.player;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class LittleEndianDataInputStream extends FilterInputStream implements DataInput {
    public static int a(byte b, byte b2, byte b3, byte b4) {
        return (b << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    public static long a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((((long) b2) & 255) << 48) | ((((long) b) & 255) << 56) | ((((long) b3) & 255) << 40) | ((((long) b4) & 255) << 32) | ((((long) b5) & 255) << 24) | ((((long) b6) & 255) << 16) | ((((long) b7) & 255) << 8) | (((long) b8) & 255);
    }

    public LittleEndianDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    public void readFully(byte[] bArr) throws IOException {
        a(this.in, bArr, 0, bArr.length);
    }

    public void a(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int b = b(inputStream, bArr, i, i2);
        if (b != i2) {
            throw new EOFException("reached end of stream after reading " + b + " bytes; " + i2 + " bytes expected");
        }
    }

    public int b(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i + i3, i2 - i3);
                if (read == -1) {
                    break;
                }
                i3 += read;
            }
            return i3;
        }
        throw new IndexOutOfBoundsException("len is negative");
    }

    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        a((InputStream) this, bArr, i, i2);
    }

    public int skipBytes(int i) throws IOException {
        return (int) this.in.skip((long) i);
    }

    public int readUnsignedByte() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    public int readUnsignedShort() throws IOException {
        return a((byte) 0, (byte) 0, a(), a());
    }

    public int readInt() throws IOException {
        byte a2 = a();
        byte a3 = a();
        return a(a(), a(), a3, a2);
    }

    public long readLong() throws IOException {
        byte a2 = a();
        byte a3 = a();
        byte a4 = a();
        byte a5 = a();
        byte a6 = a();
        byte a7 = a();
        return a(a(), a(), a7, a6, a5, a4, a3, a2);
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public String readUTF() throws IOException {
        return new DataInputStream(this.in).readUTF();
    }

    public short readShort() throws IOException {
        return (short) readUnsignedShort();
    }

    public char readChar() throws IOException {
        return (char) readUnsignedShort();
    }

    public byte readByte() throws IOException {
        return (byte) readUnsignedByte();
    }

    public boolean readBoolean() throws IOException {
        return readUnsignedByte() != 0;
    }

    private byte a() throws IOException, EOFException {
        int read = this.in.read();
        if (-1 != read) {
            return (byte) read;
        }
        throw new EOFException();
    }
}
