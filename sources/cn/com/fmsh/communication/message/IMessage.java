package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;

public interface IMessage {
    public static final int codeSize = 2;
    public static final int retCodeSize = 2;

    int addTag(ITag iTag) throws FMCommunicationMessageException;

    int getCode();

    byte[] getRetCode();

    ITag getTag4Id(int i) throws FMCommunicationMessageException;

    ITag getTag4Id(int i, int i2) throws FMCommunicationMessageException;

    ITag getTag4Index(int i) throws FMCommunicationMessageException;

    int getTagCount();

    int getTagCount4Id(int i);

    boolean isValid();

    void setRetCode(byte[] bArr);

    int setVal(ITag iTag, int i) throws FMCommunicationMessageException;

    byte[] toBytes() throws FMCommunicationMessageException;
}
