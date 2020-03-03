package org.cybergarage.upnp.ssdp;

import com.google.code.microlog4android.appender.DatagramAppender;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPHeader;
import org.cybergarage.upnp.device.MAN;
import org.cybergarage.upnp.device.NT;
import org.cybergarage.upnp.device.NTS;
import org.cybergarage.upnp.device.ST;
import org.cybergarage.upnp.device.USN;

public class SSDPPacket {
    private DatagramPacket dgmPacket = null;
    private String localAddr = "";
    public byte[] packetBytes = null;
    private long timeStamp;

    public SSDPPacket(byte[] bArr, int i) {
        this.dgmPacket = new DatagramPacket(bArr, i);
    }

    public DatagramPacket getDatagramPacket() {
        return this.dgmPacket;
    }

    public void setLocalAddress(String str) {
        this.localAddr = str;
    }

    public String getLocalAddress() {
        return this.localAddr;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public InetAddress getRemoteInetAddress() {
        return getDatagramPacket().getAddress();
    }

    public String getRemoteAddress() {
        return getDatagramPacket().getAddress().getHostAddress();
    }

    public int getRemotePort() {
        return getDatagramPacket().getPort();
    }

    public byte[] getData() {
        if (this.packetBytes != null) {
            return this.packetBytes;
        }
        DatagramPacket datagramPacket = getDatagramPacket();
        this.packetBytes = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).getBytes();
        return this.packetBytes;
    }

    public String getHost() {
        return HTTPHeader.getValue(getData(), HTTP.HOST);
    }

    public String getCacheControl() {
        return HTTPHeader.getValue(getData(), "Cache-Control");
    }

    public String getLocation() {
        return HTTPHeader.getValue(getData(), "Location");
    }

    public String getMAN() {
        return HTTPHeader.getValue(getData(), HTTP.MAN);
    }

    public String getST() {
        return HTTPHeader.getValue(getData(), HTTP.ST);
    }

    public String getNT() {
        return HTTPHeader.getValue(getData(), HTTP.NT);
    }

    public String getNTS() {
        return HTTPHeader.getValue(getData(), HTTP.NTS);
    }

    public String getServer() {
        return HTTPHeader.getValue(getData(), "Server");
    }

    public String getUSN() {
        return HTTPHeader.getValue(getData(), HTTP.USN);
    }

    public int getMX() {
        return HTTPHeader.getIntegerValue(getData(), HTTP.MX);
    }

    public InetAddress getHostInetAddress() {
        String str = DatagramAppender.DEFAULT_HOST;
        String host = getHost();
        int lastIndexOf = host.lastIndexOf(":");
        if (lastIndexOf >= 0) {
            str = host.substring(0, lastIndexOf);
            if (str.charAt(0) == '[') {
                str = str.substring(1, str.length());
            }
            if (str.charAt(str.length() - 1) == ']') {
                str = str.substring(0, str.length() - 1);
            }
        }
        return new InetSocketAddress(str, 0).getAddress();
    }

    public boolean isRootDevice() {
        if (NT.isRootDevice(getNT()) || ST.isRootDevice(getST())) {
            return true;
        }
        return USN.isRootDevice(getUSN());
    }

    public boolean isDiscover() {
        return MAN.isDiscover(getMAN());
    }

    public boolean isAlive() {
        return NTS.isAlive(getNTS());
    }

    public boolean isByeBye() {
        return NTS.isByeBye(getNTS());
    }

    public int getLeaseTime() {
        return SSDP.getLeaseTime(getCacheControl());
    }

    public String toString() {
        return new String(getData());
    }
}
