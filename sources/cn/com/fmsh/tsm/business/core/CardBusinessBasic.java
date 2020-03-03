package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.CommunicationFactory;
import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.TerminalCommunicationList;
import cn.com.fmsh.communication.core.CloseSessionRequest;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.communication.core.TerminalInfo;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.exception.InvalidParameterException;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.ScriptHandlerFactory;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.SocketExceptionHandler;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.core.Configration;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.algorithm.DES;
import cn.com.fmsh.util.algorithm.RSA;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CardBusinessBasic {
    private ApduHandler apduHandler;
    private CommunicationNotify communicationNotify;
    private Configration config = null;
    private ConfigKeyManager configKeyManager = new ConfigKeyManager();
    private ErrorCodeHandler errorCodeHandler;
    private final int exceptionTimeout = 1000;
    FMLog fmLog = LogFactory.getInstance().getLog();
    private LocalDataHandler localDataHandler;
    private final String logTag = CardBusinessBasic.class.getName();
    private IMessageHandler messageHandler;
    private byte[] mobileInfo;
    private ScriptHandler scriptHandler = null;
    private byte[] securityCode;
    private SocketExceptionHandler socketExceptionHandle;
    private TerminalCommunicationList terminalCommunicationList = CommunicationFactory.getTerminalCommunicationList();
    private byte[] terminalNumber;
    private LinkInfo userLinkInfo;

    public void setApduHandler(ApduHandler apduHandler2) {
        this.apduHandler = apduHandler2;
    }

    public ApduHandler getApduHandler() {
        return this.apduHandler;
    }

    public void setMobileInfo(byte[] bArr) {
        this.mobileInfo = bArr;
    }

    public void setSocketExceptionHandle(SocketExceptionHandler socketExceptionHandler) {
        this.socketExceptionHandle = socketExceptionHandler;
    }

    public SocketExceptionHandler getSocketExceptionHandler() {
        return this.socketExceptionHandle;
    }

    public byte[] getTerminalNumber() {
        return this.terminalNumber;
    }

    public void setTerminalNumber(byte[] bArr) {
        this.terminalNumber = bArr;
    }

    public byte[] getSecurityCode() {
        return this.securityCode;
    }

    public void setSecurityCode(byte[] bArr) {
        this.securityCode = bArr;
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        this.userLinkInfo = linkInfo;
    }

    public void registerCommunicationNotify(CommunicationNotify communicationNotify2) {
        this.communicationNotify = communicationNotify2;
    }

    /* access modifiers changed from: package-private */
    public boolean isConnect(String str) {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication == null) {
            return false;
        }
        return terminalCommunication.isConnect();
    }

    /* access modifiers changed from: package-private */
    public boolean isOpenSession(TerminalCommunication terminalCommunication) {
        return terminalCommunication.isOpenSession();
    }

    public Configration getConfigration() {
        if (this.config == null) {
            try {
                InputStream decryptFile = decryptFile(Constants.SYSTEM_CONFIG_FILE);
                if (!(decryptFile != null ? loadDefine(decryptFile) : false)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "链接到平台时，加载应用配置文件失败");
                    }
                    return null;
                }
            } catch (BusinessException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "脚本解析器初始化时，加载应用配置文件失败");
                }
                return null;
            }
        }
        return this.config;
    }

    public TerminalCommunication getTerminalCommunication(String str) {
        return this.terminalCommunicationList.getTerminalCommunication(str);
    }

    public IMessageHandler getMessageHandler() {
        InputStream inputStream;
        if (this.messageHandler == null) {
            try {
                inputStream = decryptFile("/message.xml");
            } catch (BusinessException unused) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取消息处理时，加载消息配置文件失败");
                }
                inputStream = null;
            }
            if (!(inputStream != null ? messageConfigLoad(inputStream) : false)) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取消息处理时，加载消息配置文件失败");
                }
                return null;
            }
        }
        return this.messageHandler;
    }

    public ScriptHandler getScriptHandler() {
        if (this.config == null) {
            this.config = getConfigration();
            if (this.config == null) {
                if (this.fmLog == null) {
                    return null;
                }
                this.fmLog.warn(this.logTag, "链接到平台时，加载应用配置文件失败");
                return null;
            }
        }
        if (this.scriptHandler == null) {
            this.scriptHandler = ScriptHandlerFactory.getInstance().getScriptHandler(getApduHandler());
            this.scriptHandler.setApduFilterDataInit(new ApduFilterDataInitImpl(this.config.getAids()));
        } else {
            this.scriptHandler.setApduHandler(getApduHandler());
        }
        return this.scriptHandler;
    }

    public boolean messageConfigLoad(InputStream inputStream) {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        this.messageHandler = MessageHandleFactory.getMessageHandler();
        try {
            if (this.messageHandler.loadDefine(inputStream) == 0) {
                return true;
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务处理时，消息解析器未加载配置文件失败");
            }
            this.messageHandler = null;
            return false;
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.error(str, "业务处理时，消息解析器未加载配置出现异常：" + Util4Java.getExceptionInfo(e));
            }
            return false;
        }
    }

    public ErrorCodeHandler getErrorCodeHandler() {
        InputStream inputStream;
        if (this.errorCodeHandler == null) {
            this.errorCodeHandler = new ErrorCodeHandler();
            try {
                inputStream = decryptFile(Constants.CODE_PROPERTIES_FILE);
            } catch (BusinessException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "加载平台响应过滤器出现异常:" + Util4Java.getExceptionInfo(e));
                }
                inputStream = null;
            }
            if (!this.errorCodeHandler.init(inputStream)) {
                this.errorCodeHandler = null;
            }
        }
        return this.errorCodeHandler;
    }

    public TerminalCommunication connect(String str) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str2 = this.logTag;
            fMLog.info(str2, "connect to " + str + "...");
        }
        if (this.config == null) {
            this.config = getConfigration();
            if (this.config == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "链接到平台时，加载应用配置文件失败");
                }
                return null;
            }
        }
        LinkInfo linkInfo = this.config.getLinkInfo(str);
        if (linkInfo == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "链接到平台时，配置文件中未定义该平台信息");
            }
            return null;
        }
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取通信实例失败");
            }
            return null;
        }
        terminalCommunication.registerCommunicationNotify(this.communicationNotify);
        if (terminalCommunication.isConnect()) {
            return terminalCommunication;
        }
        try {
            if (terminalCommunication.connect(linkInfo)) {
                return terminalCommunication;
            }
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, "链接到平台[" + linkInfo.getAddress() + ":" + linkInfo.getPort() + "]时，链接失败");
            }
            throw new BusinessException("链接到平台出现异常", BusinessException.ErrorMessage.local_communication_connect_fail);
        } catch (InvalidParameterException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "链接到平台时，传入的参数无效");
            }
            throw new BusinessException("链接到平台时,传入参数异常", BusinessException.ErrorMessage.local_communication_connect_param_error);
        } catch (SocketException unused2) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, "链接到平台[" + linkInfo.getAddress() + ":" + linkInfo.getPort() + "]时，链接失败");
            }
            throw new BusinessException("链接到平台出现异常", BusinessException.ErrorMessage.local_communication_connect_fail);
        }
    }

    public void disconnect(String str) throws BusinessException {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication != null) {
            try {
                terminalCommunication.disconnect();
            } catch (SocketException e) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str2 = this.logTag;
                    fMLog.warn(str2, "关闭终端和平台的链接出现异常：" + Util4Java.getExceptionInfo(e));
                }
                throw new BusinessException("关闭终端和平台的链接出现异常");
            }
        }
    }

    public void disconnectAll() throws BusinessException {
        try {
            this.terminalCommunicationList.disConnect();
        } catch (SocketException e) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, "关闭终端和平台的链接出现异常：" + Util4Java.getExceptionInfo(e));
            }
            throw new BusinessException("关闭终端和平台的链接出现异常");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean openSession(String str) throws BusinessException {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "签到时，终端连接到平台失败");
            }
            throw new BusinessException("签到时，链接到平台失败", BusinessException.ErrorMessage.local_communication_connect_fail);
        }
        if (!terminalCommunication.isConnect()) {
            terminalCommunication = connect(str);
        }
        if (!terminalCommunication.isConnect()) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "签到时，终端连接到平台失败");
            }
            throw new BusinessException("签到时，链接到平台失败", BusinessException.ErrorMessage.local_communication_connect_fail);
        }
        TerminalInfo terminalInfo = new TerminalInfo();
        Configration.Key[] keys = this.config.getKeys(str);
        if (keys == null || keys.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "终端向平台签到请求时，配置文件中未定义密钥信息，签到失败");
            }
            throw new BusinessException("终端向平台签到请求时，配置文件中未定义密钥信息，签到失败", BusinessException.ErrorMessage.local_communication_no_key);
        }
        int nextInt = keys.length > 1 ? new Random().nextInt(keys.length) : 0;
        terminalInfo.setKeyIndex((byte) keys[nextInt].index);
        terminalInfo.setExponent(keys[nextInt].exponent);
        terminalInfo.setModulus(keys[nextInt].modulus);
        terminalInfo.setTerminalType(this.config.getTerminalType());
        terminalInfo.setSecurityCode(this.securityCode);
        if (this.config.getTerminalNumber() != null) {
            int length = this.config.getTerminalNumber().length;
        }
        byte[] bArr = this.mobileInfo;
        if (bArr != null) {
            if (this.config.getSdkVersion() != null && this.config.getSdkVersion().length() > 0) {
                byte[] hexStringToBytes = FM_Bytes.hexStringToBytes(this.config.getSdkVersion());
                bArr = FM_Bytes.join(FM_Bytes.join(bArr, new byte[]{4, (byte) hexStringToBytes.length}), hexStringToBytes);
            }
            if (this.config.getBusinessVersion() != null && this.config.getBusinessVersion().length() > 0) {
                byte[] hexStringToBytes2 = FM_Bytes.hexStringToBytes(this.config.getBusinessVersion());
                bArr = FM_Bytes.join(FM_Bytes.join(bArr, new byte[]{5, (byte) hexStringToBytes2.length}), hexStringToBytes2);
            }
        } else {
            if (this.config.getSdkVersion() != null && this.config.getSdkVersion().length() > 0) {
                byte[] bytes = this.config.getSdkVersion().getBytes();
                bArr = FM_Bytes.join(new byte[]{4, (byte) bytes.length}, bytes);
            }
            if (this.config.getBusinessVersion() != null && this.config.getBusinessVersion().length() > 0) {
                byte[] hexStringToBytes3 = FM_Bytes.hexStringToBytes(this.config.getBusinessVersion());
                bArr = FM_Bytes.join(FM_Bytes.join(bArr, new byte[]{5, (byte) hexStringToBytes3.length}), hexStringToBytes3);
            }
        }
        terminalInfo.setAppend(bArr);
        if (this.terminalNumber != null) {
            terminalInfo.setTerminalNumber(this.terminalNumber);
        } else {
            terminalInfo.setTerminalNumber(new byte[32]);
        }
        try {
            return terminalCommunication.openSession(terminalInfo, false);
        } catch (OpenSessionException e) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (e.getExceptionType() != null) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str2 = this.logTag;
                    fMLog.warn(str2, "终端向平台签到请求处理失败， " + e.getExceptionType().getDescription());
                }
            } else if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, "终端向平台签到请求处理失败， " + Util4Java.getExceptionInfo(e));
            }
            throw new BusinessException("终端向平台签到请求处理失败", BusinessException.ErrorMessage.local_communication_sign_in_fail);
        } catch (InvalidParameterException e2) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, "终端向平台签参数无效： " + Util4Java.getExceptionInfo(e2));
            }
            throw new BusinessException("终端向平台签参数无效", BusinessException.ErrorMessage.local_communication_sign_in_fail);
        } catch (SocketException e3) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, "终端向平台签到时，网络异常: " + Util4Java.getExceptionInfo(e3));
            }
            throw new BusinessException("终端向平台签到时，网络异常", BusinessException.ErrorMessage.local_communication_connect_fail);
        } catch (CommunicationException e4) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str6 = this.logTag;
                fMLog5.warn(str6, "终端向平台签到时，通信数据异常： " + Util4Java.getExceptionInfo(e4));
            }
            throw new BusinessException("终端向平台签到时，通信数据异常", BusinessException.ErrorMessage.local_communication_sign_in_fail);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean closeSessionHandle(String str) throws BusinessException {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication == null) {
            return false;
        }
        try {
            return terminalCommunication.closeSession((CloseSessionRequest) null);
        } catch (CloseSessionException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.error(str2, "终端向平台签退请求处理失败 " + Util4Java.getExceptionInfo(e));
            }
            throw new BusinessException("终端向平台签退请求处理失败", BusinessException.ErrorMessage.local_communication_sign_out_fail);
        } catch (InvalidParameterException e2) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.error(str3, "终端向平台签退请求时，传入的参数无效 " + Util4Java.getExceptionInfo(e2));
            }
            throw new BusinessException("终端向平台签退请求时，传入的参数无效", BusinessException.ErrorMessage.local_communication_sign_out_fail);
        } catch (SocketException e3) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.error(str4, "终端向平台签退请求处理时，网络出现异常 " + Util4Java.getExceptionInfo(e3));
            }
            throw new BusinessException("终端向平台签退请求时，网络出现异常", BusinessException.ErrorMessage.local_communication_sign_out_fail);
        } catch (CommunicationException e4) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.error(str5, "终端向平台签退请求处理时，通信数据处理异常 " + Util4Java.getExceptionInfo(e4));
            }
            throw new BusinessException("终端向平台签退请求处理时，通信数据处理异常", BusinessException.ErrorMessage.local_communication_sign_out_fail);
        }
    }

    private boolean connectHandle(String str) {
        TerminalCommunication terminalCommunication;
        try {
            terminalCommunication = connect(str);
        } catch (BusinessException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, "业务处理时，链接异常，重新链接失败:" + Util4Java.getExceptionInfo(e));
            }
            terminalCommunication = null;
        }
        return terminalCommunication != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018 A[LOOP:0: B:7:0x0018->B:10:0x0025, LOOP_START, PHI: r0 
      PHI: (r0v6 boolean) = (r0v0 boolean), (r0v7 boolean) binds: [B:6:0x0016, B:10:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] socketExceptionHandle(boolean r6, java.lang.String r7) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r5 = this;
            boolean r0 = r5.connectHandle(r7)
            if (r0 != 0) goto L_0x003f
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            if (r1 == 0) goto L_0x0014
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            java.lang.String r2 = r5.logTag
            java.lang.String r3 = "业务处理时，链接异常，重新链接失败"
            r1.warn(r2, r3)
        L_0x0014:
            cn.com.fmsh.tsm.business.SocketExceptionHandler r1 = r5.socketExceptionHandle
            if (r1 == 0) goto L_0x0027
        L_0x0018:
            cn.com.fmsh.tsm.business.SocketExceptionHandler r1 = r5.socketExceptionHandle
            boolean r1 = r1.isReconnect4tException()
            if (r1 != 0) goto L_0x0021
            goto L_0x0027
        L_0x0021:
            boolean r0 = r5.connectHandle(r7)
            if (r0 == 0) goto L_0x0018
        L_0x0027:
            if (r0 != 0) goto L_0x003f
            cn.com.fmsh.util.log.FMLog r0 = r5.fmLog
            if (r0 == 0) goto L_0x0037
            cn.com.fmsh.util.log.FMLog r0 = r5.fmLog
            java.lang.String r1 = r5.logTag
            java.lang.String r2 = "业务处理时，链接异常，重新链接失败"
            r0.debug(r1, r2)
        L_0x0037:
            java.lang.String r0 = "业务处理时，链接异常，重新链接失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_connect_fail
            r5.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r6)
        L_0x003f:
            r0 = 0
            cn.com.fmsh.communication.TerminalCommunicationList r1 = r5.terminalCommunicationList     // Catch:{ SocketException -> 0x009e, CommunicationException -> 0x004c }
            cn.com.fmsh.communication.TerminalCommunication r7 = r1.getTerminalCommunication(r7)     // Catch:{ SocketException -> 0x009e, CommunicationException -> 0x004c }
            byte[] r7 = r7.repeat()     // Catch:{ SocketException -> 0x009e, CommunicationException -> 0x004c }
            goto L_0x00c6
        L_0x004c:
            r7 = move-exception
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            if (r1 == 0) goto L_0x0078
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            java.lang.String r2 = r5.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "业务处理时，链接异常，业务处理再次失败："
            r3.<init>(r4)
            cn.com.fmsh.communication.exception.CommunicationException$CommunicationExceptionType r4 = r7.getExceptionType()
            if (r4 != 0) goto L_0x0066
            java.lang.String r4 = ""
            goto L_0x006e
        L_0x0066:
            cn.com.fmsh.communication.exception.CommunicationException$CommunicationExceptionType r4 = r7.getExceptionType()
            java.lang.String r4 = r4.getDescription()
        L_0x006e:
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.warn(r2, r3)
        L_0x0078:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "业务处理时，链接异常，业务处理再次失败："
            r1.<init>(r2)
            cn.com.fmsh.communication.exception.CommunicationException$CommunicationExceptionType r2 = r7.getExceptionType()
            if (r2 != 0) goto L_0x0089
            java.lang.String r7 = ""
            goto L_0x0091
        L_0x0089:
            cn.com.fmsh.communication.exception.CommunicationException$CommunicationExceptionType r7 = r7.getExceptionType()
            java.lang.String r7 = r7.getDescription()
        L_0x0091:
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_connect_fail
            r5.throwExceptionAndClose((java.lang.String) r7, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r6)
            goto L_0x00c5
        L_0x009e:
            r7 = move-exception
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            if (r1 == 0) goto L_0x00bd
            cn.com.fmsh.util.log.FMLog r1 = r5.fmLog
            java.lang.String r2 = r5.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "业务处理时，链接异常，业务处理再次失败："
            r3.<init>(r4)
            java.lang.String r7 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r7)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r1.warn(r2, r7)
        L_0x00bd:
            java.lang.String r7 = "业务处理时，链接异常，业务处理再次失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_connect_fail
            r5.throwExceptionAndClose((java.lang.String) r7, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r6)
        L_0x00c5:
            r7 = r0
        L_0x00c6:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardBusinessBasic.socketExceptionHandle(boolean, java.lang.String):byte[]");
    }

    /* access modifiers changed from: package-private */
    public byte[] communicationExceptionHandle(CommunicationException communicationException, boolean z, String str) throws BusinessException {
        if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.NO_REPONSE) {
            throwExceptionAndClose("业务处理时，无效会话", BusinessException.ErrorMessage.local_communication_no_response, z);
            return null;
        } else if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.INVALID_REPONSE) {
            throwExceptionAndClose("业务处理时，无效会话", BusinessException.ErrorMessage.local_communication_no_response, z);
            return null;
        } else if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.INVALID_SESSION) {
            throwExceptionAndClose("业务处理时，无效会话", BusinessException.ErrorMessage.local_communication_invalid_session, z);
            return null;
        } else if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.INVALID_SESSION_NUMBER) {
            throwExceptionAndClose("业务处理时，无效会话流水", BusinessException.ErrorMessage.local_communication_invalid_session_serial, z);
            return null;
        } else if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.CHECK_FAILED) {
            throwExceptionAndClose("业务处理时，无效报文格式", BusinessException.ErrorMessage.local_communication_invalid_format, z);
            return null;
        } else if (communicationException.getExceptionType() == CommunicationException.CommunicationExceptionType.INVALID_FORMAT) {
            throwExceptionAndClose("业务处理时，无效会话", BusinessException.ErrorMessage.local_communication_invalid_verify, z);
            return null;
        } else {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, "业务处理时，链接异常，重新处理时,异常信息：" + communicationException.getExceptionType().getDescription());
            }
            try {
                closeSessionHandle(str);
                disconnect(str);
            } catch (BusinessException unused) {
                throwExceptionAndClose("业务处理时，链接异常，业务处理再次失败", BusinessException.ErrorMessage.local_communication_disconnect_fail, z);
            }
            throwExceptionAndClose("业务处理时，链接异常，重新处理时,异常信息", BusinessException.ErrorMessage.local_communication_connect_fail, z);
            return null;
        }
    }

    private byte[] repeat(boolean z, String str) throws BusinessException {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str);
        if (terminalCommunication != null) {
            try {
                byte[] repeat = terminalCommunication.repeat();
                try {
                    closeSessionHandle(str);
                    disconnect(str);
                    return repeat;
                } catch (BusinessException unused) {
                    throw new BusinessException("业务处理时，链接异常，业务处理再次失败", BusinessException.ErrorMessage.local_communication_disconnect_fail);
                }
            } catch (SocketException unused2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "业务处理时，链接异常，重新处理时，链接异常");
                }
                byte[] socketExceptionHandle2 = socketExceptionHandle(z, str);
                try {
                    closeSessionHandle(str);
                    disconnect(str);
                    return socketExceptionHandle2;
                } catch (BusinessException unused3) {
                    throw new BusinessException("业务处理时，链接异常，业务处理再次失败", BusinessException.ErrorMessage.local_communication_disconnect_fail);
                }
            } catch (CommunicationException e) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str2 = this.logTag;
                    fMLog.warn(str2, "业务处理时，链接异常，重新处理时,异常信息：" + e.getExceptionType().getDescription());
                }
                throw handleCommunicationException(e);
            } catch (Throwable th) {
                try {
                    closeSessionHandle(str);
                    disconnect(str);
                    throw th;
                } catch (BusinessException unused4) {
                    throw new BusinessException("业务处理时，链接异常，业务处理再次失败", BusinessException.ErrorMessage.local_communication_disconnect_fail);
                }
            }
        } else if (this.fmLog == null) {
            return null;
        } else {
            FMLog fMLog2 = this.fmLog;
            String str3 = this.logTag;
            fMLog2.warn(str3, "业务处理时，链接异常，重新处理时，获取平台通信对象[" + str + "]失败");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] interaction(byte[] r4, java.lang.String r5, boolean r6, java.lang.String r7) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r3 = this;
            cn.com.fmsh.communication.TerminalCommunicationList r0 = r3.terminalCommunicationList
            cn.com.fmsh.communication.TerminalCommunication r0 = r0.getTerminalCommunication(r7)
            r1 = 0
            if (r0 != 0) goto L_0x0029
            cn.com.fmsh.util.log.FMLog r4 = r3.fmLog
            if (r4 == 0) goto L_0x0028
            cn.com.fmsh.util.log.FMLog r4 = r3.fmLog
            java.lang.String r5 = r3.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "业务处理时,获取平台通信对象["
            r6.<init>(r0)
            r6.append(r7)
            java.lang.String r7 = "]失败"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.warn(r5, r6)
        L_0x0028:
            return r1
        L_0x0029:
            byte[] r4 = r0.sendMessage(r4)     // Catch:{ InvalidParameterException -> 0x0099, SocketException -> 0x005d, CommunicationException -> 0x002f }
            goto L_0x00d5
        L_0x002f:
            r4 = move-exception
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            if (r7 == 0) goto L_0x0054
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            java.lang.String r0 = r3.logTag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r2.<init>(r5)
            java.lang.String r5 = " 终端向平台请求时，通信数据异常: "
            r2.append(r5)
            java.lang.String r4 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r4)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            r7.error(r0, r4)
        L_0x0054:
            java.lang.String r4 = "终端向平台请求时，Socket通信异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_connect_fail
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
            goto L_0x00d4
        L_0x005d:
            r4 = move-exception
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            if (r7 != 0) goto L_0x006c
            cn.com.fmsh.util.log.LogFactory r7 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r7 = r7.getLog()
            r3.fmLog = r7
        L_0x006c:
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            if (r7 == 0) goto L_0x0090
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            java.lang.String r0 = r3.logTag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r2.<init>(r5)
            java.lang.String r5 = " 终端向平台请求时，Socket通信异常: "
            r2.append(r5)
            java.lang.String r4 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r4)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            r7.error(r0, r4)
        L_0x0090:
            java.lang.String r4 = "终端向平台请求时，Socket通信异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_connect_fail
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
            goto L_0x00d4
        L_0x0099:
            r4 = move-exception
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            if (r7 != 0) goto L_0x00a8
            cn.com.fmsh.util.log.LogFactory r7 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r7 = r7.getLog()
            r3.fmLog = r7
        L_0x00a8:
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            if (r7 == 0) goto L_0x00cc
            cn.com.fmsh.util.log.FMLog r7 = r3.fmLog
            java.lang.String r0 = r3.logTag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r2.<init>(r5)
            java.lang.String r5 = ",终端向平台请求时，参数异常: "
            r2.append(r5)
            java.lang.String r4 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r4)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            r7.error(r0, r4)
        L_0x00cc:
            java.lang.String r4 = "终端向平台请求时，参数异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_request_param_error
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
        L_0x00d4:
            r4 = r1
        L_0x00d5:
            if (r4 == 0) goto L_0x00db
            int r5 = r4.length
            r7 = 2
            if (r5 >= r7) goto L_0x00ff
        L_0x00db:
            cn.com.fmsh.util.log.FMLog r5 = r3.fmLog
            if (r5 != 0) goto L_0x00e9
            cn.com.fmsh.util.log.LogFactory r5 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r5 = r5.getLog()
            r3.fmLog = r5
        L_0x00e9:
            cn.com.fmsh.util.log.FMLog r5 = r3.fmLog
            if (r5 == 0) goto L_0x00f7
            cn.com.fmsh.util.log.FMLog r5 = r3.fmLog
            java.lang.String r7 = r3.logTag
            java.lang.String r0 = "平台处理业务请求失败,未收到平台响应数据"
            r5.warn(r7, r0)
        L_0x00f7:
            java.lang.String r5 = "终端向平台请求时，未收到平台响应数据"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_no_response
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r6)
        L_0x00ff:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardBusinessBasic.interaction(byte[], java.lang.String, boolean, java.lang.String):byte[]");
    }

    private BusinessException handleCommunicationException(CommunicationException communicationException) {
        BusinessException.ErrorMessage errorMessage;
        CommunicationException.CommunicationExceptionType exceptionType = communicationException.getExceptionType();
        if (CommunicationException.CommunicationExceptionType.INVALID_VERSION == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_version;
        } else if (CommunicationException.CommunicationExceptionType.INVALID_FORMAT == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_format;
        } else if (CommunicationException.CommunicationExceptionType.CHECK_FAILED == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_verify;
        } else if (CommunicationException.CommunicationExceptionType.INVALID_CONTROL == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_control;
        } else if (CommunicationException.CommunicationExceptionType.INVALID_SESSION == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_session;
        } else if (CommunicationException.CommunicationExceptionType.INVALID_SESSION_NUMBER == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_session_serial;
        } else if (CommunicationException.CommunicationExceptionType.INVALID_DIRECTION == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_direction;
        } else if (CommunicationException.CommunicationExceptionType.NO_REPONSE == exceptionType) {
            errorMessage = BusinessException.ErrorMessage.local_communication_invalid_response;
        } else {
            errorMessage = CommunicationException.CommunicationExceptionType.INVALID_REPONSE == exceptionType ? BusinessException.ErrorMessage.local_communication_invalid_response : null;
        }
        return new BusinessException(exceptionType.getDescription(), errorMessage);
    }

    public InputStream decryptFile(String str) throws BusinessException {
        InputStream resourceAsStream = getClass().getResourceAsStream(str);
        if (resourceAsStream == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, "终端配置文件加载时，读取[" + str + "]失败");
            }
            throw new BusinessException("终端配置文件加载时，配置文件内容无效", BusinessException.ErrorMessage.local_app_config_invaild_content);
        }
        byte[] bArr = new byte[128];
        byte[] bArr2 = new byte[512];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr3 = new byte[1];
        try {
            resourceAsStream.read(bArr3);
            if (resourceAsStream.read(bArr) < 128) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "终端配置文件加载时，配置文件内容无效，未读到RAS加密的密文");
                }
                throw new BusinessException("终端配置文件加载时，配置文件内容无效", BusinessException.ErrorMessage.local_app_config_invaild_content);
            }
            while (true) {
                int read = resourceAsStream.read(bArr2);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(Arrays.copyOf(bArr2, read));
            }
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ConfigKey configKey = this.configKeyManager.getConfigKey(bArr3[0]);
            if (configKey == null) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "终端配置文件加载时，在配置文件密钥集合中未找到配置文件使用的密钥");
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            }
            byte[] decrtyByPrivate = RSA.decrtyByPrivate(configKey.getPublicKey(), configKey.getPrivateKey(), bArr, false);
            if (decrtyByPrivate == null) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "配置文件数据无效,RSA解密失败");
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            } else if (decrtyByPrivate.length < 36) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "配置文件数据无效,RSA解密后的数据长度无效");
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            } else {
                Arrays.copyOf(decrtyByPrivate, 20);
                return new ByteArrayInputStream(FM_Bytes.byteRemovePatch4Des(DES.decrypt4des3(Arrays.copyOfRange(decrtyByPrivate, 20, 36), byteArrayOutputStream.toByteArray())));
            }
        } catch (FileNotFoundException unused) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "终端配置文件加载时，未找到配置文件");
            }
            throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_load_config_fail);
        } catch (IOException e2) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, "读取配置文件出现异常：" + Util4Java.getExceptionInfo(e2));
            }
            throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_load_config_fail);
        } catch (Throwable th) {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public boolean loadDefine(InputStream inputStream) throws BusinessException {
        String str;
        this.config = new Configration();
        if (inputStream != null) {
            try {
                Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement();
                NodeList elementsByTagName = documentElement.getElementsByTagName("Server");
                int i = 0;
                while (true) {
                    str = null;
                    int i2 = -1;
                    if (i >= elementsByTagName.getLength()) {
                        break;
                    }
                    NamedNodeMap attributes = elementsByTagName.item(i).getAttributes();
                    Node namedItem = attributes.getNamedItem("domain");
                    String nodeValue = namedItem != null ? namedItem.getNodeValue() : null;
                    Node namedItem2 = attributes.getNamedItem(Constants.XMLNode.SERVER_NAME);
                    if (namedItem2 != null) {
                        str = namedItem2.getNodeValue();
                    }
                    Node namedItem3 = attributes.getNamedItem("port");
                    if (namedItem3 != null) {
                        i2 = Util4Java.String2Int(namedItem3.getNodeValue(), -1);
                    }
                    Node namedItem4 = attributes.getNamedItem(Constants.XMLNode.SERVER_TOMEOUT);
                    this.config.addServers(nodeValue, i2, namedItem4 != null ? Util4Java.String2Int(namedItem4.getNodeValue(), 0) : 0, str);
                    i++;
                }
                NamedNodeMap attributes2 = documentElement.getElementsByTagName(Constants.XMLNode.TERMINAL).item(0).getAttributes();
                Node namedItem5 = attributes2.getNamedItem("type");
                if (namedItem5 != null) {
                    this.config.setTerminalType(FM_Bytes.hexStringToBytes(namedItem5.getNodeValue()));
                }
                Node namedItem6 = attributes2.getNamedItem(Constants.XMLNode.TERMINAL_BUSINESS_VERSION);
                if (namedItem6 != null) {
                    this.config.setBusinessVersion(namedItem6.getNodeValue());
                }
                Node namedItem7 = attributes2.getNamedItem("sdkVersion");
                if (namedItem7 != null) {
                    this.config.setSdkVersion(namedItem7.getNodeValue());
                }
                NodeList elementsByTagName2 = documentElement.getElementsByTagName(Constants.XMLNode.ORDER_SOURCE);
                if (elementsByTagName2 != null) {
                    Node item = elementsByTagName2.item(0);
                    if (item != null) {
                        Node namedItem8 = item.getAttributes().getNamedItem("value");
                        if (namedItem8 != null) {
                            this.config.setOrderSource(Util4Java.String2Byte(namedItem8.getNodeValue(), (byte) 0));
                        } else if (this.fmLog != null) {
                            this.fmLog.warn(this.logTag, "配置文件中订单来源不包含订单来源值的数据项");
                        }
                    } else if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "配置文件中订单来源不包含数据项");
                    }
                } else if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "配置文件未定义订单来源");
                }
                NodeList elementsByTagName3 = documentElement.getElementsByTagName(Constants.XMLNode.COMPANY_CODE);
                if (elementsByTagName3 != null) {
                    Node item2 = elementsByTagName3.item(0);
                    if (item2 != null) {
                        Node namedItem9 = item2.getAttributes().getNamedItem("value");
                        if (namedItem9 != null) {
                            this.config.setCompanyCode(namedItem9.getNodeValue());
                        } else if (this.fmLog != null) {
                            this.fmLog.warn(this.logTag, "配置文件不包含厂商编码信息");
                        }
                    } else if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "配置文件厂商编码信息不包含数据项");
                    }
                } else if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "配置文件未定义厂商编码");
                }
                NodeList elementsByTagName4 = documentElement.getElementsByTagName(Constants.XMLNode.KEY);
                for (int i3 = 0; i3 < elementsByTagName4.getLength(); i3++) {
                    NamedNodeMap attributes3 = elementsByTagName4.item(i3).getAttributes();
                    String str2 = "";
                    Node namedItem10 = attributes3.getNamedItem("index");
                    int parseInt = namedItem10 != null ? Integer.parseInt(namedItem10.getNodeValue()) : -1;
                    Node namedItem11 = attributes3.getNamedItem(Constants.XMLNode.KEY_EXPONENT);
                    byte[] hexStringToBytes = namedItem11 != null ? FM_Bytes.hexStringToBytes(namedItem11.getNodeValue()) : null;
                    Node namedItem12 = attributes3.getNamedItem(Constants.XMLNode.KEY_MODULUS);
                    byte[] hexStringToBytes2 = namedItem12 != null ? FM_Bytes.hexStringToBytes(namedItem12.getNodeValue()) : null;
                    Node namedItem13 = attributes3.getNamedItem(Constants.XMLNode.SERVER_NAME);
                    if (namedItem13 != null) {
                        str2 = namedItem13.getNodeValue();
                    }
                    if (!(str2 == null && (hexStringToBytes == null || hexStringToBytes2 == null || parseInt == -1))) {
                        this.config.addKey(str2, parseInt, hexStringToBytes, hexStringToBytes2);
                    }
                }
                NodeList elementsByTagName5 = documentElement.getElementsByTagName(Constants.XMLNode.AID);
                for (int i4 = 0; i4 < elementsByTagName5.getLength(); i4++) {
                    Node namedItem14 = elementsByTagName5.item(i4).getAttributes().getNamedItem("value");
                    if (namedItem14 != null) {
                        this.config.addAid(FM_Bytes.hexStringToBytes(namedItem14.getNodeValue()));
                    }
                }
                NodeList elementsByTagName6 = documentElement.getElementsByTagName(Constants.XMLNode.BUSINESS_AND_SERVER);
                int i5 = -1;
                for (int i6 = 0; i6 < elementsByTagName6.getLength(); i6++) {
                    NamedNodeMap attributes4 = elementsByTagName6.item(i6).getAttributes();
                    Node namedItem15 = attributes4.getNamedItem(Constants.XMLNode.SERVER_NAME);
                    if (namedItem15 != null) {
                        str = namedItem15.getNodeValue();
                    }
                    Node namedItem16 = attributes4.getNamedItem(Constants.XMLNode.BUSINESS_NAME);
                    if (namedItem16 != null) {
                        i5 = Util4Java.String2Int(namedItem16.getNodeValue(), -1);
                    }
                    if (i5 != -1 || str != null) {
                        this.config.addBusinessAndServer(i5, str);
                    }
                }
                if (this.fmLog == null) {
                    return true;
                }
                this.fmLog.info(this.logTag, "load config sucess");
                return true;
            } catch (ParserConfigurationException e) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e));
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            } catch (IOException e2) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e2));
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            } catch (SAXException e3) {
                if (this.fmLog == null) {
                    this.fmLog = LogFactory.getInstance().getLog();
                }
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e3));
                }
                throw new BusinessException("终端配置文件加载时，未找到配置文件", BusinessException.ErrorMessage.local_app_config_invaild_content);
            }
        } else {
            throw new BusinessException("终端配置文件加载失败");
        }
    }

    public void businessReady(String str, String str2) throws BusinessException {
        TerminalCommunication terminalCommunication = this.terminalCommunicationList.getTerminalCommunication(str2);
        if (terminalCommunication == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str3 = this.logTag;
                fMLog.warn(str3, String.valueOf(str) + " 终端连接到平台失败");
            }
            throw new BusinessException(String.valueOf(str) + " 终端连接到平台失败", BusinessException.ErrorMessage.local_communication_connect_fail);
        }
        if (!terminalCommunication.isConnect()) {
            terminalCommunication = connect(str2);
        }
        if (!terminalCommunication.isConnect()) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str4 = this.logTag;
                fMLog2.warn(str4, String.valueOf(str) + " 终端连接到平台失败");
            }
            throw new BusinessException(String.valueOf(str) + " 终端连接到平台失败", BusinessException.ErrorMessage.local_communication_connect_fail);
        } else if (!isOpenSession(terminalCommunication) && !openSession(str2)) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str5 = this.logTag;
                fMLog3.warn(str5, String.valueOf(str) + " 终端未签到");
            }
            throw new BusinessException(String.valueOf(str) + " 终端签到失败", BusinessException.ErrorMessage.local_communication_sign_in_fail);
        }
    }

    public void businessFinish(boolean z) throws BusinessException {
        if (z && this.apduHandler != null) {
            this.apduHandler.close();
        }
    }

    public void throwExceptionAndClose(String str, BusinessException.ErrorMessage errorMessage, boolean z) throws BusinessException {
        if (z && this.apduHandler != null) {
            this.apduHandler.close();
        }
        throw new BusinessException(str, errorMessage);
    }

    public void throwExceptionAndClose(BusinessException businessException, boolean z, String str) throws BusinessException {
        if (z && this.apduHandler != null) {
            this.apduHandler.close();
        }
        disconnect(str);
    }

    public void registerLocalDataHandler(LocalDataHandler localDataHandler2) {
        this.localDataHandler = localDataHandler2;
    }

    public LocalDataHandler getLocalDataHandler() {
        return this.localDataHandler;
    }

    public String getServer4Business(int i) {
        return getConfigration().getServer4Business(i);
    }
}
