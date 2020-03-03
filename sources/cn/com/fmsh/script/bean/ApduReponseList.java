package cn.com.fmsh.script.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.core.MessageHandler;
import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.util.FM_Bytes;
import java.util.ArrayList;
import java.util.List;

public class ApduReponseList {
    private List<ApduResponse> list = new ArrayList();

    public void add(ApduResponse apduResponse) {
        this.list.add(apduResponse);
    }

    public ApduResponse[] getApduResponse() {
        return (ApduResponse[]) this.list.toArray(new ApduResponse[this.list.size()]);
    }

    public byte[] toBytes4A3() {
        byte[] bArr = new byte[0];
        for (ApduResponse next : this.list) {
            if (next != null) {
                bArr = FM_Bytes.join(bArr, next.toBytes());
            }
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[(length + 2)];
        bArr2[0] = ScriptToolsConst.TagName.ResponseMultiple;
        bArr2[1] = (byte) length;
        for (int i = 0; i < length; i++) {
            bArr2[i + 2] = bArr[i];
        }
        return bArr2;
    }

    public ITag toTag4A3() throws FMCommunicationMessageException {
        MessageHandler messageHandler = MessageHandleFactory.getMessageHandler();
        Tag createTag = messageHandler.createTag((byte) ScriptToolsConst.TagName.ResponseMultiple);
        for (ApduResponse next : this.list) {
            if (next != null) {
                createTag.addValue((ITag) messageHandler.createTag(next.toBytes()));
            }
        }
        return createTag;
    }

    public byte[] toBytes4A2() {
        if (this.list.size() > 0) {
            return this.list.get(0).toBytes();
        }
        return null;
    }

    public ITag toTag4A2() throws FMCommunicationMessageException {
        MessageHandler messageHandler = MessageHandleFactory.getMessageHandler();
        if (this.list.size() > 0) {
            return messageHandler.createTag(this.list.get(0).toBytes());
        }
        return null;
    }

    public int size() {
        return this.list.size();
    }

    public ApduResponse[] getApduResponses() {
        return (ApduResponse[]) this.list.toArray(new ApduResponse[0]);
    }

    public List<byte[]> getApduResponseList() {
        ArrayList arrayList = new ArrayList();
        for (ApduResponse next : this.list) {
            if (next != null) {
                arrayList.add(next.getResult());
            }
        }
        return arrayList;
    }

    public static void main(String[] strArr) {
        byte[] hexStringToBytes = FM_Bytes.hexStringToBytes("6985");
        ApduResponse apduResponse = new ApduResponse();
        apduResponse.setResult(hexStringToBytes);
        apduResponse.setId(1);
        ApduReponseList apduReponseList = new ApduReponseList();
        apduReponseList.add(apduResponse);
        System.out.println(FM_Bytes.bytesToHexString(apduReponseList.toBytes4A2()));
        System.out.println(FM_Bytes.bytesToHexString(apduReponseList.toBytes4A3()));
    }
}
