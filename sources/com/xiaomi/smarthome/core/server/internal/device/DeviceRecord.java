package com.xiaomi.smarthome.core.server.internal.device;

import android.database.Cursor;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.xiaomi.smarthome.core.server.CoreService;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DeviceRecord {
    private static final String FIELD_BSSID = "bssid";
    private static final String FIELD_CANAUTH = "canAuth";
    private static final String FIELD_CANUSENOTBIND = "canUseNotBind";
    private static final String FIELD_DESC = "desc";
    private static final String FIELD_DESC_NEW = "descNew";
    private static final String FIELD_DESC_TIME_JSTRING = "descTimeJString";
    private static final String FIELD_DID = "did";
    private static final String FIELD_EVENTINFO = "eventInfo";
    private static final String FIELD_EXTRAINFO = "extraInfo";
    private static final String FIELD_INDEX = "index";
    private static final String FIELD_ISONLINE = "isOnline";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LOCALIP = "localIP";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_LONGITUDE = "longitude";
    private static final String FIELD_MAC = "mac";
    private static final String FIELD_METHODINFO = "methodInfo";
    private static final String FIELD_MODEL = "model";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_OWNERID = "ownerId";
    private static final String FIELD_OWNERNAME = "ownerName";
    private static final String FIELD_PARENTID = "parentId";
    private static final String FIELD_PARENTMODEL = "parentModel";
    private static final String FIELD_PERMITLEVEL = "permitLevel";
    private static final String FIELD_PID = "pid";
    private static final String FIELD_PROPINFO = "propInfo";
    private static final String FIELD_RESETFLAG = "resetFlag";
    private static final String FIELD_RSSI = "rssi";
    private static final String FIELD_SHOWMODE = "showMode";
    private static final String FIELD_SPEC_URN = "specUrn";
    private static final String FIELD_SSID = "ssid";
    private static final String FIELD_TOKEN = "token";
    private static final String FIELD_USER_ID = "userId";
    private static final String FIELD_VERSION = "version";
    private static final String FIELD_VOICE_CTRL = "voiceCtrl";
    private static int INDEX_bssid;
    private static int INDEX_canAuth;
    private static int INDEX_canUseNotBind;
    private static int INDEX_desc;
    private static int INDEX_descNew;
    private static int INDEX_descTimeJString;
    private static int INDEX_did;
    private static int INDEX_eventInfo;
    private static int INDEX_extraInfo;
    private static int INDEX_index;
    private static int INDEX_isOnline;
    private static int INDEX_latitude;
    private static int INDEX_localIP;
    private static int INDEX_location;
    private static int INDEX_longitude;
    private static int INDEX_mac;
    private static int INDEX_methodInfo;
    private static int INDEX_model;
    private static int INDEX_name;
    private static int INDEX_ownerId;
    private static int INDEX_ownerName;
    private static int INDEX_parentId;
    private static int INDEX_parentModel;
    private static int INDEX_permitLevel;
    private static int INDEX_pid;
    private static int INDEX_propInfo;
    private static int INDEX_resetFlag;
    private static int INDEX_rssi;
    private static int INDEX_showMode;
    private static int INDEX_specUrn;
    private static int INDEX_ssid;
    private static int INDEX_token;
    private static int INDEX_userId;
    private static int INDEX_version;
    private static int INDEX_voiceCtrl;
    private static boolean indexInited;
    @DatabaseField
    public String bssid;
    @DatabaseField
    public int canAuth;
    @DatabaseField
    public int canUseNotBind;
    @DatabaseField
    public String desc;
    @DatabaseField
    public String descNew;
    @DatabaseField
    public String descTimeJString;
    @DatabaseField
    public String did;
    @DatabaseField
    public String eventInfo;
    @DatabaseField
    public String extraInfo;
    @DatabaseField(generatedId = true)
    public int index;
    @DatabaseField
    public int isOnline;
    @DatabaseField
    public double latitude;
    @DatabaseField
    public String localIP;
    @DatabaseField
    public int location;
    @DatabaseField
    public double longitude;
    @DatabaseField
    public String mac;
    @DatabaseField
    public String methodInfo;
    @DatabaseField
    public String model;
    @DatabaseField
    public String name;
    @DatabaseField
    public String ownerId;
    @DatabaseField
    public String ownerName;
    @DatabaseField
    public String parentId;
    @DatabaseField
    public String parentModel;
    @DatabaseField
    public int permitLevel;
    @DatabaseField
    public int pid;
    @DatabaseField
    public String propInfo;
    @DatabaseField
    public int resetFlag;
    @DatabaseField
    public int rssi;
    @DatabaseField
    public int showMode;
    @DatabaseField
    public String specUrn;
    @DatabaseField
    public String ssid;
    @DatabaseField
    public String token;
    @DatabaseField
    public String userId;
    @DatabaseField
    public String version;
    @DatabaseField
    public int voiceCtrl;

    public static boolean insert(DeviceRecord deviceRecord) {
        try {
            Dao<DeviceRecord, Integer> deviceRecordDao = MiioDBHelper.getsInstance(CoreService.getAppContext()).getDeviceRecordDao();
            QueryBuilder<DeviceRecord, Integer> queryBuilder = deviceRecordDao.queryBuilder();
            Where<DeviceRecord, Integer> where = queryBuilder.where();
            where.eq("userId", deviceRecord.userId);
            where.and();
            where.eq("did", deviceRecord.did);
            List<DeviceRecord> query = queryBuilder.query();
            if (query != null) {
                deviceRecordDao.delete((Collection<DeviceRecord>) query);
            }
            deviceRecordDao.create(deviceRecord);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteByUserId(String str) {
        try {
            Dao<DeviceRecord, Integer> deviceRecordDao = MiioDBHelper.getsInstance(CoreService.getAppContext()).getDeviceRecordDao();
            DeleteBuilder<DeviceRecord, Integer> deleteBuilder = deviceRecordDao.deleteBuilder();
            deleteBuilder.where().eq("userId", str);
            deviceRecordDao.delete(deleteBuilder.prepare());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.xiaomi.smarthome.core.server.internal.device.DeviceRecord> queryAllByUserId(java.lang.String r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "select * from DeviceRecord where userId = ?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0048 }
            r4 = 0
            r3[r4] = r6     // Catch:{ Exception -> 0x0048 }
            android.content.Context r6 = com.xiaomi.smarthome.core.server.CoreService.getAppContext()     // Catch:{ Exception -> 0x0048 }
            com.xiaomi.smarthome.core.server.internal.device.MiioDBHelper r6 = com.xiaomi.smarthome.core.server.internal.device.MiioDBHelper.getsInstance(r6)     // Catch:{ Exception -> 0x0048 }
            android.database.sqlite.SQLiteDatabase r6 = r6.getReadableDatabase()     // Catch:{ Exception -> 0x0048 }
            android.database.Cursor r6 = r6.rawQuery(r2, r3)     // Catch:{ Exception -> 0x0048 }
            if (r6 == 0) goto L_0x0040
            boolean r1 = r6.moveToFirst()     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            if (r1 == 0) goto L_0x0040
        L_0x0026:
            com.xiaomi.smarthome.core.server.internal.device.DeviceRecord r1 = readCursor(r6)     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            if (r1 == 0) goto L_0x002f
            r0.add(r1)     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
        L_0x002f:
            if (r6 == 0) goto L_0x0040
            boolean r1 = r6.moveToNext()     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            if (r1 != 0) goto L_0x0026
            goto L_0x0040
        L_0x0038:
            r0 = move-exception
            r1 = r6
            goto L_0x0059
        L_0x003b:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0049
        L_0x0040:
            if (r6 == 0) goto L_0x0051
            r6.close()
            goto L_0x0051
        L_0x0046:
            r0 = move-exception
            goto L_0x0059
        L_0x0048:
            r6 = move-exception
        L_0x0049:
            r6.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            java.lang.String r6 = "ABC"
            java.lang.String r1 = "query finish"
            android.util.Log.d(r6, r1)
            return r0
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceRecord.queryAllByUserId(java.lang.String):java.util.List");
    }

    private static DeviceRecord readCursor(Cursor cursor) {
        if (!indexInited) {
            INDEX_index = cursor.getColumnIndex("index");
            INDEX_userId = cursor.getColumnIndex("userId");
            INDEX_did = cursor.getColumnIndex("did");
            INDEX_model = cursor.getColumnIndex("model");
            INDEX_name = cursor.getColumnIndex("name");
            INDEX_mac = cursor.getColumnIndex("mac");
            INDEX_isOnline = cursor.getColumnIndex(FIELD_ISONLINE);
            INDEX_pid = cursor.getColumnIndex("pid");
            INDEX_permitLevel = cursor.getColumnIndex(FIELD_PERMITLEVEL);
            INDEX_resetFlag = cursor.getColumnIndex(FIELD_RESETFLAG);
            INDEX_rssi = cursor.getColumnIndex(FIELD_RSSI);
            INDEX_token = cursor.getColumnIndex("token");
            INDEX_localIP = cursor.getColumnIndex(FIELD_LOCALIP);
            INDEX_longitude = cursor.getColumnIndex("longitude");
            INDEX_latitude = cursor.getColumnIndex("latitude");
            INDEX_ssid = cursor.getColumnIndex("ssid");
            INDEX_bssid = cursor.getColumnIndex("bssid");
            INDEX_showMode = cursor.getColumnIndex("showMode");
            INDEX_desc = cursor.getColumnIndex("desc");
            INDEX_parentId = cursor.getColumnIndex(FIELD_PARENTID);
            INDEX_parentModel = cursor.getColumnIndex(FIELD_PARENTMODEL);
            INDEX_ownerName = cursor.getColumnIndex(FIELD_OWNERNAME);
            INDEX_ownerId = cursor.getColumnIndex(FIELD_OWNERID);
            INDEX_propInfo = cursor.getColumnIndex(FIELD_PROPINFO);
            INDEX_methodInfo = cursor.getColumnIndex(FIELD_METHODINFO);
            INDEX_extraInfo = cursor.getColumnIndex("extraInfo");
            INDEX_eventInfo = cursor.getColumnIndex(FIELD_EVENTINFO);
            INDEX_location = cursor.getColumnIndex("location");
            INDEX_version = cursor.getColumnIndex("version");
            INDEX_canUseNotBind = cursor.getColumnIndex(FIELD_CANUSENOTBIND);
            INDEX_canAuth = cursor.getColumnIndex(FIELD_CANAUTH);
            INDEX_descNew = cursor.getColumnIndex(FIELD_DESC_NEW);
            INDEX_descTimeJString = cursor.getColumnIndex(FIELD_DESC_TIME_JSTRING);
            INDEX_specUrn = cursor.getColumnIndex(FIELD_SPEC_URN);
            INDEX_voiceCtrl = cursor.getColumnIndex(FIELD_VOICE_CTRL);
            indexInited = true;
        }
        DeviceRecord deviceRecord = new DeviceRecord();
        deviceRecord.index = cursor.getInt(INDEX_index);
        deviceRecord.userId = cursor.getString(INDEX_userId);
        deviceRecord.did = cursor.getString(INDEX_did);
        deviceRecord.model = cursor.getString(INDEX_model);
        deviceRecord.name = cursor.getString(INDEX_name);
        deviceRecord.mac = cursor.getString(INDEX_mac);
        deviceRecord.isOnline = cursor.getInt(INDEX_isOnline);
        deviceRecord.pid = cursor.getInt(INDEX_pid);
        deviceRecord.permitLevel = cursor.getInt(INDEX_permitLevel);
        deviceRecord.resetFlag = cursor.getInt(INDEX_resetFlag);
        deviceRecord.rssi = cursor.getInt(INDEX_rssi);
        deviceRecord.token = cursor.getString(INDEX_token);
        deviceRecord.localIP = cursor.getString(INDEX_localIP);
        deviceRecord.longitude = cursor.getDouble(INDEX_longitude);
        deviceRecord.latitude = cursor.getDouble(INDEX_latitude);
        deviceRecord.ssid = cursor.getString(INDEX_ssid);
        deviceRecord.bssid = cursor.getString(INDEX_bssid);
        deviceRecord.showMode = cursor.getInt(INDEX_showMode);
        deviceRecord.desc = cursor.getString(INDEX_desc);
        deviceRecord.parentId = cursor.getString(INDEX_parentId);
        deviceRecord.parentModel = cursor.getString(INDEX_parentModel);
        deviceRecord.ownerName = cursor.getString(INDEX_ownerName);
        deviceRecord.ownerId = cursor.getString(INDEX_ownerId);
        deviceRecord.propInfo = cursor.getString(INDEX_propInfo);
        deviceRecord.methodInfo = cursor.getString(INDEX_methodInfo);
        deviceRecord.extraInfo = cursor.getString(INDEX_extraInfo);
        deviceRecord.eventInfo = cursor.getString(INDEX_eventInfo);
        deviceRecord.location = cursor.getInt(INDEX_location);
        deviceRecord.version = cursor.getString(INDEX_version);
        deviceRecord.canUseNotBind = cursor.getInt(INDEX_canUseNotBind);
        deviceRecord.canAuth = cursor.getInt(INDEX_canAuth);
        deviceRecord.descNew = cursor.getString(INDEX_descNew);
        deviceRecord.descTimeJString = cursor.getString(INDEX_descTimeJString);
        deviceRecord.specUrn = cursor.getString(INDEX_specUrn);
        deviceRecord.voiceCtrl = cursor.getInt(INDEX_voiceCtrl);
        return deviceRecord;
    }
}
