package com.facebook.react.modules.websocket;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.xiaomi.smarthome.download.Downloads;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@ReactModule(hasConstants = false, name = "WebSocketModule")
public final class WebSocketModule extends ReactContextBaseJavaModule {
    public static final String NAME = "WebSocketModule";
    /* access modifiers changed from: private */
    public final Map<Integer, ContentHandler> mContentHandlers = new ConcurrentHashMap();
    private ForwardingCookieHandler mCookieHandler;
    private ReactContext mReactContext;
    /* access modifiers changed from: private */
    public final Map<Integer, WebSocket> mWebSocketConnections = new ConcurrentHashMap();

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public String getName() {
        return NAME;
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    @ReactMethod
    public void connect(String str, @Nullable ReadableArray readableArray, @Nullable ReadableMap readableMap, final int i) {
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap == null || !readableMap.hasKey(Downloads.RequestHeaders.e) || !readableMap.getType(Downloads.RequestHeaders.e).equals(ReadableType.Map)) {
            url.addHeader("origin", getDefaultOrigin(str));
        } else {
            ReadableMap map = readableMap.getMap(Downloads.RequestHeaders.e);
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            if (!map.hasKey("origin")) {
                url.addHeader("origin", getDefaultOrigin(str));
            }
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(nextKey))) {
                    url.addHeader(nextKey, map.getString(nextKey));
                } else {
                    FLog.w(ReactConstants.TAG, "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                String trim = readableArray.getString(i2).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                url.addHeader(IWebSocketAdapter.HEADER_SEC_WEBSOCKET_PROTOCOL, sb.toString());
            }
        }
        build.newWebSocket(url.build(), new WebSocketListener() {
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i), webSocket);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("protocol", response.header(IWebSocketAdapter.HEADER_SEC_WEBSOCKET_PROTOCOL, ""));
                WebSocketModule.this.sendEvent("websocketOpen", createMap);
            }

            public void onClosing(WebSocket webSocket, int i, String str) {
                webSocket.close(i, str);
            }

            public void onClosed(WebSocket webSocket, int i, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putInt("code", i);
                createMap.putString("reason", str);
                WebSocketModule.this.sendEvent("websocketClosed", createMap);
            }

            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i, th.getMessage());
            }

            public void onMessage(WebSocket webSocket, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "text");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(str, createMap);
                } else {
                    createMap.putString("data", str);
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }

            public void onMessage(WebSocket webSocket, ByteString byteString) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "binary");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(byteString, createMap);
                } else {
                    createMap.putString("data", byteString.base64());
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }
        });
        build.dispatcher().executorService().shutdown();
    }

    @ReactMethod
    public void close(int i, String str, int i2) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i2));
        if (webSocket != null) {
            try {
                webSocket.close(i, str);
                this.mWebSocketConnections.remove(Integer.valueOf(i2));
                this.mContentHandlers.remove(Integer.valueOf(i2));
            } catch (Exception e) {
                FLog.e(ReactConstants.TAG, "Could not close WebSocket connection for id " + i2, (Throwable) e);
            }
        }
    }

    @ReactMethod
    public void send(String str, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @ReactMethod
    public void sendBinary(String str, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @ReactMethod
    public void ping(int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString("message", str);
        sendEvent("websocketFailed", createMap);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057 A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094 A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00af A[Catch:{ URISyntaxException -> 0x00c0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getDefaultOrigin(java.lang.String r10) {
        /*
            java.lang.String r0 = ""
            java.net.URI r1 = new java.net.URI     // Catch:{ URISyntaxException -> 0x00c0 }
            r1.<init>(r10)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r2 = r1.getScheme()     // Catch:{ URISyntaxException -> 0x00c0 }
            int r3 = r2.hashCode()     // Catch:{ URISyntaxException -> 0x00c0 }
            r4 = 3804(0xedc, float:5.33E-42)
            r5 = 3
            r6 = -1
            r7 = 2
            r8 = 0
            r9 = 1
            if (r3 == r4) goto L_0x0047
            r4 = 118039(0x1cd17, float:1.65408E-40)
            if (r3 == r4) goto L_0x003c
            r4 = 3213448(0x310888, float:4.503E-39)
            if (r3 == r4) goto L_0x0032
            r4 = 99617003(0x5f008eb, float:2.2572767E-35)
            if (r3 == r4) goto L_0x0028
            goto L_0x0052
        L_0x0028:
            java.lang.String r3 = "https"
            boolean r2 = r2.equals(r3)     // Catch:{ URISyntaxException -> 0x00c0 }
            if (r2 == 0) goto L_0x0052
            r2 = 3
            goto L_0x0053
        L_0x0032:
            java.lang.String r3 = "http"
            boolean r2 = r2.equals(r3)     // Catch:{ URISyntaxException -> 0x00c0 }
            if (r2 == 0) goto L_0x0052
            r2 = 2
            goto L_0x0053
        L_0x003c:
            java.lang.String r3 = "wss"
            boolean r2 = r2.equals(r3)     // Catch:{ URISyntaxException -> 0x00c0 }
            if (r2 == 0) goto L_0x0052
            r2 = 0
            goto L_0x0053
        L_0x0047:
            java.lang.String r3 = "ws"
            boolean r2 = r2.equals(r3)     // Catch:{ URISyntaxException -> 0x00c0 }
            if (r2 == 0) goto L_0x0052
            r2 = 1
            goto L_0x0053
        L_0x0052:
            r2 = -1
        L_0x0053:
            switch(r2) {
                case 0: goto L_0x007d;
                case 1: goto L_0x006b;
                case 2: goto L_0x0057;
                case 3: goto L_0x0057;
                default: goto L_0x0056;
            }     // Catch:{ URISyntaxException -> 0x00c0 }
        L_0x0056:
            goto L_0x008e
        L_0x0057:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.<init>()     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r1.getScheme()     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r2.toString()     // Catch:{ URISyntaxException -> 0x00c0 }
            goto L_0x008e
        L_0x006b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.<init>()     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = "http"
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r2.toString()     // Catch:{ URISyntaxException -> 0x00c0 }
            goto L_0x008e
        L_0x007d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.<init>()     // Catch:{ URISyntaxException -> 0x00c0 }
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = "https"
            r2.append(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r2.toString()     // Catch:{ URISyntaxException -> 0x00c0 }
        L_0x008e:
            int r2 = r1.getPort()     // Catch:{ URISyntaxException -> 0x00c0 }
            if (r2 == r6) goto L_0x00af
            java.lang.String r2 = "%s://%s:%s"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ URISyntaxException -> 0x00c0 }
            r3[r8] = r0     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r1.getHost()     // Catch:{ URISyntaxException -> 0x00c0 }
            r3[r9] = r0     // Catch:{ URISyntaxException -> 0x00c0 }
            int r0 = r1.getPort()     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ URISyntaxException -> 0x00c0 }
            r3[r7] = r0     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = java.lang.String.format(r2, r3)     // Catch:{ URISyntaxException -> 0x00c0 }
            goto L_0x00bf
        L_0x00af:
            java.lang.String r2 = "%s://%s"
            java.lang.Object[] r3 = new java.lang.Object[r7]     // Catch:{ URISyntaxException -> 0x00c0 }
            r3[r8] = r0     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = r1.getHost()     // Catch:{ URISyntaxException -> 0x00c0 }
            r3[r9] = r0     // Catch:{ URISyntaxException -> 0x00c0 }
            java.lang.String r0 = java.lang.String.format(r2, r3)     // Catch:{ URISyntaxException -> 0x00c0 }
        L_0x00bf:
            return r0
        L_0x00c0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to set "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r10 = " as default origin header"
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r0.<init>(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.getDefaultOrigin(java.lang.String):java.lang.String");
    }

    private String getCookie(String str) {
        try {
            List list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return null;
            }
            return (String) list.get(0);
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
