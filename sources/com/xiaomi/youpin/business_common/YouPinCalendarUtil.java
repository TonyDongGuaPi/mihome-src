package com.xiaomi.youpin.business_common;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.libra.Color;
import com.xiaomi.youpin.common.thread.AsyncTaskUtils;
import com.xiaomi.youpin.yp_permission.PermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class YouPinCalendarUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23217a = "Asia/Shanghai";
    private static final String b = "content://com.android.calendar/calendars";
    private static final String c = "content://com.android.calendar/events";
    private static final String d = "content://com.android.calendar/reminders";
    private static final String e = "youpin";
    private static final String f = "youpin@xiaomi.com";
    private static final String g = "com.xiaomi.youpin";
    private static final String h = "小米有品";
    private static final List<String> i = new ArrayList();

    static {
        i.add("android.permission.READ_CALENDAR");
        i.add("android.permission.WRITE_CALENDAR");
    }

    public static void a(Activity activity, String str, long j, CalendarEventCallback calendarEventCallback) {
        try {
            final WeakReference weakReference = new WeakReference(activity);
            if (YouPinPermissionManager.a((Context) activity, "android.permission.READ_CALENDAR")) {
                b(weakReference, str, j, calendarEventCallback);
                return;
            }
            final String str2 = str;
            final long j2 = j;
            final CalendarEventCallback calendarEventCallback2 = calendarEventCallback;
            YouPinPermissionManager.a(activity, "android.permission.READ_CALENDAR", (PermissionCallback) new PermissionCallback() {
                public void b() {
                }

                public void a() {
                    YouPinCalendarUtil.b(weakReference, str2, j2, calendarEventCallback2);
                }

                public void a(boolean z) {
                    calendarEventCallback2.a(-2, "permission denied");
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void b(WeakReference<Activity> weakReference, String str, long j, CalendarEventCallback calendarEventCallback) {
        final WeakReference<Activity> weakReference2 = weakReference;
        final String str2 = str;
        final long j2 = j;
        final CalendarEventCallback calendarEventCallback2 = calendarEventCallback;
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Object... objArr) {
                if (weakReference2.get() != null) {
                    return Boolean.valueOf(YouPinCalendarUtil.b((Context) weakReference2.get(), str2, j2));
                }
                return false;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    calendarEventCallback2.a();
                } else {
                    calendarEventCallback2.a(-1, "");
                }
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: private */
    public static boolean b(Context context, String str, long j) {
        try {
            int a2 = a(context);
            if (a2 < 0) {
                return false;
            }
            boolean z = true;
            Cursor query = context.getContentResolver().query(Uri.parse(c), (String[]) null, "calendar_id = ? AND title = ? AND dtstart = ? ", new String[]{String.valueOf(a2), str, String.valueOf(j)}, (String) null);
            if (query == null) {
                return false;
            }
            if (query.getCount() <= 0) {
                z = false;
            }
            query.close();
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static int a(Context context) {
        int b2 = b(context);
        if (b2 >= 0) {
            return b2;
        }
        if (c(context) >= 0) {
            return b(context);
        }
        return -1;
    }

    private static int b(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(b), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            if (query != null) {
                query.close();
            }
            return -1;
        }
        try {
            if (query.getCount() > 0) {
                query.moveToFirst();
                return query.getInt(query.getColumnIndex("_id"));
            }
            if (query != null) {
                query.close();
            }
            return -1;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    private static long c(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", e);
        contentValues.put("account_name", f);
        contentValues.put("account_type", g);
        contentValues.put("calendar_displayName", h);
        contentValues.put("visible", 1);
        contentValues.put("calendar_color", Integer.valueOf(Color.h));
        contentValues.put("calendar_access_level", 700);
        contentValues.put("sync_events", 1);
        contentValues.put("calendar_timezone", timeZone.getID());
        contentValues.put("ownerAccount", f);
        contentValues.put("canOrganizerRespond", 0);
        Uri insert = context.getContentResolver().insert(Uri.parse(b).buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", f).appendQueryParameter("account_type", g).build(), contentValues);
        if (insert == null) {
            return -1;
        }
        return ContentUris.parseId(insert);
    }
}
