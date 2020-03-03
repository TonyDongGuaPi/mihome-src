package com.ximalaya.ting.android.opensdk.player.appnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.receive.PlayerReceiver;
import com.ximalaya.ting.android.opensdk.util.FileUtilBase;
import com.ximalaya.ting.android.opensdk.util.Logger;

public class XmNotificationCreater {
    public static final String A = "txt_notifyNickName";
    public static final String B = "notification_default";
    public static final String C = "ting";
    public static final String D = "notification_icon";
    public static final String E = "notify_btn_dark_next_normal_xml";
    public static final String F = "notify_btn_next_pressed";
    public static final String G = "notify_btn_dark_pause_normal_xml";
    public static final String H = "notify_btn_dark_pause2_normal_xml";
    public static final String I = "notify_btn_dark_play_normal_xml";
    public static final String J = "notify_btn_dark_play2_normal_xml";
    public static final String K = "notify_btn_dark_prev_normal_xml";
    public static final String L = "notify_btn_prev_pressed";
    public static final String M = "notify_btn_light_next_normal_xml";
    public static final String N = "notify_btn_next_pressed";
    public static final String O = "notify_btn_light_pause_normal_xml";
    public static final String P = "notify_btn_light_pause2_normal_xml";
    public static final String Q = "notify_btn_light_play_normal_xml";
    public static final String R = "notify_btn_light_play2_normal_xml";
    public static final String S = "notify_btn_light_prev_normal_xml";
    public static final String T = "notify_btn_prev_pressed";
    public static final String U = "com.ximalaya.android.sdk";
    @Nullable
    public static String V = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f2185a = "com.ximalaya.ting.android.ACTION_CONTROL_START_PAUSE";
    private static XmNotificationCreater ag = null;
    private static final String aj = "and-d8";
    public static final String b = "com.ximalaya.ting.android.ACTION_CONTROL_PLAY_PRE";
    public static final String c = "com.ximalaya.ting.android.ACTION_CONTROL_PLAY_NEXT";
    public static final String d = "com.ximalaya.ting.android.ACTION_CLOSE";
    public static final String e = "com.ximalaya.ting.android.EXTRY_NOTIFICATION_TO_MAINACTIVITY";
    public static final String f = "notification_entry";
    public static final String g = "com.ximalaya.ting.android.ACTION_CONTROL_START_PAUSE_MAIN";
    public static final String h = "com.ximalaya.ting.android.ACTION_CONTROL_PLAY_PRE_MAIN";
    public static final String i = "com.ximalaya.ting.android.ACTION_CONTROL_PLAY_NEXT_MAIN";
    public static final String j = "com.ximalaya.ting.android.ACTION_CLOSE_MAIN";
    public static final String k = "com.ximalaya.ting.android.ACTION_CONTROL_RELEASE_SERVICE";
    public static final String l = "com.ximalaya.ting.android.ACTION_CONTROL_START_PAUSE_LIVE";
    public static final String m = "view_notify_play";
    public static final String n = "view_notify_play_big";
    public static final String o = "view_notify_dark_play";
    public static final String p = "view_notify_dark_play_big";
    public static final String q = "reflect_view_notify_play_for_oppo";
    public static final String r = "reflect_view_notify_play_big_for_oppo";
    public static final String s = "reflect_view_notify_dark_play_for_oppo";
    public static final String t = "reflect_view_notify_dark_play_big_for_oppo";
    public static final String u = "img_notifyIcon";
    public static final String v = "img_notifyNext";
    public static final String w = "img_notifyPlayOrPause";
    public static final String x = "img_notifyPre";
    public static final String y = "img_notifyClose";
    public static final String z = "txt_notifyMusicName";
    private RemoteViews W;
    private RemoteViews X;
    private RemoteViews Y;
    private RemoteViews Z;
    private PendingIntent aa;
    private PendingIntent ab;
    private PendingIntent ac;
    private PendingIntent ad;
    private Resources ae;
    private Context af;
    private int ah = Build.VERSION.SDK_INT;
    private String ai = "";
    /* access modifiers changed from: private */
    public RemoteViewDetailModel ak = new RemoteViewDetailModel();

    private XmNotificationCreater(Context context) {
        this.af = context;
        this.ae = context.getResources();
        this.ai = this.af.getPackageName();
    }

    public <T> Notification a(Context context, Class<T> cls) {
        b((PendingIntent) null);
        c((PendingIntent) null);
        a((PendingIntent) null);
        d((PendingIntent) null);
        return b(context, cls);
    }

    public static XmNotificationCreater a(Context context) {
        if (ag == null) {
            synchronized (XmNotificationCreater.class) {
                if (ag == null) {
                    ag = new XmNotificationCreater(context.getApplicationContext());
                }
            }
        }
        return ag;
    }

    public void a(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            String str = f2185a;
            if (this.ai.equals("com.ximalaya.ting.android")) {
                str = g;
            }
            Intent intent = new Intent(str);
            intent.setClass(this.af, PlayerReceiver.class);
            this.aa = PendingIntent.getBroadcast(this.af, 0, intent, 0);
            return;
        }
        this.aa = pendingIntent;
    }

    public void b(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            String str = c;
            if (this.ai.equals("com.ximalaya.ting.android")) {
                str = i;
            }
            Intent intent = new Intent(str);
            intent.setClass(this.af, PlayerReceiver.class);
            this.ab = PendingIntent.getBroadcast(this.af, 0, intent, 0);
            return;
        }
        this.ab = pendingIntent;
    }

    public void c(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            String str = b;
            if (this.ai.equals("com.ximalaya.ting.android")) {
                str = h;
            }
            Intent intent = new Intent(str);
            intent.setClass(this.af, PlayerReceiver.class);
            this.ac = PendingIntent.getBroadcast(this.af, 0, intent, 0);
            return;
        }
        this.ac = pendingIntent;
    }

    public void d(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            String str = d;
            if (this.ai.equals("com.ximalaya.ting.android")) {
                str = j;
            }
            Intent intent = new Intent(str);
            intent.setClass(this.af, PlayerReceiver.class);
            this.ad = PendingIntent.getBroadcast(this.af, 0, intent, 0);
            return;
        }
        this.ad = pendingIntent;
    }

    private boolean b() {
        return this.ah >= 16;
    }

    public static NotificationCompat.Builder a(Context context, NotificationCompat.Builder builder) {
        return builder.setSmallIcon(context.getResources().getIdentifier(Build.VERSION.SDK_INT >= 21 ? D : C, "drawable", context.getPackageName()));
    }

    public <T> Notification b(Context context, Class<T> cls) {
        Notification.Builder builder;
        Notification notification;
        boolean a2 = NotificationColorUtils.a(context);
        this.X = a(context, a2);
        this.W = b(context, a2);
        a(a2, this.X, this.W);
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(context, U);
        } else {
            builder = new Notification.Builder(context);
        }
        Intent intent = new Intent(context, cls);
        intent.putExtra(f, e);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 134217728)).setContentTitle("喜马拉雅").setContentText("随时随地 听我想听").setOngoing(true).setSmallIcon(a(Build.VERSION.SDK_INT >= 21 ? D : C, "drawable"));
        if (Build.VERSION.SDK_INT >= 16) {
            builder.setPriority(2);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setCustomContentView(this.W);
            builder.setCustomBigContentView(this.X);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }
        if (Build.VERSION.SDK_INT < 24) {
            notification.contentView = this.W;
            if (Build.VERSION.SDK_INT >= 16) {
                notification.bigContentView = this.X;
            }
        }
        return notification;
    }

    private RemoteViews a(Context context, boolean z2) {
        RemoteViews remoteViews;
        if (!b()) {
            return null;
        }
        if (aj.equals(V)) {
            if (z2) {
                remoteViews = new RemoteViews(context.getPackageName(), a(t, "layout"));
            } else {
                remoteViews = new RemoteViews(context.getPackageName(), a(r, "layout"));
            }
        } else if (z2) {
            remoteViews = new RemoteViews(context.getPackageName(), a(p, "layout"));
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), a(n, "layout"));
        }
        if (this.ac == null) {
            c((PendingIntent) null);
        }
        if (this.ac != null) {
            remoteViews.setOnClickPendingIntent(a(x, "id"), this.ac);
        }
        if (this.ad == null) {
            d((PendingIntent) null);
        }
        if (this.ad != null) {
            remoteViews.setOnClickPendingIntent(a(y, "id"), this.ad);
        }
        if (this.aa == null) {
            a((PendingIntent) null);
        }
        if (this.aa != null) {
            remoteViews.setOnClickPendingIntent(a(w, "id"), this.aa);
        }
        if (this.ab == null) {
            b((PendingIntent) null);
        }
        if (this.ab != null) {
            remoteViews.setOnClickPendingIntent(a(v, "id"), this.ab);
        }
        return remoteViews;
    }

    private RemoteViews b(Context context, boolean z2) {
        RemoteViews remoteViews;
        if (aj.equals(V)) {
            if (z2) {
                remoteViews = new RemoteViews(context.getPackageName(), a(s, "layout"));
            } else {
                remoteViews = new RemoteViews(context.getPackageName(), a(q, "layout"));
            }
        } else if (z2) {
            remoteViews = new RemoteViews(context.getPackageName(), a(o, "layout"));
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), a(m, "layout"));
        }
        if (this.aa == null) {
            a((PendingIntent) null);
        }
        if (this.aa != null) {
            remoteViews.setOnClickPendingIntent(a(w, "id"), this.aa);
        }
        if (this.ab == null) {
            b((PendingIntent) null);
        }
        if (this.ab != null) {
            remoteViews.setOnClickPendingIntent(a(v, "id"), this.ab);
        }
        if (this.ad == null) {
            d((PendingIntent) null);
        }
        if (this.ad != null) {
            remoteViews.setOnClickPendingIntent(a(y, "id"), this.ad);
        }
        return remoteViews;
    }

    private RemoteViews a(Context context, PendingIntent pendingIntent, boolean z2) {
        RemoteViews remoteViews;
        if (!b()) {
            return null;
        }
        if (z2) {
            remoteViews = new RemoteViews(context.getPackageName(), a(p, "layout"));
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), a(n, "layout"));
        }
        if (pendingIntent != null) {
            remoteViews.setOnClickPendingIntent(a(w, "id"), pendingIntent);
        }
        return remoteViews;
    }

    private RemoteViews b(Context context, PendingIntent pendingIntent, boolean z2) {
        RemoteViews remoteViews;
        if (z2) {
            remoteViews = new RemoteViews(context.getPackageName(), a(o, "layout"));
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), a(m, "layout"));
        }
        if (pendingIntent != null) {
            remoteViews.setOnClickPendingIntent(a(w, "id"), pendingIntent);
        }
        return remoteViews;
    }

    private int a(String str, String str2) {
        return this.ae.getIdentifier(str, str2, this.af.getPackageName());
    }

    public void a(NotificationManager notificationManager, Notification notification, int i2, boolean z2) {
        if (notification != null) {
            this.Z = a(this.af, z2);
            if (Build.VERSION.SDK_INT >= 16) {
                notification.bigContentView = this.Z;
            }
            this.Y = b(this.af, z2);
            notification.contentView = this.Y;
            this.ak.d = z2;
            this.ak.g = true;
            try {
                a(this.ak, notificationManager, notification, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void b(NotificationManager notificationManager, Notification notification, int i2, boolean z2) {
        if (notification != null) {
            this.Z = a(this.af, z2);
            if (Build.VERSION.SDK_INT >= 16) {
                notification.bigContentView = this.Z;
            }
            this.Y = b(this.af, z2);
            notification.contentView = this.Y;
            this.ak.d = z2;
            this.ak.g = false;
            a(this.ak, notificationManager, notification, i2);
        }
    }

    public void a(long j2, String str, String str2, String str3, final NotificationManager notificationManager, final Notification notification, final int i2, PendingIntent pendingIntent, boolean z2) {
        if (notification != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("LiveplayerManager init id:");
            sb.append(i2);
            sb.append(" ");
            sb.append(notification == null);
            sb.append(" coverUrl");
            sb.append(str);
            Log.i("LiveAudio", sb.toString());
            this.Z = a(this.af, pendingIntent, z2);
            if (b() && Build.VERSION.SDK_INT >= 16) {
                notification.bigContentView = this.Z;
            }
            this.Y = b(this.af, pendingIntent, z2);
            notification.contentView = this.Y;
            if (str == null || "".equals(str) || this.Y == null || notificationManager == null) {
                this.ak.b = "随时随地 听我想听";
                this.ak.f2188a = "喜马拉雅";
                this.ak.d = z2;
                this.ak.g = false;
                this.ak.c = null;
                this.ak.f = true;
                this.ak.e = true;
                a(this.ak, notificationManager, notification, i2);
                return;
            }
            int a2 = a(this.af, 64.0f);
            if (b()) {
                a2 = a(this.af, 110.0f);
            }
            Track track = new Track();
            track.a(j2);
            track.m(str);
            track.l(str);
            track.k(str);
            this.ak.d = z2;
            this.ak.g = false;
            this.ak.c = null;
            this.ak.f = true;
            this.ak.e = true;
            this.ak.f2188a = str2;
            this.ak.b = str3;
            a(this.ak, notificationManager, notification, i2);
            FileUtilBase.b(this.af, track, a2, a2, new FileUtilBase.IBitmapDownOkCallBack() {
                public void a(Bitmap bitmap) {
                    Logger.a((Object) "getBitmapByUrl  onSuccess  " + bitmap);
                    XmNotificationCreater.this.ak.c = bitmap;
                    XmNotificationCreater.this.a(XmNotificationCreater.this.ak, notificationManager, notification, i2);
                }
            });
        }
    }

    public void a(NotificationManager notificationManager, Notification notification, int i2, PendingIntent pendingIntent, boolean z2) {
        if (notification != null) {
            this.Z = a(this.af, pendingIntent, z2);
            if (Build.VERSION.SDK_INT >= 16) {
                notification.bigContentView = this.Z;
            }
            this.Y = b(this.af, pendingIntent, z2);
            notification.contentView = this.Y;
            this.ak.d = z2;
            this.ak.g = true;
            this.ak.e = true;
            this.ak.f = true;
            a(this.ak, notificationManager, notification, i2);
        }
    }

    private void a(boolean z2, boolean z3) {
        int i2;
        int i3;
        if (b() && this.Y != null) {
            if (z2) {
                if (z3) {
                    i3 = a("notify_btn_next_pressed", "drawable");
                } else {
                    i3 = a("notify_btn_next_pressed", "drawable");
                }
            } else if (z3) {
                i3 = a(M, "drawable");
            } else {
                i3 = a(E, "drawable");
            }
            try {
                this.Y.setImageViewResource(a(v, "id"), i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (b() && this.Z != null) {
            if (z2) {
                if (z3) {
                    i2 = a("notify_btn_next_pressed", "drawable");
                } else {
                    i2 = a("notify_btn_next_pressed", "drawable");
                }
            } else if (z3) {
                i2 = a(M, "drawable");
            } else {
                i2 = a(E, "drawable");
            }
            this.Z.setImageViewResource(a(v, "id"), i2);
        }
    }

    private void b(boolean z2, boolean z3) {
        int i2;
        if (b() && this.Z != null) {
            if (z2) {
                if (z3) {
                    i2 = a("notify_btn_prev_pressed", "drawable");
                } else {
                    i2 = a("notify_btn_prev_pressed", "drawable");
                }
            } else if (z3) {
                i2 = a(S, "drawable");
            } else {
                i2 = a(K, "drawable");
            }
            try {
                this.Z.setImageViewResource(a(x, "id"), i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r9, final android.app.NotificationManager r10, final android.app.Notification r11, final int r12, boolean r13) {
        /*
            r8 = this;
            if (r11 != 0) goto L_0x0003
            return
        L_0x0003:
            android.content.Context r0 = r8.af
            android.widget.RemoteViews r0 = r8.a((android.content.Context) r0, (boolean) r13)
            r8.Z = r0
            boolean r0 = r8.b()
            if (r0 == 0) goto L_0x001b
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x001b
            android.widget.RemoteViews r0 = r8.Z
            r11.bigContentView = r0
        L_0x001b:
            android.content.Context r0 = r8.af
            android.widget.RemoteViews r0 = r8.b((android.content.Context) r0, (boolean) r13)
            r8.Y = r0
            android.widget.RemoteViews r0 = r8.Y
            r11.contentView = r0
            com.ximalaya.ting.android.opensdk.model.PlayableModel r0 = r9.n()
            r1 = 0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x012d
            android.widget.RemoteViews r4 = r8.Y
            if (r4 == 0) goto L_0x012d
            if (r10 == 0) goto L_0x012d
            r4 = r0
            com.ximalaya.ting.android.opensdk.model.track.Track r4 = (com.ximalaya.ting.android.opensdk.model.track.Track) r4
            java.lang.String r5 = r4.N()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L_0x0045
            java.lang.String r5 = ""
        L_0x0045:
            java.lang.String r6 = "track"
            java.lang.String r7 = r0.b()
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x008a
            java.lang.String r6 = "live_flv"
            java.lang.String r7 = r0.b()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x005e
            goto L_0x008a
        L_0x005e:
            java.lang.String r6 = "radio"
            java.lang.String r7 = r0.b()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x006f
            java.lang.String r6 = r4.S()
            goto L_0x009a
        L_0x006f:
            java.lang.String r6 = "schedule"
            java.lang.String r7 = r0.b()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0099
            com.ximalaya.ting.android.opensdk.model.album.Announcer r6 = r4.I()
            if (r6 == 0) goto L_0x0099
            com.ximalaya.ting.android.opensdk.model.album.Announcer r6 = r4.I()
            java.lang.String r6 = r6.b()
            goto L_0x009a
        L_0x008a:
            com.ximalaya.ting.android.opensdk.model.album.Announcer r6 = r4.I()
            if (r6 == 0) goto L_0x0099
            com.ximalaya.ting.android.opensdk.model.album.Announcer r6 = r4.I()
            java.lang.String r6 = r6.b()
            goto L_0x009a
        L_0x0099:
            r6 = r1
        L_0x009a:
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x00a2
            java.lang.String r6 = ""
        L_0x00a2:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r7 = r8.ak
            r7.b = r5
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r5 = r8.ak
            r5.f2188a = r6
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r5 = r8.ak
            r5.d = r13
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r13 = r8.ak
            r13.g = r2
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r13 = r8.ak
            r13.c = r1
            if (r9 == 0) goto L_0x0107
            java.util.List r13 = r9.d()
            if (r13 == 0) goto L_0x0107
            java.util.List r13 = r9.d()
            int r13 = r13.size()
            if (r13 <= 0) goto L_0x0107
            int r13 = r9.l()
            java.util.List r9 = r9.d()
            int r9 = r9.size()
            boolean r0 = r0 instanceof com.ximalaya.ting.android.opensdk.model.track.Track
            if (r0 == 0) goto L_0x0107
            if (r13 != 0) goto L_0x00ea
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r13 = r8.ak
            r13.e = r3
            if (r9 != r3) goto L_0x00e5
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.f = r3
            goto L_0x0107
        L_0x00e5:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.f = r2
            goto L_0x0107
        L_0x00ea:
            int r0 = r9 + -1
            if (r13 != r0) goto L_0x00ff
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r13 = r8.ak
            r13.f = r3
            r13 = 2
            if (r9 < r13) goto L_0x00fa
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.e = r2
            goto L_0x0107
        L_0x00fa:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.e = r3
            goto L_0x0107
        L_0x00ff:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.e = r2
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.f = r2
        L_0x0107:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r8.a((com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater.RemoteViewDetailModel) r9, (android.app.NotificationManager) r10, (android.app.Notification) r11, (int) r12)
            android.content.Context r9 = r8.af
            r13 = 1115684864(0x42800000, float:64.0)
            int r9 = a((android.content.Context) r9, (float) r13)
            boolean r13 = r8.b()
            if (r13 == 0) goto L_0x0122
            android.content.Context r9 = r8.af
            r13 = 1121714176(0x42dc0000, float:110.0)
            int r9 = a((android.content.Context) r9, (float) r13)
        L_0x0122:
            android.content.Context r13 = r8.af
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$2 r0 = new com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$2
            r0.<init>(r10, r11, r12)
            com.ximalaya.ting.android.opensdk.util.FileUtilBase.b(r13, r4, r9, r9, r0)
            goto L_0x0152
        L_0x012d:
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            java.lang.String r0 = "随时随地 听我想听"
            r9.b = r0
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            java.lang.String r0 = "喜马拉雅"
            r9.f2188a = r0
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.d = r13
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.g = r2
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.c = r1
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.f = r3
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r9.e = r3
            com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater$RemoteViewDetailModel r9 = r8.ak
            r8.a((com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater.RemoteViewDetailModel) r9, (android.app.NotificationManager) r10, (android.app.Notification) r11, (int) r12)
        L_0x0152:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater.a(com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl, android.app.NotificationManager, android.app.Notification, int, boolean):void");
    }

    private class RemoteViewDetailModel {

        /* renamed from: a  reason: collision with root package name */
        String f2188a;
        String b;
        Bitmap c;
        boolean d;
        boolean e;
        boolean f;
        boolean g;

        private RemoteViewDetailModel() {
        }
    }

    /* access modifiers changed from: private */
    public void a(RemoteViewDetailModel remoteViewDetailModel, NotificationManager notificationManager, Notification notification, int i2) {
        if (notificationManager != null && notification != null) {
            if (TextUtils.isEmpty(remoteViewDetailModel.f2188a)) {
                remoteViewDetailModel.f2188a = "喜马拉雅";
            }
            if (TextUtils.isEmpty(remoteViewDetailModel.b)) {
                remoteViewDetailModel.b = "随时随地 听我想听";
            }
            a(remoteViewDetailModel.d, this.Z, this.Y);
            if (b() && this.Z != null) {
                this.Z.setTextViewText(a(z, "id"), remoteViewDetailModel.b);
                this.Z.setTextViewText(a(A, "id"), remoteViewDetailModel.f2188a);
                if (remoteViewDetailModel.d) {
                    if (remoteViewDetailModel.g) {
                        this.Z.setImageViewResource(a(w, "id"), a(Q, "drawable"));
                    } else {
                        this.Z.setImageViewResource(a(w, "id"), a(O, "drawable"));
                    }
                } else if (remoteViewDetailModel.g) {
                    this.Z.setImageViewResource(a(w, "id"), a(I, "drawable"));
                } else {
                    this.Z.setImageViewResource(a(w, "id"), a(G, "drawable"));
                }
            }
            this.Y.setTextViewText(a(z, "id"), remoteViewDetailModel.b);
            this.Y.setTextViewText(a(A, "id"), remoteViewDetailModel.f2188a);
            if (remoteViewDetailModel.d) {
                if (remoteViewDetailModel.g) {
                    this.Y.setImageViewResource(a(w, "id"), a(R, "drawable"));
                } else {
                    this.Y.setImageViewResource(a(w, "id"), a(P, "drawable"));
                }
            } else if (remoteViewDetailModel.g) {
                this.Y.setImageViewResource(a(w, "id"), a(J, "drawable"));
            } else {
                this.Y.setImageViewResource(a(w, "id"), a(H, "drawable"));
            }
            a(remoteViewDetailModel.f, remoteViewDetailModel.d);
            b(remoteViewDetailModel.e, remoteViewDetailModel.d);
            if (remoteViewDetailModel.c == null || remoteViewDetailModel.c.isRecycled()) {
                if (this.Y != null) {
                    try {
                        this.Y.setInt(a(u, "id"), "setImageResource", a(B, "drawable"));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (b() && this.Z != null) {
                    try {
                        this.Z.setInt(a(u, "id"), "setImageResource", a(B, "drawable"));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            } else {
                if (this.Y != null) {
                    this.Y.setImageViewBitmap(a(u, "id"), remoteViewDetailModel.c);
                }
                if (b() && this.Z != null) {
                    this.Z.setImageViewBitmap(a(u, "id"), remoteViewDetailModel.c);
                }
            }
            try {
                notificationManager.notify(i2, notification);
            } catch (Throwable unused) {
            }
        }
    }

    private static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void a() {
        if (ag != null) {
            ag.W = null;
            ag.X = null;
            ag.Y = null;
            ag.Z = null;
            ag = null;
        }
    }

    private void a(boolean z2, RemoteViews remoteViews, RemoteViews remoteViews2) {
        if (remoteViews != null) {
            NotificationColorUtils.a(this.af, remoteViews, a(z, "id"));
            NotificationColorUtils.b(this.af, remoteViews, a(A, "id"));
        }
        NotificationColorUtils.a(this.af, remoteViews2, a(z, "id"));
        NotificationColorUtils.b(this.af, remoteViews2, a(A, "id"));
    }
}
