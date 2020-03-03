package cn.com.fmsh.communication.message.tagvalue;

import cn.com.fmsh.util.FM_CN;

public class StringValueHandler4cn implements StringValueHandler {
    public String getTagvalue(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        return FM_CN.bcdBytesToString(bArr);
    }

    public byte[] setTagValue(String str) {
        if (str == null) {
            return null;
        }
        return FM_CN.string2Bcd(str);
    }
}
