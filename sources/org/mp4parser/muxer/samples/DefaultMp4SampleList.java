package org.mp4parser.muxer.samples;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import org.mp4parser.Container;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.muxer.RandomAccessSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.support.Logger;
import org.mp4parser.tools.CastUtils;

public class DefaultMp4SampleList extends AbstractList<Sample> {
    /* access modifiers changed from: private */
    public static final Logger j = Logger.a(DefaultMp4SampleList.class);

    /* renamed from: a  reason: collision with root package name */
    Container f4004a;
    TrackBox b = null;
    SoftReference<ByteBuffer>[] c = null;
    int[] d;
    long[] e;
    long[] f;
    long[][] g;
    SampleSizeBox h;
    int i = 0;
    /* access modifiers changed from: private */
    public RandomAccessSource k;

    public DefaultMp4SampleList(long j2, Container container, RandomAccessSource randomAccessSource) {
        int i2;
        long j3 = j2;
        Container container2 = container;
        this.f4004a = container2;
        this.k = randomAccessSource;
        for (TrackBox next : container2.a(MovieBox.class).get(0).a(TrackBox.class)) {
            if (next.d().g() == j3) {
                this.b = next;
            }
        }
        if (this.b != null) {
            this.e = this.b.e().g().e();
            this.f = new long[this.e.length];
            this.c = new SoftReference[this.e.length];
            Arrays.fill(this.c, new SoftReference((Object) null));
            this.g = new long[this.e.length][];
            this.h = this.b.e().e();
            List<SampleToChunkBox.Entry> e2 = this.b.e().f().e();
            SampleToChunkBox.Entry[] entryArr = (SampleToChunkBox.Entry[]) e2.toArray(new SampleToChunkBox.Entry[e2.size()]);
            SampleToChunkBox.Entry entry = entryArr[0];
            long a2 = entry.a();
            int a3 = CastUtils.a(entry.b());
            int size = size();
            int i3 = a3;
            int i4 = 0;
            int i5 = 1;
            int i6 = 0;
            int i7 = 1;
            do {
                i4++;
                if (((long) i4) == a2) {
                    if (entryArr.length > i5) {
                        SampleToChunkBox.Entry entry2 = entryArr[i5];
                        i6 = i3;
                        i3 = CastUtils.a(entry2.b());
                        i5++;
                        a2 = entry2.a();
                    } else {
                        i6 = i3;
                        i3 = -1;
                        a2 = Long.MAX_VALUE;
                    }
                }
                this.g[i4 - 1] = new long[i6];
                i7 += i6;
            } while (i7 <= size);
            this.d = new int[(i4 + 1)];
            SampleToChunkBox.Entry entry3 = entryArr[0];
            long a4 = entry3.a();
            int i8 = 1;
            int i9 = 1;
            int i10 = 0;
            int a5 = CastUtils.a(entry3.b());
            int i11 = 0;
            while (true) {
                i2 = i11 + 1;
                this.d[i11] = i8;
                int i12 = a5;
                if (((long) i2) != a4) {
                    a5 = i12;
                } else if (entryArr.length > i9) {
                    SampleToChunkBox.Entry entry4 = entryArr[i9];
                    a5 = CastUtils.a(entry4.b());
                    a4 = entry4.a();
                    i9++;
                    i10 = i12;
                } else {
                    i10 = i12;
                    a5 = -1;
                    a4 = Long.MAX_VALUE;
                }
                i8 += i10;
                if (i8 > size) {
                    break;
                }
                i11 = i2;
            }
            this.d[i2] = Integer.MAX_VALUE;
            long j4 = 0;
            int i13 = 0;
            for (int i14 = 1; ((long) i14) <= this.h.f(); i14++) {
                while (i14 == this.d[i13]) {
                    i13++;
                    j4 = 0;
                }
                long[] jArr = this.f;
                int i15 = i13 - 1;
                int i16 = i14 - 1;
                jArr[i15] = jArr[i15] + this.h.c(i16);
                this.g[i15][i14 - this.d[i15]] = j4;
                j4 += this.h.c(i16);
            }
            return;
        }
        throw new RuntimeException("This MP4 does not contain track " + j3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int a(int i2) {
        int i3 = i2 + 1;
        if (i3 >= this.d[this.i] && i3 < this.d[this.i + 1]) {
            return this.i;
        } else if (i3 < this.d[this.i]) {
            this.i = 0;
            while (this.d[this.i + 1] <= i3) {
                this.i++;
            }
            return this.i;
        } else {
            this.i++;
            while (this.d[this.i + 1] <= i3) {
                this.i++;
            }
            return this.i;
        }
    }

    /* renamed from: b */
    public Sample get(int i2) {
        if (((long) i2) < this.h.f()) {
            return new SampleImpl(i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return CastUtils.a(this.b.e().e().f());
    }

    class SampleImpl implements Sample {
        private int b;

        public SampleImpl(int i) {
            this.b = i;
        }

        public void a(WritableByteChannel writableByteChannel) throws IOException {
            writableByteChannel.write(b());
        }

        public long a() {
            return DefaultMp4SampleList.this.h.c(this.b);
        }

        public synchronized ByteBuffer b() {
            long j;
            ByteBuffer byteBuffer;
            int a2 = DefaultMp4SampleList.this.a(this.b);
            SoftReference<ByteBuffer> softReference = DefaultMp4SampleList.this.c[a2];
            int i = DefaultMp4SampleList.this.d[a2] - 1;
            long j2 = (long) a2;
            long[] jArr = DefaultMp4SampleList.this.g[CastUtils.a(j2)];
            j = jArr[this.b - i];
            if (softReference == null || (byteBuffer = softReference.get()) == null) {
                try {
                    byteBuffer = DefaultMp4SampleList.this.k.a(DefaultMp4SampleList.this.e[CastUtils.a(j2)], jArr[jArr.length - 1] + DefaultMp4SampleList.this.h.c((i + jArr.length) - 1));
                    DefaultMp4SampleList.this.c[a2] = new SoftReference<>(byteBuffer);
                } catch (IOException e) {
                    StringWriter stringWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(stringWriter));
                    DefaultMp4SampleList.j.c(stringWriter.toString());
                    throw new IndexOutOfBoundsException(e.getMessage());
                }
            }
            return (ByteBuffer) ((ByteBuffer) byteBuffer.duplicate().position(CastUtils.a(j))).slice().limit(CastUtils.a(DefaultMp4SampleList.this.h.c(this.b)));
        }

        public String toString() {
            return "Sample(index: " + this.b + " size: " + DefaultMp4SampleList.this.h.c(this.b) + Operators.BRACKET_END_STR;
        }
    }
}
