package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.dolby.EC3SpecificBox;
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
import org.mp4parser.tools.CastUtils;

public class EC3TrackImpl extends AbstractTrack {
    private static final long f = 20;
    TrackMetaData d = new TrackMetaData();
    SampleDescriptionBox e;
    /* access modifiers changed from: private */
    public final DataSource g;
    private int h;
    /* access modifiers changed from: private */
    public int i;
    private List<BitStreamInfo> j = new LinkedList();
    private List<Sample> k;
    private long[] l;

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

    public EC3TrackImpl(DataSource dataSource) throws IOException {
        super(dataSource.toString());
        this.g = dataSource;
        boolean z = false;
        while (!z) {
            BitStreamInfo i2 = i();
            if (i2 != null) {
                for (BitStreamInfo next : this.j) {
                    if (i2.n != 1 && next.k == i2.k) {
                        z = true;
                    }
                }
                if (!z) {
                    this.j.add(i2);
                }
            } else {
                throw new IOException();
            }
        }
        if (this.j.size() != 0) {
            int i3 = this.j.get(0).m;
            this.e = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.h);
            audioSampleEntry.b(2);
            long j2 = (long) i3;
            audioSampleEntry.a(j2);
            audioSampleEntry.a(1);
            audioSampleEntry.c(16);
            EC3SpecificBox eC3SpecificBox = new EC3SpecificBox();
            int[] iArr = new int[this.j.size()];
            int[] iArr2 = new int[this.j.size()];
            for (BitStreamInfo next2 : this.j) {
                if (next2.n == 1) {
                    int i4 = next2.k;
                    iArr[i4] = iArr[i4] + 1;
                    iArr2[next2.k] = ((next2.o >> 5) & 255) | ((next2.o >> 6) & 256);
                }
            }
            for (BitStreamInfo next3 : this.j) {
                if (next3.n != 1) {
                    EC3SpecificBox.Entry entry = new EC3SpecificBox.Entry();
                    entry.f3816a = next3.f3816a;
                    entry.b = next3.b;
                    entry.c = next3.c;
                    entry.d = next3.d;
                    entry.e = next3.e;
                    entry.f = 0;
                    entry.g = iArr[next3.k];
                    entry.h = iArr2[next3.k];
                    entry.i = 0;
                    eC3SpecificBox.a(entry);
                }
                this.h += next3.l;
                this.i += next3.j;
            }
            eC3SpecificBox.a(this.h / 1000);
            audioSampleEntry.a((Box) eC3SpecificBox);
            this.e.a((Box) audioSampleEntry);
            this.d.b(new Date());
            this.d.a(new Date());
            this.d.a(j2);
            this.d.a(1.0f);
            dataSource.a(0);
            this.k = j();
            this.l = new long[this.k.size()];
            Arrays.fill(this.l, 1536);
            return;
        }
        throw new IOException();
    }

    public void close() throws IOException {
        this.g.close();
    }

    public List<Sample> l() {
        return this.k;
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        return this.l;
    }

    public TrackMetaData o() {
        return this.d;
    }

    private BitStreamInfo i() throws IOException {
        int i2;
        int i3;
        long b = this.g.b();
        ByteBuffer allocate = ByteBuffer.allocate(200);
        this.g.a(allocate);
        allocate.rewind();
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(allocate);
        if (bitReaderBuffer.a(16) != 2935) {
            return null;
        }
        BitStreamInfo bitStreamInfo = new BitStreamInfo();
        bitStreamInfo.n = bitReaderBuffer.a(2);
        bitStreamInfo.k = bitReaderBuffer.a(3);
        bitStreamInfo.j = (bitReaderBuffer.a(11) + 1) * 2;
        bitStreamInfo.f3816a = bitReaderBuffer.a(2);
        int i4 = -1;
        if (bitStreamInfo.f3816a == 3) {
            i4 = bitReaderBuffer.a(2);
            i2 = 3;
        } else {
            i2 = bitReaderBuffer.a(2);
        }
        switch (i2) {
            case 0:
                i3 = 1;
                break;
            case 1:
                i3 = 2;
                break;
            case 2:
                i3 = 3;
                break;
            case 3:
                i3 = 6;
                break;
            default:
                i3 = 0;
                break;
        }
        bitStreamInfo.j *= 6 / i3;
        bitStreamInfo.d = bitReaderBuffer.a(3);
        bitStreamInfo.e = bitReaderBuffer.a(1);
        bitStreamInfo.b = bitReaderBuffer.a(5);
        bitReaderBuffer.a(5);
        if (1 == bitReaderBuffer.a(1)) {
            bitReaderBuffer.a(8);
        }
        if (bitStreamInfo.d == 0) {
            bitReaderBuffer.a(5);
            if (1 == bitReaderBuffer.a(1)) {
                bitReaderBuffer.a(8);
            }
        }
        if (1 == bitStreamInfo.n && 1 == bitReaderBuffer.a(1)) {
            bitStreamInfo.o = bitReaderBuffer.a(16);
        }
        if (1 == bitReaderBuffer.a(1)) {
            if (bitStreamInfo.d > 2) {
                bitReaderBuffer.a(2);
            }
            if (1 == (bitStreamInfo.d & 1) && bitStreamInfo.d > 2) {
                bitReaderBuffer.a(3);
                bitReaderBuffer.a(3);
            }
            if ((bitStreamInfo.d & 4) > 0) {
                bitReaderBuffer.a(3);
                bitReaderBuffer.a(3);
            }
            if (1 == bitStreamInfo.e && 1 == bitReaderBuffer.a(1)) {
                bitReaderBuffer.a(5);
            }
            if (bitStreamInfo.n == 0) {
                if (1 == bitReaderBuffer.a(1)) {
                    bitReaderBuffer.a(6);
                }
                if (bitStreamInfo.d == 0 && 1 == bitReaderBuffer.a(1)) {
                    bitReaderBuffer.a(6);
                }
                if (1 == bitReaderBuffer.a(1)) {
                    bitReaderBuffer.a(6);
                }
                int a2 = bitReaderBuffer.a(2);
                if (1 == a2) {
                    bitReaderBuffer.a(5);
                } else if (2 == a2) {
                    bitReaderBuffer.a(12);
                } else if (3 == a2) {
                    int a3 = bitReaderBuffer.a(5);
                    if (1 == bitReaderBuffer.a(1)) {
                        bitReaderBuffer.a(5);
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(4);
                        }
                        if (1 == bitReaderBuffer.a(1)) {
                            if (1 == bitReaderBuffer.a(1)) {
                                bitReaderBuffer.a(4);
                            }
                            if (1 == bitReaderBuffer.a(1)) {
                                bitReaderBuffer.a(4);
                            }
                        }
                    }
                    if (1 == bitReaderBuffer.a(1)) {
                        bitReaderBuffer.a(5);
                        if (1 == bitReaderBuffer.a(1)) {
                            bitReaderBuffer.a(7);
                            if (1 == bitReaderBuffer.a(1)) {
                                bitReaderBuffer.a(8);
                            }
                        }
                    }
                    for (int i5 = 0; i5 < a3 + 2; i5++) {
                        bitReaderBuffer.a(8);
                    }
                    bitReaderBuffer.c();
                }
                if (bitStreamInfo.d < 2) {
                    if (1 == bitReaderBuffer.a(1)) {
                        bitReaderBuffer.a(14);
                    }
                    if (bitStreamInfo.d == 0 && 1 == bitReaderBuffer.a(1)) {
                        bitReaderBuffer.a(14);
                    }
                    if (1 == bitReaderBuffer.a(1)) {
                        if (i2 == 0) {
                            bitReaderBuffer.a(5);
                        } else {
                            for (int i6 = 0; i6 < i3; i6++) {
                                if (1 == bitReaderBuffer.a(1)) {
                                    bitReaderBuffer.a(5);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (1 == bitReaderBuffer.a(1)) {
            bitStreamInfo.c = bitReaderBuffer.a(3);
        }
        switch (bitStreamInfo.f3816a) {
            case 0:
                bitStreamInfo.m = MIOTAudioModule.SAMPLING_RATE;
                break;
            case 1:
                bitStreamInfo.m = 44100;
                break;
            case 2:
                bitStreamInfo.m = AsrRequest.d;
                break;
            case 3:
                switch (i4) {
                    case 0:
                        bitStreamInfo.m = 24000;
                        break;
                    case 1:
                        bitStreamInfo.m = 22050;
                        break;
                    case 2:
                        bitStreamInfo.m = RecordDevice.PCM_FREQUENCE_16K;
                        break;
                    case 3:
                        bitStreamInfo.m = 0;
                        break;
                }
        }
        if (bitStreamInfo.m == 0) {
            return null;
        }
        double d2 = (double) bitStreamInfo.m;
        Double.isNaN(d2);
        double d3 = (double) bitStreamInfo.j;
        Double.isNaN(d3);
        bitStreamInfo.l = (int) ((d2 / 1536.0d) * d3 * 8.0d);
        this.g.a(b + ((long) bitStreamInfo.j));
        return bitStreamInfo;
    }

    private List<Sample> j() throws IOException {
        int a2 = CastUtils.a((this.g.a() - this.g.b()) / ((long) this.i));
        ArrayList arrayList = new ArrayList(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            final int i3 = this.i * i2;
            arrayList.add(new Sample() {
                public void a(WritableByteChannel writableByteChannel) throws IOException {
                    EC3TrackImpl.this.g.a((long) i3, (long) EC3TrackImpl.this.i, writableByteChannel);
                }

                public long a() {
                    return (long) EC3TrackImpl.this.i;
                }

                public ByteBuffer b() {
                    try {
                        return EC3TrackImpl.this.g.a((long) i3, (long) EC3TrackImpl.this.i);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return arrayList;
    }

    public String toString() {
        return "EC3TrackImpl{bitrate=" + this.h + ", bitStreamInfos=" + this.j + Operators.BLOCK_END;
    }

    public static class BitStreamInfo extends EC3SpecificBox.Entry {
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;

        public String toString() {
            return "BitStreamInfo{frameSize=" + this.j + ", substreamid=" + this.k + ", bitrate=" + this.l + ", samplerate=" + this.m + ", strmtyp=" + this.n + ", chanmap=" + this.o + Operators.BLOCK_END;
        }
    }
}
