package cn.com.fmsh.communication;

import cn.com.fmsh.communication.core.CloseSessionRequest;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.communication.core.TerminalInfo;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.exception.InvalidParameterException;
import java.util.Date;

public interface TerminalCommunication {
    public static final int SERVER_SESSION_TIMEOUT = 540000;

    void cancel();

    boolean closeSession(CloseSessionRequest closeSessionRequest) throws InvalidParameterException, SocketException, CommunicationException, CloseSessionException;

    boolean connect(LinkInfo linkInfo) throws InvalidParameterException, SocketException;

    boolean disconnect() throws SocketException;

    Date getLastHeartBeat();

    byte[] getSessionNumber();

    long getSessionSerialNumber();

    boolean isConnect();

    boolean isOpenSession();

    boolean lastRequestDataIsNull();

    boolean openSession(TerminalInfo terminalInfo, boolean z) throws InvalidParameterException, SocketException, CommunicationException, OpenSessionException;

    void registerCommunicationNotify(CommunicationNotify communicationNotify);

    byte[] repeat() throws SocketException, CommunicationException;

    int repeatAsynchronous() throws InvalidParameterException;

    byte[] sendMessage(byte[] bArr) throws InvalidParameterException, SocketException, CommunicationException;

    int sendMessageAsynchronous(byte[] bArr) throws InvalidParameterException;

    void setExceptionTryCount(int i);

    void setInterval4Heartbeat(int i);
}
