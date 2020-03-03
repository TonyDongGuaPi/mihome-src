package cn.com.fmsh.script.bean;

import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ApduRequest {
    private byte[] apdu;
    private Map<Integer, Integer> expectationAndNextList = new HashMap();
    private final int expectationLength = 2;
    private int id;
    private final int idLength = 1;
    private FMLog log = LogFactory.getInstance().getLog();
    private final String logTag = "ApduRequest";
    private byte tag;

    public void fromBytes(byte[] bArr) throws FMScriptHandleException, IOException {
        if (bArr != null) {
        }
    }

    public byte[] toBytes() {
        return null;
    }

    public byte getTag() {
        return this.tag;
    }

    public void setTag(byte b) {
        this.tag = b;
    }

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

    public boolean addExpectationAndNext(byte[] bArr) {
        if (bArr == null) {
            this.log.warn("ApduRequest", "APDU请求期望返回结果 与下一条指令标识增加时，请求期望返回结果为空，获取失败");
            return false;
        } else if (bArr.length != 3) {
            this.log.warn("ApduRequest", "APDU请求期望返回结果 与下一条指令标识增加时，下一条指令不合法");
            return false;
        } else {
            this.expectationAndNextList.put(Integer.valueOf(FM_Bytes.bytesToInt(Arrays.copyOf(bArr, 2))), Integer.valueOf(FM_Bytes.bytesToInt(Arrays.copyOfRange(bArr, 2, 3))));
            return true;
        }
    }

    public int getNext4Expectation(byte[] bArr) {
        if (bArr == null) {
            this.log.warn("ApduRequest", "根据APDU请求期望返回结果获取下一指令的标识时，请求期望返回结果为空，获取失败");
            return -1;
        }
        Integer num = this.expectationAndNextList.get(Integer.valueOf(FM_Bytes.bytesToInt(bArr)));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public boolean isHaveExpectation() {
        return !this.expectationAndNextList.isEmpty();
    }

    public int getExpectationCount() {
        return this.expectationAndNextList.size();
    }

    public int compareTo(Object obj) {
        ApduRequest apduRequest = (ApduRequest) obj;
        if (this.id > apduRequest.getId()) {
            return 1;
        }
        return this.id < apduRequest.getId() ? -1 : 0;
    }
}
