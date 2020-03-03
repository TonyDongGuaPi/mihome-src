package no.nordicsemi.android.dfu.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;

public class HexInputStream extends FilterInputStream {
    private final int LINE_LENGTH = 128;
    private final int MBRSize;
    private int available;
    private int bytesRead;
    private int lastAddress = 0;
    private final byte[] localBuf = new byte[128];
    private int localPos = 128;
    private int pos;
    private int size = this.localBuf.length;

    private int asciiToInt(int i) {
        if (i >= 65) {
            return i - 55;
        }
        if (i >= 48) {
            return i - 48;
        }
        return -1;
    }

    public HexInputStream(InputStream inputStream, int i) throws HexFileValidationException, IOException {
        super(new BufferedInputStream(inputStream));
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    public HexInputStream(byte[] bArr, int i) throws HexFileValidationException, IOException {
        super(new ByteArrayInputStream(bArr));
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        skip(r0, (long) ((r1 * 2) + 2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int calculateBinSize(int r10) throws java.io.IOException {
        /*
            r9 = this;
            java.io.InputStream r0 = r9.in
            int r1 = r0.available()
            r0.mark(r1)
            int r1 = r0.read()     // Catch:{ all -> 0x0073 }
            r2 = 0
            r3 = 0
        L_0x000f:
            r9.checkComma(r1)     // Catch:{ all -> 0x0073 }
            int r1 = r9.readByte(r0)     // Catch:{ all -> 0x0073 }
            int r4 = r9.readAddress(r0)     // Catch:{ all -> 0x0073 }
            int r5 = r9.readByte(r0)     // Catch:{ all -> 0x0073 }
            r6 = 2
            r8 = 4
            if (r5 == r8) goto L_0x0050
            switch(r5) {
                case 0: goto L_0x0043;
                case 1: goto L_0x003f;
                case 2: goto L_0x0027;
                default: goto L_0x0026;
            }     // Catch:{ all -> 0x0073 }
        L_0x0026:
            goto L_0x0047
        L_0x0027:
            int r1 = r9.readAddress(r0)     // Catch:{ all -> 0x0073 }
            int r1 = r1 << r8
            if (r3 <= 0) goto L_0x003a
            int r4 = r1 >> 16
            int r2 = r2 >> 16
            int r2 = r2 + 1
            if (r4 == r2) goto L_0x003a
            r0.reset()
            return r3
        L_0x003a:
            r9.skip(r0, r6)     // Catch:{ all -> 0x0073 }
        L_0x003d:
            r2 = r1
            goto L_0x0066
        L_0x003f:
            r0.reset()
            return r3
        L_0x0043:
            int r4 = r4 + r2
            if (r4 < r10) goto L_0x0047
            int r3 = r3 + r1
        L_0x0047:
            int r1 = r1 * 2
            int r1 = r1 + 2
            long r4 = (long) r1
            r9.skip(r0, r4)     // Catch:{ all -> 0x0073 }
            goto L_0x0066
        L_0x0050:
            int r1 = r9.readAddress(r0)     // Catch:{ all -> 0x0073 }
            if (r3 <= 0) goto L_0x0060
            int r2 = r2 >> 16
            int r2 = r2 + 1
            if (r1 == r2) goto L_0x0060
            r0.reset()
            return r3
        L_0x0060:
            int r1 = r1 << 16
            r9.skip(r0, r6)     // Catch:{ all -> 0x0073 }
            goto L_0x003d
        L_0x0066:
            int r1 = r0.read()     // Catch:{ all -> 0x0073 }
            r4 = 10
            if (r1 == r4) goto L_0x0066
            r4 = 13
            if (r1 == r4) goto L_0x0066
            goto L_0x000f
        L_0x0073:
            r10 = move-exception
            r0.reset()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.internal.HexInputStream.calculateBinSize(int):int");
    }

    public int available() {
        return this.available - this.bytesRead;
    }

    public int readPacket(byte[] bArr) throws HexFileValidationException, IOException {
        int i = 0;
        while (i < bArr.length) {
            if (this.localPos < this.size) {
                byte[] bArr2 = this.localBuf;
                int i2 = this.localPos;
                this.localPos = i2 + 1;
                bArr[i] = bArr2[i2];
                i++;
            } else {
                int i3 = this.bytesRead;
                int readLine = readLine();
                this.size = readLine;
                this.bytesRead = i3 + readLine;
                if (this.size == 0) {
                    break;
                }
            }
        }
        return i;
    }

    public int read() throws IOException {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }

    public int read(byte[] bArr) throws IOException {
        return readPacket(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }

    public int sizeInBytes() {
        return this.available;
    }

    public int sizeInPackets(int i) throws IOException {
        int sizeInBytes = sizeInBytes();
        return (sizeInBytes / i) + (sizeInBytes % i > 0 ? 1 : 0);
    }

    private int readLine() throws IOException {
        if (this.pos == -1) {
            return 0;
        }
        InputStream inputStream = this.in;
        while (true) {
            int read = inputStream.read();
            this.pos++;
            if (!(read == 10 || read == 13)) {
                checkComma(read);
                int readByte = readByte(inputStream);
                this.pos += 2;
                int readAddress = readAddress(inputStream);
                this.pos += 4;
                int readByte2 = readByte(inputStream);
                this.pos += 2;
                if (readByte2 != 4) {
                    switch (readByte2) {
                        case 0:
                            if (this.lastAddress + readAddress < this.MBRSize) {
                                this.pos = (int) (((long) this.pos) + skip(inputStream, (long) ((readByte * 2) + 2)));
                                readByte2 = -1;
                                break;
                            }
                            break;
                        case 1:
                            this.pos = -1;
                            return 0;
                        case 2:
                            int readAddress2 = readAddress(inputStream) << 4;
                            this.pos += 4;
                            if (this.bytesRead <= 0 || (readAddress2 >> 16) == (this.lastAddress >> 16) + 1) {
                                this.lastAddress = readAddress2;
                                this.pos = (int) (((long) this.pos) + skip(inputStream, 2));
                                break;
                            } else {
                                return 0;
                            }
                            break;
                        default:
                            this.pos = (int) (((long) this.pos) + skip(inputStream, (long) ((readByte * 2) + 2)));
                            break;
                    }
                } else {
                    int readAddress3 = readAddress(inputStream);
                    this.pos += 4;
                    if (this.bytesRead > 0 && readAddress3 != (this.lastAddress >> 16) + 1) {
                        return 0;
                    }
                    this.lastAddress = readAddress3 << 16;
                    this.pos = (int) (((long) this.pos) + skip(inputStream, 2));
                }
                if (readByte2 == 0) {
                    int i = 0;
                    while (i < this.localBuf.length && i < readByte) {
                        int readByte3 = readByte(inputStream);
                        this.pos += 2;
                        this.localBuf[i] = (byte) readByte3;
                        i++;
                    }
                    this.pos = (int) (((long) this.pos) + skip(inputStream, 2));
                    this.localPos = 0;
                    return readByte;
                }
            }
        }
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.pos = 0;
        this.bytesRead = 0;
        this.localPos = 0;
    }

    private void checkComma(int i) throws HexFileValidationException {
        if (i != 58) {
            throw new HexFileValidationException("Not a HEX file");
        }
    }

    private long skip(InputStream inputStream, long j) throws IOException {
        long skip = inputStream.skip(j);
        return skip < j ? skip + inputStream.skip(j - skip) : skip;
    }

    private int readByte(InputStream inputStream) throws IOException {
        return asciiToInt(inputStream.read()) | (asciiToInt(inputStream.read()) << 4);
    }

    private int readAddress(InputStream inputStream) throws IOException {
        return readByte(inputStream) | (readByte(inputStream) << 8);
    }
}
