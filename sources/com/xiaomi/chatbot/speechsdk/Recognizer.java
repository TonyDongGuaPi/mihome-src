package com.xiaomi.chatbot.speechsdk;

import com.google.android.gms.actions.SearchIntents;
import com.google.common.net.HttpHeaders;
import com.google.gson.JsonObject;
import com.xiaomi.chatbot.speechsdk.auth.Authority;
import com.xiaomi.chatbot.speechsdk.common.AsrParam;
import com.xiaomi.chatbot.speechsdk.common.SpeechApp;
import com.xiaomi.chatbot.speechsdk.common.SpeechLog;
import com.xiaomi.chatbot.speechsdk.common.Utils;
import com.xiaomi.chatbot.speechsdk.listener.Listener;
import com.xiaomi.chatbot.speechsdk.listener.RecordListenerHandler;
import com.xiaomi.chatbot.speechsdk.record.RecordBuffer;
import com.xiaomi.chatbot.speechsdk.warpper.Cmd;
import com.xiaomi.chatbot.speechsdk.warpper.Meta;
import com.xiaomi.chatbot.speechsdk.warpper.MetaType;
import com.xiaomi.chatbot.speechsdk.warpper.SpeechMsg;
import java.util.Arrays;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class Recognizer extends Thread {
    private static final int INTERVAL = 1000;
    private static final String TAG = "Recognizer";
    private static RecordBuffer mRecordBuffer = RecordBuffer.getInstance();
    /* access modifiers changed from: private */
    public Boolean isCancel = false;
    private Boolean isRestart = false;
    private Boolean isStop = false;
    /* access modifiers changed from: private */
    public Boolean next = false;
    /* access modifiers changed from: private */
    public RecordListenerHandler recordListenerHandler = null;
    /* access modifiers changed from: private */
    public Boolean recvFailure = false;
    private Boolean running = false;
    /* access modifiers changed from: private */
    public String speechText = "";

    private final class WsListener extends WebSocketListener {
        private boolean expired;

        private WsListener() {
            this.expired = false;
        }

        public boolean isExpired() {
            return this.expired;
        }

        public void setExpired(boolean z) {
            this.expired = z;
        }

        public void onOpen(WebSocket webSocket, Response response) {
            SpeechLog.d(Recognizer.TAG, String.format("open:%s", new Object[]{response.toString()}));
        }

        public void onMessage(WebSocket webSocket, String str) {
            SpeechLog.d(Recognizer.TAG, String.format("receive from server [%s]", new Object[]{str}));
            SpeechMsg fromJson = SpeechMsg.fromJson(str);
            if (Recognizer.this.isCancel.booleanValue()) {
                this.expired = true;
            }
            switch (fromJson.getMeta().getType()) {
                case ERROR:
                    Boolean unused = Recognizer.this.next = false;
                    webSocket.send(Recognizer.this.getEndMsg());
                    Recognizer.this.recordListenerHandler.onError(getErrorCode(fromJson.getResponse(), "error_code"));
                    Recognizer.this.cancel();
                    return;
                case TRANSACTION_BEGIN:
                    if (this.expired) {
                        Recognizer.this.recordListenerHandler.onRecordEnd();
                        return;
                    } else {
                        Recognizer.this.recordListenerHandler.onRecordStart();
                        return;
                    }
                case TRANSACTION_END:
                    Recognizer.this.recordListenerHandler.onRecordEnd();
                    Recognizer.this.cancel();
                    return;
                case SERVICE_EVENT:
                    Recognizer.this.recordListenerHandler.OnEvent(getErrorCode(fromJson.getResponse(), "code"));
                    return;
                case RESULT_ASR_PARTIAL:
                    if (this.expired) {
                        Recognizer.this.recordListenerHandler.onRecordEnd();
                        return;
                    }
                    RecordListenerHandler access$300 = Recognizer.this.recordListenerHandler;
                    String requestId = fromJson.getMeta().getRequestId();
                    access$300.onResult(getResult(requestId, false, Recognizer.this.speechText + Recognizer.this.getText(fromJson.getResponse())));
                    return;
                case RESULT_ASR_FINAL:
                    if (this.expired) {
                        Recognizer.this.recordListenerHandler.onRecordEnd();
                        return;
                    }
                    Recognizer recognizer = Recognizer.this;
                    String unused2 = recognizer.speechText = Recognizer.this.speechText + Recognizer.this.getText(fromJson.getResponse());
                    Recognizer.this.recordListenerHandler.onResult(getResult(fromJson.getMeta().getRequestId(), true, Recognizer.this.speechText));
                    Recognizer.this.recordListenerHandler.onFinish();
                    return;
                default:
                    return;
            }
        }

        private RecordResult getResult(String str, Boolean bool, String str2) {
            RecordResult recordResult = new RecordResult();
            recordResult.setFinal(bool.booleanValue());
            recordResult.setRequestId(str);
            recordResult.setText(str2);
            return recordResult;
        }

        private ErrorCode getErrorCode(JsonObject jsonObject, String str) {
            if (jsonObject.has(str)) {
                return ErrorCode.valueOf(jsonObject.get(str).getAsInt());
            }
            SpeechLog.e(Recognizer.TAG, "UNKNOWN");
            return ErrorCode.UNKNOWN;
        }

        public void onMessage(WebSocket webSocket, ByteString byteString) {
            SpeechLog.d(Recognizer.TAG, String.format("receive from server [%s]", new Object[]{byteString.base64()}));
        }

        public void onClosing(WebSocket webSocket, int i, String str) {
            SpeechLog.d(Recognizer.TAG, String.format("closing:%s", new Object[]{str}));
        }

        public void onClosed(WebSocket webSocket, int i, String str) {
            SpeechLog.d(Recognizer.TAG, String.format("close:%s", new Object[]{str}));
        }

        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            Object[] objArr = new Object[1];
            objArr[0] = response == null ? "null" : response.toString();
            SpeechLog.d(Recognizer.TAG, String.format("failure:%s", objArr));
            Boolean unused = Recognizer.this.recvFailure = true;
        }
    }

    /* access modifiers changed from: private */
    public String getText(JsonObject jsonObject) {
        if (!jsonObject.has("queries")) {
            return "";
        }
        JsonObject asJsonObject = jsonObject.get("queries").getAsJsonArray().get(0).getAsJsonObject();
        return asJsonObject.has(SearchIntents.EXTRA_QUERY) ? asJsonObject.get(SearchIntents.EXTRA_QUERY).getAsString() : "";
    }

    public Recognizer setListener(Listener listener) {
        this.recordListenerHandler = new RecordListenerHandler(listener);
        return this;
    }

    public void run() {
        this.speechText = "";
        if (!mRecordBuffer.isStoped()) {
            mRecordBuffer.stop();
        }
        mRecordBuffer.start();
        WebSocket webSocket = getWebSocket();
        if (webSocket == null) {
            this.running = false;
            return;
        }
        this.running = true;
        this.isStop = false;
        this.isCancel = false;
        SpeechLog.d(TAG, String.format("start send %s", new Object[]{getStartMsg()}));
        webSocket.send(getStartMsg());
        SpeechLog.d(TAG, "start");
        while (!this.isStop.booleanValue() && !this.isCancel.booleanValue()) {
            byte[] readAudio = mRecordBuffer.readAudio();
            SpeechLog.d(TAG, String.format("send %d", new Object[]{Integer.valueOf(readAudio.length)}));
            webSocket.send(ByteString.of(readAudio));
            this.recordListenerHandler.onRecording(readAudio, Utils.GetWavVolume(readAudio));
            if (this.recvFailure.booleanValue()) {
                this.recvFailure = false;
                webSocket = getWebSocket();
                if (webSocket != null) {
                    webSocket.send(getStartMsg());
                } else {
                    this.recvFailure = true;
                }
            }
        }
        SpeechLog.d(TAG, "stop");
        mRecordBuffer.stop();
        if (!this.isCancel.booleanValue()) {
            webSocket.send(getEndMsg());
            SpeechLog.d(TAG, String.format("end send %s", new Object[]{getEndMsg()}));
        }
        this.running = false;
    }

    public Boolean isRunning() {
        return this.running;
    }

    private WebSocket getWebSocket() {
        String authHeader = Authority.getInstance().getAuthHeader();
        if (authHeader == null) {
            return null;
        }
        WsListener wsListener = new WsListener();
        Request.Builder builder = new Request.Builder();
        Request build = builder.url(SpeechApp.getServerUrl() + "/speech").addHeader("Authorization", authHeader).addHeader("Connection", HttpHeaders.UPGRADE).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        WebSocket newWebSocket = okHttpClient.newWebSocket(build, wsListener);
        okHttpClient.dispatcher().executorService().shutdown();
        return newWebSocket;
    }

    public void userStop() {
        this.isStop = true;
    }

    public void cancel() {
        this.isCancel = true;
    }

    private String getStartMsg() {
        SpeechMsg speechMsg = new SpeechMsg();
        Meta meta = new Meta();
        meta.setCmds(Arrays.asList(new Cmd[]{Cmd.AI_CMD_ASR}));
        meta.setType(MetaType.TRANSACTION_BEGIN);
        if (!(AsrParam.getInstance().getPunctuation() == null || AsrParam.getInstance().getVad() == null)) {
            JsonObject jsonObject = new JsonObject();
            if (AsrParam.getInstance().getVad() != null) {
                jsonObject.addProperty("vad", AsrParam.getInstance().getVad());
            }
            if (AsrParam.getInstance().getPunctuation() != null) {
                jsonObject.addProperty("remove_end_punctuation", Boolean.valueOf(true ^ AsrParam.getInstance().getPunctuation().booleanValue()));
            }
            meta.setAsr(jsonObject);
        }
        speechMsg.setMeta(meta);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("device_id", Authority.getInstance().getDeviceId());
        speechMsg.setRequest(jsonObject2);
        return speechMsg.toString();
    }

    /* access modifiers changed from: private */
    public String getEndMsg() {
        SpeechMsg speechMsg = new SpeechMsg();
        speechMsg.setMeta(new Meta(MetaType.TRANSACTION_END));
        return speechMsg.toString();
    }
}
