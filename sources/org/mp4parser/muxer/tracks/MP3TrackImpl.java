package org.mp4parser.muxer.tracks;

import com.xiaomi.ai.AsrRequest;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.cybergarage.http.HTTPServer;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.TrackMetaData;

public class MP3TrackImpl extends AbstractTrack {
    private static final int i = 3;
    private static final int j = 1;
    private static final int[] k = {44100, MIOTAudioModule.SAMPLING_RATE, AsrRequest.d, 0};
    private static final int[] l = {0, AsrRequest.d, 40000, MIOTAudioModule.SAMPLING_RATE, 56000, 64000, HTTPServer.DEFAULT_TIMEOUT, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0};
    private static final int m = 1152;
    private static final int n = 107;
    private static final int o = 5;
    TrackMetaData d;
    SampleDescriptionBox e;
    MP3Header f;
    long g;
    long h;
    private final DataSource p;
    private List<Sample> q;
    private long[] r;

    public String p() {
        return "soun";
    }

    public String toString() {
        return "MP3TrackImpl";
    }

    public MP3TrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    public MP3TrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.d = new TrackMetaData();
        this.p = dataSource;
        this.q = new LinkedList();
        this.f = a(dataSource);
        double d2 = (double) this.f.g;
        Double.isNaN(d2);
        double d3 = d2 / 1152.0d;
        double size = (double) this.q.size();
        Double.isNaN(size);
        double d4 = size / d3;
        LinkedList linkedList = new LinkedList();
        Iterator<Sample> it = this.q.iterator();
        long j2 = 0;
        while (true) {
            int i2 = 0;
            if (it.hasNext()) {
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
                    if (d6 > ((double) this.g)) {
                        this.g = (long) ((int) d6);
                    }
                }
            } else {
                double d7 = (double) (j2 * 8);
                Double.isNaN(d7);
                this.h = (long) ((int) (d7 / d4));
                this.e = new SampleDescriptionBox();
                AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.c);
                audioSampleEntry.b(this.f.j);
                audioSampleEntry.a((long) this.f.g);
                audioSampleEntry.a(1);
                audioSampleEntry.c(16);
                ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
                ESDescriptor eSDescriptor = new ESDescriptor();
                eSDescriptor.b(0);
                SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
                sLConfigDescriptor.a(2);
                eSDescriptor.a(sLConfigDescriptor);
                DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
                decoderConfigDescriptor.a(107);
                decoderConfigDescriptor.b(5);
                decoderConfigDescriptor.a(this.g);
                decoderConfigDescriptor.b(this.h);
                eSDescriptor.a(decoderConfigDescriptor);
                eSDescriptorBox.c(eSDescriptor.b());
                audioSampleEntry.a((Box) eSDescriptorBox);
                this.e.a((Box) audioSampleEntry);
                this.d.b(new Date());
                this.d.a(new Date());
                this.d.a(str);
                this.d.a(1.0f);
                this.d.a((long) this.f.g);
                this.r = new long[this.q.size()];
                Arrays.fill(this.r, 1152);
                return;
            }
        }
    }

    public void close() throws IOException {
        this.p.close();
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        return this.r;
    }

    public TrackMetaData o() {
        return this.d;
    }

    public List<Sample> l() {
        return this.q;
    }

    private MP3Header a(DataSource dataSource) throws IOException {
        MP3Header mP3Header = null;
        while (true) {
            long b = dataSource.b();
            MP3Header b2 = b(dataSource);
            if (b2 == null) {
                return mP3Header;
            }
            if (mP3Header == null) {
                mP3Header = b2;
            }
            dataSource.a(b);
            ByteBuffer allocate = ByteBuffer.allocate(b2.a());
            dataSource.a(allocate);
            allocate.rewind();
            this.q.add(new SampleImpl(allocate));
        }
    }

    private MP3Header b(DataSource dataSource) throws IOException {
        MP3Header mP3Header = new MP3Header();
        ByteBuffer allocate = ByteBuffer.allocate(4);
        while (allocate.position() < 4) {
            if (dataSource.a(allocate) == -1) {
                return null;
            }
        }
        int i2 = 2;
        if (allocate.get(0) == 84 && allocate.get(1) == 65 && allocate.get(2) == 71) {
            return null;
        }
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer((ByteBuffer) allocate.rewind());
        if (bitReaderBuffer.a(11) == 2047) {
            mP3Header.f4022a = bitReaderBuffer.a(2);
            if (mP3Header.f4022a == 3) {
                mP3Header.b = bitReaderBuffer.a(2);
                if (mP3Header.b == 1) {
                    mP3Header.c = bitReaderBuffer.a(1);
                    mP3Header.d = bitReaderBuffer.a(4);
                    mP3Header.e = l[mP3Header.d];
                    if (mP3Header.e != 0) {
                        mP3Header.f = bitReaderBuffer.a(2);
                        mP3Header.g = k[mP3Header.f];
                        if (mP3Header.g != 0) {
                            mP3Header.h = bitReaderBuffer.a(1);
                            bitReaderBuffer.a(1);
                            mP3Header.i = bitReaderBuffer.a(2);
                            if (mP3Header.i == 3) {
                                i2 = 1;
                            }
                            mP3Header.j = i2;
                            return mP3Header;
                        }
                        throw new IOException("Unexpected (reserved) sample rate frequency");
                    }
                    throw new IOException("Unexpected (free/bad) bit rate");
                }
                throw new IOException("Expected Layer III");
            }
            throw new IOException("Expected MPEG Version 1 (ISO/IEC 11172-3)");
        }
        throw new IOException("Expected Start Word 0x7ff");
    }

    class MP3Header {

        /* renamed from: a  reason: collision with root package name */
        int f4022a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;

        MP3Header() {
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return ((this.e * 144) / this.g) + this.h;
        }
    }
}
