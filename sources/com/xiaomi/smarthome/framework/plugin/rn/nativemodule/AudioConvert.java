package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
public class AudioConvert {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17300a = "AudioConvert";
    private String b;
    private String c;
    private String d;
    private MediaCodec e;
    private MediaCodec f;
    private MediaExtractor g;
    private ByteBuffer[] h;
    private ByteBuffer[] i;
    private ByteBuffer[] j;
    private ByteBuffer[] k;
    private MediaCodec.BufferInfo l;
    private MediaCodec.BufferInfo m;
    private FileOutputStream n;
    private BufferedOutputStream o;
    private FileInputStream p;
    private BufferedInputStream q;
    /* access modifiers changed from: private */
    public ArrayList<byte[]> r;
    /* access modifiers changed from: private */
    public OnCompleteListener s;
    private OnProgressListener t;
    /* access modifiers changed from: private */
    public long u;
    /* access modifiers changed from: private */
    public long v;
    /* access modifiers changed from: private */
    public boolean w = false;

    public interface OnCompleteListener {
        void a();
    }

    public interface OnProgressListener {
        void a();
    }

    private void e() {
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public void a() {
        if (this.b == null) {
            throw new IllegalArgumentException("encodeType can't be null");
        } else if (this.c == null) {
            throw new IllegalArgumentException("srcPath can't be null");
        } else if (this.d != null) {
            try {
                this.n = new FileOutputStream(new File(this.d));
                this.o = new BufferedOutputStream(this.n, 204800);
                this.u = new File(this.c).length();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.r = new ArrayList<>();
            d();
            try {
                MediaFormat createAudioFormat = MediaFormat.createAudioFormat(this.b, 44100, 2);
                createAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, 96000);
                createAudioFormat.setInteger("aac-profile", 2);
                createAudioFormat.setInteger("max-input-size", 102400);
                this.f = MediaCodec.createEncoderByType(this.b);
                this.f.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            if (this.f == null) {
                Log.e(f17300a, "create mediaEncode failed");
                return;
            }
            this.j = this.f.getInputBuffers();
            this.k = this.f.getOutputBuffers();
            this.m = new MediaCodec.BufferInfo();
            this.f.start();
        } else {
            throw new IllegalArgumentException("dstPath can't be null");
        }
    }

    private void d() {
        try {
            this.g = new MediaExtractor();
            this.g.setDataSource(this.c);
            int i2 = 0;
            while (true) {
                if (i2 >= this.g.getTrackCount()) {
                    break;
                }
                MediaFormat trackFormat = this.g.getTrackFormat(i2);
                String string = trackFormat.getString(IMediaFormat.KEY_MIME);
                if (string.startsWith("audio")) {
                    this.g.selectTrack(i2);
                    this.e = MediaCodec.createDecoderByType(string);
                    this.e.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
                    break;
                }
                i2++;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.e == null) {
            Log.e(f17300a, "create mediaDecode failed");
            return;
        }
        this.e.start();
        this.h = this.e.getInputBuffers();
        this.i = this.e.getOutputBuffers();
        this.l = new MediaCodec.BufferInfo();
        b("buffers:" + this.h.length);
    }

    public void b() {
        b("start");
        new Thread(new DecodeRunnable()).start();
        new Thread(new EncodeRunnable()).start();
    }

    private void a(byte[] bArr) {
        synchronized (AudioConvert.class) {
            this.r.add(bArr);
        }
    }

    private byte[] f() {
        synchronized (AudioConvert.class) {
            b("getPCM:" + this.r.size());
            if (this.r.isEmpty()) {
                return null;
            }
            byte[] bArr = this.r.get(0);
            this.r.remove(bArr);
            return bArr;
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        for (int i2 = 0; i2 < this.h.length - 1; i2++) {
            int dequeueInputBuffer = this.e.dequeueInputBuffer(-1);
            if (dequeueInputBuffer < 0) {
                this.w = true;
                return;
            }
            ByteBuffer byteBuffer = this.h[dequeueInputBuffer];
            byteBuffer.clear();
            int readSampleData = this.g.readSampleData(byteBuffer, 0);
            if (readSampleData < 0) {
                this.w = true;
            } else {
                this.e.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, 0, 0);
                this.g.advance();
                this.v += (long) readSampleData;
            }
        }
        int dequeueOutputBuffer = this.e.dequeueOutputBuffer(this.l, 10000);
        while (dequeueOutputBuffer >= 0) {
            ByteBuffer byteBuffer2 = this.i[dequeueOutputBuffer];
            byte[] bArr = new byte[this.l.size];
            byteBuffer2.get(bArr);
            byteBuffer2.clear();
            a(bArr);
            this.e.releaseOutputBuffer(dequeueOutputBuffer, false);
            dequeueOutputBuffer = this.e.dequeueOutputBuffer(this.l, 10000);
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        byte[] f2;
        for (int i2 = 0; i2 < this.j.length - 1 && (f2 = f()) != null; i2++) {
            int dequeueInputBuffer = this.f.dequeueInputBuffer(-1);
            ByteBuffer byteBuffer = this.j[dequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.limit(f2.length);
            byteBuffer.put(f2);
            this.f.queueInputBuffer(dequeueInputBuffer, 0, f2.length, 0, 0);
        }
        int dequeueOutputBuffer = this.f.dequeueOutputBuffer(this.m, 10000);
        while (dequeueOutputBuffer >= 0) {
            int i3 = this.m.size;
            int i4 = i3 + 7;
            ByteBuffer byteBuffer2 = this.k[dequeueOutputBuffer];
            byteBuffer2.position(this.m.offset);
            byteBuffer2.limit(this.m.offset + i3);
            byte[] bArr = new byte[i4];
            a(bArr, i4);
            byteBuffer2.get(bArr, 7, i3);
            byteBuffer2.position(this.m.offset);
            try {
                this.o.write(bArr, 0, bArr.length);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f.releaseOutputBuffer(dequeueOutputBuffer, false);
            dequeueOutputBuffer = this.f.dequeueOutputBuffer(this.m, 10000);
        }
    }

    private void a(byte[] bArr, int i2) {
        bArr[0] = -1;
        bArr[1] = -7;
        bArr[2] = (byte) 80;
        bArr[3] = (byte) (128 + (i2 >> 11));
        bArr[4] = (byte) ((i2 & 2047) >> 3);
        bArr[5] = (byte) (((i2 & 7) << 5) + 31);
        bArr[6] = -4;
    }

    /* JADX INFO: finally extract failed */
    public void c() {
        try {
            if (this.o != null) {
                this.o.flush();
            }
            if (this.o != null) {
                try {
                    this.o.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (Throwable th) {
                    this.o = null;
                    throw th;
                }
                this.o = null;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            if (this.o != null) {
                try {
                    this.o.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                } catch (Throwable th2) {
                    this.o = null;
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            if (this.o != null) {
                try {
                    this.o.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                } catch (Throwable th4) {
                    this.o = null;
                    throw th4;
                }
                this.o = null;
            }
            throw th3;
        }
        try {
            if (this.n != null) {
                this.n.close();
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        } catch (Throwable th5) {
            this.n = null;
            throw th5;
        }
        this.n = null;
        if (this.f != null) {
            this.f.stop();
            this.f.release();
            this.f = null;
        }
        if (this.e != null) {
            this.e.stop();
            this.e.release();
            this.e = null;
        }
        if (this.g != null) {
            this.g.release();
            this.g = null;
        }
        if (this.s != null) {
            this.s = null;
        }
        if (this.t != null) {
            this.t = null;
        }
        b("release");
    }

    private class DecodeRunnable implements Runnable {
        private DecodeRunnable() {
        }

        public void run() {
            while (!AudioConvert.this.w) {
                AudioConvert.this.g();
            }
        }
    }

    private class EncodeRunnable implements Runnable {
        private EncodeRunnable() {
        }

        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                if (AudioConvert.this.w && AudioConvert.this.r.isEmpty()) {
                    break;
                }
                AudioConvert.this.h();
            }
            if (AudioConvert.this.s != null) {
                AudioConvert.this.s.a();
            }
            AudioConvert audioConvert = AudioConvert.this;
            audioConvert.b("size:" + AudioConvert.this.u + " decodeSize:" + AudioConvert.this.v + "time:" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public void a(OnCompleteListener onCompleteListener) {
        this.s = onCompleteListener;
    }

    public void a(OnProgressListener onProgressListener) {
        this.t = onProgressListener;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Log.e("AudioCodec", str);
    }
}
