package org.cybergarage.upnp.ssdp;

import java.net.InetAddress;
import java.util.Vector;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.device.SearchListener;

public class SSDPSearchSocketList extends Vector {
    private InetAddress[] binds = null;
    private String multicastIPv4 = SSDP.ADDRESS;
    private String multicastIPv6 = SSDP.getIPv6Address();
    private int port = 1900;

    public SSDPSearchSocketList() {
    }

    public SSDPSearchSocketList(InetAddress[] inetAddressArr) {
        this.binds = inetAddressArr;
    }

    public SSDPSearchSocketList(InetAddress[] inetAddressArr, int i, String str, String str2) {
        this.binds = inetAddressArr;
        this.port = i;
        this.multicastIPv4 = str;
        this.multicastIPv6 = str2;
    }

    public SSDPSearchSocket getSSDPSearchSocket(int i) {
        return (SSDPSearchSocket) get(i);
    }

    public void addSearchListener(SearchListener searchListener) {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchSocket(i).addSearchListener(searchListener);
        }
    }

    public boolean open() {
        String[] strArr;
        SSDPSearchSocket sSDPSearchSocket;
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
            if (strArr[i3] != null) {
                if (HostInterface.isIPv6Address(strArr[i3])) {
                    sSDPSearchSocket = new SSDPSearchSocket(strArr[i3], this.port, this.multicastIPv6);
                } else {
                    sSDPSearchSocket = new SSDPSearchSocket(strArr[i3], this.port, this.multicastIPv4);
                }
                add(sSDPSearchSocket);
            }
        }
        return true;
    }

    public void close() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchSocket(i).close();
        }
        clear();
    }

    public void start() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchSocket(i).start();
        }
    }

    public void stop() {
        int size = size();
        for (int i = 0; i < size; i++) {
            getSSDPSearchSocket(i).stop();
        }
    }
}
