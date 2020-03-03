package cn.com.fmsh.communication.message.core;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.TLVParse;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.communication.message.tagvalue.HandlerFactory;
import cn.com.fmsh.communication.message.tagvalue.StringValueHandler;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.List;

public class Tag implements ITag {
    private List<ITag> children = new ArrayList();
    private byte id;
    private boolean isReadonly = false;
    private FMLog log = LogFactory.getInstance().getLog();
    private final String logTag = Message.class.getName();
    private MessageHandler mMessageHandler;
    private TagDefine tagDefine;
    private ETagType tagType;
    private byte[] tagValue;

    public int clear() {
        return 0;
    }

    public int setValue(Tag tag, int i) throws FMCommunicationMessageException {
        return 0;
    }

    Tag(MessageHandler messageHandler) {
        this.mMessageHandler = messageHandler;
    }

    Tag(MessageHandler messageHandler, byte b) {
        this.mMessageHandler = messageHandler;
        this.id = b;
        this.tagDefine = this.mMessageHandler.getTagDefine(b);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int fromPackageBody(byte[] r9) {
        /*
            r8 = this;
            r0 = 1
            r8.isReadonly = r0
            r1 = -1
            if (r9 == 0) goto L_0x00a4
            int r2 = r9.length
            r3 = 2
            if (r2 >= r3) goto L_0x000c
            goto L_0x00a4
        L_0x000c:
            r2 = 0
            byte r4 = r9[r2]
            r8.id = r4
            cn.com.fmsh.communication.message.core.MessageHandler r4 = r8.mMessageHandler
            byte r5 = r8.id
            cn.com.fmsh.communication.message.core.TagDefine r4 = r4.getTagDefine(r5)
            r8.tagDefine = r4
            cn.com.fmsh.communication.message.core.TagDefine r4 = r8.tagDefine
            r5 = -2
            if (r4 != 0) goto L_0x0021
            return r5
        L_0x0021:
            cn.com.fmsh.communication.message.core.TagDefine r4 = r8.tagDefine
            cn.com.fmsh.communication.message.enumerate.ETagType r4 = r4.getType()
            r8.tagType = r4
            byte r4 = r9[r0]
            r6 = 255(0xff, float:3.57E-43)
            r4 = r4 & r6
            byte[] r7 = new byte[r3]
            if (r4 != r6) goto L_0x0042
            r4 = 4
            byte r3 = r9[r3]
            r7[r2] = r3
            r3 = 3
            byte r3 = r9[r3]
            r7[r0] = r3
            int r3 = cn.com.fmsh.util.FM_Bytes.bytesToInt(r7)
            r4 = r3
            r3 = 4
        L_0x0042:
            int r6 = r9.length
            int r7 = r3 + r4
            if (r6 >= r7) goto L_0x0048
            return r1
        L_0x0048:
            cn.com.fmsh.communication.message.core.TagDefine r6 = r8.tagDefine
            int r6 = r6.getLength()
            if (r6 == 0) goto L_0x0059
            cn.com.fmsh.communication.message.core.TagDefine r6 = r8.tagDefine
            int r6 = r6.getLength()
            if (r4 <= r6) goto L_0x0059
            return r5
        L_0x0059:
            int r5 = r9.length
            if (r7 <= r5) goto L_0x009d
            cn.com.fmsh.util.log.FMLog r1 = r8.log
            if (r1 == 0) goto L_0x009b
            cn.com.fmsh.util.log.FMLog r1 = r8.log
            java.lang.String r3 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "数据长度["
            r5.<init>(r6)
            int r6 = r9.length
            r5.append(r6)
            java.lang.String r6 = "]指定小于数据指定长度["
            r5.append(r6)
            java.lang.String r6 = "%X"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0[r2] = r4
            java.lang.String r0 = java.lang.String.format(r6, r0)
            r5.append(r0)
            java.lang.String r0 = "] 和数据长度字节序列的长度["
            r5.append(r0)
            int r9 = r9.length
            r5.append(r9)
            java.lang.String r9 = "]和"
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            r1.warn(r3, r9)
        L_0x009b:
            r9 = -3
            return r9
        L_0x009d:
            byte[] r9 = java.util.Arrays.copyOfRange(r9, r3, r7)
            r8.tagValue = r9
            return r1
        L_0x00a4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.communication.message.core.Tag.fromPackageBody(byte[]):int");
    }

    public int fromPackageBody(byte b, byte[] bArr) throws FMCommunicationMessageException {
        this.id = b;
        this.isReadonly = true;
        this.tagDefine = this.mMessageHandler.getTagDefine(b);
        if (this.tagDefine == null) {
            if (this.log != null) {
                this.log.warn(this.logTag, String.format("在配置文件中未定义TAG[%X]", new Object[]{Byte.valueOf(b)}));
            }
            return -2;
        }
        this.tagType = this.tagDefine.getType();
        int length = bArr.length;
        if (this.tagDefine.getLength() == 0 || length <= this.tagDefine.getLength()) {
            this.tagValue = bArr;
            if (ETagType.C == this.tagType) {
                for (TLVParse.TagEntry next : TLVParse.intance().parse(this.tagValue, 1)) {
                    Tag tag = new Tag(this.mMessageHandler);
                    tag.fromPackageBody(next.getTag()[0], next.getData());
                    if (tag.isValid()) {
                        this.children.add(tag);
                    }
                }
            }
            return 0;
        }
        if (this.log != null) {
            this.log.warn(this.logTag, String.format("TAG[%X]定义的长度和数据给的长度不一致", new Object[]{Byte.valueOf(b)}));
        }
        return -2;
    }

    public byte[] toBytes() throws FMCommunicationMessageException {
        if (!isValid()) {
            if (this.log != null) {
                this.log.warn(this.logTag, String.format("TAG[%X]转换成字节序列时，有效性检查不过", new Object[]{Byte.valueOf(this.id)}));
            }
            throw new FMCommunicationMessageException(String.format("TAG[%X]转换成字节序列时，TAG数据无效", new Object[]{Byte.valueOf(this.id)}));
        }
        if (ETagType.C == this.tagType) {
            this.tagValue = new byte[0];
            for (ITag bytes : this.children) {
                this.tagValue = FM_Bytes.join(this.tagValue, bytes.toBytes());
            }
        }
        if (this.tagValue == null) {
            if (this.log != null) {
                this.log.warn(this.logTag, String.format("TAG[%X]转换成字节序列时,TAG数据为空", new Object[]{Byte.valueOf(this.id)}));
            }
            throw new FMCommunicationMessageException(String.format("TAG[%X]转换成字节序列时,TAG数据为空", new Object[]{Byte.valueOf(this.id)}));
        }
        int length = this.tagValue.length;
        int i = 4;
        byte[] bArr = new byte[4];
        bArr[0] = this.id;
        if (length >= 255) {
            bArr[1] = -1;
            byte[] intToBytes = FM_Bytes.intToBytes(length, 2);
            bArr[2] = intToBytes[0];
            bArr[3] = intToBytes[1];
        } else {
            bArr[1] = (byte) length;
            i = 2;
        }
        byte[] bArr2 = new byte[(i + length)];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = bArr[i2];
        }
        for (int i3 = 0; i3 < length; i3++) {
            bArr2[i + i3] = this.tagValue[i3];
        }
        return bArr2;
    }

    public ETagType getType() {
        return this.tagType;
    }

    public byte getId() {
        return this.id;
    }

    public int getIntVal() throws FMCommunicationMessageException {
        if (ETagType.I == this.tagType) {
            return FM_Bytes.bytesToInt(this.tagValue);
        }
        return -1;
    }

    public String getStringVal() throws FMCommunicationMessageException {
        StringValueHandler stringValueHandle = HandlerFactory.instance().getStringValueHandle(this.tagType);
        if (stringValueHandle != null) {
            return stringValueHandle.getTagvalue(this.tagValue);
        }
        return null;
    }

    public byte[] getBytesVal() throws FMCommunicationMessageException {
        if (ETagType.B == this.tagType) {
            return this.tagValue;
        }
        return null;
    }

    public int getItemCount() throws FMCommunicationMessageException {
        return this.children.size();
    }

    public ITag[] getItemTags() throws FMCommunicationMessageException {
        return (ITag[]) this.children.toArray(new ITag[0]);
    }

    public ETagType getItemType(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i).getType();
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的类型时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public int getItemTagID(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i).getId();
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的标识时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public int getItemIntVal(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i).getIntVal();
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的值时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public String getItemStringVal(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i).getStringVal();
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的值时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public byte[] getItemBytesVal(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i).getBytesVal();
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的值时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public ITag getItemTagVal(int i) throws FMCommunicationMessageException {
        if (i >= 0 && i <= this.children.size()) {
            return this.children.get(i);
        }
        throw new FMCommunicationMessageException(String.format("获取复杂Tag[%X]的包含Tag的值时，传入的下标越界", new Object[]{Byte.valueOf(this.id)}));
    }

    public int addValue(int i) throws FMCommunicationMessageException {
        if (this.isReadonly) {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，该Tag只读", new Object[]{Byte.valueOf(this.id)}));
        } else if (this.tagDefine != null) {
            this.tagType = this.tagDefine.getType();
            if (this.tagType == ETagType.I) {
                this.tagValue = FM_Bytes.intToBytes(i, this.tagDefine.getLength());
                return 0;
            }
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入TAG值的类型出错", new Object[]{Byte.valueOf(this.id)}));
        } else {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，获取TAG类型失败", new Object[]{Byte.valueOf(this.id)}));
        }
    }

    public int addValue(String str) throws FMCommunicationMessageException {
        if (this.isReadonly) {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，该Tag只读", new Object[]{Byte.valueOf(this.id)}));
        } else if (this.tagDefine != null) {
            this.tagType = this.tagDefine.getType();
            StringValueHandler stringValueHandle = HandlerFactory.instance().getStringValueHandle(this.tagType);
            if (stringValueHandle != null) {
                this.tagValue = stringValueHandle.setTagValue(str);
                return 0;
            }
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，获取TAG类型不合法", new Object[]{Byte.valueOf(this.id)}));
        } else {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，获取TAG类型失败", new Object[]{Byte.valueOf(this.id)}));
        }
    }

    public int addValue(byte[] bArr) throws FMCommunicationMessageException {
        if (this.isReadonly) {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，该Tag只读", new Object[]{Byte.valueOf(this.id)}));
        } else if (this.tagDefine != null) {
            this.tagType = this.tagDefine.getType();
            if (this.tagType != ETagType.B) {
                throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入TAG值的类型出错", new Object[]{Byte.valueOf(this.id)}));
            } else if (this.tagDefine.getLength() == 0 || bArr.length <= this.tagDefine.getLength()) {
                this.tagValue = bArr;
                return 0;
            } else {
                throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入TAG值的长度不合法", new Object[]{Byte.valueOf(this.id)}));
            }
        } else {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，获取TAG类型失败", new Object[]{Byte.valueOf(this.id)}));
        }
    }

    public int addValue(ITag iTag) throws FMCommunicationMessageException {
        boolean z;
        if (iTag == null) {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入的TAG为空", new Object[]{Byte.valueOf(this.id)}));
        } else if (this.isReadonly) {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，该Tag只读", new Object[]{Byte.valueOf(this.id)}));
        } else if (this.tagDefine != null) {
            this.tagType = this.tagDefine.getType();
            if (this.tagType == ETagType.C) {
                TagItemDefine[] tagItemDefines = this.tagDefine.getTagItemDefines();
                int length = tagItemDefines.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    } else if (tagItemDefines[i].getTag() == iTag.getId()) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    this.children.add(iTag);
                    return 0;
                }
                throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入TAG 标识不合法", new Object[]{Byte.valueOf(iTag.getId())}));
            }
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，传入TAG值的类型出错", new Object[]{Byte.valueOf(this.id)}));
        } else {
            throw new FMCommunicationMessageException(String.format("给TAG[%X]赋值时，获取TAG类型失败", new Object[]{Byte.valueOf(this.id)}));
        }
    }

    public boolean isValid() {
        if (this.tagDefine == null) {
            if (this.log != null) {
                this.log.warn(this.logTag, String.format("TAG[%X]验证时，获取配置文件定义失败", new Object[]{Byte.valueOf(this.id)}));
            }
            return false;
        }
        if (this.tagDefine.getLength() != 0) {
            if (this.tagValue == null || this.tagValue.length < 1) {
                if (this.log != null) {
                    this.log.warn(this.logTag, String.format("TAG[%X]验证时，TAG的值为空", new Object[]{Byte.valueOf(this.id)}));
                }
                return false;
            } else if (this.tagValue.length > this.tagDefine.getLength()) {
                if (this.log != null) {
                    this.log.warn(this.logTag, String.format("TAG[%X]数据长度和配置文件定义的数据长度不符", new Object[]{Byte.valueOf(this.id)}));
                }
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Tag) && this.id == ((Tag) obj).id) {
            return true;
        }
        return false;
    }

    public byte[] getTagValue() {
        return this.tagValue;
    }
}
