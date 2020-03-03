package cn.com.fmsh.communication.message.tagvalue;

import cn.com.fmsh.util.FM_Bytes;
import java.io.UnsupportedEncodingException;

public class StringValueHandler4utf implements StringValueHandler {
    public String getTagvalue(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        try {
            return new String(bArr, "utf-8");
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
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] strArr) {
        byte[] bArr;
        try {
            bArr = "€".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        System.out.println(FM_Bytes.bytesToHexString(bArr));
    }
}
