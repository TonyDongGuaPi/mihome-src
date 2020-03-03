package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import java.io.InputStream;

public interface IMessageHandler {
    IMessage createMessage(int i);

    IMessage createMessage(int i, byte[] bArr) throws FMCommunicationMessageException;

    IMessage createMessage(byte[] bArr) throws FMCommunicationMessageException;

    IMessage createMessageAndRetCode(int i, byte[] bArr) throws FMCommunicationMessageException;

    IMessage createMessageAndRetCode(byte[] bArr) throws FMCommunicationMessageException;

    ITag createTag(byte b);

    ITag createTag(byte b, byte[] bArr) throws FMCommunicationMessageException;

    Tag createTag(byte[] bArr) throws FMCommunicationMessageException;

    boolean isLoad();

    int loadDefine(InputStream inputStream) throws FMCommunicationMessageException;
}
