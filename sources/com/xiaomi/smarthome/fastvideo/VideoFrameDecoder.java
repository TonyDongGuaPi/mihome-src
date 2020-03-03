package com.xiaomi.smarthome.fastvideo;

import com.xiaomi.smarthome.camera.VideoFrame;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class VideoFrameDecoder {

    /* renamed from: a  reason: collision with root package name */
    protected volatile int f15900a;
    protected volatile int b;
    protected VideoGlSurfaceView c;
    protected boolean d;
    long e;
    int f;
    protected int g = 1;
    private String h = "VideoFrameDecoder";
    private long i = 0;

    public void g() {
    }

    public void h() {
    }

    public VideoFrameDecoder(VideoGlSurfaceView videoGlSurfaceView) {
        this.c = videoGlSurfaceView;
    }

    public void a(VideoGlSurfaceView videoGlSurfaceView) {
        this.c = videoGlSurfaceView;
    }

    public VideoGlSurfaceView a() {
        return this.c;
    }

    public void b() {
        if (this.c != null && this.c.getAVFrameQueue().size() > 0) {
            this.c.requestRender();
        }
    }

    public void a(Photo photo) {
        if (this.c != null) {
            this.c.setPhoto(this.c.appFilter(photo));
        }
    }

    /* access modifiers changed from: package-private */
    public VideoFrame a(boolean z) throws InterruptedException {
        if (this.c != null) {
            VideoFrame take = this.c.getAVFrameQueue().take();
            this.c.initWater(take.distrot);
            if (!take.isReal || z) {
                return take;
            }
            int size = this.c.getAVFrameQueue().size();
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.i;
            this.i = currentTimeMillis;
            int i2 = 30;
            if (size <= 30) {
                i2 = size > 20 ? 40 : size > 10 ? 45 : 50;
            }
            long j2 = (long) i2;
            if (j < j2) {
                try {
                    Thread.sleep(j2 - j);
                } catch (IllegalArgumentException | InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            return take;
        }
        LogUtil.c(this.h, "mVideoGlSurfaceView == null");
        return null;
    }

    /* access modifiers changed from: package-private */
    public VideoFrame c() {
        if (this.c == null) {
            return null;
        }
        LinkedBlockingQueue<VideoFrame> aVFrameQueue = this.c.getAVFrameQueue();
        int size = aVFrameQueue.size();
        VideoFrame poll = this.c.getAVFrameQueue().poll();
        if (size == 45) {
            while (true) {
                if (poll != null && poll.isIFrame) {
                    break;
                }
                poll = aVFrameQueue.poll();
            }
        }
        if (poll != null) {
            this.c.initWater(poll.distrot);
        }
        return poll;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        LogUtil.c(this.h, "enableFFMPEGDecoder");
        if (this.c != null) {
            this.c.changeVideoFrameDecoder(new VideoFrameDecoderFFMPEG(this.c, i2));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.d;
    }

    public void e() {
        this.d = true;
    }

    public void f() {
        this.d = false;
    }

    public int i() {
        return this.f15900a;
    }

    public int j() {
        return this.b;
    }
}
