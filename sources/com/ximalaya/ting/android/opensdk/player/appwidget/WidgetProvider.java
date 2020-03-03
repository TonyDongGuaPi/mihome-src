package com.ximalaya.ting.android.opensdk.player.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import com.ximalaya.ting.android.opensdk.player.receive.PlayerReceiver;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.FileUtilBase;

public class WidgetProvider extends BaseAppWidgetProvider {
    public void onReceive(Context context, Intent intent) {
        Log.w("WidgetProvider", "onReceive action " + intent.getAction());
        super.onReceive(context, intent);
        if (intent != null && "android.intent.action.WALLPAPER_CHANGED".equals(intent.getAction())) {
            a(context);
        }
    }

    private void a(Context context) {
        try {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), getResourceId(context, "host_reflect_appwidget_layout", "layout"));
            AppWidgetManager instance = AppWidgetManager.getInstance(context);
            String str = XmNotificationCreater.f2185a;
            if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                str = XmNotificationCreater.g;
            }
            Intent intent = new Intent(context.getApplicationContext(), PlayerReceiver.class);
            intent.setAction(str);
            remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_playOrPause", "id"), PendingIntent.getBroadcast(context, 0, intent, 134217728));
            if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                Intent intent2 = new Intent(context.getApplicationContext(), PlayerReceiver.class);
                intent2.setAction(XmNotificationCreater.h);
                remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_pre", "id"), PendingIntent.getBroadcast(context, 0, intent2, 134217728));
                Intent intent3 = new Intent(context.getApplicationContext(), PlayerReceiver.class);
                intent3.setAction(XmNotificationCreater.i);
                remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_next", "id"), PendingIntent.getBroadcast(context, 0, intent3, 134217728));
            }
            Intent intent4 = new Intent("android.intent.action.VIEW");
            if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                intent4.setData(Uri.parse("itingwelcom://open"));
                remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_icon_small", "id"), PendingIntent.getActivity(context, 0, intent4, 0));
            } else {
                intent4.setData(Uri.parse("tingcar://open"));
                remoteViews.setOnClickPendingIntent(getResourceId(context, "ll_open_app", "id"), PendingIntent.getActivity(context, 0, intent4, 0));
            }
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            ComponentName componentName = new ComponentName(context, getClass());
            if (playerSrvice == null) {
                instance.updateAppWidget(componentName, remoteViews);
                return;
            }
            PlayableModel playableModel = playerSrvice.getPlayableModel();
            if (playableModel instanceof Track) {
                a(context, (Track) playableModel, remoteViews);
            }
            instance.updateAppWidget(componentName, remoteViews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPausePlay(Context context) {
        RemoteViews remoteViews = null;
        if (context != null) {
            try {
                remoteViews = new RemoteViews(context.getPackageName(), getResourceId(context, "host_reflect_appwidget_layout", "layout"));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (remoteViews != null) {
            remoteViews.setImageViewResource(getResourceId(context, "appwidget_playOrPause", "id"), getResourceId(context, "host_reflect_widget_play", "drawable"));
            a(context, remoteViews);
        }
    }

    public void onStartPlay(Context context) {
        b(context);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        try {
            if (iArr.length > 0) {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), getResourceId(context, "host_reflect_appwidget_layout", "layout"));
                String str = XmNotificationCreater.f2185a;
                if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                    str = XmNotificationCreater.g;
                }
                Intent intent = new Intent(context.getApplicationContext(), PlayerReceiver.class);
                intent.setAction(str);
                remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_playOrPause", "id"), PendingIntent.getBroadcast(context, 0, intent, 134217728));
                if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                    Intent intent2 = new Intent(context.getApplicationContext(), PlayerReceiver.class);
                    intent2.setAction(XmNotificationCreater.h);
                    remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_pre", "id"), PendingIntent.getBroadcast(context, 0, intent2, 134217728));
                    Intent intent3 = new Intent(context.getApplicationContext(), PlayerReceiver.class);
                    intent3.setAction(XmNotificationCreater.i);
                    remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_next", "id"), PendingIntent.getBroadcast(context, 0, intent3, 134217728));
                }
                Intent intent4 = new Intent("android.intent.action.VIEW");
                if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                    intent4.setData(Uri.parse("itingwelcom://open"));
                    remoteViews.setOnClickPendingIntent(getResourceId(context, "appwidget_icon_small", "id"), PendingIntent.getActivity(context, 0, intent4, 0));
                } else {
                    intent4.setData(Uri.parse("tingcar://open"));
                    remoteViews.setOnClickPendingIntent(getResourceId(context, "ll_open_app", "id"), PendingIntent.getActivity(context, 0, intent4, 0));
                }
                XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
                if (playerSrvice == null) {
                    appWidgetManager.updateAppWidget(iArr, remoteViews);
                    return;
                }
                PlayableModel playableModel = playerSrvice.getPlayableModel();
                if (playableModel instanceof Track) {
                    a(context, (Track) playableModel, remoteViews);
                }
                appWidgetManager.updateAppWidget(iArr, remoteViews);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getResourceId(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    /* access modifiers changed from: private */
    public void a(Context context, RemoteViews remoteViews) {
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, getClass());
        if (instance != null && remoteViews != null) {
            instance.updateAppWidget(componentName, remoteViews);
        }
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    private void b(Context context) {
        PlayableModel playableModel;
        XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
        if (playerSrvice != null && (playableModel = playerSrvice.getPlayableModel()) != null) {
            a(context, (Track) playableModel);
        }
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public boolean checkAndroidVersion() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public void onInitUI(Context context, Track track) {
        a(context, track);
    }

    private void a(Context context, Track track) {
        RemoteViews remoteViews = null;
        if (context != null) {
            try {
                remoteViews = new RemoteViews(context.getPackageName(), getResourceId(context, "host_reflect_appwidget_layout", "layout"));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (remoteViews != null && track != null) {
            a(context, track, remoteViews);
            a(context, remoteViews);
        }
    }

    private void a(Context context, Track track, RemoteViews remoteViews) {
        String str;
        if (track != null && context != null && remoteViews != null) {
            try {
                if (XmPlayerService.getPlayerSrvice() != null) {
                    if (XmPlayerService.getPlayerSrvice().isPlaying()) {
                        remoteViews.setImageViewResource(getResourceId(context, "appwidget_playOrPause", "id"), getResourceId(context, "host_reflect_widget_pause", "drawable"));
                    } else {
                        remoteViews.setImageViewResource(getResourceId(context, "appwidget_playOrPause", "id"), getResourceId(context, "host_reflect_widget_play", "drawable"));
                    }
                }
                String N = track.N();
                if (track.I() == null) {
                    str = "";
                } else {
                    str = track.I().b();
                }
                remoteViews.setTextViewText(getResourceId(context, "appwidget_title", "id"), N);
                remoteViews.setTextViewText(getResourceId(context, "appwidget_name", "id"), str);
                if (context.getPackageName().equals("com.ximalaya.ting.android")) {
                    b(context, track, remoteViews);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void b(final Context context, Track track, final RemoteViews remoteViews) {
        int dp2px = dp2px(context, 55.0f);
        if (!checkAndroidVersion()) {
            dp2px = dp2px(context, 30.0f);
        }
        try {
            FileUtilBase.b(context, track, dp2px, dp2px, new FileUtilBase.IBitmapDownOkCallBack() {
                public void a(Bitmap bitmap) {
                    if (bitmap == null || bitmap.isRecycled()) {
                        if (remoteViews != null) {
                            remoteViews.setInt(WidgetProvider.this.getResourceId(context, "appwidget_icon_small", "id"), "setImageResource", WidgetProvider.this.getResourceId(context, XmNotificationCreater.B, "drawable"));
                        }
                    } else if (remoteViews != null) {
                        remoteViews.setImageViewBitmap(WidgetProvider.this.getResourceId(context, "appwidget_icon_small", "id"), bitmap);
                    }
                    if (remoteViews != null) {
                        WidgetProvider.this.a(context, remoteViews);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
