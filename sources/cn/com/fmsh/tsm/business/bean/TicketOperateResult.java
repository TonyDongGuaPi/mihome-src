package cn.com.fmsh.tsm.business.bean;

public class TicketOperateResult {
    private int operateResult;
    private byte[] ticketStub;

    public byte[] getTicketStub() {
        return this.ticketStub;
    }

    public void setTicketStub(byte[] bArr) {
        this.ticketStub = bArr;
    }

    public int getOperateResult() {
        return this.operateResult;
    }

    public void setOperateResult(int i) {
        this.operateResult = i;
    }
}
