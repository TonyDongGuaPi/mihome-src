package cn.com.fmsh.communication;

import cn.com.fmsh.communication.core.TerminalCommunicationHandler;
import cn.com.fmsh.communication.core.TerminalCommunicationListImpl;

public class CommunicationFactory {
    private static TerminalCommunication terminalCommunication;
    private static TerminalCommunicationList terminalCommunicationList;

    private CommunicationFactory() {
    }

    public static TerminalCommunication getTerminalCommunication() {
        if (terminalCommunication == null) {
            terminalCommunication = new TerminalCommunicationHandler();
        }
        return terminalCommunication;
    }

    public static TerminalCommunicationList getTerminalCommunicationList() {
        if (terminalCommunicationList == null) {
            terminalCommunicationList = new TerminalCommunicationListImpl();
        }
        return terminalCommunicationList;
    }
}
