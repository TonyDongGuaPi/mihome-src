package cn.com.fmsh.communication.core;

import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.TerminalCommunicationList;
import cn.com.fmsh.communication.exception.SocketException;
import java.util.HashMap;
import java.util.Map;

public class TerminalCommunicationListImpl implements TerminalCommunicationList {
    private volatile Map<String, TerminalCommunication> list = new HashMap();

    public TerminalCommunication getTerminalCommunication(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        TerminalCommunication terminalCommunication = this.list.get(str);
        if (terminalCommunication != null) {
            return terminalCommunication;
        }
        TerminalCommunicationHandler terminalCommunicationHandler = new TerminalCommunicationHandler();
        this.list.put(str, terminalCommunicationHandler);
        return terminalCommunicationHandler;
    }

    public void removeTerminalCommunication(String str) {
        this.list.remove(str);
    }

    public String[] getNames() {
        return (String[]) this.list.keySet().toArray(new String[0]);
    }

    public void disConnect(String str) throws SocketException {
        TerminalCommunication terminalCommunication = this.list.get(str);
        if (terminalCommunication != null && terminalCommunication.isConnect()) {
            terminalCommunication.disconnect();
        }
    }

    public void disConnect() throws SocketException {
        for (String str : (String[]) this.list.keySet().toArray(new String[0])) {
            TerminalCommunication terminalCommunication = this.list.get(str);
            if (terminalCommunication != null && terminalCommunication.isConnect()) {
                terminalCommunication.disconnect();
            }
        }
    }
}
