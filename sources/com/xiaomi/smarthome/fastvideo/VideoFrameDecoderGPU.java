package com.xiaomi.smarthome.fastvideo;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.view.Surface;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.fastvideo.AndroidH26xDecoderUtil;
import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecUtil;
import java.nio.ByteBuffer;

@TargetApi(16)
public class VideoFrameDecoderGPU extends VideoFrameDecoder {
    private static final String h = "VideoGPU";
    /* access modifiers changed from: private */
    public volatile Object i = new Object();
    private Photo j;
    private Photo k;
    private int l;
    /* access modifiers changed from: private */
    public volatile boolean m = false;
    private GlslFilter n;
    private float[] o = new float[16];
    private volatile VideoDecodeThread p;
    private AndroidH26xDecoderUtil.DecoderProperties q;
    /* access modifiers changed from: private */
    public int r = 0;

    static /* synthetic */ int c(VideoFrameDecoderGPU videoFrameDecoderGPU) {
        int i2 = videoFrameDecoderGPU.r;
        videoFrameDecoderGPU.r = i2 + 1;
        return i2;
    }

    public void e() {
        super.e();
        this.n = new GlslFilter(a().getContext());
        this.n.a((int) GlslFilter.e);
        this.n.d();
        this.l = RendererUtils.a();
        GLES20.glBindTexture(GlslFilter.e, this.l);
        RendererUtils.a("glBindTexture mTextureID");
        GLES20.glTexParameterf(GlslFilter.e, 10241, 9728.0f);
        GLES20.glTexParameterf(GlslFilter.e, 10240, 9729.0f);
        GLES20.glTexParameteri(GlslFilter.e, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.e, 10243, 33071);
        synchronized (this.i) {
            this.m = false;
        }
        RendererUtils.a("surfaceCreated");
        this.p = new VideoDecodeThread();
        this.p.start();
    }

    public VideoFrameDecoderGPU(VideoGlSurfaceView videoGlSurfaceView) {
        super(videoGlSurfaceView);
    }

    public VideoFrameDecoderGPU(VideoGlSurfaceView videoGlSurfaceView, AndroidH26xDecoderUtil.DecoderProperties decoderProperties) {
        super(videoGlSurfaceView);
        this.q = decoderProperties;
        this.g = decoderProperties.c;
    }

    public void g() {
        synchronized (this.i) {
            super.g();
            try {
                if (this.m) {
                    this.m = false;
                    if (this.p == null) {
                        LogUtil.c(h, "mVideoDecodeThread == null");
                        return;
                    }
                    int a2 = this.p.a();
                    int b = this.p.b();
                    if (a2 != 0) {
                        if (b != 0) {
                            if (this.j == null) {
                                this.j = Photo.a(a2, b);
                            } else {
                                this.j.c(a2, b);
                            }
                            if (this.k == null) {
                                this.k = new Photo(this.l, a2, b);
                            }
                            this.p.a(this.o);
                            this.n.b(this.o);
                            this.n.a(this.k, this.j);
                            a(this.j);
                        }
                    }
                    LogUtil.c(h, "videoWith:" + a2 + " videoHeight:" + b);
                }
            } catch (Exception e) {
                LogUtil.c(h, "drawFrame e:" + e.getLocalizedMessage());
            }
        }
    }

    public void f() {
        super.f();
        if (this.p != null) {
            this.p.g();
            this.p = null;
        }
        RendererUtils.a(this.l);
        if (this.n != null) {
            this.n.e();
        }
        if (this.j != null) {
            this.j.e();
            this.j = null;
        }
    }

    class VideoDecodeThread extends WorkThread {
        private static final int j = 2000;
        private static final int k = 2000;

        /* renamed from: a  reason: collision with root package name */
        int f15901a;
        int b;
        VideoFrame c;
        MediaCodec.BufferInfo d = new MediaCodec.BufferInfo();
        volatile boolean e = false;
        Surface f;
        SurfaceTexture g;
        private AndroidH26xDecoderUtil.DecoderProperties l;
        private MediaCodec m;
        private ByteBuffer[] n;
        private boolean o = false;

        public VideoDecodeThread() {
            super("VideoDecodeThread");
            LogUtil.c(VideoFrameDecoderGPU.h, "VideoDecodeThread start");
        }

        public VideoDecodeThread(AndroidH26xDecoderUtil.DecoderProperties decoderProperties) {
            super("VideoDecodeThread");
            this.l = decoderProperties;
            LogUtil.c(VideoFrameDecoderGPU.h, "VideoDecodeThread start");
        }

        public synchronized int a() {
            return VideoFrameDecoderGPU.this.f15900a;
        }

        public synchronized int b() {
            return VideoFrameDecoderGPU.this.b;
        }

        /* access modifiers changed from: protected */
        public void c() {
            if (this.m != null) {
                try {
                    this.m.flush();
                } catch (Exception unused) {
                }
            }
        }

        /* access modifiers changed from: protected */
        public int d() throws InterruptedException {
            VideoFrame videoFrame;
            ByteBuffer byteBuffer;
            if (!this.i) {
                return 0;
            }
            if (this.c != null) {
                videoFrame = this.c;
                this.c = null;
            } else {
                videoFrame = VideoFrameDecoderGPU.this.a(this.m == null);
            }
            if (!this.i) {
                LogUtil.c(VideoFrameDecoderGPU.h, "!mIsRunning");
                return 0;
            } else if (videoFrame == null || videoFrame.data == null) {
                LogUtil.c(VideoFrameDecoderGPU.h, "frame == null || frame.data == null");
                return 0;
            } else if (this.e) {
                LogUtil.c(VideoFrameDecoderGPU.h, "mInitialError");
                return 0;
            } else if (this.m == null && !videoFrame.isIFrame) {
                LogUtil.c(VideoFrameDecoderGPU.h, "decoder == null && !frame.isIFrame");
                return 0;
            } else if (videoFrame.width < 0 || videoFrame.height < 0) {
                LogUtil.c(VideoFrameDecoderGPU.h, "frame.width < 0 || frame.height < 0");
                return 0;
            } else {
                try {
                    if (!(this.m != null && videoFrame.width == this.f15901a && videoFrame.height == this.b && VideoFrameDecoderGPU.this.g == videoFrame.videoType)) {
                        LogUtil.c(VideoFrameDecoderGPU.h, "release media decoder, isIFrame:" + videoFrame.isIFrame + " (" + this.f15901a + "," + this.b + ")-->(" + videoFrame.width + "," + videoFrame.height + "," + videoFrame.videoType + Operators.BRACKET_END_STR);
                        this.f15901a = videoFrame.width;
                        this.b = videoFrame.height;
                        VideoFrameDecoderGPU.this.g = videoFrame.videoType;
                        j();
                        a(this.f15901a, this.b);
                    }
                    if (this.m == null) {
                        LogUtil.c(VideoFrameDecoderGPU.h, "decoder == null");
                        return 0;
                    } else if (!this.o || videoFrame.isIFrame) {
                        int dequeueInputBuffer = this.m.dequeueInputBuffer(2000);
                        if (dequeueInputBuffer >= 0) {
                            if (Build.VERSION.SDK_INT < 21) {
                                byteBuffer = this.n[dequeueInputBuffer];
                            } else {
                                byteBuffer = this.m.getInputBuffer(dequeueInputBuffer);
                            }
                            byteBuffer.rewind();
                            byteBuffer.put(videoFrame.data);
                            this.m.queueInputBuffer(dequeueInputBuffer, 0, videoFrame.data.length, videoFrame.timeStamp * 1000, 0);
                            this.o = false;
                        } else {
                            LogUtil.c(VideoFrameDecoderGPU.h, "dequeue input buffer time out");
                            this.c = videoFrame;
                        }
                        while (this.i) {
                            long currentTimeMillis = System.currentTimeMillis();
                            int dequeueOutputBuffer = this.m.dequeueOutputBuffer(this.d, 2000);
                            LogUtil.c(VideoFrameDecoderGPU.h, "queueOutputBuffer - " + (System.currentTimeMillis() - currentTimeMillis));
                            if (dequeueOutputBuffer >= 0) {
                                MediaFormat outputFormat = this.m.getOutputFormat();
                                LogUtil.c(VideoFrameDecoderGPU.h, "outformat:" + outputFormat.toString());
                                VideoFrameDecoderGPU.this.f15900a = outputFormat.getInteger("width");
                                VideoFrameDecoderGPU.this.b = outputFormat.getInteger("height");
                                if (outputFormat.containsKey("crop-left") && outputFormat.containsKey("crop-right")) {
                                    VideoFrameDecoderGPU.this.f15900a = (outputFormat.getInteger("crop-right") + 1) - outputFormat.getInteger("crop-left");
                                }
                                if (outputFormat.containsKey("crop-top") && outputFormat.containsKey("crop-bottom")) {
                                    VideoFrameDecoderGPU.this.b = (outputFormat.getInteger("crop-bottom") + 1) - outputFormat.getInteger("crop-top");
                                }
                                this.m.releaseOutputBuffer(dequeueOutputBuffer, true);
                            } else if (dequeueOutputBuffer == -2) {
                                LogUtil.c(VideoFrameDecoderGPU.h, "outformat:MediaCodec.INFO_OUTPUT_FORMAT_CHANGED");
                            } else {
                                LogUtil.c(VideoFrameDecoderGPU.h, "outformat:" + dequeueOutputBuffer);
                                return 0;
                            }
                        }
                        LogUtil.c(VideoFrameDecoderGPU.h, "!mIsRunning");
                        return 0;
                    } else {
                        LogUtil.c(VideoFrameDecoderGPU.h, "isStartConfig && !frame.isIFrame");
                        return 0;
                    }
                } catch (Exception unused) {
                    if (VideoFrameDecoderGPU.this.r >= 3) {
                        VideoFrameDecoderGPU.this.l();
                        int unused2 = VideoFrameDecoderGPU.this.r = 0;
                        LogUtil.c(VideoFrameDecoderGPU.h, "changeDecode()");
                        return 0;
                    }
                    VideoFrameDecoderGPU.c(VideoFrameDecoderGPU.this);
                    if (this.m == null || this.f15901a == 0 || this.b == 0) {
                        LogUtil.c(VideoFrameDecoderGPU.h, "decoder == null || mWidth == 0 || mHeight == 0 changeDecode()");
                        VideoFrameDecoderGPU.this.l();
                    } else if (!j()) {
                        VideoFrameDecoderGPU.this.l();
                        LogUtil.c(VideoFrameDecoderGPU.h, "!releaseMediaDecode()");
                    } else {
                        a(this.f15901a, this.b);
                        LogUtil.c(VideoFrameDecoderGPU.h, "configureMediaDecode");
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void e() {
            LogUtil.c(VideoFrameDecoderGPU.h, "doInitial");
            this.f15901a = 0;
            this.b = 0;
            this.g = new SurfaceTexture(VideoFrameDecoderGPU.this.k());
            this.g.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    synchronized (VideoFrameDecoderGPU.this.i) {
                        boolean unused = VideoFrameDecoderGPU.this.m = true;
                        VideoFrameDecoderGPU.this.b();
                    }
                }
            });
            this.f = new Surface(this.g);
            this.e = false;
            VideoFrameDecoderGPU.this.c.getAVFrameQueue().clear();
        }

        /* access modifiers changed from: protected */
        public void f() {
            this.g.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
            this.g.release();
            this.f.release();
            j();
            LogUtil.c(VideoFrameDecoderGPU.h, "VideoDecodeThread stop");
        }

        /* access modifiers changed from: package-private */
        public void a(int i, int i2) {
            LogUtil.c(VideoFrameDecoderGPU.h, "configureMediaDecode width:" + i + " height:" + i2);
            try {
                AndroidH26xDecoderUtil.DecoderProperties a2 = AndroidH26xDecoderUtil.a(new MediaCodecUtil.CodecKey(VideoFrameDecoderGPU.this.g, i, i2));
                if (a2 != null) {
                    this.l = a2;
                } else {
                    this.e = true;
                    VideoFrameDecoderGPU.this.l();
                }
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.l.a(), i, i2);
                LogUtil.c(VideoFrameDecoderGPU.h, "configureMediaDecode:" + createVideoFormat.toString());
                try {
                    this.m = MediaCodec.createByCodecName(this.l.f15878a);
                } catch (Exception e2) {
                    LogUtil.c(VideoFrameDecoderGPU.h, "configureMediaDecode e:" + e2.getLocalizedMessage());
                }
                if (Build.VERSION.SDK_INT <= 16) {
                    createVideoFormat.setInteger("max-input-size", 0);
                }
                this.m.configure(createVideoFormat, this.f, (MediaCrypto) null, 0);
                this.m.start();
                this.o = true;
                if (Build.VERSION.SDK_INT < 21) {
                    this.n = this.m.getInputBuffers();
                }
            } catch (Exception e3) {
                LogUtil.c(VideoFrameDecoderGPU.h, "configureMediaDecode:" + e3.getLocalizedMessage());
                this.e = true;
                VideoFrameDecoderGPU.this.l();
            }
        }

        private boolean j() {
            LogUtil.c(VideoFrameDecoderGPU.h, "releaseMediaDecode");
            if (this.m == null) {
                return false;
            }
            try {
                this.m.stop();
            } catch (IllegalStateException e2) {
                try {
                    LogUtil.c(VideoFrameDecoderGPU.h, "releaseMediaDecode e:" + e2.getLocalizedMessage());
                } catch (Exception e3) {
                    LogUtil.c(VideoFrameDecoderGPU.h, "Release decoder error" + e3.getMessage());
                    return false;
                }
            }
            this.m.release();
            this.m = null;
            LogUtil.c(VideoFrameDecoderGPU.h, "Release decoder success");
            return true;
        }

        public void a(float[] fArr) {
            if (this.g != null) {
                this.g.updateTexImage();
                this.g.getTransformMatrix(fArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int k() {
        return this.l;
    }

    /* access modifiers changed from: private */
    public void l() {
        f();
        a(this.g);
    }

    public void h() {
        super.h();
        if (this.p != null) {
            this.p.c();
        }
    }
}
