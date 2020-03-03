package com.xiaomi.smarthome.notishortcut.inward;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.xiaomi.smarthome.notishortcut.R;
import com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class RemoteViewHelper {

    /* renamed from: a  reason: collision with root package name */
    private static int[] f21031a = {R.id.noti_0, R.id.noti_1, R.id.noti_2, R.id.noti_3};
    private static int[] b = {R.id.txt_device_name_0, R.id.txt_device_name_1, R.id.txt_device_name_2, R.id.txt_device_name_3};
    private static int[] c = {R.id.iv_on_status_0, R.id.iv_on_status_1, R.id.iv_on_status_2, R.id.iv_on_status_3};
    private static int[] d = {R.id.pb_is_loading_0, R.id.pb_is_loading_1, R.id.pb_is_loading_2, R.id.pb_is_loading_3};

    RemoteViewHelper() {
    }

    public static RemoteViews a(Context context, List<NotiSettingManager.NotiItem> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.remote_device_notification);
        int[] iArr = {16842904};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(16973928, iArr);
        if (Build.VERSION.SDK_INT >= 21) {
            obtainStyledAttributes = context.obtainStyledAttributes(16974339, iArr);
        }
        boolean a2 = a(obtainStyledAttributes.getColor(0, -1));
        int i = 0;
        while (true) {
            if (i >= (arrayList.size() < 4 ? arrayList.size() : 4)) {
                break;
            }
            remoteViews.setViewVisibility(f21031a[i], 0);
            if (((NotiSettingManager.NotiItem) arrayList.get(i)).g != 2 || TextUtils.isEmpty(((NotiSettingManager.NotiItem) arrayList.get(i)).h)) {
                remoteViews.setTextViewText(b[i], TextUtils.isEmpty(((NotiSettingManager.NotiItem) arrayList.get(i)).b) ? "" : ((NotiSettingManager.NotiItem) arrayList.get(i)).b);
            } else {
                remoteViews.setTextViewText(b[i], ((NotiSettingManager.NotiItem) arrayList.get(i)).h);
            }
            if (a2) {
                remoteViews.setTextColor(b[i], context.getResources().getColor(R.color.class_V));
            } else {
                remoteViews.setTextColor(b[i], context.getResources().getColor(R.color.white));
            }
            remoteViews.setViewVisibility(d[i], ((NotiSettingManager.NotiItem) arrayList.get(i)).g == 1 ? 0 : 8);
            if (!((NotiSettingManager.NotiItem) arrayList.get(i)).d) {
                Bitmap bitmap = NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).k);
                if (bitmap == null || bitmap.isRecycled()) {
                    if (bitmap != null && bitmap.isRecycled()) {
                        NotiSettingManager.a(context).e.remove(((NotiSettingManager.NotiItem) arrayList.get(i)).k);
                    }
                    remoteViews.setImageViewResource(c[i], R.drawable.ic_noti_defualt_offline);
                } else {
                    remoteViews.setImageViewBitmap(c[i], NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).k));
                }
            } else if (((NotiSettingManager.NotiItem) arrayList.get(i)).e) {
                Bitmap bitmap2 = NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).i);
                if (bitmap2 == null || bitmap2.isRecycled()) {
                    if (bitmap2 != null && bitmap2.isRecycled()) {
                        NotiSettingManager.a(context).e.remove(((NotiSettingManager.NotiItem) arrayList.get(i)).i);
                    }
                    remoteViews.setImageViewResource(c[i], R.drawable.ic_noti_defualt_on);
                } else {
                    remoteViews.setImageViewBitmap(c[i], NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).i));
                }
            } else {
                Bitmap bitmap3 = NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).j);
                if (bitmap3 == null || bitmap3.isRecycled()) {
                    if (bitmap3 != null && bitmap3.isRecycled()) {
                        NotiSettingManager.a(context).e.remove(((NotiSettingManager.NotiItem) arrayList.get(i)).j);
                    }
                    remoteViews.setImageViewResource(c[i], R.drawable.ic_noti_defualt_off);
                } else {
                    remoteViews.setImageViewBitmap(c[i], NotiSettingManager.a(context).e.get(((NotiSettingManager.NotiItem) arrayList.get(i)).j));
                }
            }
            Intent intent = new Intent(context, QuickOpServiceNew.class);
            intent.setAction(QuickOpServiceNew.ACTION_NOTIFICATION_CLICK);
            intent.putExtra(QuickOpServiceNew.INTENT_KEY_INDEX, i);
            remoteViews.setOnClickPendingIntent(f21031a[i], PendingIntent.getService(context, i, intent, 134217731));
            i++;
        }
        for (int size = arrayList.size(); size < 4; size++) {
            remoteViews.setViewVisibility(f21031a[size], 4);
        }
        return remoteViews;
    }

    private static final boolean a(int i) {
        double red = (double) Color.red(i);
        Double.isNaN(red);
        double green = (double) Color.green(i);
        Double.isNaN(green);
        double d2 = (red * 0.299d) + (green * 0.587d);
        double blue = (double) Color.blue(i);
        Double.isNaN(blue);
        return 1.0d - ((d2 + (blue * 0.114d)) / 255.0d) >= 0.5d;
    }

    public static boolean a(Context context) {
        return !a(-16777216, b(context));
    }

    public static int b(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(new NotificationCompat.Builder(context).build().contentView.getLayoutId(), (ViewGroup) null, false);
        if (viewGroup.findViewById(16908310) != null) {
            return ((TextView) viewGroup.findViewById(16908310)).getCurrentTextColor();
        }
        return a(viewGroup);
    }

    private static boolean a(int i, int i2) {
        int i3 = i | -16777216;
        int i4 = i2 | -16777216;
        int red = Color.red(i3) - Color.red(i4);
        int green = Color.green(i3) - Color.green(i4);
        int blue = Color.blue(i3) - Color.blue(i4);
        return Math.sqrt((double) (((red * red) + (green * green)) + (blue * blue))) < 180.0d;
    }

    private static int a(ViewGroup viewGroup) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(viewGroup);
        int i = 0;
        while (linkedList.size() > 0) {
            ViewGroup viewGroup2 = (ViewGroup) linkedList.getFirst();
            int i2 = i;
            for (int i3 = 0; i3 < viewGroup2.getChildCount(); i3++) {
                if (viewGroup2.getChildAt(i3) instanceof ViewGroup) {
                    linkedList.add((ViewGroup) viewGroup2.getChildAt(i3));
                } else if ((viewGroup2.getChildAt(i3) instanceof TextView) && ((TextView) viewGroup2.getChildAt(i3)).getCurrentTextColor() != -1) {
                    i2 = ((TextView) viewGroup2.getChildAt(i3)).getCurrentTextColor();
                }
            }
            linkedList.remove(viewGroup2);
            i = i2;
        }
        return i;
    }
}
