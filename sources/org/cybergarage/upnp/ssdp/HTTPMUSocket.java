package org.cybergarage.upnp.ssdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.util.Enumeration;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.upnp.UPnP;
import org.cybergarage.util.Debug;

public class HTTPMUSocket {
    private InetSocketAddress ssdpMultiGroup = null;
    private NetworkInterface ssdpMultiIf = null;
    private MulticastSocket ssdpMultiSock = null;

    public HTTPMUSocket() {
    }

    public HTTPMUSocket(String str, int i, String str2) {
        open(str, i, str2);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        close();
    }

    public String getLocalAddress() {
        if (this.ssdpMultiGroup == null || this.ssdpMultiIf == null) {
            return "";
        }
        InetAddress address = this.ssdpMultiGroup.getAddress();
        Enumeration<InetAddress> inetAddresses = this.ssdpMultiIf.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress nextElement = inetAddresses.nextElement();
            if ((address instanceof Inet6Address) && (nextElement instanceof Inet6Address)) {
                return nextElement.getHostAddress();
            }
            if ((address instanceof Inet4Address) && (nextElement instanceof Inet4Address)) {
                return nextElement.getHostAddress();
            }
        }
        return "";
    }

    public int getMulticastPort() {
        return this.ssdpMultiGroup.getPort();
    }

    public int getLocalPort() {
        return this.ssdpMultiSock.getLocalPort();
    }

    public MulticastSocket getSocket() {
        return this.ssdpMultiSock;
    }

    public InetAddress getMulticastInetAddress() {
        if (this.ssdpMultiGroup == null) {
            return null;
        }
        return this.ssdpMultiGroup.getAddress();
    }

    public String getMulticastAddress() {
        return getMulticastInetAddress().getHostAddress();
    }

    public boolean open(String str, int i, InetAddress inetAddress) {
        try {
            this.ssdpMultiSock = new MulticastSocket((SocketAddress) null);
            this.ssdpMultiSock.setReuseAddress(true);
            this.ssdpMultiSock.bind(new InetSocketAddress(i));
            this.ssdpMultiGroup = new InetSocketAddress(InetAddress.getByName(str), i);
            this.ssdpMultiIf = NetworkInterface.getByInetAddress(inetAddress);
            this.ssdpMultiSock.joinGroup(this.ssdpMultiGroup, this.ssdpMultiIf);
            return true;
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public boolean open(String str, int i, String str2) {
        try {
            return open(str, i, InetAddress.getByName(str2));
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public boolean close() {
        if (this.ssdpMultiSock == null) {
            return true;
        }
        try {
            this.ssdpMultiSock.leaveGroup(this.ssdpMultiGroup, this.ssdpMultiIf);
            this.ssdpMultiSock.close();
            this.ssdpMultiSock = null;
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean send(String str, String str2, int i) {
        MulticastSocket multicastSocket;
        if (str2 == null || i <= 0) {
            multicastSocket = new MulticastSocket();
        } else {
            try {
                multicastSocket = new MulticastSocket((SocketAddress) null);
                multicastSocket.bind(new InetSocketAddress(str2, i));
            } catch (Exception e) {
                Debug.warning(e);
                return false;
            }
        }
        DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(), str.length(), this.ssdpMultiGroup);
        multicastSocket.setTimeToLive(UPnP.getTimeToLive());
        multicastSocket.send(datagramPacket);
        multicastSocket.close();
        return true;
    }

    public boolean send(String str) {
        return send(str, (String) null, -1);
    }

    public boolean post(HTTPRequest hTTPRequest, String str, int i) {
        return send(hTTPRequest.toString(), str, i);
    }

    public boolean post(HTTPRequest hTTPRequest) {
        return send(hTTPRequest.toString(), (String) null, -1);
    }

    public SSDPPacket receive() throws IOException {
        byte[] bArr = new byte[1024];
        SSDPPacket sSDPPacket = new SSDPPacket(bArr, bArr.length);
        sSDPPacket.setLocalAddress(getLocalAddress());
        if (this.ssdpMultiSock != null) {
            this.ssdpMultiSock.receive(sSDPPacket.getDatagramPacket());
            sSDPPacket.setTimeStamp(System.currentTimeMillis());
            return sSDPPacket;
        }
        throw new IOException("Multicast socket has already been closed.");
    }
}
