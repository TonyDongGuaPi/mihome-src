package cn.com.fmsh.communication.message.tagvalue;

public class StringValueHandler4asc implements StringValueHandler {
    public String getTagvalue(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        return new String(bArr);
    }

    public byte[] setTagValue(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }
}
