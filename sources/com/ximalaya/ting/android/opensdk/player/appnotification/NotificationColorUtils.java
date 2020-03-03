package com.ximalaya.ting.android.opensdk.player.appnotification;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import java.util.concurrent.CountDownLatch;

public class NotificationColorUtils {

    /* renamed from: a  reason: collision with root package name */
    public static String f2182a = "ximalaya_title";
    public static String b = "ximalaya_content";
    public static int c = 987654321;
    public static int d = Color.parseColor("#de000000");
    public static int e = Color.parseColor("#8a000000");
    public static int f = -1;
    public static int g = Color.parseColor("#b3ffffff");
    public static boolean h = false;
    private static final double i = 180.0d;
    private static TextView j = null;
    private static TextView k = null;
    /* access modifiers changed from: private */
    public static NotificationColorModel l;

    public static void a(Context context, RemoteViews remoteViews, int i2) {
        if (l == null) {
            a(context);
        }
        if (l.a() == c && Build.VERSION.SDK_INT >= 21) {
            if (l.c()) {
                l.a(f);
            } else {
                l.a(d);
            }
        }
        remoteViews.setTextColor(i2, l.a());
    }

    public static void b(Context context, RemoteViews remoteViews, int i2) {
        if (l == null) {
            a(context);
        }
        if (l.b() == c && Build.VERSION.SDK_INT >= 21) {
            if (l.c()) {
                l.b(g);
            } else {
                l.b(e);
            }
        }
        remoteViews.setTextColor(i2, l.b());
    }

    public static synchronized boolean a(final Context context) {
        boolean c2;
        synchronized (NotificationColorUtils.class) {
            if (l == null) {
                l = new NotificationColorModel();
                boolean z = Looper.myLooper() == Looper.getMainLooper();
                final CountDownLatch countDownLatch = null;
                if (!z) {
                    countDownLatch = new CountDownLatch(1);
                }
                AnonymousClass1 r3 = new Runnable() {
                    public void run() {
                        try {
                            int b2 = NotificationColorUtils.c(context);
                            if (b2 == NotificationColorUtils.c) {
                                NotificationColorUtils.l.a(NotificationColorUtils.c);
                                NotificationColorUtils.l.b(NotificationColorUtils.c);
                                NotificationColorUtils.l.a(true);
                            } else {
                                NotificationColorUtils.l.a(!NotificationColorUtils.b(-16777216, b2));
                            }
                        } catch (Exception unused) {
                            NotificationColorUtils.l.a(NotificationColorUtils.c);
                            NotificationColorUtils.l.b(NotificationColorUtils.c);
                            NotificationColorUtils.l.a(true);
                        }
                        if (NotificationColorUtils.l.a() == NotificationColorUtils.c && Build.VERSION.SDK_INT >= 21) {
                            if (NotificationColorUtils.l.c()) {
                                NotificationColorUtils.l.a(NotificationColorUtils.f);
                            } else {
                                NotificationColorUtils.l.a(NotificationColorUtils.d);
                            }
                        }
                        if (NotificationColorUtils.l.b() == NotificationColorUtils.c && Build.VERSION.SDK_INT >= 21) {
                            if (NotificationColorUtils.l.c()) {
                                NotificationColorUtils.l.b(NotificationColorUtils.g);
                            } else {
                                NotificationColorUtils.l.b(NotificationColorUtils.e);
                            }
                        }
                        if (countDownLatch != null) {
                            countDownLatch.countDown();
                        }
                    }
                };
                if (z) {
                    r3.run();
                } else {
                    new Handler(Looper.getMainLooper()).post(r3);
                    if (countDownLatch != null) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            c2 = l.c();
        }
        return c2;
    }

    /* access modifiers changed from: private */
    public static int c(Context context) {
        int i2;
        ViewGroup viewGroup;
        if (Build.VERSION.SDK_INT >= 24 && h) {
            return 0;
        }
        if (Build.VERSION.SDK_INT <= 15) {
            return c;
        }
        try {
            i2 = context.getResources().getIdentifier(XmNotificationCreater.B, "drawable", context.getPackageName());
        } catch (Exception e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        Notification.Builder contentText = new Notification.Builder(context).setSmallIcon(i2).setContentTitle(f2182a).setContentText(b);
        Notification build = contentText.build();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        if (Build.VERSION.SDK_INT >= 24) {
            viewGroup = (ViewGroup) contentText.createContentView().apply(context, linearLayout);
        } else {
            viewGroup = (ViewGroup) build.contentView.apply(context, linearLayout);
        }
        a(viewGroup, f2182a, b);
        if (j == null) {
            return c;
        }
        int currentTextColor = j.getCurrentTextColor();
        l.a(currentTextColor);
        if (k != null) {
            l.b(k.getCurrentTextColor());
        }
        return currentTextColor;
    }

    private static TextView a(ViewGroup viewGroup, String str, String str2) {
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText().equals(f2182a)) {
                    j = textView;
                }
                if (textView.getText().equals(b)) {
                    k = textView;
                }
            } else if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, str, str2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static boolean b(int i2, int i3) {
        int i4 = i2 | -16777216;
        int i5 = i3 | -16777216;
        int red = Color.red(i4) - Color.red(i5);
        int green = Color.green(i4) - Color.green(i5);
        int blue = Color.blue(i4) - Color.blue(i5);
        return Math.sqrt((double) (((red * red) + (green * green)) + (blue * blue))) < i;
    }

    static class NotificationColorModel {

        /* renamed from: a  reason: collision with root package name */
        private int f2184a = NotificationColorUtils.c;
        private int b = NotificationColorUtils.c;
        private boolean c = true;

        NotificationColorModel() {
        }

        public int a() {
            return this.f2184a;
        }

        public void a(int i) {
            this.f2184a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public boolean c() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }
    }
}
