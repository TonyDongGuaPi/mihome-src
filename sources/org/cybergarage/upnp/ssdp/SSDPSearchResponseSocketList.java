package org.cybergarage.upnp.ssdp;

import java.net.InetAddress;
import java.util.Vector;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

public class SSDPSearchResponseSocketList extends Vector {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private InetAddress[] binds = null;

    public SSDPSearchResponseSocketList() {
    }

    public SSDPSearchResponseSocketList(InetAddress[] inetAddressArr) {
        this.binds = inetAddressArr;
    }

    public void setControlPoint(ControlPoint controlPoint) {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchResponseSocket(i).setControlPoint(controlPoint);
        }
    }

    public SSDPSearchResponseSocket getSSDPSearchResponseSocket(int i) {
        return (SSDPSearchResponseSocket) get(i);
    }

    public boolean isValidAddress(String str) {
        return str != null && str.length() >= 1 && str.indexOf(58) == -1;
    }

    public boolean open(int i) {
        String[] strArr;
        InetAddress[] inetAddressArr = this.binds;
        if (inetAddressArr != null) {
            strArr = new String[inetAddressArr.length];
            for (int i2 = 0; i2 < inetAddressArr.length; i2++) {
                strArr[i2] = inetAddressArr[i2].getHostAddress();
            }
        } else {
            int nHostAddresses = HostInterface.getNHostAddresses();
            strArr = new String[nHostAddresses];
            for (int i3 = 0; i3 < nHostAddresses; i3++) {
                strArr[i3] = HostInterface.getHostAddress(i3);
            }
        }
        for (int i4 = 0; i4 < strArr.length; i4++) {
            CommonLog commonLog = log;
            commonLog.e((Object) "bindAddresses k = " + i4 + ", addr = " + strArr[i4]);
        }
        int i5 = 0;
        boolean z = false;
        while (i5 < strArr.length) {
            try {
                if (!isValidAddress(strArr[i5])) {
                    CommonLog commonLog2 = log;
                    commonLog2.e((Object) "ready to create SSDPSearchResponseSocket bindAddresses = " + strArr[i5] + ", it's invalid so drop it!!!");
                } else {
                    SSDPSearchResponseSocket sSDPSearchResponseSocket = new SSDPSearchResponseSocket(strArr[i5], i);
                    if (sSDPSearchResponseSocket.getDatagramSocket() == null) {
                        log.e((Object) "SSDPSearchResponseSocket.getSocket() == null!!!");
                    } else {
                        CommonLog commonLog3 = log;
                        commonLog3.i("SSDPSearchResponseSocket create success!!!bindAddresses = " + strArr[i5]);
                        add(sSDPSearchResponseSocket);
                        z = true;
                    }
                }
                i5++;
            } catch (Exception unused) {
                stop();
                close();
                clear();
                return false;
            }
        }
        return z;
    }

    public boolean open() {
        return open(1900);
    }

    public void close() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchResponseSocket(i).close();
        }
        clear();
    }

    public void start() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchResponseSocket(i).start();
        }
    }

    public void stop() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchResponseSocket(i).stop();
        }
    }

    public boolean post(SSDPSearchRequest sSDPSearchRequest) {
        int size = size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            SSDPSearchResponseSocket sSDPSearchResponseSocket = getSSDPSearchResponseSocket(i);
            String localAddress = sSDPSearchResponseSocket.getLocalAddress();
            sSDPSearchRequest.setLocalAddress(localAddress);
            String str = SSDP.ADDRESS;
            if (HostInterface.isIPv6Address(localAddress)) {
                str = SSDP.getIPv6Address();
            }
            if (!sSDPSearchResponseSocket.post(str, 1900, sSDPSearchRequest)) {
                z = false;
            }
        }
        return z;
    }
}
