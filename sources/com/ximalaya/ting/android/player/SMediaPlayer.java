package com.ximalaya.ting.android.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

public class SMediaPlayer implements Handler.Callback, XMediaplayerImpl {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;
    private final Handler A;
    private final HandlerThread B;
    /* access modifiers changed from: private */
    public boolean C = false;
    /* access modifiers changed from: private */
    public boolean D = false;
    /* access modifiers changed from: private */
    public boolean E = false;
    /* access modifiers changed from: private */
    public int F = 0;
    /* access modifiers changed from: private */
    public int G = 0;
    private long H = System.currentTimeMillis();
    private boolean I = false;
    private XMediaplayerJNI.AudioType J = XMediaplayerJNI.AudioType.NORMAL_FILE;
    private boolean K = false;
    private String L;
    /* access modifiers changed from: private */
    public XMediaPlayer.OnPositionChangeListener M;
    /* access modifiers changed from: private */
    public Runnable N = new Runnable() {
        public void run() {
            if (SMediaPlayer.this.M != null) {
                if (SMediaPlayer.this.m == 4 && !SMediaPlayer.this.D && !SMediaPlayer.this.C) {
                    SMediaPlayer.this.M.a(SMediaPlayer.this, SMediaPlayer.this.getCurrentPosition());
                }
                SMediaPlayer.this.l.postDelayed(SMediaPlayer.this.N, 1000);
            }
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public MediaPlayer f2282a = new MediaPlayer();
    /* access modifiers changed from: private */
    public Handler l;
    /* access modifiers changed from: private */
    public int m;

    public void a(long j2) {
    }

    public boolean c() {
        return true;
    }

    public void setOnPlayDataOutputListener(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener) {
    }

    public void setPreBufferUrl(String str) {
    }

    public void setSoundTouchAllParams(float f2, float f3, float f4) {
    }

    public void setTempo(float f2) {
    }

    public SMediaPlayer() {
        this.f2282a.setAudioStreamType(3);
        this.l = new Handler(Looper.myLooper());
        this.m = 1;
        this.B = new PriorityHandlerThread(getClass().getSimpleName() + ":Handler", -16);
        this.B.start();
        this.A = new Handler(this.B.getLooper(), this);
    }

    public int a() {
        return this.m;
    }

    public XMediaplayerJNI.AudioType getAudioType() {
        return this.J;
    }

    public int getCurrentPosition() {
        if (!this.C && !this.D && !this.E && !this.I) {
            this.F = this.f2282a.getCurrentPosition();
        }
        return this.F;
    }

    public int getDuration() {
        if (this.G != 0) {
            return this.G;
        }
        if (!this.C && !this.D && !this.E && !this.I) {
            this.G = this.f2282a.getDuration();
        }
        return this.G;
    }

    public boolean isPlaying() {
        if (this.C || this.D || this.E || this.I) {
            return false;
        }
        return this.f2282a.isPlaying();
    }

    public void pause() {
        this.A.obtainMessage(3).sendToTarget();
    }

    public void prepareAsync() {
        this.E = true;
        a("prepareAsync");
        this.A.obtainMessage(1).sendToTarget();
    }

    public void start() {
        a("start");
        if (this.m == 5 || this.m == 3) {
            this.A.obtainMessage(0).sendToTarget();
        }
    }

    public void stop() {
        this.A.obtainMessage(4).sendToTarget();
    }

    public void release() {
        a("release");
        e();
        this.I = true;
        if (this.A != null) {
            this.A.obtainMessage(5).sendToTarget();
        }
        this.M = null;
        if (this.B.getLooper() != null) {
            this.B.getLooper().quit();
            this.B.interrupt();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        String str2 = XMediaPlayer.Tag;
        Logger.a(str2, (Object) str + " nowtime:" + System.currentTimeMillis() + "long:" + (System.currentTimeMillis() - this.H));
        this.H = System.currentTimeMillis();
    }

    public void reset() {
        this.G = 0;
        if (this.K) {
            a("reset");
            this.A.obtainMessage(7).sendToTarget();
        }
    }

    public void seekTo(int i2) {
        this.C = true;
        this.A.obtainMessage(6, Integer.valueOf(i2)).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    this.m = 4;
                    a("MSG_START start");
                    this.f2282a.start();
                    a("MSG_START end");
                    return true;
                case 1:
                    this.m = 2;
                    a("MSG_PREPARE start");
                    this.f2282a.prepareAsync();
                    a("MSG_PREPARE end");
                    return true;
                case 3:
                    this.m = 5;
                    this.f2282a.pause();
                    return true;
                case 4:
                    this.m = 6;
                    this.f2282a.stop();
                    return true;
                case 5:
                    this.m = 9;
                    a("MSG_RELEASE start");
                    this.f2282a.release();
                    a("MSG_RELEASE end");
                    return true;
                case 6:
                    this.f2282a.seekTo(((Integer) message.obj).intValue());
                    return true;
                case 7:
                    this.m = 0;
                    a("MSG_RESET start");
                    this.f2282a.reset();
                    e();
                    a("MSG_RESET end");
                    return true;
                case 8:
                    a("MSG_SET_DATA_SOURCE start");
                    if (message.obj != null) {
                        try {
                            this.f2282a.setDataSource(message.obj.toString());
                        } catch (IllegalArgumentException e2) {
                            e2.printStackTrace();
                        } catch (SecurityException e3) {
                            e3.printStackTrace();
                        } catch (IllegalStateException e4) {
                            e4.printStackTrace();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    a("MSG_SET_DATA_SOURCE end");
                    return true;
                default:
                    return false;
            }
        } catch (Exception unused) {
            return true;
        }
        return true;
    }

    public String b() {
        return this.L;
    }

    public void setDataSource(String str) {
        a("setDataSource");
        this.G = 0;
        if (str.contains("m3u8")) {
            this.J = XMediaplayerJNI.AudioType.M3U8_FILE;
        } else {
            this.J = XMediaplayerJNI.AudioType.NORMAL_FILE;
        }
        this.K = true;
        this.A.obtainMessage(8, str).sendToTarget();
    }

    public void a(final XMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.f2282a.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                if (onBufferingUpdateListener != null) {
                    onBufferingUpdateListener.a(SMediaPlayer.this, i);
                }
            }
        });
    }

    public void a(final XMediaPlayer.OnCompletionListener onCompletionListener) {
        this.f2282a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                int unused = SMediaPlayer.this.m = 11;
                if (onCompletionListener != null) {
                    onCompletionListener.a(SMediaPlayer.this);
                }
            }
        });
    }

    public void a(final XMediaPlayer.OnErrorListener onErrorListener) {
        this.f2282a.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                SMediaPlayer.this.e();
                if (onErrorListener == null) {
                    return false;
                }
                boolean a2 = onErrorListener.a(SMediaPlayer.this, i, i2);
                if (!a2) {
                    int unused = SMediaPlayer.this.m = 8;
                }
                return a2;
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        this.C = false;
        this.D = false;
        this.E = false;
        this.G = 0;
    }

    public void a(final XMediaPlayer.OnInfoListener onInfoListener) {
        Logger.a(XMediaplayerJNI.Tag, (Object) "SMediaPlayer setOnInfoListener");
        this.f2282a.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                String str = XMediaplayerJNI.Tag;
                Logger.a(str, (Object) "SMediaPlayer OnInfoListener:" + i + "extra:" + i2);
                switch (i) {
                    case 701:
                        boolean unused = SMediaPlayer.this.D = true;
                        break;
                    case 702:
                        boolean unused2 = SMediaPlayer.this.D = false;
                        break;
                }
                if (onInfoListener != null) {
                    return onInfoListener.a(SMediaPlayer.this, 10, i);
                }
                return false;
            }
        });
    }

    public void a(final XMediaPlayer.OnPreparedListener onPreparedListener) {
        this.f2282a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                SMediaPlayer.this.a("onPrepared");
                int unused = SMediaPlayer.this.m = 3;
                boolean unused2 = SMediaPlayer.this.E = false;
                int unused3 = SMediaPlayer.this.G = SMediaPlayer.this.f2282a.getDuration();
                int unused4 = SMediaPlayer.this.F = SMediaPlayer.this.f2282a.getCurrentPosition();
                if (onPreparedListener != null) {
                    onPreparedListener.a(SMediaPlayer.this);
                }
            }
        });
    }

    public void a(final XMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.f2282a.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                boolean unused = SMediaPlayer.this.C = false;
                if (onSeekCompleteListener != null) {
                    onSeekCompleteListener.a(SMediaPlayer.this);
                }
            }
        });
    }

    public void a(XMediaPlayer.OnPositionChangeListener onPositionChangeListener) {
        this.M = onPositionChangeListener;
        if (this.M != null) {
            this.l.postDelayed(this.N, 1000);
        }
    }

    public void setVolume(float f2, float f3) {
        this.f2282a.setVolume(f2, f3);
    }

    public void a(Context context, int i2) {
        this.f2282a.setWakeMode(context, i2);
    }

    public void a(int i2) {
        this.f2282a.setAudioStreamType(i2);
    }

    public void a(FileDescriptor fileDescriptor, String str) {
        this.K = true;
        try {
            this.f2282a.setDataSource(fileDescriptor);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public void a(HttpConfig httpConfig) {
        StaticConfig.a(httpConfig);
    }

    public void d() {
        StaticConfig.a((HttpConfig) null);
    }

    public void a(Map<String, String> map) {
        StaticConfig.a(map);
    }
}
