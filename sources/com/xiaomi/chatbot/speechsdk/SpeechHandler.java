package com.xiaomi.chatbot.speechsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class SpeechHandler {
    private static SpeechHandler speechHandler;
    private ErrorListener errorListener;
    private HandlerThread mHandlerThread = new HandlerThread("HandlerThread");
    /* access modifiers changed from: private */
    public SpeechRecorder mRecorder;
    Handler mWordThreadHandler;

    public static SpeechHandler getInstance(Context context, String str, String str2, AsrParamType asrParamType, Boolean bool, ErrorListener errorListener2) {
        if (speechHandler == null) {
            synchronized (SpeechHandler.class) {
                speechHandler = new SpeechHandler(context, str, str2, asrParamType, bool, errorListener2);
            }
        }
        return speechHandler;
    }

    private SpeechHandler(Context context, String str, String str2, AsrParamType asrParamType, Boolean bool, ErrorListener errorListener2) {
        this.mHandlerThread.start();
        this.mWordThreadHandler = new Handler(this.mHandlerThread.getLooper()) {
            private static final String TAG = "HandlerThread";

            public void handleMessage(Message message) {
                Log.i(TAG, "收到消息");
            }
        };
        SpeechController.init(context, str, str2, errorListener2);
        SpeechController.setRecorderParam(asrParamType, bool);
        this.mRecorder = SpeechController.createRecorder();
        this.errorListener = errorListener2;
    }

    public void startRecord(final RecordListener recordListener) {
        this.mWordThreadHandler.post(new Runnable() {
            public void run() {
                SpeechHandler.this.mRecorder.start(recordListener);
            }
        });
    }

    public void stopRecord() {
        this.mWordThreadHandler.post(new Runnable() {
            public void run() {
                if (SpeechHandler.this.mRecorder != null) {
                    SpeechHandler.this.mRecorder.stop();
                }
            }
        });
    }

    public void cancelRecord() {
        this.mWordThreadHandler.post(new Runnable() {
            public void run() {
                if (SpeechHandler.this.mRecorder != null) {
                    SpeechHandler.this.mRecorder.cancel();
                }
            }
        });
    }
}
