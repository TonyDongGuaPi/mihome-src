package org.cybergarage.http;

import java.net.InetAddress;
import java.util.Vector;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.Device;

public class HTTPServerList extends Vector {
    private InetAddress[] binds = null;
    private int port = Device.HTTP_DEFAULT_PORT;

    public HTTPServerList() {
    }

    public HTTPServerList(InetAddress[] inetAddressArr, int i) {
        this.binds = inetAddressArr;
        this.port = i;
    }

    public void addRequestListener(HTTPRequestListener hTTPRequestListener) {
        int size = size();
        for (int i = 0; i < size; i++) {
            getHTTPServer(i).addRequestListener(hTTPRequestListener);
        }
    }

    public HTTPServer getHTTPServer(int i) {
        return (HTTPServer) get(i);
    }

    public void close() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getHTTPServer(i).close();
        }
    }

    public int open() {
        String[] strArr;
        InetAddress[] inetAddressArr = this.binds;
        if (inetAddressArr != null) {
            strArr = new String[inetAddressArr.length];
            for (int i = 0; i < inetAddressArr.length; i++) {
                strArr[i] = inetAddressArr[i].getHostAddress();
            }
        } else {
            int nHostAddresses = HostInterface.getNHostAddresses();
            strArr = new String[nHostAddresses];
            for (int i2 = 0; i2 < nHostAddresses; i2++) {
                strArr[i2] = HostInterface.getHostAddress(i2);
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < strArr.length; i4++) {
            HTTPServer hTTPServer = new HTTPServer();
            if (strArr[i4] == null || !hTTPServer.open(strArr[i4], this.port)) {
                close();
                clear();
            } else {
                add(hTTPServer);
                i3++;
            }
        }
        return i3;
    }

    public boolean open(int i) {
        this.port = i;
        return open() != 0;
    }

    public void start() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getHTTPServer(i).start();
        }
    }

    public void stop() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getHTTPServer(i).stop();
        }
    }
}
