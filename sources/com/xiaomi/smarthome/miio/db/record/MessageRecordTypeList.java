package com.xiaomi.smarthome.miio.db.record;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.db.TypeListMsgDBHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MessageRecordTypeList {
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
    @DatabaseField
    public long homeId;
    @DatabaseField
    public long homeOwner;
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
    @DatabaseField(index = true)
    public String msgId;
    @DatabaseField
    public String params;
    @DatabaseField(index = true)
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

    public static boolean insert(MessageRecordTypeList messageRecordTypeList) {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            messageRecordTypeList.userId = CoreApi.a().s();
            b.create(messageRecordTypeList);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            a2.a();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean update() {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            DeleteBuilder<MessageRecordTypeList, Integer> deleteBuilder = b.deleteBuilder();
            deleteBuilder.where().eq("msgId", this.msgId);
            b.delete(deleteBuilder.prepare());
            b.create(this);
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return false;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean deleteThis() {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            QueryBuilder<MessageRecordTypeList, Integer> queryBuilder = b.queryBuilder();
            queryBuilder.where().eq("id", Integer.valueOf(this.id));
            MessageRecordTypeList queryForFirst = queryBuilder.queryForFirst();
            if (queryForFirst != null) {
                b.delete(queryForFirst);
            }
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return false;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    public static List<MessageRecordTypeList> queryAll() {
        ArrayList arrayList = new ArrayList();
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            QueryBuilder<MessageRecordTypeList, Integer> queryBuilder = a2.b().queryBuilder();
            queryBuilder.where().eq("userId", CoreApi.a().s());
            List<MessageRecordTypeList> query = queryBuilder.query();
            a2.a();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return arrayList;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static List<MessageRecordTypeList> queryAllByRange(long j, long j2) {
        ArrayList arrayList = new ArrayList();
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            QueryBuilder<MessageRecordTypeList, Integer> queryBuilder = a2.b().queryBuilder();
            Where<MessageRecordTypeList, Integer> where = queryBuilder.where();
            where.eq("userId", CoreApi.a().s());
            where.and().between("receiveTime", Long.valueOf(Math.min(j, j2)), Long.valueOf(Math.max(j, j2)));
            queryBuilder.orderBy("receiveTime", false);
            List<MessageRecordTypeList> query = queryBuilder.query();
            a2.a();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return arrayList;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static List<MessageRecordTypeList> queryAllByRange(long j, long j2, int i) {
        ArrayList arrayList = new ArrayList();
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            QueryBuilder<MessageRecordTypeList, Integer> queryBuilder = a2.b().queryBuilder();
            Where<MessageRecordTypeList, Integer> where = queryBuilder.where();
            where.eq("userId", CoreApi.a().s());
            where.and().between("receiveTime", Long.valueOf(Math.min(j, j2)), Long.valueOf(Math.max(j, j2)));
            queryBuilder.limit(Long.valueOf((long) i));
            queryBuilder.orderBy("receiveTime", false);
            List<MessageRecordTypeList> query = queryBuilder.query();
            a2.a();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return arrayList;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean remove(String str, String str2, String str3) {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            DeleteBuilder<MessageRecordTypeList, Integer> deleteBuilder = b.deleteBuilder();
            deleteBuilder.where().eq("userId", str);
            b.delete(deleteBuilder.prepare());
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return false;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean delete(String str) {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            DeleteBuilder<MessageRecordTypeList, Integer> deleteBuilder = b.deleteBuilder();
            deleteBuilder.where().eq("msgId", str);
            b.delete(deleteBuilder.prepare());
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return false;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchInsert(List<MessageRecordTypeList> list) {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = a2.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            String s = CoreApi.a().s();
            for (MessageRecordTypeList next : list) {
                next.userId = s;
                b.create(next);
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            writableDatabase.endTransaction();
            a2.a();
            return false;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchDelete(List<String> list) {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = a2.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            for (String eq : list) {
                DeleteBuilder<MessageRecordTypeList, Integer> deleteBuilder = b.deleteBuilder();
                deleteBuilder.where().eq("userId", CoreApi.a().s()).and().eq("msgId", eq);
                b.delete(deleteBuilder.prepare());
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            writableDatabase.endTransaction();
            a2.a();
            return false;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            a2.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean deleteAll() {
        TypeListMsgDBHelper a2 = TypeListMsgDBHelper.a(SHApplication.getAppContext());
        try {
            Dao<MessageRecordTypeList, Integer> b = a2.b();
            DeleteBuilder<MessageRecordTypeList, Integer> deleteBuilder = b.deleteBuilder();
            deleteBuilder.where().eq("userId", CoreApi.a().s());
            b.delete(deleteBuilder.prepare());
            a2.a();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            a2.a();
            return false;
        } catch (Throwable th) {
            a2.a();
            throw th;
        }
    }

    public static MessageRecordTypeList copy(MessageRecord messageRecord) {
        MessageRecordTypeList messageRecordTypeList = new MessageRecordTypeList();
        messageRecordTypeList.msgId = messageRecord.msgId;
        messageRecordTypeList.userId = messageRecord.userId;
        messageRecordTypeList.senderUserId = messageRecord.senderUserId;
        messageRecordTypeList.messageType = messageRecord.messageType;
        messageRecordTypeList.receiveTime = messageRecord.receiveTime;
        messageRecordTypeList.title = messageRecord.title;
        messageRecordTypeList.content = messageRecord.content;
        messageRecordTypeList.img_url = messageRecord.img_url;
        messageRecordTypeList.params = messageRecord.params;
        messageRecordTypeList.valid = messageRecord.valid;
        messageRecordTypeList.result = messageRecord.result;
        messageRecordTypeList.is_new = messageRecord.is_new;
        messageRecordTypeList.is_read = messageRecord.is_read;
        messageRecordTypeList.status = messageRecord.status;
        return messageRecordTypeList;
    }

    public MessageRecord toMsgRecord() {
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.msgId = this.msgId;
        messageRecord.userId = this.userId;
        messageRecord.senderUserId = this.senderUserId;
        messageRecord.messageType = this.messageType;
        messageRecord.receiveTime = this.receiveTime;
        messageRecord.title = this.title;
        messageRecord.content = this.content;
        messageRecord.img_url = this.img_url;
        messageRecord.params = this.params;
        messageRecord.valid = this.valid;
        messageRecord.result = this.result;
        messageRecord.is_new = this.is_new;
        messageRecord.is_read = this.is_read;
        messageRecord.status = this.status;
        messageRecord.homeId = this.homeId;
        messageRecord.homeOwner = this.homeOwner;
        return messageRecord;
    }

    public static MessageRecordTypeList parseMessageRecord(JSONObject jSONObject) {
        JSONObject optJSONObject;
        MessageRecordTypeList messageRecordTypeList = new MessageRecordTypeList();
        messageRecordTypeList.userId = jSONObject.optString("uid");
        messageRecordTypeList.msgId = jSONObject.optString("msg_id");
        messageRecordTypeList.senderUserId = jSONObject.optString("sender_uid");
        messageRecordTypeList.messageType = jSONObject.optString("type");
        messageRecordTypeList.receiveTime = Long.valueOf(jSONObject.optString("last_modify")).longValue();
        messageRecordTypeList.title = jSONObject.optString("title");
        messageRecordTypeList.content = jSONObject.optString("content");
        messageRecordTypeList.img_url = jSONObject.optString("img_url");
        messageRecordTypeList.valid = jSONObject.optString(TSMAuthContants.PARAM_VALID);
        messageRecordTypeList.is_new = jSONObject.optString("is_new");
        messageRecordTypeList.is_read = jSONObject.optString("is_read");
        if (jSONObject.has("status")) {
            messageRecordTypeList.status = jSONObject.optInt("status");
        }
        if (!jSONObject.isNull("params") && (optJSONObject = jSONObject.optJSONObject("params")) != null) {
            messageRecordTypeList.params = optJSONObject.toString();
        }
        messageRecordTypeList.homeId = jSONObject.optLong("home_id", 0);
        messageRecordTypeList.homeOwner = jSONObject.optLong("home_owner", 0);
        return messageRecordTypeList;
    }
}
