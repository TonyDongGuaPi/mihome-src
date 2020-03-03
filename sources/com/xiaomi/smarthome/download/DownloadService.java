package com.xiaomi.smarthome.download;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.smarthome.download.DownloadInfo;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class DownloadService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private DownloadManagerContentObserver f15559a;
    /* access modifiers changed from: private */
    public Map<Long, DownloadInfo> b = new HashMap();
    /* access modifiers changed from: private */
    public boolean c;
    SystemFacade mSystemFacade;
    UpdateThread mUpdateThread;

    private class DownloadManagerContentObserver extends ContentObserver {
        public DownloadManagerContentObserver() {
            super(new Handler());
        }

        public void onChange(boolean z) {
            Log.v(Constants.f15548a, "Service ContentObserver received notification");
            DownloadService.this.a(false);
        }
    }

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Cannot bind to Download Manager Service");
    }

    public void onCreate() {
        super.onCreate();
        Miio.a("DownloadService onCreate");
        Log.v(Constants.f15548a, "Service onCreate");
        if (this.mSystemFacade == null) {
            this.mSystemFacade = new RealSystemFacade(this);
        }
        this.f15559a = new DownloadManagerContentObserver();
        getContentResolver().registerContentObserver(Downloads.ALL_DOWNLOADS_CONTENT_URI, true, this.f15559a);
        this.mSystemFacade.f();
        a(true);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        Log.v(Constants.f15548a, "Service onStart");
        a(false);
        return onStartCommand;
    }

    public void onDestroy() {
        Miio.a("DownloadService onDestroy");
        getContentResolver().unregisterContentObserver(this.f15559a);
        this.mSystemFacade.g();
        Log.v(Constants.f15548a, "Service onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this) {
            this.c = true;
            if (this.mUpdateThread == null) {
                this.mUpdateThread = new UpdateThread(z);
                this.mSystemFacade.a((Thread) this.mUpdateThread);
            }
        }
    }

    private class UpdateThread extends Thread {
        private boolean b;

        public UpdateThread(boolean z) {
            super("Download Service");
            this.b = z;
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
            r4 = r1.f15561a.mSystemFacade.a();
            r6 = new java.util.HashSet(com.xiaomi.smarthome.download.DownloadService.access$500(r1.f15561a).keySet());
            r7 = r1.f15561a.getContentResolver().query(com.xiaomi.smarthome.download.Downloads.ALL_DOWNLOADS_CONTENT_URI, (java.lang.String[]) null, (java.lang.String) null, (java.lang.String[]) null, (java.lang.String) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x007f, code lost:
            if (r7 != null) goto L_0x0082;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r8 = new com.xiaomi.smarthome.download.DownloadInfo.Reader(r1.f15561a.getContentResolver(), r7);
            r9 = r7.getColumnIndexOrThrow("_id");
            r7.moveToFirst();
            r11 = Long.MAX_VALUE;
            r10 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
            if (r7.isAfterLast() != false) goto L_0x00e8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
            r15 = r7.getLong(r9);
            r6.remove(java.lang.Long.valueOf(r15));
            r13 = (com.xiaomi.smarthome.download.DownloadInfo) com.xiaomi.smarthome.download.DownloadService.access$500(r1.f15561a).get(java.lang.Long.valueOf(r15));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ba, code lost:
            if (r13 == null) goto L_0x00c2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00bc, code lost:
            com.xiaomi.smarthome.download.DownloadService.access$600(r1.f15561a, r8, r13, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c2, code lost:
            r13 = com.xiaomi.smarthome.download.DownloadService.access$700(r1.f15561a, r8, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00cc, code lost:
            if (r13.c() == false) goto L_0x00cf;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ce, code lost:
            r10 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
            r15 = r13.c(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d7, code lost:
            if (r15 != 0) goto L_0x00db;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d9, code lost:
            r10 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00dd, code lost:
            if (r15 <= 0) goto L_0x00e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e1, code lost:
            if (r15 >= r11) goto L_0x00e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e3, code lost:
            r11 = r15;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e4, code lost:
            r7.moveToNext();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e8, code lost:
            r7.close();
            r4 = r6.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f3, code lost:
            if (r4.hasNext() == false) goto L_0x0105;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f5, code lost:
            com.xiaomi.smarthome.download.DownloadService.access$800(r1.f15561a, ((java.lang.Long) r4.next()).longValue());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0105, code lost:
            r4 = com.xiaomi.smarthome.download.DownloadService.access$500(r1.f15561a).values().iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0117, code lost:
            if (r4.hasNext() == false) goto L_0x0125;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0121, code lost:
            if (((com.xiaomi.smarthome.download.DownloadInfo) r4.next()).D == false) goto L_0x0113;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0123, code lost:
            r4 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0125, code lost:
            r4 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0126, code lost:
            r5 = com.xiaomi.smarthome.download.DownloadService.access$500(r1.f15561a).values().iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0138, code lost:
            if (r5.hasNext() == false) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x013a, code lost:
            r6 = (com.xiaomi.smarthome.download.DownloadInfo) r5.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0142, code lost:
            if (r6.D == false) goto L_0x0134;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0144, code lost:
            com.xiaomi.smarthome.download.Helpers.a(r1.f15561a.getContentResolver(), r6.h, r6.l, r6.m);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x0157, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x0158, code lost:
            r7.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x015b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r19 = this;
                r1 = r19
                java.lang.String r0 = "UpdateThread run"
                com.xiaomi.smarthome.miio.Miio.a((java.lang.String) r0)
                r0 = 10
                android.os.Process.setThreadPriority(r0)
                boolean r0 = r1.b
                if (r0 == 0) goto L_0x001f
                com.xiaomi.smarthome.download.DownloadService r0 = com.xiaomi.smarthome.download.DownloadService.this
                r0.b()
                com.xiaomi.smarthome.download.DownloadService r0 = com.xiaomi.smarthome.download.DownloadService.this
                r0.c()
                com.xiaomi.smarthome.download.DownloadService r0 = com.xiaomi.smarthome.download.DownloadService.this
                r0.a()
            L_0x001f:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r0 = 0
            L_0x0025:
                r5 = r2
                r4 = 0
            L_0x0027:
                com.xiaomi.smarthome.download.DownloadService r7 = com.xiaomi.smarthome.download.DownloadService.this
                monitor-enter(r7)
                com.xiaomi.smarthome.download.DownloadService r8 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0164 }
                com.xiaomi.smarthome.download.DownloadService$UpdateThread r8 = r8.mUpdateThread     // Catch:{ all -> 0x0164 }
                if (r8 != r1) goto L_0x015c
                com.xiaomi.smarthome.download.DownloadService r8 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0164 }
                boolean r8 = r8.c     // Catch:{ all -> 0x0164 }
                if (r8 != 0) goto L_0x0052
                com.xiaomi.smarthome.download.DownloadService r0 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0164 }
                r8 = 0
                r0.mUpdateThread = r8     // Catch:{ all -> 0x0164 }
                if (r4 != 0) goto L_0x0049
                com.xiaomi.smarthome.download.DownloadService r0 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0164 }
                r0.stopSelf()     // Catch:{ all -> 0x0164 }
                java.lang.String r0 = "stop self"
                com.xiaomi.smarthome.miio.Miio.a((java.lang.String) r0)     // Catch:{ all -> 0x0164 }
            L_0x0049:
                int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
                if (r0 == 0) goto L_0x0050
                r1.a(r5)     // Catch:{ all -> 0x0164 }
            L_0x0050:
                monitor-exit(r7)     // Catch:{ all -> 0x0164 }
                return
            L_0x0052:
                com.xiaomi.smarthome.download.DownloadService r4 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0164 }
                boolean unused = r4.c = r0     // Catch:{ all -> 0x0164 }
                monitor-exit(r7)     // Catch:{ all -> 0x0164 }
                com.xiaomi.smarthome.download.DownloadService r4 = com.xiaomi.smarthome.download.DownloadService.this
                com.xiaomi.smarthome.download.SystemFacade r4 = r4.mSystemFacade
                long r4 = r4.a()
                java.util.HashSet r6 = new java.util.HashSet
                com.xiaomi.smarthome.download.DownloadService r7 = com.xiaomi.smarthome.download.DownloadService.this
                java.util.Map r7 = r7.b
                java.util.Set r7 = r7.keySet()
                r6.<init>(r7)
                com.xiaomi.smarthome.download.DownloadService r7 = com.xiaomi.smarthome.download.DownloadService.this
                android.content.ContentResolver r8 = r7.getContentResolver()
                android.net.Uri r9 = com.xiaomi.smarthome.download.Downloads.ALL_DOWNLOADS_CONTENT_URI
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                android.database.Cursor r7 = r8.query(r9, r10, r11, r12, r13)
                if (r7 != 0) goto L_0x0082
                goto L_0x0025
            L_0x0082:
                com.xiaomi.smarthome.download.DownloadInfo$Reader r8 = new com.xiaomi.smarthome.download.DownloadInfo$Reader     // Catch:{ all -> 0x0157 }
                com.xiaomi.smarthome.download.DownloadService r9 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0157 }
                android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ all -> 0x0157 }
                r8.<init>(r9, r7)     // Catch:{ all -> 0x0157 }
                java.lang.String r9 = "_id"
                int r9 = r7.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x0157 }
                r7.moveToFirst()     // Catch:{ all -> 0x0157 }
                r11 = r2
                r10 = 0
            L_0x0098:
                boolean r13 = r7.isAfterLast()     // Catch:{ all -> 0x0157 }
                r14 = 1
                if (r13 != 0) goto L_0x00e8
                long r15 = r7.getLong(r9)     // Catch:{ all -> 0x0157 }
                java.lang.Long r13 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0157 }
                r6.remove(r13)     // Catch:{ all -> 0x0157 }
                com.xiaomi.smarthome.download.DownloadService r13 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0157 }
                java.util.Map r13 = r13.b     // Catch:{ all -> 0x0157 }
                java.lang.Long r15 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0157 }
                java.lang.Object r13 = r13.get(r15)     // Catch:{ all -> 0x0157 }
                com.xiaomi.smarthome.download.DownloadInfo r13 = (com.xiaomi.smarthome.download.DownloadInfo) r13     // Catch:{ all -> 0x0157 }
                if (r13 == 0) goto L_0x00c2
                com.xiaomi.smarthome.download.DownloadService r15 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0157 }
                r15.a(r8, r13, r4)     // Catch:{ all -> 0x0157 }
                goto L_0x00c8
            L_0x00c2:
                com.xiaomi.smarthome.download.DownloadService r13 = com.xiaomi.smarthome.download.DownloadService.this     // Catch:{ all -> 0x0157 }
                com.xiaomi.smarthome.download.DownloadInfo r13 = r13.a(r8, r4)     // Catch:{ all -> 0x0157 }
            L_0x00c8:
                boolean r15 = r13.c()     // Catch:{ all -> 0x0157 }
                if (r15 == 0) goto L_0x00cf
                r10 = 1
            L_0x00cf:
                long r15 = r13.c((long) r4)     // Catch:{ all -> 0x0157 }
                r17 = 0
                int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
                if (r13 != 0) goto L_0x00db
                r10 = 1
                goto L_0x00e4
            L_0x00db:
                int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
                if (r13 <= 0) goto L_0x00e4
                int r13 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1))
                if (r13 >= 0) goto L_0x00e4
                r11 = r15
            L_0x00e4:
                r7.moveToNext()     // Catch:{ all -> 0x0157 }
                goto L_0x0098
            L_0x00e8:
                r7.close()
                java.util.Iterator r4 = r6.iterator()
            L_0x00ef:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x0105
                java.lang.Object r5 = r4.next()
                java.lang.Long r5 = (java.lang.Long) r5
                com.xiaomi.smarthome.download.DownloadService r6 = com.xiaomi.smarthome.download.DownloadService.this
                long r7 = r5.longValue()
                r6.a((long) r7)
                goto L_0x00ef
            L_0x0105:
                com.xiaomi.smarthome.download.DownloadService r4 = com.xiaomi.smarthome.download.DownloadService.this
                java.util.Map r4 = r4.b
                java.util.Collection r4 = r4.values()
                java.util.Iterator r4 = r4.iterator()
            L_0x0113:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x0125
                java.lang.Object r5 = r4.next()
                com.xiaomi.smarthome.download.DownloadInfo r5 = (com.xiaomi.smarthome.download.DownloadInfo) r5
                boolean r5 = r5.D
                if (r5 == 0) goto L_0x0113
                r4 = 1
                goto L_0x0126
            L_0x0125:
                r4 = r10
            L_0x0126:
                com.xiaomi.smarthome.download.DownloadService r5 = com.xiaomi.smarthome.download.DownloadService.this
                java.util.Map r5 = r5.b
                java.util.Collection r5 = r5.values()
                java.util.Iterator r5 = r5.iterator()
            L_0x0134:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x0154
                java.lang.Object r6 = r5.next()
                com.xiaomi.smarthome.download.DownloadInfo r6 = (com.xiaomi.smarthome.download.DownloadInfo) r6
                boolean r7 = r6.D
                if (r7 == 0) goto L_0x0134
                com.xiaomi.smarthome.download.DownloadService r7 = com.xiaomi.smarthome.download.DownloadService.this
                android.content.ContentResolver r7 = r7.getContentResolver()
                long r8 = r6.h
                java.lang.String r10 = r6.l
                java.lang.String r6 = r6.m
                com.xiaomi.smarthome.download.Helpers.a((android.content.ContentResolver) r7, (long) r8, (java.lang.String) r10, (java.lang.String) r6)
                goto L_0x0134
            L_0x0154:
                r5 = r11
                goto L_0x0027
            L_0x0157:
                r0 = move-exception
                r7.close()
                throw r0
            L_0x015c:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0164 }
                java.lang.String r2 = "multiple UpdateThreads in DownloadService"
                r0.<init>(r2)     // Catch:{ all -> 0x0164 }
                throw r0     // Catch:{ all -> 0x0164 }
            L_0x0164:
                r0 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x0164 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.DownloadService.UpdateThread.run():void");
        }

        private void a(long j) {
            AlarmManager alarmManager = (AlarmManager) DownloadService.this.getSystemService("alarm");
            if (alarmManager == null) {
                Log.e(Constants.f15548a, "couldn't get alarm manager");
                return;
            }
            Intent intent = new Intent(Constants.h);
            intent.setClassName(DownloadService.this.getPackageName(), DownloadReceiver.class.getName());
            alarmManager.set(0, DownloadService.this.mSystemFacade.a() + j, PendingIntent.getBroadcast(DownloadService.this, 0, intent, 1073741824));
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        File[] listFiles = Environment.getDownloadCacheDirectory().listFiles();
        if (listFiles != null) {
            HashSet hashSet = new HashSet();
            for (int i = 0; i < listFiles.length; i++) {
                if (!listFiles[i].getName().equals(Constants.r) && !listFiles[i].getName().equalsIgnoreCase(Constants.s)) {
                    hashSet.add(listFiles[i].getPath());
                }
            }
            Cursor query = getContentResolver().query(Downloads.ALL_DOWNLOADS_CONTENT_URI, new String[]{Downloads._DATA}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        hashSet.remove(query.getString(0));
                    } while (query.moveToNext());
                }
                query.close();
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                new File((String) it.next()).delete();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r6 = this;
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = com.xiaomi.smarthome.download.Downloads.ALL_DOWNLOADS_CONTENT_URI
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]
            java.lang.String r3 = "_id"
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "status = '200'"
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0054
            boolean r1 = r0.moveToFirst()
            if (r1 == 0) goto L_0x0054
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "trim 's count = "
            r1.append(r2)
            int r2 = r0.getCount()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.miio.Miio.a((java.lang.String) r1)
            java.lang.String r1 = "_id"
            int r1 = r0.getColumnIndexOrThrow(r1)
        L_0x003c:
            android.net.Uri r2 = com.xiaomi.smarthome.download.Downloads.ALL_DOWNLOADS_CONTENT_URI
            long r3 = r0.getLong(r1)
            android.net.Uri r2 = android.content.ContentUris.withAppendedId(r2, r3)
            android.content.ContentResolver r3 = r6.getContentResolver()
            r4 = 0
            r3.delete(r2, r4, r4)
            boolean r2 = r0.moveToNext()
            if (r2 != 0) goto L_0x003c
        L_0x0054:
            if (r0 == 0) goto L_0x0059
            r0.close()
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.DownloadService.b():void");
    }

    /* access modifiers changed from: private */
    public void c() {
        Cursor query = getContentResolver().query(Downloads.ALL_DOWNLOADS_CONTENT_URI, new String[]{"_id"}, "status > '200'", (String[]) null, Downloads.COLUMN_LAST_MODIFICATION);
        if (query == null) {
            Log.e(Constants.f15548a, "null cursor in trimDatabase");
            return;
        }
        if (query.moveToFirst()) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_id");
            for (int count = query.getCount() - 1000; count > 0; count--) {
                getContentResolver().delete(ContentUris.withAppendedId(Downloads.ALL_DOWNLOADS_CONTENT_URI, query.getLong(columnIndexOrThrow)), (String) null, (String[]) null);
                if (!query.moveToNext()) {
                    break;
                }
            }
        }
        query.close();
    }

    /* access modifiers changed from: private */
    public DownloadInfo a(DownloadInfo.Reader reader, long j) {
        DownloadInfo a2 = reader.a((Context) this, this.mSystemFacade);
        this.b.put(Long.valueOf(a2.h), a2);
        a2.g();
        a2.b(j);
        return a2;
    }

    /* access modifiers changed from: private */
    public void a(DownloadInfo.Reader reader, DownloadInfo downloadInfo, long j) {
        int i = downloadInfo.o;
        int i2 = downloadInfo.q;
        reader.a(downloadInfo);
        boolean z = false;
        boolean z2 = i == 1 && downloadInfo.o != 1 && Downloads.isStatusCompleted(downloadInfo.q);
        if (!Downloads.isStatusCompleted(i2) && Downloads.isStatusCompleted(downloadInfo.q)) {
            z = true;
        }
        if (z2 || z) {
            this.mSystemFacade.a(downloadInfo.h);
        }
        downloadInfo.b(j);
    }

    /* access modifiers changed from: private */
    public void a(long j) {
        DownloadInfo downloadInfo = this.b.get(Long.valueOf(j));
        if (downloadInfo.q == 192) {
            downloadInfo.q = Downloads.STATUS_CANCELED;
        }
        if (!(downloadInfo.n == 0 || downloadInfo.l == null)) {
            new File(downloadInfo.l).delete();
        }
        this.mSystemFacade.a(downloadInfo.h);
        this.b.remove(Long.valueOf(downloadInfo.h));
    }
}
