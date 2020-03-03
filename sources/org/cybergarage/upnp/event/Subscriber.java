package org.cybergarage.upnp.event;

import java.net.URL;

public class Subscriber {
    private String SID = null;
    private String deliveryHost = "";
    private String deliveryPath = "";
    private int deliveryPort = 0;
    private String deliveryURL = "";
    private String ifAddr = "";
    private long notifyCount = 0;
    private long subscriptionTime = 0;
    private long timeOut = 0;

    public Subscriber() {
        renew();
    }

    public String getSID() {
        return this.SID;
    }

    public void setSID(String str) {
        this.SID = str;
    }

    public void setInterfaceAddress(String str) {
        this.ifAddr = str;
    }

    public String getInterfaceAddress() {
        return this.ifAddr;
    }

    public String getDeliveryURL() {
        return this.deliveryURL;
    }

    public void setDeliveryURL(String str) {
        this.deliveryURL = str;
        try {
            URL url = new URL(str);
            this.deliveryHost = url.getHost();
            this.deliveryPath = url.getPath();
            this.deliveryPort = url.getPort();
        } catch (Exception unused) {
        }
    }

    public String getDeliveryHost() {
        return this.deliveryHost;
    }

    public String getDeliveryPath() {
        return this.deliveryPath;
    }

    public int getDeliveryPort() {
        return this.deliveryPort;
    }

    public long getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(long j) {
        this.timeOut = j;
    }

    public boolean isExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.timeOut != -1 && getSubscriptionTime() + (getTimeOut() * 1000) < currentTimeMillis) {
            return true;
        }
        return false;
    }

    public long getSubscriptionTime() {
        return this.subscriptionTime;
    }

    public void setSubscriptionTime(long j) {
        this.subscriptionTime = j;
    }

    public long getNotifyCount() {
        return this.notifyCount;
    }

    public void setNotifyCount(int i) {
        this.notifyCount = (long) i;
    }

    public void incrementNotifyCount() {
        if (this.notifyCount == Long.MAX_VALUE) {
            this.notifyCount = 1;
        } else {
            this.notifyCount++;
        }
    }

    public void renew() {
        setSubscriptionTime(System.currentTimeMillis());
        setNotifyCount(0);
    }
}
