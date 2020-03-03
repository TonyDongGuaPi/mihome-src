package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;

public class TbsMediaFactory {

    /* renamed from: a  reason: collision with root package name */
    private Context f9099a = null;
    private bh b = null;
    private DexLoader c = null;

    public TbsMediaFactory(Context context) {
        this.f9099a = context.getApplicationContext();
        a();
    }

    private void a() {
        if (this.f9099a == null) {
            Log.e("TbsVideo", "TbsVideo needs context !!");
            return;
        }
        if (this.b == null) {
            o.a(true).a(this.f9099a, false, false);
            this.b = o.a(true).a();
            if (this.b != null) {
                this.c = this.b.b();
            }
        }
        if (this.b == null || this.c == null) {
            throw new RuntimeException("tbs core dex(s) load failure !!!");
        }
    }

    public TbsMediaPlayer createPlayer() {
        if (this.b != null && this.c != null) {
            return new TbsMediaPlayer(new az(this.c, this.f9099a));
        }
        throw new RuntimeException("tbs core dex(s) did not loaded !!!");
    }
}
