package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;

public interface ITag {
    public static final int idSize = 1;

    int addValue(int i) throws FMCommunicationMessageException;

    int addValue(ITag iTag) throws FMCommunicationMessageException;

    int addValue(String str) throws FMCommunicationMessageException;

    int addValue(byte[] bArr) throws FMCommunicationMessageException;

    byte[] getBytesVal() throws FMCommunicationMessageException;

    byte getId();

    int getIntVal() throws FMCommunicationMessageException;

    byte[] getItemBytesVal(int i) throws FMCommunicationMessageException;

    int getItemCount() throws FMCommunicationMessageException;

    int getItemIntVal(int i) throws FMCommunicationMessageException;

    String getItemStringVal(int i) throws FMCommunicationMessageException;

    int getItemTagID(int i) throws FMCommunicationMessageException;

    ITag getItemTagVal(int i) throws FMCommunicationMessageException;

    ITag[] getItemTags() throws FMCommunicationMessageException;

    ETagType getItemType(int i) throws FMCommunicationMessageException;

    String getStringVal() throws FMCommunicationMessageException;

    byte[] getTagValue();

    ETagType getType();

    boolean isValid();

    int setValue(Tag tag, int i) throws FMCommunicationMessageException;

    byte[] toBytes() throws FMCommunicationMessageException;
}
