package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.RoomStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.util.List;

public class MIOTRoomManageModule extends MIOTBaseJavaModule {
    private static final String MODULE_NAME = "MHRoom";

    public String getName() {
        return MODULE_NAME;
    }

    public MIOTRoomManageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getRoomList(Callback callback) {
        if (callback != null) {
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, "current device is null...");
            } else if (!isRock(device.model)) {
                callback.invoke(false, "current device model is " + device.model + ", can not support getRoomList...");
            } else {
                List<RoomStat> roomAll = XmPluginHostApi.instance().getRoomAll();
                WritableArray createArray = Arguments.createArray();
                if (roomAll != null && roomAll.size() > 0) {
                    int size = roomAll.size();
                    for (int i = 0; i < size; i++) {
                        createArray.pushMap(roomStateToMap(roomAll.get(i)));
                    }
                }
                callback.invoke(true, createArray);
            }
        }
    }

    @ReactMethod
    public void editRoom(ReadableMap readableMap, final Callback callback) {
        if (callback != null) {
            if (readableMap == null) {
                callback.invoke(false, "params is null");
                return;
            }
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, "current device is null...");
            } else if (!isRock(device.model)) {
                callback.invoke(false, "current device model is " + device.model + ", can not support editRoom...");
            } else {
                String string = readableMap.getString("roomId");
                String string2 = readableMap.getString("name");
                if (TextUtils.isEmpty(string)) {
                    callback.invoke(false, "roomId is null or empty...");
                    return;
                }
                XmPluginHostApi.instance().renameRoom(string, string2, new com.xiaomi.smarthome.device.api.Callback<Void>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        callback.invoke(true, "edit room success");
                    }

                    public void onFailure(int i, String str) {
                        callback.invoke(false, str);
                    }
                });
            }
        }
    }

    @ReactMethod
    public void addNewRoomWithName(String str, final Callback callback) {
        if (callback != null) {
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, "current device is null...");
            } else if (!isRock(device.model)) {
                callback.invoke(false, "current device model is " + device.model + ", can not support addNewRoom...");
            } else {
                RoomStat roomStat = new RoomStat();
                roomStat.name = str;
                XmPluginHostApi.instance().addRoom(roomStat, new com.xiaomi.smarthome.device.api.Callback<RoomStat>() {
                    /* renamed from: a */
                    public void onSuccess(RoomStat roomStat) {
                        callback.invoke(true, MIOTRoomManageModule.this.roomStateToMap(roomStat));
                    }

                    public void onFailure(int i, String str) {
                        callback.invoke(false, str);
                    }
                });
            }
        }
    }

    private boolean isRock(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("roborock.vacuum.") || str.startsWith("rockrobo.vacuum.") || str.startsWith("viomi.vacuum.") || str.equals("roborock.vacuum.s5") || str.equals("roborock.sweeper.s5v3") || str.equals("roborock.sweeper.s5v2")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public WritableMap roomStateToMap(RoomStat roomStat) {
        WritableMap createMap = Arguments.createMap();
        if (roomStat == null) {
            return createMap;
        }
        createMap.putString("homeId", roomStat.parentid);
        createMap.putString("name", roomStat.name);
        createMap.putString("roomId", roomStat.id);
        createMap.putString("shareFlag", "" + roomStat.shareflag);
        return createMap;
    }
}
