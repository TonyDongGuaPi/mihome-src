package com.xiaomi.chatbot.speechsdk;

import android.content.Context;
import com.xiaomi.chatbot.speechsdk.auth.Authority;
import com.xiaomi.chatbot.speechsdk.common.AsrParam;
import com.xiaomi.chatbot.speechsdk.common.SpeechApp;
import com.xiaomi.chatbot.speechsdk.common.SpeechLog;

public class SpeechController {
    private static SpeechRecorder recorder;

    public static void init(Context context, String str, String str2, ErrorListener errorListener) {
        SpeechApp.setContext(context);
        SpeechApp.setInited(true);
        Authority.getInstance(str, str2, errorListener).setup();
    }

    public static SpeechRecorder createRecorder() {
        if (!SpeechApp.isInited()) {
            SpeechLog.e("SpeechController", "not init.");
            return null;
        }
        if (recorder == null) {
            recorder = new SpeechRecorder();
        }
        return recorder;
    }

    public static void setRecorderParam(AsrParamType asrParamType, Boolean bool) {
        switch (asrParamType) {
            case VAD:
                AsrParam.getInstance().setVad(bool);
                return;
            case PUNCTUATION:
                AsrParam.getInstance().setPunctuation(bool);
                return;
            case CONTINUOUS:
                AsrParam.getInstance().setContinuous(bool);
                return;
            default:
                return;
        }
    }
}
