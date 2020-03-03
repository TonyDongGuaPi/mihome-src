package com.xiaomi.smarthome.camera;

import android.support.annotation.WorkerThread;

public interface IClientExListener {
    void onAudioData(byte[] bArr, byte[] bArr2);

    @WorkerThread
    void onConnected();

    void onCtrlData(int i, byte[] bArr);

    void onDisConnected();

    void onDisconnectedWithCode(int i);

    void onError(int i, String str);

    void onPause();

    void onProgress(int i);

    void onResume();

    void onRetry(int i, String str, int i2);

    void onVideoData(byte[] bArr, byte[] bArr2);
}
