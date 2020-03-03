package com.xiaomi.ai.streamplayer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.google.android.exoplayer2.util.MimeTypes;
import com.xiaomi.ai.streamplayer.a;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.miot.support.monitor.core.tasks.TaskConfig;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

public class d extends a {
    private static final String e = "Mp3HardDecoder";
    private static final String g = "OMX.google.mp3.decoder";

    /* renamed from: a  reason: collision with root package name */
    private PipedOutputStream f9938a;
    private PipedInputStream b;
    private i c = new b();
    /* access modifiers changed from: private */
    public boolean d;
    private MediaCodec f;

    private class a implements j {
        private boolean b;

        private a() {
        }

        public void a(i iVar) {
        }

        public void a(i iVar, Object obj, byte[] bArr) {
            if (!d.this.d) {
                d.this.a(bArr);
                this.b = true;
                return;
            }
            d.this.c();
        }

        public void a(i iVar, String str) {
            d.this.c();
        }

        public void b(i iVar) {
            if (d.this.d || !this.b) {
                d.this.c();
            } else {
                d.this.f();
            }
        }
    }

    public d() {
        this.c.a((j) new a());
        this.b = new PipedInputStream(TaskConfig.w);
        try {
            this.f9938a = new PipedOutputStream(this.b);
        } catch (IOException e2) {
            Log.a(e, "Mp3HardDecoder ", e2);
        }
        this.c.a(this.b);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c.a();
        try {
            this.f = MediaCodec.createByCodecName(g);
        } catch (IllegalArgumentException e2) {
            try {
                Log.a(e, " start createByCodecName ", e2);
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }
        if (this.f == null) {
            Log.a(e, "OMX.google.mp3.decoder not get");
            this.f = MediaCodec.createDecoderByType(MimeTypes.AUDIO_MPEG);
        }
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString(IMediaFormat.KEY_MIME, MimeTypes.AUDIO_MPEG);
        this.f.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        this.f.start();
    }

    /* access modifiers changed from: package-private */
    public void a(byte[] bArr) {
        try {
            int dequeueInputBuffer = this.f.dequeueInputBuffer(-1);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer[] inputBuffers = this.f.getInputBuffers();
                if (inputBuffers.length > dequeueInputBuffer) {
                    ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                    byteBuffer.clear();
                    byteBuffer.put(bArr);
                    this.f.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
                    return;
                }
                Log.a(e, " putBuffer byteBuffers.length > inputBufIndex false");
                return;
            }
            throw new RuntimeException("dequeueInputBuffer-1");
        } catch (IllegalStateException e2) {
            Log.a(e, "release ", e2);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (bArr != null && i + i2 <= bArr.length && i2 > 0) {
            try {
                this.f9938a.write(bArr, i, i2);
            } catch (IOException e2) {
                Log.a(e, "putEncodedBuffer ", e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        try {
            this.f9938a.close();
        } catch (IOException e2) {
            Log.a(e, "release ", e2);
        }
        try {
            this.b.close();
        } catch (IOException e3) {
            Log.a(e, "release ", e3);
        }
        try {
            this.f.stop();
        } catch (IllegalStateException e4) {
            Log.a(e, "stop ", e4);
        }
        this.f.release();
        Log.a(e, "Mp3HardDecoder release");
    }

    public void c() {
        d();
        this.c.b();
        this.d = true;
    }

    public void d() {
        try {
            this.f9938a.close();
        } catch (IOException e2) {
            Log.a(e, "release " + e2);
        }
    }

    public a.C0083a e() {
        if (this.d) {
            return null;
        }
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.f.dequeueOutputBuffer(bufferInfo, 0);
            a.C0083a aVar = new a.C0083a();
            if (dequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.f.getOutputFormat();
                aVar.c = 16;
                aVar.b = outputFormat.getInteger("channel-count");
                aVar.f9934a = outputFormat.getInteger("sample-rate");
            }
            if (dequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = this.f.getOutputBuffers()[dequeueOutputBuffer];
                byte[] bArr = new byte[bufferInfo.size];
                aVar.d = bufferInfo.flags == 4;
                byteBuffer.get(bArr);
                aVar.e = bArr;
                byteBuffer.clear();
                this.f.releaseOutputBuffer(dequeueOutputBuffer, false);
            }
            return aVar;
        } catch (IllegalStateException e2) {
            Log.a(e, "getDecodedBuffer ", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        try {
            int dequeueInputBuffer = this.f.dequeueInputBuffer(-1);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer[] inputBuffers = this.f.getInputBuffers();
                if (inputBuffers.length > dequeueInputBuffer) {
                    inputBuffers[dequeueInputBuffer].clear();
                    this.f.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                    return;
                }
                Log.a(e, " putEofMsg byteBuffers.length > inputBufIndex false");
                return;
            }
            throw new RuntimeException("dequeueInputBuffer-1");
        } catch (IllegalStateException e2) {
            Log.a(e, "release ", e2);
        }
    }
}
