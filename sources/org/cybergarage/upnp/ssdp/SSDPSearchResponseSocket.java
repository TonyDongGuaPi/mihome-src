package org.cybergarage.upnp.ssdp;

import com.taobao.weex.el.parse.Operators;
import java.net.DatagramSocket;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

public class SSDPSearchResponseSocket extends HTTPUSocket implements Runnable {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private ControlPoint controlPoint = null;
    private Thread deviceSearchResponseThread = null;

    public SSDPSearchResponseSocket() {
        setControlPoint((ControlPoint) null);
    }

    public SSDPSearchResponseSocket(String str, int i) {
        super(str, i);
        setControlPoint((ControlPoint) null);
    }

    public void setControlPoint(ControlPoint controlPoint2) {
        this.controlPoint = controlPoint2;
    }

    public ControlPoint getControlPoint() {
        return this.controlPoint;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        ControlPoint controlPoint2 = getControlPoint();
        while (this.deviceSearchResponseThread == currentThread) {
            Thread.yield();
            SSDPPacket receive = receive();
            if (receive != null) {
                if (controlPoint2 != null) {
                    controlPoint2.searchResponseReceived(receive);
                }
            } else {
                return;
            }
        }
    }

    public void start() {
        StringBuffer stringBuffer = new StringBuffer("Cyber.SSDPSearchResponseSocket/");
        DatagramSocket datagramSocket = getDatagramSocket();
        if (datagramSocket.getLocalAddress() != null) {
            stringBuffer.append(datagramSocket.getLocalAddress());
            stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
            stringBuffer.append(datagramSocket.getLocalPort());
        }
        this.deviceSearchResponseThread = new Thread(this, stringBuffer.toString());
        this.deviceSearchResponseThread.start();
    }

    public void stop() {
        this.deviceSearchResponseThread = null;
    }

    public boolean post(String str, int i, SSDPSearchResponse sSDPSearchResponse) {
        return post(str, i, sSDPSearchResponse.getHeader());
    }

    public boolean post(String str, int i, SSDPSearchRequest sSDPSearchRequest) {
        return post(str, i, sSDPSearchRequest.toString());
    }
}
