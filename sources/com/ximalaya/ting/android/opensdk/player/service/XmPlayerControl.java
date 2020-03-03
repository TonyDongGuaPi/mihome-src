package com.ximalaya.ting.android.opensdk.player.service;

import android.content.Context;
import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.httputil.util.freeflow.FreeFlowServiceUtil;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.XMediaplayerImpl;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import java.util.HashMap;
import java.util.Map;

public class XmPlayerControl {

    /* renamed from: a  reason: collision with root package name */
    public static long f2211a = 0;
    public static long b = 0;
    private static final String c = "XmPlayerControl";
    /* access modifiers changed from: private */
    public PlayableModel A = null;
    /* access modifiers changed from: private */
    public volatile boolean B = true;
    private float C = 1.0f;
    private float D = 0.0f;
    private float E = 1.0f;
    /* access modifiers changed from: private */
    public XMediaplayerImpl d;
    private XMediaPlayer.OnCompletionListener e;
    private XMediaPlayer.OnPreparedListener f;
    private XMediaPlayer.OnSeekCompleteListener g;
    private XMediaPlayer.OnErrorListener h;
    private XMediaPlayer.OnInfoListener i;
    private XMediaPlayer.OnBufferingUpdateListener j;
    private XMediaPlayer.OnPositionChangeListener k;
    private XMediaPlayer.OnPlayDataOutputListener l;
    /* access modifiers changed from: private */
    public IXmPlayerStatusListener m;
    /* access modifiers changed from: private */
    public volatile int n;
    /* access modifiers changed from: private */
    public boolean o = true;
    private int p;
    /* access modifiers changed from: private */
    public int q;
    private String r;
    /* access modifiers changed from: private */
    public boolean s = false;
    private Config t;
    private Context u;
    private boolean v = false;
    /* access modifiers changed from: private */
    public boolean w = false;
    /* access modifiers changed from: private */
    public IPlaySeekListener x;
    /* access modifiers changed from: private */
    public int y;
    /* access modifiers changed from: private */
    public boolean z = false;

    public interface IPlaySeekListener {
        void a(int i);
    }

    public void a(boolean z2) {
        this.v = z2;
    }

    public boolean a() {
        return this.v;
    }

    public XmPlayerControl(Context context) {
        this.u = context.getApplicationContext();
    }

    public void a(Config config) {
        Logger.b(c, "setProxy " + config);
        this.t = config;
        if (this.d != null) {
            this.d.a(FreeFlowServiceUtil.a(config));
        }
    }

    public void a(float f2, float f3) {
        if (this.d != null) {
            this.d.setVolume(f2, f3);
        }
    }

    public boolean b() {
        if (this.d == null) {
            return false;
        }
        return this.d.isPlaying();
    }

    public int c() {
        switch (this.n) {
            case 3:
            case 4:
            case 5:
            case 6:
                return this.d.getCurrentPosition();
            default:
                return 0;
        }
    }

    public String d() {
        return this.r;
    }

    public void a(String str) {
        this.r = str;
    }

    public boolean e() {
        return !TextUtils.isEmpty(this.r) && this.r.contains(ConnectionHelper.HTTP_PREFIX);
    }

    public boolean a(String str, int i2) {
        this.o = false;
        return b(str, i2);
    }

    public void b(boolean z2) {
        this.o = z2;
    }

    public boolean b(String str, int i2) {
        Logger.a("PlayerControl init 17:" + System.currentTimeMillis());
        this.w = false;
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            this.r = null;
            if (this.d != null) {
                this.d.reset();
                this.n = 0;
                this.q = 0;
            }
            return false;
        }
        Logger.a("PlayerControl init 18:" + System.currentTimeMillis());
        this.p = i2;
        if (TextUtils.isEmpty(this.r) || !str.equals(this.r)) {
            this.r = str;
            q();
            return true;
        }
        switch (this.n) {
            case 0:
            case 4:
            case 7:
            case 8:
                q();
                return true;
            case 1:
                this.d.prepareAsync();
                this.n = 9;
                return true;
            case 5:
                h();
                return true;
            default:
                return true;
        }
    }

    public PlayableModel f() {
        return this.A;
    }

    public void g() {
        if (this.d != null) {
            this.d.reset();
            this.n = 1;
            this.r = null;
        }
    }

    private void q() {
        if (this.d == null) {
            this.d = u();
        } else {
            this.d.reset();
            s();
        }
        this.d.setDataSource(this.r);
        this.n = 1;
        this.d.prepareAsync();
        this.n = 9;
        if (this.m != null) {
            this.m.f();
        }
        this.q = 0;
        if (XmPlayerService.getPlayerSrvice() != null && XmPlayerService.getPlayerSrvice().mListControl != null) {
            this.A = XmPlayerService.getPlayerSrvice().mListControl.n();
        }
    }

    private void r() {
        if (this.d != null) {
            this.d.a((XMediaPlayer.OnBufferingUpdateListener) null);
            this.d.a((XMediaPlayer.OnCompletionListener) null);
            this.d.a((XMediaPlayer.OnPreparedListener) null);
            this.d.a((XMediaPlayer.OnSeekCompleteListener) null);
            this.d.a((XMediaPlayer.OnErrorListener) null);
            this.d.a((XMediaPlayer.OnInfoListener) null);
            this.d.a((XMediaPlayer.OnPositionChangeListener) null);
            this.d.setOnPlayDataOutputListener((XMediaPlayer.OnPlayDataOutputListener) null);
        }
    }

    private void s() {
        if (this.d != null) {
            this.d.a(this.j);
            this.d.a(this.e);
            this.d.a(this.f);
            this.d.a(this.g);
            this.d.a(this.h);
            this.d.a(this.i);
            this.d.a(this.k);
            this.d.setOnPlayDataOutputListener(this.l);
        }
    }

    public boolean c(String str, int i2) {
        this.o = true;
        return b(str, i2);
    }

    public boolean h() {
        return c(false);
    }

    public boolean c(boolean z2) {
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice == null || this.d == null) {
            return false;
        }
        if (z2) {
            playerSrvice.setLossAudioFocus(false);
        }
        int i2 = this.n;
        if (i2 == 9) {
            return true;
        }
        switch (i2) {
            case 1:
                this.d.prepareAsync();
                this.n = 9;
                return true;
            case 2:
            case 5:
            case 6:
                if (playerSrvice.isLossAudioFocus()) {
                    playerSrvice.setLossAudioFocus(false);
                    return true;
                }
                playerSrvice.requestAudioFocusControl();
                if (!this.v) {
                    this.d.start();
                }
                if (this.p > 0) {
                    this.d.seekTo(this.p);
                    this.p = 0;
                }
                this.n = 3;
                if (this.m == null) {
                    return true;
                }
                this.m.a();
                return true;
            case 3:
                return true;
            case 4:
                this.d.prepareAsync();
                this.n = 9;
                if (this.m == null) {
                    return true;
                }
                this.m.a();
                return true;
            default:
                return false;
        }
    }

    public boolean i() {
        return d(true);
    }

    public boolean d(boolean z2) {
        if (this.n != 3) {
            return false;
        }
        this.d.pause();
        this.n = 5;
        if (this.m != null) {
            if (z2) {
                this.m.b();
            }
            if (!this.d.isPlaying()) {
                this.m.g();
            }
        }
        return true;
    }

    public boolean j() {
        switch (this.n) {
            case 1:
            case 9:
                return true;
            case 2:
            case 3:
            case 5:
            case 6:
                this.d.stop();
                this.n = 4;
                if (this.m == null) {
                    return true;
                }
                this.m.c();
                return true;
            default:
                return false;
        }
    }

    public void k() {
        this.n = 8;
        t();
        this.C = 1.0f;
        this.D = 0.0f;
        this.E = 1.0f;
    }

    public int l() {
        return this.q;
    }

    public boolean a(int i2) {
        Track track = (Track) this.A;
        if (!track.aN() || i2 < track.aM() * 1000) {
            this.y = i2;
            this.z = true;
            switch (this.n) {
                case 0:
                    if (this.w) {
                        this.w = false;
                        int a2 = this.d.a();
                        if (a2 == 3 || a2 == 7 || a2 == 4 || a2 == 5 || a2 == 11) {
                            this.n = 6;
                            h();
                            this.d.seekTo(i2);
                        }
                        return true;
                    }
                    break;
                case 2:
                case 3:
                case 5:
                    this.d.seekTo(i2);
                    return true;
                case 6:
                    this.d.start();
                    if (this.m != null) {
                        this.m.a();
                    }
                    this.d.seekTo(i2);
                    return true;
                case 9:
                    break;
                default:
                    return false;
            }
            this.p = i2;
            return true;
        }
        i();
        this.k.a(this.d, track.aM() * 1000);
        this.e.a(this.d);
        return false;
    }

    public int m() {
        return this.n;
    }

    public void a(IXmPlayerStatusListener iXmPlayerStatusListener) {
        this.m = iXmPlayerStatusListener;
    }

    private void t() {
        if (this.d != null) {
            try {
                r();
                this.d.stop();
                try {
                    this.d.release();
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
                try {
                    this.d.release();
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th) {
                try {
                    this.d.release();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                this.d = null;
                throw th;
            }
            this.d = null;
        }
        return;
        e.printStackTrace();
        this.d = null;
    }

    private XMediaplayerImpl u() {
        this.d = XmMediaPlayerFactory.a(this.u);
        v();
        s();
        this.d.a(FreeFlowServiceUtil.a(this.t));
        this.d.setSoundTouchAllParams(this.C, this.D, this.E);
        return this.d;
    }

    private void v() {
        if (this.e == null) {
            this.e = new XMediaPlayer.OnCompletionListener() {
                public void a(XMediaplayerImpl xMediaplayerImpl) {
                    Logger.a("XmPlayerControl onCompletion:" + System.currentTimeMillis());
                    if (XmPlayerControl.this.A != null) {
                        Track track = (Track) XmPlayerControl.this.A;
                        Logger.a("XmPlayerControl onCompletion track:" + track.toString());
                        if (track.aN()) {
                            int unused = XmPlayerControl.this.n = 0;
                            boolean unused2 = XmPlayerControl.this.w = true;
                        } else {
                            int unused3 = XmPlayerControl.this.n = 6;
                        }
                    } else {
                        int unused4 = XmPlayerControl.this.n = 6;
                    }
                    if (XmPlayerControl.this.m != null) {
                        XmPlayerControl.this.m.d();
                    }
                }
            };
        }
        if (this.f == null) {
            this.f = new XMediaPlayer.OnPreparedListener() {
                public void a(XMediaplayerImpl xMediaplayerImpl) {
                    Logger.a("XmPlayerControl onPrepared:" + System.currentTimeMillis());
                    if (XmPlayerControl.this.A != null) {
                        Logger.a("XmPlayerControl onPrepared track:" + ((Track) XmPlayerControl.this.A).toString());
                    }
                    int unused = XmPlayerControl.this.n = 2;
                    int unused2 = XmPlayerControl.this.q = xMediaplayerImpl.getDuration();
                    if (XmPlayerControl.this.m != null) {
                        XmPlayerControl.this.m.e();
                    }
                    if (XmPlayerControl.this.o) {
                        XmPlayerControl.this.h();
                    } else {
                        boolean unused3 = XmPlayerControl.this.o = true;
                    }
                    XmPlayerControl.this.w();
                }
            };
        }
        if (this.g == null) {
            this.g = new XMediaPlayer.OnSeekCompleteListener() {
                public void a(XMediaplayerImpl xMediaplayerImpl) {
                    int unused = XmPlayerControl.this.n;
                    if (XmPlayerControl.this.z) {
                        XmPlayerControl.b = (long) XmPlayerControl.this.y;
                        if (XmPlayerControl.this.x != null) {
                            XmPlayerControl.this.x.a(XmPlayerControl.this.y);
                        }
                        boolean unused2 = XmPlayerControl.this.z = false;
                    }
                }
            };
        }
        if (this.h == null) {
            this.h = new XMediaPlayer.OnErrorListener() {
                public boolean a(XMediaplayerImpl xMediaplayerImpl, int i, int i2) {
                    Logger.a("XmPlayerControl onError what:" + i + " extra:" + i2 + " time:" + System.currentTimeMillis());
                    if (XmPlayerControl.this.A != null) {
                        Logger.a("XmPlayerControl onError track:" + ((Track) XmPlayerControl.this.A).toString());
                    }
                    int unused = XmPlayerControl.this.n = 7;
                    if (XmPlayerControl.this.m == null) {
                        return true;
                    }
                    XmPlayerControl.this.m.a(new XmPlayerException(i, i2));
                    return true;
                }
            };
        }
        if (this.i == null) {
            this.i = new XMediaPlayer.OnInfoListener() {
                public boolean a(XMediaplayerImpl xMediaplayerImpl, int i, int i2) {
                    boolean z = true;
                    if (i == 701) {
                        boolean unused = XmPlayerControl.this.s = true;
                    } else if (i == 702) {
                        boolean unused2 = XmPlayerControl.this.s = false;
                    } else {
                        z = false;
                    }
                    if (XmPlayerControl.this.m != null) {
                        if (XmPlayerControl.this.s) {
                            XmPlayerControl.this.m.f();
                        } else {
                            XmPlayerControl.this.m.g();
                        }
                    }
                    return z;
                }
            };
        }
        if (this.j == null) {
            this.j = new XMediaPlayer.OnBufferingUpdateListener() {
                public void a(XMediaplayerImpl xMediaplayerImpl, int i) {
                    if (XmPlayerControl.this.m != null) {
                        XmPlayerControl.this.m.a(i);
                    }
                }
            };
        }
        if (this.k == null) {
            this.k = new XMediaPlayer.OnPositionChangeListener() {
                public void a(XMediaplayerImpl xMediaplayerImpl, int i) {
                    if (!xMediaplayerImpl.getAudioType().equals(XMediaplayerJNI.AudioType.HLS_FILE)) {
                        int duration = xMediaplayerImpl.getDuration();
                        if (duration > 0 && XmPlayerControl.this.m != null) {
                            int i2 = i - ((int) XmPlayerControl.b);
                            if (i2 > 0 && i2 <= 2000) {
                                XmPlayerControl.f2211a = (XmPlayerControl.f2211a + ((long) i)) - ((long) ((int) XmPlayerControl.b));
                            }
                            XmPlayerControl.b = (long) i;
                            XmPlayerControl.this.m.a(i, duration);
                            return;
                        }
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis() - XmPlayerControl.b;
                    if (currentTimeMillis > 0) {
                        XmPlayerControl.f2211a += currentTimeMillis;
                        XmPlayerControl.b = System.currentTimeMillis();
                    }
                    XmPlayerControl.this.m.a(0, 0);
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public void w() {
        Track j2;
        if (XmPlayerService.getPlayerSrvice() != null && XmPlayerService.getPlayerSrvice().mListControl != null && (j2 = XmPlayerService.getPlayerSrvice().mListControl.j()) != null && TextUtils.isEmpty(XmPlayerService.getPlayerSrvice().getDownloadUrl(j2))) {
            if (!j2.w()) {
                this.d.setPreBufferUrl(XmPlayerService.getPlayerSrvice().getTrackUrl(j2));
            } else if (this.B) {
                this.B = false;
                CommonRequest.a((Map<String, String>) new HashMap(), (IDataCallBack<String>) new IDataCallBack<String>() {
                    public void a(String str) {
                        boolean unused = XmPlayerControl.this.B = true;
                        XmPlayerControl.this.d.setPreBufferUrl(str);
                    }

                    public void a(int i, String str) {
                        boolean unused = XmPlayerControl.this.B = true;
                    }
                }, j2);
            }
        }
    }

    public void a(float f2, float f3, float f4) {
        this.C = f2;
        this.D = f3;
        this.E = f4;
        if (this.d != null) {
            Logger.a((Object) "setSoundTouchAllParams2 tempo:" + f2 + " pitch:" + f3 + " rate:" + f4);
            this.d.setSoundTouchAllParams(f2, f3, f4);
        }
    }

    public float n() {
        return this.C;
    }

    public boolean o() {
        return this.s;
    }

    public void a(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener) {
        if (this.d != null) {
            this.d.setOnPlayDataOutputListener(onPlayDataOutputListener);
        }
        this.l = onPlayDataOutputListener;
    }

    public boolean p() {
        return this.d != null && XMediaplayerJNI.AudioType.HLS_FILE.equals(this.d.getAudioType());
    }

    public void a(IPlaySeekListener iPlaySeekListener) {
        this.x = iPlaySeekListener;
    }
}
