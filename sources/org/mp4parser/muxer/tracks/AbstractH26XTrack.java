package org.mp4parser.muxer.tracks;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.TrackMetaData;

public abstract class AbstractH26XTrack extends AbstractTrack {
    public static int d = 67107840;
    protected long[] R_;
    protected List<CompositionTimeToSample.Entry> S_;
    protected List<SampleDependencyTypeBox.Entry> T_;
    protected List<Integer> U_;
    protected TrackMetaData V_;
    boolean W_;
    private DataSource k;

    public AbstractH26XTrack(DataSource dataSource, boolean z) {
        super(dataSource.toString());
        this.S_ = new ArrayList();
        this.T_ = new ArrayList();
        this.U_ = new ArrayList();
        this.V_ = new TrackMetaData();
        this.W_ = true;
        this.k = dataSource;
        this.W_ = z;
    }

    public AbstractH26XTrack(DataSource dataSource) {
        this(dataSource, true);
    }

    protected static InputStream a(InputStream inputStream) {
        return new CleanInputStream(inputStream);
    }

    protected static byte[] a(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        byte[] bArr = new byte[duplicate.remaining()];
        duplicate.get(bArr, 0, bArr.length);
        return bArr;
    }

    public TrackMetaData o() {
        return this.V_;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer a(LookAhead lookAhead) throws IOException {
        while (!lookAhead.b()) {
            try {
                lookAhead.c();
            } catch (EOFException unused) {
                return null;
            }
        }
        lookAhead.d();
        while (!lookAhead.a(this.W_)) {
            lookAhead.c();
        }
        return lookAhead.e();
    }

    /* access modifiers changed from: protected */
    public Sample a(List<? extends ByteBuffer> list) {
        byte[] bArr = new byte[(list.size() * 4)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        for (ByteBuffer remaining : list) {
            wrap.putInt(remaining.remaining());
        }
        ByteBuffer[] byteBufferArr = new ByteBuffer[(list.size() * 2)];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 2;
            byteBufferArr[i2] = ByteBuffer.wrap(bArr, i * 4, 4);
            byteBufferArr[i2 + 1] = (ByteBuffer) list.get(i);
        }
        return new SampleImpl(byteBufferArr);
    }

    public long[] m() {
        return this.R_;
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.S_;
    }

    public long[] b() {
        long[] jArr = new long[this.U_.size()];
        for (int i = 0; i < this.U_.size(); i++) {
            jArr[i] = (long) this.U_.get(i).intValue();
        }
        return jArr;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.T_;
    }

    public void close() throws IOException {
        this.k.close();
    }

    public static class LookAhead {

        /* renamed from: a  reason: collision with root package name */
        long f4012a = 0;
        int b = 0;
        DataSource c;
        ByteBuffer d;
        long e;

        public LookAhead(DataSource dataSource) throws IOException {
            this.c = dataSource;
            a();
        }

        public void a() throws IOException {
            this.d = this.c.a(this.f4012a, Math.min(this.c.a() - this.f4012a, (long) AbstractH26XTrack.d));
        }

        public boolean b() throws IOException {
            if (this.d.limit() - this.b >= 3) {
                if (this.d.get(this.b) == 0 && this.d.get(this.b + 1) == 0 && this.d.get(this.b + 2) == 1) {
                    return true;
                }
                return false;
            } else if (this.f4012a + ((long) this.b) + 3 < this.c.a()) {
                return false;
            } else {
                throw new EOFException();
            }
        }

        public boolean a(boolean z) throws IOException {
            if (this.d.limit() - this.b >= 3) {
                if (this.d.get(this.b) != 0 || this.d.get(this.b + 1) != 0) {
                    return false;
                }
                if ((this.d.get(this.b + 2) != 0 || !z) && this.d.get(this.b + 2) != 1) {
                    return false;
                }
                return true;
            } else if (this.f4012a + ((long) this.b) + 3 <= this.c.a()) {
                this.f4012a = this.e;
                this.b = 0;
                a();
                return a(z);
            } else if (this.f4012a + ((long) this.b) == this.c.a()) {
                return true;
            } else {
                return false;
            }
        }

        public void c() {
            this.b++;
        }

        public void d() {
            this.b += 3;
            this.e = this.f4012a + ((long) this.b);
        }

        public ByteBuffer e() {
            if (this.e >= this.f4012a) {
                this.d.position((int) (this.e - this.f4012a));
                ByteBuffer slice = this.d.slice();
                slice.limit((int) (((long) this.b) - (this.e - this.f4012a)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }
    }
}
