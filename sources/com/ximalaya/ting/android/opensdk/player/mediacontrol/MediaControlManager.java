package com.ximalaya.ting.android.opensdk.player.mediacontrol;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.receive.WireControlReceiver;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.FileUtilBase;

public class MediaControlManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2190a = "reflect_player_cover_default";
    /* access modifiers changed from: private */
    public MediaSession b;
    /* access modifiers changed from: private */
    public RemoteControlClient c;
    private AudioManager d;
    /* access modifiers changed from: private */
    public Context e;
    private ComponentName f;
    /* access modifiers changed from: private */
    public WireControlReceiver g;
    private boolean h = false;

    public MediaControlManager(Context context) {
        this.e = context;
        if (XmPlayerConfig.a(context).k()) {
            this.g = new WireControlReceiver();
            this.d = (AudioManager) context.getSystemService("audio");
        }
    }

    public void a() {
        if (XmPlayerConfig.a(this.e).k() && Build.VERSION.SDK_INT >= 21) {
            try {
                this.b = new MediaSession(this.e, "MusicService");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (this.b != null) {
                this.b.setCallback(new MediaSession.Callback() {
                    public boolean onMediaButtonEvent(Intent intent) {
                        MediaControlManager.this.g.onReceive(MediaControlManager.this.e, intent);
                        return super.onMediaButtonEvent(intent);
                    }
                });
                this.b.setFlags(3);
            }
        }
    }

    public void b() {
        if (XmPlayerConfig.a(this.e).k()) {
            if (Build.VERSION.SDK_INT >= 21 && this.b != null) {
                this.b.setActive(false);
                this.b.release();
            }
            if (Build.VERSION.SDK_INT >= 14) {
                AudioManager audioManager = (AudioManager) this.e.getSystemService("audio");
                if (audioManager != null) {
                    if (this.f != null) {
                        audioManager.unregisterMediaButtonEventReceiver(this.f);
                    }
                    if (this.c != null) {
                        audioManager.unregisterRemoteControlClient(this.c);
                    }
                }
                if (this.g != null && this.h) {
                    try {
                        this.e.unregisterReceiver(this.g);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    private void a(MediaSession mediaSession) {
        if (XmPlayerConfig.a(this.e).k() && mediaSession != null && Build.VERSION.SDK_INT >= 21) {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(new ComponentName(this.e, WireControlReceiver.class));
            mediaSession.setMediaButtonReceiver(PendingIntent.getBroadcast(this.e.getApplicationContext(), 0, intent, 0));
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setUsage(1).setContentType(2);
            mediaSession.setPlaybackToLocal(builder.build());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004b, code lost:
        r0 = (com.ximalaya.ting.android.opensdk.model.track.Track) r0.getPlayListControl().n();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r13 = this;
            android.content.Context r0 = r13.e
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig.a((android.content.Context) r0)
            boolean r0 = r0.k()
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 21
            r3 = 0
            if (r0 < r2) goto L_0x007a
            android.media.session.PlaybackState$Builder r0 = new android.media.session.PlaybackState$Builder
            r0.<init>()
            r4 = 1591(0x637, double:7.86E-321)
            android.media.session.PlaybackState$Builder r6 = r0.setActions(r4)
            r7 = 3
            r8 = 0
            r10 = 1065353216(0x3f800000, float:1.0)
            long r11 = android.os.SystemClock.elapsedRealtime()
            android.media.session.PlaybackState$Builder r0 = r6.setState(r7, r8, r10, r11)
            android.media.session.PlaybackState r0 = r0.build()
            android.media.session.MediaSession r4 = r13.b
            if (r4 == 0) goto L_0x0044
            android.media.session.MediaSession r4 = r13.b
            r13.a((android.media.session.MediaSession) r4)
            android.media.session.MediaSession r4 = r13.b
            r4.setActive(r1)
            android.media.session.MediaSession r4 = r13.b
            r4.setPlaybackState(r0)
        L_0x0044:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            if (r0 != 0) goto L_0x004b
            return
        L_0x004b:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r0 = r0.getPlayListControl()
            com.ximalaya.ting.android.opensdk.model.PlayableModel r0 = r0.n()
            com.ximalaya.ting.android.opensdk.model.track.Track r0 = (com.ximalaya.ting.android.opensdk.model.track.Track) r0
            if (r0 != 0) goto L_0x0058
            return
        L_0x0058:
            android.content.Context r4 = r13.e
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            int r4 = r4.widthPixels
            android.content.Context r5 = r13.e
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            int r5 = r5.heightPixels
            android.content.Context r4 = r13.e
            com.ximalaya.ting.android.opensdk.player.mediacontrol.MediaControlManager$2 r5 = new com.ximalaya.ting.android.opensdk.player.mediacontrol.MediaControlManager$2
            r5.<init>(r0)
            com.ximalaya.ting.android.opensdk.util.FileUtilBase.b(r4, r0, r3, r3, r5)
        L_0x007a:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 14
            if (r0 < r4) goto L_0x00f9
            android.content.Context r0 = r13.e
            java.lang.String r5 = "audio"
            java.lang.Object r0 = r0.getSystemService(r5)
            android.media.AudioManager r0 = (android.media.AudioManager) r0
            r13.d = r0
            android.content.ComponentName r0 = new android.content.ComponentName
            android.content.Context r5 = r13.e
            java.lang.String r5 = r5.getPackageName()
            java.lang.Class<com.ximalaya.ting.android.opensdk.player.receive.WireControlReceiver> r6 = com.ximalaya.ting.android.opensdk.player.receive.WireControlReceiver.class
            java.lang.String r6 = r6.getName()
            r0.<init>(r5, r6)
            r13.f = r0
            android.media.AudioManager r0 = r13.d
            android.content.ComponentName r5 = r13.f
            r0.registerMediaButtonEventReceiver(r5)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r5 = "android.intent.action.MEDIA_BUTTON"
            r0.<init>(r5)
            android.content.ComponentName r5 = r13.f
            r0.setComponent(r5)
            android.content.Context r5 = r13.e
            r6 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r0 = android.app.PendingIntent.getBroadcast(r5, r3, r0, r6)
            android.media.RemoteControlClient r3 = new android.media.RemoteControlClient
            r3.<init>(r0)
            r13.c = r3
            android.media.AudioManager r0 = r13.d
            android.media.RemoteControlClient r3 = r13.c
            r0.registerRemoteControlClient(r3)
            r0 = 669(0x29d, float:9.37E-43)
            android.media.RemoteControlClient r3 = r13.c
            r3.setTransportControlFlags(r0)
            android.content.IntentFilter r0 = new android.content.IntentFilter
            java.lang.String r3 = "android.intent.action.MEDIA_BUTTON"
            r0.<init>(r3)
            r3 = 10000(0x2710, float:1.4013E-41)
            r0.setPriority(r3)
            android.content.Context r3 = r13.e
            com.ximalaya.ting.android.opensdk.player.receive.WireControlReceiver r5 = r13.g
            r3.registerReceiver(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r4) goto L_0x00f7
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r2) goto L_0x00f7
            r13.f()
            android.media.RemoteControlClient r0 = r13.c
            if (r0 == 0) goto L_0x00f7
            android.media.RemoteControlClient r0 = r13.c
            r2 = 3
            r0.setPlaybackState(r2)
        L_0x00f7:
            r13.h = r1
        L_0x00f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.mediacontrol.MediaControlManager.c():void");
    }

    public void d() {
        if (XmPlayerConfig.a(this.e).k()) {
            if (Build.VERSION.SDK_INT >= 21 && this.b != null) {
                this.b.setPlaybackState(new PlaybackState.Builder().setState(2, 0, 1.0f).build());
            }
            if (Build.VERSION.SDK_INT >= 14 && this.c != null) {
                this.c.setPlaybackState(2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void e() {
        if (XmPlayerConfig.a(this.e).k()) {
            if (Build.VERSION.SDK_INT >= 21 && this.b != null) {
                this.b.setPlaybackState(new PlaybackState.Builder().setState(1, 0, 1.0f).build());
            }
            if (Build.VERSION.SDK_INT >= 14 && this.c != null) {
                this.c.setPlaybackState(1);
            }
        }
    }

    private void f() {
        XmPlayerService playerSrvice;
        Track track;
        if (Build.VERSION.SDK_INT >= 14 && this.c != null && (playerSrvice = XmPlayerService.getPlayerSrvice()) != null && playerSrvice.getPlayListControl() != null && (track = (Track) playerSrvice.getPlayListControl().n()) != null) {
            String N = track.N();
            String e2 = track.ao() != null ? track.ao().e() : "";
            String b2 = track.I() != null ? track.I().b() : "";
            long W = (long) track.W();
            a(N, e2, b2, W, (Bitmap) null);
            int i = this.e.getResources().getDisplayMetrics().widthPixels;
            int i2 = this.e.getResources().getDisplayMetrics().heightPixels;
            final String str = N;
            final String str2 = e2;
            final String str3 = b2;
            final long j = W;
            FileUtilBase.b(this.e, track, 0, 0, new FileUtilBase.IBitmapDownOkCallBack() {
                public void a(Bitmap bitmap) {
                    if (MediaControlManager.this.c != null && bitmap != null) {
                        MediaControlManager.this.a(str, str2, str3, j, bitmap);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, long j, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 14) {
            RemoteControlClient.MetadataEditor editMetadata = this.c.editMetadata(true);
            editMetadata.putString(7, str);
            editMetadata.putString(1, str2);
            editMetadata.putString(2, str3);
            editMetadata.putLong(9, j);
            if (bitmap == null) {
                int identifier = this.e.getResources().getIdentifier(f2190a, "drawable", this.e.getPackageName());
                if (identifier > 0 || !ConstantsOpenSdk.b || !BaseUtil.d()) {
                    bitmap = BitmapFactory.decodeResource(this.e.getResources(), identifier);
                } else {
                    throw new RuntimeException("请内置名为player_cover_default资源图片，作为锁屏封面默认图");
                }
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                try {
                    editMetadata.putBitmap(100, bitmap);
                } catch (Exception unused) {
                }
            }
            editMetadata.apply();
        }
    }
}
