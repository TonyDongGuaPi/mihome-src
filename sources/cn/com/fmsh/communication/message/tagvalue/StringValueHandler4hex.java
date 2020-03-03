package cn.com.fmsh.communication.message.tagvalue;

import cn.com.fmsh.util.FM_Bytes;

public class StringValueHandler4hex implements StringValueHandler {
    public String getTagvalue(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        return FM_Bytes.bytesToHexString(bArr);
    }

    public byte[] setTagValue(String str) {
        if (str == null) {
            return null;
        }
        return FM_Bytes.hexStringToBytes(str);
    }
}
