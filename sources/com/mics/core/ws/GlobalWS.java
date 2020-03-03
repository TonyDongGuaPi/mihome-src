package com.mics.core.ws;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mics.constant.API;
import com.mics.core.MiCS;
import com.mics.core.data.request.JoinRoom;
import com.mics.util.GsonUtil;
import com.mics.util.Logger;
import java.io.EOFException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class GlobalWS extends WebSocketListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7757a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 100;
    private static final int e = 200;
    private static final int f = 300;
    private static volatile GlobalWS m;
    private int g = 2;
    private GlobalWSListener h;
    private OkHttpClient.Builder i;
    private OkHttpClient j;
    private Request k;
    private volatile WebSocket l;

    public static GlobalWS a() {
        if (m == null) {
            synchronized (GlobalWS.class) {
                if (m == null) {
                    m = new GlobalWS();
                }
            }
        }
        return m;
    }

    private GlobalWS() {
    }

    public synchronized void b() {
        a(100);
    }

    public void a(GlobalWSListener globalWSListener) {
        this.h = globalWSListener;
    }

    public long a(String str) {
        if (TextUtils.isEmpty(MiCS.a().n())) {
            return -1;
        }
        JoinRoom joinRoom = new JoinRoom();
        JoinRoom.Body body = new JoinRoom.Body();
        body.setConnectionId(MiCS.a().m());
        body.setRoomId(str);
        body.setUserId(MiCS.a().n());
        body.setUserName(MiCS.a().o());
        joinRoom.setBody(body);
        if (this.l == null) {
            return -2;
        }
        this.l.send(GsonUtil.a(joinRoom));
        return joinRoom.getRequestId();
    }

    private void a(int i2) {
        Logger.a("state = %s; event = %s", Integer.valueOf(this.g), Integer.valueOf(i2));
        if (this.g == 2) {
            if (i2 == 100) {
                c();
                this.g = 3;
            }
        } else if (this.g == 1) {
            if (i2 == 300) {
                this.g = 2;
            }
        } else if (this.g != 3) {
        } else {
            if (i2 == 200) {
                this.g = 1;
            } else if (i2 == 300) {
                this.g = 2;
            }
        }
    }

    private void c() {
        Logger.a("WebSocket Open", new Object[0]);
        d();
        this.j.newWebSocket(this.k, this);
    }

    private void d() {
        if (this.i == null) {
            this.i = new OkHttpClient.Builder();
            this.i.pingInterval(30, TimeUnit.SECONDS);
            this.i.readTimeout(0, TimeUnit.MILLISECONDS);
        }
        if (this.j == null) {
            this.j = this.i.build();
        }
        if (this.k == null) {
            Request.Builder builder = new Request.Builder();
            builder.url(API.v());
            this.k = builder.build();
        }
    }

    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        super.onOpen(webSocket, response);
        a(webSocket);
        a(200);
        if (this.h != null) {
            this.h.d();
        }
    }

    public void onMessage(@NonNull WebSocket webSocket, @NonNull String str) {
        super.onMessage(webSocket, str);
        a(200);
        if (this.h != null) {
            this.h.d(str);
        }
    }

    public void onClosed(@NonNull WebSocket webSocket, int i2, @NonNull String str) {
        super.onClosed(webSocket, i2, str);
        a((WebSocket) null);
        Logger.c(str, new Object[0]);
    }

    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable th, @Nullable Response response) {
        super.onFailure(webSocket, th, response);
        a((WebSocket) null);
        a(300);
        if (this.h != null) {
            String simpleName = th.getClass().getSimpleName();
            if (simpleName.equals(SocketTimeoutException.class.getSimpleName())) {
                this.h.e();
            } else if (simpleName.equals(SocketException.class.getSimpleName()) || simpleName.equals(SSLException.class.getSimpleName())) {
                this.h.f();
            } else if (simpleName.equals(EOFException.class.getSimpleName())) {
                this.h.g();
            } else {
                this.h.a(th);
            }
        }
        Logger.d("%s message = %s", th.getClass().getName(), th.getMessage());
    }

    private void a(WebSocket webSocket) {
        this.l = webSocket;
    }
}
