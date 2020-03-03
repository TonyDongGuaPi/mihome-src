package org.mp4parser.muxer.tracks;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.dolby.AC3SpecificBox;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.TrackMetaData;

public class AC3TrackImpl extends AbstractTrack {
    static int[][][][] d = ((int[][][][]) Array.newInstance(int.class, new int[]{19, 2, 3, 2}));
    private final DataSource e;
    private List<Sample> f;
    private long[] g;
    private TrackMetaData h;
    private SampleDescriptionBox i;

    public List<CompositionTimeToSample.Entry> a() {
        return null;
    }

    public long[] b() {
        return null;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return null;
    }

    public SubSampleInformationBox d() {
        return null;
    }

    public String p() {
        return "soun";
    }

    static {
        d[0][0][0][0] = 32;
        d[0][1][0][0] = 32;
        d[0][0][0][1] = 64;
        d[0][1][0][1] = 64;
        d[1][0][0][0] = 40;
        d[1][1][0][0] = 40;
        d[1][0][0][1] = 80;
        d[1][1][0][1] = 80;
        d[2][0][0][0] = 48;
        d[2][1][0][0] = 48;
        d[2][0][0][1] = 96;
        d[2][1][0][1] = 96;
        d[3][0][0][0] = 56;
        d[3][1][0][0] = 56;
        d[3][0][0][1] = 112;
        d[3][1][0][1] = 112;
        d[4][0][0][0] = 64;
        d[4][1][0][0] = 64;
        d[4][0][0][1] = 128;
        d[4][1][0][1] = 128;
        d[5][0][0][0] = 80;
        d[5][1][0][0] = 80;
        d[5][0][0][1] = 160;
        d[5][1][0][1] = 160;
        d[6][0][0][0] = 96;
        d[6][1][0][0] = 96;
        d[6][0][0][1] = 192;
        d[6][1][0][1] = 192;
        d[7][0][0][0] = 112;
        d[7][1][0][0] = 112;
        d[7][0][0][1] = 224;
        d[7][1][0][1] = 224;
        d[8][0][0][0] = 128;
        d[8][1][0][0] = 128;
        d[8][0][0][1] = 256;
        d[8][1][0][1] = 256;
        d[9][0][0][0] = 160;
        d[9][1][0][0] = 160;
        d[9][0][0][1] = 320;
        d[9][1][0][1] = 320;
        d[10][0][0][0] = 192;
        d[10][1][0][0] = 192;
        d[10][0][0][1] = 384;
        d[10][1][0][1] = 384;
        d[11][0][0][0] = 224;
        d[11][1][0][0] = 224;
        d[11][0][0][1] = 448;
        d[11][1][0][1] = 448;
        d[12][0][0][0] = 256;
        d[12][1][0][0] = 256;
        d[12][0][0][1] = 512;
        d[12][1][0][1] = 512;
        d[13][0][0][0] = 320;
        d[13][1][0][0] = 320;
        d[13][0][0][1] = 640;
        d[13][1][0][1] = 640;
        d[14][0][0][0] = 384;
        d[14][1][0][0] = 384;
        d[14][0][0][1] = 768;
        d[14][1][0][1] = 768;
        d[15][0][0][0] = 448;
        d[15][1][0][0] = 448;
        d[15][0][0][1] = 896;
        d[15][1][0][1] = 896;
        d[16][0][0][0] = 512;
        d[16][1][0][0] = 512;
        d[16][0][0][1] = 1024;
        d[16][1][0][1] = 1024;
        d[17][0][0][0] = 576;
        d[17][1][0][0] = 576;
        d[17][0][0][1] = 1152;
        d[17][1][0][1] = 1152;
        d[18][0][0][0] = 640;
        d[18][1][0][0] = 640;
        d[18][0][0][1] = 1280;
        d[18][1][0][1] = 1280;
        d[0][0][1][0] = 32;
        d[0][1][1][0] = 32;
        d[0][0][1][1] = 69;
        d[0][1][1][1] = 70;
        d[1][0][1][0] = 40;
        d[1][1][1][0] = 40;
        d[1][0][1][1] = 87;
        d[1][1][1][1] = 88;
        d[2][0][1][0] = 48;
        d[2][1][1][0] = 48;
        d[2][0][1][1] = 104;
        d[2][1][1][1] = 105;
        d[3][0][1][0] = 56;
        d[3][1][1][0] = 56;
        d[3][0][1][1] = 121;
        d[3][1][1][1] = 122;
        d[4][0][1][0] = 64;
        d[4][1][1][0] = 64;
        d[4][0][1][1] = 139;
        d[4][1][1][1] = 140;
        d[5][0][1][0] = 80;
        d[5][1][1][0] = 80;
        d[5][0][1][1] = 174;
        d[5][1][1][1] = 175;
        d[6][0][1][0] = 96;
        d[6][1][1][0] = 96;
        d[6][0][1][1] = 208;
        d[6][1][1][1] = 209;
        d[7][0][1][0] = 112;
        d[7][1][1][0] = 112;
        d[7][0][1][1] = 243;
        d[7][1][1][1] = 244;
        d[8][0][1][0] = 128;
        d[8][1][1][0] = 128;
        d[8][0][1][1] = 278;
        d[8][1][1][1] = 279;
        d[9][0][1][0] = 160;
        d[9][1][1][0] = 160;
        d[9][0][1][1] = 348;
        d[9][1][1][1] = 349;
        d[10][0][1][0] = 192;
        d[10][1][1][0] = 192;
        d[10][0][1][1] = 417;
        d[10][1][1][1] = 418;
        d[11][0][1][0] = 224;
        d[11][1][1][0] = 224;
        d[11][0][1][1] = 487;
        d[11][1][1][1] = 488;
        d[12][0][1][0] = 256;
        d[12][1][1][0] = 256;
        d[12][0][1][1] = 557;
        d[12][1][1][1] = 558;
        d[13][0][1][0] = 320;
        d[13][1][1][0] = 320;
        d[13][0][1][1] = 696;
        d[13][1][1][1] = 697;
        d[14][0][1][0] = 384;
        d[14][1][1][0] = 384;
        d[14][0][1][1] = 835;
        d[14][1][1][1] = 836;
        d[15][0][1][0] = 448;
        d[15][1][1][0] = 448;
        d[15][0][1][1] = 975;
        d[15][1][1][1] = 975;
        d[16][0][1][0] = 512;
        d[16][1][1][0] = 512;
        d[16][0][1][1] = 1114;
        d[16][1][1][1] = 1115;
        d[17][0][1][0] = 576;
        d[17][1][1][0] = 576;
        d[17][0][1][1] = 1253;
        d[17][1][1][1] = 1254;
        d[18][0][1][0] = 640;
        d[18][1][1][0] = 640;
        d[18][0][1][1] = 1393;
        d[18][1][1][1] = 1394;
        d[0][0][2][0] = 32;
        d[0][1][2][0] = 32;
        d[0][0][2][1] = 96;
        d[0][1][2][1] = 96;
        d[1][0][2][0] = 40;
        d[1][1][2][0] = 40;
        d[1][0][2][1] = 120;
        d[1][1][2][1] = 120;
        d[2][0][2][0] = 48;
        d[2][1][2][0] = 48;
        d[2][0][2][1] = 144;
        d[2][1][2][1] = 144;
        d[3][0][2][0] = 56;
        d[3][1][2][0] = 56;
        d[3][0][2][1] = 168;
        d[3][1][2][1] = 168;
        d[4][0][2][0] = 64;
        d[4][1][2][0] = 64;
        d[4][0][2][1] = 192;
        d[4][1][2][1] = 192;
        d[5][0][2][0] = 80;
        d[5][1][2][0] = 80;
        d[5][0][2][1] = 240;
        d[5][1][2][1] = 240;
        d[6][0][2][0] = 96;
        d[6][1][2][0] = 96;
        d[6][0][2][1] = 288;
        d[6][1][2][1] = 288;
        d[7][0][2][0] = 112;
        d[7][1][2][0] = 112;
        d[7][0][2][1] = 336;
        d[7][1][2][1] = 336;
        d[8][0][2][0] = 128;
        d[8][1][2][0] = 128;
        d[8][0][2][1] = 384;
        d[8][1][2][1] = 384;
        d[9][0][2][0] = 160;
        d[9][1][2][0] = 160;
        d[9][0][2][1] = 480;
        d[9][1][2][1] = 480;
        d[10][0][2][0] = 192;
        d[10][1][2][0] = 192;
        d[10][0][2][1] = 576;
        d[10][1][2][1] = 576;
        d[11][0][2][0] = 224;
        d[11][1][2][0] = 224;
        d[11][0][2][1] = 672;
        d[11][1][2][1] = 672;
        d[12][0][2][0] = 256;
        d[12][1][2][0] = 256;
        d[12][0][2][1] = 768;
        d[12][1][2][1] = 768;
        d[13][0][2][0] = 320;
        d[13][1][2][0] = 320;
        d[13][0][2][1] = 960;
        d[13][1][2][1] = 960;
        d[14][0][2][0] = 384;
        d[14][1][2][0] = 384;
        d[14][0][2][1] = 1152;
        d[14][1][2][1] = 1152;
        d[15][0][2][0] = 448;
        d[15][1][2][0] = 448;
        d[15][0][2][1] = 1344;
        d[15][1][2][1] = 1344;
        d[16][0][2][0] = 512;
        d[16][1][2][0] = 512;
        d[16][0][2][1] = 1536;
        d[16][1][2][1] = 1536;
        d[17][0][2][0] = 576;
        d[17][1][2][0] = 576;
        d[17][0][2][1] = 1728;
        d[17][1][2][1] = 1728;
        d[18][0][2][0] = 640;
        d[18][1][2][0] = 640;
        d[18][0][2][1] = 1920;
        d[18][1][2][1] = 1920;
    }

    public AC3TrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    public AC3TrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.h = new TrackMetaData();
        this.e = dataSource;
        this.h.a(str);
        this.f = j();
        this.i = new SampleDescriptionBox();
        AudioSampleEntry i2 = i();
        this.i.a((Box) i2);
        this.h.b(new Date());
        this.h.a(new Date());
        this.h.a(str);
        this.h.a(i2.g());
        this.h.a(1.0f);
    }

    public void close() throws IOException {
        this.e.close();
    }

    public List<Sample> l() {
        return this.f;
    }

    public SampleDescriptionBox n() {
        return this.i;
    }

    public synchronized long[] m() {
        return this.g;
    }

    public TrackMetaData o() {
        return this.h;
    }

    private AudioSampleEntry i() throws IOException {
        int i2;
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(this.f.get(0).b());
        if (bitReaderBuffer.a(16) == 2935) {
            bitReaderBuffer.a(16);
            int a2 = bitReaderBuffer.a(2);
            switch (a2) {
                case 0:
                    i2 = MIOTAudioModule.SAMPLING_RATE;
                    break;
                case 1:
                    i2 = 44100;
                    break;
                case 2:
                    i2 = AsrRequest.d;
                    break;
                default:
                    throw new RuntimeException("Unsupported Sample Rate");
            }
            int a3 = bitReaderBuffer.a(6);
            int a4 = bitReaderBuffer.a(5);
            int a5 = bitReaderBuffer.a(3);
            int a6 = bitReaderBuffer.a(3);
            if (a4 != 16) {
                if (a4 == 9) {
                    i2 /= 2;
                } else if (!(a4 == 8 || a4 == 6)) {
                    throw new RuntimeException("Unsupported bsid");
                }
                if (a6 != 1 && (a6 & 1) == 1) {
                    bitReaderBuffer.a(2);
                }
                if ((a6 & 4) != 0) {
                    bitReaderBuffer.a(2);
                }
                if (a6 == 2) {
                    bitReaderBuffer.a(2);
                }
                switch (a6) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        int a7 = bitReaderBuffer.a(1);
                        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.g);
                        audioSampleEntry.b(2);
                        audioSampleEntry.a((long) i2);
                        audioSampleEntry.a(1);
                        audioSampleEntry.c(16);
                        AC3SpecificBox aC3SpecificBox = new AC3SpecificBox();
                        aC3SpecificBox.d(a6);
                        aC3SpecificBox.f(a3 >> 1);
                        aC3SpecificBox.b(a4);
                        aC3SpecificBox.c(a5);
                        aC3SpecificBox.a(a2);
                        aC3SpecificBox.e(a7);
                        aC3SpecificBox.g(0);
                        audioSampleEntry.a((Box) aC3SpecificBox);
                        return audioSampleEntry;
                    default:
                        throw new RuntimeException("Unsupported acmod");
                }
            } else {
                throw new RuntimeException("You cannot read E-AC-3 track with AC3TrackImpl.class - user EC3TrackImpl.class");
            }
        } else {
            throw new RuntimeException("Stream doesn't seem to be AC3");
        }
    }

    private int a(int i2, int i3) {
        int i4 = i2 >>> 1;
        int i5 = i2 & 1;
        if (i4 <= 18 && i5 <= 1 && i3 <= 2) {
            return d[i4][i5][i3][1] * 2;
        }
        throw new RuntimeException("Cannot determine framesize of current sample");
    }

    private List<Sample> j() throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(5);
        ArrayList arrayList = new ArrayList();
        while (-1 != this.e.a(allocate)) {
            long a2 = (long) a(allocate.get(4) & Constants.TagName.CARD_APP_ACTIVATION_STATUS, allocate.get(4) >> 6);
            arrayList.add(new Sample(this.e.b() - 5, a2, this.e) {
                private final long b;
                private final long c;
                private final DataSource d;

                {
                    this.b = r2;
                    this.c = r4;
                    this.d = r6;
                }

                public void a(WritableByteChannel writableByteChannel) throws IOException {
                    this.d.a(this.b, this.c, writableByteChannel);
                }

                public long a() {
                    return this.c;
                }

                public ByteBuffer b() {
                    try {
                        return this.d.a(this.b, this.c);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            this.e.a((this.e.b() - 5) + a2);
            allocate.rewind();
        }
        this.g = new long[arrayList.size()];
        Arrays.fill(this.g, 1536);
        return arrayList;
    }
}
