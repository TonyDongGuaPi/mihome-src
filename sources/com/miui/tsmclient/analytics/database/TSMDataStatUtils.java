package com.miui.tsmclient.analytics.database;

import android.content.Context;
import android.database.Cursor;
import com.miui.tsmclient.analytics.entity.DataStatInfo;
import com.miui.tsmclient.database.DatabaseConstants;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class TSMDataStatUtils {
    private static volatile TSMDataStatUtils sInstance;

    private TSMDataStatUtils() {
    }

    public static TSMDataStatUtils getInstance() {
        if (sInstance == null) {
            synchronized (TSMDataStatUtils.class) {
                if (sInstance == null) {
                    sInstance = new TSMDataStatUtils();
                }
            }
        }
        return sInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void insertData(android.content.Context r4, com.miui.tsmclient.analytics.entity.DataStatInfo r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x003e
            if (r5 == 0) goto L_0x003e
            int r0 = r5.getDataID()     // Catch:{ all -> 0x003b }
            if (r0 > 0) goto L_0x000c
            goto L_0x003e
        L_0x000c:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ all -> 0x003b }
            r0.<init>()     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "data_id"
            int r2 = r5.getDataID()     // Catch:{ all -> 0x003b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x003b }
            r0.put(r1, r2)     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "data_value"
            java.lang.String r2 = r5.getDataValue()     // Catch:{ all -> 0x003b }
            r0.put(r1, r2)     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "data_time"
            java.lang.String r5 = r5.getDataTime()     // Catch:{ all -> 0x003b }
            r0.put(r1, r5)     // Catch:{ all -> 0x003b }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ all -> 0x003b }
            android.net.Uri r5 = com.miui.tsmclient.database.DatabaseConstants.CONTENT_URI_DATA_STAT     // Catch:{ all -> 0x003b }
            r4.insert(r5, r0)     // Catch:{ all -> 0x003b }
            monitor-exit(r3)
            return
        L_0x003b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x003e:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.analytics.database.TSMDataStatUtils.insertData(android.content.Context, com.miui.tsmclient.analytics.entity.DataStatInfo):void");
    }

    public synchronized List<DataStatInfo> getAllData(Context context) {
        ArrayList arrayList;
        Cursor query;
        arrayList = new ArrayList();
        if (!(context == null || (query = context.getContentResolver().query(DatabaseConstants.CONTENT_URI_DATA_STAT, DatabaseConstants.PROJECTION_DATA_STAT_INFO, (String) null, (String[]) null, (String) null)) == null)) {
            while (query.moveToNext()) {
                DataStatInfo dataStatInfo = new DataStatInfo();
                int columnIndex = query.getColumnIndex(DatabaseConstants.DataStatTable.COLUMN_DATA_ID);
                if (columnIndex >= 0) {
                    dataStatInfo.setDataID(query.getInt(columnIndex));
                    int columnIndex2 = query.getColumnIndex(DatabaseConstants.DataStatTable.COLUMN_DATA_VALUE);
                    if (columnIndex2 >= 0) {
                        dataStatInfo.setDataValue(query.getString(columnIndex2));
                    }
                    int columnIndex3 = query.getColumnIndex(DatabaseConstants.DataStatTable.COLUMN_DATA_TIME);
                    if (columnIndex3 >= 0) {
                        dataStatInfo.setDataTime(query.getString(columnIndex3));
                    } else {
                        dataStatInfo.setDataTime("");
                    }
                    arrayList.add(dataStatInfo);
                }
            }
            query.close();
        }
        return arrayList;
    }

    public synchronized int deleteAllData(Context context) {
        int i;
        i = 0;
        if (context != null) {
            try {
                i = context.getContentResolver().delete(DatabaseConstants.CONTENT_URI_DATA_STAT, (String) null, (String[]) null);
            } catch (Throwable th) {
                throw th;
            }
        }
        LogUtils.i("delete all data stat, data count is " + i);
        return i;
    }
}
