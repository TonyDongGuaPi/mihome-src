package com.mi.global.shop.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.MainTabActivity;
import com.mi.global.shop.model.CustomNotificationContent;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import com.mi.util.ScreenInfo;
import java.util.Iterator;
import java.util.List;

public class CustomNotificationUtil {

    /* renamed from: a  reason: collision with root package name */
    private static int f7088a = 1;
    private static final float b = 3.84f;
    private static int c;
    private static int d;

    public static void a(Context context, String str, final String str2) {
        final CustomNotificationContent a2 = a(str);
        if (a2 != null) {
            LogUtil.b(a2.toString());
            final Context applicationContext = context.getApplicationContext();
            FrescoUtils.a(a2.getIconUrl(), applicationContext, (FrescoUtils.BitmapListener) new FrescoUtils.BitmapListener() {
                public void a() {
                }

                public void a(Bitmap bitmap) {
                    CustomNotificationUtil.c(applicationContext, bitmap, a2, str2);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, Bitmap bitmap, CustomNotificationContent customNotificationContent, String str) {
        List<CustomNotificationContent.Data> data = customNotificationContent.getData();
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                final CustomNotificationContent.Data data2 = data.get(i);
                switch (data2.getType()) {
                    case 0:
                        Context context2 = context;
                        data2.setCompleted(true);
                        if (!b(data)) {
                            break;
                        } else {
                            d(context, bitmap, customNotificationContent, str);
                            break;
                        }
                    case 1:
                        final List<CustomNotificationContent.Data> list = data;
                        final Context context3 = context;
                        final Bitmap bitmap2 = bitmap;
                        final CustomNotificationContent customNotificationContent2 = customNotificationContent;
                        final String str2 = str;
                        Context context4 = context;
                        FrescoUtils.a(data2.getContent(), context, a(), b(), new FrescoUtils.BitmapListener() {
                            public void a() {
                            }

                            public void a(Bitmap bitmap) {
                                data2.setBitmap(bitmap);
                                data2.setCompleted(true);
                                if (CustomNotificationUtil.b(list)) {
                                    CustomNotificationUtil.d(context3, bitmap2, customNotificationContent2, str2);
                                }
                            }
                        });
                        break;
                    default:
                        Context context5 = context;
                        break;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(List<CustomNotificationContent.Data> list) {
        for (CustomNotificationContent.Data isCompleted : list) {
            if (!isCompleted.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void d(Context context, Bitmap bitmap, CustomNotificationContent customNotificationContent, String str) {
        if (Build.VERSION.SDK_INT >= 15) {
            PendingIntent a2 = a(context, customNotificationContent.getUrl());
            String title = customNotificationContent.getTitle();
            String str2 = null;
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), c());
            remoteViews.setImageViewBitmap(R.id.iv_icon, bitmap);
            remoteViews.setTextViewText(R.id.tv_title, title);
            remoteViews.setTextViewText(R.id.tv_time, TimeFormaterUtil.a(System.currentTimeMillis()));
            int[] b2 = NotificationTextColorUtils.b(context);
            boolean z = false;
            remoteViews.setTextColor(R.id.tv_title, b2[0]);
            remoteViews.setTextColor(R.id.tv_time, b2[1]);
            if (customNotificationContent.getData().size() >= 2) {
                Iterator<CustomNotificationContent.Data> it = customNotificationContent.getData().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getType() == 1) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            for (CustomNotificationContent.Data next : customNotificationContent.getData()) {
                switch (next.getType()) {
                    case 0:
                        if (str2 == null) {
                            str2 = next.getContent();
                        }
                        RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.shop_push_notification_text);
                        remoteViews2.setTextViewText(R.id.tv_content, next.getContent());
                        remoteViews2.setTextColor(R.id.tv_content, b2[1]);
                        if (z) {
                            remoteViews2.setInt(R.id.tv_content, "setMaxLines", 1);
                        }
                        remoteViews.addView(R.id.v_container, remoteViews2);
                        break;
                    case 1:
                        RemoteViews remoteViews3 = new RemoteViews(context.getPackageName(), R.layout.shop_push_notification_image);
                        remoteViews3.setImageViewBitmap(R.id.iv_content, next.getBitmap());
                        remoteViews3.setInt(R.id.iv_content, "setMaxHeight", b());
                        remoteViews.addView(R.id.v_container, remoteViews3);
                        break;
                }
            }
            a(context, bitmap, title, TextUtils.isEmpty(str2) ? str : str2, a2, remoteViews);
        }
    }

    private static int a() {
        if (c == 0) {
            c = ScreenInfo.a().b() - PtrLocalDisplay.a(100.0f);
        }
        return c;
    }

    private static int b() {
        if (d == 0) {
            d = (int) (((float) a()) / b);
        }
        return d;
    }

    public static CustomNotificationContent a(String str) {
        try {
            return (CustomNotificationContent) new Gson().fromJson(str, CustomNotificationContent.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int c() {
        if (Device.p) {
            return R.layout.shop_push_notification_layout_style_miui;
        }
        return R.layout.shop_push_notification_layout_style_other;
    }

    @TargetApi(16)
    private static void a(Context context, Bitmap bitmap, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent, RemoteViews remoteViews) {
        Notification build = new NotificationCompat.Builder(context, "message").setSmallIcon(R.drawable.shop_icon_logo).setLargeIcon(bitmap).setContentTitle(charSequence).setContentText(charSequence2).setContentIntent(pendingIntent).setFullScreenIntent((PendingIntent) null, false).setAutoCancel(true).setWhen(System.currentTimeMillis()).build();
        build.bigContentView = remoteViews;
        int i = f7088a;
        f7088a = i + 1;
        ((NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(i, build);
    }

    private static PendingIntent a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.addFlags(536870912);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setAction("com.mi.global.shop.action_show_m_site");
        intent.putExtra("url", str);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
}
