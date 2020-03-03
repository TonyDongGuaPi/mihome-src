package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.ArrayList;
import java.util.List;

public class CloudVideoDownloadDBManager {
    private static final String TAG = "CloudVideoDownloadDBManager";
    private static CloudVideoDownloadDBManager instance;
    public boolean isDataBaseInit;
    private CloudVideoSQLiteOpenHelper sqLiteOpenHelper;

    private CloudVideoDownloadDBManager() {
        dataBaseInit();
    }

    public static CloudVideoDownloadDBManager getInstance() {
        if (instance == null) {
            synchronized (CloudVideoDownloadDBManager.class) {
                if (instance == null) {
                    instance = new CloudVideoDownloadDBManager();
                }
            }
        }
        return instance;
    }

    public void dataBaseInit() {
        if (this.sqLiteOpenHelper == null) {
            this.sqLiteOpenHelper = new CloudVideoSQLiteOpenHelper(SHApplication.getAppContext());
            this.isDataBaseInit = true;
        }
    }

    public void insertRecords(List<CloudVideoDownloadInfo> list) {
        if (list != null && list.size() > 0) {
            for (CloudVideoDownloadInfo insertRecord : list) {
                insertRecord(insertRecord);
            }
        }
    }

    public void insertRecord(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        if (cloudVideoDownloadInfo != null && this.sqLiteOpenHelper != null) {
            LogUtil.a(TAG, "addRecord filePath:" + cloudVideoDownloadInfo.mp4FilePath + " videoUrl:" + cloudVideoDownloadInfo.videoUrl + " uid:" + cloudVideoDownloadInfo.uid + " createTime:" + cloudVideoDownloadInfo.createTime + " startTime:" + cloudVideoDownloadInfo.startTime + " endTime:" + cloudVideoDownloadInfo.endTime);
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase sQLiteDatabase2 = this.sqLiteOpenHelper.getWritableDatabase();
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("user_id", cloudVideoDownloadInfo.uid);
                    contentValues.put("device_id", cloudVideoDownloadInfo.did);
                    contentValues.put("file_id", cloudVideoDownloadInfo.fileId);
                    contentValues.put("title", cloudVideoDownloadInfo.title);
                    contentValues.put("video_url", cloudVideoDownloadInfo.videoUrl);
                    if (!TextUtils.isEmpty(cloudVideoDownloadInfo.m3u8LocalPath)) {
                        contentValues.put("file_path", cloudVideoDownloadInfo.m3u8LocalPath);
                    } else {
                        contentValues.put("file_path", cloudVideoDownloadInfo.mp4FilePath);
                    }
                    contentValues.put(SmartConfigDataProvider.F, Long.valueOf(cloudVideoDownloadInfo.startTime));
                    contentValues.put("start_time_pretty", cloudVideoDownloadInfo.startTimePretty);
                    contentValues.put(Tags.ReserveOrder.END_TIME, Long.valueOf(cloudVideoDownloadInfo.endTime));
                    contentValues.put("end_time_pretty", cloudVideoDownloadInfo.endTimePretty);
                    contentValues.put("create_time", Long.valueOf(cloudVideoDownloadInfo.createTime));
                    contentValues.put("create_time_pretty", cloudVideoDownloadInfo.createTimePretty);
                    contentValues.put("duration", Long.valueOf(cloudVideoDownloadInfo.duration));
                    contentValues.put("timezone_id", cloudVideoDownloadInfo.timezoneId);
                    contentValues.put("size", Integer.valueOf(cloudVideoDownloadInfo.size));
                    contentValues.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(cloudVideoDownloadInfo.progress));
                    contentValues.put("status", Integer.valueOf(cloudVideoDownloadInfo.status));
                    sQLiteDatabase2.insert("mijia_camera_cloud_video", (String) null, contentValues);
                    if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                        sQLiteDatabase2.close();
                    }
                } catch (SQLException e) {
                    e = e;
                    sQLiteDatabase = sQLiteDatabase2;
                    LogUtil.b(TAG, "addRecord error:" + e.getLocalizedMessage());
                } catch (IllegalStateException e2) {
                    e = e2;
                    sQLiteDatabase = sQLiteDatabase2;
                    try {
                        LogUtil.b(TAG, "addRecord IllegalStateException:" + e.getLocalizedMessage());
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase2 = sQLiteDatabase;
                        if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                            sQLiteDatabase2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    sQLiteDatabase2.close();
                    throw th;
                }
            } catch (SQLException e3) {
                e = e3;
                LogUtil.b(TAG, "addRecord error:" + e.getLocalizedMessage());
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            } catch (IllegalStateException e4) {
                e = e4;
                LogUtil.b(TAG, "addRecord IllegalStateException:" + e.getLocalizedMessage());
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateRecord(com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo r7) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x0148
            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager$CloudVideoSQLiteOpenHelper r0 = r6.sqLiteOpenHelper
            if (r0 == 0) goto L_0x0148
            java.lang.String r0 = "CloudVideoDownloadDBManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " videoUrl:"
            r1.append(r2)
            java.lang.String r2 = r7.videoUrl
            r1.append(r2)
            java.lang.String r2 = " uid:"
            r1.append(r2)
            java.lang.String r2 = r7.uid
            r1.append(r2)
            java.lang.String r2 = " createTime:"
            r1.append(r2)
            long r2 = r7.createTime
            r1.append(r2)
            java.lang.String r2 = " startTime:"
            r1.append(r2)
            long r2 = r7.startTime
            r1.append(r2)
            java.lang.String r2 = " endTime:"
            r1.append(r2)
            long r2 = r7.endTime
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)
            r0 = 0
            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager$CloudVideoSQLiteOpenHelper r1 = r6.sqLiteOpenHelper     // Catch:{ SQLException -> 0x0115 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ SQLException -> 0x0115 }
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.<init>()     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "title"
            java.lang.String r4 = r7.title     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "video_url"
            java.lang.String r4 = r7.videoUrl     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "file_id"
            java.lang.String r4 = r7.fileId     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = r7.m3u8LocalPath     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            if (r3 != 0) goto L_0x0077
            java.lang.String r3 = "file_path"
            java.lang.String r4 = r7.m3u8LocalPath     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            goto L_0x007e
        L_0x0077:
            java.lang.String r3 = "file_path"
            java.lang.String r4 = r7.mp4FilePath     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
        L_0x007e:
            java.lang.String r3 = "start_time"
            long r4 = r7.startTime     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "start_time_pretty"
            java.lang.String r4 = r7.startTimePretty     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "end_time"
            long r4 = r7.endTime     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "end_time_pretty"
            java.lang.String r4 = r7.endTimePretty     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "timezone_id"
            java.lang.String r4 = r7.timezoneId     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "size"
            int r4 = r7.size     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "progress"
            int r4 = r7.progress     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "duration"
            long r4 = r7.duration     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "status"
            int r4 = r7.status     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r2.put(r3, r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r3.<init>()     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r4 = "device_id = '"
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r4 = r7.did     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r4 = "' and "
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r4 = "create_time"
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r4 = " = "
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            long r4 = r7.createTime     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            r3.append(r4)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r7 = r3.toString()     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            java.lang.String r3 = "mijia_camera_cloud_video"
            r1.update(r3, r2, r7, r0)     // Catch:{ SQLException -> 0x010f, all -> 0x010d }
            if (r1 == 0) goto L_0x0148
            boolean r7 = r1.isOpen()
            if (r7 == 0) goto L_0x0148
            r1.close()
            goto L_0x0148
        L_0x010d:
            r7 = move-exception
            goto L_0x013c
        L_0x010f:
            r7 = move-exception
            r0 = r1
            goto L_0x0116
        L_0x0112:
            r7 = move-exception
            r1 = r0
            goto L_0x013c
        L_0x0115:
            r7 = move-exception
        L_0x0116:
            java.lang.String r1 = "CloudVideoDownloadDBManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
            r2.<init>()     // Catch:{ all -> 0x0112 }
            java.lang.String r3 = "updateRecord error:"
            r2.append(r3)     // Catch:{ all -> 0x0112 }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x0112 }
            r2.append(r7)     // Catch:{ all -> 0x0112 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0112 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r7)     // Catch:{ all -> 0x0112 }
            if (r0 == 0) goto L_0x0148
            boolean r7 = r0.isOpen()
            if (r7 == 0) goto L_0x0148
            r0.close()
            goto L_0x0148
        L_0x013c:
            if (r1 == 0) goto L_0x0147
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x0147
            r1.close()
        L_0x0147:
            throw r7
        L_0x0148:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager.updateRecord(com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo):void");
    }

    public boolean deleteRecords(List<CloudVideoDownloadInfo> list) {
        boolean z = true;
        if (list == null || list.size() <= 0 || this.sqLiteOpenHelper == null) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BRACKET_START_STR);
        for (CloudVideoDownloadInfo cloudVideoDownloadInfo : list) {
            sb.append("'" + cloudVideoDownloadInfo.did + "',");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(Operators.BRACKET_END_STR);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Operators.BRACKET_START_STR);
        for (CloudVideoDownloadInfo cloudVideoDownloadInfo2 : list) {
            sb2.append("'" + cloudVideoDownloadInfo2.id + "',");
        }
        sb2.deleteCharAt(sb2.lastIndexOf(","));
        sb2.append(Operators.BRACKET_END_STR);
        String str = "DELETE FROM mijia_camera_cloud_video WHERE id IN " + sb2;
        LogUtil.a(TAG, "deleteSql:" + str);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = this.sqLiteOpenHelper.getWritableDatabase();
            try {
                if (writableDatabase.isOpen()) {
                    writableDatabase.execSQL(str);
                } else {
                    z = false;
                }
                if (writableDatabase == null || !writableDatabase.isOpen()) {
                    return z;
                }
                writableDatabase.close();
                return z;
            } catch (SQLException e) {
                e = e;
                sQLiteDatabase = writableDatabase;
                try {
                    LogUtil.b(TAG, "deleteRecord error:" + e.getLocalizedMessage());
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = writableDatabase;
                sQLiteDatabase.close();
                throw th;
            }
        } catch (SQLException e2) {
            e = e2;
            LogUtil.b(TAG, "deleteRecord error:" + e.getLocalizedMessage());
            sQLiteDatabase.close();
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo} */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v61 */
    /* JADX WARNING: type inference failed for: r0v63 */
    /* JADX WARNING: type inference failed for: r0v64 */
    /* JADX WARNING: type inference failed for: r0v65 */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0141, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0152, code lost:
        r6 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0152 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo getRecord(int r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SELECT * FROM mijia_camera_cloud_video WHERE id = "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r0 = 0
            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager$CloudVideoSQLiteOpenHelper r1 = r5.sqLiteOpenHelper     // Catch:{ SQLException -> 0x015b }
            android.database.sqlite.SQLiteDatabase r1 = r1.getReadableDatabase()     // Catch:{ SQLException -> 0x015b }
            boolean r2 = r1.isOpen()     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            if (r2 == 0) goto L_0x0146
            android.database.Cursor r6 = r1.rawQuery(r6, r0)     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            if (r6 == 0) goto L_0x0143
            int r2 = r6.getCount()     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            if (r2 <= 0) goto L_0x0143
            boolean r2 = r6.moveToFirst()     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            if (r2 == 0) goto L_0x0143
        L_0x0030:
            com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo r2 = new com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            r2.<init>()     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
            java.lang.String r0 = "id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            int r0 = r6.getInt(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.id = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "user_id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.uid = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "device_id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.did = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "file_id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.fileId = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "status"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            int r0 = r6.getInt(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.status = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "title"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.title = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "video_url"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.videoUrl = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "file_path"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            if (r3 != 0) goto L_0x00a4
            java.lang.String r3 = "mp4"
            boolean r3 = r0.endsWith(r3)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            if (r3 == 0) goto L_0x00a4
            r2.mp4FilePath = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            goto L_0x00a6
        L_0x00a4:
            r2.m3u8LocalPath = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
        L_0x00a6:
            java.lang.String r0 = "timezone_id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.timezoneId = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "start_time"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            long r3 = r6.getLong(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.startTime = r3     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "start_time_pretty"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.startTimePretty = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "end_time"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            long r3 = r6.getLong(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.endTime = r3     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "end_time_pretty"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.endTimePretty = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "create_time"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            long r3 = r6.getLong(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.createTime = r3     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "create_time_pretty"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.createTimePretty = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "size"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            int r0 = r6.getInt(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.size = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "progress"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            int r0 = r6.getInt(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.progress = r0     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "duration"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            long r3 = r6.getLong(r0)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r2.duration = r3     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r0 = "CloudVideoDownloadDBManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r3.<init>()     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r4 = "getRecord:"
            r3.append(r4)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            int r4 = r2.id     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            r3.append(r4)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r3)     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            boolean r0 = r6.moveToNext()     // Catch:{ SQLException -> 0x0141, all -> 0x0152 }
            if (r0 != 0) goto L_0x013e
            r0 = r2
            goto L_0x0143
        L_0x013e:
            r0 = r2
            goto L_0x0030
        L_0x0141:
            r6 = move-exception
            goto L_0x0156
        L_0x0143:
            r6.close()     // Catch:{ SQLException -> 0x0154, all -> 0x0152 }
        L_0x0146:
            if (r1 == 0) goto L_0x0183
            boolean r6 = r1.isOpen()
            if (r6 == 0) goto L_0x0183
            r1.close()
            goto L_0x0183
        L_0x0152:
            r6 = move-exception
            goto L_0x0184
        L_0x0154:
            r6 = move-exception
            r2 = r0
        L_0x0156:
            r0 = r1
            goto L_0x015d
        L_0x0158:
            r6 = move-exception
            r1 = r0
            goto L_0x0184
        L_0x015b:
            r6 = move-exception
            r2 = r0
        L_0x015d:
            java.lang.String r1 = "CloudVideoDownloadDBManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0158 }
            r3.<init>()     // Catch:{ all -> 0x0158 }
            java.lang.String r4 = "getRecord exception:"
            r3.append(r4)     // Catch:{ all -> 0x0158 }
            java.lang.String r6 = r6.getLocalizedMessage()     // Catch:{ all -> 0x0158 }
            r3.append(r6)     // Catch:{ all -> 0x0158 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0158 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r6)     // Catch:{ all -> 0x0158 }
            if (r0 == 0) goto L_0x0182
            boolean r6 = r0.isOpen()
            if (r6 == 0) goto L_0x0182
            r0.close()
        L_0x0182:
            r0 = r2
        L_0x0183:
            return r0
        L_0x0184:
            if (r1 == 0) goto L_0x018f
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x018f
            r1.close()
        L_0x018f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager.getRecord(int):com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo");
    }

    public List<CloudVideoDownloadInfo> getRecords(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        String str3 = CloudVideoNetUtils.getInstance().getTokenInfo().b;
        SQLiteDatabase sQLiteDatabase = null;
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2)) {
            String str4 = "SELECT * FROM mijia_camera_cloud_video WHERE user_id = '" + str3 + "' AND " + "device_id" + " = '" + str2 + "' ORDER BY " + "create_time" + " desc";
            try {
                SQLiteDatabase readableDatabase = this.sqLiteOpenHelper.getReadableDatabase();
                if (readableDatabase.isOpen()) {
                    Cursor rawQuery = readableDatabase.rawQuery(str4, (String[]) null);
                    if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
                        rawQuery.close();
                    } else {
                        do {
                            CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
                            cloudVideoDownloadInfo.id = rawQuery.getInt(rawQuery.getColumnIndex("id"));
                            cloudVideoDownloadInfo.uid = rawQuery.getString(rawQuery.getColumnIndex("user_id"));
                            cloudVideoDownloadInfo.did = rawQuery.getString(rawQuery.getColumnIndex("device_id"));
                            cloudVideoDownloadInfo.fileId = rawQuery.getString(rawQuery.getColumnIndex("file_id"));
                            cloudVideoDownloadInfo.status = rawQuery.getInt(rawQuery.getColumnIndex("status"));
                            cloudVideoDownloadInfo.title = rawQuery.getString(rawQuery.getColumnIndex("title"));
                            cloudVideoDownloadInfo.videoUrl = rawQuery.getString(rawQuery.getColumnIndex("video_url"));
                            String string = rawQuery.getString(rawQuery.getColumnIndex("file_path"));
                            if (TextUtils.isEmpty(string) || !string.endsWith("mp4")) {
                                cloudVideoDownloadInfo.m3u8LocalPath = string;
                            } else {
                                cloudVideoDownloadInfo.mp4FilePath = string;
                            }
                            cloudVideoDownloadInfo.timezoneId = rawQuery.getString(rawQuery.getColumnIndex("timezone_id"));
                            cloudVideoDownloadInfo.startTime = rawQuery.getLong(rawQuery.getColumnIndex(SmartConfigDataProvider.F));
                            cloudVideoDownloadInfo.startTimePretty = rawQuery.getString(rawQuery.getColumnIndex("start_time_pretty"));
                            cloudVideoDownloadInfo.endTime = rawQuery.getLong(rawQuery.getColumnIndex(Tags.ReserveOrder.END_TIME));
                            cloudVideoDownloadInfo.endTimePretty = rawQuery.getString(rawQuery.getColumnIndex("end_time_pretty"));
                            cloudVideoDownloadInfo.createTime = rawQuery.getLong(rawQuery.getColumnIndex("create_time"));
                            cloudVideoDownloadInfo.createTimePretty = rawQuery.getString(rawQuery.getColumnIndex("create_time_pretty"));
                            cloudVideoDownloadInfo.size = rawQuery.getInt(rawQuery.getColumnIndex("size"));
                            cloudVideoDownloadInfo.progress = rawQuery.getInt(rawQuery.getColumnIndex(NotificationCompat.CATEGORY_PROGRESS));
                            cloudVideoDownloadInfo.duration = rawQuery.getLong(rawQuery.getColumnIndex("duration"));
                            arrayList.add(cloudVideoDownloadInfo);
                        } while (rawQuery.moveToNext());
                        rawQuery.close();
                    }
                }
            } catch (SQLException e) {
                LogUtil.b(TAG, "getRecords error 1:" + e.getLocalizedMessage());
            }
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String str5 = "SELECT * FROM mijia_camera_cloud_video WHERE user_id = '" + str + "' AND " + "device_id" + " = '" + str2 + "' ORDER BY " + "create_time" + " desc";
            try {
                SQLiteDatabase sQLiteDatabase2 = this.sqLiteOpenHelper.getReadableDatabase();
                try {
                    if (sQLiteDatabase2.isOpen()) {
                        Cursor rawQuery2 = sQLiteDatabase2.rawQuery(str5, (String[]) null);
                        if (rawQuery2 == null || rawQuery2.getCount() <= 0 || !rawQuery2.moveToFirst()) {
                            rawQuery2.close();
                        } else {
                            do {
                                CloudVideoDownloadInfo cloudVideoDownloadInfo2 = new CloudVideoDownloadInfo();
                                cloudVideoDownloadInfo2.id = rawQuery2.getInt(rawQuery2.getColumnIndex("id"));
                                cloudVideoDownloadInfo2.uid = rawQuery2.getString(rawQuery2.getColumnIndex("user_id"));
                                cloudVideoDownloadInfo2.did = rawQuery2.getString(rawQuery2.getColumnIndex("device_id"));
                                cloudVideoDownloadInfo2.fileId = rawQuery2.getString(rawQuery2.getColumnIndex("file_id"));
                                cloudVideoDownloadInfo2.status = rawQuery2.getInt(rawQuery2.getColumnIndex("status"));
                                cloudVideoDownloadInfo2.title = rawQuery2.getString(rawQuery2.getColumnIndex("title"));
                                cloudVideoDownloadInfo2.videoUrl = rawQuery2.getString(rawQuery2.getColumnIndex("video_url"));
                                String string2 = rawQuery2.getString(rawQuery2.getColumnIndex("file_path"));
                                if (TextUtils.isEmpty(string2) || !string2.endsWith("mp4")) {
                                    cloudVideoDownloadInfo2.m3u8LocalPath = string2;
                                } else {
                                    cloudVideoDownloadInfo2.mp4FilePath = string2;
                                }
                                cloudVideoDownloadInfo2.timezoneId = rawQuery2.getString(rawQuery2.getColumnIndex("timezone_id"));
                                cloudVideoDownloadInfo2.startTime = rawQuery2.getLong(rawQuery2.getColumnIndex(SmartConfigDataProvider.F));
                                cloudVideoDownloadInfo2.startTimePretty = rawQuery2.getString(rawQuery2.getColumnIndex("start_time_pretty"));
                                cloudVideoDownloadInfo2.endTime = rawQuery2.getLong(rawQuery2.getColumnIndex(Tags.ReserveOrder.END_TIME));
                                cloudVideoDownloadInfo2.endTimePretty = rawQuery2.getString(rawQuery2.getColumnIndex("end_time_pretty"));
                                cloudVideoDownloadInfo2.createTime = rawQuery2.getLong(rawQuery2.getColumnIndex("create_time"));
                                cloudVideoDownloadInfo2.createTimePretty = rawQuery2.getString(rawQuery2.getColumnIndex("create_time_pretty"));
                                cloudVideoDownloadInfo2.size = rawQuery2.getInt(rawQuery2.getColumnIndex("size"));
                                cloudVideoDownloadInfo2.progress = rawQuery2.getInt(rawQuery2.getColumnIndex(NotificationCompat.CATEGORY_PROGRESS));
                                cloudVideoDownloadInfo2.duration = rawQuery2.getLong(rawQuery2.getColumnIndex("duration"));
                                arrayList.add(cloudVideoDownloadInfo2);
                            } while (rawQuery2.moveToNext());
                            rawQuery2.close();
                        }
                    }
                    if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                        sQLiteDatabase2.close();
                    }
                } catch (SQLException e2) {
                    e = e2;
                    sQLiteDatabase = sQLiteDatabase2;
                    try {
                        LogUtil.b(TAG, "getRecords error 2:" + e.getLocalizedMessage());
                        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                        LogUtil.a(TAG, "getRecords:" + arrayList.size());
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase2 = sQLiteDatabase;
                        sQLiteDatabase2.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                        sQLiteDatabase2.close();
                    }
                    throw th;
                }
            } catch (SQLException e3) {
                e = e3;
                LogUtil.b(TAG, "getRecords error 2:" + e.getLocalizedMessage());
                sQLiteDatabase.close();
                LogUtil.a(TAG, "getRecords:" + arrayList.size());
                return arrayList;
            }
            LogUtil.a(TAG, "getRecords:" + arrayList.size());
        }
        return arrayList;
    }

    public List<CloudVideoDownloadInfo> getRecords(int i) {
        ArrayList arrayList = new ArrayList();
        String str = "SELECT * FROM mijia_camera_cloud_video WHERE status = " + i + " ORDER BY " + "create_time" + " desc";
        try {
            SQLiteDatabase readableDatabase = this.sqLiteOpenHelper.getReadableDatabase();
            if (readableDatabase.isOpen()) {
                Cursor rawQuery = readableDatabase.rawQuery(str, (String[]) null);
                if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
                    rawQuery.close();
                } else {
                    do {
                        CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
                        cloudVideoDownloadInfo.id = rawQuery.getInt(rawQuery.getColumnIndex("id"));
                        cloudVideoDownloadInfo.uid = rawQuery.getString(rawQuery.getColumnIndex("user_id"));
                        cloudVideoDownloadInfo.did = rawQuery.getString(rawQuery.getColumnIndex("device_id"));
                        cloudVideoDownloadInfo.fileId = rawQuery.getString(rawQuery.getColumnIndex("file_id"));
                        cloudVideoDownloadInfo.status = rawQuery.getInt(rawQuery.getColumnIndex("status"));
                        cloudVideoDownloadInfo.title = rawQuery.getString(rawQuery.getColumnIndex("title"));
                        cloudVideoDownloadInfo.videoUrl = rawQuery.getString(rawQuery.getColumnIndex("video_url"));
                        String string = rawQuery.getString(rawQuery.getColumnIndex("file_path"));
                        if (TextUtils.isEmpty(string) || !string.endsWith("mp4")) {
                            cloudVideoDownloadInfo.m3u8LocalPath = string;
                        } else {
                            cloudVideoDownloadInfo.mp4FilePath = string;
                        }
                        cloudVideoDownloadInfo.timezoneId = rawQuery.getString(rawQuery.getColumnIndex("timezone_id"));
                        cloudVideoDownloadInfo.startTime = rawQuery.getLong(rawQuery.getColumnIndex(SmartConfigDataProvider.F));
                        cloudVideoDownloadInfo.startTimePretty = rawQuery.getString(rawQuery.getColumnIndex("start_time_pretty"));
                        cloudVideoDownloadInfo.endTime = rawQuery.getLong(rawQuery.getColumnIndex(Tags.ReserveOrder.END_TIME));
                        cloudVideoDownloadInfo.endTimePretty = rawQuery.getString(rawQuery.getColumnIndex("end_time_pretty"));
                        cloudVideoDownloadInfo.createTime = rawQuery.getLong(rawQuery.getColumnIndex("create_time"));
                        cloudVideoDownloadInfo.createTimePretty = rawQuery.getString(rawQuery.getColumnIndex("create_time_pretty"));
                        cloudVideoDownloadInfo.size = rawQuery.getInt(rawQuery.getColumnIndex("size"));
                        cloudVideoDownloadInfo.progress = rawQuery.getInt(rawQuery.getColumnIndex(NotificationCompat.CATEGORY_PROGRESS));
                        cloudVideoDownloadInfo.duration = rawQuery.getLong(rawQuery.getColumnIndex("duration"));
                        arrayList.add(cloudVideoDownloadInfo);
                    } while (rawQuery.moveToNext());
                    rawQuery.close();
                }
            }
        } catch (SQLException e) {
            LogUtil.b(TAG, "getRecords error 1:" + e.getLocalizedMessage());
        }
        return arrayList;
    }

    private class CloudVideoSQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String CREATE_TABLE_STRING = "CREATE TABLE mijia_camera_cloud_video(id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR(32), user_id VARCHAR(32), file_id VARCHAR(32), file_path VARCHAR(128), title VARCHAR(128), video_url VARCHAR(128), create_time INTEGER(16), start_time INTEGER(16), end_time INTEGER(16), duration INTEGER(16), create_time_pretty VARCHAR(32), start_time_pretty VARCHAR(32), end_time_pretty VARCHAR(32), timezone_id VARCHAR(64), size INTEGER(16), progress INTEGER, status INTEGER) ";
        private static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS mijia_camera_cloud_video";
        private static final String NAME = "mijia_camera_cloud_video.db";
        private static final int VERSION = 3;

        public CloudVideoSQLiteOpenHelper(Context context) {
            super(context, NAME, (SQLiteDatabase.CursorFactory) null, 3);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CREATE_TABLE_STRING);
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0133 A[Catch:{ Exception -> 0x0260, all -> 0x025e }] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x014f A[Catch:{ Exception -> 0x0260, all -> 0x025e }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x020f A[Catch:{ Exception -> 0x0260, all -> 0x025e }, LOOP:2: B:31:0x0209->B:33:0x020f, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onUpgrade(android.database.sqlite.SQLiteDatabase r7, int r8, int r9) {
            /*
                r6 = this;
                if (r8 == r9) goto L_0x02bc
                r0 = 3
                if (r9 != r0) goto L_0x0296
                java.lang.String r0 = "CloudVideoDownloadDBManager"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "onUpgrade from:"
                r1.append(r2)
                r1.append(r8)
                java.lang.String r8 = " to:"
                r1.append(r8)
                r1.append(r9)
                java.lang.String r8 = r1.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r8)
                r7.beginTransaction()
                boolean r8 = r7.isOpen()     // Catch:{ Exception -> 0x0260 }
                if (r8 == 0) goto L_0x024f
                java.lang.String r8 = " SELECT * FROM mijia_camera_cloud_video"
                r9 = 0
                android.database.Cursor r8 = r7.rawQuery(r8, r9)     // Catch:{ Exception -> 0x0260 }
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x0260 }
                r0.<init>()     // Catch:{ Exception -> 0x0260 }
                if (r8 == 0) goto L_0x0131
                int r1 = r8.getCount()     // Catch:{ Exception -> 0x0260 }
                if (r1 <= 0) goto L_0x0131
                boolean r1 = r8.moveToFirst()     // Catch:{ Exception -> 0x0260 }
                if (r1 == 0) goto L_0x0131
            L_0x0046:
                com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo r1 = new com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo     // Catch:{ Exception -> 0x0260 }
                r1.<init>()     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "id"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                int r2 = r8.getInt(r2)     // Catch:{ Exception -> 0x0260 }
                r1.id = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "user_id"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.uid = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "device_id"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.did = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "status"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                int r2 = r8.getInt(r2)     // Catch:{ Exception -> 0x0260 }
                r1.status = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "title"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.title = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "video_url"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.videoUrl = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "file_path"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0260 }
                if (r3 != 0) goto L_0x00ae
                java.lang.String r3 = "mp4"
                boolean r3 = r2.endsWith(r3)     // Catch:{ Exception -> 0x0260 }
                if (r3 == 0) goto L_0x00ae
                r1.mp4FilePath = r2     // Catch:{ Exception -> 0x0260 }
                goto L_0x00b0
            L_0x00ae:
                r1.m3u8LocalPath = r2     // Catch:{ Exception -> 0x0260 }
            L_0x00b0:
                java.lang.String r2 = "timezone_id"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.timezoneId = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "start_time"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                long r2 = r8.getLong(r2)     // Catch:{ Exception -> 0x0260 }
                r1.startTime = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "start_time_pretty"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.startTimePretty = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "end_time"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                long r2 = r8.getLong(r2)     // Catch:{ Exception -> 0x0260 }
                r1.endTime = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "end_time_pretty"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.endTimePretty = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "create_time"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                long r2 = r8.getLong(r2)     // Catch:{ Exception -> 0x0260 }
                r1.createTime = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "create_time_pretty"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0260 }
                r1.createTimePretty = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "size"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                int r2 = r8.getInt(r2)     // Catch:{ Exception -> 0x0260 }
                r1.size = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "progress"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                int r2 = r8.getInt(r2)     // Catch:{ Exception -> 0x0260 }
                r1.progress = r2     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = "duration"
                int r2 = r8.getColumnIndex(r2)     // Catch:{ Exception -> 0x0260 }
                long r2 = r8.getLong(r2)     // Catch:{ Exception -> 0x0260 }
                r1.duration = r2     // Catch:{ Exception -> 0x0260 }
                r0.add(r1)     // Catch:{ Exception -> 0x0260 }
                boolean r1 = r8.moveToNext()     // Catch:{ Exception -> 0x0260 }
                if (r1 != 0) goto L_0x0046
            L_0x0131:
                if (r8 == 0) goto L_0x0136
                r8.close()     // Catch:{ Exception -> 0x0260 }
            L_0x0136:
                java.lang.String r8 = "DROP TABLE mijia_camera_cloud_video"
                r7.execSQL(r8)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r8 = "CREATE TABLE mijia_camera_cloud_video(id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR(32), user_id VARCHAR(32), file_id VARCHAR(32), file_path VARCHAR(128), title VARCHAR(128), video_url VARCHAR(128), create_time INTEGER(16), start_time INTEGER(16), end_time INTEGER(16), duration INTEGER(16), create_time_pretty VARCHAR(32), start_time_pretty VARCHAR(32), end_time_pretty VARCHAR(32), timezone_id VARCHAR(64), size INTEGER(16), progress INTEGER, status INTEGER) "
                r7.execSQL(r8)     // Catch:{ Exception -> 0x0260 }
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0260 }
                r8.<init>()     // Catch:{ Exception -> 0x0260 }
                java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0260 }
            L_0x0149:
                boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x0260 }
                if (r1 == 0) goto L_0x0205
                java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x0260 }
                com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo r1 = (com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo) r1     // Catch:{ Exception -> 0x0260 }
                android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ Exception -> 0x0260 }
                r2.<init>()     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "id"
                int r4 = r1.id     // Catch:{ Exception -> 0x0260 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "user_id"
                java.lang.String r4 = r1.uid     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "device_id"
                java.lang.String r4 = r1.did     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = r1.fileId     // Catch:{ Exception -> 0x0260 }
                boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0260 }
                if (r3 != 0) goto L_0x0182
                java.lang.String r3 = "file_id"
                java.lang.String r4 = r1.fileId     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
            L_0x0182:
                java.lang.String r3 = "file_path"
                java.lang.String r4 = r1.mp4FilePath     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "title"
                java.lang.String r4 = r1.title     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "video_url"
                java.lang.String r4 = r1.videoUrl     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "create_time"
                long r4 = r1.createTime     // Catch:{ Exception -> 0x0260 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "start_time"
                long r4 = r1.startTime     // Catch:{ Exception -> 0x0260 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "end_time"
                long r4 = r1.endTime     // Catch:{ Exception -> 0x0260 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "duration"
                long r4 = r1.duration     // Catch:{ Exception -> 0x0260 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "create_time_pretty"
                java.lang.String r4 = r1.createTimePretty     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "start_time_pretty"
                java.lang.String r4 = r1.startTimePretty     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "end_time_pretty"
                java.lang.String r4 = r1.endTimePretty     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "timezone_id"
                java.lang.String r4 = r1.timezoneId     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "size"
                int r4 = r1.size     // Catch:{ Exception -> 0x0260 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "progress"
                int r4 = r1.progress     // Catch:{ Exception -> 0x0260 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r4)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "status"
                int r1 = r1.status     // Catch:{ Exception -> 0x0260 }
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0260 }
                r2.put(r3, r1)     // Catch:{ Exception -> 0x0260 }
                r8.add(r2)     // Catch:{ Exception -> 0x0260 }
                goto L_0x0149
            L_0x0205:
                java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x0260 }
            L_0x0209:
                boolean r0 = r8.hasNext()     // Catch:{ Exception -> 0x0260 }
                if (r0 == 0) goto L_0x0245
                java.lang.Object r0 = r8.next()     // Catch:{ Exception -> 0x0260 }
                android.content.ContentValues r0 = (android.content.ContentValues) r0     // Catch:{ Exception -> 0x0260 }
                java.lang.String r1 = "CloudVideoDownloadDBManager"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0260 }
                r2.<init>()     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "insert:"
                r2.append(r3)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "id"
                java.lang.Object r3 = r0.get(r3)     // Catch:{ Exception -> 0x0260 }
                r2.append(r3)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = " path:"
                r2.append(r3)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r3 = "file_path"
                java.lang.Object r3 = r0.get(r3)     // Catch:{ Exception -> 0x0260 }
                r2.append(r3)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0260 }
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0260 }
                java.lang.String r1 = "mijia_camera_cloud_video"
                r7.insert(r1, r9, r0)     // Catch:{ Exception -> 0x0260 }
                goto L_0x0209
            L_0x0245:
                r7.setTransactionSuccessful()     // Catch:{ Exception -> 0x0260 }
                java.lang.String r8 = "CloudVideoDownloadDBManager"
                java.lang.String r9 = "upgrade success"
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r8, (java.lang.String) r9)     // Catch:{ Exception -> 0x0260 }
            L_0x024f:
                r7.endTransaction()
                if (r7 == 0) goto L_0x02bc
                boolean r8 = r7.isOpen()
                if (r8 == 0) goto L_0x02bc
            L_0x025a:
                r7.close()
                goto L_0x02bc
            L_0x025e:
                r8 = move-exception
                goto L_0x0287
            L_0x0260:
                r8 = move-exception
                java.lang.String r9 = "CloudVideoDownloadDBManager"
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
                r0.<init>()     // Catch:{ all -> 0x025e }
                java.lang.String r1 = "exception:"
                r0.append(r1)     // Catch:{ all -> 0x025e }
                java.lang.String r8 = r8.getLocalizedMessage()     // Catch:{ all -> 0x025e }
                r0.append(r8)     // Catch:{ all -> 0x025e }
                java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x025e }
                com.xiaomi.smarthome.framework.log.LogUtil.b(r9, r8)     // Catch:{ all -> 0x025e }
                r7.endTransaction()
                if (r7 == 0) goto L_0x02bc
                boolean r8 = r7.isOpen()
                if (r8 == 0) goto L_0x02bc
                goto L_0x025a
            L_0x0287:
                r7.endTransaction()
                if (r7 == 0) goto L_0x0295
                boolean r9 = r7.isOpen()
                if (r9 == 0) goto L_0x0295
                r7.close()
            L_0x0295:
                throw r8
            L_0x0296:
                java.lang.String r0 = "CloudVideoDownloadDBManager"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "onUpgrade from:"
                r1.append(r2)
                r1.append(r8)
                java.lang.String r8 = " to:"
                r1.append(r8)
                r1.append(r9)
                java.lang.String r8 = r1.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r8)
                java.lang.String r8 = "DROP TABLE IF EXISTS mijia_camera_cloud_video"
                r7.execSQL(r8)
                r6.onCreate(r7)
            L_0x02bc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadDBManager.CloudVideoSQLiteOpenHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            super.onDowngrade(sQLiteDatabase, i, i2);
            if (i != i2) {
                LogUtil.a(CloudVideoDownloadDBManager.TAG, "onDowngrade from:" + i + " to:" + i2);
                sQLiteDatabase.execSQL(DROP_TABLE_STRING);
                onCreate(sQLiteDatabase);
            }
        }
    }

    private final class CloudVideoConst {
        static final String CREATE_TIME = "create_time";
        static final String CREATE_TIME_PRETTY = "create_time_pretty";
        static final String DID = "device_id";
        static final String DURATION = "duration";
        static final String END_TIME = "end_time";
        static final String END_TIME_PRETTY = "end_time_pretty";
        static final String FILE_ID = "file_id";
        static final String FILE_PATH = "file_path";
        static final String ID = "id";
        static final String PROGRESS = "progress";
        static final String SIZE = "size";
        static final String START_TIME = "start_time";
        static final String START_TIME_PRETTY = "start_time_pretty";
        static final String STATUS = "status";
        static final String TABLE_NAME = "mijia_camera_cloud_video";
        static final String TABLE_NAME_TEMP = "mijia_camera_cloud_video_temp";
        static final String TIMEZONE_ID = "timezone_id";
        static final String TITLE = "title";
        static final String UID = "user_id";
        static final String VIDEO_URL = "video_url";

        private CloudVideoConst() {
        }
    }
}
