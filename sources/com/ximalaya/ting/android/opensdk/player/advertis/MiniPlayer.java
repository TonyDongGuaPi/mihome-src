package com.ximalaya.ting.android.opensdk.player.advertis;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.io.FileDescriptor;

public class MiniPlayer {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2148a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    private static final String h = "MiniPlayer";
    private MediaPlayer i;
    private int j = 3;
    private float k = 1.0f;
    private float l = 1.0f;
    private boolean m = false;
    /* access modifiers changed from: private */
    public MediaPlayer.OnCompletionListener n;
    /* access modifiers changed from: private */
    public PlayerStatusListener o;
    /* access modifiers changed from: private */
    public int p = -1;
    private Advertis q;
    private AudioManager r;

    public interface PlayerStatusListener {
        void a();

        boolean a(Exception exc, int i, int i2);

        void b();

        void c();

        void d();
    }

    public MiniPlayer() {
        e();
    }

    public MediaPlayer a() {
        return this.i;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public void a(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.n = onCompletionListener;
    }

    @TargetApi(9)
    public int b() {
        if (Build.VERSION.SDK_INT >= 9) {
            return this.i.getAudioSessionId();
        }
        return 0;
    }

    public void a(boolean z) {
        this.m = z;
        if (this.p != -1) {
            this.i.setLooping(z);
        }
    }

    public int c() {
        switch (this.p) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return this.i.getCurrentPosition();
            default:
                return 0;
        }
    }

    public void a(float f2, float f3) {
        this.k = f2;
        this.l = f3;
        if (this.p != -1) {
            this.i.setVolume(this.k, this.l);
        }
    }

    public int d() {
        switch (this.p) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return this.i.getDuration();
            default:
                return 0;
        }
    }

    public void e() {
        try {
            if (this.i == null) {
                this.i = new MediaPlayer();
                this.p = 0;
                this.i.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        int unused = MiniPlayer.this.p = -1;
                        if (MiniPlayer.this.o == null) {
                            return true;
                        }
                        MiniPlayer.this.o.a((Exception) null, i, i2);
                        return true;
                    }
                });
                this.i.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        int unused = MiniPlayer.this.p = 5;
                        if (MiniPlayer.this.n != null) {
                            MiniPlayer.this.n.onCompletion(mediaPlayer);
                        }
                        if (MiniPlayer.this.o != null) {
                            MiniPlayer.this.o.d();
                        }
                    }
                });
            }
            if (this.p == 2) {
                this.i.stop();
                this.p = 4;
                if (this.o != null) {
                    this.o.c();
                }
            }
            this.i.reset();
            this.i.setLooping(this.m);
            this.i.setVolume(this.k, this.l);
            this.p = 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
    }

    @Deprecated
    public void a(Context context, Uri uri, int i2, int i3) {
        try {
            e();
            this.i.setDataSource(context, uri);
            this.i.prepare();
            this.p = 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
        }
    }

    @Deprecated
    public void a(Context context, Uri uri) {
        try {
            e();
            this.i.setDataSource(context, uri);
            this.i.prepare();
            this.p = 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
        }
    }

    public void f() {
        try {
            switch (this.p) {
                case 1:
                case 5:
                    this.i.start();
                    this.p = 2;
                    if (this.o != null) {
                        this.o.a();
                        return;
                    }
                    return;
                case 2:
                case 3:
                case 4:
                    this.i.stop();
                    this.i.prepare();
                    this.i.start();
                    this.p = 2;
                    if (this.o != null) {
                        this.o.a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
    }

    public int g() {
        return this.p;
    }

    public boolean h() {
        return this.p == 4;
    }

    public boolean i() {
        return this.p == 2;
    }

    public boolean j() {
        return this.p == 3;
    }

    public void a(FileDescriptor fileDescriptor, int i2, final boolean[] zArr) {
        Logger.e(h, "init seek " + i2);
        try {
            e();
            this.i.setDataSource(fileDescriptor);
            this.i.prepare();
            this.i.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    if (zArr != null && zArr.length > 0) {
                        zArr[0] = true;
                    }
                }
            });
            if (i2 > 0) {
                this.i.seekTo(i2);
            }
            this.p = 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
            if (zArr != null && zArr.length > 0) {
                zArr[0] = true;
            }
        }
    }

    public void a(FileDescriptor fileDescriptor, long j2, long j3) {
        Logger.e(h, "init offset " + j2 + ", length " + j3);
        try {
            e();
            this.i.setDataSource(fileDescriptor, j2, j3);
            this.i.prepare();
            this.p = 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
        }
    }

    public void a(String str) throws Exception {
        e();
        this.i.setDataSource(str);
        this.i.prepare();
        this.p = 1;
    }

    public void a(String str, Advertis advertis) throws Exception {
        e();
        this.i.setDataSource(str);
        this.i.prepare();
        this.p = 1;
        this.q = advertis;
        if (XmPlayerService.getPlayerSrvice() != null) {
            try {
                this.r = (AudioManager) XmPlayerService.getPlayerSrvice().getSystemService("audio");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void k() {
        try {
            if (this.q == null || this.q.H() == 0) {
                a(1.0f, 1.0f);
            } else {
                float H = ((float) this.q.H()) / 100.0f;
                a(H, H);
            }
            Logger.a("playAd 1:" + System.currentTimeMillis());
            if (!(this.p == 1 || this.p == 3)) {
                if (this.p != 5) {
                    if (this.p == 4) {
                        Logger.a("playAd 3:" + System.currentTimeMillis());
                        this.i.prepare();
                        this.i.start();
                        this.p = 2;
                        if (this.o != null) {
                            this.o.a();
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            Logger.a("playAd 2:" + System.currentTimeMillis());
            this.i.start();
            this.p = 2;
            if (this.o != null) {
                this.o.a();
            }
        } catch (Exception e2) {
            Logger.a("playAd 4:" + System.currentTimeMillis());
            e2.printStackTrace();
            this.p = -1;
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
    }

    public void l() {
        Logger.a("Ad pausePlay 0:" + System.currentTimeMillis());
        try {
            if (this.p == 2) {
                this.i.pause();
                this.p = 3;
                if (this.o != null) {
                    this.o.b();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
    }

    public void m() {
        Logger.a("AD stopPlay 0:" + System.currentTimeMillis());
        try {
            if (this.p == 2) {
                this.i.stop();
                this.p = 4;
                if (this.o != null) {
                    this.o.c();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.p = -1;
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
        this.q = null;
    }

    public void n() {
        Logger.a("AD release 0:" + System.currentTimeMillis());
        try {
            if (this.i != null) {
                if (this.p == 2) {
                    this.i.stop();
                    if (this.o != null) {
                        this.o.c();
                    }
                }
                this.i.release();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.o != null) {
                this.o.a(e2, 0, 0);
            }
        }
        this.q = null;
        this.i = null;
    }

    public Advertis o() {
        return this.q;
    }

    public void a(Advertis advertis) {
        this.q = advertis;
    }

    public void a(PlayerStatusListener playerStatusListener) {
        this.o = playerStatusListener;
    }
}
