package com.mijia.model.alarmcloud;

import android.os.Parcel;
import android.os.Parcelable;

public class AlarmVideo implements Parcelable {
    public static final Parcelable.Creator<AlarmVideo> CREATOR = new Parcelable.Creator<AlarmVideo>() {
        public AlarmVideo createFromParcel(Parcel parcel) {
            return new AlarmVideo(parcel);
        }

        public AlarmVideo[] newArray(int i) {
            return new AlarmVideo[i];
        }
    };
    public long createTime;
    public String eventType;
    public long expireTime;
    public String extraInfo;
    public String fileId;
    public FileIdMetaResult fileIdMetaResult;
    public String imgStoreId;
    public String imgStoreUrl;
    public boolean isAlarm;
    public boolean isPlaying;
    public boolean isRead;
    public int offset;
    public double startDuration;
    public String videoStoreId;

    public int describeContents() {
        return 0;
    }

    public AlarmVideo() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileId);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.expireTime);
        parcel.writeString(this.videoStoreId);
        parcel.writeString(this.imgStoreId);
        parcel.writeString(this.extraInfo);
        parcel.writeByte(this.isAlarm ? (byte) 1 : 0);
        parcel.writeString(this.eventType);
        parcel.writeInt(this.offset);
        parcel.writeDouble(this.startDuration);
        parcel.writeString(this.imgStoreUrl);
        parcel.writeParcelable(this.fileIdMetaResult, i);
        parcel.writeByte(this.isRead ? (byte) 1 : 0);
    }

    protected AlarmVideo(Parcel parcel) {
        this.fileId = parcel.readString();
        this.createTime = parcel.readLong();
        this.expireTime = parcel.readLong();
        this.videoStoreId = parcel.readString();
        this.imgStoreId = parcel.readString();
        this.extraInfo = parcel.readString();
        boolean z = false;
        this.isAlarm = parcel.readByte() != 0;
        this.eventType = parcel.readString();
        this.offset = parcel.readInt();
        this.startDuration = parcel.readDouble();
        this.imgStoreUrl = parcel.readString();
        this.fileIdMetaResult = (FileIdMetaResult) parcel.readParcelable(FileIdMetaResult.class.getClassLoader());
        this.isRead = parcel.readByte() != 0 ? true : z;
    }
}
