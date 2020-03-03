package com.xiaomi.smarthome.lite;

import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.sdk.R;

public class LiteSoundManager {
    private static LiteSoundManager d = null;
    private static final int g = 1;
    private static final int h = 2;

    /* renamed from: a  reason: collision with root package name */
    public SoundPool f19364a;
    public int b;
    public int c;
    /* access modifiers changed from: private */
    public MessageHandlerThread e = new MessageHandlerThread("LiteSoundManager");
    /* access modifiers changed from: private */
    public volatile Handler f;
    /* access modifiers changed from: private */
    public volatile boolean i = false;

    public static LiteSoundManager a() {
        if (d == null) {
            synchronized (LiteSoundManager.class) {
                if (d == null) {
                    d = new LiteSoundManager();
                }
            }
        }
        return d;
    }

    private LiteSoundManager() {
        this.e.start();
        this.f = new Handler(this.e.getLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (!LiteSoundManager.this.i) {
                            try {
                                LiteSoundManager.this.f19364a = new SoundPool(1, 1, 0);
                                LiteSoundManager.this.b = LiteSoundManager.this.f19364a.load(CommonApplication.getAppContext(), R.raw.geek_click_down, 1);
                                LiteSoundManager.this.c = LiteSoundManager.this.f19364a.load(CommonApplication.getAppContext(), R.raw.geek_click_up, 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (LiteSoundManager.this.i) {
                                LiteSoundManager.this.f.sendEmptyMessage(2);
                                return;
                            }
                            return;
                        }
                        return;
                    case 2:
                        if (LiteSoundManager.this.f19364a != null) {
                            try {
                                LiteSoundManager.this.f19364a.unload(LiteSoundManager.this.b);
                                LiteSoundManager.this.f19364a.unload(LiteSoundManager.this.c);
                                LiteSoundManager.this.f19364a.release();
                                LiteSoundManager.this.f19364a = null;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        LiteSoundManager.this.e.getLooper().quit();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void b() {
        if (!this.i && this.f19364a == null) {
            this.f.sendEmptyMessage(1);
        }
    }

    public void c() {
        d = null;
        this.i = true;
        if (this.f != null) {
            this.f.sendEmptyMessage(2);
        }
    }

    public void d() {
        if (!this.i && this.f19364a != null && SharePrefsManager.b(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.l, true)) {
            try {
                this.f19364a.play(this.b, 1.0f, 1.0f, 0, 0, 1.0f);
            } catch (Exception unused) {
            }
        }
    }

    public void e() {
        if (!this.i && this.f19364a != null && SharePrefsManager.b(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.l, true)) {
            try {
                this.f19364a.play(this.c, 1.0f, 1.0f, 0, 0, 1.0f);
            } catch (Exception unused) {
            }
        }
    }
}
