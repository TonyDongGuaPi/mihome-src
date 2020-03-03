package org.cybergarage.upnp.ssdp;

import java.net.InetAddress;
import java.util.Vector;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

public class SSDPNotifySocketList extends Vector {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private InetAddress[] binds = null;

    public SSDPNotifySocketList() {
    }

    public SSDPNotifySocketList(InetAddress[] inetAddressArr) {
        this.binds = inetAddressArr;
    }

    public SSDPNotifySocket getSSDPNotifySocket(int i) {
        return (SSDPNotifySocket) get(i);
    }

    public void setControlPoint(ControlPoint controlPoint) {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPNotifySocket(i).setControlPoint(controlPoint);
        }
    }

    public boolean isValidAddress(String str) {
        return str != null && str.length() >= 1 && str.indexOf(58) == -1;
    }

    public boolean open() {
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
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (!isValidAddress(strArr[i3])) {
                CommonLog commonLog = log;
                commonLog.e((Object) "ready to create SSDPNotifySocket bindAddresses = " + strArr[i3] + ", it's invalid so drop it!!!");
            } else if (strArr[i3] != null) {
                SSDPNotifySocket sSDPNotifySocket = new SSDPNotifySocket(strArr[i3]);
                if (sSDPNotifySocket.getSocket() == null) {
                    log.e((Object) "ssdpNotifySocket.getSocket() == null!!!");
                    return false;
                }
                CommonLog commonLog2 = log;
                commonLog2.i("ssdpNotifySocket create success!!!bindAddresses = " + strArr[i3]);
                add(sSDPNotifySocket);
            } else {
                continue;
            }
        }
        return true;
    }

    public void close() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPNotifySocket(i).close();
        }
        clear();
    }

    public void start() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPNotifySocket(i).start();
        }
    }

    public void stop() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPNotifySocket(i).stop();
        }
    }
}
