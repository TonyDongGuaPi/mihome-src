package org.cybergarage.upnp;

import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Calendar;
import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.http.HTTPRequestListener;
import org.cybergarage.http.HTTPResponse;
import org.cybergarage.http.HTTPServerList;
import org.cybergarage.net.HostInterface;
import org.cybergarage.soap.SOAPResponse;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.ActionRequest;
import org.cybergarage.upnp.control.ActionResponse;
import org.cybergarage.upnp.control.ControlRequest;
import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.control.QueryRequest;
import org.cybergarage.upnp.device.Advertiser;
import org.cybergarage.upnp.device.Description;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.upnp.device.NTS;
import org.cybergarage.upnp.device.ST;
import org.cybergarage.upnp.device.SearchListener;
import org.cybergarage.upnp.event.Subscriber;
import org.cybergarage.upnp.event.Subscription;
import org.cybergarage.upnp.event.SubscriptionRequest;
import org.cybergarage.upnp.event.SubscriptionResponse;
import org.cybergarage.upnp.ssdp.SSDPNotifyRequest;
import org.cybergarage.upnp.ssdp.SSDPNotifySocket;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.upnp.ssdp.SSDPSearchResponse;
import org.cybergarage.upnp.ssdp.SSDPSearchResponseSocket;
import org.cybergarage.upnp.ssdp.SSDPSearchSocketList;
import org.cybergarage.upnp.xml.DeviceData;
import org.cybergarage.util.Debug;
import org.cybergarage.util.FileUtil;
import org.cybergarage.util.Mutex;
import org.cybergarage.util.TimerUtil;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;

public class Device implements HTTPRequestListener, SearchListener {
    public static final String DEFAULT_DESCRIPTION_URI = "/description.xml";
    public static final int DEFAULT_DISCOVERY_WAIT_TIME = 300;
    public static final int DEFAULT_LEASE_TIME = 1800;
    public static final int DEFAULT_STARTUP_WAIT_TIME = 1000;
    private static final String DEVICE_TYPE = "deviceType";
    public static final String ELEM_NAME = "device";
    private static final String FRIENDLY_NAME = "friendlyName";
    public static final int HTTP_DEFAULT_PORT = 4004;
    private static final String MANUFACTURE = "manufacturer";
    private static final String MANUFACTURE_URL = "manufacturerURL";
    private static final String MODEL_DESCRIPTION = "modelDescription";
    private static final String MODEL_NAME = "modelName";
    private static final String MODEL_NUMBER = "modelNumber";
    private static final String MODEL_URL = "modelURL";
    private static final String SERIAL_NUMBER = "serialNumber";
    private static final String UDN = "UDN";
    private static final String UPC = "UPC";
    public static final String UPNP_ROOTDEVICE = "upnp:rootdevice";
    private static final String URLBASE_NAME = "URLBase";
    private static Calendar cal = Calendar.getInstance();
    private static final String presentationURL = "presentationURL";
    private String devUUID;
    private Node deviceNode;
    private Mutex mutex;
    private Node rootNode;
    private Object userData;
    private boolean wirelessMode;

    public Node getRootNode() {
        if (this.rootNode != null) {
            return this.rootNode;
        }
        if (this.deviceNode == null) {
            return null;
        }
        return this.deviceNode.getRootNode();
    }

    public Node getDeviceNode() {
        return this.deviceNode;
    }

    public void setRootNode(Node node) {
        this.rootNode = node;
    }

    public void setDeviceNode(Node node) {
        this.deviceNode = node;
    }

    static {
        UPnP.initialize();
    }

    public Device(Node node, Node node2) {
        this.mutex = new Mutex();
        this.userData = null;
        this.rootNode = node;
        this.deviceNode = node2;
        setUUID(UPnP.createUUID());
        setWirelessMode(false);
    }

    public Device() {
        this((Node) null, (Node) null);
    }

    public Device(Node node) {
        this((Node) null, node);
    }

    public Device(File file) throws InvalidDescriptionException {
        this((Node) null, (Node) null);
        loadDescription(file);
    }

    public Device(InputStream inputStream) throws InvalidDescriptionException {
        this((Node) null, (Node) null);
        loadDescription(inputStream);
    }

    public Device(String str) throws InvalidDescriptionException {
        this(new File(str));
    }

    public void lock() {
        this.mutex.lock();
    }

    public void unlock() {
        this.mutex.unlock();
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAbsoluteURL(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x00ee
            int r0 = r4.length()
            if (r0 > 0) goto L_0x000a
            goto L_0x00ee
        L_0x000a:
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0014 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0014 }
            return r0
        L_0x0014:
            r0 = 1
            if (r5 == 0) goto L_0x001d
            int r1 = r5.length()
            if (r1 > 0) goto L_0x0090
        L_0x001d:
            if (r6 == 0) goto L_0x0090
            int r1 = r6.length()
            if (r1 <= 0) goto L_0x0090
            java.lang.String r1 = "/"
            boolean r1 = r6.endsWith(r1)
            if (r1 == 0) goto L_0x0053
            java.lang.String r1 = "/"
            boolean r1 = r4.startsWith(r1)
            if (r1 != 0) goto L_0x0036
            goto L_0x0053
        L_0x0036:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            java.lang.String r2 = r4.substring(r0)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x006c }
            r2.<init>(r1)     // Catch:{ Exception -> 0x006c }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x006c }
            return r1
        L_0x0053:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x006c }
            r2.<init>(r1)     // Catch:{ Exception -> 0x006c }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x006c }
            return r1
        L_0x006c:
            java.lang.String r6 = org.cybergarage.http.HTTP.getAbsoluteURL(r6, r4)
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x007a }
            r1.<init>(r6)     // Catch:{ Exception -> 0x007a }
            java.lang.String r6 = r1.toString()     // Catch:{ Exception -> 0x007a }
            return r6
        L_0x007a:
            org.cybergarage.upnp.Device r6 = r3.getRootDevice()
            if (r6 == 0) goto L_0x0090
            java.lang.String r5 = r6.getLocation()
            java.lang.String r6 = org.cybergarage.http.HTTP.getHost(r5)
            int r5 = org.cybergarage.http.HTTP.getPort(r5)
            java.lang.String r5 = org.cybergarage.http.HTTP.getRequestHostURL(r6, r5)
        L_0x0090:
            if (r5 == 0) goto L_0x00ed
            int r6 = r5.length()
            if (r6 <= 0) goto L_0x00ed
            java.lang.String r6 = "/"
            boolean r6 = r5.endsWith(r6)
            if (r6 == 0) goto L_0x00c6
            java.lang.String r6 = "/"
            boolean r6 = r4.startsWith(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x00c6
        L_0x00a9:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r0 = r4.substring(r0)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x00df }
            r0.<init>(r6)     // Catch:{ Exception -> 0x00df }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x00df }
            return r6
        L_0x00c6:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x00df }
            r0.<init>(r6)     // Catch:{ Exception -> 0x00df }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x00df }
            return r6
        L_0x00df:
            java.lang.String r5 = org.cybergarage.http.HTTP.getAbsoluteURL(r5, r4)
            java.net.URL r6 = new java.net.URL     // Catch:{ Exception -> 0x00ed }
            r6.<init>(r5)     // Catch:{ Exception -> 0x00ed }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x00ed }
            return r5
        L_0x00ed:
            return r4
        L_0x00ee:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.upnp.Device.getAbsoluteURL(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public String getAbsoluteURL(String str) {
        try {
            return new URL(str).toString();
        } catch (Exception unused) {
            Device rootDevice = getRootDevice();
            String uRLBase = rootDevice.getURLBase();
            if (uRLBase == null || uRLBase.length() <= 0) {
                String location = rootDevice.getLocation();
                uRLBase = HTTP.getRequestHostURL(HTTP.getHost(location), HTTP.getPort(location));
            }
            try {
                return new URL(uRLBase + str).toString();
            } catch (Exception unused2) {
                try {
                    return new URL(HTTP.getAbsoluteURL(uRLBase, str)).toString();
                } catch (Exception unused3) {
                    return "";
                }
            }
        }
    }

    public void setNMPRMode(boolean z) {
        Node deviceNode2 = getDeviceNode();
        if (deviceNode2 != null) {
            if (z) {
                deviceNode2.setNode(UPnP.INMPR03, "1.0");
                deviceNode2.removeNode(URLBASE_NAME);
                return;
            }
            deviceNode2.removeNode(UPnP.INMPR03);
        }
    }

    public boolean isNMPRMode() {
        Node deviceNode2 = getDeviceNode();
        if (deviceNode2 == null || deviceNode2.getNode(UPnP.INMPR03) == null) {
            return false;
        }
        return true;
    }

    public void setWirelessMode(boolean z) {
        this.wirelessMode = z;
    }

    public boolean isWirelessMode() {
        return this.wirelessMode;
    }

    public int getSSDPAnnounceCount() {
        return (isNMPRMode() && isWirelessMode()) ? 4 : 1;
    }

    private void setUUID(String str) {
        this.devUUID = str;
    }

    public String getUUID() {
        return this.devUUID;
    }

    private void updateUDN() {
        setUDN(Subscription.UUID + getUUID());
    }

    public Device getRootDevice() {
        Node node;
        Node rootNode2 = getRootNode();
        if (rootNode2 == null || (node = rootNode2.getNode("device")) == null) {
            return null;
        }
        return new Device(rootNode2, node);
    }

    public Device getParentDevice() {
        if (isRootDevice()) {
            return null;
        }
        return new Device(getDeviceNode().getParentNode().getParentNode());
    }

    public void addService(Service service) {
        Node node = getDeviceNode().getNode("serviceList");
        if (node == null) {
            node = new Node("serviceList");
            getDeviceNode().addNode(node);
        }
        node.addNode(service.getServiceNode());
    }

    public void addDevice(Device device) {
        Node node = getDeviceNode().getNode(DeviceList.ELEM_NAME);
        if (node == null) {
            node = new Node(DeviceList.ELEM_NAME);
            getDeviceNode().addNode(node);
        }
        node.addNode(device.getDeviceNode());
        device.setRootNode((Node) null);
        if (getRootNode() == null) {
            Node node2 = new Node(RootDescription.ROOT_ELEMENT);
            node2.setNameSpace("", RootDescription.ROOT_ELEMENT_NAMESPACE);
            Node node3 = new Node("specVersion");
            Node node4 = new Node("major");
            node4.setValue("1");
            Node node5 = new Node("minor");
            node5.setValue("0");
            node3.addNode(node4);
            node3.addNode(node5);
            node2.addNode(node3);
            setRootNode(node2);
        }
    }

    private DeviceData getDeviceData() {
        Node deviceNode2 = getDeviceNode();
        DeviceData deviceData = (DeviceData) deviceNode2.getUserData();
        if (deviceData != null) {
            return deviceData;
        }
        DeviceData deviceData2 = new DeviceData();
        deviceNode2.setUserData(deviceData2);
        deviceData2.setNode(deviceNode2);
        return deviceData2;
    }

    private void setDescriptionFile(File file) {
        getDeviceData().setDescriptionFile(file);
    }

    public File getDescriptionFile() {
        return getDeviceData().getDescriptionFile();
    }

    private void setDescriptionURI(String str) {
        getDeviceData().setDescriptionURI(str);
    }

    private String getDescriptionURI() {
        return getDeviceData().getDescriptionURI();
    }

    private boolean isDescriptionURI(String str) {
        String descriptionURI = getDescriptionURI();
        if (str == null || descriptionURI == null) {
            return false;
        }
        return descriptionURI.equals(str);
    }

    public String getDescriptionFilePath() {
        File descriptionFile = getDescriptionFile();
        if (descriptionFile == null) {
            return "";
        }
        return descriptionFile.getAbsoluteFile().getParent();
    }

    public boolean loadDescription(InputStream inputStream) throws InvalidDescriptionException {
        try {
            this.rootNode = UPnP.getXMLParser().parse(inputStream);
            if (this.rootNode != null) {
                this.deviceNode = this.rootNode.getNode("device");
                if (this.deviceNode == null) {
                    throw new InvalidDescriptionException(Description.NOROOTDEVICE_EXCEPTION);
                } else if (!initializeLoadedDescription()) {
                    return false;
                } else {
                    setDescriptionFile((File) null);
                    return true;
                }
            } else {
                throw new InvalidDescriptionException(Description.NOROOT_EXCEPTION);
            }
        } catch (ParserException e) {
            throw new InvalidDescriptionException((Exception) e);
        }
    }

    public boolean loadDescription(String str) throws InvalidDescriptionException {
        try {
            this.rootNode = UPnP.getXMLParser().parse(str);
            if (this.rootNode != null) {
                this.deviceNode = this.rootNode.getNode("device");
                if (this.deviceNode == null) {
                    throw new InvalidDescriptionException(Description.NOROOTDEVICE_EXCEPTION);
                } else if (!initializeLoadedDescription()) {
                    return false;
                } else {
                    setDescriptionFile((File) null);
                    return true;
                }
            } else {
                throw new InvalidDescriptionException(Description.NOROOT_EXCEPTION);
            }
        } catch (ParserException e) {
            throw new InvalidDescriptionException((Exception) e);
        }
    }

    public boolean loadDescription(File file) throws InvalidDescriptionException {
        try {
            this.rootNode = UPnP.getXMLParser().parse(file);
            if (this.rootNode != null) {
                this.deviceNode = this.rootNode.getNode("device");
                if (this.deviceNode == null) {
                    throw new InvalidDescriptionException(Description.NOROOTDEVICE_EXCEPTION, file);
                } else if (!initializeLoadedDescription()) {
                    return false;
                } else {
                    setDescriptionFile(file);
                    return true;
                }
            } else {
                throw new InvalidDescriptionException(Description.NOROOT_EXCEPTION, file);
            }
        } catch (ParserException e) {
            throw new InvalidDescriptionException((Exception) e);
        }
    }

    private boolean initializeLoadedDescription() {
        setDescriptionURI(DEFAULT_DESCRIPTION_URI);
        setLeaseTime(1800);
        setHTTPPort(HTTP_DEFAULT_PORT);
        if (hasUDN()) {
            return true;
        }
        updateUDN();
        return true;
    }

    public static boolean isDeviceNode(Node node) {
        return "device".equals(node.getName());
    }

    public boolean isRootDevice() {
        return getRootNode().getNode("device").getNodeValue(UDN).equals(getUDN());
    }

    public void setSSDPPacket(SSDPPacket sSDPPacket) {
        getDeviceData().setSSDPPacket(sSDPPacket);
    }

    public SSDPPacket getSSDPPacket() {
        if (!isRootDevice()) {
            return null;
        }
        return getDeviceData().getSSDPPacket();
    }

    public void setLocation(String str) {
        getDeviceData().setLocation(str);
    }

    public String getLocation() {
        SSDPPacket sSDPPacket = getSSDPPacket();
        if (sSDPPacket != null) {
            return sSDPPacket.getLocation();
        }
        return getDeviceData().getLocation();
    }

    public void setLeaseTime(int i) {
        getDeviceData().setLeaseTime(i);
        Advertiser advertiser = getAdvertiser();
        if (advertiser != null) {
            announce();
            advertiser.restart();
        }
    }

    public int getLeaseTime() {
        SSDPPacket sSDPPacket = getSSDPPacket();
        if (sSDPPacket != null) {
            return sSDPPacket.getLeaseTime();
        }
        return getDeviceData().getLeaseTime();
    }

    public long getTimeStamp() {
        SSDPPacket sSDPPacket = getSSDPPacket();
        if (sSDPPacket != null) {
            return sSDPPacket.getTimeStamp();
        }
        return 0;
    }

    public long getElapsedTime() {
        return (System.currentTimeMillis() - getTimeStamp()) / 1000;
    }

    public boolean isExpired() {
        return ((long) (getLeaseTime() + 60)) < getElapsedTime();
    }

    private void setURLBase(String str) {
        if (isRootDevice()) {
            Node node = getRootNode().getNode(URLBASE_NAME);
            if (node != null) {
                node.setValue(str);
                return;
            }
            Node node2 = new Node(URLBASE_NAME);
            node2.setValue(str);
            boolean hasNodes = getRootNode().hasNodes();
            getRootNode().insertNode(node2, 1);
        }
    }

    private void updateURLBase(String str) {
        setURLBase(HostInterface.getHostURL(str, getHTTPPort(), ""));
    }

    public String getURLBase() {
        return isRootDevice() ? getRootNode().getNodeValue(URLBASE_NAME) : "";
    }

    public void setDeviceType(String str) {
        getDeviceNode().setNode("deviceType", str);
    }

    public String getDeviceType() {
        return getDeviceNode().getNodeValue("deviceType");
    }

    public boolean isDeviceType(String str) {
        if (str == null) {
            return false;
        }
        return str.equals(getDeviceType());
    }

    public void setFriendlyName(String str) {
        getDeviceNode().setNode(FRIENDLY_NAME, str);
    }

    public String getFriendlyName() {
        return getDeviceNode().getNodeValue(FRIENDLY_NAME);
    }

    public void setManufacture(String str) {
        getDeviceNode().setNode("manufacturer", str);
    }

    public String getManufacture() {
        return getDeviceNode().getNodeValue("manufacturer");
    }

    public void setManufactureURL(String str) {
        getDeviceNode().setNode(MANUFACTURE_URL, str);
    }

    public String getManufactureURL() {
        return getDeviceNode().getNodeValue(MANUFACTURE_URL);
    }

    public void setModelDescription(String str) {
        getDeviceNode().setNode(MODEL_DESCRIPTION, str);
    }

    public String getModelDescription() {
        return getDeviceNode().getNodeValue(MODEL_DESCRIPTION);
    }

    public void setModelName(String str) {
        getDeviceNode().setNode(MODEL_NAME, str);
    }

    public String getModelName() {
        return getDeviceNode().getNodeValue(MODEL_NAME);
    }

    public void setModelNumber(String str) {
        getDeviceNode().setNode(MODEL_NUMBER, str);
    }

    public String getModelNumber() {
        return getDeviceNode().getNodeValue(MODEL_NUMBER);
    }

    public void setModelURL(String str) {
        getDeviceNode().setNode(MODEL_URL, str);
    }

    public String getModelURL() {
        return getDeviceNode().getNodeValue(MODEL_URL);
    }

    public void setSerialNumber(String str) {
        getDeviceNode().setNode(SERIAL_NUMBER, str);
    }

    public String getSerialNumber() {
        return getDeviceNode().getNodeValue(SERIAL_NUMBER);
    }

    public void setUDN(String str) {
        getDeviceNode().setNode(UDN, str);
    }

    public String getUDN() {
        return getDeviceNode().getNodeValue(UDN);
    }

    public boolean hasUDN() {
        String udn = getUDN();
        return udn != null && udn.length() > 0;
    }

    public void setUPC(String str) {
        getDeviceNode().setNode(UPC, str);
    }

    public String getUPC() {
        return getDeviceNode().getNodeValue(UPC);
    }

    public void setPresentationURL(String str) {
        getDeviceNode().setNode(presentationURL, str);
    }

    public String getPresentationURL() {
        return getDeviceNode().getNodeValue(presentationURL);
    }

    public DeviceList getDeviceList() {
        DeviceList deviceList = new DeviceList();
        Node node = getDeviceNode().getNode(DeviceList.ELEM_NAME);
        if (node == null) {
            return deviceList;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (isDeviceNode(node2)) {
                deviceList.add(new Device(node2));
            }
        }
        return deviceList;
    }

    public boolean isDevice(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(getUDN()) || str.equals(getFriendlyName()) || str.endsWith(getDeviceType());
    }

    public Device getDevice(String str) {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        for (int i = 0; i < size; i++) {
            Device device = deviceList.getDevice(i);
            if (device.isDevice(str)) {
                return device;
            }
            Device device2 = device.getDevice(str);
            if (device2 != null) {
                return device2;
            }
        }
        return null;
    }

    public Device getDeviceByDescriptionURI(String str) {
        DeviceList deviceList = getDeviceList();
        int size = deviceList.size();
        for (int i = 0; i < size; i++) {
            Device device = deviceList.getDevice(i);
            if (device.isDescriptionURI(str)) {
                return device;
            }
            Device deviceByDescriptionURI = device.getDeviceByDescriptionURI(str);
            if (deviceByDescriptionURI != null) {
                return deviceByDescriptionURI;
            }
        }
        return null;
    }

    public ServiceList getServiceList() {
        ServiceList serviceList = new ServiceList();
        Node node = getDeviceNode().getNode("serviceList");
        if (node == null) {
            return serviceList;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (Service.isServiceNode(node2)) {
                serviceList.add(new Service(node2));
            }
        }
        return serviceList;
    }

    public Service getService(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.isService(str)) {
                return service;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Service service2 = deviceList.getDevice(i2).getService(str);
            if (service2 != null) {
                return service2;
            }
        }
        return null;
    }

    public Service getServiceBySCPDURL(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.isSCPDURL(str)) {
                return service;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Service serviceBySCPDURL = deviceList.getDevice(i2).getServiceBySCPDURL(str);
            if (serviceBySCPDURL != null) {
                return serviceBySCPDURL;
            }
        }
        return null;
    }

    public Service getServiceByControlURL(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.isControlURL(str)) {
                return service;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Service serviceByControlURL = deviceList.getDevice(i2).getServiceByControlURL(str);
            if (serviceByControlURL != null) {
                return serviceByControlURL;
            }
        }
        return null;
    }

    public Service getServiceByEventSubURL(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (service.isEventSubURL(str)) {
                return service;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Service serviceByEventSubURL = deviceList.getDevice(i2).getServiceByEventSubURL(str);
            if (serviceByEventSubURL != null) {
                return serviceByEventSubURL;
            }
        }
        return null;
    }

    public Service getSubscriberService(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if (str.equals(service.getSID())) {
                return service;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Service subscriberService = deviceList.getDevice(i2).getSubscriberService(str);
            if (subscriberService != null) {
                return subscriberService;
            }
        }
        return null;
    }

    public StateVariable getStateVariable(String str, String str2) {
        StateVariable stateVariable;
        if (str == null && str2 == null) {
            return null;
        }
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            Service service = serviceList.getService(i);
            if ((str == null || service.getServiceType().equals(str)) && (stateVariable = service.getStateVariable(str2)) != null) {
                return stateVariable;
            }
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            StateVariable stateVariable2 = deviceList.getDevice(i2).getStateVariable(str, str2);
            if (stateVariable2 != null) {
                return stateVariable2;
            }
        }
        return null;
    }

    public StateVariable getStateVariable(String str) {
        return getStateVariable((String) null, str);
    }

    public Action getAction(String str) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            ActionList actionList = serviceList.getService(i).getActionList();
            int size2 = actionList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Action action = actionList.getAction(i2);
                String name = action.getName();
                if (name != null && name.equals(str)) {
                    return action;
                }
            }
        }
        DeviceList deviceList = getDeviceList();
        int size3 = deviceList.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Action action2 = deviceList.getDevice(i3).getAction(str);
            if (action2 != null) {
                return action2;
            }
        }
        return null;
    }

    public IconList getIconList() {
        IconList iconList = new IconList();
        Node node = getDeviceNode().getNode(IconList.ELEM_NAME);
        if (node == null) {
            return iconList;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (Icon.isIconNode(node2)) {
                iconList.add(new Icon(node2));
            }
        }
        return iconList;
    }

    public Icon getIcon(int i) {
        IconList iconList = getIconList();
        if (i >= 0 || iconList.size() - 1 >= i) {
            return iconList.getIcon(i);
        }
        return null;
    }

    public Icon getSmallestIcon() {
        IconList iconList = getIconList();
        int size = iconList.size();
        Icon icon = null;
        for (int i = 0; i < size; i++) {
            Icon icon2 = iconList.getIcon(i);
            if (icon == null || icon2.getWidth() < icon.getWidth()) {
                icon = icon2;
            }
        }
        return icon;
    }

    public String getLocationURL(String str) {
        return HostInterface.getHostURL(str, getHTTPPort(), getDescriptionURI());
    }

    private String getNotifyDeviceNT() {
        return !isRootDevice() ? getUDN() : "upnp:rootdevice";
    }

    private String getNotifyDeviceUSN() {
        if (!isRootDevice()) {
            return getUDN();
        }
        return getUDN() + "::" + "upnp:rootdevice";
    }

    private String getNotifyDeviceTypeNT() {
        return getDeviceType();
    }

    private String getNotifyDeviceTypeUSN() {
        return getUDN() + "::" + getDeviceType();
    }

    public static final void notifyWait() {
        TimerUtil.waitRandom(300);
    }

    public void announce(String str) {
        String locationURL = getLocationURL(str);
        SSDPNotifySocket sSDPNotifySocket = new SSDPNotifySocket(str);
        SSDPNotifyRequest sSDPNotifyRequest = new SSDPNotifyRequest();
        sSDPNotifyRequest.setServer(UPnP.getServerName());
        sSDPNotifyRequest.setLeaseTime(getLeaseTime());
        sSDPNotifyRequest.setLocation(locationURL);
        sSDPNotifyRequest.setNTS(NTS.ALIVE);
        if (isRootDevice()) {
            String notifyDeviceNT = getNotifyDeviceNT();
            String notifyDeviceUSN = getNotifyDeviceUSN();
            sSDPNotifyRequest.setNT(notifyDeviceNT);
            sSDPNotifyRequest.setUSN(notifyDeviceUSN);
            sSDPNotifySocket.post(sSDPNotifyRequest);
            String udn = getUDN();
            sSDPNotifyRequest.setNT(udn);
            sSDPNotifyRequest.setUSN(udn);
            sSDPNotifySocket.post(sSDPNotifyRequest);
        }
        String notifyDeviceTypeNT = getNotifyDeviceTypeNT();
        String notifyDeviceTypeUSN = getNotifyDeviceTypeUSN();
        sSDPNotifyRequest.setNT(notifyDeviceTypeNT);
        sSDPNotifyRequest.setUSN(notifyDeviceTypeUSN);
        sSDPNotifySocket.post(sSDPNotifyRequest);
        sSDPNotifySocket.close();
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            serviceList.getService(i).announce(str);
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            deviceList.getDevice(i2).announce(str);
        }
    }

    public void announce() {
        String[] strArr;
        notifyWait();
        InetAddress[] hTTPBindAddress = getDeviceData().getHTTPBindAddress();
        if (hTTPBindAddress != null) {
            strArr = new String[hTTPBindAddress.length];
            for (int i = 0; i < hTTPBindAddress.length; i++) {
                strArr[i] = hTTPBindAddress[i].getHostAddress();
            }
        } else {
            int nHostAddresses = HostInterface.getNHostAddresses();
            strArr = new String[nHostAddresses];
            for (int i2 = 0; i2 < nHostAddresses; i2++) {
                strArr[i2] = HostInterface.getHostAddress(i2);
            }
        }
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (!(strArr[i3] == null || strArr[i3].length() == 0)) {
                int sSDPAnnounceCount = getSSDPAnnounceCount();
                for (int i4 = 0; i4 < sSDPAnnounceCount; i4++) {
                    announce(strArr[i3]);
                }
            }
        }
    }

    public void byebye(String str) {
        SSDPNotifySocket sSDPNotifySocket = new SSDPNotifySocket(str);
        SSDPNotifyRequest sSDPNotifyRequest = new SSDPNotifyRequest();
        sSDPNotifyRequest.setNTS(NTS.BYEBYE);
        if (isRootDevice()) {
            String notifyDeviceNT = getNotifyDeviceNT();
            String notifyDeviceUSN = getNotifyDeviceUSN();
            sSDPNotifyRequest.setNT(notifyDeviceNT);
            sSDPNotifyRequest.setUSN(notifyDeviceUSN);
            sSDPNotifySocket.post(sSDPNotifyRequest);
        }
        String notifyDeviceTypeNT = getNotifyDeviceTypeNT();
        String notifyDeviceTypeUSN = getNotifyDeviceTypeUSN();
        sSDPNotifyRequest.setNT(notifyDeviceTypeNT);
        sSDPNotifyRequest.setUSN(notifyDeviceTypeUSN);
        sSDPNotifySocket.post(sSDPNotifyRequest);
        sSDPNotifySocket.close();
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            serviceList.getService(i).byebye(str);
        }
        DeviceList deviceList = getDeviceList();
        int size2 = deviceList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            deviceList.getDevice(i2).byebye(str);
        }
    }

    public void byebye() {
        String[] strArr;
        InetAddress[] hTTPBindAddress = getDeviceData().getHTTPBindAddress();
        if (hTTPBindAddress != null) {
            strArr = new String[hTTPBindAddress.length];
            for (int i = 0; i < hTTPBindAddress.length; i++) {
                strArr[i] = hTTPBindAddress[i].getHostAddress();
            }
        } else {
            int nHostAddresses = HostInterface.getNHostAddresses();
            strArr = new String[nHostAddresses];
            for (int i2 = 0; i2 < nHostAddresses; i2++) {
                strArr[i2] = HostInterface.getHostAddress(i2);
            }
        }
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (strArr[i3] != null && strArr[i3].length() > 0) {
                int sSDPAnnounceCount = getSSDPAnnounceCount();
                for (int i4 = 0; i4 < sSDPAnnounceCount; i4++) {
                    byebye(strArr[i3]);
                }
            }
        }
    }

    public boolean postSearchResponse(SSDPPacket sSDPPacket, String str, String str2) {
        String locationURL = getRootDevice().getLocationURL(sSDPPacket.getLocalAddress());
        SSDPSearchResponse sSDPSearchResponse = new SSDPSearchResponse();
        sSDPSearchResponse.setLeaseTime(getLeaseTime());
        sSDPSearchResponse.setDate(cal);
        sSDPSearchResponse.setST(str);
        sSDPSearchResponse.setUSN(str2);
        sSDPSearchResponse.setLocation(locationURL);
        sSDPSearchResponse.setMYNAME(getFriendlyName());
        TimerUtil.waitRandom(sSDPPacket.getMX() * 1000);
        String remoteAddress = sSDPPacket.getRemoteAddress();
        int remotePort = sSDPPacket.getRemotePort();
        SSDPSearchResponseSocket sSDPSearchResponseSocket = new SSDPSearchResponseSocket();
        if (Debug.isOn()) {
            sSDPSearchResponse.print();
        }
        int sSDPAnnounceCount = getSSDPAnnounceCount();
        for (int i = 0; i < sSDPAnnounceCount; i++) {
            sSDPSearchResponseSocket.post(remoteAddress, remotePort, sSDPSearchResponse);
        }
        return true;
    }

    public void deviceSearchResponse(SSDPPacket sSDPPacket) {
        String st = sSDPPacket.getST();
        if (st != null) {
            boolean isRootDevice = isRootDevice();
            String udn = getUDN();
            if (isRootDevice) {
                udn = udn + "::upnp:rootdevice";
            }
            if (ST.isAllDevice(st)) {
                String notifyDeviceNT = getNotifyDeviceNT();
                int i = isRootDevice ? 3 : 2;
                for (int i2 = 0; i2 < i; i2++) {
                    postSearchResponse(sSDPPacket, notifyDeviceNT, udn);
                }
            } else if (ST.isRootDevice(st)) {
                if (isRootDevice) {
                    postSearchResponse(sSDPPacket, "upnp:rootdevice", udn);
                }
            } else if (ST.isUUIDDevice(st)) {
                String udn2 = getUDN();
                if (st.equals(udn2)) {
                    postSearchResponse(sSDPPacket, udn2, udn);
                }
            } else if (ST.isURNDevice(st)) {
                String deviceType = getDeviceType();
                if (st.equals(deviceType)) {
                    postSearchResponse(sSDPPacket, deviceType, getUDN() + "::" + deviceType);
                }
            }
            ServiceList serviceList = getServiceList();
            int size = serviceList.size();
            for (int i3 = 0; i3 < size; i3++) {
                serviceList.getService(i3).serviceSearchResponse(sSDPPacket);
            }
            DeviceList deviceList = getDeviceList();
            int size2 = deviceList.size();
            for (int i4 = 0; i4 < size2; i4++) {
                deviceList.getDevice(i4).deviceSearchResponse(sSDPPacket);
            }
        }
    }

    public void deviceSearchReceived(SSDPPacket sSDPPacket) {
        deviceSearchResponse(sSDPPacket);
    }

    public void setHTTPPort(int i) {
        getDeviceData().setHTTPPort(i);
    }

    public int getHTTPPort() {
        return getDeviceData().getHTTPPort();
    }

    public void setHTTPBindAddress(InetAddress[] inetAddressArr) {
        getDeviceData().setHTTPBindAddress(inetAddressArr);
    }

    public InetAddress[] getHTTPBindAddress() {
        return getDeviceData().getHTTPBindAddress();
    }

    public String getSSDPIPv4MulticastAddress() {
        return getDeviceData().getMulticastIPv4Address();
    }

    public void getSSDPIPv4MulticastAddress(String str) {
        getDeviceData().setMulticastIPv4Address(str);
    }

    public String getSSDPIPv6MulticastAddress() {
        return getDeviceData().getMulticastIPv6Address();
    }

    public void getSSDPIPv6MulticastAddress(String str) {
        getDeviceData().setMulticastIPv6Address(str);
    }

    public void httpRequestRecieved(HTTPRequest hTTPRequest) {
        if (Debug.isOn()) {
            hTTPRequest.print();
        }
        if (hTTPRequest.isGetRequest() || hTTPRequest.isHeadRequest()) {
            httpGetRequestRecieved(hTTPRequest);
        } else if (hTTPRequest.isPostRequest()) {
            httpPostRequestRecieved(hTTPRequest);
        } else if (hTTPRequest.isSubscribeRequest() || hTTPRequest.isUnsubscribeRequest()) {
            deviceEventSubscriptionRecieved(new SubscriptionRequest(hTTPRequest));
        } else {
            hTTPRequest.returnBadRequest();
        }
    }

    private synchronized byte[] getDescriptionData(String str) {
        if (!isNMPRMode()) {
            updateURLBase(str);
        }
        Node rootNode2 = getRootNode();
        if (rootNode2 == null) {
            return new byte[0];
        }
        return (((new String() + "<?xml version=\"1.0\" encoding=\"utf-8\"?>") + "\n") + rootNode2.toString()).getBytes();
    }

    private void httpGetRequestRecieved(HTTPRequest hTTPRequest) {
        byte[] bArr;
        String uri = hTTPRequest.getURI();
        Debug.message("httpGetRequestRecieved = " + uri);
        if (uri == null) {
            hTTPRequest.returnBadRequest();
            return;
        }
        byte[] bArr2 = new byte[0];
        if (isDescriptionURI(uri)) {
            String localAddress = hTTPRequest.getLocalAddress();
            if (localAddress == null || localAddress.length() <= 0) {
                localAddress = HostInterface.getInterface();
            }
            bArr = getDescriptionData(localAddress);
        } else {
            Device deviceByDescriptionURI = getDeviceByDescriptionURI(uri);
            if (deviceByDescriptionURI != null) {
                bArr = deviceByDescriptionURI.getDescriptionData(hTTPRequest.getLocalAddress());
            } else {
                Service serviceBySCPDURL = getServiceBySCPDURL(uri);
                if (serviceBySCPDURL != null) {
                    bArr = serviceBySCPDURL.getSCPDData();
                } else {
                    hTTPRequest.returnBadRequest();
                    return;
                }
            }
        }
        HTTPResponse hTTPResponse = new HTTPResponse();
        if (FileUtil.isXMLFileName(uri)) {
            hTTPResponse.setContentType("text/xml; charset=\"utf-8\"");
        }
        hTTPResponse.setStatusCode(200);
        hTTPResponse.setContent(bArr);
        hTTPRequest.post(hTTPResponse);
    }

    private void httpPostRequestRecieved(HTTPRequest hTTPRequest) {
        if (hTTPRequest.isSOAPAction()) {
            soapActionRecieved(hTTPRequest);
        } else {
            hTTPRequest.returnBadRequest();
        }
    }

    private void soapBadActionRecieved(HTTPRequest hTTPRequest) {
        SOAPResponse sOAPResponse = new SOAPResponse();
        sOAPResponse.setStatusCode(400);
        hTTPRequest.post(sOAPResponse);
    }

    private void soapActionRecieved(HTTPRequest hTTPRequest) {
        Service serviceByControlURL = getServiceByControlURL(hTTPRequest.getURI());
        if (serviceByControlURL != null) {
            deviceControlRequestRecieved(new ActionRequest(hTTPRequest), serviceByControlURL);
        } else {
            soapBadActionRecieved(hTTPRequest);
        }
    }

    private void deviceControlRequestRecieved(ControlRequest controlRequest, Service service) {
        if (controlRequest.isQueryControl()) {
            deviceQueryControlRecieved(new QueryRequest(controlRequest), service);
        } else {
            deviceActionControlRecieved(new ActionRequest(controlRequest), service);
        }
    }

    private void invalidActionControlRecieved(ControlRequest controlRequest) {
        ActionResponse actionResponse = new ActionResponse();
        actionResponse.setFaultResponse(401);
        controlRequest.post(actionResponse);
    }

    private void invalidArgumentsControlRecieved(ControlRequest controlRequest) {
        ActionResponse actionResponse = new ActionResponse();
        actionResponse.setFaultResponse(402);
        controlRequest.post(actionResponse);
    }

    private void deviceActionControlRecieved(ActionRequest actionRequest, Service service) {
        if (Debug.isOn()) {
            actionRequest.print();
        }
        Action action = service.getAction(actionRequest.getActionName());
        if (action == null) {
            invalidActionControlRecieved(actionRequest);
            return;
        }
        try {
            action.getArgumentList().setReqArgs(actionRequest.getArgumentList());
            if (!action.performActionListener(actionRequest)) {
                invalidActionControlRecieved(actionRequest);
            }
        } catch (IllegalArgumentException unused) {
            invalidArgumentsControlRecieved(actionRequest);
        }
    }

    private void deviceQueryControlRecieved(QueryRequest queryRequest, Service service) {
        if (Debug.isOn()) {
            queryRequest.print();
        }
        String varName = queryRequest.getVarName();
        if (!service.hasStateVariable(varName)) {
            invalidActionControlRecieved(queryRequest);
        } else if (!getStateVariable(varName).performQueryListener(queryRequest)) {
            invalidActionControlRecieved(queryRequest);
        }
    }

    private void upnpBadSubscriptionRecieved(SubscriptionRequest subscriptionRequest, int i) {
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setErrorResponse(i);
        subscriptionRequest.post(subscriptionResponse);
    }

    private void deviceEventSubscriptionRecieved(SubscriptionRequest subscriptionRequest) {
        Service serviceByEventSubURL = getServiceByEventSubURL(subscriptionRequest.getURI());
        if (serviceByEventSubURL == null) {
            subscriptionRequest.returnBadRequest();
        } else if (!subscriptionRequest.hasCallback() && !subscriptionRequest.hasSID()) {
            upnpBadSubscriptionRecieved(subscriptionRequest, 412);
        } else if (subscriptionRequest.isUnsubscribeRequest()) {
            deviceEventUnsubscriptionRecieved(serviceByEventSubURL, subscriptionRequest);
        } else if (subscriptionRequest.hasCallback()) {
            deviceEventNewSubscriptionRecieved(serviceByEventSubURL, subscriptionRequest);
        } else if (subscriptionRequest.hasSID()) {
            deviceEventRenewSubscriptionRecieved(serviceByEventSubURL, subscriptionRequest);
        } else {
            upnpBadSubscriptionRecieved(subscriptionRequest, 412);
        }
    }

    private void deviceEventNewSubscriptionRecieved(Service service, SubscriptionRequest subscriptionRequest) {
        String callback = subscriptionRequest.getCallback();
        try {
            new URL(callback);
            long timeout = subscriptionRequest.getTimeout();
            String createSID = Subscription.createSID();
            Subscriber subscriber = new Subscriber();
            subscriber.setDeliveryURL(callback);
            subscriber.setTimeOut(timeout);
            subscriber.setSID(createSID);
            service.addSubscriber(subscriber);
            SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
            subscriptionResponse.setStatusCode(200);
            subscriptionResponse.setSID(createSID);
            subscriptionResponse.setTimeout(timeout);
            if (Debug.isOn()) {
                subscriptionResponse.print();
            }
            subscriptionRequest.post(subscriptionResponse);
            if (Debug.isOn()) {
                subscriptionResponse.print();
            }
            service.notifyAllStateVariables();
        } catch (Exception unused) {
            upnpBadSubscriptionRecieved(subscriptionRequest, 412);
        }
    }

    private void deviceEventRenewSubscriptionRecieved(Service service, SubscriptionRequest subscriptionRequest) {
        String sid = subscriptionRequest.getSID();
        Subscriber subscriber = service.getSubscriber(sid);
        if (subscriber == null) {
            upnpBadSubscriptionRecieved(subscriptionRequest, 412);
            return;
        }
        long timeout = subscriptionRequest.getTimeout();
        subscriber.setTimeOut(timeout);
        subscriber.renew();
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setStatusCode(200);
        subscriptionResponse.setSID(sid);
        subscriptionResponse.setTimeout(timeout);
        subscriptionRequest.post(subscriptionResponse);
        if (Debug.isOn()) {
            subscriptionResponse.print();
        }
    }

    private void deviceEventUnsubscriptionRecieved(Service service, SubscriptionRequest subscriptionRequest) {
        Subscriber subscriber = service.getSubscriber(subscriptionRequest.getSID());
        if (subscriber == null) {
            upnpBadSubscriptionRecieved(subscriptionRequest, 412);
            return;
        }
        service.removeSubscriber(subscriber);
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setStatusCode(200);
        subscriptionRequest.post(subscriptionResponse);
        if (Debug.isOn()) {
            subscriptionResponse.print();
        }
    }

    private HTTPServerList getHTTPServerList() {
        return getDeviceData().getHTTPServerList();
    }

    public void setSSDPPort(int i) {
        getDeviceData().setSSDPPort(i);
    }

    public int getSSDPPort() {
        return getDeviceData().getSSDPPort();
    }

    public void setSSDPBindAddress(InetAddress[] inetAddressArr) {
        getDeviceData().setSSDPBindAddress(inetAddressArr);
    }

    public InetAddress[] getSSDPBindAddress() {
        return getDeviceData().getSSDPBindAddress();
    }

    public void setMulticastIPv4Address(String str) {
        getDeviceData().setMulticastIPv4Address(str);
    }

    public String getMulticastIPv4Address() {
        return getDeviceData().getMulticastIPv4Address();
    }

    public void setMulticastIPv6Address(String str) {
        getDeviceData().setMulticastIPv6Address(str);
    }

    public String getMulticastIPv6Address() {
        return getDeviceData().getMulticastIPv6Address();
    }

    private SSDPSearchSocketList getSSDPSearchSocketList() {
        return getDeviceData().getSSDPSearchSocketList();
    }

    private void setAdvertiser(Advertiser advertiser) {
        getDeviceData().setAdvertiser(advertiser);
    }

    private Advertiser getAdvertiser() {
        return getDeviceData().getAdvertiser();
    }

    public boolean start() {
        stop(true);
        int hTTPPort = getHTTPPort();
        HTTPServerList hTTPServerList = getHTTPServerList();
        int i = 0;
        while (!hTTPServerList.open(hTTPPort)) {
            i++;
            if (100 < i) {
                return false;
            }
            setHTTPPort(hTTPPort + 1);
            hTTPPort = getHTTPPort();
        }
        hTTPServerList.addRequestListener(this);
        hTTPServerList.start();
        SSDPSearchSocketList sSDPSearchSocketList = getSSDPSearchSocketList();
        if (!sSDPSearchSocketList.open()) {
            return false;
        }
        sSDPSearchSocketList.addSearchListener(this);
        sSDPSearchSocketList.start();
        announce();
        Advertiser advertiser = new Advertiser(this);
        setAdvertiser(advertiser);
        advertiser.start();
        return true;
    }

    private boolean stop(boolean z) {
        if (z) {
            byebye();
        }
        HTTPServerList hTTPServerList = getHTTPServerList();
        hTTPServerList.stop();
        hTTPServerList.close();
        hTTPServerList.clear();
        SSDPSearchSocketList sSDPSearchSocketList = getSSDPSearchSocketList();
        sSDPSearchSocketList.stop();
        sSDPSearchSocketList.close();
        sSDPSearchSocketList.clear();
        Advertiser advertiser = getAdvertiser();
        if (advertiser != null) {
            advertiser.stop();
            setAdvertiser((Advertiser) null);
        }
        return true;
    }

    public boolean stop() {
        return stop(true);
    }

    public boolean isRunning() {
        return getAdvertiser() != null;
    }

    public String getInterfaceAddress() {
        SSDPPacket sSDPPacket = getSSDPPacket();
        if (sSDPPacket == null) {
            return "";
        }
        return sSDPPacket.getLocalAddress();
    }

    public void setActionListener(ActionListener actionListener) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            serviceList.getService(i).setActionListener(actionListener);
        }
    }

    public void setQueryListener(QueryListener queryListener) {
        ServiceList serviceList = getServiceList();
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            serviceList.getService(i).setQueryListener(queryListener);
        }
    }

    public void setActionListener(ActionListener actionListener, boolean z) {
        setActionListener(actionListener);
        if (z) {
            DeviceList deviceList = getDeviceList();
            int size = deviceList.size();
            for (int i = 0; i < size; i++) {
                deviceList.getDevice(i).setActionListener(actionListener, true);
            }
        }
    }

    public void setQueryListener(QueryListener queryListener, boolean z) {
        setQueryListener(queryListener);
        if (z) {
            DeviceList deviceList = getDeviceList();
            int size = deviceList.size();
            for (int i = 0; i < size; i++) {
                deviceList.getDevice(i).setQueryListener(queryListener, true);
            }
        }
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
