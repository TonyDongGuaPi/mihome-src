package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.AudioSpecificConfig;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.TrackMetaData;

public class AACTrackImpl extends AbstractTrack {
    public static Map<Integer, Integer> d = new HashMap();
    static Map<Integer, String> e = new HashMap();
    TrackMetaData f;
    SampleDescriptionBox g;
    long[] h;
    AdtsHeader i;
    int j;
    long k;
    long l;
    /* access modifiers changed from: private */
    public DataSource m;
    private List<Sample> n;

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
        e.put(1, "AAC Main");
        e.put(2, "AAC LC (Low Complexity)");
        e.put(3, "AAC SSR (Scalable Sample Rate)");
        e.put(4, "AAC LTP (Long Term Prediction)");
        e.put(5, "SBR (Spectral Band Replication)");
        e.put(6, "AAC Scalable");
        e.put(7, "TwinVQ");
        e.put(8, "CELP (Code Excited Linear Prediction)");
        e.put(9, "HXVC (Harmonic Vector eXcitation Coding)");
        e.put(10, "Reserved");
        e.put(11, "Reserved");
        e.put(12, "TTSI (Text-To-Speech Interface)");
        e.put(13, "Main Synthesis");
        e.put(14, "Wavetable Synthesis");
        e.put(15, "General MIDI");
        e.put(16, "Algorithmic Synthesis and Audio Effects");
        e.put(17, "ER (Error Resilient) AAC LC");
        e.put(18, "Reserved");
        e.put(19, "ER AAC LTP");
        e.put(20, "ER AAC Scalable");
        e.put(21, "ER TwinVQ");
        e.put(22, "ER BSAC (Bit-Sliced Arithmetic Coding)");
        e.put(23, "ER AAC LD (Low Delay)");
        e.put(24, "ER CELP");
        e.put(25, "ER HVXC");
        e.put(26, "ER HILN (Harmonic and Individual Lines plus Noise)");
        e.put(27, "ER Parametric");
        e.put(28, "SSC (SinuSoidal Coding)");
        e.put(29, "PS (Parametric Stereo)");
        e.put(30, "MPEG Surround");
        e.put(31, "(Escape value)");
        e.put(32, "Layer-1");
        e.put(33, "Layer-2");
        e.put(34, "Layer-3");
        e.put(35, "DST (Direct Stream Transfer)");
        e.put(36, "ALS (Audio Lossless)");
        e.put(37, "SLS (Scalable LosslesS)");
        e.put(38, "SLS non-core");
        e.put(39, "ER AAC ELD (Enhanced Low Delay)");
        e.put(40, "SMR (Symbolic Music Representation) Simple");
        e.put(41, "SMR Main");
        e.put(42, "USAC (Unified Speech and Audio Coding) (no SBR)");
        e.put(43, "SAOC (Spatial Audio Object Coding)");
        e.put(44, "LD MPEG Surround");
        e.put(45, "USAC");
        d.put(96000, 0);
        d.put(88200, 1);
        d.put(64000, 2);
        d.put(Integer.valueOf(MIOTAudioModule.SAMPLING_RATE), 3);
        d.put(44100, 4);
        d.put(Integer.valueOf(AsrRequest.d), 5);
        d.put(24000, 6);
        d.put(22050, 7);
        d.put(Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K), 8);
        d.put(Integer.valueOf(TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET), 9);
        d.put(11025, 10);
        d.put(8000, 11);
        d.put(0, 96000);
        d.put(1, 88200);
        d.put(2, 64000);
        d.put(3, Integer.valueOf(MIOTAudioModule.SAMPLING_RATE));
        d.put(4, 44100);
        d.put(5, Integer.valueOf(AsrRequest.d));
        d.put(6, 24000);
        d.put(7, 22050);
        d.put(8, Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K));
        d.put(9, Integer.valueOf(TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET));
        d.put(10, 11025);
        d.put(11, 8000);
    }

    public AACTrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    public AACTrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.f = new TrackMetaData();
        this.m = dataSource;
        this.n = new ArrayList();
        this.i = b(dataSource);
        double d2 = (double) this.i.f;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        double size = (double) this.n.size();
        Double.isNaN(size);
        double d4 = size / d3;
        LinkedList linkedList = new LinkedList();
        Iterator<Sample> it = this.n.iterator();
        long j2 = 0;
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            int a2 = (int) it.next().a();
            j2 += (long) a2;
            linkedList.add(Integer.valueOf(a2));
            while (((double) linkedList.size()) > d3) {
                linkedList.pop();
            }
            if (linkedList.size() == ((int) d3)) {
                Iterator it2 = linkedList.iterator();
                while (it2.hasNext()) {
                    i2 += ((Integer) it2.next()).intValue();
                }
                double d5 = (double) i2;
                Double.isNaN(d5);
                double size2 = (double) linkedList.size();
                Double.isNaN(size2);
                double d6 = ((d5 * 8.0d) / size2) * d3;
                if (d6 > ((double) this.k)) {
                    this.k = (long) ((int) d6);
                }
            }
        }
        double d7 = (double) (j2 * 8);
        Double.isNaN(d7);
        this.l = (long) ((int) (d7 / d4));
        this.j = 1536;
        this.g = new SampleDescriptionBox();
        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.c);
        if (this.i.g == 7) {
            audioSampleEntry.b(8);
        } else {
            audioSampleEntry.b(this.i.g);
        }
        audioSampleEntry.a((long) this.i.f);
        audioSampleEntry.a(1);
        audioSampleEntry.c(16);
        ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
        ESDescriptor eSDescriptor = new ESDescriptor();
        eSDescriptor.b(0);
        SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
        sLConfigDescriptor.a(2);
        eSDescriptor.a(sLConfigDescriptor);
        DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
        decoderConfigDescriptor.a(64);
        decoderConfigDescriptor.b(5);
        decoderConfigDescriptor.d(this.j);
        decoderConfigDescriptor.a(this.k);
        decoderConfigDescriptor.b(this.l);
        AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
        audioSpecificConfig.b(2);
        audioSpecificConfig.c(this.i.f4010a);
        audioSpecificConfig.e(this.i.g);
        decoderConfigDescriptor.a(audioSpecificConfig);
        eSDescriptor.a(decoderConfigDescriptor);
        eSDescriptorBox.a(eSDescriptor);
        audioSampleEntry.a((Box) eSDescriptorBox);
        this.g.a((Box) audioSampleEntry);
        this.f.b(new Date());
        this.f.a(new Date());
        this.f.a(str);
        this.f.a(1.0f);
        this.f.a((long) this.i.f);
        this.h = new long[this.n.size()];
        Arrays.fill(this.h, 1024);
    }

    public void close() throws IOException {
        this.m.close();
    }

    public SampleDescriptionBox n() {
        return this.g;
    }

    public long[] m() {
        return this.h;
    }

    public TrackMetaData o() {
        return this.f;
    }

    public List<Sample> l() {
        return this.n;
    }

    private AdtsHeader a(DataSource dataSource) throws IOException {
        AdtsHeader adtsHeader = new AdtsHeader();
        ByteBuffer allocate = ByteBuffer.allocate(7);
        while (allocate.position() < 7) {
            if (dataSource.a(allocate) == -1) {
                return null;
            }
        }
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer((ByteBuffer) allocate.rewind());
        if (bitReaderBuffer.a(12) == 4095) {
            adtsHeader.b = bitReaderBuffer.a(1);
            adtsHeader.c = bitReaderBuffer.a(2);
            adtsHeader.d = bitReaderBuffer.a(1);
            adtsHeader.e = bitReaderBuffer.a(2) + 1;
            adtsHeader.f4010a = bitReaderBuffer.a(4);
            adtsHeader.f = d.get(Integer.valueOf(adtsHeader.f4010a)).intValue();
            bitReaderBuffer.a(1);
            adtsHeader.g = bitReaderBuffer.a(3);
            adtsHeader.h = bitReaderBuffer.a(1);
            adtsHeader.i = bitReaderBuffer.a(1);
            adtsHeader.j = bitReaderBuffer.a(1);
            adtsHeader.k = bitReaderBuffer.a(1);
            adtsHeader.l = bitReaderBuffer.a(13);
            adtsHeader.m = bitReaderBuffer.a(11);
            adtsHeader.n = bitReaderBuffer.a(2) + 1;
            if (adtsHeader.n == 1) {
                if (adtsHeader.d == 0) {
                    dataSource.a(ByteBuffer.allocate(2));
                }
                return adtsHeader;
            }
            throw new IOException("This muxer can only work with 1 AAC frame per ADTS frame");
        }
        throw new IOException("Expected Start Word 0xfff");
    }

    private AdtsHeader b(DataSource dataSource) throws IOException {
        AdtsHeader adtsHeader = null;
        while (true) {
            AdtsHeader a2 = a(dataSource);
            if (a2 == null) {
                return adtsHeader;
            }
            if (adtsHeader == null) {
                adtsHeader = a2;
            }
            final long b = dataSource.b();
            final long a3 = (long) (a2.l - a2.a());
            this.n.add(new Sample() {
                public void a(WritableByteChannel writableByteChannel) throws IOException {
                    AACTrackImpl.this.m.a(b, a3, writableByteChannel);
                }

                public long a() {
                    return a3;
                }

                public ByteBuffer b() {
                    try {
                        return AACTrackImpl.this.m.a(b, a3);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            dataSource.a((dataSource.b() + ((long) a2.l)) - ((long) a2.a()));
        }
    }

    public String toString() {
        return "AACTrackImpl{sampleRate=" + this.i.f + ", channelconfig=" + this.i.g + Operators.BLOCK_END;
    }

    class AdtsHeader {

        /* renamed from: a  reason: collision with root package name */
        int f4010a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;

        AdtsHeader() {
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return (this.d == 0 ? 2 : 0) + 7;
        }
    }
}
