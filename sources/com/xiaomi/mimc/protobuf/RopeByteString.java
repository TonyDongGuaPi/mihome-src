package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

final class RopeByteString extends ByteString {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11338a;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public final ByteString left;
    private final int leftLength;
    /* access modifiers changed from: private */
    public final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    static {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.MAX_VALUE);
        f11338a = new int[arrayList.size()];
        for (int i4 = 0; i4 < f11338a.length; i4++) {
            f11338a[i4] = ((Integer) arrayList.get(i4)).intValue();
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.left = byteString;
        this.right = byteString2;
        this.leftLength = byteString.size();
        this.totalLength = this.leftLength + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return a(byteString, byteString2);
        }
        if (byteString instanceof RopeByteString) {
            RopeByteString ropeByteString = (RopeByteString) byteString;
            if (ropeByteString.right.size() + byteString2.size() < 128) {
                return new RopeByteString(ropeByteString.left, a(ropeByteString.right, byteString2));
            } else if (ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.getTreeDepth() > byteString2.getTreeDepth()) {
                return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            }
        }
        if (size >= f11338a[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1]) {
            return new RopeByteString(byteString, byteString2);
        }
        return new Balancer().a(byteString, byteString2);
    }

    private static ByteString a(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[(size + size2)];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return ByteString.wrap(bArr);
    }

    static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    public byte byteAt(int i) {
        checkIndex(i, this.totalLength);
        if (i < this.leftLength) {
            return this.left.byteAt(i);
        }
        return this.right.byteAt(i - this.leftLength);
    }

    public int size() {
        return this.totalLength;
    }

    /* access modifiers changed from: protected */
    public int getTreeDepth() {
        return this.treeDepth;
    }

    /* access modifiers changed from: protected */
    public boolean isBalanced() {
        return this.totalLength >= f11338a[this.treeDepth];
    }

    public ByteString substring(int i, int i2) {
        int checkRange = checkRange(i, i2, this.totalLength);
        if (checkRange == 0) {
            return ByteString.EMPTY;
        }
        if (checkRange == this.totalLength) {
            return this;
        }
        if (i2 <= this.leftLength) {
            return this.left.substring(i, i2);
        }
        if (i >= this.leftLength) {
            return this.right.substring(i - this.leftLength, i2 - this.leftLength);
        }
        return new RopeByteString(this.left.substring(i), this.right.substring(0, i2 - this.leftLength));
    }

    /* access modifiers changed from: protected */
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        if (i + i3 <= this.leftLength) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else if (i >= this.leftLength) {
            this.right.copyToInternal(bArr, i - this.leftLength, i2, i3);
        } else {
            int i4 = this.leftLength - i;
            this.left.copyToInternal(bArr, i, i2, i4);
            this.right.copyToInternal(bArr, 0, i2 + i4, i3 - i4);
        }
    }

    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    /* access modifiers changed from: package-private */
    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        if (i + i2 <= this.leftLength) {
            this.left.writeToInternal(outputStream, i, i2);
        } else if (i >= this.leftLength) {
            this.right.writeToInternal(outputStream, i - this.leftLength, i2);
        } else {
            int i3 = this.leftLength - i;
            this.left.writeToInternal(outputStream, i, i3);
            this.right.writeToInternal(outputStream, 0, i2 - i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeTo(ByteOutput byteOutput) throws IOException {
        this.left.writeTo(byteOutput);
        this.right.writeTo(byteOutput);
    }

    /* access modifiers changed from: protected */
    public String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public boolean isValidUtf8() {
        if (this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(0, 0, this.leftLength), 0, this.right.size()) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int partialIsValidUtf8(int i, int i2, int i3) {
        if (i2 + i3 <= this.leftLength) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= this.leftLength) {
            return this.right.partialIsValidUtf8(i, i2 - this.leftLength, i3);
        }
        int i4 = this.leftLength - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i4), 0, i3 - i4);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        int peekCachedHashCode = peekCachedHashCode();
        int peekCachedHashCode2 = byteString.peekCachedHashCode();
        if (peekCachedHashCode == 0 || peekCachedHashCode2 == 0 || peekCachedHashCode == peekCachedHashCode2) {
            return a(byteString);
        }
        return false;
    }

    private boolean a(ByteString byteString) {
        boolean z;
        PieceIterator pieceIterator = new PieceIterator(this);
        ByteString.LeafByteString leafByteString = (ByteString.LeafByteString) pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString);
        ByteString.LeafByteString leafByteString2 = (ByteString.LeafByteString) pieceIterator2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = leafByteString.size() - i;
            int size2 = leafByteString2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                z = leafByteString.equalsRange(leafByteString2, i2, min);
            } else {
                z = leafByteString2.equalsRange(leafByteString, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            if (i3 < this.totalLength) {
                if (min == size) {
                    leafByteString = (ByteString.LeafByteString) pieceIterator.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == size2) {
                    leafByteString2 = (ByteString.LeafByteString) pieceIterator2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == this.totalLength) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: protected */
    public int partialHash(int i, int i2, int i3) {
        if (i2 + i3 <= this.leftLength) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= this.leftLength) {
            return this.right.partialHash(i, i2 - this.leftLength, i3);
        }
        int i4 = this.leftLength - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i4), 0, i3 - i4);
    }

    public CodedInputStream newCodedInput() {
        return CodedInputStream.a((InputStream) new RopeInputStream());
    }

    public InputStream newInput() {
        return new RopeInputStream();
    }

    private static class Balancer {

        /* renamed from: a  reason: collision with root package name */
        private final Stack<ByteString> f11339a;

        private Balancer() {
            this.f11339a = new Stack<>();
        }

        /* access modifiers changed from: private */
        public ByteString a(ByteString byteString, ByteString byteString2) {
            a(byteString);
            a(byteString2);
            ByteString pop = this.f11339a.pop();
            while (!this.f11339a.isEmpty()) {
                pop = new RopeByteString(this.f11339a.pop(), pop);
            }
            return pop;
        }

        private void a(ByteString byteString) {
            if (byteString.isBalanced()) {
                b(byteString);
            } else if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                a(ropeByteString.left);
                a(ropeByteString.right);
            } else {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
            }
        }

        private void b(ByteString byteString) {
            int a2 = a(byteString.size());
            int i = RopeByteString.f11338a[a2 + 1];
            if (this.f11339a.isEmpty() || this.f11339a.peek().size() >= i) {
                this.f11339a.push(byteString);
                return;
            }
            int i2 = RopeByteString.f11338a[a2];
            ByteString pop = this.f11339a.pop();
            while (!this.f11339a.isEmpty() && this.f11339a.peek().size() < i2) {
                pop = new RopeByteString(this.f11339a.pop(), pop);
            }
            RopeByteString ropeByteString = new RopeByteString(pop, byteString);
            while (!this.f11339a.isEmpty()) {
                if (this.f11339a.peek().size() >= RopeByteString.f11338a[a(ropeByteString.size()) + 1]) {
                    break;
                }
                ropeByteString = new RopeByteString(this.f11339a.pop(), ropeByteString);
            }
            this.f11339a.push(ropeByteString);
        }

        private int a(int i) {
            int binarySearch = Arrays.binarySearch(RopeByteString.f11338a, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }
    }

    private static class PieceIterator implements Iterator<ByteString.LeafByteString> {

        /* renamed from: a  reason: collision with root package name */
        private final Stack<RopeByteString> f11340a;
        private ByteString.LeafByteString b;

        private PieceIterator(ByteString byteString) {
            this.f11340a = new Stack<>();
            this.b = a(byteString);
        }

        private ByteString.LeafByteString a(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.f11340a.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (ByteString.LeafByteString) byteString;
        }

        private ByteString.LeafByteString b() {
            while (!this.f11340a.isEmpty()) {
                ByteString.LeafByteString a2 = a(this.f11340a.pop().right);
                if (!a2.isEmpty()) {
                    return a2;
                }
            }
            return null;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        /* renamed from: a */
        public ByteString.LeafByteString next() {
            if (this.b != null) {
                ByteString.LeafByteString leafByteString = this.b;
                this.b = b();
                return leafByteString;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return ByteString.wrap(toByteArray());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    private class RopeInputStream extends InputStream {
        private PieceIterator b;
        private ByteString.LeafByteString c;
        private int d;
        private int e;
        private int f;
        private int g;

        public boolean markSupported() {
            return true;
        }

        public RopeInputStream() {
            a();
        }

        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                return a(bArr, i, i2);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public long skip(long j) {
            if (j >= 0) {
                if (j > 2147483647L) {
                    j = 2147483647L;
                }
                return (long) a((byte[]) null, 0, (int) j);
            }
            throw new IndexOutOfBoundsException();
        }

        private int a(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (true) {
                if (i4 <= 0) {
                    break;
                }
                b();
                if (this.c != null) {
                    int min = Math.min(this.d - this.e, i4);
                    if (bArr != null) {
                        this.c.copyTo(bArr, this.e, i3, min);
                        i3 += min;
                    }
                    this.e += min;
                    i4 -= min;
                } else if (i4 == i2) {
                    return -1;
                }
            }
            return i2 - i4;
        }

        public int read() throws IOException {
            b();
            if (this.c == null) {
                return -1;
            }
            ByteString.LeafByteString leafByteString = this.c;
            int i = this.e;
            this.e = i + 1;
            return leafByteString.byteAt(i) & 255;
        }

        public int available() throws IOException {
            return RopeByteString.this.size() - (this.f + this.e);
        }

        public void mark(int i) {
            this.g = this.f + this.e;
        }

        public synchronized void reset() {
            a();
            a((byte[]) null, 0, this.g);
        }

        private void a() {
            this.b = new PieceIterator(RopeByteString.this);
            this.c = this.b.next();
            this.d = this.c.size();
            this.e = 0;
            this.f = 0;
        }

        private void b() {
            if (this.c != null && this.e == this.d) {
                this.f += this.d;
                this.e = 0;
                if (this.b.hasNext()) {
                    this.c = this.b.next();
                    this.d = this.c.size();
                    return;
                }
                this.c = null;
                this.d = 0;
            }
        }
    }
}
