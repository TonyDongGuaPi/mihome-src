package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import com.coloros.mcssdk.PushManager;

public class NotificationTarget extends SimpleTarget<Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final RemoteViews f5072a;
    private final Context b;
    private final int d;
    private final String e;
    private final Notification f;
    private final int g;

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2) {
        this(context, i, remoteViews, notification, i2, (String) null);
    }

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2, String str) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, notification, i2, str);
    }

    public NotificationTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, Notification notification, int i4, String str) {
        super(i, i2);
        this.b = (Context) Preconditions.a(context, "Context must not be null!");
        this.f = (Notification) Preconditions.a(notification, "Notification object can not be null!");
        this.f5072a = (RemoteViews) Preconditions.a(remoteViews, "RemoteViews object can not be null!");
        this.g = i3;
        this.d = i4;
        this.e = str;
    }

    private void b() {
        ((NotificationManager) Preconditions.a((NotificationManager) this.b.getSystemService(PushManager.MESSAGE_TYPE_NOTI))).notify(this.e, this.d, this.f);
    }

    public void a(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        this.f5072a.setImageViewBitmap(this.g, bitmap);
        b();
    }
}
