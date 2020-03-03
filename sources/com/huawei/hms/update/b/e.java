package com.huawei.hms.update.b;

import com.huawei.hms.support.log.a;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

final class e extends SSLSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f5918a = new Object();
    private static SocketFactory c;
    private final SSLContext b = SSLContext.getInstance("TLSv1.2");

    private e() throws NoSuchAlgorithmException, KeyManagementException {
        this.b.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
    }

    public static SocketFactory a() {
        SocketFactory socketFactory;
        synchronized (f5918a) {
            try {
                if (c == null) {
                    c = new e();
                }
                socketFactory = c;
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                a.d("TLSSocketFactory", "Failed to new TLSSocketFactory instance." + e.getMessage());
                return SSLSocketFactory.getDefault();
            } catch (Throwable th) {
                throw th;
            }
        }
        return socketFactory;
    }

    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.b.getSocketFactory().createSocket(socket, str, i, z);
        a(createSocket);
        return createSocket;
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        Socket createSocket = this.b.getSocketFactory().createSocket(str, i);
        a(createSocket);
        return createSocket;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        Socket createSocket = this.b.getSocketFactory().createSocket(str, i, inetAddress, i2);
        a(createSocket);
        return createSocket;
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket createSocket = this.b.getSocketFactory().createSocket(inetAddress, i);
        a(createSocket);
        return createSocket;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket createSocket = this.b.getSocketFactory().createSocket(inetAddress, i, inetAddress2, i2);
        a(createSocket);
        return createSocket;
    }

    private void a(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            b(sSLSocket);
            a(sSLSocket);
        }
    }

    private void b(SSLSocket sSLSocket) {
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
    }

    public static void a(SSLSocket sSLSocket) {
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        if (enabledCipherSuites != null && enabledCipherSuites.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : enabledCipherSuites) {
                if (!str.contains("RC2") && !str.contains("RC4") && !str.contains("DES") && !str.contains("MD2") && !str.contains("MD4") && !str.contains("MD5") && !str.contains("ANON") && !str.contains("NULL") && !str.contains("SKIPJACK") && !str.contains("SHA1")) {
                    arrayList.add(str);
                }
            }
            sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }
}
