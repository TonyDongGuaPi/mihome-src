package cn.com.fmsh.tsm.business.bean;

import java.util.ArrayList;
import java.util.List;

public class CardAppInfo {
    private Integer balance;
    private byte[] cardAppNo;
    private int cardType = 0;
    private String delayDate;
    private String faceId;
    private boolean isAppClose = false;
    private String moc;
    private List<CardAppRecord> records = new ArrayList();
    private String time4Validity;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getFaceId() {
        return this.faceId;
    }

    public void setFaceId(String str) {
        this.faceId = str;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer num) {
        this.balance = num;
    }

    public int getCardType() {
        return this.cardType;
    }

    public void setCardType(int i) {
        this.cardType = i;
    }

    public byte[] getCardAppNo() {
        return this.cardAppNo;
    }

    public void setCardAppNo(byte[] bArr) {
        this.cardAppNo = bArr;
    }

    public boolean isAppClose() {
        return this.isAppClose;
    }

    public void setAppClose(boolean z) {
        this.isAppClose = z;
    }

    public CardAppRecord[] getRecords() {
        if (this.records == null || this.records.size() < 1) {
            return null;
        }
        return (CardAppRecord[]) this.records.toArray(new CardAppRecord[0]);
    }

    public void addRecord(CardAppRecord[] cardAppRecordArr) {
        if (this.records != null) {
            for (CardAppRecord add : cardAppRecordArr) {
                this.records.add(add);
            }
        }
    }

    public void addRecord(CardAppRecord cardAppRecord) {
        if (cardAppRecord != null) {
            this.records.add(cardAppRecord);
        }
    }

    public String getBalanceDelayDate() {
        return this.delayDate;
    }

    public void setDelayDate(String str) {
        this.delayDate = str;
    }

    public String getMoc() {
        return this.moc;
    }

    public void setMoc(String str) {
        this.moc = str;
    }

    public String getTime4Validity() {
        return this.time4Validity;
    }

    public void setTime4Validity(String str) {
        this.time4Validity = str;
    }
}
