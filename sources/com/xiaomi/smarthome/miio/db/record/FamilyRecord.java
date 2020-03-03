package com.xiaomi.smarthome.miio.db.record;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.db.MiioDBHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyRecord {
    public static final String FIELD_ID = "id";
    public static final String FIELD_NICKNAME = "nickName";
    public static final String FIELD_RELATION = "relationship";
    public static final String FIELD_RELATION_ID = "relation_id";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_TARGET_USER_ID = "tUserId";
    public static final String FIELD_URL = "url";
    public static final String FIELD_USER_ID = "userId";
    @DatabaseField(generatedId = false)
    public int id;
    @DatabaseField
    public String nickName;
    @DatabaseField
    public String relation_id;
    @DatabaseField
    public String relationship;
    @DatabaseField
    public int shareDeviceCount;
    @DatabaseField
    public String status;
    @DatabaseField
    public String tUserId;
    @DatabaseField
    public String url;
    @DatabaseField
    public String userId;

    public static boolean insert(FamilyRecord familyRecord) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        boolean z = false;
        try {
            instance.getFamilyRecordDao().create(familyRecord);
            z = true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return z;
    }

    public boolean update() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<FamilyRecord, Integer> familyRecordDao = instance.getFamilyRecordDao();
            QueryBuilder<FamilyRecord, Integer> queryBuilder = familyRecordDao.queryBuilder();
            queryBuilder.where().eq("id", Integer.valueOf(this.id));
            FamilyRecord queryForFirst = queryBuilder.queryForFirst();
            if (queryForFirst != null) {
                familyRecordDao.delete(queryForFirst);
            }
            familyRecordDao.create(this);
            instance.release();
            return true;
        } catch (SQLException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return false;
    }

    public static List<FamilyRecord> queryAll() {
        ArrayList arrayList = new ArrayList();
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            QueryBuilder<FamilyRecord, Integer> queryBuilder = instance.getFamilyRecordDao().queryBuilder();
            queryBuilder.where().eq("userId", CoreApi.a().s());
            List<FamilyRecord> query = queryBuilder.query();
            instance.release();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return arrayList;
    }

    public static List<FamilyRecord> query(String str) {
        ArrayList arrayList = new ArrayList();
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            QueryBuilder<FamilyRecord, Integer> queryBuilder = instance.getFamilyRecordDao().queryBuilder();
            Where<FamilyRecord, Integer> where = queryBuilder.where();
            where.eq("userId", CoreApi.a().s());
            where.and();
            where.eq(FIELD_TARGET_USER_ID, str);
            List<FamilyRecord> query = queryBuilder.query();
            instance.release();
            return query;
        } catch (SQLException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return arrayList;
    }

    public static boolean remove(String str, String str2, String str3) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<FamilyRecord, Integer> familyRecordDao = instance.getFamilyRecordDao();
            DeleteBuilder<FamilyRecord, Integer> deleteBuilder = familyRecordDao.deleteBuilder();
            Where<FamilyRecord, Integer> where = deleteBuilder.where();
            where.eq("userId", str);
            where.and();
            where.eq(FIELD_TARGET_USER_ID, str2);
            familyRecordDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (SQLException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return false;
    }

    public static boolean deleteAll() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<FamilyRecord, Integer> familyRecordDao = instance.getFamilyRecordDao();
            familyRecordDao.delete(familyRecordDao.deleteBuilder().prepare());
            instance.release();
            return true;
        } catch (SQLException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
        instance.release();
        return false;
    }
}
