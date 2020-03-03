package org.mp4parser.boxes.iso23001.part7;

import com.taobao.weex.el.parse.Operators;
import java.math.BigInteger;
import java.util.Arrays;
import org.mp4parser.tools.Hex;

public class CencSampleAuxiliaryDataFormat {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f3934a = new byte[0];
    public Pair[] b = null;

    public interface Pair {
        int a();

        long b();
    }

    public int a() {
        int length = this.f3934a.length;
        return (this.b == null || this.b.length <= 0) ? length : length + 2 + (this.b.length * 6);
    }

    public Pair a(int i, long j) {
        if (i <= 127) {
            if (j <= 127) {
                return new ByteBytePair(i, j);
            }
            if (j <= 32767) {
                return new ByteShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new ByteIntPair(i, j);
            }
            return new ByteLongPair(i, j);
        } else if (i <= 32767) {
            if (j <= 127) {
                return new ShortBytePair(i, j);
            }
            if (j <= 32767) {
                return new ShortShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new ShortIntPair(i, j);
            }
            return new ShortLongPair(i, j);
        } else if (j <= 127) {
            return new IntBytePair(i, j);
        } else {
            if (j <= 32767) {
                return new IntShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new IntIntPair(i, j);
            }
            return new IntLongPair(i, j);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = (CencSampleAuxiliaryDataFormat) obj;
        if (!new BigInteger(this.f3934a).equals(new BigInteger(cencSampleAuxiliaryDataFormat.f3934a))) {
            return false;
        }
        return this.b == null ? cencSampleAuxiliaryDataFormat.b == null : Arrays.equals(this.b, cencSampleAuxiliaryDataFormat.b);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f3934a != null ? Arrays.hashCode(this.f3934a) : 0) * 31;
        if (this.b != null) {
            i = Arrays.hashCode(this.b);
        }
        return hashCode + i;
    }

    public String toString() {
        return "Entry{iv=" + Hex.a(this.f3934a) + ", pairs=" + Arrays.toString(this.b) + Operators.BLOCK_END;
    }

    private class ByteBytePair extends AbstractPair {
        private byte c;
        private byte d;

        public ByteBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (byte) i;
            this.d = (byte) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ByteShortPair extends AbstractPair {
        private byte c;
        private short d;

        public ByteShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (byte) i;
            this.d = (short) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ByteIntPair extends AbstractPair {
        private byte c;
        private int d;

        public ByteIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (byte) i;
            this.d = (int) j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ByteLongPair extends AbstractPair {
        private byte c;
        private long d;

        public ByteLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (byte) i;
            this.d = j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    private class ShortBytePair extends AbstractPair {
        private short c;
        private byte d;

        public ShortBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (short) i;
            this.d = (byte) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ShortShortPair extends AbstractPair {
        private short c;
        private short d;

        public ShortShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (short) i;
            this.d = (short) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ShortIntPair extends AbstractPair {
        private short c;
        private int d;

        public ShortIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (short) i;
            this.d = (int) j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class ShortLongPair extends AbstractPair {
        private short c;
        private long d;

        public ShortLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = (short) i;
            this.d = j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    private class IntBytePair extends AbstractPair {
        private int c;
        private byte d;

        public IntBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = i;
            this.d = (byte) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class IntShortPair extends AbstractPair {
        private int c;
        private short d;

        public IntShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = i;
            this.d = (short) ((int) j);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class IntIntPair extends AbstractPair {
        private int c;
        private int d;

        public IntIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = i;
            this.d = (int) j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return (long) this.d;
        }
    }

    private class IntLongPair extends AbstractPair {
        private int c;
        private long d;

        public IntLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.c = i;
            this.d = j;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    private abstract class AbstractPair implements Pair {
        private AbstractPair() {
        }

        /* synthetic */ AbstractPair(CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, AbstractPair abstractPair) {
            this();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return a() == pair.a() && b() == pair.b();
        }

        public String toString() {
            return "P(" + a() + "|" + b() + Operators.BRACKET_END_STR;
        }
    }
}
