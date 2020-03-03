package org.cybergarage.upnp;

import com.taobao.weex.el.parse.Operators;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.http.HTTPRequestListener;
import org.cybergarage.http.HTTPServerList;
import org.cybergarage.net.HostInterface;
import org.cybergarage.upnp.control.RenewSubscriber;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.Disposer;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.device.USN;
import org.cybergarage.upnp.event.EventListener;
import org.cybergarage.upnp.event.NotifyRequest;
import org.cybergarage.upnp.event.Property;
import org.cybergarage.upnp.event.PropertyList;
import org.cybergarage.upnp.event.SubscriptionRequest;
import org.cybergarage.upnp.event.SubscriptionResponse;
import org.cybergarage.upnp.ssdp.SSDPNotifySocketList;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.upnp.ssdp.SSDPSearchRequest;
import org.cybergarage.upnp.ssdp.SSDPSearchResponseSocketList;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.Debug;
import org.cybergarage.util.ListenerList;
import org.cybergarage.util.LogFactory;
import org.cybergarage.util.Mutex;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.NodeList;
import org.cybergarage.xml.ParserException;

public class ControlPoint implements HTTPRequestListener {
    private static final int DEFAULT_EVENTSUB_PORT = 8058;
    private static final String DEFAULT_EVENTSUB_URI = "/evetSub";
    private static final int DEFAULT_EXPIRED_DEVICE_MONITORING_INTERVAL = 60;
    private static final int DEFAULT_SSDP_PORT = 8008;
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private NodeList devNodeList;
    ListenerList deviceChangeListenerList;
    private Disposer deviceDisposer;
    private ListenerList deviceNotifyListenerList;
    private ListenerList deviceSearchResponseListenerList;
    private ListenerList eventListenerList;
    private String eventSubURI;
    private long expiredDeviceMonitoringInterval;
    private int httpPort;
    private HTTPServerList httpServerList;
    private Mutex mutex;
    private boolean nmprMode;
    private RenewSubscriber renewSubscriber;
    private int searchMx;
    private SSDPNotifySocketList ssdpNotifySocketList;
    private int ssdpPort;
    private SSDPSearchResponseSocketList ssdpSearchResponseSocketList;
    private Object userData;

    static {
        UPnP.initialize();
    }

    private SSDPNotifySocketList getSSDPNotifySocketList() {
        return this.ssdpNotifySocketList;
    }

    private SSDPSearchResponseSocketList getSSDPSearchResponseSocketList() {
        return this.ssdpSearchResponseSocketList;
    }

    public ControlPoint(int i, int i2, InetAddress[] inetAddressArr) {
        this.mutex = new Mutex();
        this.ssdpPort = 0;
        this.httpPort = 0;
        this.devNodeList = new NodeList();
        this.deviceNotifyListenerList = new ListenerList();
        this.deviceSearchResponseListenerList = new ListenerList();
        this.deviceChangeListenerList = new ListenerList();
        this.searchMx = 3;
        this.httpServerList = new HTTPServerList();
        this.eventListenerList = new ListenerList();
        this.eventSubURI = DEFAULT_EVENTSUB_URI;
        this.userData = null;
        this.ssdpNotifySocketList = new SSDPNotifySocketList(inetAddressArr);
        this.ssdpSearchResponseSocketList = new SSDPSearchResponseSocketList(inetAddressArr);
        setSSDPPort(i);
        setHTTPPort(i2);
        setDeviceDisposer((Disposer) null);
        setExpiredDeviceMonitoringInterval(60);
        setRenewSubscriber((RenewSubscriber) null);
        setNMPRMode(false);
        setRenewSubscriber((RenewSubscriber) null);
    }

    public ControlPoint(int i, int i2) {
        this(i, i2, (InetAddress[]) null);
    }

    public ControlPoint() {
        this(DEFAULT_SSDP_PORT, DEFAULT_EVENTSUB_PORT);
    }

    public void finalize() {
        log.e((Object) "finalize");
        stop();
    }

    public void lock() {
        this.mutex.lock();
    }

    public void unlock() {
        this.mutex.unlock();
    }

    public int getSSDPPort() {
        return this.ssdpPort;
    }

    public void setSSDPPort(int i) {
        this.ssdpPort = i;
    }

    public int getHTTPPort() {
        return this.httpPort;
    }

    public void setHTTPPort(int i) {
        this.httpPort = i;
    }

    public void setNMPRMode(boolean z) {
        this.nmprMode = z;
    }

    public boolean isNMPRMode() {
        return this.nmprMode;
    }

    private void addDevice(Node node) {
        synchronized (this.devNodeList) {
            this.devNodeList.add(node);
        }
    }

    private boolean isValidLocation(String str) {
        return str != null && str.length() >= 1 && str.indexOf("http://[") < 0;
    }

    private synchronized void addDevice(SSDPPacket sSDPPacket) {
        if (sSDPPacket.isRootDevice()) {
            if (!isValidLocation(sSDPPacket.getLocation())) {
                CommonLog commonLog = log;
                commonLog.e((Object) "ssdpPacket.getLocation() = " + sSDPPacket.getLocation() + ", so drop it!!!");
                return;
            }
            Device device = getDevice(USN.getUDN(sSDPPacket.getUSN()));
            if (device != null) {
                device.setSSDPPacket(sSDPPacket);
                return;
            }
            try {
                Node parse = UPnP.getXMLParser().parse(new URL(sSDPPacket.getLocation()));
                Device device2 = getDevice(parse);
                if (device2 != null) {
                    device2.setSSDPPacket(sSDPPacket);
                    addDevice(parse);
                    performAddDeviceListener(device2);
                }
            } catch (MalformedURLException e) {
                Debug.warning(sSDPPacket.toString());
                Debug.warning((Exception) e);
            } catch (ParserException e2) {
                Debug.warning(sSDPPacket.toString());
                Debug.warning((Exception) e2);
            }
        }
    }

    private Device getDevice(Node node) {
        Node node2;
        if (node == null || (node2 = node.getNode("device")) == null) {
            return null;
        }
        return new Device(node, node2);
    }

    public DeviceList getDeviceList() {
        DeviceList deviceList = new DeviceList();
        synchronized (deviceList) {
            int size = this.devNodeList.size();
            for (int i = 0; i < size; i++) {
                Device device = getDevice(this.devNodeList.getNode(i));
                if (device != null) {
                    deviceList.add(device);
                }
            }
        }
        return deviceList;
    }

    public Device getDevice(String str) {
        synchronized (this.devNodeList) {
            int size = this.devNodeList.size();
            for (int i = 0; i < size; i++) {
                Device device = getDevice(this.devNodeList.getNode(i));
                if (device != null) {
                    if (device.isDevice(str)) {
                        return device;
                    }
                    Device device2 = device.getDevice(str);
                    if (device2 != null) {
                        return device2;
                    }
                }
            }
            return null;
        }
    }

    public boolean hasDevice(String str) {
        return getDevice(str) != null;
    }

    private void removeDevice(Node node) {
        Device device = getDevice(node);
        if (device != null && device.isRootDevice()) {
            performRemoveDeviceListener(device);
        }
        synchronized (this.devNodeList) {
            this.devNodeList.remove(node);
        }
    }

    /* access modifiers changed from: protected */
    public void removeDevice(Device device) {
        if (device != null) {
            removeDevice(device.getRootNode());
        }
    }

    /* access modifiers changed from: protected */
    public void removeDevice(String str) {
        removeDevice(getDevice(str));
    }

    private void removeDevice(SSDPPacket sSDPPacket) {
        if (sSDPPacket.isByeBye()) {
            removeDevice(USN.getUDN(sSDPPacket.getUSN()));
        }
    }

    public void removeExpiredDevices() {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        Device[] deviceArr = new Device[size];
        for (int i = 0; i < size; i++) {
            deviceArr[i] = deviceList.getDevice(i);
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (deviceArr[i2].isExpired()) {
                Debug.message("Expired device = " + deviceArr[i2].getFriendlyName());
                removeDevice(deviceArr[i2]);
            }
        }
    }

    public void setExpiredDeviceMonitoringInterval(long j) {
        this.expiredDeviceMonitoringInterval = j;
    }

    public long getExpiredDeviceMonitoringInterval() {
        return this.expiredDeviceMonitoringInterval;
    }

    public void setDeviceDisposer(Disposer disposer) {
        this.deviceDisposer = disposer;
    }

    public Disposer getDeviceDisposer() {
        return this.deviceDisposer;
    }

    public void addNotifyListener(NotifyListener notifyListener) {
        this.deviceNotifyListenerList.add(notifyListener);
    }

    public void removeNotifyListener(NotifyListener notifyListener) {
        this.deviceNotifyListenerList.remove(notifyListener);
    }

    public void performNotifyListener(SSDPPacket sSDPPacket) {
        int size = this.deviceNotifyListenerList.size();
        for (int i = 0; i < size; i++) {
            try {
                ((NotifyListener) this.deviceNotifyListenerList.get(i)).deviceNotifyReceived(sSDPPacket);
            } catch (Exception e) {
                Debug.warning("NotifyListener returned an error:", e);
            }
        }
    }

    public void addSearchResponseListener(SearchResponseListener searchResponseListener) {
        this.deviceSearchResponseListenerList.add(searchResponseListener);
    }

    public void removeSearchResponseListener(SearchResponseListener searchResponseListener) {
        this.deviceSearchResponseListenerList.remove(searchResponseListener);
    }

    public void performSearchResponseListener(SSDPPacket sSDPPacket) {
        int size = this.deviceSearchResponseListenerList.size();
        for (int i = 0; i < size; i++) {
            try {
                ((SearchResponseListener) this.deviceSearchResponseListenerList.get(i)).deviceSearchResponseReceived(sSDPPacket);
            } catch (Exception e) {
                Debug.warning("SearchResponseListener returned an error:", e);
            }
        }
    }

    public void addDeviceChangeListener(DeviceChangeListener deviceChangeListener) {
        this.deviceChangeListenerList.add(deviceChangeListener);
    }

    public void removeDeviceChangeListener(DeviceChangeListener deviceChangeListener) {
        this.deviceChangeListenerList.remove(deviceChangeListener);
    }

    public void performAddDeviceListener(Device device) {
        int size = this.deviceChangeListenerList.size();
        for (int i = 0; i < size; i++) {
            ((DeviceChangeListener) this.deviceChangeListenerList.get(i)).deviceAdded(device);
        }
    }

    public void performRemoveDeviceListener(Device device) {
        int size = this.deviceChangeListenerList.size();
        for (int i = 0; i < size; i++) {
            ((DeviceChangeListener) this.deviceChangeListenerList.get(i)).deviceRemoved(device);
        }
    }

    public void notifyReceived(SSDPPacket sSDPPacket) {
        if (sSDPPacket.isRootDevice()) {
            if (sSDPPacket.isAlive()) {
                addDevice(sSDPPacket);
            } else if (sSDPPacket.isByeBye()) {
                CommonLog commonLog = log;
                commonLog.e((Object) "is byebye message , packet = " + sSDPPacket.toString());
                removeDevice(sSDPPacket);
            }
        }
        performNotifyListener(sSDPPacket);
    }

    public void searchResponseReceived(SSDPPacket sSDPPacket) {
        if (sSDPPacket.isRootDevice()) {
            addDevice(sSDPPacket);
        }
        performSearchResponseListener(sSDPPacket);
    }

    public int getSearchMx() {
        return this.searchMx;
    }

    public void setSearchMx(int i) {
        this.searchMx = i;
    }

    public boolean search(String str, int i) {
        return getSSDPSearchResponseSocketList().post(new SSDPSearchRequest(str, i));
    }

    public boolean search(String str) {
        return search(str, 3);
    }

    public boolean search() {
        return search("upnp:rootdevice", 3);
    }

    private HTTPServerList getHTTPServerList() {
        return this.httpServerList;
    }

    public void httpRequestRecieved(HTTPRequest hTTPRequest) {
        if (Debug.isOn()) {
            hTTPRequest.print();
        }
        if (hTTPRequest.isNotifyRequest()) {
            NotifyRequest notifyRequest = new NotifyRequest(hTTPRequest);
            String sid = notifyRequest.getSID();
            long seq = notifyRequest.getSEQ();
            PropertyList propertyList = notifyRequest.getPropertyList();
            int size = propertyList.size();
            for (int i = 0; i < size; i++) {
                Property property = propertyList.getProperty(i);
                performEventListener(sid, seq, property.getName(), property.getValue());
            }
            hTTPRequest.returnOK();
            return;
        }
        hTTPRequest.returnBadRequest();
    }

    public void addEventListener(EventListener eventListener) {
        this.eventListenerList.add(eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        this.eventListenerList.remove(eventListener);
    }

    public void performEventListener(String str, long j, String str2, String str3) {
        int size = this.eventListenerList.size();
        for (int i = 0; i < size; i++) {
            ((EventListener) this.eventListenerList.get(i)).eventNotifyReceived(str, j, str2, str3);
        }
    }

    public String getEventSubURI() {
        return this.eventSubURI;
    }

    public void setEventSubURI(String str) {
        this.eventSubURI = str;
    }

    private String getEventSubCallbackURL(String str) {
        return HostInterface.getHostURL(str, getHTTPPort(), getEventSubURI());
    }

    public boolean subscribe(Service service, long j) {
        if (service.isSubscribed()) {
            return subscribe(service, service.getSID(), j);
        }
        Device rootDevice = service.getRootDevice();
        if (rootDevice == null) {
            return false;
        }
        String interfaceAddress = rootDevice.getInterfaceAddress();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setSubscribeRequest(service, getEventSubCallbackURL(interfaceAddress), j);
        SubscriptionResponse post = subscriptionRequest.post();
        if (post.isSuccessful()) {
            service.setSID(post.getSID());
            service.setTimeout(post.getTimeout());
            return true;
        }
        service.clearSID();
        return false;
    }

    public boolean subscribe(Service service) {
        return subscribe(service, -1);
    }

    public boolean subscribe(Service service, String str, long j) {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setRenewRequest(service, str, j);
        if (Debug.isOn()) {
            subscriptionRequest.print();
        }
        SubscriptionResponse post = subscriptionRequest.post();
        if (Debug.isOn()) {
            post.print();
        }
        if (post.isSuccessful()) {
            service.setSID(post.getSID());
            service.setTimeout(post.getTimeout());
            return true;
        }
        service.clearSID();
        return false;
    }

    public boolean subscribe(Service service, String str) {
        return subscribe(service, str, -1);
    }

    public boolean isSubscribed(Service service) {
        if (service == null) {
            return false;
        }
        return service.isSubscribed();
    }

    public boolean unsubscribe(Service service) {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setUnsubscribeRequest(service);
        if (!subscriptionRequest.post().isSuccessful()) {
            return false;
        }
        service.clearSID();
        return true;
    }

    public void unsubscribe(Device device) {
        ServiceList serviceList = device.getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.hasSID()) {
                unsubscribe(service);
            }
        }
        DeviceList deviceList = device.getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            unsubscribe(deviceList.getDevice(i2));
        }
    }

    public void unsubscribe() {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        for (int i = 0; i < size; i++) {
            unsubscribe(deviceList.getDevice(i));
        }
    }

    public Service getSubscriberService(String str) {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        for (int i = 0; i < size; i++) {
            Service subscriberService = deviceList.getDevice(i).getSubscriberService(str);
            if (subscriberService != null) {
                return subscriberService;
            }
        }
        return null;
    }

    public void renewSubscriberService(Device device, long j) {
        ServiceList serviceList = device.getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.isSubscribed() && !subscribe(service, service.getSID(), j)) {
                subscribe(service, j);
            }
        }
        DeviceList deviceList = device.getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            renewSubscriberService(deviceList.getDevice(i2), j);
        }
    }

    public void renewSubscriberService(long j) {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        for (int i = 0; i < size; i++) {
            renewSubscriberService(deviceList.getDevice(i), j);
        }
    }

    public void renewSubscriberService() {
        renewSubscriberService(-1);
    }

    public void setRenewSubscriber(RenewSubscriber renewSubscriber2) {
        this.renewSubscriber = renewSubscriber2;
    }

    public RenewSubscriber getRenewSubscriber() {
        return this.renewSubscriber;
    }

    public boolean start(String str, int i) {
        CommonLog commonLog = log;
        commonLog.e((Object) "start target = " + str + ", mx = " + i);
        stop();
        int hTTPPort = getHTTPPort();
        HTTPServerList hTTPServerList = getHTTPServerList();
        int i2 = 0;
        while (!hTTPServerList.open(hTTPPort)) {
            i2++;
            if (100 < i2) {
                return false;
            }
            setHTTPPort(hTTPPort + 1);
            hTTPPort = getHTTPPort();
        }
        hTTPServerList.addRequestListener(this);
        hTTPServerList.start();
        SSDPNotifySocketList sSDPNotifySocketList = getSSDPNotifySocketList();
        if (!sSDPNotifySocketList.open()) {
            return false;
        }
        sSDPNotifySocketList.setControlPoint(this);
        sSDPNotifySocketList.start();
        int sSDPPort = getSSDPPort();
        SSDPSearchResponseSocketList sSDPSearchResponseSocketList = getSSDPSearchResponseSocketList();
        int i3 = 0;
        while (!sSDPSearchResponseSocketList.open(sSDPPort)) {
            i3++;
            if (100 < i3) {
                return false;
            }
            setSSDPPort(sSDPPort + 1);
            sSDPPort = getSSDPPort();
        }
        sSDPSearchResponseSocketList.setControlPoint(this);
        sSDPSearchResponseSocketList.start();
        search(str, i);
        Disposer disposer = new Disposer(this);
        setDeviceDisposer(disposer);
        disposer.start();
        if (isNMPRMode()) {
            RenewSubscriber renewSubscriber2 = new RenewSubscriber(this);
            setRenewSubscriber(renewSubscriber2);
            renewSubscriber2.start();
        }
        return true;
    }

    public boolean start(String str) {
        return start(str, 3);
    }

    public boolean start() {
        return start("upnp:rootdevice", 3);
    }

    public boolean stop() {
        log.e((Object) "stop");
        unsubscribe();
        SSDPNotifySocketList sSDPNotifySocketList = getSSDPNotifySocketList();
        sSDPNotifySocketList.stop();
        sSDPNotifySocketList.close();
        sSDPNotifySocketList.clear();
        SSDPSearchResponseSocketList sSDPSearchResponseSocketList = getSSDPSearchResponseSocketList();
        sSDPSearchResponseSocketList.stop();
        sSDPSearchResponseSocketList.close();
        sSDPSearchResponseSocketList.clear();
        HTTPServerList hTTPServerList = getHTTPServerList();
        hTTPServerList.stop();
        hTTPServerList.close();
        hTTPServerList.clear();
        Disposer deviceDisposer2 = getDeviceDisposer();
        if (deviceDisposer2 != null) {
            deviceDisposer2.stop();
            setDeviceDisposer((Disposer) null);
        }
        RenewSubscriber renewSubscriber2 = getRenewSubscriber();
        if (renewSubscriber2 != null) {
            renewSubscriber2.stop();
            setRenewSubscriber((RenewSubscriber) null);
        }
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommonLog commonLog = log;
        commonLog.e((Object) "ready to clear devNodeList...devNodeList.size = " + this.devNodeList.size());
        try {
            if (this.devNodeList == null) {
                return true;
            }
            synchronized (this.devNodeList) {
                this.devNodeList = new NodeList();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }

    public void print() {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        Debug.message("Device Num = " + size);
        for (int i = 0; i < size; i++) {
            Device device = deviceList.getDevice(i);
            Debug.message(Operators.ARRAY_START_STR + i + "] " + device.getFriendlyName() + ", " + device.getLeaseTime() + ", " + device.getElapsedTime());
        }
    }
}
