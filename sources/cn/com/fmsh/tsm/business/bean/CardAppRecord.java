package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.tsm.business.enums.EnumTradeType;
import java.io.Serializable;

public class CardAppRecord implements Serializable {
    private static final long serialVersionUID = 1;
    private int amount;
    private int balance;
    private byte oriTradeType;
    private int overdraft;
    private byte terminalTradeType;
    private String tradeDate;
    private String tradeDevice;
    private int tradeNo;
    private String tradeTime;
    private EnumTradeType tradeType;

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public String getTradeDate() {
        return this.tradeDate;
    }

    public void setTradeDate(String str) {
        this.tradeDate = str;
    }

    public String getTradeDevice() {
        return this.tradeDevice;
    }

    public void setTradeDevice(String str) {
        this.tradeDevice = str;
    }

    public EnumTradeType getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(EnumTradeType enumTradeType) {
        this.tradeType = enumTradeType;
    }

    public int getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(int i) {
        this.tradeNo = i;
    }

    public byte getTerminalTradeType() {
        return this.terminalTradeType;
    }

    public void setTerminalTradeType(byte b) {
        this.terminalTradeType = b;
    }

    public byte getOriTradeType() {
        return this.oriTradeType;
    }

    public void setOriTradeType(byte b) {
        this.oriTradeType = b;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public int getOverdraft() {
        return this.overdraft;
    }

    public void setOverdraft(int i) {
        this.overdraft = i;
    }
}
