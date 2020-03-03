package org.apache.commons.compress.compressors.gzip;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.compressors.CompressorInputStream;

public class GzipCompressorInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f3322a = (!GzipCompressorInputStream.class.desiredAssertionStatus());
    private static final int b = 2;
    private static final int c = 4;
    private static final int d = 8;
    private static final int e = 16;
    private static final int f = 224;
    private final InputStream g;
    private final boolean h;
    private final byte[] i;
    private int j;
    private Inflater k;
    private final CRC32 l;
    private boolean m;
    private final byte[] n;
    private final GzipParameters o;

    public GzipCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public GzipCompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.i = new byte[8192];
        this.j = 0;
        this.k = new Inflater(true);
        this.l = new CRC32();
        this.m = false;
        this.n = new byte[1];
        this.o = new GzipParameters();
        if (inputStream.markSupported()) {
            this.g = inputStream;
        } else {
            this.g = new BufferedInputStream(inputStream);
        }
        this.h = z;
        a(true);
    }

    public GzipParameters a() {
        return this.o;
    }

    private boolean a(boolean z) throws IOException {
        if (f3322a || z || this.h) {
            int read = this.g.read();
            int read2 = this.g.read();
            if (read == -1 && !z) {
                return false;
            }
            if (read == 31 && read2 == 139) {
                DataInputStream dataInputStream = new DataInputStream(this.g);
                int readUnsignedByte = dataInputStream.readUnsignedByte();
                if (readUnsignedByte == 8) {
                    int readUnsignedByte2 = dataInputStream.readUnsignedByte();
                    if ((readUnsignedByte2 & 224) == 0) {
                        this.o.a(b(dataInputStream) * 1000);
                        int readUnsignedByte3 = dataInputStream.readUnsignedByte();
                        if (readUnsignedByte3 == 2) {
                            this.o.a(9);
                        } else if (readUnsignedByte3 == 4) {
                            this.o.a(1);
                        }
                        this.o.b(dataInputStream.readUnsignedByte());
                        if ((readUnsignedByte2 & 4) != 0) {
                            int readUnsignedByte4 = (dataInputStream.readUnsignedByte() << 8) | dataInputStream.readUnsignedByte();
                            while (true) {
                                int i2 = readUnsignedByte4 - 1;
                                if (readUnsignedByte4 <= 0) {
                                    break;
                                }
                                dataInputStream.readUnsignedByte();
                                readUnsignedByte4 = i2;
                            }
                        }
                        if ((readUnsignedByte2 & 8) != 0) {
                            this.o.a(new String(a(dataInputStream), "ISO-8859-1"));
                        }
                        if ((readUnsignedByte2 & 16) != 0) {
                            this.o.b(new String(a(dataInputStream), "ISO-8859-1"));
                        }
                        if ((readUnsignedByte2 & 2) != 0) {
                            dataInputStream.readShort();
                        }
                        this.k.reset();
                        this.l.reset();
                        return true;
                    }
                    throw new IOException("Reserved flags are set in the .gz header");
                }
                throw new IOException("Unsupported compression method " + readUnsignedByte + " in the .gz header");
            }
            throw new IOException(z ? "Input is not in the .gz format" : "Garbage after a valid .gz stream");
        }
        throw new AssertionError();
    }

    private byte[] a(DataInputStream dataInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            if (readUnsignedByte == 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(readUnsignedByte);
        }
    }

    private long b(DataInputStream dataInputStream) throws IOException {
        return ((long) (dataInputStream.readUnsignedByte() | (dataInputStream.readUnsignedByte() << 8) | (dataInputStream.readUnsignedByte() << 16))) | (((long) dataInputStream.readUnsignedByte()) << 24);
    }

    public int read() throws IOException {
        if (read(this.n, 0, 1) == -1) {
            return -1;
        }
        return this.n[0] & 255;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.m) {
            return -1;
        }
        int i4 = i2;
        int i5 = 0;
        while (i3 > 0) {
            if (this.k.needsInput()) {
                this.g.mark(this.i.length);
                this.j = this.g.read(this.i);
                if (this.j != -1) {
                    this.k.setInput(this.i, 0, this.j);
                } else {
                    throw new EOFException();
                }
            }
            try {
                int inflate = this.k.inflate(bArr, i4, i3);
                this.l.update(bArr, i4, inflate);
                i4 += inflate;
                i3 -= inflate;
                i5 += inflate;
                a(inflate);
                if (this.k.finished()) {
                    this.g.reset();
                    long remaining = (long) (this.j - this.k.getRemaining());
                    if (this.g.skip(remaining) == remaining) {
                        this.j = 0;
                        DataInputStream dataInputStream = new DataInputStream(this.g);
                        if (b(dataInputStream) != this.l.getValue()) {
                            throw new IOException("Gzip-compressed data is corrupt (CRC32 error)");
                        } else if (b(dataInputStream) != (this.k.getBytesWritten() & MessageHead.SERIAL_MAK)) {
                            throw new IOException("Gzip-compressed data is corrupt(uncompressed size mismatch)");
                        } else if (!this.h || !a(false)) {
                            this.k.end();
                            this.k = null;
                            this.m = true;
                            if (i5 == 0) {
                                return -1;
                            }
                            return i5;
                        }
                    } else {
                        throw new IOException();
                    }
                }
            } catch (DataFormatException unused) {
                throw new IOException("Gzip-compressed data is corrupt");
            }
        }
        return i5;
    }

    public static boolean a(byte[] bArr, int i2) {
        return i2 >= 2 && bArr[0] == 31 && bArr[1] == -117;
    }

    public void close() throws IOException {
        if (this.k != null) {
            this.k.end();
            this.k = null;
        }
        if (this.g != System.in) {
            this.g.close();
        }
    }
}
