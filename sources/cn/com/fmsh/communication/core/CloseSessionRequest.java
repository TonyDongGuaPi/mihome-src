package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;

public class CloseSessionRequest {
    private static final int TERMINAL_TIME_LENGTH = 7;
    private static final int TERMINAL_TIME_OFFSET = 0;
    private byte[] terminalTime;

    public byte[] getTerminalTime() {
        return this.terminalTime;
    }

    public void setTerminalTime(byte[] bArr) {
        this.terminalTime = bArr;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[7];
        if (this.terminalTime == null) {
            this.terminalTime = FM_CN.string2Bcd(Util4Java.date2string("yyyyMMddHHmmss"));
        }
        for (int i = 0; i < 7; i++) {
            bArr[i + 0] = this.terminalTime[i];
        }
        return bArr;
    }

    public void fromBytes(byte[] bArr) {
        if (bArr != null && bArr.length == 7) {
            this.terminalTime = bArr;
        }
    }
}
