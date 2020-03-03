package org.mp4parser.muxer.tracks;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.qti.location.sdk.IZatTestService;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.dolby.DTSSpecificBox;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.TrackMetaData;

public class DTSTrackImpl extends AbstractTrack {
    private static final int K = 67108864;
    int A = 0;
    int B = 0;
    int C = 0;
    int D = 0;
    int E = 0;
    int F = 0;
    int G = 0;
    int H = 0;
    int I = 0;
    String J = "none";
    private long[] L;
    private int M = 0;
    private DataSource N;
    private List<Sample> O;
    private String P = "eng";
    TrackMetaData d = new TrackMetaData();
    SampleDescriptionBox e;
    int f;
    int g;
    int h = 0;
    int i;
    int j;
    int k;
    DTSSpecificBox l = new DTSSpecificBox();
    boolean m = false;
    boolean n = false;
    boolean o = false;
    int p = 0;
    int q = 0;
    int r = 0;
    int s = 0;
    int t = 0;
    int u = 0;
    int v = 0;
    int w = 0;
    int x = 0;
    int y = 0;
    int z = 0;

    public List<CompositionTimeToSample.Entry> a() {
        return null;
    }

    public long[] b() {
        return null;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return null;
    }

    public String p() {
        return "soun";
    }

    public DTSTrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.P = str;
        this.N = dataSource;
        i();
    }

    public DTSTrackImpl(DataSource dataSource) throws IOException {
        super(dataSource.toString());
        this.N = dataSource;
        i();
    }

    public void close() throws IOException {
        this.N.close();
    }

    private void i() throws IOException {
        if (j()) {
            this.e = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(this.J);
            audioSampleEntry.b(this.k);
            audioSampleEntry.a((long) this.f);
            audioSampleEntry.a(1);
            audioSampleEntry.c(16);
            audioSampleEntry.a((Box) this.l);
            this.e.a((Box) audioSampleEntry);
            this.d.b(new Date());
            this.d.a(new Date());
            this.d.a(this.P);
            this.d.a((long) this.f);
            return;
        }
        throw new IOException();
    }

    public List<Sample> l() {
        return this.O;
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        return this.L;
    }

    public TrackMetaData o() {
        return this.d;
    }

    private void a(int i2, ByteBuffer byteBuffer) {
        byteBuffer.getInt();
        byteBuffer.get();
        byteBuffer.getInt();
        byteBuffer.get();
        short s2 = byteBuffer.getShort();
        byteBuffer.get();
        this.p = byteBuffer.get();
        if ((s2 & 1) == 1) {
            this.m = true;
        }
        if ((s2 & 8) == 8) {
            this.n = true;
        }
        if ((s2 & 16) == 16) {
            this.o = true;
            this.p++;
        } else {
            this.p = 0;
        }
        for (int i3 = 14; i3 < i2; i3++) {
            byteBuffer.get();
        }
    }

    private boolean b(int i2, ByteBuffer byteBuffer) {
        this.q = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.r = byteBuffer.getShort();
        this.s = byteBuffer.getShort();
        this.t = byteBuffer.getInt();
        for (int i3 = 11; i3 < i2; i3++) {
            byteBuffer.get();
        }
        return true;
    }

    private boolean c(int i2, ByteBuffer byteBuffer) {
        int i3;
        byteBuffer.get();
        short s2 = byteBuffer.getShort();
        this.y = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.A = byteBuffer.getInt();
        this.B = byteBuffer.getShort();
        this.C = (byteBuffer.get() << 32) | (byteBuffer.getInt() & 65535);
        this.D = byteBuffer.getShort();
        this.E = byteBuffer.getShort();
        if ((s2 & 3) == 3) {
            this.F = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.G = byteBuffer.getShort();
            this.H = byteBuffer.getShort();
            i3 = 28;
        } else {
            i3 = 21;
        }
        if ((s2 & 4) > 0) {
            this.I = byteBuffer.get();
            i3++;
        }
        if ((s2 & 8) > 0) {
            this.z = 1;
        }
        while (i3 < i2) {
            byteBuffer.get();
            i3++;
        }
        return true;
    }

    private boolean d(int i2, ByteBuffer byteBuffer) {
        int i3;
        this.u = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        if (this.m) {
            this.v = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.w = byteBuffer.getShort();
            i3 = 8;
        } else {
            this.x = byteBuffer.getInt();
            i3 = 7;
        }
        while (i3 < i2) {
            byteBuffer.get();
            i3++;
        }
        return true;
    }

    private boolean j() throws IOException {
        int i2;
        int i3;
        boolean z2;
        int i4;
        int i5;
        boolean z3;
        int i6;
        boolean z4;
        ByteBuffer a2 = this.N.a(0, 25000);
        int i7 = a2.getInt();
        int i8 = a2.getInt();
        if (i7 == 1146377032 && i8 == 1145586770) {
            while (true) {
                if (!(i7 == 1398035021 && i8 == 1145132097) && a2.remaining() > 100) {
                    int i9 = (int) a2.getLong();
                    if (i7 == 1146377032 && i8 == 1145586770) {
                        a(i9, a2);
                    } else if (i7 == 1129271877 && i8 == 1397968196) {
                        if (!b(i9, a2)) {
                            return false;
                        }
                    } else if (i7 == 1096110162 && i8 == 759710802) {
                        if (!c(i9, a2)) {
                            return false;
                        }
                    } else if (i7 != 1163416659 || i8 != 1398754628) {
                        for (int i10 = 0; i10 < i9; i10++) {
                            a2.get();
                        }
                    } else if (!d(i9, a2)) {
                        return false;
                    }
                    i7 = a2.getInt();
                    i8 = a2.getInt();
                }
            }
            long j2 = a2.getLong();
            this.M = a2.position();
            boolean z5 = false;
            int i11 = -1;
            int i12 = -1;
            boolean z6 = false;
            char c = 65535;
            boolean z7 = false;
            boolean z8 = false;
            boolean z9 = false;
            int i13 = 0;
            boolean z10 = false;
            int i14 = 0;
            boolean z11 = false;
            boolean z12 = false;
            while (!z5) {
                int position = a2.position();
                int i15 = a2.getInt();
                boolean z13 = z5;
                if (i15 == 2147385345) {
                    if (i11 == 1) {
                        z5 = true;
                    } else {
                        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(a2);
                        int a3 = bitReaderBuffer.a(1);
                        int a4 = bitReaderBuffer.a(5);
                        int a5 = bitReaderBuffer.a(1);
                        if (a3 != 1 || a4 != 31 || a5 != 0) {
                            return false;
                        }
                        this.j = (bitReaderBuffer.a(7) + 1) * 32;
                        int a6 = bitReaderBuffer.a(14);
                        this.h += a6 + 1;
                        int a7 = bitReaderBuffer.a(6);
                        this.f = b(bitReaderBuffer.a(4));
                        this.g = a(bitReaderBuffer.a(5));
                        if (bitReaderBuffer.a(1) != 0) {
                            return false;
                        }
                        bitReaderBuffer.a(1);
                        bitReaderBuffer.a(1);
                        bitReaderBuffer.a(1);
                        bitReaderBuffer.a(1);
                        i14 = bitReaderBuffer.a(3);
                        i13 = bitReaderBuffer.a(1);
                        bitReaderBuffer.a(1);
                        int i16 = a7;
                        bitReaderBuffer.a(2);
                        bitReaderBuffer.a(1);
                        if (a5 == 1) {
                            bitReaderBuffer.a(16);
                        }
                        bitReaderBuffer.a(1);
                        int a8 = bitReaderBuffer.a(4);
                        bitReaderBuffer.a(2);
                        switch (bitReaderBuffer.a(3)) {
                            case 0:
                            case 1:
                                this.i = 16;
                                break;
                            case 2:
                            case 3:
                                this.i = 20;
                                break;
                            case 5:
                            case 6:
                                this.i = 24;
                                break;
                            default:
                                return false;
                        }
                        bitReaderBuffer.a(1);
                        bitReaderBuffer.a(1);
                        switch (a8) {
                            case 6:
                                bitReaderBuffer.a(4);
                                break;
                            case 7:
                                bitReaderBuffer.a(4);
                                break;
                            default:
                                bitReaderBuffer.a(4);
                                break;
                        }
                        a2.position(position + a6 + 1);
                        z5 = z13;
                        i12 = i16;
                        i11 = 1;
                    }
                } else if (i15 == 1683496997) {
                    if (i11 == -1) {
                        this.j = this.B;
                        i11 = 0;
                    }
                    BitReaderBuffer bitReaderBuffer2 = new BitReaderBuffer(a2);
                    bitReaderBuffer2.a(8);
                    bitReaderBuffer2.a(2);
                    if (bitReaderBuffer2.a(1) == 0) {
                        i5 = 8;
                        i4 = 16;
                    } else {
                        i5 = 12;
                        i4 = 20;
                    }
                    int a9 = bitReaderBuffer2.a(i4) + 1;
                    a2.position(position + bitReaderBuffer2.a(i5) + 1);
                    int i17 = a2.getInt();
                    if (i17 == 1515870810) {
                        if (z7) {
                            z13 = true;
                        }
                        i6 = i11;
                        z4 = z11;
                        z3 = z12;
                        z7 = true;
                    } else if (i17 == 1191201283) {
                        if (z10) {
                            z13 = true;
                        }
                        i6 = i11;
                        z4 = z11;
                        z3 = z12;
                        z10 = true;
                    } else if (i17 == 496366178) {
                        if (z11) {
                            z13 = true;
                        }
                        i6 = i11;
                        z3 = z12;
                        z4 = true;
                    } else {
                        i6 = i11;
                        z4 = z11;
                        if (i17 == 1700671838) {
                            if (z12) {
                                z13 = true;
                            }
                            z3 = true;
                        } else {
                            z3 = z12;
                            if (i17 == 176167201) {
                                if (z9) {
                                    z13 = true;
                                }
                                z9 = true;
                            } else if (i17 == 1101174087) {
                                if (z6) {
                                    z13 = true;
                                }
                                z6 = true;
                            } else if (i17 == 45126241) {
                                if (z8) {
                                    z13 = true;
                                }
                                z8 = true;
                            }
                        }
                    }
                    if (!z13) {
                        this.h += a9;
                    }
                    a2.position(position + a9);
                    z11 = z4;
                    z5 = z13;
                    i11 = i6;
                    z12 = z3;
                    c = 1;
                } else {
                    throw new IOException("No DTS_SYNCWORD_* found at " + a2.position());
                }
            }
            boolean z14 = z11;
            boolean z15 = z12;
            int i18 = this.j;
            int i19 = i18 != 512 ? i18 != 1024 ? i18 != 2048 ? i18 != 4096 ? -1 : 3 : 2 : 1 : 0;
            if (i19 == -1) {
                return false;
            }
            if (!(i12 == 0 || i12 == 2)) {
                switch (i12) {
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        break;
                    default:
                        i12 = 31;
                        break;
                }
            }
            if (i11 == 0) {
                if (z6) {
                    if (!z8) {
                        i2 = 17;
                        this.J = AudioSampleEntry.j;
                    } else {
                        i2 = 21;
                        this.J = AudioSampleEntry.k;
                    }
                } else if (z9) {
                    i2 = 18;
                    this.J = AudioSampleEntry.l;
                } else {
                    if (z8) {
                        this.J = AudioSampleEntry.k;
                        if (!z10 && !z6) {
                            i2 = 19;
                        } else if (z10 && !z6) {
                            i2 = 20;
                        } else if (!z10 && z6) {
                            i2 = 21;
                        }
                    }
                    i2 = 0;
                }
                this.f = this.y;
                this.i = 24;
            } else {
                if (c >= 1) {
                    int i20 = i14;
                    this.J = AudioSampleEntry.k;
                    if (i13 == 0) {
                        if (z8 || !z10 || z14 || z15 || z6 || z9) {
                            if (z8 || z10 || z14) {
                                z2 = true;
                            } else {
                                z2 = true;
                                if (z15 && !z6 && !z9) {
                                    i2 = 6;
                                }
                            }
                            if (!z8 && z10 == z2 && !z14 && z15 == z2 && !z6 && !z9) {
                                i2 = 9;
                            } else if (!z8 && !z10 && z14 && !z15 && !z6 && !z9) {
                                i2 = 10;
                            } else if (!z8 && z10 && z14 && !z15 && !z6 && !z9) {
                                i2 = 13;
                            } else if (!z8 && !z10 && !z14 && !z15 && z6 && !z9) {
                                i2 = 14;
                            }
                        } else {
                            i2 = 5;
                        }
                    } else if (i20 == 0 && !z8 && !z10 && !z14 && z15 && !z6 && !z9) {
                        i2 = 7;
                    } else if (i20 == 6 && !z8 && !z10 && !z14 && z15 && !z6 && !z9) {
                        i2 = 8;
                    } else if (i20 == 0 && !z8 && !z10 && z14 && !z15 && !z6 && !z9) {
                        i2 = 11;
                    } else if (i20 == 6 && !z8 && !z10 && z14 && !z15 && !z6 && !z9) {
                        i2 = 12;
                    } else if (i20 == 0 && !z8 && !z10 && !z14 && !z15 && z6 && !z9) {
                        i2 = 15;
                    } else if (i20 == 2 && !z8 && !z10 && !z14 && !z15 && z6 && !z9) {
                        i2 = 16;
                    }
                } else if (i13 > 0) {
                    int i21 = i14;
                    if (i21 == 0) {
                        this.J = "dtsc";
                        i2 = 2;
                    } else if (i21 == 2) {
                        this.J = "dtsc";
                        i2 = 4;
                    } else if (i21 != 6) {
                        this.J = AudioSampleEntry.k;
                    } else {
                        this.J = AudioSampleEntry.k;
                        i2 = 3;
                    }
                } else {
                    this.J = "dtsc";
                    i2 = 1;
                }
                i2 = 0;
            }
            this.l.b((long) this.y);
            if (this.m) {
                this.l.c((long) ((this.r + this.v) * 1000));
            } else {
                this.l.c((long) ((this.r + this.u) * 1000));
            }
            this.l.a((long) ((this.r + this.u) * 1000));
            this.l.a(this.i);
            this.l.b(i19);
            this.l.c(i2);
            if ((this.s & 8) > 0 || (this.s & 4096) > 0) {
                this.l.d(1);
            } else {
                this.l.d(0);
            }
            this.l.e(i12);
            this.l.f(this.t);
            this.l.g(0);
            this.l.h(4);
            this.l.i(this.D);
            if (this.q <= 0 || this.u <= 0) {
                i3 = 0;
                this.l.j(0);
            } else {
                this.l.j(1);
                i3 = 0;
            }
            this.l.k(this.z);
            this.l.m(i3);
            this.k = i3;
            while (i3 < 16) {
                if (((this.D >> i3) & 1) == 1) {
                    if (i3 != 0) {
                        if (i3 != 12) {
                            if (i3 != 14) {
                                switch (i3) {
                                    case 3:
                                    case 4:
                                        break;
                                    default:
                                        switch (i3) {
                                            case 7:
                                            case 8:
                                                break;
                                            default:
                                                this.k += 2;
                                                break;
                                        }
                                }
                            }
                        }
                    }
                    this.k++;
                    i3++;
                }
                i3++;
            }
            this.O = a(this.N, this.M, j2, i11);
            this.L = new long[this.O.size()];
            Arrays.fill(this.L, (long) this.j);
            return true;
        }
        throw new IOException("data does not start with 'DTSHDHDR' as required for a DTS-HD file");
    }

    private List<Sample> a(DataSource dataSource, int i2, long j2, int i3) throws IOException {
        LookAhead lookAhead = new LookAhead(dataSource, (long) i2, j2, i3);
        ArrayList arrayList = new ArrayList();
        while (true) {
            final ByteBuffer a2 = lookAhead.a();
            if (a2 != null) {
                arrayList.add(new Sample() {
                    public void a(WritableByteChannel writableByteChannel) throws IOException {
                        writableByteChannel.write((ByteBuffer) a2.rewind());
                    }

                    public long a() {
                        return (long) a2.rewind().remaining();
                    }

                    public ByteBuffer b() {
                        return a2;
                    }
                });
            } else {
                System.err.println("all samples found");
                return arrayList;
            }
        }
    }

    private int a(int i2) throws IOException {
        switch (i2) {
            case 0:
                return 32;
            case 1:
                return 56;
            case 2:
                return 64;
            case 3:
                return 96;
            case 4:
                return 112;
            case 5:
                return 128;
            case 6:
                return 192;
            case 7:
                return 224;
            case 8:
                return 256;
            case 9:
                return CommonUtils.x;
            case 10:
                return BitmapCounterProvider.MAX_BITMAP_COUNT;
            case 11:
                return 448;
            case 12:
                return 512;
            case 13:
                return 576;
            case 14:
                return 640;
            case 15:
                return 768;
            case 16:
                return 960;
            case 17:
                return 1024;
            case 18:
                return 1152;
            case 19:
                return 1280;
            case 20:
                return 1344;
            case 21:
                return 1408;
            case 22:
                return 1411;
            case 23:
                return 1472;
            case 24:
                return 1536;
            case 25:
                return -1;
            default:
                throw new IOException("Unknown bitrate value");
        }
    }

    private int b(int i2) throws IOException {
        switch (i2) {
            case 1:
                return 8000;
            case 2:
                return RecordDevice.PCM_FREQUENCE_16K;
            case 3:
                return AsrRequest.d;
            case 6:
                return 11025;
            case 7:
                return 22050;
            case 8:
                return 44100;
            case 11:
                return TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET;
            case 12:
                return 24000;
            case 13:
                return MIOTAudioModule.SAMPLING_RATE;
            default:
                throw new IOException("Unknown Sample Rate");
        }
    }

    class LookAhead {

        /* renamed from: a  reason: collision with root package name */
        long f4019a;
        int b = 0;
        DataSource c;
        long d;
        ByteBuffer e;
        long f;
        private final int h;

        LookAhead(DataSource dataSource, long j, long j2, int i) throws IOException {
            this.c = dataSource;
            this.f4019a = j;
            this.d = j2 + j;
            this.h = i;
            b();
        }

        public ByteBuffer a() throws IOException {
            while (true) {
                try {
                    if (this.h != 1) {
                        if (c()) {
                            break;
                        }
                    } else if (d()) {
                        break;
                    }
                    g();
                } catch (EOFException unused) {
                    return null;
                }
            }
            i();
            while (true) {
                if (this.h != 1) {
                    if (e()) {
                        break;
                    }
                } else if (f()) {
                    break;
                }
                h();
            }
            return j();
        }

        private void b() throws IOException {
            System.err.println("Fill Buffer");
            this.e = this.c.a(this.f4019a, Math.min(this.d - this.f4019a, IZatTestService.B));
        }

        private boolean c() throws IOException {
            return a(Constants.TagName.PAY_ORDER_LIST, (byte) 88, (byte) 32, Constants.TagName.ORDER_RANGE_TYPE);
        }

        private boolean d() throws IOException {
            return a(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean a(byte b2, byte b3, byte b4, byte b5) throws IOException {
            if (this.e.limit() - this.b >= 4) {
                if (this.e.get(this.b) == b2 && this.e.get(this.b + 1) == b3 && this.e.get(this.b + 2) == b4 && this.e.get(this.b + 3) == b5) {
                    return true;
                }
                return false;
            } else if (this.f4019a + ((long) this.b) + 4 < this.c.a()) {
                return false;
            } else {
                throw new EOFException();
            }
        }

        private boolean e() throws IOException {
            return b(Constants.TagName.PAY_ORDER_LIST, (byte) 88, (byte) 32, Constants.TagName.ORDER_RANGE_TYPE);
        }

        private boolean f() throws IOException {
            return b(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean b(byte b2, byte b3, byte b4, byte b5) throws IOException {
            if (this.e.limit() - this.b >= 4) {
                if ((this.f4019a + ((long) this.b)) % 1048576 == 0) {
                    PrintStream printStream = System.err;
                    printStream.println("" + (((this.f4019a + ((long) this.b)) / 1024) / 1024));
                }
                if (this.e.get(this.b) == b2 && this.e.get(this.b + 1) == b3 && this.e.get(this.b + 2) == b4 && this.e.get(this.b + 3) == b5) {
                    return true;
                }
                return false;
            } else if (this.f4019a + ((long) this.b) + 4 <= this.d) {
                this.f4019a = this.f;
                this.b = 0;
                b();
                return d();
            } else if (this.f4019a + ((long) this.b) == this.d) {
                return true;
            } else {
                return false;
            }
        }

        private void g() {
            this.b++;
        }

        private void h() {
            this.b += 4;
        }

        private void i() {
            this.f = this.f4019a + ((long) this.b);
            this.b += 4;
        }

        private ByteBuffer j() {
            if (this.f >= this.f4019a) {
                this.e.position((int) (this.f - this.f4019a));
                ByteBuffer slice = this.e.slice();
                slice.limit((int) (((long) this.b) - (this.f - this.f4019a)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }
    }
}
