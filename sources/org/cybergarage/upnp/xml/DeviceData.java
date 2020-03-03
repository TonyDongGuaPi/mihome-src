package org.cybergarage.upnp.xml;

import java.io.File;
import java.net.InetAddress;
import org.cybergarage.http.HTTPServerList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.Advertiser;
import org.cybergarage.upnp.ssdp.SSDP;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.upnp.ssdp.SSDPSearchSocketList;
import org.cybergarage.util.ListenerList;

public class DeviceData extends NodeData {
    private Advertiser advertiser = null;
    private ListenerList controlActionListenerList = new ListenerList();
    private File descriptionFile = null;
    private String descriptionURI = null;
    private InetAddress[] httpBinds = null;
    private int httpPort = Device.HTTP_DEFAULT_PORT;
    private HTTPServerList httpServerList = null;
    private int leaseTime = 1800;
    private String location = "";
    private InetAddress[] ssdpBinds = null;
    private String ssdpMulticastIPv4 = SSDP.ADDRESS;
    private String ssdpMulticastIPv6 = SSDP.getIPv6Address();
    private SSDPPacket ssdpPacket = null;
    private int ssdpPort = 1900;
    private SSDPSearchSocketList ssdpSearchSocketList = null;

    public File getDescriptionFile() {
        return this.descriptionFile;
    }

    public String getDescriptionURI() {
        return this.descriptionURI;
    }

    public void setDescriptionFile(File file) {
        this.descriptionFile = file;
    }

    public void setDescriptionURI(String str) {
        this.descriptionURI = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public int getLeaseTime() {
        return this.leaseTime;
    }

    public void setLeaseTime(int i) {
        this.leaseTime = i;
    }

    public HTTPServerList getHTTPServerList() {
        if (this.httpServerList == null) {
            this.httpServerList = new HTTPServerList(this.httpBinds, this.httpPort);
        }
        return this.httpServerList;
    }

    public void setHTTPBindAddress(InetAddress[] inetAddressArr) {
        this.httpBinds = inetAddressArr;
    }

    public InetAddress[] getHTTPBindAddress() {
        return this.httpBinds;
    }

    public int getHTTPPort() {
        return this.httpPort;
    }

    public void setHTTPPort(int i) {
        this.httpPort = i;
    }

    public ListenerList getControlActionListenerList() {
        return this.controlActionListenerList;
    }

    public SSDPSearchSocketList getSSDPSearchSocketList() {
        if (this.ssdpSearchSocketList == null) {
            this.ssdpSearchSocketList = new SSDPSearchSocketList(this.ssdpBinds, this.ssdpPort, this.ssdpMulticastIPv4, this.ssdpMulticastIPv6);
        }
        return this.ssdpSearchSocketList;
    }

    public void setSSDPPort(int i) {
        this.ssdpPort = i;
    }

    public int getSSDPPort() {
        return this.ssdpPort;
    }

    public void setSSDPBindAddress(InetAddress[] inetAddressArr) {
        this.ssdpBinds = inetAddressArr;
    }

    public InetAddress[] getSSDPBindAddress() {
        return this.ssdpBinds;
    }

    public void setMulticastIPv4Address(String str) {
        this.ssdpMulticastIPv4 = str;
    }

    public String getMulticastIPv4Address() {
        return this.ssdpMulticastIPv4;
    }

    public void setMulticastIPv6Address(String str) {
        this.ssdpMulticastIPv6 = str;
    }

    public String getMulticastIPv6Address() {
        return this.ssdpMulticastIPv6;
    }

    public SSDPPacket getSSDPPacket() {
        return this.ssdpPacket;
    }

    public void setSSDPPacket(SSDPPacket sSDPPacket) {
        this.ssdpPacket = sSDPPacket;
    }

    public void setAdvertiser(Advertiser advertiser2) {
        this.advertiser = advertiser2;
    }

    public Advertiser getAdvertiser() {
        return this.advertiser;
    }
}
