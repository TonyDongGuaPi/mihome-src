package org.cybergarage.upnp;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import org.cybergarage.http.HTTP;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.upnp.device.NTS;
import org.cybergarage.upnp.device.ST;
import org.cybergarage.upnp.event.NotifyRequest;
import org.cybergarage.upnp.event.Subscriber;
import org.cybergarage.upnp.event.SubscriberList;
import org.cybergarage.upnp.ssdp.SSDPNotifyRequest;
import org.cybergarage.upnp.ssdp.SSDPNotifySocket;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.upnp.xml.ServiceData;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.Debug;
import org.cybergarage.util.LogFactory;
import org.cybergarage.util.Mutex;
import org.cybergarage.util.StringUtil;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;

public class Service {
    private static final String CONTROL_URL = "controlURL";
    public static final String ELEM_NAME = "service";
    private static final String EVENT_SUB_URL = "eventSubURL";
    public static final String MAJOR = "major";
    public static final String MAJOR_VALUE = "1";
    public static final String MINOR = "minor";
    public static final String MINOR_VALUE = "0";
    private static final String SCPDURL = "SCPDURL";
    public static final String SCPD_ROOTNODE = "scpd";
    public static final String SCPD_ROOTNODE_NS = "urn:schemas-upnp-org:service-1-0";
    private static final String SERVICE_ID = "serviceId";
    private static final String SERVICE_TYPE = "serviceType";
    public static final String SPEC_VERSION = "specVersion";
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private Mutex mutex;
    private Node serviceNode;
    private Object userData;

    public Node getServiceNode() {
        return this.serviceNode;
    }

    public Service() {
        this(new Node("service"));
        Node node = new Node("specVersion");
        Node node2 = new Node("major");
        node2.setValue("1");
        node.addNode(node2);
        Node node3 = new Node("minor");
        node3.setValue("0");
        node.addNode(node3);
        Node node4 = new Node(SCPD_ROOTNODE);
        node4.addAttribute("xmlns", SCPD_ROOTNODE_NS);
        node4.addNode(node);
        getServiceData().setSCPDNode(node4);
    }

    public Service(Node node) {
        this.mutex = new Mutex();
        this.userData = null;
        this.serviceNode = node;
    }

    public void lock() {
        this.mutex.lock();
    }

    public void unlock() {
        this.mutex.unlock();
    }

    public static boolean isServiceNode(Node node) {
        return "service".equals(node.getName());
    }

    private Node getDeviceNode() {
        Node parentNode = getServiceNode().getParentNode();
        if (parentNode == null) {
            return null;
        }
        return parentNode.getParentNode();
    }

    private Node getRootNode() {
        return getServiceNode().getRootNode();
    }

    public Device getDevice() {
        return new Device(getRootNode(), getDeviceNode());
    }

    public Device getRootDevice() {
        return getDevice().getRootDevice();
    }

    public void setServiceType(String str) {
        getServiceNode().setNode("serviceType", str);
    }

    public String getServiceType() {
        return getServiceNode().getNodeValue("serviceType");
    }

    public void setServiceID(String str) {
        getServiceNode().setNode("serviceId", str);
    }

    public String getServiceID() {
        return getServiceNode().getNodeValue("serviceId");
    }

    private boolean isURL(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str2.equals(str) || str2.equals(HTTP.toRelativeURL(str, false));
    }

    public void setSCPDURL(String str) {
        getServiceNode().setNode(SCPDURL, str);
    }

    public String getSCPDURL() {
        return getServiceNode().getNodeValue(SCPDURL);
    }

    public boolean isSCPDURL(String str) {
        return isURL(getSCPDURL(), str);
    }

    public void setControlURL(String str) {
        getServiceNode().setNode(CONTROL_URL, str);
    }

    public String getControlURL() {
        return getServiceNode().getNodeValue(CONTROL_URL);
    }

    public boolean isControlURL(String str) {
        return isURL(getControlURL(), str);
    }

    public void setEventSubURL(String str) {
        getServiceNode().setNode(EVENT_SUB_URL, str);
    }

    public String getEventSubURL() {
        return getServiceNode().getNodeValue(EVENT_SUB_URL);
    }

    public boolean isEventSubURL(String str) {
        return isURL(getEventSubURL(), str);
    }

    public boolean loadSCPD(String str) throws InvalidDescriptionException {
        try {
            Node parse = UPnP.getXMLParser().parse(str);
            if (parse == null) {
                return false;
            }
            getServiceData().setSCPDNode(parse);
            return true;
        } catch (ParserException e) {
            throw new InvalidDescriptionException((Exception) e);
        }
    }

    public boolean loadSCPD(File file) throws ParserException {
        Node parse = UPnP.getXMLParser().parse(file);
        if (parse == null) {
            return false;
        }
        getServiceData().setSCPDNode(parse);
        return true;
    }

    public boolean loadSCPD(InputStream inputStream) throws ParserException {
        Node parse = UPnP.getXMLParser().parse(inputStream);
        if (parse == null) {
            return false;
        }
        getServiceData().setSCPDNode(parse);
        return true;
    }

    public void setDescriptionURL(String str) {
        getServiceData().setDescriptionURL(str);
    }

    public String getDescriptionURL() {
        return getServiceData().getDescriptionURL();
    }

    private Node getSCPDNode(URL url) throws ParserException {
        return UPnP.getXMLParser().parse(url);
    }

    private Node getSCPDNode(File file) throws ParserException {
        return UPnP.getXMLParser().parse(file);
    }

    private Node getSCPDNode() {
        ServiceData serviceData = getServiceData();
        Node sCPDNode = serviceData.getSCPDNode();
        if (sCPDNode != null) {
            return sCPDNode;
        }
        Device device = getDevice();
        if (device == null) {
            return null;
        }
        String scpdurl = getSCPDURL();
        if (scpdurl != null && scpdurl.length() > 0 && scpdurl.charAt(0) == '/') {
            scpdurl = scpdurl.substring(1);
        }
        try {
            Node sCPDNode2 = getSCPDNode(new URL(device.getAbsoluteURL(scpdurl)));
            if (sCPDNode2 != null) {
                serviceData.setSCPDNode(sCPDNode2);
                return sCPDNode2;
            }
        } catch (Exception unused) {
        }
        try {
            getSCPDNode(new File(device.getDescriptionFilePath() + HTTP.toRelativeURL(scpdurl)));
        } catch (Exception e) {
            Debug.warning(e);
        }
        return null;
    }

    public byte[] getSCPDData() {
        Node sCPDNode = getSCPDNode();
        if (sCPDNode == null) {
            return new byte[0];
        }
        return (((new String() + "<?xml version=\"1.0\" encoding=\"utf-8\"?>") + "\n") + sCPDNode.toString()).getBytes();
    }

    public ActionList getActionList() {
        Node node;
        ActionList actionList = new ActionList();
        Node sCPDNode = getSCPDNode();
        if (sCPDNode == null || (node = sCPDNode.getNode((String) ActionList.ELEM_NAME)) == null) {
            return actionList;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (Action.isActionNode(node2)) {
                actionList.add(new Action(this.serviceNode, node2));
            }
        }
        return actionList;
    }

    public Action getAction(String str) {
        ActionList actionList = getActionList();
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            Action action = actionList.getAction(i);
            String name = action.getName();
            if (name != null && name.equals(str)) {
                return action;
            }
        }
        return null;
    }

    public void addAction(Action action) {
        Iterator it = action.getArgumentList().iterator();
        while (it.hasNext()) {
            ((Argument) it.next()).setService(this);
        }
        Node sCPDNode = getSCPDNode();
        Node node = sCPDNode.getNode(ActionList.ELEM_NAME);
        if (node == null) {
            node = new Node(ActionList.ELEM_NAME);
            sCPDNode.addNode(node);
        }
        node.addNode(action.getActionNode());
    }

    public ServiceStateTable getServiceStateTable() {
        ServiceStateTable serviceStateTable = new ServiceStateTable();
        Node node = getSCPDNode().getNode(ServiceStateTable.ELEM_NAME);
        if (node == null) {
            return serviceStateTable;
        }
        Node serviceNode2 = getServiceNode();
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (StateVariable.isStateVariableNode(node2)) {
                serviceStateTable.add(new StateVariable(serviceNode2, node2));
            }
        }
        return serviceStateTable;
    }

    public StateVariable getStateVariable(String str) {
        ServiceStateTable serviceStateTable = getServiceStateTable();
        int size = serviceStateTable.size();
        for (int i = 0; i < size; i++) {
            StateVariable stateVariable = serviceStateTable.getStateVariable(i);
            String name = stateVariable.getName();
            if (name != null && name.equals(str)) {
                return stateVariable;
            }
        }
        return null;
    }

    public boolean hasStateVariable(String str) {
        return getStateVariable(str) != null;
    }

    public boolean isService(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(getServiceType()) || str.endsWith(getServiceID());
    }

    private ServiceData getServiceData() {
        Node serviceNode2 = getServiceNode();
        ServiceData serviceData = (ServiceData) serviceNode2.getUserData();
        if (serviceData != null) {
            return serviceData;
        }
        ServiceData serviceData2 = new ServiceData();
        serviceNode2.setUserData(serviceData2);
        serviceData2.setNode(serviceNode2);
        return serviceData2;
    }

    private String getNotifyServiceTypeNT() {
        return getServiceType();
    }

    private String getNotifyServiceTypeUSN() {
        return getDevice().getUDN() + "::" + getServiceType();
    }

    public void announce(String str) {
        String locationURL = getRootDevice().getLocationURL(str);
        String notifyServiceTypeNT = getNotifyServiceTypeNT();
        String notifyServiceTypeUSN = getNotifyServiceTypeUSN();
        Device device = getDevice();
        SSDPNotifyRequest sSDPNotifyRequest = new SSDPNotifyRequest();
        sSDPNotifyRequest.setServer(UPnP.getServerName());
        sSDPNotifyRequest.setLeaseTime(device.getLeaseTime());
        sSDPNotifyRequest.setLocation(locationURL);
        sSDPNotifyRequest.setNTS(NTS.ALIVE);
        sSDPNotifyRequest.setNT(notifyServiceTypeNT);
        sSDPNotifyRequest.setUSN(notifyServiceTypeUSN);
        SSDPNotifySocket sSDPNotifySocket = new SSDPNotifySocket(str);
        Device.notifyWait();
        sSDPNotifySocket.post(sSDPNotifyRequest);
    }

    public void byebye(String str) {
        String notifyServiceTypeNT = getNotifyServiceTypeNT();
        String notifyServiceTypeUSN = getNotifyServiceTypeUSN();
        SSDPNotifyRequest sSDPNotifyRequest = new SSDPNotifyRequest();
        sSDPNotifyRequest.setNTS(NTS.BYEBYE);
        sSDPNotifyRequest.setNT(notifyServiceTypeNT);
        sSDPNotifyRequest.setUSN(notifyServiceTypeUSN);
        SSDPNotifySocket sSDPNotifySocket = new SSDPNotifySocket(str);
        Device.notifyWait();
        sSDPNotifySocket.post(sSDPNotifyRequest);
    }

    public boolean serviceSearchResponse(SSDPPacket sSDPPacket) {
        String st = sSDPPacket.getST();
        if (st == null) {
            return false;
        }
        Device device = getDevice();
        String notifyServiceTypeNT = getNotifyServiceTypeNT();
        String notifyServiceTypeUSN = getNotifyServiceTypeUSN();
        if (ST.isAllDevice(st)) {
            device.postSearchResponse(sSDPPacket, notifyServiceTypeNT, notifyServiceTypeUSN);
        } else if (ST.isURNService(st)) {
            String serviceType = getServiceType();
            if (st.equals(serviceType)) {
                device.postSearchResponse(sSDPPacket, serviceType, notifyServiceTypeUSN);
            }
        }
        return true;
    }

    public void setQueryListener(QueryListener queryListener) {
        ServiceStateTable serviceStateTable = getServiceStateTable();
        int size = serviceStateTable.size();
        for (int i = 0; i < size; i++) {
            serviceStateTable.getStateVariable(i).setQueryListener(queryListener);
        }
    }

    public SubscriberList getSubscriberList() {
        return getServiceData().getSubscriberList();
    }

    public void addSubscriber(Subscriber subscriber) {
        getSubscriberList().add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        getSubscriberList().remove(subscriber);
    }

    public Subscriber getSubscriber(String str) {
        String sid;
        SubscriberList subscriberList = getSubscriberList();
        int size = subscriberList.size();
        for (int i = 0; i < size; i++) {
            Subscriber subscriber = subscriberList.getSubscriber(i);
            if (subscriber != null && (sid = subscriber.getSID()) != null && sid.equals(str)) {
                return subscriber;
            }
        }
        return null;
    }

    private boolean notify(Subscriber subscriber, StateVariable stateVariable) {
        String name = stateVariable.getName();
        String value = stateVariable.getValue();
        String deliveryHost = subscriber.getDeliveryHost();
        int deliveryPort = subscriber.getDeliveryPort();
        NotifyRequest notifyRequest = new NotifyRequest();
        notifyRequest.setRequest(subscriber, name, value);
        if (!notifyRequest.post(deliveryHost, deliveryPort).isSuccessful()) {
            return false;
        }
        subscriber.incrementNotifyCount();
        return true;
    }

    public void notify(StateVariable stateVariable) {
        SubscriberList subscriberList = getSubscriberList();
        int size = subscriberList.size();
        Subscriber[] subscriberArr = new Subscriber[size];
        for (int i = 0; i < size; i++) {
            subscriberArr[i] = subscriberList.getSubscriber(i);
        }
        for (int i2 = 0; i2 < size; i2++) {
            Subscriber subscriber = subscriberArr[i2];
            if (subscriber != null && subscriber.isExpired()) {
                removeSubscriber(subscriber);
            }
        }
        int size2 = subscriberList.size();
        Subscriber[] subscriberArr2 = new Subscriber[size2];
        for (int i3 = 0; i3 < size2; i3++) {
            subscriberArr2[i3] = subscriberList.getSubscriber(i3);
        }
        for (int i4 = 0; i4 < size2; i4++) {
            Subscriber subscriber2 = subscriberArr2[i4];
            if (subscriber2 != null) {
                notify(subscriber2, stateVariable);
            }
        }
    }

    public void notifyAllStateVariables() {
        ServiceStateTable serviceStateTable = getServiceStateTable();
        int size = serviceStateTable.size();
        for (int i = 0; i < size; i++) {
            StateVariable stateVariable = serviceStateTable.getStateVariable(i);
            if (stateVariable.isSendEvents()) {
                notify(stateVariable);
            }
        }
    }

    public String getSID() {
        return getServiceData().getSID();
    }

    public void setSID(String str) {
        getServiceData().setSID(str);
    }

    public void clearSID() {
        setSID("");
        setTimeout(0);
    }

    public boolean hasSID() {
        return StringUtil.hasData(getSID());
    }

    public boolean isSubscribed() {
        return hasSID();
    }

    public long getTimeout() {
        return getServiceData().getTimeout();
    }

    public void setTimeout(long j) {
        getServiceData().setTimeout(j);
    }

    public void setActionListener(ActionListener actionListener) {
        ActionList actionList = getActionList();
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            actionList.getAction(i).setActionListener(actionListener);
        }
    }

    public void addStateVariable(StateVariable stateVariable) {
        Node node = getSCPDNode().getNode(ServiceStateTable.ELEM_NAME);
        if (node == null) {
            node = new Node(ServiceStateTable.ELEM_NAME);
            getSCPDNode().addNode(node);
        }
        stateVariable.setServiceNode(getServiceNode());
        node.addNode(stateVariable.getStateVariableNode());
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
