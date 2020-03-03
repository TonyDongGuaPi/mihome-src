package com.xiaomi.smarthome.miio.db.record;

import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.db.MiioDBHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MessageRecord implements Parcelable {
    public static final Parcelable.Creator<MessageRecord> CREATOR = new Parcelable.Creator<MessageRecord>() {
        public MessageRecord createFromParcel(Parcel parcel) {
            return new MessageRecord(parcel);
        }

        public MessageRecord[] newArray(int i) {
            return new MessageRecord[i];
        }
    };
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

    public int describeContents() {
        return 0;
    }

    public static boolean insert(MessageRecord messageRecord) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            messageRecord.userId = CoreApi.a().s();
            messageRecordDao.create(messageRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            instance.release();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean update() {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            DeleteBuilder<MessageRecord, Integer> deleteBuilder = messageRecordDao.deleteBuilder();
            deleteBuilder.where().eq("msgId", this.msgId);
            messageRecordDao.delete(deleteBuilder.prepare());
            messageRecordDao.create(this);
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            QueryBuilder<MessageRecord, Integer> queryBuilder = messageRecordDao.queryBuilder();
            queryBuilder.where().eq("id", Integer.valueOf(this.id));
            MessageRecord queryForFirst = queryBuilder.queryForFirst();
            if (queryForFirst != null) {
                messageRecordDao.delete(queryForFirst);
            }
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    public static List<MessageRecord> queryAll() {
        ArrayList arrayList = new ArrayList();
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        try {
            QueryBuilder<MessageRecord, Integer> queryBuilder = instance.getMessageRecordDao().queryBuilder();
            queryBuilder.where().eq("userId", CoreApi.a().s());
            List<MessageRecord> query = queryBuilder.query();
            instance.release();
            return query;
        } catch (Exception e) {
            e.printStackTrace();
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
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            DeleteBuilder<MessageRecord, Integer> deleteBuilder = messageRecordDao.deleteBuilder();
            deleteBuilder.where().eq("userId", str);
            messageRecordDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            DeleteBuilder<MessageRecord, Integer> deleteBuilder = messageRecordDao.deleteBuilder();
            deleteBuilder.where().eq("msgId", str);
            messageRecordDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean batchInsert(List<MessageRecord> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            String s = CoreApi.a().s();
            for (MessageRecord next : list) {
                next.userId = s;
                messageRecordDao.create(next);
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
    public static boolean batchDelete(List<String> list) {
        MiioDBHelper instance = MiioDBHelper.getInstance(SHApplication.getAppContext());
        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            for (String eq : list) {
                DeleteBuilder<MessageRecord, Integer> deleteBuilder = messageRecordDao.deleteBuilder();
                deleteBuilder.where().eq("userId", CoreApi.a().s()).and().eq("msgId", eq);
                messageRecordDao.delete(deleteBuilder.prepare());
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            Dao<MessageRecord, Integer> messageRecordDao = instance.getMessageRecordDao();
            DeleteBuilder<MessageRecord, Integer> deleteBuilder = messageRecordDao.deleteBuilder();
            deleteBuilder.where().eq("userId", CoreApi.a().s());
            messageRecordDao.delete(deleteBuilder.prepare());
            instance.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            instance.release();
            return false;
        } catch (Throwable th) {
            instance.release();
            throw th;
        }
    }

    public static boolean parse(JSONObject jSONObject, MessageRecord messageRecord) {
        JSONObject optJSONObject;
        messageRecord.userId = jSONObject.optString("uid");
        messageRecord.msgId = jSONObject.optString("msg_id");
        messageRecord.senderUserId = jSONObject.optString("sender_uid");
        messageRecord.messageType = jSONObject.optString("type");
        messageRecord.receiveTime = Long.valueOf(jSONObject.optString("last_modify")).longValue();
        messageRecord.title = jSONObject.optString("title");
        messageRecord.content = jSONObject.optString("content");
        messageRecord.img_url = jSONObject.optString("img_url");
        if (jSONObject.isNull("params") || (optJSONObject = jSONObject.optJSONObject("params")) == null) {
            return false;
        }
        messageRecord.params = optJSONObject.toString();
        messageRecord.valid = jSONObject.optString(TSMAuthContants.PARAM_VALID);
        messageRecord.is_new = jSONObject.optString("is_new");
        messageRecord.is_read = jSONObject.optString("is_read");
        if (jSONObject.has("status")) {
            messageRecord.status = jSONObject.optInt("status");
        }
        messageRecord.homeId = jSONObject.optLong("home_id", 0);
        messageRecord.homeOwner = jSONObject.optLong("home_owner", 0);
        return true;
    }

    public MessageRecord() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.msgId);
        parcel.writeString(this.userId);
        parcel.writeString(this.senderUserId);
        parcel.writeString(this.messageType);
        parcel.writeLong(this.receiveTime);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeString(this.img_url);
        parcel.writeString(this.params);
        parcel.writeString(this.valid);
        parcel.writeString(this.result);
        parcel.writeString(this.is_new);
        parcel.writeString(this.is_read);
        parcel.writeInt(this.status);
        parcel.writeLong(this.homeId);
        parcel.writeLong(this.homeOwner);
    }

    protected MessageRecord(Parcel parcel) {
        this.id = parcel.readInt();
        this.msgId = parcel.readString();
        this.userId = parcel.readString();
        this.senderUserId = parcel.readString();
        this.messageType = parcel.readString();
        this.receiveTime = parcel.readLong();
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.img_url = parcel.readString();
        this.params = parcel.readString();
        this.valid = parcel.readString();
        this.result = parcel.readString();
        this.is_new = parcel.readString();
        this.is_read = parcel.readString();
        this.status = parcel.readInt();
        this.homeId = parcel.readLong();
        this.homeOwner = parcel.readLong();
    }
}
