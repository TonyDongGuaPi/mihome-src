package com.xiaomi.jr.feature.voice;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.xiaomi.chatbot.speechsdk.AsrParamType;
import com.xiaomi.chatbot.speechsdk.ErrorCode;
import com.xiaomi.chatbot.speechsdk.ErrorListener;
import com.xiaomi.chatbot.speechsdk.RecordListener;
import com.xiaomi.chatbot.speechsdk.RecordResult;
import com.xiaomi.chatbot.speechsdk.SpeechHandler;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.feature.voice.Voice;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class VoiceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10806a = "VoiceHelper";
    private static final String b = "invalid parameter";
    private static final String c = "invalid param end without audio";
    private static final String d = "execution timeout";
    private static final String e = "execution error";
    private static final String f = "truncation";
    private static final String g = "store failed";
    private static final String h = "unknown";
    private AtomicBoolean i;
    private SpeechHandler j;
    private ErrorListener k;

    interface RecognizeResultListener {
        void onResult(Voice.RecognizeResult recognizeResult);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z) {
        MifiLog.b(f10806a, "SpeechHandler ErrorListener - error = " + z);
        if (z) {
            this.i.compareAndSet(false, true);
        }
    }

    class MyRecordListener extends RecordListener {
        private RecognizeResultListener b;

        MyRecordListener(RecognizeResultListener recognizeResultListener) {
            this.b = recognizeResultListener;
        }

        public void onRecordStart() {
            MifiLog.b(VoiceHelper.f10806a, "onRecordStart");
        }

        public void onRecording(byte[] bArr, int i) {
            MifiLog.b(VoiceHelper.f10806a, String.format(Locale.getDefault(), "onRecording wav length %d, volume %d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        }

        public void onRecordEnd() {
            MifiLog.b(VoiceHelper.f10806a, "onRecordEnd");
        }

        public void onResult(RecordResult recordResult) {
            MifiLog.b(VoiceHelper.f10806a, String.format("onResult result isFinal %s,result id %s, result text %s", new Object[]{Boolean.valueOf(recordResult.isFinal()), recordResult.getRequestId(), recordResult.getText()}));
            Voice.RecognizeResult recognizeResult = new Voice.RecognizeResult();
            recognizeResult.f10805a = recordResult.isFinal();
            recognizeResult.b = recordResult.getText();
            recognizeResult.c = "0";
            recognizeResult.d = "";
            if (this.b != null) {
                this.b.onResult(recognizeResult);
            }
        }

        public void onError(ErrorCode errorCode) {
            MifiLog.b(VoiceHelper.f10806a, "onError:" + errorCode.value());
            Voice.RecognizeResult recognizeResult = new Voice.RecognizeResult();
            recognizeResult.f10805a = false;
            recognizeResult.b = "";
            recognizeResult.c = String.valueOf(errorCode.value());
            switch (errorCode) {
                case INVALID_PARAMETER:
                    recognizeResult.d = VoiceHelper.b;
                    break;
                case INVALID_PARAM_END_WITHOUT_AUDIO:
                    recognizeResult.d = VoiceHelper.c;
                    break;
                case EXECUTION_TIMEOUT:
                    recognizeResult.d = VoiceHelper.d;
                    break;
                case EXECUTION_ERROR:
                    recognizeResult.d = VoiceHelper.e;
                    break;
                case TRUNCATION:
                    recognizeResult.d = VoiceHelper.f;
                    break;
                case STORE_FAILED:
                    recognizeResult.d = VoiceHelper.g;
                    break;
                case UNKNOWN:
                    recognizeResult.d = "unknown";
                    break;
            }
            if (this.b != null) {
                this.b.onResult(recognizeResult);
            }
        }

        public void onEvent(ErrorCode errorCode) {
            MifiLog.b(VoiceHelper.f10806a, "onError:" + errorCode.value());
            onError(errorCode);
        }

        public void onFinish() {
            MifiLog.b(VoiceHelper.f10806a, "onFinish");
        }
    }

    private static class SingleCreator {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final VoiceHelper f10809a = new VoiceHelper();

        private SingleCreator() {
        }
    }

    public static VoiceHelper a() {
        return SingleCreator.f10809a;
    }

    private VoiceHelper() {
        this.i = new AtomicBoolean(false);
        this.k = new ErrorListener() {
            public final void setError(boolean z) {
                VoiceHelper.this.a(z);
            }
        };
    }

    private void d() {
        if (this.i.get()) {
            throw new IllegalStateException("VoiceHelper check error");
        }
    }

    private void a(RecordListener recordListener) {
        d();
        if (this.j != null) {
            this.j.startRecord(recordListener);
            MifiLog.b(f10806a, "start record");
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.j != null) {
            this.j.cancelRecord();
            MifiLog.b(f10806a, "cancel record");
        }
        this.i.set(false);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.j != null) {
            this.j.stopRecord();
            MifiLog.b(f10806a, "stop record");
        }
        this.i.set(false);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void a(Context context, String str, RecognizeResultListener recognizeResultListener) {
        this.i.set(false);
        Utils.b();
        this.j = SpeechHandler.getInstance(context, (String) null, str, AsrParamType.CONTINUOUS, true, this.k);
        a((RecordListener) new MyRecordListener(recognizeResultListener));
    }
}
