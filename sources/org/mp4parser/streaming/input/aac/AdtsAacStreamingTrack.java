package org.mp4parser.streaming.input.aac;

import com.google.android.exoplayer2.C;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.AudioSpecificConfig;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.SLConfigDescriptor;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.streaming.TrackExtension;
import org.mp4parser.streaming.extensions.DefaultSampleFlagsTrackExtension;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;
import org.mp4parser.streaming.input.AbstractStreamingTrack;
import org.mp4parser.streaming.input.StreamingSampleImpl;

public class AdtsAacStreamingTrack extends AbstractStreamingTrack implements Callable<Void> {
    static final /* synthetic */ boolean f = (!AdtsAacStreamingTrack.class.desiredAssertionStatus());
    private static Map<Integer, Integer> g = new HashMap();
    private static Logger h = Logger.getLogger(AdtsAacStreamingTrack.class.getName());
    CountDownLatch d = new CountDownLatch(1);
    SampleDescriptionBox e = null;
    private InputStream i;
    private boolean j;
    private AdtsHeader k;
    private String l = C.LANGUAGE_UNDETERMINED;
    private long m;
    private long n;

    public String b() {
        return "soun";
    }

    static {
        g.put(96000, 0);
        g.put(88200, 1);
        g.put(64000, 2);
        g.put(Integer.valueOf(MIOTAudioModule.SAMPLING_RATE), 3);
        g.put(44100, 4);
        g.put(Integer.valueOf(AsrRequest.d), 5);
        g.put(24000, 6);
        g.put(22050, 7);
        g.put(Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K), 8);
        g.put(Integer.valueOf(TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET), 9);
        g.put(11025, 10);
        g.put(8000, 11);
        g.put(0, 96000);
        g.put(1, 88200);
        g.put(2, 64000);
        g.put(3, Integer.valueOf(MIOTAudioModule.SAMPLING_RATE));
        g.put(4, 44100);
        g.put(5, Integer.valueOf(AsrRequest.d));
        g.put(6, 24000);
        g.put(7, 22050);
        g.put(8, Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K));
        g.put(9, Integer.valueOf(TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET));
        g.put(10, 11025);
        g.put(11, 8000);
    }

    public AdtsAacStreamingTrack(InputStream inputStream, long j2, long j3) {
        this.m = j2;
        this.n = j3;
        if (f || inputStream != null) {
            this.i = inputStream;
            DefaultSampleFlagsTrackExtension defaultSampleFlagsTrackExtension = new DefaultSampleFlagsTrackExtension();
            defaultSampleFlagsTrackExtension.a(2);
            defaultSampleFlagsTrackExtension.b(2);
            defaultSampleFlagsTrackExtension.c(2);
            defaultSampleFlagsTrackExtension.d(2);
            defaultSampleFlagsTrackExtension.a(false);
            a((TrackExtension) defaultSampleFlagsTrackExtension);
            return;
        }
        throw new AssertionError();
    }

    public boolean e() {
        return this.j;
    }

    public synchronized SampleDescriptionBox d() {
        f();
        if (this.e == null) {
            this.e = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.c);
            if (this.k.g == 7) {
                audioSampleEntry.b(8);
            } else {
                audioSampleEntry.b(this.k.g);
            }
            audioSampleEntry.a((long) this.k.f);
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
            decoderConfigDescriptor.d(1536);
            decoderConfigDescriptor.a(this.n);
            decoderConfigDescriptor.b(this.m);
            AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
            audioSpecificConfig.b(2);
            audioSpecificConfig.c(this.k.f4070a);
            audioSpecificConfig.e(this.k.g);
            decoderConfigDescriptor.a(audioSpecificConfig);
            eSDescriptor.a(decoderConfigDescriptor);
            eSDescriptorBox.a(eSDescriptor);
            audioSampleEntry.a((Box) eSDescriptorBox);
            this.e.a((Box) audioSampleEntry);
        }
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        try {
            this.d.await();
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public long a() {
        f();
        return (long) this.k.f;
    }

    public String c() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public void close() throws IOException {
        this.j = true;
        this.i.close();
    }

    private AdtsHeader a(InputStream inputStream) throws IOException {
        AdtsHeader adtsHeader = new AdtsHeader();
        int read = inputStream.read() << 4;
        int read2 = inputStream.read();
        if (read2 == -1) {
            return null;
        }
        if (read + (read2 >> 4) == 4095) {
            adtsHeader.b = (read2 & 8) >> 3;
            adtsHeader.c = (read2 & 6) >> 1;
            adtsHeader.d = read2 & 1;
            int read3 = inputStream.read();
            adtsHeader.e = ((read3 & 192) >> 6) + 1;
            adtsHeader.f4070a = (read3 & 60) >> 2;
            if (f || adtsHeader.f4070a != 15) {
                adtsHeader.f = g.get(Integer.valueOf(adtsHeader.f4070a)).intValue();
                adtsHeader.g = (read3 & 1) << 2;
                int read4 = inputStream.read();
                adtsHeader.g += (read4 & 192) >> 6;
                adtsHeader.h = (read4 & 32) >> 5;
                adtsHeader.i = (read4 & 16) >> 4;
                adtsHeader.j = (read4 & 8) >> 3;
                adtsHeader.k = (read4 & 4) >> 2;
                adtsHeader.l = (read4 & 3) << 9;
                adtsHeader.l += inputStream.read() << 3;
                int read5 = inputStream.read();
                adtsHeader.l += (read5 & 224) >> 5;
                adtsHeader.m = (read5 & 31) << 6;
                int read6 = inputStream.read();
                adtsHeader.m += (read6 & 252) >> 2;
                adtsHeader.n = (read6 & 3) + 1;
                if (adtsHeader.n == 1) {
                    if (adtsHeader.d == 0) {
                        inputStream.read();
                        inputStream.read();
                    }
                    return adtsHeader;
                }
                throw new IOException("This muxer can only work with 1 AAC frame per ADTS frame");
            }
            throw new AssertionError();
        }
        throw new IOException("Expected Start Word 0xfff");
    }

    /* renamed from: g */
    public Void call() throws Exception {
        while (true) {
            try {
                AdtsHeader a2 = a(this.i);
                if (a2 == null) {
                    return null;
                }
                if (this.k == null) {
                    this.k = a2;
                    this.d.countDown();
                }
                byte[] bArr = new byte[(a2.l - a2.a())];
                int i2 = 0;
                while (i2 < bArr.length) {
                    int read = this.i.read(bArr, i2, bArr.length - i2);
                    if (read >= 0) {
                        i2 += read;
                    } else {
                        throw new EOFException();
                    }
                }
                this.c.a(new StreamingSampleImpl(ByteBuffer.wrap(bArr), 1024), this);
            } catch (EOFException unused) {
                h.info("Done reading ADTS AAC file.");
                return null;
            }
        }
    }

    public String toString() {
        TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) a(TrackIdTrackExtension.class);
        if (trackIdTrackExtension == null) {
            return "AdtsAacStreamingTrack{}";
        }
        return "AdtsAacStreamingTrack{trackId=" + trackIdTrackExtension.a() + "}";
    }
}
