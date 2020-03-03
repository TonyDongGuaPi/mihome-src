package com.xiaomi.ai.mibrain;

public class MibrainRequest {
    private static final int BRAIN_ADD_DATA_FLAG_CONTINUE = 0;
    private static final int BRAIN_ADD_DATA_FLAG_TASK_END = 1;
    private static final int BRAIN_ADD_DATA_FLAG_VOR_REG_DIV = 2;
    public static final int MIBRAINSDK_ERR_BUFFERQUEUE = -7;
    public static final int MIBRAINSDK_ERR_ENDED = -5;
    public static final int MIBRAINSDK_ERR_HAS_INITIALIZED = -3;
    public static final int MIBRAINSDK_ERR_INIT_FAILED = -1;
    public static final int MIBRAINSDK_ERR_MEM_FAILED = -4;
    public static final int MIBRAINSDK_ERR_PARAMS = -6;
    public static final int MIBRAINSDK_ERR_RECVDATA = -9;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_ACTIVE_TIMEOUT = -20;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_AUTH_FAILED = -22;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_CONNECT_TIMEOUT = -10;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_CRT_ERROR = -14;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_GET_TOKEN_FAILED = -23;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_HANDSHAKE_ERROR = -13;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_INIT_FAILED_ERROR = -16;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_INIT_STATE_ERROR = -17;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_NETWORK_ERROR = -15;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_READ_DATA_ERROR = -11;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_READ_DATA_TIMEOUT = -18;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_SYSTEM_TIME_ERROR = -21;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_WRITE_DATA_ERROR = -12;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_WRITE_DATA_TIMEOUT = -19;
    public static final int MIBRAINSDK_ERR_UNINITIALED = -2;
    public static final int MIBRAINSDK_ERR_UNKNOWN = -8;
    public static final int MIBRAINSDK_NEW_MODE_NORMAL = 0;
    public static final int MIBRAINSDK_NEW_MODE_PENDING_DATA = 1;
    public static final int MIBRAINSDK_OK = 0;
    public static final int MIBRAIN_IPC_BINARY_MSG = 1;
    public static final int MIBRAIN_IPC_TEXT_MSG = 4;
    public static final int MI_ERR_ERRORTYPE_LOCAL = 2;
    public static final int MI_ERR_ERRORTYPE_SERVICE = 1;
    private MibrainRequestListener internalRequestListener = new MibrainRequestListener() {
        public void onRequestSessionStart(MibrainRequest mibrainRequest) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestSessionStart(mibrainRequest);
            }
        }

        public void onRequestError(MibrainRequest mibrainRequest, String str, int i, int i2) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestError(mibrainRequest, str, i, i2);
            }
            MibrainRequest.this.mErrorCode = i2;
        }

        public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z) {
            if (MibrainRequest.this.mRequestListener != null) {
                return MibrainRequest.this.mRequestListener.onRequestGetToken(mibrainRequest, z);
            }
            return null;
        }

        public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestOtherResult(mibrainRequest, str);
            }
        }

        public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestNLpResult(mibrainRequest, str);
            }
        }

        public void onRequestASRResult(MibrainRequest mibrainRequest, String str, boolean z) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestASRResult(mibrainRequest, str, z);
            }
        }

        public void onRequestTTSResult(MibrainRequest mibrainRequest, String str, byte[] bArr, boolean z) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestTTSResult(mibrainRequest, str, bArr, z);
            }
        }

        public void onInstruction(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onInstruction(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintRecognizedResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintRecognizedResult(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintRegisteredResult(MibrainRequest mibrainRequest, String str, boolean z) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintRegisteredResult(mibrainRequest, str, z);
            }
        }

        public void onRequestVoiceprintQueryResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintQueryResult(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintDeleteResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintDeleteResult(mibrainRequest, str);
            }
        }

        public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
            synchronized (MibrainRequest.this) {
                if (MibrainRequest.this.mInter != null) {
                    MibrainRequest.this.mInter.onGetAnonymousAuthorization(MibrainRequest.this.mMibrainNativeRequest.getAnonymousAuthorization(MibrainRequest.this.mNativeContext));
                }
                MibrainRequest.this.mMibrainNativeRequest.deleteContext(MibrainRequest.this.mNativeContext);
                boolean unused = MibrainRequest.this.mEnd = true;
            }
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestSessionEnd(mibrainRequest);
            }
        }

        public String onNeedUpdateToken() {
            return MibrainRequest.this.mRequestListener.onNeedUpdateToken();
        }

        public void onEventTrack(String str) {
            MibrainRequest.this.mRequestListener.onEventTrack(str);
        }
    };
    /* access modifiers changed from: private */
    public boolean mEnd = false;
    public int mErrorCode;
    GetAnonymousAuthorizationInterface mInter;
    /* access modifiers changed from: private */
    public MibrainNativeRequest mMibrainNativeRequest;
    /* access modifiers changed from: private */
    public long mNativeContext;
    /* access modifiers changed from: private */
    public MibrainRequestListener mRequestListener;
    private MibrainRequestParams mRequestParams;
    private MibrainWakeupRequestParams mWakeupRequestParams;
    private int mode;

    public void setGetAnonymousAuthorizationCallback(GetAnonymousAuthorizationInterface getAnonymousAuthorizationInterface) {
        this.mInter = getAnonymousAuthorizationInterface;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public MibrainRequest(MibrainRequestParams mibrainRequestParams) {
        this.mRequestParams = mibrainRequestParams;
        this.mMibrainNativeRequest = new MibrainNativeRequest(this.internalRequestListener, this);
    }

    public MibrainRequest(MibrainWakeupRequestParams mibrainWakeupRequestParams) {
        this.mWakeupRequestParams = mibrainWakeupRequestParams;
        this.mMibrainNativeRequest = new MibrainNativeRequest(this.internalRequestListener, this);
    }

    public static String getResourceDetails(String str, String str2, String str3, int i) {
        return MibrainNativeRequest.getResourceDetails(str, str2, str3, i);
    }

    public void updateRequestParams(MibrainRequestParams mibrainRequestParams) {
        this.mRequestParams = mibrainRequestParams;
    }

    public int startWakeupRequest(int i, int i2) {
        long newWakeUpRequestParams = this.mMibrainNativeRequest.newWakeUpRequestParams(i, i2);
        if (newWakeUpRequestParams == 0) {
            return -1;
        }
        int sendWakeUpRequest = this.mMibrainNativeRequest.sendWakeUpRequest(newWakeUpRequestParams, this.mWakeupRequestParams);
        this.mMibrainNativeRequest.releaseWakeUpRequestParams(newWakeUpRequestParams);
        return sendWakeUpRequest;
    }

    public long startUploadRequest(int i, int i2) {
        long newWakeUpRequestParams = this.mMibrainNativeRequest.newWakeUpRequestParams(i, i2);
        if (newWakeUpRequestParams == 0) {
            return -1;
        }
        return this.mMibrainNativeRequest.startUploadRequest(newWakeUpRequestParams, this.mWakeupRequestParams);
    }

    public int addUploadData(long j, byte[] bArr, int i) {
        return this.mMibrainNativeRequest.addUploadData(j, bArr, i);
    }

    public int endUploadData(long j) {
        return this.mMibrainNativeRequest.endUploadData(j);
    }

    public void releaseUploadRequest(long j) {
        this.mMibrainNativeRequest.releaseWakeUpRequestParams(j);
    }

    public void setRequestListener(MibrainRequestListener mibrainRequestListener) {
        this.mRequestListener = mibrainRequestListener;
    }

    public synchronized String getAnonymousAuthorization() {
        return this.mMibrainNativeRequest.getAnonymousAuthorization(this.mNativeContext);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003e, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int startRequest() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.mEnd     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0008
            r0 = -5
            monitor-exit(r5)
            return r0
        L_0x0008:
            long r0 = r5.mNativeContext     // Catch:{ all -> 0x003f }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0013
            r0 = -3
            monitor-exit(r5)
            return r0
        L_0x0013:
            com.xiaomi.ai.mibrain.MibrainNativeRequest r0 = r5.mMibrainNativeRequest     // Catch:{ all -> 0x003f }
            com.xiaomi.ai.mibrain.MibrainRequestParams r1 = r5.mRequestParams     // Catch:{ all -> 0x003f }
            r4 = 0
            long r0 = r0.newContext(r1, r4, r2)     // Catch:{ all -> 0x003f }
            r5.mNativeContext = r0     // Catch:{ all -> 0x003f }
            long r0 = r5.mNativeContext     // Catch:{ all -> 0x003f }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r0 = 1
            if (r4 != 0) goto L_0x002a
            r5.mEnd = r0     // Catch:{ all -> 0x003f }
            r0 = -4
            monitor-exit(r5)
            return r0
        L_0x002a:
            com.xiaomi.ai.mibrain.MibrainNativeRequest r1 = r5.mMibrainNativeRequest     // Catch:{ all -> 0x003f }
            long r2 = r5.mNativeContext     // Catch:{ all -> 0x003f }
            int r1 = r1.startRequest(r2)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            r5.mEnd = r0     // Catch:{ all -> 0x003f }
            com.xiaomi.ai.mibrain.MibrainNativeRequest r0 = r5.mMibrainNativeRequest     // Catch:{ all -> 0x003f }
            long r2 = r5.mNativeContext     // Catch:{ all -> 0x003f }
            r0.deleteContext(r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r5)
            return r1
        L_0x003f:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.mibrain.MibrainRequest.startRequest():int");
    }

    public synchronized int sendPendingData() {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -5;
        }
        return (int) this.mMibrainNativeRequest.newContext(this.mRequestParams, 1, this.mNativeContext);
    }

    public synchronized int addRawData(byte[] bArr, int i, boolean z, boolean z2) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        return this.mMibrainNativeRequest.addDataRaw(this.mNativeContext, i, bArr, bArr == null ? 0 : bArr.length, z ? 1 : 0, z2 ? 1 : 0);
    }

    public synchronized int addAudioData(byte[] bArr, boolean z) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        if (!((this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_TTS && this.mRequestParams.ttsSuperaddition == 0) || this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_NLP)) {
            if (this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                return this.mMibrainNativeRequest.addData(this.mNativeContext, bArr, bArr == null ? 0 : bArr.length, z ? 1 : 0);
            }
        }
        return -6;
    }

    public synchronized int addAudioData(byte[] bArr, boolean z, boolean z2) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        if (!((this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_TTS && this.mRequestParams.ttsSuperaddition == 0) || this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_NLP)) {
            if (this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                return this.mMibrainNativeRequest.addData(this.mNativeContext, bArr, bArr == null ? 0 : bArr.length, z2 ? 1 : z ? 2 : 0);
            }
        }
        return -6;
    }

    public synchronized int addAudioData(byte[] bArr, int i, boolean z) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        if (!((this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_TTS && this.mRequestParams.ttsSuperaddition == 0) || this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_NLP)) {
            if (this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                return this.mMibrainNativeRequest.addData(this.mNativeContext, bArr, i, z ? 1 : 0);
            }
        }
        return -6;
    }

    public synchronized int addAudioData(byte[] bArr, int i, boolean z, boolean z2) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        if (!((this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_TTS && this.mRequestParams.ttsSuperaddition == 0) || this.mRequestParams.sdkCmd == MibrainNativeRequest.MIBRAINSDK_CMD_NLP)) {
            if (this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                int i2 = 0;
                if (z) {
                    i2 = 2;
                }
                return this.mMibrainNativeRequest.addData(this.mNativeContext, bArr, i, z2 ? 1 : i2);
            }
        }
        return -6;
    }

    public synchronized int addNlpData(String str) {
        if (this.mEnd) {
            return -5;
        }
        if (this.mNativeContext == 0) {
            return -2;
        }
        if ((this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_TTS || this.mRequestParams.ttsSuperaddition != 0) && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP_TTS && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_EVENT && this.mRequestParams.sdkCmd != MibrainNativeRequest.MIBRAINSDK_CMD_EVENT_TTS) {
            return -6;
        }
        return this.mMibrainNativeRequest.addNlpData(this.mNativeContext, str);
    }

    public synchronized void asyncStopRequest() {
        if (!this.mEnd) {
            if (this.mNativeContext != 0) {
                this.mMibrainNativeRequest.stopRequest(this.mNativeContext, MibrainNativeRequest.MI_STOP_FLAG_ASYNC_STOP);
            }
        }
    }

    public synchronized void waitForResult() {
        if (!this.mEnd) {
            if (this.mNativeContext != 0) {
                this.mMibrainNativeRequest.stopRequest(this.mNativeContext, MibrainNativeRequest.MI_STOP_FLAG_WAIT_RESULT);
            }
        }
    }
}
