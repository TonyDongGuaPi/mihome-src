package com.xiaomi.smarthome.device;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.router.miio.miioplugin.DeviceStatus;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthomedevice.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class Device implements Cloneable {
    public static final int BIND_TIMEINTERVAL_LEN = 1000;
    protected static final int BIND_TIMES = 30;
    public static final String EVENT_PREFIX = "event.";
    private static int PERMISSION_AGGREGATE = 64;
    private static int PERMISSION_FAMILY = 8;
    private static int PERMISSION_NONE = 65296;
    private static int PERMISSION_NONE_MASK = 30;
    private static int PERMISSION_OWNER = 16;
    private static int PERMISSION_SHARE = 4;
    private static int PERMISSION_SHARE_READONLY = 32;
    public static int PID_BLE_MESH = 16;
    public static int PID_BLUETOOTH = 6;
    public static int PID_CLOUD = 2;
    public static int PID_IR_CONTROL = 15;
    public static int PID_MIIO = 0;
    public static int PID_NBIOT = 13;
    public static int PID_SUBDEVICE = 3;
    public static int PID_VIRTUAL_DEVICE = 5;
    public static int PID_VIRTUAL_GROUP = 17;
    public static int PID_WEBSOCKET = 4;
    public static int PID_YUNYI = 1;
    public static final String PROP_PREFIX = "prop.";
    public static int SHOW_HIDDEN = 0;
    public static final int UPDATE_BIND_STATUS = 103;
    public static final int VOICECTRL_OTHER = 1;
    public static final int VOICECTRL_XIAOAI = 2;
    public static final byte VOICE_CTRL_GENERAL_VC = 1;
    public static final byte VOICE_CTRL_NON_VC = 0;
    public static final byte VOICE_CTRL_XIAOAI_VC = 2;
    public String bssid;
    public boolean canAuth = true;
    public boolean canUseNotBind = false;
    public String desc;
    public String descNew;
    public String descTimeJString;
    public String did;
    public String event;
    public String extra;
    public String extraToken = "";
    public String ip;
    public boolean isConnected = true;
    public boolean isNew;
    public boolean isOnline = true;
    public int isSetPinCode;
    public long lastModified;
    public double latitude;
    public Location location;
    public double longitude;
    ArrayList<WeakReference<StateChangedListener>> mStateChangedListeners = new ArrayList<>();
    public String mac;
    public JSONArray method;
    public String model;
    public String name;
    public Runnable notifyChange;
    public String ownerId;
    public String ownerName;
    public String parentId;
    public String parentModel;
    public int permitLevel;
    public int pid;
    public JSONObject propInfo;
    public Bundle property = new Bundle();
    public int resetFlag;
    public int rssi;
    public ScanResult scanResult;
    public boolean scrollTo = false;
    public int showMode;
    public int sort;
    public String specUrn;
    public String ssid;
    public String token;
    public String userId = "";
    public String version;
    public byte voiceCtrl;

    public interface IBindDeviceCallback {
        void a();

        void a(int i);

        void b();

        void c();

        void d();
    }

    public enum Location {
        UNKNOWN,
        LOCAL,
        REMOTE
    }

    public interface StateChangedListener {
        void onStateChanged(Device device);
    }

    public void bindDevice(Context context, IBindDeviceCallback iBindDeviceCallback) {
    }

    public boolean hasShortcut() {
        return true;
    }

    public boolean isOpen() {
        return true;
    }

    public void parseEvent(String str) {
    }

    public void parseExtra(String str) {
    }

    public void parseProp() {
    }

    /* access modifiers changed from: protected */
    public void processUpdateBindStatus(Message message) {
    }

    public void setLaunchParams(Intent intent) {
    }

    public void initialLocal() {
        this.location = Location.REMOTE;
    }

    public CharSequence getStatusDescription(Context context) {
        if (this.isOnline) {
            return context.getString(R.string.list_device_online);
        }
        return context.getString(R.string.list_device_offline);
    }

    public void addStateChangedListener(StateChangedListener stateChangedListener) {
        if (stateChangedListener != null) {
            Iterator<WeakReference<StateChangedListener>> it = this.mStateChangedListeners.iterator();
            while (it.hasNext()) {
                if (it.next().get() == stateChangedListener) {
                    return;
                }
            }
            this.mStateChangedListeners.add(new WeakReference(stateChangedListener));
        }
    }

    public void removeStateChangedListener(StateChangedListener stateChangedListener) {
        if (stateChangedListener != null) {
            for (int i = 0; i < this.mStateChangedListeners.size(); i++) {
                if (this.mStateChangedListeners.get(i).get() == stateChangedListener) {
                    this.mStateChangedListeners.remove(i);
                    return;
                }
            }
        }
    }

    public void subscribeDevice(List<String> list, int i, AsyncCallback<Boolean, Error> asyncCallback) {
        DevicelibApi.subscribeDevice(CommonApplication.getAppContext(), this.did, this.pid, list, i, asyncCallback);
    }

    public void unsubscribeDevice(List<String> list, AsyncCallback<Boolean, Error> asyncCallback) {
        DevicelibApi.unsubscribeDevice(CommonApplication.getAppContext(), this.did, this.pid, list, asyncCallback);
    }

    public void notifyStateChanged() {
        if (this.notifyChange == null) {
            this.notifyChange = new Runnable() {
                public void run() {
                    Device.this.doNotifyStateChanged();
                }
            };
        }
        CommonApplication.getGlobalHandler().removeCallbacks(this.notifyChange);
        CommonApplication.getGlobalHandler().post(this.notifyChange);
    }

    /* access modifiers changed from: private */
    public void doNotifyStateChanged() {
        for (int i = 0; i < this.mStateChangedListeners.size(); i++) {
            StateChangedListener stateChangedListener = (StateChangedListener) this.mStateChangedListeners.get(i).get();
            if (stateChangedListener != null) {
                stateChangedListener.onStateChanged(this);
            }
        }
    }

    public boolean isOwner() {
        return ((this.permitLevel & PERMISSION_NONE_MASK) & PERMISSION_OWNER) != 0;
    }

    public void setOwner(boolean z) {
        this.permitLevel &= PERMISSION_NONE_MASK ^ -1;
        if (z) {
            this.permitLevel |= PERMISSION_OWNER;
        } else {
            this.permitLevel &= PERMISSION_OWNER ^ -1;
        }
    }

    public boolean isFamily() {
        return ((this.permitLevel & PERMISSION_NONE_MASK) & PERMISSION_FAMILY) != 0;
    }

    public void setFamily(boolean z) {
        this.permitLevel &= PERMISSION_NONE_MASK ^ -1;
        if (z) {
            this.permitLevel |= PERMISSION_FAMILY;
        } else {
            this.permitLevel &= PERMISSION_FAMILY ^ -1;
        }
    }

    public void setShared(boolean z) {
        this.permitLevel &= PERMISSION_NONE_MASK ^ -1;
        if (!this.canAuth || !z || TextUtils.isEmpty(this.ownerName)) {
            this.permitLevel &= PERMISSION_SHARE ^ -1;
        } else {
            this.permitLevel |= PERMISSION_SHARE;
        }
    }

    public boolean isShared() {
        return this.canAuth && (this.permitLevel & PERMISSION_SHARE) != 0 && !TextUtils.isEmpty(this.ownerName);
    }

    public boolean isHomeShared() {
        return ((this.permitLevel & PERMISSION_SHARE) == 0 || (this.permitLevel & PERMISSION_AGGREGATE) == 0) ? false : true;
    }

    public boolean isBinded() {
        return (this.permitLevel & PERMISSION_NONE_MASK) != 0;
    }

    public boolean isMiioBinded() {
        if (((this.permitLevel & PERMISSION_SHARE) == 0 || !TextUtils.isEmpty(this.ownerName)) && (this.permitLevel & PERMISSION_NONE_MASK) != 0) {
            return true;
        }
        return false;
    }

    public boolean isSharedReadOnly() {
        return this.canAuth && (this.permitLevel & PERMISSION_SHARE_READONLY) != 0 && !TextUtils.isEmpty(this.ownerName);
    }

    public void setUnbind() {
        this.permitLevel &= PERMISSION_NONE_MASK ^ -1;
    }

    public boolean isSubDevice() {
        return !TextUtils.isEmpty(this.parentId);
    }

    public boolean isShowMainList() {
        return this.showMode > SHOW_HIDDEN;
    }

    public String getExtraToken() {
        return this.extraToken;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        if (this.did == null || !this.did.equals(device.did)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.did.hashCode();
    }

    public DeviceStat newDeviceStat() {
        DeviceStat deviceStat = new DeviceStat();
        deviceStat.did = this.did;
        deviceStat.name = this.name;
        deviceStat.mac = this.mac;
        deviceStat.model = this.model;
        deviceStat.ip = this.ip;
        deviceStat.parentId = this.parentId != null ? this.parentId : "";
        deviceStat.parentModel = this.parentModel != null ? this.parentModel : "";
        deviceStat.authFlag = isShared() ? 1 : 0;
        deviceStat.bindFlag = (isOwner() || isFamily()) ? 1 : 0;
        deviceStat.token = this.token;
        deviceStat.userId = this.userId;
        if (this.location != null) {
            deviceStat.location = this.location.ordinal();
        }
        deviceStat.latitude = this.latitude;
        deviceStat.longitude = this.longitude;
        deviceStat.bssid = this.bssid;
        deviceStat.lastModified = this.lastModified;
        deviceStat.pid = this.pid;
        deviceStat.rssi = this.rssi;
        deviceStat.isOnline = this.isOnline;
        deviceStat.resetFlag = this.resetFlag;
        deviceStat.ssid = this.ssid;
        deviceStat.ownerName = this.ownerName;
        deviceStat.ownerId = this.ownerId;
        deviceStat.propInfo = this.propInfo;
        deviceStat.version = this.version;
        deviceStat.property.clear();
        deviceStat.property.putAll(this.property);
        deviceStat.extrainfo = this.extra;
        deviceStat.showMode = this.showMode;
        deviceStat.event = this.event;
        deviceStat.permitLevel = this.permitLevel;
        deviceStat.isSetPinCode = this.isSetPinCode;
        return deviceStat;
    }

    public DeviceStatus newDeviceStatus() {
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.d = this.did;
        deviceStatus.e = this.name;
        deviceStatus.f = this.mac;
        deviceStatus.g = this.model;
        deviceStatus.i = this.ip;
        deviceStatus.j = this.parentId != null ? this.parentId : "";
        deviceStatus.k = this.parentModel != null ? this.parentModel : "";
        deviceStatus.m = isShared() ? 1 : 0;
        deviceStatus.l = (isOwner() || isFamily()) ? 1 : 0;
        deviceStatus.n = this.token;
        deviceStatus.o = this.userId;
        if (this.location != null) {
            deviceStatus.p = this.location.ordinal();
        }
        deviceStatus.q = this.latitude;
        deviceStatus.r = this.longitude;
        deviceStatus.s = this.bssid;
        deviceStatus.t = this.lastModified;
        deviceStatus.u = this.pid;
        deviceStatus.v = this.rssi;
        deviceStatus.w = this.isOnline;
        deviceStatus.x = this.resetFlag;
        deviceStatus.y = this.ssid;
        deviceStatus.z = this.ownerName;
        deviceStatus.A = this.ownerId;
        deviceStatus.B = this.propInfo;
        deviceStatus.C = this.version;
        deviceStatus.D.clear();
        deviceStatus.D.putAll(this.property);
        deviceStatus.h = this.extra;
        deviceStatus.E = this.showMode;
        deviceStatus.F = this.event;
        deviceStatus.G = this.permitLevel;
        deviceStatus.H = this.isSetPinCode;
        return deviceStatus;
    }

    public JSONObject toJSON() {
        int i;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.did);
            jSONObject.put("name", this.name);
            jSONObject.put("mac", this.mac);
            jSONObject.put("model", this.model);
            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, this.ip);
            jSONObject.put("parentId", this.parentId);
            jSONObject.put("parentModel", this.parentModel);
            jSONObject.put("authFlag", isShared() ? 1 : 0);
            if (!isOwner()) {
                if (!isFamily()) {
                    i = 0;
                    jSONObject.put("bindFlag", i);
                    jSONObject.put("token", this.token);
                    jSONObject.put("userId", this.userId);
                    jSONObject.put("latitude", this.latitude);
                    jSONObject.put("longitude", this.longitude);
                    jSONObject.put(DeviceTagInterface.e, this.ssid);
                    jSONObject.put("show_mode", this.showMode);
                    jSONObject.put("bssid", this.bssid);
                    jSONObject.put("lastModified", this.lastModified);
                    jSONObject.put("pid", this.pid);
                    jSONObject.put("rssi", this.rssi);
                    jSONObject.put("isOnline", this.isOnline);
                    jSONObject.put("resetFlag", this.resetFlag);
                    jSONObject.put("ownerName", this.ownerName);
                    jSONObject.put("ownerId", this.ownerId);
                    jSONObject.put("propInfo", this.propInfo);
                    jSONObject.put("version", this.version);
                    jSONObject.put("desc", this.desc);
                    jSONObject.put("isSetPinCode", this.isSetPinCode);
                    return jSONObject;
                }
            }
            i = 1;
            jSONObject.put("bindFlag", i);
            jSONObject.put("token", this.token);
            jSONObject.put("userId", this.userId);
            jSONObject.put("latitude", this.latitude);
            jSONObject.put("longitude", this.longitude);
            jSONObject.put(DeviceTagInterface.e, this.ssid);
            jSONObject.put("show_mode", this.showMode);
            jSONObject.put("bssid", this.bssid);
            jSONObject.put("lastModified", this.lastModified);
            jSONObject.put("pid", this.pid);
            jSONObject.put("rssi", this.rssi);
            jSONObject.put("isOnline", this.isOnline);
            jSONObject.put("resetFlag", this.resetFlag);
            jSONObject.put("ownerName", this.ownerName);
            jSONObject.put("ownerId", this.ownerId);
            jSONObject.put("propInfo", this.propInfo);
            jSONObject.put("version", this.version);
            jSONObject.put("desc", this.desc);
            jSONObject.put("isSetPinCode", this.isSetPinCode);
        } catch (Exception | JSONException unused) {
        }
        return jSONObject;
    }

    public CharSequence getName() {
        if (TextUtils.isEmpty(this.name)) {
            return this.did;
        }
        return this.name;
    }

    public boolean isNoneClickableDevice() {
        if (this.isOnline) {
            return false;
        }
        PluginRecord d = CoreApi.a().d(this.model);
        if (d == null || d.c() == null || (d.c().z() != 1 && d.c().z() != 2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void handlerBindingNetworkError(int i, IBindDeviceCallback iBindDeviceCallback) {
        MyLog.f("binding network onFailure" + this.did);
        Miio.g("binding network onFailure" + this.did);
        setOwner(false);
        if (iBindDeviceCallback != null) {
            iBindDeviceCallback.a(i);
        }
    }

    /* access modifiers changed from: protected */
    public void handlerFetchTokenError(Context context, IBindDeviceCallback iBindDeviceCallback) {
        String str = "fetchToken onFailure ip = " + this.ip + " did ip = " + this.did;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", str);
            CoreApi.a().a(StatType.TIME, "fetch_token_error", jSONObject.toString(), (String) null, false);
        } catch (JSONException unused) {
        }
        String b = WifiUtil.b(context);
        if (b != null) {
            str = str + " bssid = " + b;
        }
        MyLog.f(str);
        Miio.g("fetchToken onFailure" + this.did);
        setOwner(false);
        if (iBindDeviceCallback != null) {
            iBindDeviceCallback.d();
        }
    }

    public String getSubtitleByDesc(Context context, boolean z) {
        String str = this.desc;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (!isShared() && !isBinded()) {
            str = context.getString(R.string.local_device);
        }
        if (z) {
            return str;
        }
        if (!GlobalSetting.w && !GlobalSetting.j) {
            return str;
        }
        return str + " " + this.did;
    }

    public boolean isNoneOperatableDevice() {
        return !this.canAuth;
    }

    public boolean isSupportCommonSwitch() {
        boolean z;
        if (isSharedReadOnly() || this.method == null || this.method.length() == 0 || this.propInfo == null || this.propInfo.length() == 0 || this.propInfo.isNull(CameraPropertyBase.l)) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= this.method.length()) {
                z = false;
                break;
            }
            JSONObject optJSONObject = this.method.optJSONObject(i);
            if (optJSONObject != null && !optJSONObject.isNull("name") && TextUtils.equals(optJSONObject.optString("name"), AppConstants.B)) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return false;
        }
        String optString = this.propInfo.optString(CameraPropertyBase.l);
        if (TextUtils.equals(optString, "on") || TextUtils.equals(optString, "off")) {
            return true;
        }
        return false;
    }

    public boolean isVirtualDevice() {
        return this.pid == PID_VIRTUAL_DEVICE;
    }

    public boolean canRename() {
        return isOwner();
    }

    public boolean canBeShared() {
        PluginRecord d = CoreApi.a().d(this.model);
        boolean z = d == null || !(d.c() == null || d.c().G() == 3);
        if (isVirtualDevice()) {
            return false;
        }
        if ((this.pid != PID_VIRTUAL_GROUP || Arrays.asList(LightGroupManager.j).contains(this.model)) && isOwner() && this.canAuth && !isSubDevice() && !this.model.equalsIgnoreCase("xiaomi.wifispeaker.s12") && z) {
            return true;
        }
        return false;
    }

    public boolean canBeDeleted() {
        return !this.model.equalsIgnoreCase("xiaomi.wifispeaker.s12");
    }

    public boolean isOnlineAdvance() {
        return this.isOnline;
    }

    public String toString() {
        return "name = " + this.name + ", did = " + this.did + ", model = " + this.model + ", extra = " + this.extra + ", property = " + this.property;
    }

    public Device clone() {
        Device device = new Device();
        cloneTo(device);
        return device;
    }

    public void cloneTo(Device device) {
        device.did = this.did;
        device.token = this.token;
        device.name = this.name;
        device.userId = this.userId;
        device.model = this.model;
        device.location = this.location;
        device.latitude = this.latitude;
        device.longitude = this.longitude;
        device.bssid = this.bssid;
        device.ssid = this.ssid;
        device.showMode = this.showMode;
        device.pid = this.pid;
        device.rssi = this.rssi;
        device.resetFlag = this.resetFlag;
        device.permitLevel = this.permitLevel;
        device.isSetPinCode = this.isSetPinCode;
        device.sort = this.sort;
        device.lastModified = this.lastModified;
        device.parentId = this.parentId;
        device.parentModel = this.parentModel;
        device.ip = this.ip;
        device.mac = this.mac;
        device.ownerName = this.ownerName;
        device.ownerId = this.ownerId;
        device.extra = this.extra;
        device.event = this.event;
        device.extraToken = this.extraToken;
        device.desc = this.desc;
        device.version = this.version;
        device.descNew = this.descNew;
        device.descTimeJString = this.descTimeJString;
        device.isNew = this.isNew;
        device.isOnline = this.isOnline;
        device.scrollTo = this.scrollTo;
        device.canAuth = this.canAuth;
        device.canUseNotBind = this.canUseNotBind;
        device.isConnected = this.isConnected;
        device.propInfo = this.propInfo;
        device.method = this.method;
        device.scanResult = this.scanResult;
        device.specUrn = this.specUrn;
        device.voiceCtrl = this.voiceCtrl;
        device.property.clear();
        device.property.putAll(this.property);
    }
}
