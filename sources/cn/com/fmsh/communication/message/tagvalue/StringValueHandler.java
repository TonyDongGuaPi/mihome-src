package cn.com.fmsh.communication.message.tagvalue;

public interface StringValueHandler {
    String getTagvalue(byte[] bArr);

    byte[] setTagValue(String str);
}
