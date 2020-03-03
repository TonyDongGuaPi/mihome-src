package com.xiaomi.smarthome.camera;

public interface XmMp4Record {

    public interface IRecordListener {
        void onFailed(int i, String str);

        void onSuccess(String str);
    }

    int getRecordTime();

    void setMineDuration(int i);

    void startRecord(String str, int i, int i2, int i3, int i4);

    void startRecord(String str, int i, int i2, int i3, int i4, int i5);

    void stopRecord(IRecordListener iRecordListener);

    void writeAAcData(byte[] bArr, int i);

    void writeVideoData(byte[] bArr, int i, boolean z, int i2);
}
