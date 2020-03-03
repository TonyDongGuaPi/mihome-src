package com.xiaomi.smarthome.miio.db.record;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.miio.db.BaseDBRecord;
import com.xiaomi.smarthome.miio.db.MiioDBHelper;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareUserRecord extends BaseDBRecord {
    public static final String FIELD_BIRTH = "birth";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_LOCAL_PATH = "localPath";
    public static final String FIELD_NICKNAME = "nickName";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_SEX = "sex";
    public static final String FIELD_SHARE_TIME = "shareTime";
    public static final String FIELD_URL = "url";
    public static final String FIELD_USER_ID = "userId";
    @DatabaseField
    public String birth;
    @DatabaseField
    public String email;
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    public String localPath;
    @DatabaseField
    public String nickName;
    @DatabaseField
    public String phone;
    @DatabaseField
    public String sex;
    @DatabaseField
    public long shareTime;
    @DatabaseField
    public String url;
    @DatabaseField
    public String userId;

    public UserInfo createUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.b = this.nickName;
        userInfo.f16462a = this.userId;
        userInfo.c = this.url;
        userInfo.d = "";
        userInfo.e = this.nickName;
        userInfo.f = 0;
        userInfo.g = 0;
        return userInfo;
    }

    public static void insert(List<ShareDeviceDetail.ShareUser> list) {
        if (list != null) {
            for (ShareDeviceDetail.ShareUser insert : list) {
                insert(insert);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchInsert(List<ShareDeviceDetail.ShareUser> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<ShareUserRecord, Integer> shareUserRecordDao = instance.getShareUserRecordDao();
            String s = CoreApi.a().s();
            for (ShareDeviceDetail.ShareUser next : list) {
                ShareUserRecord shareUserRecord = get(next.f19679a);
                if (shareUserRecord != null) {
                    shareUserRecord.nickName = next.b;
                    shareUserRecord.url = next.c;
                    if (next.d > 0) {
                        shareUserRecord.shareTime = next.d;
                    }
                    shareUserRecord.userId = s;
                    shareUserRecordDao.create(shareUserRecord);
                }
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            instance.release();
            return true;
        } catch (SQLException unused) {
            writableDatabase.endTransaction();
            instance.release();
            return false;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            instance.release();
            throw th;
        }
    }

    public static boolean insert(ShareDeviceDetail.ShareUser shareUser) {
        ShareUserRecord shareUserRecord;
        if (shareUser == null || (shareUserRecord = get(shareUser.f19679a)) == null) {
            return false;
        }
        shareUserRecord.nickName = shareUser.b;
        shareUserRecord.url = shareUser.c;
        if (shareUser.d > 0) {
            shareUserRecord.shareTime = shareUser.d;
        }
        return insert(shareUserRecord);
    }

    public static boolean insert(ShareUserRecord shareUserRecord) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<ShareUserRecord, Integer> shareUserRecordDao = instance.getShareUserRecordDao();
            QueryBuilder<ShareUserRecord, Integer> queryBuilder = shareUserRecordDao.queryBuilder();
            queryBuilder.where().eq("userId", shareUserRecord.userId);
            List<ShareUserRecord> query = queryBuilder.query();
            if (query != null) {
                shareUserRecordDao.delete((Collection<ShareUserRecord>) query);
            }
            if (shareUserRecord.shareTime == 0) {
                shareUserRecord.shareTime = System.currentTimeMillis() / 1000;
            }
            shareUserRecordDao.create(shareUserRecord);
            return true;
        } catch (SQLException unused) {
            return false;
        } catch (Exception unused2) {
            return false;
        } finally {
            instance.release();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        r0.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r5 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A[ExcHandler: all (r5v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.miio.db.record.ShareUserRecord get(java.lang.String r5) {
        /*
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            com.xiaomi.smarthome.miio.db.MiioDBHelper r0 = com.xiaomi.smarthome.miio.db.MiioDBHelper.getInstance(r0)
            r1 = 0
            com.j256.ormlite.dao.Dao r2 = r0.getShareUserRecordDao()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            com.j256.ormlite.stmt.QueryBuilder r2 = r2.queryBuilder()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            com.j256.ormlite.stmt.Where r3 = r2.where()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            java.lang.String r4 = "userId"
            r3.eq(r4, r5)     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            java.util.List r2 = r2.query()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            if (r2 == 0) goto L_0x002f
            int r3 = r2.size()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            if (r3 <= 0) goto L_0x002f
            r5 = 0
            java.lang.Object r5 = r2.get(r5)     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            com.xiaomi.smarthome.miio.db.record.ShareUserRecord r5 = (com.xiaomi.smarthome.miio.db.record.ShareUserRecord) r5     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            goto L_0x003e
        L_0x002f:
            com.xiaomi.smarthome.miio.db.record.ShareUserRecord r2 = new com.xiaomi.smarthome.miio.db.record.ShareUserRecord     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            r2.<init>()     // Catch:{ Exception | SQLException -> 0x003d, all -> 0x0038 }
            r2.userId = r5     // Catch:{ Exception | SQLException -> 0x0036, all -> 0x0038 }
        L_0x0036:
            r5 = r2
            goto L_0x003e
        L_0x0038:
            r5 = move-exception
            r0.release()
            throw r5
        L_0x003d:
            r5 = r1
        L_0x003e:
            r0.release()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.db.record.ShareUserRecord.get(java.lang.String):com.xiaomi.smarthome.miio.db.record.ShareUserRecord");
    }

    /* JADX INFO: finally extract failed */
    public static List<ShareUserRecord> queryAll() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            List<ShareUserRecord> query = instance.getShareUserRecordDao().queryBuilder().orderBy(FIELD_SHARE_TIME, false).query();
            instance.release();
            return query;
        } catch (SQLException unused) {
            instance.release();
            return null;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean deleteAll(com.xiaomi.smarthome.miio.db.record.ShareUserRecord r2) {
        /*
            r2 = 0
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ Exception -> 0x001f }
            com.xiaomi.smarthome.miio.db.MiioDBHelper r0 = com.xiaomi.smarthome.miio.db.MiioDBHelper.getInstance(r0)     // Catch:{ Exception -> 0x001f }
            r0.getShareUserRecordDao()     // Catch:{ Exception -> 0x0018, all -> 0x0013 }
            r2 = 1
            if (r0 == 0) goto L_0x0012
            r0.release()
        L_0x0012:
            return r2
        L_0x0013:
            r2 = move-exception
            r1 = r0
            r0 = r2
            r2 = r1
            goto L_0x002a
        L_0x0018:
            r2 = move-exception
            r1 = r0
            r0 = r2
            r2 = r1
            goto L_0x0020
        L_0x001d:
            r0 = move-exception
            goto L_0x002a
        L_0x001f:
            r0 = move-exception
        L_0x0020:
            r0.printStackTrace()     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x0028
            r2.release()
        L_0x0028:
            r2 = 0
            return r2
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.release()
        L_0x002f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.db.record.ShareUserRecord.deleteAll(com.xiaomi.smarthome.miio.db.record.ShareUserRecord):boolean");
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchDelete(List<String> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<ShareUserRecord, Integer> shareUserRecordDao = instance.getShareUserRecordDao();
            for (String eq : list) {
                DeleteBuilder<ShareUserRecord, Integer> deleteBuilder = shareUserRecordDao.deleteBuilder();
                deleteBuilder.where().eq("userId", eq);
                shareUserRecordDao.delete(deleteBuilder.prepare());
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            instance.release();
            return true;
        } catch (Exception | SQLException unused) {
            writableDatabase.endTransaction();
            instance.release();
            return false;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            instance.release();
            throw th;
        }
    }

    public JSONObject getSyncDownJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "device");
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: finally extract failed */
    public static int update(ShareUserRecord shareUserRecord) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            UpdateBuilder<ShareUserRecord, Integer> updateBuilder = instance.getShareUserRecordDao().updateBuilder();
            updateBuilder.where().eq("userId", shareUserRecord.userId);
            if (!TextUtils.isEmpty(shareUserRecord.url)) {
                updateBuilder.updateColumnValue("url", shareUserRecord.url);
            }
            if (!TextUtils.isEmpty(shareUserRecord.nickName)) {
                updateBuilder.updateColumnValue("nickName", shareUserRecord.nickName);
            }
            if (!TextUtils.isEmpty(shareUserRecord.phone)) {
                updateBuilder.updateColumnValue("phone", shareUserRecord.phone);
            }
            if (!TextUtils.isEmpty(shareUserRecord.email)) {
                updateBuilder.updateColumnValue("email", shareUserRecord.email);
            }
            if (!TextUtils.isEmpty(shareUserRecord.sex)) {
                updateBuilder.updateColumnValue(FIELD_SEX, shareUserRecord.sex);
            }
            if (!TextUtils.isEmpty(shareUserRecord.birth)) {
                updateBuilder.updateColumnValue(FIELD_BIRTH, shareUserRecord.birth);
            }
            int update = updateBuilder.update();
            instance.release();
            return update;
        } catch (Exception e) {
            MyLog.a((Throwable) e);
            instance.release();
            return 0;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }
}
