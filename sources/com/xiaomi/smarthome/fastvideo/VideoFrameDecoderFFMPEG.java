package com.xiaomi.smarthome.fastvideo;

import android.opengl.GLES20;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.fastvideo.decoder.H264Decoder;

public class VideoFrameDecoderFFMPEG extends VideoFrameDecoder {
    private static final String i = "VideoFFMPEG";
    H264Decoder h;
    private YUVFilter j;
    private Photo k;
    private int l;
    private int m;
    private int[] n;
    private long o = 0;
    private boolean p = false;

    public VideoFrameDecoderFFMPEG(VideoGlSurfaceView videoGlSurfaceView) {
        super(videoGlSurfaceView);
    }

    public VideoFrameDecoderFFMPEG(VideoGlSurfaceView videoGlSurfaceView, int i2) {
        super(videoGlSurfaceView);
        this.g = i2;
    }

    public void e() {
        super.e();
        this.j = new YUVFilter(a().getContext());
        this.j.d();
        this.n = new int[3];
        GLES20.glGenTextures(this.n.length, this.n, 0);
        this.j.a(this.n);
    }

    public synchronized void g() {
        super.g();
        if (!d()) {
            LogUtil.c(i, "!isInitialed()");
            return;
        }
        VideoFrame c = c();
        if (c != null) {
            if (c.data != null) {
                if (c.width >= 0) {
                    if (c.height >= 0) {
                        if (this.h != null || c.isIFrame) {
                            if (!(this.h != null && c.width == this.l && c.height == this.m && this.g == c.videoType)) {
                                this.l = c.width;
                                this.m = c.height;
                                this.g = c.videoType;
                                if (this.h != null) {
                                    this.h.release();
                                }
                                this.h = new H264Decoder(this.g);
                            }
                            if (this.p) {
                                if (c.isIFrame) {
                                    this.p = false;
                                } else {
                                    LogUtil.c(i, "mNeedIFrame true");
                                    return;
                                }
                            }
                            if (c.num - this.o <= 5 || c.isIFrame) {
                                this.o = c.num;
                                if (this.h.decode(c.data, c.data.length, c.timeStamp)) {
                                    if (this.h.toTexture(this.n[0], this.n[1], this.n[2]) < 0) {
                                        LogUtil.c(i, "toTexture failed");
                                        return;
                                    }
                                    this.f15900a = this.h.getWidth();
                                    this.b = this.h.getHeight();
                                    if (this.k == null) {
                                        this.k = Photo.a(this.h.getWidth(), this.h.getHeight());
                                    } else {
                                        this.k.c(this.h.getWidth(), this.h.getHeight());
                                    }
                                    try {
                                        this.j.a((Photo) null, this.k);
                                    } catch (Exception e) {
                                        this.k = null;
                                        LogUtil.c(i, "Exception:" + e.getLocalizedMessage());
                                    }
                                    if (this.k != null) {
                                        a(this.k);
                                    } else {
                                        LogUtil.c(i, "mPhoto == null");
                                    }
                                }
                                b();
                                return;
                            }
                            this.o = c.num;
                            this.p = true;
                            LogUtil.c(i, "mNeedIFrame true 2");
                            return;
                        }
                        LogUtil.c(i, "videoDecoder == null && !frame.isIFrame");
                        return;
                    }
                }
                LogUtil.c(i, "frame.width < 0 || frame.height < 0");
                return;
            }
        }
        LogUtil.c(i, "frame == null || frame.data == null");
    }

    public void f() {
        super.f();
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
        if (this.j != null) {
            this.j.e();
            this.j = null;
            if (this.n != null) {
                GLES20.glDeleteTextures(this.n.length, this.n, 0);
            }
        }
        if (this.k != null) {
            this.k.e();
            this.k = null;
        }
    }
}
