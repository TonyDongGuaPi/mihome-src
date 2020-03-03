package com.xiaomi.ai.mibrain;

import com.xiaomi.ai.mibrain.Mibrainsdk;
import java.io.UnsupportedEncodingException;

class MibrainNativeRequest {
    static int MIBRAINSDK_CMD_ASR_NLP = 0;
    static int MIBRAINSDK_CMD_ASR_NLP_TTS = 2;
    static int MIBRAINSDK_CMD_EVENT = 10;
    static int MIBRAINSDK_CMD_EVENT_TTS = 11;
    static int MIBRAINSDK_CMD_NLP = 4;
    static int MIBRAINSDK_CMD_NLP_TTS = 5;
    static int MIBRAINSDK_CMD_TTS = 3;
    static int MIBRAINSDK_CMD_VOR = 7;
    static int MIBRAINSDK_CMD_VOR_DEL = 8;
    static int MIBRAINSDK_CMD_VOR_QUERY = 9;
    static int MIBRAINSDK_CMD_VOR_REG = 6;
    static int MIBRAINSDK_RESULT_TYPE_BINARY_TTS = 121;
    static int MIBRAINSDK_RESULT_TYPE_TXT_ASR = 11;
    static int MIBRAINSDK_RESULT_TYPE_TXT_ASR_FINAL = 12;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_ERROR = 72;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_OTHER = 62;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_BEGIN = 41;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_END = 42;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_BEGIN = 51;
    static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_END = 52;
    static int MIBRAINSDK_RESULT_TYPE_TXT_INSTRUCTION = 86;
    static int MIBRAINSDK_RESULT_TYPE_TXT_NLP = 31;
    static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_DEL = 84;
    static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_QUERY = 85;
    static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REC = 92;
    static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL = 82;
    static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_PARTIAL = 83;
    static int MIBRAINSDK_RESULT_TYPE_TXT_TTS = 122;
    static int MIRBAINSDK_CMD_ASR = 1;
    static int MI_STOP_FLAG_ASYNC_STOP = 2;
    static int MI_STOP_FLAG_SYNC_STOP = 1;
    static int MI_STOP_FLAG_WAIT_RESULT;
    static Mibrainsdk.MibrainsdkLogHook slogHook;
    private MibrainRequestListener mMibrainRequestListener;
    private MibrainRequest mibrainRequest;

    static native String getResourceDetails(String str, String str2, String str3, int i);

    static native int setDebugLogLevel(int i);

    static native int setLogHookEnable(int i);

    /* access modifiers changed from: package-private */
    public native int addData(long j, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public native int addDataRaw(long j, int i, byte[] bArr, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native int addNlpData(long j, String str);

    /* access modifiers changed from: package-private */
    public native int addUploadData(long j, byte[] bArr, int i);

    /* access modifiers changed from: package-private */
    public native int deleteContext(long j);

    /* access modifiers changed from: package-private */
    public native int endUploadData(long j);

    /* access modifiers changed from: package-private */
    public native String getAnonymousAuthorization(long j);

    /* access modifiers changed from: package-private */
    public native long newContext(MibrainRequestParams mibrainRequestParams, int i, long j);

    /* access modifiers changed from: package-private */
    public native long newWakeUpRequestParams(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void releaseWakeUpRequestParams(long j);

    /* access modifiers changed from: package-private */
    public native int sendWakeUpRequest(long j, MibrainWakeupRequestParams mibrainWakeupRequestParams);

    /* access modifiers changed from: package-private */
    public native int startRequest(long j);

    /* access modifiers changed from: package-private */
    public native long startUploadRequest(long j, MibrainWakeupRequestParams mibrainWakeupRequestParams);

    /* access modifiers changed from: package-private */
    public native int stopRequest(long j, int i);

    MibrainNativeRequest(MibrainRequestListener mibrainRequestListener, MibrainRequest mibrainRequest2) {
        this.mMibrainRequestListener = mibrainRequestListener;
        this.mibrainRequest = mibrainRequest2;
    }

    static void setLogHook(Mibrainsdk.MibrainsdkLogHook mibrainsdkLogHook) {
        slogHook = mibrainsdkLogHook;
        if (mibrainsdkLogHook == null) {
            setLogHookEnable(0);
        } else {
            setLogHookEnable(1);
        }
    }

    static {
        Mibrainsdk.loadMibrainLibs();
    }

    private static void onLogHook(int i, byte[] bArr) {
        if (slogHook != null) {
            try {
                slogHook.onLog(i, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                slogHook.onLog(i, "log UnsupportedEncodingException ");
            }
        }
    }

    private void onResults(byte[] bArr, boolean z, int i) {
        MibrainRequestListener mibrainRequestListener = this.mMibrainRequestListener;
        boolean z2 = false;
        if (i == MIBRAINSDK_RESULT_TYPE_TXT_ASR) {
            try {
                mibrainRequestListener.onRequestASRResult(this.mibrainRequest, new String(bArr, "UTF-8"), false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_ASR_FINAL) {
            try {
                mibrainRequestListener.onRequestASRResult(this.mibrainRequest, new String(bArr, "UTF-8"), true);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_NLP) {
            try {
                mibrainRequestListener.onRequestNLpResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_BINARY_TTS) {
            mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, (String) null, bArr, z);
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_TTS) {
            try {
                mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), (byte[]) null, z);
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_BEGIN) {
            try {
                mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), (byte[]) null, false);
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_END) {
            try {
                mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), (byte[]) null, true);
            } catch (UnsupportedEncodingException e6) {
                e6.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_ERROR) {
            try {
                mibrainRequestListener.onRequestError(this.mibrainRequest, new String(bArr, "UTF-8"), 1, -11);
            } catch (UnsupportedEncodingException e7) {
                e7.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REC) {
            try {
                mibrainRequestListener.onRequestVoiceprintRecognizedResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e8) {
                e8.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL || i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_PARTIAL) {
            try {
                MibrainRequest mibrainRequest2 = this.mibrainRequest;
                String str = new String(bArr, "UTF-8");
                if (i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL) {
                    z2 = true;
                }
                mibrainRequestListener.onRequestVoiceprintRegisteredResult(mibrainRequest2, str, z2);
            } catch (UnsupportedEncodingException e9) {
                e9.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_DEL) {
            try {
                mibrainRequestListener.onRequestVoiceprintDeleteResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e10) {
                e10.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_QUERY) {
            try {
                mibrainRequestListener.onRequestVoiceprintQueryResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e11) {
                e11.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else if (i == MIBRAINSDK_RESULT_TYPE_TXT_INSTRUCTION) {
            try {
                mibrainRequestListener.onInstruction(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e12) {
                e12.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        } else {
            try {
                mibrainRequestListener.onRequestOtherResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e13) {
                e13.printStackTrace();
                mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
            }
        }
    }

    private void onError(int i, byte[] bArr) {
        MibrainRequestListener mibrainRequestListener = this.mMibrainRequestListener;
        try {
            mibrainRequestListener.onRequestError(this.mibrainRequest, new String(bArr, "UTF-8"), 2, i);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            mibrainRequestListener.onRequestError(this.mibrainRequest, "onError UnsupportedEncodingException", 2, -11);
        }
    }

    private void onEnd() {
        this.mMibrainRequestListener.onRequestSessionEnd(this.mibrainRequest);
    }

    private void onStart() {
        this.mMibrainRequestListener.onRequestSessionStart(this.mibrainRequest);
    }

    private String onGetToken(int i) {
        return this.mMibrainRequestListener.onRequestGetToken(this.mibrainRequest, i != 0);
    }

    private String needUpdateToken() {
        return this.mMibrainRequestListener.onNeedUpdateToken();
    }

    private void onEventTrack(String str) {
        this.mMibrainRequestListener.onEventTrack(str);
    }
}
