package cn.com.fmsh.script.core;

import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequest;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.bean.ApduResponse;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.Arrays;

public class ScriptHandlerImpl implements ScriptHandler {
    private ApduFilter apduFilter;
    private ApduHandler apduHandle;
    private FMLog fmLog = LogFactory.getInstance().getLog();
    private boolean isStop;
    private String logTag = ScriptHandlerImpl.class.getName();

    public ScriptHandlerImpl(ApduHandler apduHandler) {
        this.apduHandle = apduHandler;
        this.apduFilter = new ApduFilter();
    }

    public void setApduHandler(ApduHandler apduHandler) {
        this.apduHandle = apduHandler;
    }

    public synchronized boolean isStop() {
        return this.isStop;
    }

    public synchronized void setStop(boolean z) {
        this.isStop = z;
    }

    public void setApduFilterDataInit(ApduFilterDataInit apduFilterDataInit) {
        if (apduFilterDataInit != null) {
            for (FilterPolicy addFilterPolicy : apduFilterDataInit.getFilterPolicies()) {
                this.apduFilter.addFilterPolicy(addFilterPolicy);
            }
        }
    }

    public ApduReponseList execute(ApduRequestList apduRequestList) throws FMScriptHandleException {
        byte[] bArr;
        int i;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        this.isStop = false;
        ApduRequest apduRequest = null;
        if (apduRequestList == null) {
            return null;
        }
        ApduReponseList apduReponseList = new ApduReponseList();
        ApduRequest firstApduRequest = apduRequestList.getFirstApduRequest();
        if (firstApduRequest == null) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "多条指令执行时，获取第一条指令失败");
            }
            new FMScriptHandleException("多条指令执行时，获取第一条指令失败").setType(FMScriptHandleException.ScriptHandleExceptionType.INVALID_FIRST_ID);
            return null;
        }
        while (!isStop()) {
            byte[] apdu = firstApduRequest.getApdu();
            if (this.apduHandle.getApduHandlerType() == ApduHandler.ApduHandlerType.OPEN_MOBILE) {
                byte[] filter = this.apduFilter.filter(apdu);
                if (filter == null) {
                    bArr = this.apduHandle.transceive(apdu);
                } else if (this.apduHandle.open(filter)) {
                    bArr = new byte[2];
                    bArr[0] = Constants.TagName.SYSTEM_VERSION;
                } else {
                    bArr = new byte[]{Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ACTIVITY_END};
                }
            } else {
                bArr = this.apduHandle.transceive(apdu);
            }
            ApduResponse apduResponse = new ApduResponse();
            apduResponse.setId(firstApduRequest.getId());
            apduResponse.setApdu(firstApduRequest.getApdu());
            if (bArr == null || bArr.length < 2) {
                apduResponse.setResult(new byte[]{1});
                apduReponseList.add(apduResponse);
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.warn(str, "多条指令执行时，[" + firstApduRequest.getId() + "]指令执行失败");
                }
            } else {
                apduResponse.setResult(bArr);
                apduReponseList.add(apduResponse);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, bArr.length - 2, bArr.length);
                if (firstApduRequest.getTag() == -96) {
                    if (firstApduRequest.getNext4Expectation(new byte[]{-1, -1}) != -1) {
                        apduRequest = apduRequestList.getApduRequest(firstApduRequest.getId() + 1);
                    } else if (firstApduRequest.getNext4Expectation(copyOfRange) != -1) {
                        apduRequest = apduRequestList.getApduRequest(firstApduRequest.getId() + 1);
                    } else if (this.fmLog != null) {
                        FMLog fMLog2 = this.fmLog;
                        String str2 = this.logTag;
                        fMLog2.error(str2, "多条指令执行时，[" + firstApduRequest.getId() + "]指令响应码[" + FM_Bytes.bytesToHexString(copyOfRange) + "]与期望值不符");
                    }
                } else if (firstApduRequest.getTag() == -92) {
                    if (firstApduRequest.isHaveExpectation()) {
                        i = firstApduRequest.getNext4Expectation(copyOfRange);
                        if (i == -1) {
                            i = firstApduRequest.getNext4Expectation(new byte[]{-1, -1});
                        }
                        if (i == 0) {
                            i = firstApduRequest.getId() + 1;
                        } else if (i == 255) {
                        }
                    } else {
                        i = firstApduRequest.getId() + 1;
                    }
                    apduRequest = apduRequestList.getApduRequest(i);
                }
                if (apduRequest != null) {
                    if (apduRequest.getId() > firstApduRequest.getId()) {
                        firstApduRequest = apduRequest;
                    } else {
                        FMScriptHandleException fMScriptHandleException = new FMScriptHandleException("多条指令执行时，待指令的下条指令的编号不大于本条指令的标识，执行失败");
                        fMScriptHandleException.setType(FMScriptHandleException.ScriptHandleExceptionType.INVALID_NEXT);
                        throw fMScriptHandleException;
                    }
                }
            }
            return apduReponseList;
        }
        FMScriptHandleException fMScriptHandleException2 = new FMScriptHandleException("多条指令执行时,当前操作被停止");
        fMScriptHandleException2.setType(FMScriptHandleException.ScriptHandleExceptionType.STOPED);
        throw fMScriptHandleException2;
    }

    public byte[] execute(byte[] bArr) throws FMScriptHandleException {
        return this.apduHandle.transceive(bArr);
    }

    public void cancel() {
        setStop(true);
    }
}
