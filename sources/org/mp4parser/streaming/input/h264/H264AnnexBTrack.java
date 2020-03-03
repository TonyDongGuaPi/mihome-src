package org.mp4parser.streaming.input.h264;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;

public class H264AnnexBTrack extends H264NalConsumingTrack implements Callable<Void> {
    static final /* synthetic */ boolean d = (!H264AnnexBTrack.class.desiredAssertionStatus());
    private InputStream v;

    public H264AnnexBTrack(InputStream inputStream) throws IOException {
        if (d || inputStream != null) {
            this.v = new BufferedInputStream(inputStream);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public Void call() throws IOException, InterruptedException {
        NalStreamTokenizer nalStreamTokenizer = new NalStreamTokenizer(this.v);
        while (true) {
            byte[] a2 = nalStreamTokenizer.a();
            if (a2 != null) {
                b(ByteBuffer.wrap(a2));
            } else {
                a(a((List<ByteBuffer>) this.s, this.t.f4072a, this.u), true, true);
                return null;
            }
        }
    }

    public String toString() {
        TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) a(TrackIdTrackExtension.class);
        if (trackIdTrackExtension == null) {
            return "H264AnnexBTrack{}";
        }
        return "H264AnnexBTrack{trackId=" + trackIdTrackExtension.a() + "}";
    }

    public static class NalStreamTokenizer {
        private static final Logger c = Logger.getLogger(NalStreamTokenizer.class.getName());

        /* renamed from: a  reason: collision with root package name */
        MyByteArrayOutputStream f4071a = new MyByteArrayOutputStream();
        int b = 0;
        private InputStream d;

        public NalStreamTokenizer(InputStream inputStream) {
            this.d = inputStream;
        }

        public byte[] a() throws IOException {
            if (c.isLoggable(Level.FINEST)) {
                c.finest("getNext() called");
            }
            while (true) {
                int read = this.d.read();
                if (read == -1) {
                    byte[] byteArray = this.f4071a.toByteArray();
                    this.f4071a.reset();
                    if (byteArray.length > 0) {
                        return byteArray;
                    }
                    return null;
                } else if (this.b == 2 && read == 3) {
                    this.b = 0;
                } else {
                    this.f4071a.write(read);
                    if (this.b == 0 && read == 0) {
                        this.b = 1;
                    } else if (this.b == 1 && read == 0) {
                        this.b = 2;
                    } else if (this.b == 2 && read == 0) {
                        byte[] a2 = this.f4071a.a();
                        this.f4071a.reset();
                        if (a2 != null) {
                            return a2;
                        }
                    } else if (this.b == 2 && read == 1) {
                        byte[] a3 = this.f4071a.a();
                        this.f4071a.reset();
                        this.b = 0;
                        if (a3 != null) {
                            return a3;
                        }
                    } else if (this.b != 0) {
                        this.b = 0;
                    }
                }
            }
        }
    }

    static class MyByteArrayOutputStream extends ByteArrayOutputStream {
        MyByteArrayOutputStream() {
        }

        public byte[] a() {
            if (this.count <= 3) {
                return null;
            }
            return Arrays.copyOf(this.buf, this.count - 3 > 0 ? this.count - 3 : 0);
        }
    }
}
