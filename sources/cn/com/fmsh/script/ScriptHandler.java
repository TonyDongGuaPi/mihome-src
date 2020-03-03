package cn.com.fmsh.script;

import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.exception.FMScriptHandleException;

public interface ScriptHandler {
    void cancel();

    ApduReponseList execute(ApduRequestList apduRequestList) throws FMScriptHandleException;

    byte[] execute(byte[] bArr) throws FMScriptHandleException;

    void setApduFilterDataInit(ApduFilterDataInit apduFilterDataInit);

    void setApduHandler(ApduHandler apduHandler);
}
