package org.cybergarage.upnp.ssdp;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.InetAddress;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

public class SSDPNotifySocket extends HTTPMUSocket implements Runnable {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private ControlPoint controlPoint = null;
    private Thread deviceNotifyThread = null;
    private boolean useIPv6Address;

    public SSDPNotifySocket(String str) {
        String str2 = SSDP.ADDRESS;
        this.useIPv6Address = false;
        if (HostInterface.isIPv6Address(str)) {
            str2 = SSDP.getIPv6Address();
            this.useIPv6Address = true;
        }
        open(str2, 1900, str);
        setControlPoint((ControlPoint) null);
    }

    public void setControlPoint(ControlPoint controlPoint2) {
        this.controlPoint = controlPoint2;
    }

    public ControlPoint getControlPoint() {
        return this.controlPoint;
    }

    public boolean post(SSDPNotifyRequest sSDPNotifyRequest) {
        String str = SSDP.ADDRESS;
        if (this.useIPv6Address) {
            str = SSDP.getIPv6Address();
        }
        sSDPNotifyRequest.setHost(str, 1900);
        return post(sSDPNotifyRequest);
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        ControlPoint controlPoint2 = getControlPoint();
        while (this.deviceNotifyThread == currentThread) {
            Thread.yield();
            try {
                SSDPPacket receive = receive();
                if (receive != null) {
                    InetAddress multicastInetAddress = getMulticastInetAddress();
                    InetAddress hostInetAddress = receive.getHostInetAddress();
                    if (!(multicastInetAddress == null || !multicastInetAddress.equals(hostInetAddress) || controlPoint2 == null)) {
                        controlPoint2.notifyReceived(receive);
                    }
                }
            } catch (IOException unused) {
                return;
            }
        }
    }

    public void start() {
        StringBuffer stringBuffer = new StringBuffer("Cyber.SSDPNotifySocket/");
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
        this.deviceNotifyThread = new Thread(this, stringBuffer.toString());
        this.deviceNotifyThread.start();
    }

    public void stop() {
        close();
        this.deviceNotifyThread = null;
    }
}
