package org.cybergarage.upnp;

import java.io.PrintStream;
import java.util.Iterator;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.ActionRequest;
import org.cybergarage.upnp.control.ActionResponse;
import org.cybergarage.upnp.control.ControlResponse;
import org.cybergarage.upnp.xml.ActionData;
import org.cybergarage.util.Debug;
import org.cybergarage.util.Mutex;
import org.cybergarage.xml.Node;

public class Action {
    public static final String ELEM_NAME = "action";
    private static final String NAME = "name";
    private Node actionNode;
    private Mutex mutex = new Mutex();
    private Node serviceNode;
    private UPnPStatus upnpStatus = new UPnPStatus();
    private Object userData = null;

    private Node getServiceNode() {
        return this.serviceNode;
    }

    public Service getService() {
        return new Service(getServiceNode());
    }

    /* access modifiers changed from: package-private */
    public void setService(Service service) {
        this.serviceNode = service.getServiceNode();
        Iterator it = getArgumentList().iterator();
        while (it.hasNext()) {
            ((Argument) it.next()).setService(service);
        }
    }

    public Node getActionNode() {
        return this.actionNode;
    }

    public Action(Node node) {
        this.serviceNode = node;
        this.actionNode = new Node("action");
    }

    public Action(Node node, Node node2) {
        this.serviceNode = node;
        this.actionNode = node2;
    }

    public Action(Action action) {
        this.serviceNode = action.getServiceNode();
        this.actionNode = action.getActionNode();
    }

    public void lock() {
        this.mutex.lock();
    }

    public void unlock() {
        this.mutex.unlock();
    }

    public static boolean isActionNode(Node node) {
        return "action".equals(node.getName());
    }

    public void setName(String str) {
        getActionNode().setNode("name", str);
    }

    public String getName() {
        return getActionNode().getNodeValue("name");
    }

    public ArgumentList getArgumentList() {
        ArgumentList argumentList = new ArgumentList();
        Node node = getActionNode().getNode(ArgumentList.ELEM_NAME);
        if (node == null) {
            return argumentList;
        }
        int nNodes = node.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node2 = node.getNode(i);
            if (Argument.isArgumentNode(node2)) {
                argumentList.add(new Argument(getServiceNode(), node2));
            }
        }
        return argumentList;
    }

    public void setArgumentList(ArgumentList argumentList) {
        Node node = getActionNode().getNode(ArgumentList.ELEM_NAME);
        if (node == null) {
            node = new Node(ArgumentList.ELEM_NAME);
            getActionNode().addNode(node);
        } else {
            node.removeAllNodes();
        }
        Iterator it = argumentList.iterator();
        while (it.hasNext()) {
            Argument argument = (Argument) it.next();
            argument.setService(getService());
            node.addNode(argument.getArgumentNode());
        }
    }

    public ArgumentList getInputArgumentList() {
        ArgumentList argumentList = getArgumentList();
        int size = argumentList.size();
        ArgumentList argumentList2 = new ArgumentList();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            if (argument.isInDirection()) {
                argumentList2.add(argument);
            }
        }
        return argumentList2;
    }

    public ArgumentList getOutputArgumentList() {
        ArgumentList argumentList = getArgumentList();
        int size = argumentList.size();
        ArgumentList argumentList2 = new ArgumentList();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            if (argument.isOutDirection()) {
                argumentList2.add(argument);
            }
        }
        return argumentList2;
    }

    public Argument getArgument(String str) {
        ArgumentList argumentList = getArgumentList();
        int size = argumentList.size();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            String name = argument.getName();
            if (name != null && str.equals(name)) {
                return argument;
            }
        }
        return null;
    }

    public void setArgumentValues(ArgumentList argumentList) {
        getArgumentList().set(argumentList);
    }

    public void setInArgumentValues(ArgumentList argumentList) {
        getArgumentList().setReqArgs(argumentList);
    }

    public void setOutArgumentValues(ArgumentList argumentList) {
        getArgumentList().setResArgs(argumentList);
    }

    public void setArgumentValue(String str, String str2) {
        Argument argument = getArgument(str);
        if (argument != null) {
            argument.setValue(str2);
        }
    }

    public void setArgumentValue(String str, int i) {
        setArgumentValue(str, Integer.toString(i));
    }

    private void clearOutputAgumentValues() {
        ArgumentList argumentList = getArgumentList();
        int size = argumentList.size();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            if (argument.isOutDirection()) {
                argument.setValue("");
            }
        }
    }

    public String getArgumentValue(String str) {
        Argument argument = getArgument(str);
        if (argument == null) {
            return "";
        }
        return argument.getValue();
    }

    public int getArgumentIntegerValue(String str) {
        Argument argument = getArgument(str);
        if (argument == null) {
            return 0;
        }
        return argument.getIntegerValue();
    }

    private ActionData getActionData() {
        Node actionNode2 = getActionNode();
        ActionData actionData = (ActionData) actionNode2.getUserData();
        if (actionData != null) {
            return actionData;
        }
        ActionData actionData2 = new ActionData();
        actionNode2.setUserData(actionData2);
        actionData2.setNode(actionNode2);
        return actionData2;
    }

    public ActionListener getActionListener() {
        return getActionData().getActionListener();
    }

    public void setActionListener(ActionListener actionListener) {
        getActionData().setActionListener(actionListener);
    }

    public boolean performActionListener(ActionRequest actionRequest) {
        ActionListener actionListener = getActionListener();
        if (actionListener == null) {
            return false;
        }
        ActionResponse actionResponse = new ActionResponse();
        setStatus(401);
        clearOutputAgumentValues();
        if (actionListener.actionControlReceived(this)) {
            actionResponse.setResponse(this);
        } else {
            UPnPStatus status = getStatus();
            actionResponse.setFaultResponse(status.getCode(), status.getDescription());
        }
        if (Debug.isOn()) {
            actionResponse.print();
        }
        actionRequest.post(actionResponse);
        return true;
    }

    private ControlResponse getControlResponse() {
        return getActionData().getControlResponse();
    }

    private void setControlResponse(ControlResponse controlResponse) {
        getActionData().setControlResponse(controlResponse);
    }

    public UPnPStatus getControlStatus() {
        return getControlResponse().getUPnPError();
    }

    public boolean postControlAction() {
        ArgumentList argumentList = getArgumentList();
        ArgumentList inputArgumentList = getInputArgumentList();
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setRequest(this, inputArgumentList);
        if (Debug.isOn()) {
            actionRequest.print();
        }
        ActionResponse post = actionRequest.post();
        if (Debug.isOn()) {
            post.print();
        }
        setControlResponse(post);
        setStatus(post.getStatusCode());
        if (!post.isSuccessful()) {
            return false;
        }
        try {
            argumentList.setResArgs(post.getResponse());
            return true;
        } catch (IllegalArgumentException unused) {
            setStatus(402, "Action succesfully delivered but invalid arguments returned.");
            return false;
        }
    }

    public void print() {
        PrintStream printStream = System.out;
        printStream.println("Action : " + getName());
        ArgumentList argumentList = getArgumentList();
        int size = argumentList.size();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            String name = argument.getName();
            String value = argument.getValue();
            String direction = argument.getDirection();
            PrintStream printStream2 = System.out;
            printStream2.println(" [" + i + "] = " + direction + ", " + name + ", " + value);
        }
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

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
