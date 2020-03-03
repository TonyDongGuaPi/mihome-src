package cn.com.fmsh.nfcos.client.service.business;

import android.util.Log;
import cn.com.fmsh.nfcos.client.service.constants.Constants;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.Session;

public class OpenMobileApduHandler implements ApduHandler {
    private byte[] aid = null;
    private Channel channel;
    private int channelNo;
    private FMLog fmLog = null;
    private volatile boolean isRun = false;
    private byte[] lastOpenAid = null;
    private String logTag = "OpenMobileApduHandle";
    private Reader reader;
    private SEService seService;
    private SeServiceHandler seServiceHandler;
    private Session session;

    public byte[] getLastOpenAid() {
        return this.lastOpenAid;
    }

    public OpenMobileApduHandler(SEService sEService, SeServiceHandler seServiceHandler2, byte[] bArr, int i) {
        this.aid = bArr;
        this.lastOpenAid = bArr;
        this.seService = sEService;
        this.seServiceHandler = seServiceHandler2;
        this.channelNo = i;
    }

    public void setAid(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            this.aid = bArr;
        }
    }

    public int openSession(int i) {
        Reader[] readerArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        this.channelNo = i;
        try {
            readerArr = this.seService.getReaders();
        } catch (IllegalStateException unused) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.seService = this.seServiceHandler.seServiceReopen();
            if (this.seService != null) {
                readerArr = this.seService.getReaders();
            } else if (this.fmLog == null || !this.fmLog.getShowLogFlag()) {
                return Constants.ErrorCode.OPEN_MOBILE_SESERVICE_NULL;
            } else {
                this.fmLog.warn(this.logTag, "以Open Mobile访问上海交通卡时,SEService为空");
                return Constants.ErrorCode.OPEN_MOBILE_SESERVICE_NULL;
            }
        }
        if (readerArr == null) {
            if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                this.fmLog.warn(this.logTag, "以Open Mobile访问上海交通卡时,Readers为空");
            }
            return Constants.ErrorCode.OPEN_MOBILE_OPEN_READER_FAIL;
        } else if (readerArr.length < 1) {
            if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                this.fmLog.warn(this.logTag, "以Open Mobile访问上海交通卡时,Readers为空");
            }
            return Constants.ErrorCode.OPEN_MOBILE_OPEN_READER_FAIL;
        } else if (i <= readerArr.length) {
            this.reader = readerArr[i];
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "open reader name:" + this.reader.getName());
            try {
                this.session = this.reader.openSession();
                return 0;
            } catch (IOException e2) {
                if (this.fmLog == null || !this.fmLog.getShowLogFlag()) {
                    return Constants.ErrorCode.OPEN_MOBILE_OPEN_SESSION_FAIL;
                }
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, "以Open Mobile访问上海交通卡时, open session出现异常：" + Util4Java.getExceptionInfo(e2));
                return Constants.ErrorCode.OPEN_MOBILE_OPEN_SESSION_FAIL;
            }
        } else if (this.fmLog == null || !this.fmLog.getShowLogFlag()) {
            return Constants.ErrorCode.OPEN_MOBILE_CHANNEL_INVAILD;
        } else {
            this.fmLog.warn(this.logTag, "以Open Mobile访问上海交通卡时, 传入无效的通道编号");
            return Constants.ErrorCode.OPEN_MOBILE_CHANNEL_INVAILD;
        }
    }

    public byte[] transceive(byte[] bArr) throws FMScriptHandleException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "==request==:" + FM_Bytes.bytesToHexString(bArr));
        }
        if (this.channel == null || this.channel.isClosed()) {
            this.fmLog.debug(this.logTag, "transceive: channel is close");
            if (this.lastOpenAid == null || this.lastOpenAid.length < 1) {
                this.lastOpenAid = this.aid;
            }
            if (this.session == null || this.session.isClosed()) {
                int openSession = openSession(this.channelNo);
                if (openSession != 0) {
                    if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                        this.fmLog.warn(this.logTag, "Apdu指令执行时，openMobile open channel失败");
                    }
                    throw new FMScriptHandleException("Apdu指令执行时，openMobile打开session 失败 ，错误码:" + openSession);
                }
                this.fmLog.debug(this.logTag, "transceive: session open ok ");
            } else {
                this.fmLog.debug(this.logTag, "transceive: session open ");
            }
            if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.debug(str2, "open aid[" + FM_Bytes.bytesToHexString(this.lastOpenAid) + "]...");
            }
            boolean openLogicalChannel = openLogicalChannel(this.aid);
            if (!openLogicalChannel && Arrays.equals(this.aid, Constants.appAid.STPC_AID_EXT)) {
                openLogicalChannel = openLogicalChannel(Constants.appAid.STPC_AID);
                this.lastOpenAid = Constants.appAid.STPC_AID;
            }
            if (!openLogicalChannel) {
                throw new FMScriptHandleException("Apdu指令执行时，open  channel失败");
            }
        } else {
            this.fmLog.debug(this.logTag, "transceive: channel is open");
        }
        if (this.channel != null) {
            try {
                byte[] transmit = this.channel.transmit(bArr);
                if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.debug(str3, "==respApdu==:" + FM_Bytes.bytesToHexString(transmit));
                }
                return transmit;
            } catch (IOException e) {
                if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, "Apdu指令执行时，出现异常：" + Util4Java.getExceptionInfo(e));
                }
                throw new FMScriptHandleException(e.getMessage());
            } catch (IllegalStateException e2) {
                if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, "Apdu指令执行时，出现异常：" + Util4Java.getExceptionInfo(e2));
                }
                throw new FMScriptHandleException(e2.getMessage());
            } catch (IllegalArgumentException e3) {
                if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, "Apdu指令执行时，出现异常：" + Util4Java.getExceptionInfo(e3));
                }
                throw new FMScriptHandleException(e3.getMessage());
            }
        } else {
            throw new FMScriptHandleException("Apdu指令执行时，open channel[" + FM_Bytes.bytesToHexString(this.lastOpenAid) + "]失败");
        }
    }

    public ApduHandler.ApduHandlerType getApduHandlerType() {
        return ApduHandler.ApduHandlerType.OPEN_MOBILE;
    }

    public boolean open(byte[] bArr) {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (bArr == null || bArr.length < 1) {
            bArr = this.aid;
        }
        if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "open aid[" + FM_Bytes.bytesToHexString(bArr) + Operators.ARRAY_END_STR);
        }
        if (bArr == null || bArr.length <= 1) {
            bArr = this.lastOpenAid;
        } else {
            if (this.channel != null && !this.channel.isClosed()) {
                this.channel.close();
                if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                    this.fmLog.debug(this.logTag, "重新打开新的aid，原先打开的aid关闭成功");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.lastOpenAid = bArr;
        }
        if (bArr == null || bArr.length < 1) {
            bArr = this.aid;
        }
        if (this.session == null || this.session.isClosed()) {
            if (openSession(this.channelNo) != 0) {
                if (this.fmLog == null || !this.fmLog.getShowLogFlag()) {
                    return false;
                }
                this.fmLog.debug(this.logTag, "Apdu指令执行时，openMobile open channel失败");
                return false;
            } else if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
                this.fmLog.debug(this.logTag, "reOpen session sucess");
            }
        }
        boolean openLogicalChannel = openLogicalChannel(bArr);
        if (!openLogicalChannel && Arrays.equals(bArr, Constants.appAid.STPC_AID_EXT)) {
            openLogicalChannel = openLogicalChannel(Constants.appAid.STPC_AID);
            this.lastOpenAid = Constants.appAid.STPC_AID;
        }
        if (openLogicalChannel) {
            this.isRun = true;
        }
        return openLogicalChannel;
    }

    private boolean openLogicalChannel(byte[] bArr) {
        try {
            this.channel = this.session.openLogicalChannel(bArr);
            if (this.channel == null) {
                String str = this.logTag;
                Log.e(str, "open[" + FM_Bytes.bytesToHexString(bArr) + "]失败");
                return false;
            } else if (this.fmLog == null || !this.fmLog.getShowLogFlag()) {
                return true;
            } else {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.debug(str2, "open channel[" + FM_Bytes.bytesToHexString(bArr) + "] sucess");
                return true;
            }
        } catch (IOException e) {
            Log.e(this.logTag, Util4Java.getExceptionInfo(e));
            return false;
        } catch (NoSuchElementException e2) {
            String str3 = this.logTag;
            Log.e(str3, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常,NoSuchElementException:" + Util4Java.getExceptionInfo(e2));
            return false;
        } catch (IllegalStateException e3) {
            String str4 = this.logTag;
            Log.e(str4, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常,IllegalStateException:" + Util4Java.getExceptionInfo(e3));
            return false;
        } catch (NullPointerException e4) {
            String str5 = this.logTag;
            Log.e(str5, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常,NullPointerException:" + Util4Java.getExceptionInfo(e4));
            return false;
        } catch (IllegalArgumentException e5) {
            String str6 = this.logTag;
            Log.e(str6, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常,IllegalArgumentException:" + Util4Java.getExceptionInfo(e5));
            return false;
        } catch (SecurityException e6) {
            String str7 = this.logTag;
            Log.e(str7, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常,SecurityException:" + Util4Java.getExceptionInfo(e6));
            return false;
        } catch (Exception e7) {
            String str8 = this.logTag;
            Log.e(str8, "open[" + FM_Bytes.bytesToHexString(bArr) + "]出现异常:" + Util4Java.getExceptionInfo(e7));
            return false;
        }
    }

    public boolean connect() {
        if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
            this.fmLog.debug(this.logTag, "OpenMobileApduHandler connect...");
        }
        return open((byte[]) null);
    }

    public boolean isConnect() {
        if (this.isRun) {
            return true;
        }
        if (this.channel != null && !this.channel.isClosed()) {
            return true;
        }
        return false;
    }

    public void close() {
        this.isRun = false;
        if (this.fmLog != null && this.fmLog.getShowLogFlag()) {
            this.fmLog.debug(this.logTag, "OpenMobileApduHandler close");
        }
        if (this.channel != null) {
            this.channel.close();
        }
        if (this.session != null) {
            this.session.close();
        }
        this.channel = null;
        this.session = null;
    }

    public byte[] getAid() {
        return this.lastOpenAid;
    }
}
