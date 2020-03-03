package com.xiaomi.chatbot.speechsdk;

import com.xiaomi.chatbot.speechsdk.listener.Listener;

public abstract class RecordListener extends Listener {
    public abstract void onError(ErrorCode errorCode);

    public abstract void onEvent(ErrorCode errorCode);

    public abstract void onFinish();

    public abstract void onRecordEnd();

    public abstract void onRecordStart();

    public abstract void onRecording(byte[] bArr, int i);

    public abstract void onResult(RecordResult recordResult);
}
