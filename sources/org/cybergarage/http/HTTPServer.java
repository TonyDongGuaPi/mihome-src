package org.cybergarage.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.cybergarage.util.Debug;
import org.cybergarage.util.ListenerList;

public class HTTPServer implements Runnable {
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_TIMEOUT = 80000;
    public static final String NAME = "CyberHTTP";
    public static final String VERSION = "1.0";
    private InetAddress bindAddr;
    private int bindPort;
    private ListenerList httpRequestListenerList;
    private Thread httpServerThread;
    private ServerSocket serverSock;
    protected int timeout;

    public static String getName() {
        String property = System.getProperty("os.name");
        String property2 = System.getProperty("os.version");
        return property + "/" + property2 + " " + NAME + "/" + "1.0";
    }

    public HTTPServer() {
        this.serverSock = null;
        this.bindAddr = null;
        this.bindPort = 0;
        this.timeout = DEFAULT_TIMEOUT;
        this.httpRequestListenerList = new ListenerList();
        this.httpServerThread = null;
        this.serverSock = null;
    }

    public ServerSocket getServerSock() {
        return this.serverSock;
    }

    public String getBindAddress() {
        if (this.bindAddr == null) {
            return "";
        }
        return this.bindAddr.toString();
    }

    public int getBindPort() {
        return this.bindPort;
    }

    public synchronized int getTimeout() {
        return this.timeout;
    }

    public synchronized void setTimeout(int i) {
        this.timeout = i;
    }

    public boolean open(InetAddress inetAddress, int i) {
        if (this.serverSock != null) {
            return true;
        }
        try {
            this.serverSock = new ServerSocket(this.bindPort, 0, this.bindAddr);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean open(String str, int i) {
        if (this.serverSock != null) {
            return true;
        }
        try {
            this.bindAddr = InetAddress.getByName(str);
            this.bindPort = i;
            this.serverSock = new ServerSocket(this.bindPort, 0, this.bindAddr);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean close() {
        if (this.serverSock == null) {
            return true;
        }
        try {
            this.serverSock.close();
            this.serverSock = null;
            this.bindAddr = null;
            this.bindPort = 0;
            return true;
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public Socket accept() {
        if (this.serverSock == null) {
            return null;
        }
        try {
            Socket accept = this.serverSock.accept();
            accept.setSoTimeout(getTimeout());
            return accept;
        } catch (Exception e) {
            Debug.message(e.toString() + "isOpen" + isOpened());
            return null;
        }
    }

    public boolean isOpened() {
        if (this.serverSock != null && !this.serverSock.isClosed()) {
            return true;
        }
        return false;
    }

    public void addRequestListener(HTTPRequestListener hTTPRequestListener) {
        this.httpRequestListenerList.add(hTTPRequestListener);
    }

    public void removeRequestListener(HTTPRequestListener hTTPRequestListener) {
        this.httpRequestListenerList.remove(hTTPRequestListener);
    }

    public void performRequestListener(HTTPRequest hTTPRequest) {
        int size = this.httpRequestListenerList.size();
        for (int i = 0; i < size; i++) {
            ((HTTPRequestListener) this.httpRequestListenerList.get(i)).httpRequestRecieved(hTTPRequest);
        }
    }

    public void run() {
        if (isOpened()) {
            Thread currentThread = Thread.currentThread();
            while (this.httpServerThread == currentThread) {
                Thread.yield();
                try {
                    Debug.message("accept ...isOpened" + isOpened());
                    Socket accept = accept();
                    if (accept != null) {
                        Debug.message("sock = " + accept.getRemoteSocketAddress());
                        new HTTPServerThread(this, accept).start();
                        Debug.message("httpServThread ...");
                    } else {
                        Debug.message("sock = null.........isOpened" + isOpened());
                        if (!isOpened()) {
                            Debug.message("server sock in not open");
                            return;
                        }
                        return;
                    }
                } catch (Exception e) {
                    Debug.warning(e);
                    return;
                }
            }
        }
    }

    public boolean start() {
        StringBuffer stringBuffer = new StringBuffer("Cyber.HTTPServer/");
        stringBuffer.append(this.serverSock.getLocalSocketAddress());
        this.httpServerThread = new Thread(this, stringBuffer.toString());
        this.httpServerThread.start();
        return true;
    }

    public boolean stop() {
        this.httpServerThread = null;
        return true;
    }
}
