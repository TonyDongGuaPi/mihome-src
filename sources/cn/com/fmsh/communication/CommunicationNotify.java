package cn.com.fmsh.communication;

import cn.com.fmsh.FM_Exception;

public interface CommunicationNotify {
    void exceptionNotify(FM_Exception fM_Exception, Class<?> cls);

    void heartbeatNoReponseNotify();

    void newsNotify();

    void reponseMessageNotify(byte[] bArr);
}
