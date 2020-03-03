package com.xiaomi.chatbot.speechsdk;

import com.xiaomi.chatbot.speechsdk.common.Utils;

public class SpeechRecorder {
    private static final int INTERVAL = 10;
    protected Recognizer recognizer = null;

    public void start(RecordListener recordListener) {
        if (this.recognizer != null && this.recognizer.isRunning().booleanValue()) {
            this.recognizer.cancel();
            while (this.recognizer.isRunning().booleanValue()) {
                Utils.SleepCatchException(10);
            }
        }
        this.recognizer = new Recognizer().setListener(recordListener);
        this.recognizer.start();
    }

    public void cancel() {
        if (this.recognizer != null) {
            this.recognizer.cancel();
            this.recognizer = null;
        }
    }

    public void stop() {
        if (this.recognizer != null) {
            this.recognizer.userStop();
        }
    }
}
