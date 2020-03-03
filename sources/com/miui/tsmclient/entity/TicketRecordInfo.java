package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class TicketRecordInfo implements Parcelable, JSONSerializable, ObjectParser<TicketRecordInfo> {
    public static final Parcelable.Creator<TicketRecordInfo> CREATOR = new Parcelable.Creator<TicketRecordInfo>() {
        public TicketRecordInfo createFromParcel(Parcel parcel) {
            TicketRecordInfo ticketRecordInfo = new TicketRecordInfo();
            ticketRecordInfo.readFromParcel(parcel);
            return ticketRecordInfo;
        }

        public TicketRecordInfo[] newArray(int i) {
            return new TicketRecordInfo[i];
        }
    };
    private static final String KEY_DATE = "mDate";
    private static final String KEY_IN_OUT_FLAG = "mInOutFlag";
    private static final String KEY_STATION_NAME = "mStationName";
    private static final String KEY_TIME = "mTime";
    public static final int STATION_STATUS_IN = 0;
    public static final int STATION_STATUS_OUT = 1;
    public static final int STATION_STATUS_UNKNOWN = -1;
    private String mDate;
    private int mInOutFlag = -1;
    private String mStationName;
    private String mTime;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mInOutFlag);
        parcel.writeString(this.mDate);
        parcel.writeString(this.mTime);
        parcel.writeString(this.mStationName);
    }

    public void readFromParcel(Parcel parcel) {
        this.mInOutFlag = parcel.readInt();
        this.mDate = parcel.readString();
        this.mTime = parcel.readString();
        this.mStationName = parcel.readString();
    }

    public TicketRecordInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mInOutFlag = jSONObject.optInt(KEY_IN_OUT_FLAG);
            this.mDate = jSONObject.optString(KEY_DATE);
            this.mTime = jSONObject.optString(KEY_TIME);
            this.mStationName = jSONObject.optString(KEY_STATION_NAME);
        }
        return this;
    }

    public JSONObject serialize() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_IN_OUT_FLAG, this.mInOutFlag);
            jSONObject.put(KEY_DATE, this.mDate);
            jSONObject.put(KEY_TIME, this.mTime);
            jSONObject.put(KEY_STATION_NAME, this.mStationName);
        } catch (JSONException e) {
            LogUtils.e("serialize TicketRecordInfo failed.", e);
        }
        return jSONObject;
    }

    public int getInOutFlag() {
        return this.mInOutFlag;
    }

    public void setInOutFlag(int i) {
        this.mInOutFlag = i;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public String getStationName() {
        return this.mStationName;
    }

    public void setStationName(String str) {
        this.mStationName = str;
    }
}
