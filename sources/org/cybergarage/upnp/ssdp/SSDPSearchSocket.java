package org.cybergarage.upnp.ssdp;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.device.SearchListener;
import org.cybergarage.util.ListenerList;

public class SSDPSearchSocket extends HTTPMUSocket implements Runnable {
    private ListenerList deviceSearchListenerList = new ListenerList();
    private Thread deviceSearchThread = null;
    private boolean useIPv6Address;

    public SSDPSearchSocket(String str, int i, String str2) {
        open(str, str2);
    }

    public SSDPSearchSocket(InetAddress inetAddress) {
        if (inetAddress.getAddress().length != 4) {
            open((Inet6Address) inetAddress);
        } else {
            open((Inet4Address) inetAddress);
        }
    }

    public boolean open(Inet4Address inet4Address) {
        this.useIPv6Address = false;
        return open(SSDP.ADDRESS, 1900, (InetAddress) inet4Address);
    }

    public boolean open(Inet6Address inet6Address) {
        this.useIPv6Address = true;
        return open(SSDP.getIPv6Address(), 1900, (InetAddress) inet6Address);
    }

    public boolean open(String str, String str2) {
        if (HostInterface.isIPv6Address(str) && HostInterface.isIPv6Address(str2)) {
            this.useIPv6Address = true;
        } else if (!HostInterface.isIPv4Address(str) || !HostInterface.isIPv4Address(str2)) {
            throw new IllegalArgumentException("Cannot open a UDP Socket for IPv6 address on IPv4 interface or viceversa");
        } else {
            this.useIPv6Address = false;
        }
        return open(str2, 1900, str);
    }

    public boolean open(String str) {
        String str2 = SSDP.ADDRESS;
        this.useIPv6Address = false;
        if (HostInterface.isIPv6Address(str)) {
            str2 = SSDP.getIPv6Address();
            this.useIPv6Address = true;
        }
        return open(str2, 1900, str);
    }

    public void addSearchListener(SearchListener searchListener) {
        this.deviceSearchListenerList.add(searchListener);
    }

    public void removeSearchListener(SearchListener searchListener) {
        this.deviceSearchListenerList.remove(searchListener);
    }

    public void performSearchListener(SSDPPacket sSDPPacket) {
        int size = this.deviceSearchListenerList.size();
        for (int i = 0; i < size; i++) {
            ((SearchListener) this.deviceSearchListenerList.get(i)).deviceSearchReceived(sSDPPacket);
        }
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        while (this.deviceSearchThread == currentThread) {
            Thread.yield();
            try {
                SSDPPacket receive = receive();
                if (receive != null && receive.isDiscover()) {
                    performSearchListener(receive);
                }
            } catch (IOException unused) {
                return;
            }
        }
    }

    public void start() {
        StringBuffer stringBuffer = new StringBuffer("Cyber.SSDPSearchSocket/");
        String localAddress = getLocalAddress();
        if (localAddress != null && localAddress.length() > 0) {
            stringBuffer.append(getLocalAddress());
            stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
            stringBuffer.append(getLocalPort());
            stringBuffer.append(" -> ");
            stringBuffer.append(getMulticastAddress());
            stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
            stringBuffer.append(getMulticastPort());
        }
        this.deviceSearchThread = new Thread(this, stringBuffer.toString());
        this.deviceSearchThread.start();
    }

    public void stop() {
        close();
        this.deviceSearchThread = null;
    }
}
