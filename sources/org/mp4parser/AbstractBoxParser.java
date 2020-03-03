package org.mp4parser;

import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;
import org.mp4parser.tools.IsoTypeReader;

public abstract class AbstractBoxParser implements BoxParser {
    private static final int b = 524288000;
    private static Logger c = Logger.getLogger(AbstractBoxParser.class.getName());

    /* renamed from: a  reason: collision with root package name */
    ThreadLocal<ByteBuffer> f3730a = new ThreadLocal<ByteBuffer>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ByteBuffer initialValue() {
            return ByteBuffer.allocate(32);
        }
    };

    public abstract ParsableBox a(String str, byte[] bArr, String str2);

    public ParsableBox a(ReadableByteChannel readableByteChannel, String str) throws IOException {
        int read;
        long j;
        long j2;
        byte[] bArr;
        long j3;
        ReadableByteChannel readableByteChannel2 = readableByteChannel;
        this.f3730a.get().rewind().limit(8);
        int i = 0;
        do {
            read = readableByteChannel2.read(this.f3730a.get());
            i += read;
            if (i >= 8) {
                this.f3730a.get().rewind();
                long b2 = IsoTypeReader.b(this.f3730a.get());
                if (b2 >= 8 || b2 <= 1) {
                    String m = IsoTypeReader.m(this.f3730a.get());
                    if (b2 == 1) {
                        this.f3730a.get().limit(16);
                        readableByteChannel2.read(this.f3730a.get());
                        this.f3730a.get().position(8);
                        j = IsoTypeReader.h(this.f3730a.get()) - 16;
                    } else {
                        if (b2 != 0) {
                            j3 = b2 - 8;
                        } else if (readableByteChannel2 instanceof FileChannel) {
                            FileChannel fileChannel = (FileChannel) readableByteChannel2;
                            j3 = fileChannel.size() - fileChannel.position();
                        } else {
                            j = 3;
                        }
                        j = j3;
                    }
                    if ("uuid".equals(m)) {
                        this.f3730a.get().limit(this.f3730a.get().limit() + 16);
                        readableByteChannel2.read(this.f3730a.get());
                        bArr = new byte[16];
                        for (int position = this.f3730a.get().position() - 16; position < this.f3730a.get().position(); position++) {
                            bArr[position - (this.f3730a.get().position() - 16)] = this.f3730a.get().get(position);
                        }
                        j2 = j - 16;
                    } else {
                        j2 = j;
                        bArr = null;
                    }
                    ParsableBox a2 = a(m, bArr, str);
                    this.f3730a.get().rewind();
                    if (j2 > 524288000) {
                        SDKLog.e(Tag.i, "OOM size " + j2);
                        return null;
                    }
                    a2.a(readableByteChannel, this.f3730a.get(), j2, this);
                    return a2;
                }
                c.severe("Plausibility check failed: size < 8 (size = " + b2 + "). Stop parsing!");
                return null;
            }
        } while (read >= 0);
        throw new EOFException();
    }
}
