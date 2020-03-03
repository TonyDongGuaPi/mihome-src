package com.ximalaya.ting.android.opensdk.player.appwidget;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;

public abstract class BaseAppWidgetProvider extends AppWidgetProvider {
    public abstract void onInitUI(Context context, Track track);

    public abstract void onPausePlay(Context context);

    public abstract void onStartPlay(Context context);

    public void onReceive(Context context, Intent intent) {
        PlayableModel playableModel;
        Track track;
        Track track2;
        super.onReceive(context, intent);
        if (intent != null) {
            if (intent.getAction().equals(ConstantsOpenSdk.f)) {
                onPausePlay(context);
            } else if (intent.getAction().equals(ConstantsOpenSdk.e)) {
                onStartPlay(context);
            } else if (intent.getAction().equals(ConstantsOpenSdk.g)) {
                onPausePlay(context);
            } else if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                String c = SharedPreferencesUtil.a(context).c("lasttrack");
                if (c != null) {
                    try {
                        track2 = (Track) new Gson().fromJson(c, Track.class);
                    } catch (Exception unused) {
                        track2 = null;
                    }
                    if (track2 != null) {
                        onInitUI(context, track2);
                    }
                }
            } else if (intent.getAction().equals(ConstantsOpenSdk.h)) {
                Bundle extras = intent.getExtras();
                if (extras != null && (track = (Track) extras.get("track")) != null) {
                    onInitUI(context, track);
                }
            } else if (intent.getAction().equals(XmNotificationCreater.h)) {
                PlayableModel playableModel2 = XmPlayerService.getPlayerSrvice().getPlayableModel();
                if (playableModel2 != null) {
                    onInitUI(context, (Track) playableModel2);
                }
            } else if (intent.getAction().equals(XmNotificationCreater.i) && (playableModel = XmPlayerService.getPlayerSrvice().getPlayableModel()) != null) {
                onInitUI(context, (Track) playableModel);
            }
        }
    }
}
