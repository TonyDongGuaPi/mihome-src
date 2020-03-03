package com.xiaomi.smarthome.miio.db.record;

import android.database.sqlite.SQLiteDatabase;
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

public class MessageRecordShop {
    public static final String FIELD_DEVICENAME = "deviceName";
    public static final String FIELD_ID = "id";
    public static final String FIELD_MESSAGETYPE = "messageType";
    public static final String FIELD_MSG_ID = "msgId";
    public static final String FIELD_RECEIVE_TIME = "receiveTime";
    public static final String FIELD_RESULT = "result";
    public static final String FIELD_SEND_USER_ID = "senderUserId";
    public static final String FIELD_USER_ID = "userId";
    @DatabaseField
    public String content;
    @DatabaseField(generatedId = false)
    public int id;
    @DatabaseField
    public String img_url;
    @DatabaseField
    public String is_new;
    @DatabaseField
    public String is_read;
    @DatabaseField
    public String messageType;
    @DatabaseField
    public String msgId;
    @DatabaseField
    public String params;
    @DatabaseField
    public long receiveTime;
    @DatabaseField
    public String result;
    @DatabaseField
    public String senderUserId;
    @DatabaseField
    public int status;
    @DatabaseField
    public String title;
    @DatabaseField
    public String userId;
    @DatabaseField
    public String valid;

    public static boolean insert(MessageRecordShop messageRecordShop) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            messageRecordShop.userId = CoreApi.a().s();
            messageRecordShopDao.create(messageRecordShop);
            return true;
        } catch (SQLException unused) {
            return false;
        } finally {
            instance.release();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean update() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            DeleteBuilder<MessageRecordShop, Integer> deleteBuilder = messageRecordShopDao.deleteBuilder();
            deleteBuilder.where().eq("msgId", this.msgId);
            messageRecordShopDao.delete(deleteBuilder.prepare());
            messageRecordShopDao.create(this);
            instance.release();
            return true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean deleteThis() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            QueryBuilder<MessageRecordShop, Integer> queryBuilder = messageRecordShopDao.queryBuilder();
            queryBuilder.where().eq("id", Integer.valueOf(this.id));
            MessageRecordShop queryForFirst = queryBuilder.queryForFirst();
            if (queryForFirst != null) {
                messageRecordShopDao.delete(queryForFirst);
            }
            instance.release();
            return true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    public static List<MessageRecordShop> queryAll() {
        ArrayList arrayList = new ArrayList();
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            QueryBuilder<MessageRecordShop, Integer> queryBuilder = instance.getMessageRecordShopDao().queryBuilder();
            queryBuilder.where().eq("userId", CoreApi.a().s());
            List<MessageRecordShop> query = queryBuilder.query();
            instance.release();
            return query;
        } catch (SQLException unused) {
            instance.release();
            return arrayList;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean remove(String str, String str2, String str3) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            DeleteBuilder<MessageRecordShop, Integer> deleteBuilder = messageRecordShopDao.deleteBuilder();
            deleteBuilder.where().eq("userId", str);
            messageRecordShopDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean delete(String str) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            DeleteBuilder<MessageRecordShop, Integer> deleteBuilder = messageRecordShopDao.deleteBuilder();
            deleteBuilder.where().eq("msgId", str);
            messageRecordShopDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchInsert(List<MessageRecordShop> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            String s = CoreApi.a().s();
            for (MessageRecordShop next : list) {
                next.userId = s;
                messageRecordShopDao.create(next);
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

    /* JADX INFO: finally extract failed */
    public static boolean batchDelete(List<Integer> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            for (Integer intValue : list) {
                DeleteBuilder<MessageRecordShop, Integer> deleteBuilder = messageRecordShopDao.deleteBuilder();
                Where<MessageRecordShop, Integer> and = deleteBuilder.where().eq("userId", CoreApi.a().s()).and();
                and.eq("msgId", intValue.intValue() + "");
                messageRecordShopDao.delete(deleteBuilder.prepare());
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

    /* JADX INFO: finally extract failed */
    public static boolean deleteAll() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecordShop, Integer> messageRecordShopDao = instance.getMessageRecordShopDao();
            DeleteBuilder<MessageRecordShop, Integer> deleteBuilder = messageRecordShopDao.deleteBuilder();
            deleteBuilder.where().eq("userId", CoreApi.a().s());
            messageRecordShopDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (SQLException unused) {
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }
}
