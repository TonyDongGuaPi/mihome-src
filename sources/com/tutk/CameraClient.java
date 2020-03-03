package com.tutk;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.debug.SDKLog;
import com.xiaomi.smarthome.camera.IClientListener;
import com.xiaomi.smarthome.camera.P2pResponse;
import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.camera.XmP2PInfo;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.util.HashMap;
import java.util.List;
import org.cybergarage.http.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CameraClient implements XmCameraP2p {
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
    protected static final int StatusConnected = 1;
    private static final int StatusInitial = 0;
    private static final int StatusNone = -1;
    protected static final String TAG = "Tutk";
    static HashMap<String, CameraClient> sCacheCameraClientMap = new HashMap<>();
    private static int sClinetId;
    /* access modifiers changed from: private */
    public int ThreadIndex;
    volatile boolean isBigOrder;
    /* access modifiers changed from: private */
    public boolean isCanRelease;
    IClientListener mClientListener;
    volatile int mClientStatus;
    long mFirstConnect;
    volatile boolean mIsPaused;
    volatile boolean mIsStartSpeaking;
    private Handler mMainHandler;
    Handler mP2PHandler;
    private HandlerThread mP2PHandlerThread;
    int mPwdTry;
    int mRetry;
    boolean mUsrExpPlanEnabled;
    volatile boolean pwdChange;
    private Runnable releaseCallBack;

    /* access modifiers changed from: protected */
    public abstract void doConnect() throws CameraException;

    /* access modifiers changed from: protected */
    public abstract void doDirection(int i);

    /* access modifiers changed from: protected */
    public abstract void doDisConnect();

    /* access modifiers changed from: protected */
    public abstract void doPause();

    /* access modifiers changed from: protected */
    public abstract void doPlayBack(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void doResume();

    /* access modifiers changed from: protected */
    public abstract void doSendAudioData(byte[] bArr, int i);

    /* access modifiers changed from: protected */
    public abstract void doStopConnect();

    public abstract void getP2PInfo(List<Pair<String, String>> list);

    public abstract String getUid();

    public abstract void sendMsg(int i, int i2, byte[] bArr, P2pResponse p2pResponse);

    static /* synthetic */ int access$608(CameraClient cameraClient) {
        int i = cameraClient.ThreadIndex;
        cameraClient.ThreadIndex = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    public static synchronized void releaseCache(String str) {
        synchronized (CameraClient.class) {
            sCacheCameraClientMap.remove(str);
        }
    }

    CameraClient(XmP2PInfo xmP2PInfo) {
        this.ThreadIndex = 1;
        this.mClientStatus = -1;
        this.isCanRelease = true;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mIsPaused = false;
        this.pwdChange = false;
        this.isBigOrder = false;
        this.mIsStartSpeaking = false;
        this.mFirstConnect = 0;
        this.mUsrExpPlanEnabled = false;
        this.mRetry = 0;
        this.mPwdTry = 0;
        this.releaseCallBack = new Runnable() {
            public void run() {
                if (CameraClient.this.isCanRelease) {
                    CameraClient.this.mClientStatus = 0;
                    CameraClient.this.doStopConnect();
                    CameraClient.releaseCache(CameraClient.this.getUid());
                    CameraClient.this.clearHandleMessages();
                    CameraClient.this.sendEmptyMessage(2);
                    CameraClient.this.sendEmptyMessage(5);
                    CameraClient.this.mClientListener = null;
                    SDKLog.e("Tutk", "release success " + toString());
                    return;
                }
                SDKLog.e("Tutk", "release fail " + toString());
            }
        };
        this.mPwdTry = 3;
    }

    /* access modifiers changed from: package-private */
    public void onConnected() {
        if (this.mClientListener != null) {
            this.mClientListener.onConnected();
        }
    }

    private void onDisConnected() {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (CameraClient.this.mClientListener != null) {
                    CameraClient.this.mClientListener.onDisConnected();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onProgress(final int i) {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (CameraClient.this.mClientListener != null) {
                    CameraClient.this.mClientListener.onProgress(i);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onError(final int i, final String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("onError ");
        sb.append(i);
        sb.append(HTTP.HEADER_LINE_DELIM);
        sb.append(str == null ? " " : str);
        SDKLog.e("Tutk", sb.toString());
        this.mFirstConnect = 0;
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (CameraClient.this.mClientListener != null) {
                    CameraClient.this.mClientListener.onError(i, str);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onRetry(final int i, final String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("onRetry ");
        sb.append(i);
        sb.append(HTTP.HEADER_LINE_DELIM);
        sb.append(str == null ? " " : str);
        SDKLog.e("Tutk", sb.toString());
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (CameraClient.this.mClientListener != null) {
                    CameraClient.this.mClientListener.onRetry(i, str, CameraClient.this.mRetry);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void connectInner() throws CameraException {
        if (this.mClientStatus != 1) {
            doConnect();
        }
    }

    /* access modifiers changed from: private */
    public void disConnectInner() {
        if (this.mP2PHandler != null) {
            this.mP2PHandler.removeMessages(6);
        }
        if (this.mClientStatus != -1) {
            doDisConnect();
            this.mClientStatus = -1;
            onDisConnected();
        }
    }

    private class P2PHandler extends Handler {
        P2PHandler(Looper looper) {
            super(looper);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: byte[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r4) {
            /*
                r3 = this;
                int r0 = r4.what
                r1 = 0
                switch(r0) {
                    case 1: goto L_0x006c;
                    case 2: goto L_0x0066;
                    case 3: goto L_0x0045;
                    case 4: goto L_0x0006;
                    case 5: goto L_0x002e;
                    case 6: goto L_0x0006;
                    case 7: goto L_0x0016;
                    case 8: goto L_0x000f;
                    case 9: goto L_0x0008;
                    case 10: goto L_0x0006;
                    case 11: goto L_0x0033;
                    default: goto L_0x0006;
                }
            L_0x0006:
                goto L_0x007e
            L_0x0008:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.doResume()
                goto L_0x007e
            L_0x000f:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.doPause()
                goto L_0x007e
            L_0x0016:
                java.lang.Object r0 = r4.obj
                if (r0 != 0) goto L_0x001b
                goto L_0x0020
            L_0x001b:
                java.lang.Object r4 = r4.obj
                r1 = r4
                byte[] r1 = (byte[]) r1
            L_0x0020:
                if (r1 != 0) goto L_0x0028
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.doResume()
                goto L_0x007e
            L_0x0028:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.doPlayBack(r1)
                goto L_0x007e
            L_0x002e:
                com.tutk.CameraClient r0 = com.tutk.CameraClient.this
                r0.releaseP2PThread()
            L_0x0033:
                int r0 = r4.arg1
                java.lang.Object r2 = r4.obj
                if (r2 != 0) goto L_0x003a
                goto L_0x003f
            L_0x003a:
                java.lang.Object r4 = r4.obj
                r1 = r4
                byte[] r1 = (byte[]) r1
            L_0x003f:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.sendIOCtrl(r0, r1)
                goto L_0x007e
            L_0x0045:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this     // Catch:{ CameraException -> 0x004b }
                r4.connectInner()     // Catch:{ CameraException -> 0x004b }
                goto L_0x007e
            L_0x004b:
                r4 = move-exception
                java.lang.String r0 = "Tutk"
                java.lang.String r1 = "MSG_CONNECT"
                com.debug.SDKLog.e(r0, r1, r4)
                com.tutk.CameraClient r0 = com.tutk.CameraClient.this
                r0.disConnectInner()
                com.tutk.CameraClient r0 = com.tutk.CameraClient.this
                int r1 = r4.getError()
                java.lang.String r4 = r4.getMessage()
                r0.onError(r1, r4)
                goto L_0x007e
            L_0x0066:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                r4.disConnectInner()
                goto L_0x007e
            L_0x006c:
                int r4 = r4.arg1
                if (r4 <= 0) goto L_0x0076
                com.tutk.CameraClient r0 = com.tutk.CameraClient.this
                r0.doDirection(r4)
                goto L_0x007e
            L_0x0076:
                com.tutk.CameraClient r4 = com.tutk.CameraClient.this
                android.os.Handler r4 = r4.mP2PHandler
                r0 = 1
                r4.removeMessages(r0)
            L_0x007e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tutk.CameraClient.P2PHandler.handleMessage(android.os.Message):void");
        }
    }

    private synchronized void initialP2PThread() {
        if (this.mP2PHandlerThread == null) {
            sClinetId++;
            this.mP2PHandlerThread = new HandlerThread("P2PClient_" + sClinetId);
            this.mP2PHandlerThread.start();
            this.mP2PHandler = new P2PHandler(this.mP2PHandlerThread.getLooper());
        }
    }

    /* access modifiers changed from: private */
    public synchronized void releaseP2PThread() {
        if (this.mP2PHandlerThread != null) {
            SDKLog.d("Tutk", "releaseP2PThread");
            clearHandleMessages();
            this.mP2PHandlerThread.quit();
            this.mP2PHandlerThread = null;
            this.mP2PHandler = null;
        }
    }

    public void setClientListener(IClientListener iClientListener) {
        this.mClientListener = iClientListener;
    }

    public synchronized boolean isConnected() {
        boolean z;
        z = true;
        if (this.mClientStatus < 1) {
            z = false;
        }
        return z;
    }

    public void release(boolean z, IClientListener iClientListener) {
        if (iClientListener != null) {
            if (iClientListener != this.mClientListener) {
                SDKLog.e("Tutk", "release but listener error");
                return;
            } else {
                this.mClientListener = null;
                SDKLog.e("Tutk", "release and rest listener");
            }
        }
        this.isCanRelease = true;
        if (this.mP2PHandler != null) {
            this.mP2PHandler.removeCallbacks(this.releaseCallBack);
        }
        if (!z || !isConnected()) {
            SDKLog.e("Tutk", "release now " + toString());
            this.mClientStatus = 0;
            if (this.mP2PHandler != null) {
                this.mP2PHandler.post(this.releaseCallBack);
                return;
            }
            return;
        }
        SDKLog.e("Tutk", "release " + toString());
        if (this.mP2PHandler != null) {
            this.mP2PHandler.postDelayed(this.releaseCallBack, 10000);
        }
    }

    public void reconnect() {
        SDKLog.e("Tutk", "reconnect");
        this.mRetry = 3;
        this.mFirstConnect = System.currentTimeMillis();
        doStopConnect();
        releaseP2PThread();
        initialP2PThread();
        sendEmptyMessage(2);
        sendEmptyMessage(3);
        onProgress(0);
    }

    /* access modifiers changed from: package-private */
    public void retryInner() {
        doStopConnect();
        releaseP2PThread();
        initialP2PThread();
        sendEmptyMessage(2);
        sendEmptyMessage(3);
        onProgress(0);
    }

    public void pause(IClientListener iClientListener) {
        if (this.mClientListener == null || (iClientListener != null && iClientListener == this.mClientListener)) {
            SDKLog.b("Tutk", "pause");
            this.mIsPaused = true;
            if (this.mP2PHandler != null && isConnected()) {
                this.mP2PHandler.sendEmptyMessage(8);
                return;
            }
            return;
        }
        SDKLog.d("Tutk", "pause but listener error");
    }

    public boolean resume() {
        this.mIsPaused = false;
        if (this.mP2PHandler == null || !isConnected()) {
            SDKLog.b("Tutk", "resume but connect ");
            connect();
            return false;
        }
        this.mP2PHandler.sendEmptyMessage(9);
        return true;
    }

    public void connect() {
        SDKLog.e("Tutk", "connect");
        this.isCanRelease = false;
        this.mIsPaused = false;
        this.mRetry = 3;
        if (!isConnected()) {
            SDKLog.d("Tutk", "not connect");
            this.mFirstConnect = System.currentTimeMillis();
            releaseP2PThread();
            initialP2PThread();
            clearHandleMessages();
            sendEmptyMessage(3);
            onProgress(0);
        } else if (this.pwdChange) {
            this.mFirstConnect = System.currentTimeMillis();
            releaseP2PThread();
            initialP2PThread();
            sendEmptyMessage(2);
            sendEmptyMessage(3);
            onProgress(0);
            SDKLog.d("Tutk", "pwd change need retry");
        } else {
            SDKLog.d("Tutk", "pwd change not change");
            if (this.mClientListener != null) {
                this.mClientListener.onConnected();
            }
            onProgress(90);
            sendEmptyMessage(9);
        }
    }

    public void playBack(byte[] bArr) {
        SDKLog.e("Tutk", "playBack ");
        if (this.mP2PHandler != null) {
            if (!isConnected()) {
                initialP2PThread();
                clearHandleMessages();
                sendEmptyMessage(3);
            }
            this.mP2PHandler.removeMessages(7);
            this.mP2PHandler.obtainMessage(7, bArr).sendToTarget();
        }
    }

    public void direction(byte[] bArr) {
        SDKLog.b("Tutk", "direction ");
        if (this.mP2PHandler != null) {
            Message obtainMessage = this.mP2PHandler.obtainMessage(1, (Object) null);
            try {
                obtainMessage.arg1 = new JSONObject(new String(bArr)).getInt(HomeManager.v);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mP2PHandler.sendMessage(obtainMessage);
        }
    }

    public void startSpeak() {
        this.mIsStartSpeaking = true;
        if (this.mP2PHandler != null) {
            this.mP2PHandler.post(new Runnable() {
                public void run() {
                    CameraClient.this.sendIOCtrl(769, new byte[8]);
                }
            });
        }
    }

    public void stopSpeak() {
        this.mIsStartSpeaking = false;
        if (this.mP2PHandler != null) {
            this.mP2PHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!CameraClient.this.mIsStartSpeaking) {
                        CameraClient.this.sendIOCtrl(768, new byte[8]);
                    }
                }
            }, 250);
        }
    }

    public boolean isPaused() {
        return this.mIsPaused;
    }

    public void sendAudioData(byte[] bArr, int i) {
        doSendAudioData(bArr, i);
    }

    /* access modifiers changed from: protected */
    public void sendEmptyMessage(int i) {
        SDKLog.b("Tutk", "sendEmptyMessage " + i);
        if (this.mP2PHandler != null) {
            this.mP2PHandler.sendEmptyMessage(i);
        }
    }

    /* access modifiers changed from: private */
    public void clearHandleMessages() {
        SDKLog.d("Tutk", "clearHandleMessages");
        if (this.mP2PHandler != null) {
            for (int i = 0; i < 10; i++) {
                this.mP2PHandler.removeMessages(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleAVIOCtrl(final int i, final byte[] bArr) {
        if (this.mClientListener != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    SDKLog.d("Tutk", "handleAVIOCtrl:" + i);
                    if (CameraClient.this.mClientListener != null) {
                        CameraClient.this.mClientListener.onCtrlData(i, bArr);
                    }
                }
            });
        }
    }

    public void runInP2pThread(Runnable runnable) {
        if (this.mP2PHandler != null) {
            this.mP2PHandler.post(runnable);
        }
    }

    abstract class BaseThread extends WorkThread {
        BaseThread(String str) {
            super(str + ":" + CameraClient.this.ThreadIndex);
            CameraClient.access$608(CameraClient.this);
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            SDKLog.b("Tutk", "doInitial " + getName());
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            SDKLog.b("Tutk", "doRelease " + getName());
        }

        /* access modifiers changed from: package-private */
        public int stopRun() {
            this.mIsRunning = false;
            return 0;
        }
    }

    public void playBackSpeed(final byte[] bArr) {
        if (this.mP2PHandler != null) {
            this.mP2PHandler.post(new Runnable() {
                public void run() {
                    CameraClient.this.sendIOCtrl(61443, bArr);
                }
            });
        }
    }
}
