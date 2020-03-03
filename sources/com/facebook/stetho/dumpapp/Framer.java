package com.facebook.stetho.dumpapp;

import com.facebook.stetho.common.LogUtil;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

class Framer {
    public static final byte ENTER_FRAME_PREFIX = 33;
    public static final byte EXIT_FRAME_PREFIX = 120;
    public static final byte STDERR_FRAME_PREFIX = 50;
    public static final byte STDIN_FRAME_PREFIX = 45;
    public static final byte STDIN_REQUEST_FRAME_PREFIX = 95;
    public static final byte STDOUT_FRAME_PREFIX = 49;
    private static final String TAG = "FramingSocket";
    /* access modifiers changed from: private */
    public final DataInputStream mInput;
    /* access modifiers changed from: private */
    public final DataOutputStream mMultiplexedOutputStream;
    private final PrintStream mStderr = new PrintStream(new FramingOutputStream((byte) 50));
    private final InputStream mStdin = new FramingInputStream();
    private final PrintStream mStdout = new PrintStream(new BufferedOutputStream(new FramingOutputStream((byte) 49)));

    public Framer(InputStream inputStream, OutputStream outputStream) throws IOException {
        this.mInput = new DataInputStream(inputStream);
        this.mMultiplexedOutputStream = new DataOutputStream(outputStream);
    }

    public InputStream getStdin() {
        return this.mStdin;
    }

    public PrintStream getStdout() {
        return this.mStdout;
    }

    public PrintStream getStderr() {
        return this.mStderr;
    }

    public byte readFrameType() throws IOException {
        return this.mInput.readByte();
    }

    public int readInt() throws IOException {
        return this.mInput.readInt();
    }

    public String readString() throws IOException {
        byte[] bArr = new byte[this.mInput.readUnsignedShort()];
        this.mInput.readFully(bArr);
        return new String(bArr, Charset.forName("UTF-8"));
    }

    public void writeExitCode(int i) throws IOException {
        this.mStdout.flush();
        this.mStderr.flush();
        writeIntFrame((byte) 120, i);
    }

    public void writeIntFrame(byte b, int i) throws IOException {
        this.mMultiplexedOutputStream.write(b);
        this.mMultiplexedOutputStream.writeInt(i);
    }

    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this.mMultiplexedOutputStream.write(bArr, i, i2);
    }

    private static <T extends Throwable> T handleSuppression(@Nullable T t, T t2) {
        if (t == null) {
            return t2;
        }
        LogUtil.i(TAG, t2, "Suppressed while handling " + t);
        return t;
    }

    private class FramingInputStream extends InputStream {
        private final ClosedHelper mClosedHelper;

        private FramingInputStream() {
            this.mClosedHelper = new ClosedHelper();
        }

        public int read() throws IOException {
            byte[] bArr = new byte[1];
            if (read(bArr) == 0) {
                return -1;
            }
            return bArr[0];
        }

        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int readInt;
            this.mClosedHelper.throwIfClosed();
            synchronized (Framer.this) {
                Framer.this.writeIntFrame((byte) 95, i2);
                byte readFrameType = Framer.this.readFrameType();
                if (readFrameType == 45) {
                    readInt = Framer.this.readInt();
                    if (readInt > 0) {
                        if (readInt <= i2) {
                            Framer.this.mInput.readFully(bArr, i, readInt);
                        } else {
                            throw new DumpappFramingException("Expected at most " + i2 + " bytes, got: " + readInt);
                        }
                    }
                } else {
                    throw new UnexpectedFrameException((byte) 45, readFrameType);
                }
            }
            return readInt;
        }

        public long skip(long j) throws IOException {
            long j2;
            byte[] bArr = new byte[((int) Math.min(j, 2048))];
            synchronized (Framer.this) {
                j2 = 0;
                while (true) {
                    if (j2 >= j) {
                        break;
                    }
                    try {
                        int read = read(bArr);
                        if (read < 0) {
                            break;
                        }
                        j2 += (long) read;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return j2;
        }

        public void close() throws IOException {
            this.mClosedHelper.close();
        }
    }

    private class FramingOutputStream extends OutputStream {
        private final ClosedHelper mClosedHelper = new ClosedHelper();
        private final byte mPrefix;

        public FramingOutputStream(byte b) {
            this.mPrefix = b;
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.mClosedHelper.throwIfClosed();
            if (i2 > 0) {
                try {
                    synchronized (Framer.this) {
                        Framer.this.writeIntFrame(this.mPrefix, i2);
                        Framer.this.writeBlob(bArr, i, i2);
                        Framer.this.mMultiplexedOutputStream.flush();
                    }
                } catch (IOException e) {
                    throw new DumpappOutputBrokenException((Throwable) e);
                }
            }
        }

        public void write(int i) throws IOException {
            byte[] bArr = {(byte) i};
            write(bArr, 0, bArr.length);
        }

        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        public void close() throws IOException {
            this.mClosedHelper.close();
        }
    }

    private static class ClosedHelper {
        private volatile boolean mClosed;

        private ClosedHelper() {
        }

        public void throwIfClosed() throws IOException {
            if (this.mClosed) {
                throw new IOException("Stream is closed");
            }
        }

        public void close() {
            this.mClosed = true;
        }
    }
}