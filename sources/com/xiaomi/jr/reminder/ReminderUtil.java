package com.xiaomi.jr.reminder;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReminderUtil {

    /* renamed from: a  reason: collision with root package name */
    private static Queue<CalendarOperationTask> f11012a = new LinkedList();

    public interface TaskListener {
        void a();
    }

    private static abstract class CalendarOperationTask extends AsyncTask<Void, Void, Void> {
        private CalendarOperationTask() {
        }

        public void a() {
            executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            ReminderUtil.c();
            return null;
        }
    }

    public static void a(String str) {
        ReminderManager.a(str);
    }

    /* access modifiers changed from: private */
    public static synchronized void c() {
        synchronized (ReminderUtil.class) {
            CalendarOperationTask poll = f11012a.poll();
            if (poll != null) {
                poll.a();
            }
        }
    }

    private static synchronized void a(Context context, CalendarOperationTask calendarOperationTask) {
        synchronized (ReminderUtil.class) {
            if (f11012a.isEmpty()) {
                f11012a.add(new PermissionCheckTask(context));
            }
            f11012a.add(calendarOperationTask);
            d();
        }
    }

    private static synchronized void d() {
        synchronized (ReminderUtil.class) {
            if (f11012a.peek() instanceof PermissionCheckTask) {
                f11012a.poll().a();
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void e() {
        synchronized (ReminderUtil.class) {
            f11012a.clear();
        }
    }

    static class PermissionCheckTask extends CalendarOperationTask {

        /* renamed from: a  reason: collision with root package name */
        Context f11017a;

        public PermissionCheckTask(Context context) {
            super();
            this.f11017a = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            PermissionManager.a(this.f11017a, new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"}, (Request.Callback) new Request.Callback() {
                public /* synthetic */ void b() {
                    Request.Callback.CC.$default$b(this);
                }

                public void a() {
                    ReminderUtil.c();
                }

                public void a(String str) {
                    ReminderUtil.e();
                }
            });
            return null;
        }
    }

    public static void a(final Context context, final RemindersInfo remindersInfo) {
        if (remindersInfo != null && !TextUtils.isEmpty(remindersInfo.a())) {
            a(context, (CalendarOperationTask) new CalendarOperationTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    new ReminderManager(context).a(remindersInfo.a(), remindersInfo.c(), remindersInfo.b());
                    return super.doInBackground(new Void[0]);
                }
            });
        }
    }

    public static void a(final Context context, final String str) {
        if (!TextUtils.isEmpty(str)) {
            a(context, (CalendarOperationTask) new CalendarOperationTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    new ReminderManager(context).b(str);
                    return super.doInBackground(new Void[0]);
                }
            });
        }
    }

    public static void a(Context context, String str, String str2, String str3, long j, long j2) {
        if (!TextUtils.isEmpty(str)) {
            ReminderInfo reminderInfo = new ReminderInfo();
            reminderInfo.a(str2);
            reminderInfo.b(str3);
            reminderInfo.a(j);
            ArrayList arrayList = new ArrayList();
            arrayList.add(reminderInfo);
            RemindersInfo remindersInfo = new RemindersInfo();
            remindersInfo.a(str);
            remindersInfo.a(j2);
            remindersInfo.a((List<ReminderInfo>) arrayList);
            a(context, remindersInfo);
        }
    }

    public static void a(final Context context, final String str, final long j) {
        if (!TextUtils.isEmpty(str)) {
            a(context, (CalendarOperationTask) new CalendarOperationTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    new ReminderManager(context).a(str, j);
                    return super.doInBackground(new Void[0]);
                }
            });
        }
    }

    public static void a(final Context context, final TaskListener taskListener) {
        if (!TextUtils.isEmpty(ReminderManager.a())) {
            new CalendarOperationTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    new ReminderManager(context).b();
                    return null;
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Void voidR) {
                    if (taskListener != null) {
                        taskListener.a();
                    }
                }
            }.a();
        }
    }
}
