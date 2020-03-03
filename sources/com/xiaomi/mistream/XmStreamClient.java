package com.xiaomi.mistream;

import android.os.Handler;
import android.os.Looper;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.tutk.IOTC.Packet;
import com.xiaomi.smarthome.camera.IClientExListener;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IRDTListener;
import com.xiaomi.smarthome.camera.IRDTPictureListener;
import com.xiaomi.smarthome.camera.IRDTTimelineListener;
import com.xiaomi.smarthome.camera.IRdtDataListener;
import com.xiaomi.smarthome.camera.IXmStreamClient;
import com.xiaomi.smarthome.camera.MissConfig;
import com.xiaomi.smarthome.camera.P2pResponse;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import java.util.HashMap;

public class XmStreamClient implements IXmStreamClient {
    private static final int MSG_CONNECT = 3;
    private static final int MSG_DIRECTION = 1;
    private static final int MSG_DISCONNECT = 2;
    static final int MSG_IO = 11;
    private static final int MSG_LAST = 10;
    private static final int MSG_PAUSE = 8;
    private static final int MSG_PLAYBACK = 7;
    private static final int MSG_RELEASE = 5;
    private static final int MSG_RESUME = 9;
    private static final int MSG_SEND_IO_MESSAGE_REQ_MSG = 6;
    protected static final int StatusConnected = 2;
    protected static final int StatusConnecting = 1;
    protected static final int StatusInitial = 0;
    protected static final int StatusNone = -1;
    protected static final String TAG = "XmStreamClient";
    static HashMap<String, XmStreamClient> sCacheCameraClientMap = new HashMap<>();
    private int ThreadIndex;
    private DeviceStat deviceStat;
    private boolean isAllRdtDataReceived;
    /* access modifiers changed from: private */
    public boolean isAutoAudio;
    /* access modifiers changed from: private */
    public boolean isAutoVideo;
    volatile boolean isBigOrder;
    IClientExListener mClientListener;
    volatile int mClientStatus;
    long mFirstConnect;
    volatile boolean mIsPaused;
    volatile boolean mIsStartSpeaking;
    int mPwdTry;
    IRDTListener mRDTListener;
    IRDTPictureListener mRDTPictureListener;
    IRDTTimelineListener mRDTTimelineListener;
    private IRdtDataListener mRdtDataListener;
    int mRetry;
    boolean mUsrExpPlanEnabled;
    volatile boolean pwdChange;
    private int rdtCommand;
    private byte[] rdtCommandBuffer;
    private int rdtCommandOffset;
    private int rdtCommandSize;
    private int rdtCommandStatus;
    public volatile MIStream stream;

    /* access modifiers changed from: protected */
    public void doStopConnect() {
    }

    private static synchronized void releaseCache(String str) {
        synchronized (XmStreamClient.class) {
            try {
                sCacheCameraClientMap.remove(str);
            } catch (Exception e) {
                LogUtil.b(TAG, "releaseCache:" + e.getLocalizedMessage());
            }
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.xiaomi.mistream.XmStreamClient getInstance(com.xiaomi.smarthome.device.api.DeviceStat r5) {
        /*
            java.lang.Class<com.xiaomi.mistream.XmStreamClient> r0 = com.xiaomi.mistream.XmStreamClient.class
            monitor-enter(r0)
            if (r5 == 0) goto L_0x006e
            java.lang.String r1 = r5.model     // Catch:{ all -> 0x006b }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x006b }
            if (r1 != 0) goto L_0x006e
            java.lang.String r1 = r5.did     // Catch:{ all -> 0x006b }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0016
            goto L_0x006e
        L_0x0016:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r1.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r2 = r5.model     // Catch:{ all -> 0x006b }
            r1.append(r2)     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch:{ all -> 0x006b }
            java.lang.String r2 = r5.did     // Catch:{ all -> 0x006b }
            r1.append(r2)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "XmStreamClient"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r3.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r4 = "mapKey:"
            r3.append(r4)     // Catch:{ all -> 0x006b }
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x006b }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r2, r3)     // Catch:{ all -> 0x006b }
            java.util.HashMap<java.lang.String, com.xiaomi.mistream.XmStreamClient> r2 = sCacheCameraClientMap     // Catch:{ all -> 0x006b }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x006b }
            com.xiaomi.mistream.XmStreamClient r2 = (com.xiaomi.mistream.XmStreamClient) r2     // Catch:{ all -> 0x006b }
            if (r2 != 0) goto L_0x0066
            java.lang.Class<com.xiaomi.mistream.XmStreamClient> r2 = com.xiaomi.mistream.XmStreamClient.class
            monitor-enter(r2)     // Catch:{ all -> 0x006b }
            com.xiaomi.mistream.XmStreamClient r3 = new com.xiaomi.mistream.XmStreamClient     // Catch:{ all -> 0x0063 }
            r3.<init>(r5)     // Catch:{ all -> 0x0063 }
            java.util.HashMap<java.lang.String, com.xiaomi.mistream.XmStreamClient> r4 = sCacheCameraClientMap     // Catch:{ all -> 0x0063 }
            r4.put(r1, r3)     // Catch:{ all -> 0x0063 }
            java.lang.String r5 = r5.model     // Catch:{ all -> 0x0063 }
            com.debug.SDKLog.a(r5)     // Catch:{ all -> 0x0063 }
            monitor-exit(r2)     // Catch:{ all -> 0x0063 }
            r2 = r3
            goto L_0x0069
        L_0x0063:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0063 }
            throw r5     // Catch:{ all -> 0x006b }
        L_0x0066:
            r2.updateInfo()     // Catch:{ all -> 0x006b }
        L_0x0069:
            monitor-exit(r0)
            return r2
        L_0x006b:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        L_0x006e:
            r5 = 0
            monitor-exit(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistream.XmStreamClient.getInstance(com.xiaomi.smarthome.device.api.DeviceStat):com.xiaomi.mistream.XmStreamClient");
    }

    private XmStreamClient(DeviceStat deviceStat2) {
        this.ThreadIndex = 1;
        this.mClientStatus = -1;
        this.mIsPaused = false;
        this.pwdChange = false;
        this.isBigOrder = false;
        this.mIsStartSpeaking = false;
        this.mFirstConnect = 0;
        this.mUsrExpPlanEnabled = false;
        this.mRetry = 0;
        this.mPwdTry = 3;
        this.rdtCommand = 0;
        this.rdtCommandSize = 0;
        this.rdtCommandStatus = 0;
        this.rdtCommandOffset = 0;
        this.isAllRdtDataReceived = false;
        this.isAutoVideo = true;
        this.isAutoAudio = false;
        this.mPwdTry = 3;
        this.deviceStat = deviceStat2;
    }

    /* access modifiers changed from: private */
    public void runInUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void updateInfo() {
        if (this.deviceStat != null) {
            LogUtil.a(TAG, "updateInfo:" + this.deviceStat.model + "" + this.deviceStat.did);
            this.pwdChange = true;
            this.mPwdTry = 3;
        }
    }

    public void setClientListener(IClientExListener iClientExListener) {
        this.mClientListener = iClientExListener;
    }

    /* access modifiers changed from: protected */
    public void doSendAudioData(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("type:");
        sb.append(i);
        sb.append(" data length:");
        sb.append(bArr != null ? Integer.valueOf(bArr.length) : "");
        LogUtil.a(TAG, sb.toString());
        if (bArr != null && bArr.length != 0 && this.stream != null && this.stream.isCallStarted) {
            this.stream.avChannelSendAudio(bArr, i);
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

    public synchronized boolean isConnected() {
        return this.mClientStatus >= 2;
    }

    public boolean isConnecting() {
        return this.mClientStatus >= 1;
    }

    public void release(IClientExListener iClientExListener) {
        if (iClientExListener != null) {
            if (iClientExListener != this.mClientListener) {
                LogUtil.b(TAG, "release but listener error");
                return;
            } else {
                this.mClientListener = null;
                LogUtil.a(TAG, "release and rest listener");
            }
        }
        this.mClientStatus = 0;
        LogUtil.a(TAG, "release " + toString());
    }

    public void streamStart(IMISSListener iMISSListener) {
        streamStart((MissConfig) null, iMISSListener);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void streamStart(com.xiaomi.smarthome.camera.MissConfig r6, final com.xiaomi.smarthome.camera.IMISSListener r7) {
        /*
            r5 = this;
            com.xiaomi.mistream.XmStreamClient$1 r0 = new com.xiaomi.mistream.XmStreamClient$1
            r0.<init>()
            r5.runInUIThread(r0)
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.deviceStat
            r1 = 0
            if (r0 != 0) goto L_0x001a
            r5.mClientStatus = r1
            if (r7 == 0) goto L_0x0019
            com.xiaomi.mistream.XmStreamClient$2 r6 = new com.xiaomi.mistream.XmStreamClient$2
            r6.<init>(r7)
            r5.runInUIThread(r6)
        L_0x0019:
            return
        L_0x001a:
            com.xiaomi.mistream.MIStream r0 = r5.stream     // Catch:{ Exception -> 0x00a1 }
            if (r0 != 0) goto L_0x002f
            monitor-enter(r5)     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.mistream.MIStream r0 = r5.stream     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x002a
            com.xiaomi.mistream.MIStream r0 = new com.xiaomi.mistream.MIStream     // Catch:{ all -> 0x002c }
            r0.<init>()     // Catch:{ all -> 0x002c }
            r5.stream = r0     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r5)     // Catch:{ all -> 0x002c }
            goto L_0x002f
        L_0x002c:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x002c }
            throw r6     // Catch:{ Exception -> 0x00a1 }
        L_0x002f:
            int r0 = r5.mClientStatus     // Catch:{ Exception -> 0x00a1 }
            r2 = 2
            if (r0 != r2) goto L_0x003f
            if (r7 == 0) goto L_0x003e
            com.xiaomi.mistream.XmStreamClient$3 r6 = new com.xiaomi.mistream.XmStreamClient$3     // Catch:{ Exception -> 0x00a1 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00a1 }
            r5.runInUIThread(r6)     // Catch:{ Exception -> 0x00a1 }
        L_0x003e:
            return
        L_0x003f:
            int r0 = r5.mClientStatus     // Catch:{ Exception -> 0x00a1 }
            r2 = 1
            if (r0 != r2) goto L_0x004f
            if (r7 == 0) goto L_0x004e
            com.xiaomi.mistream.XmStreamClient$4 r6 = new com.xiaomi.mistream.XmStreamClient$4     // Catch:{ Exception -> 0x00a1 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00a1 }
            r5.runInUIThread(r6)     // Catch:{ Exception -> 0x00a1 }
        L_0x004e:
            return
        L_0x004f:
            com.xiaomi.mistream.MIStream r0 = r5.stream     // Catch:{ Exception -> 0x00a1 }
            if (r0 == 0) goto L_0x0095
            java.lang.String r0 = "XmStreamClient"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a1 }
            r2.<init>()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = "streamStart:"
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.deviceStat     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r3.model     // Catch:{ Exception -> 0x00a1 }
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.deviceStat     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r3.did     // Catch:{ Exception -> 0x00a1 }
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r0, r2)     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.mistream.MIStream r0 = r5.stream     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.smarthome.device.api.DeviceStat r2 = r5.deviceStat     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r2 = r2.model     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.deviceStat     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r3.did     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.mistream.XmStreamClient$5 r4 = new com.xiaomi.mistream.XmStreamClient$5     // Catch:{ Exception -> 0x00a1 }
            r4.<init>(r7)     // Catch:{ Exception -> 0x00a1 }
            r0.connect(r2, r3, r4, r6)     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.mistream.MIStream r6 = r5.stream     // Catch:{ Exception -> 0x00a1 }
            com.xiaomi.mistream.XmStreamClient$6 r0 = new com.xiaomi.mistream.XmStreamClient$6     // Catch:{ Exception -> 0x00a1 }
            r0.<init>()     // Catch:{ Exception -> 0x00a1 }
            r6.streamHandler = r0     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00c7
        L_0x0095:
            r5.mClientStatus = r1     // Catch:{ Exception -> 0x00a1 }
            if (r7 == 0) goto L_0x00c7
            r6 = -1002(0xfffffffffffffc16, float:NaN)
            java.lang.String r0 = "stream null"
            r7.onFailed(r6, r0)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00c7
        L_0x00a1:
            r6 = move-exception
            r5.mClientStatus = r1
            if (r7 == 0) goto L_0x00ad
            r0 = -1009(0xfffffffffffffc0f, float:NaN)
            java.lang.String r1 = "exception"
            r7.onFailed(r0, r1)
        L_0x00ad:
            java.lang.String r7 = "XmStreamClient"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "exception:"
            r0.append(r1)
            java.lang.String r6 = r6.getLocalizedMessage()
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r7, r6)
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistream.XmStreamClient.streamStart(com.xiaomi.smarthome.camera.MissConfig, com.xiaomi.smarthome.camera.IMISSListener):void");
    }

    public void streamStop(final IMISSListener iMISSListener) {
        this.mClientStatus = 0;
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    LogUtil.a(TAG, "streamStop:" + this.deviceStat.model + ":" + this.deviceStat.did);
                    this.stream.disConnect(this.deviceStat.model, this.deviceStat.did, new IStreamCallback<String>() {
                        public void onSuccess(String str, Object obj) {
                            XmStreamClient.this.mClientStatus = 0;
                            if (iMISSListener != null) {
                                iMISSListener.onSuccess(str, obj);
                            }
                        }

                        public void onFailed(int i, String str) {
                            XmStreamClient.this.mClientStatus = 0;
                            if (iMISSListener != null) {
                                iMISSListener.onFailed(i, str);
                            }
                        }

                        public void onProgress(int i) {
                            if (iMISSListener != null) {
                                iMISSListener.onProgress(i);
                            }
                        }
                    });
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                this.mClientStatus = 0;
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "exception:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamStartCall(IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.speakerStart(this.deviceStat.model, this.deviceStat.did, iMISSListener);
                    this.stream.audioStart(this.deviceStat.model, this.deviceStat.did, (IMISSListener) null);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamStartCall:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamStopCall(IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.speakerStop(this.deviceStat.model, this.deviceStat.did, iMISSListener);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamStopCall:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamResolution(int i, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.setResolution(this.deviceStat.model, this.deviceStat.did, i, iMISSListener);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamResolution:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamDirection(int i, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.setDirection(this.deviceStat.model, this.deviceStat.did, i, iMISSListener);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamDirection:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamToggleAudio(boolean z, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    if (z) {
                        this.stream.audioStop(this.deviceStat.model, this.deviceStat.did, iMISSListener);
                    } else {
                        this.stream.audioStart(this.deviceStat.model, this.deviceStat.did, iMISSListener);
                    }
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamToggleAudio:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamSendMessage(int i, int i2, byte[] bArr, P2pResponse p2pResponse, IMISSListener iMISSListener) {
        try {
            if (this.stream != null) {
                this.stream.avChannelSendRDTData(getTLVData(i, bArr), iMISSListener);
            } else if (iMISSListener != null) {
                iMISSListener.onFailed(-1008, "stream null");
            }
        } catch (Exception e) {
            if (iMISSListener != null) {
                iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
            }
            LogUtil.b(TAG, "streamSendMessage:" + e.getLocalizedMessage());
        }
    }

    public void streamRDTMessage(int i, int i2, byte[] bArr, P2pResponse p2pResponse, IMISSListener iMISSListener) {
        try {
            if (this.stream != null) {
                this.stream.avChannelSendRDTData(getTLVData(i, bArr), iMISSListener);
            } else if (iMISSListener != null) {
                iMISSListener.onFailed(-1008, "stream null");
            }
        } catch (Exception e) {
            if (iMISSListener != null) {
                iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
            }
            LogUtil.b(TAG, "streamSendMessage:" + e.getLocalizedMessage());
        }
    }

    public void sendRdtCmd(byte[] bArr, IMISSListener iMISSListener) {
        try {
            if (this.stream != null) {
                this.stream.avChannelSendRDTData(bArr, iMISSListener);
            } else if (iMISSListener != null) {
                iMISSListener.onFailed(-1008, "stream null");
            }
        } catch (Exception e) {
            if (iMISSListener != null) {
                iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
            }
            LogUtil.b(TAG, "streamSendMessage:" + e.getLocalizedMessage());
        }
    }

    public void streamCmdMessage(int i, String str, final IMISSListener iMISSListener) {
        if (this.stream != null) {
            this.stream.missCommandSend(i, str, new IStreamCallback<Integer>() {
                public void onSuccess(Integer num, Object obj) {
                    if (iMISSListener != null) {
                        iMISSListener.onSuccess(String.valueOf(num), obj);
                    }
                }

                public void onFailed(int i, String str) {
                    if (iMISSListener != null) {
                        iMISSListener.onFailed(i, str);
                    }
                }

                public void onProgress(int i) {
                    if (iMISSListener != null) {
                        iMISSListener.onProgress(i);
                    }
                }
            });
        }
    }

    public void streamPlaybackSpeed(String str, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.avPlaybackSpeed(this.deviceStat.model, this.deviceStat.did, str, iMISSListener);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamPlaybackSpeed:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamPlayback(String str, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    this.stream.avPlayback(this.deviceStat.model, this.deviceStat.did, str, iMISSListener);
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamPlayback:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamToggleRemoteVideo(boolean z, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    int i = this.stream.toggleRemoteVideo(z);
                    if (iMISSListener == null) {
                        return;
                    }
                    if (i >= 0) {
                        if (z) {
                            this.mIsPaused = false;
                        } else {
                            this.mIsPaused = true;
                        }
                        iMISSListener.onSuccess(String.valueOf(i), (Object) null);
                        return;
                    }
                    iMISSListener.onFailed(i, "doStreamToggleRemoteVideo");
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamToggleRemoteVideo:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void streamToggleRemoteAudio(boolean z, IMISSListener iMISSListener) {
        if (this.deviceStat != null) {
            try {
                if (this.stream != null) {
                    int i = this.stream.toggleRemoteAudio(z);
                    if (iMISSListener == null) {
                        return;
                    }
                    if (i >= 0) {
                        iMISSListener.onSuccess(String.valueOf(i), (Object) null);
                    } else {
                        iMISSListener.onFailed(i, "doStreamToggleRemoteAudio");
                    }
                } else if (iMISSListener != null) {
                    iMISSListener.onFailed(-1008, "stream null");
                }
            } catch (Exception e) {
                if (iMISSListener != null) {
                    iMISSListener.onFailed(-1009, LogCategory.CATEGORY_EXCEPTION);
                }
                LogUtil.b(TAG, "streamToggleRemoteAudio:" + e.getLocalizedMessage());
            }
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1001, "deviceStat null");
        }
    }

    public void setRDTTimelineListener(IRDTTimelineListener iRDTTimelineListener) {
        this.mRDTTimelineListener = iRDTTimelineListener;
    }

    public void setRDTPictureListener(IRDTPictureListener iRDTPictureListener) {
        this.mRDTPictureListener = iRDTPictureListener;
    }

    public void setRDTListener(IRDTListener iRDTListener) {
        this.mRDTListener = iRDTListener;
    }

    public void setRdtDataListener(IRdtDataListener iRdtDataListener) {
        this.mRdtDataListener = iRdtDataListener;
    }

    public void streamGetDeviceInfo(IMISSListener iMISSListener) {
        if (this.stream != null) {
            this.stream.getDeviceInfo(iMISSListener);
        }
    }

    private void parseRDTDeviceInfo(byte[] bArr) {
        try {
            new String(bArr);
        } catch (Exception unused) {
        }
    }

    public void parseRDTResponseData(byte[] bArr) {
        if (bArr != null) {
            if (this.mRdtDataListener != null) {
                this.mRdtDataListener.onRdtDataReceived(bArr);
            }
            try {
                if (this.rdtCommandSize == 0 && this.rdtCommand == 0) {
                    this.rdtCommandBuffer = null;
                    this.rdtCommandSize = 0;
                    this.rdtCommandOffset = 0;
                    this.rdtCommandStatus = 0;
                    this.rdtCommand = 0;
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(bArr, 0, bArr2, 0, 4);
                    int byteArrayToInt = Packet.byteArrayToInt(bArr2, 0, false);
                    if (byteArrayToInt <= 0) {
                        LogUtil.b(TAG, "command invalid:" + byteArrayToInt);
                        return;
                    }
                    this.rdtCommand = byteArrayToInt;
                    byte[] bArr3 = new byte[4];
                    System.arraycopy(bArr, 4, bArr3, 0, 4);
                    int byteArrayToInt2 = Packet.byteArrayToInt(bArr3, 0, false);
                    if (this.rdtCommand > 0 && this.rdtCommand <= 65535 && byteArrayToInt2 > 0) {
                        if (byteArrayToInt2 < 20971520) {
                            this.rdtCommandSize = byteArrayToInt2;
                            byte[] bArr4 = new byte[4];
                            System.arraycopy(bArr, 8, bArr4, 0, 4);
                            int byteArrayToInt3 = Packet.byteArrayToInt(bArr4, 0, false);
                            this.rdtCommandStatus = byteArrayToInt3;
                            LogUtil.a(TAG, "rawDataSize:" + byteArrayToInt2);
                            int length = bArr.length - 12;
                            this.rdtCommandBuffer = new byte[byteArrayToInt2];
                            System.arraycopy(bArr, 12, this.rdtCommandBuffer, 0, length);
                            this.rdtCommandOffset = length;
                            if (length >= this.rdtCommandSize) {
                                this.isAllRdtDataReceived = true;
                            }
                            LogUtil.a(TAG, "receive_rdt_data command:" + byteArrayToInt + " size:" + byteArrayToInt2 + " status:" + byteArrayToInt3);
                        }
                    }
                    this.rdtCommand = 0;
                    this.rdtCommandSize = 0;
                    LogUtil.b(TAG, "size invalid:" + byteArrayToInt2);
                    return;
                }
                System.arraycopy(bArr, 0, this.rdtCommandBuffer, this.rdtCommandOffset, bArr.length);
                this.rdtCommandOffset += bArr.length;
                if (this.rdtCommandOffset >= this.rdtCommandSize) {
                    this.isAllRdtDataReceived = true;
                }
                if (this.rdtCommand > 0 && this.rdtCommandSize > 0 && this.isAllRdtDataReceived) {
                    if (this.rdtCommand == 9) {
                        byte[] bArr5 = new byte[this.rdtCommandBuffer.length];
                        System.arraycopy(this.rdtCommandBuffer, 0, bArr5, 0, this.rdtCommandBuffer.length);
                        parseRDTDeviceInfo(bArr5);
                    } else if (this.rdtCommand == 6) {
                        if (this.mRDTTimelineListener != null) {
                            LogUtil.a(TAG, "rdtCommandBuffer:" + this.rdtCommandBuffer.length);
                            final byte[] bArr6 = new byte[this.rdtCommandBuffer.length];
                            System.arraycopy(this.rdtCommandBuffer, 0, bArr6, 0, this.rdtCommandBuffer.length);
                            runInUIThread(new Runnable() {
                                public void run() {
                                    XmStreamClient.this.mRDTTimelineListener.onTimelineDataReceived(bArr6);
                                }
                            });
                        }
                    } else if (this.rdtCommand == 5) {
                        if (this.mRDTPictureListener != null) {
                            final byte[] bArr7 = new byte[this.rdtCommandBuffer.length];
                            System.arraycopy(this.rdtCommandBuffer, 0, bArr7, 0, this.rdtCommandBuffer.length);
                            runInUIThread(new Runnable() {
                                public void run() {
                                    XmStreamClient.this.mRDTPictureListener.onPictureDataReceived(bArr7);
                                }
                            });
                        }
                    } else if (this.mRDTListener != null) {
                        byte[] bArr8 = new byte[this.rdtCommandBuffer.length];
                        System.arraycopy(this.rdtCommandBuffer, 0, bArr8, 0, this.rdtCommandBuffer.length);
                        this.mRDTListener.onRDTDataReceived(bArr8);
                    }
                    this.rdtCommandBuffer = null;
                    this.rdtCommandSize = 0;
                    this.rdtCommandOffset = 0;
                    this.rdtCommandStatus = 0;
                    this.rdtCommand = 0;
                    this.isAllRdtDataReceived = false;
                }
            } catch (Exception e) {
                LogUtil.b(TAG, "parseRDTResponseData:" + e.getLocalizedMessage() + " rdtCommand:" + this.rdtCommand);
                this.rdtCommandBuffer = null;
                this.rdtCommandSize = 0;
                this.rdtCommandOffset = 0;
                this.rdtCommandStatus = 0;
                this.rdtCommand = 0;
                this.isAllRdtDataReceived = false;
            }
        }
    }

    public boolean isPaused() {
        return this.mIsPaused;
    }

    public void sendAudioData(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            LogUtil.b(TAG, "sendAudioData data null");
        } else {
            doSendAudioData(bArr, i);
        }
    }

    public boolean getAutoVideo() {
        return this.isAutoVideo;
    }

    public void setAutoVideo(boolean z) {
        this.isAutoVideo = z;
    }

    public boolean getAutoAudio() {
        return this.isAutoAudio;
    }

    public void setAutoAudio(boolean z) {
        this.isAutoAudio = z;
    }

    public void markCallStarted(boolean z) {
        if (this.stream != null) {
            this.stream.markCallStart(z);
        }
    }
}
