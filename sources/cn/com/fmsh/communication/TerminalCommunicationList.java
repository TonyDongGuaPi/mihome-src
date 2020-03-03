package cn.com.fmsh.communication;

import cn.com.fmsh.communication.exception.SocketException;

public interface TerminalCommunicationList {
    void disConnect() throws SocketException;

    String[] getNames();

    TerminalCommunication getTerminalCommunication(String str);

    void removeTerminalCommunication(String str);
}
