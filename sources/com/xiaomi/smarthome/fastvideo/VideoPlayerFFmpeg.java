package com.xiaomi.smarthome.fastvideo;

import android.media.MediaPlayer;
import android.opengl.GLES20;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.fastvideo.decoder.H264Decoder;
import com.xiaomi.smarthome.fastvideo.decoder.Mp4Read;
import java.io.File;

public class VideoPlayerFFmpeg extends VideoPlayerBase {
    private static final int o = 138;
    private static final int p = 136;
    private int[] A = new int[1];
    private int[] B = new int[1];
    private int[] C = new int[1];
    private int[] D = new int[1];
    private int[] E = new int[1];
    private int[] F = new int[1];
    private byte[] G = new byte[1048576];
    private boolean H = false;
    private long I = 0;
    protected int[] m = new int[1];
    private final String n = "video";
    private YUVFilter q;
    private Photo r;
    private int s;
    private int t;
    private int[] u;
    private H264Decoder v;
    private int w = 0;
    /* access modifiers changed from: private */
    public volatile boolean x = false;
    private volatile int y = 0;
    /* access modifiers changed from: private */
    public Mp4Read z = null;

    public VideoPlayerFFmpeg(VideoGlSurfaceView videoGlSurfaceView) {
        super(videoGlSurfaceView);
    }

    public void e() {
        super.e();
        this.q = new YUVFilter(a().getContext());
        this.q.d();
        this.u = new int[3];
        GLES20.glGenTextures(this.u.length, this.u, 0);
        this.q.a(this.u);
        LogUtil.a("video", "init VideoPlayerFFmpeg ");
        if (this.k != null) {
            this.k.onInitialed();
        }
    }

    public void b() {
        if (this.c != null) {
            this.c.requestRender();
        }
    }

    public void changeSource(final String str) {
        if (this.c != null) {
            this.c.queue(new Runnable() {
                public void run() {
                    if (VideoPlayerFFmpeg.this.z != null) {
                        boolean unused = VideoPlayerFFmpeg.this.x = true;
                        VideoPlayerFFmpeg.this.z.closeFile();
                        Mp4Read unused2 = VideoPlayerFFmpeg.this.z = null;
                    }
                    VideoPlayerFFmpeg.this.openVideoPlayer(str);
                    VideoPlayerFFmpeg.this.b();
                }
            });
        }
    }

    public void openVideoPlayer(String str) {
        if (!new File(str).exists()) {
            if (this.i != null) {
                this.i.onError((MediaPlayer) null, -1, -1);
            }
        } else if (this.z != null) {
            this.x = false;
            b();
        } else {
            a(str);
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.j = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.h = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.i = onErrorListener;
    }

    public void g() {
        super.g();
        if (d()) {
            if ((!this.x || this.y != 0) && this.z != null) {
                VideoFrame k = k();
                if (k == null || k.data == null) {
                    b();
                } else if (k.width < 0 || k.height < 0) {
                    b();
                } else if (this.v != null || k.isIFrame) {
                    if (!(this.v != null && k.width == this.s && k.height == this.t && this.g == k.videoType)) {
                        this.s = k.width;
                        this.t = k.height;
                        this.g = k.videoType;
                        if (this.v != null) {
                            this.v.release();
                        }
                        this.v = new H264Decoder(this.g, false);
                    }
                    if (this.v.decode(k.data, k.data.length, k.timeStamp)) {
                        if (this.v.toTexture(this.u[0], this.u[1], this.u[2]) < 0) {
                            b();
                            return;
                        }
                        this.f15900a = this.v.getWidth();
                        this.b = this.v.getHeight();
                        if (this.r == null) {
                            this.r = Photo.a(this.v.getWidth(), this.v.getHeight());
                        } else {
                            this.r.c(this.v.getWidth(), this.v.getHeight());
                        }
                        this.q.a((Photo) null, this.r);
                        a(this.r);
                    }
                    if (!this.x) {
                        this.w = (int) k.timeStamp;
                    }
                    if (this.I > 0) {
                        long currentTimeMillis = System.currentTimeMillis() - this.I;
                        if (currentTimeMillis < 50 && currentTimeMillis > 0) {
                            try {
                                Thread.sleep(50 - currentTimeMillis);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    this.I = System.currentTimeMillis();
                    b();
                } else {
                    b();
                }
            }
        }
    }

    public void f() {
        super.f();
        if (this.v != null) {
            this.v.release();
            this.v = null;
        }
        if (this.q != null) {
            this.q.e();
            this.q = null;
            if (this.u != null) {
                GLES20.glDeleteTextures(this.u.length, this.u, 0);
            }
        }
        if (this.r != null) {
            this.r.e();
            this.r = null;
        }
        if (this.l != null) {
            this.l = null;
        }
        if (this.z != null) {
            this.z.closeFile();
            this.z = null;
        }
    }

    public void pause() {
        this.x = true;
    }

    public boolean isPlaying() {
        return this.z != null && !this.x;
    }

    public int getDuration() {
        if (this.z == null) {
            return 0;
        }
        return this.m[0];
    }

    public int getCurrentPosition() {
        if (this.z == null) {
            return 0;
        }
        return this.w;
    }

    public void seekTo(int i) {
        if (i == 0) {
            this.y = 1;
        } else {
            this.y = i;
        }
        if (this.x) {
            this.w = i;
        }
        b();
    }

    public void setVolume(int i) {
        this.H = i == 0;
    }

    private void a(String str) {
        this.z = new Mp4Read();
        int openFile = this.z.openFile(str, this.A, this.B, this.C, this.m, this.E, this.F, this.D);
        if (openFile < 0) {
            seekTo(0);
            if (this.i != null) {
                this.i.onError((MediaPlayer) null, openFile, -1);
                return;
            }
            return;
        }
        if (this.j != null) {
            this.j.onPrepared((MediaPlayer) null);
        }
        this.x = false;
        this.w = 0;
        b();
    }

    private VideoFrame k() {
        int i;
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        if (this.y != 0) {
            iArr3[0] = this.y;
            i = this.z.getVideoFrame(this.G, iArr3, iArr, iArr2);
            if (i > 0 && iArr2[0] == 1) {
                do {
                    iArr3[0] = 0;
                    i = this.z.getVideoFrame(this.G, iArr3, iArr, iArr2);
                    if (i < 0) {
                        break;
                    }
                } while (iArr2[0] == 0);
            }
            this.y = 0;
        } else {
            i = this.z.getVideoFrame(this.G, iArr3, iArr, iArr2);
        }
        int i2 = i;
        if (i2 < 0) {
            this.x = true;
            seekTo(0);
            if (this.h != null) {
                this.h.onCompletion((MediaPlayer) null);
            }
        } else {
            byte[] bArr = new byte[i2];
            if (iArr2[0] != 1) {
                System.arraycopy(this.G, 0, bArr, 0, i2);
                return new VideoFrame(bArr, 0, i2, this.E[0], this.F[0], (long) iArr3[0], this.A[0], iArr[0] == 1);
            } else if (!this.H && this.l != null && !this.x) {
                System.arraycopy(this.G, 0, bArr, 0, i2);
                this.l.onAudioData(bArr, (long) iArr3[0], 136);
            }
        }
        return null;
    }
}
