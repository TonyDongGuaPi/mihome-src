package com.ximalaya.ting.android.opensdk.player.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.util.Timer;
import java.util.TimerTask;

public class XmPlayerAudioFocusControl {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f2204a;
    /* access modifiers changed from: private */
    public AudioManager b;
    private TelephonyManager c;
    private TelephonyManager d;
    private TelephonyManager e;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;
    private PhoneStateListener h = new PhoneStateListener() {
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            switch (i) {
                case 0:
                    XmPlayerAudioFocusControl.this.f();
                    return;
                case 1:
                    XmPlayerAudioFocusControl.this.g();
                    return;
                case 2:
                    XmPlayerAudioFocusControl.this.g();
                    return;
                default:
                    return;
            }
        }
    };
    private BroadcastReceiver i = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals(PlayerConstants.r)) {
                if (action.equals("android.intent.action.HEADSET_PLUG") || "android.media.AUDIO_BECOMING_NOISY".equals(action)) {
                    if (intent.getIntExtra("state", 0) != 1) {
                        if (XmPlayerAudioFocusControl.this.g) {
                            new Timer().schedule(new TimerTask() {
                                public void run() {
                                    boolean unused = XmPlayerAudioFocusControl.this.g = false;
                                }
                            }, 3000);
                            return;
                        }
                        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
                        if (playerSrvice != null && playerSrvice.isPlaying()) {
                            playerSrvice.pausePlay();
                        }
                    }
                } else if (!action.equals("android.net.conn.CONNECTIVITY_CHANGE") && !action.equals(PlayerConstants.s) && !action.equals(PlayerConstants.t)) {
                    action.equals(PlayerConstants.u);
                }
            }
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                XmPlayerAudioFocusControl.this.g();
                return;
            }
            switch (((TelephonyManager) context.getSystemService("phone")).getCallState()) {
                case 0:
                    XmPlayerAudioFocusControl.this.f();
                    return;
                case 1:
                    XmPlayerAudioFocusControl.this.g();
                    return;
                case 2:
                    XmPlayerAudioFocusControl.this.g();
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public AudioManager.OnAudioFocusChangeListener l = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            Logger.a((Object) "XmPlayerAudioFocusControl : onAudioFocusChange = " + i);
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (i == -1) {
                if (XmPlayerAudioFocusControl.this.g) {
                    boolean unused = XmPlayerAudioFocusControl.this.g = false;
                    return;
                }
                if (playerSrvice != null) {
                    playerSrvice.setLossAudioFocus(true);
                    playerSrvice.pausePlay();
                }
                if (XmPlayerAudioFocusControl.this.b != null) {
                    XmPlayerAudioFocusControl.this.b.abandonAudioFocus(XmPlayerAudioFocusControl.this.l);
                }
            } else if (i == -2) {
                if (playerSrvice == null) {
                    return;
                }
                if (playerSrvice.isPlaying()) {
                    playerSrvice.pausePlay();
                    boolean unused2 = XmPlayerAudioFocusControl.this.k = true;
                } else if (playerSrvice.getPlayControl() != null && playerSrvice.getPlayControl().m() == 9) {
                    playerSrvice.setLossAudioFocus(true);
                    boolean unused3 = XmPlayerAudioFocusControl.this.k = true;
                }
            } else if (i != 2) {
                if (i == 1) {
                    if (playerSrvice != null) {
                        playerSrvice.setLossAudioFocus(false);
                        if (XmPlayerAudioFocusControl.this.k) {
                            playerSrvice.startPlay();
                            boolean unused4 = XmPlayerAudioFocusControl.this.k = false;
                        }
                        playerSrvice.setVolume(1.0f, 1.0f);
                    }
                } else if (i == -3) {
                    if (playerSrvice != null) {
                        playerSrvice.setVolume(XmPlayerConfig.a(XmPlayerAudioFocusControl.this.f2204a).i(), XmPlayerConfig.a(XmPlayerAudioFocusControl.this.f2204a).i());
                    }
                } else if (i == 3 && playerSrvice != null) {
                    playerSrvice.setVolume(XmPlayerConfig.a(XmPlayerAudioFocusControl.this.f2204a).i(), XmPlayerConfig.a(XmPlayerAudioFocusControl.this.f2204a).i());
                }
            }
        }
    };

    public XmPlayerAudioFocusControl(Context context) {
        this.f2204a = context.getApplicationContext();
        d();
    }

    public AudioManager a() {
        return this.b;
    }

    private void d() {
        if (this.f2204a != null) {
            this.b = (AudioManager) this.f2204a.getSystemService("audio");
            if (XmPlayerConfig.a(this.f2204a).h()) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.f2204a.registerReceiver(this.i, intentFilter);
            }
            if (XmPlayerConfig.a(this.f2204a).g()) {
                e();
                this.f2204a.registerReceiver(this.j, new IntentFilter());
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0028 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
            r3 = this;
            android.content.Context r0 = r3.f2204a
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            r3.c = r0
            android.telephony.TelephonyManager r0 = r3.c
            android.telephony.PhoneStateListener r1 = r3.h
            r2 = 32
            r0.listen(r1, r2)
            android.content.Context r0 = r3.f2204a     // Catch:{ Exception -> 0x0028 }
            java.lang.String r1 = "phone1"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch:{ Exception -> 0x0028 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x0028 }
            r3.d = r0     // Catch:{ Exception -> 0x0028 }
            android.telephony.TelephonyManager r0 = r3.d     // Catch:{ Exception -> 0x0028 }
            android.telephony.PhoneStateListener r1 = r3.h     // Catch:{ Exception -> 0x0028 }
            r0.listen(r1, r2)     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            android.content.Context r0 = r3.f2204a     // Catch:{ Exception -> 0x003b }
            java.lang.String r1 = "phone2"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch:{ Exception -> 0x003b }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x003b }
            r3.e = r0     // Catch:{ Exception -> 0x003b }
            android.telephony.TelephonyManager r0 = r3.e     // Catch:{ Exception -> 0x003b }
            android.telephony.PhoneStateListener r1 = r3.h     // Catch:{ Exception -> 0x003b }
            r0.listen(r1, r2)     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerAudioFocusControl.e():void");
    }

    /* access modifiers changed from: private */
    public void f() {
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice != null && this.f) {
            playerSrvice.startPlay();
        }
        this.f = false;
    }

    /* access modifiers changed from: private */
    public void g() {
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice != null && playerSrvice.isPlaying()) {
            this.f = true;
            playerSrvice.pausePlay();
        }
    }

    public void b() {
        if (XmPlayerConfig.a(this.f2204a).f()) {
            try {
                this.b.requestAudioFocus(this.l, 3, 1);
                XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
                if (playerSrvice != null) {
                    playerSrvice.setLossAudioFocus(false);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void c() {
        if (XmPlayerConfig.a(this.f2204a).f()) {
            this.b.abandonAudioFocus(this.l);
        }
    }

    public void a(boolean z) {
        this.k = !z;
    }

    public void b(boolean z) {
        this.g = z;
    }
}
