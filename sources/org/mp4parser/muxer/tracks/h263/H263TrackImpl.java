package org.mp4parser.muxer.tracks.h263;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.FileDataSourceImpl;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.MultiFileDataSourceImpl;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.tracks.AbstractH26XTrack;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.Path;

public class H263TrackImpl extends AbstractH26XTrack {
    private static Logger u = Logger.getLogger(ESDescriptor.class.getName());
    int k = 0;
    int l;
    int m;
    int n;
    SampleDescriptionBox o;
    List<Sample> p;
    List<ByteBuffer> q;
    boolean r;
    int s;
    int t;

    public String p() {
        return "vide";
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r3v1, types: [int, boolean] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public H263TrackImpl(org.mp4parser.muxer.DataSource r22) throws java.io.IOException {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = 0
            r0.<init>(r1, r2)
            r0.k = r2
            r3 = 1
            r0.l = r3
            r4 = 2
            r0.m = r4
            r5 = 3
            r0.n = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0.p = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0.q = r5
            r0.r = r2
            r5 = -1
            r0.s = r5
            r0.t = r2
            org.mp4parser.muxer.tracks.AbstractH26XTrack$LookAhead r5 = new org.mp4parser.muxer.tracks.AbstractH26XTrack$LookAhead
            r5.<init>(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.mp4parser.boxes.sampleentry.VisualSampleEntry r6 = new org.mp4parser.boxes.sampleentry.VisualSampleEntry
            java.lang.String r7 = "mp4v"
            r6.<init>(r7)
            org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox r7 = new org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox
            r7.<init>()
            r0.o = r7
            org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox r7 = r0.o
            r7.a((org.mp4parser.Box) r6)
            r9 = 0
            r10 = r9
            r9 = 0
            r12 = -1
        L_0x004b:
            java.nio.ByteBuffer r14 = r0.a((org.mp4parser.muxer.tracks.AbstractH26XTrack.LookAhead) r5)
            r15 = 32
            if (r14 == 0) goto L_0x0158
            java.nio.ByteBuffer r2 = r14.duplicate()
            int r7 = org.mp4parser.tools.IsoTypeReader.f(r14)
            r8 = 176(0xb0, float:2.47E-43)
            r4 = 181(0xb5, float:2.54E-43)
            if (r7 == r8) goto L_0x013b
            if (r7 == r4) goto L_0x013b
            if (r7 == 0) goto L_0x013b
            if (r7 == r15) goto L_0x013b
            r8 = 178(0xb2, float:2.5E-43)
            if (r7 != r8) goto L_0x006d
            goto L_0x013b
        L_0x006d:
            r4 = 179(0xb3, float:2.51E-43)
            if (r7 != r4) goto L_0x00a9
            r0.r = r3
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer r4 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer
            r4.<init>(r14)
            r7 = 18
            int r4 = r4.a(r7)
            r7 = r4 & 63
            int r8 = r4 >>> 7
            r8 = r8 & 63
            int r8 = r8 * 60
            int r7 = r7 + r8
            int r4 = r4 >>> 13
            r4 = r4 & 31
            int r4 = r4 * 60
            int r4 = r4 * 60
            int r7 = r7 + r4
            long r7 = (long) r7
            java.util.List r4 = r0.U_
            java.util.List<org.mp4parser.muxer.Sample> r10 = r0.p
            int r10 = r10.size()
            int r10 = r10 + r3
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r4.add(r10)
            r1.add(r2)
            r10 = r7
            r17 = -1
            goto L_0x0153
        L_0x00a9:
            r4 = 182(0xb6, float:2.55E-43)
            if (r7 != r4) goto L_0x0133
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer r4 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer
            r4.<init>(r14)
            r7 = 2
            r4.a(r7)
        L_0x00b6:
            boolean r7 = r4.a()
            if (r7 == 0) goto L_0x00c0
            r7 = 1
            long r10 = r10 + r7
            goto L_0x00b6
        L_0x00c0:
            r4.a()
            r7 = 0
        L_0x00c4:
            int r8 = r0.t
            int r14 = r3 << r7
            if (r8 < r14) goto L_0x00cd
            int r7 = r7 + 1
            goto L_0x00c4
        L_0x00cd:
            int r4 = r4.a(r7)
            int r7 = r0.t
            long r7 = (long) r7
            long r7 = r7 * r10
            int r14 = r0.t
            int r14 = r4 % r14
            long r14 = (long) r14
            long r7 = r7 + r14
            r17 = -1
            int r14 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1))
            if (r14 == 0) goto L_0x00f2
            long[] r14 = r0.R_
            long[] r15 = new long[r3]
            long r19 = r7 - r12
            r16 = 0
            r15[r16] = r19
            long[] r14 = org.mp4parser.tools.Mp4Arrays.a((long[]) r14, (long[]) r15)
            r0.R_ = r14
        L_0x00f2:
            java.io.PrintStream r14 = java.lang.System.err
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r3 = "Frame increment: "
            r15.append(r3)
            long r12 = r7 - r12
            r15.append(r12)
            java.lang.String r3 = " vop time increment: "
            r15.append(r3)
            r15.append(r4)
            java.lang.String r3 = " last_sync_point: "
            r15.append(r3)
            r15.append(r10)
            java.lang.String r3 = " time_code: "
            r15.append(r3)
            r15.append(r7)
            java.lang.String r3 = r15.toString()
            r14.println(r3)
            r1.add(r2)
            java.util.List<org.mp4parser.muxer.Sample> r2 = r0.p
            org.mp4parser.muxer.Sample r3 = r0.a((java.util.List<? extends java.nio.ByteBuffer>) r1)
            r2.add(r3)
            r1.clear()
            r12 = r7
            goto L_0x0153
        L_0x0133:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Got start code I don't know. Ask Sebastian via mp4parser mailing list what to do"
            r1.<init>(r2)
            throw r1
        L_0x013b:
            r17 = -1
            boolean r3 = r0.r
            if (r3 != 0) goto L_0x0153
            java.util.List<java.nio.ByteBuffer> r3 = r0.q
            r3.add(r2)
            if (r7 != r15) goto L_0x014c
            r0.a(r14, r9, r6)
            goto L_0x0153
        L_0x014c:
            if (r7 != r4) goto L_0x0153
            int r2 = r0.b((java.nio.ByteBuffer) r14)
            r9 = r2
        L_0x0153:
            r2 = 0
            r3 = 1
            r4 = 2
            goto L_0x004b
        L_0x0158:
            long[] r1 = r0.R_
            r2 = 1
            long[] r3 = new long[r2]
            long[] r4 = r0.R_
            long[] r5 = r0.R_
            int r5 = r5.length
            int r5 = r5 - r2
            r7 = r4[r5]
            r4 = 0
            r3[r4] = r7
            long[] r1 = org.mp4parser.tools.Mp4Arrays.a((long[]) r1, (long[]) r3)
            r0.R_ = r1
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor r1 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor
            r1.<init>()
            r1.b(r2)
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor r2 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor
            r2.<init>()
            r2.a((int) r15)
            r3 = 4
            r2.b((int) r3)
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderSpecificInfo r3 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderSpecificInfo
            r3.<init>()
            java.util.List<java.nio.ByteBuffer> r4 = r0.q
            org.mp4parser.muxer.Sample r4 = r0.a((java.util.List<? extends java.nio.ByteBuffer>) r4)
            long r7 = r4.a()
            int r5 = org.mp4parser.tools.CastUtils.a(r7)
            byte[] r5 = new byte[r5]
            java.nio.ByteBuffer r4 = r4.b()
            r4.get(r5)
            r3.a((byte[]) r5)
            r2.a((org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderSpecificInfo) r3)
            r1.a((org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor) r2)
            org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor r2 = new org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor
            r2.<init>()
            r3 = 2
            r2.a((int) r3)
            r1.a((org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor) r2)
            org.mp4parser.boxes.iso14496.part14.ESDescriptorBox r2 = new org.mp4parser.boxes.iso14496.part14.ESDescriptorBox
            r2.<init>()
            r2.a(r1)
            r6.a((org.mp4parser.Box) r2)
            org.mp4parser.muxer.TrackMetaData r1 = r0.V_
            int r2 = r0.t
            long r2 = (long) r2
            r1.a((long) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.muxer.tracks.h263.H263TrackImpl.<init>(org.mp4parser.muxer.DataSource):void");
    }

    public static void a(String[] strArr) throws IOException {
        File[] listFiles = new File("C:\\dev\\mp4parser\\frames").listFiles();
        Arrays.sort(listFiles);
        Movie movie = new Movie();
        movie.a((Track) new H263TrackImpl(new MultiFileDataSourceImpl(listFiles)));
        new DefaultMp4Builder().a(movie).a(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void b(String[] strArr) throws IOException {
        FileDataSourceImpl fileDataSourceImpl = new FileDataSourceImpl("C:\\content\\bbb.h263");
        Movie movie = new Movie();
        movie.a((Track) new H263TrackImpl(fileDataSourceImpl));
        new DefaultMp4Builder().a(movie).a(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void c(String[] strArr) throws IOException {
        ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) Path.a((Container) new IsoFile((ReadableByteChannel) new FileInputStream("C:\\content\\bbb.mp4").getChannel()), "/moov[0]/trak[0]/mdia[0]/minf[0]/stbl[0]/stsd[0]/mp4v[0]/esds[0]");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        eSDescriptorBox.b(Channels.newChannel(byteArrayOutputStream));
        System.err.println(Hex.a(byteArrayOutputStream.toByteArray()));
        System.err.println(eSDescriptorBox.h());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        eSDescriptorBox.b(Channels.newChannel(byteArrayOutputStream2));
        System.err.println(Hex.a(byteArrayOutputStream2.toByteArray()));
    }

    private int b(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        if (!bitReaderBuffer.a()) {
            return 0;
        }
        int a2 = bitReaderBuffer.a(4);
        bitReaderBuffer.a(3);
        return a2;
    }

    private void a(ByteBuffer byteBuffer, int i, VisualSampleEntry visualSampleEntry) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        bitReaderBuffer.a();
        bitReaderBuffer.a(8);
        if (bitReaderBuffer.a()) {
            i = bitReaderBuffer.a(4);
            bitReaderBuffer.a(3);
        }
        if (bitReaderBuffer.a(4) == 15) {
            bitReaderBuffer.a(8);
            bitReaderBuffer.a(8);
        }
        if (bitReaderBuffer.a()) {
            bitReaderBuffer.a(2);
            bitReaderBuffer.a();
            if (bitReaderBuffer.a()) {
                throw new RuntimeException("Implemented when needed");
            }
        }
        int a2 = bitReaderBuffer.a(2);
        if (a2 == this.n && i != 1) {
            bitReaderBuffer.a(4);
        }
        bitReaderBuffer.a();
        this.t = bitReaderBuffer.a(16);
        bitReaderBuffer.a();
        if (bitReaderBuffer.a()) {
            u.info("Fixed Frame Rate");
            int i2 = 0;
            while (this.t >= (1 << i2)) {
                i2++;
            }
            this.s = bitReaderBuffer.a(i2);
        }
        if (a2 == this.m) {
            throw new RuntimeException("Please implmenet me");
        } else if (a2 == this.k) {
            bitReaderBuffer.a();
            visualSampleEntry.b(bitReaderBuffer.a(13));
            bitReaderBuffer.a();
            visualSampleEntry.c(bitReaderBuffer.a(13));
            bitReaderBuffer.a();
        }
    }

    /* access modifiers changed from: protected */
    public Sample a(List<? extends ByteBuffer> list) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[]{0, 0, 1});
        ByteBuffer[] byteBufferArr = new ByteBuffer[(list.size() * 2)];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 2;
            byteBufferArr[i2] = wrap;
            byteBufferArr[i2 + 1] = (ByteBuffer) list.get(i);
        }
        return new SampleImpl(byteBufferArr);
    }

    public SampleDescriptionBox n() {
        return this.o;
    }

    public List<Sample> l() {
        return this.p;
    }
}
