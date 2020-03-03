package com.taobao.weex.appfram.websocket;

import android.os.Looper;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.utils.WXLogUtils;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WebSocketModule extends WXSDKEngine.DestroyableModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String KEY_CODE = "code";
    private static final String KEY_DATA = "data";
    private static final String KEY_REASON = "reason";
    private static final String KEY_WAS_CLEAN = "wasClean";
    private static final String TAG = "WebSocketModule";
    private WebSocketEventListener eventListener;
    private IWebSocketAdapter webSocketAdapter;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2704164229884472619L, "com/taobao/weex/appfram/websocket/WebSocketModule", 53);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ IWebSocketAdapter access$500(WebSocketModule webSocketModule) {
        boolean[] $jacocoInit = $jacocoInit();
        IWebSocketAdapter iWebSocketAdapter = webSocketModule.webSocketAdapter;
        $jacocoInit[50] = true;
        return iWebSocketAdapter;
    }

    static /* synthetic */ IWebSocketAdapter access$502(WebSocketModule webSocketModule, IWebSocketAdapter iWebSocketAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        webSocketModule.webSocketAdapter = iWebSocketAdapter;
        $jacocoInit[51] = true;
        return iWebSocketAdapter;
    }

    static /* synthetic */ WebSocketEventListener access$602(WebSocketModule webSocketModule, WebSocketEventListener webSocketEventListener) {
        boolean[] $jacocoInit = $jacocoInit();
        webSocketModule.eventListener = webSocketEventListener;
        $jacocoInit[52] = true;
        return webSocketEventListener;
    }

    public WebSocketModule() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        WXLogUtils.e("WebSocketModule", "create new instance");
        $jacocoInit[1] = true;
    }

    @JSMethod(uiThread = false)
    public void WebSocket(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.webSocketAdapter == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            WXLogUtils.w("WebSocketModule", "close");
            $jacocoInit[4] = true;
            this.webSocketAdapter.close(WebSocketCloseCodes.CLOSE_GOING_AWAY.getCode(), WebSocketCloseCodes.CLOSE_GOING_AWAY.name());
            $jacocoInit[5] = true;
        }
        this.webSocketAdapter = this.mWXSDKInstance.getWXWebSocketAdapter();
        $jacocoInit[6] = true;
        if (reportErrorIfNoAdapter()) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            this.eventListener = new WebSocketEventListener(this, (AnonymousClass1) null);
            $jacocoInit[9] = true;
            this.webSocketAdapter.connect(str, str2, this.eventListener);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    @JSMethod(uiThread = false)
    public void send(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (reportErrorIfNoAdapter()) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            this.webSocketAdapter.send(str);
            $jacocoInit[14] = true;
        }
        $jacocoInit[15] = true;
    }

    @JSMethod(uiThread = false)
    public void close(String str, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (reportErrorIfNoAdapter()) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            int code = WebSocketCloseCodes.CLOSE_NORMAL.getCode();
            if (str == null) {
                $jacocoInit[18] = true;
            } else {
                try {
                    $jacocoInit[19] = true;
                    i = Integer.parseInt(str);
                    $jacocoInit[20] = true;
                } catch (NumberFormatException unused) {
                    $jacocoInit[21] = true;
                }
                this.webSocketAdapter.close(i, str2);
                $jacocoInit[22] = true;
            }
            i = code;
            this.webSocketAdapter.close(i, str2);
            $jacocoInit[22] = true;
        }
        $jacocoInit[23] = true;
    }

    @JSMethod(uiThread = false)
    public void onopen(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.eventListener == null) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            WebSocketEventListener.access$102(this.eventListener, jSCallback);
            $jacocoInit[26] = true;
        }
        $jacocoInit[27] = true;
    }

    @JSMethod(uiThread = false)
    public void onmessage(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.eventListener == null) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            WebSocketEventListener.access$202(this.eventListener, jSCallback);
            $jacocoInit[30] = true;
        }
        $jacocoInit[31] = true;
    }

    @JSMethod(uiThread = false)
    public void onclose(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.eventListener == null) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            WebSocketEventListener.access$302(this.eventListener, jSCallback);
            $jacocoInit[34] = true;
        }
        $jacocoInit[35] = true;
    }

    @JSMethod(uiThread = false)
    public void onerror(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.eventListener == null) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            WebSocketEventListener.access$402(this.eventListener, jSCallback);
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        AnonymousClass1 r1 = new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WebSocketModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-6440536151618593326L, "com/taobao/weex/appfram/websocket/WebSocketModule$1", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                WXLogUtils.w("WebSocketModule", "close session with instance id " + this.this$0.mWXSDKInstance.getInstanceId());
                $jacocoInit[1] = true;
                if (WebSocketModule.access$500(this.this$0) == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    WebSocketModule.access$500(this.this$0).destroy();
                    $jacocoInit[4] = true;
                }
                WebSocketModule.access$502(this.this$0, (IWebSocketAdapter) null);
                $jacocoInit[5] = true;
                WebSocketModule.access$602(this.this$0, (WebSocketEventListener) null);
                $jacocoInit[6] = true;
            }
        };
        $jacocoInit[40] = true;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            $jacocoInit[41] = true;
            WXBridgeManager.getInstance().post(r1);
            $jacocoInit[42] = true;
        } else {
            r1.run();
            $jacocoInit[43] = true;
        }
        $jacocoInit[44] = true;
    }

    private boolean reportErrorIfNoAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.webSocketAdapter == null) {
            if (this.eventListener == null) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                this.eventListener.onError("No implementation found for IWebSocketAdapter");
                $jacocoInit[47] = true;
            }
            WXLogUtils.e("WebSocketModule", "No implementation found for IWebSocketAdapter");
            $jacocoInit[48] = true;
            return true;
        }
        $jacocoInit[49] = true;
        return false;
    }

    private class WebSocketEventListener implements IWebSocketAdapter.EventListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private JSCallback onClose;
        private JSCallback onError;
        private JSCallback onMessage;
        private JSCallback onOpen;
        final /* synthetic */ WebSocketModule this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-850590485166778984L, "com/taobao/weex/appfram/websocket/WebSocketModule$WebSocketEventListener", 30);
            $jacocoData = a2;
            return a2;
        }

        private WebSocketEventListener(WebSocketModule webSocketModule) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = webSocketModule;
            $jacocoInit[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ WebSocketEventListener(WebSocketModule webSocketModule, AnonymousClass1 r3) {
            this(webSocketModule);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[25] = true;
        }

        static /* synthetic */ JSCallback access$102(WebSocketEventListener webSocketEventListener, JSCallback jSCallback) {
            boolean[] $jacocoInit = $jacocoInit();
            webSocketEventListener.onOpen = jSCallback;
            $jacocoInit[26] = true;
            return jSCallback;
        }

        static /* synthetic */ JSCallback access$202(WebSocketEventListener webSocketEventListener, JSCallback jSCallback) {
            boolean[] $jacocoInit = $jacocoInit();
            webSocketEventListener.onMessage = jSCallback;
            $jacocoInit[27] = true;
            return jSCallback;
        }

        static /* synthetic */ JSCallback access$302(WebSocketEventListener webSocketEventListener, JSCallback jSCallback) {
            boolean[] $jacocoInit = $jacocoInit();
            webSocketEventListener.onClose = jSCallback;
            $jacocoInit[28] = true;
            return jSCallback;
        }

        static /* synthetic */ JSCallback access$402(WebSocketEventListener webSocketEventListener, JSCallback jSCallback) {
            boolean[] $jacocoInit = $jacocoInit();
            webSocketEventListener.onError = jSCallback;
            $jacocoInit[29] = true;
            return jSCallback;
        }

        public void onOpen() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.onOpen == null) {
                $jacocoInit[1] = true;
            } else {
                $jacocoInit[2] = true;
                this.onOpen.invoke(new HashMap(0));
                $jacocoInit[3] = true;
            }
            $jacocoInit[4] = true;
        }

        public void onMessage(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.onMessage == null) {
                $jacocoInit[5] = true;
            } else {
                $jacocoInit[6] = true;
                HashMap hashMap = new HashMap(1);
                $jacocoInit[7] = true;
                hashMap.put("data", str);
                $jacocoInit[8] = true;
                this.onMessage.invokeAndKeepAlive(hashMap);
                $jacocoInit[9] = true;
            }
            $jacocoInit[10] = true;
        }

        public void onClose(int i, String str, boolean z) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.onClose == null) {
                $jacocoInit[11] = true;
            } else {
                $jacocoInit[12] = true;
                HashMap hashMap = new HashMap(3);
                $jacocoInit[13] = true;
                hashMap.put("code", Integer.valueOf(i));
                $jacocoInit[14] = true;
                hashMap.put("reason", str);
                $jacocoInit[15] = true;
                hashMap.put(WebSocketModule.KEY_WAS_CLEAN, Boolean.valueOf(z));
                $jacocoInit[16] = true;
                this.onClose.invoke(hashMap);
                $jacocoInit[17] = true;
            }
            $jacocoInit[18] = true;
        }

        public void onError(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.onError == null) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
                HashMap hashMap = new HashMap(1);
                $jacocoInit[21] = true;
                hashMap.put("data", str);
                $jacocoInit[22] = true;
                this.onError.invokeAndKeepAlive(hashMap);
                $jacocoInit[23] = true;
            }
            $jacocoInit[24] = true;
        }
    }
}
