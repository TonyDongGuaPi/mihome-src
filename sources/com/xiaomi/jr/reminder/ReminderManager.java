package com.xiaomi.jr.reminder;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

@SuppressLint({"MissingPermission"})
class ReminderManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11011a = "ReminderManager";
    private static final String b = "com_xiaomi_jr_calendar";
    private static String d;
    private int c;
    private Context e;

    static void a(String str) {
        d = str;
    }

    static String a() {
        return d;
    }

    ReminderManager(Context context) {
        if (!TextUtils.isEmpty(d)) {
            this.e = context.getApplicationContext();
            c();
            this.c = d();
            return;
        }
        throw new IllegalStateException("The Calendar account name is invalid!");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
            r13 = this;
            android.content.Context r0 = r13.e
            android.content.ContentResolver r0 = r0.getContentResolver()
            r7 = 0
            android.net.Uri r2 = android.provider.CalendarContract.Calendars.CONTENT_URI     // Catch:{ Exception -> 0x00ad }
            r1 = 3
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00ad }
            java.lang.String r1 = "_id"
            r8 = 0
            r3[r8] = r1     // Catch:{ Exception -> 0x00ad }
            java.lang.String r1 = "name"
            r9 = 1
            r3[r9] = r1     // Catch:{ Exception -> 0x00ad }
            java.lang.String r1 = "sync_events"
            r10 = 2
            r3[r10] = r1     // Catch:{ Exception -> 0x00ad }
            java.lang.String r4 = "name=?"
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ Exception -> 0x00ad }
            java.lang.String r1 = "com_xiaomi_jr_calendar"
            r5[r8] = r1     // Catch:{ Exception -> 0x00ad }
            r6 = 0
            r1 = r0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00ad }
            if (r11 == 0) goto L_0x00a4
            int r1 = r11.getCount()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r1 < r9) goto L_0x00a4
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r7.<init>()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
        L_0x0036:
            boolean r1 = r11.moveToNext()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r1 == 0) goto L_0x007b
            int r12 = r11.getInt(r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r1 = r11.getInt(r10)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r1 != 0) goto L_0x004e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r12)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r7.add(r1)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            goto L_0x0036
        L_0x004e:
            android.net.Uri r2 = android.provider.CalendarContract.Events.CONTENT_URI     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String[] r3 = new java.lang.String[r9]     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r1 = "_id"
            r3[r8] = r1     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r4 = "calendar_id=?"
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r1 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r5[r8] = r1     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r6 = 0
            r1 = r0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r1 == 0) goto L_0x006e
            int r2 = r1.getCount()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r2 > 0) goto L_0x0075
        L_0x006e:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r7.add(r2)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
        L_0x0075:
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            goto L_0x0036
        L_0x007b:
            java.util.Iterator r1 = r7.iterator()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
        L_0x007f:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r2 == 0) goto L_0x00a4
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            android.net.Uri r3 = android.provider.CalendarContract.Calendars.CONTENT_URI     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r4 = "_id=?"
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r5[r8] = r2     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r0.delete(r3, r4, r5)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            goto L_0x007f
        L_0x009f:
            r0 = move-exception
            goto L_0x00ce
        L_0x00a1:
            r0 = move-exception
            r7 = r11
            goto L_0x00ae
        L_0x00a4:
            if (r11 == 0) goto L_0x00cd
            r11.close()
            goto L_0x00cd
        L_0x00aa:
            r0 = move-exception
            r11 = r7
            goto L_0x00ce
        L_0x00ad:
            r0 = move-exception
        L_0x00ae:
            java.lang.String r1 = "ReminderManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00aa }
            r2.<init>()     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = "Exception in deleteInvalidAccount. "
            r2.append(r3)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00aa }
            r2.append(r0)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x00aa }
            com.xiaomi.jr.common.utils.MifiLog.e(r1, r0)     // Catch:{ all -> 0x00aa }
            if (r7 == 0) goto L_0x00cd
            r7.close()
        L_0x00cd:
            return
        L_0x00ce:
            if (r11 == 0) goto L_0x00d3
            r11.close()
        L_0x00d3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.reminder.ReminderManager.c():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int d() {
        /*
            r12 = this;
            r0 = -1
            r1 = 0
            android.content.Context r2 = r12.e     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            android.net.Uri r4 = android.provider.CalendarContract.Calendars.CONTENT_URI     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            r2 = 2
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            java.lang.String r2 = "_id"
            r9 = 0
            r5[r9] = r2     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            java.lang.String r2 = "name"
            r10 = 1
            r5[r10] = r2     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            java.lang.String r6 = "name=?"
            java.lang.String[] r7 = new java.lang.String[r10]     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            java.lang.String r2 = "com_xiaomi_jr_calendar"
            r7[r9] = r2     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            r8 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00e5, all -> 0x00e2 }
            if (r2 == 0) goto L_0x0039
            boolean r1 = r2.moveToFirst()     // Catch:{ Exception -> 0x0036 }
            if (r1 == 0) goto L_0x0039
            int r1 = r2.getInt(r9)     // Catch:{ Exception -> 0x0036 }
            if (r2 == 0) goto L_0x0035
            r2.close()
        L_0x0035:
            return r1
        L_0x0036:
            r1 = move-exception
            goto L_0x00e9
        L_0x0039:
            java.lang.String r1 = d     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = "ReminderManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0036 }
            r4.<init>()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "create calendar account: "
            r4.append(r5)     // Catch:{ Exception -> 0x0036 }
            r4.append(r1)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0036 }
            com.xiaomi.jr.common.utils.MifiLog.b(r3, r4)     // Catch:{ Exception -> 0x0036 }
            android.content.Context r3 = r12.e     // Catch:{ Exception -> 0x0036 }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x0036 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ Exception -> 0x0036 }
            r4.<init>()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "account_name"
            r4.put(r5, r1)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "account_type"
            java.lang.String r6 = "LOCAL"
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "sync_events"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x0036 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "name"
            java.lang.String r6 = "com_xiaomi_jr_calendar"
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "calendar_displayName"
            android.content.Context r6 = r12.e     // Catch:{ Exception -> 0x0036 }
            int r7 = com.xiaomi.jr.reminder.R.string.calendar_display_name     // Catch:{ Exception -> 0x0036 }
            java.lang.Object[] r8 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x0036 }
            r8[r9] = r1     // Catch:{ Exception -> 0x0036 }
            java.lang.String r6 = r6.getString(r7, r8)     // Catch:{ Exception -> 0x0036 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "calendar_color"
            java.lang.String r6 = "#FF0000"
            int r6 = android.graphics.Color.parseColor(r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0036 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "calendar_access_level"
            r6 = 700(0x2bc, float:9.81E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0036 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "ownerAccount"
            r4.put(r5, r1)     // Catch:{ Exception -> 0x0036 }
            android.net.Uri r5 = android.provider.CalendarContract.Calendars.CONTENT_URI     // Catch:{ Exception -> 0x0036 }
            android.net.Uri$Builder r5 = r5.buildUpon()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r6 = "caller_is_syncadapter"
            java.lang.String r7 = "true"
            android.net.Uri$Builder r5 = r5.appendQueryParameter(r6, r7)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r6 = "account_name"
            android.net.Uri$Builder r1 = r5.appendQueryParameter(r6, r1)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r5 = "account_type"
            java.lang.String r6 = "LOCAL"
            android.net.Uri$Builder r1 = r1.appendQueryParameter(r5, r6)     // Catch:{ Exception -> 0x0036 }
            android.net.Uri r1 = r1.build()     // Catch:{ Exception -> 0x0036 }
            android.net.Uri r1 = r3.insert(r1, r4)     // Catch:{ Exception -> 0x0036 }
            if (r1 != 0) goto L_0x00d4
            if (r2 == 0) goto L_0x00d3
            r2.close()
        L_0x00d3:
            return r0
        L_0x00d4:
            java.lang.String r1 = r1.getLastPathSegment()     // Catch:{ Exception -> 0x0036 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0036 }
            if (r2 == 0) goto L_0x00e1
            r2.close()
        L_0x00e1:
            return r1
        L_0x00e2:
            r0 = move-exception
            r2 = r1
            goto L_0x010a
        L_0x00e5:
            r2 = move-exception
            r11 = r2
            r2 = r1
            r1 = r11
        L_0x00e9:
            java.lang.String r3 = "ReminderManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0109 }
            r4.<init>()     // Catch:{ all -> 0x0109 }
            java.lang.String r5 = "Exception in createCalendarIfNeeded. "
            r4.append(r5)     // Catch:{ all -> 0x0109 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0109 }
            r4.append(r1)     // Catch:{ all -> 0x0109 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0109 }
            com.xiaomi.jr.common.utils.MifiLog.e(r3, r1)     // Catch:{ all -> 0x0109 }
            if (r2 == 0) goto L_0x0108
            r2.close()
        L_0x0108:
            return r0
        L_0x0109:
            r0 = move-exception
        L_0x010a:
            if (r2 == 0) goto L_0x010f
            r2.close()
        L_0x010f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.reminder.ReminderManager.d():int");
    }

    /* access modifiers changed from: package-private */
    public long a(String str, String str2, String str3, long j, long j2) {
        if (this.c < 0) {
            return -1;
        }
        ContentResolver contentResolver = this.e.getContentResolver();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("calendar_id", Integer.valueOf(this.c));
            contentValues.put("title", str2);
            contentValues.put("description", str3);
            contentValues.put("allDay", 0);
            contentValues.put("dtstart", Long.valueOf(j));
            contentValues.put("dtend", Long.valueOf(j + 3600000));
            contentValues.put("eventTimezone", TimeZone.getDefault().getID());
            contentValues.put("hasAlarm", 1);
            contentValues.put("hasAttendeeData", 1);
            contentValues.put("eventStatus", 1);
            contentValues.put("organizer", str);
            Uri insert = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues);
            if (insert == null) {
                return -1;
            }
            long parseLong = Long.parseLong(insert.getLastPathSegment());
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("event_id", Long.valueOf(parseLong));
            contentValues2.put("method", 1);
            contentValues2.put("minutes", Long.valueOf(j2));
            contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, contentValues2);
            return parseLong;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public long a(String str, List<ReminderInfo> list, long j) {
        if (this.c < 0 || list == null || list.size() <= 0) {
            return 0;
        }
        int i = 0;
        for (ReminderInfo next : list) {
            if (a(str, next.a(), next.b(), next.c(), j) >= 0) {
                i++;
            }
        }
        return (long) i;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r12, long r13) {
        /*
            r11 = this;
            int r0 = r11.c
            r1 = 0
            if (r0 >= 0) goto L_0x0006
            return r1
        L_0x0006:
            long r13 = r11.b((long) r13)
            r2 = 86400000(0x5265c00, double:4.2687272E-316)
            long r2 = r2 + r13
            android.content.Context r0 = r11.e
            android.content.ContentResolver r4 = r0.getContentResolver()
            r0 = 0
            android.net.Uri r5 = android.provider.CalendarContract.Events.CONTENT_URI     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r7 = "_id"
            r6[r1] = r7     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r7 = "organizer=? AND dtstart>? AND dtend<?"
            r8 = 3
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r8[r1] = r12     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r12 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r8[r10] = r12     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r12 = 2
            java.lang.String r13 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r8[r12] = r13     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r9 = 0
            android.database.Cursor r12 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            long[] r13 = r11.a((android.database.Cursor) r12)     // Catch:{ Exception -> 0x0056, all -> 0x0053 }
            if (r12 == 0) goto L_0x0040
            r12.close()
        L_0x0040:
            if (r13 == 0) goto L_0x0052
            int r12 = r13.length
            r14 = 0
        L_0x0044:
            if (r14 >= r12) goto L_0x0052
            r2 = r13[r14]
            boolean r0 = r11.a((long) r2)
            if (r0 != 0) goto L_0x004f
            return r1
        L_0x004f:
            int r14 = r14 + 1
            goto L_0x0044
        L_0x0052:
            return r10
        L_0x0053:
            r13 = move-exception
            r0 = r12
            goto L_0x0059
        L_0x0056:
            r0 = r12
            goto L_0x005f
        L_0x0058:
            r13 = move-exception
        L_0x0059:
            if (r0 == 0) goto L_0x005e
            r0.close()
        L_0x005e:
            throw r13
        L_0x005f:
            if (r0 == 0) goto L_0x0064
            r0.close()
        L_0x0064:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.reminder.ReminderManager.a(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str) {
        return a("organizer", (Object) str);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return a("calendar_id", (Object) Integer.valueOf(this.c));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r10, java.lang.Object r11) {
        /*
            r9 = this;
            int r0 = r9.c
            r1 = 0
            if (r0 >= 0) goto L_0x0006
            return r1
        L_0x0006:
            android.content.Context r0 = r9.e
            android.content.ContentResolver r2 = r0.getContentResolver()
            r0 = 0
            android.net.Uri r3 = android.provider.CalendarContract.Events.CONTENT_URI     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            r8 = 1
            java.lang.String[] r4 = new java.lang.String[r8]     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.String r5 = "_id"
            r4[r1] = r5     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            r5.<init>()     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            r5.append(r10)     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.String r10 = "=?"
            r5.append(r10)     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.String[] r6 = new java.lang.String[r8]     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            java.lang.String r10 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            r6[r1] = r10     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            r7 = 0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005c, all -> 0x0055 }
            long[] r11 = r9.a((android.database.Cursor) r10)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            if (r10 == 0) goto L_0x003d
            r10.close()
        L_0x003d:
            if (r11 == 0) goto L_0x004f
            int r10 = r11.length
            r0 = 0
        L_0x0041:
            if (r0 >= r10) goto L_0x004f
            r2 = r11[r0]
            boolean r2 = r9.a((long) r2)
            if (r2 != 0) goto L_0x004c
            return r1
        L_0x004c:
            int r0 = r0 + 1
            goto L_0x0041
        L_0x004f:
            return r8
        L_0x0050:
            r11 = move-exception
            r0 = r10
            goto L_0x0056
        L_0x0053:
            r0 = r10
            goto L_0x005c
        L_0x0055:
            r11 = move-exception
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            r0.close()
        L_0x005b:
            throw r11
        L_0x005c:
            if (r0 == 0) goto L_0x0061
            r0.close()
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.reminder.ReminderManager.a(java.lang.String, java.lang.Object):boolean");
    }

    private boolean a(long j) {
        if (this.c < 0) {
            return false;
        }
        ContentResolver contentResolver = this.e.getContentResolver();
        try {
            if (contentResolver.delete(CalendarContract.Events.CONTENT_URI, "_id=?", new String[]{String.valueOf(j)}) < 0) {
                return false;
            }
            if (contentResolver.delete(CalendarContract.Reminders.CONTENT_URI, "event_id=?", new String[]{String.valueOf(j)}) < 0) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private long[] a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        long[] jArr = new long[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            jArr[i] = cursor.getLong(0);
            i++;
        }
        return jArr;
    }

    private long b(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return new GregorianCalendar(instance.get(1), instance.get(2), instance.get(5)).getTimeInMillis();
    }
}
