package com.ximalaya.ting.android.player;

import android.content.Context;
import android.text.TextUtils;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.FileDescriptor;
import java.util.Map;

public class XMediaPlayerWrapper implements XMediaplayerImpl {

    /* renamed from: a  reason: collision with root package name */
    public XMediaplayerImpl f2294a;
    private boolean b = false;
    /* access modifiers changed from: private */
    public Context c;
    /* access modifiers changed from: private */
    public boolean d;
    private String e;
    private XMediaPlayer.OnBufferingUpdateListener f;
    private XMediaPlayer.OnCompletionListener g;
    /* access modifiers changed from: private */
    public XMediaPlayer.OnErrorListener h;
    private XMediaPlayer.OnInfoListener i;
    private XMediaPlayer.OnPreparedListener j;
    private XMediaPlayer.OnSeekCompleteListener k;
    private XMediaPlayer.OnPositionChangeListener l;

    public XMediaPlayerWrapper(Context context, boolean z) {
        if (context != null) {
            this.c = context.getApplicationContext();
            XMediaPlayerConstants.a(this.c);
            this.d = z;
            a(this.c, z);
            return;
        }
        throw new RuntimeException("context cannot be null!!!!!!");
    }

    private void a(Context context, boolean z) {
        XMediaPlayerConstants.a(context);
        this.b = false;
        String e2 = PlayerUtil.e();
        String property = System.getProperty("os.arch");
        if (TextUtils.isEmpty(e2) || TextUtils.isEmpty(property)) {
            String str = XMediaplayerJNI.Tag;
            Logger.a(str, (Object) "cpuinfo null:" + e2 + "cpuArch:" + property);
        } else {
            if (e2.contains("Marvell") && property.contains("armv5tel")) {
                this.b = true;
            }
            String str2 = XMediaplayerJNI.Tag;
            Logger.a(str2, (Object) "cpuinfo:" + e2 + "cpuArch:" + property);
        }
        this.f2294a = b(context, z);
    }

    public XMediaPlayerWrapper(Context context, boolean z, boolean z2) {
        if (context != null) {
            this.c = context.getApplicationContext();
            XMediaPlayerConstants.a(this.c);
            if (z2) {
                this.b = true;
                this.f2294a = b(this.c, z);
                return;
            }
            a(this.c, z);
            return;
        }
        throw new RuntimeException("context cannot be null!!!!!!");
    }

    public boolean c() {
        return this.b;
    }

    private XMediaplayerImpl b(Context context, boolean z) {
        if (this.b) {
            this.f2294a = new SMediaPlayer();
        } else {
            Logger.a((Object) "jniHandler newXMediaplayer XMediaPlayer");
            this.f2294a = new XMediaPlayer(context, z);
        }
        return this.f2294a;
    }

    public int a() {
        return this.f2294a.a();
    }

    public XMediaplayerJNI.AudioType getAudioType() {
        return this.f2294a.getAudioType();
    }

    public void a(int i2) {
        this.f2294a.a(i2);
    }

    public void a(FileDescriptor fileDescriptor, String str) {
        this.e = str;
        this.f2294a.a(fileDescriptor, str);
    }

    public void a(long j2) {
        this.f2294a.a(j2);
    }

    public int getCurrentPosition() {
        return this.f2294a.getCurrentPosition();
    }

    public int getDuration() {
        return this.f2294a.getDuration();
    }

    public boolean isPlaying() {
        return this.f2294a.isPlaying();
    }

    public void pause() {
        this.f2294a.pause();
    }

    public void prepareAsync() {
        this.f2294a.prepareAsync();
    }

    public void release() {
        this.f2294a.release();
    }

    public void reset() {
        this.f2294a.reset();
    }

    public void seekTo(int i2) {
        this.f2294a.seekTo(i2);
    }

    public void setDataSource(String str) {
        this.e = str;
        this.f2294a.setDataSource(str);
    }

    public void a(XMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.f = onBufferingUpdateListener;
        this.f2294a.a(this.f);
    }

    public void a(XMediaPlayer.OnCompletionListener onCompletionListener) {
        this.g = onCompletionListener;
        this.f2294a.a(this.g);
    }

    public void a(XMediaPlayer.OnErrorListener onErrorListener) {
        this.h = onErrorListener;
        this.f2294a.a((XMediaPlayer.OnErrorListener) new XMediaPlayer.OnErrorListener() {
            public boolean a(XMediaplayerImpl xMediaplayerImpl, int i, int i2) {
                if (i2 == -1011) {
                    XMediaPlayerWrapper.this.c(XMediaPlayerWrapper.this.c, XMediaPlayerWrapper.this.d);
                    return true;
                } else if (XMediaPlayerWrapper.this.h != null) {
                    return XMediaPlayerWrapper.this.h.a(XMediaPlayerWrapper.this.f2294a, i, i2);
                } else {
                    return false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0099, code lost:
        if (r3 != null) goto L_0x008a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0094 A[SYNTHETIC, Splitter:B:15:0x0094] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Context r3, boolean r4) {
        /*
            r2 = this;
            r0 = 1
            r2.b = r0
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r1 = 0
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnBufferingUpdateListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnCompletionListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnErrorListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnInfoListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnPreparedListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnSeekCompleteListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r0 = r2.f2294a
            r0.a((com.ximalaya.ting.android.player.XMediaPlayer.OnPositionChangeListener) r1)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.b(r3, r4)
            r2.f2294a = r3
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnBufferingUpdateListener r4 = r2.f
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnBufferingUpdateListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnCompletionListener r4 = r2.g
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnCompletionListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnErrorListener r4 = r2.h
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnErrorListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnInfoListener r4 = r2.i
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnInfoListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnPreparedListener r4 = r2.j
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnPreparedListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnSeekCompleteListener r4 = r2.k
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnSeekCompleteListener) r4)
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            com.ximalaya.ting.android.player.XMediaPlayer$OnPositionChangeListener r4 = r2.l
            r3.a((com.ximalaya.ting.android.player.XMediaPlayer.OnPositionChangeListener) r4)
            java.lang.String r3 = r2.e
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00a1
            java.lang.String r3 = r2.e
            java.lang.String r4 = "http"
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L_0x0078
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            java.lang.String r4 = r2.e
            r3.setDataSource(r4)
            goto L_0x009c
        L_0x0078:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0098, all -> 0x0090 }
            java.lang.String r4 = r2.e     // Catch:{ Exception -> 0x0098, all -> 0x0090 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0098, all -> 0x0090 }
            com.ximalaya.ting.android.player.XMediaplayerImpl r4 = r2.f2294a     // Catch:{ Exception -> 0x0099, all -> 0x008e }
            java.io.FileDescriptor r0 = r3.getFD()     // Catch:{ Exception -> 0x0099, all -> 0x008e }
            java.lang.String r1 = r2.e     // Catch:{ Exception -> 0x0099, all -> 0x008e }
            r4.a((java.io.FileDescriptor) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0099, all -> 0x008e }
        L_0x008a:
            r3.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x009c
        L_0x008e:
            r4 = move-exception
            goto L_0x0092
        L_0x0090:
            r4 = move-exception
            r3 = r1
        L_0x0092:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0097:
            throw r4
        L_0x0098:
            r3 = r1
        L_0x0099:
            if (r3 == 0) goto L_0x009c
            goto L_0x008a
        L_0x009c:
            com.ximalaya.ting.android.player.XMediaplayerImpl r3 = r2.f2294a
            r3.prepareAsync()
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.XMediaPlayerWrapper.c(android.content.Context, boolean):void");
    }

    public void a(XMediaPlayer.OnInfoListener onInfoListener) {
        this.i = onInfoListener;
        this.f2294a.a(this.i);
    }

    public void a(XMediaPlayer.OnPreparedListener onPreparedListener) {
        this.j = onPreparedListener;
        this.f2294a.a(this.j);
    }

    public void a(XMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.k = onSeekCompleteListener;
        this.f2294a.a(this.k);
    }

    public void a(XMediaPlayer.OnPositionChangeListener onPositionChangeListener) {
        this.l = onPositionChangeListener;
        this.f2294a.a(this.l);
    }

    public void setVolume(float f2, float f3) {
        this.f2294a.setVolume(f2, f3);
    }

    public void a(Context context, int i2) {
        this.f2294a.a(context, i2);
    }

    public void start() {
        this.f2294a.start();
    }

    public void stop() {
        this.f2294a.stop();
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

    public void setTempo(float f2) {
        if (this.f2294a != null) {
            this.f2294a.setTempo(f2);
        }
    }

    public void setSoundTouchAllParams(float f2, float f3, float f4) {
        if (this.f2294a != null) {
            this.f2294a.setSoundTouchAllParams(f2, f3, f4);
        }
    }

    public void setPreBufferUrl(String str) {
        if (this.f2294a != null) {
            this.f2294a.setPreBufferUrl(str);
        }
    }

    public void setOnPlayDataOutputListener(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener) {
        if (this.f2294a != null) {
            this.f2294a.setOnPlayDataOutputListener(onPlayDataOutputListener);
        }
    }
}
