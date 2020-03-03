package cn.com.xm.bt.profile.nfc;

import com.taobao.weex.el.parse.Operators;

public class HMNFCStatus {
    public static final int DISCONNECT_NEAR = 1;
    public static final int DISCONNECT_TIMEOUT = 2;
    public static final int INIT_FAILED = 4;
    public static final int NFC_ERROR = 3;
    public static final int NFC_NEAR = 37;
    public static final int NFC_UNFINISH = 38;
    static final byte OTHER = -1;
    public static final int SUCCESS = 0;
    private int mStatus = 0;

    public HMNFCStatus() {
    }

    public HMNFCStatus(int i) {
        this.mStatus = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public boolean isSuccess() {
        return this.mStatus == 0;
    }

    public String toString() {
        return "HMNFCStatus{mStatus=" + this.mStatus + Operators.BLOCK_END;
    }
}
