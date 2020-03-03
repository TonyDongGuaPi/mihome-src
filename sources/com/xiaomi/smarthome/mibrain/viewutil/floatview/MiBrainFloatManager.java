package com.xiaomi.smarthome.mibrain.viewutil.floatview;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.mibrain.activity.MiBrainActivityNew;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.voiceassistant.mijava.NLProcessor;
import com.yanzhenjie.permission.Action;
import java.util.List;

public class MiBrainFloatManager {

    /* renamed from: a  reason: collision with root package name */
    private WindowManager.LayoutParams f10721a = new WindowManager.LayoutParams();
    /* access modifiers changed from: private */
    public MiBrainFloatView b = null;
    private boolean c = false;
    /* access modifiers changed from: private */
    public Activity d;
    /* access modifiers changed from: private */
    public MiBrainPreferenceManager e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public boolean g;
    private View h;
    private PopupWindow i;
    private int j = -1;
    private final String k = "MiBrainFloatManager";
    /* access modifiers changed from: private */
    public int l = 20;
    private WindowManager m;
    private Vibrator n;
    /* access modifiers changed from: private */
    public Handler o = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int i = message.what;
        }
    };
    private NoDuplicateClickListener p = new NoDuplicateClickListener() {
        public void a(View view) {
            boolean unused = MiBrainFloatManager.this.g = MiBrainFloatManager.this.b.isHide();
            boolean unused2 = MiBrainFloatManager.this.f = MiBrainFloatManager.this.e.d();
            if (MiBrainFloatManager.this.g) {
                int unused3 = MiBrainFloatManager.this.l = 150;
            } else {
                int unused4 = MiBrainFloatManager.this.l = 20;
            }
            MiBrainFloatManager.this.o.postDelayed(new Runnable() {
                public void run() {
                    final Activity e = MiBrainFloatManager.this.d;
                    if (e != null) {
                        Miio.b("MiBrainFloatManager", "onNoDulicateClick startActivity" + MiBrainFloatManager.this.g);
                        MiBrainFloatManager.this.b.startTimerCount();
                        MiBrainFloatManager.this.b.setHide(false);
                        PermissionHelper.b(e, true, new Action() {
                            public void onAction(List<String> list) {
                                NLProcessor.b = "";
                                Intent intent = new Intent(e, MiBrainActivityNew.class);
                                intent.putExtra("from", "mainpage");
                                e.startActivity(intent);
                                StatHelper.an();
                            }
                        });
                    }
                }
            }, (long) MiBrainFloatManager.this.l);
        }
    };

    public MiBrainFloatManager(Activity activity) {
        this.d = activity;
        this.e = new MiBrainPreferenceManager(activity);
        this.m = (WindowManager) activity.getSystemService("window");
        this.n = (Vibrator) activity.getSystemService("vibrator");
    }

    public Activity a() {
        return this.d;
    }

    public void b() {
        Miio.b("MiBrainFloatManager", "createView----isDisplay--" + this.c);
        if (!this.c && this.d != null) {
            this.b = new MiBrainFloatView(this.d, this.f10721a, this.m);
            this.b.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    MiBrainFloatManager.this.f();
                    return true;
                }
            });
            this.b.setNoDuplicateClickListener(this.p);
            this.m.addView(this.b, this.f10721a);
            this.c = true;
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        Miio.b("MiBrainFloatManager", "长按");
        if (this.b != null && this.d != null) {
            if (this.n == null) {
                this.n = (Vibrator) this.d.getSystemService("vibrator");
            }
            this.n.vibrate(200);
            this.b.setImageResource(this.b.getDefaultResource());
            this.b.setCanMove(true);
            this.b.startTimerCount();
        }
    }

    public void c() {
        Miio.b("MiBrainFloatManager", "removeView----isDisplay--" + this.c);
        if (this.c) {
            this.b.cancelTimerCount();
            this.m.removeViewImmediate(this.b);
            this.c = false;
        }
    }

    public void d() {
        Miio.b("MiBrainFloatManager", "destroyWindowManager----isDisplay--" + this.c);
        if (this.c) {
            if (this.b != null) {
                this.b.cancelTimerCount();
            }
            if (this.m != null) {
                this.m.removeViewImmediate(this.b);
            }
            this.c = false;
            this.d = null;
        }
    }

    private void g() {
        if (this.i != null) {
            this.i.dismiss();
            this.i = null;
        }
    }

    public void e() {
        if (this.b != null) {
            this.b.cancelTimerCount();
            this.b.cancelSecondTimerCount();
        }
    }
}
