package org.mp4parser.muxer.tracks.mjpeg;

import com.taobao.weex.el.parse.Operators;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ObjectDescriptorFactory;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.tools.Hex;

public class OneJpegPerIframe extends AbstractTrack {
    File[] d;
    TrackMetaData e = new TrackMetaData();
    long[] f;
    SampleDescriptionBox g;
    long[] h;

    public void close() throws IOException {
    }

    public String p() {
        return "vide";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OneJpegPerIframe(String str, File[] fileArr, Track track) throws IOException {
        super(str);
        File[] fileArr2 = fileArr;
        this.d = fileArr2;
        if (track.b().length == fileArr2.length) {
            BufferedImage read = ImageIO.read(fileArr2[0]);
            this.e.a((double) read.getWidth());
            this.e.b((double) read.getHeight());
            this.e.a(track.o().b());
            long[] m = track.m();
            long[] b = track.b();
            this.f = new long[b.length];
            boolean z = true;
            long j = 0;
            int i = 1;
            for (int i2 = 1; i2 < m.length; i2++) {
                if (i < b.length && ((long) i2) == b[i]) {
                    this.f[i - 1] = j;
                    i++;
                    j = 0;
                }
                j += m[i2];
            }
            this.f[this.f.length - 1] = j;
            this.g = new SampleDescriptionBox();
            VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.f3955a);
            this.g.a((Box) visualSampleEntry);
            ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
            eSDescriptorBox.c(ByteBuffer.wrap(Hex.a("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102")));
            eSDescriptorBox.a((ESDescriptor) ObjectDescriptorFactory.a(-1, ByteBuffer.wrap(Hex.a("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102"))));
            visualSampleEntry.a((Box) eSDescriptorBox);
            this.h = new long[fileArr2.length];
            int i3 = 0;
            while (i3 < this.h.length) {
                int i4 = i3 + 1;
                this.h[i3] = (long) i4;
                i3 = i4;
            }
            double d2 = 0.0d;
            boolean z2 = true;
            for (Edit next : track.g()) {
                if (next.c() == -1 && !z) {
                    throw new RuntimeException("Cannot accept edit list for processing (1)");
                } else if (next.c() >= 0 && !z2) {
                    throw new RuntimeException("Cannot accept edit list for processing (2)");
                } else if (next.c() == -1) {
                    d2 += next.b();
                } else {
                    double c = (double) next.c();
                    double a2 = (double) next.a();
                    Double.isNaN(c);
                    Double.isNaN(a2);
                    d2 -= c / a2;
                    z = false;
                    z2 = false;
                }
            }
            if (track.a() != null && track.a().size() > 0) {
                int[] a3 = CompositionTimeToSample.a(track.a());
                long j2 = 0;
                int i5 = 0;
                while (i5 < a3.length && i5 < 50) {
                    a3[i5] = (int) (((long) a3[i5]) + j2);
                    j2 += track.m()[i5];
                    i5++;
                }
                Arrays.sort(a3);
                double d3 = (double) a3[0];
                double b2 = (double) track.o().b();
                Double.isNaN(d3);
                Double.isNaN(b2);
                d2 += d3 / b2;
            }
            if (d2 < 0.0d) {
                List<Edit> g2 = g();
                double b3 = (double) o().b();
                Double.isNaN(b3);
                long j3 = (long) ((-d2) * b3);
                long b4 = o().b();
                double e2 = (double) e();
                double b5 = (double) o().b();
                Double.isNaN(e2);
                Double.isNaN(b5);
                g2.add(new Edit(j3, b4, 1.0d, e2 / b5));
            } else if (d2 > 0.0d) {
                g().add(new Edit(-1, o().b(), 1.0d, d2));
                List<Edit> g3 = g();
                long b6 = o().b();
                double e3 = (double) e();
                double b7 = (double) o().b();
                Double.isNaN(e3);
                Double.isNaN(b7);
                g3.add(new Edit(0, b6, 1.0d, e3 / b7));
            }
        } else {
            throw new RuntimeException("Number of sync samples doesn't match the number of stills (" + track.b().length + " vs. " + fileArr2.length + Operators.BRACKET_END_STR);
        }
    }

    public SampleDescriptionBox n() {
        return this.g;
    }

    public long[] m() {
        return this.f;
    }

    public TrackMetaData o() {
        return this.e;
    }

    public long[] b() {
        return this.h;
    }

    public List<Sample> l() {
        return new AbstractList<Sample>() {
            public int size() {
                return OneJpegPerIframe.this.d.length;
            }

            /* renamed from: a */
            public Sample get(final int i) {
                return new Sample() {

                    /* renamed from: a  reason: collision with root package name */
                    ByteBuffer f4055a = null;

                    public void a(WritableByteChannel writableByteChannel) throws IOException {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.d[i], "r");
                        randomAccessFile.getChannel().transferTo(0, randomAccessFile.length(), writableByteChannel);
                        randomAccessFile.close();
                    }

                    public long a() {
                        return OneJpegPerIframe.this.d[i].length();
                    }

                    public ByteBuffer b() {
                        if (this.f4055a == null) {
                            try {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.d[i], "r");
                                this.f4055a = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        return this.f4055a;
                    }
                };
            }
        };
    }
}
