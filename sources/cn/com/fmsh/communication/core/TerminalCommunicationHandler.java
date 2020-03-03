package cn.com.fmsh.communication.core;

import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.core.ControlWord;
import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.exception.InvalidParameterException;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.algorithm.DES;
import cn.com.fmsh.util.algorithm.MAC;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import cn.com.fmsh.util.socket.DataLengthHandle;
import cn.com.fmsh.util.socket.ReceiveHandler;
import com.taobao.weex.el.parse.Operators;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TerminalCommunicationHandler implements TerminalCommunication {
    private volatile LinkedList<byte[]> asynchronousMessages;
    private int checkKeyEnd;
    private int checkKeyStart;
    private CommunicationNotify communicationNotify;
    private DataLengthHandle dataLengthHandle;
    private int exceptionTryCount;
    private FMLog fmLog;
    private ReceiveHandler handler;

    /* renamed from: in  reason: collision with root package name */
    private DataInputStream f548in;
    private int interval4Heartbeat;
    private volatile boolean isConnect;
    private volatile boolean isHandle;
    private boolean isHeartbeat;
    private volatile boolean isSendBusinessData;
    private volatile boolean isStop;
    private volatile long lastCalledTime4Connect;
    private volatile long lastCalledTime4Session;
    byte[] lastRequestData;
    private Lock lock;
    private String logTag;
    private volatile long nextSendSerialNumber;
    private volatile boolean openSessionFlag;
    private DataOutputStream out;
    private volatile long serialNumber;
    private byte[] sessionKey;
    private byte[] sessionNumber;
    private Socket socket;
    private final int socketTimeoutDefault;
    private byte[] tempKey;
    private byte[] terminalRamdom;
    private int timeout;

    public Date getLastHeartBeat() {
        return null;
    }

    public TerminalCommunicationHandler() {
        this.exceptionTryCount = 3;
        this.interval4Heartbeat = 1000;
        this.socketTimeoutDefault = 5000;
        this.socket = null;
        this.checkKeyStart = 4;
        this.checkKeyEnd = 12;
        this.isSendBusinessData = false;
        this.isConnect = false;
        this.openSessionFlag = false;
        this.timeout = 5000;
        this.fmLog = null;
        this.logTag = TerminalCommunicationHandler.class.getName();
        this.lastCalledTime4Connect = 0;
        this.lastCalledTime4Session = 0;
        this.dataLengthHandle = new NFCosDataLengthHandler();
        this.fmLog = LogFactory.getInstance().getLog();
        this.sessionKey = new byte[16];
        this.sessionNumber = new byte[4];
        this.asynchronousMessages = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    public void setTimeout(int i) {
        if (i > 0) {
            this.timeout = i;
        }
    }

    public long getLastCalledTime() {
        return this.lastCalledTime4Session;
    }

    public void setExceptionTryCount(int i) {
        if (i > 0) {
            this.exceptionTryCount = i;
        }
    }

    public void setInterval4Heartbeat(int i) {
        if (i > 0) {
            this.interval4Heartbeat = i;
        }
    }

    public boolean connect(LinkInfo linkInfo) throws InvalidParameterException, SocketException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (isConnect()) {
            return true;
        }
        if (linkInfo == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "链接到平台时，传入的链接数据为空");
            }
            throw new InvalidParameterException("链接到平台时，传入的链接数据为空");
        } else if (linkInfo.getPort() < 1 || linkInfo.getPort() >= 65535) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "链接到平台时，传入的端口号不合法");
            }
            throw new InvalidParameterException("链接到平台时，传入的端口号不合法");
        } else if (linkInfo.getAddress() == null || linkInfo.getAddress().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "链接到平台时，传入的平台地址为空");
            }
            throw new InvalidParameterException("链接到平台时，传入的平台地址为空");
        } else {
            if (linkInfo.getTimeout() != -1) {
                this.timeout = linkInfo.getTimeout();
            }
            this.lock.lock();
            try {
                this.socket = new Socket(linkInfo.getAddress(), linkInfo.getPort());
                if (this.socket.getSoTimeout() == 0) {
                    this.socket.setSoTimeout(this.timeout);
                }
                this.f548in = new DataInputStream(this.socket.getInputStream());
                this.out = new DataOutputStream(this.socket.getOutputStream());
                this.lock.unlock();
                this.isConnect = true;
                this.lastCalledTime4Connect = System.currentTimeMillis();
                return true;
            } catch (IOException e) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.debug(str, "Platform " + linkInfo.getAddress() + ":" + linkInfo.getPort());
                    this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e));
                }
                throw new SocketException(Util4Java.getExceptionInfo(e));
            } catch (Exception e2) {
                if (this.fmLog != null) {
                    FMLog fMLog2 = this.fmLog;
                    String str2 = this.logTag;
                    fMLog2.debug(str2, "Platform " + linkInfo.getAddress() + ":" + linkInfo.getPort());
                    this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e2));
                }
                throw new SocketException(Util4Java.getExceptionInfo(e2));
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
    }

    public boolean disconnect() throws SocketException {
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "disconnect....");
        }
        this.isConnect = false;
        try {
            if (this.out != null) {
                this.out.close();
            }
        } catch (IOException e) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e));
            }
        }
        this.out = null;
        try {
            if (this.f548in != null) {
                this.f548in.close();
            }
        } catch (IOException e2) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e2));
            }
        }
        this.f548in = null;
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e3) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, Util4Java.getExceptionInfo(e3));
            }
        }
        this.socket = null;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isHandle() {
        return this.isHandle;
    }

    /* access modifiers changed from: package-private */
    public void setHandle(boolean z) {
        this.isHandle = z;
    }

    public void registerCommunicationNotify(CommunicationNotify communicationNotify2) {
        this.communicationNotify = communicationNotify2;
    }

    public boolean openSession(TerminalInfo terminalInfo, boolean z) throws InvalidParameterException, SocketException, CommunicationException, OpenSessionException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.openSessionFlag) {
            return true;
        }
        if (terminalInfo != null) {
            this.isHeartbeat = z;
            ControlWord controlWord = new ControlWord();
            controlWord.setDirection(ControlWord.Direction.REQUEST);
            controlWord.setType(ControlWord.MessageType.CONTROL);
            controlWord.setCommandType(ControlWord.CommandType.OPENSESSION);
            MessageHead messageHead = new MessageHead();
            messageHead.setProtocolVersion((byte) 17);
            messageHead.setSecurityLevel(new byte[]{(byte) MessageHead.SecurityLevel.PLAIN.getValue(), (byte) MessageHead.CheckType.CRC16.getValue()});
            messageHead.setControlWord(controlWord);
            byte[] bytes = messageHead.toBytes();
            OpenSessionRequest openSessionRequest = new OpenSessionRequest();
            openSessionRequest.setTerminalType(terminalInfo.getTerminalType());
            openSessionRequest.setAppend(terminalInfo.getAppend());
            openSessionRequest.setKeyIndex(terminalInfo.getKeyIndex());
            openSessionRequest.setExponent(terminalInfo.getExponent());
            openSessionRequest.setModulus(terminalInfo.getModulus());
            openSessionRequest.setSecurityCode(terminalInfo.getSecurityCode());
            openSessionRequest.setTerminalNumber(terminalInfo.getTerminalNumber());
            Random random = new Random();
            this.terminalRamdom = new byte[8];
            random.nextBytes(this.terminalRamdom);
            openSessionRequest.setTerminalRandom(this.terminalRamdom);
            this.tempKey = new byte[16];
            random.nextBytes(this.tempKey);
            openSessionRequest.setTempKey(this.tempKey);
            byte[] bytes2 = openSessionRequest.toBytes();
            if (bytes2 != null) {
                byte[] join = FM_Bytes.join(bytes, bytes2);
                byte[] join2 = FM_Bytes.join(join, CRCUtil.calculateCRC16(join));
                try {
                    byte[] send = send(this.dataLengthHandle.initDataSize(join2.length), join2);
                    if (send == null) {
                        if (this.fmLog != null) {
                            this.fmLog.error(this.logTag, "终端签到请求时，平台响应数据为空，签到失败");
                        }
                        throw new CommunicationException("终端签到请求时，平台响应数据为空，签到失败");
                    } else if (send.length >= 12) {
                        messageHead.fromBytes(Arrays.copyOf(send, 12));
                        ControlWord controlWord2 = messageHead.getControlWord();
                        if (controlWord2.getDirection() == ControlWord.Direction.RESPONSE) {
                            byte[] securityLevel = messageHead.getSecurityLevel();
                            if (securityLevel[0] != MessageHead.SecurityLevel.PLAIN.getValue() || securityLevel[1] != MessageHead.CheckType.CRC16.getValue()) {
                                if (this.fmLog != null) {
                                    this.fmLog.warn(this.logTag, "终端签到请求时，收到响应报文的安全级别无效");
                                }
                                throw new OpenSessionException("终端签到请求时，收到响应报文的安全级别无效");
                            } else if (!Arrays.equals(CRCUtil.calculateCRC16(Arrays.copyOf(send, send.length - 2)), Arrays.copyOfRange(send, send.length - 2, send.length))) {
                                if (this.fmLog != null) {
                                    this.fmLog.warn(this.logTag, "终端签到请求时，签到响应数据CRC验证失败");
                                }
                                throw new OpenSessionException("终端签到请求时，签到响应数据CRC验证失败");
                            } else {
                                byte[] copyOfRange = Arrays.copyOfRange(send, 12, send.length - 2);
                                if (controlWord2.getReponseCode() != 0 && controlWord2.getReponseCode() != 14) {
                                    CommunicationException.CommunicationExceptionType instance = CommunicationException.CommunicationExceptionType.instance(controlWord2.getReponseCode());
                                    CommunicationException communicationException = new CommunicationException("签到失败:" + instance.getDescription());
                                    communicationException.setExceptionType(instance);
                                    communicationException.setDirection(CommunicationException.CommandDirection.RESPONSE);
                                    throw communicationException;
                                } else if (copyOfRange.length < 1) {
                                    throw new CommunicationException("平台业务处理，响应数据包体为空，业务处理失败");
                                } else if (controlWord2.getReponseCode() != 14) {
                                    byte[] decrypt4des3 = DES.decrypt4des3(this.tempKey, copyOfRange);
                                    if (!FM_Bytes.isPatch4Des(decrypt4des3)) {
                                        if (this.fmLog != null) {
                                            this.fmLog.warn(this.logTag, "平台响应数据3Des解密后，未补位");
                                        }
                                        throw new OpenSessionException("平台响应数据3Des解密后，未补位");
                                    }
                                    byte[] byteRemovePatch4Des = FM_Bytes.byteRemovePatch4Des(decrypt4des3);
                                    OpenSessionResponse openSessionResponse = new OpenSessionResponse();
                                    openSessionResponse.fromBytes(byteRemovePatch4Des);
                                    if (Arrays.equals(openSessionResponse.getTerminalRandom(), this.terminalRamdom)) {
                                        this.sessionKey = openSessionResponse.getSessionKey();
                                        if (this.sessionKey != null) {
                                            int length = this.sessionKey.length;
                                            openSessionResponse.getClass();
                                            if (length == 16) {
                                                this.serialNumber = FM_Bytes.bytesToLong(FM_Bytes.join(new byte[1], openSessionResponse.getSerialNumber())) - 1;
                                                this.sessionNumber = openSessionResponse.getSessionNumber();
                                                this.openSessionFlag = true;
                                                this.lastCalledTime4Session = System.currentTimeMillis();
                                                this.lastCalledTime4Connect = System.currentTimeMillis();
                                                this.lastRequestData = null;
                                                return true;
                                            }
                                        }
                                        throw new OpenSessionException("签到时，平台响应的会话密钥无效");
                                    }
                                    throw new OpenSessionException("签到时，平台响应的随机数不是终端发出的随机数");
                                } else {
                                    OpenSessionException.OpenSessionExceptionType instance2 = OpenSessionException.OpenSessionExceptionType.instance(copyOfRange[0]);
                                    OpenSessionException openSessionException = new OpenSessionException(instance2.getDescription());
                                    openSessionException.setExceptionType(instance2);
                                    throw openSessionException;
                                }
                            }
                        } else {
                            CommunicationException communicationException2 = new CommunicationException("终端签到请求时，收到的平台数据不是响应数据，签到失败");
                            communicationException2.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_DIRECTION);
                            communicationException2.setDirection(CommunicationException.CommandDirection.RESPONSE);
                            throw communicationException2;
                        }
                    } else {
                        CommunicationException communicationException3 = new CommunicationException("终端签到请求时，收到平台响应数据长度不合法，签到失败");
                        communicationException3.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_FORMAT);
                        communicationException3.setDirection(CommunicationException.CommandDirection.RESPONSE);
                        throw communicationException3;
                    }
                } catch (IOException e) {
                    this.isConnect = false;
                    if (this.fmLog != null) {
                        FMLog fMLog = this.fmLog;
                        String str = this.logTag;
                        fMLog.error(str, "签到是，网络出现异常：" + Util4Java.getExceptionInfo(e));
                    }
                    throw new SocketException(Util4Java.getExceptionInfo(e));
                } catch (Exception e2) {
                    this.isConnect = false;
                    throw new SocketException(Util4Java.getExceptionInfo(e2));
                }
            } else {
                throw new OpenSessionException("终端签到时，请求数据body为空，签到失败");
            }
        } else {
            throw new InvalidParameterException("终端签到时，请求数据对象为空，签到失败");
        }
    }

    public boolean closeSession(CloseSessionRequest closeSessionRequest) throws InvalidParameterException, SocketException, CommunicationException, CloseSessionException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "=======closeSession....");
        }
        this.lastRequestData = null;
        this.openSessionFlag = false;
        ControlWord controlWord = new ControlWord();
        controlWord.setDirection(ControlWord.Direction.REQUEST);
        controlWord.setType(ControlWord.MessageType.CONTROL);
        controlWord.setCommandType(ControlWord.CommandType.CLOSESESSION);
        generateNextSerialNumber();
        MessageHead messageHead = new MessageHead();
        messageHead.setProtocolVersion((byte) 17);
        messageHead.setSessionNumber(this.sessionNumber);
        messageHead.setSerialNumber(this.nextSendSerialNumber);
        messageHead.setSecurityLevel(new byte[]{(byte) MessageHead.SecurityLevel.PLAIN.getValue(), (byte) MessageHead.CheckType.MAC.getValue()});
        messageHead.setControlWord(controlWord);
        if (closeSessionRequest == null) {
            closeSessionRequest = new CloseSessionRequest();
        }
        byte[] join = FM_Bytes.join(messageHead.toBytes(), closeSessionRequest.toBytes());
        byte[] join2 = FM_Bytes.join(join, Arrays.copyOf(MAC.calculateMAC4DES(Arrays.copyOfRange(this.sessionKey, this.checkKeyStart, this.checkKeyEnd), new byte[8], join), 4));
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "close session resuest:" + FM_Bytes.bytesToHexString(join2));
        }
        try {
            byte[] send = send(this.dataLengthHandle.initDataSize(join2.length), join2);
            if (send == null) {
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, "终端签退时，平台响应数据为空，签退失败");
                }
                throw new CommunicationException("终端签退时，平台响应数据为空，签退失败");
            }
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.debug(str2, "签退响应：" + FM_Bytes.bytesToHexString(send));
            }
            if (send.length < 12) {
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, "终端签退时，收到响应数据格式错误，签退失败");
                }
                CommunicationException communicationException = new CommunicationException("终端签退时，收到响应数据格式错误，签退失败");
                communicationException.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_FORMAT);
                communicationException.setDirection(CommunicationException.CommandDirection.RESPONSE);
                throw communicationException;
            }
            messageHead.fromBytes(Arrays.copyOf(send, 12));
            ControlWord controlWord2 = messageHead.getControlWord();
            if (controlWord2.getDirection() != ControlWord.Direction.RESPONSE) {
                CommunicationException communicationException2 = new CommunicationException("终端签退时，收到的平台数据不是响应数据，签退失败");
                communicationException2.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_DIRECTION);
                communicationException2.setDirection(CommunicationException.CommandDirection.RESPONSE);
                throw communicationException2;
            } else if (controlWord2.getReponseCode() != 0) {
                CommunicationException.CommunicationExceptionType instance = CommunicationException.CommunicationExceptionType.instance(controlWord2.getReponseCode());
                CommunicationException communicationException3 = new CommunicationException("签退失败:" + instance.getDescription());
                communicationException3.setExceptionType(instance);
                communicationException3.setDirection(CommunicationException.CommandDirection.RESPONSE);
                throw communicationException3;
            } else if (controlWord2.getCommandType() == ControlWord.CommandType.CLOSESESSION) {
                return true;
            } else {
                throw new CommunicationException("终端签退请求时，收到的平台数据不是签退应答");
            }
        } catch (IOException e) {
            this.isConnect = false;
            throw new SocketException(Util4Java.getExceptionInfo(e));
        } catch (Exception e2) {
            this.isConnect = false;
            throw new SocketException(Util4Java.getExceptionInfo(e2));
        }
    }

    public int sendMessageAsynchronous(byte[] bArr) throws InvalidParameterException {
        this.asynchronousMessages.addFirst(bArr);
        this.isSendBusinessData = true;
        return 0;
    }

    public int repeatAsynchronous() throws InvalidParameterException {
        this.isSendBusinessData = true;
        return 0;
    }

    public byte[] sendMessage(byte[] bArr) throws InvalidParameterException, SocketException, CommunicationException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "开始业务请求处理,message:" + FM_Bytes.bytesToHexString(bArr));
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务请求时，业务请求数据为空，业务处理失败");
            }
            throw new InvalidParameterException("业务请求时，业务请求数据为空，业务处理失败");
        }
        ControlWord controlWord = new ControlWord();
        controlWord.setDirection(ControlWord.Direction.REQUEST);
        controlWord.setType(ControlWord.MessageType.BUSINESS);
        MessageHead messageHead = new MessageHead();
        messageHead.setProtocolVersion((byte) 17);
        messageHead.setSecurityLevel(new byte[]{(byte) MessageHead.SecurityLevel.CIPHER.getValue(), (byte) MessageHead.CheckType.MAC.getValue()});
        messageHead.setControlWord(controlWord);
        generateNextSerialNumber();
        messageHead.setSerialNumber(this.nextSendSerialNumber);
        messageHead.setSessionNumber(this.sessionNumber);
        byte[] join = FM_Bytes.join(messageHead.toBytes(), DES.encrypt4des3(this.sessionKey, FM_Bytes.bytePatch4Des(bArr)));
        byte[] join2 = FM_Bytes.join(join, Arrays.copyOf(MAC.calculateMAC4DES(Arrays.copyOfRange(this.sessionKey, this.checkKeyStart, this.checkKeyEnd), new byte[8], join), 4));
        this.lastRequestData = join2;
        setHandle(true);
        try {
            byte[] send = send(this.dataLengthHandle.initDataSize(join2.length), join2);
            this.lastCalledTime4Session = System.currentTimeMillis();
            this.lastCalledTime4Connect = System.currentTimeMillis();
            setHandle(false);
            return responseHandle(send, false);
        } catch (Exception e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "和平台数据交换异常，重新交换数据....");
            }
            this.isConnect = false;
            throw new SocketException(Util4Java.getExceptionInfo(e));
        } catch (Throwable th) {
            this.lastCalledTime4Session = System.currentTimeMillis();
            this.lastCalledTime4Connect = System.currentTimeMillis();
            setHandle(false);
            throw th;
        }
    }

    public byte[] repeat() throws SocketException, CommunicationException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        setHandle(true);
        if (this.lastRequestData != null) {
            try {
                byte[] send = send(this.dataLengthHandle.initDataSize(this.lastRequestData.length), this.lastRequestData);
                this.lastRequestData = null;
                this.lastCalledTime4Session = System.currentTimeMillis();
                this.lastCalledTime4Connect = System.currentTimeMillis();
                setHandle(false);
                return responseHandle(send, false);
            } catch (IOException e) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.error(str, "业务处理时，发送出现异常" + Util4Java.getExceptionInfo(e));
                }
                this.isConnect = false;
                throw new SocketException(Util4Java.getExceptionInfo(e));
            } catch (Exception e2) {
                this.isConnect = false;
                if (this.fmLog != null) {
                    FMLog fMLog2 = this.fmLog;
                    String str2 = this.logTag;
                    fMLog2.warn(str2, "业务处理时，异步发送出现异常" + Util4Java.getExceptionInfo(e2));
                }
                throw new SocketException(Util4Java.getExceptionInfo(e2));
            }
        } else {
            throw new CommunicationException("没有需要重发的数据");
        }
    }

    private byte[] responseHandle(byte[] bArr, boolean z) throws CommunicationException, SocketException {
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "平台业务处理响应数据为空，业务处理失败");
            }
            CommunicationException communicationException = new CommunicationException("平台业务处理响应数据为空，业务处理失败");
            communicationException.setExceptionType(CommunicationException.CommunicationExceptionType.NO_REPONSE);
            if (z) {
                this.communicationNotify.exceptionNotify(communicationException, communicationException.getClass());
                return null;
            }
            throw communicationException;
        } else if (bArr.length < 12) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "平台业务处理时，收到响应数据格式错误，业务处理失败");
            }
            CommunicationException communicationException2 = new CommunicationException("平台业务处理时，收到响应数据格式错误，业务处理失败");
            communicationException2.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_REPONSE);
            communicationException2.setDirection(CommunicationException.CommandDirection.RESPONSE);
            if (z) {
                this.communicationNotify.exceptionNotify(communicationException2, communicationException2.getClass());
                return null;
            }
            throw communicationException2;
        } else {
            MessageHead messageHead = new MessageHead();
            messageHead.fromBytes(Arrays.copyOf(bArr, 12));
            ControlWord controlWord = messageHead.getControlWord();
            if (controlWord.getDirection() != ControlWord.Direction.RESPONSE) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "平台业务处理时，收到不是响应数据，业务处理失败");
                }
                CommunicationException communicationException3 = new CommunicationException("平台业务处理时，收到不是响应数据，业务处理失败");
                communicationException3.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_DIRECTION);
                communicationException3.setDirection(CommunicationException.CommandDirection.RESPONSE);
                if (z) {
                    this.communicationNotify.exceptionNotify(communicationException3, communicationException3.getClass());
                    return null;
                }
                throw communicationException3;
            }
            if (!Arrays.equals(this.sessionNumber, messageHead.getSessionNumber())) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "平台业务处理时，收到无效会话编号，业务处理失败");
                }
                this.openSessionFlag = false;
                CommunicationException communicationException4 = new CommunicationException("平台业务处理时，收到无效会话编号，业务处理失败");
                communicationException4.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_SESSION);
                communicationException4.setDirection(CommunicationException.CommandDirection.RESPONSE);
                if (z) {
                    this.communicationNotify.exceptionNotify(communicationException4, communicationException4.getClass());
                } else {
                    throw communicationException4;
                }
            }
            if (this.nextSendSerialNumber != messageHead.getSerialNumber()) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "平台业务处理时，收到无效会话流水，业务处理失败");
                }
                this.openSessionFlag = false;
                CommunicationException communicationException5 = new CommunicationException("平台业务处理时，收到无效会话流水，业务处理失败");
                communicationException5.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_SESSION_NUMBER);
                communicationException5.setDirection(CommunicationException.CommandDirection.RESPONSE);
                if (z) {
                    this.communicationNotify.exceptionNotify(communicationException5, communicationException5.getClass());
                } else {
                    throw communicationException5;
                }
            } else {
                this.serialNumber = this.nextSendSerialNumber;
            }
            if (controlWord.getReponseCode() == 0 || controlWord.getReponseCode() == 14) {
                byte[] securityLevel = messageHead.getSecurityLevel();
                if (!(securityLevel[0] == MessageHead.SecurityLevel.CIPHER.getValue() || securityLevel[1] == MessageHead.CheckType.MAC.getValue())) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "平台业务处理时，收到无效报文安全级别，业务处理失败");
                    }
                    CommunicationException communicationException6 = new CommunicationException("平台业务处理时，收到无效报文安全级别，业务处理失败");
                    communicationException6.setExceptionType(CommunicationException.CommunicationExceptionType.CHECK_FAILED);
                    communicationException6.setDirection(CommunicationException.CommandDirection.RESPONSE);
                    if (z) {
                        this.communicationNotify.exceptionNotify(communicationException6, communicationException6.getClass());
                    } else {
                        throw communicationException6;
                    }
                }
                byte[] copyOfRange = Arrays.copyOfRange(bArr, bArr.length - 4, bArr.length);
                byte[] calculateMAC4DES = MAC.calculateMAC4DES(Arrays.copyOfRange(this.sessionKey, this.checkKeyStart, this.checkKeyEnd), new byte[8], Arrays.copyOf(bArr, bArr.length - 4));
                if (!Arrays.equals(copyOfRange, Arrays.copyOf(calculateMAC4DES, 4))) {
                    if (this.fmLog != null) {
                        FMLog fMLog = this.fmLog;
                        String str = this.logTag;
                        fMLog.warn(str, "平台业务处理时，MAC验证失败，平台MAC[" + FM_Bytes.bytesToHexString(copyOfRange) + "],终端计算MAC[" + FM_Bytes.bytesToHexString(calculateMAC4DES) + Operators.ARRAY_END_STR);
                    }
                    CommunicationException communicationException7 = new CommunicationException("平台业务处理时，MAC验证失败，业务处理失败");
                    communicationException7.setExceptionType(CommunicationException.CommunicationExceptionType.CHECK_FAILED);
                    communicationException7.setDirection(CommunicationException.CommandDirection.RESPONSE);
                    if (z) {
                        this.communicationNotify.exceptionNotify(communicationException7, communicationException7.getClass());
                    } else {
                        throw communicationException7;
                    }
                }
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 12, bArr.length - 4);
                if (copyOfRange2.length < 1 || bArr.length % 8 != 0) {
                    CommunicationException communicationException8 = new CommunicationException("平台业务处理时，报文数据不是DES加密后的数据，业务处理失败");
                    communicationException8.setExceptionType(CommunicationException.CommunicationExceptionType.UNKNOW);
                    communicationException8.setDirection(CommunicationException.CommandDirection.RESPONSE);
                    if (z) {
                        this.communicationNotify.exceptionNotify(communicationException8, communicationException8.getClass());
                    } else {
                        throw communicationException8;
                    }
                }
                byte[] decrypt4des3 = DES.decrypt4des3(this.sessionKey, copyOfRange2);
                if (!FM_Bytes.isPatch4Des(decrypt4des3)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "平台响应的业务数据未补位");
                    }
                    CommunicationException communicationException9 = new CommunicationException("平台响应的业务数据未补位");
                    communicationException9.setExceptionType(CommunicationException.CommunicationExceptionType.UNKNOW);
                    communicationException9.setDirection(CommunicationException.CommandDirection.RESPONSE);
                    if (z) {
                        this.communicationNotify.exceptionNotify(communicationException9, communicationException9.getClass());
                    } else {
                        throw communicationException9;
                    }
                }
                byte[] byteRemovePatch4Des = FM_Bytes.byteRemovePatch4Des(decrypt4des3);
                if (z) {
                    this.communicationNotify.reponseMessageNotify(byteRemovePatch4Des);
                }
                if (this.fmLog != null) {
                    FMLog fMLog2 = this.fmLog;
                    String str2 = this.logTag;
                    fMLog2.debug(str2, "业务处理完成,message:" + FM_Bytes.bytesToHexString(byteRemovePatch4Des));
                }
                if (controlWord.isHaveNews()) {
                    this.communicationNotify.newsNotify();
                }
                return byteRemovePatch4Des;
            }
            CommunicationException.CommunicationExceptionType instance = CommunicationException.CommunicationExceptionType.instance(controlWord.getReponseCode());
            if (CommunicationException.CommunicationExceptionType.INVALID_SESSION == instance || CommunicationException.CommunicationExceptionType.INVALID_SESSION_NUMBER == instance) {
                this.openSessionFlag = false;
            }
            CommunicationException communicationException10 = new CommunicationException("业务处理失败:" + instance.getDescription());
            communicationException10.setExceptionType(instance);
            communicationException10.setDirection(CommunicationException.CommandDirection.RESPONSE);
            throw communicationException10;
        }
    }

    public byte[] getSessionNumber() {
        return this.sessionNumber;
    }

    public long getSessionSerialNumber() {
        return this.serialNumber;
    }

    public boolean isConnect() {
        if (this.isConnect && System.currentTimeMillis() - this.lastCalledTime4Connect >= 540000) {
            if (this.fmLog != null) {
                this.fmLog.debug(getClass().getName(), "server link timeout!");
            }
            this.isConnect = false;
        }
        return this.isConnect;
    }

    private byte[] send(byte[] bArr, byte[] bArr2) throws IOException {
        if (this.f548in == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(getClass().getName(), "Link is not established");
            }
            throw new IOException("Socket数据发送时,链接未建立");
        } else if (this.out == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(getClass().getName(), "Link is not established");
            }
            throw new IOException("Socket数据发送时,链接未建立");
        } else {
            this.lock.lock();
            try {
                byte[] join = FM_Bytes.join(bArr, bArr2);
                this.out.write(join);
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String name = getClass().getName();
                    fMLog.debug(name, "send data:" + FM_Bytes.bytesToHexString(join));
                }
                this.out.flush();
                this.handler = ReceiveHandler.instance();
                byte[] receive = this.handler.receive(this.dataLengthHandle, this.timeout, this.f548in);
                this.lock.unlock();
                if (this.fmLog != null) {
                    FMLog fMLog2 = this.fmLog;
                    String name2 = getClass().getName();
                    fMLog2.debug(name2, "recieve data:" + FM_Bytes.bytesToHexString(receive));
                }
                return receive;
            } catch (IOException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(getClass().getName(), "socket send data exception");
                }
                if (this.fmLog != null) {
                    this.fmLog.error(getClass().getName(), Util4Java.getExceptionInfo(e));
                    this.fmLog.debug(getClass().getName(), "异常抛出.....");
                }
                throw new IOException("Socket数据发送时,出现异常" + Util4Java.getExceptionInfo(e));
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
    }

    public boolean isOpenSession() {
        if (this.openSessionFlag && System.currentTimeMillis() - this.lastCalledTime4Session >= 540000) {
            if (this.fmLog != null) {
                this.fmLog.debug(getClass().getName(), "server link timeout!");
            }
            this.openSessionFlag = false;
        }
        return this.openSessionFlag;
    }

    public void cancel() {
        this.lastRequestData = null;
        if (this.handler != null) {
            this.handler.cancel();
        }
        this.isStop = true;
    }

    public boolean lastRequestDataIsNull() {
        return this.lastRequestData == null;
    }

    private void generateNextSerialNumber() {
        if (this.serialNumber >= MessageHead.SERIAL_MAK) {
            this.nextSendSerialNumber = 0;
        } else {
            this.nextSendSerialNumber = this.serialNumber + 1;
        }
    }
}
