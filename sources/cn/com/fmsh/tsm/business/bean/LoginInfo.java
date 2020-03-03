package cn.com.fmsh.tsm.business.bean;

public class LoginInfo {
    int failureNum;
    byte[] patchData;
    int result;
    int userLockTime;

    public int getResult() {
        return this.result;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public int getFailureNum() {
        return this.failureNum;
    }

    public void setFailureNum(int i) {
        this.failureNum = i;
    }

    public int getUserLockTime() {
        return this.userLockTime;
    }

    public void setUserLockTime(int i) {
        this.userLockTime = i;
    }

    public byte[] getPatchData() {
        return this.patchData;
    }

    public void setPatchData(byte[] bArr) {
        this.patchData = bArr;
    }
}
