package cn.com.fmsh.communication.message.core;

import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.TLVParse;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message implements IMessage {
    private int code;
    private boolean isReadOnly = false;
    FMLog log = LogFactory.getInstance().getLog();
    String logTag = Message.class.getName();
    private MessageDefine messageDefine;
    private MessageHandler messageHandler;
    private byte[] retCode;
    private List<ITag> tags = new ArrayList();

    public int getTagCount4Id(int i) {
        return 0;
    }

    public int setVal(ITag iTag, int i) throws FMCommunicationMessageException {
        return -1;
    }

    Message(MessageHandler messageHandler2) {
        this.messageHandler = messageHandler2;
    }

    Message(MessageHandler messageHandler2, int i) {
        this.messageHandler = messageHandler2;
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public int getTagCount() {
        return this.tags.size();
    }

    public byte[] getRetCode() {
        return this.retCode;
    }

    public void setRetCode(byte[] bArr) {
        this.retCode = bArr;
    }

    public int fromPackageBodyAndRetCode(int i, byte[] bArr) throws FMCommunicationMessageException {
        this.isReadOnly = true;
        this.code = i;
        if (bArr == null || bArr.length < 2) {
            throw new InvalidParameterException("根据字节序列构造消息时，传入的字节序列长度不合法");
        }
        this.retCode = Arrays.copyOfRange(bArr, 2, 2);
        this.messageDefine = this.messageHandler.getMessageRetDefine(this.code);
        if (this.messageDefine != null) {
            messageTagsHandle(Arrays.copyOfRange(bArr, 2, bArr.length));
            return 0;
        }
        throw new FMCommunicationMessageException("根据二进制序列构造Message时，解析获取到的消息编码不合法");
    }

    public int fromPackageBodyAndRetCode(byte[] bArr) throws FMCommunicationMessageException {
        this.isReadOnly = true;
        if (bArr == null || bArr.length < 4) {
            throw new InvalidParameterException("根据字节序列构造消息时，传入的字节序列长度不合法");
        }
        this.code = Util4Java.String2Int(FM_CN.bcdBytesToString(Arrays.copyOf(bArr, 2)), -1);
        this.retCode = Arrays.copyOfRange(bArr, 2, 4);
        this.messageDefine = this.messageHandler.getMessageRetDefine(this.code);
        if (this.messageDefine != null) {
            messageTagsHandle(Arrays.copyOfRange(bArr, 4, bArr.length));
            return 0;
        }
        throw new FMCommunicationMessageException("根据二进制序列构造Message时，解析获取到的消息编码不合法");
    }

    public int fromPackageBody(int i, byte[] bArr) throws FMCommunicationMessageException {
        this.isReadOnly = true;
        this.code = i;
        this.messageDefine = this.messageHandler.getMessageDefine(this.code);
        if (this.messageDefine != null) {
            messageTagsHandle(bArr);
            return 0;
        }
        throw new FMCommunicationMessageException("根据二进制序列构造Message时，解析获取到的消息编码不合法");
    }

    public int fromPackageBody(byte[] bArr) throws FMCommunicationMessageException {
        this.isReadOnly = true;
        if (bArr == null || bArr.length < 2) {
            throw new InvalidParameterException("根据字节序列构造消息时，传入的字节序列长度不合法");
        }
        this.code = Util4Java.String2Int(FM_CN.bcdBytesToString(Arrays.copyOf(bArr, 2)), -1);
        if (this.code != -1) {
            this.messageDefine = this.messageHandler.getMessageDefine(this.code);
            if (this.messageDefine != null) {
                messageTagsHandle(Arrays.copyOfRange(bArr, 2, bArr.length));
                return 0;
            }
            throw new FMCommunicationMessageException("根据二进制序列构造Message时，解析获取到的消息编码不合法");
        }
        this.tags.clear();
        throw new InvalidParameterException("根据字节序列构造消息时，解析获取到的编码不合法");
    }

    private void messageTagsHandle(byte[] bArr) throws FMCommunicationMessageException {
        for (TLVParse.TagEntry next : TLVParse.intance().parse(bArr, 1)) {
            Tag createTag = this.messageHandler.createTag(next.getTag()[0], next.getData());
            if (!createTag.isValid()) {
                this.tags.clear();
                if (this.log != null) {
                    this.log.warn(Message.class.getName(), "二进制序列构造Message时，Tag标识不合法");
                }
                throw new FMCommunicationMessageException("根据二进制序列构造Message时，解析获取到的TAG验证失败");
            }
            this.tags.add(createTag);
        }
    }

    public byte[] toBytes() throws FMCommunicationMessageException {
        if (isValid()) {
            byte[] intToBcdBytes = FM_CN.intToBcdBytes(this.code, 2);
            byte[] bArr = new byte[0];
            for (ITag bytes : this.tags) {
                byte[] bytes2 = bytes.toBytes();
                if (bytes2 != null) {
                    intToBcdBytes = FM_Bytes.join(intToBcdBytes, bytes2);
                }
            }
            return intToBcdBytes;
        }
        throw new FMCommunicationMessageException("消息对象转换成字节序列，消息对象无效");
    }

    public int clear() {
        this.tags.clear();
        return 0;
    }

    public ITag getTag4Index(int i) throws FMCommunicationMessageException {
        if (i < 0 || i > this.tags.size()) {
            throw new FMCommunicationMessageException("根据序列号获取tag时，序列号越界");
        }
        ITag iTag = this.tags.get(i);
        if (iTag != null) {
            return iTag;
        }
        return null;
    }

    public ITag getTag4Id(int i) throws FMCommunicationMessageException {
        for (ITag next : this.tags) {
            if (next != null && i == next.getId()) {
                return next;
            }
        }
        return null;
    }

    public ITag getTag4Id(int i, int i2) throws FMCommunicationMessageException {
        ArrayList arrayList = new ArrayList();
        for (ITag next : this.tags) {
            if (next != null && i == next.getId()) {
                arrayList.add(next);
            }
        }
        if (i2 >= 0 && i2 <= arrayList.size()) {
            return (ITag) arrayList.get(i2);
        }
        throw new FMCommunicationMessageException("根据Tag标识和序列号获取tag时，序列号越界");
    }

    public int addTag(ITag iTag) throws FMCommunicationMessageException {
        if (this.isReadOnly) {
            return -1;
        }
        if (iTag == null) {
            return -2;
        }
        this.tags.add(iTag);
        return 0;
    }

    public boolean isValid() {
        if (this.messageDefine == null) {
            if (this.retCode == null) {
                this.messageDefine = this.messageHandler.getMessageDefine(this.code);
            } else {
                this.messageDefine = this.messageHandler.getMessageRetDefine(this.code);
            }
        }
        if (this.messageDefine == null) {
            if (this.log != null) {
                this.log.warn(this.logTag, "消息合法性检查时，获取消息定义信息失败");
            }
            return false;
        }
        MessageTagDefine[] messageTagDefines = this.messageDefine.getMessageTagDefines();
        for (MessageTagDefine messageTagDefine : messageTagDefines) {
            if (messageTagDefine.getExist() == 1) {
                boolean z = false;
                for (ITag id : this.tags) {
                    if (id.getId() == messageTagDefine.getTag()) {
                        z = true;
                    }
                }
                if (!z) {
                    if (this.log != null) {
                        this.log.warn(this.logTag, String.format("配置文件定义Message[" + this.code + "]必须存在的Tag[%X]，不存在，该消息不合法", new Object[]{Byte.valueOf(messageTagDefine.getTag())}));
                    }
                    return false;
                }
            }
        }
        for (ITag next : this.tags) {
            boolean z2 = false;
            for (MessageTagDefine tag : messageTagDefines) {
                if (next.getId() == tag.getTag()) {
                    z2 = true;
                }
            }
            if (!z2) {
                if (this.log != null) {
                    this.log.warn(this.logTag, String.format("消息中已经有的TAG[%X],在配置文件中未定义", new Object[]{Byte.valueOf(next.getId())}));
                }
                return false;
            }
        }
        return true;
    }
}
