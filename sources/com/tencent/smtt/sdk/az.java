package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import com.brentvatne.react.ReactVideoViewManager;
import com.taobao.weex.common.Constants;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsMediaPlayer;

class az {

    /* renamed from: a  reason: collision with root package name */
    private DexLoader f9153a = null;
    private Object b = null;

    public az(DexLoader dexLoader, Context context) {
        this.f9153a = dexLoader;
        this.b = this.f9153a.newInstance("com.tencent.tbs.player.TbsMediaPlayerProxy", new Class[]{Context.class}, context);
    }

    public void a(float f) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setVolume", new Class[]{Float.TYPE}, Float.valueOf(f));
    }

    public void a(int i) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "subtitle", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    public void a(long j) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", ReactVideoViewManager.PROP_SEEK, new Class[]{Long.TYPE}, Long.valueOf(j));
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setSurfaceTexture", new Class[]{SurfaceTexture.class}, surfaceTexture);
    }

    public void a(TbsMediaPlayer.TbsMediaPlayerListener tbsMediaPlayerListener) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setPlayerListener", new Class[]{Object.class}, tbsMediaPlayerListener);
    }

    public void a(String str, Bundle bundle) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "startPlay", new Class[]{String.class, Bundle.class}, str, bundle);
    }

    public boolean a() {
        return this.b != null;
    }

    public float b() {
        Float f = (Float) this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "getVolume", new Class[0], new Object[0]);
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public void b(int i) {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "audio", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    public void c() {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "pause", new Class[0], new Object[0]);
    }

    public void d() {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", Constants.Value.PLAY, new Class[0], new Object[0]);
    }

    public void e() {
        this.f9153a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "close", new Class[0], new Object[0]);
    }
}
