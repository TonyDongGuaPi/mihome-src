package cn.com.fmsh.nfcos.client.service.business;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.IOException;

public class NFCApduHandler implements ApduHandler {
    private IsoDep dep;
    private FMLog fmlog = LogFactory.getInstance().getLog();
    private String logTag = NFCApduHandler.class.getName();

    public boolean open(byte[] bArr) {
        return true;
    }

    public boolean setTag(Tag tag) {
        if (tag == null) {
            if (this.fmlog != null) {
                this.fmlog.warn(this.logTag, "Apdu处理器设置Tag时，Tag对象为空");
            }
            return true;
        }
        this.dep = IsoDep.get(tag);
        if (this.dep != null) {
            return true;
        }
        if (this.fmlog == null) {
            return false;
        }
        this.fmlog.warn(this.logTag, "Apdu处理器设置Tag时，Tag的类型不是IsoDep");
        return false;
    }

    public boolean connect() {
        if (this.dep == null) {
            if (this.fmlog != null) {
                this.fmlog.warn(this.logTag, "Apdu处理器IsoDep connect时,dep is null");
            }
            return false;
        }
        if (this.dep.isConnected()) {
            close();
        }
        if (this.fmlog != null) {
            this.fmlog.warn(this.logTag, "Apdu处理器IsoDep connect...");
        }
        try {
            this.dep.connect();
            return true;
        } catch (IOException e) {
            if (this.fmlog != null) {
                FMLog fMLog = this.fmlog;
                String str = this.logTag;
                fMLog.warn(str, "Apdu处理器IsoDep connect时出现异常:" + Util4Java.getExceptionInfo(e));
            }
            return false;
        }
    }

    public ApduHandler.ApduHandlerType getApduHandlerType() {
        return ApduHandler.ApduHandlerType.NFC;
    }

    public boolean isConnect() {
        if (this.dep == null) {
            return false;
        }
        return this.dep.isConnected();
    }

    public byte[] transceive(byte[] bArr) throws FMScriptHandleException {
        if (this.dep == null) {
            return null;
        }
        if (!this.dep.isConnected()) {
            try {
                this.dep.connect();
            } catch (IOException e) {
                if (this.fmlog != null) {
                    FMLog fMLog = this.fmlog;
                    String str = this.logTag;
                    fMLog.error(str, "Tag connect exception :" + Util4Java.getExceptionInfo(e));
                }
                throw new FMScriptHandleException(e.getMessage());
            }
        }
        if (this.fmlog != null) {
            FMLog fMLog2 = this.fmlog;
            String str2 = this.logTag;
            fMLog2.debug(str2, "NFC Apdu request:" + FM_Bytes.bytesToHexString(bArr));
        }
        try {
            byte[] transceive = this.dep.transceive(bArr);
            if (this.fmlog != null) {
                FMLog fMLog3 = this.fmlog;
                String str3 = this.logTag;
                fMLog3.debug(str3, "NFC Apdu reponse:" + FM_Bytes.bytesToHexString(transceive));
            }
            return transceive;
        } catch (IOException e2) {
            if (this.fmlog != null) {
                FMLog fMLog4 = this.fmlog;
                String str4 = this.logTag;
                fMLog4.error(str4, "Tag connect exception :" + Util4Java.getExceptionInfo(e2));
            }
            throw new FMScriptHandleException(e2.getMessage());
        }
    }

    public void close() {
        Log.e(this.logTag, ".....dep close...");
        if (this.dep != null) {
            try {
                this.dep.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
