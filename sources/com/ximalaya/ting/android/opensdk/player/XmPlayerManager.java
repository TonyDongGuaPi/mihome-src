package com.ximalaya.ting.android.opensdk.player;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.live.program.Program;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.schedule.Schedule;
import com.ximalaya.ting.android.opensdk.model.live.schedule.ScheduleList;
import com.ximalaya.ting.android.opensdk.model.statistic.RecordModel;
import com.ximalaya.ting.android.opensdk.model.token.AccessToken;
import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.advertis.OpenSdkAdsDataHandler;
import com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants;
import com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher;
import com.ximalaya.ting.android.opensdk.player.service.IXmCommonBusinessDispatcher;
import com.ximalaya.ting.android.opensdk.player.service.IXmCommonBusinessHandle;
import com.ximalaya.ting.android.opensdk.player.service.IXmDataCallback;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayer;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.IXmTokenInvalidForSDKCallBack;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.ModelUtil;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.player.PlayerUtil;
import com.ximalaya.ting.android.player.XMediaPlayerConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class XmPlayerManager {
    private static int D = 20;
    private static Config E = null;
    /* access modifiers changed from: private */
    public static int U = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f2141a = "XmPlayerServiceManager";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final int l = 12;
    private static final int m = 13;
    private static final int n = 14;
    private static final int o = 15;
    private static final int p = 16;
    private static final int q = 17;
    private static final int r = 18;
    private static final int s = 19;
    private static final int t = 20;
    private static volatile XmPlayerManager u;
    private static byte[] v = new byte[0];
    /* access modifiers changed from: private */
    public UIHandler A;
    /* access modifiers changed from: private */
    public int B = 0;
    /* access modifiers changed from: private */
    public Notification C;
    /* access modifiers changed from: private */
    public boolean F;
    private List<IXmPlayerStatusListener> G = new CopyOnWriteArrayList();
    private List<IXmAdsStatusListener> H = new CopyOnWriteArrayList();
    private ServiceConnection I = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.c(XmPlayerManager.f2141a, "onServiceDisconnected");
            boolean unused = XmPlayerManager.this.y = false;
            boolean unused2 = XmPlayerManager.this.z = false;
            XmPlayerManager.this.a(XmPlayerManager.this.B, XmPlayerManager.this.C);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Logger.c(XmPlayerManager.f2141a, "onServiceConnected progress:" + Process.myPid());
                boolean unused = XmPlayerManager.this.y = true;
                boolean unused2 = XmPlayerManager.this.z = true;
                IXmPlayer unused3 = XmPlayerManager.this.w = IXmPlayer.Stub.asInterface(iBinder);
                XmPlayerManager.this.w.registePlayerListener(XmPlayerManager.this.P);
                XmPlayerManager.this.w.registeAdsListener(XmPlayerManager.this.O);
                if (BaseUtil.b(XmPlayerManager.this.x)) {
                    XmPlayerManager.this.w.setPlayerProcessRequestEnvironment(XmPlayerManager.U);
                    XmPlayerManager.this.w.registeCommonBusinessListener(XmPlayerManager.this.M);
                    XmPlayerManager.this.w.setPlayListChangeListener(XmPlayerManager.this.R);
                    if (XmPlayerManager.this.C != null) {
                        XmPlayerManager.this.w.setNotification(XmPlayerManager.this.B, XmPlayerManager.this.C);
                    }
                }
                if (!TextUtils.isEmpty(CommonRequest.a().e()) && !BaseUtil.d() && BaseUtil.b(XmPlayerManager.this.x)) {
                    if (CommonRequest.a().b() != null) {
                        XmPlayerManager.this.w.setTokenInvalidForSDK(XmPlayerManager.this.L);
                    }
                    XmPlayerManager.this.w.setAppSecret(CommonRequest.a().e());
                    XmPlayerManager.this.w.setAppkeyAndPackId(CommonRequest.a().f(), CommonRequest.a().l());
                    XmPlayerManager.this.w.setTokenToPlayForSDK(AccessTokenManager.a().f());
                }
                if (!BaseUtil.d() && BaseUtil.b(XmPlayerManager.this.x)) {
                    XmPlayerManager.this.w.setAdsDataHandlerClassName(OpenSdkAdsDataHandler.class.getName());
                    XmPlayerManager.this.w.setPlayStatisticClassName(OpenSdkPlayStatisticUpload.class.getName());
                }
                XmPlayerManager.this.U();
                XmPlayerManager.this.X();
                Logger.c(XmPlayerManager.f2141a, "onServiceConnected123");
                for (IConnectListener iConnectListener : XmPlayerManager.this.J) {
                    if (iConnectListener != null) {
                        iConnectListener.a();
                    }
                }
                if (XmPlayerManager.this.K != null) {
                    XmPlayerManager.this.K.a();
                }
                PlayableModel unused4 = XmPlayerManager.this.T = XmPlayerManager.this.w.getTrack(XmPlayerManager.this.w.getCurrIndex());
                boolean n = XmPlayerManager.this.F;
                if (ConstantsOpenSdk.b) {
                    n = SharedPreferencesUtil.a(XmPlayerManager.this.x).b(PreferenceConstantsInOpenSdk.af, true) && XmPlayerManager.this.F;
                }
                XmPlayerManager.this.w.setCheckAdContent(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public Set<IConnectListener> J = new HashSet();
    /* access modifiers changed from: private */
    public IConnectListener K;
    /* access modifiers changed from: private */
    public IXmTokenInvalidForSDKCallBack.Stub L = new IXmTokenInvalidForSDKCallBack.Stub() {
        public void tokenInvalid() throws RemoteException {
            CommonRequest.ITokenStateChange b = CommonRequest.a().b();
            if (b == null) {
                AccessTokenManager.a().c();
            } else if (!b.b()) {
                b.c();
            }
        }
    };
    /* access modifiers changed from: private */
    public IXmCommonBusinessDispatcher.Stub M = new IXmCommonBusinessDispatcher.Stub() {
        public String getDownloadPlayPath(Track track) throws RemoteException {
            if (XmPlayerManager.this.N != null) {
                return XmPlayerManager.this.N.a(track);
            }
            return null;
        }

        public void isOldTrackDownload(Track track) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(19);
            obtainMessage.obj = track;
            obtainMessage.sendToTarget();
        }

        public void closeApp() throws RemoteException {
            XmPlayerManager.this.A.removeCallbacksAndMessages((Object) null);
            XmPlayerManager.this.A.obtainMessage(20).sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public IXmCommonBusinessHandle N;
    /* access modifiers changed from: private */
    public IXmAdsEventDispatcher.Stub O = new IXmAdsEventDispatcher.Stub() {
        public void onStartPlayAds(Advertis advertis, int i) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(16);
            obtainMessage.arg1 = i;
            obtainMessage.obj = advertis;
            obtainMessage.sendToTarget();
        }

        public void onStartGetAdsInfo() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(12).sendToTarget();
        }

        public void onGetAdsInfo(AdvertisList advertisList) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(13);
            obtainMessage.obj = advertisList;
            obtainMessage.sendToTarget();
        }

        public void onError(int i, int i2) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(18);
            obtainMessage.arg1 = i;
            obtainMessage.arg2 = i2;
            obtainMessage.sendToTarget();
        }

        public void onCompletePlayAds() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(17).sendToTarget();
        }

        public void onAdsStopBuffering() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(15).sendToTarget();
        }

        public void onAdsStartBuffering() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(14).sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public IXmPlayerEventDispatcher.Stub P = new IXmPlayerEventDispatcher.Stub() {
        public void onSoundPrepared() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(5).sendToTarget();
        }

        public void onPlayStop() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(3).sendToTarget();
        }

        public void onPlayStart() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(1).sendToTarget();
        }

        public void onPlayProgress(int i, int i2) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(7);
            obtainMessage.arg1 = i;
            obtainMessage.arg2 = i2;
            obtainMessage.sendToTarget();
        }

        public void onPlayPause() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(2).sendToTarget();
        }

        public void onSoundPlayComplete() throws RemoteException {
            XmPlayerManager.this.A.obtainMessage(4).sendToTarget();
        }

        public void onError(XmPlayerException xmPlayerException) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(10);
            obtainMessage.obj = xmPlayerException;
            obtainMessage.sendToTarget();
        }

        public void onBufferProgress(int i) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(9);
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }

        public void onBufferingStart() throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(6);
            obtainMessage.obj = true;
            obtainMessage.sendToTarget();
        }

        public void onBufferingStop() throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(6);
            obtainMessage.obj = false;
            obtainMessage.sendToTarget();
        }

        public void onSoundSwitch(Track track, Track track2) throws RemoteException {
            Message obtainMessage = XmPlayerManager.this.A.obtainMessage(8);
            obtainMessage.obj = new Object[]{track, track2};
            obtainMessage.sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public IXmDataCallback Q;
    /* access modifiers changed from: private */
    public IXmDataCallback.Stub R = new IXmDataCallback.Stub() {
        public void onDataReady(List<Track> list, boolean z, boolean z2) throws RemoteException {
            if (XmPlayerManager.this.Q != null) {
                XmPlayerManager.this.Q.onDataReady(list, z, z2);
            }
        }

        public void onError(int i, String str, boolean z) throws RemoteException {
            if (XmPlayerManager.this.Q != null) {
                XmPlayerManager.this.Q.onError(i, str, z);
            }
        }
    };
    private List<Track> S;
    /* access modifiers changed from: private */
    public PlayableModel T;
    private int V = 0;
    /* access modifiers changed from: private */
    public IXmPlayer w;
    /* access modifiers changed from: private */
    public Context x;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public boolean z = false;

    public interface IConnectListener {
        void a();
    }

    public void a(IConnectListener iConnectListener) {
        this.J.add(iConnectListener);
    }

    public void b(IConnectListener iConnectListener) {
        this.J.remove(iConnectListener);
    }

    @Deprecated
    public void c(IConnectListener iConnectListener) {
        this.K = iConnectListener;
    }

    public void a(IXmCommonBusinessHandle iXmCommonBusinessHandle) {
        this.N = iXmCommonBusinessHandle;
    }

    public static XmPlayerManager a(Context context) {
        if (ConstantsOpenSdk.b) {
            Context context2 = (context != null || u == null) ? context : u.x;
            if (context2 != null && !BaseUtil.b(context2) && BaseUtil.d()) {
                Thread.dumpStack();
                throw new RuntimeException("only main process can use this method");
            }
        }
        if (u == null) {
            synchronized (v) {
                if (u == null) {
                    u = new XmPlayerManager(context);
                }
            }
        }
        return u;
    }

    public void a(Config config) {
        E = config;
        if (V()) {
            try {
                U();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void U() throws RemoteException {
        if (this.w != null) {
            this.w.setProxyNew(E);
        }
    }

    private boolean V() {
        return this.y && this.w != null && this.w.asBinder() != null && this.w.asBinder().isBinderAlive();
    }

    /* access modifiers changed from: private */
    public boolean W() {
        if (this.y && this.w != null && this.w.asBinder() != null && this.w.asBinder().isBinderAlive()) {
            return true;
        }
        Logger.c(f2141a, "checkConnectionStatus disconnected");
        a(this.B, this.C);
        return false;
    }

    private XmPlayerManager(Context context) {
        this.x = context.getApplicationContext();
        this.A = new UIHandler(Looper.getMainLooper());
        XMediaPlayerConstants.a(this.x);
    }

    public boolean a() {
        return this.y;
    }

    public void a(IXmPlayerStatusListener iXmPlayerStatusListener) {
        if (iXmPlayerStatusListener != null && !this.G.contains(iXmPlayerStatusListener)) {
            this.G.add(iXmPlayerStatusListener);
        }
    }

    public void b(IXmPlayerStatusListener iXmPlayerStatusListener) {
        if (iXmPlayerStatusListener != null && this.G != null) {
            this.G.remove(iXmPlayerStatusListener);
        }
    }

    public boolean c(IXmPlayerStatusListener iXmPlayerStatusListener) {
        return this.G.contains(iXmPlayerStatusListener);
    }

    public void a(IXmAdsStatusListener iXmAdsStatusListener) {
        if (iXmAdsStatusListener != null && !this.H.contains(iXmAdsStatusListener)) {
            this.H.add(iXmAdsStatusListener);
        }
    }

    public void b(IXmAdsStatusListener iXmAdsStatusListener) {
        if (iXmAdsStatusListener != null && this.H != null) {
            this.H.remove(iXmAdsStatusListener);
        }
    }

    public void b() {
        try {
            this.x.startService(XmPlayerService.getIntent(this.x));
            this.z = this.x.bindService(XmPlayerService.getIntent(this.x), this.I, 1);
            Logger.c(f2141a, "Bind ret " + this.z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(int i2, Notification notification) {
        if (BaseUtil.d()) {
            b();
            return;
        }
        this.B = i2;
        this.C = notification;
        b();
        if (this.y && this.w != null && this.w.asBinder() != null && this.w.asBinder().isBinderAlive()) {
            try {
                this.w.setNotification(this.B, this.C);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void c() {
        Logger.c(f2141a, "unBind release");
        if (u != null) {
            if (u.z || !(u.w == null || u.w.asBinder() == null || !u.w.asBinder().isBinderAlive())) {
                u.x.unbindService(u.I);
                u.z = false;
            }
            u.C = null;
            u.G.clear();
            u.H.clear();
            u.J.clear();
            u.K = null;
            u.T = null;
            u.y = false;
            u.w = null;
            u = null;
        }
    }

    public static void d() {
        Logger.c(f2141a, "release");
        if (u != null) {
            u.o();
            u.p();
            if (u.z || !(u.w == null || u.w.asBinder() == null || !u.w.asBinder().isBinderAlive())) {
                u.x.unbindService(u.I);
                u.z = false;
            }
            u.x.stopService(XmPlayerService.getIntent(u.x));
            u.C = null;
            u.G.clear();
            u.H.clear();
            u.J.clear();
            u.K = null;
            u.T = null;
            u.y = false;
            u.w = null;
        }
    }

    public boolean e() {
        if (!W()) {
            return false;
        }
        try {
            return this.w.permutePlayList();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean f() {
        if (!V()) {
            return true;
        }
        try {
            return this.w.getPlayListOrder();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public void g() {
        if (V()) {
            try {
                this.w.getNextPlayList();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void h() {
        if (V()) {
            try {
                this.w.getPrePlayList();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(IXmDataCallback iXmDataCallback) {
        this.Q = iXmDataCallback;
    }

    public int i() {
        if (!V()) {
            return 7;
        }
        try {
            return this.w.getPlayerStatus();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 7;
        }
    }

    public int j() {
        if (!V()) {
            return -1;
        }
        try {
            return this.w.getCurrIndex();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private PlayableModel c(Track track) {
        if (!W()) {
            return null;
        }
        int i2 = -1;
        try {
            i2 = this.w.getPlaySourceType();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (track != null) {
            if (i2 == 2) {
                return (track == null || !"schedule".equals(track.b())) ? track : ModelUtil.b(track);
            }
            if (i2 == 3) {
                if ("radio".equals(track.b())) {
                    return ModelUtil.a(track);
                }
                if ("schedule".equals(track.b())) {
                    return ModelUtil.b(track);
                }
            }
        }
        return null;
    }

    public PlayableModel k() {
        return b(true);
    }

    public Track a(boolean z2) {
        if (!V()) {
            return null;
        }
        if (this.T != null && z2) {
            return (Track) this.T;
        }
        try {
            return this.w.getTrack(this.w.getCurrIndex());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public PlayableModel b(boolean z2) {
        if (!V()) {
            return null;
        }
        if (this.T != null && z2) {
            return c((Track) this.T);
        }
        try {
            return c(this.w.getTrack(this.w.getCurrIndex()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public PlayableModel c(boolean z2) {
        if (!V()) {
            return null;
        }
        if (this.T != null && z2) {
            PlayableModel playableModel = this.T;
        }
        try {
            Track track = this.w.getTrack(this.w.getCurrIndex());
            if (track == null || !track.f) {
                return null;
            }
            return track;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void d(boolean z2) {
        if (V()) {
            try {
                this.w.setDLNAState(z2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean l() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.isDLNAState();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void m() {
        if (W()) {
            try {
                if (this.V == 3) {
                    LocalBroadcastManager.getInstance(this.x).sendBroadcast(new Intent(PlayerConstants.C));
                } else {
                    this.w.startPlay();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int n() {
        if (!V()) {
            return 0;
        }
        try {
            return this.w.getPlayCurrPosition();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public void a(int i2) {
        if (W()) {
            try {
                this.w.play(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void o() {
        if (W()) {
            try {
                if (this.V == 2) {
                    LocalBroadcastManager.getInstance(this.x).sendBroadcast(new Intent(PlayerConstants.D));
                } else {
                    this.w.pausePlay();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void p() {
        if (W()) {
            try {
                this.w.stopPlay();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void q() {
        if (W()) {
            try {
                this.w.playPre();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void r() {
        if (W()) {
            try {
                this.w.playNext();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(XmPlayListControl.PlayMode playMode) {
        if (W()) {
            try {
                this.w.setPlayMode(playMode.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(float f2) {
        a(f2, 0.0f, 1.0f);
    }

    public void a(float f2, float f3, float f4) {
        if (W()) {
            try {
                Logger.a((Object) "setSoundTouchAllParams1 tempo:" + f2 + " pitch:" + f3 + " rate:" + f4);
                this.w.setSoundTouchAllParams(f2, f3, f4);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public XmPlayListControl.PlayMode s() {
        if (!V()) {
            return XmPlayListControl.PlayMode.PLAY_MODEL_LIST;
        }
        try {
            return XmPlayListControl.PlayMode.valueOf(this.w.getPlayMode());
        } catch (Exception e2) {
            e2.printStackTrace();
            return XmPlayListControl.PlayMode.PLAY_MODEL_LIST;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0029 A[Catch:{ Exception -> 0x002d }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r2) {
        /*
            r1 = this;
            int r0 = D
            if (r0 != r2) goto L_0x0005
            return
        L_0x0005:
            D = r2
            boolean r2 = r1.y     // Catch:{ Exception -> 0x002d }
            if (r2 == 0) goto L_0x0026
            com.ximalaya.ting.android.opensdk.player.service.IXmPlayer r2 = r1.w     // Catch:{ Exception -> 0x002d }
            if (r2 == 0) goto L_0x0026
            com.ximalaya.ting.android.opensdk.player.service.IXmPlayer r2 = r1.w     // Catch:{ Exception -> 0x002d }
            android.os.IBinder r2 = r2.asBinder()     // Catch:{ Exception -> 0x002d }
            if (r2 == 0) goto L_0x0026
            com.ximalaya.ting.android.opensdk.player.service.IXmPlayer r2 = r1.w     // Catch:{ Exception -> 0x002d }
            android.os.IBinder r2 = r2.asBinder()     // Catch:{ Exception -> 0x002d }
            boolean r2 = r2.isBinderAlive()     // Catch:{ Exception -> 0x002d }
            if (r2 != 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r2 = 0
            goto L_0x0027
        L_0x0026:
            r2 = 1
        L_0x0027:
            if (r2 != 0) goto L_0x0031
            r1.X()     // Catch:{ Exception -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.XmPlayerManager.b(int):void");
    }

    /* access modifiers changed from: private */
    public void X() throws RemoteException {
        if (W() && this.w != null) {
            this.w.setPageSize(D);
        }
    }

    public List<Track> t() {
        if (!V()) {
            return null;
        }
        this.S = new ArrayList();
        int i2 = 0;
        while (true) {
            try {
                List<Track> playList = this.w.getPlayList(i2);
                if (playList == null) {
                    return this.S;
                }
                this.S.addAll(playList);
                if (playList.size() < 30) {
                    return this.S;
                }
                i2++;
            } catch (Exception e2) {
                e2.printStackTrace();
                return this.S;
            }
        }
    }

    public int u() {
        if (!V()) {
            return 0;
        }
        try {
            return this.w.getPlayListSize();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public Track c(int i2) {
        if (!V()) {
            return null;
        }
        try {
            return this.w.getTrack(i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean a(List<Schedule> list, int i2) {
        if (!W() || list == null) {
            return false;
        }
        try {
            this.w.setPlayList((Map) null, ModelUtil.a(list));
            if (i2 == -1) {
                int i3 = 0;
                while (true) {
                    if (i3 >= list.size()) {
                        break;
                    }
                    if (BaseUtil.a(list.get(i3).j() + "-" + list.get(i3).k()) == 0) {
                        i2 = i3;
                        break;
                    }
                    i3++;
                }
            }
            this.w.play(i2);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean a(Radio radio) {
        if (!W() || radio == null) {
            return false;
        }
        CommonTrackList commonTrackList = new CommonTrackList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ModelUtil.a(radio, false));
        commonTrackList.a(arrayList);
        commonTrackList.b(1);
        commonTrackList.c(1);
        a(commonTrackList, 0);
        return true;
    }

    public void a(Radio radio, int i2, int i3) {
        if (W() && radio != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("radio_id", radio.a() + "");
            if (i2 >= 0) {
                hashMap.put(DTransferConstants.J, i2 + "");
            }
            final boolean z2 = i2 == Calendar.getInstance().get(7) - 1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy:MM:dd");
            Calendar instance = Calendar.getInstance();
            if (i2 >= 0) {
                instance.set(7, i2 + 1);
            }
            final String format = simpleDateFormat.format(instance.getTime());
            final Radio radio2 = radio;
            final int i4 = i3;
            CommonRequest.Q(hashMap, new IDataCallBack<ScheduleList>() {
                public void a(int i, String str) {
                }

                public void a(ScheduleList scheduleList) {
                    if (XmPlayerManager.this.W() && scheduleList != null) {
                        ArrayList arrayList = new ArrayList();
                        if (scheduleList.a() == null || scheduleList.a().isEmpty()) {
                            Schedule a2 = ModelUtil.a(radio2);
                            if (a2 != null) {
                                arrayList.add(a2);
                                XmPlayerManager.this.a((List<Schedule>) arrayList, -1);
                                return;
                            }
                            return;
                        }
                        List<Schedule> a3 = scheduleList.a();
                        Iterator<Schedule> it = a3.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Schedule next = it.next();
                            next.c(format + ":" + next.j());
                            next.d(format + ":" + next.k());
                            Program l = next.l();
                            if (l == null) {
                                l = new Program();
                                next.a(l);
                            }
                            l.b(radio2.v());
                            next.e(radio2.a());
                            next.f(radio2.h());
                            next.c(radio2.t());
                            if (z2) {
                                if (BaseUtil.a(next.j() + "-" + next.k()) == 0) {
                                    l.c(radio2.p());
                                    l.d(radio2.q());
                                    l.e(radio2.r());
                                    l.f(radio2.s());
                                    break;
                                }
                            }
                        }
                        XmPlayerManager.this.a(a3, i4);
                    }
                }
            });
        }
    }

    public boolean b(Radio radio) {
        if (!W() || radio == null) {
            return false;
        }
        CommonTrackList commonTrackList = new CommonTrackList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ModelUtil.a(radio, true));
        commonTrackList.a(arrayList);
        commonTrackList.b(1);
        commonTrackList.c(1);
        a(commonTrackList, 0);
        return true;
    }

    public void b(List<Track> list, int i2) {
        if (W()) {
            if (list == null || list.size() == 0) {
                Logger.c(f2141a, "Empty TrackList");
            } else {
                a((Map<String, String>) null, list, i2, true);
            }
        }
    }

    public void a(CommonTrackList commonTrackList, int i2) {
        if (W() && commonTrackList != null && commonTrackList.c() != null && commonTrackList.c().size() != 0) {
            a(commonTrackList.d(), commonTrackList.c(), i2, true);
        }
    }

    public void c(List<Track> list, int i2) {
        if (W() && list != null && list.size() != 0) {
            a((Map<String, String>) null, list, i2, false);
        }
    }

    public void b(CommonTrackList commonTrackList, int i2) {
        if (W() && commonTrackList != null && commonTrackList.c() != null && commonTrackList.c().size() != 0) {
            a(commonTrackList.d(), commonTrackList.c(), i2, false);
        }
    }

    private void a(Map<String, String> map, List<Track> list, int i2, boolean z2) {
        if (W() && list != null) {
            try {
                int size = list.size();
                if (size < 30) {
                    this.w.setPlayList(map, list);
                } else {
                    for (int i3 = 0; i3 < size / 30; i3++) {
                        if (i3 == 0) {
                            this.w.setPlayList(map, list.subList(i3 * 30, (i3 + 1) * 30));
                        } else {
                            this.w.addPlayList(list.subList(i3 * 30, (i3 + 1) * 30));
                        }
                    }
                    int i4 = size % 30;
                    if (i4 != 0) {
                        int i5 = 30 * (size / 30);
                        this.w.addPlayList(list.subList(i5, i4 + i5));
                    }
                }
                if (z2) {
                    this.w.play(i2);
                } else {
                    this.w.setPlayIndex(i2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public CommonTrackList v() {
        if (!V()) {
            return null;
        }
        try {
            CommonTrackList commonTrackList = new CommonTrackList();
            commonTrackList.a((Map<String, String>) this.w.getParam());
            commonTrackList.a(t());
            return commonTrackList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean w() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.isOnlineSource();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean x() {
        if (!V()) {
            return false;
        }
        try {
            if (this.V == 2) {
                return true;
            }
            return this.w.isPlaying();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean y() {
        if (!V()) {
            return false;
        }
        try {
            if (this.V == 2) {
                return true;
            }
            if (this.V == 0) {
                return true;
            }
            return this.w.isAdsActive();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean z() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.hasPreSound();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean A() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.hasNextSound();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public int B() {
        if (!V()) {
            return 0;
        }
        try {
            return this.w.getDuration();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public void b(float f2) {
        d((int) (((float) B()) * f2));
    }

    public void d(int i2) {
        if (W()) {
            try {
                if (this.V != 1 && this.V != 2) {
                    if (this.V != 3) {
                        this.w.seekTo(i2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void C() {
        if (V()) {
            try {
                this.w.clearPlayCache();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static long D() {
        return PlayerUtil.g();
    }

    /* access modifiers changed from: private */
    public void Y() {
        for (IXmPlayerStatusListener e2 : this.G) {
            e2.e();
        }
    }

    public void a(Track track) {
        if (V()) {
            try {
                if (this.w.updateTrackInPlayList(track)) {
                    this.T = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void b(Track track) {
        if (W()) {
            try {
                this.w.updateTrackDownloadUrlInPlayList(track);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int E() {
        if (!V()) {
            return -1;
        }
        try {
            return this.w.getPlaySourceType();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public String F() {
        if (!V()) {
            return null;
        }
        try {
            return this.w.getCurPlayUrl();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(float f2, float f3) {
        if (W()) {
            try {
                this.w.setVolume(f2, f3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(CdnConfigModel cdnConfigModel) {
        if (V()) {
            try {
                this.w.setPlayCdnConfigureModel(cdnConfigModel);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(PlayableModel playableModel, PlayableModel playableModel2) {
        this.T = playableModel2;
        for (IXmPlayerStatusListener a2 : this.G) {
            a2.a(playableModel, playableModel2);
        }
    }

    /* access modifiers changed from: private */
    public void Z() {
        for (IXmPlayerStatusListener c2 : this.G) {
            c2.c();
        }
    }

    /* access modifiers changed from: private */
    public void aa() {
        for (IXmPlayerStatusListener a2 : this.G) {
            a2.a();
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        for (IXmPlayerStatusListener a2 : this.G) {
            a2.a(i2, i3);
        }
    }

    /* access modifiers changed from: private */
    public void ab() {
        for (IXmPlayerStatusListener b2 : this.G) {
            b2.b();
        }
    }

    /* access modifiers changed from: private */
    public void ac() {
        for (IXmPlayerStatusListener d2 : this.G) {
            d2.d();
        }
    }

    /* access modifiers changed from: private */
    public void a(XmPlayerException xmPlayerException) {
        for (IXmPlayerStatusListener a2 : this.G) {
            a2.a(xmPlayerException);
        }
    }

    /* access modifiers changed from: private */
    public void h(int i2) {
        for (IXmPlayerStatusListener a2 : this.G) {
            a2.a(i2);
        }
    }

    /* access modifiers changed from: private */
    public void h(boolean z2) {
        for (IXmPlayerStatusListener next : this.G) {
            if (z2) {
                next.f();
            } else {
                next.g();
            }
        }
    }

    /* access modifiers changed from: private */
    public void ad() {
        for (IXmAdsStatusListener a2 : this.H) {
            a2.a();
        }
    }

    /* access modifiers changed from: private */
    public void a(AdvertisList advertisList) {
        for (IXmAdsStatusListener a2 : this.H) {
            a2.a(advertisList);
        }
    }

    /* access modifiers changed from: private */
    public void ae() {
        for (IXmAdsStatusListener b2 : this.H) {
            b2.b();
        }
    }

    /* access modifiers changed from: private */
    public void af() {
        for (IXmAdsStatusListener c2 : this.H) {
            c2.c();
        }
    }

    /* access modifiers changed from: private */
    public void a(Advertis advertis, int i2) {
        for (IXmAdsStatusListener a2 : this.H) {
            a2.a(advertis, i2);
        }
    }

    /* access modifiers changed from: private */
    public void ag() {
        for (IXmAdsStatusListener d2 : this.H) {
            d2.d();
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2, int i3) {
        for (IXmAdsStatusListener a2 : this.H) {
            a2.a(i2, i3);
        }
    }

    private class UIHandler extends Handler {
        public UIHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    XmPlayerManager.this.aa();
                    return;
                case 2:
                    XmPlayerManager.this.ab();
                    return;
                case 3:
                    XmPlayerManager.this.Z();
                    return;
                case 4:
                    XmPlayerManager.this.ac();
                    return;
                case 5:
                    XmPlayerManager.this.Y();
                    return;
                case 6:
                    if (message.obj instanceof Boolean) {
                        XmPlayerManager.this.h(((Boolean) message.obj).booleanValue());
                        return;
                    }
                    return;
                case 7:
                    XmPlayerManager.this.a(message.arg1, message.arg2);
                    return;
                case 8:
                    Object[] objArr = (Object[]) message.obj;
                    XmPlayerManager.this.a((PlayableModel) objArr[0], (PlayableModel) objArr[1]);
                    return;
                case 9:
                    XmPlayerManager.this.h(message.arg1);
                    return;
                case 10:
                    XmPlayerManager.this.a((XmPlayerException) message.obj);
                    return;
                case 12:
                    XmPlayerManager.this.ad();
                    return;
                case 13:
                    XmPlayerManager.this.a((AdvertisList) message.obj);
                    return;
                case 14:
                    XmPlayerManager.this.ae();
                    return;
                case 15:
                    XmPlayerManager.this.af();
                    return;
                case 16:
                    XmPlayerManager.this.a((Advertis) message.obj, message.arg1);
                    return;
                case 17:
                    XmPlayerManager.this.ag();
                    return;
                case 18:
                    XmPlayerManager.this.b(message.arg1, message.arg2);
                    return;
                case 19:
                    XmPlayerManager.this.d((Track) message.obj);
                    return;
                case 20:
                    XmPlayerManager.this.ah();
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void ah() {
        if (this.N != null) {
            this.N.a();
        }
    }

    /* access modifiers changed from: private */
    public void d(Track track) {
        if (this.N != null) {
            this.N.b(track);
        }
    }

    public void G() {
        if (V()) {
            try {
                this.w.resetPlayList();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void e(int i2) {
        if (V()) {
            try {
                this.w.removeListByIndex(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void e(boolean z2) {
        if (V()) {
            try {
                this.w.needContinuePlay(z2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(RecordModel recordModel) {
        if (V()) {
            try {
                this.w.setRecordModel(recordModel);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int a(long j2) {
        if (!V()) {
            return -1;
        }
        try {
            String historyPos = this.w.getHistoryPos(String.valueOf(j2));
            if (TextUtils.isEmpty(historyPos)) {
                return -1;
            }
            Logger.a((Object) "XmPlayerManager HistoryPos result:" + historyPos);
            return Integer.parseInt(historyPos);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void a(long j2, int i2) {
        if (V()) {
            try {
                this.w.setHistoryPosById(j2, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(List<? extends Track> list) {
        int i2;
        if (V() && list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<? extends Track> it = list.iterator();
            boolean z2 = true;
            while (true) {
                i2 = 0;
                if (it.hasNext()) {
                    Track track = (Track) it.next();
                    if (z2) {
                        z2 = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append(track.a());
                } else {
                    try {
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
            String historyPos = this.w.getHistoryPos(sb.toString());
            if (!TextUtils.isEmpty(historyPos)) {
                String[] split = historyPos.split(",");
                if (split.length == list.size()) {
                    for (Track a2 : list) {
                        a2.a(Integer.parseInt(split[i2]));
                        i2++;
                    }
                    Logger.a((Object) "HistoryPos result:" + historyPos);
                } else if (ConstantsOpenSdk.b) {
                    throw new RuntimeException("track list not equal length with callback data length");
                }
            }
        }
    }

    public static void f(int i2) {
        if (u == null) {
            U = i2;
        } else if (u.V()) {
            try {
                u.w.setPlayerProcessRequestEnvironment(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void H() {
        if (V()) {
            try {
                this.w.requestSoundAd();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void b(long j2) {
        if (W()) {
            try {
                this.w.pausePlayInMillis(j2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean I() {
        if (!V()) {
            return false;
        }
        try {
            if (this.V == 2) {
                return true;
            }
            return this.w.isAdPlaying();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean J() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.haveNextPlayList();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean K() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.havePrePlayList();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean L() {
        if (!V()) {
            return false;
        }
        try {
            return this.w.isBuffering();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void a(AccessToken accessToken) {
        if (W()) {
            try {
                this.w.setTokenToPlayForSDK(accessToken);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(CommonRequest.ITokenStateChange iTokenStateChange) {
        if (V()) {
            if (iTokenStateChange != null) {
                try {
                    this.w.setTokenInvalidForSDK(this.L);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else {
                this.w.setTokenInvalidForSDK((IXmTokenInvalidForSDKCallBack) null);
            }
        }
    }

    public void M() {
        if (W()) {
            try {
                this.w.exitSoundAd();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void f(boolean z2) {
        if (W()) {
            try {
                this.w.setBreakpointResume(z2);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean N() {
        if (!W()) {
            return false;
        }
        try {
            if (this.V != 2) {
                if (this.V != 3) {
                    return this.w.isLoading();
                }
            }
            return false;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public Map<String, String> O() {
        if (!W()) {
            return null;
        }
        try {
            return this.w.getParam();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void b(List<Track> list) {
        if (W()) {
            try {
                this.w.addPlayList(list);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void c(List<Track> list) {
        if (W()) {
            try {
                this.w.insertPlayListHead(list);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void a(String str) {
        if (W()) {
        }
    }

    public void P() {
        if (W()) {
            try {
                this.w.resetPlayer();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void g(boolean z2) {
        this.F = z2;
        if (V()) {
            try {
                this.w.setCheckAdContent(z2);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public long Q() {
        if (!W()) {
            return 0;
        }
        try {
            return this.w.getCurrentTrackPlayedDuration();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return 0;
        } catch (Exception e3) {
            e3.printStackTrace();
            return 0;
        }
    }

    public void g(int i2) {
        this.V = i2;
    }

    public int R() {
        return this.V;
    }

    public float S() {
        if (!W()) {
            return 0.0f;
        }
        try {
            return this.w.getTempo();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return 0.0f;
        } catch (Exception e3) {
            e3.printStackTrace();
            return 0.0f;
        }
    }
}
