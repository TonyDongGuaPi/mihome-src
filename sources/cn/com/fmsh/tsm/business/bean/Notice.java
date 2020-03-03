package cn.com.fmsh.tsm.business.bean;

import java.io.Serializable;

public class Notice implements Serializable {
    public static int NOTICE_TXT = 22;
    public static int NOTICE_UNSOLVED = 33;
    private static final long serialVersionUID = 1;
    String content;
    String endDate;

    /* renamed from: no  reason: collision with root package name */
    int f550no;
    byte[] order;
    String startDate;
    String title;
    int type;

    public int getNo() {
        return this.f550no;
    }

    public void setNo(int i) {
        this.f550no = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public byte[] getOrder() {
        return this.order;
    }

    public void setOrder(byte[] bArr) {
        this.order = bArr;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
