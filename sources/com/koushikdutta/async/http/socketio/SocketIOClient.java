package com.koushikdutta.async.http.socketio;

import android.os.Handler;
import android.text.TextUtils;
import com.codebutler.android_websockets.WebSocketClient;
import com.koushikdutta.http.AsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class SocketIOClient extends EventEmitter {
    boolean b;
    boolean c;
    Handler d;
    ConnectCallback e;
    ErrorCallback f;
    DisconnectCallback g;
    ReconnectCallback h;
    JSONCallback i;
    StringCallback j;
    SocketIOConnection k;
    String l;

    private void a(int i2, String str, Acknowledge acknowledge) {
        this.k.a(i2, this, str, acknowledge);
    }

    public void a(String str, JSONArray jSONArray) {
        b(str, jSONArray, (Acknowledge) null);
    }

    public void a(String str) {
        a(str, (Acknowledge) null);
    }

    public void a(JSONObject jSONObject) {
        a(jSONObject, (Acknowledge) null);
    }

    public void b(String str, JSONArray jSONArray, Acknowledge acknowledge) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            jSONObject.put("args", jSONArray);
            a(5, jSONObject.toString(), acknowledge);
        } catch (Exception unused) {
        }
    }

    public void a(String str, Acknowledge acknowledge) {
        a(3, str, acknowledge);
    }

    public void a(JSONObject jSONObject, Acknowledge acknowledge) {
        a(4, jSONObject.toString(), acknowledge);
    }

    public static void a(String str, ConnectCallback connectCallback, Handler handler) {
        a(new AsyncHttpClient.SocketIORequest(str), connectCallback, handler);
    }

    public static void a(final AsyncHttpClient.SocketIORequest socketIORequest, final ConnectCallback connectCallback, final Handler handler) {
        final SocketIOConnection socketIOConnection = new SocketIOConnection(handler, new AsyncHttpClient(), socketIORequest);
        socketIOConnection.c.add(new SocketIOClient(socketIOConnection, "", new ConnectCallback() {
            public void a(Exception exc, SocketIOClient socketIOClient) {
                if (exc != null || TextUtils.isEmpty(socketIORequest.b())) {
                    socketIOClient.d = handler;
                    if (connectCallback != null) {
                        connectCallback.a(exc, socketIOClient);
                        return;
                    }
                    return;
                }
                socketIOConnection.c.remove(socketIOClient);
                socketIOClient.a(socketIORequest.b(), (ConnectCallback) new ConnectCallback() {
                    public void a(Exception exc, SocketIOClient socketIOClient) {
                        if (connectCallback != null) {
                            connectCallback.a(exc, socketIOClient);
                        }
                    }
                });
            }
        }));
        socketIOConnection.b();
    }

    public void a(ErrorCallback errorCallback) {
        this.f = errorCallback;
    }

    public ErrorCallback a() {
        return this.f;
    }

    public void a(DisconnectCallback disconnectCallback) {
        this.g = disconnectCallback;
    }

    public DisconnectCallback b() {
        return this.g;
    }

    public void a(ReconnectCallback reconnectCallback) {
        this.h = reconnectCallback;
    }

    public ReconnectCallback c() {
        return this.h;
    }

    public void a(JSONCallback jSONCallback) {
        this.i = jSONCallback;
    }

    public JSONCallback d() {
        return this.i;
    }

    public void a(StringCallback stringCallback) {
        this.j = stringCallback;
    }

    public StringCallback e() {
        return this.j;
    }

    private SocketIOClient(SocketIOConnection socketIOConnection, String str, ConnectCallback connectCallback) {
        this.l = str;
        this.k = socketIOConnection;
        this.e = connectCallback;
    }

    public boolean f() {
        return this.b && !this.c && this.k.a();
    }

    public void g() {
        this.k.b(this);
        final DisconnectCallback disconnectCallback = this.g;
        if (disconnectCallback != null) {
            this.d.post(new Runnable() {
                public void run() {
                    disconnectCallback.a((Exception) null);
                }
            });
        }
    }

    public void a(String str, ConnectCallback connectCallback) {
        this.k.a(new SocketIOClient(this.k, str, connectCallback));
    }

    public WebSocketClient h() {
        return this.k.d;
    }
}
