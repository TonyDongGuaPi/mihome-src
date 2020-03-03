package com.ximalaya.ting.android.opensdk.player.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.Logger;

public class WireControlReceiver extends BroadcastReceiver {
    public static final int DELAY_MILLIS = 600;

    /* renamed from: a  reason: collision with root package name */
    private static final String f2196a = "WireControlReceiver";
    private static int b;
    private static Handler c = new Handler();
    /* access modifiers changed from: private */
    public static int d = 0;
    static Runnable doubleNextRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.e = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice != null) {
                playerSrvice.seekTo(playerSrvice.getPlayCurrPosition() + 15000);
            }
        }
    };
    static Runnable doublePreRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.f = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice != null) {
                playerSrvice.seekTo(playerSrvice.getPlayCurrPosition() - 15000);
            }
        }
    };
    /* access modifiers changed from: private */
    public static int e = 0;
    /* access modifiers changed from: private */
    public static int f = 0;
    static Runnable playNextRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.e = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice != null) {
                playerSrvice.playNext();
            }
        }
    };
    static Runnable playOrPauseDoubleClickRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.d = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice != null) {
                playerSrvice.playNext();
            }
        }
    };
    static Runnable playOrPauseRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.d = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice == null) {
                return;
            }
            if (playerSrvice.isPlaying()) {
                playerSrvice.pausePlay();
            } else {
                playerSrvice.startPlay();
            }
        }
    };
    static Runnable playPreRunnable = new Runnable() {
        public void run() {
            int unused = WireControlReceiver.f = 0;
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice != null) {
                playerSrvice.playPre();
            }
        }
    };

    public void onReceive(Context context, Intent intent) {
        KeyEvent keyEvent;
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice != null && "android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
            if (keyEvent.getRepeatCount() > 0) {
                b = keyEvent.getRepeatCount();
            }
            if (!(keyEvent.getAction() == 1)) {
                Logger.a("WireControlReceiver  0  " + keyEvent + "   " + b);
                if (keyEvent.getKeyCode() == 87) {
                    if (b > 0) {
                        a(true, true);
                    }
                } else if (keyEvent.getKeyCode() == 88 && b > 0) {
                    a(false, true);
                }
            } else {
                int keyCode = keyEvent.getKeyCode();
                if (keyCode != 79) {
                    switch (keyCode) {
                        case 85:
                            break;
                        case 86:
                            Logger.a("WireControlReceiver  1");
                            playerSrvice.pausePlay();
                            break;
                        case 87:
                            Logger.a("WireControlReceiver  4  " + b);
                            if (b <= 0) {
                                e++;
                                Logger.a("WireControlReceiver  5  " + e);
                                if (e < 2) {
                                    c.removeCallbacks(playNextRunnable);
                                    c.postDelayed(playNextRunnable, 600);
                                    break;
                                } else {
                                    c.removeCallbacks(playNextRunnable);
                                    c.removeCallbacks(doubleNextRunnable);
                                    c.postDelayed(doubleNextRunnable, 600);
                                    break;
                                }
                            } else {
                                a(true, false);
                                break;
                            }
                        case 88:
                            Logger.a("WireControlReceiver  6  " + b);
                            if (b <= 0) {
                                f++;
                                Logger.a("WireControlReceiver  7  " + f);
                                if (f < 2) {
                                    c.removeCallbacks(playPreRunnable);
                                    c.postDelayed(playPreRunnable, 600);
                                    break;
                                } else {
                                    c.removeCallbacks(playPreRunnable);
                                    c.removeCallbacks(doublePreRunnable);
                                    c.postDelayed(doublePreRunnable, 600);
                                    break;
                                }
                            } else {
                                a(false, false);
                                break;
                            }
                        default:
                            switch (keyCode) {
                                case 126:
                                    Logger.a("WireControlReceiver  2");
                                    playerSrvice.startPlay();
                                    break;
                                case 127:
                                    break;
                            }
                            Logger.a("WireControlReceiver  1");
                            playerSrvice.pausePlay();
                            break;
                    }
                }
                d++;
                Logger.a("WireControlReceiver  3  == " + d);
                c.removeCallbacks(playOrPauseRunnable);
                c.removeCallbacks(playOrPauseDoubleClickRunnable);
                if (d == 1) {
                    c.postDelayed(playOrPauseRunnable, 600);
                } else if (d == 2) {
                    c.postDelayed(playOrPauseDoubleClickRunnable, 400);
                } else {
                    d = 0;
                    playerSrvice.playPre();
                }
                try {
                    if (isOrderedBroadcast()) {
                        abortBroadcast();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void a(boolean z, boolean z2) {
        int duration;
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice != null && (duration = playerSrvice.getDuration()) > 0) {
            int playCurrPosition = ((int) (((((float) (b * duration)) * 1.0f) / 100.0f) * ((float) (z ? 1 : -1)))) + playerSrvice.getPlayCurrPosition();
            if (playCurrPosition > duration) {
                playCurrPosition = duration;
            }
            if (z2) {
                playerSrvice.notifProgress(playCurrPosition, duration);
                return;
            }
            playerSrvice.seekTo(playCurrPosition);
            b = 0;
        }
    }
}
