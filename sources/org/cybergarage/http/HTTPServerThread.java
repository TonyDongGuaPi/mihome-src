package org.cybergarage.http;

import java.net.Socket;

public class HTTPServerThread extends Thread {
    private HTTPServer httpServer;
    private Socket sock;

    public HTTPServerThread(HTTPServer hTTPServer, Socket socket) {
        super("Cyber.HTTPServerThread");
        this.httpServer = hTTPServer;
        this.sock = socket;
    }

    public void run() {
        HTTPSocket hTTPSocket = new HTTPSocket(this.sock);
        if (hTTPSocket.open()) {
            HTTPRequest hTTPRequest = new HTTPRequest();
            hTTPRequest.setSocket(hTTPSocket);
            while (hTTPRequest.read()) {
                this.httpServer.performRequestListener(hTTPRequest);
                if (!hTTPRequest.isKeepAlive()) {
                    break;
                }
            }
            hTTPSocket.close();
        }
    }
}
