package cn.com.fmsh.tsm.business;

import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.core.ErrorCodeHandler;

public interface BusinessManager {
    BusinessExtend getBusinessExtend();

    CardAppInstall getCardAppInstall();

    CardAppTrade getCardAppTrade();

    ErrorCodeHandler getErrorCodeHandler();

    void registerCommunicationNotify(CommunicationNotify communicationNotify);

    void registerLocalDataHandler(LocalDataHandler localDataHandler);

    void setApduHandler(ApduHandler apduHandler);

    void setLinkInfo(LinkInfo linkInfo);

    void setMobileInfo(byte[] bArr);

    boolean setSecurityCode(byte[] bArr);

    void setSocketExceptionHandle(SocketExceptionHandler socketExceptionHandler);

    boolean setTerminalNumber(byte[] bArr);
}
