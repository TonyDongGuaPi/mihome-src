package com.xiaomi.smarthome.fastvideo;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.util.Log;
import android.view.Surface;

@TargetApi(16)
public class VideoPlayerRender extends VideoPlayerBase implements MediaPlayer.OnPreparedListener {
    private static final String m = "VideoPlayerRender";
    private Photo n;
    private Photo o;
    private boolean p = false;
    private int q;
    /* access modifiers changed from: private */
    public volatile boolean r = false;
    private GlslFilter s;
    private float[] t = new float[16];
    /* access modifiers changed from: private */
    public MediaPlayer u;
    private Surface v;
    private SurfaceTexture w;
    private volatile boolean x = true;
    private int y = -1;

    public void b() {
        if (this.c != null) {
            this.c.requestRender();
        }
    }

    public void changeSource(final String str) {
        if (this.u == null) {
            openVideoPlayer(str);
            return;
        }
        this.x = false;
        this.c.queue(new Runnable() {
            public void run() {
                VideoPlayerRender.this.l();
                VideoPlayerRender.this.openVideoPlayer(str);
            }
        });
    }

    public void openVideoPlayer(String str) {
        if (this.u != null) {
            try {
                if (this.p) {
                    this.u.start();
                }
            } catch (IllegalStateException e) {
                LogUtil.b(m, "IllegalStateException:" + e.getLocalizedMessage());
            }
        } else {
            this.w = new SurfaceTexture(k());
            this.w.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    boolean unused = VideoPlayerRender.this.r = true;
                    VideoPlayerRender.this.b();
                }
            });
            this.v = new Surface(this.w);
            this.u = new MediaPlayer();
            this.u.setOnPreparedListener(this);
            this.u.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (VideoPlayerRender.this.h != null) {
                        VideoPlayerRender.this.h.onCompletion(mediaPlayer);
                    }
                }
            });
            this.u.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    VideoPlayerRender.this.u.reset();
                    return VideoPlayerRender.this.i != null && VideoPlayerRender.this.i.onError(mediaPlayer, i, i2);
                }
            });
            this.u.setScreenOnWhilePlaying(true);
            this.u.setAudioStreamType(3);
            this.u.setSurface(this.v);
            try {
                this.u.setDataSource(str);
                this.u.prepareAsync();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.x = true;
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        if (this.u != null) {
            this.u.setOnErrorListener((MediaPlayer.OnErrorListener) null);
            this.u.setOnCompletionListener((MediaPlayer.OnCompletionListener) null);
            this.u.setOnPreparedListener((MediaPlayer.OnPreparedListener) null);
            this.u.reset();
            this.u.release();
            this.u = null;
        }
        if (this.w != null) {
            this.w.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
            this.w.release();
            this.w = null;
        }
        if (this.v != null) {
            this.v.release();
            this.v = null;
        }
        this.p = false;
    }

    public void pause() {
        if (this.u != null && this.u.isPlaying()) {
            this.u.pause();
        }
    }

    public boolean isPlaying() {
        return this.u != null && this.u.isPlaying();
    }

    public int getDuration() {
        if (this.u == null || !this.p) {
            return 0;
        }
        return this.u.getDuration();
    }

    public void seekTo(int i) {
        if (this.u != null && this.p) {
            this.u.seekTo(i);
        }
    }

    public void setVolume(int i) {
        if (!this.p || this.u == null || !this.u.isPlaying()) {
            this.y = i;
            return;
        }
        float f = (float) i;
        this.u.setVolume(f, f);
    }

    public int getCurrentPosition() {
        if (this.u == null || !this.x) {
            return 0;
        }
        return this.u.getCurrentPosition();
    }

    public VideoPlayerRender(VideoGlSurfaceView videoGlSurfaceView) {
        super(videoGlSurfaceView);
    }

    public void e() {
        super.e();
        this.s = new GlslFilter(a().getContext());
        this.s.a((int) GlslFilter.e);
        this.s.d();
        this.q = RendererUtils.a();
        GLES20.glBindTexture(GlslFilter.e, this.q);
        RendererUtils.a("glBindTexture mTextureID");
        GLES20.glTexParameterf(GlslFilter.e, 10241, 9728.0f);
        GLES20.glTexParameterf(GlslFilter.e, 10240, 9729.0f);
        GLES20.glTexParameteri(GlslFilter.e, 10242, 33071);
        GLES20.glTexParameteri(GlslFilter.e, 10243, 33071);
        this.r = false;
        RendererUtils.a("surfaceCreated");
        if (this.k != null) {
            this.k.onInitialed();
        }
        Log.e("video", " initial video player render");
    }

    public void f() {
        super.f();
        RendererUtils.a(this.q);
        if (this.s != null) {
            this.s.e();
        }
        if (this.n != null) {
            this.n.e();
            this.n = null;
        }
        if (this.o != null) {
            this.o.e();
            this.o = null;
        }
        this.h = null;
        this.i = null;
        this.k = null;
        l();
    }

    public void g() {
        super.g();
        if (this.r) {
            this.r = false;
            if (this.u != null) {
                int videoWidth = this.u.getVideoWidth();
                int videoHeight = this.u.getVideoHeight();
                if (videoWidth != 0 && videoHeight != 0) {
                    if (this.n == null) {
                        this.n = Photo.a(videoWidth, videoHeight);
                    } else {
                        this.n.c(videoWidth, videoHeight);
                    }
                    if (this.o == null) {
                        this.o = new Photo(this.q, videoWidth, videoHeight);
                    }
                    this.w.updateTexImage();
                    this.w.getTransformMatrix(this.t);
                    this.s.b(this.t);
                    this.s.a(this.o, this.n);
                    a(this.n);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int k() {
        return this.q;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.u.start();
        if (this.y >= 0) {
            this.u.setVolume((float) this.y, (float) this.y);
        }
        this.p = true;
        if (this.j != null) {
            this.j.onPrepared(mediaPlayer);
        }
    }
}
