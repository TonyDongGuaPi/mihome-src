package org.mp4parser.muxer.tracks.h265;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox;
import org.mp4parser.boxes.iso14496.part15.HevcDecoderConfigurationRecord;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.tracks.AbstractH26XTrack;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.IsoTypeReader;

public class H265TrackImpl extends AbstractH26XTrack implements H265NalUnitTypes {
    ArrayList<ByteBuffer> aa = new ArrayList<>();
    ArrayList<ByteBuffer> ab = new ArrayList<>();
    ArrayList<ByteBuffer> ac = new ArrayList<>();
    ArrayList<Sample> ad = new ArrayList<>();
    SampleDescriptionBox ae;

    public String p() {
        return "vide";
    }

    public H265TrackImpl(DataSource dataSource, long j, int i) throws IOException {
        super(dataSource);
        ArrayList arrayList = new ArrayList();
        AbstractH26XTrack.LookAhead lookAhead = new AbstractH26XTrack.LookAhead(dataSource);
        boolean[] zArr = {false};
        boolean[] zArr2 = {true};
        while (true) {
            ByteBuffer a2 = a(lookAhead);
            if (a2 != null) {
                H265NalUnitHeader b = b(a2);
                if (zArr[0]) {
                    if (!a(b)) {
                        switch (b.b) {
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 39:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                                a(arrayList, zArr, zArr2);
                                break;
                        }
                    } else if ((a2.get(2) & Byte.MIN_VALUE) != 0) {
                        a(arrayList, zArr, zArr2);
                    }
                }
                int i2 = b.b;
                if (i2 != 39) {
                    switch (i2) {
                        case 32:
                            a2.position(2);
                            this.ac.add(a2.slice());
                            System.err.println("Stored VPS");
                            break;
                        case 33:
                            a2.position(2);
                            this.aa.add(a2.slice());
                            a2.position(1);
                            new SequenceParameterSetRbsp(Channels.newInputStream(new ByteBufferByteChannel(a2.slice())));
                            System.err.println("Stored SPS");
                            break;
                        case 34:
                            a2.position(2);
                            this.ab.add(a2.slice());
                            System.err.println("Stored PPS");
                            break;
                    }
                } else {
                    new SEIMessage(new BitReaderBuffer(a2.slice()));
                }
                switch (b.b) {
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                        break;
                    default:
                        PrintStream printStream = System.err;
                        printStream.println("Adding " + b.b);
                        arrayList.add(a2);
                        break;
                }
                if (a(b)) {
                    switch (b.b) {
                        case 19:
                        case 20:
                            zArr2[0] = zArr2[0] & true;
                            break;
                        default:
                            zArr2[0] = false;
                            break;
                    }
                }
                zArr[0] = zArr[0] | a(b);
            } else {
                this.ae = i();
                this.R_ = new long[this.ad.size()];
                o().a(j);
                Arrays.fill(this.R_, (long) i);
                return;
            }
        }
    }

    public static H265NalUnitHeader b(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        int d = IsoTypeReader.d(byteBuffer);
        H265NalUnitHeader h265NalUnitHeader = new H265NalUnitHeader();
        h265NalUnitHeader.f4048a = (32768 & d) >> 15;
        h265NalUnitHeader.b = (d & 32256) >> 9;
        h265NalUnitHeader.c = (d & 504) >> 3;
        h265NalUnitHeader.d = d & 7;
        return h265NalUnitHeader;
    }

    private SampleDescriptionBox i() {
        this.ae = new SampleDescriptionBox();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.f);
        visualSampleEntry.a(1);
        visualSampleEntry.e(24);
        visualSampleEntry.d(1);
        visualSampleEntry.a(72.0d);
        visualSampleEntry.b(72.0d);
        visualSampleEntry.b(1920);
        visualSampleEntry.c(1080);
        visualSampleEntry.b("HEVC Coding");
        HevcConfigurationBox hevcConfigurationBox = new HevcConfigurationBox();
        HevcDecoderConfigurationRecord.Array array = new HevcDecoderConfigurationRecord.Array();
        array.f3920a = true;
        array.c = 33;
        array.d = new ArrayList();
        Iterator<ByteBuffer> it = this.aa.iterator();
        while (it.hasNext()) {
            array.d.add(a(it.next()));
        }
        HevcDecoderConfigurationRecord.Array array2 = new HevcDecoderConfigurationRecord.Array();
        array2.f3920a = true;
        array2.c = 34;
        array2.d = new ArrayList();
        Iterator<ByteBuffer> it2 = this.ab.iterator();
        while (it2.hasNext()) {
            array2.d.add(a(it2.next()));
        }
        HevcDecoderConfigurationRecord.Array array3 = new HevcDecoderConfigurationRecord.Array();
        array3.f3920a = true;
        array3.c = 32;
        array3.d = new ArrayList();
        Iterator<ByteBuffer> it3 = this.ac.iterator();
        while (it3.hasNext()) {
            array3.d.add(a(it3.next()));
        }
        hevcConfigurationBox.v().addAll(Arrays.asList(new HevcDecoderConfigurationRecord.Array[]{array, array3, array2}));
        visualSampleEntry.a((Box) hevcConfigurationBox);
        this.ae.a((Box) visualSampleEntry);
        return this.ae;
    }

    public void a(List<ByteBuffer> list, boolean[] zArr, boolean[] zArr2) {
        this.ad.add(a((List<? extends ByteBuffer>) list));
        PrintStream printStream = System.err;
        printStream.print("Create AU from " + list.size() + " NALs");
        if (zArr2[0]) {
            System.err.println("  IDR");
        } else {
            System.err.println();
        }
        zArr[0] = false;
        zArr2[0] = true;
        list.clear();
    }

    public SampleDescriptionBox n() {
        return this.ae;
    }

    public List<Sample> l() {
        return this.ad;
    }

    /* access modifiers changed from: package-private */
    public boolean a(H265NalUnitHeader h265NalUnitHeader) {
        return h265NalUnitHeader.b >= 0 && h265NalUnitHeader.b <= 31;
    }
}
