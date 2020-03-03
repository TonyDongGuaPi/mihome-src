package com.xiaomi.chatbot.speechsdk.listener;

import android.os.Message;
import com.xiaomi.chatbot.speechsdk.ErrorCode;
import com.xiaomi.chatbot.speechsdk.RecordListener;
import com.xiaomi.chatbot.speechsdk.RecordResult;

public class RecordListenerHandler extends ListenerHandler {
    private static final int LISTENER_MESSAGE_ON_ERROR = 0;
    private static final int LISTENER_MESSAGE_ON_EVENT = 1;
    private static final int LISTENER_MESSAGE_ON_FINISH = 3;
    private static final int LISTENER_MESSAGE_ON_RECORDING = 5;
    private static final int LISTENER_MESSAGE_ON_RECORD_START = 4;
    private static final int LISTENER_MESSAGE_ON_RECORD_STOP = 6;
    private static final int LISTENER_MESSAGE_ON_RESULT = 2;

    class Pcm {
        public int volume = 0;
        public byte[] wav = null;

        public Pcm(byte[] bArr, int i) {
            if (bArr == null || bArr.length == 0) {
                this.wav = null;
            } else {
                this.wav = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.wav, 0, bArr.length);
            }
            this.volume = i;
        }
    }

    public RecordListenerHandler(Listener listener) {
        super(listener);
    }

    public void onFinish() {
        Message message = new Message();
        message.what = 3;
        AddMessage(message);
    }

    public void onError(ErrorCode errorCode) {
        Message message = new Message();
        message.what = 0;
        message.obj = errorCode;
        AddMessage(message);
    }

    public void OnEvent(ErrorCode errorCode) {
        Message message = new Message();
        message.what = 1;
        message.obj = errorCode;
        AddMessage(message);
    }

    public void onRecordStart() {
        Message message = new Message();
        message.what = 4;
        AddMessage(message);
    }

    public void onRecordEnd() {
        Message message = new Message();
        message.what = 6;
        AddMessage(message);
    }

    public void onRecording(byte[] bArr, int i) {
        Message message = new Message();
        message.what = 5;
        message.obj = new Pcm(bArr, i);
        AddMessage(message);
    }

    public void onResult(RecordResult recordResult) {
        Message message = new Message();
        message.what = 2;
        message.obj = recordResult;
        AddMessage(message);
    }

    /* access modifiers changed from: protected */
    public void workMessage(Message message) {
        if (this.listener != null) {
            switch (message.what) {
                case 0:
                    ((RecordListener) this.listener).onError((ErrorCode) message.obj);
                    return;
                case 1:
                    ((RecordListener) this.listener).onEvent((ErrorCode) message.obj);
                    return;
                case 2:
                    ((RecordListener) this.listener).onResult((RecordResult) message.obj);
                    return;
                case 3:
                    ((RecordListener) this.listener).onFinish();
                    return;
                case 4:
                    ((RecordListener) this.listener).onRecordStart();
                    return;
                case 5:
                    Pcm pcm = (Pcm) message.obj;
                    ((RecordListener) this.listener).onRecording(pcm.wav, pcm.volume);
                    return;
                case 6:
                    ((RecordListener) this.listener).onRecordEnd();
                    return;
                default:
                    return;
            }
        }
    }
}
