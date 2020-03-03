package org.cybergarage.upnp.ssdp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.Debug;
import org.cybergarage.util.LogFactory;

public class HTTPUSocket {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private String localAddr = "";
    private DatagramSocket ssdpUniSock = null;

    public DatagramSocket getDatagramSocket() {
        return this.ssdpUniSock;
    }

    public HTTPUSocket() {
        open();
    }

    public HTTPUSocket(String str, int i) {
        open(str, i);
    }

    public HTTPUSocket(int i) {
        open(i);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        close();
    }

    public void setLocalAddress(String str) {
        this.localAddr = str;
    }

    public DatagramSocket getUDPSocket() {
        return this.ssdpUniSock;
    }

    public String getLocalAddress() {
        if (this.localAddr.length() > 0) {
            return this.localAddr;
        }
        return this.ssdpUniSock.getLocalAddress().getHostAddress();
    }

    public boolean open() {
        close();
        try {
            this.ssdpUniSock = new DatagramSocket();
            return true;
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public boolean open(String str, int i) {
        close();
        try {
            this.ssdpUniSock = new DatagramSocket(new InetSocketAddress(InetAddress.getByName(str), i));
            setLocalAddress(str);
            return true;
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public boolean open(int i) {
        close();
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(i);
            this.ssdpUniSock = new DatagramSocket((SocketAddress) null);
            this.ssdpUniSock.setReuseAddress(true);
            this.ssdpUniSock.bind(inetSocketAddress);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean close() {
        if (this.ssdpUniSock == null) {
            return true;
        }
        try {
            this.ssdpUniSock.close();
            this.ssdpUniSock = null;
            return true;
        } catch (Exception e) {
            Debug.warning(e);
            return false;
        }
    }

    public boolean post(String str, int i, String str2) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            this.ssdpUniSock.send(new DatagramPacket(str2.getBytes(), str2.length(), byName, i));
            CommonLog commonLog = log;
            commonLog.e((Object) "send to " + byName.toString() + ", port = " + i);
            return true;
        } catch (Exception e) {
            Debug.warning("addr = " + this.ssdpUniSock.getLocalAddress().getHostName());
            Debug.warning("port = " + this.ssdpUniSock.getLocalPort());
            Debug.warning(e);
            CommonLog commonLog2 = log;
            commonLog2.e((Object) "addr = " + this.ssdpUniSock.getLocalAddress().getHostName());
            CommonLog commonLog3 = log;
            commonLog3.e((Object) "port = " + this.ssdpUniSock.getLocalPort());
            log.e(e);
            return false;
        }
    }

    public SSDPPacket receive() {
        byte[] bArr = new byte[1024];
        SSDPPacket sSDPPacket = new SSDPPacket(bArr, bArr.length);
        sSDPPacket.setLocalAddress(getLocalAddress());
        try {
            this.ssdpUniSock.receive(sSDPPacket.getDatagramPacket());
            sSDPPacket.setTimeStamp(System.currentTimeMillis());
            return sSDPPacket;
        } catch (Exception unused) {
            return null;
        }
    }
}
