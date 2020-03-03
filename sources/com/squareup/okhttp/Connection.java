package com.squareup.okhttp;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.ConnectionSpecSelector;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.Version;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.http.FramedTransport;
import com.squareup.okhttp.internal.http.HttpConnection;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.Transport;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;

public final class Connection {
    private FramedConnection framedConnection;
    private Handshake handshake;
    private HttpConnection httpConnection;
    private long idleStartTimeNs;
    private Object owner;
    private final ConnectionPool pool;
    private Protocol protocol;
    private int recycleCount;
    private final Route route;
    private Socket socket;

    public Connection(ConnectionPool connectionPool, Route route2) {
        this.pool = connectionPool;
        this.route = route2;
    }

    /* access modifiers changed from: package-private */
    public Object getOwner() {
        Object obj;
        synchronized (this.pool) {
            obj = this.owner;
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public void setOwner(Object obj) {
        if (!isFramed()) {
            synchronized (this.pool) {
                if (this.owner == null) {
                    this.owner = obj;
                } else {
                    throw new IllegalStateException("Connection already has an owner!");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean clearOwner() {
        synchronized (this.pool) {
            if (this.owner == null) {
                return false;
            }
            this.owner = null;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r2.socket == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2.socket.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeIfOwnedBy(java.lang.Object r3) throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.isFramed()
            if (r0 != 0) goto L_0x0020
            com.squareup.okhttp.ConnectionPool r0 = r2.pool
            monitor-enter(r0)
            java.lang.Object r1 = r2.owner     // Catch:{ all -> 0x001d }
            if (r1 == r3) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x000f:
            r3 = 0
            r2.owner = r3     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            java.net.Socket r3 = r2.socket
            if (r3 == 0) goto L_0x001c
            java.net.Socket r3 = r2.socket
            r3.close()
        L_0x001c:
            return
        L_0x001d:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r3
        L_0x0020:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Connection.closeIfOwnedBy(java.lang.Object):void");
    }

    /* access modifiers changed from: package-private */
    public void connect(int i, int i2, int i3, List<ConnectionSpec> list, boolean z) throws RouteException {
        Socket socket2;
        if (this.protocol == null) {
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list);
            Proxy proxy = this.route.getProxy();
            Address address = this.route.getAddress();
            if (this.route.address.getSslSocketFactory() != null || list.contains(ConnectionSpec.CLEARTEXT)) {
                RouteException routeException = null;
                while (this.protocol == null) {
                    try {
                        if (proxy.type() != Proxy.Type.DIRECT) {
                            if (proxy.type() != Proxy.Type.HTTP) {
                                socket2 = new Socket(proxy);
                                this.socket = socket2;
                                connectSocket(i, i2, i3, connectionSpecSelector);
                            }
                        }
                        socket2 = address.getSocketFactory().createSocket();
                        this.socket = socket2;
                        connectSocket(i, i2, i3, connectionSpecSelector);
                    } catch (IOException e) {
                        Util.closeQuietly(this.socket);
                        this.socket = null;
                        this.handshake = null;
                        this.protocol = null;
                        this.httpConnection = null;
                        this.framedConnection = null;
                        if (routeException == null) {
                            routeException = new RouteException(e);
                        } else {
                            routeException.addConnectException(e);
                        }
                        if (!z || !connectionSpecSelector.connectionFailed(e)) {
                            throw routeException;
                        }
                    }
                }
                return;
            }
            throw new RouteException(new UnknownServiceException("CLEARTEXT communication not supported: " + list));
        }
        throw new IllegalStateException("already connected");
    }

    private void connectSocket(int i, int i2, int i3, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        this.socket.setSoTimeout(i2);
        Platform.get().connectSocket(this.socket, this.route.getSocketAddress(), i);
        if (this.route.address.getSslSocketFactory() != null) {
            connectTls(i2, i3, connectionSpecSelector);
        } else {
            this.protocol = Protocol.HTTP_1_1;
        }
        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            this.framedConnection = new FramedConnection.Builder(this.route.address.uriHost, true, this.socket).protocol(this.protocol).build();
            this.framedConnection.sendConnectionPreface();
            return;
        }
        this.httpConnection = new HttpConnection(this.pool, this, this.socket);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r7v2, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f5 A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fb A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fe  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(int r6, int r7, com.squareup.okhttp.internal.ConnectionSpecSelector r8) throws java.io.IOException {
        /*
            r5 = this;
            com.squareup.okhttp.Route r0 = r5.route
            boolean r0 = r0.requiresTunnel()
            if (r0 == 0) goto L_0x000b
            r5.createTunnel(r6, r7)
        L_0x000b:
            com.squareup.okhttp.Route r6 = r5.route
            com.squareup.okhttp.Address r6 = r6.getAddress()
            javax.net.ssl.SSLSocketFactory r7 = r6.getSslSocketFactory()
            r0 = 0
            java.net.Socket r1 = r5.socket     // Catch:{ AssertionError -> 0x00ee }
            java.lang.String r2 = r6.getUriHost()     // Catch:{ AssertionError -> 0x00ee }
            int r3 = r6.getUriPort()     // Catch:{ AssertionError -> 0x00ee }
            r4 = 1
            java.net.Socket r7 = r7.createSocket(r1, r2, r3, r4)     // Catch:{ AssertionError -> 0x00ee }
            javax.net.ssl.SSLSocket r7 = (javax.net.ssl.SSLSocket) r7     // Catch:{ AssertionError -> 0x00ee }
            com.squareup.okhttp.ConnectionSpec r8 = r8.configureSecureSocket(r7)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r1 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r1 == 0) goto L_0x0040
            com.squareup.okhttp.internal.Platform r1 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r2 = r6.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r3 = r6.getProtocols()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.configureTlsExtensions(r7, r2, r3)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x0040:
            r7.startHandshake()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLSession r1 = r7.getSession()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            com.squareup.okhttp.Handshake r1 = com.squareup.okhttp.Handshake.get(r1)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.HostnameVerifier r2 = r6.getHostnameVerifier()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r3 = r6.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLSession r4 = r7.getSession()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r2 = r2.verify(r3, r4)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r2 == 0) goto L_0x0093
            com.squareup.okhttp.CertificatePinner r2 = r6.getCertificatePinner()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = r6.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r3 = r1.peerCertificates()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r2.check((java.lang.String) r6, (java.util.List<java.security.cert.Certificate>) r3)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r6 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r6 == 0) goto L_0x007a
            com.squareup.okhttp.internal.Platform r6 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r0 = r6.getSelectedProtocol(r7)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x007a:
            r5.socket = r7     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r5.handshake = r1     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r0 == 0) goto L_0x0085
            com.squareup.okhttp.Protocol r6 = com.squareup.okhttp.Protocol.get(r0)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            goto L_0x0087
        L_0x0085:
            com.squareup.okhttp.Protocol r6 = com.squareup.okhttp.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x0087:
            r5.protocol = r6     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r7 == 0) goto L_0x0092
            com.squareup.okhttp.internal.Platform r6 = com.squareup.okhttp.internal.Platform.get()
            r6.afterHandshake(r7)
        L_0x0092:
            return
        L_0x0093:
            java.util.List r8 = r1.peerCertificates()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0 = 0
            java.lang.Object r8 = r8.get(r0)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.security.cert.X509Certificate r8 = (java.security.cert.X509Certificate) r8     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLPeerUnverifiedException r0 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.<init>()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r2 = "Hostname "
            r1.append(r2)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = r6.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = " not verified:"
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = "\n    certificate: "
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = com.squareup.okhttp.CertificatePinner.pin(r8)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = "\n    DN: "
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.security.Principal r6 = r8.getSubjectDN()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = r6.getName()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = "\n    subjectAltNames: "
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r6 = com.squareup.okhttp.internal.tls.OkHostnameVerifier.allSubjectAltNames(r8)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.append(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r6 = r1.toString()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.<init>(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            throw r0     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x00e6:
            r6 = move-exception
            goto L_0x00fc
        L_0x00e8:
            r6 = move-exception
            r0 = r7
            goto L_0x00ef
        L_0x00eb:
            r6 = move-exception
            r7 = r0
            goto L_0x00fc
        L_0x00ee:
            r6 = move-exception
        L_0x00ef:
            boolean r7 = com.squareup.okhttp.internal.Util.isAndroidGetsocknameError(r6)     // Catch:{ all -> 0x00eb }
            if (r7 == 0) goto L_0x00fb
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x00eb }
            r7.<init>(r6)     // Catch:{ all -> 0x00eb }
            throw r7     // Catch:{ all -> 0x00eb }
        L_0x00fb:
            throw r6     // Catch:{ all -> 0x00eb }
        L_0x00fc:
            if (r7 == 0) goto L_0x0105
            com.squareup.okhttp.internal.Platform r8 = com.squareup.okhttp.internal.Platform.get()
            r8.afterHandshake(r7)
        L_0x0105:
            com.squareup.okhttp.internal.Util.closeQuietly((java.net.Socket) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Connection.connectTls(int, int, com.squareup.okhttp.internal.ConnectionSpecSelector):void");
    }

    private void createTunnel(int i, int i2) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpConnection httpConnection2 = new HttpConnection(this.pool, this, this.socket);
        httpConnection2.setTimeouts(i, i2);
        HttpUrl httpUrl = createTunnelRequest.httpUrl();
        String str = "CONNECT " + httpUrl.host() + ":" + httpUrl.port() + " HTTP/1.1";
        do {
            httpConnection2.writeRequest(createTunnelRequest.headers(), str);
            httpConnection2.flush();
            Response build = httpConnection2.readResponse().request(createTunnelRequest).build();
            long contentLength = OkHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = httpConnection2.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    createTunnelRequest = OkHeaders.processAuthHeader(this.route.getAddress().getAuthenticator(), build, this.route.getProxy());
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
            } else if (httpConnection2.bufferSize() > 0) {
                throw new IOException("TLS tunnel buffered too many bytes!");
            } else {
                return;
            }
        } while (createTunnelRequest != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private Request createTunnelRequest() throws IOException {
        HttpUrl build = new HttpUrl.Builder().scheme("https").host(this.route.address.uriHost).port(this.route.address.uriPort).build();
        return new Request.Builder().url(build).header("Host", Util.hostHeader(build)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    /* access modifiers changed from: package-private */
    public void connectAndSetOwner(OkHttpClient okHttpClient, Object obj) throws RouteException {
        setOwner(obj);
        if (!isConnected()) {
            connect(okHttpClient.getConnectTimeout(), okHttpClient.getReadTimeout(), okHttpClient.getWriteTimeout(), this.route.address.getConnectionSpecs(), okHttpClient.getRetryOnConnectionFailure());
            if (isFramed()) {
                okHttpClient.getConnectionPool().share(this);
            }
            okHttpClient.routeDatabase().connected(getRoute());
        }
        setTimeouts(okHttpClient.getReadTimeout(), okHttpClient.getWriteTimeout());
    }

    /* access modifiers changed from: package-private */
    public boolean isConnected() {
        return this.protocol != null;
    }

    public Route getRoute() {
        return this.route;
    }

    public Socket getSocket() {
        return this.socket;
    }

    /* access modifiers changed from: package-private */
    public BufferedSource rawSource() {
        if (this.httpConnection != null) {
            return this.httpConnection.rawSource();
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public BufferedSink rawSink() {
        if (this.httpConnection != null) {
            return this.httpConnection.rawSink();
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public boolean isAlive() {
        return !this.socket.isClosed() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown();
    }

    /* access modifiers changed from: package-private */
    public boolean isReadable() {
        if (this.httpConnection != null) {
            return this.httpConnection.isReadable();
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void resetIdleStartTime() {
        if (this.framedConnection == null) {
            this.idleStartTimeNs = System.nanoTime();
            return;
        }
        throw new IllegalStateException("framedConnection != null");
    }

    /* access modifiers changed from: package-private */
    public boolean isIdle() {
        return this.framedConnection == null || this.framedConnection.isIdle();
    }

    /* access modifiers changed from: package-private */
    public long getIdleStartTimeNs() {
        return this.framedConnection == null ? this.idleStartTimeNs : this.framedConnection.getIdleStartTimeNs();
    }

    public Handshake getHandshake() {
        return this.handshake;
    }

    /* access modifiers changed from: package-private */
    public Transport newTransport(HttpEngine httpEngine) throws IOException {
        return this.framedConnection != null ? new FramedTransport(httpEngine, this.framedConnection) : new HttpTransport(httpEngine, this.httpConnection);
    }

    /* access modifiers changed from: package-private */
    public boolean isFramed() {
        return this.framedConnection != null;
    }

    public Protocol getProtocol() {
        return this.protocol != null ? this.protocol : Protocol.HTTP_1_1;
    }

    /* access modifiers changed from: package-private */
    public void setTimeouts(int i, int i2) throws RouteException {
        if (this.protocol == null) {
            throw new IllegalStateException("not connected");
        } else if (this.httpConnection != null) {
            try {
                this.socket.setSoTimeout(i);
                this.httpConnection.setTimeouts(i, i2);
            } catch (IOException e) {
                throw new RouteException(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void incrementRecycleCount() {
        this.recycleCount++;
    }

    /* access modifiers changed from: package-private */
    public int recycleCount() {
        return this.recycleCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address.uriHost);
        sb.append(":");
        sb.append(this.route.address.uriPort);
        sb.append(", proxy=");
        sb.append(this.route.proxy);
        sb.append(" hostAddress=");
        sb.append(this.route.inetSocketAddress.getAddress().getHostAddress());
        sb.append(" cipherSuite=");
        sb.append(this.handshake != null ? this.handshake.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
