package cn.com.fmsh.communication.message.tagvalue;

import java.io.UnsupportedEncodingException;

public class StringValueHandler4gbk implements StringValueHandler {
    public String getTagvalue(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        try {
            return new String(bArr, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] setTagValue(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
