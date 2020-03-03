package com.xiaomi.smarthome.miio.miband.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.MiBandServerApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.miio.miband.utils.AccessManager;
import com.xiaomi.smarthome.miio.miband.utils.BandConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19454a = 3;
    public static final int b = 2;
    public static final int c = 1;
    public static final int d = 0;
    public static final int e = 34;
    public static final int f = 32;
    private static final Byte[] g = new Byte[0];
    private static final Handler h = new Handler(Looper.getMainLooper());
    private static volatile DataManager n;
    private Device i;
    /* access modifiers changed from: private */
    public List<DataChangeListener> j;
    private List<SleepDataItem> k;
    private List<StepDataItem> l;
    /* access modifiers changed from: private */
    public UserData m;

    public interface DataChangeListener {
        void onSleepDataChanged();

        void onStepDataChanged();

        void onUserDataChanged();
    }

    public static DataManager a() {
        if (n == null) {
            synchronized (DataManager.class) {
                if (n == null) {
                    n = new DataManager();
                    n.j();
                }
            }
        }
        return n;
    }

    public static String a(Context context, int i2, long j2) {
        if (j2 < 0) {
            return "";
        }
        Date date = new Date(j2);
        Calendar instance = Calendar.getInstance();
        if (i2 == 3) {
            instance.setTime(DateUtils.d(date));
            return instance.get(11) + ":00";
        } else if (i2 == 2) {
            Date c2 = DateUtils.c(date);
            instance.setTime(c2);
            if (DateUtils.a(c2)) {
                return context.getString(R.string.timer_today);
            }
            if (DateUtils.e(c2, Calendar.getInstance().getTime())) {
                return context.getString(R.string.timer_yesterday);
            }
            if (instance.get(7) == 2) {
                return context.getString(R.string.timer_monday);
            }
            return (instance.get(2) + 1) + "/" + instance.get(5);
        } else {
            int i3 = -1;
            if (i2 == 1) {
                if (DateUtils.d(instance.getTime(), date)) {
                    return context.getString(R.string.timer_this_week);
                }
                instance.add(3, -1);
                if (DateUtils.d(instance.getTime(), date)) {
                    return context.getString(R.string.timer_last_week);
                }
                instance.setTime(date);
                int i4 = 0;
                if (instance.get(7) == 2) {
                    i4 = 5;
                } else if (instance.get(7) == 3) {
                    i3 = -2;
                    i4 = 4;
                } else if (instance.get(7) == 4) {
                    i3 = -3;
                    i4 = 3;
                } else if (instance.get(7) == 5) {
                    i3 = -4;
                    i4 = 2;
                } else if (instance.get(7) == 6) {
                    i3 = -5;
                    i4 = 1;
                } else if (instance.get(7) == 7) {
                    i3 = -6;
                } else if (instance.get(7) == 1) {
                    i3 = 0;
                    i4 = 6;
                } else {
                    i3 = 0;
                }
                instance.add(5, i3);
                String str = (instance.get(2) + 1) + "/" + instance.get(5);
                instance.add(5, i4 - i3);
                return str + "-" + (instance.get(2) + 1) + "/" + instance.get(5);
            } else if (i2 != 0) {
                return "";
            } else {
                if (DateUtils.c(instance.getTime(), date)) {
                    return context.getString(R.string.timer_this_month);
                }
                instance.add(2, -1);
                if (DateUtils.c(instance.getTime(), date)) {
                    return context.getString(R.string.timer_last_month);
                }
                instance.setTime(date);
                int i5 = instance.get(2) + 1;
                if (i5 == 0) {
                    return instance.get(1) + context.getResources().getString(R.string.picker_year) + i5 + context.getResources().getString(R.string.picker_month);
                }
                return i5 + context.getResources().getString(R.string.picker_month);
            }
        }
    }

    public void a(Device device) {
        if (!device.equals(this.i)) {
            synchronized (g) {
                this.i = device;
                this.j.clear();
                this.k.clear();
                this.l.clear();
            }
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    DataManager.this.a(SHApplication.getAppContext());
                    return null;
                }
            }, new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        h.post(new Runnable() {
            public void run() {
                for (DataChangeListener dataChangeListener : DataManager.this.j) {
                    dataChangeListener.onSleepDataChanged();
                    dataChangeListener.onStepDataChanged();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        h.post(new Runnable() {
            public void run() {
                for (DataChangeListener onUserDataChanged : DataManager.this.j) {
                    onUserDataChanged.onUserDataChanged();
                }
            }
        });
    }

    public List<StepDataItem> b() {
        return this.l;
    }

    public List<SleepDataItem> c() {
        return this.k;
    }

    /* access modifiers changed from: private */
    public void a(final Date date, final Date date2) {
        String b2 = AccessManager.c().b();
        String a2 = AccessManager.c().a();
        if (b2 != null && a2 != null && !b2.isEmpty() && !a2.isEmpty()) {
            MiBandServerApi.a().a(date, date2, b2, a2, new AsyncResponseCallback<JSONObject>() {
                public void a(int i) {
                }

                public void a(int i, Object obj) {
                }

                public void a(final JSONObject jSONObject) {
                    AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Void doInBackground(Void... voidArr) {
                            JSONArray optJSONArray;
                            if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("data")) == null) {
                                return null;
                            }
                            int length = optJSONArray.length();
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            for (int i = 0; i < length; i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    SleepDataItem a2 = SleepDataItem.a(optJSONObject);
                                    StepDataItem a3 = StepDataItem.a(optJSONObject);
                                    if (a2 != null) {
                                        arrayList2.add(0, a2);
                                    }
                                    if (a3 != null) {
                                        arrayList.add(0, a3);
                                    }
                                }
                            }
                            DataManager.this.a(date, date2, (List<SleepDataItem>) arrayList2);
                            DataManager.this.b(date, date2, arrayList);
                            DataManager.this.b((List<StepDataItem>) arrayList);
                            DataManager.this.a((List<SleepDataItem>) arrayList2);
                            DataManager.this.c(SHApplication.getAppContext());
                            arrayList.clear();
                            arrayList2.clear();
                            DataManager.this.h();
                            return null;
                        }
                    }, new Void[0]);
                }
            });
        }
    }

    public void d() {
        String b2 = AccessManager.c().b();
        String a2 = AccessManager.c().a();
        if (b2 != null && a2 != null && !b2.isEmpty() && !a2.isEmpty()) {
            MiBandServerApi.a().a(b2, a2, new AsyncResponseCallback<JSONObject>() {
                public void a(int i) {
                }

                public void a(int i, Object obj) {
                }

                public void a(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        UserData unused = DataManager.this.m = UserData.a(jSONObject.optJSONObject("data"));
                        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                            /* access modifiers changed from: protected */
                            /* renamed from: a */
                            public Void doInBackground(Void... voidArr) {
                                DataManager.this.b(SHApplication.getAppContext());
                                return null;
                            }
                        }, new Void[0]);
                        DataManager.this.i();
                    }
                }
            });
        }
    }

    public UserData e() {
        return this.m;
    }

    public static Date a(Calendar calendar, Date date) {
        if (date == null) {
            return null;
        }
        calendar.setTime(date);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public SleepDataItem a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        for (SleepDataItem next : this.k) {
            if (str.equalsIgnoreCase(next.f)) {
                return next;
            }
        }
        return null;
    }

    public SleepDataItem a(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
        for (SleepDataItem next : this.k) {
            if (format.equalsIgnoreCase(next.f)) {
                return next;
            }
        }
        return null;
    }

    public StepDataItem b(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        for (StepDataItem next : this.l) {
            if (str.equalsIgnoreCase(next.h)) {
                return next;
            }
        }
        return null;
    }

    public StepDataItem b(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
        for (StepDataItem next : this.l) {
            if (format.equalsIgnoreCase(next.h)) {
                return next;
            }
        }
        return null;
    }

    public int f() {
        if (this.m != null) {
            return this.m.e;
        }
        return 0;
    }

    public void g() {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        instance.add(6, -6);
        a(instance.getTime(), time);
        instance.add(6, -1);
        final Date time2 = instance.getTime();
        instance.add(6, -6);
        final Date time3 = instance.getTime();
        h.postDelayed(new Runnable() {
            public void run() {
                DataManager.this.a(time3, time2);
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void a(List<SleepDataItem> list) {
        synchronized (g) {
            for (SleepDataItem remove : list) {
                this.k.remove(remove);
            }
            this.k.addAll(list);
            Collections.sort(this.k);
        }
    }

    /* access modifiers changed from: private */
    public void b(List<StepDataItem> list) {
        synchronized (g) {
            for (StepDataItem remove : list) {
                this.l.remove(remove);
            }
            this.l.addAll(list);
            Collections.sort(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        String s = CoreApi.a().s();
        if (s != null && !s.isEmpty() && !s.equalsIgnoreCase("0")) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(BandConstants.i + s, 0);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString(BandConstants.l, (String) null);
                if (string != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        synchronized (g) {
                            this.m = UserData.a(jSONObject);
                        }
                        i();
                    } catch (JSONException unused) {
                    }
                }
                String string2 = sharedPreferences.getString(BandConstants.k, (String) null);
                if (string2 != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(string2);
                        ArrayList arrayList = new ArrayList();
                        int length = jSONArray.length();
                        for (int i2 = 0; i2 < length; i2++) {
                            arrayList.add(SleepDataItem.a(jSONArray.optJSONObject(i2)));
                        }
                        a((List<SleepDataItem>) arrayList);
                    } catch (JSONException unused2) {
                    }
                }
                String string3 = sharedPreferences.getString(BandConstants.j, (String) null);
                if (string3 != null) {
                    try {
                        JSONArray jSONArray2 = new JSONArray(string3);
                        ArrayList arrayList2 = new ArrayList();
                        int length2 = jSONArray2.length();
                        for (int i3 = 0; i3 < length2; i3++) {
                            arrayList2.add(StepDataItem.a(jSONArray2.optJSONObject(i3)));
                        }
                        b((List<StepDataItem>) arrayList2);
                    } catch (JSONException unused3) {
                    }
                }
                h();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        if (r0 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        r4 = r4.edit();
        r4.remove(com.xiaomi.smarthome.miio.miband.utils.BandConstants.l);
        r4.putString(com.xiaomi.smarthome.miio.miband.utils.BandConstants.l, r0.toString());
        r4.apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r4) {
        /*
            r3 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            if (r0 == 0) goto L_0x006d
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x006d
            java.lang.String r1 = "0"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x0019
            goto L_0x006d
        L_0x0019:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "mi.band.data.cache"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            r2 = 0
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r1, r2)
            if (r4 != 0) goto L_0x0032
            return
        L_0x0032:
            java.lang.Byte[] r1 = g
            monitor-enter(r1)
            com.xiaomi.smarthome.miio.miband.data.UserData r2 = r3.m     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x0068
            com.xiaomi.smarthome.miio.miband.data.UserData r2 = r3.m     // Catch:{ all -> 0x006a }
            int r2 = r2.f19466a     // Catch:{ all -> 0x006a }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x006a }
            boolean r0 = r0.equalsIgnoreCase(r2)     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0048
            goto L_0x0068
        L_0x0048:
            com.xiaomi.smarthome.miio.miband.data.UserData r0 = r3.m     // Catch:{ all -> 0x006a }
            org.json.JSONObject r0 = r0.a()     // Catch:{ all -> 0x006a }
            monitor-exit(r1)     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0052
            return
        L_0x0052:
            android.content.SharedPreferences$Editor r4 = r4.edit()
            java.lang.String r1 = "mi.band.user.data"
            r4.remove(r1)
            java.lang.String r1 = "mi.band.user.data"
            java.lang.String r0 = r0.toString()
            r4.putString(r1, r0)
            r4.apply()
            return
        L_0x0068:
            monitor-exit(r1)     // Catch:{ all -> 0x006a }
            return
        L_0x006a:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x006a }
            throw r4
        L_0x006d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.miband.data.DataManager.b(android.content.Context):void");
    }

    /* access modifiers changed from: private */
    public void c(Context context) {
        String s = CoreApi.a().s();
        if (s != null && !s.isEmpty() && !s.equalsIgnoreCase("0")) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(BandConstants.i + s, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                JSONArray jSONArray = new JSONArray();
                synchronized (g) {
                    for (SleepDataItem a2 : this.k) {
                        jSONArray.put(a2.a());
                    }
                    edit.remove(BandConstants.k);
                    edit.putString(BandConstants.k, jSONArray.toString());
                    JSONArray jSONArray2 = new JSONArray();
                    for (StepDataItem a3 : this.l) {
                        jSONArray2.put(a3.a());
                    }
                    edit.remove(BandConstants.j);
                    edit.putString(BandConstants.j, jSONArray2.toString());
                }
                edit.apply();
            }
        }
    }

    public void a(DataChangeListener dataChangeListener) {
        if (dataChangeListener != null) {
            for (DataChangeListener equals : this.j) {
                if (dataChangeListener.equals(equals)) {
                    return;
                }
            }
            this.j.add(dataChangeListener);
        }
    }

    public void b(DataChangeListener dataChangeListener) {
        if (dataChangeListener != null) {
            this.j.remove(dataChangeListener);
        }
    }

    private void j() {
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new ArrayList();
    }

    /* access modifiers changed from: private */
    public void a(Date date, Date date2, List<SleepDataItem> list) {
        boolean z;
        Calendar instance = Calendar.getInstance();
        instance.setTime(DateUtils.c(date));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        ArrayList arrayList = new ArrayList();
        for (Date time = instance.getTime(); !time.after(date2); time = instance.getTime()) {
            String format = simpleDateFormat.format(time);
            Iterator<SleepDataItem> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().f.equalsIgnoreCase(format)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList.add(0, new SleepDataItem(time));
            }
            instance.add(6, 1);
        }
        list.addAll(arrayList);
    }

    /* access modifiers changed from: private */
    public void b(Date date, Date date2, List<StepDataItem> list) {
        boolean z;
        Calendar instance = Calendar.getInstance();
        instance.setTime(DateUtils.c(date));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        ArrayList arrayList = new ArrayList();
        for (Date time = instance.getTime(); !time.after(date2); time = instance.getTime()) {
            String format = simpleDateFormat.format(time);
            Iterator<StepDataItem> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().h.equalsIgnoreCase(format)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList.add(0, new StepDataItem(time));
            }
            instance.add(6, 1);
        }
        list.addAll(arrayList);
    }

    private DataManager() {
    }
}
