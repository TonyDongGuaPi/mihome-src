package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.BusinessExtend;
import cn.com.fmsh.tsm.business.BusinessManager;
import cn.com.fmsh.tsm.business.CardAppInstall;
import cn.com.fmsh.tsm.business.CardAppTrade;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.SocketExceptionHandler;

public class BusinessManagerImpl implements BusinessManager {
    private BusinessExtend businessExtend = new BusinessExtendImpl(this.cardBusinessBasic);
    private CardAppInstallImpl cardAppDownload = new CardAppInstallImpl(this.cardBusinessBasic);
    private CardBusinessBasic cardBusinessBasic = new CardBusinessBasic();
    private CardAppTradeImpl cardTrade = new CardAppTradeImpl(this.cardBusinessBasic);

    public void setApduHandler(ApduHandler apduHandler) {
        this.cardBusinessBasic.setApduHandler(apduHandler);
    }

    public void setMobileInfo(byte[] bArr) {
        this.cardBusinessBasic.setMobileInfo(bArr);
    }

    public void setSocketExceptionHandle(SocketExceptionHandler socketExceptionHandler) {
        this.cardBusinessBasic.setSocketExceptionHandle(socketExceptionHandler);
    }

    public boolean setTerminalNumber(byte[] bArr) {
        if (bArr == null || bArr.length != 32) {
            return false;
        }
        this.cardBusinessBasic.setTerminalNumber(bArr);
        return true;
    }

    public boolean setSecurityCode(byte[] bArr) {
        if (bArr == null || bArr.length < 1 || bArr[0] != bArr.length - 1) {
            return false;
        }
        this.cardBusinessBasic.setSecurityCode(bArr);
        return true;
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        this.cardBusinessBasic.setLinkInfo(linkInfo);
    }

    public CardAppInstall getCardAppInstall() {
        return this.cardAppDownload;
    }

    public CardAppTrade getCardAppTrade() {
        return this.cardTrade;
    }

    public BusinessExtend getBusinessExtend() {
        return this.businessExtend;
    }

    public void registerCommunicationNotify(CommunicationNotify communicationNotify) {
        this.cardBusinessBasic.registerCommunicationNotify(communicationNotify);
    }

    public void registerLocalDataHandler(LocalDataHandler localDataHandler) {
        this.cardBusinessBasic.registerLocalDataHandler(localDataHandler);
    }

    public CardBusinessBasic getCardBusinessBasic() {
        return this.cardBusinessBasic;
    }

    public ErrorCodeHandler getErrorCodeHandler() {
        return this.cardBusinessBasic.getErrorCodeHandler();
    }
}
