package com.ximalaya.ting.android.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.FileDescriptor;
import java.util.Map;

public class XMediaPlayer extends XMediaplayerJNI implements Handler.Callback {
    private static final int F = 0;
    private static final int G = 1;
    private static final int H = 2;
    private static final int I = 3;
    private static final int J = 4;
    private static final int K = 5;
    private static final int L = 6;
    private static final int M = 7;
    private static final int N = 8;
    private static final int O = 9;
    private static final int P = 10;
    private static final int S = 0;
    private static final int T = 1;
    private static final int U = 2;
    private static final int V = 3;
    private static final int W = 4;
    private static final int X = 5;
    private static final int Y = 6;
    private static final int Z = 7;

    /* renamed from: a  reason: collision with root package name */
    public static final int f2291a = 1;
    private static final int aa = 8;
    private static final int ab = 9;
    private static final int ac = 99;
    private static final int ad = 100;
    private static final int ae = 200;
    private static final int af = 201;
    private static final int ag = 202;
    public static final int b = 100;
    public static final int c = 200;
    public static final int d = -1004;
    public static final int e = -1007;
    public static final int f = -1010;
    public static final int g = -1011;
    public static final int h = -110;
    public static final int i = 700;
    public static final int j = 701;
    public static final int k = 702;
    public static final int l = 801;
    public static final int m = 901;
    private Context A;
    private Handler B;
    private HandlerThread C;
    private PowerManager.WakeLock D = null;
    private boolean E;
    /* access modifiers changed from: private */
    public volatile int Q;
    private EventHandler R;
    private long ah = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public OnPreparedListener ai;
    /* access modifiers changed from: private */
    public OnCompletionListener aj;
    /* access modifiers changed from: private */
    public OnBufferingUpdateListener ak;
    /* access modifiers changed from: private */
    public OnSeekCompleteListener al;
    /* access modifiers changed from: private */
    public OnErrorListener am;
    /* access modifiers changed from: private */
    public OnInfoListener an;
    /* access modifiers changed from: private */
    public OnPositionChangeListener ao;

    public interface OnBufferingUpdateListener {
        void a(XMediaplayerImpl xMediaplayerImpl, int i);
    }

    public interface OnCompletionListener {
        void a(XMediaplayerImpl xMediaplayerImpl);
    }

    public interface OnErrorListener {
        boolean a(XMediaplayerImpl xMediaplayerImpl, int i, int i2);
    }

    public interface OnInfoListener {
        boolean a(XMediaplayerImpl xMediaplayerImpl, int i, int i2);
    }

    public interface OnPlayDataOutputListener {
        void a(byte[] bArr);
    }

    public interface OnPositionChangeListener {
        void a(XMediaplayerImpl xMediaplayerImpl, int i);
    }

    public interface OnPreparedListener {
        void a(XMediaplayerImpl xMediaplayerImpl);
    }

    public interface OnSeekCompleteListener {
        void a(XMediaplayerImpl xMediaplayerImpl);
    }

    public void a(int i2) {
    }

    public boolean c() {
        return false;
    }

    public XMediaPlayer(Context context, boolean z) {
        super(context, z);
        this.A = context.getApplicationContext();
        b();
    }

    private void b() {
        this.Q = 1;
        this.C = new PriorityHandlerThread(getClass().getSimpleName() + ":HandlerForPlayer", -16);
        this.C.start();
        this.B = new Handler(this.C.getLooper(), this);
        this.B.obtainMessage(10).sendToTarget();
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.R = new EventHandler(this, myLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.R = new EventHandler(this, mainLooper);
            } else {
                this.R = null;
            }
        }
        Logger.a((Object) "Mediaplayer XMediaPlayer init()");
    }

    public void setDataSource(String str) {
        this.B.obtainMessage(8, str).sendToTarget();
    }

    public void prepareAsync() {
        this.B.obtainMessage(1).sendToTarget();
    }

    public void start() {
        a(true);
        this.B.removeMessages(3);
        this.B.removeMessages(0);
        this.B.obtainMessage(0).sendToTarget();
    }

    public void stop() {
        a(false);
        this.B.removeMessages(3);
        this.B.removeMessages(0);
        this.B.removeMessages(6);
        this.B.obtainMessage(4).sendToTarget();
    }

    public void pause() {
        a(false);
        this.B.removeMessages(3);
        this.B.removeMessages(0);
        this.B.obtainMessage(3).sendToTarget();
    }

    public void release() {
        a(false);
        this.B.removeCallbacksAndMessages((Object) null);
        this.R.removeCallbacksAndMessages((Object) null);
        this.ai = null;
        this.ak = null;
        this.aj = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.B.obtainMessage(5).sendToTarget();
    }

    private void a(boolean z) {
        if (this.D != null) {
            if (z && !this.D.isHeld()) {
                this.D.acquire();
            } else if (!z && this.D.isHeld()) {
                this.D.release();
            }
        }
        this.E = z;
    }

    public void a(FileDescriptor fileDescriptor, String str) {
        setDataSource(str);
    }

    public void a(Context context, int i2) {
        boolean z;
        if (context != null) {
            if (this.D != null) {
                if (this.D.isHeld()) {
                    z = true;
                    this.D.release();
                } else {
                    z = false;
                }
                this.D = null;
            } else {
                z = false;
            }
            PowerManager powerManager = (PowerManager) context.getSystemService(CameraPropertyBase.l);
            if (powerManager != null) {
                this.D = powerManager.newWakeLock(i2 | 536870912, MediaPlayer.class.getName());
                this.D.setReferenceCounted(false);
                if (z) {
                    this.D.acquire();
                }
            }
        }
    }

    public int getCurrentPosition() {
        if (this.Q == 12) {
            return 0;
        }
        return super.getCurrentPosition();
    }

    public int getDuration() {
        if (this.Q == 12) {
            return 0;
        }
        return super.getDuration();
    }

    public boolean isPlaying() {
        if (this.Q == 12 || this.Q == 3 || !super.isPlaying() || this.Q != 4) {
            return false;
        }
        return true;
    }

    public void reset() {
        a(false);
        Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_RESET start send");
        this.B.removeCallbacksAndMessages((Object) null);
        this.R.removeCallbacksAndMessages((Object) null);
        this.B.obtainMessage(7).sendToTarget();
    }

    public void seekTo(int i2) {
        this.B.removeMessages(6);
        this.B.obtainMessage(6, Integer.valueOf(i2)).sendToTarget();
    }

    public void setVolume(float f2, float f3) {
        super.setVolume(f2, f3);
    }

    public int a() {
        return this.Q;
    }

    public boolean handleMessage(Message message) {
        if (this.Q == 12) {
            Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 mPlayState NOT_ARCH_SUPPORT");
            return true;
        }
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "handleMessage00 mPlayState:" + this.Q);
        try {
            switch (message.what) {
                case 0:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 STARTED start");
                    this.Q = 4;
                    super.start();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 STARTED end");
                    return true;
                case 1:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_PREPARE start");
                    this.Q = 2;
                    super.prepareAsync();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_PREPARE end");
                    return true;
                case 3:
                    if (this.Q != 8) {
                        Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_PAUSE start");
                        this.Q = 5;
                        super.pause();
                        Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_PAUSE end");
                    }
                    return true;
                case 4:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_STOP start");
                    this.Q = 6;
                    super.stop();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_STOP end");
                    return true;
                case 5:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_RELEASE start");
                    this.Q = 9;
                    super.release();
                    this.C.getLooper().quit();
                    this.C.interrupt();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_RELEASE end");
                    return true;
                case 6:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_SEEK_TO start");
                    super.seekTo(((Integer) message.obj).intValue());
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_SEEK_TO end");
                    return true;
                case 7:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_RESET start");
                    this.Q = 0;
                    super.reset();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_RESET end");
                    this.Q = 0;
                    return true;
                case 8:
                    if (message.obj != null) {
                        super.setDataSource(message.obj.toString());
                    }
                    return true;
                case 9:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_COMPLETE start");
                    this.Q = 11;
                    super.onCompletionInner();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage00 MSG_COMPLETE end");
                    return true;
                case 10:
                    Logger.a(XMediaplayerJNI.Tag, (Object) "Mediaplayer handleMessage00 MSG_INIT start");
                    xmediaplayerInit();
                    Logger.a(XMediaplayerJNI.Tag, (Object) "Mediaplayer handleMessage00 MSG_INIT end");
                    return true;
                default:
                    return false;
            }
        } catch (Exception unused) {
            return true;
        }
    }

    public final void onPreparedInner() {
        super.onPreparedInner();
        this.Q = 3;
        if (this.R != null) {
            this.R.obtainMessage(1).sendToTarget();
        }
    }

    public final void onSeekCompletedInner() {
        Logger.a(Tag, (Object) "onSeekCompletedInner");
        if (this.R != null) {
            this.R.obtainMessage(4).sendToTarget();
        }
    }

    public final void onInfoInner(int i2) {
        if (this.Q == 4 || this.Q == 2) {
            if (i2 == 701) {
                this.isBuffing = true;
            } else if (i2 == 702) {
                this.isBuffing = false;
            }
            if (this.R != null) {
                this.R.obtainMessage(200, i2, i2).sendToTarget();
            }
        }
    }

    public final void onErrorInner(int i2, int i3) {
        if (i3 == -1011) {
            this.Q = 12;
            Logger.a(XMediaplayerJNI.Tag, (Object) "onErrorInner mPlayState NOT_ARCH_SUPPORT");
        } else {
            String str = XMediaplayerJNI.Tag;
            Logger.a(str, (Object) "onErrorInner errorCode:" + i2 + "extra:" + i3);
            this.Q = 8;
        }
        super.onErrorInner(i2, i3);
        a(false);
        if (this.R != null) {
            this.R.obtainMessage(100, i2, i3).sendToTarget();
        }
    }

    public final void mOnTimedChangeListenerInner() {
        if (this.R != null) {
            this.R.removeMessages(202);
            this.R.obtainMessage(202).sendToTarget();
        }
    }

    public final void onCompletionInner() {
        a(false);
        this.B.obtainMessage(9).sendToTarget();
        if (this.R != null) {
            this.R.obtainMessage(2).sendToTarget();
        }
    }

    public final void onBufferingUpdateInner(int i2) {
        if (this.R != null && System.currentTimeMillis() - this.ah >= 1000) {
            this.ah = System.currentTimeMillis();
            this.R.removeMessages(3);
            this.R.obtainMessage(3, i2, 0).sendToTarget();
        }
    }

    private class EventHandler extends Handler {
        private XMediaPlayer b;

        public EventHandler(XMediaPlayer xMediaPlayer, Looper looper) {
            super(looper);
            this.b = xMediaPlayer;
        }

        public void handleMessage(Message message) {
            if (XMediaPlayer.this.Q != 12 || message.what == 100) {
                String str = XMediaplayerJNI.Tag;
                Logger.a(str, (Object) "handleMessage11 mPlayState:" + XMediaPlayer.this.Q);
                int i = message.what;
                switch (i) {
                    case 0:
                        return;
                    case 1:
                        if (XMediaPlayer.this.ai != null) {
                            XMediaPlayer.this.ai.a(this.b);
                            return;
                        }
                        return;
                    case 2:
                        if (XMediaPlayer.this.aj != null) {
                            XMediaPlayer.this.aj.a(this.b);
                            return;
                        }
                        return;
                    case 3:
                        if (XMediaPlayer.this.ak != null) {
                            XMediaPlayer.this.ak.a(this.b, message.arg1);
                            return;
                        }
                        return;
                    case 4:
                        if (XMediaPlayer.this.al != null) {
                            XMediaPlayer.this.al.a(this.b);
                            return;
                        }
                        return;
                    default:
                        switch (i) {
                            case 6:
                            case 7:
                            case 8:
                                return;
                            case 9:
                                return;
                            default:
                                switch (i) {
                                    case 99:
                                        return;
                                    case 100:
                                        String str2 = XMediaplayerJNI.Tag;
                                        Logger.a(str2, (Object) "Error (" + message.arg1 + "," + message.arg2 + Operators.BRACKET_END_STR);
                                        boolean z = false;
                                        if (XMediaPlayer.this.am != null) {
                                            z = XMediaPlayer.this.am.a(this.b, message.arg1, message.arg2);
                                        }
                                        if (XMediaPlayer.this.aj != null && !z) {
                                            XMediaPlayer.this.aj.a(this.b);
                                            return;
                                        }
                                        return;
                                    default:
                                        switch (i) {
                                            case 200:
                                                if (XMediaPlayer.this.an != null) {
                                                    XMediaPlayer.this.an.a(this.b, message.arg1, message.arg2);
                                                    return;
                                                }
                                                return;
                                            case 201:
                                                return;
                                            case 202:
                                                if (XMediaPlayer.this.ao != null && !XMediaPlayer.this.isSeeking()) {
                                                    XMediaPlayer.this.ao.a(this.b, this.b.getCurrentPosition());
                                                    return;
                                                }
                                                return;
                                            default:
                                                String str3 = XMediaplayerJNI.Tag;
                                                Logger.a(str3, (Object) "Unknown message type " + message.what);
                                                return;
                                        }
                                }
                        }
                }
            } else {
                Logger.a(XMediaplayerJNI.Tag, (Object) "handleMessage11 mPlayState NOT_ARCH_SUPPORT");
            }
        }
    }

    public void a(OnPreparedListener onPreparedListener) {
        this.ai = onPreparedListener;
    }

    public void a(OnCompletionListener onCompletionListener) {
        this.aj = onCompletionListener;
    }

    public void a(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.ak = onBufferingUpdateListener;
    }

    public void a(OnSeekCompleteListener onSeekCompleteListener) {
        this.al = onSeekCompleteListener;
    }

    public void a(OnErrorListener onErrorListener) {
        this.am = onErrorListener;
    }

    public void a(OnInfoListener onInfoListener) {
        this.an = onInfoListener;
    }

    public void a(OnPositionChangeListener onPositionChangeListener) {
        this.ao = onPositionChangeListener;
    }

    public void a(long j2) {
        if (j2 > 0) {
            XMediaPlayerConstants.l = (int) (j2 / 65536);
        }
    }

    public void a(HttpConfig httpConfig) {
        StaticConfig.a(httpConfig);
    }

    public void a(Map<String, String> map) {
        StaticConfig.a(map);
    }

    public void d() {
        StaticConfig.a((HttpConfig) null);
    }
}
