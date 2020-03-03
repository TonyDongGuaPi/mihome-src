package com.codebutler.android_websockets;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.codebutler.android_websockets.HybiParser;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import miuipub.security.DigestUtils;
import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.LineParser;

public class WebSocketClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5148a = "WebSocketClient";
    private static TrustManager[] b;
    /* access modifiers changed from: private */
    public final Object c = new Object();
    /* access modifiers changed from: private */
    public URI d;
    /* access modifiers changed from: private */
    public Listener e;
    /* access modifiers changed from: private */
    public Socket f;
    private Thread g;
    private HandlerThread h;
    private Handler i;
    /* access modifiers changed from: private */
    public List<BasicNameValuePair> j;
    /* access modifiers changed from: private */
    public HybiParser k;
    /* access modifiers changed from: private */
    public boolean l;

    public interface Listener {
        void a(int i, String str);

        void a(Exception exc);

        void a(String str);

        void a(List<Header> list);

        void a(byte[] bArr);
    }

    public WebSocketClient(URI uri, Listener listener, List<BasicNameValuePair> list) {
        this.d = uri;
        this.e = listener;
        this.j = list;
        this.l = false;
        this.k = new HybiParser(this);
        this.h = new HandlerThread("websocket-thread");
        this.h.start();
        this.i = new Handler(this.h.getLooper());
    }

    public static void a(TrustManager[] trustManagerArr) {
        b = trustManagerArr;
    }

    public Listener a() {
        return this.e;
    }

    public void b() {
        if (this.g == null || !this.g.isAlive()) {
            this.g = new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                    java.lang.Thread.sleep(1000);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
                    com.codebutler.android_websockets.WebSocketClient.c(r13.f5149a).connect(new java.net.InetSocketAddress(com.codebutler.android_websockets.WebSocketClient.a(r13.f5149a).getHost(), r1), 10000);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:79:0x0330, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:80:0x0331, code lost:
                    android.util.Log.d(com.codebutler.android_websockets.WebSocketClient.f5148a, "Websocket SSL error!", r1);
                    com.codebutler.android_websockets.WebSocketClient.d(r13.f5149a).a(0, "SSL");
                    com.codebutler.android_websockets.WebSocketClient.a(r13.f5149a, false);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:81:0x0349, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:82:0x034a, code lost:
                    android.util.Log.d(com.codebutler.android_websockets.WebSocketClient.f5148a, "WebSocket EOF!", r1);
                    com.codebutler.android_websockets.WebSocketClient.d(r13.f5149a).a(0, "EOF");
                    com.codebutler.android_websockets.WebSocketClient.a(r13.f5149a, false);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x01db A[Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330, EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }] */
                /* JADX WARNING: Removed duplicated region for block: B:49:0x024f A[Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330, EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }] */
                /* JADX WARNING: Removed duplicated region for block: B:72:0x02f9 A[Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330, EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }] */
                /* JADX WARNING: Removed duplicated region for block: B:79:0x0330 A[ExcHandler: SSLException (r1v4 'e' javax.net.ssl.SSLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
                /* JADX WARNING: Removed duplicated region for block: B:81:0x0349 A[ExcHandler: EOFException (r1v0 'e' java.io.EOFException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r13 = this;
                        r0 = 0
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r1 = r1.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        int r1 = r1.getPort()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2 = -1
                        if (r1 == r2) goto L_0x0019
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r1 = r1.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        int r1 = r1.getPort()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        goto L_0x0044
                    L_0x0019:
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r1 = r1.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r1 = r1.getScheme()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "wss"
                        boolean r1 = r1.equals(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r1 != 0) goto L_0x0042
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r1 = r1.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r1 = r1.getScheme()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "https"
                        boolean r1 = r1.equals(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r1 == 0) goto L_0x003f
                        goto L_0x0042
                    L_0x003f:
                        r1 = 80
                        goto L_0x0044
                    L_0x0042:
                        r1 = 443(0x1bb, float:6.21E-43)
                    L_0x0044:
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r2 = r2.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.getPath()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r2 == 0) goto L_0x0057
                        java.lang.String r2 = "/"
                        goto L_0x0061
                    L_0x0057:
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r2 = r2.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.getPath()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x0061:
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r3 = r3.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r3.getQuery()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r3 != 0) goto L_0x008f
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r3.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r3.append(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "?"
                        r3.append(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r2 = r2.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.getQuery()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r3.append(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r3.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x008f:
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r3 = r3.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r3.getScheme()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r4 = "wss"
                        boolean r3 = r3.equals(r4)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r3 == 0) goto L_0x00a5
                        java.lang.String r3 = "https"
                        goto L_0x00a7
                    L_0x00a5:
                        java.lang.String r3 = "http"
                    L_0x00a7:
                        java.net.URI r4 = new java.net.URI     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r5.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = "//"
                        r5.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r6 = r6.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r6.getHost()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r5.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r5 = r5.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r6 = 0
                        r4.<init>(r3, r5, r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r3 = r3.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r3.getScheme()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r5 = "wss"
                        boolean r3 = r3.equals(r5)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r3 != 0) goto L_0x00f3
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r3 = r3.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r3.getScheme()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r5 = "https"
                        boolean r3 = r3.equals(r5)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r3 == 0) goto L_0x00ee
                        goto L_0x00f3
                    L_0x00ee:
                        javax.net.SocketFactory r3 = javax.net.SocketFactory.getDefault()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        goto L_0x00f9
                    L_0x00f3:
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        javax.net.ssl.SSLSocketFactory r3 = r3.f()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x00f9:
                        com.codebutler.android_websockets.WebSocketClient r5 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.Socket r3 = r3.createSocket()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.Socket unused = r5.f = r3     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r3 = 10000(0x2710, float:1.4013E-41)
                        r5 = 403(0x193, float:5.65E-43)
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.Socket r6 = r6.f     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.InetSocketAddress r7 = new java.net.InetSocketAddress     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        com.codebutler.android_websockets.WebSocketClient r8 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.URI r8 = r8.d     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.lang.String r8 = r8.getHost()     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        r7.<init>(r8, r1)     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        r6.connect(r7, r3)     // Catch:{ Exception -> 0x011f, EOFException -> 0x0349, SSLException -> 0x0330 }
                        goto L_0x013c
                    L_0x011f:
                        r6 = 1000(0x3e8, double:4.94E-321)
                        java.lang.Thread.sleep(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.Socket r6 = r6.f     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.InetSocketAddress r7 = new java.net.InetSocketAddress     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        com.codebutler.android_websockets.WebSocketClient r8 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.net.URI r8 = r8.d     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        java.lang.String r8 = r8.getHost()     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        r7.<init>(r8, r1)     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                        r6.connect(r7, r3)     // Catch:{ Exception -> 0x0312, EOFException -> 0x0349, SSLException -> 0x0330 }
                    L_0x013c:
                        java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.Socket r3 = r3.f     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.<init>(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r3.e()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r6.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r7 = "GET "
                        r6.append(r7)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r6.append(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = " HTTP/1.1\r\n"
                        r6.append(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r6.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "Upgrade: websocket\r\n"
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "Connection: Upgrade\r\n"
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = "Host: "
                        r2.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.URI r6 = r6.d     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r6.getHost()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = "\r\n"
                        r2.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = "Origin: "
                        r2.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r4 = r4.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.append(r4)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r4 = "\r\n"
                        r2.append(r4)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r4 = "Sec-WebSocket-Key: "
                        r2.append(r4)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.append(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r4 = "\r\n"
                        r2.append(r4)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "Sec-WebSocket-Version: 13\r\n"
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.util.List r2 = r2.j     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r4 = 1
                        if (r2 == 0) goto L_0x020a
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.util.List r2 = r2.j     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.util.Iterator r2 = r2.iterator()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x01e5:
                        boolean r6 = r2.hasNext()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r6 == 0) goto L_0x020a
                        java.lang.Object r6 = r2.next()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        org.apache.http.NameValuePair r6 = (org.apache.http.NameValuePair) r6     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r7 = "%s: %s\r\n"
                        r8 = 2
                        java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r9 = r6.getName()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r8[r0] = r9     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r6.getValue()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r8[r4] = r6     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = java.lang.String.format(r7, r8)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.print(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        goto L_0x01e5
                    L_0x020a:
                        java.lang.String r2 = "\r\n"
                        r1.print(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.flush()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.HybiParser$HappyDataInputStream r1 = new com.codebutler.android_websockets.HybiParser$HappyDataInputStream     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.net.Socket r2 = r2.f     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.io.InputStream r2 = r2.getInputStream()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.<init>(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        long r6 = java.lang.System.currentTimeMillis()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r8 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r8 = r8.a((com.codebutler.android_websockets.HybiParser.HappyDataInputStream) r1)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        org.apache.http.StatusLine r2 = r2.b((java.lang.String) r8)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r8 = "WebSocketClient"
                        java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r9.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r10 = "readLine costs "
                        r9.append(r10)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        long r10 = java.lang.System.currentTimeMillis()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r12 = 0
                        long r10 = r10 - r6
                        r9.append(r10)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r9.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        android.util.Log.d(r8, r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r2 == 0) goto L_0x02f9
                        int r6 = r2.getStatusCode()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r6 != r5) goto L_0x0267
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.e     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "握手失败"
                        r1.a(r5, r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean unused = r1.l = r0     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        return
                    L_0x0267:
                        int r5 = r2.getStatusCode()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r6 = 101(0x65, float:1.42E-43)
                        if (r5 != r6) goto L_0x02eb
                        java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x0274:
                        com.codebutler.android_websockets.WebSocketClient r5 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r5 = r5.a((com.codebutler.android_websockets.HybiParser.HappyDataInputStream) r1)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r6 != 0) goto L_0x02d3
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        org.apache.http.Header r5 = r6.c((java.lang.String) r5)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.add(r5)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r5.getName()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r7 = "Sec-WebSocket-Accept"
                        boolean r6 = r6.equals(r7)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r6 == 0) goto L_0x0274
                        com.codebutler.android_websockets.WebSocketClient r6 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r6 = r6.d((java.lang.String) r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r6 == 0) goto L_0x02cb
                        java.lang.String r7 = r5.getValue()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean r7 = r6.equals(r7)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        if (r7 == 0) goto L_0x02a8
                        goto L_0x0274
                    L_0x02a8:
                        java.lang.Exception r1 = new java.lang.Exception     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.<init>()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = "Invalid Sec-WebSocket-Accept, expected: "
                        r2.append(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.append(r6)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = ", got: "
                        r2.append(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = r5.getValue()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.append(r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.<init>(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        throw r1     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x02cb:
                        java.lang.Exception r1 = new java.lang.Exception     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "SHA-1 algorithm not found"
                        r1.<init>(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        throw r1     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x02d3:
                        com.codebutler.android_websockets.WebSocketClient r3 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient$Listener r3 = r3.e     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r3.a((java.util.List<org.apache.http.Header>) r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean unused = r2.l = r4     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.HybiParser r2 = r2.k     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r2.a((com.codebutler.android_websockets.HybiParser.HappyDataInputStream) r1)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        goto L_0x0361
                    L_0x02eb:
                        org.apache.http.client.HttpResponseException r1 = new org.apache.http.client.HttpResponseException     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        int r3 = r2.getStatusCode()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = r2.getReasonPhrase()     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        r1.<init>(r3, r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        throw r1     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x02f9:
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.e     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "握手失败"
                        r1.a(r5, r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean unused = r1.l = r0     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        org.apache.http.HttpException r1 = new org.apache.http.HttpException     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r2 = "Received no reply from server."
                        r1.<init>(r2)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        throw r1     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x0312:
                        r1 = move-exception
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient$Listener r2 = r2.e     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        java.lang.String r3 = "握手失败"
                        r2.a(r5, r3)     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        com.codebutler.android_websockets.WebSocketClient r2 = com.codebutler.android_websockets.WebSocketClient.this     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        boolean unused = r2.l = r0     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                        throw r1     // Catch:{ EOFException -> 0x0349, SSLException -> 0x0330, Exception -> 0x0325 }
                    L_0x0325:
                        r0 = move-exception
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this
                        com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.e
                        r1.a((java.lang.Exception) r0)
                        goto L_0x0361
                    L_0x0330:
                        r1 = move-exception
                        java.lang.String r2 = "WebSocketClient"
                        java.lang.String r3 = "Websocket SSL error!"
                        android.util.Log.d(r2, r3, r1)
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this
                        com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.e
                        java.lang.String r2 = "SSL"
                        r1.a(r0, r2)
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this
                        boolean unused = r1.l = r0
                        goto L_0x0361
                    L_0x0349:
                        r1 = move-exception
                        java.lang.String r2 = "WebSocketClient"
                        java.lang.String r3 = "WebSocket EOF!"
                        android.util.Log.d(r2, r3, r1)
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this
                        com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.e
                        java.lang.String r2 = "EOF"
                        r1.a(r0, r2)
                        com.codebutler.android_websockets.WebSocketClient r1 = com.codebutler.android_websockets.WebSocketClient.this
                        boolean unused = r1.l = r0
                    L_0x0361:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.codebutler.android_websockets.WebSocketClient.AnonymousClass1.run():void");
                }
            });
            this.g.start();
        }
    }

    public void c() {
        if (this.f != null) {
            this.i.post(new Runnable() {
                public void run() {
                    if (WebSocketClient.this.f != null) {
                        try {
                            WebSocketClient.this.f.close();
                        } catch (IOException e) {
                            Log.d(WebSocketClient.f5148a, "Error while disconnecting", e);
                            WebSocketClient.this.e.a((Exception) e);
                        }
                        Socket unused = WebSocketClient.this.f = null;
                    }
                    boolean unused2 = WebSocketClient.this.l = false;
                }
            });
        }
    }

    public void a(String str) {
        b(this.k.a(str));
    }

    public void a(byte[] bArr) {
        b(this.k.a(bArr));
    }

    public boolean d() {
        return this.l;
    }

    /* access modifiers changed from: private */
    public StatusLine b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return BasicLineParser.parseStatusLine(str, (LineParser) new BasicLineParser());
    }

    /* access modifiers changed from: private */
    public Header c(String str) {
        return BasicLineParser.parseHeader(str, new BasicLineParser());
    }

    /* access modifiers changed from: private */
    public String a(HybiParser.HappyDataInputStream happyDataInputStream) throws IOException {
        int read = happyDataInputStream.read();
        if (read == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        while (read != 10) {
            if (read != 13) {
                sb.append((char) read);
            }
            read = happyDataInputStream.read();
            if (read == -1) {
                return null;
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String d(String str) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance(DigestUtils.b).digest((str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()), 0).trim();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String e() {
        byte[] bArr = new byte[16];
        for (int i2 = 0; i2 < 16; i2++) {
            bArr[i2] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    /* access modifiers changed from: package-private */
    public void b(final byte[] bArr) {
        this.i.post(new Runnable() {
            public void run() {
                try {
                    synchronized (WebSocketClient.this.c) {
                        OutputStream outputStream = WebSocketClient.this.f.getOutputStream();
                        outputStream.write(bArr);
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    WebSocketClient.this.e.a((Exception) e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public SSLSocketFactory f() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, b, (SecureRandom) null);
        return instance.getSocketFactory();
    }
}
