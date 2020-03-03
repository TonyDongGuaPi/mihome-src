package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f5063a;
    private final ComponentName b;
    private final RemoteViews d;
    private final Context e;
    private final int f;

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, int... iArr) {
        super(i, i2);
        if (iArr.length != 0) {
            this.e = (Context) Preconditions.a(context, "Context can not be null!");
            this.d = (RemoteViews) Preconditions.a(remoteViews, "RemoteViews object can not be null!");
            this.f5063a = (int[]) Preconditions.a(iArr, "WidgetIds can not be null!");
            this.f = i3;
            this.b = null;
            return;
        }
        throw new IllegalArgumentException("WidgetIds must have length > 0");
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, int... iArr) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, iArr);
    }

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, ComponentName componentName) {
        super(i, i2);
        this.e = (Context) Preconditions.a(context, "Context can not be null!");
        this.d = (RemoteViews) Preconditions.a(remoteViews, "RemoteViews object can not be null!");
        this.b = (ComponentName) Preconditions.a(componentName, "ComponentName can not be null!");
        this.f = i3;
        this.f5063a = null;
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, ComponentName componentName) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, componentName);
    }

    private void b() {
        AppWidgetManager instance = AppWidgetManager.getInstance(this.e);
        if (this.b != null) {
            instance.updateAppWidget(this.b, this.d);
        } else {
            instance.updateAppWidget(this.f5063a, this.d);
        }
    }

    public void a(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        this.d.setImageViewBitmap(this.f, bitmap);
        b();
    }
}
