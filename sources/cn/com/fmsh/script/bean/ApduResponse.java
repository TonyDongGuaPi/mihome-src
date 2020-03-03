package cn.com.fmsh.script.bean;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.util.FM_Bytes;

public class ApduResponse {
    private byte[] apdu;
    private int id;
    private byte[] result;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public byte[] getApdu() {
        return this.apdu;
    }

    public void setApdu(byte[] bArr) {
        this.apdu = bArr;
    }

    public byte[] getResult() {
        return this.result;
    }

    public void setResult(byte[] bArr) {
        this.result = bArr;
    }

    public byte[] toBytes() {
        if (this.result == null || this.result.length < 1) {
            return null;
        }
        byte[] bArr = new byte[(this.result.length + 3)];
        bArr[0] = ScriptToolsConst.TagName.ResponseSingle;
        bArr[1] = (byte) (this.result.length + 1);
        bArr[2] = (byte) this.id;
        for (int i = 0; i < this.result.length; i++) {
            bArr[i + 3] = this.result[i];
        }
        return bArr;
    }

    public static void main(String[] strArr) {
        byte[] hexStringToBytes = FM_Bytes.hexStringToBytes("01");
        ApduResponse apduResponse = new ApduResponse();
        apduResponse.setResult(hexStringToBytes);
        System.out.println(FM_Bytes.bytesToHexString(apduResponse.toBytes()));
    }
}
