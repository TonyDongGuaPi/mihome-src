package org.cybergarage.upnp;

import java.util.Iterator;
import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.control.QueryRequest;
import org.cybergarage.upnp.control.QueryResponse;
import org.cybergarage.upnp.xml.NodeData;
import org.cybergarage.upnp.xml.StateVariableData;
import org.cybergarage.util.Debug;
import org.cybergarage.xml.Node;

public class StateVariable extends NodeData {
    private static final String DATATYPE = "dataType";
    private static final String DEFAULT_VALUE = "defaultValue";
    public static final String ELEM_NAME = "stateVariable";
    private static final String NAME = "name";
    private static final String SENDEVENTS = "sendEvents";
    private static final String SENDEVENTS_NO = "no";
    private static final String SENDEVENTS_YES = "yes";
    private Node serviceNode;
    private Node stateVariableNode;
    private UPnPStatus upnpStatus;
    private Object userData;

    public Node getServiceNode() {
        return this.serviceNode;
    }

    /* access modifiers changed from: package-private */
    public void setServiceNode(Node node) {
        this.serviceNode = node;
    }

    public Service getService() {
        Node serviceNode2 = getServiceNode();
        if (serviceNode2 == null) {
            return null;
        }
        return new Service(serviceNode2);
    }

    public Node getStateVariableNode() {
        return this.stateVariableNode;
    }

    public StateVariable() {
        this.upnpStatus = new UPnPStatus();
        this.userData = null;
        this.serviceNode = null;
        this.stateVariableNode = new Node(ELEM_NAME);
    }

    public StateVariable(Node node, Node node2) {
        this.upnpStatus = new UPnPStatus();
        this.userData = null;
        this.serviceNode = node;
        this.stateVariableNode = node2;
    }

    public static boolean isStateVariableNode(Node node) {
        return ELEM_NAME.equals(node.getName());
    }

    public void setName(String str) {
        getStateVariableNode().setNode("name", str);
    }

    public String getName() {
        return getStateVariableNode().getNodeValue("name");
    }

    public void setDataType(String str) {
        getStateVariableNode().setNode(DATATYPE, str);
    }

    public String getDataType() {
        return getStateVariableNode().getNodeValue(DATATYPE);
    }

    public void setSendEvents(boolean z) {
        getStateVariableNode().setAttribute(SENDEVENTS, z ? "yes" : SENDEVENTS_NO);
    }

    public boolean isSendEvents() {
        String attributeValue = getStateVariableNode().getAttributeValue(SENDEVENTS);
        if (attributeValue != null && attributeValue.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }

    public void set(StateVariable stateVariable) {
        setName(stateVariable.getName());
        setValue(stateVariable.getValue());
        setDataType(stateVariable.getDataType());
        setSendEvents(stateVariable.isSendEvents());
    }

    public StateVariableData getStateVariableData() {
        Node stateVariableNode2 = getStateVariableNode();
        StateVariableData stateVariableData = (StateVariableData) stateVariableNode2.getUserData();
        if (stateVariableData != null) {
            return stateVariableData;
        }
        StateVariableData stateVariableData2 = new StateVariableData();
        stateVariableNode2.setUserData(stateVariableData2);
        stateVariableData2.setNode(stateVariableNode2);
        return stateVariableData2;
    }

    public void setValue(String str) {
        String value = getStateVariableData().getValue();
        if (value == null || !value.equals(str)) {
            getStateVariableData().setValue(str);
            Service service = getService();
            if (service != null && isSendEvents()) {
                service.notify(this);
            }
        }
    }

    public void setValue(int i) {
        setValue(Integer.toString(i));
    }

    public void setValue(long j) {
        setValue(Long.toString(j));
    }

    public String getValue() {
        return getStateVariableData().getValue();
    }

    public AllowedValueList getAllowedValueList() {
        AllowedValueList allowedValueList = new AllowedValueList();
        Node node = getStateVariableNode().getNode(AllowedValueList.ELEM_NAME);
        if (node == null) {
            return null;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (AllowedValue.isAllowedValueNode(node2)) {
                allowedValueList.add(new AllowedValue(node2));
            }
        }
        return allowedValueList;
    }

    public void setAllowedValueList(AllowedValueList allowedValueList) {
        getStateVariableNode().removeNode(AllowedValueList.ELEM_NAME);
        getStateVariableNode().removeNode(AllowedValueRange.ELEM_NAME);
        Node node = new Node(AllowedValueList.ELEM_NAME);
        Iterator it = allowedValueList.iterator();
        while (it.hasNext()) {
            node.addNode(((AllowedValue) it.next()).getAllowedValueNode());
        }
        getStateVariableNode().addNode(node);
    }

    public boolean hasAllowedValueList() {
        return getAllowedValueList() != null;
    }

    public AllowedValueRange getAllowedValueRange() {
        Node node = getStateVariableNode().getNode(AllowedValueRange.ELEM_NAME);
        if (node == null) {
            return null;
        }
        return new AllowedValueRange(node);
    }

    public void setAllowedValueRange(AllowedValueRange allowedValueRange) {
        getStateVariableNode().removeNode(AllowedValueList.ELEM_NAME);
        getStateVariableNode().removeNode(AllowedValueRange.ELEM_NAME);
        getStateVariableNode().addNode(allowedValueRange.getAllowedValueRangeNode());
    }

    public boolean hasAllowedValueRange() {
        return getAllowedValueRange() != null;
    }

    public QueryListener getQueryListener() {
        return getStateVariableData().getQueryListener();
    }

    public void setQueryListener(QueryListener queryListener) {
        getStateVariableData().setQueryListener(queryListener);
    }

    public boolean performQueryListener(QueryRequest queryRequest) {
        QueryListener queryListener = getQueryListener();
        if (queryListener == null) {
            return false;
        }
        QueryResponse queryResponse = new QueryResponse();
        StateVariable stateVariable = new StateVariable();
        stateVariable.set(this);
        stateVariable.setValue("");
        stateVariable.setStatus(404);
        if (queryListener.queryControlReceived(stateVariable)) {
            queryResponse.setResponse(stateVariable);
        } else {
            UPnPStatus status = stateVariable.getStatus();
            queryResponse.setFaultResponse(status.getCode(), status.getDescription());
        }
        queryRequest.post(queryResponse);
        return true;
    }

    public QueryResponse getQueryResponse() {
        return getStateVariableData().getQueryResponse();
    }

    private void setQueryResponse(QueryResponse queryResponse) {
        getStateVariableData().setQueryResponse(queryResponse);
    }

    public UPnPStatus getQueryStatus() {
        return getQueryResponse().getUPnPError();
    }

    public boolean postQuerylAction() {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setRequest(this);
        if (Debug.isOn()) {
            queryRequest.print();
        }
        QueryResponse post = queryRequest.post();
        if (Debug.isOn()) {
            post.print();
        }
        setQueryResponse(post);
        if (!post.isSuccessful()) {
            setValue(post.getReturnValue());
            return false;
        }
        setValue(post.getReturnValue());
        return true;
    }

    public void setStatus(int i, String str) {
        this.upnpStatus.setCode(i);
        this.upnpStatus.setDescription(str);
    }

    public void setStatus(int i) {
        setStatus(i, UPnPStatus.code2String(i));
    }

    public UPnPStatus getStatus() {
        return this.upnpStatus;
    }

    public String getDefaultValue() {
        return getStateVariableNode().getNodeValue(DEFAULT_VALUE);
    }

    public void setDefaultValue(String str) {
        getStateVariableNode().setNode(DEFAULT_VALUE, str);
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
