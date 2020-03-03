package com.mi.global.shop.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class NotificationTextColorUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7102a = -1;
    private static final int b = Color.parseColor("#000000");
    private static final int c = Color.parseColor("#757575");
    private static final String d = "notification_title";
    /* access modifiers changed from: private */
    public static int e = -1;

    private interface Filter {
        void a(View view);
    }

    public static int a(Context context) {
        try {
            if (e == -1) {
                if (context instanceof AppCompatActivity) {
                    e = d(context);
                } else {
                    e = c(context);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return e;
    }

    public static int[] b(Context context) {
        int a2 = a(context);
        if (a2 != -1) {
            return new int[]{a2, a(a2)};
        }
        try {
            return new int[]{context.getResources().getColor(17170436), context.getResources().getColor(17170440)};
        } catch (Exception unused) {
            return new int[]{b, c};
        }
    }

    public static int a(int i) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        int alpha = Color.alpha(i);
        if (alpha <= 0) {
            alpha = 255;
        }
        return Color.argb((int) (((float) alpha) * 0.5f), red, green, blue);
    }

    private static int c(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "message");
        builder.setContentTitle(d);
        try {
            ViewGroup viewGroup = (ViewGroup) builder.build().contentView.apply(context, new FrameLayout(context));
            TextView textView = (TextView) viewGroup.findViewById(16908310);
            if (textView != null) {
                return textView.getCurrentTextColor();
            }
            a(viewGroup, new Filter() {
                public void a(View view) {
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        if (NotificationTextColorUtils.d.equals(textView.getText().toString())) {
                            int unused = NotificationTextColorUtils.e = textView.getCurrentTextColor();
                        }
                    }
                }
            });
            return e;
        } catch (Exception e2) {
            e2.printStackTrace();
            return d(context);
        }
    }

    private static int d(Context context) {
        try {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(new NotificationCompat.Builder(context, "message").build().contentView.getLayoutId(), (ViewGroup) null);
            TextView textView = (TextView) viewGroup.findViewById(16908310);
            if (textView == null) {
                return a((View) viewGroup);
            }
            return textView.getCurrentTextColor();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private static void a(View view, Filter filter) {
        if (view != null && filter != null) {
            filter.a(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    a(viewGroup.getChildAt(i), filter);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r3 = b(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(android.view.View r3) {
        /*
            r0 = -1
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.List r3 = b((android.view.View) r3)
            int r1 = a((java.util.List<android.widget.TextView>) r3)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L_0x001b
            java.lang.Object r3 = r3.get(r1)
            android.widget.TextView r3 = (android.widget.TextView) r3
            int r3 = r3.getCurrentTextColor()
            return r3
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.NotificationTextColorUtils.a(android.view.View):int");
    }

    private static int a(List<TextView> list) {
        float f = -2.14748365E9f;
        int i = Integer.MIN_VALUE;
        int i2 = 0;
        for (TextView next : list) {
            if (f < next.getTextSize()) {
                f = next.getTextSize();
                i = i2;
            }
            i2++;
        }
        return i;
    }

    private static List<TextView> b(View view) {
        final ArrayList arrayList = new ArrayList();
        a(view, new Filter() {
            public void a(View view) {
                if (view instanceof TextView) {
                    arrayList.add((TextView) view);
                }
            }
        });
        return arrayList;
    }
}
