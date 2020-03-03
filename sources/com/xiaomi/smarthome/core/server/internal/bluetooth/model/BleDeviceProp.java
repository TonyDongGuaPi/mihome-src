package com.xiaomi.smarthome.core.server.internal.bluetooth.model;

import android.text.TextUtils;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class BleDeviceProp implements Serializable {
    private String beaconName;
    private int boundStatus;
    private String desc;
    private String did;
    private String encryptedKeyId;
    private String encryptedLtmk;
    private String encryptedMeshBindInfo;
    private String encryptedSession;
    private String encryptedToken;
    private JSONObject extras = new JSONObject();
    private int ltmkEncryptType;
    private String model;
    private String name;
    private String ownerId;
    private String ownerName;
    private int permitLevel;
    private String pincode;
    private int productId;
    private BleGattProfile profile;
    private int rssi;
    private String scanRecord;
    private boolean showPincode;
    private String smac;
    private int type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSmac() {
        return this.smac;
    }

    public void setSmac(String str) {
        this.smac = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public int getBoundStatus() {
        return this.boundStatus;
    }

    public void setBoundStatus(int i) {
        this.boundStatus = i;
    }

    public String getDid() {
        return this.did;
    }

    public void setDid(String str) {
        this.did = str;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int i) {
        this.productId = i;
    }

    public String getEncryptedToken() {
        return this.encryptedToken;
    }

    public void setEncryptedToken(String str) {
        this.encryptedToken = str;
    }

    public String getScanRecord() {
        return this.scanRecord;
    }

    public void setScanRecord(String str) {
        this.scanRecord = str;
    }

    public BleGattProfile getProfile() {
        return this.profile;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public void setProfile(BleGattProfile bleGattProfile) {
        this.profile = bleGattProfile;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getBeaconName() {
        return this.beaconName;
    }

    public void setBeaconName(String str) {
        this.beaconName = str;
    }

    public void setExtra(String str, int i) {
        try {
            this.extras.put(str, i);
        } catch (JSONException e) {
            BluetoothLog.a((Throwable) e);
        }
    }

    public void setExtra(String str, boolean z) {
        try {
            this.extras.put(str, z);
        } catch (JSONException e) {
            BluetoothLog.a((Throwable) e);
        }
    }

    public void setExtra(String str, String str2) {
        try {
            this.extras.put(str, str2);
        } catch (JSONException e) {
            BluetoothLog.a((Throwable) e);
        }
    }

    public int getExtra(String str, int i) {
        return this.extras.optInt(str, i);
    }

    public boolean getExtra(String str, boolean z) {
        return this.extras.optBoolean(str, z);
    }

    public String getExtra(String str, String str2) {
        return this.extras.optString(str, str2);
    }

    public void removeExtra(String str) {
        this.extras.remove(str);
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public int getPermitLevel() {
        return this.permitLevel;
    }

    public void setPermitLevel(int i) {
        this.permitLevel = i;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String str) {
        this.ownerName = str;
    }

    public void setEncryptedSession(String str) {
        this.encryptedSession = str;
    }

    public String getEncryptedSession() {
        return this.encryptedSession;
    }

    public void setEncryptedLtmk(String str) {
        this.encryptedLtmk = str;
    }

    public String getEncryptedLtmk() {
        return this.encryptedLtmk;
    }

    public void setPincode(String str) {
        this.pincode = str;
    }

    public String getPincode() {
        return this.pincode;
    }

    public void setLtmkEncryptType(int i) {
        this.ltmkEncryptType = i;
    }

    public int getLtmkEncryptType() {
        return this.ltmkEncryptType;
    }

    public boolean isShowPincode() {
        return this.showPincode;
    }

    public void setShowPincode(boolean z) {
        this.showPincode = z;
    }

    public void setEncryptedKeyId(String str) {
        this.encryptedKeyId = str;
    }

    public String getEncryptedKeyId() {
        return this.encryptedKeyId;
    }

    public void setEncryptedMeshBindInfo(String str) {
        this.encryptedMeshBindInfo = str;
    }

    public String getEncryptedMeshBindInfo() {
        return this.encryptedMeshBindInfo;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.name);
            jSONObject.put("did", this.did);
            jSONObject.put("desc", this.desc);
            jSONObject.put("model", this.model);
            jSONObject.put(MifareCardInfo.KEY_PRODUCT_ID, this.productId);
            jSONObject.put("boundStatus", this.boundStatus);
            jSONObject.put("encryptedToken", this.encryptedToken);
            jSONObject.put("encryptedLtmk", this.encryptedLtmk);
            jSONObject.put("pincode", this.pincode);
            jSONObject.put("ltmkEncryptType", this.ltmkEncryptType);
            jSONObject.put("showPincode", this.showPincode);
            jSONObject.put("encryptedKeyId", this.encryptedKeyId);
            jSONObject.put("ownerId", this.ownerId);
            jSONObject.put("type", this.type);
            jSONObject.put("permitLevel", this.permitLevel);
            jSONObject.put("ownerName", this.ownerName);
            jSONObject.put("extras", this.extras);
            jSONObject.put("encryptedMeshBindInfo", this.encryptedMeshBindInfo);
        } catch (Exception e) {
            BluetoothLog.a((Throwable) e);
        }
        return jSONObject.toString();
    }

    public static BleDeviceProp fromJson(String str) {
        try {
            BleDeviceProp bleDeviceProp = new BleDeviceProp();
            JSONObject jSONObject = new JSONObject(str);
            bleDeviceProp.name = jSONObject.optString("name");
            bleDeviceProp.did = jSONObject.optString("did");
            bleDeviceProp.desc = jSONObject.optString("desc");
            bleDeviceProp.model = jSONObject.optString("model");
            bleDeviceProp.productId = jSONObject.optInt(MifareCardInfo.KEY_PRODUCT_ID);
            bleDeviceProp.boundStatus = jSONObject.optInt("boundStatus");
            bleDeviceProp.encryptedToken = jSONObject.optString("encryptedToken");
            bleDeviceProp.encryptedLtmk = jSONObject.optString("encryptedLtmk");
            bleDeviceProp.pincode = jSONObject.optString("pincode");
            bleDeviceProp.ltmkEncryptType = jSONObject.optInt("ltmkEncryptType");
            bleDeviceProp.showPincode = jSONObject.optBoolean("showPincode", true);
            bleDeviceProp.encryptedKeyId = jSONObject.optString("encryptedKeyId");
            bleDeviceProp.ownerId = jSONObject.optString("ownerId");
            bleDeviceProp.permitLevel = jSONObject.optInt("permitLevel");
            bleDeviceProp.ownerName = jSONObject.optString("ownerName", "");
            bleDeviceProp.type = jSONObject.optInt("type", 0);
            JSONObject optJSONObject = jSONObject.optJSONObject("extras");
            if (optJSONObject != null) {
                bleDeviceProp.extras = optJSONObject;
            }
            bleDeviceProp.encryptedMeshBindInfo = jSONObject.optString("encryptedMeshBindInfo");
            return bleDeviceProp;
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.name)) {
            sb.append("name = " + this.name);
        }
        if (!TextUtils.isEmpty(this.did)) {
            sb.append(", did = " + this.did);
        }
        if (!TextUtils.isEmpty(this.desc)) {
            sb.append(", desc = " + this.desc);
        }
        if (!TextUtils.isEmpty(this.model)) {
            sb.append(", model = " + this.model);
        }
        if (this.productId > 0) {
            sb.append(", productId = " + this.productId);
        }
        sb.append(", boundStatus = " + this.boundStatus);
        if (this.extras.length() > 0) {
            sb.append(", extras = " + this.extras);
        }
        return sb.toString();
    }
}
