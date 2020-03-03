package com.ximalaya.ting.android.opensdk.player.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.Logger;

public class PlayerReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2195a = "com.ximalaya.ting.android.car";
    private static final String c = "PLAYERRECEIVER";
    private int b = -1;
    private long d;

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction().equals(XmNotificationCreater.k)) {
            XmPlayerManager.c();
            return;
        }
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if ((playerSrvice != null || !XmNotificationCreater.j.equals(intent.getAction())) && playerSrvice != null) {
            a(context, intent, XmPlayerService.getPlayerSrvice(), false);
        }
    }

    private void a(Context context, Intent intent, XmPlayerService xmPlayerService, boolean z) {
        if (xmPlayerService != null) {
            Logger.b(c, "handleAction " + intent.getAction());
            if (XmNotificationCreater.j.equals(intent.getAction()) || XmNotificationCreater.d.equals(intent.getAction())) {
                Logger.a((Object) "process Main is running");
                if (xmPlayerService != null) {
                    xmPlayerService.closeNotification();
                }
                xmPlayerService.closeApp();
            } else if (intent.getAction().equals(XmNotificationCreater.h) && xmPlayerService != null) {
                xmPlayerService.playPre();
            } else if (intent.getAction().equals(XmNotificationCreater.i) && xmPlayerService != null) {
                xmPlayerService.playNext();
            } else if (!intent.getAction().equals(XmNotificationCreater.g) || xmPlayerService == null) {
                if (xmPlayerService == null && context.getApplicationInfo().packageName.equalsIgnoreCase(f2195a)) {
                    XmPlayerManager.d();
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse("tingcar://open"));
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    if (intent2.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent2);
                    }
                } else if (xmPlayerService == null) {
                } else {
                    if (intent.getAction().equals(XmNotificationCreater.c)) {
                        xmPlayerService.playNext();
                    } else if (intent.getAction().equals(XmNotificationCreater.b)) {
                        xmPlayerService.playPre();
                    } else if (!intent.getAction().equals(XmNotificationCreater.f2185a)) {
                    } else {
                        if (xmPlayerService.getPlayListSize() == 0 && context.getApplicationInfo().packageName.equalsIgnoreCase(f2195a)) {
                            Intent intent3 = new Intent("android.intent.action.VIEW");
                            intent3.setData(Uri.parse("tingcar://open"));
                            intent3.addFlags(C.ENCODING_PCM_MU_LAW);
                            if (intent3.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(intent3);
                            }
                        } else if (xmPlayerService.isPlaying()) {
                            xmPlayerService.pausePlay();
                        } else {
                            xmPlayerService.startPlay(z);
                        }
                    }
                }
            } else if (xmPlayerService.isPlaying()) {
                xmPlayerService.pausePlay();
            } else {
                XmPlayerControl playControl = xmPlayerService.getPlayControl();
                XmPlayListControl playListControl = xmPlayerService.getPlayListControl();
                if (playControl == null || playListControl == null || playControl.m() != 4) {
                    xmPlayerService.startPlay(z);
                    return;
                }
                int l = playListControl.l();
                if (l == -1) {
                    l = this.b;
                }
                if (l != -1) {
                    xmPlayerService.play(l);
                }
            }
        }
    }
}
