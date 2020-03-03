package com.tutk;

import android.os.ConditionVariable;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.debug.SDKLog;
import com.mijia.debug.Tag;
import com.tutk.CameraClient;
import com.tutk.IOTC.AVAPIs;
import com.tutk.IOTC.AVIOCTRLDEFs;
import com.tutk.IOTC.IOTCAPIs;
import com.tutk.IOTC.Packet;
import com.tutk.IOTC.RDTAPIs;
import com.tutk.IOTC.St_RDT_Status;
import com.tutk.IOTC.St_SInfo;
import com.xiaomi.smarthome.camera.IClientListener;
import com.xiaomi.smarthome.camera.P2pResponse;
import com.xiaomi.smarthome.camera.XmP2PInfo;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class TuTkClient extends CameraClient {
    private static final int TIME_INTERVAL_3 = 3000;
    private static final int TIME_INTERVAL_30 = 30000;
    public static volatile long mConnect_Cache;
    public static volatile long mConnect_Client;
    public static volatile long mConnect_Iotc;
    public static volatile long mConnect_Public_Key;
    public static volatile long mConnect_Start;
    private static String[] sP2P_MODE = {P2PUtils.TAG, "Relay", "Lan"};
    /* access modifiers changed from: private */
    public final int FRAME_INFO_SIZE;
    /* access modifiers changed from: private */
    public int mAVClientIndex = -1;
    private AVIOCtrlReceiveThread mAVIOCtrlReceiveThread = null;
    /* access modifiers changed from: private */
    public volatile int mAVServerIndex = -1;
    private AudioReceiveThread mAudioReceiveThread = null;
    private volatile int mLocalSessionId = -1;
    private int mNumber = 0;
    /* access modifiers changed from: private */
    public P2PInfoImp mP2PInfo;
    private volatile int mQuality = 0;
    /* access modifiers changed from: private */
    public int mRDTIndex = -1;
    private RDTReceiveThread mRDTReceiveThread = null;
    /* access modifiers changed from: private */
    public volatile int mSessionId = -1;
    private VideoReceiveThread mVideoReceiveThread = null;
    private St_SInfo m_stSInfo = new St_SInfo();
    private int videoBlockTimes = 0;

    static /* synthetic */ int access$708(TuTkClient tuTkClient) {
        int i = tuTkClient.videoBlockTimes;
        tuTkClient.videoBlockTimes = i + 1;
        return i;
    }

    private TuTkClient(P2PInfoImp p2PInfoImp) {
        super(p2PInfoImp);
        this.mP2PInfo = p2PInfoImp;
        this.FRAME_INFO_SIZE = p2PInfoImp.mFrameInfoSize;
        if (!TextUtils.isEmpty(p2PInfoImp.getDid())) {
            this.mUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(p2PInfoImp.getDid());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.tutk.CameraClient getCameraClient(com.xiaomi.smarthome.camera.XmP2PInfo r3) {
        /*
            java.lang.Class<com.tutk.TuTkClient> r0 = com.tutk.TuTkClient.class
            monitor-enter(r0)
            r1 = 0
            if (r3 == 0) goto L_0x003f
            java.lang.String r2 = r3.getUid()     // Catch:{ all -> 0x003c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x0011
            goto L_0x003f
        L_0x0011:
            boolean r2 = r3 instanceof com.tutk.P2PInfoImp     // Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x0017
            monitor-exit(r0)
            return r1
        L_0x0017:
            java.util.HashMap r1 = sCacheCameraClientMap     // Catch:{ all -> 0x003c }
            java.lang.String r2 = r3.getUid()     // Catch:{ all -> 0x003c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x003c }
            com.tutk.CameraClient r1 = (com.tutk.CameraClient) r1     // Catch:{ all -> 0x003c }
            if (r1 != 0) goto L_0x0037
            com.tutk.TuTkClient r1 = new com.tutk.TuTkClient     // Catch:{ all -> 0x003c }
            r2 = r3
            com.tutk.P2PInfoImp r2 = (com.tutk.P2PInfoImp) r2     // Catch:{ all -> 0x003c }
            r1.<init>(r2)     // Catch:{ all -> 0x003c }
            java.util.HashMap r2 = sCacheCameraClientMap     // Catch:{ all -> 0x003c }
            java.lang.String r3 = r3.getUid()     // Catch:{ all -> 0x003c }
            r2.put(r3, r1)     // Catch:{ all -> 0x003c }
            goto L_0x003a
        L_0x0037:
            r1.updateInfo(r3)     // Catch:{ all -> 0x003c }
        L_0x003a:
            monitor-exit(r0)
            return r1
        L_0x003c:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x003f:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tutk.TuTkClient.getCameraClient(com.xiaomi.smarthome.camera.XmP2PInfo):com.tutk.CameraClient");
    }

    public void updateInfo(XmP2PInfo xmP2PInfo) {
        if (xmP2PInfo instanceof P2PInfoImp) {
            P2PInfoImp p2PInfoImp = (P2PInfoImp) xmP2PInfo;
            if (this.mP2PInfo == null || !this.mP2PInfo.getPwd().equals(xmP2PInfo.getPwd()) || !this.mP2PInfo.mRemoteKey.equals(p2PInfoImp.mRemoteKey)) {
                SDKLog.d(Tag.c, "updateInfo ");
                this.pwdChange = true;
                this.mPwdTry = 3;
                this.mP2PInfo = p2PInfoImp;
                if (isConnected()) {
                    SDKLog.d(Tag.c, "updateInfo connect");
                    reconnect();
                    return;
                }
                return;
            }
            this.mP2PInfo.setOnLine(xmP2PInfo.getOnLine());
        }
    }

    public String getUid() {
        return this.mP2PInfo == null ? "" : this.mP2PInfo.getUid();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x013d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doConnect() throws com.tutk.CameraException {
        /*
            r8 = this;
            java.lang.String r0 = "Tutk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "doConnect "
            r1.append(r2)
            java.lang.String r2 = r8.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.debug.SDKLog.e(r0, r1)
            r0 = 5
            r8.onProgress(r0)
            com.tutk.P2PUtils.initial()
            r0 = 10
            r8.onProgress(r0)
            int r0 = com.tutk.IOTC.IOTCAPIs.IOTC_Get_SessionID()
            if (r0 < 0) goto L_0x0163
            r8.mLocalSessionId = r0
            r1 = 20
            r8.onProgress(r1)
            long r1 = java.lang.System.currentTimeMillis()
            mConnect_Iotc = r1
            java.lang.String r1 = "Tutk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TuTkClient IOTC_Connect "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.debug.SDKLog.e(r1, r2)
            com.tutk.P2PInfoImp r1 = r8.mP2PInfo
            java.lang.String r1 = r1.getUid()
            int r0 = com.tutk.IOTC.IOTCAPIs.IOTC_Connect_ByUID_Parallel(r1, r0)
            r8.mSessionId = r0
            int r0 = r8.mSessionId
            if (r0 < 0) goto L_0x0155
            r0 = 30
            r8.onProgress(r0)
            java.lang.String r0 = "Tutk"
            java.lang.String r1 = "TuTkClient Free_Channel"
            com.debug.SDKLog.e(r0, r1)
            int r0 = r8.mSessionId
            int r0 = com.tutk.IOTC.IOTCAPIs.IOTC_Session_Get_Free_Channel(r0)
            if (r0 < 0) goto L_0x014b
            r0 = 1
            int[] r7 = new int[r0]
            int[] r5 = new int[r0]
            java.lang.String r1 = "Tutk"
            java.lang.String r2 = "TuTkClient avClientStart2"
            com.debug.SDKLog.e(r1, r2)
            com.tutk.P2PInfoImp r1 = r8.mP2PInfo
            java.lang.String r1 = r1.mAccount
            com.tutk.P2PInfoImp r2 = r8.mP2PInfo
            byte[] r2 = r2.mShareKey
            if (r2 == 0) goto L_0x00cb
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r3 = "public_key"
            org.libsodium.jni.encoders.Hex r4 = org.libsodium.jni.encoders.Encoder.b     // Catch:{ JSONException -> 0x00b0 }
            com.tutk.P2PInfoImp r6 = r8.mP2PInfo     // Catch:{ JSONException -> 0x00b0 }
            byte[] r6 = r6.mPublicKey     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r4 = r4.a((byte[]) r6)     // Catch:{ JSONException -> 0x00b0 }
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r3 = "sign"
            com.tutk.P2PInfoImp r4 = r8.mP2PInfo     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r4 = r4.mRemoteSing     // Catch:{ JSONException -> 0x00b0 }
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r3 = "account"
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00b0 }
            goto L_0x00cc
        L_0x00b0:
            r2 = move-exception
            java.lang.String r3 = "Tutk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "avClientStart2 "
            r4.append(r6)
            java.lang.String r2 = r2.toString()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.debug.SDKLog.e(r3, r2)
        L_0x00cb:
            r2 = r1
        L_0x00cc:
            r1 = 0
            r8.pwdChange = r1
            r1 = 40
            r8.onProgress(r1)
            long r3 = java.lang.System.currentTimeMillis()
            mConnect_Client = r3
            int r1 = r8.mSessionId
            com.tutk.P2PInfoImp r3 = r8.mP2PInfo
            java.lang.String r3 = r3.getPwd()
            r4 = 10
            r6 = 0
            int r1 = com.tutk.IOTC.AVAPIs.avClientStart2(r1, r2, r3, r4, r5, r6, r7)
            r8.mAVClientIndex = r1
            int r1 = r8.mAVClientIndex
            if (r1 < 0) goto L_0x013d
            r1 = 50
            r8.onProgress(r1)
            com.tutk.TuTkClient$AVIOCtrlReceiveThread r1 = r8.mAVIOCtrlReceiveThread
            if (r1 == 0) goto L_0x00fd
            com.tutk.TuTkClient$AVIOCtrlReceiveThread r1 = r8.mAVIOCtrlReceiveThread
            r1.stopThreadAsyn()
        L_0x00fd:
            com.tutk.TuTkClient$AVIOCtrlReceiveThread r1 = new com.tutk.TuTkClient$AVIOCtrlReceiveThread
            r1.<init>()
            r8.mAVIOCtrlReceiveThread = r1
            com.tutk.TuTkClient$AVIOCtrlReceiveThread r1 = r8.mAVIOCtrlReceiveThread
            r1.start()
            com.tutk.TuTkClient$RDTReceiveThread r1 = new com.tutk.TuTkClient$RDTReceiveThread
            r1.<init>()
            r8.mRDTReceiveThread = r1
            com.tutk.TuTkClient$RDTReceiveThread r1 = r8.mRDTReceiveThread
            r1.start()
            r1 = 60
            r8.onProgress(r1)
            com.tutk.TuTkClient$AudioReceiveThread r1 = new com.tutk.TuTkClient$AudioReceiveThread
            r1.<init>()
            r8.mAudioReceiveThread = r1
            com.tutk.TuTkClient$AudioReceiveThread r1 = r8.mAudioReceiveThread
            r1.start()
            com.tutk.TuTkClient$VideoReceiveThread r1 = new com.tutk.TuTkClient$VideoReceiveThread
            r1.<init>()
            r8.mVideoReceiveThread = r1
            com.tutk.TuTkClient$VideoReceiveThread r1 = r8.mVideoReceiveThread
            r1.start()
            r1 = 70
            r8.onProgress(r1)
            r8.mClientStatus = r0
            r8.onConnected()
            return
        L_0x013d:
            com.tutk.CameraException r0 = new com.tutk.CameraException
            int r1 = r8.mAVClientIndex
            int r2 = r8.mAVClientIndex
            java.lang.String r2 = getErrorInfo(r2)
            r0.<init>(r1, r2)
            throw r0
        L_0x014b:
            com.tutk.CameraException r1 = new com.tutk.CameraException
            java.lang.String r2 = getErrorInfo(r0)
            r1.<init>(r0, r2)
            throw r1
        L_0x0155:
            com.tutk.CameraException r0 = new com.tutk.CameraException
            int r1 = r8.mSessionId
            int r2 = r8.mSessionId
            java.lang.String r2 = getErrorInfo(r2)
            r0.<init>(r1, r2)
            throw r0
        L_0x0163:
            com.tutk.CameraException r1 = new com.tutk.CameraException
            java.lang.String r2 = getErrorInfo(r0)
            r1.<init>(r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tutk.TuTkClient.doConnect():void");
    }

    public int sendIOCtrl(int i, byte[] bArr) {
        if (this.mP2PHandler == null) {
            return -1;
        }
        try {
            if (Looper.myLooper() != this.mP2PHandler.getLooper()) {
                Message obtain = Message.obtain();
                obtain.what = 11;
                obtain.arg1 = i;
                obtain.obj = bArr;
                this.mP2PHandler.sendMessage(obtain);
                SDKLog.d(Tag.c, "sendIOCtrl not in p2p Thread");
                return 0;
            }
            SDKLog.d(Tag.c, "TuTkClient sendIOCtrl :" + i);
            if (this.mAVClientIndex < 0) {
                SDKLog.e(Tag.c, "sendIOCtrl mAVClientIndex:" + i);
                return -2;
            }
            if (bArr == null) {
                bArr = new byte[8];
            }
            int avSendIOCtrl = AVAPIs.avSendIOCtrl(this.mAVClientIndex, i, bArr, bArr.length);
            SDKLog.d(Tag.c, "endIOCtrl :" + i + " ret: " + avSendIOCtrl);
            return avSendIOCtrl;
        } catch (Exception e) {
            LogUtil.b(Tag.c, "Exception:" + e.getLocalizedMessage());
            return -3;
        }
    }

    public void setQuality(int i) {
        final byte[] bArr;
        if (i != this.mQuality && isConnected()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("videoquality", i);
                bArr = jSONObject.toString().getBytes(Charset.forName("UTF-8"));
            } catch (JSONException unused) {
                SDKLog.e(Tag.c, "quality fail ");
                bArr = null;
            }
            if (bArr != null) {
                this.mP2PHandler.post(new Runnable() {
                    public void run() {
                        TuTkClient.this.sendIOCtrl(800, bArr);
                    }
                });
            } else {
                return;
            }
        }
        this.mQuality = i;
    }

    /* access modifiers changed from: protected */
    public void doSendAudioData(byte[] bArr, int i) {
        int i2;
        if (bArr != null && bArr.length != 0) {
            if (this.mAVServerIndex < 0) {
                this.mP2PHandler.post(new Runnable() {
                    public void run() {
                        if (TuTkClient.this.mAVServerIndex <= 0) {
                            int IOTC_Session_Get_Free_Channel = IOTCAPIs.IOTC_Session_Get_Free_Channel(TuTkClient.this.mSessionId);
                            if (IOTC_Session_Get_Free_Channel < 0) {
                                SDKLog.e(Tag.c, "speak start no channle");
                                return;
                            }
                            byte[] bArr = new byte[8];
                            System.arraycopy(Packet.intToByteArray(IOTC_Session_Get_Free_Channel, TuTkClient.this.isBigOrder), 0, bArr, 0, 4);
                            int sendIOCtrl = TuTkClient.this.sendIOCtrl(848, bArr);
                            if (sendIOCtrl < 0) {
                                SDKLog.e(Tag.c, "speak start fail " + sendIOCtrl);
                            }
                            SDKLog.d(Tag.c, "speak start " + IOTC_Session_Get_Free_Channel);
                            int unused = TuTkClient.this.mAVServerIndex = AVAPIs.avServStart(TuTkClient.this.mSessionId, (byte[]) null, (byte[]) null, 10, 0, IOTC_Session_Get_Free_Channel);
                            if (TuTkClient.this.mAVServerIndex < 0) {
                                SDKLog.e(Tag.c, "speak start fail:" + TuTkClient.this.mAVServerIndex + " channel:" + IOTC_Session_Get_Free_Channel);
                            }
                        }
                    }
                });
                return;
            }
            this.mNumber += 20;
            byte[] parseContent = parseContent((short) i, (byte) 2, (byte) 0, (byte) 0, this.mNumber, this.isBigOrder);
            if (this.mP2PInfo == null || !this.mP2PInfo.isIPC009AudioFullEncryption) {
                i2 = AVAPIs.avSendAudioData(this.mAVServerIndex, bArr, bArr.length, parseContent, parseContent.length);
            } else {
                byte[] encryptAudio = DecryptUtil.encryptAudio(bArr, this.mP2PInfo.mShareKey);
                i2 = AVAPIs.avSendAudioData(this.mAVServerIndex, encryptAudio, encryptAudio.length, parseContent, parseContent.length);
            }
            if (i2 < 0) {
                SDKLog.e(Tag.c, "TuTkClient avSendAudioData error:" + getErrorInfo(i2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doPause() {
        SDKLog.d(Tag.c, "doPause");
        sendIOCtrl(AVIOCTRLDEFs.IOTYPE_USER_IPCAM_STOP, new byte[8]);
        sendIOCtrl(769, new byte[8]);
        if (this.mAVClientIndex > 0) {
            AVAPIs.avClientCleanVideoBuf(this.mAVClientIndex);
            AVAPIs.avClientCleanAudioBuf(this.mAVClientIndex);
        }
        if (this.mClientListener != null) {
            this.mClientListener.onPause();
        }
        if (this.videoBlockTimes > 0) {
            this.videoBlockTimes = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        SDKLog.d(Tag.c, "doResume ");
        this.videoBlockTimes = 0;
        if (!isConnected()) {
            this.mRetry = 0;
            this.mFirstConnect = 0;
            retryInner();
            return;
        }
        onProgress(70);
        mConnect_Start = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("videoquality", this.mQuality);
            jSONObject.put("enableaudio", 1);
            sendIOCtrl(511, jSONObject.toString().getBytes(Charset.forName("UTF-8")));
        } catch (JSONException e) {
            e.printStackTrace();
            SDKLog.e(Tag.c, "json error");
        }
        onProgress(90);
        if (this.mClientListener != null) {
            this.mClientListener.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void doPlayBack(byte[] bArr) {
        if (!isConnected()) {
            try {
                doConnect();
            } catch (CameraException e) {
                e.printStackTrace();
            }
        } else if (isPaused()) {
            this.mIsPaused = false;
            sendIOCtrl(768, new byte[8]);
        }
        sendIOCtrl(AVIOCTRLDEFs.IOTYPE_USER_IPCAM_PLAYBACK_CTRL_REQ, bArr);
    }

    /* access modifiers changed from: protected */
    public void doDirection(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HomeManager.v, i);
            sendIOCtrl(61445, jSONObject.toString().getBytes(Charset.forName("UTF-8")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    public void getP2PInfo(List<Pair<String, String>> list) {
        if (list != null && this.mSessionId >= 0 && IOTCAPIs.IOTC_Session_Check(this.mSessionId, this.m_stSInfo) == 0) {
            list.add(new Pair("P2P_UID", new String(this.m_stSInfo.UID)));
            if (this.m_stSInfo.Mode < sP2P_MODE.length) {
                list.add(new Pair("P2P_Mode", sP2P_MODE[this.m_stSInfo.Mode]));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doStopConnect() {
        if (this.mLocalSessionId >= 0) {
            IOTCAPIs.IOTC_Connect_Stop_BySID(this.mLocalSessionId);
            SDKLog.d(Tag.c, "doStopConnect " + this.mLocalSessionId);
        }
    }

    public void sendMsg(int i, int i2, byte[] bArr, P2pResponse p2pResponse) {
        if (isConnected() && this.mRDTReceiveThread != null) {
            this.mRDTReceiveThread.sendMessage(new P2PMessage(i, i2, bArr, p2pResponse));
        } else if (p2pResponse != null) {
            p2pResponse.onError(-1);
        }
    }

    /* access modifiers changed from: protected */
    public void doDisConnect() {
        SDKLog.d(Tag.c, "doDisConnect " + toString());
        if (this.mRDTReceiveThread != null) {
            this.mRDTReceiveThread.stopThreadAsyn();
            this.mRDTReceiveThread = null;
        }
        if (this.mAVIOCtrlReceiveThread != null) {
            this.mAVIOCtrlReceiveThread.stopThreadAsyn();
            this.mAVIOCtrlReceiveThread = null;
        }
        if (this.mAudioReceiveThread != null) {
            this.mAudioReceiveThread.stopThreadAsyn();
            this.mAudioReceiveThread = null;
        }
        if (this.mVideoReceiveThread != null) {
            this.mVideoReceiveThread.stopThreadAsyn();
            this.mVideoReceiveThread = null;
        }
        if (this.mRDTIndex >= 0) {
            RDTAPIs.RDT_Abort(this.mRDTIndex);
            sendIOCtrl(513, new byte[8]);
            int RDT_Destroy = RDTAPIs.RDT_Destroy(this.mRDTIndex);
            if (RDT_Destroy < 0) {
                SDKLog.b(Tag.c, "RDT_Destroy " + RDT_Destroy);
            }
            this.mRDTIndex = -1;
        }
        if (this.mAVServerIndex >= 0) {
            AVAPIs.avServStop(this.mAVServerIndex);
            AVAPIs.avServExit(this.mSessionId, this.mAVServerIndex);
            this.mAVServerIndex = -1;
        }
        if (this.mAVClientIndex >= 0) {
            AVAPIs.avClientStop(this.mAVClientIndex);
            AVAPIs.avClientExit(this.mSessionId, this.mAVClientIndex);
            this.mAVClientIndex = -1;
        }
        if (this.mSessionId >= 0) {
            IOTCAPIs.IOTC_Session_Close(this.mSessionId);
            SDKLog.b(Tag.c, "Session_Close " + this.mSessionId);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] copyByteData(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 + i > bArr.length) {
            return null;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private class AVIOCtrlReceiveThread extends CameraClient.BaseThread {
        int[] ioType = new int[1];
        byte[] recData = new byte[2048];

        AVIOCtrlReceiveThread() {
            super("AVIOCtrlReceive");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() {
            if (TuTkClient.this.mAVClientIndex < 0) {
                SDKLog.d(Tag.c, "TuTkClient avRecvIOCtrl mAVClientIndex < 0");
                return stopRun();
            }
            int avRecvIOCtrl = AVAPIs.avRecvIOCtrl(TuTkClient.this.mAVClientIndex, this.ioType, this.recData, this.recData.length, 1000);
            if (avRecvIOCtrl >= 0) {
                TuTkClient.this.handleAVIOCtrl(this.ioType[0], TuTkClient.copyByteData(this.recData, 0, avRecvIOCtrl));
            } else if (avRecvIOCtrl != -20011) {
                SDKLog.d(Tag.c, "TuTkClient avRecvIOCtrl" + TuTkClient.getErrorInfo(avRecvIOCtrl));
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!(avRecvIOCtrl == -20019 || avRecvIOCtrl == -20010)) {
                switch (avRecvIOCtrl) {
                    case AVAPIs.AV_ER_REMOTE_TIMEOUT_DISCONNECT:
                    case AVAPIs.AV_ER_SESSION_CLOSE_BY_REMOTE:
                        break;
                }
            }
            stopRun();
            if (TuTkClient.this.mIsPaused) {
                TuTkClient.this.release(true, (IClientListener) null);
            } else {
                TuTkClient.this.mRetry = 0;
                TuTkClient.this.mFirstConnect = 0;
                TuTkClient.this.retryInner();
            }
            return 0;
        }
    }

    private class AudioReceiveThread extends CameraClient.BaseThread {
        static final int AUDIO_BUF_SIZE = 2048;
        byte[] frameData = new byte[2048];
        byte[] frameInfo = new byte[TuTkClient.this.FRAME_INFO_SIZE];

        AudioReceiveThread() {
            super("AudioReceive");
        }

        public synchronized void start() {
            super.start();
            if (TuTkClient.this.mAVClientIndex > 0) {
                AVAPIs.avClientCleanAudioBuf(TuTkClient.this.mAVClientIndex);
            }
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            super.doRelease();
            this.frameData = null;
            this.frameInfo = null;
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() {
            if (TuTkClient.this.mAVClientIndex < 0) {
                SDKLog.d(Tag.c, "TuTkClient avRecvAudioData mAVClientIndex < 0");
                return stopRun();
            } else if (TuTkClient.this.mIsPaused) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            } else {
                int avRecvAudioData = AVAPIs.avRecvAudioData(TuTkClient.this.mAVClientIndex, this.frameData, 2048, this.frameInfo, 0, new int[1]);
                if (avRecvAudioData > 0) {
                    byte[] access$300 = TuTkClient.copyByteData(this.frameData, 0, avRecvAudioData);
                    if (this.frameData == null) {
                        return 0;
                    }
                    if (!TuTkClient.this.mIsStartSpeaking && TuTkClient.this.mClientListener != null) {
                        TuTkClient.this.mClientListener.onAudioData(DecryptUtil.decryptAudioG711(access$300, TuTkClient.this.mP2PInfo.mShareKey), this.frameInfo);
                    }
                } else if (avRecvAudioData != -20012) {
                    SDKLog.d(Tag.c, "TuTkClient avRecvAudioData" + TuTkClient.getErrorInfo(avRecvAudioData));
                }
                if (avRecvAudioData == -20012) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException unused) {
                        Thread.interrupted();
                    }
                    return 0;
                } else if (avRecvAudioData == -20010 || avRecvAudioData == -20016 || avRecvAudioData == -20015) {
                    return stopRun();
                } else {
                    return 0;
                }
            }
        }
    }

    private class VideoReceiveThread extends CameraClient.BaseThread {
        static final int VIDEO_BUF_SIZE = 1048576;
        byte[] frameInfo = new byte[TuTkClient.this.FRAME_INFO_SIZE];
        long mLastSuccessVideoFrameTime;
        int[] outFrmInfoBufSize = new int[1];
        int[] outFrmSize = new int[1];
        int[] pFrmInfoBuf = new int[1];
        long validDelayTime;
        byte[] videoBuffer = new byte[1048576];

        VideoReceiveThread() {
            super("VideoReceive");
        }

        public synchronized void start() {
            super.start();
            this.mLastSuccessVideoFrameTime = 0;
            if (TuTkClient.this.mAVClientIndex > 0) {
                AVAPIs.avClientCleanVideoBuf(TuTkClient.this.mAVClientIndex);
            }
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            super.doRelease();
            this.frameInfo = null;
            this.videoBuffer = null;
            this.outFrmSize = null;
            this.pFrmInfoBuf = null;
            this.outFrmInfoBufSize = null;
        }

        /* access modifiers changed from: package-private */
        public int stopRun() {
            this.mLastSuccessVideoFrameTime = 0;
            return super.stopRun();
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() {
            byte[] bArr;
            if (TuTkClient.this.mAVClientIndex < 0) {
                SDKLog.d(Tag.c, "TuTkClient videoReceive mAVClientIndex < 0");
                return stopRun();
            } else if (TuTkClient.this.mIsPaused) {
                this.mLastSuccessVideoFrameTime = 0;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            } else {
                int avRecvFrameData2 = AVAPIs.avRecvFrameData2(TuTkClient.this.mAVClientIndex, this.videoBuffer, 1048576, this.outFrmSize, this.pFrmInfoBuf, this.frameInfo, TuTkClient.this.FRAME_INFO_SIZE, this.outFrmInfoBufSize, new int[1]);
                if (avRecvFrameData2 <= 0) {
                    if (avRecvFrameData2 != -20012) {
                        SDKLog.b(Tag.c, "TuTkClient VideoReceive: " + TuTkClient.getErrorInfo(avRecvFrameData2));
                    }
                    if (avRecvFrameData2 == -20012) {
                        long currentTimeMillis = this.mLastSuccessVideoFrameTime > 0 ? System.currentTimeMillis() - this.mLastSuccessVideoFrameTime : 0;
                        logAVBlock(currentTimeMillis);
                        if (currentTimeMillis > 30000) {
                            SDKLog.d(Tag.c, "logAVBlock: ");
                            AVAPIs.avClientCleanVideoBuf(TuTkClient.this.mAVClientIndex);
                            stopRun();
                            TuTkClient.this.mRetry = 0;
                            TuTkClient.this.mFirstConnect = 0;
                            TuTkClient.this.retryInner();
                        }
                        try {
                            Thread.sleep(20);
                            return 0;
                        } catch (InterruptedException unused) {
                            this.mLastSuccessVideoFrameTime = 0;
                            Thread.interrupted();
                            return 0;
                        }
                    } else if (avRecvFrameData2 == -20010 || avRecvFrameData2 == -20016 || avRecvFrameData2 == -20015) {
                        return stopRun();
                    } else {
                        return 0;
                    }
                } else if (avRecvFrameData2 > 1048576) {
                    SDKLog.b(Tag.c, "recv video frame too big to recv:" + avRecvFrameData2);
                    return 0;
                } else {
                    byte[] access$300 = TuTkClient.copyByteData(this.videoBuffer, 0, avRecvFrameData2);
                    if (access$300 == null || TuTkClient.this.mClientListener == null) {
                        return 0;
                    }
                    short byteArrayToShort = Packet.byteArrayToShort(this.frameInfo, 0, TuTkClient.this.isBigOrder);
                    if (byteArrayToShort == 138 || byteArrayToShort == 136 || byteArrayToShort == 1) {
                        TuTkClient.this.mClientListener.onAudioData(DecryptUtil.decryptAudioG711(access$300, TuTkClient.this.mP2PInfo.mShareKey), this.frameInfo);
                    } else {
                        this.mLastSuccessVideoFrameTime = System.currentTimeMillis();
                        if (TuTkClient.this.mP2PInfo == null || !TuTkClient.this.mP2PInfo.isIPC009VideoFullEncryption) {
                            bArr = DecryptUtil.decryptVideo(access$300, TuTkClient.this.mP2PInfo.mShareKey, false, byteArrayToShort);
                        } else {
                            bArr = DecryptUtil.decryptVideoEx(access$300, TuTkClient.this.mP2PInfo.mShareKey);
                        }
                        TuTkClient.this.mClientListener.onVideoData(bArr, this.frameInfo);
                        if (TuTkClient.this.mFirstConnect != 0) {
                            long currentTimeMillis2 = System.currentTimeMillis() - TuTkClient.this.mFirstConnect;
                            TuTkClient.this.mFirstConnect = 0;
                            if (currentTimeMillis2 > 0 && currentTimeMillis2 < 120000) {
                                ArrayList<Pair> arrayList = new ArrayList<>();
                                TuTkClient.this.getP2PInfo(arrayList);
                                for (Pair pair : arrayList) {
                                    if (((String) pair.first).equals("P2P_Mode")) {
                                        String str = (String) pair.second;
                                    }
                                }
                            }
                        }
                    }
                    return 0;
                }
            }
        }

        private void logAVBlock(long j) {
            if (j > this.validDelayTime) {
                this.validDelayTime = j;
            } else if (this.validDelayTime > 3000) {
                TuTkClient.access$708(TuTkClient.this);
                String name = getName();
                SDKLog.b(name, "validDelayTime:" + this.validDelayTime);
                this.validDelayTime = 0;
            }
        }
    }

    private class RDTReceiveThread extends CameraClient.BaseThread {
        static final int MAXSIZE_RECVBUF = 2048;
        static final int READ_DATA_TIME_OUT = 5000;
        /* access modifiers changed from: private */
        public ConditionVariable mConditionVariable = new ConditionVariable();
        byte[] mRecvBuf = new byte[2048];
        St_RDT_Status mSt_RDT_Status = new St_RDT_Status();
        LinkedBlockingQueue<P2PMessage> messagePriorityQueue = new LinkedBlockingQueue<>(40);

        RDTReceiveThread() {
            super("RDTReceive");
        }

        /* access modifiers changed from: package-private */
        public void sendMessage(P2PMessage p2PMessage) {
            if (!this.mIsRunning) {
                handleMessageError(p2PMessage, -1);
                return;
            }
            SDKLog.d(Tag.c, "send msg " + p2PMessage.reqId);
            if (this.messagePriorityQueue.size() >= 40) {
                SDKLog.d(Tag.c, "send msg but full " + p2PMessage.reqId);
                p2PMessage.resp.onError(-3);
            } else if (!this.messagePriorityQueue.add(p2PMessage)) {
                handleMessageError(p2PMessage, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public int stopRun() {
            int stopRun = super.stopRun();
            P2PMessage poll = this.messagePriorityQueue.poll();
            while (poll != null) {
                handleMessageError(poll, -1);
                poll = this.messagePriorityQueue.poll();
            }
            return stopRun;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            super.doRelease();
            this.mConditionVariable.open();
            this.messagePriorityQueue.clear();
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            P2PMessage take = this.messagePriorityQueue.take();
            if (!this.mIsRunning) {
                SDKLog.d(Tag.c, "TuTkClient RDT_Read mRDTIndex < 0");
                handleMessageError(take, -1);
                return stopRun();
            }
            if (take != null) {
                if (!TuTkClient.this.isConnected()) {
                    handleMessageError(take, -1);
                    return 0;
                } else if (TuTkClient.this.mSessionId < 0) {
                    SDKLog.d(Tag.c, "RDT mSessionId <0");
                    handleMessageError(take, TuTkClient.this.mRDTIndex);
                    return 0;
                } else if (TuTkClient.this.mRDTIndex < 0) {
                    createRDT(take);
                    return 0;
                } else {
                    handleMessage(take);
                }
            }
            return 0;
        }

        private void handleMessageError(P2PMessage p2PMessage, int i) {
            if (p2PMessage != null && p2PMessage.resp != null) {
                p2PMessage.resp.onError(i);
            }
        }

        private void checkRDTStatus() {
            if (TuTkClient.this.mRDTIndex >= 0) {
                RDTAPIs.RDT_Status_Check(TuTkClient.this.mRDTIndex, this.mSt_RDT_Status);
                if (this.mSt_RDT_Status.Timeout > this.mSt_RDT_Status.TimeoutThreshold) {
                    SDKLog.d(Tag.c, "RDT_Check timeout abort");
                    RDTAPIs.RDT_Abort(TuTkClient.this.mRDTIndex);
                } else if (TuTkClient.this.mRDTIndex >= 0) {
                    while (this.mSt_RDT_Status.BufSizeInRecvQueue > 0) {
                        RDTAPIs.RDT_Read(TuTkClient.this.mRDTIndex, this.mRecvBuf, 2048, 5000);
                        RDTAPIs.RDT_Status_Check(TuTkClient.this.mRDTIndex, this.mSt_RDT_Status);
                        if (!this.mIsRunning) {
                            return;
                        }
                    }
                }
            }
        }

        private void createRDT(P2PMessage p2PMessage) {
            int IOTC_Session_Get_Free_Channel = IOTCAPIs.IOTC_Session_Get_Free_Channel(TuTkClient.this.mSessionId);
            JSONObject jSONObject = new JSONObject();
            final boolean[] zArr = {false};
            try {
                jSONObject.put("channel", IOTC_Session_Get_Free_Channel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mConditionVariable.close();
            TuTkClient.this.sendIoMessage(512, jSONObject.toString().getBytes(Charset.forName("UTF-8")), new P2pResponse() {
                public void onResponse(int i, byte[] bArr) {
                    SDKLog.d(Tag.c, "RDT_Create: IO Message " + i);
                    zArr[0] = true;
                    RDTReceiveThread.this.mConditionVariable.open();
                }

                public void onError(int i) {
                    SDKLog.d(Tag.c, "RDT_Create: IO Message " + i);
                    if (i == 0) {
                        zArr[0] = true;
                    }
                    RDTReceiveThread.this.mConditionVariable.open();
                }
            });
            this.mConditionVariable.block(3000);
            if (zArr[0]) {
                int unused = TuTkClient.this.mRDTIndex = RDTAPIs.RDT_Create(TuTkClient.this.mSessionId, 10000, IOTC_Session_Get_Free_Channel);
                SDKLog.d(Tag.c, "RDT_Create:" + TuTkClient.this.mRDTIndex + " channel " + IOTC_Session_Get_Free_Channel);
                if (TuTkClient.this.mRDTIndex < 0) {
                    SDKLog.d(Tag.c, "RDT_Create fail:" + TuTkClient.this.mRDTIndex);
                    handleMessageError(p2PMessage, TuTkClient.this.mRDTIndex);
                    return;
                }
                handleMessage(p2PMessage);
                return;
            }
            SDKLog.d(Tag.c, "RDT_Create: but IO fial ");
            handleMessageError(p2PMessage, TuTkClient.this.mRDTIndex);
        }

        private void handleMessage(P2PMessage p2PMessage) {
            SDKLog.d(Tag.c, "take " + p2PMessage.reqId);
            if (TuTkClient.this.mRDTIndex < 0) {
                SDKLog.d(Tag.c, "RDT_Create:" + TuTkClient.getErrorInfo(TuTkClient.this.mRDTIndex));
                handleMessageError(p2PMessage, TuTkClient.this.mRDTIndex);
            }
            checkRDTStatus();
            byte[] tLVData = getTLVData(p2PMessage.reqId, p2PMessage.data);
            int RDT_Write = RDTAPIs.RDT_Write(TuTkClient.this.mRDTIndex, tLVData, tLVData.length);
            if (!this.mIsRunning) {
                SDKLog.b(Tag.c, "TuTkClient RDT_Write mRDTIndex < 0");
                handleMessageError(p2PMessage, -1);
                stopRun();
            }
            if (RDT_Write == tLVData.length) {
                receiverData(p2PMessage);
            } else if (RDT_Write > 0) {
                SDKLog.d(Tag.c, "RDT_Write data not equal data.length");
                if (p2PMessage.resp != null) {
                    p2PMessage.resp.onError(-1);
                }
            } else {
                SDKLog.d(Tag.c, "RDT_Write error" + TuTkClient.getErrorInfo(RDT_Write));
                if (p2PMessage.resp != null) {
                    p2PMessage.resp.onError(RDT_Write);
                }
            }
        }

        private int readData(P2PMessage p2PMessage) {
            int i = 3;
            while (i > 0) {
                if (TuTkClient.this.mRDTIndex < 0) {
                    SDKLog.d(Tag.c, "RDT_Create:" + TuTkClient.getErrorInfo(TuTkClient.this.mRDTIndex));
                    handleMessageError(p2PMessage, TuTkClient.this.mRDTIndex);
                    return TuTkClient.this.mRDTIndex;
                }
                int RDT_Read = RDTAPIs.RDT_Read(TuTkClient.this.mRDTIndex, this.mRecvBuf, 2048, 5000);
                if (!this.mIsRunning) {
                    SDKLog.d(Tag.c, "RDT_Read mRDTIndex < 0");
                    handleMessageError(p2PMessage, -1);
                    stopRun();
                    return -1;
                }
                if (RDT_Read == -10007) {
                    SDKLog.d(Tag.c, "RDT_Read timeout retry:" + i);
                    i += -1;
                    if (i == 0) {
                        RDTAPIs.RDT_Abort(TuTkClient.this.mRDTIndex);
                    }
                }
                return RDT_Read;
            }
            return RDTAPIs.RDT_ER_TIMEOUT;
        }

        private void receiverData(P2PMessage p2PMessage) {
            SDKLog.b(Tag.c, "receiverData:" + p2PMessage.reqId);
            int readData = readData(p2PMessage);
            if (readData > 0) {
                int byteArrayToInt = Packet.byteArrayToInt(this.mRecvBuf, 0, TuTkClient.this.isBigOrder);
                SDKLog.d(Tag.c, "RDT_Read command:" + byteArrayToInt);
                if (byteArrayToInt != p2PMessage.reqId) {
                    SDKLog.d(Tag.c, "TuTkClient RDT_Read command!=p2PMessage.resId");
                    return;
                }
                int byteArrayToInt2 = Packet.byteArrayToInt(this.mRecvBuf, 4, TuTkClient.this.isBigOrder);
                SDKLog.b(Tag.c, "RDT_Read length:" + byteArrayToInt2);
                int byteArrayToInt3 = Packet.byteArrayToInt(this.mRecvBuf, 8, TuTkClient.this.isBigOrder);
                byte[] access$300 = TuTkClient.copyByteData(this.mRecvBuf, 12, readData - 12);
                if (byteArrayToInt3 != 0 || access$300 == null || byteArrayToInt2 < 0) {
                    if (byteArrayToInt3 == 0) {
                        byteArrayToInt3 = -1;
                    }
                    handleMessageError(p2PMessage, byteArrayToInt3);
                } else if (byteArrayToInt2 == 0) {
                    p2PMessage.resp.onResponse(0, (byte[]) null);
                } else {
                    ByteBuffer allocate = ByteBuffer.allocate(2048);
                    allocate.put(access$300);
                    int length = byteArrayToInt2 - access$300.length;
                    while (length > 0) {
                        int readData2 = readData(p2PMessage);
                        if (readData2 > 0) {
                            int remaining = allocate.remaining();
                            if (remaining < readData2) {
                                byte[] access$3002 = TuTkClient.copyByteData(this.mRecvBuf, 0, readData2);
                                if (access$3002 == null) {
                                    handleMessageError(p2PMessage, -1);
                                    return;
                                }
                                allocate.put(access$3002, 0, remaining);
                                allocate.flip();
                                if (p2PMessage.resp != null) {
                                    int i = length > 0 ? length - remaining : length;
                                    byte[] bArr = new byte[allocate.remaining()];
                                    allocate.get(bArr);
                                    p2PMessage.resp.onResponse(i, bArr);
                                    allocate.clear();
                                    allocate.put(access$3002, remaining, readData2 - remaining);
                                }
                                if (length > 0) {
                                    length -= access$3002.length;
                                }
                            } else {
                                byte[] access$3003 = TuTkClient.copyByteData(this.mRecvBuf, 0, readData2);
                                if (access$3003 == null) {
                                    handleMessageError(p2PMessage, -1);
                                    return;
                                }
                                if (length > 0) {
                                    length -= access$3003.length;
                                }
                                allocate.put(access$3003);
                            }
                        } else {
                            SDKLog.d(Tag.c, "TuTkClient RDT_Read In" + TuTkClient.getErrorInfo(readData2));
                            handleMessageError(p2PMessage, readData2);
                            return;
                        }
                    }
                    allocate.flip();
                    if (p2PMessage.resp != null) {
                        byte[] bArr2 = new byte[allocate.remaining()];
                        allocate.get(bArr2);
                        p2PMessage.resp.onResponse(0, bArr2);
                        allocate.clear();
                    }
                }
            } else {
                SDKLog.d(Tag.c, "TuTkClient RDT_Read" + TuTkClient.getErrorInfo(readData));
                handleMessageError(p2PMessage, readData);
            }
        }

        private byte[] getTLVData(int i, byte[] bArr) {
            int length = bArr != null ? bArr.length : 0;
            byte[] bArr2 = new byte[(length + 8)];
            putIntData(bArr2, 0, i);
            putIntData(bArr2, 4, length);
            if (length > 0) {
                System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
            }
            return bArr2;
        }

        private void putIntData(byte[] bArr, int i, int i2) {
            bArr[i] = (byte) i2;
            bArr[i + 1] = (byte) (i2 >>> 8);
            bArr[i + 2] = (byte) (i2 >>> 16);
            bArr[i + 3] = (byte) (i2 >>> 24);
        }
    }

    /* access modifiers changed from: private */
    public static String getErrorInfo(int i) {
        return " error:" + i;
    }

    /* access modifiers changed from: private */
    public void sendIoMessage(final int i, final byte[] bArr, final P2pResponse p2pResponse) {
        if (!isConnected()) {
            SDKLog.d(Tag.c, "send msg but  not connect");
            if (p2pResponse != null) {
                p2pResponse.onError(-2);
            }
        } else if (this.mP2PHandler == null) {
            SDKLog.d(Tag.c, "send msg but   mP2PHandler null");
            if (p2pResponse != null) {
                p2pResponse.onError(-2);
            }
        } else {
            this.mP2PHandler.post(new Runnable() {
                public void run() {
                    int sendIOCtrl = TuTkClient.this.sendIOCtrl(i, bArr);
                    if (p2pResponse != null) {
                        p2pResponse.onError(sendIOCtrl);
                    }
                }
            });
        }
    }

    private static byte[] parseContent(short s, byte b, byte b2, byte b3, int i, boolean z) {
        byte[] bArr = new byte[16];
        System.arraycopy(Packet.shortToByteArray(s, z), 0, bArr, 0, 2);
        bArr[2] = b;
        bArr[3] = b2;
        bArr[4] = b3;
        System.arraycopy(Packet.intToByteArray(i, z), 0, bArr, 12, 4);
        return bArr;
    }
}
